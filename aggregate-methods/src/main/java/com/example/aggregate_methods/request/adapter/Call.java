package com.example.aggregate_methods.request.adapter;

import com.example.aggregate_methods.request.callback.Callback;
import com.example.aggregate_methods.request.model.Response;
import com.example.aggregate_methods.request.pattern.base.Request;


/**
 * CREATE BY LiYang
 * ON 2021-03-15
 * SUPPLY : Thanks for watching
 */
public interface Call<T> {
    /**
     * 同步执行
     */
    Response<T> execute() throws Exception;

    /**
     * 异步回调执行
     */
    void execute(Callback<T> callback);

    /**
     * 是否已经执行
     */
    boolean isExecuted();

    /**
     * 取消
     */
    void cancel();

    /**
     * 是否取消
     */
    boolean isCanceled();

    Call<T> clone();

    Request getRequest();
}
