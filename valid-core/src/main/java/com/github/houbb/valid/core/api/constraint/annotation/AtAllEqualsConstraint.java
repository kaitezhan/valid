package com.github.houbb.valid.core.api.constraint.annotation;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.annotation.constraint.AllEquals;
import com.github.houbb.valid.core.api.constraint.Constraints;

/**
 * 范围注解约束实现
 * @author binbin.hou
 * @since 0.0.9
 */
@NotThreadSafe
public class AtAllEqualsConstraint extends AbstractAnnotationConstraint<AllEquals> {

    @Override
    protected IConstraint buildConstraint(AllEquals annotation) {
        return Constraints.allEqualsConstraint(annotation.value());
    }

}
