package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ComAccountDaoEx {

	ComAccount queryByAccount(String acct);

	/**
	 * 根据账号查询账号所属蛙站账号信息
	 * @param acct
	 * @return
	 */
	ComAccount queryWaInfoByAccount(String acct);


	/**
	 * 根据收货人电话号码获取账号信息
	 * @param telephone
	 * @return
	 */
	ComAccount queryAccountByTelephone(String telephone);


	/**
	 * 根据账号或电话号码获取账号信息
	 * @param param
	 * @return
	 */
	ComAccount queryAccountByParam(String param);

	List<ComAccount> queryUnbindDriver(@Param("companyAccountId")Integer companyAccountId);
}