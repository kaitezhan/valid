/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.api;

/**
 * <p> </p>
 *
 * <pre> Created: 2019/1/7 9:37 PM  </pre>
 * <pre> Project: valid  </pre>
 *
 * @author houbinbin
 */
public interface ICondition {

    /**
     * 生效的条件
     * @param context 执行的上下文
     * @return 是否生效
     */
    boolean conditon(IContext context);

}
