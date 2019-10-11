package com.github.houbb.valid.api.api.validator;

import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.constraint.IConstraint;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 校验明细信息接口
 * @author binbin.hou
 * @since 0.1.0
 */
public interface IValidatorEntry {

    /**
     * 待校验的值
     * @return 待校验的值
     * @since 0.1.0
     */
    Object value();

    /**
     * 约束实现类
     * @return 约束实现类
     * @since 0.1.0
     */
    IConstraint constraint();

    /**
     * 约束进行验证的条件
     * @return 约束进行验证的条件
     * @since 0.1.0
     */
    ICondition condition();

    /**
     * 约束提示信息
     * @return 提示信息
     * @since 0.1.0
     */
    String message();

    /**
     * 约束分组信息
     * @since 0.1.0
     * @return 约束分组信息
     */
    Class[] group();

    /**
     * 对应的实例对象
     * @return 实例对象
     * @since 0.1.0
     */
    Object instance();

    /**
     * 对应的字段列表
     * @return 实例对象
     * @since 0.1.0
     */
    List<Field> fieldList();

}
