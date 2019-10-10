package com.github.houbb.valid.api.annotation.constraint;

import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.annotation.IAnnotationConstraint;

import java.lang.annotation.*;

/**
 * 用于指定注解约束的元注解
 * @author binbin.hou
 * @since 0.0.9
 */
@Inherited
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraint {

    /**
     * 约束条件实现类
     * @return 实现类 class
     */
    Class<? extends IAnnotationConstraint> value();

}
