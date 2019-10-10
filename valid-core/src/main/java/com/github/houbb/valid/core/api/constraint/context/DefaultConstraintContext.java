package com.github.houbb.valid.core.api.constraint.context;

import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.fail.IFail;

import java.lang.reflect.Field;
import java.util.List;
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
     * 失败处理实现
     * @since 0.0.7
     */
    private IFail fail;

    /**
     * 属性 map
     * @since 0.0.4
     */
    private Map<String, Object> attrMap = new ConcurrentHashMap<>();

    /**
     * 所有的字段列表信息
     * @since 0.0.9
     */
    private List<Field> allFieldList;

    /**
     * 当前对应的对象实例信息
     * @since 0.0.9
     */
    private Object instance;

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
    public IFail fail() {
        return fail;
    }

    public DefaultConstraintContext fail(IFail fail) {
        this.fail = fail;
        return this;
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

    /**
     * 设置字段列表信息
     * @param allFieldList 所有字段列表信息
     * @return this
     * @since 0.0.9
     */
    public DefaultConstraintContext allFieldList(List<Field> allFieldList) {
        this.allFieldList = allFieldList;
        return this;
    }

    @Override
    public Object getFieldValue(String fieldName) {
        if(CollectionUtil.isEmpty(allFieldList)
            || ObjectUtil.isNull(instance)) {
            return null;
        }

        for(Field field : allFieldList) {
            if(field.getName().equals(fieldName)) {
                return ReflectFieldUtil.getValue(field, instance);
            }
        }
        return null;
    }

    public DefaultConstraintContext instance(Object instance) {
        this.instance = instance;
        return this;
    }
}
