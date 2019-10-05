package com.github.houbb.valid.core.api.result;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.valid.api.api.result.IResultHandler;

/**
 * 详细结果处理工具类
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class ResultHandlers {

    /**
     * 简单实现
     * @since 0.0.3
     */
    public static final IResultHandler SIMPLE = Instances.singleton(SimpleResultHandler.class);

    /**
     * 详情实现
     */
    public static final IResultHandler DETAIL = Instances.singleton(DetailResultHandler.class);

}
