package com.colin.demo.mvp.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TaskManager {
    private static TaskManager instance;
    private ExecutorService threadPoolExecutor;
    private ExecutorService serialTaskExecutor;

    private TaskManager() {
        threadPoolExecutor = Executors.newFixedThreadPool(3);
        serialTaskExecutor = Executors.newSingleThreadExecutor();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            synchronized (TaskManager.class) {
                if (instance == null) {
                    instance = new TaskManager();
                }
            }
        }
        return instance;
    }

    public void post(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }


    public void enqueue(Runnable task) {
        serialTaskExecutor.execute(task);
    }


    public void shutdownPostTask() {
        threadPoolExecutor.shutdown();
    }

    public void shutdownSerialTask() {
        serialTaskExecutor.shutdown();
    }


    /**
     * 关闭所有任务线程
     */
    public void onDestory() {
        shutdownPostTask();
        threadPoolExecutor = null;
        shutdownSerialTask();
        serialTaskExecutor = null;
        instance = null;
    }
}
