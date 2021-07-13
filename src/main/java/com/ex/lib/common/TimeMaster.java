package com.ex.lib.common;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Locale;

/**
 * @author patrick
 */
public class TimeMaster {


    public static final String PATTERN_YEAR = "yyyy";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final Locale DEFAULT_LOCALE = Locale.US;

    public static final int FIELD_YEAR = 1;
    public static final int FIELD_MONTH = 2;
    public static final int FIELD_WEEK = 3;
    public static final int FIELD_DAY = 4;

    /**
     * 获取当前系统时间对应的的日期时间字符串
     * 默认格式为yyyy-MM-dd HH:mm:ss
     */
    public static String getString(){
        return getString(LocalDateTime.now(), DateTimeFormatter.ofPattern(PATTERN_DATE_TIME));
    }
    /**
     * 获取指定10位Unix时间戳对应的的日期时间字符串
     * 默认格式为yyyy-MM-dd HH:mm:ss
     */
    public static String getString(int unixSeconds){
        return getString(LocalDateTime.ofEpochSecond(unixSeconds, 0, ZoneOffset.UTC),
                DateTimeFormatter.ofPattern(PATTERN_DATE_TIME));
    }

    /**
     * 通过指定的格式获取当前时间的日期时间字符串
     */
    public static String getString(DateTimeFormatter dateTimeFormatter){
        return getString(LocalDateTime.now(), dateTimeFormatter);
    }


    /**
     * 获取指定dateTime的日期时间字符串
     * 默认格式为yyyy-MM-dd HH:mm:ss
     */
    public static String getString(LocalDateTime dateTime){
        return getString(dateTime, DateTimeFormatter.ofPattern(PATTERN_DATE_TIME));
    }

    /**
     * 通过指定的格式和本地化信息获取指定dateTime的日期时间字符串
     */
    public static String getString(LocalDateTime dateTime, DateTimeFormatter dateTimeFormatter){
        return dateTime.format(dateTimeFormatter);
    }

    /**
     * 判断某个10位unix时间戳对应的日期是否在当前日期之前
     */
    public static boolean isBefore(long seconds){
        return isBefore(LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC));
    }

    /**
     * 判断某个日期是否在当前日期之前
     */
    public static boolean isBefore(LocalDateTime dateTime){
        return dateTime.isBefore(LocalDateTime.now());
    }

    /**
     * 获取当前日期之后指定年数的日期
     */
    public static LocalDateTime getDateAfterSomeYears(int years){
        return getDateAfterSomeYears(LocalDateTime.now(), years);
    }

    /**
     * 获取指定日期之后指定年数的日期
     */
    public static LocalDateTime getDateAfterSomeYears(LocalDateTime dateTime, int years){
        return getDateAfter(dateTime, years, ChronoUnit.YEARS);
    }

    /**
     * 获取当前日期之后指定月数的日期
     */
    public static LocalDateTime getDateAfterSomeMonths(int months){
        return getDateAfterSomeMonths(LocalDateTime.now(), months);
    }

    /**
     * 获取指定日期之后指定月数的日期
     */
    public static LocalDateTime getDateAfterSomeMonths(LocalDateTime dateTime, int months){
        return getDateAfter(dateTime, months, ChronoUnit.MONTHS);
    }

    /**
     * 获取当前日期之后指定周数的日期
     */
    public static LocalDateTime getDateAfterSomeWeeks(int weeks){
        return getDateAfterSomeWeeks(LocalDateTime.now(), weeks);
    }

    /**
     * 获取指定日期之后指定周数的日期
     */
    public static LocalDateTime getDateAfterSomeWeeks(LocalDateTime dateTime, int weeks){
        return getDateAfter(dateTime, weeks, ChronoUnit.WEEKS);
    }

    /**
     * 获取当前日期之后指定天数的日期
     */
    public static LocalDateTime getDateAfterSomeDays(int days){
        return getDateAfterSomeDays(LocalDateTime.now(), days);
    }


    /**
     * 获取指定日期之后指定天数的日期
     */
    public static LocalDateTime getDateAfterSomeDays(LocalDateTime dateTime, int days){
        return getDateAfter(dateTime, days, ChronoUnit.DAYS);
    }

    /**
     * 获取某个日期之后指定value和unit的新日期
     * @param dateTime   指定的日期
     * @param value     之后日期的计算值
     * @param unit      之后日期的计算单位
     * @return
     */
    private static LocalDateTime getDateAfter(LocalDateTime dateTime, int value, TemporalUnit unit){
        return dateTime.plus(value, unit);
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
    public static int getUnixSeconds(String dateString){
        return (int) (getUnixMilliSeconds(dateString) / 1000);
    }

    /**
     * 获取指定date对应的unix秒级时间戳
     */
    public static int getUnixSeconds(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return getUnixSeconds(localDateTime);
    }

    /**
     * 获取指定localDateTime对应的unix秒级时间戳
     */
    public static int getUnixSeconds(LocalDateTime dateTime){
        if(dateTime == null) {
            return 0;
        }
        return (int) (getUnixMilliSeconds(dateTime) / 1000);
    }

    /**
     * 获取指定时间字符串对应的unix毫秒级时间戳
     * @param dateString 字符串时间 yyyy-MM-dd or yyyy-MM-dd HH:mm:ss
     */
    public static long getUnixMilliSeconds(String dateString){
        try {
            if(StringUtils.isEmpty(dateString)) {
                return 0L;
            }
            dateString = dateString.trim();
            return getUnixMilliSeconds(getDateTime(dateString));
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取指定date对应的unix毫秒级时间戳
     */
    public static long getUnixMilliSeconds(LocalDateTime dateTime){
        if(dateTime == null) {
            return 0L;
        }
        return dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    /**
     * 获取今天0点的unix秒级时间戳
     */
    public static int getUnixSecondsStartOfDay(){
        return getUnixSecondsStartOfDay(LocalDateTime.now());
    }

    /**
     * 获取指定日期当天0点的unix秒级时间戳
     */
    public static int getUnixSecondsStartOfDay(LocalDateTime dateTime){
        if(dateTime == null) {
            return 0;
        }
        return getUnixSeconds(dateTime.toLocalDate().atStartOfDay());
    }

    /**
     * 获取今天24点的unix秒级时间戳
     */
    public static int getUnixSecondsEndOfDay(){
        return getUnixSecondsEndOfDay(LocalDateTime.now());
    }

    /**
     * 获取指定日期当天24点的unix秒级时间戳
     */
    public static int getUnixSecondsEndOfDay(LocalDateTime dateTime){
        if(dateTime == null) {
            return 0;
        }
        return getUnixSeconds(dateTime.toLocalDate().atTime(23, 59, 59)) + 1;
    }

    /**
     * 字符串格式日期时间转成LocalDateTime格式
     * @param dateString 字符串时间 yyyy-MM-dd or yyyy-MM-dd HH:mm:ss
     */
    public static LocalDateTime getDateTime(String dateString){
        LocalDateTime dateTime = null;
        try {
            if(RegularMaster.matchDate(dateString)) {
                dateTime = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(PATTERN_DATE)).atStartOfDay();
            }else if(RegularMaster.matchDateTime(dateString)){
                dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(PATTERN_DATE_TIME));
            }
            return dateTime;
        } catch (Exception e) {
            return dateTime;
        }
    }


    /**
     * 计算2个日期之间相差的天数
     */
    public static int getDifferentDays(LocalDateTime d1, LocalDateTime d2){
        long diff = getUnixMilliSeconds(d1) - getUnixMilliSeconds(d2);
        return (int) (diff / (24 * 3600 * 1000));
    }

    /**
     * 计算2个日期之间相差的秒数
     */
    public static int getDifferentSeconds(LocalDateTime d1, LocalDateTime d2){
        long diff = getUnixMilliSeconds(d1) - getUnixMilliSeconds(d2);
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
    public static boolean isSameDay(LocalDateTime d1, LocalDateTime d2) {
        if(d1 != null && d2 != null) {
            return d1.toLocalDate().isEqual(d2.toLocalDate());
        } else {
            return false;
        }
    }

}
