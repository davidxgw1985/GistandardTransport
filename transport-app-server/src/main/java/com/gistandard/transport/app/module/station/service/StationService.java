package com.gistandard.transport.app.module.station.service;

import com.gistandard.transport.app.module.station.bean.CustomerListBean;
import com.gistandard.transport.app.module.station.bean.GetAllMiMovingByMiStopIdReq;
import com.gistandard.transport.app.module.station.bean.QueryMandWstationReq;
import com.gistandard.transport.base.exception.MobileStationBizException;

import java.util.List;

/**
 * Created by m on 2016/10/8.
 */
public interface StationService {
    /**
     * 查询符合路由的M站和W站
     *
     * @return
     */
    List<CustomerListBean> queryMandWstation(QueryMandWstationReq req) throws MobileStationBizException;

    /**
     * 查询会经过咪站停靠点的所有咪站
     * @param req
     * @return
     * @throws MobileStationBizException
     */
    List<CustomerListBean> getAllMiMovingByMiStopId(GetAllMiMovingByMiStopIdReq req) throws MobileStationBizException;
}
