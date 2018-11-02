package com.colin.demo.mvp.view;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Author : WaterFlower.
 * Created on 2017/8/11.
 * Desc :
 */

public abstract class BaseActivity  extends AppCompatActivity {

    private Unbinder mUnbinder;


    /**
     * @return 布局resourceId
     */
    public abstract int getViewLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //             透明导航栏
            //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        getWindow().setBackgroundDrawable(null);
        setContentView(getViewLayout());
        mUnbinder = ButterKnife.bind(this);
        initView(savedInstanceState, getIntent());

    }

    /**
     * 从intent获取数据，初始化View。或者其他view级第三方控件的初始化,及相关点击事件的绑定
     *
     * @param savedInstanceState
     * @param args
     */
    protected abstract void initView(Bundle savedInstanceState, Intent args);

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }


}
