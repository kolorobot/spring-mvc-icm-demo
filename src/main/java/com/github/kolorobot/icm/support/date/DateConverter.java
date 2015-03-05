package com.github.kolorobot.icm.support.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static final String FORMAT = "yyyy-MM-dd HH:m:s";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);

    public static String toDateString(Date date) {
        return dateFormat.format(date);
    }

    public static Date toDate(String source) {
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            return null;
        }
    }
}
