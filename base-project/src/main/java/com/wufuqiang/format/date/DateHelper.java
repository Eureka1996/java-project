package com.wufuqiang.format.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    }


}
