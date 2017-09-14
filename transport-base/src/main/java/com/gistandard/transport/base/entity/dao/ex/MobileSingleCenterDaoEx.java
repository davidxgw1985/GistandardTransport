package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileSingleCenter;

import java.util.List;

/**
 * Created by m on 2016/12/12.
 */
@MyBatisRepository
public interface MobileSingleCenterDaoEx {
    int batchInsert(List<MobileSingleCenter> mobileSingleCenters);
}
