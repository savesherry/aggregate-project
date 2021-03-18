package com.example.aggregateproject.http;

import java.io.File;

/**
 * CREATE BY LiYang
 * ON 2021-03-18
 * SUPPLY : Thanks for watching
 */
public interface DownloadResponseListener {

    void onFinish(File file);

    void onProgress(String downloadLength, String totalLength, String downloadSpeed, int percentage);

    void onFailure(String error);

}
