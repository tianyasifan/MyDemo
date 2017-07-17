package com.txt.plugin;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.txt.mydemo.R;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class ProxyActivity extends Activity {
    private static final String TAG = "ProxyActivity";
    public static final String FROM = "extra.from";
    public static final int FROM_EXTERNAL = 0;
    public static final int FROM_INTERNAL = 1;

    public static final String EXTRA_DEX_PATH = "extra.dex.path";
    public static final String EXTRA_CLASS = "extra.class";

    private String mClass;
    private String mDexPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        mDexPath = getIntent().getStringExtra(EXTRA_DEX_PATH);
        mClass = getIntent().getStringExtra(EXTRA_CLASS);

        Log.i(TAG, "mClass=" + mClass + " mDexPath=" + mDexPath);
        if (mClass == null) {
            launchTargetActivity();
        } else {
            launchTargetActivity(mClass);
        }

    }

    private void launchTargetActivity() {
        PackageInfo packageInfo = getPackageManager().getPackageArchiveInfo(mDexPath, PackageManager.GET_ACTIVITIES);
        ActivityInfo[] aInfo = packageInfo.activities;
        if (aInfo != null && aInfo.length > 0) {
            String activityName = aInfo[0].name;
            mClass = activityName;
            launchTargetActivity(mClass);
        }
    }

    private void launchTargetActivity(String className){
        Log.d(TAG, "start launchTargetActivity, className=" + className);
        File dexOutputDir = getDir("dex", 0);
        String dexOutputPath = dexOutputDir.getAbsolutePath();
        DexClassLoader loader = new DexClassLoader(mDexPath, dexOutputPath, null,getClassLoader());
        try {
            Class loadClass = loader.loadClass(className);
            Object instance = loadClass.newInstance();
            Method setProxy = loadClass.getMethod("setProxy", new Class[]{Activity.class});
            setProxy.setAccessible(true);
            setProxy.invoke(instance,this);

            Method onCreate = loadClass.getDeclaredMethod("onCreate",
                    new Class[] { Bundle.class });
            onCreate.setAccessible(true);
            Bundle bundle = new Bundle();
            bundle.putInt(FROM, FROM_EXTERNAL);
            onCreate.invoke(instance, new Object[] { bundle });

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
