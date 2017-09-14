package com.gistandard.transport.system.frame.disruptor.service.impl;

import com.gistandard.transport.base.entity.bean.ComWaybillTrace;
import com.gistandard.transport.system.frame.disruptor.MsgEvent;
import com.gistandard.transport.system.frame.disruptor.service.DisruptorOrderService;
import com.gistandard.transport.system.frame.disruptor.service.DisruptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by m on 2016/12/7.
 */
@Service("disruptorService")
public class DisruptorServiceImpl implements DisruptorService {
    @Autowired
    private DisruptorOrderService disruptorOrderService;

    @Override
    public void doDisruptorService(MsgEvent msgEvent) {
        List list = msgEvent.get();
        Object o = list.get(0);
        if (o instanceof ComWaybillTrace){
            disruptorOrderService.batchInsertComWaybillTrace(list);
        }
    }

}
