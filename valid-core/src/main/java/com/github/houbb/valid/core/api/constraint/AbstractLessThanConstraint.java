package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

/**
 * 抽象小于等于约束实现
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
class AbstractLessThanConstraint<T extends Comparable> extends AbstractConstraint {

    /**
     * 是否包含等于，默认为等于
     * @since 0.0.3
     */
    protected final boolean inclusive;

    /**
     * 预期的值
     * @since 0.0.3
     */
    protected final T expect;

    public AbstractLessThanConstraint(boolean inclusive, T expect) {
        this.inclusive = inclusive;
        this.expect = expect;
    }

    /**
     * 默认为包含等于的情况
     * @param expect 预期值
     * @since 0.0.3
     */
    public AbstractLessThanConstraint(T expect) {
        this(true, expect);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected boolean pass(final IConstraintContext context, final Object value) {
        final T comparableValue = (T)value;
        if(inclusive) {
            return comparableValue.compareTo(expect) <= 0;
        }
        return comparableValue.compareTo(expect) < 0;
    }

    @Override
    protected String expectValue(IConstraintContext context) {
        final String value = expect.toString();

        if(inclusive) {
            return "less than or equals " + value;
        }
        return "less than " + value;
    }

}
