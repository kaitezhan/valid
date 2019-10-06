package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

/**
 * 为 true 约束
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
class AssertTrueConstraint extends AbstractConstraint {

    /**
     * 唯一实例
     * @since 0.0.3
     */
    private static final IConstraint INSTANCE = new AssertTrueConstraint();

    /**
     * 获取单例示例
     * @return 示例
     * @since 0.0.3
     */
    static IConstraint getInstance() {
        return INSTANCE;
    }

    @Override
    protected boolean pass(final IConstraintContext context, final Object value) {
        return Boolean.TRUE.equals(value);
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        return "true";
    }

}
