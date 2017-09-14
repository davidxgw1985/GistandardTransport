package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.ComUserinfo;
import com.gistandard.transport.base.entity.dao.ComUserinfoDao;
import com.gistandard.transport.base.entity.service.ComUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yujie
 * @ClassName ComUserinfoServiceImpl
 * @Description
 * @Version 1.0
 * @Date 2015-09-08
 */
@Service
public class ComUserinfoServiceImpl implements ComUserinfoService {

    @Autowired
    private ComUserinfoDao comUserinfoDao;

    @Override
    public ComUserinfo queryUserInfoById(int acctId) {
        return comUserinfoDao.queryByAcctId(acctId);
    }

    @Override
    public int insert(ComUserinfo comUserinfo) {
        return comUserinfoDao.insert(comUserinfo);
    }

    @Override
    public int update(ComUserinfo comUserinfo) {
        return comUserinfoDao.updateByPrimaryKey(comUserinfo);
    }
}
