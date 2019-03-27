package com.txt.mydemo;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.txt.view.HomeAnimView;

public class CustomAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donghua);
        ConstraintLayout root = (ConstraintLayout) findViewById(R.id.custom_root);
        root.removeAllViews();
        root.addView(new HomeAnimView(this));
    }
}
