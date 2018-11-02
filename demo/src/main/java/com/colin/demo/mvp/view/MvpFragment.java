package com.colin.demo.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.colin.demo.mvp.presenter.IPresenter;

import java.lang.reflect.ParameterizedType;



public abstract class MvpFragment<P extends IPresenter> extends BaseFragment implements IView<P> {

    protected P presenter;
    private static final String TAG = "MvpFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
           Log.e(TAG,"initPresenter 成功 presenter=" +presenter);
            return presenter;
        } catch (Exception e) {
           Log.e(TAG,"initPresenter  Exception=" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
