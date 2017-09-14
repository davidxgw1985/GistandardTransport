package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComDriverInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ComDriverInfoDaoEx {

    /**
     * 根据账户ID 关联司机和设备表 删除该账户对应的所有司机信息
     *
     * @param acctId
     *            账户ID
     */
    Integer deleteDriverInfoByAcctId(Integer acctId);


    List<ComDriverInfo> queryListByVehicleId(Integer vehicleId);

    List<ComDriverInfo> queryListByAccountId(Integer accountId);

    int updateByAccountId(@Param("vehicleId")Integer vehicleId,@Param("accountId") Integer accountId);

    int updateSetNullByAccountId(@Param("vehicleId")Integer vehicleId,@Param("accountId") Integer accountId);
}