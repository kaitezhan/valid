package com.github.houbb.valid.annotation.condition;

import com.github.houbb.valid.api.ICondition;
import com.github.houbb.valid.api.impl.ConditionAlwaysTrue;

import java.lang.annotation.*;

/**
 * 约束注解生效的条件
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
    Class<? extends ICondition> condition() default ConditionAlwaysTrue.class;

    /**
     * 约束注解的关联注解类
     * @return 对应的注解类列表
     */
    Class<? extends Annotation>[] constrain() default {};

}
