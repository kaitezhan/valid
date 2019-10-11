package com.github.houbb.valid.core.api.validator;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.api.validator.IValidator;
import com.github.houbb.valid.api.api.validator.IValidatorContext;

import java.util.List;

/**
 * 默认的验证器实现
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class AbstractValidator implements IValidator {

    @Override
    public List<IConstraintResult> valid(IValidatorContext context) {
        return null;
    }

}
