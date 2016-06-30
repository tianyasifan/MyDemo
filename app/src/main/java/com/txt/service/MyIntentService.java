package com.txt.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by txt on 2016/1/11.
 */
public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("myIntentService","onHandleIntent");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("myIntentService","滞后2s---->"+intent.getStringExtra("abc"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myIntentService","onDestroy");

    }
}
