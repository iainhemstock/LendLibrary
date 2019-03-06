package com.iainhemstock.lendlibrary.domain.service;

import com.iainhemstock.lendlibrary.domain.service.impls.ClockImpl;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class ClockShould {

    private ClockImpl clock = new ClockImpl();
    private Date today = clock.now();

    @Test
    public void add_a_given_number_of_days_to_today() {

        Date tomorrow = clock.datePlusDays(today,1);
        Date oneWeek = clock.datePlusDays(today, 7);
        Date twoWeeks = clock.datePlusDays(today, 14);

        assertThat(tomorrow,
                is(equalTo(todayPlusDays(1))));

        assertThat(oneWeek,
                is(equalTo(todayPlusDays(7))));

        assertThat(twoWeeks,
                is(equalTo(todayPlusDays(14))));
    }

    private Date todayPlusDays(final int numDays) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, numDays);
        return calendar.getTime();
    }
}
