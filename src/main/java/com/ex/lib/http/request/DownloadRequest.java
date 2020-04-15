package com.ex.lib.http.request;


import com.ex.lib.entities.HttpDownloadInfo;

import java.util.Map;
import okhttp3.Headers;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;


/**
 * @author patrick
 */
public class DownloadRequest extends AbstractRequest {

    private String mFileName;
    private String mUrl;
    private String mPath;
    private HttpDownloadInfo mDownloadInfo;

    public DownloadRequest() {
    }

    public DownloadRequest fileName(String fileName){
        this.mFileName = fileName;
        return this;
    }

    public DownloadRequest url (String url){
        this.mUrl = url;
        return this;
    }

    public DownloadRequest path (String path){
        this.mPath = path;
        return this;
    }

    public HttpDownloadInfo createDownloadInfo (){
        HttpDownloadInfo downloadInfo = new HttpDownloadInfo();
        if(StringUtils.isEmpty(mFileName)){
            mFileName = mUrl.split("/")[mUrl.split("/").length -1];
        }
        downloadInfo.setFileName(mFileName);
        downloadInfo.setUrl(mUrl);
        downloadInfo.setPath(mPath);
        return downloadInfo;
    }

    @Override
    protected Request createRequest() {
        mDownloadInfo = createDownloadInfo();
        Request.Builder builder = new Request.Builder();
        if(header !=null){
            Headers headers = Headers.of(header.stringMap);
            builder.headers(headers);
        }
        if(parameters != null){
            StringBuilder stringBuilder = new StringBuilder(mUrl);
            stringBuilder.append("?");
            for(Map.Entry<String,String> entry : parameters.getStringMap().entrySet()){
                stringBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            mUrl = stringBuilder.toString().substring(0,stringBuilder.toString().length()-1);
        }
        if(requestTag != null){
            builder.tag(requestTag);
        }
        builder.get().url(mUrl);
        return builder.build();
    }
}
