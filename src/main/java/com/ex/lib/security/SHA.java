package com.ex.lib.security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author patrick
 */
public class SHA {

    private static final String DEFAULT_KEY = "#94n$ye7~jp06%mn#32*nf%{9k2#*";

    public static String sha256(String content) {
        return sha256(content, DEFAULT_KEY);
    }

    /**
     * sha256算法加密字符串
     */
    public static String sha256(String content, String key){
        try {
            Mac sha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256.init(secretKey);
            byte[] array = sha256.doFinal(content.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
