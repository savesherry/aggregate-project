package com.example.aggregate_methods.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.example.aggregate_methods.tools.Logger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 含有MapView初始化的base类型
 * <p>
 * CREATE BY LiYang
 * ON 2021-03-26
 * SUPPLY : Thanks for watching
 */
public abstract class BaseMapActivity extends AppCompatActivity {

    public String TAG = getClass().getSimpleName() + "：";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 禁用横屏
        setContentView(getLayout());
        initMap(savedInstanceState);
        ActivityCollector.addActivity(this);
        Logger.v(TAG, getClass().getSimpleName());
        initBase();
    }

    private void initBase() {
        initView();
        initData();
        setListener();
        setNerWork();
    }

    protected abstract void initMap(Bundle savedInstanceState);

    //布局
    protected abstract int getLayout();

    //控件
    protected abstract void initView();

    //数据
    protected abstract void initData();

    //事件
    protected abstract void setListener();

    //请求
    protected abstract void setNerWork();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
