package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @Company:浙江核新同花顺网络信息股份有限公司
 * @ClassName: DateUtil
 * @Description: 时间日期处理工具
 * @Author: machuan@myhexin.com
 * @CreateDate: 2017-07-12 上午9:24:26
 * @version:1.0
 */
public final class DateUtil {

    public static final Logger  logger              = LoggerFactory.getLogger(DateUtil.class);

    /**
     * new SimpleDateFormat("yyyy-MM-dd")
     */
    public static final String  YYYYMMDD_LINE       = "yyyy-MM-dd";

    /**
     * new SimpleDateFormat("yy-MM-dd")
     */
    public static final String  YYMMDD_LINE         = "yy-MM-dd";

    /**
     * new SimpleDateFormat("yyyy/MM/dd")
     */
    public static final String  YYYYMMDD_SLASH      = "yyyy/MM/dd";

    /**
     * new SimpleDateFormat("yyyyMMdd")
     */
    public static final String  YYYYMMDD            = "yyyyMMdd";

    /**
     * new SimpleDateFormat("yyyy-MM-dd HH:mm")
     */
    public static final String  YYYYMMDDHHmm_LINE   = "yyyy-MM-dd HH:mm";

    /**
     * new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     */
    public static final String  YYYYMMDDHHmmSS_LINE = "yyyy-MM-dd HH:mm:ss";

    /**
     * new SimpleDateFormat("yyyyMMddHHmm")
     */
    public static final String  YYYYMMDDHHmm        = "yyyyMMddHHmm";

    /**
     * new SimpleDateFormat("yyyyMMddHHmmss")
     */
    public static final String  YYYYMMDDHHmmSS      = "yyyyMMddHHmmss";

    /**
     * 时间格式化对象
     */
    private static final String TIME_SEC_FORMAT     = "HH:mm:ss";

    public static final String  HHmm                = "HH:mm";

    private DateUtil(){
        super();
    }

    /**
     * 获取当前时间的时间对象
     * 
     * @return 时间对象
     */
    public static final Date nowDate() {
        return new Date();
    }

    /**
     * 系统最小时间
     * 
     * @return 时间对象
     */
    public static final Date minDate() {
        return dateBegin(getDate(1900, 1, 1));
    }

    /**
     * 系统最大时间
     * 
     * @return 时间对象
     */
    public static final Date maxDate() {
        return dateEnd(getDate(2079, 1, 1));
    }

    /**
     * 获取指定时间的年
     * 
     * @param date 时间对象
     * @return 年数
     */
    public static final int year(Date date) {
        if (date == null) return 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取指定时间的月
     * 
     * @param date 时间对象
     * @return 月数
     */
    public static final int month(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定时间的日
     * 
     * @param date 时间对象
     * @return 日数
     */
    public static final int day(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取指定时间的日
     *
     * @param date 时间对象
     * @return 小时数
     */
    public static final int hour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 获取一个时间对象
     * 
     * @param year 格式为：2004
     * @param month 从1开始
     * @param date 从1开始
     * @return 时间对象
     */
    public static final Date getDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        return calendar.getTime();
    }

    /**
     * 获取一个时间对象
     * 
     * @param year 格式为：2004
     * @param month 从1开始
     * @param date 从1开始
     * @param hour 小时
     * @param minute 分钟
     * @param second 秒钟
     * @return 日期对象
     */
    public static final Date getDateTime(int year, int month, int date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date, hour, minute, second);
        return calendar.getTime();
    }

    /**
     * 在一个已知时间的基础上增加指定的时间,负数表示减少
     * 
     * @param oldDate 已知时间对象
     * @param year 年
     * @param month 月
     * @param date 日
     * @return 时间对象
     */
    public static final Date addDate(Date oldDate, int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, date);
        return calendar.getTime();
    }

    public static int constDateSub = -36500;

    /**
     * 返回两个时间相差的天数
     * 
     * @param a 时间对象a
     * @param b 时间对象b
     * @return 相差天数
     */
    public static final int dateSub(Date a, Date b) {
        if (a == null || b == null) {
            return constDateSub;
        }

        int date = (int) (a.getTime() / (24 * 60 * 60 * 1000) - b.getTime() / (24 * 60 * 60 * 1000));
        return date;
    }

    /**
     * 判断两个日期是否相等
     * 
     * @param a 时间对象a
     * @param b 时间对象b
     * @return 是都相同，0：不同；1：相同
     */
    public static final int dateSubAddOne(Date a, Date b) {
        int date = (int) (a.getTime() / (24 * 60 * 60 * 1000) - b.getTime() / (24 * 60 * 60 * 1000));
        return date <= 0 ? 1 : date + 1;
    }

    /**
     * 判断某一时间是否介于两个时间之内
     * 
     * @param beginDate 开始时间
     * @param nowDate 需检测的时间
     * @param endDate 结束时间
     * @return
     */
    public static final boolean isBetweenDateS(Date beginDate, Date nowDate, Date endDate) {
        if (beginDate != null && nowDate != null && endDate != null) {
            if ((beginDate.getTime() / (24 * 60 * 60 * 1000)) <= (nowDate.getTime() / (24 * 60 * 60 * 1000))
                && (nowDate.getTime() / (24 * 60 * 60 * 1000)) <= (endDate.getTime() / (24 * 60 * 60 * 1000))) {
                return true;
            }
        } else if (beginDate != null && nowDate != null && endDate == null) {
            if ((beginDate.getTime() / (24 * 60 * 60 * 1000)) <= (nowDate.getTime() / (24 * 60 * 60 * 1000))) {
                return true;
            }
        } else if (beginDate == null && nowDate != null && endDate != null) {
            if ((nowDate.getTime() / (24 * 60 * 60 * 1000)) <= (endDate.getTime() / (24 * 60 * 60 * 1000))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回两个时间相差多少分钟
     * 
     * @param a 时间对象a
     * @param b 时间对象b
     * @return 相差分钟数
     */
    public static final int subSecond(Date a, Date b) {
        return (int) (a.getTime() / (1000) - b.getTime() / (1000));
    }

    /**
     * 判断两个时间相差秒数
     * 
     * @param str 时间字符串
     * @param b 时间对象
     * @return 相差秒数
     */
    public static final int subSecond(String str, Date b) {
        Date a = null;
        try {
            a = new SimpleDateFormat(HHmm).parse(str);
        } catch (ParseException e) {

            return 0;
        }
        return (int) ((a.getTime() % (24 * 60 * 60 * 1000)) / 1000 - (b.getTime() % (24 * 60 * 60 * 1000)) / 1000);
    }

    /**
     * 一天的开始时间
     * 
     * @param date 时间对象
     * @return 开始时间对象
     */
    public static final Date dateBegin(Date date) {
        if (date == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateBegin(calendar);
        return calendar.getTime();
    }

    /**
     * 一天的结束时间
     * 
     * @param date 时间对象
     * @return 结束时间对象
     */
    public static final Date dateEnd(Date date) {
        if (date == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateEnd(calendar);
        return calendar.getTime();
    }

    /**
     * 一天的结束时间
     * 
     * @param calendar 日历对象
     * @return 结束时间日历对象
     */
    public static final Calendar dateEnd(Calendar calendar) {
        if (calendar == null) return null;
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 一天的开始时间
     * 
     * @param calendar 日历对象
     * @return 开始时间日历对象
     */
    public static final Calendar dateBegin(Calendar calendar) {
        if (calendar == null) return null;
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 一月的开始时间
     * 
     * @param date 时间对象
     * @return 开始时间对象
     */
    public static final Date monthBegin(Date date) {
        if (date == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateBegin(calendar);
        return calendar.getTime();
    }

    /**
     * 一月的结束时间
     * 
     * @param date 时间对象
     * @return 结束时间对象
     */
    public static final Date monthEnd(Date date) {
        if (date == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
    }

    /**
     * 一年的开始时间
     * 
     * @param date 时间对象
     * @return 开始时间对象
     */
    public static final Date yearBegin(Date date) {
        if (date == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.getActualMinimum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, month);
        dateBegin(calendar);
        return calendar.getTime();
    }

    /**
     * 一年的结束时间
     * 
     * @param date 时间对象
     * @return 结束时间对象
     */
    public static final Date yearEnd(Date date) {
        if (date == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
    }

    /**
     * 从字符串转换为date 默认格式为 "yyyy-MM-dd"
     * 
     * @param source 需转换的字符串
     * @return 时间对象
     */
    public static final Date parseDate(String source) {
        if (source == null || source.trim().length() == 0) return null;
        // 判断日期格式
        if (source.trim().length() == 8) try {
            Date returnDate = new SimpleDateFormat(YYYYMMDD).parse(source);
            return returnDate;
        } catch (ParseException e) {
            logger.error("DateUtil parseDate error", e);
            return null;
        }
        try {
            Date returnDate = new SimpleDateFormat(YYYYMMDD_LINE).parse(source);
            return returnDate;
        } catch (ParseException e) {
            logger.error("DateUtil parseDate error", e);
            return null;
        }
    }

    /**
     * 从字符串转换为date 默认格式为 "yyyy-MM-dd HH:mm"
     * 
     * @param source 需转换的字符串
     * @return 日期对象
     */
    public static final Date parseDateTime(String source) {
        if (source == null || source.length() == 0) return null;
        try {
            return new SimpleDateFormat(YYYYMMDDHHmm_LINE).parse(source);
        } catch (ParseException e) {
            logger.error("DateUtil parseDate error", e);
            return null;
        }
    }

    /**
     * 从字符串转换为date
     * 
     * @param source 需转换的字符串
     * @param formatStr 格式类型
     * @return 日期对象
     */
    public static final Date parseDateTime(String source, String formatStr) {
        if (source == null || source.length() == 0) return null;
        try {
            return new SimpleDateFormat(formatStr).parse(source);
        } catch (ParseException e) {
            logger.error("DateUtil parseDate error", e);
            return null;
        }
    }

    /**
     * 从字符串转换为date 默认格式为 "yyyy-MM-dd HH:mm:ss"
     * 
     * @param source 需转换的字符串
     * @return 日期对象
     */
    public static final Date parseDateTimes(String source) {
        if (source == null || source.equals("") || source.length() == 0) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(source);
        } catch (ParseException e) {
            logger.error("DateUtil parseDate error", e);
        }
        return null;
    }

    /**
     * 格式化输出（只读的时候） 默认格式为 "yyyy-MM-dd"
     * 
     * @param date 时间对象
     * @return 格式化后的时间字符串
     */
    public static String formatDate(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(YYYYMMDD_LINE).format(date);
    }

    /**
     * 格式化输出（只读的时候） 默认格式为 "yy-MM-dd"
     * 
     * @param date 时间独享
     * @return 格式化后的时间字符串
     */
    public static String formatDateYY(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(YYMMDD_LINE).format(date);
    }

    /**
     * 格式化输出显示（填写的时候） yyyyMMdd
     * 
     * @param date 时间的对象
     * @return 格式化后的时间字符串
     */
    public static String formatDate_input(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(YYYYMMDD).format(date);
    }

    /**
     * 格式化输出显示（填写的时候） yyyy/MM/dd
     * 
     * @param date 时间对象
     * @return 格式化后的时间字符串
     */
    public static String formatDate_slide(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(YYYYMMDD_SLASH).format(date);
    }

    /**
     * 格式化输出 默认格式为 "yyyy-MM-dd HH:mm"
     * 
     * @param date 时间对象
     * @return 格式化后的时间字符串
     */
    public static String formatDateTime(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(YYYYMMDDHHmm_LINE).format(date);
    }

    /**
     * 格式化输出 默认格式为 "yyyy-MM-dd HH:mm:ss"
     * 
     * @param date 时间对象
     * @return 格式化后的时间字符串
     */
    public static String formatDateTimeS(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(YYYYMMDDHHmmSS_LINE).format(date);
    }

    /**
     * 格式化输出 默认格式为 "yyyy-MM-dd HH:mm"
     * 
     * @param date 时间对象
     * @return 格式化后的时间字符串
     */
    public static String getDateTime(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(YYYYMMDDHHmm_LINE).format(date).substring(5, 10).replaceAll("_", "/");
    }

    /**
     * 格式化输出 默认格式为 "yyyyMMddHHmm"
     * 
     * @param date 时间对象
     * @return 格式化后的时间字符串
     */
    public static String formatDateTime_input(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(YYYYMMDDHHmm).format(date);
    }

    /**
     * 格式化输出 默认格式为 "yyyyMMddHHmmss"
     * 
     * @param date 时间对象
     * @return 格式化后的时间字符串
     */
    public static String formatDateTimeS_input(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(YYYYMMDDHHmmSS).format(date);
    }

    /**
     * 格式化输出显示（填写的时候） HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String formatDateNoday(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(TIME_SEC_FORMAT).format(date);
    }

    /**
     * 时间戳转换成整天时间
     * 
     * @param timestamp 时间戳
     * @return 整天时间
     * @throws ParseException
     */
    public static Date parse2Day(Long timestamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_LINE);
        return sdf.parse(sdf.format(new Date(timestamp)));
    }

    /**
     * 判断是否是闰年
     * 
     * @param yearInt 年份
     * @return 是否为闰年
     */
    public static boolean isLeapYear(int yearInt) {
        boolean flag = false;
        if (((yearInt % 4 == 0) && (yearInt % 100 != 0)) || ((yearInt % 4 == 0) && (yearInt % 400 == 0))) {
            return true;
        }
        return flag;
    }

    /**
     * 在指定的时间内增加天数。负数表示为减
     * 
     * @param date 时间对象
     * @param days 增加的天数
     * @return 增加后的时间对象
     */
    public static Date addDays(Date date, int days) {
        Date newdate = new Date();
        long newtimems = date.getTime() + ((long) days * 24 * 60 * 60 * 1000);
        newdate.setTime(newtimems);
        return newdate;
    }

    /**
     * 根据日期判断今天 昨天 前天3个时间段，如果不是返回String类型
     * 
     * @param date 时间对象
     * @return 返回判断结果
     */
    public static String cnDate(Date date) {
        if (date == null) {
            return "";
        }
        Date newdate = new Date();
        Long newTimes = newdate.getTime();
        Long cdTimes = date.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String dateStr1 = DateUtil.formatDate(DateUtil.addDays(newdate, -1)) + " 23:59:59";
        String dateStr2 = DateUtil.formatDate(DateUtil.addDays(newdate, -2)) + " 23:59:59";
        String dateStr3 = DateUtil.formatDate(DateUtil.addDays(newdate, -3)) + " 23:59:59";
        Date date1 = DateUtil.parseDateTimes(dateStr1);
        Date date2 = DateUtil.parseDateTimes(dateStr2);
        Date date3 = DateUtil.parseDateTimes(dateStr3);
        if (newTimes >= cdTimes && cdTimes > date1.getTime()) {
            return "今天 " + sdf.format(date);
        } else if (cdTimes < date1.getTime() && cdTimes > date2.getTime()) {
            return "昨天 " + sdf.format(date);
        } else if (cdTimes < date2.getTime() && cdTimes > date3.getTime()) {
            return "前天 " + sdf.format(date);
        } else

            return DateUtil.formatDateTime(date);
    }

    /**
     * 判断8位的日期的字符串是否为正确的日期字符串
     * 
     * @param dateString 时间字符串
     * @return 不是正确的日期字符串返回true
     */
    public static boolean isErrorFormatDateString(String dateString) {
        boolean flag = false;
        String yearString = "";
        String monthString = "";
        String dayString = "";
        if (dateString.length() == 8) {
            yearString = dateString.substring(0, 4);
            monthString = dateString.substring(4, 6);
            dayString = dateString.substring(6, 8);
        } else {
            return true;
        }
        int yearInt = Integer.parseInt(yearString);
        int monthInt = Integer.parseInt(monthString);
        int dayInt = Integer.parseInt(dayString);
        if (DateUtil.year(DateUtil.nowDate()) != yearInt) {
            return true;
        }
        if (monthInt > 0 && monthInt < 12) {
            switch (monthInt) {
                case 1:
                    if (dayInt > 31 || dayInt < 1) flag = true;
                    break;
                case 2:
                    if (isLeapYear(yearInt)) {
                        if (dayInt > 29 || dayInt < 1) flag = true;
                    } else {
                        if (dayInt > 28 || dayInt < 1) flag = true;
                    }
                    break;
                case 3:
                    if (dayInt > 31 || dayInt < 1) flag = true;
                    break;
                case 4:
                    if (dayInt > 30 || dayInt < 1) flag = true;
                    break;
                case 5:
                    if (dayInt > 31 || dayInt < 1) flag = true;
                    break;
                case 6:
                    if (dayInt > 30 || dayInt < 1) flag = true;
                    break;
                case 7:
                    if (dayInt > 31 || dayInt < 1) flag = true;
                    break;
                case 8:
                    if (dayInt > 31 || dayInt < 1) flag = true;
                    break;
                case 9:
                    if (dayInt > 30 || dayInt < 1) flag = true;
                    break;
                case 10:
                    if (dayInt > 31 || dayInt < 1) flag = true;
                    break;
                case 11:
                    if (dayInt > 30 || dayInt < 1) flag = true;
                    break;
                case 12:
                    if (dayInt > 31 || dayInt < 1) flag = true;
                    break;

                default:
                    break;
            }
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 返回秒数对应的分秒或者时分
     * 
     * @param type 转换类型：1/分秒 2/时分 例如：1/20分20秒 ，2/1小时/25分钟
     * @param s 秒
     * @return times
     */
    public static String getStrTime(int s, String type) throws Exception {
        String times = "";
        int sec = 1;// second
        int min = 60 * sec;// minute
        int hh = 60 * min;// hour

        // 时、分、秒
        int hour = 0;
        int minute = 0;
        int second = 0;

        // 两位数
        String strH = "";
        String strM = "";
        String strS = "";

        if (type == null) {
            throw new Exception("输入的转换类型为空");
        } else if (type.equals("1")) {
            minute = s / min;
            second = s - (minute * min);
            strM = minute < 10 ? "0" + minute : "" + minute;
            strS = second < 10 ? "0" + second : "" + second;
            times = strM + "分" + strS + "秒";
        } else if (type.equals("2")) {
            hour = s / hh;
            minute = (s - hour * hh) / min;
            strH = hour < 10 ? "0" + hour : "" + hour;
            strM = minute < 10 ? "0" + minute : "" + minute;
            times = strH + "时" + strM + "分";
        }
        return times;
    }

    /**
     * 在一个已知时间的基础上增加指定的时间,负数表示减少
     * 
     * @param oldDate 已知时间
     * @param year 年
     * @param month 月
     * @param date 日
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return 增加后的时间对象
     */
    public static final Date addDate(Date oldDate, int year, int month, int date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 返回“yyyy-MM”格式的String日期
     * 
     * @param d “yyyy-MM” 时间对象
     * @return 格式化后的时间字符串
     */
    public static String toDateStr(Date d) {
        if (d == null) {
            return "";
        } else {
            return (new SimpleDateFormat("yyyy-MM")).format(d);
        }
    }

    /**
     * 按照指定格式将时间转化为str
     * 
     * @param date 时间对象
     * @param format 转换的格式
     * @return 格式化后的时间字符串
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 判断是否在同一天
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static Boolean isSameDate(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);

        if ((year1 == year2) && (day1 == day2)) {
            return true;
        }
        return false;
    }

    /**
     * sql语句中起始日期处理
     * 
     * @param date 时间字符串
     * @return sql字符串
     */
    public static String sqlDateS(String date) {
        return "str_to_date('" + date + " 00:00:00','%Y-%m-%d %h:%i:%s')";
    }

    /**
     * sql语句中结束日期处理
     * 
     * @param date 时间字符串
     * @return sql字符串
     */
    public static String sqlDateE(String date) {
        return "str_to_date('" + date + " 23:59:59','%Y-%m-%d %h:%i:%s')";
    }

    /**
     * 解析java.util.Date to java.sql.Date(used for ps.setDate(x,xxx))
     * 
     * @param dateStr 要转换的java.util.date
     * @return java.sql.Date
     */
//    public static java.sql.Date parse2SqlDate(String dateStr) {
//        return StringUtil.isBlank(dateStr) ? null : new java.sql.Date(parseDate(dateStr).getTime());
//    }

    /**
     * 身份证有效日期效验
     * 
     * @param beginStr 开始时间
     * @param endStr 结束时间
     * @param birthday 生日
     * @return String 返回1时为效验通过
     * @throws ParseException
     */
    public static String validateDate(String beginStr, String endStr, String birthday) {
        try {
            // 验证日期是否有效
            String regx = "^(?:19|20|30)[0-9][0-9](?:(?:0[1-9])|(?:1[0-2]))(?:(?:[0-2][1-9])|(?:[1-3][0-1]))$";
            if (!Pattern.matches(regx, endStr)) {
                return "证件有效截止日期无效";
            }
            if (!Pattern.matches(regx, beginStr)) {
                return "证件有效起始日期无效";
            }
            if (!Pattern.matches(regx, birthday)) {
                return "出生日期无效";
            }

            Long begin = new SimpleDateFormat(YYYYMMDD).parse(beginStr).getTime();
            Long end = new SimpleDateFormat(YYYYMMDD).parse(endStr).getTime();
            Long birth = new SimpleDateFormat(YYYYMMDD).parse(birthday).getTime();

            Long now = System.currentTimeMillis();
            if (begin < birth) {
                return "开始日期早于出生日期";
            }
            if (begin > now) {
                return "开始日期晚于今天";
            }
            if (end < now) {
                return "您的身份证已到期";
            }
        } catch (ParseException e) {
            logger.warn("日期格式转换错误：begin:" + beginStr + ",end:" + endStr + ",birthday:" + birthday);
            return "日期格式不符合规则。";

        }
        return "1";
    }

}
