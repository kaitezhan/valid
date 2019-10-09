package com.github.houbb.valid.core.api.condition;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.valid.api.api.condition.ICondition;

/**
 * 条件工具类
 * @author binbin.hou
 * @since 0.0.3
 */
public final class Conditions {

    private Conditions(){}

    /**
     * 永远为真
     * @since 0.0.6
     * @return 条件实现
     */
    public static ICondition alwaysTrue() {
        return Instances.singleton(AlwaysTrueCondition.class);
    }

    /**
     * 永远为假
     * @since 0.0.6
     * @return 条件实现
     */
    public static ICondition alwaysFalse() {
        return Instances.singleton(AlwaysFalseCondition.class);
    }

    /**
     * 分组条件
     * @since 0.0.7
     * @return 条件实现
     */
    public static ICondition groupCondition() {
        return Instances.singleton(GroupCondition.class);
    }

}
