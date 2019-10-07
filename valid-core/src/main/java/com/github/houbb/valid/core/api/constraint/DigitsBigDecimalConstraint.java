package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

import java.math.BigDecimal;

/**
 * {@link javax.validation.constraints.Digits} 约束注解实现
 * @author binbin.hou
 * @since 0.0.3
 * @see java.math.BigDecimal
 */
@ThreadSafe
class DigitsBigDecimalConstraint extends AbstractDigitsConstraint<BigDecimal> {

    public DigitsBigDecimalConstraint(int integer, int fraction) {
        super(integer, fraction);
    }

    public DigitsBigDecimalConstraint(int integer) {
        super(integer);
    }

    /**
     * 用整体精度位数-小数位数
     * @param value 值
     * @return 整数位数
     */
    @Override
    protected int getIntegerDigits(BigDecimal value) {
        return value.precision() - value.scale();
    }

    @Override
    protected int getFractionDigits(BigDecimal value) {
        return value.scale();
    }

}
