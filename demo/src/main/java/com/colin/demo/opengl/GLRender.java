package com.colin.demo.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRender implements GLSurfaceView.Renderer {
    private static final String TAG = "GLRender";
    /**
     * @param gl
     * @param config
     * 1.创建的时候调用
     * 2.从其它activity切换回来的时候调用
     * 3.屏幕唤醒调用
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.i(TAG, "onSurfaceCreated: ");
        //屏幕将显示红色
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);



    }

    /**
     * @param gl
     * @param width
     * @param height
     * 1.Surface尺寸变化调用
     * 2.横竖屏切换的时候Surface变化
     */

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.i(TAG, "onSurfaceChanged: ");
        GLES20.glViewport(0,0,width,height);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.i(TAG, "onDrawFrame: ");
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

    }
}
