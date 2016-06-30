package com.txt.mydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.txt.view.CicleView;

/**
 * Created by txt on 2016/5/31.
 */
public class CustomViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CicleView(this));
    }
}
