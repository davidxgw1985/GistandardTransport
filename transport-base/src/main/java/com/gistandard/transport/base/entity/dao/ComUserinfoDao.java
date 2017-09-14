package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComUserinfo;
@MyBatisRepository
public interface ComUserinfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComUserinfo record);

    int insertSelective(ComUserinfo record);

    ComUserinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComUserinfo record);

    int updateByPrimaryKey(ComUserinfo record);

    /**
     * 根据帐号ID，查找人员信息
     * @param acctId
     * @return
     */
    ComUserinfo queryByAcctId(Integer acctId);
}