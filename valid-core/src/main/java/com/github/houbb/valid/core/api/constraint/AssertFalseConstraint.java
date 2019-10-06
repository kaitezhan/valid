package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

/**
 * 为 false 约束
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class AssertFalseConstraint extends AbstractConstraint {

    @Override
    protected boolean pass(final IConstraintContext context, final Object value) {
        return Boolean.FALSE.equals(value);
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        return "false";
    }

}
