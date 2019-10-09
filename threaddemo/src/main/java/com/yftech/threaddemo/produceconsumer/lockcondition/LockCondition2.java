package com.yftech.threaddemo.produceconsumer.lockcondition;

import com.yftech.threaddemo.produceconsumer.AbsConsumer;
import com.yftech.threaddemo.produceconsumer.AbsProducer;
import com.yftech.threaddemo.produceconsumer.Model;
import com.yftech.threaddemo.produceconsumer.Product;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by colin on 18-1-18.
 */

public class LockCondition2 implements Model {
    /**
     * @param <A> 实现自己的队列
     *            在头部出队，尾部入队
     *            在poll中只操作head
     *            在offer中只操作tail
     */
    private static class MyQueue<A> {
        private Node head;
        private Node tail;

        MyQueue() {
            tail = head = new Node(null);//两个对象最终指向一个对象，这个对象只有两个头尾节点，节点里面没有元素，并且最多放入一个元素
        }

        public void offer(A a) {
            tail.next = new Node(a);//将元素从尾部节点
            tail = tail.next;//将尾部节点指向尾部元素
        }

        public A poll() {
            head = head.next;//从头部取出之前放入的元素
            A a = head.item;//通过头部节点访问元素
            head.item = null;//元素被取出
            return a;
        }

        private class Node {
            A item;
            Node next;//一个属性，表示指向的下一个节点

            Node(A item) {
                this.item = item;
            }
        }

    }

    private final Lock CONSUME_LOCK = new ReentrantLock();
    private final Condition NOT_EMPTY = CONSUME_LOCK.newCondition();//没空就消费，没空才能拿到锁;消费--非空
    private final Lock PRODUCE_LOCK = new ReentrantLock();
    private final Condition NOT_FULL = PRODUCE_LOCK.newCondition();//没满就生产,没满才能拿到锁;生产--非满
    private MyQueue<Product> queue = new MyQueue<>();
    private final AtomicInteger number = new AtomicInteger(0);
    private final AtomicInteger queueSize = new AtomicInteger(0);
    private int capacity;


    public LockCondition2(int capacity) {
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

    private class ProducerImpl extends AbsProducer {
        @Override
        public void produce() throws InterruptedException {
            Thread.sleep((long) (Math.random() * 1000));
            int newSize = -1;
            //生产之前要先获取锁
            PRODUCE_LOCK.lockInterruptibly();//要一个个的来生产
            try {
                while (capacity == queueSize.get()) {
                    System.out.println("queue is full...");
                    NOT_FULL.await();//满了，要等一下才能not_full
                }
                Product product = new Product(number.getAndIncrement());
                queue.offer(product);
                System.out.println("product:" + product.getNumber());
                newSize = queueSize.incrementAndGet();
                if (newSize < capacity) {
                    NOT_FULL.signalAll();
                }
            } finally {
                PRODUCE_LOCK.unlock();
            }
            //产品非空，通知消费者来消费
            if (newSize > 0) {
                CONSUME_LOCK.lockInterruptibly();
                try {
                    NOT_EMPTY.signalAll();
                } finally {
                    CONSUME_LOCK.unlock();
                }
            }


        }
    }

    private class ConsumerImpl extends AbsConsumer {
        @Override
        public void consume() throws InterruptedException {
            int newSize;
            CONSUME_LOCK.lockInterruptibly();
            try {
                while (queueSize.get() == 0) {
                    System.out.println("queue is Empty...");
                    NOT_EMPTY.await();
                }
                Product product = queue.poll();
                newSize = queueSize.decrementAndGet();
                // 固定时间范围的消费，模拟相对稳定的服务器处理过程
                Thread.sleep(500 + (long) (Math.random() * 500));
                System.out.println("consume:" + product.getNumber());
                if (newSize > 0) {
                    NOT_EMPTY.signalAll();
                }
            }finally {
                CONSUME_LOCK.unlock();
            }
            if (newSize <capacity) {
                PRODUCE_LOCK.lockInterruptibly();
                try{
                    NOT_FULL.signalAll();
                }finally {
                    PRODUCE_LOCK.unlock();
                }
            }

        }

    }

    public static void main(String[] args) {
        Model model = new LockCondition2(3);
        for (int i = 0; i < 5; i++) {
            new Thread(model.newProducer()).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(model.newConsumer()).start();
        }

    }
}
