package com.txt.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.txt.mydemo.R;

import java.util.List;

/**
 * Created by txt on 2015/12/10.
 * 一个简单的pagerAdapter
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private List<Fragment> fragments;
    private Context context;

    public void setTitles(String[] titles){
        this.titles = titles;
    }
    public void setFragments(List<Fragment> fragments){
        this.fragments = fragments;
    }

    public TabPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(fragments!=null){
           return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if(fragments!=null){
            return fragments.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //提供指示器上的页标题
        return titles[position];
    }

   /* public View getView(int position){
        View view = LayoutInflater.from(context).inflate(R.layout.tabview,null);
        TextView tv = (TextView) view.findViewById(R.id.tv_tab);
        tv.setText(titles[position]);
        return view;
    }*/
}
