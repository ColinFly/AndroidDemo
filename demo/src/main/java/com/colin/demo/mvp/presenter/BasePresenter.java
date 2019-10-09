package com.colin.demo.mvp.presenter;


import android.util.Log;

import com.colin.demo.mvp.view.IView;

import java.lang.ref.WeakReference;



public class BasePresenter<V extends IView> implements IPresenter<V> {

    private static final String TAG = "BasePresenter";
    private WeakReference<V> viewReference;
    protected String viewClassName;

    @Override
    public void attachView(V view) {
        viewClassName = view.getClass().getName();
        viewReference = new WeakReference<V>(view);
    }


    @Override
    public boolean isViewAttached() {
        return viewReference != null && viewReference.get() != null;
    }


    @Override
    public V getView() {
        if (isViewAttached()) {
            return viewReference.get();
        } else {
            throw new RuntimeException(" View is not attached to Presenter ");
        }

    }


    @Override
    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }


    @Override
    public void onCreate() {
        Log.e(TAG,this.viewClassName + " ---  BasePresenter  onCreate");
    }

    @Override
    public void onStart() {
        Log.e(TAG,this.viewClassName + " ---  BasePresenter  onStart");
    }

    @Override
    public void onResume() {
        Log.e(TAG,this.viewClassName + " ---  BasePresenter  onResume");
    }

    @Override
    public void onPause() {
        Log.e(TAG,this.viewClassName + " ---  BasePresenter  onPause");
    }

    @Override
    public void onStop() {
        Log.e(TAG,this.viewClassName + " ---  BasePresenter  onStop");
    }


    @Override
    public void onDestroy() {
        Log.e(TAG,this.viewClassName+" ---  BasePresenter  onDestroy");
        detachView();
    }
}
