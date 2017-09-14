package com.gistandard.transport.app.module.address.service;

import java.util.List;

import com.gistandard.transport.app.module.address.bean.AddressRes;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.system.common.bean.AddressQueryRes;
import com.gistandard.transport.system.common.bean.AddressReq;
import com.gistandard.transport.system.common.bean.QueryAddressReq;

/**
 * @author kongxm
 * @ClassName CustomerAddressInfoService
 * @Description 我的地址管理接口
 * @Version 1.0
 * @Date 2016/1/26
 */
public interface CustomerAddressInfoService {
	/**
	 * 添加地址
	 * 
	 * @param address
	 * @return
	 */
	// AddressRes add(AddressReq address) throws CustomerBizException;

	/**
	 * BS添加地址
	 * 
	 * @param address
	 * @return
	 */
	AddressRes addAddress(AddressReq address) throws CustomerBizException;

	/**
	 * 删除，修改状态,并不是物理删除
	 * 
	 * @param addrIds
	 *            地址数组
	 * @return 删除的条数
	 */
	int delete(Integer[] addrIds);

	/**
	 * 更新
	 * 
	 * @param address
	 * @return
	 */
	int update(AddressReq address) throws CustomerBizException;

	/**
	 * 查询用户地址列表
	 * 
	 * @param req
	 * @return
	 */
	List<AddressQueryRes> queryByAccountId(QueryAddressReq req) throws CustomerBizException;

	/**
	 * 设置默认（收货\发货）地址
	 * 
	 * @param req
	 * @return
	 */
	void defaultAddress(AddressReq req) throws CustomerBizException;

	/**
	 * 总条数
	 * 
	 * @param req
	 * @return
	 */
	Integer recordCount(QueryAddressReq req);

	/**
	 * 根据id查询地址
	 * 
	 * @param id
	 * @return
	 */
	AddressQueryRes queryByPrimaryKey(Integer id);

}
