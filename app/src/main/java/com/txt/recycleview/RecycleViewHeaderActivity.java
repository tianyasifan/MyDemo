package com.txt.recycleview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.txt.mydemo.R;

public class RecycleViewHeaderActivity extends AppCompatActivity {
    private int[] arrays = {1,2,3,4,5,6,7,8,9,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_header);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_header);
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        rv.setLayoutManager(manager);
        rv.setAdapter(new MyAdapter(arrays));
    }

    static class MyAdapter extends RecyclerView.Adapter{
        private enum ITEM_TYPE{HEADER,NORMAL};
        private int[] arrays;
        public MyAdapter(int[] arrays){
            this.arrays = arrays;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder holder;
            if(viewType == ITEM_TYPE.HEADER.ordinal()){
                holder = new MyHeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_header_header,parent,false));
            }else{
                holder = new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_header_item, parent, false));
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyHolder){
                ((MyHolder) holder).tv.setText(""+arrays[position-1]);
            }else if(holder instanceof MyHeaderHolder){
                ((MyHeaderHolder) holder).iv.setBackgroundResource(R.drawable.ic_action_name);
            }
        }

        @Override
        public int getItemCount() {
            return arrays.length + 1;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? ITEM_TYPE.HEADER.ordinal():ITEM_TYPE.NORMAL.ordinal();
        }

        @Override
        public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if(manager instanceof GridLayoutManager){
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return getItemViewType(position)==ITEM_TYPE.HEADER.ordinal()? gridLayoutManager.getSpanCount():1;
                    }
                });
            }
        }

        @Override
        public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if(lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams){
                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) lp;
                layoutParams.setFullSpan(holder.getLayoutPosition()==0);
            }
        }
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_rv_header_item);
        }
    }

    static class MyHeaderHolder extends RecyclerView.ViewHolder{
        public ImageView iv;
        public MyHeaderHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_rv_header_header);
        }
    }
}
