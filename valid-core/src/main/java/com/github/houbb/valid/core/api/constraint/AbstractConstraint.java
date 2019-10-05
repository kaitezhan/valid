package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.core.api.constraint.result.DefaultConstraintResult;

/**
 * 抽象约束实现
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public abstract class AbstractConstraint implements IConstraint {

    /**
     * 是否通过验证
     * @param context 上下文
     * @return 是否通过
     * @since 0.0.3
     */
    protected abstract boolean pass(final IConstraintContext context);

    /**
     * 预期值
     * @param context 上下文
     * @return 预期值字符串描述
     * @since 0.0.3
     */
    protected abstract String expectValue(final IConstraintContext context);

    /**
     * 约束名称
     * @return 约束实现名称
     * @since 0.0.3
     */
    protected String constraint() {
        // 获取当前实现类的名称
        return this.getClass().getSimpleName();
    }

    /**
     * 构建信息描述
     * @param context 上下文
     * @return 信息描述
     * @since 0.0.3
     */
    protected String message(final IConstraintContext context) {
        return "Expect is <"+expectValue(context)+">, but actual is <"+context.value()+">.";
    }

    @Override
    public IConstraintResult constraint(IConstraintContext context) {
        DefaultConstraintResult result = DefaultConstraintResult.newInstance();

        Object value = context.value();
        if(pass(context)) {
            result.pass(true);
        } else {
            final String message = message(context);
            result.pass(false).message(message);
        }

        final String constraint = constraint();
        result.value(value).constraint(constraint);
        return result;
    }

}
