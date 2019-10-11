package com.github.houbb.valid.test.jsr.constraint;

import com.github.houbb.heaven.util.util.DateUtil;
import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.core.bs.ValidBs;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * @author binbin.hou
 * @since 0.0.3
 */
public class FutureConstraintTest {

    @Test
    public void passTest() {
        Date testDate = DateUtil.getFormatDate("90120101", DateUtil.PURE_DATE_FORMAT);
        IResult result = ValidBs.on(testDate, JsrConstraints.futureConstraint())
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passInclusiveTest() {
        Date testDate = DateUtil.getFormatDate("90120101", DateUtil.PURE_DATE_FORMAT);
        IResult result = ValidBs.on(testDate, JsrConstraints.futureConstraint(new Date()))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passNullTest() {
        IResult result = ValidBs.on(null, JsrConstraints.futureConstraint(new Date()))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notPassTest() {
        Date testDate = DateUtil.getFormatDate("20190101", DateUtil.PURE_DATE_FORMAT);
        IResult result = ValidBs.on(testDate, JsrConstraints.futureConstraint(new Date()))
                .valid()
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

    /**
     * （1）此处应该和组合型的保持一致。
     * （2）防止痴呆设计，同时给出支持的数据类型。
     */
    @Test(expected = ClassCastException.class)
    public void unSupportClassTypeTest() {
        IResult result = ValidBs.on(123, JsrConstraints.futureConstraint())
                .valid()
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

}
