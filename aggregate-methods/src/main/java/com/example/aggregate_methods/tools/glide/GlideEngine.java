package com.example.aggregate_methods.tools.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * CREATE BY LiYang
 * ON 2021-01-27
 * SUPPLY : Thanks for watching
 */
public class GlideEngine implements ImageEngine {

    private static GlideEngine engine;

    public static GlideEngine instantiation() {
        if (null == engine) {
            synchronized (GlideEngine.class) {
                if (null == engine)
                    engine = new GlideEngine();
            }
        }
        return engine;
    }

    @Override
    public void loadImage(Context context, ImageView imageView, Object path) {
        Glide.with(context)
                .load(path)
                .apply(new RequestOptions()
                        .priority(Priority.HIGH)
                        .centerCrop())
                .into(imageView);
    }

    @Override
    public void loadImageSize(Context context, ImageView imageView, Object path, @Nullable SizeEngine sizeEngine) {
        Glide.with(context)
                .asBitmap()
                .load(path)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        sizeEngine.onSize(resource.getWidth(), resource.getHeight());
                        imageView.setImageBitmap(resource);
                    }
                });
    }

    @Override
    public void loadImageState(Context context, ImageView imageView, Object path, StateEngine stateEngine) {
        Glide.with(context).load(path).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                stateEngine.onStateFailure();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                stateEngine.onStateSuccess();
                return false;
            }
        }).into(imageView);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .override(resizeX, resizeY)
                        .priority(Priority.HIGH)
                        .centerCrop())
                .into(imageView);
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context)
                .asGif()
                .load(uri)
                .apply(new RequestOptions()
                        .override(resizeX, resizeY)
                        .priority(Priority.HIGH)
                        .fitCenter())
                .into(imageView);
    }

    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Glide.with(context)
                .asBitmap() // some .jpeg files are actually gif
                .load(uri)
                .apply(new RequestOptions()
                        .override(resize, resize)
                        .placeholder(placeholder)
                        .centerCrop())
                .into(imageView);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Glide.with(context)
                .asBitmap() // some .jpeg files are actually gif
                .load(uri)
                .apply(new RequestOptions()
                        .override(resize, resize)
                        .placeholder(placeholder)
                        .centerCrop())
                .into(imageView);
    }
}
