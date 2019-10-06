package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

import java.util.Collection;
import java.util.Map;

/**
 * 为 size 约束
 * @see java.lang.reflect.Array#getLength(Object)  数组
 * @see CharSequence#length()
 * @see Collection#size()
 * @see Map#size()
 *
 * @author binbin.hou
 * @since 0.0.3
 * @see javax.validation.constraints.Size
 */
@ThreadSafe
public class SizeConstraint extends AbstractConstraint {

    /**
     * 最小值
     * size the element must be higher or equal to
     * @since 0.0.3
     */
    private final int min;

    /**
     * 最大值
     * size the element must be lower or equal to
     * @since 0.0.3
     */
    private final int max;

    public SizeConstraint(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean pass(final IConstraintContext context, final Object value) {
        if(ObjectUtil.isNull(value)) {
            return true;
        }

        //类型判断，根据概率 String > collection > map > array
        if(value instanceof String) {
            String string = (String)value;
            return sizeCheck(string.length());
        }
        if(value instanceof Collection) {
            Collection collection = (Collection)value;
            return sizeCheck(collection.size());
        }
        if(value instanceof Map) {
            Map map = (Map)value;
            return sizeCheck(map.size());
        }
        if(ClassTypeUtil.isArray(value.getClass())) {
            Object[] array = (Object[])value;
            return sizeCheck(array.length);
        }

        // 直接认为通过
        return true;
    }

    @Override
    protected boolean supportClassType(Class valueClassType) {
        if(String.class == valueClassType) {
            return true;
        }
        if(ClassTypeUtil.isCollection(valueClassType)) {
            return true;
        }
        if(ClassTypeUtil.isArray(valueClassType)) {
            return true;
        }
        if(ClassTypeUtil.isMap(valueClassType)) {
            return true;
        }

        return false;
    }

    /**
     * 大小检查
     * @param size 大小
     * @return 是否满足大小
     * @since 0.0.3
     */
    private boolean sizeCheck(final int size) {
        return size >= min && size <= max;
    }

    @Override
    protected String expectValue(final IConstraintContext context) {
        return "size must be in ["+min+","+max+"]";
    }

}
