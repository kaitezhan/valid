/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.core.api.message;

import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.valid.api.api.message.IMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> 抽象消息实现类 </p>
 *
 * <pre> Created: 2019/10/9 10:21 PM  </pre>
 * <pre> Project: valid  </pre>
 *
 * @author houbinbin
 * @since 0.0.8
 * @see com.github.houbb.heaven.constant.enums.LangEnum 语言枚举
 * @see java.util.Locale 本地属性指定
 */
public class DefaultMessage implements IMessage {

    /**
     * 语言-模板 map
     *
     * @since 0.0.8
     */
    private final Map<String, String> LANG_TEMPLATE_MAP = new ConcurrentHashMap<>();

    /**
     * 构建 key
     *
     * @param lang     语言
     * @param template 模板
     * @return 结果
     * @since 0.0.8
     */
    protected String buildKey(String lang, String template) {
        return lang + PunctuationConst.UNDERLINE + template;
    }

    /**
     * 参数检查
     * @param lang 语言
     * @param template 模板
     * @since 0.0.8
     */
    private void paramCheck(String lang, String template) {
        ArgUtil.notNull(lang, "lang");
        ArgUtil.notNull(template, "template");
    }


    @Override
    public String get(String lang, String template) {
        paramCheck(lang, template);

        final String key = buildKey(lang, template);
        return LANG_TEMPLATE_MAP.get(key);
    }

    @Override
    public IMessage set(String lang, String template, String message) {
        paramCheck(lang, template);

        final String key = buildKey(lang, template);
        LANG_TEMPLATE_MAP.put(key, message);

        return this;
    }

}
