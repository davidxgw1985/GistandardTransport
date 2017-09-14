package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileAddressInfo;

/**
 * Created by m on 2016/9/30.
 */
@MyBatisRepository
public interface MobileAddressInfoDaoEx {
    /**
     * 查询重复地址簿
     * @param record
     * @return
     */
    int querySameAddressInfo(MobileAddressInfo record);

    /**
     * 为微信修改地址簿定制
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobileAddressInfo record);
}
