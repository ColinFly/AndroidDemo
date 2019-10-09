package com.colin.demo.mvp.view;

import android.os.Bundle;
import android.util.Log;

import com.colin.demo.mvp.presenter.IPresenter;

import java.lang.reflect.ParameterizedType;



public abstract class MvpActivity<P extends IPresenter> extends BaseActivity
        implements IView<P>{

    protected P presenter;
    private static final String TAG = "MvpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"MvpActivity  onCreate");
        presenter = initPresenter();
        getLifecycle().addObserver(presenter);
        super.onCreate(savedInstanceState);
    }

    @Override
    public P initPresenter() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<? extends IPresenter> presenterClass = (Class<? extends IPresenter>) type.getActualTypeArguments()[0];
        try {
            this.presenter = (P) presenterClass.newInstance();
            presenter.attachView(this);
            return presenter;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onStart() {
        Log.e(TAG,"MvpActivity  onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG,"MvpActivity  onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG,"MvpActivity  onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(TAG,"MvpActivity  onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG,"MvpActivity  onDestroy");
        super.onDestroy();
    }
}
