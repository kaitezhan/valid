package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * {@link javax.validation.constraints.Digits} 约束注解实现
 * @author binbin.hou
 * @since 0.0.3
 * @see CharSequence
 * @see BigDecimal
 * @see BigInteger
 * @see Integer
 * @see Byte
 * @see Short
 * @see Long
 *
 * @param <T> 泛型
 * @since 0.0.3
 */
@ThreadSafe
abstract class AbstractDigitsConstraint<T> extends AbstractConstraint<T> {

    /**
     * 整数部分最多多少位
     * @since 0.0.3
     */
    private final int integer;

    /**
     * 小数部分最多多少位
     * @since 0.0.3
     */
    private final int fraction;

    /**
     * 构造器
     * @param integer 整数位数
     * @param fraction 小数位数
     * @since 0.0.3
     */
    public AbstractDigitsConstraint(int integer, int fraction) {
        this.integer = integer;
        this.fraction = fraction;
    }

    /**
     * 构造器
     * @param integer 整数位数
     * @since 0.0.3
     */
    public AbstractDigitsConstraint(int integer) {
        this(integer, 0);
    }

    /**
     * 获取整数部分位数
     * @param value 值
     * @return 位数
     * @since 0.0.3
     */
    protected abstract int getIntegerDigits(final T value);

    /**
     * 获取小数位数
     * @param value 值
     * @return 位数
     * @since 0.0.3
     */
    protected int getFractionDigits(final T value) {
        return 0;
    }

    @Override
    protected boolean pass(IConstraintContext context, T value) {
        final int integerDigits = getIntegerDigits(value);
        final int fractionDigits = getFractionDigits(value);

        if(integerDigits <= integer
            && fractionDigits <= fraction) {
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected String expectValue(IConstraintContext context) {
        final T value = (T)context.value();
        return String.format("integer digits <%d>, fraction digits <%d>",
                getIntegerDigits(value), getFractionDigits(value));
    }

}
