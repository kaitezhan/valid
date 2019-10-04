package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.core.api.constraint.result.DefaultConstraintResult;

/**
 * 不可为 null 约束
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class NotNullConstraint implements IConstraint {

    @Override
    public IConstraintResult constraint(IConstraintContext context) {
        DefaultConstraintResult result = DefaultConstraintResult.newInstance();

        Object value = context.value();
        if(ObjectUtil.isNull(value)) {
            result.pass(false).message("expect not null, but actual is null.");
        } else {
            result.pass(true);
        }

        result.value(value).constraint(this.getClass().getSimpleName());
        return result;
    }

}
