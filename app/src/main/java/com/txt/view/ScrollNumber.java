package com.txt.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tongxt on 2017/7/11.
 */

public class ScrollNumber extends View {
    private Paint mPaint;
    private float textSize = 80;
    private float deta = textSize / 100;
    private int currentNum = 5;
    private int height = (int)textSize;
    private Handler mHandler;
    private int i = 0;
    public ScrollNumber(Context context) {
        super(context);
        init();
    }

    public ScrollNumber(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,height);
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(textSize);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                invalidate();

                i++;
            }
        };
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(""+(currentNum-1),0,0-i*deta,mPaint);
        canvas.drawText(""+currentNum,0,textSize-i*deta,mPaint);
        canvas.drawText(""+(currentNum + 1),0,textSize*2-i*deta,mPaint);
        if(i<10000) {
            mHandler.sendMessageDelayed(mHandler.obtainMessage(), 10);
        }
    }
}
