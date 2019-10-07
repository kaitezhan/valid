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
public class AssertTrueConstraintTest {

    @Test
    public void passTest() {
        IResult result = ValidBs.newInstance().on(true, Constraints.assertTrueConstraint())
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passNullTest() {
        IResult result = ValidBs.newInstance().on(null, Constraints.assertTrueConstraint())
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notPassTest() {
        IResult result = ValidBs.newInstance().on(false, Constraints.assertTrueConstraint())
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

}
