package com.github.houbb.valid.test.jsr.constraint;

import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.core.bs.ValidBs;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.3
 */
public class MaxConstraintTest {

    @Test
    public void passTest() {
        IResult result = ValidBs.on(99, JsrConstraints.maxConstraint(100))
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passInclusiveTest() {
        IResult result = ValidBs.on(100, JsrConstraints.maxConstraint(true, 100))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passNullTest() {
        IResult result = ValidBs.on(null, JsrConstraints.maxConstraint(100))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notPassTest() {
        IResult result = ValidBs.on(101, JsrConstraints.maxConstraint(100))
                .valid()
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

    /**
     * TODO: 后期可以考虑添加 float double 的拓展支持。
     */
    @Test(expected = ClassCastException.class)
    public void classCastException() {
        IResult result = ValidBs.on(123.34f, JsrConstraints.maxConstraint(100))
                .valid()
                .result();
        System.out.println(result);
    }

}
