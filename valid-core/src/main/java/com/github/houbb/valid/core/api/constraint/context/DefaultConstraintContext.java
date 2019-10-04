package com.github.houbb.valid.core.api.constraint.context;

import com.github.houbb.valid.api.api.constraint.IConstraintContext;

/**
 * 默认约束条件上下文
 * @author binbin.hou
 * @since 0.0.2
 */
public class DefaultConstraintContext implements IConstraintContext {

    /**
     * 待校验的值
     * @since 0.0.2
     */
    private Object value;

    public static DefaultConstraintContext newInstance() {
        return new DefaultConstraintContext();
    }

    @Override
    public Object value() {
        return this.value;
    }

    public DefaultConstraintContext value(Object value) {
        this.value = value;
        return this;
    }

}
