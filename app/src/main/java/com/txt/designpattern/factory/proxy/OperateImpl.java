package com.txt.designpattern.factory.proxy;

/**
 * Created by tongxt on 2017/5/22.
 */

/**
 * 委托类，实现了Operate接口
 */
public class OperateImpl implements Operate{
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }
}
