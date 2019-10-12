package com.github.houbb.valid.test;

import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.util.DateUtil;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.api.api.validator.IValidatorEntry;
import com.github.houbb.valid.api.exception.ValidRuntimeException;
import com.github.houbb.valid.core.api.constraint.AbstractStrictConstraint;
import com.github.houbb.valid.core.api.constraint.chain.ConstraintChains;
import com.github.houbb.valid.core.api.fail.Fails;
import com.github.houbb.valid.core.api.validator.entry.ValidatorEntryFactory;
import com.github.houbb.valid.core.bs.ValidBs;
import com.github.houbb.valid.jsr.api.validator.JsrValidator;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import com.github.houbb.valid.test.model.JsrUser;
import com.github.houbb.valid.test.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

/**
 * @author binbin.hou
 * @since 0.0.2
 */
public class ValidBsTest {

    /**
     * 自定义非空约束
     * @since 0.1.0
     * @return 约束实现
     */
    private IValidatorEntry notNullValidatorEntry() {
        final IConstraint constraint = new AbstractStrictConstraint() {
            @Override
            protected boolean pass(IConstraintContext context, Object value) {
                return ObjectUtil.isNotNull(value);
            }
        };
        return ValidatorEntryFactory.of(constraint);
    }

    /**
     * 自定义非空判断实现
     * @since 0.0.8
     */
    @Test
    public void simpleTest() {
        ValidBs.on(null, new AbstractStrictConstraint() {
            @Override
            protected boolean pass(IConstraintContext context, Object value) {
                return ObjectUtil.isNotNull(value);
            }
        }).result().print();
    }

    /**
     * i18n 测试
     * @since 0.0.8
     */
    @Test
    public void i18nTest() {
        Locale.setDefault(Locale.ENGLISH);
        ValidBs.on(null, notNullValidatorEntry())
                .valid()
                .result().print();
    }

    /**
     * 消息指定测试
     * @since 0.0.4
     */
    @Test
    public void messageTest() {
        final IValidatorEntry of = notNullValidatorEntry()
                .message("指定值不能为空");

        ValidBs.on(null, of)
                .valid()
                .result().print();
    }

    /**
     * 约束链测试
     * @since 0.0.4
     */
    @Test
    public void chainTest() {
        final IConstraint constraintChain = ConstraintChains.chain(JsrConstraints.sizeConstraint(5, 10),
                JsrConstraints.sizeConstraint(10, 20));

        ValidBs.on("12", ValidatorEntryFactory.of(constraintChain))
                .fail(Fails.failOver())
                .result()
                .print();
    }

    /**
     * 多个约束条件测试
     * @since 0.0.5
     */
    @Test
    public void multiConstraintTest() {
        IResult result = ValidBs
                .on("12", ValidatorEntryFactory.of(JsrConstraints.sizeConstraint(5, 10)),
                        ValidatorEntryFactory.of(JsrConstraints.sizeConstraint(10, 20)))
                .fail(Fails.failOver())
                .valid()
                .result();

        Assert.assertEquals(2, result.notPassList().size());
        result.print();
    }

    /**
     * 分组信息验证
     * （1）指定验证的分组为 String.class，只会命中第一个约束条件。
     */
    @Test
    public void groupTest() {
        final IValidatorEntry of = ValidatorEntryFactory.of(JsrConstraints.sizeConstraint(5, 10))
                .group(String.class);
        IResult result = ValidBs
                .on("12", of)
                .fail(Fails.failOver())
                // 指定一个分组信息
                .group(String.class)
                .valid()
                .result()
                .print();

        Assert.assertEquals(1, result.notPassList().size());
    }

    /**
     * 结果抛出异常测试
     * @since 0.0.6
     */
    @Test(expected = ValidRuntimeException.class)
    public void resultThrowsExTest() {
        ValidBs.on(null, notNullValidatorEntry())
                .valid()
                .result()
                .throwsEx();
    }

    /**
     * 对象验证测试
     * @since 0.0.9
     */
    @Test
    public void beanValidTest() {
        User user = new User();
        user.sex("what").password("old").password2("new");

        ValidBs.on(user)
                .result()
                .print();
    }

    /**
     * 全部失败
     * @since 0.1.1
     */
    @Test
    public void jsrAllFailTest() {
        Date future = DateUtil.getFormatDate("90190101", DateUtil.PURE_DATE_FORMAT);;
        Date past = DateUtil.getFormatDate("20190101", DateUtil.PURE_DATE_FORMAT);;

        JsrUser jsrUser = new JsrUser();
        jsrUser.assertFalse(true)
                .assertTrue(false)
                .decimalMin(new BigDecimal("1"))
                .decimalMax(new BigDecimal("55.55"))
                .min(5)
                .max(20)
                .digits(333333L)
                .future(past)
                .past(future)
                .nullVal("123")
                .notNullVal(null)
                .pattern("asdfasdf")
                .size("22222222222222222222");

        ValidBs.on(jsrUser)
                .fail(Fails.failOver())
                .valid(JsrValidator.getInstance())
                .result()
                .print();
    }

    /**
     * 日期比较
     * 这个是有问题的，直接设置结果不准确。
     */
    @Test
    public void dateCompareTest() {
        Date past = new Date();
        past.setYear(2000);

        Long time = past.getTime();
        Long now = new Date().getTime();
        System.out.println(time);
        System.out.println(now);
        System.out.println(time.compareTo(now));
        System.out.println(past.compareTo(new Date()));
    }

    /**
     * 全部通过
     * @since 0.1.1
     */
    @Test
    public void jsrAllPassTest() {
        Date future = DateUtil.getFormatDate("90190101", DateUtil.PURE_DATE_FORMAT);;
        Date past = DateUtil.getFormatDate("20190101", DateUtil.PURE_DATE_FORMAT);;

        JsrUser jsrUser = new JsrUser();
        jsrUser.assertFalse(false)
                .assertTrue(true)
                .decimalMin(new BigDecimal("55.55"))
                .decimalMax(new BigDecimal("1"))
                .min(20)
                .max(5)
                .digits(3L)
                .future(future)
                .past(past)
                .nullVal(null)
                .notNullVal("123")
                .pattern("13")
                .size("22")
        ;
        ValidBs.on(jsrUser)
                .fail(Fails.failOver())
                .valid(JsrValidator.getInstance())
                .result()
                .print();
    }

}
