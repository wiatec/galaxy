package com.ex.lib.http.callback;


import com.ex.lib.entities.HttpDownloadInfo;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * @author patrick
 */
public abstract class DownloadCallback extends AbstractCallback {

    public abstract void onPending(HttpDownloadInfo downloadInfo);
    public abstract void onStart(HttpDownloadInfo downloadInfo);
    public abstract void onPause(HttpDownloadInfo downloadInfo);
    public abstract void onProgress(HttpDownloadInfo downloadInfo);
    public abstract void onComplete(HttpDownloadInfo downloadInfo);
    public abstract void onCancel(HttpDownloadInfo downloadInfo);

    private HttpDownloadInfo httpDownloadInfo;

    public void setHttpDownloadInfo(HttpDownloadInfo httpDownloadInfo) {
        this.httpDownloadInfo = httpDownloadInfo;
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        super.onResponse(call, response);
        if(StringUtils.isEmpty(httpDownloadInfo.getPath())){
            onFailure(4201, "Download Path is not present");
            return;
        }
        InputStream inputStream =null;
        RandomAccessFile randomAccessFile = null;
        try {
            ResponseBody responseBody = response.body();
            if(responseBody == null){
                onFailure(5101, "Response Body Error");
                return;
            }
            if(responseBody.contentLength() <= 0 ){
                onFailure(5102, "Response File Length Read Error");
                return;
            }
            httpDownloadInfo.setLength(responseBody.contentLength());
            onPending(httpDownloadInfo);
            File dir = new File(httpDownloadInfo.getPath());
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, httpDownloadInfo.getFileName());
            if(file.exists() && file.length() == httpDownloadInfo.getLength()){
                httpDownloadInfo.setStatus(1);
                httpDownloadInfo.setProgress(100);
                httpDownloadInfo.setMessage("file is exists , no need download");
                onFailure(5103, httpDownloadInfo.getMessage());
                return;
            }
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(httpDownloadInfo.getStartPosition());
            inputStream = responseBody.byteStream();
            httpDownloadInfo.setStatus(1);
            httpDownloadInfo.setMessage("download is start");
            onStart(httpDownloadInfo);
            int length = -1;
            byte[] buffer = new byte[1024 * 1024];
            long finished = 0;
            long currentTime =System.currentTimeMillis();
            while ((length = inputStream.read(buffer)) != -1) {
                randomAccessFile.write(buffer, 0, length);
                finished += length;
                if(System.currentTimeMillis() - currentTime > 2000) {
                    httpDownloadInfo.setFinishedPosition(finished);
                    httpDownloadInfo.setProgress((int) (finished * 100L / httpDownloadInfo.getLength()));
                    httpDownloadInfo.setStatus(1);
                    httpDownloadInfo.setMessage("downloading");
                    onProgress(httpDownloadInfo);
                    currentTime = System.currentTimeMillis();
                }
            }
            if (file.length() == httpDownloadInfo.getLength()) {
                httpDownloadInfo.setProgress(100);
                httpDownloadInfo.setStatus(1);
                httpDownloadInfo.setMessage("download was finished");
                onComplete(httpDownloadInfo);
            } else {
                httpDownloadInfo.setStatus(1);
                httpDownloadInfo.setMessage("file check error after download");
                onFailure(5103, httpDownloadInfo.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            httpDownloadInfo.setStatus(1);
            httpDownloadInfo.setMessage(e.getMessage());
            onFailure(5104, httpDownloadInfo.getMessage());
        }finally {
            try {
                if(randomAccessFile !=null) {
                    randomAccessFile.close();
                }
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
