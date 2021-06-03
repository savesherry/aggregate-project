package com.example.aggregate_methods.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import com.example.aggregate_methods.tools.Logger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * CREATE BY LiYang
 * ON 5/6/21
 * SUPPLY : Thanks for watching
 */
public abstract class BaseJetPackActivity extends AppCompatActivity {

    public String TAG = getClass().getSimpleName() + "：";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 禁用横屏
        ActivityCollector.addActivity(this);
        Logger.v(TAG, getClass().getSimpleName());
        getViewDataBing();
        initBase();
    }

    private void initBase() {
        initData();
        setListener();
        setNerWork();
    }

    //布局
    protected abstract int initLayout();

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

    /**
     * 绑定当前页面
     *
     * @return
     */
    protected ViewDataBinding getViewDataBing() {
        return DataBindingUtil.setContentView(this, initLayout());
    }

    /**
     * 判断是否连接网络
     *
     * @param context
     * @return
     */
    public boolean isConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (context != null) {
            if (Build.VERSION.SDK_INT < 23) {
                NetworkInfo mWiFiNetworkInfo = cm.getActiveNetworkInfo();
                if (mWiFiNetworkInfo != null) {
                    if (mWiFiNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {//WIFI
                        return true;
                    } else if (mWiFiNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {//移动数据
                        return true;
                    }
                }
            } else {
                Network network = cm.getActiveNetwork();
                if (network != null) {
                    NetworkCapabilities nc = cm.getNetworkCapabilities(network);
                    if (nc != null) {
                        if (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {//WIFI
                            return true;
                        } else if (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {//移动数据
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
