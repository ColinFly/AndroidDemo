package com.yf.gradle4android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

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
        findViewById(R.id.btn_test_broadcast_implicit).setOnClickListener(this);
        findViewById(R.id.btn_test_broadcast_explicit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test_broadcast_implicit:
                sendBroadcast(new Intent("com.colin.receiver.test"));
                break;
            case R.id.btn_test_broadcast_explicit:
                //shell am broadcast -n com.colin.demo/.TestReceiver
                Intent intent = new Intent("com.colin.receiver.test");
                intent.setPackage("com.colin.demo");
                sendBroadcast(intent);
                break;
        }
    }
}
