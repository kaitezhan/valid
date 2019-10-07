package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.valid.api.api.constraint.IConstraint;

import java.util.Calendar;
import java.util.Date;

/**
 * 约束条件工具类
 * @author binbin.hou
 * @since 0.0.3
 */
public final class Constraints {

    private Constraints(){}

    /**
     * @return 为 true 约束条件
     * @since 0.0.3
     */
    public static IConstraint assertTrueConstraint() {
        return AssertTrueConstraint.getInstance();
    }

    /**
     * @return 为 false 约束条件
     * @since 0.0.3
     */
    public static IConstraint assertFalseConstraint() {
        return AssertFalseConstraint.getInstance();
    }

    /**
     * @return 为 null 约束条件
     * @since 0.0.3
     */
    public static IConstraint nullConstraint() {
        return NullConstraint.getInstance();
    }

    /**
     * @return 不为 null 约束条件
     * @since 0.0.3
     */
    public static IConstraint notNullConstraint() {
        return NotNullConstraint.getInstance();
    }

    /**
     * 是否在当前时间之前约束条件
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint pastConstraint() {
        return new PastConstraint(new Date());
    }

    /**
     * 是否在指定时间之前约束条件
     * @param date 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint pastConstraint(final Date date) {
        return new PastConstraint(date);
    }

    /**
     * 是否在指定时间之前约束条件
     * @param date 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint pastConstraint(final Calendar date) {
        return new PastConstraint(date);
    }

    /**
     * 是否在当前时间之后约束条件
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint futureConstraint() {
        return new FutureConstraint(new Date());
    }

    /**
     * 是否在指定时间之后约束条件
     * @param date 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint futureConstraint(final Date date) {
        return new FutureConstraint(date);
    }

    /**
     * 是否在指定时间之后约束条件
     * @param date 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint futureConstraint(final Calendar date) {
        return new FutureConstraint(date);
    }

    /**
     *
     * @param regex 正则表达式
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint patternConstraint(final String regex) {
        return new PastConstraint(regex);
    }

    /**
     *
     * @param min 最小值
     * @param max 最大值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint sizeConstraint(final int min, final int max) {
        return new SizeConstraint(min, max);
    }

    /**
     * 位数约束
     * @param integer 整数
     * @param fraction 精度
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint digitsConstraint(final int integer, final int fraction) {
        return new DigitsConstraint(integer, fraction);
    }

    /**
     * 位数约束
     * @param integer 整数
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint digitsConstraint(final int integer) {
        return new DigitsConstraint(integer);
    }

    /**
     *
     * @param charSequence 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint decimalMaxConstraint(final CharSequence charSequence) {
        return new DecimalMaxConstraint(charSequence);
    }

    /**
     *
     * @param inclusive 是否包含
     * @param charSequence 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint decimalMaxConstraint(final boolean inclusive, final CharSequence charSequence) {
        return new DecimalMaxConstraint(inclusive, charSequence);
    }

    /**
     *
     * @param charSequence 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint decimalMinConstraint(final CharSequence charSequence) {
        return new DecimalMinConstraint(charSequence);
    }

    /**
     *
     * @param inclusive  是否包含
     * @param charSequence 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint decimalMinConstraint(final boolean inclusive, final CharSequence charSequence) {
        return new DecimalMinConstraint(inclusive, charSequence);
    }

    /**
     * 最小约束条件
     * @param inclusive 是否相等
     * @param expect 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint minConstraint(final boolean inclusive, final long expect) {
        return new MinConstraint(inclusive, expect);
    }

    /**
     * 最小约束条件
     * @param expect 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint minConstraint(final long expect) {
        return new MinConstraint(expect);
    }

    /**
     * 最大约束条件
     * @param inclusive 是否相等
     * @param expect 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint maxConstraint(final boolean inclusive, final long expect) {
        return new MaxConstraint(inclusive, expect);
    }

    /**
     * 最大约束条件
     * @param expect 阈值
     * @return 约束条件
     * @since 0.0.3
     */
    public static IConstraint maxConstraint(final long expect) {
        return new MaxConstraint(expect);
    }
}
