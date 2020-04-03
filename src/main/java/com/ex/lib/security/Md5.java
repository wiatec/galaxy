package com.ex.lib.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author patrick
 */
public class Md5 {

    public static String make16(String source){
        return make(source).substring(8,24);
    }

    public static String make(String source){
        return DigestUtils.md5Hex(source);
    }


}
