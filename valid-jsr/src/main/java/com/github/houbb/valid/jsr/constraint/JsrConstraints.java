package com.github.houbb.valid.jsr.constraint;

import com.github.houbb.valid.api.api.constraint.IConstraint;

import java.util.Calendar;
import java.util.Date;

/**
 * 约束条件工具类
 * @author binbin.hou
 * @since 0.0.3
 */
public final class JsrConstraints {

    private JsrConstraints(){}

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
        return new PatternConstraint(regex);
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
     * 最大值默认为 {@link Integer#MAX_VALUE}
     * @param min 最小值
     * @return 约束条件
     * @since 0.1.2
     */
    public static IConstraint sizeConstraintMin(final int min) {
        return new SizeConstraint(min, Integer.MAX_VALUE);
    }

    /**
     * 最小值默认为 0
     * @param max 最大值
     * @return 约束条件
     * @since 0.1.2
     */
    public static IConstraint sizeConstraintMax(final int max) {
        return new SizeConstraint(0, max);
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

    /**
     * 不能为空格
     * @return 空格
     * @since 0.2.0
     */
    public static IConstraint notBlankConstraint() {
        return new NotBlankConstraint();
    }

    /**
     * 不能为空
     * @return 为空
     * @since 0.2.0
     */
    public static IConstraint notEmptyConstraint() {
        return new NotEmptyConstraint();
    }

    /**
     * 长度约束
     * @param min 最小值
     * @param max 最大值
     * @return 长度约束
     * @since 0.2.0
     */
    public static IConstraint lengthConstraint(final int min, final int max) {
        return new LengthConstraint(min, max);
    }

    /**
     * cnpj约束
     * @return 长度约束
     * @since 0.2.0
     */
    public static IConstraint cnpjConstraint() {
        return new CNPJConstraint();
    }

    /**
     * CPF 约束
     * @return 长度约束
     * @since 0.2.0
     */
    public static IConstraint cpfConstraint() {
        return new CPFConstraint();
    }

    /**
     * URL 约束
     * @return 长度约束
     * @since 0.2.0
     */
    public static IConstraint urlConstraint() {
        return new URLConstraint();
    }

    /**
     * EMAIL 约束
     * @return 长度约束
     * @since 0.2.0
     */
    public static IConstraint emailConstraint() {
        return new EmailConstraint();
    }

    /**
     * uniqueElements 约束
     * @return 长度约束
     * @since 0.2.0
     */
    public static IConstraint uniqueElementsConstraint() {
        return new UniqueElementsConstraint();
    }

    /**
     * Range 约束
     * @return 长度约束
     * @since 0.2.0
     */
    public static IConstraint rangeConstraint(long min, long max) {
        return new RangeConstraint(min, max);
    }

}
