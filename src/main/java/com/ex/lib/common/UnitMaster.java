package com.ex.lib.common;

/**
 * @author patrick
 */
public class UnitMaster {

    private static final int F_1024 = 1024;

    /**
     * 开氏温度转为摄氏温度
     */
    public static float kelvinToCelsius (float kelvin){
        float t = kelvin - 273.15f;
        return (float)(Math.round(t*10))/10;
    }

    /**
     * 摄氏温度转为华氏温度
     */
    public static int celsiusToFahrenheit (float celsius){
        return (int) ((1.8f * celsius)+32);
    }

    /**
     * 将比特单位数值格式化成标准字符串形式(xxGb xxMb xxKb xxb)
     */
    public static String formatStorage(int value){
        int gb = value / F_1024 / F_1024 / F_1024;
        int mb = (value - gb * F_1024 * F_1024 * F_1024) / F_1024 / F_1024;
        int kb = (value - gb * F_1024 * F_1024 * F_1024 - mb * F_1024 * F_1024) / F_1024;
        int bytes = (value - gb * F_1024 * F_1024 * F_1024 - mb * F_1024 * F_1024 - kb * F_1024);
        StringBuilder stringBuilder = new StringBuilder();
        if(gb > 0){
            stringBuilder.append(gb).append("GB");
        }
        if(mb > 0){
            stringBuilder.append(mb).append("MB");
        }
        if(kb > 0){
            stringBuilder.append(kb).append("KB");
        }
        if(bytes > 0){
            stringBuilder.append(bytes).append("bytes");
        }
        return stringBuilder.toString();
    }

}
