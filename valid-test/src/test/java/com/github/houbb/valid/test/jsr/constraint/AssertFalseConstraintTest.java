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
        IResult result = ValidBs.newInstance().on(false, JsrConstraints.assertFalseConstraint())
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passNullTest() {
        IResult result = ValidBs.newInstance().on(null, JsrConstraints.assertFalseConstraint())
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notPassTest() {
        IResult result = ValidBs.newInstance().on(true, JsrConstraints.assertFalseConstraint())
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

    @Test(expected = ClassCastException.class)
    public void unSupportClassTypeTest() {
        IResult result = ValidBs.newInstance().on("123", JsrConstraints.assertFalseConstraint())
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

}
