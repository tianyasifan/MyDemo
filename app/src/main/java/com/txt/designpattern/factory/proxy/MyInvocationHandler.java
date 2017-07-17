package com.txt.designpattern.factory.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by tongxt on 2017/5/22.
 */

/**
 * 连接代理类和委托类的中间类，必须实现该接口
 */
public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    /**
     * 声明一个构造函数，把委托类传进来
     * @param target
     */
    public MyInvocationHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target,args);//执行委托类的方法
    }
}
