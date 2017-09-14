package com.gistandard.transport.app.module.address.service;

import com.gistandard.transport.base.entity.bean.ComTown;

import java.util.List;

/**
 * Created by shenzhijun on 2016/11/25.
 */
public interface TownService {

    /**
     * 区县查询街道
     * @param countyId
     * @return
     */
    List<ComTown> getTownListByConutyId(Integer countyId);
}
