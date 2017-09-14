package com.gistandard.transport.order.module.mobilestation.service.impl;

import com.gistandard.transport.base.bean.im.MsgIMReq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by yujie on 2016/10/6.
 */
public class BlockingQueueFactory {
    public static final BlockingQueue<MsgIMReq> getQueue() {
        return SingleBlockingQueueHolder.QUEUE;
    }

    private static class SingleBlockingQueueHolder{
        private static final BlockingQueue<MsgIMReq> QUEUE = new SingletonLinkedBlockingQueue<>(10000);
    }

    private static class SingletonLinkedBlockingQueue<E> extends LinkedBlockingQueue<E> {

        private SingletonLinkedBlockingQueue(){
            super();
        }

        private SingletonLinkedBlockingQueue(int capacity){
            super(capacity);
        }
    }
}
