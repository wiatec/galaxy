package com.ex.lib.common;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author patrick
 */
public class GsonUtils {

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

    public static String toStr(Object object){
        if(object == null){
            return "";
        }
        return new Gson().toJson(object);
    }

    public static <T> T toObj(String str, Class<T> tClass){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        return new Gson().fromJson(str, tClass);
    }

    public static <T> List<T> toList(String str, Class<T> tClass){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        ParameterizedType parameterizedType = getType(List.class, tClass);
        return new Gson().fromJson(str, parameterizedType);
    }

}
