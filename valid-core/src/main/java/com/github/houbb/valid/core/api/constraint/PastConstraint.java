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
public class PastConstraint extends AbstractCombineConstraint {

    @Override
    protected List<Class> getSupportClassList() {
        return SupportClassTypeUtil.getPastFutureSupportClassList();
    }

    @Override
    protected AbstractConstraint getConstraintInstance(IConstraintContext context) {
        final Object value = context.value();
        if(value instanceof Date) {
            Date date = (Date)value;
            return new DatePastConstraint(date);
        }

        Calendar calendar = (Calendar)value;
        return new CalendarPastConstraint(calendar);
    }
    
}
