package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.heaven.util.util.ArrayUtil;

import java.util.List;

/**
 * 范围注解约束实现
 * @author binbin.hou
 * @since 0.0.9
 */
@NotThreadSafe
class RangesConstraint extends AbstractContainsConstraint {

    /**
     * 元素信息
     */
    private final Object[] objects;

    RangesConstraint(Object[] objects) {
        this.objects = objects;
    }

    @Override
    protected List ranges() {
        return ArrayUtil.arrayToList(objects);
    }
}
