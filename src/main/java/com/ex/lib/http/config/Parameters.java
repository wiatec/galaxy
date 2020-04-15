package com.ex.lib.http.config;

import java.io.File;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author patrick
 */
public class Parameters {

    private Map<String ,String> stringMap = new ConcurrentHashMap<>(5);
    private Map<String ,File> fileMap = new ConcurrentHashMap<>(1);

    public Parameters() {

    }

    public Parameters(String key, String value) {
        put(key, value);
    }

    public Parameters(String key, File value){
        put(key, value);
    }

    public void put (String key, String value){
        stringMap.put(key ,value);
    }

    public void put (String key, Integer value){
        stringMap.put(key, value.toString());
    }

    public void put (String key, Boolean value){
        stringMap.put(key, value.toString());
    }

    public void put (String key, Float value){
        stringMap.put(key, value.toString());
    }

    public void put (String key, Double value){
        stringMap.put(key, value.toString());
    }

    public void put (String key, BigDecimal value){
        stringMap.put(key, value.toString());
    }

    public void put (String key, File value){
        fileMap.put(key, value);
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public Map<String, File> getFileMap() {
        return fileMap;
    }
}
