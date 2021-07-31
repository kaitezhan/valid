package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 * UniqueElements 信息
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class AtUniqueElementsConstraint extends AbstractAnnotationConstraint<UniqueElements> {

    @Override
    protected IConstraint buildConstraint(UniqueElements annotation) {
        return JsrConstraints.uniqueElementsConstraint();
    }

}
