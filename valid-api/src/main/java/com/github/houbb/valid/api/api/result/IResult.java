package com.github.houbb.valid.api.api.result;

import com.github.houbb.valid.api.api.constraint.IConstraintResult;

import java.util.List;

/**
 * 结果处理
 * TODO:
 * （1）可以考虑添加 print() 接口，输出相关结果信息。
 * print 可以指定相关配置，输出位置，输出级别等等。
 *
 * （2）添加 throws 未通过的信息。
 * 详细程度。
 *
 * 二者可以抽象为结果的 handler。
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
