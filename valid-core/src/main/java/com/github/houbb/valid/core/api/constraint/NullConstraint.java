package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

/**
 * 为 null 约束
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class NullConstraint extends AbstractConstraint {

    @Override
    protected boolean pass(IConstraintContext context) {
        return ObjectUtil.isNull(context.value());
    }

    @Override
    protected String expectValue() {
        return "null";
    }

}
