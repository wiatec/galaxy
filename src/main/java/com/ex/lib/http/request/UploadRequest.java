package com.ex.lib.http.request;

import java.io.File;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author patrick
 */
public class UploadRequest extends AbstractRequest {


    @Override
    protected Request createRequest() {
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        bodyBuilder.setType(MultipartBody.FORM);
        if(parameters != null && parameters.getStringMap().size() > 0){
            for(Map.Entry<String ,String > entry: parameters.getStringMap().entrySet()){
                bodyBuilder.addFormDataPart(entry.getKey() ,entry.getValue());
            }
        }
        if(parameters != null && parameters.getFileMap().size() > 0){
            for(Map.Entry<String , File> entry: parameters.getFileMap().entrySet()){
                bodyBuilder.addFormDataPart(entry.getKey(),
                        entry.getValue().getName(),
                        RequestBody.create(null, entry.getValue()));
            }
        }
        Request.Builder builder = new Request.Builder();
        if(header!= null){
            Headers headers = Headers.of(header.stringMap);
            builder.headers(headers);
        }
        if(requestTag != null){
            builder.tag(requestTag);
        }
        builder.post(bodyBuilder.build()).url(url);
        return builder.build();
    }
}
