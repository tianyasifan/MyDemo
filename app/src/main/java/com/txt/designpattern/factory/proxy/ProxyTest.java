package com.txt.designpattern.factory.proxy;


import java.lang.reflect.Proxy;

/**
 * Created by tongxt on 2017/5/22.
 */

public class ProxyTest {
    public void test(){
        //创建一个代理类,该代理类同样实现Operate接口（强制转换）
        Operate proxy = (Operate) Proxy.newProxyInstance(Operate.class.getClassLoader(),
               new Class[]{ Operate.class},new MyInvocationHandler(new OperateImpl()));
        proxy.doSomething();
    }
}
