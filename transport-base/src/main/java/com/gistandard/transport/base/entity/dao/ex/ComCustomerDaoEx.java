package com.gistandard.transport.base.entity.dao.ex;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 企业拓展DAO
 * 
 * @author ShengHao
 * 
 *         2016-2-27下午3:27:43
 */
@MyBatisRepository
public interface ComCustomerDaoEx {

	/**
	 * 根据账户删除企业信息
	 * 
	 * @param accountId
	 *            账户ID
	 */
	Integer deleteByAccountId(Integer accountId);

	/**
	 * 根据企业主键查询该企业的GISNO账号
	 * 
	 * @param customerId
	 *            企业主键
	 */
	String queryGisNoByCustomerId(Integer customerId);


    /**
     * 根据ACCOUNT_ID查询企业信息
     * @param acctId
     * @return
     */
    ComCustomer queryCustomerInfoByAcctId(Integer acctId);

	/**
	 * 根据企业简称 或 企业编号查找企业信息
	 * @param custTtl 企业简称
	 * @param customNo 企业编号
	 * @return
	 */
	List<ComCustomer> queryCompanyByParams(@Param(value = "custTtl") String custTtl, @Param(value = "customNo") String customNo);
	
	/**
	 * 根据站点编码获取站点数据
	 * @param custTtl
	 * @return
	 */
	ComCustomer getComCustomerByCustTtl(String custTtl);

	/**
	 * 根据站点编码获取站点账号
	 * @param custTtl
	 * @return
	 */
	String getAcctUserNameByCustTtl(@Param(value = "custTtl")String custTtl);
}
