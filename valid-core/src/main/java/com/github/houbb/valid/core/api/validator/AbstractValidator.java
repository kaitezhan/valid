package com.github.houbb.valid.core.api.validator;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.api.fail.IFail;
import com.github.houbb.valid.api.api.fail.IFailContext;
import com.github.houbb.valid.api.api.validator.IValidator;
import com.github.houbb.valid.api.api.validator.IValidatorContext;
import com.github.houbb.valid.api.api.validator.IValidEntry;
import com.github.houbb.valid.api.constant.enums.FailTypeEnum;
import com.github.houbb.valid.core.api.condition.Conditions;
import com.github.houbb.valid.core.api.condition.chain.ConditionChains;
import com.github.houbb.valid.core.api.condition.context.DefaultConditionContext;
import com.github.houbb.valid.core.api.constraint.context.DefaultConstraintContext;
import com.github.houbb.valid.core.api.fail.context.DefaultFailContext;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/**
 * 抽象的验证器实现
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public abstract class AbstractValidator implements IValidator {

    /**
     * 构建验证明细列表
     * TODO: 为了简化，暂时不考虑 {@link com.github.houbb.valid.api.annotation.condition.Condition} 的支持。
     *
     * @param fieldList 字段列表
     * @param field 字段信息
     * @param instance 实例
     * @return 验证明细列表
     * @since 0.1.1
     */

    protected abstract List<IValidEntry> buildValidatorEntryList(final List<Field> fieldList,
                                                                 final Field field,
                                                                 final Object instance);

    /**
     * 构建验证明细列表
     * （1）null 对象直接返回
     * （2）map/抽象类接口/jdk自带类/基础变量 直接返回
     * （3）字段列表为空的，直接返回。
     * @return 验证明细列表
     * @since 0.1.0
     */
    protected List<IValidEntry> buildValidatorEntryList(final Object object) {
        if(ObjectUtil.isNull(object)) {
            return Collections.emptyList();
        }

        final Class clazz = object.getClass();
        // 不处理的类型
        if(ClassTypeUtil.isMap(clazz)
                || ClassTypeUtil.isAbstractOrInterface(clazz)
                || ClassTypeUtil.isPrimitive(clazz)
                || ClassTypeUtil.isJdk(clazz)) {
            return Collections.emptyList();
        }
        // 字段为空
        List<Field> fieldList = ClassUtil.getAllFieldList(clazz);
        if(CollectionUtil.isEmpty(fieldList)) {
            return Collections.emptyList();
        }

        List<IValidEntry> validatorEntryList = Guavas.newArrayList();
        for(Field field : fieldList) {
            List<IValidEntry> fieldValidatorEntry = buildValidatorEntryList(fieldList, field, object);
            validatorEntryList.addAll(fieldValidatorEntry);
        }

        return validatorEntryList;
    }

    @Override
    public List<IConstraintResult> valid(IValidatorContext context) {
        List<IConstraintResult> resultList = Guavas.newArrayList();

        // 构建完整的校验对象。
        List<IValidEntry> beanValidatorEntries = buildValidatorEntryList(context.value());
        List<IValidEntry> allValidatorEntries = Guavas.newArrayList(context.validatorEntries());
        allValidatorEntries.addAll(beanValidatorEntries);
        final Class[] validGroup = context.group();
        final IFail fail = context.fail();

        // 循环执行。
        for(IValidEntry validatorEntry : allValidatorEntries) {
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
     * 符合指定条件的约束信息
     * （1）判断 condition
     * （2）判断 group 信息
     * @param validatorEntry 验证器明细
     * @param validGroup 待验证分组信息
     * @return 是否需要执行
     * @since 0.0.5
     */
    private boolean conditionConstraint(final IValidEntry validatorEntry,
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

}
