package com.example.aggregate_methods.tools.expandable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.aggregate_methods.R;
import com.example.aggregate_methods.tools.Logger;

/**
 * CREATE BY LiYang
 * ON 2021-03-20
 * SUPPLY : Thanks for watching
 */
public class ExpandableLayout extends LinearLayout implements View.OnClickListener {
    public String TAG = getClass().getSimpleName() + "：";
    private Context mContext;
    private View headView, contentView;
    private ImageView imageView;
    int mContentHeight = 0;
    int mTitleHeight = 0;
    private boolean isExpand;
    private Animation animationUp, animationDown;

    public ExpandableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mContentHeight == 0) {
            this.contentView.measure(widthMeasureSpec, 0);
            this.mContentHeight = this.contentView.getMeasuredHeight();
        }
        if (this.mTitleHeight == 0) {
            this.headView.measure(widthMeasureSpec, 0);
            this.mTitleHeight = this.headView.getMeasuredHeight();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int num = getChildCount();
        if (num == 2 || num == 3) {
            if (num == 2) {
                this.headView = getChildAt(0);
                this.contentView = getChildAt(1);
            } else {
                this.headView = getChildAt(0);
                this.contentView = getChildAt(2);
            }
            this.headView.setOnClickListener(this);
            imageView = this.headView.findViewWithTag("arrowRight");
            if (null == imageView)
                Logger.e(TAG, "不含有tag名称为arrowRight的转向图片");
            contentView.setVisibility(View.GONE);
        } else {
            Logger.e(TAG, "布局数量只支持到2-3个组件，默认3组件的中间组件为分割线");
        }
    }

    @Override
    public void onClick(View v) {
        clearAnimation();
        if (!isExpand) {
            isExpand = true;
            if (animationDown == null) {
                animationDown = new DropDownAnimation(mContext, null, contentView, mContentHeight, true);
                animationDown.setDuration(500); // SUPPRESS CHECKSTYLE
            }
            startAnimation(animationDown);
            contentView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_alpha_in));
            imageView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_rotate_down));
        } else {
            isExpand = false;
            if (animationUp == null) {
                animationUp = new DropDownAnimation(mContext, null, contentView, mContentHeight, false);
                animationUp.setDuration(500); // SUPPRESS CHECKSTYLE
            }
            startAnimation(animationUp);
            contentView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_alpha_out));
            imageView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_rotate_up));
        }
    }
}
