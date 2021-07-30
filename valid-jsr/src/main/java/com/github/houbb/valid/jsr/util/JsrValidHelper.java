package com.github.houbb.valid.jsr.util;

import com.github.houbb.valid.api.api.fail.IFail;
import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.core.api.fail.Fails;
import com.github.houbb.valid.core.bs.ValidBs;
import com.github.houbb.valid.jsr.api.validator.JsrValidator;

/**
 * 校验工具类
 *
 * @author binbin.hou
 * @since 0.1.5
 */
public final class JsrValidHelper {

    private JsrValidHelper(){}

    /**
     * 全部验证后返回
     * @param object 对象
     * @return 结果
     * @since 0.1.5
     */
    public static IResult failOver(final Object object) {
        return valid(object, Fails.failOver());
    }

    /**
     * 快速验证后返回
     * @param object 对象
     * @return 结果
     * @since 0.1.5
     */
    public static IResult failFast(final Object object) {
        return valid(object, Fails.failFast());
    }

    /**
     * 全部验证后返回-抛出异常
     * @param object 对象
     * @since 0.1.5
     */
    public static void failOverThrow(final Object object) {
        failOver(object).throwsEx();
    }

    /**
     * 快速验证后返回-抛出异常
     * @param object 对象
     * @since 0.1.5
     */
    public static void failFastThrow(final Object object) {
        failFast(object).throwsEx();
    }

    private static IResult valid(final Object object,
                                 final IFail fail) {
        return ValidBs.on(object)
                .fail(fail)
                .validator(JsrValidator.getInstance())
                .valid();
    }

}
