package com.github.houbb.valid.api.api.constraint;

/**
 * 约束条件上下文接口
 * @author binbin.hou
 * @since 0.0.2
 */
public interface IConstraintContext {

    /**
     * 待验证的值
     * @return 待验证的值
     * @since 0.0.2
     */
    Object value();

    /**
     * 指定描述消息
     * @return 描述消息
     * @since 0.0.4
     */
    String message();

}
