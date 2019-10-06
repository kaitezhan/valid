package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.exception.ValidRuntimeException;
import com.github.houbb.valid.core.api.constraint.result.DefaultConstraintResult;

/**
 * 抽象约束实现
 * @author binbin.hou
 * @since 0.0.3
 * @param <T> 泛型
 */
@ThreadSafe
public abstract class AbstractConstraint<T> implements IConstraint {

    /**
     * 是否通过验证
     * （1）当前校验值非常需要使用，所以为了方便，直接放在子类属性中。
     * @param context 上下文
     * @param value 当前校验值
     * @return 是否通过
     * @since 0.0.3
     */
    protected abstract boolean pass(final IConstraintContext context, final T value);

    /**
     * 预期值
     * @param context 上下文
     * @return 预期值字符串描述
     * @since 0.0.3
     */
    protected abstract String expectValue(final IConstraintContext context);

    /**
     * 是否支持的数据字段类型
     * （1）不同的实现类可以重写此方法。
     * @param valueClassType 当前字段类型
     * @return true
     * @since 0.0.3
     */
    protected boolean supportClassType(final Class valueClassType) {
        return true;
    }

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

    /**
     * 为 null 的时候，是否验证通过。
     * 根据 JDK-303 标准，除了 {@link javax.validation.constraints.NotNull} 需要验证为 Null,其他都是通过的。
     * @param value 值
     * @return 是否通过
     * @since 0.0.3
     */
    protected boolean isNullPass(final T value) {
        if(ObjectUtil.isNull(value)) {
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IConstraintResult constraint(IConstraintContext context) {
        // 类型是否支持校验
        supportType(context);

        DefaultConstraintResult result = DefaultConstraintResult.newInstance();

        T value = (T) context.value();

        if(isNullPass(value) || pass(context, value)) {
            result.pass(true);
        } else {
            final String message = message(context);
            result.pass(false).message(message);
        }

        final String constraint = constraint();
        result.value(value).constraint(constraint);
        return result;
    }

    /**
     * 支持的（数据）类型
     * （1）null 默认支持
     * @param context 上下文
     * @since 0.0.3
     */
    private void supportType(final IConstraintContext context) {
        final Object value = context.value();
        if(null == value) {
            return;
        }

        final Class valueClass = value.getClass();
        boolean supportClassType = this.supportClassType(valueClass);
        if(!supportClassType) {
            final String tips = String.format("UnSupport class type <%s> for constraint: <%s>",
                    valueClass, this.constraint());
            throw new ValidRuntimeException(tips);
        }
    }

}
