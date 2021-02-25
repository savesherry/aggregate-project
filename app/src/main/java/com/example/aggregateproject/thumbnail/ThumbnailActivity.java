package com.example.aggregateproject.thumbnail;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.aggregate_methods.base.BaseActivity;
import com.example.aggregate_methods.tools.Methods;
import com.example.aggregate_methods.tools.click.OnMultiClickListener;
import com.example.aggregate_methods.tools.enlarge.EnlargeActivity;
import com.example.aggregate_methods.tools.glide.GlideEngine;
import com.example.aggregate_methods.tools.transition.ActivityTransition;
import com.example.aggregate_methods.tools.transition.ActivityTransitionOptions;
import com.example.aggregateproject.CustomConstant;
import com.example.aggregateproject.R;

public class ThumbnailActivity extends BaseActivity {

    private static final String TAG = ThumbnailActivity.class.getSimpleName();
    private ImageView imageView;

    @Override
    protected int getLayout() {
        return R.layout.activity_thumbnail;
    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.imageView);
    }

    @Override
    protected void initData() {
        GlideEngine.instantiation().loadImageSize(this, imageView, CustomConstant.imagePath,
                (width, height) -> {
                    Methods.imageViewScale(imageView, width, height);
                });
    }

    @Override
    protected void setListener() {
        imageView.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                Intent intent = new Intent(ThumbnailActivity.this, EnlargeActivity.class);
                intent.putExtra("imagePath", CustomConstant.imagePath);
                // ready for transition options
                ActivityTransitionOptions options =
                        ActivityTransitionOptions.makeTransitionOptions(
                                ThumbnailActivity.this,
                                findViewById(R.id.imageView));
                // start transition
                ActivityTransition.startActivity(intent, options);
            }
        });
    }

    @Override
    protected void setNerWork() {

    }
}
