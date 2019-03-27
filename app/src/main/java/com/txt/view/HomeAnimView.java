package com.txt.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.txt.mydemo.R;

/**
 * Created by tongxiaotao on 19-2-27.
 */
public class HomeAnimView extends ImageView {
    private Paint mPaint;
    private Path mPath;
    private Rect mRect;
    private Bitmap src,dst;
    private int srcTop;
    private int width,height;
    private Xfermode mXfermode;
    private boolean isStartMix;
    public HomeAnimView(Context context) {
        super(context);
//        initGroup1();
//        initGroup2();
//        initGroup3();
        //以上三组实验都开启的时候，应该打开drawGroup1方法

        initGroup4();//开启这个实验的时候，onDraw中应该执行drawGroup2
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawGroup1(canvas);
        drawGroup2(canvas);
    }

    private void initGroup1(){
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint = new Paint();// 这个是用来绘制图层的画笔，包括设置Xfermode
        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.mz_bottom_ic_home_nor_light);
        width = drawable.getBitmap().getWidth();
        height = drawable.getBitmap().getHeight();
        setLayoutParams(new ViewGroup.LayoutParams(width,height*2));//设置当前view的高度是图像的两倍
        dst = drawable.getBitmap();//目标图像来源于资源文件

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//创建一个宽高一致的图像作为源图像，此时的图像还是一个没有任何颜色值的
        Canvas canvas = new Canvas(bitmap);//在图像上着色画图形，需要使用画布来操作
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//作画需要画笔嘛
        paint.setColor(Color.RED);// 设置画笔的颜色
        canvas.drawRect(0,0,width,height,paint);//在该画布上画一个和view的宽高一样的红色矩形块，这个矩形块填满了bitmap
        src = bitmap;//把这个红色矩形图像当做源图像，源图像的内容要作用到目标图像dst上

        srcTop = height;
        startAnim();
    }

    private void drawGroup1(Canvas canvas){
        int layerID = canvas.saveLayer(0,0,width,height*2,mPaint,Canvas.ALL_SAVE_FLAG); // 图像混合的标准步骤1

        canvas.drawBitmap(dst, 0, 0, mPaint); // 将目标图像设置到view的画布图层中

        if(isStartMix)
        mPaint.setXfermode(mXfermode); // 图像混合的标准步骤2：设置模式

        canvas.drawBitmap(src, 0, srcTop, mPaint); // 将源图像加到view的画布图层中，并根据模式进行运算

        mPaint.setXfermode(null);//图像混合的标准步骤3：清除模式，恢复图层
        canvas.restoreToCount(layerID);
    }



    private void initGroup2(){
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint = new Paint();// 这个是用来绘制图层的画笔，包括设置Xfermode
        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.home_solid1);
        width = drawable.getBitmap().getWidth();
        height = drawable.getBitmap().getHeight();
        setLayoutParams(new ViewGroup.LayoutParams(width,height*2));//设置当前view的高度是图像的两倍
        dst = drawable.getBitmap();//目标图像来源于资源文件

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//创建一个宽高一致的图像作为源图像，此时的图像还是一个没有任何颜色值的
        Canvas canvas = new Canvas(bitmap);//在图像上着色画图形，需要使用画布来操作
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//作画需要画笔嘛
        paint.setColor(Color.RED);// 设置画笔的颜色
        canvas.drawRect(0,0,width,height,paint);//在该画布上画一个和view的宽高一样的红色矩形块，这个矩形块填满了bitmap
        src = bitmap;//把这个红色矩形图像当做源图像，源图像的内容要作用到目标图像dst上

        srcTop = height;
        startAnim();
    }

    private void initGroup3(){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.mz_bottom_ic_home_nor_light);
        setImageDrawable(bitmapDrawable);//把最原始的透明房子设置给自己
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint = new Paint();// 这个是用来绘制图层的画笔，包括设置Xfermode
        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.home_solid);//取出实心小房子，宽高和原来的是一样的
        width = drawable.getBitmap().getWidth();
        height = drawable.getBitmap().getHeight();
        setLayoutParams(new ViewGroup.LayoutParams(width,height));//设置当前view的高度是图像的高度
        dst = drawable.getBitmap();//目标图像来源于资源文件

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//创建一个宽高一致的图像作为源图像，此时的图像还是一个没有任何颜色值的
        Canvas canvas = new Canvas(bitmap);//在图像上着色画图形，需要使用画布来操作
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//作画需要画笔嘛
        paint.setColor(Color.RED);// 设置画笔的颜色
        canvas.drawRect(0,0,width,height,paint);//在该画布上画一个和view的宽高一样的红色矩形块，这个矩形块填满了bitmap
        src = bitmap;//把这个红色矩形图像当做源图像，源图像的内容要作用到目标图像dst上

        srcTop = height;
        startAnim();
    }


    private Canvas srcCanvas;
    private Paint srcPaint;
    /**
     * 第四组实验，也是在源bitmap上绘制一个矩形图案，但是这个矩形图案的高度初始值为bitmap的height值，也就是在该bitmap的最底部。
     * 也就是说现在的bitmap上方是一个透明的区域，最下方是一个矩形图案。当透明的区域和目标图像进行合成的时候，就会将目标图像擦除，也变成了透明区域。
     * 接下来，在onDraw方法中，通过改变矩形图像的top值来动态移动矩形图像在源bitmap中的位置，也就是矩形图像慢慢的填充满整个源bitmap。
     * 而此次合并的过程中目标的bitmap和源bitmap是完全重合的。透明合成的区域被擦除，有矩形块的区域的颜色值就作用到目标图像上了。
     */
    private void initGroup4(){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.mz_bottom_ic_home_nor_light);
        setImageDrawable(bitmapDrawable);//把最原始的透明房子设置给自己
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint = new Paint();// 这个是用来绘制图层的画笔，包括设置Xfermode
        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.home_solid);//取出实心小房子，宽高和原来的是一样的
        width = drawable.getBitmap().getWidth();
        height = drawable.getBitmap().getHeight();
        setLayoutParams(new ViewGroup.LayoutParams(width,height));//设置当前view的高度是图像的高度
        dst = drawable.getBitmap();//目标图像来源于资源文件

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//创建一个宽高一致的图像作为源图像，此时的图像还是一个没有任何颜色值的
        //画布和画笔都定义成全局变量，因为在动画的过程中，需要用到这两个变量对矩形块进行绘制
        srcCanvas = new Canvas(bitmap);//在图像上着色画图形，需要使用画布来操作
        srcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//作画需要画笔嘛
        srcPaint.setColor(Color.RED);// 设置画笔的颜色

        //在这组实验中，主要是改变源图像的矩形块的高度
        srcTop = height;
        mRect = new Rect(0,srcTop, width, height); // 创建一个红色矩形块，这个矩形块顶部的距离初始就是目标图像的高度（即在底部）
        srcCanvas.drawRect(mRect,srcPaint);//把这个矩形块画在bitmap上，初始效果是在bitmap的最底部
        src = bitmap;//把这个红色矩形图像当做源图像，源图像的内容要作用到目标图像dst上

        startAnim();
    }

    private void drawGroup2(Canvas canvas){
        int layerID = canvas.saveLayer(0,0,width,height*2,mPaint,Canvas.ALL_SAVE_FLAG); // 图像混合的标准步骤1

        canvas.drawBitmap(dst, 0, 0, mPaint); // 将目标图像设置到view的画布图层中

        mPaint.setXfermode(mXfermode); // 图像混合的标准步骤2：设置模式

        mRect.top = srcTop; // 动态改变源图像中的矩形块的top值
        srcCanvas.drawRect(mRect, srcPaint);//在源画布上根据新的top距离绘制矩形块，绘制的矩形块会在src上表现出来
        canvas.drawBitmap(src, 0, 0, mPaint); // 将源图像加到view的画布图层中，并根据模式进行运算，（源图像的左边和顶部局域始终和目标图像重合）

        mPaint.setXfermode(null);//图像混合的标准步骤3：清除模式，恢复图层
        canvas.restoreToCount(layerID);
    }

    public void startAnim(){
        final ValueAnimator animator = ValueAnimator.ofInt(height,0);//动画执行的值从目标图像的高度开始，一直变化到0
        animator.setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                srcTop = (int)animation.getAnimatedValue();//不断的取得源图像在view中的canvas的位置
                postInvalidate();
            }
        });
        postDelayed(new Runnable() {
            @Override
            public void run() {
                isStartMix = true; // 标记开始混合图像
                animator.start();
            }
        },3000);

    }
}
