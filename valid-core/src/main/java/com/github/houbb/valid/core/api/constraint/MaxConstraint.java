package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * 元素 max 约束
 * （1）value 必须小于等于 max
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
class MaxConstraint extends AbstractLessThanConstraint<Long> {

    public MaxConstraint(boolean inclusive, Long expect) {
        super(inclusive, expect);
    }

    public MaxConstraint(Long expect) {
        super(expect);
    }
}
