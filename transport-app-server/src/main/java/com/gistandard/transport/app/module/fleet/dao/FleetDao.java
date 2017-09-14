package com.gistandard.transport.app.module.fleet.dao;

import com.gistandard.transport.app.module.fleet.bean.QueryFleetListBean;
import com.gistandard.transport.app.module.fleet.bean.QueryFleetListReq;
import com.gistandard.transport.base.annotation.MyBatisRepository;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: FleetDao
 * @Date: 2016/3/7
 * @Description: 车队列表查询
 */
@MyBatisRepository
public interface FleetDao {
    /**
     * 车队列表查询
     *
     * @param queryFleetListReq
     * @return
     */
    List<QueryFleetListBean> queryFleetList(QueryFleetListReq queryFleetListReq);

}
