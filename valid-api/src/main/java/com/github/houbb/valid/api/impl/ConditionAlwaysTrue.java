package com.github.houbb.valid.api.impl;

import com.github.houbb.valid.api.ICondition;
import com.github.houbb.valid.api.IConditionContext;

/**
 * 永远生效的条件
 * @author binbin.hou
 * @since 0.0.1
 */
public class ConditionAlwaysTrue implements ICondition {

    @Override
    public boolean condition(final IConditionContext conditionContext) {
        return true;
    }

}
