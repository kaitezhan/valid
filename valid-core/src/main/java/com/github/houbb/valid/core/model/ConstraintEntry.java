package com.github.houbb.valid.core.model;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.constraint.IConstraint;

/**
 * 约束明细类
 * @author binbin.hou
 * @since 0.0.5
 */
@Deprecated
public class ConstraintEntry {

    /**
     * 约束实现
     * @since 0.0.5
     */
    private IConstraint constraint;

    /**
     * 自定义消息
     * @since 0.0.5
     */
    private String message;

    /**
     * 约束生效条件
     * @since 0.0.5
     */
    private ICondition condition;

    /**
     * 约束分组信息
     * @since 0.0.5
     */
    private Class[] group;

    private ConstraintEntry(){}

    public static ConstraintEntry newInstance(final IConstraint constraint) {
        ConstraintEntry entry = new ConstraintEntry();
        entry.constraint(constraint);
        return entry;
    }

    public IConstraint constraint() {
        return constraint;
    }

    public ConstraintEntry constraint(IConstraint constraint) {
        ArgUtil.notNull(constraint, "constraint");

        this.constraint = constraint;
        return this;
    }

    public String message() {
        return message;
    }

    public ConstraintEntry message(String message) {
        this.message = message;
        return this;
    }

    public ICondition condition() {
        return condition;
    }

    public ConstraintEntry condition(ICondition condition) {
        this.condition = condition;
        return this;
    }

    public Class[] group() {
        return group;
    }

    public ConstraintEntry group(Class... group) {
        this.group = group;
        return this;
    }

}
