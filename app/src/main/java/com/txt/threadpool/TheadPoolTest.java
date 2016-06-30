package com.txt.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by txt on 2016/6/21.
 */
public class TheadPoolTest {
    public void test(){
        ExecutorService service = Executors.newFixedThreadPool(3);//创建一个含有三个线程的线程池
        List<Future> list = new ArrayList<>();//用一个集合来保存submit执行后得到的结果
        for(int i=0;i<3;i++){
            Future future = service.submit(new MyCallable("tasknum:"+i));
            list.add(future);
        }

        for(Future future : list){
            try {
                System.out.println(">>>"+future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
