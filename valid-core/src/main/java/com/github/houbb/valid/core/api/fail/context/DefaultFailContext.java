package com.github.houbb.valid.core.api.fail.context;

import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.api.fail.IFailContext;

import java.util.List;

/**
 * 默认的失败上下文
 * @author binbin.hou
 * @since 0.0.7
 */
public class DefaultFailContext implements IFailContext {

    /**
     * 当前约束结果
     * @since 0.0.7
     */
    private IConstraintResult constraintResult;

    /**
     * 约束结果列表
     * @since 0.0.7
     */
    private List<IConstraintResult> constraintResultList;

    public static DefaultFailContext newInstance() {
        return new DefaultFailContext();
    }

    @Override
    public IConstraintResult constraintResult() {
        return constraintResult;
    }

    public DefaultFailContext constraintResult(IConstraintResult constraintResult) {
        this.constraintResult = constraintResult;
        return this;
    }

    @Override
    public List<IConstraintResult> constraintResultList() {
        return constraintResultList;
    }

    public DefaultFailContext constraintResultList(List<IConstraintResult> constraintResultList) {
        this.constraintResultList = constraintResultList;
        return this;
    }
}
