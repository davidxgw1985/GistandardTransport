package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.OrderNumberPartition;
import com.gistandard.transport.base.entity.dao.OrderNumberPartitionDao;
import com.gistandard.transport.base.entity.dao.ex.OrderNumberPartitionDaoEx;
import com.gistandard.transport.base.entity.service.OrderNumberPartitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yujie on 2016/9/9.
 */
@Service
public class OrderNumberPartitionServiceImpl implements OrderNumberPartitionService {

    @Autowired
    private OrderNumberPartitionDao orderNumberPartitionDao;

    @Autowired
    private OrderNumberPartitionDaoEx orderNumberPartitionDaoEx;


    @Override
    public int insert(OrderNumberPartition orderNumberPartition) {
        return orderNumberPartitionDao.insert(orderNumberPartition);
    }

    @Override
    public int updateByPrimaryKey(OrderNumberPartition orderNumberPartition) {
        return orderNumberPartitionDao.updateByPrimaryKey(orderNumberPartition);
    }

    @Override
    public int updateIdByCityIdAndPartitionNo(int cityId, int partitionNo, int id){
        return orderNumberPartitionDaoEx.updateIdByCityIdAndPartitionNo(cityId, partitionNo, id);
    }
}
