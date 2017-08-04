package com.colin.musicservice;

import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.colin.music.common.MusicPlayCallback;

/**
 * Created by colin on 17-8-3.
 */

public class MusicManager {
    private RemoteCallbackList<MusicPlayCallback> mCallbackList=new RemoteCallbackList<>();

    public MusicManager() {

    }

    public void registerMusicPlayCallback(MusicPlayCallback callback) {
        mCallbackList.register(callback);
    }

    public void unregisterMusicPlayCallback(MusicPlayCallback callback) {
        mCallbackList.unregister(callback);
    }

    public synchronized void onProgress(int progress) {
        int i = mCallbackList.beginBroadcast();
        while (i != 0) {
            i--;
            try {
                mCallbackList.getBroadcastItem(i).onProgress(progress);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mCallbackList.finishBroadcast();
    }
}
