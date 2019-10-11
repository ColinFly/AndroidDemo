package com.colin.demo.thread.optimize_async;

public interface IPostExecute<Result> {
    void onPostExecute(Result result);
}
