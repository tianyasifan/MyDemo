package com.txt.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.meizu.common.renderer.drawable.GLBlurBitmapDrawable;
import com.meizu.common.renderer.effect.GLRenderer;

import java.util.Arrays;

/**
 * Created by cuicui on 2017/8/15.
 */
public class StaticBlurEffect extends View {

    private static final String TAG = "GLBlendView";
    private GLBlurBitmapDrawable mGLBitmap;
    //private Bitmap mImage;

    private int mMaskColor;
    private int mBitmapColor;

    private boolean isBimtapSet;

    private Context mContext;

    public StaticBlurEffect(Context context) {
        this(context, null);
    }

    public StaticBlurEffect(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StaticBlurEffect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        GLRenderer.initialize(context);
        mGLBitmap = new GLBlurBitmapDrawable(null, false);

        setBackground(mGLBitmap);
        isBimtapSet = false;
    }

    public void setBitmap(Bitmap bitmap) {
        if (true) {
            //mImage = bitmap;
            mGLBitmap.setBitmap(bitmap);
            mGLBitmap.setBlurLevel(0.6f);

        //mBitmapColor = PrimaryColor.adjustBannerGradentBgColor();
            //mBitmapColor = PrimaryColor.generate(bitmap);
//        float[] hsv = rgb2hsb(Color.red(mBitmapColor), Color.green(mBitmapColor), Color.blue(mBitmapColor));
//        hsv[1] = 0.02f;
//        hsv[2] = 0.98f;
//        int[] rgb = hsb2rgb(hsv[0], hsv[1], hsv[2]);
//        mMaskColor = Color.argb(153, rgb[0], rgb[1], rgb[2]);

            //mBitmapColor = Color.parseColor("#ffff0000");
            mMaskColor = Color.argb(225, 255,255,255);
            //  a r g b 模式
            mGLBitmap.setColorFilter(mMaskColor);
            mGLBitmap.setScale(0.01f);
            mGLBitmap.setPassCount(3);
            isBimtapSet = true;
            invalidate();
            //Log.e("wangjiangchuan", "draw setbitmap !");
        }
    }

    public int getmMaskColor() {
        return mMaskColor;
    }

    public int getBitmaColor() {
        return mBitmapColor;
    }

    public void setCount(float count) {
        mGLBitmap.setPassCount((int) count);
    }

    public void setRadius(float radius) {
        mGLBitmap.setRadius((int) radius);
    }

    public void setScale(float scale) {
        mGLBitmap.setScale(scale);
    }

    public void myinvalidate() {
        invalidate();
    }

    public void setCoverColor(int color) {
        mGLBitmap.setColorFilter(color);
    }

    public static float[] rgb2hsb(int rgbR, int rgbG, int rgbB) {
        assert 0 <= rgbR && rgbR <= 255;
        assert 0 <= rgbG && rgbG <= 255;
        assert 0 <= rgbB && rgbB <= 255;
        int[] rgb = new int[]{rgbR, rgbG, rgbB};
        Arrays.sort(rgb);
        int max = rgb[2];
        int min = rgb[0];

        if (max == min)
            return new float[] {0, 0, 1};

        float hsbB = max / 255.0f;
        float hsbS = max == 0 ? 0 : (max - min) / (float) max;

        float hsbH = 0;
        if (max == rgbR && rgbG >= rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 0;
        } else if (max == rgbR && rgbG < rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 360;
        } else if (max == rgbG) {
            hsbH = (rgbB - rgbR) * 60f / (max - min) + 120;
        } else if (max == rgbB) {
            hsbH = (rgbR - rgbG) * 60f / (max - min) + 240;
        }

        return new float[]{hsbH, hsbS, hsbB};
    }

    public static int[] hsb2rgb(float h, float s, float v) {
        assert Float.compare(h, 0.0f) >= 0 && Float.compare(h, 360.0f) <= 0;
        assert Float.compare(s, 0.0f) >= 0 && Float.compare(s, 1.0f) <= 0;
        assert Float.compare(v, 0.0f) >= 0 && Float.compare(v, 1.0f) <= 0;

        float r = 0, g = 0, b = 0;
        int i = (int) ((h / 60) % 6);
        float f = (h / 60) - i;
        float p = v * (1 - s);
        float q = v * (1 - f * s);
        float t = v * (1 - (1 - f) * s);
        switch (i) {
            case 0:
                r = v;
                g = t;
                b = p;
                break;
            case 1:
                r = q;
                g = v;
                b = p;
                break;
            case 2:
                r = p;
                g = v;
                b = t;
                break;
            case 3:
                r = p;
                g = q;
                b = v;
                break;
            case 4:
                r = t;
                g = p;
                b = v;
                break;
            case 5:
                r = v;
                g = p;
                b = q;
                break;
            default:
                break;
        }
        return new int[]{(int) (r * 255.0), (int) (g * 255.0),
                (int) (b * 255.0)};
    }


}
