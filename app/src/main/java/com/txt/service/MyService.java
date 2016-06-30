package com.txt.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Stack;

/**
 * Created by txt on 2016/5/13.
 */
public class MyService extends Service {
    private String tag = MyService.class.getSimpleName();
    private MyBinder mBinder;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(tag,"onCreate");
        Toast.makeText(this,"abc",Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(tag,"onBind");
        if(mBinder==null)
            mBinder = new MyBinder();
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(tag,"onStartCommand");
        showDialog();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(tag,"onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(tag,"onUnBind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(tag,"onDestroy");
        super.onDestroy();
    }

    class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    /**
     * service中显示dialog，必须设置为系统对话框
     * setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
     * 并且在manifest中添加权限
     *     <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
     */
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("abc");
        AlertDialog dialog = builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();

    }
}
