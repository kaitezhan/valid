package com.github.houbb.valid.jsr.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.core.api.constraint.AbstractLessThanConstraint;

import java.math.BigInteger;

/**
 * BigInteger max 约束
 * （1）value 必须小于等于 max
 * @author binbin.hou
 * @since 0.0.3
 * @see BigInteger
 */
@ThreadSafe
class BigIntegerMaxConstraint extends AbstractLessThanConstraint<BigInteger> {

    public BigIntegerMaxConstraint(boolean inclusive, BigInteger expect) {
        super(inclusive, expect);
    }

    public BigIntegerMaxConstraint(BigInteger expect) {
        super(expect);
    }
}
