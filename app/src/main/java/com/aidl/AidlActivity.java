package com.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.txt.mydemo.R;

/**
 * Created by tongxiaotao on 19-4-11.
 */
public class AidlActivity extends Activity {
    private String TAG = this.getClass().getSimpleName();
    private IDemandManager manager;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            manager = IDemandManager.Stub.asInterface(service); // 拿到服务器的接口对象就可以调用具体的方法了
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl3);
        bindService(new Intent(this,AidlService.class), connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    public void getDemand(View view){
        try {
            MessageBean bean = manager.getDemand(); // 执行接口方法的时候，调用者所在线程会被挂起，一旦这个方法执行时间过长，且我们在主线程中调用的话，就会产生anr
            Log.i(TAG, "get: " + bean.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setDemandIn(View view){
        MessageBean bean = new MessageBean("我从activity来In",10);
        try {
            manager.setDemandIn(bean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setDemandOut(View view){
        MessageBean bean = new MessageBean("我从activity来Out",12);
        try {
            manager.setDemandOut(bean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setDemandInOut(View view){
        MessageBean bean = new MessageBean("我从activity来InOut",13);
        try {
            manager.setDemandInOut(bean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
