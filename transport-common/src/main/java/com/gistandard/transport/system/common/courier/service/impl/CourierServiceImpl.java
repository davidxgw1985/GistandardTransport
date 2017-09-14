package com.gistandard.transport.system.common.courier.service.impl;


import com.alibaba.fastjson.JSON;
import com.gistandard.dubbo.CouponDubboService;
import com.gistandard.dubbo.bean.DataResultBean;
import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.AttentionInfoDao;
import com.gistandard.transport.base.entity.dao.ComQuoteItemDao;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.courier.bean.*;
import com.gistandard.transport.system.common.courier.dao.CourierDao;
import com.gistandard.transport.system.common.courier.service.CourierService;
import com.gistandard.transport.system.common.courier.service.GpsLocationService;
import com.gistandard.transport.system.common.station.dao.CustomerAttentionInfoDao;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author kongxm
 * @Description
 * @Version 1.0
 * @Date 2016/3/8
 */
@Service
public class CourierServiceImpl implements CourierService {
    private static final Logger logger = LoggerFactory.getLogger(CourierServiceImpl.class);

    @Autowired
    private CourierDao courierDao;

    @Autowired
    private CustomerAttentionInfoDao customerAttentionInfoDao;

    @Autowired
    private AttentionInfoDao attentionInfoDao;

    @Autowired
    private GpsLocationService gpsLocationService;

    @Autowired
    private ComProvinceService comProvinceService;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private ComCountyService comCountyService;

    @Autowired
    private ComQuoteItemDao comQuoteItemDao;

    @Autowired
    private CouponDubboService couponDubboService;

    private static final Logger LOG = LoggerFactory.getLogger(CourierServiceImpl.class);


    @Override
    public List<Courier> query(CourierQueryReq req) {
        LOG.debug("Courier query req={}", JSON.toJSONString(req));
        ComQuoteItem comQuoteItem = comQuoteItemDao.selectByPrimaryKey(req.getItemId());
        if (null != comQuoteItem) {
            req.setOrderType(comQuoteItem.getItemType());
        }
        //嗨付券参数
        DataResultBean dataResultBean = couponDubboService.checkHaveCoupons(req.getAcctUsername());
        boolean isHifu = false;
        logger.info("Hi voucher interface call dataResultBean-{}", JSON.toJSONString(dataResultBean));
        if (dataResultBean == null) {
            logger.error("Hi voucher interface call failed res is null");
        } else {
            if (dataResultBean.getSucceed()) {
                logger.info("Hi voucher interface call successful");
                if (Boolean.TRUE.equals(dataResultBean.getRefObject())) {
                    logger.info("Hi voucher interface response result is true");
                    isHifu = true;
                }
                if (Boolean.FALSE.equals(dataResultBean.getRefObject())) {
                    logger.info("Hi voucher interface response result is false");
                }
            } else {
                logger.error("Hi voucher interface call failed");
            }
        }
        //设置快递员的账号
        List<LocationInfo> locationInfos = gpsLocationService.queryUsersByLocationWithHifu(req.getLongitude(), req.getLatitude(), req.getDistance(), isHifu);
        if (locationInfos != null && locationInfos.size() > 0) {
            String[] usernames = new String[locationInfos.size()];
            int size = 0;
            for (LocationInfo tInfo : locationInfos) {
                usernames[size] = "'" + tInfo.getUserCode() + "'";
                size++;
            }
            if (size > 0) {
                String userLoginAccounts = org.apache.commons.lang.StringUtils.substringBetween(ArrayUtils.toString(usernames), "{", "}");
                req.setO2ids(userLoginAccounts);
            } else {
                req.setO2ids("-1");
            }
        } else {
            req.setO2ids("-1");
        }
        //设置咪站的账号
        List<LocationInfo> miLocationInfos = gpsLocationService.queryFixedMiByLocationWithHifu(req.getLongitude(), req.getLatitude(), req.getDistance(), isHifu);
        if (miLocationInfos != null && miLocationInfos.size() > 0) {
            String[] usernames = new String[miLocationInfos.size()];
            int size = 0;
            for (LocationInfo tInfo : miLocationInfos) {
                usernames[size] = "'" + tInfo.getUserCode() + "'";
                size++;
            }
            if (size > 0) {
                String miLoginAccounts = org.apache.commons.lang.StringUtils.substringBetween(ArrayUtils.toString(usernames), "{", "}");
                req.setMiO2ids(miLoginAccounts);
            } else {
                req.setMiO2ids("-1");
            }
        } else {
            req.setMiO2ids("-1");
        }
        List<Courier> courierList = courierDao.query(req);
        List<Courier> courierListResult = new ArrayList<>();
        if (courierList != null && courierList.size() > 0) {
            //获取查询位置附近手机
            if (locationInfos != null && locationInfos.size() > 0) {
                for (Courier courier : courierList) {
                    for (LocationInfo locationInfo : locationInfos) {
                        if (null != locationInfo.getUserCode() && null != courier.getAcctUsername() && courier.getAcctUsername().equals(locationInfo.getUserCode()) && courier.getRoleId() != 23) {
                            courier.setStaLatitude(locationInfo.getLatitude());
                            courier.setStaLongitude(locationInfo.getLongitude());
                        }
                    }
                }
            }
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            for (Courier courier : courierList) {
                if (courier.getStaLatitude() == null || courier.getStaLongitude() == null) {
                    continue;
                } else {
                    if (req.getRoleId() == null) {
                        if (courier.getProvinceCode() != null && comProvinceMap.get(courier.getProvinceCode()) != null) {
                            courier.setProvinceName(comProvinceMap.get(courier.getProvinceCode()).getProvinceName());
                        }
                        if (courier.getCityCode() != null && comCityMap.get(courier.getCityCode()) != null) {
                            courier.setCityName(comCityMap.get(courier.getCityCode()).getName());
                        }
                        if (courier.getCountyCode() != null && comCountyMap.get(courier.getCountyCode()) != null) {
                            courier.setCountyName(comCountyMap.get(courier.getCountyCode()).getAreaName());
                        }
                        courier.setRoleName(SysAccountRole.getName(courier.getRoleId().intValue()));
                        courierListResult.add(courier);
                    } else {
                        if (courier.getRoleId() == req.getRoleId()) {
                            if (courier.getProvinceCode() != null && comProvinceMap.get(courier.getProvinceCode()) != null) {
                                courier.setProvinceName(comProvinceMap.get(courier.getProvinceCode()).getProvinceName());
                            }
                            if (courier.getCityCode() != null && comCityMap.get(courier.getCityCode()) != null) {
                                courier.setCityName(comCityMap.get(courier.getCityCode()).getName());
                            }
                            if (courier.getCountyCode() != null && comCountyMap.get(courier.getCountyCode()) != null) {
                                courier.setCountyName(comCountyMap.get(courier.getCountyCode()).getAreaName());
                            }
                            courier.setRoleName(SysAccountRole.getName(courier.getRoleId().intValue()));
                            courierListResult.add(courier);
                        }
                    }
                }

            }
        }
        LOG.debug("Courier query res size={}", courierListResult.size());
        return courierListResult;
    }

    @Override
    public int queryCount(CourierQueryReq req) {
        int count = courierDao.queryCount(req);
        return count;
    }

    @Override
    public List<Courier> queryNearby(CourierQueryNearbyReq req) {
        Double longitude = req.getLongitude();
        Double latitude = req.getLatitude();
        Double radius = req.getDistance();

        List<LocationInfo> locationInfos = gpsLocationService.queryUsersByLocation(longitude, latitude, radius);

        List<Courier> courierList = courierDao.queryNearby(req);

        for (Courier courier : courierList) {
            for (LocationInfo locationInfo : locationInfos) {
                if (courier.getAcctUsername().equals(locationInfo.getUserCode())) {
                    courier.setStaLongitude(locationInfo.getLongitude());
                    courier.setStaLongitude(locationInfo.getLatitude());
                }
            }
        }
        return courierList;
    }

    @Override
    public int queryNearbyCount(CourierQueryNearbyReq req) {
        return courierDao.queryNearbyCount(req);
    }

    @Override
    public void follow(CourierFollowReq req) throws MobileStationBizException {
        Integer courierId = req.getCourierId();
        Integer accountId = req.getAccountId();
        if (courierId == null) {
            throw new MobileStationBizException("id不能为空");
        }
        if (accountId == null) {
            throw new MobileStationBizException("accountId不能为空");
        }
        if (req.getRoleId() == null || req.getRoleId() == 0) {
            throw new MobileStationBizException("roleId不能为空");
        }
        //组装数据
        AttentionInfo attentionInfo = new AttentionInfo();
        attentionInfo.setAccountId(accountId);
        attentionInfo.setTransportType(2);//1是运输，2快递
        attentionInfo.setAttentionType(1);//1=站点,2快递员3司机
        attentionInfo.setStationId(courierId);
        attentionInfo.setAttentionStatus(1);//0=无效，1=有效
        attentionInfo.setAttentionTime(new Date());
        //TODO:ATTENTION_SYSTEM是什么字段？设置的客户下单app
        attentionInfo.setAttentionSystem("customerApp");
        attentionInfo.setRoleId(req.getRoleId());
        //判断是否关注过
        int count = customerAttentionInfoDao.count(attentionInfo);
        if (count > 0) {
            customerAttentionInfoDao.updateStatus(attentionInfo);
        } else {
            //插入数据
            attentionInfoDao.insert(attentionInfo);
        }
    }

    @Override
    public void unfollow(CourierUnFollowReq req) throws MobileStationBizException {
        Integer courierId = req.getCourierId();
        if (courierId == null) {
            throw new MobileStationBizException("Id参数错误");
        }
        Integer accountId = req.getAccountId();
        if (accountId == null) {
            throw new MobileStationBizException("accountId参数错误");
        }
        if (req.getRoleId() == null || req.getRoleId() == 0) {
            throw new MobileStationBizException("roleId不能为空");
        }
        AttentionInfo attentionInfo = new AttentionInfo();
        attentionInfo.setAccountId(accountId);
        attentionInfo.setStationId(courierId);
        attentionInfo.setAttentionStatus(0);
        attentionInfo.setRoleId(req.getRoleId());
        customerAttentionInfoDao.updateStatus(attentionInfo);
    }

    @Override
    public List<Courier> queryFollow(AppBasePagerRequest req) throws MobileStationBizException {
        List<Courier> courierList = courierDao.queryFollow(req);
        List<Courier> courierListResult = null;
        if (courierList != null && courierList.size() > 0) {
            //获取查询位置附近手机
            String[] usernames = new String[courierList.size()];
            int size = 0;
            for (Courier courier : courierList) {
                if (courier.getRoleId() != 23) {
                    usernames[size] = courier.getAcctUsername();
                    size++;
                }
            }
            if (size > 0) {
                String userLoginAccounts = StringUtils.join(usernames, ",");
                List<LocationInfo> locationInfos = gpsLocationService.queryLocationByLoginAccount(userLoginAccounts);
                if (locationInfos != null && locationInfos.size() > 0) {
                    for (Courier courier
                            : courierList) {
                        for (LocationInfo locationInfo : locationInfos) {
                            if (courier.getAcctUsername().equals(locationInfo.getUserCode()) && courier.getRoleId() != 23) {
                                courier.setStaLatitude(locationInfo.getLatitude());
                                courier.setStaLongitude(locationInfo.getLongitude());
                            }
                        }
                    }
                }
            }
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            for (Courier courier : courierList) {
                if (courierListResult == null) {
                    courierListResult = new ArrayList<Courier>();
                }
                if (courier.getRoleId() != null && !req.getAccountId().equals(courier.getAccountId())) {
                    if (courier.getProvinceCode() != null && comProvinceMap.get(courier.getProvinceCode()) != null) {
                        courier.setProvinceName(comProvinceMap.get(courier.getProvinceCode()).getProvinceName());
                    }
                    if (courier.getCityCode() != null && comCityMap.get(courier.getCityCode()) != null) {
                        courier.setCityName(comCityMap.get(courier.getCityCode()).getName());
                    }
                    if (courier.getCountyCode() != null && comCountyMap.get(courier.getCountyCode()) != null) {
                        courier.setCountyName(comCountyMap.get(courier.getCountyCode()).getAreaName());
                    }
                    courier.setRoleName(SysAccountRole.getName(courier.getRoleId().intValue()));
                    courierListResult.add(courier);
                }
            }
        }

        return courierListResult;
    }

    @Override
    public int queryFollowCount(AppBasePagerRequest req) throws MobileStationBizException {
        return courierDao.queryFollowCount(req);
    }
}
