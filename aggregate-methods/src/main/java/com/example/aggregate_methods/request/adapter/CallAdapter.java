package com.example.aggregate_methods.request.adapter;

/**
 * CREATE BY LiYang
 * ON 2021-03-15
 * SUPPLY : Thanks for watching
 */
public interface CallAdapter <T, R> {

    /** call执行的代理方法 */
    R adapt(Call<T> call, AdapterParam param);
}
