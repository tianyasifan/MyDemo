package com.txt.mydemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.txt.Callable.CallableTest;
import com.txt.javatest.Test;
import com.txt.service.MyIntentService;
import com.txt.view.CustomRecycleView;
import com.txt.view.MyViewPager;
import com.txt.wxmvp.WXActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends ListActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    String[] string = {"TabLayout","动态改变view的位置及ViewDragHelper的使用","Video","仿知乎首页","滑动删除","Android样式实例",
    "水平画廊","异步任务内存泄露测试","注解与反射测试","自定义横向listview","http使用","获取所有的apk","mvp示例","viewpager画廊",
    "Activity与Service","Activity与远程Service","Binder线程池实例","Messenger使用","Fragment使用实例",
    "自定义ViewPager实例","RecycleView实例","自定义view实例","列表悬浮头实例"};
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView home = (ImageView)findViewById(R.id.iv_home);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                home.setVisibility(View.GONE);
            }
        },2000);
        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, string));
        startService(new Intent(this, MyIntentService.class));
        startService(new Intent(this, MyIntentService.class));

//        CallableTest.useCallableAndFuture();
//        Test.print();
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position){
            case 0:
                Log.i(TAG, string[0]);
                startActivity(new Intent(this,TabLayoutActivity.class));
                break;
            case 1:
                Log.i(TAG,string[1]);
                startActivity(new Intent(this,ViewDragHelperActivity.class));
                break;
            case 2:
                Log.i(TAG,string[2]);
                startActivity(new Intent(this,VideoActivity.class));
                break;
            case 3:
                Log.i(TAG,string[3]);
                startActivity(new Intent(this,zhiHuActivity.class));
                break;
            case 4:
                Log.i(TAG,string[4]);
                startActivity(new Intent(this,SlideCutActivity.class));
                break;
            case 5:
                Log.i(TAG, string[5]);
                startActivity(new Intent(this,ViewStyleActivity.class));
                break;
            case 6:
                Log.i(TAG, string[6]);
                startActivity(new Intent(this,GalleryActivity.class));
                break;
            case 7:
                startActivity(new Intent(this,AsyncTaskActivity.class));
                break;
            case 8:
                startActivity(new Intent(this,AnnotationActivity.class));
                break;
            case 9:
                startActivity(new Intent(this,HorizontalListActivity.class));
                break;
            case 10:
                startActivity(new Intent(this,HttpActivity.class));
                break;
            case 11:
                startActivity(new Intent(this,ListPackageInfoActivity.class));
                break;
            case 12:
                startActivity(new Intent(this,WXActivity.class));
                break;
            case 13:
                startActivity(new Intent(this,ViewPagerGalleryActivity.class));
                break;
            case 14:
                startActivity(new Intent(this,Service1Activity.class));
                break;
            case 15:
                startActivity(new Intent(this,AidlActivity.class));
                break;
            case 16:
                startActivity(new Intent(this,AidlBinderPoolActivity.class));
                break;
            case 17:
                startActivity(new Intent(this,MessengerActivity.class));
                break;
            case 18:
                startActivity(new Intent(this,FragmentActivity.class));
                break;
            case 19:
                startActivity(new Intent(this,MyViewPagerActivity.class));
                break;
            case 20:
                startActivity(new Intent(this,RecycleViewActivity.class));
                break;
            case 21:
                startActivity(new Intent(this,CustomViewActivity.class));
                break;
            case 22:
                startActivity(new Intent(this,StickyHeaderListActivity.class));
                break;
            default:
                break;
        }
    }
}
