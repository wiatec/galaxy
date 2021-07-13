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
     * @param sourcePath    文件完整路径
     */
    public static String md5(String sourcePath){
        try {
            File file = new File(sourcePath);
            if (!file.isFile()) {
                return "1";
            }
            MessageDigest digest;
            FileInputStream in;
            byte[] buffer = new byte[1024];
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
            return null;
        }
    }

    /**
     * 获取文件的后缀名称
     * @param sourcePath 要获取的源文件完整路径 eg. /Users/patrick/IdeaProjects/galaxy/src/main/java/com/ex/lib/common/FileMaster.java
     * @return 后缀名称 eg. java
     */
    public static String getSuffixName(String sourcePath){
        if(StringUtils.isEmpty(sourcePath)){
            return null;
        }
        String [] ss = sourcePath.split("\\.");
        if(ss.length <= 0) {
            return null;
        }
        return ss[ss.length - 1];
    }

    /**
     * 获取文件的后缀名称
     * @param sourceFile 要获取的源文件 eg. /Users/patrick/IdeaProjects/galaxy/src/main/java/com/ex/lib/common/FileMaster.java
     * @return 后缀名称 eg. java
     */
    public static String getSuffixName(File sourceFile){
        if(sourceFile == null || !sourceFile.exists()){
            return null;
        }
        String sourcePath = sourceFile.getAbsolutePath();
        return getSuffixName(sourcePath);
    }

    /**
     * 通过文件路径获取文件完整名称
     * @param sourcePath 要获取的源文件完整路径 eg. /Users/patrick/IdeaProjects/galaxy/src/main/java/com/ex/lib/common/FileMaster.java
     * @return 文件名称 eg.FileMaster.java
     */
    public static String getFileName(String sourcePath){
        if(StringUtils.isEmpty(sourcePath)){
            return null;
        }
        String [] ss = sourcePath.split("/");
        if(ss.length <= 0) {
            return null;
        }
        return ss[ss.length - 1];
    }

    /**
     * 通过文件获取文件完整名称
     * @param sourceFile 要获取的源文件 eg. /Users/patrick/IdeaProjects/galaxy/src/main/java/com/ex/lib/common/FileMaster.java
     * @return 文件名称 eg.FileMaster.java
     */
    public static String getFileName(File sourceFile){
        if(sourceFile == null || !sourceFile.exists()){
            return null;
        }
        String sourcePath = sourceFile.getAbsolutePath();
        return getFileName(sourcePath);
    }

    /**
     * 获取不带后缀的文件名称
     * @param sourcePath 要获取的源文件完整路径 eg. /Users/patrick/IdeaProjects/galaxy/src/main/java/com/ex/lib/common/FileMaster.java
     * @return 不带后缀名的文件名称 eg.FileMaster
     */
    public static String getNoSuffixFileName(String sourcePath){
        String fileName = getFileName(sourcePath);
        if(StringUtils.isEmpty(fileName)){
            return null;
        }
        String[] ss = fileName.split("\\.");
        if(ss.length <= 0) {
            return null;
        }
        return ss[0];
    }


    /**
     * 获取不带后缀的文件名称
     * @param sourceFile 要获取的源文件 eg. /Users/patrick/IdeaProjects/galaxy/src/main/java/com/ex/lib/common/FileMaster.java
     * @return 不带后缀名的文件名称 eg.FileMaster
     */
    public static String getNoSuffixFileName(File sourceFile){
        if(sourceFile == null || !sourceFile.exists()){
            return null;
        }
        String sourcePath = sourceFile.getAbsolutePath();
        return getNoSuffixFileName(sourcePath);
    }

    /**
     * 修改文件后缀名
     * @param sourcePath   源文件完整路径
     * @param newSuffix    新的后缀名称
     * @return 更换后缀名后的文件完整路径
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
                targetPath.append(".")
                        .append(newSuffix);
            }else{
                targetPath.append(ss[i]);
            }
        }
        return targetPath.toString();
    }

    /**
     * 修改文件后缀名
     * @param sourceFile   源文件
     * @param newSuffix    新的后缀名称
     * @return 更换后缀名后的文件完整路径
     */
    public static String convertFileSuffix(File sourceFile, String newSuffix){
        if(sourceFile == null || !sourceFile.exists()){
            return null;
        }
        String sourcePath = sourceFile.getAbsolutePath();
        return convertFileSuffix(sourcePath, newSuffix);
    }

    /**
     * 清空文件夹中的所有文件
     * @param directoryPath 文件夹完整路径
     * @return 是否情况成功
     */
    public static boolean cleanDirectory(String directoryPath){
        if(StringUtils.isEmpty(directoryPath)){
            return false;
        }
        File directoryFile = new File(directoryPath);
        return cleanDirectory(directoryFile);
    }

    /**
     * 清空文件夹中的所有文件
     * @param directoryFile 文件夹文件
     * @return 是否情况成功
     */
    public static boolean cleanDirectory(File directoryFile){
        if(directoryFile == null){
            return false;
        }
        if(!directoryFile.exists()){
           return false;
        }
        if(!directoryFile.isDirectory()){
            return false;
        }
        File[] files = directoryFile.listFiles();
        if(files != null && files.length > 0){
            for(File file: files){
                if(file.isDirectory()){
                    cleanDirectory(file);
                    file.delete();
                }else {
                    file.delete();
                }
            }
        }
        return true;
    }

    /**
     * 根据文件路径删除文件
     * @param sourcePath  文件完整路径
     */
    public boolean delete(String sourcePath){
        if(StringUtils.isEmpty(sourcePath)){
            return false;
        }
        File file = new File(sourcePath);
        return delete(file);
    }

    /**
     * 删除文件
     */
    public boolean delete(File sourceFile){
        if(sourceFile == null){
            return false;
        }
        if(!sourceFile.exists()) {
            return false;
        }
        return sourceFile.delete();
    }
}
