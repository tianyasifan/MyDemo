package com.txt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.txt.mydemo.R;

import java.util.ArrayList;

/**
 * Created by txt on 2016/5/27.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] mTitles;
    private int[] mPics;
    private LayoutInflater mInflater;
    private Context mContext;
    private String tag = RecycleViewAdapter.class.getSimpleName();
    private enum ITEM_TYPE{ITEM1,ITEM2};

    public RecycleViewAdapter(Context context, String[] titles,int[] pics){
        mInflater = LayoutInflater.from(context);
        mTitles = titles;
        mPics = pics;
        mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(tag, "onCreateViewHolder");
        View view = null;
        if(viewType == ITEM_TYPE.ITEM1.ordinal()) {
            view = mInflater.inflate(R.layout.item_recycleview, parent, false);
            return new MyViewHolder(view);
        }else{
            view = mInflater.inflate(R.layout.item_recycleview2,parent,false);
            return new MyViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof MyViewHolder) {
            ((MyViewHolder)holder).iv.setBackgroundResource(mPics[position]);
            ((MyViewHolder)holder).tv.setText(mTitles[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mTitles[position], Toast.LENGTH_LONG).show();
                }
            });
        }else if(holder instanceof MyViewHolder2){
            ((MyViewHolder2)holder).tv.setText(mTitles[position]);
        }
    }

    @Override
    public int getItemCount() {
        return mTitles==null ? 0 : mTitles.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2 == 0 ? ITEM_TYPE.ITEM1.ordinal():ITEM_TYPE.ITEM2.ordinal();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv;
        public ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_text);
            iv = (ImageView) itemView.findViewById(R.id.iv_pic);
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder{

        public TextView tv;
        public MyViewHolder2(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
