package com.ex.lib.common;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * regular util
 * @author patrick
 */
public class RegularMaster {

    /**
     * 验证email
     */
    public static boolean matchEmail(String email){
        if(StringUtils.isEmpty(email)){
            return false;
        }
        String regular = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+([-|_][a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return match(regular, email);
    }

    /**
     * 验证是否是纯数字
     */
    public static boolean matchDigit(String digit){
        if(StringUtils.isEmpty(digit)){
            return false;
        }
        String regular = "^[\\d]*$";
        return match(regular, digit);
    }

    /**
     * 验证日期格式 yyyy-MM-dd
     */
    public static boolean matchDate(String dateStr){
        if(StringUtils.isEmpty(dateStr)){
            return false;
        }
        String regular = "^[\\d]{4}-[\\d]{2}-[\\d]{2}$";
        return match(regular, dateStr);
    }


    /**
     * 验证日期时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static boolean matchDateTime(String dateTimeStr){
        if(StringUtils.isEmpty(dateTimeStr)){
            return false;
        }
        String regular = "^[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}$";
        return match(regular, dateTimeStr);
    }

    /**
     * 验证mac地址 xx:xx:xx:xx:xx:xx
     */
    public static boolean matchMacAddress(String madAddress){
        if(StringUtils.isEmpty(madAddress)){
            return false;
        }
        madAddress = madAddress.toUpperCase();
        String regular = "^[\\dA-F]{2}:[\\dA-F]{2}:[\\dA-F]{2}:[\\dA-F]{2}:[\\dA-F]{2}:[\\dA-F]{2}$";
        return match(regular, madAddress);
    }

    private static boolean match(String regular, String source){
        Pattern pattern = Pattern.compile(regular);
        return pattern.matcher(source).matches();
    }


}
