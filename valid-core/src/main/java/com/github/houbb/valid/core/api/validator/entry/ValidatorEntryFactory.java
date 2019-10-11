package com.github.houbb.valid.core.api.validator.entry;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.validator.IValidatorEntry;

/**
 * 校验明细工具类
 * @author binbin.hou
 * @since 0.1.0
 */
public final class ValidatorEntryFactory {

    private ValidatorEntryFactory(){}

    /**
     * 指定校验的条件等相关信息
     * @param constraint 约束条件
     * @since 0.1.0
     * @return 验证明细信息
     */
    public static IValidatorEntry of(final IConstraint constraint) {
        return of(constraint, null);
    }

    /**
     * 指定校验的条件等相关信息
     * @param constraint 约束条件
     * @param message 消息
     * @since 0.1.0
     * @return 验证明细信息
     */
    public static IValidatorEntry of(final IConstraint constraint,
                                                 final String message) {
        return of(constraint, message, null);
    }

    /**
     * 指定校验的条件等相关信息
     * @param constraint 约束条件
     * @param message 消息
     * @param condition 约束条件
     * @since 0.1.0
     * @return 验证明细信息
     */
    public static IValidatorEntry of(final IConstraint constraint,
                                          final String message, final ICondition condition) {
        return of(constraint, message, condition, null);
    }

    /**
     * 指定校验的条件等相关信息
     * @param constraint 约束条件
     * @param message 消息
     * @param condition 约束条件
     * @param group 分组信息
     * @since 0.1.0
     * @return 验证明细信息
     */
    public static IValidatorEntry of(final IConstraint constraint,
                              final String message, final ICondition condition,
                              final Class[] group) {
        ArgUtil.notNull(constraint, "constraint");

        return DefaultValidatorEntry
                .newInstance()
                .constraint(constraint)
                .message(message)
                .condition(condition)
                .group(group);
    }

}
