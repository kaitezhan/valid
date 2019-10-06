package com.github.houbb.valid.core.api.constraint;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 判断当前时间，是否在过去
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
class PastConstraint extends AbstractCombineConstraint {

    /**
     * 预期值
     * @since 0.0.3
     */
    private final Object expect;

    /**
     * 构造器
     * @param expect 预期值
     * @since 0.0.3
     */
    public PastConstraint(Object expect) {
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
            return new DatePastConstraint(exceptDate);
        }

        Calendar exceptCalendar = (Calendar)value;
        return new CalendarPastConstraint(exceptCalendar);
    }
    
}
