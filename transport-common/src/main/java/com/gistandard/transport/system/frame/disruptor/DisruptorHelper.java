package com.gistandard.transport.system.frame.disruptor;

import com.gistandard.transport.system.frame.disruptor.service.DisruptorService;
import com.gistandard.transport.tools.util.SpringContextUtil;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by m on 2016/12/5.
 */
public class DisruptorHelper<T> {
    private static final Logger logger = LoggerFactory.getLogger(DisruptorHelper.class);

    private MsgEventHandler eventHandler = null;

    private final int BUFFER_SIZE = 1024;

    private Disruptor<MsgEvent> disruptor = null;

    public static DisruptorHelper getInstance(){
        return DisruptorHelperHolder.instance;
    }

    private DisruptorHelper(){
        eventHandler = new MsgEventHandler();
        disruptor = new Disruptor<>(new MsgEventFactory(), BUFFER_SIZE,
                new ConsumerThreadFactory(), ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.handleEventsWith(eventHandler);

        logger.info("disruptor init");
    }

    public void producer(List<T> msg){
        RingBuffer<MsgEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();
        try {
            MsgEvent msgEvent = ringBuffer.get(sequence);
            msgEvent.set(msg);
        } finally {
            ringBuffer.publish(sequence);
            logger.info("ringBuffer publish sequence --> " + msg);
        }
    }

    public void start(){
        disruptor.start();
        logger.info("disruptor start");
    }

    public void shutdown(){
        disruptor.shutdown();
        logger.info("disruptor shutdown");
    }

    private static class DisruptorHelperHolder {
        public static DisruptorHelper instance = new DisruptorHelper();
    }

    /**
     * Disruptor的消費者，依次從Ring Buffer當中讀取數據並執行相應的處理。
     */
    static final class MsgEventHandler<T> implements EventHandler<MsgEvent<T>>{
        @Autowired
        private DisruptorService disruptorService;

        @Override
        public void onEvent(MsgEvent<T> msgEvent, long l, boolean b) throws Exception {
            if (disruptorService == null){
                disruptorService = (DisruptorService) SpringContextUtil.getBean("disruptorService");
            }
            disruptorService.doDisruptorService(msgEvent);
        }
    }

    static final class MsgEventFactory implements EventFactory<MsgEvent>{

        @Override
        public MsgEvent newInstance() {
            return new MsgEvent();
        }
    }

    static final class ConsumerThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r);
        }
    }
}
