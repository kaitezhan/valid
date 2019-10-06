package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * {@link javax.validation.constraints.DecimalMin} 约束注解实现
 * @author binbin.hou
 * @since 0.0.3
 * @see BigDecimal
 * @see BigInteger
 * @see Integer
 * @see Byte
 * @see Short
 * @see Long
 * @see CharSequence
 */
@ThreadSafe
class DecimalMinConstraint extends AbstractCombineConstraint {

    /**
     * 预期值
     * @since 0.0.3
     */
    private final Object expectValue;

    public DecimalMinConstraint(Object expectValue) {
        ArgUtil.notNull(expectValue, "expectValue");
        this.expectValue = expectValue;
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
        final Object exceptValue = super.formatValue(value);

        if(value instanceof CharSequence) {
            return new BigDecimalMinConstraint((BigDecimal) exceptValue);
        }

        if(value instanceof BigDecimal) {
            return new BigDecimalMinConstraint((BigDecimal) exceptValue);
        }

        if(value instanceof  BigInteger) {
            return new BigIntegerMinConstraint((BigInteger) exceptValue);
        }

        // 其他，直接使用 Min long 进行处理
        return new MinConstraint((Long) exceptValue);
    }

}
