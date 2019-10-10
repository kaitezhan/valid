package com.github.houbb.valid.core.api.constraint.annotation;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.annotation.IAnnotationConstraint;
import com.github.houbb.valid.core.annotation.constraint.AllEquals;
import com.github.houbb.valid.core.api.constraint.AbstractConstraint;

/**
 * 范围注解约束实现
 * @author binbin.hou
 * @since 0.0.9
 */
@NotThreadSafe
public class AllEqualsConstraint extends AbstractConstraint implements IAnnotationConstraint<AllEquals> {

    /**
     * 注解信息
     */
    private AllEquals annotation;

    @Override
    public void init(AllEquals annotation) {
        ArgUtil.notNull(annotation, "annotation");

        this.annotation = annotation;
    }

    @Override
    protected boolean pass(IConstraintContext context, Object value) {
        String[] otherFieldNames = annotation.value();

        for(String fieldName : otherFieldNames) {
            // 获取对应字段的值
            Object fieldValue = context.getFieldValue(fieldName);

            if(!ObjectUtil.isEqualsOrNull(fieldValue, value)) {
                // 这里可以重写 message 消息。
                // 设置 context#message 信息。
                return false;
            }
        }

        return true;
    }

}
