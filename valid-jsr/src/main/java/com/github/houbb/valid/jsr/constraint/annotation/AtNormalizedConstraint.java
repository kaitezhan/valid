package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.hibernate.validator.constraints.Normalized;

import javax.validation.constraints.NotNull;

/**
 *
 * @author binbin.hou
 * @since 0.1.1
 */
@ThreadSafe
public class AtNormalizedConstraint extends AbstractAnnotationConstraint<Normalized> {

    @Override
    protected IConstraint buildConstraint(Normalized annotation) {
        throw new UnsupportedOperationException();
    }

}
