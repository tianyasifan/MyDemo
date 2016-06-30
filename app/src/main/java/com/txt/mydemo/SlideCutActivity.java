package com.txt.mydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.txt.view.SwipeDismissListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txt on 2015/12/28.
 */
public class SlideCutActivity extends Activity {
    private SwipeDismissListView listView;
    private List<String> dataSourceList = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = new SwipeDismissListView(this);
        setContentView(listView);

        for (int i = 0; i < 20; i++) {
            dataSourceList.add("滑动删除" + i);
        }
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,dataSourceList);
        listView.setAdapter(adapter);
        listView.setOnDismissCallback(new SwipeDismissListView.OnDismissCallback() {
            @Override
            public void onDismiss(int dismissPosition) {
                adapter.remove(adapter.getItem(dismissPosition));//只是删除数据而已
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SlideCutActivity.this,"点我",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
