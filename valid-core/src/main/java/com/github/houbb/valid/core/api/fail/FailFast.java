package com.github.houbb.valid.core.api.fail;

import com.github.houbb.valid.api.constant.enums.FailTypeEnum;

/**
 * fail fast 实现
 * @author binbin.hou
 * @since 0.0.7
 */
public class FailFast extends AbstractFail {

    @Override
    protected FailTypeEnum failType() {
        return FailTypeEnum.FAIL_FAST;
    }

}
