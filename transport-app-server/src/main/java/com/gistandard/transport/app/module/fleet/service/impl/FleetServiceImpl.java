package com.gistandard.transport.app.module.fleet.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.fleet.bean.QueryFleetListBean;
import com.gistandard.transport.app.module.fleet.bean.QueryFleetListReq;
import com.gistandard.transport.app.module.fleet.bean.QueryFleetListRes;
import com.gistandard.transport.app.module.fleet.dao.FleetDao;
import com.gistandard.transport.app.module.fleet.service.FleetService;
import com.gistandard.transport.base.exception.CustomerBizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xgw
 * @Description
 * @Version 1.0
 * @Date 2017/7/18
 */
@Service
public class FleetServiceImpl implements FleetService {
    private final static Logger logger = LoggerFactory.getLogger(FleetServiceImpl.class);
    @Autowired
    private FleetDao fleetDao;

    /**
     * 车队列表查询
     * @param req
     * @return
     * @throws CustomerBizException
     */
    @Override
    public QueryFleetListRes queryFleetList(QueryFleetListReq req) throws CustomerBizException {

        QueryFleetListRes res = new QueryFleetListRes(req);
        List<QueryFleetListBean> fleetList = fleetDao.queryFleetList(req);
        res.setData(fleetList);
        return res;
    }
}
