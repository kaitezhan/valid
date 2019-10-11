package com.github.houbb.valid.core.api.validator.context;

import com.github.houbb.valid.api.api.fail.IFail;
import com.github.houbb.valid.api.api.validator.IValidatorContext;
import com.github.houbb.valid.api.api.validator.IValidatorEntry;

import java.util.List;

/**
 * 默认验证器上下文
 * @author binbin.hou
 * @since 0.1.0
 */
public class DefaultValidatorContext implements IValidatorContext {

    private Object value;

    private IFail fail;

    private Class[] group;

    private List<IValidatorEntry> validators;

    public static DefaultValidatorContext newInstance() {
        return new DefaultValidatorContext();
    }

    @Override
    public Object value() {
        return value;
    }

    public DefaultValidatorContext value(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public IFail fail() {
        return fail;
    }

    public DefaultValidatorContext fail(IFail fail) {
        this.fail = fail;
        return this;
    }

    @Override
    public Class[] group() {
        return group;
    }

    public DefaultValidatorContext group(Class[] group) {
        this.group = group;
        return this;
    }

    @Override
    public List<IValidatorEntry> validators() {
        return validators;
    }

    public DefaultValidatorContext validators(List<IValidatorEntry> validators) {
        this.validators = validators;
        return this;
    }
}
