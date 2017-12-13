package com.yf.gradle4android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate:FLAVOR "+BuildConfig.FLAVOR);
        Log.i(TAG, "onCreate:SERVER_HOST "+BuildConfig.SERVER_HOST);
        Log.i(TAG, "onCreate: VERSION_NAME "+BuildConfig.VERSION_NAME);
        Log.i(TAG, "onCreate: VERSION_CODE "+BuildConfig.VERSION_CODE);
        Log.i(TAG, "onCreate: APPLICATION_ID " + BuildConfig.APPLICATION_ID);
    }
}
