package com.colin.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.colin.library.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by colin on 16-5-3.
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.aty_main);
        super.onCreate(savedInstanceState);
    }


    @OnClick({R.id.notification, R.id.service})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notification:
                startActivity(new Intent(this, com.colin.demo.notification.MainActivity.class));
                break;
            case R.id.service:
                startActivity(com.colin.demo.service.MainActivity.class);
                break;
        }
    }

}
