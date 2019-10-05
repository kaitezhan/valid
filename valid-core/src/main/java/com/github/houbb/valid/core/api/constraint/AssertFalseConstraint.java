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
    protected boolean pass(IConstraintContext context) {
        return Boolean.FALSE.equals(context.value());
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        return "false";
    }

}
