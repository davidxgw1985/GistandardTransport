package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCustomerRelation;

import java.util.List;

@MyBatisRepository
public interface ComCustomerRelationDaoEx {

    int batchInsert(List<ComCustomerRelation> list);
}