package com.github.houbb.valid.test;

import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.core.api.constraint.Constraints;
import com.github.houbb.valid.core.bs.ValidBs;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.2
 */
public class ValidBsTest {

    @Test
    public void simpleTest() {
        IResult result = ValidBs.newInstance().on(null, Constraints.notNullConstraint()).result();
        System.out.println(result);
    }

}
