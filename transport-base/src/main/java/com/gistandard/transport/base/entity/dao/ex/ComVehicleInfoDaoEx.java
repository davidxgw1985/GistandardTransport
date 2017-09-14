package com.gistandard.transport.base.entity.dao.ex;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ComVehicleInfoDaoEx {

	/**
	 * 根据账号号查询设备列表信息
	 * 
	 * @param acctId
	 *            账户ID 主键
	 * @return 当前账户对应的设备列表信息
	 */
	List<ComVehicleInfo> queryVehicleByAcctId(Integer acctId);

	/**
	 * 清除该账户下面所有的设备列表信息
	 * 
	 * @param id
	 *            账户主键
	 */
	Integer deleteByVehicleId(@Param("id")Integer id,@Param("companyAccountId")Integer companyAccountId);

	/**
	 * 删除车厢信息
	 * 
	 * @param acctId
	 * @return
	 */
	Integer deleteCarriesByVehicleId(Integer acctId);

	/**
	 * 删除托架信息
	 * 
	 * @param acctId
	 * @return
	 */
	Integer deleteTraiesByVehicleId(Integer acctId);

	List<ComVehicleInfo> queryAll();

	List<ComVehicleInfo> queryVehicleByTruckCode(String truckCode);

}
