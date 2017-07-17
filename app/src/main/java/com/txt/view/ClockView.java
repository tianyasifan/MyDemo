package com.txt.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by txt on 2016/5/31.
 */
public class ClockView extends View {
    private Paint ciclePaint;
    private Paint degreePaint;
    private int mWidth,mHeight;
    private int radius;
    private int padding = 20;
    private Path textPath;

    public ClockView(Context context) {
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth();
        mHeight = wm.getDefaultDisplay().getHeight();
        radius = Math.min(mWidth,mHeight) / 2;
        Log.i("tag", "" + radius);
        ciclePaint = new Paint();
        ciclePaint.setStyle(Paint.Style.STROKE);
        ciclePaint.setAntiAlias(true);
        ciclePaint.setStrokeWidth(5);
        degreePaint = new Paint();
        textPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("tag", "onDraw");

        canvas.drawCircle(mWidth/2,mHeight/2,radius-padding, ciclePaint);
        for(int i=0;i<12;i++){
            if(i == 0 || i == 3 || i == 6 || i == 9){
                degreePaint.setStrokeWidth(5);
                degreePaint.setTextSize(30);
                canvas.drawLine(mWidth/2,mHeight/2-radius,mWidth/2,mHeight/2-radius+60,degreePaint);
                String text = String.valueOf(i);

                canvas.drawText(text,mWidth/2-degreePaint.measureText(text)/2,mHeight/2-radius+90,degreePaint);

            }else{
                degreePaint.setStrokeWidth(3);
                degreePaint.setTextSize(15);
                canvas.drawLine(mWidth/2,mHeight/2-radius,mWidth/2,mHeight/2-radius+30,degreePaint);
                String text = String.valueOf(i);

                canvas.drawText(text,mWidth/2-degreePaint.measureText(text)/2,mHeight/2-radius+60,degreePaint);

            }
            //旋转画布来简化坐标运算
            canvas.rotate(30,mWidth/2,mHeight/2);
        }
    }
}
