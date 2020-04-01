package com.ex.lib.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * token util
 * @author patrick
 */
public class MD5Utils {

    public static String create16(String s1){
        return create32(s1).substring(8,24);
    }

    public static String create32(String s1){
        return DigestUtils.md5Hex(s1 + System.currentTimeMillis());
    }

    public static String create64(String s1){
        return DigestUtils.md5Hex(s1 + System.currentTimeMillis()) +
                DigestUtils.md5Hex(s1 + System.currentTimeMillis() + System.currentTimeMillis()) ;
    }

    public static String make16(String s1){
        return make32(s1).substring(8,24);
    }

    public static String make32(String s1){
        return DigestUtils.md5Hex(s1);
    }

    public static void main (String [] args){
        String t = create64("dsfdss");
        System.out.println(t);
    }

}
