package com.github.houbb.valid.core.api.constraint.context;

import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.constant.enums.FailTypeEnum;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认约束条件上下文
 * @author binbin.hou
 * @since 0.0.2
 */
public class DefaultConstraintContext implements IConstraintContext {

    /**
     * 待校验的值
     * @since 0.0.2
     */
    private Object value;

    /**
     * 消息信息
     * @since 0.0.4
     */
    private String message;

    /**
     * 失败模式
     * @since 0.0.4
     */
    private FailTypeEnum failType;

    /**
     * 属性 map
     * @since 0.0.4
     */
    private Map<String, Object> attrMap = new ConcurrentHashMap<>();

    public static DefaultConstraintContext newInstance() {
        return new DefaultConstraintContext();
    }

    @Override
    public Object value() {
        return this.value;
    }

    public DefaultConstraintContext value(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public String message() {
        return message;
    }

    public DefaultConstraintContext message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public FailTypeEnum failType() {
        return failType;
    }

    @Override
    public IConstraintContext putAttr(String key, Object object) {
        this.attrMap.put(key, object);
        return this;
    }

    @Override
    public Object getAttr(String key) {
        return attrMap.get(key);
    }

    public DefaultConstraintContext failType(FailTypeEnum failType) {
        this.failType = failType;
        return this;
    }


}
