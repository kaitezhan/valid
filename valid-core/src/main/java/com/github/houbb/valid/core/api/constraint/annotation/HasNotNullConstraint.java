package com.github.houbb.valid.core.api.constraint.annotation;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.annotation.IAnnotationConstraint;
import com.github.houbb.valid.core.annotation.constraint.HasNotNull;
import com.github.houbb.valid.core.api.constraint.AbstractConstraint;

/**
 * 范围注解约束实现
 *
 * （1）对于接口的调整思考：
 * 关于验证类，是属于一大类。
 *
 * 但是对于注解类，属于另外一大类。
 * 其中，context 继承自约束类 context。
 *
 * 尽量保证二者接口的一致性。
 *
 * @author binbin.hou
 * @since 0.0.9
 */
@NotThreadSafe
public class HasNotNullConstraint extends AbstractConstraint implements IAnnotationConstraint<HasNotNull> {

    /**
     * 注解信息
     */
    private HasNotNull annotation;

    @Override
    public void init(HasNotNull annotation) {
        ArgUtil.notNull(annotation, "annotation");
        this.annotation = annotation;
    }

    @Override
    protected boolean pass(IConstraintContext context, Object value) {
        // 当前 field 信息
        // 其他 field 信息
        if(ObjectUtil.isNotNull(value)) {
            return true;
        }

        String[] otherFieldNames = annotation.value();
        for(String fieldName : otherFieldNames) {
            // 获取对应字段的值
            Object fieldValue = context.getFieldValue(fieldName);

            if(ObjectUtil.isNotNull(fieldValue)) {
                return true;
            }
        }

        return false;
    }

}
