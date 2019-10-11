package com.github.houbb.valid.core.api.validator;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectAnnotationUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.Optional;
import com.github.houbb.valid.api.annotation.constraint.Constraint;
import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.api.constraint.annotation.IAnnotationConstraint;
import com.github.houbb.valid.api.api.fail.IFail;
import com.github.houbb.valid.api.api.fail.IFailContext;
import com.github.houbb.valid.api.api.validator.IValidator;
import com.github.houbb.valid.api.api.validator.IValidatorContext;
import com.github.houbb.valid.api.api.validator.IValidatorEntry;
import com.github.houbb.valid.api.constant.enums.FailTypeEnum;
import com.github.houbb.valid.core.api.condition.Conditions;
import com.github.houbb.valid.core.api.condition.chain.ConditionChains;
import com.github.houbb.valid.core.api.condition.context.DefaultConditionContext;
import com.github.houbb.valid.core.api.constraint.context.DefaultConstraintContext;
import com.github.houbb.valid.core.api.fail.context.DefaultFailContext;
import com.github.houbb.valid.core.api.validator.entry.DefaultValidatorEntry;
import com.github.houbb.valid.core.constant.AnnotationConst;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 默认的验证器实现
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class DefaultValidator implements IValidator {

    @Override
    public List<IConstraintResult> valid(IValidatorContext context) {
        List<IConstraintResult> resultList = Guavas.newArrayList();

        // 构建完整的校验对象。
        List<IValidatorEntry> beanValidatorEntries = buildValidatorEntryList(context.value());
        List<IValidatorEntry> allValidatorEntries = Guavas.newArrayList(context.validators());
        allValidatorEntries.addAll(beanValidatorEntries);
        final Class[] validGroup = context.group();
        final IFail fail = context.fail();

        // 循环执行。
        for(IValidatorEntry validatorEntry : allValidatorEntries) {
            if(conditionConstraint(validatorEntry, validGroup)) {
                // 构建约束上下文
                // fail 对于 chain 也要保证语义的一致性。
                IConstraintContext constraintContext = DefaultConstraintContext.newInstance()
                        .fail(fail)
                        .value(validatorEntry.value())
                        .message(validatorEntry.message())
                        .instance(validatorEntry.instance())
                        .fieldList(validatorEntry.fieldList());

                IConstraintResult constraintResult = validatorEntry
                        .constraint()
                        .constraint(constraintContext);

                resultList.add(constraintResult);

                // 根据失败实现进行处理 @since0.0.7
                if(!constraintResult.pass()) {
                    IFailContext failContext = DefaultFailContext.newInstance()
                            .constraintResult(constraintResult)
                            .constraintResultList(resultList);

                    FailTypeEnum failTypeEnum = fail.fail(failContext);
                    if(FailTypeEnum.FAIL_FAST.equals(failTypeEnum)) {
                        break;
                    }
                    // 后期可以添加更加丰富的失败处理策略
                }
            }
        }

        // 返回结果
        return resultList;
    }

    /**
     * 构建验证明细列表
     * @return 验证明细列表
     * @since 0.1.0
     */
    private List<IValidatorEntry> buildValidatorEntryList(final Object object) {
        if(ObjectUtil.isNull(object)) {
            return Collections.emptyList();
        }

        final Class clazz = object.getClass();
        // 不处理的类型
        if(ClassTypeUtil.isMap(clazz)
                || ClassTypeUtil.isAbstractOrInterface(clazz)
                || ClassTypeUtil.isPrimitive(clazz)
                || ClassTypeUtil.isJavaBean(clazz)) {
            return Collections.emptyList();
        }

        List<IValidatorEntry> validatorEntryList = Guavas.newArrayList();
        List<Field> fieldList = ClassUtil.getAllFieldList(clazz);
        for(Field field : fieldList) {
            List<IValidatorEntry> fieldValidatorEntry = buildValidatorEntryList(fieldList, field, object);
            validatorEntryList.addAll(fieldValidatorEntry);
        }

        return validatorEntryList;
    }
    /**
     * 符合指定条件的约束信息
     * （1）判断 condition
     * （2）判断 group 信息
     * @param validatorEntry 验证器明细
     * @param validGroup 待验证分组信息
     * @return 是否需要执行
     * @since 0.0.5
     */
    private boolean conditionConstraint(final IValidatorEntry validatorEntry,
                                        final Class[] validGroup) {
        // 是否满足执行条件
        DefaultConditionContext conditionContext = DefaultConditionContext
                .newInstance()
                .value(validatorEntry.value())
                .group(validatorEntry.group())
                .validGroup(validGroup);

        // 构建调用链
        ICondition condition = ConditionChains.chain(Conditions.groupCondition(),
                validatorEntry.condition());

        // 返回结果
        return condition.condition(conditionContext);
    }

    /**
     * 构建验证明细列表
     * TODO: 为了简化，暂时不考虑 {@link com.github.houbb.valid.api.annotation.condition.Condition} 的支持。
     *
     * @param fieldList 字段列表
     * @param field 字段信息
     * @param instance 实例
     * @return 验证明细列表
     * @since 0.0.9
     */
    @SuppressWarnings("unchecked")
    private List<IValidatorEntry> buildValidatorEntryList(final List<Field> fieldList,
                                                 final Field field,
                                                 final Object instance) {
        final List<IValidatorEntry> validatorEntryList = Guavas.newArrayList();

        Annotation[] annotations = field.getAnnotations();

        for(Annotation annotation : annotations) {
            Optional<IAnnotationConstraint> constraintOptional = constraintOptional(annotation);
            if(constraintOptional.isNotPresent()) {
                continue;
            }

            // 构建上下文
            IAnnotationConstraint annotationConstraint = constraintOptional.get();
            annotationConstraint.init(annotation);
            final Object fieldValue = ReflectFieldUtil.getValue(field, instance);

            IValidatorEntry validatorEntry = DefaultValidatorEntry.newInstance()
                    .message(getMessage(annotation))
                    .group(getGroup(annotation))
                    .constraint(annotationConstraint)
                    .fieldList(fieldList)
                    .instance(instance)
                    .value(fieldValue);
            validatorEntryList.add(validatorEntry);

            // 进一步处理 valid 明细
            Valid valid = field.getAnnotation(Valid.class);
            if(ObjectUtil.isNotNull(valid)
                    && ObjectUtil.isNotNull(fieldValue)) {
                final Class fieldClazz = field.getType();
                // 判断是否为集合
                if(ClassTypeUtil.isCollection(fieldClazz)) {
                    Collection collection = (Collection)fieldValue;
                    for(Object entry : collection) {
                        List<IValidatorEntry> entryList = this.buildValidatorEntryList(entry);
                        validatorEntryList.addAll(entryList);
                    }
                } else if(ClassTypeUtil.isArray(fieldClazz)) {
                    // 是否为数组
                    ArrayUtil.toList(fieldValue, new IHandler() {
                        @Override
                        public Object handle(Object o) {
                            List<IValidatorEntry> entryList = buildValidatorEntryList(o);
                            validatorEntryList.addAll(entryList);
                            return o;
                        }
                    });
                } else {
                    // 其他场景
                    List<IValidatorEntry> entryList = buildValidatorEntryList(fieldValue);
                    validatorEntryList.addAll(entryList);
                }
            }
        }

        return validatorEntryList;
    }

    /**
     * 获取注解对应的 message 信息
     * @param annotation 注解
     * @return message() 方法对应的值。
     * @since 0.0.9
     */
    private String getMessage(final Annotation annotation) {
        return ReflectAnnotationUtil.getValueStr(annotation, AnnotationConst.MESSAGE);
    }

    /**
     * 获取注解对应的 message 信息
     *
     * @param annotation 注解
     * @return message() 方法对应的值。
     * @since 0.0.9
     */
    private Class[] getGroup(final Annotation annotation) {
        Object object = ReflectAnnotationUtil.getValue(annotation, AnnotationConst.GROUP);

        if(ObjectUtil.isNull(object)) {
            return null;
        }

        return (Class[]) object;
    }


    /**
     * 获取对应的注解约束信息
     * @param annotation 注解
     * @return 约束实现类
     * @since 0.0.9
     */
    private Optional<IAnnotationConstraint> constraintOptional(final Annotation annotation) {
        Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
        if (ObjectUtil.isNotNull(constraint)) {
            Class<? extends IAnnotationConstraint> clazz = constraint.value();
            return Optional.of(ClassUtil.newInstance(clazz));
        }

        return Optional.empty();
    }

}
