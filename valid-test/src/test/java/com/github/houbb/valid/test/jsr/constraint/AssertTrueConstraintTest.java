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
public class AssertTrueConstraintTest {

    @Test
    public void passTest() {
        IResult result = ValidBs.on(true, JsrConstraints.assertTrueConstraint())
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passNullTest() {
        IResult result = ValidBs.on(null, JsrConstraints.assertTrueConstraint())
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notPassTest() {
        IResult result = ValidBs.on(false, JsrConstraints.assertTrueConstraint())
                .valid()
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

}
