package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

/**
 * 元素 max 约束
 * （1）value 必须小于等于 max
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class MaxConstraint extends AbstractConstraint {

    /**
     * 是否包含等于，默认为等于
     * @since 0.0.3
     */
    private final boolean inclusive;

    /**
     * 最大值
     * @since 0.0.3
     */
    private final long max;

    public MaxConstraint(boolean inclusive, long max) {
        this.inclusive = inclusive;
        this.max = max;
    }

    public MaxConstraint(long max) {
        this(true, max);
    }

    @Override
    protected boolean pass(IConstraintContext context) {
        final long value = (long) context.value();
        if(inclusive) {
            return value <= max;
        }
        return value < max;
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        if(inclusive) {
            return "less than equals "+max;
        }
        return "less than " + max;
    }

}
