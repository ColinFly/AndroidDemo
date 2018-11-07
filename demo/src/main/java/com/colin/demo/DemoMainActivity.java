package com.colin.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.colin.demo.android_room_with_view.WordMainActivity;
import com.colin.demo.data_binding.DataBindingMainActivity;
import com.colin.demo.data_binding.recyclerview.RecyclerViewActivity;
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


    @OnClick({R.id.btn_android_room_with_view, R.id.btn_mvp,R.id.btn_data_binding,R.id.btn_data_bind_recycler_view})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_android_room_with_view:
                intent=new Intent(this, WordMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_mvp:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_data_binding:
                intent=new Intent(this, DataBindingMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_data_bind_recycler_view:
                intent = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
        }
    }



}
