package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;

import java.math.BigInteger;

/**
 * BigInteger max 约束
 * （1）value 必须小于等于 max
 * @author binbin.hou
 * @since 0.0.3
 * @see java.math.BigInteger
 */
@ThreadSafe
public class BigIntegerMaxConstraint extends AbstractLessThanConstraint<BigInteger> {

    public BigIntegerMaxConstraint(boolean inclusive, BigInteger expect) {
        super(inclusive, expect);
    }

    public BigIntegerMaxConstraint(BigInteger expect) {
        super(expect);
    }
}
