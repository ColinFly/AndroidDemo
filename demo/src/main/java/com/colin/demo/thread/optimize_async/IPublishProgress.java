package com.colin.demo.thread.optimize_async;

public interface IPublishProgress<Progress> {
    void showProgress(Progress... values);
}
