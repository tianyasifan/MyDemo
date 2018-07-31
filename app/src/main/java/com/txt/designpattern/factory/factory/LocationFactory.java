package com.txt.designpattern.factory.factory;


/**
 * Created by txt on 2016/2/26.
 * 简单工厂模式例子
 * 建立一个位置服务工厂，
 * 提供一个静态方法，根据参数获取具体的位置服务
 * 缺点：违背开放-关闭原则，一旦要新加入一个产品（即新增一个位置服务）就需要修改该工厂的if...else语句
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
     * 通过反射来优化静态工厂方法，去除了if...else语句，新增产品不会修改，只需要传入产品的具体类的类名（全路径）
     * @param className
     * @return
     */
    public static Location getInstanceByReflec(String className){
        Location location = null;
        try {
            Class locationClass = Class.forName(className);
            location = (Location) locationClass.newInstance(); // 通过默认构造函数实例化产品对象（注意调用这必须保证产品有默认公共构造方法，否则会异常）
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return location;
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
    public static class BaiduLocation implements Location{

        @Override
        public void getPosition() {
            System.out.println("baidu");
        }

        @Override
        public void getCityName(long lat, long lng) {
            System.out.println(""+lat+"x"+lng);
        }
    }

    public static class XiaomiLocation implements Location{

        @Override
        public void getPosition() {
            System.out.println("xiaomi");
        }

        @Override
        public void getCityName(long lat, long lng) {
            System.out.println(""+lat+"x"+lng);
        }
    }
}
