package com.txt.mydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.txt.view.MyViewPager;

/**
 * Created by txt on 2016/5/25.
 */
public class MyViewPagerActivity extends AppCompatActivity {
    private MyViewPager mPager;
    private int[] res = {R.drawable.anhui,R.drawable.hubei,R.drawable.jiangxi};
    private int[] color = {R.color.color1,R.color.color2,R.color.color3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myviewpager);
        mPager = (MyViewPager) findViewById(R.id.my_view_pager);

        for(int i=0;i<res.length;i++){
            TextView textView = new TextView(this);
            textView.setBackgroundColor(color[i]);
            textView.setText(""+i);
            textView.setTextSize(50f);
            mPager.addView(textView);
        }
    }
}
