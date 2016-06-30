package com.txt.mydemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.txt.service.MyService;

import butterknife.ButterKnife;


/**
 * Created by txt on 2016/5/13.
 */
public class Service1Activity extends Activity implements View.OnClickListener {

    private String tag = Service1Activity.class.getSimpleName();
    Button startAct,startService,bind,unbind,stopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service1);
        ButterKnife.inject(this);
        startAct = (Button) findViewById(R.id.btn_start_activity);
        startService = (Button) findViewById(R.id.btn_start_service);
        bind = (Button) findViewById(R.id.btn_bind);
        unbind = (Button) findViewById(R.id.btn_unbind);
        stopService = (Button) findViewById(R.id.btn_stop_service);

        startAct.setOnClickListener(this);
        startService.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
        stopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_activity:
                startActivity(new Intent(this,Service2Activity.class));
                break;
            case R.id.btn_start_service:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.btn_bind:
                bindService(new Intent(this, MyService.class),conn,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                unbindService(conn);
                break;
            case R.id.btn_stop_service:
                stopService(new Intent(this, MyService.class));
                break;
        }
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(tag,"onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(tag,"onServiceDisconnected");
        }
    };
}
