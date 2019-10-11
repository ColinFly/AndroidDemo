package com.colin.demo.thread.optimize_async;

public interface IDoInBackground<Params,Progress, Result> {
    Result doInBackground(IPublishProgress<Progress> publishProgress, Params... params);

}
