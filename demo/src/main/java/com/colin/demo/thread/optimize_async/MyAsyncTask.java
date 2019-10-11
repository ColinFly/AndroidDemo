package com.colin.demo.thread.optimize_async;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

public class MyAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> implements IPublishProgress<Progress> {

    private IPreExecute mPreExecute;
    private IPostExecute mPostExecute;
    private IProgressUpdate<Progress> mProgressUpdate;
    private IDoInBackground<Params, Progress, Result> mDoInBackground;
    private IIsViewActive mViewActive;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mPreExecute != null && (mViewActive == null || mViewActive.isViewActive())) {
            mPreExecute.onPreExecute();
        }
    }

    @Override
    protected Result doInBackground(Params... params) {
        return mDoInBackground == null ? null : mDoInBackground.doInBackground(this, params);
    }


    @Override
    protected void onProgressUpdate(Progress... values) {
        if (mProgressUpdate != null && (mViewActive == null || mViewActive.isViewActive())) {
            mProgressUpdate.onProgressUpdate(values);
        }

    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        if (mPostExecute != null && (mViewActive == null || mViewActive.isViewActive())) mPostExecute.onPostExecute(result);
    }


    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    public void showProgress(Progress... values) {
        this.publishProgress(values);
    }


    @SafeVarargs
    public final AsyncTask<Params, Progress, Result> start(Params... params) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return super.executeOnExecutor(THREAD_POOL_EXECUTOR, params);
        } else {
            return super.execute(params);
        }
    }

    private static final String TAG = "MyAsyncTask";
    public void stop(boolean interrupt) {
        this.cancel(interrupt);
        Log.i(TAG, "stop: "+this.toString());
    }




    public static <Params, Progress, Result> Builder<Params, Progress, Result> newBuilder(){
        return new Builder<>();
    }


    public static class Builder<Params, Progress, Result> {

        public Builder() {
            mAsyncTask = new MyAsyncTask<>();
        }

        private final MyAsyncTask<Params, Progress, Result> mAsyncTask;

        public Builder<Params, Progress, Result> setPreExecute(IPreExecute preExecute) {
            mAsyncTask.mPreExecute = preExecute;
            return this;
        }

        public Builder<Params, Progress, Result> setProgressUpdate(IProgressUpdate<Progress> progressUpdate) {
            mAsyncTask.mProgressUpdate = progressUpdate;
            return this;
        }

        public Builder<Params, Progress, Result> setDoInBackground(IDoInBackground<Params, Progress, Result> doInBackground) {
            mAsyncTask.mDoInBackground = doInBackground;
            return this;
        }

        public Builder<Params, Progress, Result> setViewActive(IIsViewActive viewActive) {
            mAsyncTask.mViewActive = viewActive;
            return this;
        }

        public Builder<Params, Progress, Result> setPostExecute(IPostExecute<Result> postExecute) {
            mAsyncTask.mPostExecute = postExecute;
            return this;
        }

        @SafeVarargs
        public final AsyncTask<Params, Progress, Result> start(Params... params) {
            return mAsyncTask.start(params);
        }


    }
}
