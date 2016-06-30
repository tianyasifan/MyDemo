package com.txt.annotation;

/**
 * Created by txt on 2016/4/19.
 * 自定义注解测试类
 */
public class AnnotationTest {

    @MyAnnotation(value1 = "abc")
    private String name;
    @MyAnnotation(value2 = 8)
    private int age;

    public void execute(String name){
        System.out.println("测试注解"+name);
    }
}
