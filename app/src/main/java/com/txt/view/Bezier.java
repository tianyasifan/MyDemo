package com.txt.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tongxt on 2017/7/14.
 * 贝塞尔曲线
 */

public class Bezier extends View {
    private String tag = Bezier.class.getSimpleName();
    private PointF start,end,control;
    private Paint mPaint;
    private int centerX,centerY;
    private Path mPath;

    public Bezier(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Bezier(Context context) {
        super(context);
        init();
    }

    public void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control = new PointF(0, 0);
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(tag, "onSizeChanged: w=" + w + ",h=" + h);
        centerX = w/2;
        centerY = h/2;

        //初始化三个点的坐标
        start.x = centerX - centerX/2;
        start.y = centerY;
        end.x = centerX + centerX/2;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY/2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制起始点和控制点
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y,mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control.x, control.y, mPaint);

        //绘制辅助线
        mPaint.setStrokeWidth(10);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(control.x,control.y,end.x,end.y,mPaint);

        //绘制二阶贝塞尔曲线（三阶贝塞尔就是多出一个控制点来，调用的是path.cubicTo方法
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(5);
//        Path mPath = new Path();
        mPath.reset();//重置路径，否则之前的路径也会被绘制出来
        mPath.moveTo(start.x,start.y);
        mPath.quadTo(control.x, control.y, end.x, end.y);
        canvas.drawPath(mPath,mPaint);
    }
}
