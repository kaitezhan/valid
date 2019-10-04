package com.github.houbb.valid.api.api.result;

import com.github.houbb.valid.api.api.constraint.IConstraintResult;

import java.util.List;

/**
 * 结果处理
 * @author binbin.hou
 * @since 0.0.2
 */
public interface IResult {

    /**
     * 是否全部通过验证
     * @return 是否
     * @since 0.0.2
     */
    boolean pass();

    /**
     * 未通过的列表信息
     * @return 验证结果
     * @since 0.0.2
     */
    List<IConstraintResult> notPassList();

    /**
     * 所有的验证结果列表
     * @return 所有的验证结果
     * @since 0.0.2
     */
    List<IConstraintResult> allList();

}
