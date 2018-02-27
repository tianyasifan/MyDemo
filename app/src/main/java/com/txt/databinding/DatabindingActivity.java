package com.txt.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.txt.mydemo.R;
import com.txt.mydemo.databinding.ActivityDatabindBinding;

public class DatabindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabindBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_databind);
        binding.setUser(new User("a","b"));
    }
}
