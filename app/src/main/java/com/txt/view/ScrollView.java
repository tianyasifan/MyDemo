package com.txt.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by tongxiaotao on 19-1-4.
 */
public class ScrollView extends View {
    Scroller mScroller;
    Paint mPaint;

    public ScrollView(Context context) {
        super(context);
        init();
    }

    public ScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mScroller = new Scroller(getContext());
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollBy(mScroller.getCurrX(), mScroller.getCurrY());
//            invalidate();
            Log.i("scroll", "x: " + mScroller.getCurrX());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2, getWidth()/2, getWidth()/4, mPaint);
    }

    public void startScroll(){
        mScroller.startScroll(10,0, 10,0);
        invalidate();
    }
}
