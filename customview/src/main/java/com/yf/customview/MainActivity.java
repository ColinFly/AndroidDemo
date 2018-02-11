package com.yf.customview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.yf.customview.view.MyView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private static final int MSG_WHAT_ALL_POINTS = 1;
    private static final int MSG_WHAT_TARGET_POINTS = 2;
    private static final int MSG_WHAT_NEXT_TASK = 3;
    private static final int TARGET_POINT = 50;

    private List<Position> mLeftList = new ArrayList<>();
    private List<Position> mRightList = new ArrayList<>();

    private List<Position> mNewLeftList = new ArrayList<>();
    private List<Position> mNewRightList = new ArrayList<>();

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_WHAT_ALL_POINTS) {
                myView.setLeftList(mLeftList);
                myView.setRightList(mRightList);
                myView.refresh();

            } else if (msg.what == MSG_WHAT_TARGET_POINTS) {
                myView.setLeftList(mNewLeftList);
                myView.setRightList(mNewRightList);
                myView.refresh();
                saveData(mNewLeftList);
                saveData(mNewRightList);
                mHandler.sendEmptyMessageDelayed(MSG_WHAT_NEXT_TASK, 3000);
            } else if (msg.what == MSG_WHAT_NEXT_TASK) {
                reset();
//                new Thread(new MyTask(bitmap)).start();
            }
        }
    };

    private StringBuilder sb=new StringBuilder();
    private void saveData(List<Position> positions) {
        sb.append("{");
        for (int i = 0; i <positions.size(); i++) {
            Position position=positions.get(i);
            int result=position.x << 16 | position.y;
            sb.append("0x");
            sb.append(Integer.toHexString(result));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("},\r");
        Log.i(TAG, "saveData: " + sb.toString());
    }

    private void reset() {
        isChangeArray = false;
        mLeftList.clear();
        mRightList.clear();
        mNewLeftList.clear();
        mNewRightList.clear();
        myView.setLeftList(null);
        myView.setRightList(null);
    }


    private MyView myView;
    private boolean isChangeArray;

    private class MyTask implements Runnable {
        private static final String TAG = "MyTask";
        private Bitmap bitmap;
        private int width;
        private int height;

        public MyTask(Bitmap bitmap) {
            this.bitmap = bitmap;
            width = bitmap.getWidth();
            height = bitmap.getHeight();
        }

        @Override
        public void run() {
            for (int i = 0; i < width; i += 1) {
                for (int j = 0; j < height; j += 1) {
                    int pixel = bitmap.getPixel(i, j);
                    int redValue = Color.red(pixel);
                    int greenValue = Color.green(pixel);
                    int blueValue = Color.blue(pixel);
//                    Log.v(TAG, "onCreate: redValue:" + redValue + " greenValue:" + greenValue + " blueValue:" + blueValue);
                    if (redValue == 255 && greenValue == 174 && blueValue == 0) {
                        if (mLeftList.size() > 0) {
                            Position lastPoint = mLeftList.get(mLeftList.size() - 1);
                            if (Math.abs(j - lastPoint.y) > 100 || Math.abs((i - lastPoint.x)) > 100) {
                                isChangeArray = true;
                            }
                            if (!isChangeArray) {
                                mLeftList.add(new Position(i, j));
                            } else {
                                mRightList.add(new Position(i, j));
                            }

                        } else {
                            mLeftList.add(new Position(i, j));
                        }

                    }
                }
            }
            mHandler.sendEmptyMessageDelayed(MSG_WHAT_ALL_POINTS, 500);
            Log.i(TAG, "mLeftList size:" + mLeftList.size() + " mRightList size:" + mRightList.size());

            Collections.sort(mLeftList);
            mNewLeftList.add(mLeftList.get(0));
            double gap1 = mLeftList.size()*1.0f / (TARGET_POINT-1);
            Log.i(TAG, "gap1:" + gap1);
            for (int i = 1; i < (TARGET_POINT-1); i ++) {
                int index = (int) Math.ceil(gap1 * i);
                mNewLeftList.add(mLeftList.get(index));
            }
            mNewLeftList.add(mLeftList.get(mLeftList.size() - 1));

            Collections.sort(mRightList);
            mNewRightList.add(mRightList.get(0));
            double gap2 = (mRightList.size()-1)*1.0f / (TARGET_POINT-1);
            Log.i(TAG, "gap2:" + gap2);
            for (int i = 1; i < (TARGET_POINT-1); i++) {
                int index = (int) Math.ceil(gap2 * i);
                mNewRightList.add(mRightList.get(index));
            }
            mNewRightList.add(mRightList.get(mRightList.size() - 1));

            Log.i(TAG, "mNewLeftList size:" + mNewLeftList.size());
            Log.i(TAG, "mNewRightList size:" + mNewRightList.size());
            mHandler.sendEmptyMessageDelayed(MSG_WHAT_TARGET_POINTS, 2000);

        }
    }


    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_main);
        myView = (MyView) findViewById(R.id.myView);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.line);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        Log.i(TAG, "onCreate: height:" + height + " width:" + width);
        new Thread(new MyTask(bitmap)).start();
    }


}
