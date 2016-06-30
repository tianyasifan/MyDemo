package com.txt.handlerthread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.telephony.SmsMessage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by txt on 2016/6/22.
 */
public class MyHandlerThreadText {

    MyHandler mHandler;
    Thread mThread = new Thread(){
        @Override
        public void run() {
            Looper.prepare();
            mHandler = new MyHandler();
            Looper.loop();
        }
    };

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }


}
