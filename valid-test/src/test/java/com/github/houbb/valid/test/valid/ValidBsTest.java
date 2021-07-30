package com.github.houbb.valid.test.valid;

import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.core.api.constraint.chain.ConstraintChains;
import com.github.houbb.valid.core.api.fail.Fails;
import com.github.houbb.valid.core.api.validator.DefaultValidator;
import com.github.houbb.valid.core.bs.ValidBs;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.2
 */
public class ValidBsTest {

    /**
     * @since 0.1.2
     */
    @Test
    public void helloValidTest() {
        IResult result = ValidBs.on(null, JsrConstraints.notNullConstraint())
                .valid()
                .print();
        Assert.assertFalse(result.pass());
    }

    /**
     * @since 0.1.2
     */
    @Test
    public void helloValidAllConfigTest() {
        IResult result = ValidBs.on(null, JsrConstraints.notNullConstraint())
                .fail(Fails.failFast())
                .group()
                .validator(DefaultValidator.getInstance())
                .valid()
                .print();
        Assert.assertFalse(result.pass());
    }

    /**
     * 约束链测试
     * @since 0.0.4
     */
    @Test
    public void constraintChainTest() {
        final IConstraint constraintChain = ConstraintChains.chain(JsrConstraints.sizeConstraint(5, 10),
                JsrConstraints.sizeConstraint(10, 20));

        ValidBs.on("12", constraintChain)
                .fail(Fails.failOver())
                .valid()
                .print();
    }

    /**
     * 多个约束条件测试
     * @since 0.0.5
     */
    @Test
    public void multiConstraintTest() {
        IResult result = ValidBs
                .on("12", JsrConstraints.sizeConstraint(5, 10),
                        JsrConstraints.sizeConstraint(10, 20))
                .fail(Fails.failOver())
                .validator()
                .valid();

        Assert.assertEquals(2, result.notPassList().size());
        result.print();
    }

}
