package com.txt.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by txt on 2015/12/8.
 */
public abstract class BaseRecycleViewAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public interface OnItemSelectedListener{
        void onItemSelect(View view, int position);
    }

    private OnItemClickListener mClickListener;
    private OnItemSelectedListener mSelectListener;

    public void setOnItemSelectedListener(OnItemSelectedListener listener){
        mSelectListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mClickListener = listener;
    }

    private LayoutInflater inflater;
    private int layoutId;
    private List<T> datas;
    public BaseRecycleViewAdapter(Context context, int layoutId, List<T> datas){
        inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.datas = datas;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutId,parent,false);
        return createHolder(view);
    }

    public abstract VH createHolder(View view);
    public abstract void bindHolder(final VH holder, int position);

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        bindHolder(holder,position);
        holder.itemView.setFocusable(true);
        if(mClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
        }
        if(mSelectListener!=null){
        //要使用，必须强制设置holder.itemView.setFocusable(true);子view必须能获得焦点;
            //并且，需要手动设置，当RecycleView获得焦点时，其子view获取焦点
            holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        mSelectListener.onItemSelect(v,holder.getLayoutPosition());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(datas!=null){
            return datas.size();
        }
        return 0;
    }
}
