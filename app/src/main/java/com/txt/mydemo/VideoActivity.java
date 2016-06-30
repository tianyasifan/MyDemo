package com.txt.mydemo;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.txt.helpers.TheftproolHelper;
import com.txt.view.VideoView;

import java.util.Random;

/**
 * Created by txt on 2015/12/16.
 */
public class VideoActivity extends Activity{
    private VideoView videoView;
    private RelativeLayout.LayoutParams lp,largeLp;
//    private WindowManager wm;
    private TextView tv1,tv2;
    private boolean addFlag=false;
    private String videoPath = "http://ad-tv.golive-tv.com/MI5_ITR-A_ENG_TXTD_HEVC_1080p.mp4";
    private String tag=VideoActivity.class.getSimpleName();
    private RelativeLayout parent;
    private TextView theftproofTv;
    private TheftproolHelper helper;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        parent = (RelativeLayout)findViewById(R.id.parent);
        tv1 = (TextView)findViewById(R.id.tv_v1);
        tv2 = (TextView)findViewById(R.id.tv_v2);
//        theftproofTv = (TextView) findViewById(R.id.tv_theftproof);
        videoView = new VideoView(this);
//        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        wm = getWindowManager();
        lp = (RelativeLayout.LayoutParams) tv1.getLayoutParams();
        Log.i(tag, "v1--->left:" + lp.leftMargin + "   right:" + lp.rightMargin + "   top:" + lp.topMargin + "   bottom:" + lp.bottomMargin
                + "   width:" + lp.width + "   height:" + lp.height);
        parent.addView(videoView, lp);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(tag, "onPrepared");

            }
        });
        handler = new Handler();

        /**
         * 通过post进去，可以延迟获取布局的控件的大小
         */
        parent.post(new Runnable() {
            @Override
            public void run() {
                helper = new TheftproolHelper();
                helper.initView(VideoActivity.this, (ViewGroup) parent,handler);
            }
        });

    }

    public void v1Btn(View view){
//        videoView.setVideoUrl(videoPath);
//        videoView.start();
//        setTheftproofTvPosition(theftproofTv);
        helper.start();
    }

    public void v2Btn(View view){
        int location[] = new int[2];
        tv2.getLocationOnScreen(location);
        Log.i(tag,"v2--->x:"+location[0]+"    y:"+location[1]);

        lp = (RelativeLayout.LayoutParams) tv2.getLayoutParams();
        Log.i(tag, "v2--->left:" + lp.leftMargin + "   right:" + lp.rightMargin + "   top:" + lp.topMargin + "   bottom:" + lp.bottomMargin
                + "   width:" + lp.width + "   height:" + lp.height);
//        videoView.setLayoutParams(lp);//设置布局属性

        //设置在屏幕中的绝对位置
        videoView.setLeft(location[0]);
        videoView.setTop(location[1]);
        videoView.setRight(location[0] + lp.width);
        videoView.setBottom(location[1] + lp.height);
    }

    public void v3Btn(View view){
        parent.setPadding(0, 0, 0, 0);
//       largeLp = new RelativeLayout.LayoutParams(1920,1080);
//        videoView.setLayoutParams(largeLp);
        videoView.setLeft(0);
        videoView.setTop(0);
        videoView.setRight(1980);
        videoView.setBottom(1080);
        addFlag = true;
    }

    @Override
    public void onBackPressed() {
        if(addFlag){
           videoView.setLayoutParams(lp);
            addFlag = false;
        }else {
            super.onBackPressed();
            videoView.stopPlayback();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.stopPlayback();
        helper.stop();
    }


    private void setTheftproofTvPosition(View view){
        Random random = new Random();
        int position = random.nextInt(4);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(500);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int margin = 50;
        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;
        Log.i(tag,"position:"+position + "  width:"+widthPixels + "  heigth:"+heightPixels);
        switch (position){
            case 0:
                params.leftMargin = margin;
                params.topMargin = margin;
                break;
            case 1:
                params.leftMargin = margin;
                params.topMargin = heightPixels - view.getHeight() - margin;
                Log.i(tag,"top:"+params.topMargin);
                break;
            case 2:
                params.leftMargin = widthPixels - view.getWidth() - margin;
                params.topMargin = margin;
                break;
            case 3:
                params.leftMargin = widthPixels - view.getWidth() - margin;
                params.topMargin = heightPixels - view.getHeight() -margin;
                break;
        }
        view.setLayoutParams(params);
        view.startAnimation(alphaAnimation);
    }
}
