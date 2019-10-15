package com.github.houbb.valid.core.api.validator.entry;

import com.github.houbb.valid.api.api.validator.IValidEntryInstanceContext;

/**
 * 验证明细上下文
 * @author binbin.hou
 * @since 0.1.3
 */
public class ValidEntryInstanceContext implements IValidEntryInstanceContext {

    /**
     * 验证分组信息
     * @since 0.1.3
     */
    private Class[] group;

    /**
     * 创建对象实例
     * @return 上下文实例
     * @since 0.1.3
     */
    public static ValidEntryInstanceContext newInstance() {
        return new ValidEntryInstanceContext();
    }

    public ValidEntryInstanceContext group(Class[] group) {
        this.group = group;
        return this;
    }

    @Override
    public Class[] group() {
        return group;
    }

}
