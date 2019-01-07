/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.annotation.metadata;

import com.github.houbb.valid.api.ICondition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证生效的条件
 * @author binbin.hou
 * @date 2019/1/6
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {

    /**
     * 条件生效的条件
     * @return 对应的类
     */
    Class<? extends ICondition> value();

}
