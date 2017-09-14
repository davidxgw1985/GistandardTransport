package com.gistandard.transport.base.entity.dao.ex;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleInfoRecord;

import java.util.List;

@MyBatisRepository
public interface ComVehicleInfoRecordDaoEx {

    /**
     * 根据帐户ID查询
     * @return
     */
    List<ComVehicleInfoRecord> queryByAccountId(Integer accountId);

    /**
     * 根据帐户ID删除
     * @param accountId
     * @return
     */
    Integer deleteByAccountId(Integer accountId);

    /**
     * 根据车牌号查询
     * @param truckCode
     * @return
     */
    List<ComVehicleInfoRecord> queryVehicleByTruckCode(String truckCode);
}