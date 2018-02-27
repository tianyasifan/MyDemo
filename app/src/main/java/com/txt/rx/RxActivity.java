package com.txt.rx;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by txt on 2016/5/4.
 */
public class RxActivity extends Activity {
    private String tag = RxActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*基本用法*/
    public void baseUse(){
        String name[] = {"a","b","c"};
        //创建一个发送事件的被观察者，发送的事件是由里面的对象ObservableOnSubcribe的subscribe来执行
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Thread.sleep(1000);
                e.onNext(2);
                Thread.sleep(1000);
                e.onNext(3);
                Thread.sleep(1000);
                e.onComplete();
            }
        }) ;

        //subscriber和observer类似，都是观察者,rx2使用后者，因为在被观察者的subscribe订阅的时候，接受后者
        Observer<Integer> subscriber = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                //被观察者和观察着链接时调用
                Log.i(tag, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                //被观察者执行onNext时调用（也就是上面的ObservableEmitter发射器调用的onNext
                Log.i(tag, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.i(tag, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(tag, "onComplete");
            }
        };

        //被观察者和观察这产生联系（subscribe一旦订阅成功，马上被观察者就发送事件
        observable.subscribe(subscriber);
    }

    /*基本转换操作符*/
    public void change(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Thread.sleep(1000);
                e.onNext(2);
                Thread.sleep(1000);
                e.onNext(3);
                Thread.sleep(1000);
                e.onComplete();
            }
        });
        observable.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return null;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }

    public void change2(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {
                return null;
            }
        });
    }
}
