package com.gistandard.transport.order.dubbo.dao;

import com.gistandard.transport.app.dubbo.order.bean.PopAndPodOrderCount;
import com.gistandard.transport.base.annotation.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhuming on 2017/6/22 0022.
 */

@MyBatisRepository
public interface OrderCountDao {
    PopAndPodOrderCount QueryPopAndPodCount(@Param("accountId")Integer accountId, @Param("date")String date);
}
