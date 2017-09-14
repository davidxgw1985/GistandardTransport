package com.gistandard.transport.system.frame.disruptor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by m on 2016/12/5.
 */
public class EventProducer<T> {
    private static EventProducer instance = new EventProducer();

    public static EventProducer getInstance() {
        return instance;
    }

    private EventProducer() {
    }

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);

    public void producerEvent(List<T> tmps) {
        fixedThreadPool.execute(new ProducerThread(tmps));
    }
}
