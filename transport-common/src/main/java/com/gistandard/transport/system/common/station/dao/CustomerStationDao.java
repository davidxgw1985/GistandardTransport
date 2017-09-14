package com.gistandard.transport.system.common.station.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.system.common.station.bean.Station;
import com.gistandard.transport.system.common.station.bean.StationQueryNearbyReq;
import com.gistandard.transport.system.common.station.bean.StationQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CustomerStationDao {
    List<Station> query(StationQueryReq req);

    /**
     * 查询服务商(站点)总条数
     *
     * @param req
     * @return
     */
    int queryCount(StationQueryReq req);

    /**
     * 查询关注服务商(站点)
     * @param req
     * @return
     */
    List<Station> queryFollow(AppBasePagerRequest req);

    /**
     *
     * 查询关注服务商总数
     *
     * @param req
     * @return
     */
    int queryFollowCount(AppBasePagerRequest req);

    /**
     * 查询附近服务商
     *
     * @param req
     * @return
     */
    List<Station> queryNearby(StationQueryNearbyReq req);

    /**
     * 查询附近服务商(站点)总条数
     *
     * @param req
     * @return
     */
    int queryNearbyCount(StationQueryNearbyReq req);


    Station queryByStationCode(String stationCode);

    Station queryByStationO2id(@Param("o2id")String o2id);
}