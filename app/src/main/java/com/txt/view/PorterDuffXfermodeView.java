package com.txt.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.txt.mydemo.R;

/**
 * Created by tongxiaotao on 19-2-26.
 */
public class PorterDuffXfermodeView extends View {
    private static final int WAVE_TRANS_SPEED = 4;

    private Paint mBitmapPaint, mPicPaint;
    private int mTotalWidth, mTotalHeight;
    private int mCenterX, mCenterY;
    private int mSpeed;

    private Bitmap mSrcBitmap;
    private Rect mSrcRect, mDestRect;

    private PorterDuffXfermode mPorterDuffXfermode;
    private Bitmap mMaskBitmap;
    private Rect mMaskSrcRect, mMaskDestRect;
    private PaintFlagsDrawFilter mDrawFilter;

    private int mCurrentPosition;

    public PorterDuffXfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initBitmap();
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mSpeed = WAVE_TRANS_SPEED;
        mDrawFilter = new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG, Paint.DITHER_FLAG);
        new Thread() {
            public void run() {
                while (true) {
                    // 不断改变绘制的波浪的位置
                    mCurrentPosition += mSpeed;
                    if (mCurrentPosition >= mSrcBitmap.getWidth()) {
                        mCurrentPosition = 0;
                    }
                    try {
                        // 为了保证效果的同时，尽可能将cpu空出来，供其他部分使用
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                    }

                    postInvalidate();
                }

            };
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 从canvas层面去除锯齿
        canvas.setDrawFilter(mDrawFilter);
        canvas.drawColor(Color.TRANSPARENT);

        /*
         * 将绘制操作保存到新的图层
         */
        int sc = canvas.saveLayer(0, 0, mTotalWidth, mTotalHeight, null, Canvas.ALL_SAVE_FLAG);

        // 设定要绘制的波纹部分
        mSrcRect.set(mCurrentPosition, 0, mCurrentPosition + mCenterX, mTotalHeight);
        // 绘制波纹部分
        canvas.drawBitmap(mSrcBitmap, mSrcRect, mDestRect, mBitmapPaint);

        // 设置图像的混合模式
        mBitmapPaint.setXfermode(mPorterDuffXfermode);
        // 绘制遮罩圆
        canvas.drawBitmap(mMaskBitmap, mMaskSrcRect, mMaskDestRect,
                mBitmapPaint);
        mBitmapPaint.setXfermode(null);
        canvas.restoreToCount(sc);
    }

    // 初始化bitmap
    private void initBitmap() {
        mSrcBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.mz_bottom_ic_home_nor_light))
                .getBitmap();
        mMaskBitmap = ((BitmapDrawable) getResources().getDrawable(
                R.drawable.test))
                .getBitmap();
    }

    // 初始化画笔paint
    private void initPaint() {

        mBitmapPaint = new Paint();
        // 防抖动
        mBitmapPaint.setDither(true);
        // 开启图像过滤
        mBitmapPaint.setFilterBitmap(true);

        mPicPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPicPaint.setDither(true);
        mPicPaint.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mCenterX = mTotalWidth / 2;
        mCenterY = mTotalHeight / 2;

        mSrcRect = new Rect();
        mDestRect = new Rect(0, 0, mTotalWidth, mTotalHeight);

        int maskWidth = mMaskBitmap.getWidth();
        int maskHeight = mMaskBitmap.getHeight();
        mMaskSrcRect = new Rect(0, 0, maskWidth, maskHeight);
        mMaskDestRect = new Rect(0, 0, mTotalWidth, mTotalHeight);
    }
}
