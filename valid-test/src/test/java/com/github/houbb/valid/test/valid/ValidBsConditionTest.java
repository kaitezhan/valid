package com.github.houbb.valid.test.valid;

import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.condition.IConditionContext;
import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.api.api.validator.IValidEntry;
import com.github.houbb.valid.core.api.fail.Fails;
import com.github.houbb.valid.core.api.result.ResultHandlers;
import com.github.houbb.valid.core.api.validator.entry.ValidEntry;
import com.github.houbb.valid.core.bs.ValidBs;
import com.github.houbb.valid.jsr.constraint.JsrConstraints;
import org.junit.Assert;
import org.junit.Test;

/**
 * 失败处理策略验证
 * @author binbin.hou
 * @since 0.1.2
 */
public class ValidBsConditionTest {

    /**
     * 条件指定测试
     * @since 0.1.2
     */
    @Test
    public void conditionTest() {
        IValidEntry validEntry = ValidEntry.of(JsrConstraints.sizeConstraintMin(3));

        final ICondition condition = new ICondition() {
            @Override
            public boolean condition(IConditionContext conditionContext) {
                final String value = conditionContext.value().toString();
                return value.startsWith("1");
            }
        };
        final ICondition condition2 = new ICondition() {
            @Override
            public boolean condition(IConditionContext conditionContext) {
                final String value = conditionContext.value().toString();
                return value.startsWith("2");
            }
        };

        IResult result = ValidBs.on("12", validEntry.condition(condition))
                .fail(Fails.failFast())
                .valid(ResultHandlers.detail())
                .print();
        Assert.assertFalse(result.pass());

        IResult result2 = ValidBs.on("12", validEntry.condition(condition2))
                .fail(Fails.failFast())
                .valid(ResultHandlers.detail())
                .print();
        Assert.assertTrue(result2.pass());
    }

}
