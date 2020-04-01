package com.ex.lib.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author patrick
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    private int code;
    private String msg;
    private Integer count;
    private T data;



    private Response(int code, String msg){
        this.code = code;
        this.msg = msg;
    }


    private Response(EnumResponseStatus enumResponseStatus){
        this.code = enumResponseStatus.getCode();
        this.msg = enumResponseStatus.getMessage();
    }

    private Response(EnumResponseStatus enumResponseStatus, T data){
        this.code = enumResponseStatus.getCode();
        this.msg = enumResponseStatus.getMessage();
        this.data = data;
    }



    @JsonIgnore
    public boolean isSuccess(){
        return this.code == EnumResponseStatus.SUCCESS.getCode();
    }

    public static Response success(){
        return new Response(EnumResponseStatus.SUCCESS);
    }

    public static Response success(String msg){
        return new Response(EnumResponseStatus.SUCCESS.getCode(), msg);
    }

    public static Response success(int code, String msg){
        return new Response(EnumResponseStatus.SUCCESS.getCode(), msg);
    }

    public static <T> Response<T> success(T data){
        return new Response<>(EnumResponseStatus.SUCCESS, data);
    }

    public static Response error(){
        return new Response(EnumResponseStatus.ERROR_INTERNAL_SERVER_SQL);
    }

    public static Response error(EnumResponseStatus enumResponseStatus){
        return new Response(enumResponseStatus);
    }

    public static Response error(String msg){
        return new Response(EnumResponseStatus.ERROR_INTERNAL_SERVER.getCode(), msg);
    }

    public static Response error(int code, String msg){
        return new Response(code, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
