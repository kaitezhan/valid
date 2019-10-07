package com.github.houbb.valid.test.constraint;

import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.core.api.constraint.Constraints;
import com.github.houbb.valid.core.bs.ValidBs;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.3
 */
public class AssertFalseConstraintTest {

    @Test
    public void passTest() {
        IResult result = ValidBs.newInstance().on(false, Constraints.assertFalseConstraint())
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passNullTest() {
        IResult result = ValidBs.newInstance().on(null, Constraints.assertFalseConstraint())
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notPassTest() {
        IResult result = ValidBs.newInstance().on(true, Constraints.assertFalseConstraint())
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

    @Test(expected = ClassCastException.class)
    public void unSupportClassTypeTest() {
        IResult result = ValidBs.newInstance().on("123", Constraints.assertFalseConstraint())
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

}
