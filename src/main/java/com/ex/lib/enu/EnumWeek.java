package com.ex.lib.enu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author patrick
 */
public enum EnumWeek {

    /**
     * 星期枚举
     */
    MONDAY(1, "Mon", "Monday", "星期一"),
    TUESDAY(2, "Tue", "Tuesday", "星期二"),
    WEDNESDAY(3, "Wed", "Wednesday", "星期三"),
    THURSDAY(4, "Thu", "Thursday", "星期四"),
    FRIDAY(5, "Fri", "Friday", "星期五"),
    SATURDAY(6, "Sat", "Saturday", "星期六"),
    SUNDAY(7, "Sun", "Sunday", "星期日");

    private int day;
    private String enName;
    private String enFullName;
    private String zhName;

    EnumWeek(int day, String enName, String enFullName, String zhName) {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getEnFullName() {
        return enFullName;
    }

    public void setEnFullName(String enFullName) {
        this.enFullName = enFullName;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public static List<String> getEnFullNames(){
        return Arrays.stream(EnumWeek.values()).map(EnumWeek::getEnFullName).collect(Collectors.toList());
    }

    public static List<String> getEnNames(){
        return Arrays.stream(EnumWeek.values()).map(EnumWeek::getEnName).collect(Collectors.toList());
    }

    public static List<String> getZhNames(){
        return Arrays.stream(EnumWeek.values()).map(EnumWeek::getZhName).collect(Collectors.toList());
    }
}
