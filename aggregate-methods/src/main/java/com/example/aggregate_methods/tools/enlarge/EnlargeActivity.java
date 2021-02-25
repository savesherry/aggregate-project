package com.example.aggregate_methods.tools.enlarge;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aggregate_methods.BuildConfig;
import com.example.aggregate_methods.R;
import com.example.aggregate_methods.base.BaseActivity;
import com.example.aggregate_methods.tools.glide.GlideEngine;
import com.example.aggregate_methods.tools.transition.ActivityTransition;

import androidx.annotation.Nullable;

public class EnlargeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imageView;
    private boolean finishEnter = false;
    private long transitionDuration = 800;
    private String imagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState)
            transitionDuration = 0;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_enlarge;
    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.imageView);
    }

    @Override
    protected void initData() {
        if (!getIntent().hasExtra("imagePath")) {
            Toast.makeText(EnlargeActivity.this, "请查看路径是否正确", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            imagePath = getIntent().getStringExtra("imagePath");
            GlideEngine.instantiation().loadImageSize(this, imageView, imagePath, ((width, height) -> {
                ActivityTransition.enter(
                        this,
                        transitionDuration,
                        new DecelerateInterpolator(),
                        new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                // init other views after transition anim
                                finishEnter = true;
                            }
                        });
            }));
        }
    }

    @Override
    protected void setListener() {
        imageView.setOnClickListener(this);
    }

    @Override
    protected void setNerWork() {

    }

    @Override
    public void onClick(View v) {
        if (finishEnter) {
            finishEnter = false;
            ActivityTransition.exit(EnlargeActivity.this, BuildConfig.TRANSITION_DURATION, new DecelerateInterpolator());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (finishEnter) {
            finishEnter = false;
            ActivityTransition.exit(EnlargeActivity.this, BuildConfig.TRANSITION_DURATION, new DecelerateInterpolator());
        }
    }
}
