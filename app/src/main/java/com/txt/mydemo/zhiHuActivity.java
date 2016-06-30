package com.txt.mydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.txt.view.MyScrollView;

/**
 * Created by txt on 2015/12/28.
 */
public class zhiHuActivity extends AppCompatActivity implements MyScrollView.OnScrollListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu);
    }


    @Override
    public void onScroll(int scrollY) {

    }
}
