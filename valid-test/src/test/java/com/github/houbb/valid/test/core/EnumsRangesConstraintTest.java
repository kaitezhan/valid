package com.github.houbb.valid.test.core;

import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.api.constant.enums.FailTypeEnum;
import com.github.houbb.valid.core.api.constraint.Constraints;
import com.github.houbb.valid.core.bs.ValidBs;
import org.junit.Assert;
import org.junit.Test;

/**
 * 枚举指定范围内测试
 * @author binbin.hou
 * @since 0.1.2
 */
public class EnumsRangesConstraintTest {

    @Test
    public void notPassTest() {
        IResult result = ValidBs.on("DEFINE", Constraints.enumRangesConstraint(FailTypeEnum.class))
            .result();

        Assert.assertFalse(result.pass());
    }

    @Test
    public void passTest(){
        IResult result = ValidBs.on("FAIL_FAST", Constraints.enumRangesConstraint(FailTypeEnum.class))
                .result();

        Assert.assertTrue(result.pass());
    }

}
