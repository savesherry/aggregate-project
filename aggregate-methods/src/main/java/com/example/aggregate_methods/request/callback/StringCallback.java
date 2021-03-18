package com.example.aggregate_methods.request.callback;

import com.example.aggregate_methods.request.convert.StringConvert;

import okhttp3.Response;

/**
 * CREATE BY LiYang
 * ON 2021-03-15
 * SUPPLY : Thanks for watching
 */
public abstract class StringCallback extends AbsCallback<String> {
    private StringConvert convert;

    public StringCallback() {
        convert = new StringConvert();
    }

    @Override
    public String convertResponse(Response response) throws Throwable {
        String s = convert.convertResponse(response);
        response.close();
        return s;
    }
}
