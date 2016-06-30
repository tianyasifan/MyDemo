package com.txt.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.txt.mydemo.R;

/**
 * Created by txt on 2016/5/6.
 */
public class ViewPagerGalleryAdapter extends PagerAdapter {
    private String tag = ViewPagerGalleryAdapter.class.getSimpleName();
    private SparseIntArray mDatas;
    private LayoutInflater mInflater;
    private Context mContext;
    public ViewPagerGalleryAdapter(Context context, SparseIntArray datas){
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        mContext = context;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
//        Log.i(tag,"isViewFromObject");
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i(tag,"instantiateItem:"+position);
        View view = mInflater.inflate(R.layout.item_gallery,container,false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View)object);
        Log.i(tag,"destroyItem:"+position);
    }
}
