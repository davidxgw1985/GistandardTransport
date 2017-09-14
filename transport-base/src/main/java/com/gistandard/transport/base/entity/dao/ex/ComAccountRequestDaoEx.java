package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccountRequest;

import java.util.List;

@MyBatisRepository
public interface ComAccountRequestDaoEx {

    /**
     * 根据帐户ID查询是否存在申请请求
     * @param accountId
     * @return
     */
    List<ComAccountRequest> queryApplyReqByAccountId(Integer accountId);

    ComAccountRequest selectByAccountIdAndRoleIdAndStatus(ComAccountRequest record);
}