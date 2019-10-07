package com.github.houbb.valid.core.model;

import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.constraint.IConstraint;

/**
 * 验证的明细信息
 * @author binbin.hou
 * @since 0.0.2
 */
public class ValidEntry {

    /**
     * 待校验的值
     */
    private Object value;

    /**
     * 约束实现类
     */
    private IConstraint constraint;

    /**
     * 约束进行验证的条件
     */
    private ICondition condition;

    /**
     * 拦截信息
     * @since 0.0.4
     */
    private String message;

    public static ValidEntry newInstance() {
        return new ValidEntry();
    }

    public Object value() {
        return value;
    }

    public ValidEntry value(Object value) {
        this.value = value;
        return this;
    }

    public IConstraint constraint() {
        return constraint;
    }

    public ValidEntry constraint(IConstraint constraint) {
        this.constraint = constraint;
        return this;
    }

    public ICondition condition() {
        return condition;
    }

    public ValidEntry condition(ICondition condition) {
        this.condition = condition;
        return this;
    }

    public String message() {
        return message;
    }

    public ValidEntry message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "ValidEntry{" +
                "value=" + value +
                ", constraint=" + constraint +
                ", condition=" + condition +
                ", message='" + message + '\'' +
                '}';
    }
}
