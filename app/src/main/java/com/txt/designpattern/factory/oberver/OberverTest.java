package com.txt.designpattern.factory.oberver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txt on 2016/2/26.
 */
public class OberverTest {

    public void test(){
        Iphone iphone = new Iphone();
        Fensi c = new Chinese();
        Fensi a = new American();
        iphone.register(c);
        System.out.println("一个中国人登记了");
        iphone.register(a);
        System.out.println("一个美国人登记了");
        try {
            System.out.println("经过漫长的等待");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        iphone.notyfys();
    }

    /**
     * 被观察者，提供对观察者的注册和通知
     */
    class Iphone {
        List<Fensi> list = new ArrayList<Fensi>();
        public void register(Fensi fensi){
            list.add(fensi);
            System.out.println("又一个苹果被预订了,现在总共有："+list.size()+"个人预订了...");
        }

        public void notyfys(){
            System.out.println("IPHONE 6现在高调发售...");
            for(Fensi fensi : list){
                fensi.receive();
            }
        }
    }

    /**
     * 观察者通用接口，所有观察者实现这个接口的方法
     */
    interface Fensi {
        public void receive();
    }

    /**
     * 以下是各个观察者
     */
    class Chinese implements Fensi {

        @Override
        public void receive() {
            System.out.println("中国人：我终于买到了，高兴死了....");
        }
    }

    class American implements Fensi {

        @Override

        public void receive() {

            // TODO Auto-generated method stub

            System.out.println("美国人喊叫：嗯哼，有点贵....");

        }

    }
}