package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import org.hibernate.validator.constraints.time.DurationMin;

/**
 * DurationMin 信息
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class AtDurationMinConstraint extends AbstractAnnotationConstraint<DurationMin> {

    @Override
    protected IConstraint buildConstraint(DurationMin annotation) {
        throw  new UnsupportedOperationException();
    }

}
