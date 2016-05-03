package com.colin.demo.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.ProgressBar;

import com.colin.demo.R;
import com.colin.library.base.BaseActivity;
import com.orhanobut.logger.Logger;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by colin on 16-5-3.
 */
public class MainActivity extends BaseActivity {

    private MsgService mMsgService;
    @InjectView(R.id.progressBar)
    ProgressBar mProgressBar;
    private int mProgress;
    private MsgReceiver mMsgReceiver;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.aty_service_main);
        super.onCreate(savedInstanceState);

    }

    //Service连接的回调接口实现
    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //通过binder拿到service对象
            mMsgService = ((MsgService.MsgBinder) service).getService();
            Logger.i("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @OnClick({R.id.btn_bind_service,R.id.btn_download_start,R.id.btn_register_broadcast,R.id.btn_start_service})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind_service:
                Intent intent = new Intent(mContext,MsgService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_download_start:
                //开始下载
                mMsgService.startDownLoad();
                //通过接口回调更新进度
                mMsgService.setListener(new MsgService.ProgressChangeListener() {
                    @Override
                    public void onChanged(int progress) {
                        mProgressBar.setProgress(progress);
                    }
                });
                //直接从Service里取值
                //listenProgress();
                break;
            case R.id.btn_register_broadcast:
                //通过Action过滤Service
                mMsgReceiver=new MsgReceiver();
                registerReceiver(mMsgReceiver, new IntentFilter("com.colin.demo.service.Service2"));
                Logger.i("已注册广播接收器");
                break;
            case R.id.btn_start_service:
                //启动service
                mIntent = new Intent("com.colin.demo.service.Service2");
                startService(mIntent);
                break;
        }

    }

    /**
     * 广播接收器
     * @author len
     *
     */
    public class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //拿到进度，更新UI
            int progress = intent.getIntExtra("progress", 0);
            Logger.i("拿到进度，更新UI  ");
            mProgressBar.setProgress(progress);
        }

}

    private void listenProgress() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(mProgress < MsgService.MAX_PROGRESS){
                    mProgress = mMsgService.getProgress();
                    Logger.i(mProgress+"");
                    mProgressBar.setProgress(mProgress);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        //销毁服务
        unbindService(connection);

        //停止服务
        stopService(mIntent);
        //注销广播
        unregisterReceiver(mMsgReceiver);
        super.onDestroy();
    }
}
