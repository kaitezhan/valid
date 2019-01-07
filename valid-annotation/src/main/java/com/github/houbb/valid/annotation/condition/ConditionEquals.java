/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.annotation.condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> </p>
 *
 * <pre> Created: 2019/1/7 9:47 PM  </pre>
 * <pre> Project: valid  </pre>
 *
 * @author houbinbin
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionEquals {
}
