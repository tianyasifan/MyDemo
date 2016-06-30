 package com.txt.annotation;

/**
 * Created by txt on 2016/4/20.
 */

@TestA(name = "type",id=0,gid = Long.class)
public class UserAnnotation {
    @TestA(name = "param",id = 1,gid = Long.class)
    private Integer age;
    @TestA(name = "construct",id=2,gid = Long.class)
    public UserAnnotation(){}
    @TestA(name = "method",id = 3,gid = Long.class)
    public void a(){
        System.out.println("测试注解方法");
    }
}
