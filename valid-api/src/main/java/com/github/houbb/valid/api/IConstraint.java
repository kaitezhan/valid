/*
 * Copyright (c)  2019. houbinbin Inc.
 * valid All rights reserved.
 */

package com.github.houbb.valid.api;

/**
 * <p> </p>
 *
 * <pre> Created: 2019/1/7 9:45 PM  </pre>
 * <pre> Project: valid  </pre>
 *
 * @author houbinbin
 */
public interface IConstraint {


    /**
     * 执行约束
     * @param context 执行上下文
     * @return 执行的结果
     */
    IConstraintResult constraint(IContext context);

}
