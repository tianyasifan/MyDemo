package com.txt.mydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

/**
 * Created by tongxt on 2017/4/20.
 */

public class LifecycleActivity extends Activity{
    String tag = LifecycleActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.i(tag, "onCreate sdk version: " + getApplicationInfo().targetSdkVersion);

    }

    public void startNormalAty(View view){
        startActivity(new Intent(this, LifecycleTargetActivity1.class));
    }

    public void startTransparentAty(View view){
        startActivity(new Intent(this, LifecycleTargetActivity2.class));
    }

    public void startDialogAty(View view){
        startActivity(new Intent(this, LifecycleTargetActivity3.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tag, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(tag, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(tag, "onResume");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i(tag, "onWindowFocusChanged");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(tag, "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(tag, "onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(tag, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(tag, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tag, "onDestory");
    }
}
