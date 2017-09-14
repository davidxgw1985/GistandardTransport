package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccountCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ComAccountCategoryDaoEx {

    int deleteByRequestId(Integer requestId);

    List<ComAccountCategory> queryByRequestId(@Param(value = "requestId") Integer requestId);

    /**
     * 根据账号ID获取蛙站状态信息
     * @param accountId
     * @return
     */
    ComAccountCategory getWaStationStatus(int accountId);

    /**
     * 根据账号ID获取咪站状态信息
     * @param accountId
     * @return
     */
    ComAccountCategory getMiStationStatus(int accountId);
}