package com.txt.javatest;

/**
 * Created by tongxiaotao on 19-2-14.
 */
public class OperatorTest {
    public static void test(){
        OperatorTest test;
        boolean flag = false && test.getFlag();
        System.out.println("打印出flag：" + flag);
    }

    public boolean getFlag(){
        return true;
    }
}
