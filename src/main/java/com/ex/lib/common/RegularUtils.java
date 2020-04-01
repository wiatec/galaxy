package com.ex.lib.common;

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
        String regular = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+([-|_][a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return match(regular, email);
    }

    /**
     * validate is all digit
     */
    public static boolean matchDigit(String data){
        String regular = "^[\\d]*$";
        return match(regular, data);
    }

    public static boolean matchDate(String dateStr){
        String regular = "^[\\d]{4}-[\\d]{2}-[\\d]{2}$";
        return match(regular, dateStr);
    }

    public static boolean matchDateTime(String dateTimeStr){
        String regular = "^[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}$";
        return match(regular, dateTimeStr);
    }

    private static boolean match(String regular, String source){
        Pattern pattern = Pattern.compile(regular);
        return pattern.matcher(source).matches();
    }


}
