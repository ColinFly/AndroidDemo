package com.colin.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by colin on 16-5-3.
 */
public class MsgService extends Service {
    /**
     * 进度条的最大值
     */
    public static final int MAX_PROGRESS = 100;
    /**
     * 进度条的进度值
     */
    private int progress = 0;


    public int getProgress() {
        return progress;
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MsgBinder();
    }

    /**
     * 通过Binder对象,当Activity通过调用bindService(Intent service, ServiceConnection conn,int flags),
     * 我们可以得到一个Service的一个对象实例，然后我们就可以访问Service中的方法
     */
    public class MsgBinder extends Binder {
        public MsgService getService() {
            return MsgService.this;
        }
    }

    /**
     * 模拟下载任务，每秒钟更新一次
     */
    public void startDownLoad(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progress < MAX_PROGRESS){
                    progress += 5;
                    //方法一:接口回调把进度值传出去
                    if (listener != null) {
                        listener.onChanged(progress);
                    }
                    //方法二:
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }


    public interface ProgressChangeListener {
        void onChanged(int progress);
    }

    private ProgressChangeListener listener;

    public void setListener(ProgressChangeListener listener) {
        this.listener = listener;
    }
}

