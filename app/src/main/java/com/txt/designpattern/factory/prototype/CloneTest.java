package com.txt.designpattern.factory.prototype;

/**
 * Created by tongxt on 2017/6/27.
 */

public class CloneTest {

    public static void test(){
        Student student = new Student();
        student.setNumber(1);
        student.setName("张三");
        student.show();

        Student student1 = (Student) student.clone();
        student1.setNumber(2);
        student1.setName("李四");
        student1.show();
    }
}
