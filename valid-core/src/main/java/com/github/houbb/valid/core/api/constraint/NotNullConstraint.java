package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

/**
 * 不可为 null 约束
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class NotNullConstraint extends AbstractConstraint {

    @Override
    protected boolean pass(IConstraintContext context) {
        return ObjectUtil.isNotNull(context.value());
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        return "not null";
    }

}
