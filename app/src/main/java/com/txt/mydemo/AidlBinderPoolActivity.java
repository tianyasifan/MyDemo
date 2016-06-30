package com.txt.mydemo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.txt.IComputerManager;
import com.txt.IMyAidlInterface;
import com.txt.IOnComputerArrivedListener;
import com.txt.aidlpool.BinderPoolFactory;
import com.txt.bean.ComputerEntity;

import java.util.List;

/**
 * Created by txt on 2016/5/17.
 * Binder连接池实例
 */
public class AidlBinderPoolActivity extends Activity {
    private BinderPoolFactory mBinderPoolFactory;
    private String tag = AidlBinderPoolActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tag,"onCreate");
        setContentView(R.layout.activity_aidl_binder_pool);
        mBinderPoolFactory = BinderPoolFactory.newInstance(this);
    }

    /**
     * 在布局文件中android：onClick属性指定
     *
     * @param view
     */
    public void binderOne(View view) {
        IBinder binder =
                mBinderPoolFactory.createBinder(BinderPoolFactory.BINDER_ONE);
        IMyAidlInterface caculate = IMyAidlInterface.Stub.asInterface(binder);
        try {
            System.out.println(caculate.add(10, 30));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void binderTwo(View view) {
        IBinder binder =
                mBinderPoolFactory.createBinder(BinderPoolFactory.BINDER_TWO);
        IComputerManager manager = IComputerManager.Stub.asInterface(binder);
        try {
            manager.addComputer(new ComputerEntity(1, "adb", "abd:" + 1));
            List<ComputerEntity> datas = manager.getComputerList();
            for (ComputerEntity entity : datas) {
                System.out.println(entity);
            }
            manager.registerUser(new IOnComputerArrivedListener.Stub() {
                @Override
                public void onComputerArrived(ComputerEntity computer) throws RemoteException {
                    System.out.println(computer);
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
