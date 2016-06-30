package com.txt.mydemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.txt.service.MyMessengerService;

/**
 * Created by txt on 2016/5/18.
 */
public class MessengerActivity extends Activity {
    private String tag = MessengerActivity.class.getSimpleName();
    private Messenger mClientMessenger;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(tag,"onServiceConnected");
            mClientMessenger = new Messenger(service);//跟服务器端的messenger对象关联上了
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private MyClientHandler mClientHandler = new MyClientHandler();

    class MyClientHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //处理接收到服务端的信息
            Toast.makeText(MessengerActivity.this, msg.getData().getString("key"), Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        bindService(new Intent(this, MyMessengerService.class), conn, BIND_AUTO_CREATE);
    }

    public void caculateABC(View view){
        Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("key","abc");
        msg.setData(bundle);
        //通过Message的replyTo属性将Messenger对象传递到服务端，这个messenger对象关联了一个handler，以便能处理服务器回传的信息
        msg.replyTo = new Messenger(mClientHandler);
        try {
            mClientMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
