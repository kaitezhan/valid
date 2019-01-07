/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.annotation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> </p>
 *
 * <pre> Created: 2019/1/7 9:49 PM  </pre>
 * <pre> Project: valid  </pre>
 *
 * @author houbinbin
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneNotNull {

    /**
     * 当前字段及其指定的字段 至少有一个不为 null
     * @return 指定的字段列表
     */
    String[] value();

    /**
     * 提示消息
     * @return 错误提示
     */
    String message() default "${valid.AtLeastOneNotNull.message}";

}
