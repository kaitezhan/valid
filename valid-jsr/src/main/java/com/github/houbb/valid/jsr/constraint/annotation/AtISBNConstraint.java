package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class AtISBNConstraint extends AbstractAnnotationConstraint<ISBN> {

    @Override
    protected IConstraint buildConstraint(ISBN annotation) {
        throw new UnsupportedOperationException();
    }

}
