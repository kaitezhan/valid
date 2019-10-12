package com.github.houbb.valid.core.api.validator.entry;

import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.validator.IValidatorEntry;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 默认验证器上下文
 *
 * 希望后期可以拓展：
 * <pre>
 *  ValidatorEntryChain.notNull().notEmpty().size(12)
 * </pre>
 *
 * or
 *
 * <pre>
 *  ValidatorEntryChain.addValidatorEntry(XXX).addValidatorEntry(XXX);
 * </pre>
 *
 * 直接使用链式调用的方式，生成一个完整的约束调用链。
 * 允许用户自定定义。
 * @author binbin.hou
 * @since 0.1.0
 */
public class DefaultValidatorEntry implements IValidatorEntry {

    private Object value;

    private IConstraint constraint;

    private ICondition condition;

    private String message;

    private Class[] group;

    /**
     * 对应的实例对象
     * @since 0.1.0
     */
    private Object instance;

    /**
     * 对应的字段列表
     * @since 0.1.0
     */
    private List<Field> fieldList;

    public static DefaultValidatorEntry newInstance() {
        return new DefaultValidatorEntry();
    }

    @Override
    public Object value() {
        return value;
    }

    public DefaultValidatorEntry value(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public IConstraint constraint() {
        return constraint;
    }

    public DefaultValidatorEntry constraint(IConstraint constraint) {
        this.constraint = constraint;
        return this;
    }

    @Override
    public ICondition condition() {
        return condition;
    }

    public DefaultValidatorEntry condition(ICondition condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public DefaultValidatorEntry message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public Class[] group() {
        return group;
    }

    @Override
    public DefaultValidatorEntry group(Class... group) {
        this.group = group;
        return this;
    }

    @Override
    public Object instance() {
        return instance;
    }

    public DefaultValidatorEntry instance(Object instance) {
        this.instance = instance;
        return this;
    }

    @Override
    public List<Field> fieldList() {
        return fieldList;
    }

    public DefaultValidatorEntry fieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
        return this;
    }

}
