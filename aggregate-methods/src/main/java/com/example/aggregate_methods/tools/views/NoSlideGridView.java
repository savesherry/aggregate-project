package com.example.aggregate_methods.tools.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * CREATE BY LiYang
 * ON 2021-01-26
 * SUPPLY : Thanks for watching
 */
public class NoSlideGridView extends GridView {
    public NoSlideGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoSlideGridView(Context context) {
        super(context);
    }

    public NoSlideGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
