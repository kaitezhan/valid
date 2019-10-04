package com.github.houbb.valid.core.api.condition;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.condition.IConditionContext;

/**
 * 永远为真条件
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class AlwaysTrueCondition implements ICondition {

    @Override
    public boolean condition(IConditionContext conditionContext) {
        return true;
    }

}
