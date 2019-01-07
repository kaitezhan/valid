package com.github.houbb.valid.annotation;

import java.lang.annotation.*;

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
}
