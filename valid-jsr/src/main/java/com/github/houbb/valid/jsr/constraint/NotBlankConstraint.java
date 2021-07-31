package com.github.houbb.valid.jsr.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.core.api.constraint.AbstractConstraint;
import com.github.houbb.valid.jsr.util.JsrSupportClassUtil;

import java.util.List;

/**
 * 为字符串不能为空格
 *
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
class NotBlankConstraint extends AbstractConstraint<String> {

    /**
     * 唯一实例
     * @since 0.2.0
     */
    private static final IConstraint INSTANCE = new NotBlankConstraint();

    /**
     * 获取单例示例
     * @return 示例
     * @since 0.2.0
     */
    static IConstraint getInstance() {
        return INSTANCE;
    }

    @Override
    protected boolean pass(IConstraintContext context, String value) {
        return StringUtil.isNotBlank(value);
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        return (String) context.value();
    }

}
