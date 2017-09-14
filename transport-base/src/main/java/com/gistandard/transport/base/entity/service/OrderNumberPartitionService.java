package com.gistandard.transport.base.entity.service;


import com.gistandard.transport.base.entity.bean.OrderNumberPartition;

/**
 * Created by yujie on 2016/9/9.
 */
public interface OrderNumberPartitionService {

    int insert(OrderNumberPartition orderNumberPartition);

    int updateByPrimaryKey(OrderNumberPartition orderNumberPartition);

    int updateIdByCityIdAndPartitionNo(int cityId, int partitionNo, int id);
}
