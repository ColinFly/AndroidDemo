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
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by colin on 16-11-23.
 */

public class MyService extends Service implements View.OnClickListener, View.OnTouchListener {

    private static final String TAG = "MyService";
    private LinearLayout layout;
    private Button mIpBtn;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    private void createView() {
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        WindowManager mWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);

        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.RIGHT | Gravity.TOP;
        wmParams.x = 0;
        wmParams.y = 0;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        LayoutInflater inflater = LayoutInflater.from(getApplication());

        LinearLayout mFloatlayout = (LinearLayout) inflater.inflate(R.layout.activity_main, null);
        mWindowManager.addView(mFloatlayout, wmParams);

        layout = (LinearLayout) mFloatlayout.findViewById(R.id.ll_function);
        mFloatlayout.findViewById(R.id.btn_display).setOnClickListener(this);
        mFloatlayout.findViewById(R.id.btn_host).setOnClickListener(this);
        mFloatlayout.findViewById(R.id.btn_debug).setOnClickListener(this);
        mFloatlayout.findViewById(R.id.btn_reboot).setOnClickListener(this);
        mFloatlayout.findViewById(R.id.btn_acc).setOnClickListener(this);
        mFloatlayout.findViewById(R.id.btn_background).setOnClickListener(this);
        mFloatlayout.findViewById(R.id.btn_uninstall).setOnClickListener(this);
        mFloatlayout.findViewById(R.id.btn_adb).setOnClickListener(this);
        mIpBtn = (Button) mFloatlayout.findViewById(R.id.btn_ip);
        mIpBtn.setOnClickListener(this);
        mFloatlayout.setOnTouchListener(this);

    }

    @Override
    public void onDestroy() {
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
                ShellUtil.runRootCmd("sh /system/bin/switch_iap_adb.sh -iap");
                break;
            case R.id.btn_debug:
                ShellUtil.runRootCmd("sh /system/bin/switch_iap_adb.sh -adb");
                break;
            case R.id.btn_reboot:
                ShellUtil.runRootCmd("reboot");
                break;
            case R.id.btn_acc:
                ShellUtil.runRootCmd("input keyevent 26");
                break;
            case R.id.btn_display:
                if (layout.isShown()) {
                    layout.setVisibility(View.GONE);
                } else {
                    layout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_uninstall:
                ShellUtil.runRootCmd("pm uninstall com.yftech.debugtool");
                break;
            case R.id.btn_background:
                ShellUtil.runRootCmd("am broadcast -a com.yftech.function");
                break;
            case R.id.btn_adb:
                ShellUtil.runRootCmd("start adbd");
                break;
            case R.id.btn_ip:
                mIpBtn.setText(IPUtil.getIP(getApplicationContext()));
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }
}
