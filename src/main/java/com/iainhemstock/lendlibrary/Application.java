package com.iainhemstock.lendlibrary;

import java.util.Calendar;
import java.util.Date;

public class Application {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DATE, 14);
        Date returnDate = calendar.getTime();

        System.out.println(today);
        System.out.println(returnDate);
    }

}
