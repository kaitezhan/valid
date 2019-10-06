package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;

import java.math.BigDecimal;

/**
 * BigDecimal max 约束
 * （1）value 必须小于等于 max
 * @author binbin.hou
 * @since 0.0.3
 * @see BigDecimal
 */
@ThreadSafe
public class BigDecimalMaxConstraint extends AbstractLessThanConstraint<BigDecimal> {

    public BigDecimalMaxConstraint(boolean inclusive, BigDecimal expect) {
        super(inclusive, expect);
    }

    public BigDecimalMaxConstraint(BigDecimal expect) {
        super(expect);
    }
}
