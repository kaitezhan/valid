package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import org.hibernate.validator.constraints.ScriptAssert;
import org.hibernate.validator.constraints.pl.NIP;

/**
 * ScriptAssert 信息
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class AtScriptAssertConstraint extends AbstractAnnotationConstraint<ScriptAssert> {

    @Override
    protected IConstraint buildConstraint(ScriptAssert annotation) {
        throw new UnsupportedOperationException();
    }

}
