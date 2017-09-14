package com.gistandard.transport.system.common.courier.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gistandard.transport.system.common.courier.bean.GiPositionInfo;
import com.gistandard.transport.system.common.courier.bean.LocationInfo;
import com.gistandard.transport.system.common.courier.bean.RoutePlan;
import com.gistandard.transport.system.common.courier.service.GpsLocationService;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kongxm
 * @Description
 * @Version 1.0
 * @Date 2016/3/9
 */
@Service
public class GpsLocationServiceImpl implements GpsLocationService {

    @Autowired
    private PnWebService pnWebService;

    @Override
    public List<LocationInfo> queryLocationByLoginAccount(String userCodes) {

        List<LocationInfo> locationInfos = new ArrayList<>();

        if (StringUtils.isBlank(userCodes)) {
            return locationInfos;
        }

        String jsonStr = pnWebService.getAllGiLocationCurrentByUserCodes(userCodes);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            BigDecimal latitude = object.getJSONObject("giPoint").getBigDecimal("latitude");
            BigDecimal longitude = object.getJSONObject("giPoint").getBigDecimal("longitude");
            String userCode = object.getString("userCode");

            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setLatitude(latitude);
            locationInfo.setLongitude(longitude);
            locationInfo.setUserCode(userCode);
            locationInfos.add(locationInfo);
        }
        return locationInfos;
    }

    /**
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    半径（米）
     */
    @Override
    public List<LocationInfo> queryUsersByLocation(Double longitude, Double latitude, Double radius) {

        List<LocationInfo> locationInfos = new ArrayList<>();

        String jsonStr = pnWebService.getAllLocExpressByLanLatRad(longitude, latitude, radius);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            BigDecimal _latitude = object.getJSONObject("giPoint").getBigDecimal("latitude");
            BigDecimal _longitude = object.getJSONObject("giPoint").getBigDecimal("longitude");
            String userCode = object.getString("userCode");

            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setLatitude(_latitude);
            locationInfo.setLongitude(_longitude);
            locationInfo.setUserCode(userCode);
            locationInfos.add(locationInfo);
        }
        return locationInfos;
    }

    /**
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    半径（米）
     * @param isHifu    true 嗨付单 false 正常单
     * @param jsonAllRoleCode
     */
    @Override
    public List<GiPositionInfo> queryExpressDriverByLanLatRad(Double longitude, Double latitude, Double radius, Boolean isHifu, String jsonAllRoleCode) {

        String jsonStr = pnWebService.getAllExpressDriverByLanLatRad(longitude, latitude, radius, isHifu, jsonAllRoleCode);
        List<GiPositionInfo> giPositionInfoList = JSON.parseArray(jsonStr, GiPositionInfo.class);
        return giPositionInfoList;
    }


    /**
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    半径（米）
     * @param isHifu    true 嗨付单 false 正常单
     */
    @Override
    public List<LocationInfo> queryUsersByLocationWithHifu(Double longitude, Double latitude, Double radius, Boolean isHifu) {

        List<LocationInfo> locationInfos = new ArrayList<>();

        String jsonStr = pnWebService.getAllLocExpressByLanLatRadWithHifu(longitude, latitude, radius, isHifu);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            BigDecimal _latitude = object.getJSONObject("giPoint").getBigDecimal("latitude");
            BigDecimal _longitude = object.getJSONObject("giPoint").getBigDecimal("longitude");
            String userCode = object.getString("userCode");

            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setLatitude(_latitude);
            locationInfo.setLongitude(_longitude);
            locationInfo.setUserCode(userCode);
            locationInfos.add(locationInfo);
        }
        return locationInfos;
    }

    /**
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    半径（米）
     * @param isHifu    true 嗨付单 false 正常单
     */
    @Override
    public List<LocationInfo> queryFixedMiByLocationWithHifu(Double longitude, Double latitude, Double radius, Boolean isHifu) {

        List<LocationInfo> locationInfos = new ArrayList<>();

        String jsonStr = pnWebService.getAllLocFixedMiByLanLatRadWithHifu(longitude, latitude, radius, isHifu);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            BigDecimal _latitude = object.getJSONObject("giPoint").getBigDecimal("latitude");
            BigDecimal _longitude = object.getJSONObject("giPoint").getBigDecimal("longitude");
            String userCode = object.getString("userCode");

            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setLatitude(_latitude);
            locationInfo.setLongitude(_longitude);
            locationInfo.setUserCode(userCode);
            locationInfos.add(locationInfo);
        }
        return locationInfos;
    }

    @Override
    public RoutePlan getGiRoutePlanById(String planId) {
        String jsonStr = pnWebService.getGiRoutePlanById(planId);
        RoutePlan routePlan = JSONObject.parseObject(jsonStr, RoutePlan.class);
        return routePlan;
    }
}
