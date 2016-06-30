package com.txt.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.txt.mydemo.R;

import java.util.List;

/**
 * Created by txt on 2016/4/26.
 */
public class HorizontalScrollViewAdapter {
    private Context mContext;
    private  List mDatas;
    private LayoutInflater mInflate;

    public HorizontalScrollViewAdapter(Context context, List datas){
        mContext = context;
        mDatas = datas;
        mInflate = LayoutInflater.from(context);
    }

    public Object getItem(int position){
        return mDatas.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public int getCount(){
        return mDatas.size();
    }

    public View getView(int position,View convertView,ViewGroup parent){
        Log.i("adpater","getview:"+position);
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.item_horizontal_list,parent,false);
            holder.mImg = (ImageView) convertView.findViewById(R.id.iv);
            holder.mText = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.mText.setText(""+position);
        holder.mImg.setImageResource((int)mDatas.get(position%mDatas.size()));
        return convertView;
    }

    private class ViewHolder
    {
        ImageView mImg;
        TextView mText;
    }
}
