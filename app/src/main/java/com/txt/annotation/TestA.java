package com.txt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by txt on 2016/4/20.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestA {
    String name();
    int id();
    Class<Long> gid();
}
