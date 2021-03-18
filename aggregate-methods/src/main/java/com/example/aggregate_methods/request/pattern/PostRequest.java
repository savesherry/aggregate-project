package com.example.aggregate_methods.request.pattern;

import com.example.aggregate_methods.request.model.HttpMethod;
import com.example.aggregate_methods.request.pattern.base.BodyRequest;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * CREATE BY LiYang
 * ON 2021-03-15
 * SUPPLY : Thanks for watching
 */
public class PostRequest <T> extends BodyRequest<T, PostRequest<T>> {

    public PostRequest(String url) {
        super(url);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        Request.Builder requestBuilder = generateRequestBuilder(requestBody);
        return requestBuilder.post(requestBody).url(url).tag(tag).build();
    }
}

