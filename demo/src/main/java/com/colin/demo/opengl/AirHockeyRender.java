package com.colin.demo.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class AirHockeyRender implements GLSurfaceView.Renderer {
    private static final String TAG = "AirHockeyRender";
    //一个顶点有两个分量
    private static final int POSITION_COMPONENT_COUNT = 2;
    //每个float对应4字节
    private static final int BYTES_PER_FLOAT = 4;


    private final FloatBuffer vertexData;

    //构造函数
    public AirHockeyRender() {
        //桌子的顶点(只能通过三角形的方式去绘制)
        //注意定义三角形是逆时针的方式排列顶点，这样的卷曲顺序可以优化性能
        /*
		float[] tableVertices = {
			0f,  0f,
			0f, 14f,
			9f, 14f,
			9f,  0f
		};
         */
		float[] tableVerticesWithTriangles = {
                //三角形1
                0f, 0f,
                9f, 14f,
                0f,14f,
                //三角形2
                0f,0f,
                9f,0,
                9f,14f,
                //中间线
                0f,7f,
                9f,7f,
                //木槌
                4.5f,2f,
                4.5f,12f
        };
		//分配native内存
        vertexData = ByteBuffer.allocateDirect(tableVerticesWithTriangles.length*BYTES_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
        //将数据从dalvik的内存复制到native
        vertexData.put(tableVerticesWithTriangles);
    }

    /**
     * @param gl
     * @param config 1.创建的时候调用
     *               2.从其它activity切换回来的时候调用
     *               3.屏幕唤醒调用
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
     * @param height 1.Surface尺寸变化调用
     *               2.横竖屏切换的时候Surface变化
     */

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.i(TAG, "onSurfaceChanged: ");
        GLES20.glViewport(0, 0, width, height);

    }

    /**
     * @param gl 1.system call.系统高频反复在调用
     *           2.必须绘制点东西，不然可能闪屏
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        Log.i(TAG, "onDrawFrame: ");
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

    }
}
