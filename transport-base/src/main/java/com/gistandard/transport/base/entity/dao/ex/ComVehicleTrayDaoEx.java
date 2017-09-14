package com.gistandard.transport.base.entity.dao.ex;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleTray;

import java.util.List;

/**
 * 托架拓展DAO
 * 
 * @author ShengHao
 * 
 *         2016-3-23下午2:30:04
 */
@MyBatisRepository
public interface ComVehicleTrayDaoEx {

	/**
	 * 根据设备主键查询该设备下面的所有托架列表
	 */
	List<ComVehicleTray> queryListByVeicleId(Integer veicleId);

}
