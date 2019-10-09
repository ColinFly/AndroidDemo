package com.colin.musicservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.colin.music.common.MusicPlayCallback;
import com.colin.music.common.MusicPlayControl;
import com.colin.music.common.entity.MusicFile;

/**
 * Created by colin on 17-8-3.
 */

public class MusicService extends Service {
    private static final String TAG = "MusicService";
    private MusicManager mMusicManager;

    @Override
    public IBinder onBind(Intent intent) {
        return mMusicPlayControl;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMusicManager=new MusicManager();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }




    
    MusicPlayControl.Stub mMusicPlayControl=new MusicPlayControl.Stub() {
        @Override
        public void registerPlayCallback(MusicPlayCallback callback) throws RemoteException {
            mMusicManager.registerMusicPlayCallback(callback);
        }

        @Override
        public void unregisterPlayCallback(MusicPlayCallback callback) throws RemoteException {
            mMusicManager.unregisterMusicPlayCallback(callback);
        }

        @Override
        public void play(MusicFile music) throws RemoteException {


        }

        @Override
        public void previous() throws RemoteException {

        }

        @Override
        public void next() throws RemoteException {
            Log.i(TAG, "next: ");
        }

        @Override
        public void playOrPause() throws RemoteException {
            Log.i(TAG, "play: and will use onProgress");
            mMusicManager.onProgress(11);
        }

        @Override
        public void setPlayProgress(int position) throws RemoteException {

        }
    };





}
