package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.pl.NIP;

/**
 * NIP 信息
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class AtNIPConstraint extends AbstractAnnotationConstraint<NIP> {

    @Override
    protected IConstraint buildConstraint(NIP annotation) {
        throw new UnsupportedOperationException();
    }

}
