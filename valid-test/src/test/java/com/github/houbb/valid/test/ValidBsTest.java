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

    /**
     * 消息指定测试
     * @since 0.0.4
     */
    @Test
    public void messageTest() {
        IResult result = ValidBs.newInstance()
                .on(null, Constraints.notNullConstraint())
                .message("指定值必填")
                .result();

        System.out.println(result);
    }

}
