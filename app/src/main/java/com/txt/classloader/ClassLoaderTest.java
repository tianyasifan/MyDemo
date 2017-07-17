package com.txt.classloader;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by tongxt on 2017/3/4.
 */
public class ClassLoaderTest {
    private String tag = "ClassLoaderTest";
    void printClassLoader(){
        Log.i(tag, "Context的加载器：" + Context.class.getClassLoader());
        Log.i(tag, "TextView的加载器：" + TextView.class.getClassLoader());
    }
}
