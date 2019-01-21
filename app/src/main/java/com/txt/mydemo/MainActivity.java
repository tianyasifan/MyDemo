package com.txt.mydemo;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

//import com.txt.javatest.Main;
import com.txt.databinding.DatabindingActivity;
import com.txt.designpattern.factory.factory.FactoryTest;
import com.txt.designpattern.factory.prototype.CloneTest;
import com.txt.javatest.JsonTest;
import com.txt.javatest.UrlEncoderUtils;
import com.txt.recycleview.RecycleViewHeaderActivity;
import com.txt.rx.RxActivity;
import com.txt.scrollnumber.ScrollNumberActivity;
import com.txt.service.ForegroundService;
import com.txt.service.MyIntentService;
import com.txt.threads.SemaphoreTest;
import com.txt.threadtoast.ThreadTostActivity;
import com.txt.web2native.Web2nativeActivity;
import com.txt.wxmvp.WXActivity;

import java.util.List;

//import test.com.tsapi.WebTranslateActivity;

public class MainActivity extends ListActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    String[] string = {"TabLayout","动态改变view的位置及ViewDragHelper的使用","Video","仿知乎首页","滑动删除","Android样式实例",
    "水平画廊","异步任务内存泄露测试","注解与反射测试","自定义横向listview","http使用","获取所有的apk","mvp示例","viewpager画廊",
    "Activity与Service","Activity与远程Service","Binder线程池实例","Messenger使用","Fragment使用实例",
    "自定义ViewPager实例","RecycleView实例","自定义view实例","列表悬浮头实例","动态加载","Activity生命周期",
    "从浏览器打开本地app","RecyclerView添加Header","线程里创建Toast","AIDL实例",
    "滚轮数字","进程保活-前台服务","进程保活-1像素Activity","按back键进入桌面",
    "贝塞尔曲线", "打开一个空白的activity", "UC浏览器","系统浏览器","Rx2", "数据绑定","网页翻译",
    "布局测试", "图片裁剪", "new app", "字体大小适配", "View的滑动"};
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.MyTheme);
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
//        startService(new Intent(this, MyIntentService.class));
//        startService(new Intent(this, MyIntentService.class));

//        CallableTest.useCallableAndFuture();
//        Test.print();
//        Main.main(null);
//        new SemaphoreTest().run();

//        CloneTest.test();
        Log.e(TAG, " " + UrlEncoderUtils.hasUrlEncoded("sms:+7 915 444-54-44"));

        new JsonTest().json();

        FactoryTest.test(this);

        getCarrier();
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position){
            case 0:
                Log.i(TAG, string[0]);
                startActivity(new Intent(this,TabLayoutActivity.class));
                break;
            case 1://动态改变view的位置及ViewDragHelper的使用
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
            case 14:// activity和service生命周期
                startActivity(new Intent(this,Service1Activity.class));
                break;
            case 15:// activity与远程service
                startActivity(new Intent(this,AidlActivity.class));
                break;
            case 16:// bind线程池
                startActivity(new Intent(this,AidlBinderPoolActivity.class));
                break;
            case 17:
                startActivity(new Intent(this,MessengerActivity.class));
                break;
            case 18://fragment使用实例
                startActivity(new Intent(this,FragmentActivity.class));
                break;
            case 19:
                startActivity(new Intent(this,MyViewPagerActivity.class));
                break;
            case 20:
                startActivity(new Intent(this,RecycleViewActivity.class));
                break;
            case 21://自定义view实例
                startActivity(new Intent(this,CustomViewActivity.class));
                break;
            case 22:
                startActivity(new Intent(this,StickyHeaderListActivity.class));
                break;
            case 23:
                startActivity(new Intent(this,DexPathTestActivity.class));
                break;
            case 24://生命周期
                startActivity(new Intent(this,LifecycleActivity.class));
                break;
            case 25:
                startActivity(new Intent(this, Web2nativeActivity.class));
                break;
            case 26:
                startActivity(new Intent(this, RecycleViewHeaderActivity.class));
                break;
            case 27:
                startActivity(new Intent(this, ThreadTostActivity.class));
                break;
            case 28://AIDL实例
                startActivity(new Intent(this, com.tongxt.aidlservicedemo.AidlActivity.class));
                break;
            case 29://滚轮数字
                startActivity(new Intent(this, ScrollNumberActivity.class));
                break;
            case 30://启动前台服务
                startService(new Intent(this, ForegroundService.class));
                break;
            case 31://1像素activity，注册锁屏广播接收器
                IntentFilter filter = new IntentFilter();
                filter.addAction(Intent.ACTION_SCREEN_ON);
                filter.addAction(Intent.ACTION_SCREEN_OFF);
                registerReceiver(new KeepLiveReceiver(),filter);
                break;
            case 32://按back键进入桌面
                startActivity(new Intent(this,MoveToBackActivity.class));
                break;
            case 33://贝塞尔曲线
                startActivity(new Intent(this,BezierActivity.class));
                break;
            case 34:// 空白activity
                startActivity(new Intent(this, EmptyActivity.class));
                break;
            case 35: // UCweb
                startActivity(new Intent(this, UCActivity.class));
                break;
            case 36: // 系统浏览器
                startActivity(new Intent(this, WebActivity.class));
                break;
            case 37:
                startActivity(new Intent(this, RxActivity.class));
                break;
            case 38:// 数据绑定
                startActivity(new Intent(this, DatabindingActivity.class));
                break;
            case 39:// 网页翻译
//                startActivity(new Intent(this, WebTranslateActivity.class));
                break;
            case 40: // 布局
                startActivity(new Intent(this, LayoutActivity.class));
                break;
            case 41: // 图片裁剪
                startActivity(new Intent(this, ImageActivity.class));
                break;
            case 42: //
                doStartApplicationWithPackageName(this, "com.android.browser");
                break;
            case 43://字体大小适配
                startActivity(new Intent(this, FontSizeActivity.class));
                break;
            case 44: // view的滑动
                startActivity(new Intent(this, ScrollViewActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, ForegroundService.class));//停止前台服务
    }

    public class KeepLiveReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(Intent.ACTION_SCREEN_OFF.equals(action)){
                KeepLiveActivity.KeepManager.getInstance().startKeepLiveActivity(MainActivity.this);
            }else if(Intent.ACTION_SCREEN_ON.equals(action)){
                KeepLiveActivity.KeepManager.getInstance().finishKeepLiveActivity();
            }
        }
    }

    //获取运营商
    public void getCarrier() {
        /*String region = Settings.Global.getString(AppContextUtils.getAppContext().getContentResolver(), "mz_custom_marketing_region");
        String tim = Settings.Global.getString(AppContextUtils.getAppContext().getContentResolver(), "mz_custom_marketing_carrier");
        LogUtils.e("IN", "地区： " + region + ", 运营商:　" + tim);
        if ("CL".equals(region) && "WOM".equals(tim)){
            return true;
        }
        return false;*/
        TelephonyManager telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = null;
        try {
            telManager.getSubscriberId();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(imsi!=null){
            if(imsi.startsWith("46000") || imsi.startsWith("46002")){//因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
                Log.w("carrier", "中国移动");//中国移动
            }else if(imsi.startsWith("46001")){
                Log.w("carrier", "中国联通");//中国联通
            }else if(imsi.startsWith("46003")){
                Log.w("carrier", "中国电信"); //中国电信
            }else if(imsi.startsWith("73009")){
                Log.w("carrier", "智利WOM");
            }
        }
    }

    /**
     * 打开某个应用
     *
     * @param context
     * @param packagename
     */
    public static void doStartApplicationWithPackageName(Context context, String packagename) {

        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = context.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packagename = 参数packname
            String packageName = resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
            String className = resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            // 设置ComponentName参数1:packagename参数2:MainActivity路径
            ComponentName cn = new ComponentName(packageName, className);

            intent.setComponent(cn);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
