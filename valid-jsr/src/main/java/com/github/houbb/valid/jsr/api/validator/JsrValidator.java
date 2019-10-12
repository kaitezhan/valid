package com.github.houbb.valid.jsr.api.validator;

import com.github.houbb.valid.api.api.validator.IValidatorEntry;
import com.github.houbb.valid.core.api.validator.DefaultValidator;

import java.lang.reflect.Field;
import java.util.List;

/**
 * JSR 验证器
 *
 * （1）保证 hibernate-only 和 jsr-only
 * （2）保证二者可以和 {@link DefaultValidator} 进行整合
 * （3）保证性能，避免重复列表循环。
 *
 *  同时保证代码的优雅与可重复使用？？
 *
 * 如何使用
 *
 * 灵活性：
 * <pre>
 *     ValidBs.valid(defaultValidator);
 *     ValidBs.valid(jsrValidator);
 *     ValidBs.valid(hibernateValidator);
 *     ValidBs.valid(validator, jsrValidator, hibernateValidator);
 * </pre>
 *
 * 性能：
 * defaultValidator+jsrValidator=defaultJsrValidator
 *
 * 全量而不缺失：
 * 如何保证每一个实现都是独立的，合并起来又是全量的？
 * 而不是通过一个组合的实现。
 * @author binbin.hou
 * @since 0.1.1
 * @see com.github.houbb.valid.core.api.validator.DefaultValidator 默认验证器实现
 */
public class JsrValidator extends DefaultValidator {

    @Override
    protected List<IValidatorEntry> buildValidatorEntryList(List<Field> fieldList, Field field, Object instance) {
        List<IValidatorEntry> defaultEntryList = super.buildValidatorEntryList(fieldList, field, instance);

        return defaultEntryList;
    }

}
