package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.core.util.SupportClassTypeUtil;

import java.util.List;

/**
 * 为 false 约束
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
class AssertFalseConstraint extends AbstractConstraint {

    /**
     * 唯一实例
     * @since 0.0.3
     */
    private static final IConstraint INSTANCE = new AssertFalseConstraint();

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
        return Boolean.FALSE.equals(value);
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        return "false";
    }

    @Override
    protected List<Class> getSupportClassList() {
        return SupportClassTypeUtil.getAssertTrueFalseSupportClassList();
    }
}
