package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.valid.api.api.constraint.IConstraint;

/**
 * 默认约束实现类
 * @author binbin.hou
 * @since 0.1.1
 */
public final class Constraints {

    private Constraints(){}

    /**
     * 全部相等约束
     * @param otherFields 其他字段名称
     * @return 约束实现
     * @since 0.1.1
     */
    public static IConstraint allEqualsConstraint(String ... otherFields) {
        ArgUtil.notEmpty(otherFields, "otherFields");

        return new AllEqualsConstraint(otherFields);
    }

    /**
     * 枚举范围内约束
     * @param enumClass 枚举类
     * @return 约束类
     * @since 0.1.1
     */
    public static IConstraint enumRangesConstraint(final Class<Enum> enumClass) {
        ArgUtil.notNull(enumClass, "enumClass");

        return new EnumRangesConstraint(enumClass);
    }

    /**
     * 拥有不为空的值约束
     * @param otherFields 其他字段
     * @return 约束类
     * @since 0.1.1
     */
    public static IConstraint hasNotNullConstraint(String ... otherFields) {
        ArgUtil.notEmpty(otherFields, "otherFields");
        return new HasNotNullConstraint(otherFields);
    }

    /**
     * 值再指定范围内约束
     * （1）这里为了和注解保持一致性。
     * @param strings 对象范围
     * @return 约束类
     * @since 0.1.1
     */
    public static IConstraint rangesConstraint(String ... strings) {
        ArgUtil.notEmpty(strings, "strings");

        return new RangesConstraint(strings);
    }

}
