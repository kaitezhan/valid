/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.api.api.message;

/**
 * <p> 信息接口 </p>
 *
 * （1）为了适应 i18n，以及允许用户自行定义 i18n 信息。
 *
 * （2）设计方案：懒加载的方式
 *
 *  可以分成多个实现类，或者两个类去实现。
 *
 *  系统内置实现类
 *  用户自定义实现类
 *
 *  二者要保证实现模式的完全一致性。
 *
 * （3）接口的简单性，实现的丰富性
 *
 *  接口尽量保持简单就好。
 *  对于实现，可以丰富起来。
 *
 *  （4）和 {@link String} 之间的转换
 *
 *  如何方便兼容字符串：
 *
 * <pre>
 *     IMessage message = Messages.of("String");
 *     String string = message.toString();
 * </pre>
 *
 *  （5）使用作用
 *
 *  如 notnull 对应的描述：
 *
 *  not null，中文自动转换为：禁止为空
 *
 *  （6）约束
 *
 *   lang 枚举
 *   template 常量
 *
 * <pre> Created: 2019/10/9 9:52 PM  </pre>
 * <pre> Project: valid  </pre>
 *
 * @author houbinbin
 * @since 0.0.8
 */
public interface IMessage {

    /**
     * 获取指定语言，指定 template
     * @param lang 语言
     * @param template 模板
     * @return 对应的信息
     * @since 0.0.8
     */
    String get(final String lang, final String template);

    /**
     * 设置语言模板对应的描述
     * @param lang 编码
     * @param template 模板
     * @param message 信息
     * @return this
     * @since 0.0.8
     */
    IMessage set(final String lang, final String template, final String message);

}
