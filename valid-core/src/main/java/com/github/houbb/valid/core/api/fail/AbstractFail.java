package com.github.houbb.valid.core.api.fail;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.valid.api.api.fail.IFail;
import com.github.houbb.valid.api.api.fail.IFailContext;
import com.github.houbb.valid.api.constant.enums.FailTypeEnum;

/**
 * fail 抽象实现
 * @author binbin.hou
 * @since 0.0.7
 */
public abstract class AbstractFail implements IFail {

    /**
     * 返回失败类型
     * @return 失败类型枚举
     * @since 0.0.7
     */
    protected abstract FailTypeEnum failType();

    /**
     * 处理相关信息
     * @param context 上下文
     * @since 0.0.7
     */
    protected void handle(final IFailContext context) {
    }

    @Override
    public FailTypeEnum fail(IFailContext context) {
        this.handle(context);

        FailTypeEnum failTypeEnum = this.failType();
        ArgUtil.notNull(failTypeEnum, "failTypeEnum");

        return failTypeEnum;
    }

}
