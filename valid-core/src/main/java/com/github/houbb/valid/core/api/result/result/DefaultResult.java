package com.github.houbb.valid.core.api.result.result;

import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.api.result.IResult;

import java.util.List;

/**
 * 默认结果
 * @author binbin.hou
 * @since 0.0.2
 */
public class DefaultResult implements IResult {

    /**
     * 是否全部通过验证
     * @since 0.0.2
     */
    private boolean pass;

    /**
     * 未通过的列表信息
     * @since 0.0.2
     */
    private List<IConstraintResult> notPassList;

    /**
     * 所有的验证结果列表
     * @since 0.0.2
     */
    private List<IConstraintResult> allList;

    public static DefaultResult newInstance() {
        return new DefaultResult();
    }

    @Override
    public boolean pass() {
        return pass;
    }

    public DefaultResult pass(boolean pass) {
        this.pass = pass;
        return this;
    }

    @Override
    public List<IConstraintResult> notPassList() {
        return notPassList;
    }

    public DefaultResult notPassList(List<IConstraintResult> notPassList) {
        this.notPassList = notPassList;
        return this;
    }

    @Override
    public List<IConstraintResult> allList() {
        return allList;
    }

    public DefaultResult allList(List<IConstraintResult> allList) {
        this.allList = allList;
        return this;
    }

    @Override
    public String toString() {
        return "DefaultResult{" +
                "pass=" + pass +
                ", notPassList=" + notPassList +
                ", allList=" + allList +
                '}';
    }
}
