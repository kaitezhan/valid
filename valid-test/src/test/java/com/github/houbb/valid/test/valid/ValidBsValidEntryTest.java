package com.github.houbb.valid.test.valid;

import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.api.api.validator.IValidEntry;
import com.github.houbb.valid.core.api.fail.Fails;
import com.github.houbb.valid.core.api.validator.entry.ValidEntry;
import com.github.houbb.valid.core.bs.ValidBs;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.2
 */
public class ValidBsValidEntryTest {

    /**
     * 非空 validEntry 测试验证
     */
    @Test
    public void notNullValidEntryTest() {
        IValidEntry validEntry = ValidEntry.of(JsrConstraints.notNullConstraint());

        IResult result = ValidBs.on(null, validEntry)
            .valid()
            .print();

        Assert.assertFalse(result.pass());
    }

    /**
     * 消息指定测试
     * @since 0.0.4
     */
    @Test
    public void messageTest() {
        final IValidEntry validEntry = ValidEntry.of(JsrConstraints.notNullConstraint())
                .message("自定义：指定值不能为空");

        IResult result = ValidBs.on(null, validEntry)
                .validator()
                .valid();

        Assert.assertEquals("自定义：指定值不能为空", result.notPassList().get(0).message());
    }


    /**
     * 分组信息验证
     * （1）指定验证的分组为 String.class，只会命中第一个约束条件。
     */
    @Test
    public void groupTest() {
        final IValidEntry firstEntry = ValidEntry.of(JsrConstraints.sizeConstraint(5, 10))
                .group(String.class);

        final IValidEntry otherEntry = ValidEntry.of(JsrConstraints.sizeConstraint(3, 20))
                .group(Integer.class);

        IResult result = ValidBs
                .on("12", firstEntry, otherEntry)
                .group(String.class)
                .fail(Fails.failOver())
                .valid();

        Assert.assertEquals(1, result.notPassList().size());
    }

}
