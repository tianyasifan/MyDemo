package com.txt.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by txt on 2016/1/12.
 */
public class ScrollNumberTextView extends TextView {
    private String tag = getClass().getSimpleName();
    private int mViewHeight;
    private int mViewWidth;
    private int textSize;
    private Paint mPaint;
    private String[] nums = {"0","1","2","3","4","5","6","7","8","9","."};

    public ScrollNumberTextView(Context context) {
        super(context);
        init();
    }

    public ScrollNumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollNumberTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setClickable(true);
        textSize = (int) getTextSize();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(textSize);
        mPaint.setColor(Color.YELLOW);
        Log.i(tag,"textSize:"+textSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewHeight = getMeasuredHeight();
        mViewWidth = getMeasuredWidth();
        Log.i(tag,"mViewHeigt:"+mViewHeight+ "height:"+getHeight());
    }

    private int currentIndex=1;//当前数字索引
    private int prevIndex = 0;
    private int nextIndex = 2;
    private int muti=1;//移动的数字高度倍数，往上滑加，往下滑减

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<20;i++){
            canvas.drawText(nums[i%10],0,textSize*(i+1)+detaY,mPaint);
        }

    }

    private int startY = 0;
    private int detaY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                muti = 1;
                startY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                detaY = startY - (int)event.getY();
                scrollBy(0,detaY/10);
//                postInvalidate();
                break;
        }
        Log.i(tag,"onTouchEvent: starty="+startY + "   detaY="+detaY);
        return super.onTouchEvent(event);
    }

    public void setTextTo(String totext){
        getText();
    }
}
