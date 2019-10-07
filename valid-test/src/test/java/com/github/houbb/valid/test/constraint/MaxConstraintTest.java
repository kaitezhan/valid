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
public class MaxConstraintTest {

    @Test
    public void passTest() {
        IResult result = ValidBs.newInstance().on(99, Constraints.maxConstraint(100))
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passInclusiveTest() {
        IResult result = ValidBs.newInstance().on(100, Constraints.maxConstraint(true, 100))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passNullTest() {
        IResult result = ValidBs.newInstance().on(null, Constraints.maxConstraint(100))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notPassTest() {
        IResult result = ValidBs.newInstance().on(101, Constraints.maxConstraint(100))
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

    /**
     * TODO: 后期可以考虑添加 float double 的拓展支持。
     */
    @Test(expected = ClassCastException.class)
    public void classCastException() {
        IResult result = ValidBs.newInstance().on(123.34f, Constraints.maxConstraint(100))
                .result();
        System.out.println(result);
    }

}
