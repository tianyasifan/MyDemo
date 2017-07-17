package com.tongxt.aidlservicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.txt.mydemo.R;

public class AidlActivity extends AppCompatActivity {
    private String tag = AidlActivity.class.getSimpleName();
    private BookManager mBookManager;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(tag, "onServiceConnected");
            mBookManager = BookManager.Stub.asInterface(service);
            Toast.makeText(AidlActivity.this, "建立连接成功", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void addBook(View view){
        Book book = new Book("wo","3.2","txt");
        try {
            mBookManager.addBook(book);
            int count = mBookManager.getBookCount();
            Toast.makeText(AidlActivity.this,"总共添加了 " + count + " 本书", Toast.LENGTH_LONG).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void bindService(View view){
        Log.i(tag, "bindService");
        Intent intent = new Intent();
        intent.setPackage("com.tongxt.aidlservicedemo");
        intent.setAction("tongxt.aidl.demo");
        bindService(intent,mConnection,BIND_AUTO_CREATE);
    }

    public void startService(View view){
        startService(new Intent("tongxt.aidl.demo"));
    }
}
