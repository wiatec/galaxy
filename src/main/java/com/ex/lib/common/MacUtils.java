package com.ex.lib.common;

import java.util.regex.Pattern;

/**
 * @author patrick
 */
public class MacUtils {

    public static String[] createMacs(String startMac, String endMac){
        String start = startMac.replaceAll(":", "");
        String end = endMac.replaceAll(":", "");
        long s1 = Long.parseLong(start, 16);
        long e1 = Long.parseLong(end, 16);
        int length = (int) (e1 - s1 + 1);
        String [] macs = new String[length];
        for(int i = 0; i < length; i ++){
            String m = Long.toHexString(s1).toUpperCase();
            macs[i] = addColon(m).toUpperCase();
            s1 ++;
        }
        return macs;
    }

    private static String addColon(String s){
        String result = "";
        char[] chars = s.toCharArray();
        int length = chars.length - 1;
        for(int i = length ; i >= 0; i --){
            if(i % 2 == 0 && i != 0){
                result = ":" + chars[i] + result;
            }else{
                result = chars[i] + result;
            }
        }
        return result;
    }

    /**
     * 比较2个mac的大小， 要求mac address 前5位一致
     * @param bigMac bigMac
     * @param smallMac smallMac
     * @return boolean
     */
    public static boolean compare(String bigMac, String smallMac){
        String start = bigMac.replaceAll(":", "");
        String end = smallMac.replaceAll(":", "");
        long s1 = Long.parseLong(start, 16);
        long e1 = Long.parseLong(end, 16);
        return s1 > e1;
    }

    /**
     * 验证mac
     * @param mac mac
     * @return boolean
     */
    public static boolean validateMac(String mac){
        String newMac = mac;
        if(newMac.length() != 17){
            return false;
        }
        newMac = newMac.replaceAll(":", "");
        if(newMac.length() != 12){
            return false;
        }
        String lowMac = newMac.toLowerCase();
        String regular = "[a-zA-Z0-9]{12}";
        Pattern pattern = Pattern.compile(regular);
        return pattern.matcher(lowMac).matches();
    }

}
