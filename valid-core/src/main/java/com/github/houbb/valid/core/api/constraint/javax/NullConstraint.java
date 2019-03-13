package com.github.houbb.valid.core.api.constraint.javax;

import com.github.houbb.valid.api.IConstraint;
import com.github.houbb.valid.api.IConstraintResult;
import com.github.houbb.valid.api.IContext;

/**
 * 不可为 null 的校验
 * @author binbin.hou
 * @date 2019/3/13
 * @since 0.0.1
 */
public class NullConstraint implements IConstraint {

    public IConstraintResult constraint(IContext context) {
        return null;
    }

}
