package com.ex.lib.http.callback;


import okhttp3.Call;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author patrick
 */
public abstract class StringCallback extends AbstractCallback {

    public abstract void onSuccess(String data);

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        super.onResponse(call, response);
        if(response.body() != null){
            String data = response.body().string();
            onSuccess(data);
        }
    }
}
