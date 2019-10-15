package com.github.houbb.valid.api.annotation.condition;


import com.github.houbb.valid.api.api.condition.annotation.IAnnotationCondition;

import java.lang.annotation.*;

/**
 * 指定注解生效的条件的元注解
 *
 * @author binbin.hou
 * @since 0.0.9
 */
@Inherited
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {

    /**
     * 生效条件实现类
     * @return 对应的 class 实现
     * @since 0.1.3
     */
    Class<? extends IAnnotationCondition> value();

}
