package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.core.util.NumUtil;
import com.github.houbb.valid.core.util.SupportClassTypeUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * {@link javax.validation.constraints.DecimalMax} 约束注解实现
 * @author binbin.hou
 * @since 0.0.3
 * @see BigDecimal
 * @see java.math.BigInteger
 * @see Integer
 * @see Byte
 * @see Short
 * @see Long
 * @see CharSequence
 */
@ThreadSafe
class DecimalMaxConstraint extends AbstractCombineConstraint {

    /**
     * 是否包含
     * @since 0.0.3
     */
    private final boolean inclusive;

    /**
     * 预期值
     * @since 0.0.3
     */
    private final Object expectValue;

    public DecimalMaxConstraint(boolean inclusive, Object expectValue) {
        ArgUtil.notNull(expectValue, "expectValue");
        this.inclusive = inclusive;
        this.expectValue = expectValue;
    }

    public DecimalMaxConstraint(Object expectValue) {
        this(true, expectValue);
    }

    @Override
    protected List<Class> getSupportClassList() {
        return SupportClassTypeUtil.getDecimalMaxMinSupportClassList();
    }

    /**
     * 获取对应的约束实现
     * @param context 上下文
     * @return 约束实现
     * @since 0.0.3
     */
    @Override
    protected AbstractConstraint getConstraintInstance(final IConstraintContext context) {
        final Object value = context.value();
        final Object exceptValue = super.formatValue(expectValue);

        if(value instanceof CharSequence) {
            return new BigDecimalMaxConstraint(inclusive, (BigDecimal) exceptValue);
        }

        if(value instanceof BigDecimal) {
            return new BigDecimalMaxConstraint(inclusive, (BigDecimal) exceptValue);
        }

        if(value instanceof  BigInteger) {
            return new BigIntegerMaxConstraint(inclusive, (BigInteger) exceptValue);
        }

        // 其他，直接使用 max long 进行处理
        Long longVal = NumUtil.parseLong(exceptValue);
        return new MaxConstraint(inclusive, longVal);
    }

}
