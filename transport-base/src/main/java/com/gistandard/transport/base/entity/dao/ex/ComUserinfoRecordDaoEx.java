package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComUserinfoRecord;

import java.util.List;

@MyBatisRepository
public interface ComUserinfoRecordDaoEx {

    /**
     * 根据帐户ID查询
     * @return
     */
    List<ComUserinfoRecord> queryByAccountId(Integer accountId);

    /**
     * 根据帐户ID删除
     * @param accountId
     * @return
     */
    Integer deleteByAccountId(Integer accountId);
}