package com.txt.mydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.txt.view.ScrollView;

public class ScrollViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_view);

        findViewById(R.id.scrollto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo(50,50);
            }
        });

        findViewById(R.id.scrollby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollBy(30, 30);
            }
        });

        findViewById(R.id.scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.startScroll();
            }
        });
    }
}
