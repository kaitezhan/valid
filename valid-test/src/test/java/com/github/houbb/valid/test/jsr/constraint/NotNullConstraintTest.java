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
public class NotNullConstraintTest {

    @Test
    public void notNullPassTest() {
        IResult result = ValidBs.on("", JsrConstraints.notNullConstraint())
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    /**
     * not null 约束验证不通过场景
     */
    @Test
    public void notNullNotPassTest() {
        IResult result = ValidBs.on(null, JsrConstraints.notNullConstraint())
                .result()
                .print();
        Assert.assertFalse(result.pass());
    }

}
