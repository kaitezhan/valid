package com.github.houbb.valid.core.api.validator.entry;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.validator.IValidEntry;

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
public class ValidEntry implements IValidEntry {

    /**
     * 待验证的值
     * @since 0.1.0
     */
    private Object value;

    /**
     * 约束实现
     * @since 0.1.0
     */
    private IConstraint constraint;

    /**
     * 约束对应的生效条件
     * @since 0.1.0
     */
    private ICondition condition;

    /**
     * 约束对应的消息提示
     * @since 0.1.0
     */
    private String message;

    /**
     * 分组信息
     * @since 0.1.0
     */
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

    /**
     * 私有化构造器
     * @since 0.1.2
     */
    private ValidEntry(){}

    /**
     * 创建一个对象实例
     * @param constraint 约束实现
     * @return 验证明细
     * @since 0.1.2
     */
    public static ValidEntry of(final IConstraint constraint) {
        ValidEntry validEntry = new ValidEntry();
        validEntry.constraint(constraint);
        return validEntry;
    }

    @Override
    public Object value() {
        return value;
    }

    public ValidEntry value(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public IConstraint constraint() {
        return constraint;
    }

    public ValidEntry constraint(IConstraint constraint) {
        ArgUtil.notNull(constraint, "constraint");

        this.constraint = constraint;
        return this;
    }

    @Override
    public ICondition condition() {
        return condition;
    }

    public ValidEntry condition(ICondition condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public ValidEntry message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public Class[] group() {
        return group;
    }

    @Override
    public ValidEntry group(Class... group) {
        this.group = group;
        return this;
    }

    @Override
    public Object instance() {
        return instance;
    }

    public ValidEntry instance(Object instance) {
        this.instance = instance;
        return this;
    }

    @Override
    public List<Field> fieldList() {
        return fieldList;
    }

    public ValidEntry fieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
        return this;
    }

}
