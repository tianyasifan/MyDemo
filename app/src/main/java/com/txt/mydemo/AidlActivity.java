package com.txt.mydemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.Toast;

import com.txt.IComputerManager;
import com.txt.IMyAidlInterface;
import com.txt.IOnComputerArrivedListener;
import com.txt.bean.ComputerEntity;
import com.txt.service.MyAidlService;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by txt on 2016/5/16.
 */
public class AidlActivity extends Activity {
    private String tag = AidlActivity.class.getSimpleName();
    private IMyAidlInterface mInterface;
    private IComputerManager mComputerManager;


    /**
     * 第二种重连方法
     * 由于Binder是有可能会意外死亡的，也就是Service所在进程被系统杀死，
     * 这时候我们调用Service的方法就会失败。
     * 在第一个例子中我们通过onServiceDisconnected方法中重新绑定服务。
     * 在这个例子中我们采用了另外一种方法，
     * 由于在Binder中提供了两个配对的方法linkToDeath和unlinkToDeath，
     * 通过linkToDeath可以给Binder设置一个死亡代理，
     * Binder死亡时回调binderDied方法，在binderDied方法中我们重新绑定服务即可。
     */
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if(mComputerManager!=null){
                mComputerManager.asBinder().unlinkToDeath(mDeathRecipient,0);
                bindService();
            }
        }
    };
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(tag,"onServiceConnected");
            mInterface = IMyAidlInterface.Stub.asInterface(service);

            mComputerManager = IComputerManager.Stub.asInterface(service);
            try {
                mComputerManager.asBinder().linkToDeath(mDeathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //尝试重连：第一种方法：当系统杀死后台service的时候会调用。主动unbind不会调用
            bindService();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        bindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    private void bindService(){
        bindService(new Intent(this, MyAidlService.class),conn,BIND_AUTO_CREATE);
    }

    //以下是按钮的操作
    public void caculate(View view){
        try {
            int i = mInterface.add(2,3);
            Toast.makeText(this,"计算结果："+i,Toast.LENGTH_LONG).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void computer(View view){
        try {
            mComputerManager.addComputer(new ComputerEntity(0, "abc", "yes"));
            List<ComputerEntity> datas = mComputerManager.getComputerList();
            for(ComputerEntity entity:datas){
                System.out.println(entity);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void observal(View view){
        try {
            mComputerManager.registerUser(new IOnComputerArrivedListener.Stub() {
                @Override
                public void onComputerArrived(ComputerEntity computer) throws RemoteException {
                    System.out.println(computer);
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void isOnline(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        Thread thread = new Thread();
        thread.start();

        ExecutorService service = Executors.newFixedThreadPool(3);

        LruCache cache = new LruCache(30){
            @Override
            protected int sizeOf(Object key, Object value) {
                return super.sizeOf(key, value);
            }
        };
    }
}
