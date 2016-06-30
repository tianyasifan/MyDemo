package com.txt.retrofit2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import retrofit2.Retrofit;

/**
 * Created by txt on 2016/6/3.
 */
public class RetrofitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("").build();
    }
}
