package com.gistandard.transport.base.entity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.base.entity.service.ComAccountService;

/**
 * @author yujie
 * @ClassName ComAccountServiceImpl
 * @Description
 * @Version 1.0
 * @Date 2015-09-08
 */
@Service
public class ComAccountServiceImpl implements ComAccountService {

	@Autowired
	private ComAccountDao comAccountDao;

	@Autowired
	private ComAccountDaoEx comAccountDaoEx;

	@Override
	public ComAccount queryAccountById(int acctId) {
		return comAccountDao.selectByPrimaryKey(acctId);
	}

	@Override
	public ComAccount queryAccountByAcctUsername(String acctUsername) {
		return comAccountDaoEx.queryByAccount(acctUsername);
	}

	@Override
	public int insert(ComAccount comAccount) {
		return comAccountDao.insert(comAccount);
	}

	@Override
	public int update(ComAccount comAccount) {
		return comAccountDao.updateByPrimaryKey(comAccount);
	}
}
