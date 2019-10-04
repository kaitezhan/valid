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

}
