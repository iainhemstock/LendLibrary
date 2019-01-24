package com.iainhemstock.lendlibrary;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Application {

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(1974, 8, 7);
        System.out.println(calendar.getTime());
    }
}
