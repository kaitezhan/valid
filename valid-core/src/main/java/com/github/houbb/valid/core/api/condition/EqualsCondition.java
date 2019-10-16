package com.github.houbb.valid.core.api.condition;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.condition.IConditionContext;

/**
 * 等于预期值时生效的条件
 * @author binbin.hou
 * @since 0.1.3
 */
@ThreadSafe
public class EqualsCondition implements ICondition {

    /**
     * 预期值
     * 注意：因为 jsr 标准本身对于 null 就有对应的处理，所以这里预期值禁止为 null
     */
    private final Object expect;

    /**
     * 字段名称
     * @since 0.1.3
     */
    private final String fieldName;

    public EqualsCondition(Object expect) {
        this(expect, null);
    }

    public EqualsCondition(Object expect, String fieldName) {
        ArgUtil.notNull(expect, "expect");
        this.expect = expect;
        this.fieldName = fieldName;
    }

    @Override
    public boolean condition(IConditionContext conditionContext) {
        final Object value = getFieldValue(conditionContext);
        if(ObjectUtil.isNull(value)) {
            return false;
        }

        return expect.equals(value);
    }

    /**
     * 获取对应的字段值
     * @param conditionContext 条件信息
     * @return 字段值
     * @since 0.1.3
     */
    private Object getFieldValue(IConditionContext conditionContext) {
        if(StringUtil.isEmpty(fieldName)) {
            return conditionContext.value();
        }

        return conditionContext.getFieldValue(fieldName);
    }

}
