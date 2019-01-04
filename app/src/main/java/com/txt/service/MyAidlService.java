package com.txt.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.txt.IComputerManager;
import com.txt.IMyAidlInterface;
import com.txt.IOnComputerArrivedListener;
import com.txt.bean.ComputerEntity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by txt on 2016/5/16.
 */
public class MyAidlService extends Service {
    private boolean flag;
    //解决多线程同步问题
    private CopyOnWriteArrayList<ComputerEntity> mComputerEntities = new CopyOnWriteArrayList<>();
    //用RemoteCallbackList封装了一下监听器
    private RemoteCallbackList<IOnComputerArrivedListener> mListener = new RemoteCallbackList<>();

    private String tag = MyAidlService.class.getSimpleName();
    //传递自定义实体类型，并且增加回调接口（用户服务端主动通知客户端）
    private Binder mComputerBinder = new IComputerManager.Stub() {
        @Override
        public void addComputer(ComputerEntity entity) throws RemoteException {
            Log.w(tag, "addComputer, thread:" + Thread.currentThread()); // 执行在Binder线程Thread[Binder:3223_1,5,main]
            try {
                Thread.sleep(10000); // 休眠6s后执行，看调用者（客户端是否被挂起）
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mComputerEntities.add(entity);
        }

        @Override
        public List<ComputerEntity> getComputerList() throws RemoteException {
            return mComputerEntities;
        }

        @Override
        public void registerUser(IOnComputerArrivedListener listener) throws RemoteException {
            mListener.register(listener);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int counts = mListener.beginBroadcast();
                    int count = counts;
                    Log.i(tag, "count:" + count);
                    int sum = 3;
                    while (flag && sum>0) {
                        try {
                            Thread.sleep(3000);
                            ComputerEntity entity = new ComputerEntity(sum,"adc","abc+"+sum);
                            count = counts;
                            while(count>0){
                                count--;
                                try {
                                    mListener.getBroadcastItem(count).onComputerArrived(entity);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        sum--;
                    }
                    mListener.finishBroadcast();
                }
            }).start();
        }

        @Override
        public void unRegisterUser(IOnComputerArrivedListener listener) throws RemoteException {
            mListener.unregister(listener);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        flag = true;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.w(tag, "onBind, thread:" + Thread.currentThread()); // 执行在主线程
        return mComputerBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
