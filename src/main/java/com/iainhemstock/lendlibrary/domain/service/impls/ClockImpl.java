package com.iainhemstock.lendlibrary.domain.service.impls;

import com.iainhemstock.lendlibrary.domain.service.Clock;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ClockImpl implements Clock {

    @Override
    public Date now() {
        return new Date();
    }

    @Override
    public Date datePlusDays(Date date, int numDays) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, numDays);
        return calendar.getTime();
    }
}
