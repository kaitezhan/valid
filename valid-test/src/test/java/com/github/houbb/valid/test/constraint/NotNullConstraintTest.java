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
public class NotNullConstraintTest {

    @Test
    public void notNullPassTest() {
        IResult result = ValidBs.newInstance().on("", Constraints.notNullConstraint())
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notNullNotPassTest() {
        IResult result = ValidBs.newInstance().on(null, Constraints.notNullConstraint())
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

}
