package com.txt.javatest;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by tongxiaotao on 18-7-18.
 */
public class SynchronouseQueueDemo {

    public static void main(String[] args) {
        final SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
        final Thread put = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put start");
                try {
                    synchronousQueue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("put end");
            }
        });
        Thread take = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take start");
                try {
                    System.out.println("take:" + synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("take end");
            }
        });
        put.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        take.start();
    }
}
