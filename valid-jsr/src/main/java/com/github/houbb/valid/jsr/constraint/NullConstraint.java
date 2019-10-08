package com.github.houbb.valid.jsr.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.core.api.constraint.AbstractConstraint;

/**
 * 为 null 约束
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
class NullConstraint extends AbstractConstraint {

    /**
     * 唯一实例
     * @since 0.0.3
     */
    private static final IConstraint INSTANCE = new NullConstraint();

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
        return ObjectUtil.isNull(value);
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        return "null";
    }

}
