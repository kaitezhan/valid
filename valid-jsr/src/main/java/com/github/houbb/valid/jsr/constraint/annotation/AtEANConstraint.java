package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.hibernate.validator.constraints.EAN;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class AtEANConstraint extends AbstractAnnotationConstraint<EAN> {

    @Override
    protected IConstraint buildConstraint(EAN annotation) {
        throw new UnsupportedOperationException();
    }

}
