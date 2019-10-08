package com.github.houbb.valid.jsr.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.core.api.constraint.AbstractGreatThanConstraint;

import java.math.BigInteger;

/**
 * 元素 BigInteger min 约束
 * （1）value 必须小于等于 max
 * @author binbin.hou
 * @since 0.0.3
 * @see BigInteger
 */
@ThreadSafe
class BigIntegerMinConstraint extends AbstractGreatThanConstraint<BigInteger> {

    public BigIntegerMinConstraint(boolean inclusive, BigInteger expect) {
        super(inclusive, expect);
    }

    public BigIntegerMinConstraint(BigInteger expect) {
        super(expect);
    }
}
