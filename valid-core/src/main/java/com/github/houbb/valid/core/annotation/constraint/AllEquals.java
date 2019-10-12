/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.core.annotation.constraint;

import com.github.houbb.valid.api.annotation.constraint.Constraint;
import com.github.houbb.valid.core.api.constraint.annotation.AtAllEqualsConstraint;

import java.lang.annotation.*;

/**
 * <p> 指定的字段和当前字段全部相等 </p>
 *
 * <pre> Created: 2019/1/7 9:49 PM  </pre>
 * <pre> Project: valid  </pre>
 *
 * @author houbinbin
 * @since 0.0.9
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(AtAllEqualsConstraint.class)
public @interface AllEquals {

    /**
     * 当前字段及其指定的字段 全部相等
     * 1. 字段类型及其他字段相同
     * @return 指定的字段列表
     */
    String[] value();

    /**
     * 提示消息
     * @return 错误提示
     */
    String message() default "";

}
