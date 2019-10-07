package com.github.houbb.valid.core.bs;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.condition.IConditionContext;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.api.api.result.IResultHandler;
import com.github.houbb.valid.core.api.condition.context.DefaultConditionContext;
import com.github.houbb.valid.core.api.constraint.context.DefaultConstraintContext;
import com.github.houbb.valid.core.api.result.ResultHandlers;
import com.github.houbb.valid.core.constant.enums.FailTypeEnum;
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
        ArgUtil.notNull(constraint, "constraint");

        ValidEntry validEntry = ValidEntry.newInstance().value(value)
                .constraint(constraint)
                .message(message)
                .condition(condition);
        this.validEntryList.add(validEntry);

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
     * 对信息进行校验
     * （1）结合 {@link #failType} 失败模式
     * @param resultHandler 结果处理方式
     * @return 结果
     * @since 0.0.2
     */
    public IResult result(final IResultHandler resultHandler) {
        ArgUtil.notNull(resultHandler, "resultHandler");

        // 执行校验
        List<IConstraintResult> constraintResultList = Guavas.newArrayList();

        for(ValidEntry validEntry : validEntryList) {
            final Object value = validEntry.value();
            ICondition condition = validEntry.condition();
            final IConditionContext conditionContext = DefaultConditionContext.newInstance().value(value);
            // 构建 condition context
            if(ObjectUtil.isNull(condition)
                || condition.condition(conditionContext)) {
                // 构建约束上下文
                IConstraintContext constraintContext = DefaultConstraintContext.newInstance().value(value)
                        .message(validEntry.message());
                IConstraintResult constraintResult = validEntry.constraint().constraint(constraintContext);
                constraintResultList.add(constraintResult);

                // 根据失败模式返回
                if(FailTypeEnum.FAIL_FAST.equals(this.failType)
                    && !constraintResult.pass()) {
                    break;
                }
            }
            //TODO: 可以添加是否进行验证的处理信息返回。
        }

        // 对结果进行处理
        return resultHandler.handle(constraintResultList);
    }

    /**
     * 获取结果
     * @return 结果
     * @since 0.0.2
     */
    public IResult result() {
        return this.result(ResultHandlers.SIMPLE);
    }

}
