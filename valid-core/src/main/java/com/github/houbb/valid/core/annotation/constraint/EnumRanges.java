/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.core.annotation.constraint;

import com.github.houbb.valid.api.annotation.constraint.Constraint;
import com.github.houbb.valid.core.api.constraint.annotation.EnumRangesConstraint;

import java.lang.annotation.*;

/**
 * <p> 枚举值范围判断 </p>
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
@Constraint(EnumRangesConstraint.class)
public @interface EnumRanges {

    /**
     * 当前字段必须在枚举值指定的范围内
     * @return 指定的字段列表
     */
    Class<Enum> value();

    /**
     * 提示消息
     * @return 错误提示
     */
    String message() default "";

}
