package com.ex.lib.common;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * regular util
 * @author patrick
 */
public class RegularUtils {

    /**
     * validate email input format
     */
    public static boolean matchEmail(String email){
        if(StringUtils.isEmpty(email)){
            return false;
        }
        String regular = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+([-|_][a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return match(regular, email);
    }

    /**
     * validate is all digit
     */
    public static boolean matchDigit(String digit){
        if(StringUtils.isEmpty(digit)){
            return false;
        }
        String regular = "^[\\d]*$";
        return match(regular, digit);
    }

    public static boolean matchDate(String dateStr){
        if(StringUtils.isEmpty(dateStr)){
            return false;
        }
        String regular = "^[\\d]{4}-[\\d]{2}-[\\d]{2}$";
        return match(regular, dateStr);
    }

    public static boolean matchDateTime(String dateTimeStr){
        if(StringUtils.isEmpty(dateTimeStr)){
            return false;
        }
        String regular = "^[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}$";
        return match(regular, dateTimeStr);
    }

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
