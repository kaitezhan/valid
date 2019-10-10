package com.github.houbb.valid.api.api.constraint;

import com.github.houbb.valid.api.api.fail.IFail;

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
    IFail fail();

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
     * 根据字段名称，获取对应的字段值
     * （1）如果对应字段列表为空，或者对象实例为 null，则返回 null
     * （2）返回名称和指定 fieldName 相同的才返回对应的值信息。
     *
     * TRADE-OFF:
     *
     * 这样对于字段间的关系可以增强，但是不适合暴露太多信息，会让使用者变得混乱。
     * @param fieldName 字段名称
     * @return 该字段的值
     * @since 0.0.9
     */
    Object getFieldValue(final String fieldName);

}
