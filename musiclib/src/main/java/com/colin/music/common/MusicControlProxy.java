package com.colin.music.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.colin.music.common.entity.MusicFile;

import java.util.List;


/**
 * 由代理类完成对服务类的接口调用
 */
public class MusicControlProxy implements ServiceConnection {

    private static final String TAG = "MusicControlProxy";
    private Context mContext;
    private MusicPlayControl mMusicPlayControl;
    private onProgressChangeListener mOnProgressChangeListener;


    public MusicControlProxy(Context context) {
        this.mContext = context;
    }

    public void onCreate() {
        if (mContext != null) {
            mContext.bindService(new Intent("com.colin.music.service.MusicService"), this, Context.BIND_AUTO_CREATE);
        }
    }
    public void onStart() {
        registerCallback();
    }

    public void onRestart() {
        registerCallback();
    }

    public void onStop() {
        unRegisterCallback();
    }

    public void onDestroy() {
        unRegisterCallback();
        mContext.unbindService(this);
        mOnProgressChangeListener = null;
    }

    private void unRegisterCallback() {
        if (mMusicPlayControl != null) {
            try {
                mMusicPlayControl.unregisterPlayCallback(mMusicPlayCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mMusicPlayControl = MusicPlayControl.Stub.asInterface(service);
        try {
            service.linkToDeath(mBinderDeathListener, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        registerCallback();

    }

    private void registerCallback()  {
        if (mMusicPlayControl != null) {
            //也可以继续注册更多的接口回调(分层次写更好)
            try {
                mMusicPlayControl.registerPlayCallback(mMusicPlayCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        unRegisterCallback();
    }
    private IBinder.DeathRecipient mBinderDeathListener=new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.i(TAG, "binderDied: ");
            unRegisterCallback();
            //接口回调出去做处理
        }
    };

    private MusicPlayCallback mMusicPlayCallback=new MusicPlayCallback.Stub() {
        @Override
        public void playingMusic(MusicFile musicFile) throws RemoteException {
            Log.i(TAG, "playingMusic: ");
        }

        @Override
        public void onProgress(int progress) throws RemoteException {
            Log.i(TAG, "onProgress: ");
            mOnProgressChangeListener.onProgressChange(progress);
        }

        @Override
        public void onPrepared(int duration) throws RemoteException {

        }

        @Override
        public void listAllFileMusic(List<MusicFile> allFiles) throws RemoteException {

        }
    };

    //更多的接口实现...

    public interface onProgressChangeListener {
        void onProgressChange(int progress);
    }

    public void setOnProgressChangeListener(onProgressChangeListener listener) {
        mOnProgressChangeListener=listener;
    }


    //代理去实现各种控制方法
    public void play() {
//        mMusicPlayControl.play(new MusicFile());
    }

    public void next() {
        try {
            mMusicPlayControl.next();
            mOnProgressChangeListener.onProgressChange(11111111);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void previous() {
        try {
            mMusicPlayControl.previous();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



}