package com.github.houbb.valid.api.api.constraint;

import com.github.houbb.valid.api.constant.enums.FailTypeEnum;

/**
 * 约束条件上下文接口
 * @author binbin.hou
 * @since 0.0.2
 */
public interface IConstraintContext {

    /**
     * 待验证的值
     * @return 待验证的值
     * @since 0.0.2
     */
    Object value();

    /**
     * 指定描述消息
     * @return 描述消息
     * @since 0.0.4
     */
    String message();

    /**
     * 失败模式
     * @return 失败模式
     * @since 0.0.4
     */
    FailTypeEnum failType();

    /**
     * 设置属性值
     * @param key key
     * @param object 值
     * @return this
     * @since 0.0.4
     */
    IConstraintContext putAttr(final String key, final Object object);

    /**
     * 获取属性值
     * @param key key
     * @return this
     * @since 0.0.4
     */
    Object getAttr(final String key);

    /**
     * 获取匹配的分组信息
     * @return 分组信息
     * @since 0.0.5
     */
    Class matchGroup();

}
