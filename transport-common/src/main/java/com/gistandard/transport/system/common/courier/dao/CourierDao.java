package com.gistandard.transport.system.common.courier.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.system.common.courier.bean.Courier;
import com.gistandard.transport.system.common.courier.bean.CourierQueryNearbyReq;
import com.gistandard.transport.system.common.courier.bean.CourierQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kongxm
 * @Description
 * @Version 1.0
 * @Date 2016/3/9
 */
@MyBatisRepository
public interface CourierDao {
    List<Courier> query(CourierQueryReq req);

    int queryCount(CourierQueryReq req);

    List<Courier> queryFollow(AppBasePagerRequest req);

    int queryFollowCount(AppBasePagerRequest req);

    List<Courier> queryNearby(CourierQueryNearbyReq req);

    int queryNearbyCount(CourierQueryNearbyReq req);

    Courier queryCourierByO2id(@Param("o2id")String o2id);
}
