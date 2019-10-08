package com.github.houbb.valid.jsr.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.core.api.constraint.AbstractGreatThanConstraint;

import java.math.BigDecimal;

/**
 * 元素 BigDecimal min 约束
 * （1）value 必须小于等于 max
 * @author binbin.hou
 * @since 0.0.3
 * @see BigDecimal
 */
@ThreadSafe
class BigDecimalMinConstraint extends AbstractGreatThanConstraint<BigDecimal> {

    public BigDecimalMinConstraint(boolean inclusive, BigDecimal expect) {
        super(inclusive, expect);
    }

    public BigDecimalMinConstraint(BigDecimal expect) {
        super(expect);
    }
}
