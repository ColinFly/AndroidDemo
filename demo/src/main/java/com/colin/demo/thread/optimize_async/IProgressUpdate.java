package com.colin.demo.thread.optimize_async;

public interface IProgressUpdate<Progress> {
    void onProgressUpdate(Progress... values);
}
