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
public class SizeConstraintTest {

    @Test
    public void passTest() {
        IResult result = ValidBs.newInstance().on("23", Constraints.sizeConstraint(1,2))
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passNullTest() {
        IResult result = ValidBs.newInstance().on(null, Constraints.sizeConstraint(1, 2))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notPassTest() {
        IResult result = ValidBs.newInstance().on("12345", Constraints.sizeConstraint(1, 2))
                .result();
        Assert.assertFalse(result.pass());
        System.out.println(result);
    }

    /**
     * （1）此处应该和组合型的保持一致。
     * （2）防止痴呆设计，同时给出支持的数据类型。
     */
    @Test(expected = ClassCastException.class)
    public void unSupportClassTypeTest() {
        IResult result = ValidBs.newInstance().on(12345, Constraints.sizeConstraint(1, 2))
                .result();
    }

}
