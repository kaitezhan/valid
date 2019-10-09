package com.github.houbb.valid.api.api.fail;

import com.github.houbb.valid.api.constant.enums.FailTypeEnum;

/**
 * 失败处理接口
 * @author binbin.hou
 * @since 0.0.7
 */
public interface IFail {

    /**
     * 失败类型
     * @param context 上下文
     * @return 失败类型枚举
     * @since 0.0.7
     */
    FailTypeEnum fail(final IFailContext context);

}
