package com.example.aggregate_methods.tools.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * CREATE BY LiYang
 * ON 2021-01-26
 * SUPPLY : Thanks for watching
 */
public class NonSlideListView extends ListView {
    public NonSlideListView(Context context) {
        super(context);
    }

    public NonSlideListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonSlideListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
