package com.txt.helpers;

import java.util.Random;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class TheftproolHelper {

	private String tag = TheftproolHelper.class.getSimpleName();
	private TextView textView;
	private int parentWidth,parentHeight;
	private int margin = 50;
	private int width,height;
    private Handler handler;
    private int showTime = 10;//10s
    private int spaceTime = 16;//16分钟
    private Runnable spaceRun,showRun;//分别是间隔执行任务，展示计时任务
	
	public void initView(Context context,ViewGroup parent,Handler handler){
        this.handler = handler;
		textView = new TextView(context);
		textView.setTextSize(50);
		textView.setText("游客  MAC");
		parentWidth = parent.getWidth();
		parentHeight = parent.getHeight();
		parent.addView(textView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        spaceRun = new Runnable() {
            @Override
            public void run() {
                setViewPosition();
            }
        };
        showRun = new Runnable() {
            @Override
            public void run() {
//                toHide();//到时间，隐藏防盗水印
                textView.setVisibility(View.INVISIBLE);
                start();//开始下一次计时
            }
        };
	}

    public void start(){
        Log.i(tag,"开启显示水印倒计时");
        handler.postDelayed(spaceRun,spaceTime*1000);//16s后执行一次
    }

    public void stop(){
        handler.removeCallbacks(spaceRun);
        handler.removeCallbacks(showRun);
        spaceRun = null;
        showRun = null;
    }
	
	public void setViewPosition(){
		Random random = new Random();
        int position = random.nextInt(4);
        MarginLayoutParams params = (MarginLayoutParams) textView.getLayoutParams();
        width = textView.getWidth();
        height = textView.getHeight();
        Log.i(tag, "position:" + position + "  parentW:" + parentWidth + "  parentH:" + parentHeight + "  w:" + width + "  h:" + height);
        switch (position){
            case 0:
                params.leftMargin = margin;
                params.topMargin = margin;
                break;
            case 1:
                params.leftMargin = margin;
                params.topMargin = parentHeight - height - margin;
                Log.i(tag,"top:"+params.topMargin);
                break;
            case 2:
                params.leftMargin = parentWidth - width- margin;
                params.topMargin = margin;
                break;
            case 3:
                params.leftMargin = parentWidth - width - margin;
                params.topMargin = parentHeight - height -margin;
                break;
        }
        textView.setLayoutParams(params);
        textView.setVisibility(View.VISIBLE);
        handler.postDelayed(showRun, showTime * 1000);
	}
	
	public void toShow(){
		AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(500);
        textView.startAnimation(alphaAnimation);
	}
	
	public void toHide(){
		AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        textView.startAnimation(alphaAnimation);
	}
}
