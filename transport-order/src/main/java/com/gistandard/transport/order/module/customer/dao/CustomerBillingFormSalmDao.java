package com.gistandard.transport.order.module.customer.dao;



import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BillingFormSalm;

import java.util.List;

@MyBatisRepository
public interface CustomerBillingFormSalmDao {
    List<BillingFormSalm>  queryByBusiBooknoId(Integer busiBooknoId);
}