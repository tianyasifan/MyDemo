package com.txt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.txt.base.BaseRecycleViewAdapter;

import java.util.List;

/**
 * Created by txt on 2016/1/28.
 */
public class GalleryAdapter<T> extends BaseRecycleViewAdapter<T,GalleryAdapter.ViewHolder> {

    public GalleryAdapter(Context context, int layoutId, List<T> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public ViewHolder createHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindHolder(ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
