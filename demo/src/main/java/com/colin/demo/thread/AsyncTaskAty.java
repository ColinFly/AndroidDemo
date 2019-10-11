package com.colin.demo.thread;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.colin.demo.R;
import com.colin.demo.thread.optimize_async.IDoInBackground;
import com.colin.demo.thread.optimize_async.IIsViewActive;
import com.colin.demo.thread.optimize_async.IPostExecute;
import com.colin.demo.thread.optimize_async.IPreExecute;
import com.colin.demo.thread.optimize_async.IProgressUpdate;
import com.colin.demo.thread.optimize_async.IPublishProgress;
import com.colin.demo.thread.optimize_async.MyAsyncTask;


public class AsyncTaskAty extends AppCompatActivity implements View.OnClickListener {


    private TextView mProgressTv;
    private ProgressBar mProgressBar;
    private Button mStartBtn, mCancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        mProgressTv = findViewById(R.id.tv_progress);
        mProgressBar = findViewById(R.id.pb_async_task);
        mStartBtn = findViewById(R.id.btn_start_task);
        mCancelBtn = findViewById(R.id.btn_cancel_task);
        mStartBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
    }

    boolean pause;
    //基础使用方式
    AsynckTask1 asynckTask1 = new AsynckTask1();

    //优化后的使用方式
    //1.全功能调用
    //2.简短的调用
    MyAsyncTask<String, Integer, Boolean> task1;
    private void loadData() {
        //泛型参数化
        task1 = (MyAsyncTask<String, Integer, Boolean>) MyAsyncTask.<String, Integer, Boolean>newBuilder().setPreExecute(new IPreExecute() {
            @Override
            public void onPreExecute() {
                mProgressTv.setText("开始下载");
            }
        }).setDoInBackground(new IDoInBackground<String, Integer, Boolean>() {//产生数据的接口
            @Override
            public Boolean doInBackground(IPublishProgress<Integer> publishProgress, String... strings) {
                for (int i = 0; i < 100; i++) {
                    //原生类的方法引用
                    publishProgress.showProgress(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return false;
                    }

                }
                return true;
            }
        }).setViewActive(new IIsViewActive() {
            @Override
            public boolean isViewActive() {
                return isViewActived();
            }
        }).setProgressUpdate(new IProgressUpdate<Integer>() {
            @Override
            public void onProgressUpdate(Integer... values) {
                mProgressBar.setProgress(values[0]);

            }
        }).setPostExecute(new IPostExecute<Boolean>() {
            @Override
            public void onPostExecute(Boolean aBoolean) {
                mProgressTv.setText("任务完成");
            }
        }).start("参数1");

        Log.i(TAG, "loadData: "+task1.toString());
    }

    private static final String TAG = "AsyncTaskAty";

    private void loadData2() {
         MyAsyncTask<Void, Void, Void> task2 = (MyAsyncTask<Void, Void, Void>) MyAsyncTask.<Void, Void, Void>newBuilder().setDoInBackground(new IDoInBackground<Void, Void, Void>() {
            @Override
            public Void doInBackground(IPublishProgress<Void> publishProgress, Void... voids) {
                //执行数据保存
                return null;
            }
        }).start();
        task2.stop(true);
    }
    /**
     * @return 判断当前Activity是否处于活跃状态
     */
    public boolean isViewActived() {
        return !(isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && isDestroyed()));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_task:
//                asynckTask1.execute();
                loadData();
                break;
            case R.id.btn_cancel_task:
//                asynckTask1.cancel(true);
                task1.stop(true);
                break;
        }
    }

    /**
     * 入参，进度，出参
     * 使用的时候必须指定这三个泛型参数.不使用参数可用Void代替
     * <p>
     * 这个task和ui控件强绑定
     */
    class AsynckTask1 extends AsyncTask<String, Integer, String> {
        private static final String TAG = "AsynckTask1";

        /**
         * ui线程,任务处理前
         */
        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute: ");
            mProgressTv.setText("加载中");
        }

        /**
         * 子线程,处理任务中，产生进度
         */
        @Override
        protected String doInBackground(String... strings) {
            Log.i(TAG, "doInBackground: ");
            int count = 0;
            int len = 1;
            while (count < 99) {
                count += len;
                publishProgress(count);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        /**
         * ui线程，任务处理后
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(TAG, "onPostExecute: ");
            mProgressTv.setText("任务完毕");
        }

        /**
         * ui线程，更新进度
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i(TAG, "onProgressUpdate: ");
            mProgressBar.setProgress(values[0]);
        }


        /**
         * ui线程，显示任务取消了
         */
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i(TAG, "onCancelled: ");
            mProgressBar.setProgress(0);

        }
    }
}
