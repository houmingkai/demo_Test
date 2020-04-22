package com.Test;

import com.example.demo.util.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class YHZQTest {
    
    public static void main(String[] args){
//        int m = calcDate(5, "y");
////        System.out.println(m);
            List list = new ArrayList<>();

    }

    public static int calcDate(int term, String unit) {
        Calendar rightNow = Calendar.getInstance();

        if ("d".equals(unit)) {
            rightNow.add(Calendar.HOUR_OF_DAY, -term);
        }
        if ("m".equals(unit)) {
            rightNow.add(Calendar.MONDAY, -term);
        }

        if ("y".equals(unit)) {
            rightNow.add(Calendar.FEBRUARY, -term);
        }

        String now = DateUtils.formatDate(rightNow.getTime(), "yyyyMMdd");

        return Integer.valueOf(now);

    }
}
