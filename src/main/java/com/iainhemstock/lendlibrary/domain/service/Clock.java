package com.iainhemstock.lendlibrary.domain.service;

import java.util.Date;

public interface Clock {

    Date now();

    Date datePlusDays(Date date, int numDays);

}
