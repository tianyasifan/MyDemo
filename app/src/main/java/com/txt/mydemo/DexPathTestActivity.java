package com.txt.mydemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.tongxt.mylibrary.IShowToast;
import com.txt.plugin.ProxyActivity;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by tongxt on 2017/3/5.
 */
public class DexPathTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dex_path);
    }

    public void loadOutJar(View view){
        File dexOutputDir = getDir("dex",0);//在data/data/xx/下面创建一个dex文件夹。
        String dexPath = Environment.getExternalStorageDirectory().toString() + File.separator + "loader_dex.jar";
        // 4.1以后不能够将optimizedDirectory（即第二个参数）设置到sd卡目录， 否则抛出异常.  
        DexClassLoader loader = new DexClassLoader(dexPath,dexOutputDir.getAbsolutePath(),null,getClassLoader());
        try {
            Class clz = loader.loadClass("com.tongxt.mylibrary.ShowToastImpl");
            Method method = clz.getDeclaredMethod("showToast", Context.class);
            method.invoke(clz.newInstance(), this);
//            IShowToast toast = (IShowToast) clz.newInstance();
//            toast.showToast(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadOutApk(View view){
        Intent intent = new Intent(DexPathTestActivity.this, ProxyActivity.class);
        String dexPath = Environment.getExternalStorageDirectory().toString() + File.separator + "dynamic-debug.apk";
        intent.putExtra(ProxyActivity.EXTRA_DEX_PATH, dexPath);
        startActivity(intent);
    }
}
