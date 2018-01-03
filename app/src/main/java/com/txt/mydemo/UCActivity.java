package com.txt.mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.txt.Md5SignUtils;
import com.uc.webview.export.WebChromeClient;
import com.uc.webview.export.WebView;
import com.uc.webview.export.WebViewClient;
import com.uc.webview.export.extension.UCClient;
import com.uc.webview.export.extension.UCCore;
import com.uc.webview.export.extension.UCSettings;


public class UCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Md5SignUtils.getSignMD5(this);
        /**
         * 接入后，不需要混淆uc相关代码，混淆规则应   keep com.uc.**
         *
         */

        //务必，务必，务必 初始化调用后才使用 webview，尽量在程序启动时就初始化，初始化为异步行为。
        this.init();


        WebViewClient mWebViewClient = new WebViewClient();
        WebChromeClient mWebChromeClient = new WebChromeClient();

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        WebView mWebView = new WebView(this);
        mWebView.setLayoutParams(params);

        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);


        //系统webview设置
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setUseWideViewPort(true);

        //各种UCSDK扩展功能需要判断是否初始化成功用上了UC WebView
        if(mWebView.getUCExtension() != null)
        {

            //UClient 需要通过UCExtension来进行设置。
            UCClient mUCClient = new UCClient();
            mWebView.getUCExtension().setClient(mUCClient);
            UCSettings.setForceUserScalable(UCSettings.FORCE_USER_SCALABLE_DISABLE);

            //夜间模式
            UCSettings.setNightMode(true);
        }




        mWebView.loadUrl("https://www.baidu.com");


        //WebView.getCoreType() 可获取内核使用类型，3 为U4内核  2为系统内核
        //需要尽量在初始化后使用，没有初始化就getCoreType，会抛异常。
        Log.d("CORETYPE", "" + WebView.getCoreType());
        setContentView(mWebView);

    }



    public void init() {

        boolean Debug = true ;
        //输出初始化日志，可帮助定位初始化遇到的问题
        if(Debug) {
            UCCore.setPrintLog(true);
        }

        UCCore.setup(UCCore.OPTION_CONTEXT, this.getApplicationContext())  //初始化需要传入getApplicationContext
              .setup(UCCore.OPTION_USE_SYSTEM_WEBVIEW, false)              //是否强制初始化成系统内核
              .setup(UCCore.OPTION_HARDWARE_ACCELERATED, true)             //开启硬件渲染
              .setup(UCCore.OPTION_MULTI_CORE_TYPE, true)                  //支持多内核切换
              .setup(UCCore.OPTION_WEBVIEW_POLICY, UCCore.WEBVIEW_POLICY_WAIT_UNTIL_FALLBACK_SYSTEM)  //当UC内核没初始化好，则先提供系统内核
              .setup(UCCore.OPTION_VIDEO_HARDWARE_ACCELERATED, false)      //使用SWAC方式渲染视频
              .setup(UCCore.OPTION_PROVIDED_KEYS,
                      new String[]{/*"G6o7AekHIcwSA8k+4Xlq1I4M+VK0aegPCa72XW/n94n5ivQedFIEnctpWdmHXw+4gQsHJfxFujD05C2/4NqWEw=="*/"b2E3He2Rh9OR/iemgdafdP5/FufawFaf71W36lwK30hjY3a2FfPYnevxgpKFHafaebharefawe4cfewfcMQ=="})  //传入授权码
              .setup(UCCore.OPTION_WEBVIEW_POLICY_WAIT_MILLIS, 4000)    //等待 内核初始化 4秒内没有成功初始化则降级为系统内核
              .setup(UCCore.OPTION_LOAD_POLICY, UCCore.LOAD_POLICY_SPECIFIED_ONLY)       //只加载当前指定路径内核
              .setup(UCCore.OPTION_VERIFY_POLICY, UCCore.VERIFY_POLICY_ALL)   // 安全校验：对所有动态包进行校验
              .setup(UCCore.OPTION_UCM_LIB_DIR, this.getApplicationInfo().nativeLibraryDir)    //内核文件所在目录
              .setup(UCCore.OPTION_INIT_IN_SETUP_THREAD, true)                                //在 Setup线程进行初始化
              .setup(UCCore.OPTION_SETUP_THREAD_PRIORITY, -20)                                //线程优先级，适配层调度可避免跟主进程争抢资源，优先级请参照android 官方文档
              .start();
    }


}


