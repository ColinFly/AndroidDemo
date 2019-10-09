package com.yf.rxjava;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by colin on 18-1-11.
 */
//scheduler可以指定每段代码在什么样的线程中执行
public class MainScheduler {
    public static void main(String[] args) {
        Observable.just("Hello", "World")
                .subscribeOn(Schedulers.newThread())//指定:在新的线程中发起
                .observeOn(Schedulers.io())//指定:在io线程中处理
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return handleString(s);//这段代码将在io线程中执行
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//指定:切换到主线程
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        show(s);//这段代码将在主线程中执行
                    }
                });
    }


    private static void show(String s) {
        System.out.println(s);
    }

    private static String handleString(String s) {
        return s;
    }

}
