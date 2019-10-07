package com.github.houbb.valid.api.api.result;

import com.github.houbb.valid.api.api.constraint.IConstraintResult;

import java.util.List;

/**
 * 结果处理
 * @author binbin.hou
 * @since 0.0.2
 * @param <T> 泛型
 */
public interface IResultHandler<T> {

    /**
     * 对约束结果进行统一处理
     * @param constraintResultList 约束结果列表
     * @return 结果
     */
    T handle(final List<IConstraintResult> constraintResultList);

}
