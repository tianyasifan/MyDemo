package com.txt.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tongxt on 2017/6/4.
 */

public class DrawView extends View {
    private Paint mPaint;
    private List<Point> mPoints;
    public DrawView(Context context) {
        super(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
        setBackgroundColor(Color.WHITE);
        mPoints = new ArrayList<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mPoints.add(new Point((int)event.getX(),(int)event.getY()));
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(Point point : mPoints){
            canvas.drawCircle(point.x,point.y,50,mPaint);
        }
    }
}
