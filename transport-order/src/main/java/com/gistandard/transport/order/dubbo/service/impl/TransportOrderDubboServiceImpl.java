package com.gistandard.transport.order.dubbo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.app.dubbo.order.bean.*;
import com.gistandard.transport.app.dubbo.order.service.TransportOrderDubboService;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.BookingFormDao;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccountRoleRelDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComCompanyStaffDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComCustomerDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComUserinfoDaoEx;
import com.gistandard.transport.base.entity.service.ComAccountService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.CustomerOrderService;
import com.gistandard.transport.order.module.customer.TransportOrderService;
import com.gistandard.transport.order.module.customer.bean.*;
import com.gistandard.transport.order.webservice.client.merchant.order.BaseRequestResult;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: TransportOrderDubboServiceImpl
 * @Date: 2017/7/31
 * @Description: 运输平台订单Dubbo服务实现类
 */
public class TransportOrderDubboServiceImpl implements TransportOrderDubboService {

    private static final Logger logger = LoggerFactory.getLogger(TransportOrderDubboServiceImpl.class);

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private BookingFormDao bookingFormDao;

    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;

    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;

    @Autowired
    private ComCompanyStaffDaoEx comCompanyStaffDaoEx;

    @Autowired
    private ComAccountRoleRelDaoEx comAccountRoleRelDaoEx;

    @Autowired
    private ComAccountService comAccountService;

    @Autowired
    private TransportOrderService transportOrderService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;

    /**
     * 同城快递 同城专送 下单接口
     * @param peoReq 请求对象
     * @return 下单接口返回
     */
    @Override
    public PlaceOrderResult placeExpressOrder(PlaceExpressOrderReq peoReq) {
        logger.info("同城快递 同城专送 下单接口 req {}", JSON.toJSONString(peoReq));
        PlaceOrderResult result = new PlaceOrderResult();
        PlaceAnOrderReq req = new PlaceAnOrderReq();
        try {
            if (StringUtils.isNotBlank(peoReq.getGoodsInfoListStr())) {
                JSONArray JS = JSON.parseArray(peoReq.getGoodsInfoListStr());
                List<GoodsInfo> giList = new ArrayList<>();
                for (Object object : JS) {
                    GoodsInfo goodsInfo = JSON.parseObject(object.toString(), GoodsInfo.class);
                    giList.add(goodsInfo);
                }
                req.setGoodsInfos(giList);
            }
            BeanUtils.copyProperties(req, peoReq);
        } catch (Exception e) {
            result.setRetCode(SystemDefine.FAILURE);
            result.setRetMsg(e.getMessage());
            return result;
        }
        logger.debug("apply jsonStr={}", JSON.toJSONString(req));
        ComAccount comAccount = comAccountService.queryAccountById(req.getAccountId());
        if (comAccount == null) {
            result.setRetCode(SystemDefine.FAILURE);
            result.setRetMsg("操作用户不存在！");
            return result;
        }
        getApploginInfo(req, comAccount);
        if (req != null && req.getAppLoginInfo() != null && req.getAppLoginInfo().getCurrentComCustomer() != null) {
            if (req.getAppLoginInfo().getCurrentComCustomer().getAccountId() != null) {
                req.setStaffAccountId(req.getAppLoginInfo().getCurrentComCustomer().getAccountId());
            }
        }
        if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(req.getItemCode()) && req.getMileage() == null) {
            result.setRetCode(SystemDefine.FAILURE);
            result.setRetMsg("同城专送单必须有公里数！");
            return result;
        }
        PlaceAnOrderRes placeAnOrderRes;
        try {
            if ((req.isDestnErr() != null && req.isDestnErr()) || (req.isStartErr() != null && req.isStartErr())) {
                placeAnOrderRes = customerOrderService.placeAnErrOrder(req);
            } else {
                placeAnOrderRes = customerOrderService.placeAnOrder(req);
            }
            //更新路由
            if (req.getItemCode() != null && !MobileStationDefine.PRODUCT_TYPE_TCZS.equals(req.getItemCode())) {
                BaseRequestResult webServiceRes = mobileRecOrderWebService.updateOrderRouteId(placeAnOrderRes.getBusiBookNo());
                if (webServiceRes == null || webServiceRes.getStatus() != 1) {
                    throw new MobileStationBizException(webServiceRes == null ? "调用签派更新路由失败" : (webServiceRes.getMesasge() == null ? "调用签派更新路由失败" : webServiceRes.getMesasge()));
                }
            }
        } catch (Exception e) {
            result.setRetCode(SystemDefine.FAILURE);
            result.setRetMsg(e.getMessage());
            return result;
        }
        logger.info("快递专送下单业务接口完成--等待将结果值copyProperties");
        PlaceOrderDubboRes res = new PlaceOrderDubboRes();
        try {
            BeanUtils.copyProperties(res, placeAnOrderRes);
        } catch (Exception e) {
            result.setRetCode(SystemDefine.FAILURE);
            result.setRetMsg(e.getMessage());
            return result;
        }
        logger.debug("同城快递或专送客户下单APP订单保存 apply操作结果{}", JSON.toJSONString(result));
        result.setData(res);
        //TODO 同城快递或专送 用户也是需要通知GPS的
        placeAnOrderRes.setRoleId(SysAccountRole.NORMAL_PERSONAL.getValue());
        customerOrderService.notifyGps(placeAnOrderRes, null);
        return result;
    }


    /**
     * 同城运输 下单接口
     * @param ptoReq 请求对象
     * @return 下单接口返回
     */
    @Override
    public PlaceOrderResult placeTransportOrder(PlaceTransportOrderReq ptoReq) {
        logger.info("同城运输 下单接口 ptoReq {}", JSON.toJSONString(ptoReq));
        PlaceOrderResult result = new PlaceOrderResult();
        PlaceAnOrderReq req = new PlaceAnOrderReq();
        try {
            BeanUtils.copyProperties(req, ptoReq);
            if (StringUtils.isNotBlank(ptoReq.getGoodsInfoListStr())) {
                JSONArray JS = JSON.parseArray(ptoReq.getGoodsInfoListStr());
                List<GoodsInfo> giList = new ArrayList<>();
                for (Object object : JS) {
                    GoodsInfo goodsInfo = JSON.parseObject(object.toString(), GoodsInfo.class);
                    giList.add(goodsInfo);
                }
                req.setGoodsInfos(giList);
            }
            if (StringUtils.isNotBlank(ptoReq.getMobileValueAddListStr())) {
                JSONArray JA = JSON.parseArray(ptoReq.getMobileValueAddListStr());
                List<MobileValueAdd> mobileValueAddList = new ArrayList<>();
                for (Object object : JA) {
                    MobileValueAdd mva = JSON.parseObject(object.toString(), MobileValueAdd.class);
                    mobileValueAddList.add(mva);
                }
                req.setMobileValueAddList(mobileValueAddList);
            }
        } catch (Exception e) {
            result.setRetCode(SystemDefine.FAILURE);
            result.setRetMsg(e.getMessage());
            return result;
        }
        logger.debug("-同城运输用户下单请求--{}", JSON.toJSONString(req));
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(req.getAccountId());
        if (comAccount == null) {
            result.setRetCode(SystemDefine.FAILURE);
            result.setRetMsg("操作用户不存在！");
            return result;
        }
        getApploginInfo(req, comAccount);
        PlaceAnOrderRes placeAnOrderRes;
        try {
            placeAnOrderRes = transportOrderService.placeAnOrder(req);
        } catch (Exception e) {
            result.setRetCode(SystemDefine.FAILURE);
            result.setRetMsg(e.getMessage());
            return result;
        }
        logger.info("运输下单业务接口完成--等待将结果值copyProperties");
        PlaceOrderDubboRes res = new PlaceOrderDubboRes();
        try {
            BeanUtils.copyProperties(res, placeAnOrderRes);
        } catch (Exception e) {
            result.setRetCode(SystemDefine.FAILURE);
            result.setRetMsg(e.getMessage());
            return result;
        }
        logger.debug("同城运输客户下单APP订单保存 apply操作结果{}", JSON.toJSONString(res));
        result.setData(res);
        //TODO 同城运输 用户也是需要通知GPS的
        placeAnOrderRes.setRoleId(SysAccountRole.NORMAL_PERSONAL.getValue());
        customerOrderService.notifyGps(placeAnOrderRes, null);
        return result;
    }


    /**
     * 获取apploginInfo(对请求注入)
     * @param req req
     * @param comAccount comAccount
     */
    private void getApploginInfo(PlaceAnOrderReq req, ComAccount comAccount) {
        String acctUserName = comAccount.getAcctUsername();
        Integer accountId = comAccount.getId();
        logger.info("accountId-{}-;--acctUserName-{}", accountId, acctUserName);
        AppLoginInfo appLoginInfo = new AppLoginInfo();
        appLoginInfo.setAccountId(accountId);
        appLoginInfo.setAcctUsername(acctUserName);
        appLoginInfo.setApplicationName(SystemDefine.APPLICATION_NAME);//todo
        List<ComAccountRoleRel> comAccountRoleRels = comAccountRoleRelDaoEx.queryByAccountId(accountId);
        ComCompanyStaff comCompanyStaff = comCompanyStaffDaoEx.queryCompanyByAccount(accountId);
        ComCustomer comCustomer = null;
        ComCustomer currentComCustomer = null;
        ComAccount currentComCustomerAccount = null;
        if (comCompanyStaff != null) {
            comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(comCompanyStaff.getCompanyAccountId());
            currentComCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(comCompanyStaff.getCompanyAccountId());
            currentComCustomerAccount = comAccountDao.selectByPrimaryKey(comCompanyStaff.getCompanyAccountId());
        }
        ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(accountId);
        appLoginInfo.setComAccount(comAccount);
        appLoginInfo.setComAccountRoleRels(comAccountRoleRels);
        appLoginInfo.setComCustomer(comCustomer);
        appLoginInfo.setComUserinfo(comUserinfo);
        if(req.getCompanyAccountId() != null){
            appLoginInfo.setCurrentComCustomerAccount(currentComCustomerAccount);
            appLoginInfo.setCurrentComCustomer(currentComCustomer);
        }
        req.setAppLoginInfo(appLoginInfo);
        logger.info("setApploginInfo注入完成--req-{}", JSON.toJSONString(req));
    }


    /**
     * 同城运输 用户确认报价
     * @param handleOrderPriceReq 请求对象
     * @return 用户确认报价返回
     */
    @Override
    public MsDubboResBean confirmOrderPrice(HandleOrderPriceReq handleOrderPriceReq) {
        logger.info("dubbo确认报价入参-{}", JSON.toJSONString(handleOrderPriceReq));
        MsDubboResBean res = new MsDubboResBean();
        QuotePriceReq req = new QuotePriceReq();
        try {
            BeanUtils.copyProperties(req, handleOrderPriceReq);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException-{},", e);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("确认报价出错");
            return res;
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException{},", e);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("确认报价出错");
            return res;
        }
        try {
            QuotePriceRes result = transportOrderService.handleQuotePrice(req);
            BeanUtils.copyProperties(res, result);
        } catch (MobileStationBizException e) {
            logger.error("MobileStationBizException-{},", e);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
            return res;
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException-{},", e);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("确认报价出错");
            return res;
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException{},", e);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("确认报价出错");
            return res;
        }
        logger.info("dubbo确认报价返回-{}", JSON.toJSONString(res));
        return res;
    }


    /**
     * 同城运输 用户取消报价
     * @param handleOrderPriceReq 请求对象
     * @return 用户取消报价返回
     */
    @Override
    public MsDubboResBean cancleOrderPrice(HandleOrderPriceReq handleOrderPriceReq) {
        logger.info("dubbo取消报价入参-{}", JSON.toJSONString(handleOrderPriceReq));
        MsDubboResBean res = new MsDubboResBean();
        CancleOrderReq req = new CancleOrderReq();
        try {
            BeanUtils.copyProperties(req, handleOrderPriceReq);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException-{},", e);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("取消报价出错");
            return res;
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException{},", e);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("取消报价出错");
            return res;
        }
        try {
            CancleOrderRes result = transportOrderService.handleCancleOrder(req);
            BeanUtils.copyProperties(res, result);
        } catch (MobileStationBizException e) {
            logger.error("MobileStationBizException-{},", e);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
            return res;
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException-{},", e);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("取消报价出错");
            return res;
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException{},", e);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("取消报价出错");
            return res;
        }
        logger.info("dubbo取消报价返回-{}", JSON.toJSONString(res));
        return res;
    }


    /**
     * 同城快递、同城专送 用户取消订单
     * @param handleCleOrderReq 请求对象
     * @return 用户取消报价返回
     */
    @Override
    public MsDubboResBean cancleOrder4KDZS(HandleCleOrderReq handleCleOrderReq) {
        logger.info("dubboKDZS取消订单入参-{}", JSON.toJSONString(handleCleOrderReq));
        MsDubboResBean res = new MsDubboResBean();
        CancleOrderReq req = new CancleOrderReq();
        BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(handleCleOrderReq.getId());
        if (bookingForm == null) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("订单不存在");
            return res;
        }
        req.setOrderId(handleCleOrderReq.getId());
        req.setReason(handleCleOrderReq.getReason());
        req.setBusiBookNo(bookingForm.getBusiBookNo());
        req.setProductType(bookingForm.getTransportType());
        req.setAcctUsername(handleCleOrderReq.getAcctUsername());
        try {
            customerOrderService.cancelOrder4KDZS(req);
        } catch (MobileStationBizException e) {
            logger.error("MobileStationBizException-{},", e);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
            return res;
        }
        logger.info("dubboKDZS取消订单结束--successful!!");
        return res;
    }
}
