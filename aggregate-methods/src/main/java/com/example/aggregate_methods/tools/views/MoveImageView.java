package com.example.aggregate_methods.tools.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.example.aggregate_methods.tools.Methods;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * CREATE BY LiYang
 * ON 2021-03-09
 * SUPPLY : Thanks for watching
 */
public class MoveImageView extends AppCompatImageView implements View.OnTouchListener {
    private int lastX = 0;
    private int lastY = 0;

    private int originX = 0;
    private int originY = 0;

    private Context context;

    private OnNoTouchListener listener;

    private final int min_distance = 5; //  最小位移量

    //屏幕宽度
    private static int screenWidth = 0;

    public MoveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //  last字样为一直重新赋值记录触摸控件后的坐标，origin字样为记录起始坐标
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                originX = (int) event.getRawX();
                originY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX; //  计算手指触控后的偏移量X
                int dy = (int) event.getRawY() - lastY; //  计算手指触控后的偏移量Y
                setX(getX() + dx);
                setY(getY() + dy);

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(originX - lastX) < min_distance && Math.abs(originY - lastY) < min_distance) {
                    if (listener != null) {
                        listener.onNoTouch(v);
                    }
                    return false;// 距离较小，当作click事件来处理
                } else {
                    translateX(lastX);
                }
                break;
            default:
                break;
        }
        return true;
    }

    private void translateX(int x) {
        ObjectAnimator animator;
        animator = ObjectAnimator.ofFloat(this, "x", getX(), screenWidth - getWidth() + Methods.dip2px(context, 8));
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    public interface OnNoTouchListener {
        void onNoTouch(View v);
    }

    public MoveImageView setOnNoTouchListener(OnNoTouchListener listener) {
        this.listener = listener;
        return this;
    }
}

