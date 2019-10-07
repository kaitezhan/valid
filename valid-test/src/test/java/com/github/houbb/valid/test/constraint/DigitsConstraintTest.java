package com.github.houbb.valid.test.constraint;

import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.core.api.constraint.Constraints;
import com.github.houbb.valid.core.bs.ValidBs;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author binbin.hou
 * @since 0.0.3
 */
public class DigitsConstraintTest {

    @Test
    public void passIntegerTest() {
        IResult result = ValidBs.newInstance().on(12345, Constraints.digitsConstraint(5))
            .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passLongTest() {
        IResult result = ValidBs.newInstance().on(12345L, Constraints.digitsConstraint(5))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passStringTest() {
        IResult result = ValidBs.newInstance().on("12345", Constraints.digitsConstraint(5))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passBigIntegerTest() {
        BigInteger bigInteger = new BigInteger("12345");
        IResult result = ValidBs.newInstance().on(bigInteger, Constraints.digitsConstraint(5))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passBigDecimalTest() {
        BigDecimal bigInteger = new BigDecimal("12345");
        IResult result = ValidBs.newInstance().on(bigInteger, Constraints.digitsConstraint(5))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passFractionTest() {
        IResult result = ValidBs.newInstance().on("2.23", Constraints.digitsConstraint(1,2))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void passNullTest() {
        IResult result = ValidBs.newInstance().on(null, Constraints.digitsConstraint(1, 2))
                .result();
        Assert.assertTrue(result.pass());
        System.out.println(result);
    }

    @Test
    public void notPassTest() {
        IResult result = ValidBs.newInstance().on("123456", Constraints.digitsConstraint(5))
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
