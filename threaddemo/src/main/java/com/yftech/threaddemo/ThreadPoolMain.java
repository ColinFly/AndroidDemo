package com.yftech.threaddemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by colin on 17-1-6.
 */

public class ThreadPoolMain {
    public static void main(String[] args) {
//        newThread();
//        cachedThreadPool();
        fixedThreadPool();
//        scheduledThreadPool();
    }


    Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public static int mark;

    /**
     * 计划任务线程,可用于定时器，周期执行任务等，好用!!
     */
    private static void scheduledThreadPool() {
        final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        //延迟3秒后执行任务
//        scheduledThreadPool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("delay 3s and execute");
//            }
//        },3, TimeUnit.SECONDS);
        //延迟3s后,每隔3s执行一次任务,怎么停止呢?shutdown
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                    mark++;
                    System.out.println(Thread.currentThread());
                    System.out.println("delay 3 seconds, and excute every 3 seconds,mark:"+mark);
                if (mark == 10) {
                    scheduledThreadPool.shutdown();
                    System.out.println("task has been stopped");
                }
            }
        },5,5, TimeUnit.SECONDS);
    }

    /**
     * 定长线程池，这些线程是并发执行的
     * 最好根据系统资源进行设置
     * Runtime.getRuntime().availableProcessors()
     */
    private static void fixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                    System.out.println(index);
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 可缓存线程池的方式
     * 灵活复用空闲线程
     * 没有可复用进程时才新建线程
     * 线程总数没有上限
     */
    private static void cachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                    System.out.println(index);
                }
            });
        }
    }

    /**
     * 不断new线程的方式执行任务
     */
    private static void newThread() {
        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                    System.out.println(index + "");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
