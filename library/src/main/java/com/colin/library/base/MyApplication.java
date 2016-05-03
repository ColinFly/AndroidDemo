package com.colin.library.base;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;


/**
 * Created by colin on 16-5-3.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
    }

    private void initLog() {
        Logger.init("Colin");                 // default PRETTYLOGGER or use just init()

    }
}
