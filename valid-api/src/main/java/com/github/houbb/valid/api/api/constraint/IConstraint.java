package com.github.houbb.valid.api.api.constraint;

/**
 * 约束规则接口
 * @author binbin.hou
 * @since 0.0.2
 */
public interface IConstraint {

    /**
     * 触发约束规则
     * @param context 上下文
     * @return 结果
     * @since 0.0.3
     */
    IConstraintResult constraint(final IConstraintContext context);

}
