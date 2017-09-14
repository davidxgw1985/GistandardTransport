package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.ComUserinfo;

/**
 * @author yujie
 * @ClassName ComUserinfoService
 * @Description
 * @Version 1.0
 * @Date 2015-09-08
 */
public interface ComUserinfoService {

    ComUserinfo queryUserInfoById(int acctId);

    int insert(ComUserinfo comUserinfo);

    int update(ComUserinfo comUserinfo);
}
