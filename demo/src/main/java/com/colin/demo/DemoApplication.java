package com.colin.demo;

import android.app.Application;
import android.content.Context;

public class DemoApplication extends Application {
    private static final String TAG = "DemoApplication";
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
