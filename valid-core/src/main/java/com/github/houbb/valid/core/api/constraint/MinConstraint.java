package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

/**
 * 元素 min 约束
 * （1）value 必须大于等于 min
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class MinConstraint extends AbstractConstraint {

    /**
     * 是否包含等于，默认为等于
     * @since 0.0.3
     */
    private final boolean inclusive;

    /**
     * 最小值
     * @since 0.0.3
     */
    private final long min;

    public MinConstraint(boolean inclusive, long min) {
        this.inclusive = inclusive;
        this.min = min;
    }

    public MinConstraint(long min) {
        this(true, min);
    }

    @Override
    protected boolean pass(IConstraintContext context) {
        final long value = (long) context.value();
        if(inclusive) {
            return value >= min;
        }
        return value > min;
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        if(inclusive) {
            return "great than equals "+min;
        }
        return "great than " + min;
    }

}
