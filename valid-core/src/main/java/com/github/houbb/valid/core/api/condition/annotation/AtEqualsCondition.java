package com.github.houbb.valid.core.api.condition.annotation;

import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.core.annotation.condition.EqualsCondition;
import com.github.houbb.valid.core.api.condition.Conditions;

/**
 * 相等注解实现类
 * @author binbin.hou
 * @since 0.1.3
 */
public class AtEqualsCondition extends AbstractAnnotationCondition<EqualsCondition> {

    @Override
    protected ICondition buildCondition(EqualsCondition annotation) {
        return Conditions.equalsCondition(annotation.value(), annotation.fieldName());
    }

}
