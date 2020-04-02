package com.ex.lib.common;

import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author patrick
 */
public class TimeUtils {


    public static final String FORMATTER_YEAR = "yyyy";
    public static final String FORMATTER_DATE = "yyyy-MM-dd";
    public static final String FORMATTER_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final Locale DEFAULT_LOCALE = Locale.US;

    public static final int FIELD_YEAR = 1;
    public static final int FIELD_MONTH = 2;
    public static final int FIELD_WEEK = 3;
    public static final int FIELD_DAY = 4;

    /**
     * 获取当前系统时间对应的的日期时间字符串
     * 默认格式为yyyy-MM-dd HH:mm:ss， 本地化信息为Locale.US
     */
    public static String getString(){
        return getString(new Date(System.currentTimeMillis()));
    }

    /**
     * 通过指定的格式获取当前时间的日期时间字符串
     */
    private static String getString(String formatter){
        return getString(new Date(System.currentTimeMillis()), formatter, DEFAULT_LOCALE);
    }

    /**
     * 获取指定Unix时间戳对应的的日期时间字符串
     * 默认格式为yyyy-MM-dd HH:mm:ss， 本地化信息为Locale.US
     */
    public static String getString(long milliseconds){
        return getString(new Date(milliseconds));
    }

    /**
     * 获取指定date的日期时间字符串
     * 默认格式为yyyy-MM-dd HH:mm:ss， 本地化信息为Locale.US
     */
    public static String getString(Date date){
        return getString(date, FORMATTER_DATE_TIME, DEFAULT_LOCALE);
    }

    /**
     * 通过指定的格式获取指定date的日期时间字符串
     */
    private static String getString(Date date, String formatter){
        return getString(date, formatter, DEFAULT_LOCALE);
    }

    /**
     * 通过指定的格式和本地化信息获取指定date的日期时间字符串
     */
    private static String getString(Date date, String formatter, Locale locale){
        return new SimpleDateFormat(formatter, locale).format(date);
    }

    /**
     * 判断某个unix时间戳对应的日期是否在当前日期之前
     */
    public static boolean before(long milliseconds){
        return before(new Date(milliseconds));
    }

    /**
     * 判断某个日期是否在当前日期之前
     */
    public static boolean before(Date date){
        return date.before(new Date());
    }

    /**
     * 获取当前日期之后指定年数的日期
     */
    public static Date getDateAfterSomeYears(int year){
        return getDateAfterSomeYears(new Date(), year);
    }

    /**
     * 获取指定日期之后指定年数的日期
     */
    public static Date getDateAfterSomeYears(Date date, int year){
        return getDateAfter(date, FIELD_YEAR, year);
    }

    /**
     * 获取当前日期之后指定月数的日期
     */
    public static Date getDateAfterSomeMonths(int month){
        return getDateAfterSomeMonths(new Date(), month);
    }

    /**
     * 获取指定日期之后指定月数的日期
     */
    public static Date getDateAfterSomeMonths(Date date, int month){
        return getDateAfter(date, FIELD_MONTH, month);
    }

    /**
     * 获取当前日期之后指定周数的日期
     */
    public static Date getDateAfterSomeWeeks(int week){
        return getDateAfterSomeWeeks(new Date(), week);
    }

    /**
     * 获取指定日期之后指定周数的日期
     */
    public static Date getDateAfterSomeWeeks(Date date, int week){
        return getDateAfter(date, FIELD_WEEK, week);
    }

    /**
     * 获取当前日期之后指定天数的日期
     */
    public static Date getDateAfterSomeDays(int day){
        return getDateAfterSomeDays(new Date(), day);
    }


    /**
     * 获取指定日期之后指定天数的日期
     */
    public static Date getDateAfterSomeDays(Date date, int day){
        return getDateAfter(date, FIELD_DAY, day);
    }

    /**
     * 获取某个日期之后指定field和value的新日期
     * @param date   指定的日期
     * @param field  之后日期的计算单位，YEAR, MONTH, WEEK, DAY
     * @param value  之后日期的计算值
     * @return
     */
    private static Date getDateAfter(Date date, int field, int value){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(field == FIELD_YEAR){
            calendar.add(Calendar.YEAR, value);
        }else if(field == FIELD_MONTH) {
            calendar.add(Calendar.MONTH, value);
        }else if(field == FIELD_WEEK) {
            calendar.add(Calendar.WEEK_OF_YEAR, value);
        }else if(field == FIELD_DAY) {
            calendar.add(Calendar.DAY_OF_YEAR, value);
        }
        return calendar.getTime();
    }

    /**
     * 获取当前系统时间的unix秒级时间戳
     */
    public static int getUnixSeconds(){
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取时间字符串对应的unix秒级时间戳
     */
    public static int getUnixSeconds(String dateStr){
        return (int) (getUnix(dateStr) / 1000);
    }

    /**
     * 获取指定date对应的unix秒级时间戳
     */
    public static int getUnixSeconds(Date date){
        return (int) (getUnix(date) / 1000);
    }

    /**
     * 获取指定时间字符串对应的unix毫秒级时间戳
     * @param dateStr 字符串时间 yyyy-MM-dd or yyyy-MM-dd HH:mm:ss
     */
    public static long getUnix(String dateStr){
        try {
            if(StringUtils.isEmpty(dateStr)) {
                return 0L;
            }
            dateStr = dateStr.trim();
            if(RegularUtils.matchDate(dateStr.trim())){
                return getUnix(new SimpleDateFormat(FORMATTER_DATE).parse(dateStr));
            }else if(RegularUtils.matchDateTime(dateStr.trim())){
                return getUnix(new SimpleDateFormat(FORMATTER_DATE_TIME).parse(dateStr));
            }else{
                return 0L;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取指定date对应的unix毫秒级时间戳
     */
    public static long getUnix(Date date){
        return date.getTime();
    }

    /**
     * 获取今天0点的unix秒级时间戳
     */
    public static int getUnixSecondBeginOf(){
        return getUnixSecondBeginOf(new Date());
    }

    /**
     * 获取指定日期当天0点的unix秒级时间戳
     */
    public static int getUnixSecondBeginOf(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return (int) (calendar.getTime().getTime() / 1000);
    }

    /**
     * 获取今天24点的unix秒级时间戳
     */
    public static int getUnixSecondEndOf(){
        return getUnixSecondEndOf(new Date());
    }

    /**
     * 获取指定日期当天24点的unix秒级时间戳
     */
    public static int getUnixSecondEndOf(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return (int) (calendar.getTime().getTime() / 1000) + 1;
    }

    /**
     * 字符串格式日期时间转成date格式
     */
    public static Date getDate(String dateString){
        Date date = null;
        try {
            if(RegularUtils.matchDate(dateString)) {
                date = new SimpleDateFormat(FORMATTER_DATE, DEFAULT_LOCALE).parse(dateString);
            }else if(RegularUtils.matchDateTime(dateString)){
                date = new SimpleDateFormat(FORMATTER_DATE_TIME, DEFAULT_LOCALE).parse(dateString);
            }
            return date;
        } catch (ParseException e) {
            return date;
        }
    }


    /**
     * 计算2个日期之间相差的天数
     */
    public static int getDifferentDays(Date d1, Date d2){
        long diff = d1.getTime() - d2.getTime();
        return (int) (diff / (24 * 3600 * 1000));
    }

    /**
     * 计算2个日期之间相差的秒数
     */
    public static int getDifferentSeconds(Date d1, Date d2){
        long diff = d1.getTime() - d2.getTime();
        return (int) (diff / 1000);
    }

    /**
     * 将秒数时间格式化成视频媒体格式时间(hh:mm:ss)
     */
    public static String getMediaTime(int seconds){
        int day, hour, minute, second;
        day = seconds / (3600 * 24);
        hour = (seconds - day * 3600 * 24) / 3600;
        minute = (seconds - day * 3600 * 24 - hour * 3600) / 60;
        second = (seconds - day * 3600 * 24 - hour * 3600 - minute * 60);
        String sDay, sHour, sMinute, sSecond;
        sDay = String.valueOf(day);
        if (hour < 10) {
            sHour = "0" + hour;
        } else {
            sHour = String.valueOf(hour);
        }
        if (minute < 10) {
            sMinute = "0" + minute;
        } else {
            sMinute = String.valueOf(minute);
        }
        if (second < 10) {
            sSecond = "0" + second;
        } else {
            sSecond = String.valueOf(second);
        }
        if(day > 0){
            return sDay + " " + sHour + ":" + sMinute + ":" + sSecond;
        }else{
            return sHour + ":" + sMinute + ":" + sSecond;
        }
    }


    /**
     * 判断2个日期是否是同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            return false;
        }
    }

    private static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                    cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                    cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        } else {
            return false;
        }
    }

}
