package com.gistandard.transport.app.module.fleet.service;

import com.gistandard.transport.app.module.fleet.bean.QueryFleetListReq;
import com.gistandard.transport.app.module.fleet.bean.QueryFleetListRes;
import com.gistandard.transport.base.exception.CustomerBizException;

/**
 * @author xgw
 * @Description
 * @Version 1.0
 * @Date 2017/7/18
 */
public interface FleetService {

	QueryFleetListRes queryFleetList(QueryFleetListReq req) throws CustomerBizException;

}
