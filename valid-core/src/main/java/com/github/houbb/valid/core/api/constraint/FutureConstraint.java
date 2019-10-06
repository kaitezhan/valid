package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 判断当前时间，是否在未来
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
class FutureConstraint extends AbstractCombineConstraint {

    /**
     * 预期值
     * @since 0.0.3
     */
    private final Object expect;

    /**
     * 构造器
     * @since 0.0.3
     * @param expect 预期值
     */
    public FutureConstraint(Object expect) {
        this.expect = expect;
    }

    @Override
    protected List<Class> getSupportClassList() {
        return SupportClassTypeUtil.getPastFutureSupportClassList();
    }

    @Override
    protected AbstractConstraint getConstraintInstance(IConstraintContext context) {
        final Object value = context.value();
        if(value instanceof Date) {
            Date exceptDate = (Date)expect;
            return new DateFutureConstraint(exceptDate);
        }

        Calendar exceptCalendar = (Calendar)expect;
        return new CalendarFutureConstraint(exceptCalendar);
    }

}
