package com.colin.musicclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.colin.music.common.MusicBaseActivity;
import com.colin.music.common.MusicControlProxy;
import com.colin.music.common.MusicPlayCallback;
import com.colin.music.common.MusicPlayControl;
import com.colin.music.common.entity.MusicFile;

import java.util.List;


public class MainActivity extends MusicBaseActivity implements  View.OnClickListener {

    private static final String TAG = "MainActivity";
//    private MusicPlayControl mMusicPlayControl;

    private MusicControlProxy mMusicPlayControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent("com.colin.music.service.MusicService");
//        bindService(intent, this, BIND_AUTO_CREATE);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
        findViewById(R.id.btn_add_callback).setOnClickListener(this);
        mMusicPlayControl=getMusicControlProxy();
    }
//
//    @Override
//    public void onServiceConnected(ComponentName name, IBinder service) {
//        mMusicPlayControl = MusicPlayControl.Stub.asInterface(service);
//        if (mMusicPlayControl != null) {
//            Log.i(TAG, "onServiceConnected: ok");
//            try {
//                mMusicPlayControl.registerPlayCallback(mMusicPlayCallback);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void onServiceDisconnected(ComponentName name) {
//        if (mMusicPlayControl != null) {
//            try {
//                mMusicPlayControl.unregisterPlayCallback(mMusicPlayCallback);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unbindService(this);
//    }

    private MusicPlayCallback mMusicPlayCallback=new MusicPlayCallback.Stub() {
        @Override
        public void playingMusic(MusicFile musicFile) throws RemoteException {
            Log.i(TAG, "playingMusic: ");
        }

        @Override
        public void onProgress(int progress) throws RemoteException {
            Log.i(TAG, "onProgress: "+progress);
        }

        @Override
        public void onPrepared(int duration) throws RemoteException {

        }

        @Override
        public void listAllFileMusic(List<MusicFile> allFiles) throws RemoteException {

        }
    };

    @Override
    public void onProgressChange(int progress) {
        Log.i(TAG, "onProgressChange: "+progress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
//                try {
//                    mMusicPlayControl.playOrPause();
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
                mMusicPlayControl.play();
                break;
            case R.id.btn_next:
//                try {
//                    mMusicPlayControl.next();
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
                mMusicPlayControl.next();
                break;
            case R.id.btn_add_callback:
//                try {
//                    mMusicPlayControl.registerPlayCallback(new MusicPlayCallback.Stub() {
//                        @Override
//                        public void playingMusic(MusicFile musicFile) throws RemoteException {
//
//                        }
//
//                        @Override
//                        public void onProgress(int progress) throws RemoteException {
//                            Log.i("TAG2", "onProgress: "+progress);
//                        }
//
//                        @Override
//                        public void onPrepared(int duration) throws RemoteException {
//
//                        }
//
//                        @Override
//                        public void listAllFileMusic(List<MusicFile> allFiles) throws RemoteException {
//
//                        }
//                    });
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
                break;
        }
    }
}
