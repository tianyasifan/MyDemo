package com.txt.designpattern.factory.factory;


/**
 * Created by txt on 2016/2/26.
 * 工厂模式例子
 * 建立一个位置服务工厂，
 * 提供一个静态方法，根据参数获取具体的位置服务
 */
public class LocationFactory {
    public static Location getInstance(String type){
        if("baidu".equals(type)){
            return new BaiduLocation();
        }else if("xiaomi".equals(type)){
            return new XiaomiLocation();
        }
        return null;
    }

    /**
     * 定义一个位置服务接口，提供几个通用方法，所有类型的服务实现这个接口
     */
    interface Location{
        public void getPosition();
        public void getCityName(long lat,long lng);
    }

    /*
    以下是各种类型的服务
     */
    static class BaiduLocation implements Location{

        @Override
        public void getPosition() {

        }

        @Override
        public void getCityName(long lat, long lng) {

        }
    }

    static class XiaomiLocation implements Location{

        @Override
        public void getPosition() {

        }

        @Override
        public void getCityName(long lat, long lng) {

        }
    }
}
