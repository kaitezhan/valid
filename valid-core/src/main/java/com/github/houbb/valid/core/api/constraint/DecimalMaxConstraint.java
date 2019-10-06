package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

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
public class DecimalMaxConstraint extends AbstractCombineConstraint {

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

        if(value instanceof CharSequence) {
            CharSequence charSequence = (CharSequence)value;
            BigDecimal bigDecimal = new BigDecimal(charSequence.toString());
            return new BigDecimalMaxConstraint(bigDecimal);
        }

        if(value instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal)value;
            return new BigDecimalMaxConstraint(bigDecimal);
        }

        if(value instanceof  BigInteger) {
            BigInteger bigInteger = (BigInteger)value;
            return new BigIntegerMaxConstraint(bigInteger);
        }

        // 其他，直接使用 max long 进行处理
        Long longValue = (Long)value;
        return new MaxConstraint(longValue);
    }

}
