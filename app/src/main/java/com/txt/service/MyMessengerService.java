package com.txt.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by txt on 2016/5/18.
 */
public class MyMessengerService extends Service {
    private Messenger mMessenger = new Messenger(new MyMessengerHandler());
    private Messenger mClientMessenger;//客户端的信使

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();//这样客户端就拿到了一个messenger对象，这个对象持有handler的实例
    }


    class MyMessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String s = bundle.getString("key");
            mClientMessenger = msg.replyTo;//把客户端传递过来的信使赋值

            Bundle bundle1 = new Bundle();
            Message message = Message.obtain();
            bundle1.putString("key",s.toUpperCase());//处理数据
            message.setData(bundle1);
            try {
                mClientMessenger.send(message);//通过客户端的信使，把转换后的数据回传给客户端
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
