package com.yftech.debugtool;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by colin on 16-11-23.
 */

public class MyService extends Service implements View.OnClickListener {

    private static final String TAG = "MyService";

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    private void createView(){
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        WindowManager mWindowManager = (WindowManager) getApplication().getSystemService(
                Context.WINDOW_SERVICE);
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.CENTER | Gravity.TOP;
        wmParams.x = 0;
        wmParams.y = 0;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        LayoutInflater inflater = LayoutInflater.from(getApplication());

        LinearLayout mFloatlayout = (LinearLayout) inflater.inflate(R.layout.activity_main, null);
        mWindowManager.addView(mFloatlayout, wmParams);

        mFloatlayout.findViewById(R.id.btn_host).setOnClickListener(this);
        mFloatlayout.findViewById(R.id.btn_debug).setOnClickListener(this);
        mFloatlayout.findViewById(R.id.btn_reboot).setOnClickListener(this);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //在程序退出(Activity销毁）时销毁悬浮窗口
//        wm.removeView(myFV);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        createView();
        mHandler.sendEmptyMessageDelayed(1, 1000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_host:
                ShellUtil.runCommand("sh /system/bin/switch_iap_adb.sh -iap");
                break;
            case R.id.btn_debug:
                ShellUtil.runRootCmd("sh /system/bin/switch_iap_adb.sh -adb");
                break;
            case R.id.btn_reboot:
                ShellUtil.runRootCmd("reboot");
                break;
        }
    }
}
