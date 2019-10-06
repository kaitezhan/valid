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
public class NullConstraintTest {

    @Test
    public void nullPassTest() {
        IResult result = ValidBs.newInstance().on(null, Constraints.nullConstraint())
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void nullNotPassTest() {
        IResult result = ValidBs.newInstance().on("", Constraints.nullConstraint())
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

}
