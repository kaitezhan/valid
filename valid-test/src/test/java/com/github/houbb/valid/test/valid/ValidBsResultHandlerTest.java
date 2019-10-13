package com.github.houbb.valid.test.valid;

import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.core.api.fail.Fails;
import com.github.houbb.valid.core.api.result.ResultHandlers;
import com.github.houbb.valid.core.bs.ValidBs;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.junit.Assert;
import org.junit.Test;

/**
 * 结果处理策略验证
 * @author binbin.hou
 * @since 0.1.2
 */
public class ValidBsResultHandlerTest {

    /**
     * 简单结果测试
     * @since 0.1.2
     */
    @Test
    public void simpleTest() {
        IResult result = ValidBs.on("12", JsrConstraints.sizeConstraintMin(2))
                .result(ResultHandlers.simple())
                .print();

        Assert.assertTrue(result.pass());
    }

    /**
     * 详细结果测试
     * @since 0.1.2
     */
    @Test
    public void detailTest() {
        IResult result = ValidBs.on("12", JsrConstraints.sizeConstraintMin(2))
                .result(ResultHandlers.detail())
                .print();

        Assert.assertTrue(result.pass());
    }

}
