package com.gistandard.transport.order.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.app.dubbo.wechat.bean.*;
import com.gistandard.transport.app.dubbo.wechat.service.WeChatAddressDubboService;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.BookingForm;
import com.gistandard.transport.base.entity.bean.MobileAddressInfo;
import com.gistandard.transport.base.entity.dao.MobileAddressInfoDao;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileAddressInfoDaoEx;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.order.module.customer.CustomerTrackService;
import com.gistandard.transport.order.module.customer.bean.track.QueryOrderCurrInfoReq;
import com.gistandard.transport.order.module.customer.bean.track.QueryOrderCurrInfoRes;
import com.gistandard.transport.order.wechat.dao.WeChatAddressDao;
import com.gistandard.transport.order.wechat.dao.WeChatComTownDaoEx;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by m on 2017/2/7.
 */
public class WeChatAddressDubboServiceImpl implements WeChatAddressDubboService {

    private static final Logger logger = LoggerFactory.getLogger(WeChatAddressDubboServiceImpl.class);

    @Autowired
    private WeChatAddressDao weChatAddressDao;
    @Autowired
    private MobileAddressInfoDao mobileAddressInfoDao;
    @Autowired
    private WeChatComTownDaoEx weChatComTownDaoEx;
    @Autowired
    private MobileAddressInfoDaoEx mobileAddressInfoDaoEx;
    @Autowired
    private CustomerTrackService customerTrackService;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    /**
     * 查询地址类型查询地址
     * @param weChatAddressQueryReq
     * @return
     */
    @Override
    public List<AddressInfo> queryAddressByType(WeChatAddressQueryReq weChatAddressQueryReq) {
        logger.debug("queryAddressByType req={}", JSON.toJSONString(weChatAddressQueryReq));
        return weChatAddressDao.queryAddressByType(weChatAddressQueryReq);
    }

    /**
     * 新增地址
     * 返回地址簿ID
     * @param address
     * @return
     */
    @Override
    public WebAddressInfoRes addAddressInfo(AddressInfo address) {
        logger.debug("addAddressInfo req={}", JSON.toJSONString(address));
        WebAddressInfoRes res = new WebAddressInfoRes(address);
        MobileAddressInfo mobileAddressInfo = new MobileAddressInfo();
        int result = 0;
        try {
            checkParam(address);
            try {
                BeanUtils.copyProperties(mobileAddressInfo, address);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new CustomerBizException(e);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new CustomerBizException(e);
            }
            mobileAddressInfo.setStatus(1);
            mobileAddressInfo.setCreateTime(new Date());
            mobileAddressInfo.setDefaultAddress(0);
            result = mobileAddressInfoDao.insert(mobileAddressInfo);
        } catch (CustomerBizException e) {
            e.printStackTrace();
            res.setRetMsg(e.getMessage());
            res.setRetCode(SystemDefine.FAILURE);
            logger.debug("addAddressInfo res={}", JSON.toJSONString(res));
            return res;
        }

        if (result > 0) {
            res.setRetCode(SystemDefine.SUCCESS);
            res.setId(mobileAddressInfo.getId());
        }

        logger.debug("addAddressInfo res={}", JSON.toJSONString(res));
        return res;
    }

    /**
     * 修改地址
     * @param address
     * @return
     */
    @Override
    public MsDubboResBean updateAddressInfo(AddressInfo address) {
        logger.debug("updateAddressInfo req={}", JSON.toJSONString(address));
        MsDubboResBean res = new MsDubboResBean();
        MobileAddressInfo mobileAddressInfo = new MobileAddressInfo();
        int result = 0;
        try {
            checkParam(address);
            try {
                BeanUtils.copyProperties(mobileAddressInfo, address);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new CustomerBizException(e);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new CustomerBizException(e);
            }

            result = mobileAddressInfoDao.updateByPrimaryKeySelective(mobileAddressInfo);
        } catch (CustomerBizException e) {
            e.printStackTrace();
            res.setRetMsg(e.getMessage());
            res.setRetCode(SystemDefine.FAILURE);
            logger.debug("updateAddressInfo res={}", JSON.toJSONString(res));
            return res;
        }

        if (result > 0) {
            res.setRetCode(SystemDefine.SUCCESS);
        } else {
            res.setRetCode(SystemDefine.FAILURE);
        }
        logger.debug("updateAddressInfo res={}", JSON.toJSONString(res));
        return res;
    }

    /**
     * 区县查询街道
     * @param countyId
     * @return
     */
    @Override
    public List<WeChatComTown> getTownListByConutyId(Integer countyId) {
        logger.debug("getTownListByConutyId req={}", countyId);
        return weChatComTownDaoEx.selectByCountyId(countyId);
    }


    /**
     * 校验经纬度
     * @param address
     * @throws CustomerBizException
     */
    private void checkAddressLongitudeAndLatitude(AddressInfo address) throws CustomerBizException {
        if (address.getAddressLongitude() == null || address.getAddressLongitude().compareTo(new BigDecimal(0)) == 0) {
            throw new CustomerBizException("经度不能为空");
        }
        if (address.getAddressLatitude() == null || address.getAddressLatitude().compareTo(new BigDecimal(0)) == 0) {
            throw new CustomerBizException("纬度不能为空");
        }
    }

    private void checkParam(AddressInfo address) throws CustomerBizException {
        // //1是自己的收地址，2别人的地址
        if (address.getAddressType() == null || (address.getAddressType() != 1 && address.getAddressType() != 2)) {
            throw new CustomerBizException("地址类型不正确");
        }
        if (StringUtils.isEmpty(address.getName())) {
            throw new CustomerBizException("姓名不可为空");
        }
        if (address.getCountry() == null) {
            throw new CustomerBizException("国家代码不能为空");
        }
        if (StringUtils.isEmpty(address.getProvince())) {
            throw new CustomerBizException("省份代码不能为空");
        }
        if (StringUtils.isEmpty(address.getCity())) {
            throw new CustomerBizException("市代码不能为空");
        }
        if (StringUtils.isEmpty(address.getCounty())) {
            throw new CustomerBizException("区代码不能为空");
        }
//        if (StringUtils.isEmpty(address.getAddress())) {
//            throw new CustomerBizException("地址不能为空");
//        }
        if (StringUtils.isEmpty(address.getDetailAddress())) {
            throw new CustomerBizException("门牌号地址不能为空");
        }
        if (StringUtils.isEmpty(address.getWeChatId())) {
            throw new CustomerBizException("微信标识ID不能为空");
        }
        if (StringUtils.isEmpty(address.getTelephone()) || address.getTelephone().length() != 11) {
            throw new CustomerBizException("手机号码不正确");
        }
        // 检查经纬度
//        checkAddressLongitudeAndLatitude(address);
    }

    @Override
    public MsDubboResBean delete(Integer[] addrIds, String weChatId, Integer accountId) {
        MsDubboResBean res = new MsDubboResBean();
        int totalSize = 0;
        for (int i = 0; i < addrIds.length; i++) {
            MobileAddressInfo mobileAddressInfo = new MobileAddressInfo();
            mobileAddressInfo.setId(addrIds[i]);
            mobileAddressInfo.setStatus(0);// 设置为失效
            if (!StringUtils.isEmpty(weChatId)) {
                mobileAddressInfo.setWeChatId(weChatId);
            }
            if (null != accountId) {
                mobileAddressInfo.setAccountId(accountId);
            }
            int size = mobileAddressInfoDaoEx.updateByPrimaryKeySelective(mobileAddressInfo);
            totalSize += size;
        }

        if (totalSize > 0) {
            res.setRetCode(SystemDefine.SUCCESS);
        } else {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("删除失败");
        }

        return res;
    }


    /**
     * 获取订单坐标信息
     * @param weChatOrderCdntReq 请求
     * @return 获取的订单坐标信息
     */
    @Override
    public WeChatOrderCdntRes getOrderCoordinateInfo(WeChatOrderCdntReq weChatOrderCdntReq) {

        logger.info("微信端查询订单坐标接口,req:{}",JSON.toJSONString(weChatOrderCdntReq));

        WeChatOrderCdntRes dataRes = new WeChatOrderCdntRes();

        if (validateCdntReq(weChatOrderCdntReq, dataRes)) {
            logger.error("微信端查询订单坐标接口校验不通过");
            return dataRes;
        }
        QueryOrderCurrInfoReq req = new QueryOrderCurrInfoReq();

        req.setBusiBookNo(weChatOrderCdntReq.getBusiBookNo());

        QueryOrderCurrInfoRes res = customerTrackService.queryOrderCurrInfo(req);

        try {
            BeanUtils.copyProperties(dataRes, res);
        } catch (Exception e) {
            dataRes.setRetCode(SystemDefine.FAILURE);
            dataRes.setRetMsg("查询坐标出错");
        }
        return dataRes;
    }


    /**
     * 微信端查询订单坐标信息校验请求参数
     * @param req 订单坐标信息校验请求参数
     * @param dataRes 订单坐标信息校验返回信息
     * @return true:校验不通过,false:校验通过
     */
    private boolean validateCdntReq(WeChatOrderCdntReq req, WeChatOrderCdntRes dataRes) {

        logger.info("微信端查询订单坐标校验参数开始--begin");

        if (StringUtils.isBlank(req.getBusiBookNo())) {
            dataRes.setRetCode(SystemDefine.FAILURE);
            dataRes.setRetMsg("空订单号");
            return true;
        }
        BookingForm bf = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusiBookNo());
        if (bf == null) {
            dataRes.setRetCode(SystemDefine.FAILURE);
            dataRes.setRetMsg("订单已不存在");
            return true;
        }
        logger.info("微信端查询订单坐标校验参数结束--to the end");

        return false;
    }

}
