/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.jsr.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p> Jsr I18N </p>
 *
 * sizeMustBeIn=大小必须在 [%d, %d] 范围内
 * matchRegexPattern=必须匹配正则表达式 %s
 * integerDigitsFractionDigits=整数位数 [%d], 小数位数 [%d]
 * <pre> Created: 2018/7/28 下午4:53  </pre>
 * <pre> Project: valid </pre>
 *
 * @author houbinbin
 * @since 0.1.1
 */
public final class JsrI18N {

    private JsrI18N(){}

    /**
     * 默认的配置文件
     */
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "i18n.JSR";

    /**
     * 获取属性的值
     * @param key 键值
     * @return 属性 I18n
     */
    public static String get(final String key) {
        Locale currentLocale = Locale.getDefault();
        ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_PROPERTIES_FILE_NAME, currentLocale);
        return myResources.getString(key);
    }

    /**
     * 固定的键值标识
     * @since 0.1.1
     */
    public static class Key {

        /**
         * @since 0.0.9
         */
        private Key(){}

        /**
         * 大小必须在指定范围内
         */
        public static final String SIZE_MUST_BE_IN = "sizeMustBeIn";

        /**
         * 必须匹配正则
         */
        public static final String MATCH_REGEX_PATTERN = "matchRegexPattern";

        /**
         * 整数小数位数
         */
        public static final String INTEGER_DIGITS_FRACTION_DIGITS = "integerDigitsFractionDigits";
    }

}
