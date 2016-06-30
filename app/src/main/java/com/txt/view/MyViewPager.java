package com.txt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by txt on 2016/5/25.
 */
public class MyViewPager extends ViewGroup implements GestureDetector.OnGestureListener{
    private GestureDetector mGestureDetector;
    private String tag = MyViewPager.class.getSimpleName();

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context,this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for(int i=0;i<getChildCount();i++){//遍历所有的子view，确定子view的在该group中的位置
            View view = getChildAt(i);
            view.layout(i*getWidth(),0,getWidth()*(i+1),getHeight());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(tag, "onTouchEvent");
        mGestureDetector.onTouchEvent(event);//把事件传入手势检测类中，否则无法检测
        return true;//必须消费掉事件，否则手势监测不起作用
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i(tag, "dx:" + distanceX + "   dy:" + distanceY);
        scrollBy((int)distanceX,0);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
