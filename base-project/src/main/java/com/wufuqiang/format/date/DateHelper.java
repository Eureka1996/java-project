package com.wufuqiang.format.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateHelper {

    public static LocalDateTime getTodayBegin(){
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    public static LocalDateTime getYesterdayBegin(){
        return LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN);
    }


    public static String getDateString(){
        return DateTimeFormatter.ofPattern("YYYY-mm-dd").format(LocalDateTime.now());
    }



    public static void main(String[] args) {
//        System.out.println(getTodayBegin());
//        System.out.println(getYesterdayBegin());
        System.out.println(getDateString());

        System.out.println(LocalDate.now());
        System.out.println(new Date());

        System.out.println(java.sql.Date.valueOf(LocalDate.now()));
        System.out.println(java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));

        System.out.println(java.sql.Date.from(Instant.now()));
        System.out.println(new java.sql.Timestamp(Instant.now().toEpochMilli()));

        System.out.println(ZoneId.getAvailableZoneIds());

    }


}
