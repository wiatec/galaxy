package com.ex.lib.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 对字符串进行base64编解码
 * @author patrick
 */
public class B64 {

    public static String encode(String source){
        return Base64.getEncoder().encodeToString(source.getBytes(StandardCharsets.UTF_8));
    }

    public static String decode(String source){
        return new String(Base64.getDecoder().decode(source), StandardCharsets.UTF_8);
    }
}
