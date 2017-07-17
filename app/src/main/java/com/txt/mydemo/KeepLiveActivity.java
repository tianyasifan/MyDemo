package com.txt.mydemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

public class KeepLiveActivity extends AppCompatActivity {

    private String tag = KeepLiveActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tag,"onCreate");
        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.width = 1;
        params.height = 1;
        window.setAttributes(params);
        KeepManager.getInstance().setActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tag, "onDestroy");
    }

    static class KeepManager{
        private static KeepManager instance;
        private WeakReference activity;
        private KeepManager(){};

        public static KeepManager getInstance(){
            if(instance == null ){
                synchronized (KeepManager.class){
                    if(instance == null){
                        instance = new KeepManager();
                    }
                }
            }
            return instance;
        }

        public void setActivity(Activity activity){
            this.activity = new WeakReference<Activity>(activity);
        }

        public void startKeepLiveActivity(Context context){
            Intent intent = new Intent(context,KeepLiveActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        public void finishKeepLiveActivity(){
            if(activity != null){
                Activity act = (Activity) activity.get();
                if(act != null){
                    act.finish();
                }
            }
        }
    }

}
