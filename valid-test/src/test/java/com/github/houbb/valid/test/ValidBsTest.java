package com.github.houbb.valid.test;

import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.api.constant.enums.FailTypeEnum;
import com.github.houbb.valid.core.api.constraint.Constraints;
import com.github.houbb.valid.core.api.constraint.chain.ConstraintChains;
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

    /**
     * 约束链测试
     * @since 0.0.4
     */
    @Test
    public void chainTest() {
        IResult result = ValidBs.newInstance()
                .failType(FailTypeEnum.FAIL_OVER)
                .on("12", ConstraintChains.chain(Constraints.sizeConstraint(5, 10),
                        Constraints.sizeConstraint(10, 20)))
                .message("指定值必须满足约束链条件")
                .result();

        System.out.println(result);
    }

}
