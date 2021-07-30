package com.github.houbb.valid.test.bean;

import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.core.api.fail.Fails;
import com.github.houbb.valid.core.bs.ValidBs;
import com.github.houbb.valid.test.model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * 核心模块-基于 bean 进行校验
 * @author binbin.hou
 * @since 0.1.2
 */
public class ValidBsCoreBeanTest {

    /**
     * 对象验证失败测试
     * @since 0.0.9
     */
    @Test
    public void beanFailTest() {
        User user = new User();
        user.sex("what").password("old").password2("new")
            .failType("DEFINE");

        IResult result = ValidBs.on(user)
                .fail(Fails.failOver())
                .valid()
                .print();

        Assert.assertFalse(result.pass());
    }

    @Test
    public void beanPassTest() {
        User user = new User();
        user.sex("boy").password("old").password2("old")
                .failType("FAIL_OVER").name("name");

        IResult result = ValidBs.on(user)
                .fail(Fails.failOver())
                .valid();

        Assert.assertTrue(result.pass());
    }

}
