package com.txt.mydemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.SparseIntArray;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.txt.adapter.ViewPagerGalleryAdapter;
import com.txt.animation.Rotate3dAnimation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by txt on 2016/5/6.
 */
public class ViewPagerGalleryActivity extends FragmentActivity implements View.OnClickListener{
    private ViewPager mViewPager;
    private ImageView mImageView;
    private SparseIntArray datas;
    private boolean clickFlag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mViewPager = (ViewPager) findViewById(R.id.gallery);
        mImageView = (ImageView)findViewById(R.id.iv_page);
        mImageView.setOnClickListener(this);

        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setPageMargin(10);
        init();
        mViewPager.setAdapter(new ViewPagerGalleryAdapter(this,datas));
        mViewPager.setCurrentItem(datas.size()/2);
    }

    public void init(){
        datas = new SparseIntArray();
        for(int i=0;i<10;i++){
            datas.append(i,i);
        }

    }

    @Override
    public void onClick(View v) {

        if(clickFlag) {
            float centerX = v.getWidth() / 2;
            float centerY = v.getHeight() / 2;
            Rotate3dAnimation animation = new Rotate3dAnimation(0f, 45f, centerX, centerY, 310f, true);
            animation.setDuration(500);
            animation.setFillAfter(true);
            animation.setInterpolator(new AccelerateInterpolator());
            mImageView.startAnimation(animation);
            clickFlag = false;
        }else{
            float centerX = v.getWidth() / 2;
            float centerY = v.getHeight() / 2;
            Rotate3dAnimation animation = new Rotate3dAnimation(45f, 0f, centerX, centerY, 310f, false);
            animation.setDuration(500);
            animation.setFillAfter(true);
            animation.setInterpolator(new AccelerateInterpolator());
            mImageView.startAnimation(animation);
            clickFlag = true;
        }
    }
}
