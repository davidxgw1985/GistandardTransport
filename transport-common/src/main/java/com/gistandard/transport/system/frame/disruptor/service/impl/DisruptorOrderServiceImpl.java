package com.gistandard.transport.system.frame.disruptor.service.impl;

import com.gistandard.transport.base.entity.bean.ComWaybillTrace;
import com.gistandard.transport.base.entity.dao.ex.ComWaybillTraceDaoEx;
import com.gistandard.transport.system.frame.disruptor.service.DisruptorOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by m on 2016/12/7.
 */
@Service
public class DisruptorOrderServiceImpl implements DisruptorOrderService {
    @Autowired
    private ComWaybillTraceDaoEx comWaybillTraceDaoEx;

    @Override
    public void batchInsertComWaybillTrace(List<ComWaybillTrace> list) {
        comWaybillTraceDaoEx.batchInsert(list);
    }
}
