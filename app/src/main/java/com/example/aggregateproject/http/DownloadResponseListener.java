package com.example.aggregateproject.http;

import java.io.File;

/**
 * CREATE BY LiYang
 * ON 2021-03-18
 * SUPPLY : Thanks for watching
 */
public interface DownloadResponseListener {

    default void onStart() {
    }

    void onProgress(String downloadLength, String totalLength, String downloadSpeed, int percentage);

    void onFinish(File file);

    void onFailure(String error);


}
