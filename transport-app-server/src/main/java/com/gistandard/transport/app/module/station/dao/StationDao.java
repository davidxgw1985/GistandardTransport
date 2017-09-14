package com.gistandard.transport.app.module.station.dao;

import com.gistandard.transport.app.module.station.bean.StationInfo;
import com.gistandard.transport.base.annotation.MyBatisRepository;

import java.util.List;

/**
 * Created by yujie on 2017/5/3.
 */
@MyBatisRepository
public interface StationDao {

    List<StationInfo> batchQueryStationInfo(List<String> accountList);

}
