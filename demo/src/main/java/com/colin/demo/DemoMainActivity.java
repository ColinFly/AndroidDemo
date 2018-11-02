package com.colin.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.colin.demo.mvp.view.BaseActivity;
import com.colin.demo.mvp_demo.view.LoginActivity;

import butterknife.OnClick;

public class DemoMainActivity extends BaseActivity {
    @Override
    public int getViewLayout() {
        return R.layout.activity_demo_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState, Intent args) {

    }


    @OnClick({R.id.btn_mvc, R.id.btn_mvp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mvc:

                break;
            case R.id.btn_mvp:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
