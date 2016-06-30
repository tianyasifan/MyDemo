package com.txt.wxmvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.txt.mydemo.R;

/**
 * Created by txt on 2016/5/4.
 */
public class WXActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxmvp);
    }
}
