package com.colin.music.common;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by colin on 17-8-4.
 */

public class MusicBaseActivity extends Activity implements MusicControlProxy.onProgressChangeListener {

    private static final String TAG = "MusicBaseActivity";
    private MusicControlProxy mMusicControlProxy;

    public MusicControlProxy getMusicControlProxy() {
        return mMusicControlProxy;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mMusicControlProxy=new MusicControlProxy(this);
        mMusicControlProxy.onCreate();
        mMusicControlProxy.setOnProgressChangeListener(this);
//        mMusicControlProxy.set各种接口
        super.onCreate(savedInstanceState);
    }


    /**
     * @param progress
     * 这里提供的空实现交给子类去复写
     */
    @Override
    public void onProgressChange(int progress) {
        Log.i(TAG, "onProgressChange: ");
    }



    @Override
    protected void onStart() {
        mMusicControlProxy.onStart();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mMusicControlProxy.onStop();
        super.onStop();

    }

    @Override
    protected void onRestart() {
        mMusicControlProxy.onRestart();
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        mMusicControlProxy.onDestroy();
        super.onDestroy();
    }


}
