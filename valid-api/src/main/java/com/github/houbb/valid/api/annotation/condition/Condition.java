package com.github.houbb.valid.api.annotation.condition;


import com.github.houbb.valid.api.api.condition.ICondition;

import java.lang.annotation.*;

/**
 * 约束注解生效的条件
 * （1）不添加当前注解，则默认当前字段所有的约束都是要生效的。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {

    /**
     * 生效条件
     * @return 对应的 class 实现
     */
    Class<? extends ICondition> condition();

    /**
     * 约束注解的关联注解类
     * @return 对应的注解类列表
     */
    Class<? extends Annotation>[] constrains() default {};

}
