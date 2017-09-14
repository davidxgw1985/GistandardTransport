package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.OrderCustomInfo;

@MyBatisRepository
public interface OrderCustomInfoDao {
    int deleteByPrimaryKey(Integer orderCustomId);

    int insert(OrderCustomInfo record);

    int insertSelective(OrderCustomInfo record);

    OrderCustomInfo selectByPrimaryKey(Integer orderCustomId);

    int updateByPrimaryKeySelective(OrderCustomInfo record);

    int updateByPrimaryKey(OrderCustomInfo record);
}