package com.yf.gradle4android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView = (TextView) findViewById(R.id.tv_info);
        String info = "FLAVOR " + BuildConfig.FLAVOR + "\n" +
                "SERVER_HOST " + BuildConfig.SERVER_HOST + "\n" +
                " VERSION_NAME " + BuildConfig.VERSION_NAME + "\n" +
                " VERSION_CODE " + BuildConfig.VERSION_CODE + "\n" +
                " APPLICATION_ID " + BuildConfig.APPLICATION_ID ;
        textView.setText(info);
    }
}
