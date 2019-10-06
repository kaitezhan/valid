package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.util.guava.Guavas;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 支持的数据类型
 * （1）为了不让这个类被外部使用，暂时放在这里。
 *
 * @author binbin.hou
 * @since 0.0.3
 */
final class SupportClassTypeUtil {

    private SupportClassTypeUtil(){}

    /**
     * 支持的类型列表
     * @since 0.0.3
     */
    private static final List<Class> DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST;

    /**
     * 时间过去未来支持的类型列表
     * @since 0.0.3
     */
    private static final List<Class> PAST_FUTURE_SUPPORT_CLASS_LIST;

    /**
     * 位数类型列表
     * @since 0.0.3
     */
    private static final List<Class> DIGITS_SUPPORT_CLASS_LIST;

    static {
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST = Guavas.newArrayList(11);
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST.add(CharSequence.class);
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST.add(BigDecimal.class);
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST.add(BigInteger.class);
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST.add(int.class);
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST.add(Integer.class);
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST.add(short.class);
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST.add(Short.class);
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST.add(long.class);
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST.add(Long.class);
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST.add(byte.class);
        DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST.add(Byte.class);

        PAST_FUTURE_SUPPORT_CLASS_LIST = Guavas.newArrayList(2);
        PAST_FUTURE_SUPPORT_CLASS_LIST.add(Date.class);
        PAST_FUTURE_SUPPORT_CLASS_LIST.add(Calendar.class);

        DIGITS_SUPPORT_CLASS_LIST = Guavas.newArrayList(11);
        DIGITS_SUPPORT_CLASS_LIST.add(CharSequence.class);
        DIGITS_SUPPORT_CLASS_LIST.add(BigDecimal.class);
        DIGITS_SUPPORT_CLASS_LIST.add(BigInteger.class);
        DIGITS_SUPPORT_CLASS_LIST.add(int.class);
        DIGITS_SUPPORT_CLASS_LIST.add(Integer.class);
        DIGITS_SUPPORT_CLASS_LIST.add(short.class);
        DIGITS_SUPPORT_CLASS_LIST.add(Short.class);
        DIGITS_SUPPORT_CLASS_LIST.add(long.class);
        DIGITS_SUPPORT_CLASS_LIST.add(Long.class);
        DIGITS_SUPPORT_CLASS_LIST.add(byte.class);
        DIGITS_SUPPORT_CLASS_LIST.add(Byte.class);
    }

    /**
     * 获取 {@link javax.validation.constraints.DecimalMin} 和 {@link javax.validation.constraints.DecimalMax}
     * 支持的数据类型列表
     * @return 列表信息
     * @since 0.0.3
     */
    static List<Class> getDecimalMaxMinSupportClassList() {
        return DECIMAL_MAX_MIN_SUPPORT_CLASS_LIST;
    }

    /**
     * 获取 {@link javax.validation.constraints.Future} 和 {@link javax.validation.constraints.Past}
     * 支持的数据类型列表
     * @return 列表信息
     * @since 0.0.3
     */
    static List<Class> getPastFutureSupportClassList() {
        return PAST_FUTURE_SUPPORT_CLASS_LIST;
    }

    /**
     * 获取 {@link javax.validation.constraints.Digits} 支持的数据类型
     * @return 列表信息
     * @since 0.0.3
     */
    static List<Class> getDigitsSupportClassList() {
        return DIGITS_SUPPORT_CLASS_LIST;
    }

}
