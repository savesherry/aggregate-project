package com.example.aggregate_methods.request.callback;

import com.example.aggregate_methods.request.model.Progress;
import com.example.aggregate_methods.request.model.Response;
import com.example.aggregate_methods.request.pattern.base.Request;
import com.example.aggregate_methods.tools.Logger;

/**
 * CREATE BY LiYang
 * ON 2021-03-15
 * SUPPLY : Thanks for watching
 */
public abstract class AbsCallback<T> implements Callback<T> {

    @Override
    public void onStart(Request<T, ? extends Request> request) {
    }

    @Override
    public void onCacheSuccess(Response<T> response) {
    }

    @Override
    public void onError(Response<T> response) {
        Logger.e("OkGo", response.getException() + "");
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void uploadProgress(Progress progress) {
    }

    @Override
    public void downloadProgress(Progress progress) {
    }
}

