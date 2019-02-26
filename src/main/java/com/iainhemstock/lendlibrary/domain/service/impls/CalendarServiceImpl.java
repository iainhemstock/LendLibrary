package com.iainhemstock.lendlibrary.domain.service.impls;

import com.iainhemstock.lendlibrary.domain.service.CalendarService;

import java.util.Date;

public class CalendarServiceImpl implements CalendarService {

    @Override
    public Date now() {
        return new Date();
    }
}
