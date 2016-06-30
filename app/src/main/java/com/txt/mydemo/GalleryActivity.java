package com.txt.mydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.txt.adapter.GalleryAdapter;
import com.txt.view.CustomRecycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txt on 2016/1/28.
 */
public class GalleryActivity extends Activity{
    private CustomRecycleView gallery;
    private GalleryAdapter adapter;
    private List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_promotion);
        gallery = (CustomRecycleView) findViewById(R.id.hl_promotion);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gallery.setLayoutManager(manager);
        for(int i=0;i<5;i++){
            datas.add("wowowo");
        }
        adapter = new GalleryAdapter(this,R.layout.item_promotion_list,datas);
        gallery.setAdapter(adapter);
        gallery.requestFocus();
    }
}
