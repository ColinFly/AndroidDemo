package com.yf.rxjava;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by colin on 18-1-10.
 */

public class HelloRx {
    private static final String TAG = "HelloRx";
    public static void main(String[] args) {
        //1.创建观察者listener-->observer
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:"+s);
            }
        };
        //2.创建被观察者btn-->observable
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {//这里的call是怎么触发的？
                subscriber.onNext("Hello");
                subscriber.onNext("World");
                subscriber.onCompleted();
            }
        });
        //3.绑定set-->subscribe
        observable.subscribe(observer);

        //observer的拓展类subscriber,基本使用都一样
        Subscriber<String> subscriber=new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:"+s);

            }
        };
        observable.subscribe(subscriber);


        //简洁一点的写法
        Observable.just("Hello"," Boy").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext"+s);
            }
        });
        //更简洁的写法
        Observable.just("Good", "Boy").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("call:"+s);
            }
        });



    }
}
