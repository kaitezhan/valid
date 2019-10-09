/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.core.api.constraint.result.DefaultConstraintResult;

import java.lang.reflect.Array;
import java.util.List;

/**
 * 严格抽象约束实现
 * （1）默认的 jsr 标准，对于 null 值其实是相对宽松的。
 * @see AbstractConstraint 对比这个，这里对于 null值更加严格。
 * @author binbin.hou
 * @param <T> 泛型
 * @since 0.0.8
 */
@ThreadSafe
public abstract class AbstractStrictConstraint<T> extends AbstractConstraint<T> {

    @Override
    protected boolean isNullPass(T value) {
        return false;
    }

}
