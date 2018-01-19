package com.yftech.threaddemo.produceconsumer.lockcondition;

import com.yftech.threaddemo.produceconsumer.AbsConsumer;
import com.yftech.threaddemo.produceconsumer.AbsProducer;
import com.yftech.threaddemo.produceconsumer.Model;
import com.yftech.threaddemo.produceconsumer.Product;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by colin on 18-1-17.
 * 这种实现由于生产者和消费者共同一个锁，同一时刻将只有一个线程在跑，根本没有并发
 * 如何让生产者和生产者是串行的，生产者和消费者是并行的呢?
 */

public class LockCondition implements Model {
    private final Lock QUEUE_LOCK = new ReentrantLock();
    private final Condition QUEUE_CONDITION = QUEUE_LOCK.newCondition();
    private Queue<Product> queue = new LinkedList<>();
    private AtomicInteger number = new AtomicInteger(0);
    private int capacity;

    public LockCondition(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Runnable newConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newProducer() {
        return new ProducerImpl();
    }


    private class ConsumerImpl extends AbsConsumer {
        @Override
        public void consume() throws InterruptedException {
            QUEUE_LOCK.lockInterruptibly();
            try {
                while (queue.size() == 0) {
                    QUEUE_CONDITION.await();
                }
                Thread.sleep(500 + (long) (Math.random() * 500));
                Product product = queue.poll();
                System.out.println("consume:" + product.getNumber());
                QUEUE_CONDITION.signalAll();
            } finally {
                QUEUE_LOCK.unlock();
            }
        }
    }


    private class ProducerImpl extends AbsProducer {
        @Override
        public void produce() throws InterruptedException {
            // 不定期生产，模拟随机的用户请求
            Thread.sleep((long) (Math.random() * 1000));
            QUEUE_LOCK.lockInterruptibly();
            try {
                while (queue.size() == capacity) {
                    QUEUE_CONDITION.await();
                }
                Product product = new Product(number.getAndIncrement());
                // 固定时间范围的消费，模拟相对稳定的服务器处理过程
                queue.offer(product);
                System.out.println("produce:" + product.getNumber());
                QUEUE_CONDITION.signalAll();
            } finally {
                QUEUE_LOCK.unlock();
            }

        }
    }

    public static void main(String[] args) {
        Model model = new LockCondition(3);


        for (int i = 0; i < 5; i++) {
            new Thread(model.newProducer()).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(model.newConsumer()).start();
        }
    }
}
