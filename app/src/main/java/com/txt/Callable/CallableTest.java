package com.txt.Callable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by txt on 2016/6/23.
 */
public class CallableTest {
    public void test(){
        Callable<String> mCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "我是Callable";
            }
        };

        FutureTask<String> mTask = new FutureTask<String>(mCallable);
        Thread mThread1 = new Thread(mTask);
        mThread1.start();//启动线程
        mTask.cancel(true);//取消线程
    }

    /**
     * Callable+Future获得子线程的执行结果，通过线程池来执行
     */
    public static void useCallableAndFuture(){
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                System.out.println("计算结果");
                int i=10;
                return i;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(callable);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行任务");

        try {
            System.out.println("获得子线程的运行结果："+future.get());//get方法会阻塞等待执行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有线程执行完毕");
    }

    public static void useCallableAndFutureTask(){
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                System.out.println("计算结果");
                int i=10;
                return i;
            }
        };

        FutureTask<Integer> task = new FutureTask<Integer>(callable);//用FutureTask封装了callable

        /*一种方式：使用线程池来执行*/
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(task);//FutureTask实现了Runnable接口，所以可以加进来。

        /*二种方式：使用线程来执行*/
        /*Thread thread = new Thread(task);
        thread.start();*/

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行任务");

        try {
            System.out.println("获得子线程的运行结果："+task.get());//get方法会阻塞等待执行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有线程执行完毕");

        HashSet set = new HashSet();

    }
}
