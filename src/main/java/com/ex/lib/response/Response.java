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

    public static Response<String> success(String msg){
        return new Response<String> (EnumResponseStatus.SUCCESS)
                .setData(msg);
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

    public Response<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public Response<T> setCount(Integer count) {
        this.count = count;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
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
