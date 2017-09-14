package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.SysExceptionLog;
import com.gistandard.transport.base.entity.dao.SysExceptionLogDao;
import com.gistandard.transport.base.entity.service.SysExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yujie on 2016/9/29.
 */
@Service
public class SysExceptionLogServiceImpl implements SysExceptionLogService {

    @Autowired
    private SysExceptionLogDao sysExceptionLogDao;

    @Override
    public int insert(SysExceptionLog sysExceptionLog) {
        return sysExceptionLogDao.insert(sysExceptionLog);
    }
}
