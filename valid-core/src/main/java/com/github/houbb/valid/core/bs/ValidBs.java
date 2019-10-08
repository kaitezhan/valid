package com.github.houbb.valid.core.bs;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.condition.IConditionContext;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.api.api.result.IResultHandler;
import com.github.houbb.valid.api.constant.enums.FailTypeEnum;
import com.github.houbb.valid.core.api.condition.context.DefaultConditionContext;
import com.github.houbb.valid.core.api.constraint.context.DefaultConstraintContext;
import com.github.houbb.valid.core.api.result.ResultHandlers;
import com.github.houbb.valid.core.model.ConstraintEntry;
import com.github.houbb.valid.core.model.ValidEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * valid 引导类
 *
 * <pre>
 * ValidBs.failType()
 *  .on(value, validator).when("XXX")
 *  .on(value, validator)
 *  .lang(XXX)  -- 默认使用当地，不存在则使用 EN
 *  .result()
 * </pre>
 *
 * 全部提供默认值，简化如下：
 *
 * <pre>
 * ValidBs
 * .on(value, validator)
 * .result();
 * </pre>
 *
 * 所有的校验使用 lazy 模式，在 result() 调用时才进行调用。
 *
 * @author binbin.hou
 * @since 0.0.2
 */
public final class ValidBs {

    private ValidBs(){}

    /**
     * 失败模式
     * @since 0.0.2
     */
    private FailTypeEnum failType;

    /**
     * 待验证明细列表
     * @since 0.0.2
     */
    private List<ValidEntry> validEntryList;

    /**
     * 验证组信息
     * （1）如果不指定，则说明验证所有约束条件
     * （2）如果指定列表，则只验证符合当前 group 的约束条件。
     * @since 0.0.5
     */
    private Class[] validGroup;

    /**
     * 新建一个实例
     * @since 0.0.3
     * @param failType 失败类型枚举
     * @return this
     */
    public static ValidBs newInstance(final FailTypeEnum failType) {
        ValidBs validBs = new ValidBs();
        validBs.validEntryList = new ArrayList<>();
        validBs.failType(failType);
        return validBs;
    }

    /**
     * 新建一个实例
     * @since 0.0.3
     * @return this
     */
    public static ValidBs newInstance() {
        return newInstance(FailTypeEnum.FAIL_FAST);
    }

    /**
     * 指定失败类型
     * @param failType 失败类型枚举
     * @return this
     * @since 0.0.3
     */
    public ValidBs failType(FailTypeEnum failType) {
        ArgUtil.notNull(failType, "failType");
        this.failType = failType;
        return this;
    }

    /**
     * 设置验证组列表信息
     * （1）理论上这里可以为空，但是为了规范，目前做不为空的校验。
     * @param validGroup 验证组信息，禁止为空。
     * @return this
     * @since 0.0.5
     */
    public ValidBs validGroup(Class ... validGroup) {
        ArgUtil.notEmpty(validGroup, "validGroup");

        this.validGroup = validGroup;
        return this;
    }

    /**
     * 指定验证的对象
     * 问题是，如果这样，那么和 {@link #when(ICondition)} 之间的关系会怎么样？
     *
     * 最简单的方式就是直接上一个（约束链）生效，和原来一样。
     * @param value 对象信息
     * @param constraint 约束条件
     * @return this
     * @since 0.0.2
     */
    public ValidBs on(final Object value, final IConstraint constraint) {
        return this.on(value, constraint, null);
    }

    /**
     * 指定校验的条件等相关信息
     * @param value 待校验对象
     * @param constraint 约束条件
     * @param message 消息
     * @return this
     * @since 0.0.4
     */
    public ValidBs on(final Object value, final IConstraint constraint,
                      final String message) {
        return this.on(value, constraint, message, null);
    }

    /**
     * 指定校验的条件等相关信息
     * @param value 待校验对象
     * @param constraint 约束条件
     * @param message 消息
     * @param condition 约束条件
     * @return this
     * @since 0.0.4
     */
    public ValidBs on(final Object value, final IConstraint constraint,
                      final String message, final ICondition condition) {
        return this.on(value, constraint, message, condition, null);
    }

    /**
     * 指定校验的条件等相关信息
     * @param value 待校验对象
     * @param constraint 约束条件
     * @param message 消息
     * @param condition 约束条件
     * @param group 分组信息
     * @return this
     * @since 0.0.4
     */
    public ValidBs on(final Object value, final IConstraint constraint,
                      final String message, final ICondition condition,
                      final Class[] group) {
        ArgUtil.notNull(constraint, "constraint");

        ValidEntry validEntry = ValidEntry.newInstance().value(value)
                .constraint(constraint)
                .message(message)
                .condition(condition)
                .group(group);
        this.validEntryList.add(validEntry);

        return this;
    }

    /**
     * 指定单个或者多个约束条件
     *
     * 每个约束条件之间是完全独立的。
     * @param value 值
     * @param constraintEntries 约束条件列表
     * @return this
     * @since 0.0.5
     * @see #on(Object, IConstraint, String, ICondition, Class[]) 分别指定信息
     */
    public ValidBs on(final Object value, final ConstraintEntry ... constraintEntries) {
        ArgUtil.notEmpty(constraintEntries, "constraintEntries");

        for(ConstraintEntry entry : constraintEntries) {
            this.on(value, entry.constraint(), entry.message(), entry.condition(), entry.group());
        }
        return this;
    }

    /**
     * 指定上一个约束条件的生效条件
     * （1）仅仅对上一个生效，如果没有指定约束方式，则直接忽略
     * @param condition 条件
     * @return this
     * @since 0.0.2
     */
    public ValidBs when(final ICondition condition) {
        // 获取上一个信息
        if(CollectionUtil.isNotEmpty(validEntryList)) {
            ValidEntry lastEntry = validEntryList.get(validEntryList.size()-1);
            lastEntry.condition(condition);
        }

        return this;
    }

    /**
     * 指定上一个约束条件的拦截信息
     * @param message 信息
     * @return this
     * @since 0.0.4
     */
    public ValidBs message(final String message) {
        // 获取上一个信息
        if(CollectionUtil.isNotEmpty(validEntryList)) {
            ValidEntry lastEntry = validEntryList.get(validEntryList.size()-1);
            lastEntry.message(message);
        }
        return this;
    }

    /**
     * 指定上一个约束条件的分组信息
     * @param group 分组
     * @return this
     * @since 0.0.5
     */
    public ValidBs group(final Class[] group) {
        // 获取上一个信息
        if(CollectionUtil.isNotEmpty(validEntryList)) {
            ValidEntry lastEntry = validEntryList.get(validEntryList.size()-1);
            lastEntry.group(group);
        }
        return this;
    }


    /**
     * 对信息进行校验
     * （1）结合 {@link #failType} 失败模式
     * @param resultHandler 结果处理方式
     * @return 结果
     * @since 0.0.2
     * @param <T> 自定义处理结果泛型
     */
    public <T> T result(final IResultHandler<T> resultHandler) {
        ArgUtil.notNull(resultHandler, "resultHandler");

        // 执行校验
        List<IConstraintResult> constraintResultList = Guavas.newArrayList();

        for(ValidEntry validEntry : validEntryList) {
            final Object value = validEntry.value();

            // 是否满足执行条件
            if(conditionConstraint(validEntry)) {
                // 构建约束上下文
                // failType 对于 chain 也要保证语义的一致性。
                IConstraintContext constraintContext = DefaultConstraintContext.newInstance().value(value)
                        .message(validEntry.message()).failType(failType).matchGroup(validEntry.matchGroup());
                IConstraintResult constraintResult = validEntry.constraint().constraint(constraintContext);
                constraintResultList.add(constraintResult);

                // 根据失败模式返回
                if(FailTypeEnum.FAIL_FAST.equals(this.failType)
                    && !constraintResult.pass()) {
                    break;
                }
            }
        }

        // 对结果进行处理
        return resultHandler.handle(constraintResultList);
    }

    /**
     * 符合指定条件的约束信息
     * （1）判断 condition
     * （2）判断 group 信息
     *
     * （3）设置符合的 group 信息到 context 中去。
     * @param validEntry 验证明细
     * @return 是否需要执行
     * @since 0.0.5
     */
    private boolean conditionConstraint(final ValidEntry validEntry) {
        final Object value = validEntry.value();
        ICondition condition = validEntry.condition();
        final IConditionContext conditionContext = DefaultConditionContext.newInstance().value(value);

        //1. 满足 condition
        if(ObjectUtil.isNull(condition)
                || condition.condition(conditionContext)) {

            // 2. 满足 group
            // 2.1 未指定，直接认为满足
            if(ArrayUtil.isEmpty(validGroup)) {
                return true;
            }

            // 2.2 指定，则判断 group 是否满足
            Class[] constraintGroup = validEntry.group();
            // 2.2.1 约束 group 为空
            if(ArrayUtil.isEmpty(constraintGroup)) {
                return false;
            }
            // 2.2.2 遍历比较，如果有一个匹配，则直接返回 true
            for(Class validClass : validGroup) {
                //2.2.3 设置匹配的 group 到 context 中
                for(Class constraintGroupClass : constraintGroup) {
                    if(validClass == constraintGroupClass) {
                        // 设置
                        validEntry.matchGroup(validClass);

                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 获取结果
     * @return 结果
     * @since 0.0.2
     */
    public IResult result() {
        return this.result(ResultHandlers.simple());
    }

}
