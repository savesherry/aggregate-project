package com.example.aggregate_methods.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aggregate_methods.tools.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * CREATE BY LiYang
 * ON 5/31/21
 * SUPPLY : Thanks for watching
 */
public abstract class BaseJetPackFragment extends Fragment {

    public String TAG = getClass().getSimpleName() + "：";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.v(TAG, getClass().getSimpleName());
        return initBing(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBase();
    }

    private void initBase() {
        initData();
        setListener();
        setNerWork();
    }

    //布局
    protected abstract int initLayout();

    //初始化绑定
    protected abstract View initBing(LayoutInflater inflater, ViewGroup container, Bundle bundle);

    //数据
    protected abstract void initData();

    //事件
    protected abstract void setListener();

    //请求
    protected abstract void setNerWork();

    /**
     * 绑定当前页面
     *
     * @return
     */
    protected ViewDataBinding getViewDataBing(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, initLayout(), container, false);
    }
}
