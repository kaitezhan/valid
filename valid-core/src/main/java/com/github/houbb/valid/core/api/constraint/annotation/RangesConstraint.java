package com.github.houbb.valid.core.api.constraint.annotation;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.api.constraint.annotation.IAnnotationConstraint;
import com.github.houbb.valid.core.annotation.constraint.Ranges;
import com.github.houbb.valid.core.api.constraint.AbstractContainsConstraint;

import java.util.List;

/**
 * 范围注解约束实现
 * @author binbin.hou
 * @since 0.0.9
 */
@NotThreadSafe
public class RangesConstraint implements IAnnotationConstraint<Ranges> {

    /**
     * 注解信息
     */
    private Ranges annotation;

    @Override
    public void init(Ranges annotation) {
        ArgUtil.notNull(annotation, "ranges annotation");
        ArgUtil.notEmpty(annotation.value(), "ranges");

        this.annotation = annotation;
    }

    @Override
    public IConstraintResult constraint(IConstraintContext context) {
        return new AbstractContainsConstraint<String>() {
            @Override
            protected List<String> ranges() {
                return ArrayUtil.arrayToList(annotation.value());
            }
        }.constraint(context);
    }

}
