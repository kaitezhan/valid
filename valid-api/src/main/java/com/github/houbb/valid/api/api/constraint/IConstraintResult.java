package com.github.houbb.valid.api.api.constraint;

/**
 * 约束规则结果接口
 * @author binbin.hou
 * @since 0.0.2
 */
public interface IConstraintResult {

    /**
     * 是否通过约束限制
     * @return 是否
     * @since 0.0.2
     */
    boolean pass();

    /**
     * 消息信息
     * @return 消息
     * @since 0.0.2
     */
    String message();

    /**
     * 未通过的限制值
     * @return 结果
     * @since 0.0.2
     */
    Object value();

    /**
     * 约束类信息
     * @return 约束类信息
     * @since 0.0.2
     */
    String constraint();

    /**
     * 预期值
     * （1）主要用于约束验证链信息拼接。
     * @return 预期值
     * @since 0.0.4
     */
    String expectValue();

    /**
     * 匹配的分组信息
     * @since 0.0.5
     * @return 匹配分组信息
     */
    Class matchGroup();

}
