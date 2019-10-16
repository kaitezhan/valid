package com.github.houbb.valid.core.api.condition.annotation;

import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.core.annotation.condition.NotEqualsCondition;
import com.github.houbb.valid.core.api.condition.Conditions;

/**
 * 抽象注解条件接口
 * @author binbin.hou
 * @since 0.1.3
 */
public class AtNotEqualsCondition extends AbstractAnnotationCondition<NotEqualsCondition> {

    @Override
    protected ICondition buildCondition(NotEqualsCondition annotation) {
        return Conditions.notEqualsCondition(annotation.value(), annotation.fieldName());
    }

}
