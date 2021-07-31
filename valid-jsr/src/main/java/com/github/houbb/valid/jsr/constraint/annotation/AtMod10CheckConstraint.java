package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Mod10Check;

/**
 *
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class AtMod10CheckConstraint extends AbstractAnnotationConstraint<Mod10Check> {

    @Override
    protected IConstraint buildConstraint(Mod10Check annotation) {
        throw new UnsupportedOperationException();
    }

}
