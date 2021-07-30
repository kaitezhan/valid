package com.github.houbb.valid.test.valid;

import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.core.api.fail.Fails;
import com.github.houbb.valid.core.bs.ValidBs;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.junit.Assert;
import org.junit.Test;

/**
 * 失败处理策略验证
 * @author binbin.hou
 * @since 0.1.2
 */
public class ValidBsFailTest {

    /**
     * 快速失败测试
     * @since 0.1.2
     */
    @Test
    public void failFastTest() {
        IResult result = ValidBs.on("12", JsrConstraints.sizeConstraintMin(3),
                JsrConstraints.patternConstraint("[678]{3}"))
                .fail(Fails.failFast())
                .valid()
                .print();

        Assert.assertEquals(1, result.notPassList().size());
    }

    /**
     * 验证完所有属性
     * @since 0.1.2
     */
    @Test
    public void failOverTest() {
        IResult result = ValidBs.on("12", JsrConstraints.sizeConstraintMin(3),
                JsrConstraints.patternConstraint("[678]{3}"))
                .fail(Fails.failOver())
                .valid()
                .print();

        Assert.assertEquals(2, result.notPassList().size());
    }

}
