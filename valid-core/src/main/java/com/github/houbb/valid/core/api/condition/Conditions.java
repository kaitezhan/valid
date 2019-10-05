package com.github.houbb.valid.core.api.condition;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.valid.api.api.condition.ICondition;

/**
 * 条件工具类
 * @author binbin.hou
 * @since 0.0.3
 */
public class Conditions {

    /**
     * 永远为真
     */
    public static final ICondition ALWAYS_TRUE = Instances.singleton(AlwaysTrueCondition.class);

    /**
     * 永远为假
     */
    public static final ICondition ALWAYS_FALSE = Instances.singleton(AlwaysFalseCondition.class);

}
