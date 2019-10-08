package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.core.api.constraint.result.DefaultConstraintResult;

import java.lang.reflect.Array;
import java.util.List;

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
     * 确切的值
     * （1）但是为了保证线程安全，此处不建议使用内部变量。
     * （2）可以考虑 {@link ThreadLocal} 结合 value 保存结果，然后取出。
     *
     * 建议使用这种方式：
     * （3）结合 {@link IConstraintContext#putAttr(String, Object)} 设置和获取
     * @param context 上下文
     * @return 确切的值
     * @since 0.0.3
     */
    protected String actualValue(final IConstraintContext context) {
        return StringUtil.objectToString(context.value());
    }

    /**
     * 获取支持的数据类型列表
     * @return 支持的类型列表
     * @since 0.0.3
     */
    protected List<Class> getSupportClassList() {
        List<Class> classList = Guavas.newArrayList(1);
        classList.add(Object.class);
        return classList;
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
     * （1）防止痴呆设计，给出提示的具体信息。
     * （2）如果用户指定了自己的描述，则直接返回用户自定义的信息。@since 0.0.4
     * @param context 上下文
     * @return 信息描述
     * @since 0.0.3
     */
    protected String message(final IConstraintContext context) {
        final String defineMsg = context.message();
        if(StringUtil.isNotEmpty(defineMsg)) {
            return defineMsg;
        }
        return "Expect is <"+expectValue(context)+">, but actual is <"+actualValue(context)+">.";
    }

    /**
     * 为 null 的时候，是否验证通过。
     * 根据 JDK-303 标准，除了 javax.validation.constraints.NotNull 需要验证为 Null,其他都是通过的。
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
        result.value(value).constraint(constraint).matchGroup(context.matchGroup());
        // 如果未通过校验，则添加预期值
        if(!result.pass()) {
            final String expectValue = this.expectValue(context);
            result.expectValue(expectValue);
        }
        return result;
    }

    /**
     * 是否支持的数据字段类型
     * （1）是否为指定类型的子类
     * （2）是否与指定类型相同
     * （3）数组的支持，通过类型 {@link Array} 进行指定。
     * @param valueClassType 当前字段类型
     * @return true
     * @since 0.0.3
     */
    @SuppressWarnings("unchecked")
    private boolean supportClassType(final Class valueClassType) {
        List<Class> classList = getSupportClassList();
        for(Class supportClass : classList) {
            if(supportClass.isAssignableFrom(valueClassType)
                    || supportClass == valueClassType) {
                return true;
            }
            // 对数组的支持
            if(Array.class == supportClass
                && valueClassType.isArray()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 支持的（数据）类型
     * （1）null 默认支持
     * （2）防止痴呆设计，自动提示出支持的数据类型。
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
            final String tips = String.format("Valid un-support class type <%s> for constraint: <%s>, supported class list: <%s>", valueClass, this.constraint(), this.getSupportClassList());
            throw new ClassCastException(tips);
        }
    }

}
