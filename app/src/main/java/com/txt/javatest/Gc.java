package com.txt.javatest;

import java.lang.ref.WeakReference;

/**
 * Created by tongxiaotao on 18-7-25.
 */
public class Gc {
    public static void main(String[] args) {
        Object object = new Object();
        WeakReference<Object> weak = new WeakReference<>(object);
        System.out.println("weak:" + weak.get());
        object = null;
        System.gc();
        System.out.println("weak gc:" + weak.get());
    }
}
