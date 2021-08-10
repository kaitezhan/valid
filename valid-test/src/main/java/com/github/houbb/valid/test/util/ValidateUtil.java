package com.github.houbb.valid.test.util;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class ValidateUtil {

    /**
     * 使用hibernate的注解来进行验证
     */
    private  static Validator validator = Validation
            .byProvider(HibernateValidator.class)
            .configure().failFast(true)
            .buildValidatorFactory()
            .getValidator();

    public static <T> void validate(T t) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            final String msg = constraintViolations.iterator().next().getMessage();
            throw new IllegalArgumentException(msg);
        }
    }

}
