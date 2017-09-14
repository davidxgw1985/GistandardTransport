package com.gistandard.transport.app.module.address.service.impl;

import com.gistandard.transport.app.module.address.service.TownService;
import com.gistandard.transport.base.entity.bean.ComTown;
import com.gistandard.transport.base.entity.dao.ex.ComTownDaoEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenzhijun on 2016/11/25.
 */
@Service
public class TownServiceImpl implements TownService{

    @Autowired
    private ComTownDaoEx comTownDaoEx;

    @Override
    public List<ComTown> getTownListByConutyId(Integer countyId) {
        return comTownDaoEx.selectByCountyId(countyId);
    }
}
