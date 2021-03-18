package com.example.aggregate_methods.request.convert;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * CREATE BY LiYang
 * ON 2021-03-15
 * SUPPLY : Thanks for watching
 */
public class StringConvert implements Converter<String> {

    @Override
    public String convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;
        return body.string();
    }
}
