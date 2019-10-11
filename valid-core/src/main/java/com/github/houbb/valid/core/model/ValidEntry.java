package com.github.houbb.valid.core.model;

import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.constraint.IConstraint;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 验证的明细信息
 * @author binbin.hou
 * @since 0.0.2
 * @see ConstraintEntry 可以考虑继承这个类，暂时不做调整。
 */
public class ValidEntry {

    /**
     * 待校验的值
     */
    private Object value;

    /**
     * 约束实现类
     */
    private IConstraint constraint;

    /**
     * 约束进行验证的条件
     */
    private ICondition condition;

    /**
     * 拦截信息
     * @since 0.0.4
     */
    private String message;

    /**
     * 约束分组信息
     * @since 0.0.5
     */
    private Class[] group;

    /**
     * 待验证约束分组信息
     * @since 0.0.7
     */
    private Class[] validGroup;

    /**
     * 对象实例
     * @since 0.0.9
     */
    private Object instance;

    /**
     * 全部字段列表
     * @since 0.0.9
     */
    private List<Field> fieldList;

    public static ValidEntry newInstance() {
        return new ValidEntry();
    }

    public Object value() {
        return value;
    }

    public ValidEntry value(Object value) {
        this.value = value;
        return this;
    }

    public IConstraint constraint() {
        return constraint;
    }

    public ValidEntry constraint(IConstraint constraint) {
        this.constraint = constraint;
        return this;
    }

    public ICondition condition() {
        return condition;
    }

    public ValidEntry condition(ICondition condition) {
        this.condition = condition;
        return this;
    }

    public String message() {
        return message;
    }

    public ValidEntry message(String message) {
        this.message = message;
        return this;
    }

    public Class[] group() {
        return group;
    }

    public ValidEntry group(Class[] group) {
        this.group = group;
        return this;
    }

    public Class[] validGroup() {
        return validGroup;
    }

    public ValidEntry validGroup(Class[] validGroup) {
        this.validGroup = validGroup;
        return this;
    }

    public Object instance() {
        return instance;
    }

    public ValidEntry instance(Object instance) {
        this.instance = instance;
        return this;
    }

    public List<Field> fieldList() {
        return fieldList;
    }

    public ValidEntry fieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
        return this;
    }
}
