package com.gistandard.transport.dubbo.dao;

import com.gistandard.transport.app.dubbo.register.bean.RegComAccountRequest;
import com.gistandard.transport.base.annotation.MyBatisRepository;

import java.util.List;

/**
 * Created by zhuming on 2017/4/25 0025.
 */
@MyBatisRepository
public interface RequestDubboDao {

    /**
     * 根据帐户ID查询是否存在申请请求
     * @param accountId
     * @return
     */
    List<RegComAccountRequest> queryApplytoByAccountId(Integer accountId);

}
