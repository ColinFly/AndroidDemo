package com.yftech.threaddemo.produceconsumer.blockingqueue;

import com.yftech.threaddemo.produceconsumer.AbsConsumer;
import com.yftech.threaddemo.produceconsumer.AbsProducer;
import com.yftech.threaddemo.produceconsumer.Model;
import com.yftech.threaddemo.produceconsumer.Product;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by colin on 18-1-17.
 * 实现生产者额消费者模型
 * BlockingQueue的写法最简单。
 * 核心思想是，把并发和容量控制封装在缓冲区中。而BlockingQueue的性质天生满足这个要求。
 */

public class BlockingQueueModel implements Model {
    //放产品的阻塞队列
    private BlockingQueue<Product> queue;
    //记录产品数量的变量
    private AtomicInteger number = new AtomicInteger(0);

    /**
     * @param capacity 　阻塞队列放置元素的个数
     */
    public BlockingQueueModel(int capacity) {
        // LinkedBlockingQueue 的队列是 lazy-init 的，但 ArrayBlockingQueue 在创建时就已经 init
        queue = new LinkedBlockingQueue<>(capacity);
    }


    @Override
    public Runnable newConsumer() {
        return new ConsumerImp();
    }

    @Override
    public Runnable newProducer() {
        return new ProducerImpl();
    }

    private class ConsumerImp extends AbsConsumer {

        @Override
        public void consume() throws InterruptedException {
            Product product = queue.take();
            // 固定时间范围的消费，模拟相对稳定的服务器处理过程
            Thread.sleep((long) (500 + Math.random() * 500));
            synchronized (this) {
                System.out.println(Thread.currentThread() + "consumer:" + product.getNumber());
            }
        }
    }


    private class ProducerImpl extends AbsProducer {
        @Override
        public void produce() throws InterruptedException {
            // 不定期生产，模拟随机的用户请求
            Thread.sleep((long) (Math.random() * 1000));
            Product product = new Product(number.getAndIncrement());
//            queue.add(product)//注意这里如果add就会报异常，使用put才能变成阻塞队列
            queue.put(product);
            synchronized (this) {
                System.out.println(System.currentTimeMillis() + "  " + Thread.currentThread() + "produce:" + product.getNumber());
            }
        }
    }

    public static void main(String[] args) {
        //创建模型
        Model model = new BlockingQueueModel(1);
        //生产者生产
//        for (int i = 0; i < 3; i++) {
//        }
        //消费者消费

        for (int i = 0; i < 2; i++) {
            new Thread(model.newConsumer()).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(model.newProducer()).start();
        }

    }


}
