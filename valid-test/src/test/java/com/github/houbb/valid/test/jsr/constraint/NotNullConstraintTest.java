package com.github.houbb.valid.test.jsr.constraint;

import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.core.api.validator.entry.ValidatorEntryFactory;
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
        IResult result = ValidBs.on("", ValidatorEntryFactory.of(JsrConstraints.notNullConstraint()))
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notNullNotPassTest() {
        IResult result = ValidBs.on(null, JsrConstraints.notNullConstraint())
                .valid()
                .result()
                .print();
        Assert.assertFalse(result.pass());
    }

}
