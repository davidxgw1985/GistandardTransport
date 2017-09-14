package com.gistandard.transport.system.frame.disruptor.service;

import com.gistandard.transport.base.entity.bean.ComWaybillTrace;

import java.util.List;

/**
 * Created by m on 2016/12/7.
 */
public interface DisruptorOrderService {
    void batchInsertComWaybillTrace(List<ComWaybillTrace> list);
}
