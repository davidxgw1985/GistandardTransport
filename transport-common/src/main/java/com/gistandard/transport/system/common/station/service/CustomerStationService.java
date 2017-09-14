package com.gistandard.transport.system.common.station.service;


import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.station.bean.*;

import java.util.List;

/**
 * @author kongxm
 * @ClassName CustomerStationService
 * @Description 服务商管理
 * @Version 1.0
 * @Date 2016/1/28
 */
public interface CustomerStationService {
    /**
     * 查询服务商(站点)
     *
     * @param req
     * @return
     */
    List<Station> query(StationQueryReq req);

    /**
     * 查询服务商(站点)总条数
     *
     * @param req
     * @return
     */
    int queryCount(StationQueryReq req);

    /**
     * 查询附近的服务商(站点)
     *
     * @return
     */
    List<Station> queryNearby(StationQueryNearbyReq req);

    /**
     * 查询附近的服务商(站点)总条数
     *
     * @return
     */
    int queryNearbyCount(StationQueryNearbyReq req);

    /**
     * 关注服务商
     *
     * @param req
     */
    void follow(StationFollowReq req) throws MobileStationBizException;

    /**
     * 取消关注服务商
     *
     * @param req
     */
    void unfollow(StationUnFollowReq req) throws MobileStationBizException;

    /**
     * 查询我的关注
     *
     * @param req
     */
    List<Station> queryFollow(AppBasePagerRequest req) throws MobileStationBizException;

    /**
     * 查询关注服务商个数
     *
     * @param req
     * @return
     */
    int queryFollowCount(AppBasePagerRequest req);

    /**
     * 客户下单查询商业中心业务员列表
     * @param req
     * @return
     */
    QueryBusinessRelationResult queryBusinessRelation(QueryBusinessRelationReq req);
}
