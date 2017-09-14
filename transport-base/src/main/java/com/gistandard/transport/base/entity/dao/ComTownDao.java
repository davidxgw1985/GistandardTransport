package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComTown;

@MyBatisRepository
public interface ComTownDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComTown record);

    int insertSelective(ComTown record);

    ComTown selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComTown record);

    int updateByPrimaryKey(ComTown record);
}