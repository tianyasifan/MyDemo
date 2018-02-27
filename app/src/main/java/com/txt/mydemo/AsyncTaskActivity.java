package com.txt.mydemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


import java.lang.ref.WeakReference;

/**
 * Created by txt on 2016/4/15.
 */
public class AsyncTaskActivity extends Activity {
    private AsyncTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 用匿名内部类的方式创建*/
         task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                for(int i=0;i<10;i++){
                    Log.i("task","i="+i);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(isCancelled()){
                        break;
                    }
                }
                return null;
            }

             @Override
             protected void onPostExecute(Object o) {
                 super.onPostExecute(o);
                 Log.i("task","执行结束了");
             }

             @Override
             protected void onCancelled() {
                 super.onCancelled();
                 Log.i("task","执行了取消");
             }
         };
        task.execute();
        //从log可以看到，上个task和下面的task是串行执行的


        task = new MyTask(this);
        task.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        task.cancel(true);
        //这里不做取消MyTask的任务是为了验证weakReference是否起作用
    }


    public void doSomething(){
        //为保险，还是需要判断下当前activity是否已经销毁，因为weakReference修饰的对象并不是马上就能被回收
        Log.i("AsyncActivity","异步任务完成，更新UI");
    }


    static class MyTask extends AsyncTask<String,Integer,String>{
        private WeakReference<Activity> weakAty;
        public MyTask(Activity activity){
            weakAty = new WeakReference<Activity>(activity);
        }

        @Override
        protected String doInBackground(String... params) {
            for(int i=0;i<100;i++){
                Log.i("Mytask","i="+i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(isCancelled()){
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("Mytask","执行结束了");
            AsyncTaskActivity mActivity;
            if((mActivity= (AsyncTaskActivity) weakAty.get())!=null){
                mActivity.doSomething();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("Mytask","执行了取消");
        }
    }
}
