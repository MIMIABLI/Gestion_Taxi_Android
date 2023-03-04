package org.doranco.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class GetTime {
    private static SimpleDateFormat simpleDateFormat;

    public static String getTodayDateAndHour(Date date) {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        return simpleDateFormat.format(date);
    }

}
