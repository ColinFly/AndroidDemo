package com.colin.recyclerviewdemo;

import android.os.Bundle;
import android.view.View;

import com.colin.library.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick({R.id.line, R.id.line2, R.id.grid, R.id.staggered})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.line:
                startActivity(LineAty.class);
                break;
            case R.id.line2:
                break;
            case R.id.grid:
                break;
            case R.id.staggered:
                break;
        }
    }
}
