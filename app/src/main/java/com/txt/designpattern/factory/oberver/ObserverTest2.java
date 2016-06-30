package com.txt.designpattern.factory.oberver;

import java.util.ArrayList;

/**
 * Created by txt on 2016/6/7.
 */
public class ObserverTest2 {
    //观察者
    interface Observer{
        void update(String s);
    }
    //被观察者：即主题
    interface Subject{
        void rejist(Observer observer);

        void unRejist(Observer observer);

        void notifyObserver(String s);
    }

    static class ConcreteSubject implements Subject{
        ArrayList<Observer> mObservers = new ArrayList<>();

        @Override
        public void rejist(Observer observer) {
            mObservers.add(observer);
        }

        @Override
        public void unRejist(Observer observer) {
            mObservers.remove(observer);
        }

        @Override
        public void notifyObserver(String s) {
            for(Observer observer : mObservers){
                observer.update(s);
            }
        }
    }

    static class Observer1 implements Observer{
        public Observer1(Subject subject){
            subject.rejist(this);
        }

        @Override
        public void update(String s) {
            System.out.println("observer1:"+s);
        }
    }

    static class Observer2 implements Observer{

        public Observer2(Subject subject){
            subject.rejist(this);
        }
        @Override
        public void update(String s) {

        }
    }

    public static void test(){
        ConcreteSubject subject = new ConcreteSubject();
        Observer1 observer1 = new Observer1(subject);
        Observer2 observer2 = new Observer2(subject);
        subject.notifyObserver("abc");
    }
}
