package com.ex.lib.common;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author patrick
 */
public class FileUtils {

    /**
     * 获取文件md5值
     */
    public static String md5(String path){
        try {
            File file = new File(path);
            if (!file.isFile()) {
                return "1";
            }
            MessageDigest digest = null;
            FileInputStream in = null;
            byte buffer[] = new byte[1024];
            int len;
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            return "1";
        }
    }

    /**
     * 修改文件后缀名
     */
    public static String convertFileSuffix(String sourcePath, String suffix){
        if(StringUtils.isEmpty(sourcePath)){
            return "";
        }
        String [] ss = sourcePath.split("\\.");
        StringBuilder targetPath = new StringBuilder();
        int len = ss.length;
        for(int i = 0; i < len; i ++){
            if(i == len - 1){
                targetPath.append(suffix);
            }else{
                targetPath.append(ss[i]);
            }
        }
        return targetPath.toString();
    }
}
