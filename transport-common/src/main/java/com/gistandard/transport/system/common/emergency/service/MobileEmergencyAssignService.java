package com.gistandard.transport.system.common.emergency.service;


import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.emergency.bean.GetNearMsDataResult;
import com.gistandard.transport.system.common.emergency.bean.GetNearMsReq;

/**
 * @author xgw
 * @ClassName MobileEmergencyAssignService
 * @Description
 * @Version 3.0
 * @Date 2016-06-17
 */
public interface MobileEmergencyAssignService {


    /**
     * 获取距离范围内附近的MS接口
     *
     * @param getNearMsReq
     * @throws Exception
     */
    GetNearMsDataResult queryUsersByLocation(GetNearMsReq getNearMsReq) throws MobileStationBizException;
}
