package com.gistandard.transport.app.module.dubbo.fleet.dao;

import com.gistandard.transport.app.dubbo.fleet.bean.FleetAccountInfo;
import com.gistandard.transport.app.dubbo.fleet.bean.QueryAccountPar;
import com.gistandard.transport.app.dubbo.fleet.bean.QueryVehiclePar;
import com.gistandard.transport.app.dubbo.fleet.bean.QueryunbindDriverPar;
import com.gistandard.transport.base.annotation.MyBatisRepository;

import java.util.List;

/**
 * Created by zhuming on 2017/6/20 0020.
 */

@MyBatisRepository
public interface FleetDubboDao {
    List<FleetAccountInfo> queryVehicle(QueryVehiclePar queryVehiclePar);

    int  queryVehicleCount(QueryVehiclePar queryVehiclePar);

    List<FleetAccountInfo> queryUnbindDriver(QueryunbindDriverPar queryunbindDriverPar);

    int queryUnbindDriverCount(QueryunbindDriverPar queryunbindDriverPar);

    /**
     * 查帐号信息
     * @param queryAccountPar
     * @return
     */
    List<QueryAccountPar> queryAccountByPar(QueryAccountPar queryAccountPar);
}
