package com.txt.singleton;

/**
 * Created by txt on 2016/5/30.
 */
public class SingletonTest {
    /**
     * 饿汉式
     */
    static class Singleton1{
        public static Singleton1 instance = new Singleton1();
        private Singleton1(){}
        public static Singleton1 getInstance(){
            return instance;
        }
    }

    /**
     * 内部类形式
     */
    static class Singleton2{
        private static class Singleton2Holder{
            static Singleton2 instance = new Singleton2();
        }
        public static Singleton2 getInstance(){
           return Singleton2Holder.instance;
        }
    }
}
