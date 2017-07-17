package com.txt.scrollnumber;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.txt.mydemo.R;
import com.txt.view.ScrollNumber;

public class ScrollNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_number);
        ((ConstraintLayout)findViewById(R.id.parent)).addView(new ScrollNumber(this));
    }
}
