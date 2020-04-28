package com.ex.lib.http.request;


import com.google.gson.Gson;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;

/**
 * @author patrick
 */
public class PutRequest extends AbstractRequest {


    @Override
    protected Request createRequest() {
        Request.Builder builder = new Request.Builder();
        if(header != null){
            Headers headers = Headers.of(header.stringMap);
            builder.headers(headers);
        }
        if(parameters != null){
            RequestBody requestBody;
            if(jsonRequest){
                if(StringUtils.isEmpty(jsonParams)){
                    jsonParams = new Gson().toJson(parameters.getStringMap());
                }
                MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                requestBody = RequestBody.create(mediaType, jsonParams);
            }else {
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
                StringBuilder stringBuilder = new StringBuilder();
                for (Map.Entry<String, String> entry : parameters.getStringMap().entrySet()) {
                    stringBuilder.append(entry.getKey())
                            .append("=")
                            .append(entry.getValue())
                            .append("&");
                }
                requestBody = RequestBody.create(mediaType, stringBuilder.toString());
            }
            builder.put(requestBody);
        }
        if(requestTag != null){
            builder.tag(requestTag);
        }
        builder.url(url);
        return builder.build();
    }
}
