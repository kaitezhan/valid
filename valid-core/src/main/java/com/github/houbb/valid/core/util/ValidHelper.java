package com.github.houbb.valid.core.util;

import com.github.houbb.valid.api.api.fail.IFail;
import com.github.houbb.valid.core.api.fail.Fails;
import com.github.houbb.valid.core.bs.ValidBs;

/**
 * 校验工具类
 *
 * @author binbin.hou
 * @since 0.1.4
 */
public final class ValidHelper {

    private ValidHelper(){}

    /**
     * 快速失败
     * @param object 对象
     * @since 0.1.4
     */
    public static void failFast(final Object object) {
        valid(object, Fails.failFast());
    }

    /**
     * 全部验证后失败
     * @param object 对象
     * @since 0.1.4
     */
    public static void failOver(final Object object) {
        valid(object, Fails.failOver());
    }

    /**
     * 执行校验
     * @param object 对象
     * @param fail 失败策略
     * @since 0.1.4
     */
    private static void valid(final Object object,
                       IFail fail) {
        ValidBs.on(object)
                .fail(fail)
                .result()
                .throwsEx();
    }

}
