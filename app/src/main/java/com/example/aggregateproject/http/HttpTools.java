package com.example.aggregateproject.http;

import android.content.Context;
import android.text.TextUtils;

import com.example.aggregate_methods.request.OkGo;
import com.example.aggregate_methods.request.callback.StringCallback;
import com.example.aggregate_methods.request.exception.ExceptionCode;
import com.example.aggregate_methods.request.exception.HttpException;
import com.example.aggregate_methods.request.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * CREATE BY liyang
 * ON 2020-12-10
 * SUPPLY : Thanks for watching
 */
public class HttpTools {
    public static void post(Context context, String url, HashMap<String, Object> params, final JsonResponseListener listener) {
        JSONObject json = new JSONObject(params);
        OkGo.<String>post(url).tag(context)
                .upJson(json.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String result = response.body();
                        try {
                            JSONObject json = new JSONObject(result);
                            String message = json.optString("message");
                            String code = json.optString("status");
                            if (TextUtils.equals("1", code))
                                listener.onSuccess(json);
                            else
                                listener.onFailure(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Throwable exception = response.getException();
                        if (null != exception) {
                            exception.printStackTrace();
                        }
                        if (exception instanceof ConnectException || exception instanceof UnknownHostException) {
                            listener.onFailure("ConnectException || UnknownHostException : "
                                    + ExceptionCode.ConnectAndUnknownHostException);
                        } else if (exception instanceof SocketTimeoutException) {
                            listener.onFailure("SocketTimeoutException : "
                                    + ExceptionCode.SocketTimeoutException);
                        } else if (exception instanceof HttpException) {
                            listener.onFailure("HttpException : "
                                    + ExceptionCode.HttpException);
                        } else if (exception instanceof IllegalStateException) {
                            listener.onFailure("IllegalStateException : "
                                    + ExceptionCode.IllegalStateException);
                        } else {
                            listener.onFailure("网络请求未知错误");
                        }
                    }
                });
    }
}
