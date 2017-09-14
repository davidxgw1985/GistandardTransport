package com.gistandard.transport.base.entity.dao.ex;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleCarriage;

import java.util.List;

/**
 * 车辆车辆信息拓展DAO
 * 
 * @author ShengHao
 * 
 *         2016-3-23下午2:10:36
 */
@MyBatisRepository
public interface ComVehicleCarriageDaoEx {

	/**
	 * 根据设备主键查询该设备拥有的所有的车厢列表
	 */
	List<ComVehicleCarriage> queryListByVehicleId(Integer vehicleId);

}
