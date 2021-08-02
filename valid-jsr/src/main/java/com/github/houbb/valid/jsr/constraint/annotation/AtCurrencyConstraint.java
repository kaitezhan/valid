package com.github.houbb.valid.jsr.constraint.annotation;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AbstractAnnotationConstraint;
import org.hibernate.validator.constraints.Currency;

/**
 * Currency 信息
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class AtCurrencyConstraint extends AbstractAnnotationConstraint<Currency> {

    @Override
    protected IConstraint buildConstraint(Currency annotation) {
        throw  new UnsupportedOperationException();
    }

}
