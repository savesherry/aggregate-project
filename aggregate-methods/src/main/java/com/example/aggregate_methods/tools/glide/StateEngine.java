package com.example.aggregate_methods.tools.glide;

/**
 * CREATE BY LiYang
 * ON 2021-02-25
 * SUPPLY : Thanks for watching
 */
public interface StateEngine {

    /**
     * 加载完毕
     */
    void onStateSuccess();

    /**
     * 加载失败
     */
    default void onStateFailure() {

    }
}
