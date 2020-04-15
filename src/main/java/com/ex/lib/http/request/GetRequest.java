package com.ex.lib.http.request;


import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;


/**
 * @author patrick
 */
public class GetRequest extends AbstractRequest {

    @Override
    protected Request createRequest() {
        Request.Builder builder = new Request.Builder();
        // 设置请求头
        if(header !=null){
            Headers headers = Headers.of(header.stringMap);
            builder.headers(headers);
        }
        // 设置请求参数
        if(parameters != null){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(url);
            stringBuilder.append("?");
            for(Map.Entry<String,String> entry : parameters.getStringMap().entrySet()){
                stringBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            url = stringBuilder.toString().substring(0, stringBuilder.toString().length()-1);
        }
        // 设置请求标识
        if(requestTag != null){
            builder.tag(requestTag);
        }
        builder.url(url);
        return builder.build();
    }
}
