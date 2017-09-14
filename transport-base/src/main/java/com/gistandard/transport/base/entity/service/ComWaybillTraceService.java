package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.ComWaybillTrace;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.base.exception.MobileStationBizException;

import java.util.List;

/**
 * @author yujie
 * @ClassName ComWaybillTrace
 * @Description
 * @Version 1.0
 * @Date 2015-12-18
 */
public interface ComWaybillTraceService {
    Integer insert(ComWaybillTrace comWaybillTrace);

    List<ComWaybillTrace> queryWaybillListByCondition(ComWaybillTrace comWaybillTrace) throws MobileStationBizException;
}
