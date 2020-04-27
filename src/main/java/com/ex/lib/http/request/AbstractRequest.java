package com.ex.lib.http.request;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import com.ex.lib.entities.HttpDownloadInfo;
import com.ex.lib.http.HttpMaster;
import com.ex.lib.http.callback.AbstractCallback;
import com.ex.lib.http.callback.DownloadCallback;
import com.ex.lib.http.config.Header;
import com.ex.lib.http.config.Parameters;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

/**
 * @author patrick
 */
public abstract class AbstractRequest {
    /**
     * 请求头
     */
    protected Header header;

    /**
     * 请求参数
     */
    protected Parameters parameters;

    /**
     * 请求url
     */
    protected String url;

    /**
     * 每个请求设置的标识，用于取消等操作
     */
    protected String requestTag;

    /**
     * 存储请求call的集合
     */
    private Map<String , Call> callMap;

    /**
     * 是否将请求参数转换为json格式
     */
    protected boolean jsonRequest = false;

    /**
     * 下载时设置的下载信息
     */
    protected HttpDownloadInfo mDownloadInfo;

    public AbstractRequest() {
        parameters = new Parameters();
        header = new Header();
    }

    public AbstractRequest tag(String requestTag){
        this.requestTag = requestTag;
        return this;
    }

    public AbstractRequest header(String key , String value){
        if(value != null) {
            header.put(key, value);
        }
        return this;
    }

    public AbstractRequest headers(Header header){
        this.header = header;
        return this;
    }

    public AbstractRequest json(boolean json){
        this.jsonRequest = json;
        return this;
    }

    public AbstractRequest param(String key , String value){
        if(!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            parameters.put(key, value);
        }
        return this;
    }

    public AbstractRequest param(String key , Integer value){
        if(!StringUtils.isEmpty(key) && value != null) {
            parameters.put(key, value);
        }
        return this;
    }

    public AbstractRequest param(String key , Float value){
        if(!StringUtils.isEmpty(key) && value != null) {
            parameters.put(key, value);
        }
        return this;
    }

    public AbstractRequest param(String key , Double value){
        if(!StringUtils.isEmpty(key) && value != null) {
            parameters.put(key, value);
        }
        return this;
    }

    public AbstractRequest param(String key , BigDecimal value){
        if(!StringUtils.isEmpty(key) && value != null) {
            parameters.put(key, value);
        }
        return this;
    }

    public AbstractRequest param(String key , Boolean value){
        if(!StringUtils.isEmpty(key) && value != null) {
            parameters.put(key, value);
        }
        return this;
    }

    public AbstractRequest param(String key , File value){
        if(!StringUtils.isEmpty(key) && value != null) {
            parameters.put(key, value);
        }
        return this;
    }

    public AbstractRequest params(Parameters parameters){
        if(parameters != null) {
            this.parameters = parameters;
        }
        return this;
    }

    public AbstractRequest setUrl(String url){
        this.url = url;
        return this;
    }

    public AbstractRequest setCallMap(Map<String, Call> callMap) {
        this.callMap = callMap;
        return this;
    }

    /**
     * 创建实际的http请求，具体有子类实现
     * @return okhttp Request对象
     */
    protected abstract Request createRequest();

    /**
     * 同步执行请求
     * 返回文本结果
     */
    public String execute() throws IOException {
        Request request = createRequest();
        Call call = HttpMaster.okHttpClient.newCall(request);
        if (requestTag != null) {
            addCall(requestTag, call);
        }
        Response response = call.execute();
        return response.body() != null? response.body().string(): null;
    }
    /**
     * 同步执行请求
     * 返回okhttp response对象
     */
    public Response executeResponse() throws IOException {
        Request request = createRequest();
        Call call = HttpMaster.okHttpClient.newCall(request);
        if (requestTag != null) {
            addCall(requestTag, call);
        }
        return call.execute();
    }


    /**
     * 异步执行请求
     */
    public void enqueue (AbstractCallback abstractCallback){
        Request request = createRequest();
        Call call = HttpMaster.okHttpClient.newCall(request);
        if (requestTag != null) {
            addCall(requestTag, call);
        }
        if(abstractCallback != null) {
            call.enqueue(abstractCallback);
        }
    }

    /**
     * 异步执行文件下载请求
     */
    public void download (DownloadCallback downloadCallback){
        Request request = createRequest();
        Call call = HttpMaster.okHttpClient.newCall(request);
        if(requestTag !=null) {
            addCall(requestTag, call);
        }
        if(downloadCallback != null) {
            downloadCallback.setHttpDownloadInfo(mDownloadInfo);
            call.enqueue(downloadCallback);
        }
    }

    /**
     * 异步执行文件上传请求
     */
    public void upload(AbstractCallback abstractCallback){
        Request request = createRequest();
        Call call = HttpMaster.okHttpClient.newCall(request);
        if(requestTag !=null) {
            addCall(requestTag, call);
        }
        if(abstractCallback != null) {
            call.enqueue(abstractCallback);
        }
    }



    public void addCall(String requestTag, Call call){
        if(StringUtils.isEmpty(requestTag)) {
            return;
        }
        callMap.put(requestTag, call);
    }

    public void removeCall(String requestTag){
        callMap.remove(requestTag);
    }

}
