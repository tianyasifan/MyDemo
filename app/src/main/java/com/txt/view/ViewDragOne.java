package com.txt.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;

/**
 * Created by txt on 2015/12/15.
 */
public class ViewDragOne extends LinearLayout {
    private String tag="DragLayout";
    private View viewOne,viewTwo;
    private ViewDragHelper dragHelper;

    public ViewDragOne(Context context) {
        this(context,null);
    }

    public ViewDragOne(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ViewDragOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        dragHelper = ViewDragHelper.create(this,1.0f,new DragHelperCallback());
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        /**
         * 进行捕获拦截，那些View可以进行drag操作
         * @param child
         * @param pointerId
         * @return  直接返回true，拦截所有的VIEW
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.d("DragLayout", "clampViewPositionHorizontal: " + left + "," + dx);
            int leftBound = getPaddingLeft();
            int rightBound = getWidth()-viewOne.getWidth();
            int newLeft = Math.min(Math.max(left,leftBound),rightBound);
            return newLeft;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.d("DragLayout", "clampViewPositionVertical: " + top + "," + dy);
            int topBound = getPaddingTop();
            int botomBound = getHeight()-viewOne.getHeight();
            int newTop = Math.min(Math.max(top,topBound),botomBound);
            return newTop;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    /**
     * 当View中所有的子控件均被映射成xml后触发
     * 即执行完inflate后触发
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        viewOne = getChildAt(0);
        viewTwo = getChildAt(1);
    }
}
