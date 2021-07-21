package com.ex.lib.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author patrick
 */
public class PinYinMaster {


    /**
     * 获取汉字拼音首字母
     * @param source 汉字
     */
    public static String convert2Pinyin(String source){
        return convert2Pinyin(source, false);
    }


    /**
     * 将汉字转为拼音
     * @param source 汉字
     * @param isFull true 取全部拼音， false 取拼音首字母
     */
    public static String convert2Pinyin(String source, boolean isFull){
         // ^[\u2E80-\u9FFF]+$ 匹配所有东亚区的语言
         // ^[\u4E00-\u9FFF]+$ 匹配简体和繁体
         // ^[\u4E00-\u9FA5]+$ 匹配简体
        String regExp="^[\u4E00-\u9FFF]+$";
        StringBuilder sb = new StringBuilder();
        if(source == null|| "" .equals(source.trim())){
            return "";
        }
        String pinyin;
        for(int i = 0; i < source.length(); i ++){
            char unit = source.charAt(i);
            //是汉字，则转拼音
            Pattern pattern=Pattern.compile(regExp);
            Matcher matcher=pattern.matcher(String.valueOf(unit));
            if(matcher.find()){
                pinyin = convertSingle2Pinyin(unit);
                if(isFull){
                    sb.append(pinyin);
                }
                else{
                    sb.append(pinyin.charAt(0));
                }
            }else{
                sb.append(unit);
            }
        }
        return sb.toString();
    }

    /**
     * 将单个汉字转为拼音
     * @param source 单个汉字
     */
    private static String convertSingle2Pinyin(char source){
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String[] res;
        StringBuilder sb = new StringBuilder();
        try {
            res = PinyinHelper.toHanyuPinyinStringArray(source, outputFormat);
            //对于多音字，只用第一个拼音
            sb.append(res[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }
}
