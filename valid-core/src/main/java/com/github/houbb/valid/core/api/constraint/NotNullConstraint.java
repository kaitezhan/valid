package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

/**
 * 不可为 null 约束
 * TODO: 可以考虑约束为等于。
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class NotNullConstraint extends AbstractConstraint {

    @Override
    protected boolean isNullPass(Object value) {
        return false;
    }

    @Override
    protected boolean pass(final IConstraintContext context, final Object value) {
        return ObjectUtil.isNotNull(value);
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        return "not null";
    }

}
