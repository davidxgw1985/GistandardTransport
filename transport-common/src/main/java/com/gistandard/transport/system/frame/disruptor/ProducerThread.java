package com.gistandard.transport.system.frame.disruptor;

import java.util.List;

/**
 * Created by m on 2016/12/6.
 */
public class ProducerThread<T> implements Runnable {
    private List<T> msg;

    @Override
    public void run() {
        DisruptorHelper.getInstance().producer(msg);
    }

    public ProducerThread(List<T> msg) {
        this.msg = msg;
    }
}
