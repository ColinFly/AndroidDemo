package com.colin.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * Created by colin on 16-5-3.
 */
public class MsgService2 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 进度条的最大值
     */
    public static final int MAX_PROGRESS = 100;
    /**
     * 进度条的进度值
     */
    private int progress = 0;

    private Intent intent = new Intent("com.colin.demo.service.Service2");

    /**
     * 模拟下载任务，每秒钟更新一次
     */
    public void startDownLoad(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(progress < MAX_PROGRESS){
                    progress += 5;
                    //发送Action为com.example.communication.RECEIVER的广播
                    intent.putExtra("progress", progress);
                    sendBroadcast(intent);
                    Logger.i("正在发送广播");
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
    public int onStartCommand(Intent intent, int flags, int startId) {
        startDownLoad();
        return super.onStartCommand(intent, flags, startId);

    }
}
