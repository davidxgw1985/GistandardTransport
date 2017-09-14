package com.gistandard.transport.app.dubbo.fleet.service;

import com.gistandard.transport.app.dubbo.fleet.bean.*;
import com.gistandard.transport.app.dubbo.pojo.res.TableResultDubboBean;
import com.gistandard.transport.app.dubbo.register.bean.RegResultBean;

/**
 * Created by zhuming on 2017/6/19 0019.
 */
public interface VehicleDubboService {

    /**
     * 新增车辆接口
     * @param vehicleInfoBean
     * @return
     * @throws Exception
     */
    RegResultBean addVehicle(VehicleInfoBean vehicleInfoBean) throws Exception;
    /**
     * 更新车辆接口
     * @param vehicleInfoBean
     * @return
     * @throws Exception
     */
    RegResultBean updateVehicle(VehicleInfoBean vehicleInfoBean) throws Exception;
    /**
     * 查询车辆列表 传企业id start limit
     * @param queryVehiclePar
     * @return
     * @throws Exception
     */
    TableResultDubboBean queryVehicle(QueryVehiclePar queryVehiclePar);
    /**
     * 删除车辆 传企业id 车辆id 如果绑定了司机也传司机Id
     * @param vehicleParBean
     * @return
     * @throws Exception
     */
    RegResultBean delVehicle(VehicleParBean vehicleParBean);

    /**
     * 绑定车辆 传企业id 车辆id 司机Id
     * @param vehicleParBean
     * @return
     * @throws Exception
     */
    RegResultBean bindVehicleToDriver(VehicleParBean vehicleParBean);

    /**
     * 绑定车辆 传企业id 车辆id 司机Id
     * @param vehicleParBean
     * @return
     * @throws Exception
     */
    RegResultBean unbindVehicleToDriver(VehicleParBean vehicleParBean);

    /**
     * 绑定企业未绑定司机
     * @param queryunbindDriverPar
     * @return
     * @throws Exception
     */
    TableResultDubboBean queryunbindDriver(QueryunbindDriverPar queryunbindDriverPar) throws Exception;

    /**
     * 查询车辆详情
     * @param id
     * @return
     * @throws Exception
     */
    RegResultBean queryVehicleDetails(Integer id) throws Exception;

    RegResultBean queryAccountByPar(QueryAccountPar queryAccountPar);
}
