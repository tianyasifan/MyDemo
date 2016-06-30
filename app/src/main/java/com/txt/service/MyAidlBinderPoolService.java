package com.txt.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.txt.aidlpool.BinderPoolFactory;

/**
 * Created by txt on 2016/5/17.
 */
public class MyAidlBinderPoolService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {//执行多次绑定，只会调用一次，所以这里返回一个Binder池
        return new BinderPoolFactory.BinderPoolImpl();
    }
}
