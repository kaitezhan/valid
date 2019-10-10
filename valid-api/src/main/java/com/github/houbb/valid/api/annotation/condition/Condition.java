package com.github.houbb.valid.api.annotation.condition;


import com.github.houbb.valid.api.api.condition.ICondition;

import java.lang.annotation.*;

/**
 * 指定注解生效的条件的元注解
 *
 * TODO: 为了校验逻辑的简单性，暂时先不支持 condition 操作。
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
     */
    Class<? extends ICondition> value();

}
