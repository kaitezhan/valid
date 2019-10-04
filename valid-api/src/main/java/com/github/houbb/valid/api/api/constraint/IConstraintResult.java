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
     */
    boolean pass();

    /**
     * 消息信息
     * @return 消息
     */
    String message();

    /**
     * 未通过的限制值
     * @return 结果
     */
    Object value();

    /**
     * 约束类信息
     * @return 约束类信息
     */
    String constraint();


}
