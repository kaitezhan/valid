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
public class AssertFalseConstraintTest {

    @Test
    public void passTest() {
        IResult result = ValidBs.on(false, JsrConstraints.assertFalseConstraint())
            .valid();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passNullTest() {
        IResult result = ValidBs.on(null, JsrConstraints.assertFalseConstraint())
                .valid();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notPassTest() {
        IResult result = ValidBs.on(true, JsrConstraints.assertFalseConstraint())
                .validator()
                .valid();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

    @Test(expected = ClassCastException.class)
    public void unSupportClassTypeTest() {
        IResult result = ValidBs.on("123", JsrConstraints.assertFalseConstraint())
                .validator()
                .valid();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

}
