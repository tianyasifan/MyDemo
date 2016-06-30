package com.txt.mydemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by txt on 2016/6/29.
 */
public class StickyHeaderListActivity extends AppCompatActivity{
    private String tag = "StickyHeaderListActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_header_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_sticky_header);
        final TextView fakeHeader = (TextView) findViewById(R.id.tv_header);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new MyAdapter(this));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i(tag,"onScrolled:"+dx+";"+dy);
                //查找该（x,y)位置上对应的子view,x位置设置为伪悬浮头的宽度的中心位置，Y位置偏移5个像素，如果设置0的话可能会找不到。
                View item = recyclerView.findChildViewUnder(fakeHeader.getMeasuredWidth()/2,5);
                if(item!=null && item.getContentDescription()!=null){//查找到子view，并且有描述
                    fakeHeader.setText(item.getContentDescription());//同一类型的item的描述是一样的，详见adapter中为item设置的描述
                    Log.i(tag,""+item.getContentDescription());
                }
                //获取伪装的悬浮顶fakeHeader下面的item
                View transInfoView = recyclerView.findChildViewUnder(fakeHeader.getMeasuredWidth()/2,fakeHeader.getMeasuredHeight()+1);
                //找到item，并且这个item设置了tag（这个设置在adapter中）
                if(transInfoView!=null && transInfoView.getTag()!=null){
                    int sticky = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - fakeHeader.getMeasuredHeight();//计算这个悬浮head顶部与fakeheader的距离（一定是个负值）
                    //如果这是一个悬浮header类型的item
                    if(sticky == MyAdapter.HAS_STICKY_VIEW){
                        //还在屏幕内
                        if(transInfoView.getTop()>0){
                            fakeHeader.setTranslationY(dealtY);//伪装者开始移动了，向上移（即负方向）
                        }else{
                            //当前header类型的item头部已经移除屏幕外了(此时该header和fakeheader处在同一位置，
                            //为了保证header悬浮的效果，马上显示伪装者
                            fakeHeader.setTranslationY(0);//还原
                        }
                    }else{
                        fakeHeader.setTranslationY(0);
                    }
                }
            }
        });
    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder>{
        public static final int FIRST_STICKY_VIEW = 1;
        public static final int HAS_STICKY_VIEW = 2;
        public static final int NONE_STICKY_VIEW = 3;
        Context mContext;
        ArrayList<Model> datas = new ArrayList<>();

        void initDatas(){
            Model model = null;
            for(int i=0;i<28;i++){
                if(i<4) {
                    model = new Model("Head0");
                }else if(i<8){
                    model = new Model("Head1");
                }else if(i<12){
                    model = new Model("Head3");
                }else if(i<16){
                    model = new Model("Head4");
                }else if(i<20){
                    model = new Model("Head5");
                }else if(i<24){
                    model = new Model("Head6");
                }else if(i<28){
                    model = new Model("Head7");
                }
                datas.add(model);
            }
        }

        public MyAdapter(Context context){
            mContext = context;
            initDatas();
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_sticky_header,parent,false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            Model model = datas.get(position);
            if(position%4==0){
                if(position==0){
                    holder.itemView.setTag(FIRST_STICKY_VIEW);
                }else{
                    holder.itemView.setTag(HAS_STICKY_VIEW);
                }
                holder.tv.setText(model.mSticky);
                holder.tv.setBackgroundResource(R.color.color4);

            }else{
                holder.itemView.setTag(NONE_STICKY_VIEW);
                holder.tv.setText(model.mName);
                holder.tv.setBackgroundResource(R.color.color5);
            }
            holder.itemView.setContentDescription(model.mSticky);//设置描述语言,同一类型的head下的描述语言一样。
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_header);
        }
    }

    class Model{
        public Model(String sticky){
            mSticky = sticky;
            mName = "item";
        }
        private String mSticky;
        private String mName;
    }
}
