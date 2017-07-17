package com.txt.threads;


import java.util.concurrent.Semaphore;

/**
 * Created by tongxt on 2017/4/18.
 */
public class SemaphoreTest {
    private Semaphore mSemaphore = new Semaphore(5);
    public void run(){
        for(int i=0; i< 100; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            }).start();
        }
    }

    private void test(){
        try {
            mSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 进来了");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 出去了");
        mSemaphore.release();
    }
}
