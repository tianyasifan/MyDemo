package com.txt.mydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.txt.view.AnimationView;
import com.txt.view.ClockView;

/**
 * Created by txt on 2016/5/31.
 */
public class CustomViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new ClockView(this));//时钟效果
        setContentView(new AnimationView(this));//动画实现从矩形变成圆形再打勾
    }
}
