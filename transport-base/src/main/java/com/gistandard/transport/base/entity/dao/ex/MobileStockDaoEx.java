package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileStock;

import java.util.List;

/**
 * Created by m on 2016/8/6.
 */
@MyBatisRepository
public interface MobileStockDaoEx {

    /**
     * 批量插入
     * @param recordList
     * @return
     */
    int batchInsert(List<MobileStock> recordList);
}
