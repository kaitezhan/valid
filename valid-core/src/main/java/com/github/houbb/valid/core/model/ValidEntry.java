package com.github.houbb.valid.core.model;

import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.constraint.IConstraint;

import java.util.Arrays;

/**
 * 验证的明细信息
 * @author binbin.hou
 * @since 0.0.2
 * @see ConstraintEntry 可以考虑继承这个类，暂时不做调整。
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

    /**
     * 约束分组信息
     * @since 0.0.5
     */
    private Class[] group;

    /**
     * 命中的约束分组信息类
     * @since 0.0.5
     */
    private Class matchGroup;

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

    public Class[] group() {
        return group;
    }

    public ValidEntry group(Class[] group) {
        this.group = group;
        return this;
    }

    public Class matchGroup() {
        return matchGroup;
    }

    public ValidEntry matchGroup(Class matchGroup) {
        this.matchGroup = matchGroup;
        return this;
    }

}
