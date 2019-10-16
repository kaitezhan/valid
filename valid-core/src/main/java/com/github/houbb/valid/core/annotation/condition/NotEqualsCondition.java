/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.core.annotation.condition;

import com.github.houbb.valid.api.annotation.condition.Condition;
import com.github.houbb.valid.core.api.condition.annotation.AtNotEqualsCondition;

import java.lang.annotation.*;

/**
 * <p> 指定的字段和当前字段全部相等 </p>
 *
 * <pre> Created: 2019/1/7 9:49 PM  </pre>
 * <pre> Project: valid  </pre>
 *
 * @author houbinbin
 * @since 0.1.3
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Condition(AtNotEqualsCondition.class)
public @interface NotEqualsCondition {

    /**
     * 字段值不等于什么的时候
     * @return 字符串
     */
    String value();

    /**
     * 字段名称
     * @return 字段名称
     */
    String fieldName() default "";

    /**
     * 包含的约束注解属性
     * @return 列表
     * @since 0.1.3
     */
    Class<? extends Annotation>[] includes() default {};

    /**
     * 包含的约束注解属性
     * @return 列表
     * @since 0.1.3
     */
    Class<? extends Annotation>[] excludes() default {};

}
