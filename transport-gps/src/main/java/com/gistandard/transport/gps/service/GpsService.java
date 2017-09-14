package com.gistandard.transport.gps.service;


import com.gistandard.transport.system.webservice.client.gps.GiPoint;

/**
 * Created by m on 2017/3/10.
 */
public interface GpsService {

    /**
     * 百度地图
     * 根据地址查询经纬度
     *
     * @param addr
     * @return
     */
    GiPoint getGiPointByAddress(String addr);

    /**
     * 百度地图
     * 根据关键字检索获取经纬度
     *
     * @param city
     * @param addr
     * @return
     */
    GiPoint getGiPointByCityAddress(String city, String addr);

    /**
     * 百度地图
     * 先根据地址获取中心点，再根据中心点附近找地址
     *
     * @param addr
     * @return
     */
    GiPoint getGiPointByNearBy(String addr, String detailAddr);
}
