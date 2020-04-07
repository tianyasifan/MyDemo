package com.txt.gray;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.txt.mydemo.R;

public class GrayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gray);
        
        //设置灰化
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE,paint);
        
        //设置水印
        Watermark.getInstance().show(this,"tongxt");
    }
}
