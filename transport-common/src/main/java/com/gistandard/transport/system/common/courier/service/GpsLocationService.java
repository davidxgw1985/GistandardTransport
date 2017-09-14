package com.gistandard.transport.system.common.courier.service;



import com.gistandard.transport.system.common.courier.bean.GiPositionInfo;
import com.gistandard.transport.system.common.courier.bean.LocationInfo;
import com.gistandard.transport.system.common.courier.bean.RoutePlan;

import java.util.List;

/**
 * @author kongxm
 * @Description
 * @Version 1.0
 * @Date 2016/3/9
 */
public interface GpsLocationService {
    List<LocationInfo> queryLocationByLoginAccount(String loginAccount);

    List<LocationInfo> queryUsersByLocation(Double longitude, Double latitude, Double radius);

    List<LocationInfo> queryUsersByLocationWithHifu(Double longitude, Double latitude, Double radius, Boolean isHifu);

    List<GiPositionInfo> queryExpressDriverByLanLatRad(Double longitude, Double latitude, Double radius, Boolean isHifu, String jsonAllRoleCode);

    List<LocationInfo> queryFixedMiByLocationWithHifu(Double longitude, Double latitude, Double radius, Boolean isHifu);

    RoutePlan getGiRoutePlanById(String planId);
}
