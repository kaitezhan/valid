package com.github.houbb.valid.api.api.validator;

/**
 * 校验明细对象上下文接口\
 * （1）主要用于存放信息，便于后期拓展。
 * @author binbin.hou
 * @since 0.1.3
 */
public interface IValidEntryInstanceContext {

    /**
     * 验证分组信息
     * @return 验证分组信息
     * @since 0.1.3
     */
    Class[] group();

}
