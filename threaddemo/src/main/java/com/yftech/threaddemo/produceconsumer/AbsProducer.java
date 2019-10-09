package com.yftech.threaddemo.produceconsumer;

/**
 * Created by colin on 18-1-17.
 */

public abstract class AbsProducer implements Producer,Runnable {
    @Override
    public void produce() throws InterruptedException {

    }

    @Override
    public void run() {

        while (true) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

    }
}
