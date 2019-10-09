/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.core.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p> i18n 编码支持 </p>
 *
 * <pre> Created: 2018/7/28 下午4:53  </pre>
 * <pre> Project: valid </pre>
 *
 * @author houbinbin
 * @since 0.0.8
 */
public final class I18N {

    /**
     * 默认的配置文件
     */
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "i18n.Valid";

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
     */
    public static class Key {

        /**
         * 消息-值不是预期值
         * @since 0.0.8
         */
        public static final String MESSAGE_VALUE_NOT_EXPECTED = "MESSAGE_VALUE_NOT_EXPECTED";

        /**
         * 消息-预期值是，但是实际值是
         *
         * @since 0.0.8
         */
        public static final String MESSAGE_EXPECT_BUT_ACTUAL = "MESSAGE_EXPECT_BUT_ACTUAL";

        /**
         * 支持类型提示
         */
        public static final String SUPPORT_CLASS_TYPE_TIPS = "SUPPORT_CLASS_TYPE_TIPS";

        /**
         * 小于等于
         * @since 0.0.8
         */
        public static final String LESS_THAN_OR_EQUALS = "LESS_THAN_OR_EQUALS";

        /**
         * 小于
         * @since 0.0.8
         */
        public static final String LESS_THAN = "LESS_THAN";

        /**
         * 大于等于
         * @since 0.0.8
         */
        public static final String GREAT_THAN_OR_EQUALS = "GREAT_THAN_OR_EQUALS";

        /**
         * 大于
         * "great than or equals "
         */
        public static final String GREAT_THAN = "GREAT_THAN";
    }

}
