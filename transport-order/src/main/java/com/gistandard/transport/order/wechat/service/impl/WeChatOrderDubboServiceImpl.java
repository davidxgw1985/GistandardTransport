package com.gistandard.transport.order.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gistandard.transport.app.dubbo.genno.GenerateOrderNumberService;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.app.dubbo.wechat.bean.*;
import com.gistandard.transport.app.dubbo.wechat.service.WeChatOrderDubboService;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.BookingFormDao;
import com.gistandard.transport.base.entity.dao.BookingFormErrDao;
import com.gistandard.transport.base.entity.dao.ComWaybillTraceDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComCurrencyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.webservice.client.merchant.order.BaseRequestResult;
import com.gistandard.transport.order.webservice.client.merchant.order.Exception_Exception;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import com.gistandard.transport.order.wechat.dao.WeChatOrderDao;
import com.gistandard.transport.order.wechat.dao.WeChatOrderTraceDao;
import com.gistandard.transport.quote.system.database.services.ComQuoteService;
import com.gistandard.transport.system.common.address.dao.CustomerMobileAddressInfoDao;
import com.gistandard.transport.system.common.bean.AddressQueryRes;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.common.bean.AccountInfo;
import com.valueplus.psc.dubbo.service.common.bean.ServiceAuthBean;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import com.valueplus.psc.dubbo.service.login.AccountService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by m on 2017/2/7.
 */
public class WeChatOrderDubboServiceImpl implements WeChatOrderDubboService {

    private static final Logger logger = LoggerFactory.getLogger(WeChatOrderDubboServiceImpl.class);


    @Autowired
    private BookingFormDao bookingFormDao;
    @Autowired
    private BookingFormErrDao bookingFormErrDao;

    @Autowired
    private ComWaybillTraceDao comWaybillTraceDao;

    @Autowired
    private ComQuoteService comQuoteService;

    @Autowired
    private GenerateOrderNumberService generateOrderNumberService;

    @Autowired
    private ComCurrencyService comCurrencyService;

    @Autowired
    private GpsLogService gpsLogService;

    @Autowired
    private ComCountyService comCountyService;

    @Autowired
    private ComProvinceService comProvinceService;

    @Autowired
    private GpsOrderService gpsOrderService;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private CustomerMobileAddressInfoDao customerMobileAddressInfoDao;

    @Autowired
    private WeChatOrderDao weChatOrderDao;

    @Autowired
    private WeChatOrderTraceDao weChatOrderTraceDao;

    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

    @Autowired
    private AccountService accountService;

    @Value("#{spring.user}")
    private String user;

    @Value("#{spring.passwd}")
    private String passwd;

    @Value("#{customerOrderIM.imContentSystemUrl}")
    private String imContentSystemUrl;

    @Value("#{customerOrderIM.imPushUrl}")
    private String imPushUrl;

    @Value("#{customerOrderIM.customerOrderPredictValue}")
    private String customerOrderPredictValue;

    @Override
    public WeChatOrderRes saveWechatOrder(WeChatOrderReq req) {
        logger.debug("WeChatOrderDubboServiceImpl saveWechatOrder req={}", JSON.toJSONString(req));
        WeChatOrderRes weChatOrderRes = new WeChatOrderRes(req);
        try {
            weChatOrderRes = makeOrder(req);
        } catch (Exception e) {
            weChatOrderRes.setRetCode(SystemDefine.FAILURE);
            weChatOrderRes.setRetMsg(e.getMessage());
            logger.debug("WeChatOrderDubboServiceImpl saveWechatOrder res={}", JSON.toJSONString(weChatOrderRes));
            return weChatOrderRes;
        }
        if (weChatOrderRes == null || StringUtil.isEmpty(weChatOrderRes.getBusiBookNo())) {
            weChatOrderRes.setRetCode(SystemDefine.FAILURE);
            weChatOrderRes.setRetMsg(weChatOrderRes.getRetMsg());
            return weChatOrderRes;
        }
        if ((req.isStartErr() != null && req.isStartErr()) || (req.isDestnErr() != null && req.isDestnErr())) {
            notifyGPS(weChatOrderRes.getOrderId(), true);
        } else {
            try {
                logger.debug("WeChatOrderDubboServiceImpl updateOrderRouteId ={}", JSON.toJSONString(weChatOrderRes));
                if (req.getItemCode() != null && !MobileStationDefine.PRODUCT_TYPE_TCZS.equals(req.getItemCode())) {
                    MsDubboResBean appBaseResult = updateOrderRouteId(weChatOrderRes.getBusiBookNo());
                    if (appBaseResult.getRetCode() == SystemDefine.FAILURE) {
                        cancleWechatOrder(weChatOrderRes.getOrderId());
                        weChatOrderRes.setRetCode(SystemDefine.FAILURE);
                        weChatOrderRes.setRetMsg(appBaseResult.getRetMsg());
                        logger.debug("WeChatOrderDubboServiceImpl saveWechatOrder res={}", JSON.toJSONString(weChatOrderRes));
                        return weChatOrderRes;
                    }
                }
            } catch (Exception e) {
                cancleWechatOrder(weChatOrderRes.getOrderId());
                weChatOrderRes.setRetCode(SystemDefine.FAILURE);
                weChatOrderRes.setRetMsg(e.getMessage());
                logger.debug("WeChatOrderDubboServiceImpl saveWechatOrder res={}", JSON.toJSONString(weChatOrderRes));
                return weChatOrderRes;
            }
            notifyGPS(weChatOrderRes.getOrderId(), false);
        }
        logger.debug("WeChatOrderDubboServiceImpl saveWechatOrder res={}", JSON.toJSONString(weChatOrderRes));
        return weChatOrderRes;
    }

    /**
     * 我的订单列表
     *
     * @param weChatOrderQueryReq
     * @return
     */
    @Override
    public WeChatOrderQueryRes queryOrderListByWeChatId(WeChatOrderQueryReq weChatOrderQueryReq) {
        logger.debug("WeChatOrderDubboServiceImpl queryOrderListByWeChatId req={}", JSON.toJSONString(weChatOrderQueryReq));
        WeChatOrderQueryRes res = new WeChatOrderQueryRes();
        List<OrderQueryDetail> dataLists = weChatOrderDao.queryOrderListByWeChatId(weChatOrderQueryReq);
        if (dataLists != null) {
            res.setOrderQueryDetailList(dataLists);
        }
        logger.debug("WeChatOrderDubboServiceImpl queryOrderListByWeChatId res={}", JSON.toJSONString(res));
        return res;
    }

    /**
     * 根据单号查询订单跟踪信息
     *
     * @param weChatOrderTraceReq
     * @return
     */
    @Override
    public WeChatOrderTracRes queryOrderTraceByBusiNo(WeChatOrderTraceReq weChatOrderTraceReq) {
        logger.debug("WeChatOrderDubboServiceImpl queryOrderTraceByBusiNo req={}", JSON.toJSONString(weChatOrderTraceReq));
        WeChatOrderTracRes res = new WeChatOrderTracRes();
        List<OrderTraceDetail> dataLists = weChatOrderTraceDao.queryOrderTraceByBusiNo(weChatOrderTraceReq);
        if (dataLists != null) {
            res.setOrderTraceDetailList(dataLists);
        }
        logger.debug("WeChatOrderDubboServiceImpl queryOrderTraceByBusiNo res={}", JSON.toJSONString(res));
        return res;
    }

    /**
     * 参数校验
     *
     * @param req
     * @throws MobileStationBizException
     */
    private void checkParam(WeChatOrderReq req) throws MobileStationBizException {
        if (StringUtils.isBlank(req.getQuoteNo())) {
            throw new MobileStationBizException("客户下单报价单为空");
        }

        if (StringUtil.isEmpty(req.getWeChatId())) {
            throw new MobileStationBizException("微信ID为空");
        }
//        if (StringUtil.isEmpty(req.getWeChatName())) {
//            throw new MobileStationBizException("微信下单人名为空");
//        }
        ComQuote comQuote = comQuoteService.getQuoteByQuoteNo(req.getQuoteNo());
        if (comQuote == null) {
            throw new MobileStationBizException("报价单不存在！");
        }

        Integer transType = req.getTransType();
        if (transType == null || (transType != 1 && transType != 2)) {
            throw new MobileStationBizException("客户下单类型入参错误");
        }

        if (StringUtil.isEmpty(req.getItemCode())) {
            throw new MobileStationBizException("客户下单产品代码入参为空");
        }
//        checkAddress(req);
        Integer payType = req.getPaymentType();
        if (payType == null || (payType != 3 && payType != 2 && payType != 1)) {
            throw new MobileStationBizException("客户下单付款方式入参错误");
        }
        //1-客户自送，2-上门接货
        Integer accessMethod = req.getAccessMethod();
        if (accessMethod == null || (accessMethod != 1 && accessMethod != 2)) {
            throw new MobileStationBizException("客户下单取件方式入参错误");
        }
        if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(req.getItemCode()) && req.getMileage() == null) {
            throw new MobileStationBizException("同城专送单必须有公里数");
        }
    }


    /**
     * 客户下单地址校验
     *
     * @param req
     * @throws MobileStationBizException
     */
    private void checkAddress(WeChatOrderReq req) throws MobileStationBizException {
        Integer senderAddrId = req.getSenderAddrId();
        if (senderAddrId == null && req.getSenderAddr() == null) {
            throw new MobileStationBizException("发件人地址入参错误");
        }

        Integer receiverAddrId = req.getReceiverAddrId();
        if (receiverAddrId == null) {
            throw new MobileStationBizException("收件人地址入参错误");
        }
    }

    private String getCity(String cityCode) {
        StringBuilder routeFullName = new StringBuilder();
        //区县
        ComCounty tCounty = comCountyService.queryForMap().get(cityCode);
        if (null != tCounty) {
            routeFullName.append(tCounty.getAreaName());
            cityCode = StringUtil.getObjStr(tCounty.getCityId());
        }
        ComCity tCtiy = comCityService.queryForMap().get(cityCode);
        if (null != tCtiy) {
            routeFullName.insert(0, tCtiy.getName() + " ");
            cityCode = StringUtil.getObjStr(tCtiy.getProvinceId());
        }
        ComProvince comProvince = comProvinceService.queryForMap().get(cityCode);
        if (null != comProvince) {
            routeFullName.insert(0, comProvince.getProvinceName() + " ");
        }
        return routeFullName.toString();
    }

    /**
     * 保存收货地址
     *
     * @param req
     * @param bookingForm
     */
    private void saveReceiverAddress(WeChatOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        Integer receiverAddrId = req.getReceiverAddrId();
        //如果只传递了是地址id,代表是选择的已有地址
        if (receiverAddrId != null) {
            AddressQueryRes addressQueryRes = customerMobileAddressInfoDao.queryByPrimaryKey(receiverAddrId);
            if (addressQueryRes == null) {
                throw new MobileStationBizException("地址不存在");
            }
            bookingForm.setCneeCustAddr(addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress());
            bookingForm.setCneeCustLinkMan(addressQueryRes.getName());
            bookingForm.setCneeCustLinkTel(addressQueryRes.getTelephone());
            bookingForm.setCarriageDelivAddr((addressQueryRes.getProvinceName() == null ? "" : addressQueryRes.getProvinceName()) + (addressQueryRes.getCityName() == null ? "" : addressQueryRes.getCityName()) + (addressQueryRes.getCountyName() == null ? "" : addressQueryRes.getCountyName()) + (addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress()) + (addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress()));
            bookingForm.setCneeCustHouseNumber(addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress());
            bookingForm.setCarriageDelivLongitude(addressQueryRes.getAddressLongitude());
            bookingForm.setCarriageDelivLatitude(addressQueryRes.getAddressLatitude());
            if (!StringUtil.isEmpty(addressQueryRes.getCounty()) && !"0".equals(addressQueryRes.getCounty())) {
                bookingForm.setCarriageDelivCounty(addressQueryRes.getCounty());
            }
            if (addressQueryRes.getCity() != null) {
                bookingForm.setCarriageDelivCity(addressQueryRes.getCity());
            }
            if (addressQueryRes.getProvince() != null) {
                bookingForm.setCarriageDelivProvince(addressQueryRes.getProvince());
            }
            if (addressQueryRes.getCompanyName() != null) {
                bookingForm.setCneeCustName(addressQueryRes.getCompanyName());
            }
            if (addressQueryRes.getZipCode() != null) {
                bookingForm.setReceiverZipCode(addressQueryRes.getZipCode());
            }

        } else {
            if (req.getReceiverAddr() == null) {
                throw new MobileStationBizException("地址不存在");
            }
            bookingForm.setCneeCustName(req.getReceiverAddr().getName());
            bookingForm.setCneeCustHouseNumber(req.getReceiverAddr().getDetailAddress() == null ? "" : req.getReceiverAddr().getDetailAddress());
            bookingForm.setCneeCustAddr(req.getReceiverAddr().getAddress() == null ? "" : req.getReceiverAddr().getAddress());
            bookingForm.setCneeCustLinkMan(req.getReceiverAddr().getName());
            bookingForm.setCneeCustLinkTel(req.getReceiverAddr().getTelephone());
            /*收件人地址*/
            StringBuffer sb = new StringBuffer();
            if (StringUtils.isNotBlank(req.getReceiverAddr().getProvinceName())) {
                sb.append(req.getReceiverAddr().getProvinceName());
            }
            if (StringUtils.isNotBlank(req.getReceiverAddr().getCityName())) {
                sb.append(req.getReceiverAddr().getCityName());
            }
            if (StringUtils.isNotBlank(req.getReceiverAddr().getCountyName())) {
                sb.append(req.getReceiverAddr().getCountyName());
            }
            if (StringUtils.isNotBlank(req.getReceiverAddr().getAddress())) {
                sb.append(req.getReceiverAddr().getAddress());
            }
            if (StringUtils.isNotBlank(req.getReceiverAddr().getDetailAddress())) {
                sb.append(req.getReceiverAddr().getDetailAddress());
            }
            bookingForm.setCarriageDelivAddr(sb.toString());
            /*县级单位*/
            if (!StringUtil.isEmpty(req.getReceiverAddr().getCounty()) && !"0".equals(req.getReceiverAddr().getCounty())) {
                bookingForm.setCarriageDelivCounty(req.getReceiverAddr().getCounty());
            }
            if (req.getReceiverAddr().getCity() != null) {
                bookingForm.setCarriageDelivCity(req.getReceiverAddr().getCity());
            }
            if (req.getReceiverAddr().getProvince() != null) {
                bookingForm.setCarriageDelivProvince(req.getReceiverAddr().getProvince());
            }
            if (req.getReceiverAddr().getCompanyName() != null) {
                bookingForm.setCneeCustName(req.getReceiverAddr().getCompanyName());
            }
            if (req.getReceiverAddr().getZipCode() != null) {
                bookingForm.setReceiverZipCode(req.getReceiverAddr().getZipCode());
            }
            bookingForm.setCarriageDelivLongitude(req.getReceiverAddr().getAddressLongitude());
            bookingForm.setCarriageDelivLatitude(req.getReceiverAddr().getAddressLatitude());
        }
    }

    /**
     * 保存发货地址
     *
     * @param req
     * @param bookingForm
     */
    private void saveSenderAddress(WeChatOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        Integer senderAddrId = req.getSenderAddrId();
        //如果只传递了发货地址ID,代表是选择的已有地址
        if (senderAddrId != null) {
            AddressQueryRes addressQueryRes = customerMobileAddressInfoDao.queryByPrimaryKey(senderAddrId);
            if (addressQueryRes == null) {
                throw new MobileStationBizException("地址不存在");
            }
            bookingForm.setShipCustaDdr(addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress());
            bookingForm.setShipCustHouseNumber(addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress());
            bookingForm.setShipCustlinkMan(addressQueryRes.getName());
            bookingForm.setShipCustlinkTel(addressQueryRes.getTelephone());
            bookingForm.setCarriageReceiAddr((addressQueryRes.getProvinceName() == null ? "" : addressQueryRes.getProvinceName()) + (addressQueryRes.getCityName() == null ? "" : addressQueryRes.getCityName()) + (addressQueryRes.getCountyName() == null ? "" : addressQueryRes.getCountyName()) + (addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress()) + (addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress()));
            bookingForm.setCarriageReceiLongitude(addressQueryRes.getAddressLongitude());
            bookingForm.setCarriageReceiLatitude(addressQueryRes.getAddressLatitude());
            if (!StringUtil.isEmpty(addressQueryRes.getCounty()) && !"0".equals(addressQueryRes.getCounty())) {
                bookingForm.setCarriageReceiCounty(addressQueryRes.getCounty());
            }
            if (addressQueryRes.getCity() != null) {
                bookingForm.setCarriageReceiCity(addressQueryRes.getCity());
            }
            if (addressQueryRes.getProvince() != null) {
                bookingForm.setCarriageReceiProvince(addressQueryRes.getProvince());
            }
            if (addressQueryRes.getCompanyName() != null) {
                bookingForm.setShipCustName(addressQueryRes.getCompanyName());
            }
            if (addressQueryRes.getZipCode() != null) {
                bookingForm.setSenderZipCode(addressQueryRes.getZipCode());
            }

        } else {
            if (req.getSenderAddr() == null) {
                throw new MobileStationBizException("地址不存在");
            }
            bookingForm.setShipCustaDdr(req.getSenderAddr().getAddress() == null ? "" : req.getSenderAddr().getAddress());
            bookingForm.setShipCustHouseNumber(req.getSenderAddr().getDetailAddress() == null ? "" : req.getSenderAddr().getDetailAddress());
            bookingForm.setShipCustlinkMan(req.getSenderAddr().getName());
            bookingForm.setShipCustlinkTel(req.getSenderAddr().getTelephone());
             /*发件人地址*/
            StringBuffer sb = new StringBuffer();
            if (StringUtils.isNotBlank(req.getSenderAddr().getProvinceName())) {
                sb.append(req.getSenderAddr().getProvinceName());
            }
            if (StringUtils.isNotBlank(req.getSenderAddr().getCityName())) {
                sb.append(req.getSenderAddr().getCityName());
            }
            if (StringUtils.isNotBlank(req.getSenderAddr().getCountyName())) {
                sb.append(req.getSenderAddr().getCountyName());
            }
            if (StringUtils.isNotBlank(req.getSenderAddr().getAddress())) {
                sb.append(req.getSenderAddr().getAddress());
            }
            if (StringUtils.isNotBlank(req.getSenderAddr().getDetailAddress())) {
                sb.append(req.getSenderAddr().getDetailAddress());
            }
            bookingForm.setCarriageReceiAddr(sb.toString());
            if (!StringUtil.isEmpty(req.getSenderAddr().getCounty()) && !"0".equals(req.getSenderAddr().getCounty())) {
                bookingForm.setCarriageReceiCounty(req.getSenderAddr().getCounty());
            }
            if (req.getSenderAddr().getCity() != null) {
                bookingForm.setCarriageReceiCity(req.getSenderAddr().getCity());
            }
            if (req.getSenderAddr().getProvince() != null) {
                bookingForm.setCarriageReceiProvince(req.getSenderAddr().getProvince());
            }
            if (req.getSenderAddr().getCompanyName() != null) {
                bookingForm.setShipCustName(req.getSenderAddr().getCompanyName());
            }
            if (req.getSenderAddr().getZipCode() != null) {
                bookingForm.setSenderZipCode(req.getSenderAddr().getZipCode());
            }
            bookingForm.setCarriageReceiLongitude(req.getSenderAddr().getAddressLongitude());
            bookingForm.setCarriageReceiLatitude(req.getSenderAddr().getAddressLatitude());
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public WeChatOrderRes makeOrder(WeChatOrderReq req) {
        WeChatOrderRes placeAnOrderRes = new WeChatOrderRes(req);
        BookingForm bookingForm = new BookingForm();
        try {
            //check param.
            checkParam(req);
            //保存地址信息
            saveSenderAddress(req, bookingForm);
            saveReceiverAddress(req, bookingForm);
        } catch (MobileStationBizException e) {
            placeAnOrderRes.setRetCode(SystemDefine.FAILURE);
            placeAnOrderRes.setRetMsg(e.getMessage());
            logger.debug("WeChatOrderDubboServiceImpl makeOrder res={}", JSON.toJSONString(placeAnOrderRes));
            return placeAnOrderRes;
        }

        //报价单
        if (null != req.getQuoteNo()) {
            bookingForm.setDocno(req.getQuoteNo());
            ComQuote comQuote = comQuoteService.getQuoteByQuoteNo(req.getQuoteNo());
            if (null != comQuote) {
                bookingForm.setQuotedType(comQuote.getQuoteType());
                bookingForm.setPredictCurr(comQuote.getCurrencyCode());
            }
        }

//        if (null != req.getWeChatName()) {
//            bookingForm.setShipCustlinkMail(req.getWeChatName());
//        }

        if (null != req.getNarrate()) {
            bookingForm.setNarrate(req.getNarrate());
        }
        if (null != req.getOrderForm()) {
            bookingForm.setOrderForm(req.getOrderForm());
        } else {
            bookingForm.setOrderForm(CustomerDefine.IM_ORDERFORM_WECHAT);
        }
        if (null != req.getWeChatId()) {
            bookingForm.setWechatId(req.getWeChatId());
        }


        bookingForm.setCarriageDeiv("2");//送货上门


        //保存主表信息
        bookingForm.setBookingDate(new Date());//申请订单日期
        bookingForm.setOrderType(req.getTransType()); // 1运输  2快递

        bookingForm.setPayType(req.getPaymentType());//1-到付 2-在线支付 3-现金支付

        bookingForm.setCarriAgerecei(req.getAccessMethod() + "");//取货方式：1-客户自送，2-上门接货
        if (!StringUtils.isEmpty(req.getAccessTime())) {
            bookingForm.setAccesstime(req.getAccessTime());
        }

        bookingForm.setBusiCtrl(OrderState.WAITRE_CEIVING.getValue());//待接单

        bookingForm.setCargoLoader("0");//整车：1, 零担：0, 自拼车：2

        String busiNo = generateOrderNumberService.getOrderNumberByCityId(NumberUtils.toInt(bookingForm.getCarriageReceiCity()));
        if (StringUtil.isEmpty(busiNo)) {
            placeAnOrderRes.setRetCode(SystemDefine.FAILURE);
            placeAnOrderRes.setRetMsg("获取订单号异常!");
            logger.debug("WeChatOrderDubboServiceImpl makeOrder res={}", JSON.toJSONString(placeAnOrderRes));
            return placeAnOrderRes;
        }
        bookingForm.setBusiBookNo(busiNo);
        bookingForm.setWaybillNo(busiNo);

        bookingForm.setIsJs(0);
        bookingForm.setIsDel(0);
        bookingForm.setSmsNoty(0);


        if (!StringUtil.isEmpty(req.getPredictCurr())) {
            bookingForm.setPredictCurr(req.getPredictCurr());
        }
        bookingForm.setTransportType(req.getItemCode());
        bookingForm.setWhtGrosswht(new BigDecimal(0));
        bookingForm.setWhtVolcbm(new BigDecimal(0));
        bookingForm.setGoodsQty(new BigDecimal(0));

        bookingForm.setSenderFollow(CustomerDefine.UNFOLLOW);
        bookingForm.setReceiverFollow(CustomerDefine.UNFOLLOW);

        if (!StringUtil.isEmpty(bookingForm.getTeamComsixName())) {
            bookingForm.setStartLocusNa(bookingForm.getTeamComsixName());
        }
        if (!StringUtil.isEmpty(bookingForm.getTeamComsixNo())) {
            bookingForm.setStartLocus(bookingForm.getTeamComsixNo());
        }
        bookingForm.setNeedInsure(false);

        if (!StringUtil.isEmpty(req.getCreateUser())) {
            ComAccount comAccount = comAccountDaoEx.queryByAccount(req.getCreateUser());
            if (comAccount != null) {
                bookingForm.setCreateUserId(comAccount.getId());
            }
            bookingForm.setCreateUser(req.getCreateUser());
        }

        if (!StringUtil.isEmpty(bookingForm.getCneeCustLinkTel())) {
            ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
            serviceAuthBean.setAuthPwd(SystemDefine.CUSTOMER_AUTH_PWD);
            serviceAuthBean.setAuthUser(SystemDefine.CUSTOMER_AUTH_USER);
            serviceAuthBean.setSysFlag(SystemDefine.TRANSPORT_SYS_FLAG);
            ServiceResult serviceResult = accountService.queryAccountByTelephone(serviceAuthBean, bookingForm.getCneeCustLinkTel());
            if (serviceResult.isResult()) {
                List<AccountInfo> accountInfos = (List<AccountInfo>) serviceResult.getData();
                if (accountInfos != null) {
                    bookingForm.setReceiverUser(accountInfos.get(0).getAcctUsername());
                }
            }
        }

        if (req.getStaffAccountId() != null && req.getStaffAccountId() > 0) {
            bookingForm.setCreateCompanyId(req.getStaffAccountId());
        }
        if (req.getMileage() != null) {
            bookingForm.setMileage(req.getMileage());
        }
        if ((req.isStartErr() != null && req.isStartErr()) || (req.isDestnErr() != null && req.isDestnErr())) {
            //有错误地址坐标
            BookingFormErr bookingFormErr = new BookingFormErr();
            try {
                BeanUtils.copyProperties(bookingFormErr, bookingForm);
                bookingFormErr.setStartErr(req.isStartErr());
                bookingFormErr.setDestnErr(req.isDestnErr());
                bookingFormErrDao.insertSelective(bookingFormErr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String tempRouteBookNa = null;
            if (StringUtil.isEmpty(bookingForm.getCarriageReceiCounty()) || "0".equals(bookingForm.getCarriageReceiCounty())) {
                tempRouteBookNa = getCity(bookingForm.getCarriageReceiCity());
            } else {
                tempRouteBookNa = getCity(bookingForm.getCarriageReceiCounty());
            }
            tempRouteBookNa = tempRouteBookNa + "-";
            if (StringUtil.isEmpty(bookingForm.getCarriageDelivCounty()) || "0".equals(bookingForm.getCarriageDelivCounty())) {
                tempRouteBookNa = tempRouteBookNa + getCity(bookingForm.getCarriageDelivCity());
            } else {
                tempRouteBookNa = tempRouteBookNa + getCity(bookingForm.getCarriageDelivCounty());
            }
            bookingForm.setRouteBookNa(tempRouteBookNa);

            if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(req.getItemCode())) {
                try {
                    BaseRequestResult baseRequestResult = mobileRecOrderWebService.checkRouteExists(bookingForm.getCarriageReceiLongitude().doubleValue(),
                            bookingForm.getCarriageReceiLatitude().doubleValue(), bookingForm.getCarriageDelivLongitude().doubleValue(), bookingForm.getCarriageDelivLatitude().doubleValue());
                    if (baseRequestResult == null || baseRequestResult.getStatus() != 1) {
                        placeAnOrderRes.setRetCode(SystemDefine.FAILURE);
                        placeAnOrderRes.setRetMsg(baseRequestResult.getMesasge());
                        logger.debug("WeChatOrderDubboServiceImpl makeOrder res={}", JSON.toJSONString(placeAnOrderRes));
                        return placeAnOrderRes;
                    }
                } catch (Exception_Exception e) {
                    e.printStackTrace();
                    placeAnOrderRes.setRetCode(SystemDefine.FAILURE);
                    placeAnOrderRes.setRetMsg(e.getMessage());
                    logger.debug("WeChatOrderDubboServiceImpl makeOrder res={}", JSON.toJSONString(placeAnOrderRes));
                    return placeAnOrderRes;
                }
            }
        }
        bookingFormDao.insertSelective(bookingForm);

        ComWaybillTrace tmp = new ComWaybillTrace();


//        tmp.setRealName(req.getWeChatName());

        tmp.setBusiBookNo(bookingForm.getBusiBookNo());
        tmp.setGrade(0);
        tmp.setRemark(BusinessDefine.ORDER);

        tmp.setExecCode(WayBillStatusDefine.ORDER.getIntValue());

        tmp.setStaDate(new Date());
        tmp.setRoleId(1);
        comWaybillTraceDao.insert(tmp);

        if (StringUtil.isEmpty(bookingForm.getId())) {
            placeAnOrderRes.setRetCode(SystemDefine.FAILURE);
        } else {
            placeAnOrderRes.setRetCode(SystemDefine.SUCCESS);
            placeAnOrderRes.setBusiBookNo(bookingForm.getBusiBookNo());
            placeAnOrderRes.setOrderId(bookingForm.getId());
        }

        logger.debug("WeChatOrderDubboServiceImpl makeOrder res={}", JSON.toJSONString(placeAnOrderRes));
        return placeAnOrderRes;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MsDubboResBean updateOrderRouteId(String busiNo) {
        logger.debug("WeChatOrderDubboServiceImpl updateOrderRouteId req={}", JSON.toJSONString(busiNo));
        MsDubboResBean res = new MsDubboResBean();
        //更新路由
        BaseRequestResult result = null;
        try {
            result = mobileRecOrderWebService.updateOrderRouteId(busiNo);
            if (result == null) {
                res.setRetCode(SystemDefine.FAILURE);
                res.setRetMsg("调用签派更新路由失败");
            } else if (result.getStatus() != 1) {
                res.setRetCode(SystemDefine.FAILURE);
                res.setRetMsg(result.getMesasge());
            } else {
                res.setRetCode(SystemDefine.SUCCESS);
            }
        } catch (Exception_Exception e) {
            e.printStackTrace();
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("调用签派更新路由失败");
        }
        logger.debug("WeChatOrderDubboServiceImpl updateOrderRouteId res={}", JSON.toJSONString(res));
        return res;
    }

    @Override
    public void notifyGPS(Integer orderId, Boolean errOrder) {

        BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(orderId);
        if (null != bookingForm) {
            try {
                if (errOrder == null || !errOrder) {
                    List<GiBizOrder> pushBeanList = new ArrayList<>();
                    GiBizOrder pushBean = new GiBizOrder();
                    pushBean.setAction(MobileStationDefine.GPS_ACTION_RESYNC_DATA);
                    pushBean.setAppTag(CustomerDefine.IM_MS_DEFINE);
                    pushBean.setDocNo(bookingForm.getBusiBookNo());
                    pushBean.setDocId(bookingForm.getId());
                    pushBean.setDocFrom("6");
                    pushBean.setProductType(bookingForm.getTransportType());
                    pushBean.setBizType(bookingForm.getOrderType());
                    if (!StringUtil.isEmpty(bookingForm.getNarrate())) {
                        pushBean.setDescription(bookingForm.getNarrate());
                    }
                    pushBean.setDestAddress(bookingForm.getCarriageDelivAddr());
                    GiPoint dest = new GiPoint();
                    dest.setLatitude(bookingForm.getCarriageDelivLatitude().doubleValue());
                    dest.setLongitude(bookingForm.getCarriageDelivLongitude().doubleValue());
                    pushBean.setPointDest(dest);
                    if (!StringUtil.isEmpty(bookingForm.getCarriageDelivCounty())) {
                        ComCounty tCounty = comCountyService.queryForMap().get(bookingForm.getCarriageDelivCounty());
                        if (null != tCounty) {
                            pushBean.setDestDistrict(tCounty.getAreaName());
                        }
                    }
                    if (!StringUtil.isEmpty(bookingForm.getCarriageDelivCity())) {
                        ComCity tCtiy = comCityService.queryForMap().get(bookingForm.getCarriageDelivCity());
                        if (null != tCtiy) {
                            pushBean.setDestCity(tCtiy.getName());
                        }
                    }
                    if (!StringUtil.isEmpty(bookingForm.getCarriageDelivProvince())) {
                        ComProvince comProvince = comProvinceService.queryForMap().get(bookingForm.getCarriageDelivProvince());
                        if (null != comProvince) {
                            pushBean.setDestProvince(comProvince.getProvinceName());
                        }
                    }


                    pushBean.setSourceAddress(bookingForm.getCarriageReceiAddr());
                    pushBean.setSourceUserTel(bookingForm.getShipCustlinkTel());
                    pushBean.setSourceUserName(bookingForm.getShipCustlinkMan());
                    pushBean.setDestUserTel(bookingForm.getCneeCustLinkTel());
                    pushBean.setDestUserName(bookingForm.getCneeCustLinkMan());
                    GiPoint source = new GiPoint();
                    source.setLongitude(bookingForm.getCarriageReceiLongitude().doubleValue());
                    source.setLatitude(bookingForm.getCarriageReceiLatitude().doubleValue());
                    pushBean.setPointSource(source);
                    if (!StringUtil.isEmpty(bookingForm.getCarriageReceiCounty())) {
                        ComCounty tCounty = comCountyService.queryForMap().get(bookingForm.getCarriageReceiCounty());
                        if (null != tCounty) {
                            pushBean.setSourceDistrict(tCounty.getAreaName());
                        }
                    }
                    if (!StringUtil.isEmpty(bookingForm.getCarriageReceiCity())) {
                        ComCity tCtiy = comCityService.queryForMap().get(bookingForm.getCarriageReceiCity());
                        if (null != tCtiy) {
                            pushBean.setSourceCity(tCtiy.getName());
                        }
                    }
                    if (!StringUtil.isEmpty(bookingForm.getCarriageReceiProvince())) {
                        ComProvince comProvince = comProvinceService.queryForMap().get(bookingForm.getCarriageReceiProvince());
                        if (null != comProvince) {
                            pushBean.setSourceProvince(comProvince.getProvinceName());
                        }
                    }

                    pushBean.setTotalPrice(BigDecimal.valueOf(0));
                    if (null != bookingForm.getWhtGrosswht()) {
                        pushBean.setTotalWeight(bookingForm.getWhtGrosswht());
                    }
                    if (null != bookingForm.getWhtVolcbm()) {
                        pushBean.setTotalVolume(bookingForm.getWhtVolcbm());
                    }
                    if (null != bookingForm.getPredictCurr()) {
                        pushBean.setCurrencyName(comCurrencyService.getComCurrencyByCode(bookingForm.getPredictCurr()).getCurrencyCh());
                    }
                    pushBean.setAppTag(CustomerDefine.IM_MS_DEFINE);
                    pushBean.setDocId(bookingForm.getId());
                    pushBean.setDocNo(bookingForm.getBusiBookNo());
                    pushBean.setDocType(CustomerDefine.DocType_Pickup);
                    List<String> allModuleCode = new ArrayList<String>();
                    allModuleCode.add(bookingForm.getTransportType());
                    pushBean.setAllModuleCode(allModuleCode);
                    List<String> allRoleCode = new ArrayList<String>();
                    if (bookingForm.getOrderType() == 1) {//运输
                        allRoleCode.add("OPERATOR_CAR_OWNER");
                    } else if (bookingForm.getOrderType() == 2) {//快递
                        allRoleCode.add("OPERATOR_COURIER");
                        allRoleCode.add("OPERATOR_MSTATION");
                    } else {
                        allRoleCode.add("OPERATOR_CAR_OWNER");
                    }
                    pushBean.setAllRoleCode(allRoleCode);
                    pushBean.setTsClientPushed(new Date());
                    //微信下单默认都是非实名
                    pushBean.setIsRealName(false);
                    pushBeanList.add(pushBean);
                    logger.info("broadcastOrder sendBroadCastOrderMessage ={}", JSONArray.toJSONString(pushBeanList));
                    gpsOrderService.sendBroadCastOrderMessage(pushBeanList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
            List<String> allBusNo = new ArrayList<>();
            allBusNo.add(bookingForm.getBusiBookNo());
            giOrderTraceResynced.setAllBusiNo(allBusNo);
            giOrderTraceResynced.setProductType(bookingForm.getTransportType());
            if (errOrder != null && errOrder) {
                giOrderTraceResynced.setAction(MobileStationDefine.Action_ErrorPosition);
            } else {
                giOrderTraceResynced.setAction(MobileStationDefine.Action_PopOrdered);
            }
            giOrderTraceResynced.setIsToAccept(true);
            giOrderTraceResynced.setTsAct(new Date());
            giOrderTraceResynced.setUserCode(MobileStationDefine.WEIXINORDERCODE);
            giOrderTraceResynced.setUserName(bookingForm.getShipCustlinkMail());
            giOrderTraceResynced.setUserTel(bookingForm.getShipCustlinkTel());
            logger.info("notifyGps giOrderTraceResynced ={}", JSONArray.toJSONString(giOrderTraceResynced));
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
        }

    }

    @Override
    public Integer cancleWechatOrder(Integer orderId) {

        BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(orderId);
        if (null != bookingForm) {
            bookingFormDao.deleteByPrimaryKey(orderId);

            //通知中层
            GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
            List<String> allBusNo = new ArrayList<>();
            allBusNo.add(bookingForm.getBusiBookNo());
            giOrderTraceResynced.setAllBusiNo(allBusNo);
            giOrderTraceResynced.setProductType(bookingForm.getTransportType());
            giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
            giOrderTraceResynced.setTsAct(new Date());
            giOrderTraceResynced.setUserCode(MobileStationDefine.WEIXINORDERCODE);
            giOrderTraceResynced.setUserName(bookingForm.getShipCustlinkMail());
            giOrderTraceResynced.setUserTel(bookingForm.getShipCustlinkTel());
            logger.info("cancleOrder sendGpsLogMessage ={}", JSONArray.toJSONString(giOrderTraceResynced));
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);

            //通知gps取消
            List<GiBizOrder> pushBeanList = new ArrayList<>();
            GiBizOrder pushBean = new GiBizOrder();
            pushBean.setAction(MobileStationDefine.GPS_ACTION_DELETE);
            pushBean.setAppTag(CustomerDefine.IM_MS_DEFINE);
            pushBean.setDocNo(bookingForm.getBusiBookNo());
            pushBean.setDocId(bookingForm.getId());
            pushBean.setDocFrom(CustomerDefine.IM_BS_DEFINE);
            pushBean.setProductType(bookingForm.getTransportType());
            pushBean.setBizType(bookingForm.getOrderType());
            pushBeanList.add(pushBean);
            logger.info("cancleOrder sendBroadCastOrderMessage ={}", JSONArray.toJSONString(pushBeanList));
            gpsOrderService.sendBroadCastOrderMessage(pushBeanList);
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public MsDubboResBean signOrder(SignOrderReq signOrderReq) {
        logger.info("signOrder req ={}", JSONArray.toJSONString(signOrderReq));
        MsDubboResBean res = new MsDubboResBean();
        if (null == signOrderReq || null == signOrderReq.getId() || null == signOrderReq.getTelephone()) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("参数为空！");
            return res;
        }
        BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(signOrderReq.getId());
        if (null == bookingForm) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("订单不存在！");
            return res;
        }
        if (bookingForm.getBusiCtrl() == 6) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("订单已签收！");
            return res;
        }
        if (bookingForm.getBusiCtrl() != 5) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("订单未被确认送达！");
            return res;
        }
        if (!StringUtil.isEmpty(bookingForm.getCneeCustLinkTel()) && !bookingForm.getCneeCustLinkTel().equals(signOrderReq.getTelephone())) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("订单不是您的，无法签收！");
            return res;
        }

        bookingForm.setBusiCtrl(6);
        int result = bookingFormDao.updateByPrimaryKey(bookingForm);
        if (result > 0) {
            ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
            serviceAuthBean.setAuthPwd(SystemDefine.CUSTOMER_AUTH_PWD);
            serviceAuthBean.setAuthUser(SystemDefine.CUSTOMER_AUTH_USER);
            serviceAuthBean.setSysFlag(SystemDefine.TRANSPORT_SYS_FLAG);
            ServiceResult serviceResult = accountService.queryAccountByTelephone(serviceAuthBean, signOrderReq.getTelephone());
            if (serviceResult.isResult()) {
                List<AccountInfo> accountInfos = (List<AccountInfo>) serviceResult.getData();
                if (null != accountInfos && accountInfos.size() > 0) {
                    ComWaybillTrace tmp = new ComWaybillTrace();
                    tmp.setExecCode(WayBillStatusDefine.FINISH_ORDER.getIntValue());
                    tmp.setRemark(WayBillStatusDefine.MS_ARRIVE_POD.getName());
                    tmp.setRealName(accountInfos.get(0).getRealName());
                    tmp.setAcctUsername(accountInfos.get(0).getAcctUsername());
                    tmp.setBusiBookNo(bookingForm.getBusiBookNo());
                    tmp.setGrade(0);
                    tmp.setStaDate(new Date());
                    tmp.setRoleId(1);
                    comWaybillTraceDao.insert(tmp);
                }
            }

        } else {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("更新订单状态失败！");
        }


        logger.debug("signOrder res={}", JSON.toJSONString(res));
        return res;
    }
}
