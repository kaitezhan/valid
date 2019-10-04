package com.github.houbb.valid.api.exception;

/**
 * 校验运行时异常
 * @author binbin.hou
 * @since 0.0.2
 */
public class ValidRuntimeException extends RuntimeException {

    public ValidRuntimeException() {
    }

    public ValidRuntimeException(String message) {
        super(message);
    }

    public ValidRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidRuntimeException(Throwable cause) {
        super(cause);
    }

    public ValidRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
