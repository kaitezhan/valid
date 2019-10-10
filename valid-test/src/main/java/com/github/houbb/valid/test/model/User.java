package com.github.houbb.valid.test.model;

import com.github.houbb.valid.core.annotation.constraint.AllEquals;
import com.github.houbb.valid.core.annotation.constraint.Ranges;

/**
 * 用户信息
 * @author binbin.hou
 * @since 0.0.9
 */
public class User {

    /**
     * 名称
     */
    private String name;

    /**
     * 原始密码
     */
    @AllEquals("password2")
    private String password;

    /**
     * 新密码
     */
    private String password2;

    /**
     * 性别
     */
    @Ranges({"boy", "girl"})
    private String sex;

    public String name() {
        return name;
    }

    public User name(String name) {
        this.name = name;
        return this;
    }

    public String password() {
        return password;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public String password2() {
        return password2;
    }

    public User password2(String password2) {
        this.password2 = password2;
        return this;
    }

    public String sex() {
        return sex;
    }

    public User sex(String sex) {
        this.sex = sex;
        return this;
    }

}
