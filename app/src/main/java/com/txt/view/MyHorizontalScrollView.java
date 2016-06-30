package com.txt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.txt.adapter.HorizontalScrollViewAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by txt on 2016/4/26.
 */
public class MyHorizontalScrollView extends HorizontalScrollView {

    private int mScreenWidth = 700;
    private LinearLayout mContainer;
    private HorizontalScrollViewAdapter mAdapter;
    private int mChildWidth;
    private int mChildHeight;
    private String TAG = MyHorizontalScrollView.class.getSimpleName();
    private int mCountOneScreen;
    private Map<View,Integer> mViewPos = new HashMap<>();
    private int mCurrentIndex;

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager vm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        DisplayMetrics outmetrics = new DisplayMetrics();
        vm.getDefaultDisplay().getMetrics(outmetrics);
        mScreenWidth = outmetrics.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContainer = (LinearLayout)getChildAt(0);
    }

    public void initDatas(HorizontalScrollViewAdapter adapter){
        mAdapter = adapter;
        mContainer = (LinearLayout)getChildAt(0);
        //获得适配器中的第一个view；
        View view = mAdapter.getView(0,null,mContainer);
        mContainer.addView(view);
        // 强制计算当前View的宽和高  
        if (mChildWidth == 0 && mChildHeight == 0)
        {
            int w = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            view.measure(w, h);
            mChildHeight = view.getMeasuredHeight();
            mChildWidth = view.getMeasuredWidth();
            Log.e(TAG, view.getMeasuredWidth() + "," + view.getMeasuredHeight());
            mChildHeight = view.getMeasuredHeight();
            // 计算每次加载多少个View  
            mCountOneScreen = mScreenWidth / mChildWidth+2;

            Log.e(TAG, "mCountOneScreen = " + mCountOneScreen
                    + " ,mChildWidth = " + mChildWidth);
        }
        initFirstScreenChildren();
    }

    private void initFirstScreenChildren() {
        mContainer.removeAllViews();
        mViewPos.clear();
        for(int i=0;i<mCountOneScreen;i++){
            View view = mAdapter.getView(i,null,mContainer);
            mContainer.addView(view);
            mViewPos.put(view,i);
            mCurrentIndex = i;
        }
    }
}
