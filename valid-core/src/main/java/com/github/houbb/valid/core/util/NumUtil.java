package com.github.houbb.valid.core.util;

import com.github.houbb.heaven.annotation.CommonEager;
import com.github.houbb.heaven.util.lang.ObjectUtil;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 数字工具类
 * @author binbin.hou
 * @since 0.0.3
 */
@CommonEager
public final class NumUtil {

    private NumUtil(){}

    /**
     * 将对象转换为 long
     * @param object 对象
     * @return long
     * @since 0.0.3
     * @see CharSequence
     * @see java.math.BigDecimal
     * @see java.math.BigInteger
     * @see Integer
     * @see Short
     * @see Byte
     * @see Long
     * @throws ClassCastException 类型转换异常
     */
    public static Long parseLong(final Object object) {
        if(ObjectUtil.isNull(object)) {
            return null;
        }

        final Class valueClass = object.getClass();
        if(object instanceof Byte
                || valueClass == byte.class) {
            Byte aByte = (Byte) object;
            return aByte.longValue();
        }
        if(object instanceof Short
                || valueClass == short.class) {
            Short aShort = (Short) object;
            return aShort.longValue();
        }
        if(object instanceof Integer
                || valueClass == int.class) {
            Integer integer = (Integer) object;
            return integer.longValue();
        }
        if(object instanceof Long) {
            return (Long) object;
        }
        if(object instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) object;
            return Long.parseLong(charSequence.toString());
        }
        if(object instanceof BigInteger) {
            BigInteger bigInteger = (BigInteger) object;
            return bigInteger.longValue();
        }
        if(object instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) object;
            return bigDecimal.longValue();
        }

        throw new ClassCastException("Class cast exception for parse long");
    }

}
