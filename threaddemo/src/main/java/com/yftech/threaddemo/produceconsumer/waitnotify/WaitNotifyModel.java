package com.yftech.threaddemo.produceconsumer.waitnotify;

import com.yftech.threaddemo.produceconsumer.AbsConsumer;
import com.yftech.threaddemo.produceconsumer.AbsProducer;
import com.yftech.threaddemo.produceconsumer.Model;
import com.yftech.threaddemo.produceconsumer.Product;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by colin on 18-1-17.
 */

public class WaitNotifyModel implements Model{

    private int capacity;

    public WaitNotifyModel(int capacity) {
        this.capacity = capacity;
    }

    private final Object QUEUE_LOCK =new Object();
    private AtomicInteger number=new AtomicInteger(0);
    private Queue<Product> queue=new LinkedList<>();


    @Override
    public Runnable newConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newProducer() {
        return new ProducerImpl();
    }

    private class ProducerImpl extends AbsProducer {
        @Override
        public void produce() throws InterruptedException {
            Thread.sleep((long) (Math.random()*1000));
            //获取到锁才能生产
            synchronized (QUEUE_LOCK) {
                while (queue.size() == capacity) {
                    QUEUE_LOCK.wait();
                }
                Product product = new Product(number.getAndIncrement());
                queue.offer(product);
                System.out.println("produce:"+product.getNumber());
                QUEUE_LOCK.notifyAll();
            }
        }
    }

    private class ConsumerImpl extends AbsConsumer {
        @Override
        public void consume() throws InterruptedException {
            synchronized (QUEUE_LOCK) {
                while (queue.size() == 0) {
                    QUEUE_LOCK.wait();
                }
                Product product = queue.poll();
                // 固定时间范围的消费，模拟相对稳定的服务器处理过程
                Thread.sleep(500 + (long) (Math.random() * 500));
                System.out.println("consume:" + product.getNumber());
                QUEUE_LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        WaitNotifyModel model=new WaitNotifyModel(2);
        for (int i = 0; i < 3; i++) {
            new Thread(model.newConsumer()).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(model.newProducer()).start();
        }
    }

}
