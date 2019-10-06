package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

/**
 * 是否满足正则表达式
 * @author binbin.hou
 * @since 0.0.3
 * @see javax.validation.constraints.Pattern 正则表达式注解
 */
@ThreadSafe
public class PatternConstraint extends AbstractConstraint<CharSequence> {

    /**
     * 正则表达式
     * @since 0.0.3
     */
    private final String regex;

    public PatternConstraint(String regex) {
        this.regex = regex;
    }

    @Override
    protected boolean pass(IConstraintContext context, CharSequence value) {
        if(ObjectUtil.isNull(value)) {
            return true;
        }

        String string = value.toString();
        return string.matches(regex);
    }

    @Override
    protected String expectValue(IConstraintContext context) {
        return "match regex pattern " + regex;
    }

}
