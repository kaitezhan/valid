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
import com.github.houbb.valid.api.api.constraint.annotation.IAnnotationConstraint;
import com.github.houbb.valid.api.api.validator.IValidatorEntry;
import com.github.houbb.valid.core.api.validator.entry.DefaultValidatorEntry;
import com.github.houbb.valid.core.constant.AnnotationConst;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

/**
 * 默认的验证器实现
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class DefaultValidator extends AbstractValidator {

    /**
     * 获取单例实例
     * @since 0.1.1
     */
    private static final DefaultValidator INSTANCE = new DefaultValidator();

    public static DefaultValidator getInstance() {
        return INSTANCE;
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
    @Override
    @SuppressWarnings("unchecked")
    protected List<IValidatorEntry> buildValidatorEntryList(final List<Field> fieldList,
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
            annotationConstraint.initialize(annotation);
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
    protected String getMessage(final Annotation annotation) {
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
            return new Class[0];
        }

        return (Class[]) object;
    }


    /**
     * 获取对应的注解约束信息
     * @param annotation 注解
     * @return 约束实现类
     * @since 0.0.9
     */
    protected Optional<IAnnotationConstraint> constraintOptional(final Annotation annotation) {
        Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
        if (ObjectUtil.isNotNull(constraint)) {
            Class<? extends IAnnotationConstraint> clazz = constraint.value();
            return Optional.of(ClassUtil.newInstance(clazz));
        }

        return Optional.empty();
    }

}
