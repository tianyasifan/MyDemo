package com.txt.designpattern.factory.factory;

import android.content.Context;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Properties;

/**
 * Created by txt on 2016/2/26.
 */
public class FactoryTest {
    public static void main(String[] args) {
        LocationFactory.Location location = LocationFactory.getInstance("baidu");
        location.getCityName(10,10);
        location.getPosition();
        System.out.println("class:" + location.getClass());

        LocationFactory.Location location1 = LocationFactory.getInstanceByReflec("com.txt.designpattern.factory.factory.LocationFactory$BaiduLocation");
        location1.getCityName(20,30);
        location1.getPosition();

    }

    public static void test(Context context){
        // 有时候我们预先不知道产品类的全路径，我们可以通过修改配置文件，读配置文件拿到确切的产品类
        Properties properties = new Properties();
        try {
            properties.load(context.getAssets().open("location.properties")); // 读取assets路径下的配置文件
            String className = properties.getProperty("locationBaidu");
            LocationFactory.Location location = LocationFactory.getInstanceByReflec(className);
            location.getCityName(12,34);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
