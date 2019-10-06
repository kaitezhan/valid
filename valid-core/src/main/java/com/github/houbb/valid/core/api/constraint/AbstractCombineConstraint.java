package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

import java.util.List;

/**
 * 抽象组合约束实现
 * 适用场景：一个注解对应多个约束实现类。
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public abstract class AbstractCombineConstraint extends AbstractConstraint {

    /**
     * 获取支持的数据类型列表
     * @return 支持的类型列表
     * @since 0.0.3
     */
    protected abstract List<Class> getSupportClassList();

    /**
     * 获取对应的约束实现
     * @param context 上下文
     * @return 约束实现
     * @since 0.0.3
     */
    protected abstract AbstractConstraint getConstraintInstance(final IConstraintContext context);

    @Override
    protected boolean supportClassType(Class valueClassType) {
        return getSupportClassList().contains(valueClassType);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean pass(IConstraintContext context, Object value) {
        if(ObjectUtil.isNull(value)) {
            return true;
        }

        AbstractConstraint abstractConstraint = getConstraintInstance(context);
        return abstractConstraint.pass(context, value);
    }

    @Override
    protected String expectValue(IConstraintContext context) {
        AbstractConstraint abstractConstraint = getConstraintInstance(context);
        return abstractConstraint.expectValue(context);
    }

}
