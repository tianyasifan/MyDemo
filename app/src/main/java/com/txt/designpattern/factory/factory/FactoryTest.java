package com.txt.designpattern.factory.factory;

/**
 * Created by txt on 2016/2/26.
 */
public class FactoryTest {
    public void test(){
        LocationFactory.Location location = LocationFactory.getInstance("baidu");
        location.getCityName(10,10);
        location.getPosition();
    }
}
