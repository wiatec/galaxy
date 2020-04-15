package com.ex.lib.http;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.ex.lib.http.request.*;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;

/**
 * @author patrick
 */
public class HttpMaster {


    /**
     * 存放请求call的集合
     */
    private static Map<String , Call> callMap = new ConcurrentHashMap<>(20);
    public static OkHttpClient okHttpClient;

    /*
     * okhttp client init
     */
    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.retryOnConnectionFailure(true);
        builder.connectTimeout(120, TimeUnit.SECONDS);
        builder.writeTimeout(120,TimeUnit.SECONDS);
        builder.readTimeout(120,TimeUnit.SECONDS);
//        builder.addInterceptor(new SessionInterceptor());
        okHttpClient = builder.build();
    }


    /**
     * get request
     */
    public static AbstractRequest get(String url){
        GetRequest request = new GetRequest();
        request.setUrl(url);
        return request;
    }

    /**
     * post request
     */
    public static AbstractRequest post(String url){
        PostRequest request = new PostRequest();
        request.setUrl(url);
        return request;
    }

    /**
     * put request
     */
    public static AbstractRequest put(String url){
        PutRequest request = new PutRequest();
        request.setUrl(url);
        return request;
    }

    /**
     * delete request
     */
    public static AbstractRequest delete(String url){
        DeleteRequest request = new DeleteRequest(url);
        request.setUrl(url);
        return request;
    }

    /**
     * upload request
     */
    public static AbstractRequest upload(String url){
        UploadRequest request = new UploadRequest();
        request.setUrl(url);
        return request;
    }

    /**
     * download request
     */
    public static DownloadRequest download(String url) {
        DownloadRequest request = new DownloadRequest();
        request.setUrl(url);
        return request;
    }

    /**
     * 取消所有请求
     */
    public static void cancelAll(){
        HttpMaster.okHttpClient.dispatcher().cancelAll();
    }

    /**
     * 根据设置的标签取消请求
     */
    public static void cancel(String requestTag){
        if(StringUtils.isEmpty(requestTag)) {
            return;
        }
        Call call = callMap.get(requestTag);
        if(call != null){
            call.cancel();
        }
    }

    public static void addCall(String requestTag, Call call){
        if(StringUtils.isEmpty(requestTag)) {
            return;
        }
        callMap.put(requestTag, call);
    }

    public static void removeCall(String requestTag){
        callMap.remove(requestTag);
    }
}
