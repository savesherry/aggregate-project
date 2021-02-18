package com.example.aggregate_methods.tools.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

/**
 * CREATE BY LiYang
 * ON 2021-01-27
 * SUPPLY : Thanks for watching
 */
public interface ImageEngine {
    /**
     * 加载网络图片
     *
     * @param context
     * @param imageView
     * @param path
     */
    void loadImage(Context context, ImageView imageView, Object path);

    /**
     * 加载网络图片
     *
     * @param context
     * @param resizeX
     * @param resizeY
     * @param imageView
     * @param uri
     */
    void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri);

    /**
     * 加载动态GIF
     *
     * @param context
     * @param resizeX
     * @param resizeY
     * @param imageView
     * @param uri
     */
    void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri);

    /**
     * 加载缩略图
     *
     * @param context
     * @param resize
     * @param placeholder
     * @param imageView
     * @param uri
     */
    void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri);

    /**
     * 加载缩略GIF
     *
     * @param context
     * @param resize
     * @param placeholder
     * @param imageView
     * @param uri
     */
    void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri);
}
