package com.yftech.threaddemo.produceconsumer;

/**
 * Created by colin on 18-1-17.
 */

public abstract class AbsConsumer implements Consumer,Runnable {
    @Override
    public void consume() throws InterruptedException {

    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

    }
}
