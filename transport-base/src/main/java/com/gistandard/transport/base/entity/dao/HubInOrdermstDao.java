package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.HubInOrdermst;

@MyBatisRepository
public interface HubInOrdermstDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HubInOrdermst record);

    int insertSelective(HubInOrdermst record);

    HubInOrdermst selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HubInOrdermst record);

    int updateByPrimaryKey(HubInOrdermst record);
}