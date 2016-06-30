package com.txt.mydemo;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by txt on 2016/5/3.
 */
public class ListPackageInfoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("listpackge","onCreate");
        new Thread(new Runnable() {
            @Override
            public void run() {
               getPackageInfos();
            }
        }).start();
    }

    public void getPackageInfos(){
        PackageManager pm = getPackageManager();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(0);
        System.out.println("size:"+packageInfos.size());
        for(PackageInfo info:packageInfos){
            System.out.println("apk name:"+getApplicationName(info.packageName,pm)
            +"\n apk size:"+getApkSize(info));
        }
    }

    public String getApplicationName(String packageName,PackageManager packageManager){
        String appName = null;
        try {
            ApplicationInfo info = packageManager.getApplicationInfo(packageName,0);
            info.loadIcon(packageManager);
            appName = (String) packageManager.getApplicationLabel(info);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }

    public Drawable getApplicationIcon(String packageName, PackageManager packageManager){
        Drawable icon = null;
        try {
            ApplicationInfo info = packageManager.getApplicationInfo(packageName,0);
            icon = info.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return icon;
    }

    public float getApkSize(PackageInfo packageInfo){
        float size=0;
        File file = new File(packageInfo.applicationInfo.sourceDir);
        size = file.length()/1024/1024;
        return size;
    }
}
