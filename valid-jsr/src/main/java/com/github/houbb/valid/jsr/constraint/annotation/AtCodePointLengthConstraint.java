package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import org.hibernate.validator.constraints.CodePointLength;
import org.hibernate.validator.constraints.pl.REGON;

/**
 * CodePointLength 信息
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class AtCodePointLengthConstraint extends AbstractAnnotationConstraint<CodePointLength> {

    @Override
    protected IConstraint buildConstraint(CodePointLength annotation) {
        throw new UnsupportedOperationException();
    }

}
