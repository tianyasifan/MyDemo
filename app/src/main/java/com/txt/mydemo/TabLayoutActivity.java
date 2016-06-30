package com.txt.mydemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.txt.adapter.TabPagerAdapter;
import com.txt.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txt on 2015/12/9.
 */
public class TabLayoutActivity extends FragmentActivity {
    private String[]titles=new String[]{"全部","氪TV","O2O","新硬件","Fun!!","企业服务","Fit&Health",
            "在线教育","互联网金融","大公司","专栏","新产品"};
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabPagerAdapter pagerAdapter;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(),this);
        init();
    }

    private void init(){
        fragments = new ArrayList<>();
        int titleSize = titles.length;
        Fragment fragment;
        for(int i=0;i<titleSize;i++){
            fragment = new TwoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("extr",titles[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        pagerAdapter.setTitles(titles);
        pagerAdapter.setFragments(fragments);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        for(int j=0;j<titleSize;j++){
//            tabLayout.getTabAt(j).setCustomView(pagerAdapter.getView(j));
//        }
    }
}
