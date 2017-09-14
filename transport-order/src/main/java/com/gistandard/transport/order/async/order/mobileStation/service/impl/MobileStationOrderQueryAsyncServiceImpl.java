package com.gistandard.transport.order.async.order.mobileStation.service.impl;

import com.gistandard.transport.app.dubbo.order.bean.MobileMyOrderListReq;
import com.gistandard.transport.app.dubbo.order.bean.MobileUserOrderListReq;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.order.async.order.mobileStation.service.MobileStationOrderQueryAsyncService;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileUserOrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by m on 2016/11/14.
 */
@Service
public class MobileStationOrderQueryAsyncServiceImpl implements MobileStationOrderQueryAsyncService {
    private final Logger logger = LoggerFactory.getLogger(MobileStationOrderQueryAsyncServiceImpl.class);

    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;
    @Autowired
    private MobileUserOrderDao mobileUserOrderDao;

    @Override
    @Async
    public Future<Integer> getMyOrderListCount(MobileMyOrderListReq myOrderListReq) {
        long start = System.currentTimeMillis();
        int recordCount;
        if (myOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
            recordCount = mobileMyOrderDao.getMiOrderListCount(myOrderListReq);
        } else {
            recordCount = mobileMyOrderDao.getMyOrderListCount(myOrderListReq);
        }
        logger.info("recordCount ---> " + recordCount);
        long end = System.currentTimeMillis();
        System.out.println("Async " + (end - start));
        return new AsyncResult<>(recordCount);
    }

    @Override
    @Async
    public Future<Integer> queryListCount(MobileUserOrderListReq mobileUserOrderListReq) {
        long start = System.currentTimeMillis();
        // 查询订单总条数
        int recordCount;
        if (mobileUserOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
            recordCount = mobileUserOrderDao.getMiUserOrderListCount(mobileUserOrderListReq);
        } else {
            recordCount = mobileUserOrderDao.getUserOrderListCount(mobileUserOrderListReq);
        }
        logger.info("recordCount ---> " + recordCount);
        long end = System.currentTimeMillis();
        System.out.println("Async " + (end - start));
        return new AsyncResult<>(recordCount);
    }

}
