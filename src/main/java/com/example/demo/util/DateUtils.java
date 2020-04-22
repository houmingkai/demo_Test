package com.example.demo.util;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDD_LINE = "yyyy-MM-dd";
    public static final String YYYYMMDDHHMMSS_LINE = "yyyy-MM-dd HH:mm:ss";

    private static final String DEFAULT_FORMAT = YYYYMMDD;

    private static final ThreadLocal<Map<String, DateFormat>> tl = new ThreadLocal<>();


    public static DateFormat getDateFormat() {
        return getDateFormat(DEFAULT_FORMAT);
    }

    public static DateFormat getDateFormat(String format) {
        Map<String, DateFormat> map = tl.get();

        if (map == null) {
            map = new HashMap<>();
            tl.set(map);
        }

        if (StringUtils.isBlank(format)) {
            format = DEFAULT_FORMAT;
        }

        DateFormat ret = map.get(format);

        if (ret == null) {
            ret = new SimpleDateFormat(format);
            map.put(format, ret);
        }

        return ret;
    }


    /**
     * 将int类型的数字转化为yyyy-MM-dd格式：20180101->“2018-01-01”
     *
     * @param dateInt
     * @return
     */
    public static String formatDate(Integer dateInt) {
        if (dateInt == null) return "";
        return formatDate(dateInt + "");
    }

    /**
     * 将String类型的数字转化为yyyy-MM-dd格式："20180101"->“2018-01-01”
     *
     * @param dateStr
     * @return
     */
    public static String formatDate(String dateStr) {
        if (StringUtils.isBlank(dateStr) || "0".equals(dateStr)) {
            return "";
        }

        if (dateStr.length() != 8) {
            throw new ClassFormatError("格式错误异常：" + dateStr);
        }

        Date date = parseDate(dateStr);
        return getDateFormat().format(date);
    }

    public static String formatDate(Date date) {
        if (date == null) return "";
        return getDateFormat().format(date);
    }

    public static String formatDate(Date date, String format) {
        if (date == null) return "";
        return getDateFormat(format).format(date);
    }


    public static final Date parseDate(String source) {
        return parseDate(source, DEFAULT_FORMAT);
    }

    public static final Date parseDate(String source, String format) {
        if (source == null || source.trim().length() == 0) return null;
        Date returnDate = null;
        try {
            returnDate = getDateFormat(format).parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnDate;
    }


    public static final int dateSub(Date end, Date begin) {
        if (end == null || begin == null) {
            return 0;
        }

        int date = (int) ((end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000));
        return date;
    }

    /**
     * 计算时间忽略时分秒
     *
     * @param end
     * @param begin
     * @return
     */
    public static final int dateSubIgnoreHms(Date end, Date begin) {
        if (end == null || begin == null) {
            return 0;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(end);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(begin);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        int date = (int) ((cal1.getTime().getTime() - cal2.getTime().getTime()) / (24 * 60 * 60 * 1000));
        return date;
    }
    /**
     * 解析时间为字符串
     *
     * @param date
     *            时间
     * @return 字符串
     */
    public static String formatDate2Str(Date date) {
        return formatDate2Str(date, YYYYMMDDHHMMSS_LINE);
    }

    /**
     * 解析时间为字符串
     *
     * @param date
     *            时间
     * @param pattern
     *            字符串格式
     * @return 字符串
     */
    public static String formatDate2Str(Date date, String pattern) {
        if (null == date) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.format(date);
    }
    /**
     * 解析字符串为时间
     *
     * @param source
     *            字符串
     * @param pattern
     *            字符串格式
     * @return 时间
     * @throws ParseException
     */
    public static Date parse(String source, String pattern)
            throws ParseException {
        if (source == null || "".equals(source)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(source);
        return new Timestamp(date.getTime());
    }


    public static void main(String[] args) throws ParseException {
        String time = "2018-12-14 09:06:29.518935";
        Date parse = parse(time, "yyyy-MM-dd HH:mm:ss");

        System.out.println(formatDate2Str(parse));

    }

}
