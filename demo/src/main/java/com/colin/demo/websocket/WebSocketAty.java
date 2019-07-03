package com.colin.demo.websocket;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.colin.demo.R;


public class WebSocketAty extends Activity implements View.OnClickListener {
    private static final String TAG = "WebSocketAty";
    private WebSocketManager mWebSocketManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);
        findViewById(R.id.btn_connect).setOnClickListener(this);
        findViewById(R.id.btn_disconnect).setOnClickListener(this);
        findViewById(R.id.btn_get_data).setOnClickListener(this);
        mWebSocketManager = new WebSocketManager("ws://58.241.16.4:8366");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_connect:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mWebSocketManager.connect();
                    }
                }).start();
                break;
            case R.id.btn_disconnect:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mWebSocketManager.disconnect();
                    }
                });
            case R.id.btn_get_data:
                mWebSocketManager.sendMessage("02");
                break;
        }
    }





}
