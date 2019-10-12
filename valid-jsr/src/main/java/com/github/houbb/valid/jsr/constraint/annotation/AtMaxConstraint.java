package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;

import javax.validation.constraints.Max;

/**
 * @author binbin.hou
 * @since 0.1.1
 */
@ThreadSafe
public class AtMaxConstraint extends AbstractAnnotationConstraint<Max> {

    @Override
    protected IConstraint buildConstraint(Max annotation) {
        return JsrConstraints.maxConstraint(annotation.value());
    }

}
