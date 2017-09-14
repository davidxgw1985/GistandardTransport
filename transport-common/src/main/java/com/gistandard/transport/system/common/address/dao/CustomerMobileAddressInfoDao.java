package com.gistandard.transport.system.common.address.dao;

import java.util.List;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.system.common.bean.AddressQueryRes;
import com.gistandard.transport.system.common.bean.AddressReq;
import com.gistandard.transport.system.common.bean.QueryAddressReq;

/**
 * @author kongxm
 * @ClassName AddressDao
 * @Description 地址管理dao
 * @Version 1.0
 * @Date 2016/1/26
 */
@MyBatisRepository
public interface CustomerMobileAddressInfoDao {
	List<AddressQueryRes> queryByAccountId(QueryAddressReq req);

	List<AddressQueryRes> queryByCondition(AddressReq req);

	AddressQueryRes queryByPrimaryKey(Integer id);

	Integer totalCount(QueryAddressReq req);

}
