package com.ex.lib.common;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author patrick
 */
public class FileMaster {

    /**
     * 获取文件md5值
     * @param filePath    文件完整路径
     */
    public static String md5(String filePath){
        try {
            File file = new File(filePath);
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
     * @param sourcePath   源文件完整路径
     * @param newSuffix    新的后缀名称
     */
    public static String convertFileSuffix(String sourcePath, String newSuffix){
        if(StringUtils.isEmpty(sourcePath)){
            return "";
        }
        String [] ss = sourcePath.split("\\.");
        StringBuilder targetPath = new StringBuilder();
        int len = ss.length;
        for(int i = 0; i < len; i ++){
            if(i == len - 1){
                targetPath.append(newSuffix);
            }else{
                targetPath.append(ss[i]);
            }
        }
        return targetPath.toString();
    }

    /**
     * 根据文件路径删除文件
     * @param filePath     文件完整路径
     */
    public boolean delete(String filePath){
        if(StringUtils.isEmpty(filePath)){
            return false;
        }
        File file = new File(filePath);
        return delete(file);
    }

    /**
     * 删除文件
     */
    public boolean delete(File file){
        if(file == null){
            return false;
        }
        if(!file.exists()) {
            return false;
        }
        return file.delete();
    }
}
