package com.share.meizu.newsfeedapi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IFeedView {

    RecyclerView recyclerView;
    MyRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        LinearLayoutManager manager = new LinearLayoutManager(App.sAppContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter = new MyRecyclerAdapter());
        recyclerView.addItemDecoration(new DividerItemDecoration(App.sAppContext,DividerItemDecoration.VERTICAL));

//        TouTiaoServerManager.getInstance().getFeedFromServer(this);
//        TouTiaoServerManager.getInstance().getTokenFromServer2();
        TouTiaoServerManager.getInstance().getFeedsFromServer();
    }

    @Override
    public void onLoadFeed(List<FeedBean.FeedDataBean> feeds) {
        mAdapter.setList(feeds);
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>{
        List<FeedBean.FeedDataBean> list;

        public void setList(List<FeedBean.FeedDataBean> list){
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.i("list", "onCreateViewHolder");
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
            MyViewHolder holder = new MyViewHolder(item);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Log.i("list", "onBindViewHolder: " + position);
            if(list != null){
                holder.title.setText(list.get(position).title);
            }
        }

        @Override
        public int getItemCount() {
            if(list == null)
                return 0;
            return list.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.img);
        }
    }

}
