/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.api.annotation.constraint;

import java.lang.annotation.*;

/**
 * <p> 范围判断 </p>
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
public @interface Ranges {

    /**
     * 当前字段必须在指定的范围内
     * @return 指定的字段列表
     */
    String[] value() default {};

    /**
     * 提示消息
     * @return 错误提示
     */
    String message() default "";

}
