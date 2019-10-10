/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.core.annotation.constraint;

import com.github.houbb.valid.api.annotation.constraint.Constraint;
import com.github.houbb.valid.core.api.constraint.annotation.HasNotNullConstraint;

import java.lang.annotation.*;

/**
 * <p> 至少一个不为空 </p>
 *
 * <pre> Created: 2019/1/7 9:49 PM  </pre>
 * <pre> Project: valid  </pre>
 *
 * @author houbinbin
 * @since 0.0.1
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(HasNotNullConstraint.class)
public @interface HasNotNull {

    /**
     * 当前字段及其指定的字段 至少有一个不为 null
     * @return 指定的字段列表
     */
    String[] value();

    /**
     * 提示消息
     * @return 错误提示
     */
    String message() default "";

}
