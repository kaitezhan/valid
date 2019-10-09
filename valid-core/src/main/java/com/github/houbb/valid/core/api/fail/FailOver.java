package com.github.houbb.valid.core.api.fail;

import com.github.houbb.valid.api.constant.enums.FailTypeEnum;

/**
 * fail over 实现
 * @author binbin.hou
 * @since 0.0.7
 */
public class FailOver extends AbstractFail {

    @Override
    protected FailTypeEnum failType() {
        return FailTypeEnum.FAIL_OVER;
    }

}
