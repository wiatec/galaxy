package com.ex.lib.http.callback;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author patrick
 */
public abstract class AbstractCallback implements Callback {


    public abstract void onFailure(int code, String error);

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        onFailure(4000, e.getLocalizedMessage());
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.code() == 400) {
            onFailure(response.code(), "Bad Request");
            return;
        }
        if (response.code() == 401) {
            onFailure(response.code(), "Unauthorized");
            return;
        }
        if (response.code() == 403) {
            onFailure(response.code(), "Forbidden Explained");
            return;
        }
        if (response.code() == 404) {
            onFailure(response.code(), "No Found");
            return;
        }
        if (response.code() == 405) {
            onFailure(response.code(), "Method Not Allowed");
            return;
        }
        if (response.code() == 408) {
            onFailure(response.code(), "Request Timeout");
            return;
        }
        if (response.code() == 500) {
            onFailure(response.code(), "Internal Server Error");
            return;
        }
        if (response.code() == 502) {
            onFailure(response.code(), "Bad Gateway");
            return;
        }
        if (response.code() == 503) {
            onFailure(response.code(), "Service Unavailable");
            return;
        }
    }
}
