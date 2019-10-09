package com.colin.demo.opengl;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class AirHockeyRenderActivity extends Activity {
    private static final String TAG = "AirHockeyRenderActivity";
    private GLSurfaceView mGlSurfaceView;
    private boolean mIsRender;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化opnegl
        mGlSurfaceView = new GLSurfaceView(this);
        //检查是否支持es2.0
        boolean isSupportES2=checkIsSupportES2();
        Log.i(TAG, "onCreate: isSupportES2:"+isSupportES2);
        if (isSupportES2) {
            // Request an OpenGL ES 2.0 compatible context.
            mGlSurfaceView.setEGLContextClientVersion(2);
            mGlSurfaceView.setRenderer(new AirHockeyRender());
            mIsRender = true;
        } else {
            Toast.makeText(this, "This device does not support OpenGL ES 2.0.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        setContentView(mGlSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mIsRender) {
            mGlSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsRender) {
            mGlSurfaceView.onResume();
        }
    }

    private boolean checkIsSupportES2() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager != null ? activityManager.getDeviceConfigurationInfo() : null;
        return configurationInfo.reqGlEsVersion >= 0x20000;


    }
}
