package com.example.aggregate_methods.tools.expandable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

import com.example.aggregate_methods.tools.Logger;

/**
 * CREATE BY LiYang
 * ON 2021-03-20
 * SUPPLY : Thanks for watching
 */
public class DropDownAnimation extends TranslateAnimation {

    private int targetHeight;
    private View targetView;
    private boolean targetState;

    public DropDownAnimation(Context context, AttributeSet attrs, View targetView, int targetHeight, boolean targetState) {
        super(context, attrs);
        this.targetView = targetView;
        this.targetHeight = targetHeight;
        this.targetState = targetState;
    }

    // down的时候，interpolatedTime从0增长到1，这样newHeight也从0增长到targetHeight
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight;
        if (targetState) {
            newHeight = (int) (targetHeight * interpolatedTime);
        } else {
            newHeight = (int) (targetHeight * (1 - interpolatedTime));
        }
        targetView.getLayoutParams().height = newHeight;
        targetView.requestLayout();
        if (targetView.getVisibility() == View.GONE)
            targetView.setVisibility(View.VISIBLE);
    }
}
