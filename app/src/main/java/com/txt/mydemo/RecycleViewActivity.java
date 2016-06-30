package com.txt.mydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.txt.adapter.RecycleViewAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by txt on 2016/5/27.
 */
public class RecycleViewActivity extends AppCompatActivity {
    @InjectView(R.id.rv)
    RecyclerView mRecyclerView;
    private String[] mtitles = {"1","2","3","4","5"};
    private int[] mpics = {R.drawable.anhui,R.drawable.hubei,R.drawable.jiangxi,R.drawable.dongfang,R.drawable.shenzhen};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        ButterKnife.inject(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new RecycleViewAdapter(this,mtitles,mpics));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
