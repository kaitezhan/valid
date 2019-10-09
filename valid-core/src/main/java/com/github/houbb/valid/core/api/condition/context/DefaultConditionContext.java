package com.github.houbb.valid.core.api.condition.context;

import com.github.houbb.valid.api.api.condition.IConditionContext;

/**
 * 默认的条件上下文
 * @author binbin.hou
 * @since 0.0.2
 */
public class DefaultConditionContext implements IConditionContext {

    /**
     * 当前验证值
     */
    private Object value;

    /**
     * 约束类分组信息
     * @since 0.0.7
     */
    private Class[] group;

    /**
     * 期望验证的分组信息
     * @since 0.0.7
     */
    private Class[] validGroup;

    public static DefaultConditionContext newInstance() {
        return new DefaultConditionContext();
    }

    @Override
    public Object value() {
        return value;
    }

    public DefaultConditionContext value(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public Class[] group() {
        return group;
    }

    public DefaultConditionContext group(Class[] group) {
        this.group = group;
        return this;
    }

    @Override
    public Class[] validGroup() {
        return validGroup;
    }

    public DefaultConditionContext validGroup(Class[] validGroup) {
        this.validGroup = validGroup;
        return this;
    }
}
