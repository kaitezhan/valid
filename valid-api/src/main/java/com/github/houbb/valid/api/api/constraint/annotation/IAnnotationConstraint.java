package com.github.houbb.valid.api.api.constraint.annotation;

import com.github.houbb.valid.api.api.constraint.IConstraint;

import java.lang.annotation.Annotation;

/**
 * 注解约束规则接口
 * 注意：所有的实现类都需要提供无参构造函数。
 * @author binbin.hou
 * @since 0.0.9
 */
public interface IAnnotationConstraint<A extends Annotation> extends IConstraint {

    /**
     * 初始化映射关系
     * @param annotation 注解信息
     * @since 0.0.9
     */
    void init(A annotation);

}
