package com.gistandard.transport.system.frame.disruptor.service;

import com.gistandard.transport.system.frame.disruptor.MsgEvent;

/**
 * Created by m on 2016/12/7.
 */
public interface DisruptorService {
    void doDisruptorService(MsgEvent msgEvent);
}
