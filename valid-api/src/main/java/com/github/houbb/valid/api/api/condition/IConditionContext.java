package com.github.houbb.valid.api.api.condition;

/**
 * 条件生效的上下文
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IConditionContext {

    /**
     * 待验证的值
     * @return 待验证的值
     * @since 0.0.2
     */
    Object value();

    /**
     * 约束类分组信息
     * @return 分组信息
     * @since 0.0.7
     */
    Class[] group();

    /**
     * 期望验证的分组信息
     * @return 分组信息
     * @since 0.0.7
     */
    Class[] validGroup();


}
