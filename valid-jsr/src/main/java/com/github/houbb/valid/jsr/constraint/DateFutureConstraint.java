package com.github.houbb.valid.jsr.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.core.api.constraint.AbstractGreatThanConstraint;

import java.util.Date;

/**
 * 判断当前时间，是否在未来
 *
 * 可以进一步抽象为：
 * （1）大于等于
 * （2）小于等于
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
class DateFutureConstraint extends AbstractGreatThanConstraint<Date> {

    public DateFutureConstraint(boolean inclusive, Date expect) {
        super(inclusive, expect);
    }

    public DateFutureConstraint(Date expect) {
        super(expect);
    }

}
