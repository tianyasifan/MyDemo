package com.txt.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tongxt on 2017/7/4.
 */

public class AnimationView extends View {
    private RectF mRect;
    private Paint mPaint;
    private Paint okPaint;
    private Path okPath;
    private ValueAnimator mAnimatorRectAngle;
    private int height=100,width=300,top = 100,left = 100,right;
    private float cycleAngle=0;
    private int widthDistance;//两边圆角之间的距离，其实就是动态改变mRect的矩形的左右距离大小，让其慢慢向中间靠拢
    private boolean isDrawOk = false;
    private float pathLenth;//l路径的长度
    private float intervals[];

    public AnimationView(Context context) {
        super(context);
        Log.i("AnimationView","construct");
        mRect = new RectF(left, top, width+left, height+top);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        right = width + left;


        initOk();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRectAngle(canvas,cycleAngle);
        if(isDrawOk){
            drawOk(canvas);
        }
    }

    public void drawRectAngle(Canvas canvas, float angle){
        mRect.left = left + widthDistance;
        mRect.right = right - widthDistance;
        canvas.drawRoundRect(mRect,angle,angle,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("AnimationView", "onTouchEvent");
        if(MotionEvent.ACTION_DOWN == event.getAction()){
            setRectToAngleAnimator();//先把矩形的四个角从直角慢慢变成圆角
//            setRectToCycleAnimator();//再把矩形从两边慢慢向中间靠拢
            return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 从直角矩形到圆角矩形的动画
     */
    public void setRectToAngleAnimator(){
        mAnimatorRectAngle = ValueAnimator.ofFloat(0,height/2);
        mAnimatorRectAngle.setDuration(5000);
        mAnimatorRectAngle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                cycleAngle = (float) animation.getAnimatedValue();//获取矩形角度值
                invalidate();
            }
        });
        mAnimatorRectAngle.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setRectToCycleAnimator();//完成后调用收拢成圆的动画
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimatorRectAngle.start();
    }

    /**
     * 从圆角矩形向内收拢变成圆的动画
     */
    public void setRectToCycleAnimator(){
        ValueAnimator animator = ValueAnimator.ofInt(0,(width-height)/2);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                widthDistance = (int) animation.getAnimatedValue();//改变矩形的左右距离，慢慢向中间靠拢
                invalidate();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                isDrawOk = true;
                setHookAnimator();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    /**
     * 在圆上打对钩的动画
     */
    public void setHookAnimator(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1,0);
        valueAnimator.setDuration(4000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float phase = (float) animation.getAnimatedValue();
                okPaint.setPathEffect(new DashPathEffect(intervals,pathLenth*phase));
                isDrawOk = true;
                invalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 初始化对钩path路径
     */
    public void initOk(){
        okPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        okPaint.setColor(Color.RED);
        okPaint.setStyle(Paint.Style.STROKE);
        okPaint.setStrokeWidth(3.0f);

        okPath = new Path();
        okPath.moveTo(20,30);//设置路径的起点
        okPath.lineTo(30,70);
        okPath.lineTo(60,10);
        PathMeasure measure = new PathMeasure(okPath,false);//用该path构造一个measure对象
        pathLenth = measure.getLength();//获得okPath路径的长度
        intervals = new float[]{pathLenth,pathLenth};
    }

    public void drawOk(Canvas canvas){
        canvas.drawPath(okPath, okPaint);
    }
}
