package com.github.houbb.valid.core.api.constraint.annotation;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.annotation.constraint.EnumRanges;
import com.github.houbb.valid.core.api.constraint.Constraints;

/**
 * 范围注解约束实现
 * @author binbin.hou
 * @since 0.0.9
 */
@NotThreadSafe
public class AtEnumRangesConstraint extends AbstractAnnotationConstraint<EnumRanges> {

    @Override
    protected IConstraint buildConstraint(EnumRanges annotation) {
        return Constraints.enumRangesConstraint(annotation.value());
    }

}
