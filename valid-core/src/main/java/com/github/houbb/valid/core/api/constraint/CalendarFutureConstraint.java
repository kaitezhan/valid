package com.github.houbb.valid.core.api.constraint;

import java.util.Calendar;

/**
 * 日历是否在未来
 * @author binbin.hou
 * @since 0.0.3
 */
class CalendarFutureConstraint extends AbstractGreatThanConstraint<Calendar> {

    public CalendarFutureConstraint(boolean inclusive, Calendar expect) {
        super(inclusive, expect);
    }

    public CalendarFutureConstraint(Calendar expect) {
        super(expect);
    }
}
