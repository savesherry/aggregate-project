package com.example.aggregateproject.asynchronous;


import com.example.aggregate_methods.base.BaseActivity;
import com.example.aggregateproject.R;

public class RxJavaActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_rx_java;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
//        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
//            emitter.onNext(1);
//            emitter.onNext(2);
//            emitter.onNext(3);
//            emitter.onComplete();
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Logger.e(TAG, "======================onSubscribe");
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Logger.e(TAG, integer.toString());
//                Logger.e(TAG, "======================onNext");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Logger.e(TAG, "======================onError");
//            }
//
//            @Override
//            public void onComplete() {
//                Logger.e(TAG, "======================onComplete");
//            }
//        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setNerWork() {

    }
}
