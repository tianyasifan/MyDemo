package com.txt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * Created by txt on 2015/12/28.
 */
public class SlideCutListView extends ListView {
    private static final int SNAP_VELOCITY = 600;
    private int screenWidth;
    private Scroller mScroller;
    private int mTouchSlop;//触发滑动事件的最短距离，取系统的默认值
    private int downX,downY;
    private int slidePosition;
    private View itemView;
    private boolean isSlide;//判断是否滑动
    private VelocityTracker mVelocityTracker;

    public SlideCutListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        screenWidth = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public SlideCutListView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SlideCutListView(Context context) {
        this(context, null, 0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                slidePosition = pointToPosition(downX,downY);
                if(slidePosition == AdapterView.INVALID_POSITION){//无效的
                    return super.dispatchTouchEvent(ev);
                }
                itemView = getChildAt(slidePosition-getFirstVisiblePosition());//计算选中的item在当前屏幕中的位置
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(getScrollVelocity()) > SNAP_VELOCITY
                        || (Math.abs(ev.getX() - downX) > mTouchSlop && Math
                        .abs(ev.getY() - downY) < mTouchSlop)) {
                    isSlide = true;

                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void addVelocityTracker(MotionEvent event){
        if(mVelocityTracker==null){
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void releaseVelocityTracker(MotionEvent event){
        if(mVelocityTracker!=null){
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker=null;
        }
    }

    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        return (int)mVelocityTracker.getXVelocity();
    }
}
