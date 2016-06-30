package com.txt.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by txt on 2016/4/19.
 * 自定义注解类
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value1() default "abcd";
    int value2() default 0;
}
