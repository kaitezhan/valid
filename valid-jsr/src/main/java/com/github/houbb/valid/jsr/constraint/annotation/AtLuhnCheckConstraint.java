package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import org.hibernate.validator.constraints.LuhnCheck;
import org.hibernate.validator.constraints.Mod11Check;

/**
 *
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class AtLuhnCheckConstraint extends AbstractAnnotationConstraint<LuhnCheck> {

    @Override
    protected IConstraint buildConstraint(LuhnCheck annotation) {
        throw new UnsupportedOperationException();
    }

}
