package com.txt.mydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class FontSizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_size);

        TextView dtv = (TextView) findViewById(R.id.display);
        DisplayMetrics display = getResources().getDisplayMetrics();
        dtv.setText("dpi:" + display.densityDpi + ", density:" + display.density + ", scale:" + display.scaledDensity
        + "\n height:" + display.heightPixels + ", width:" + display.widthPixels);
    }
}
