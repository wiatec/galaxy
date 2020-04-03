package com.ex.lib.common;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author patrick
 */
public class GsonMaster {

    private static ParameterizedType getType(final Class c, final Type... args){
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getRawType() {
                return c;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    /**
     * 将对象转为json字符串
     */
    public static String toStr(Object object){
        if(object == null){
            return null;
        }
        return new Gson().toJson(object);
    }

    /**
     * 将json 字符串转为对象
     */
    public static <T> T toObj(String str, Class<T> tClass){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        return new Gson().fromJson(str, tClass);
    }

    /**
     * 将json字符串转为对象列表
     */
    public static <T> List<T> toList(String str, Class<T> tClass){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        ParameterizedType parameterizedType = getType(List.class, tClass);
        return new Gson().fromJson(str, parameterizedType);
    }

}
