package com.ex.lib.enu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author patrick
 */
public enum EnumMonth {

    /**
     * 月份枚举
     */
    JANUARY(1, "Jan", "January", "一月"),
    FEBRUARY(2, "Feb", "February", "二月"),
    MARCH(3, "Mar", "March", "三月"),
    APRIL(4, "Apr", "April", "四月"),
    MAY(5, "May", "May", "五月"),
    JUNE(6, "Jun", "June", "六月"),
    JULY(7, "Jul", "July", "七月"),
    AUGUST(8, "Aug", "August", "八月"),
    SEPTEMBER(9, "Sep", "September", "九月"),
    OCTOBER(10, "Oct", "October", "十月"),
    NOVEMBER(11, "Nov", "November", "十一月"),
    DECEMBER(12, "Dec", "December", "十二月");

    private int month;
    private String enName;
    private String enFullName;
    private String zhName;

    EnumMonth(int month, String enName, String enFullName, String zhName) {
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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
        return Arrays.stream(EnumMonth.values()).map(EnumMonth::getEnFullName).collect(Collectors.toList());
    }

    public static List<String> getEnNames(){
        return Arrays.stream(EnumMonth.values()).map(EnumMonth::getEnName).collect(Collectors.toList());
    }

    public static List<String> getZhNames(){
        return Arrays.stream(EnumMonth.values()).map(EnumMonth::getZhName).collect(Collectors.toList());
    }
}
