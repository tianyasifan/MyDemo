package com.txt.mydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.txt.service.MyIntentService;

public class IntentServiceTestActivity extends AppCompatActivity {

    private String tag = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service_test);
    }

    public void startService(View view){
        Intent intent = new Intent(this, MyIntentService.class);
        Log.e(tag, "startService");
        startService(intent);
    }

}
