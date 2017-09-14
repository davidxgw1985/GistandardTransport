package com.gistandard.transport.system.common.courier.service;


import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.courier.bean.*;

import java.util.List;

/**
 * @author kongxm
 * @Description
 * @Version 1.0
 * @Date 2016/3/8
 */
public interface CourierService {
    public List<Courier> query(CourierQueryReq req);

    public int queryCount(CourierQueryReq req);

    public List<Courier> queryNearby(CourierQueryNearbyReq req);

    public int queryNearbyCount(CourierQueryNearbyReq req);

    public void follow(CourierFollowReq req) throws MobileStationBizException;

    public void unfollow(CourierUnFollowReq req) throws MobileStationBizException;

    public List<Courier> queryFollow(AppBasePagerRequest req) throws MobileStationBizException;

    public int queryFollowCount(AppBasePagerRequest req) throws MobileStationBizException;
}
