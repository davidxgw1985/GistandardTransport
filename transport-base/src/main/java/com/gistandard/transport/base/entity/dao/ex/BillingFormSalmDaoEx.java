package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BillingFormSalm;

import java.util.List;

/**
 * Created by m on 2016/9/30.
 */
@MyBatisRepository
public interface BillingFormSalmDaoEx {
    /*
	 * 定仓定单表ID删除货物数据
	 */
    int deleteByBusiBooknoId(Integer id);

    int batchInsert(List<BillingFormSalm> recordList);

    List<BillingFormSalm>  queryByBusiBooknoId(Integer busiBooknoId);

    List<BillingFormSalm>  queryByBusiBookno(String busiBookno);
}
