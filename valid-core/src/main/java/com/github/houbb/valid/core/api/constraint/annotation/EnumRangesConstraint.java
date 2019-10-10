package com.github.houbb.valid.core.api.constraint.annotation;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.api.constraint.annotation.IAnnotationConstraint;
import com.github.houbb.valid.core.annotation.constraint.EnumRanges;
import com.github.houbb.valid.core.api.constraint.AbstractContainsConstraint;

import java.util.List;

/**
 * 范围注解约束实现
 * @author binbin.hou
 * @since 0.0.9
 */
@NotThreadSafe
public class EnumRangesConstraint implements IAnnotationConstraint<EnumRanges> {

    /**
     * 注解信息
     */
    private EnumRanges annotation;

    @Override
    public void init(EnumRanges annotation) {
        ArgUtil.notNull(annotation, "EnumRanges");

        this.annotation = annotation;
    }

    @Override
    public IConstraintResult constraint(IConstraintContext context) {
        return new AbstractContainsConstraint<String>() {
            @Override
            protected List<String> ranges() {
                return getEnumValues(annotation.value());
            }
        }.constraint(context);
    }

    /**
     * 获取枚举值对应的信息
     * @param enumClass 枚举类
     * @return 枚举说明
     * @since 0.0.9
     */
    private List<String> getEnumValues(Class<Enum> enumClass) {
        Enum[] enums = enumClass.getEnumConstants();

        return ArrayUtil.toList(enums, new IHandler<Enum, String>() {
            @Override
            public String handle(Enum anEnum) {
                return anEnum.toString();
            }
        });
    }

}
