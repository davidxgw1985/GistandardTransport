package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.ComAccount;

/**
 * @author yujie
 * @ClassName ComAccountService
 * @Description
 * @Version 1.0
 * @Date 2015-09-08
 */
public interface ComAccountService {

	ComAccount queryAccountById(int acctId);

	ComAccount queryAccountByAcctUsername(String acctUsername);

	int insert(ComAccount comAccount);

	int update(ComAccount comAccount);

}
