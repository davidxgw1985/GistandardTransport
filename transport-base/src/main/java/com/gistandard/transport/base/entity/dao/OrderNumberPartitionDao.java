package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.OrderNumberPartition;

@MyBatisRepository
public interface OrderNumberPartitionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderNumberPartition record);

    int insertSelective(OrderNumberPartition record);

    OrderNumberPartition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderNumberPartition record);

    int updateByPrimaryKey(OrderNumberPartition record);
}