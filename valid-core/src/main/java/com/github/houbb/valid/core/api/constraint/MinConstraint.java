package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * 元素 min 约束
 * （1）value 必须大于等于 min
 * @author binbin.hou
 * @since 0.0.3
 * @see Integer
 * @see Short
 * @see Byte
 */
@ThreadSafe
class MinConstraint extends AbstractGreatThanConstraint<Long> {

    public MinConstraint(boolean inclusive, Long expect) {
        super(inclusive, expect);
    }

    public MinConstraint(Long expect) {
        super(expect);
    }
}
