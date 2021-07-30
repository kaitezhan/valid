package com.github.houbb.valid.test.util;

import com.github.houbb.valid.api.exception.ValidRuntimeException;
import com.github.houbb.valid.core.util.CoreValidHelper;
import com.github.houbb.valid.jsr.util.JsrValidHelper;
import com.github.houbb.valid.test.model.User;
import org.junit.Test;

/**
 * ValidHelper 测试工具类
 *
 * @author binbin.hou
 * @since 0.1.4
 */
public class CoreValidHelperTest {

    @Test
    public void failOverTest() {
        try {
            User user = new User();
            user.sex("what").password("old").password2("new");

            JsrValidHelper.failOverThrow(user);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test(expected = ValidRuntimeException.class)
    public void failFastTest() {
        User user = new User();
        user.sex("what").password("old").password2("new");

        CoreValidHelper.failFastThrow(user);
    }

}
