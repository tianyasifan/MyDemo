package com.txt.mydemo;

import android.app.Activity;
import android.os.Bundle;

import com.txt.adapter.HorizontalScrollViewAdapter;
import com.txt.view.MyHorizontalScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by txt on 2016/4/26.
 */
public class HorizontalListActivity extends Activity {
    private MyHorizontalScrollView hrListView;
    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
            R.drawable.ic_action_name, R.drawable.ic_action_name, R.drawable.ic_action_name, R.drawable.ic_action_name,
            R.drawable.ic_action_name, R.drawable.ic_action_name, R.drawable.ic_action_name, R.drawable.ic_action_name,
            R.drawable.ic_action_name));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_list);
        hrListView = (MyHorizontalScrollView)findViewById(R.id.hr_listview);
        hrListView.initDatas(new HorizontalScrollViewAdapter(HorizontalListActivity.this,mDatas));
    }
}
