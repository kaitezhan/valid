package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

/**
 * 为 true 约束
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class AssertTrueConstraint extends AbstractConstraint {

    @Override
    protected boolean pass(final IConstraintContext context, final Object value) {
        return Boolean.TRUE.equals(value);
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        return "true";
    }

}
