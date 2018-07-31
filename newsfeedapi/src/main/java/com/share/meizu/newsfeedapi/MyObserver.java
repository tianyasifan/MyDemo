package com.share.meizu.newsfeedapi;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by tongxiaotao on 18-3-6.
 */

public class MyObserver implements Observer<BaseBean>{
    String TAG = "";

    @Override
    public void onSubscribe(Disposable d) {
        Log.i(TAG, "onSubscribe");
    }

    @Override
    public void onNext(BaseBean value) {
        Log.i(TAG, "onNext:" + value);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e);
    }

    @Override
    public void onComplete() {
        Log.i(TAG, "onComplete");
    }
}
