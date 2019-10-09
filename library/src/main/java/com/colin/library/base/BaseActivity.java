package com.colin.library.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by colin on 16-5-3.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {
    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        EventBus.getDefault().register(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    protected void startActivity(Class<? extends BaseActivity> activity) {
        startActivity(new Intent(this, activity));
    }

    public void onEventMainThread(BaseEvent event) {
    }

    @Override
    public void onClick(View v) {

    }
}
