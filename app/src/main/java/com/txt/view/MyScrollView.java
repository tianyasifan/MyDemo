package com.txt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by txt on 2015/12/28.
 */
public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mListener!=null){
            mListener.onScroll(t);
        }
    }

    public interface OnScrollListener{
        void onScroll(int scrollY);
    }

    private OnScrollListener mListener;
    public void setOnScrollListener(OnScrollListener listener){
        mListener = listener;
    }
}
