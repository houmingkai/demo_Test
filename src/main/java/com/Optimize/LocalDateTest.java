package com.Optimize;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * LocalDate精确到日期，LocalDateTime精确到时分秒。
 */
public class LocalDateTest {

    public static void main(String[] args) {
//        old();
        optimize();
    }

    /**
     * waterStart:2019-02-18 03:00:00
     * waterEnd:2019-02-18 04:00:00
     * waterStartTime:Mon Feb 18 03:00:00 CST 2019
     * waterEndTime:Mon Feb 18 04:00:00 CST 2019
     */
    public static void old() {

        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfMins = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date now = new Date();
        String today = sdfDay.format(now);
        String waterStart = today + " 03:00:00";
        System.out.println("waterStart:" + waterStart);
        String waterEnd = today + " 04:00:00";
        System.out.println("waterEnd:" + waterEnd);

        try {
            Date waterStartTime = sdfMins.parse(waterStart);
            System.out.println("waterStartTime:" + waterStartTime);
            Date waterEndTime = sdfMins.parse(waterEnd);
            System.out.println("waterEndTime:" + waterEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * now:2019-02-18T13:55:28.299
     * localDate:2019-02-18
     * now.getYear():2019
     * now.getMonth():FEBRUARY
     * now.getDayOfMonth():18
     * waterStart:2019-02-18T03:00
     * waterEndTime:2019-02-18T04:00
     */
    public static void optimize() {
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println("now:"+now);
//        LocalDate localDate = LocalDate.now();
//        System.out.println("localDate:"+localDate);
//
//        System.out.println("now.getYear():"+now.getYear());
//        System.out.println("now.getMonth():"+now.getMonth());
//        System.out.println("now.getDayOfMonth():"+now.getDayOfMonth());
//        LocalDateTime waterStart = LocalDateTime.of(now.getYear(), now.getMonth(),now.getDayOfMonth(), 3, 0);
//        System.out.println("waterStart:"+waterStart);
//        LocalDateTime waterEndTime  = LocalDateTime.of(now.getYear(), now.getMonth(),now.getDayOfMonth(), 4, 0);
//        System.out.println("waterEndTime:"+waterEndTime);

        System.out.println(LocalDateTime.MAX);

    }
}
