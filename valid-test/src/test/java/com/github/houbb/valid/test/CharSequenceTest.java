package com.github.houbb.valid.test;

import com.github.houbb.valid.core.api.constraint.DecimalMaxConstraint;
import com.github.houbb.valid.core.bs.ValidBs;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.3
 */
public class CharSequenceTest {

    @Test
    public void charsTest() {
        final String chars = "123.45";
        System.out.println(ValidBs.failType().on(chars, new DecimalMaxConstraint(expectValue)).result());
    }

}
