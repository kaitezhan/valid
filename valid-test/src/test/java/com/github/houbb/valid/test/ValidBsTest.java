package com.github.houbb.valid.test;

import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.api.constant.enums.FailTypeEnum;
import com.github.houbb.valid.api.exception.ValidRuntimeException;
import com.github.houbb.valid.core.api.constraint.AbstractStrictConstraint;
import com.github.houbb.valid.core.api.constraint.chain.ConstraintChains;
import com.github.houbb.valid.core.api.fail.Fails;
import com.github.houbb.valid.core.bs.ValidBs;
import com.github.houbb.valid.core.model.ConstraintEntry;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * @author binbin.hou
 * @since 0.0.2
 */
public class ValidBsTest {

    /**
     * 自定义非空判断实现
     * @since 0.0.8
     */
    @Test
    public void simpleTest() {
        ValidBs.newInstance().on(null, new AbstractStrictConstraint() {
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
        ValidBs.newInstance().on(null, new AbstractStrictConstraint() {
            @Override
            protected boolean pass(IConstraintContext context, Object value) {
                return ObjectUtil.isNotNull(value);
            }
        }).result().print();
    }

    /**
     * 消息指定测试
     * @since 0.0.4
     */
    @Test
    public void messageTest() {
        IResult result = ValidBs.newInstance()
                .on(null, JsrConstraints.notNullConstraint())
                .message("指定值必填")
                .result();

        System.out.println(result);
    }

    /**
     * 约束链测试
     * @since 0.0.4
     */
    @Test
    public void chainTest() {
        IResult result = ValidBs.newInstance()
                .fail(Fails.failOver())
                .on("12", ConstraintChains.chain(JsrConstraints.sizeConstraint(5, 10),
                        JsrConstraints.sizeConstraint(10, 20)))
                .message("指定值必须满足约束链条件")
                .result();

        System.out.println(result);
    }

    /**
     * 多个约束条件测试
     * @since 0.0.5
     */
    @Test
    public void multiConstraintTest() {
        IResult result = ValidBs.newInstance()
                .fail(Fails.failOver())
                .on("12", ConstraintEntry.newInstance(JsrConstraints.sizeConstraint(5, 10)),
                        ConstraintEntry.newInstance(JsrConstraints.sizeConstraint(10, 20)))
                .result();

        Assert.assertEquals(2, result.notPassList().size());
        System.out.println(result);
    }

    /**
     * 分组信息验证
     * （1）指定验证的分组为 String.class，只会命中第一个约束条件。
     */
    @Test
    public void groupTest() {
        IResult result = ValidBs.newInstance()
                .fail(Fails.failOver())
                // 指定一个分组信息
                .validGroup(String.class)
                .on("12", ConstraintEntry.newInstance(JsrConstraints.sizeConstraint(5, 10))
                        .group(String.class),
                        ConstraintEntry.newInstance(JsrConstraints.sizeConstraint(10, 20)))
                .result();

        Assert.assertEquals(1, result.notPassList().size());
        System.out.println(result);
    }

    /**
     * 结果输出测试
     * @since 0.0.6
     */
    @Test
    public void resultPrintTest() {
        ValidBs.newInstance().on(null, JsrConstraints.notNullConstraint())
                .result()
                .print();
    }

    /**
     * 结果抛出异常测试
     * @since 0.0.6
     */
    @Test(expected = ValidRuntimeException.class)
    public void resultThrowsExTest() {
        ValidBs.newInstance().on(null, JsrConstraints.notNullConstraint())
                .result()
                .throwsEx();
    }

}
