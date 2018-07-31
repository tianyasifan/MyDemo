package com.txt.mydemo;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.txt.view.DrawView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        final ImageView imageView = (ImageView) findViewById(R.id.img);

        imageView.setImageResource(R.drawable.test);





        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int dwidth = imageView.getDrawable().getIntrinsicWidth();
                int dheight = imageView.getDrawable().getIntrinsicHeight();
                int vwidth = imageView.getWidth();
                int vheight = imageView.getHeight();
                float scale;
                if (dwidth * vheight > vwidth * dheight) {
                    scale = (float) vheight / (float) dheight;
                } else {
                    scale = (float) vwidth / (float) dwidth;
                }
                Log.i("image", " width:" + imageView.getWidth() + ", height:" + imageView.getHeight() + ",scale:" + scale + ", dwidth:" + dwidth + ", dheight:" + dheight);
                Matrix matrix = new Matrix();
                matrix.setScale(scale,scale);
                imageView.setScaleType(ImageView.ScaleType.MATRIX);
                imageView.setImageMatrix(matrix);
            }
        });

    }
}
