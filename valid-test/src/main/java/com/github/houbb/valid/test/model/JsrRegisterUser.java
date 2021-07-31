package com.github.houbb.valid.test.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * JSR 用户信息
 * @author binbin.hou
 * @since 0.1.1
 */
public class JsrRegisterUser {

    @Size(min = 4, max = 6, message = "code 错误")
    private String code;

    @Size(min = 10, max = 16, message = "email 错误")
    private String email;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "JsrRegisterUser{" +
                "code='" + code + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
