package com.txt.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class ForegroundService extends Service {
    public ForegroundService() {
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        Log.i("ForegroundService", "onStartCommand");
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        builder.setContentTitle("Foreground");
        builder.setContentText("我是一个前台服务");
        Notification notification = builder.build();
        startForeground(110,notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
