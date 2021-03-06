package com.ex.lib.http.callback;


import com.ex.lib.http.HttpDownloadInfo;
import okhttp3.Call;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author patrick
 */
public abstract class UploadCallback extends AbstractCallback {

    public abstract void onPending(HttpDownloadInfo downloadInfo);
    public abstract void onStart(HttpDownloadInfo downloadInfo);
    public abstract void onPause(HttpDownloadInfo downloadInfo);
    public abstract void onProgress(HttpDownloadInfo downloadInfo);
    public abstract void onComplete(HttpDownloadInfo downloadInfo);
    public abstract void onCancel(HttpDownloadInfo downloadInfo);

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        super.onResponse(call, response);
        
    }
}
