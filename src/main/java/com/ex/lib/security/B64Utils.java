package com.ex.lib.security;

import java.util.Base64;

/**
 * @author patrick
 */
public class B64Utils {

    /**
     * encode
     * @return
     */
    public static String encode(String source) {
        try {
            return Base64.getEncoder()
                    .encodeToString(source.getBytes("utf-8"));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * encode
     * @return
     */
    public static String encode(String source1, String source2) {
        try {
            return Base64.getEncoder()
                    .encodeToString((source1 + ":" + source2).getBytes("utf-8"));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * encode
     * @return
     */
    public static String decode(String source) {
        try {
            byte[] base64decodedBytes = Base64.getDecoder().decode(source);
            return new String(base64decodedBytes, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }



    public static void main(String[] args){
        //cGF0cmljazoxMjMxMjM=
        System.out.println(encode("patrick:" + "123123"));
        System.out.println(encode("patrick", "123123"));
        System.out.println(decode("cGF0cmljazoxMjMxMjM="));
    }
}
