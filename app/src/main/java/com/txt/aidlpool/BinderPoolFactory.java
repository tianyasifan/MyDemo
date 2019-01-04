package com.txt.aidlpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.txt.IBinderPool;
import com.txt.service.MyAidlBinderPoolService;

/**
 * Created by txt on 2016/5/17.
 * 使用工厂方法模式，通过不同的码生成对应的Binder对象
 */
public class BinderPoolFactory {
    //生成对应Binder的码
    public static final int BINDER_NONE = -1;
    public static final int BINDER_ONE = BINDER_NONE+1;
    public static final int BINDER_TWO = BINDER_ONE+1;
    private static BinderPoolFactory mInstance;
    private IBinderPool mIBinderPool;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIBinderPool = BinderPoolImpl.Stub.asInterface(service); // 拿到服务端的binder对象
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public BinderPoolFactory(Context context){
        connectedService(context.getApplicationContext());
    }

    public static BinderPoolFactory newInstance(Context context){
        if(mInstance==null){
            synchronized (BinderPoolFactory.class){
                if(mInstance==null){
                    mInstance = new BinderPoolFactory(context);
                }
            }
        }
        return mInstance;
    }

    private void connectedService(Context context){
        context.bindService(new Intent(context, MyAidlBinderPoolService.class),mConnection,Context.BIND_AUTO_CREATE);
    }

    public IBinder createBinder(int code){
        IBinder binder = null;
        try {
            binder = mIBinderPool.queryBinder(code);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return binder;
    }

    /**
     * 实现查询Binder的接口，运行在服务端
     */
   public static class BinderPoolImpl extends IBinderPool.Stub{

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder iBinder = null;
            switch (binderCode){
                case BINDER_ONE:
                    iBinder = new BinderOne();
                    break;
                case BINDER_TWO:
                    iBinder = new BinderTwo();
                    break;
            }
            return iBinder;
        }
    }
}
