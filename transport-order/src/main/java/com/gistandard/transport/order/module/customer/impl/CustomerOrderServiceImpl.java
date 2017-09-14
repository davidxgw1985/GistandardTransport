package com.gistandard.transport.order.module.customer.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gistandard.dubbo.CouponDubboService;
import com.gistandard.dubbo.bean.DataResultBean;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.genno.GenerateOrderNumberService;
import com.gistandard.transport.app.dubbo.wechat.bean.AddressInfo;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.gps.service.GpsService;
import com.gistandard.transport.order.module.customer.CustomerOrderService;
import com.gistandard.transport.order.module.customer.bean.*;
import com.gistandard.transport.order.module.customer.dao.CustomerBookingFormDao;
import com.gistandard.transport.order.module.mobilestation.bean.MobileAssignBean;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileStationOrderService;
import com.gistandard.transport.order.module.mobilestation.service.impl.BlockingQueueFactory;
import com.gistandard.transport.order.webservice.client.FreightCommonService.*;
import com.gistandard.transport.order.webservice.client.merchant.order.BaseRequestResult;
import com.gistandard.transport.order.webservice.client.merchant.order.Exception_Exception;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.RecordMobileStationOrderRequest;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.RevUserBean;
import com.gistandard.transport.order.webservice.server.mobilestation.constant.RecordOrderType;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteInfoReq;
import com.gistandard.transport.quote.system.common.define.ItemCategory;
import com.gistandard.transport.quote.system.common.define.QuoteTypeDefine;
import com.gistandard.transport.quote.system.database.services.ComQuoteService;
import com.gistandard.transport.quote.system.database.services.ExpressService;
import com.gistandard.transport.system.common.address.dao.CustomerMobileAddressInfoDao;
import com.gistandard.transport.system.common.bean.AddressQueryRes;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.system.common.define.OrderDefine;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.system.webservice.client.calcWebService.CalcWebService;
import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuote;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormDetailModel;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormInModel;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormOutModel;
import com.gistandard.transport.system.webservice.client.payinfo.QueryCalcManagerWebService;
import com.gistandard.transport.tools.util.Base64Util;
import com.gistandard.transport.tools.util.HeadAuthentication;
import com.gistandard.transport.tools.util.HttpClientUtil;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.common.bean.AccountInfo;
import com.valueplus.psc.dubbo.service.common.bean.ServiceAuthBean;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import com.valueplus.psc.dubbo.service.login.AccountService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yjf on 2016/9/30.
 * 客户下单管理
 */
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private MobileAddressInfoDaoEx mobileAddressInfoDaoEx;
    @Autowired
    private CustomerMobileAddressInfoDao customerMobileAddressInfoDao;
    @Autowired
    private MobileAddressInfoDao mobileAddressInfoDao;
    @Autowired
    private BookingFormDao bookingFormDao;
    @Autowired
    private BookingFormErrDao bookingFormErrDao;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private CustomerBookingFormDao customerBookingFormDao;
    @Autowired
    private BillingFormSalmDaoEx billingFormSalmDaoEx;
    @Autowired
    private BillingFormSalmDao billingFormSalmDao;
    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;
    @Autowired
    private ComCurrencyService comCurrencyService;
    @Autowired
    private ComWaybillTraceDao comWaybillTraceDao;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private ComAccountDaoEx comAccountDaoEx;
    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;
    @Autowired
    private GpsOrderService gpsOrderService;
    @Autowired
    private MobileStationOrderService mobileStationOrderService;
    @Autowired
    private ComProvinceService comProvinceService;
    @Autowired
    private ComCityService comCityService;
    @Autowired
    private ComCountyService comCountyService;
    @Autowired(required = true)
    private IZrxCommonService iZrxCommonService;
    @Autowired
    private ComGoodsTypeDaoEx comGoodsTypeDaoEx;
    @Autowired
    private ComGoodsTypeService comGoodsTypeService;
    @Autowired
    private ComUnitService comUnitService;
    @Autowired
    private MobileMoudleRelDaoEx mobileMoudleRelDaoEx;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileGoodsDtlDao mobileGoodsDtlDao;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private ComAccountService comAccountService;
    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;
    @Autowired
    private ComQuoteService comQuoteService;
    @Autowired
    private ComAccountRoleRelDaoEx comAccountRoleRelDaoEx;
    @Autowired
    private GenerateOrderNumberService generateOrderNumberService;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;
    @Autowired
    private GpsLogService gpsLogService;
    @Autowired
    private GpsService gpsService;

    @Autowired
    private MobileValueAddService mobileValueAddService;

    @Autowired
    private QueryCalcManagerWebService queryCalcManagerWebService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private MobileFleetBiddingDaoEx mobileFleetBiddingDaoEx;

    @Autowired
    private MobileValueAddDaoEx mobileValueAddDaoEx;

    @Autowired
    private MobileValueAddRelDao mobileValueAddRelDao;

    @Autowired
    private ComVehicleInfoDao comVehicleInfoDao;

    @Autowired
    private ComVehicleTypeDao comVehicleTypeDao;

    @Autowired
    private ComDriverInfoDaoEx comDriverInfoDaoEx;

    @Autowired
    private CouponDubboService couponDubboService;

    @Autowired
    private CalcWebService calcWebService;

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

    //编码格式。发送编码格式统一用GBK
    private static String ENCODING = "GBK";

    private static final Logger logger = LoggerFactory.getLogger(CustomerOrderServiceImpl.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PlaceAnOrderRes placeAnOrder(PlaceAnOrderReq req) throws MobileStationBizException {
        PlaceAnOrderRes placeAnOrderRes = new PlaceAnOrderRes();
        String stationCode = req.getStationCode();
        //check param.
        checkParam(req);
        BookingForm bookingForm = new BookingForm();
        //保存地址信息
        saveSenderAddress(req, bookingForm);
        saveReceiverAddress(req, bookingForm);
        if (null != stationCode) {
            //服务提供者(站点)
            setStation(stationCode, bookingForm);
        }
        //报价单
        if (null != req.getQuoteNo()) {
            bookingForm.setDocno(req.getQuoteNo());
        }
        if (req.getRoleId() != null) {
            bookingForm.setBookingCtrl(req.getRoleId());
        }
        if (req.getQuotedType() == 6) {//面议生产一口价报价单号
            QuoteInfoReq quoteInfoReq = new QuoteInfoReq();
            quoteInfoReq.setAcctUsername(req.getAcctUsername());
            quoteInfoReq.setItemCategory(ItemCategory.YKJ.getCode());
            ComQuote comQuote = new ComQuote();
            comQuote.setCreateAccount(req.getAcctUsername());
            comQuote.setCurrencyCode(req.getPredictCurr());
            comQuote.setUnitPrice(req.getPredictValue());
            QuoteBean quoteBean = new QuoteBean();
            quoteBean.setComQuote(comQuote);
            ResultBean baseResBean = comQuoteService.saveMobileYkjQuote(quoteBean);
            if (baseResBean != null && !StringUtil.isEmpty(baseResBean.getData())) {
                ComQuote tmp = (ComQuote) baseResBean.getData();
                if (tmp != null) {
                    bookingForm.setDocno(tmp.getQuoteNo() + "");
                }
            } else {
                throw new MobileStationBizException("生成面议报价失败！");
            }
        }

        //收件人账号
        if (null != req.getReceiverAcctUsername()) {
            bookingForm.setReceiverUser(req.getReceiverAcctUsername());
        }

        ComAccount comAccount = req.getAppLoginInfo().getComAccount();
        if (null != comAccount) {
            bookingForm.setCreateUserId(comAccount.getId());
            req.setAccountId(comAccount.getId());
        }
        if (null != req.getAcctUsername()) {
            bookingForm.setCreateUser(req.getAcctUsername());
        }

        if (null != req.getNarrate()) {
            bookingForm.setNarrate(req.getNarrate());
        }
        if (null != req.getOrderForm()) {
            bookingForm.setOrderForm(req.getOrderForm());
        } else {
            bookingForm.setOrderForm(CustomerDefine.IM_ORDERFORM_APP);
        }
        bookingForm.setCarriageDeiv("2");//送货上门
        //保存主表信息
        bookingForm.setBookingDate(new Date());//申请订单日期
        bookingForm.setOrderType(req.getTransType()); // 1运输  2快递
        //价格和增值服务
        bookingForm.setNeedInsure(req.getInsured());
        if (null != req.getGoodsValue()) {
            bookingForm.setGoodsValue(BigDecimal.valueOf(req.getGoodsValue()));
        }
        if (null != req.getPremiumValue()) {
            bookingForm.setPremiumValue(req.getPremiumValue());
        }
        if (req.getPaymentType() == 1) {
            if (req.getReceiverAcctUsername() == null)
                throw new MobileStationBizException("收件人不是平台用户");
            if (null != req.getPredictValue() && req.getPredictValue().compareTo(BigDecimal.valueOf(Long.valueOf(customerOrderPredictValue))) > 0)
                throw new MobileStationBizException("运费金额要小于等于" + customerOrderPredictValue + "元");
        }
        bookingForm.setPayType(req.getPaymentType());//1-到付 2-在线支付 3-现金支付
        if (req.getPaymentType() == MobileStationDefine.PAYTYPE_COLLECT) {
            //到付设置付款人为收件人的平台帐号o2id
            bookingForm.setPayUser(req.getReceiverAcctUsername());
            if (StringUtils.isNotEmpty(req.getReceiverAcctUsername())) {
                ComAccount receiveAccount = comAccountDaoEx.queryByAccount(req.getReceiverAcctUsername());
                if (receiveAccount != null) {
                    bookingForm.setPayUserRealName(receiveAccount.getRealName());
                    bookingForm.setPayUserTelephone(receiveAccount.getTelephone());
                }
            }
        } else if (req.getPaymentType() == MobileStationDefine.PAYTYPE_GENERALACCT) {
            //平台支付，付款人设置为下单人
            if (StringUtil.isEmpty(req.getCompanyAccountId())) {
                bookingForm.setPayUser(req.getAcctUsername());
                bookingForm.setPayUserRealName(req.getAppLoginInfo().getComAccount().getRealName());
                bookingForm.setPayUserTelephone(req.getAppLoginInfo().getComAccount().getTelephone());
            } else {
                ComAccount companyAccount = comAccountDao.selectByPrimaryKey(req.getCompanyAccountId());
                bookingForm.setPayUser(companyAccount.getAcctUsername());
                bookingForm.setPayUserRealName(companyAccount.getRealName());
                bookingForm.setPayUserTelephone(companyAccount.getTelephone());
            }
        } else if (req.getPaymentType() == MobileStationDefine.PAYTYPE_CASH) {
            //现金支付，在快递员收件时设置为快递员
            bookingForm.setPayUser(req.getAcctUsername());
            bookingForm.setPayUserRealName(req.getAppLoginInfo().getComAccount().getRealName());
            bookingForm.setPayUserTelephone(req.getAppLoginInfo().getComAccount().getTelephone());
        }
        if (null != req.getCourierId()) {
            bookingForm.setRevUserId(req.getCourierId());
            //客户下指派单，快递员强制接单
            if (req.getRoleId() != null && req.getRoleId() == 7) {
                placeAnOrderRes.setForceAccept(1);
            }
            if (null != req.getCourierAcctUsername()) {
                bookingForm.setRevUser(req.getCourierAcctUsername());
            }
            if (null != req.getCourierAcctUserRealName()) {
                bookingForm.setRevUserName(req.getCourierAcctUserRealName());
            } else {
                if (null != req.getCourierAcctUsername()) {
                    bookingForm.setRevUser(req.getCourierAcctUsername());
                    ComAccount account = comAccountService.queryAccountByAcctUsername(req.getCourierAcctUsername());
                    if (null != account) {
                        if (!StringUtil.isEmpty(account.getRealName())) {
                            bookingForm.setRevUserName(account.getRealName());
                        }

                    }
                }
            }

            if (req.getCompanyAccountId() != null) {
                bookingForm.setCreateCompanyId(req.getCompanyAccountId());
            }
        }
        if (null != req.getQuotedType()) {
            bookingForm.setQuotedType(req.getQuotedType());
        }
        bookingForm.setCarriAgerecei(req.getAccessMethod() + "");//取货方式：1-客户自送，2-上门接货
        if (!StringUtils.isEmpty(req.getAccessTime())) {
            bookingForm.setAccesstime(req.getAccessTime());
        }

        bookingForm.setBusiCtrl(OrderState.WAITRE_CEIVING.getValue());//待接单

        bookingForm.setCargoLoader("0");//整车：1, 零担：0, 自拼车：2

        String busiNo = generateOrderNumberService.getOrderNumberByCityId(NumberUtils.toInt(bookingForm.getCarriageReceiCity()));
        if (StringUtil.isEmpty(busiNo)) {
            throw new MobileStationBizException("获取订单号异常!");
        }
        bookingForm.setBusiBookNo(busiNo);
        bookingForm.setWaybillNo(busiNo);

        if (null != req.getPredictValue()) {
            bookingForm.setPredictValue(req.getPredictValue());
        }
        if (null != req.getGoodsPayment()) {
            bookingForm.setGoodsPayment(req.getGoodsPayment());
        }
        if (null != req.getGoodsPaymentCurr()) {
            bookingForm.setGoodsPaymentCurr(req.getGoodsPaymentCurr());
        }
        bookingForm.setIsJs(0);
        bookingForm.setIsDel(0);

        if (null != req.getSmsNoty()) {
            bookingForm.setSmsNoty(req.getSmsNoty());
        }

        if (!StringUtil.isEmpty(req.getPredictCurr())) {
            bookingForm.setPredictCurr(req.getPredictCurr());
        }
        bookingForm.setTransportType(req.getItemCode());
        bookingForm.setWhtGrosswht(new BigDecimal(0));
        bookingForm.setWhtVolcbm(new BigDecimal(0));
        bookingForm.setGoodsQty(new BigDecimal(0));
        bookingForm.setWhtVolwht(new BigDecimal(0));
        bookingForm.setMileage(req.getMileage());
        for (GoodsInfo goodsInfoReq : req.getGoodsInfos()) {
            if (goodsInfoReq.getWeight() != null) {
                //毛重
                bookingForm.setWhtGrosswht(bookingForm.getWhtGrosswht().add(BigDecimal.valueOf(goodsInfoReq.getWeight())));
                bookingForm.setWhtFeewht(bookingForm.getWhtGrosswht());
            }
            if (null != goodsInfoReq.getHeight() && null != goodsInfoReq.getLength() && null != goodsInfoReq.getWidth()) {
                //体积
                bookingForm.setWhtVolcbm(bookingForm.getWhtVolcbm().add(BigDecimal.valueOf(goodsInfoReq.getHeight() * goodsInfoReq.getLength() * goodsInfoReq.getWidth()).setScale(4, BigDecimal.ROUND_HALF_UP)));
                BigDecimal goodsFeewht = new BigDecimal(goodsInfoReq.getLength() * goodsInfoReq.getWidth() * goodsInfoReq.getHeight())
                        .divide(new BigDecimal(6000), 8, BigDecimal.ROUND_HALF_EVEN);
                bookingForm.setWhtVolwht(bookingForm.getWhtVolwht().add(goodsFeewht));
            }
            if (null != goodsInfoReq.getQty()) {
                bookingForm.setGoodsQty(bookingForm.getGoodsQty().add(goodsInfoReq.getQty()));
            }
            if (bookingForm.getWhtFeewht() != null && bookingForm.getWhtVolwht() != null) {
                if (bookingForm.getWhtVolwht().compareTo(bookingForm.getWhtFeewht()) > 0) {
                    bookingForm.setWhtFeewht(bookingForm.getWhtVolwht());
                }
            }
        }
        if (req.getGoodsInfos() != null && req.getGoodsInfos().size() > 0) {
            PlatFormInModel platFormInModel = new PlatFormInModel();
            platFormInModel.setItemCode(bookingForm.getTransportType());
            platFormInModel.setFromCountryCode("142");
            platFormInModel.setFromProvinceCode(bookingForm.getCarriageReceiProvince());
            platFormInModel.setFromCityCode(bookingForm.getCarriageReceiCity());
            platFormInModel.setFromCountyCode(bookingForm.getCarriageReceiCounty());
            platFormInModel.setArriveCountryCode("142");
            platFormInModel.setArriveProvinceCode(bookingForm.getCarriageDelivProvince());
            platFormInModel.setArriveCityCode(bookingForm.getCarriageDelivCity());
            platFormInModel.setArriveCountyCode(bookingForm.getCarriageDelivCounty());
            if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(req.getItemCode())) {
                platFormInModel.setQuoteType(MobileStationDefine.QUOTE_TYPE_SPECIAL_DELIVERY);
                platFormInModel.setMileage(req.getMileage());
            } else {
                platFormInModel.setQuoteType(MobileStationDefine.QUOTE_TYPE_EXPRESS);
            }

            if (bookingForm.getWhtGrosswht().compareTo(bookingForm.getWhtVolwht()) > 0) {
                platFormInModel.setWeight(bookingForm.getWhtGrosswht());
            } else {
                platFormInModel.setWeight(bookingForm.getWhtVolwht());
            }
            platFormInModel.setVolume(bookingForm.getWhtVolcbm().divide(new BigDecimal(1000000), 8, BigDecimal.ROUND_HALF_EVEN));
            platFormInModel.setCurrency("142");
            try {
                logger.debug("print call calc interface -> query price, busiNo : {},, param : {} ", busiNo, JSON.toJSONString(platFormInModel));
                PlatFormOutModel platFormOutModel = queryCalcManagerWebService.getQuote(platFormInModel);
                if (platFormOutModel.getStatus() != null && "0".equals(platFormOutModel.getStatus())) {
                    List<PlatFormDetailModel> quoteDetailList = platFormOutModel.getQuoteDetailList();
                    if (quoteDetailList != null && quoteDetailList.size() > 0) {
                        PlatFormDetailModel platFormDetailModel = quoteDetailList.get(0);
                        logger.info("place order goods info ,busiNo : {}, predictValue : {}, docNo : {}", bookingForm.getBusiBookNo(),
                                platFormDetailModel.getPredictValue(), platFormDetailModel.getQuoteNo());
                        bookingForm.setPredictValue(platFormDetailModel.getPredictValueAfterTax());
                        bookingForm.setDocno(platFormDetailModel.getQuoteNo());
                        bookingForm.setPredictCurr(platFormDetailModel.getCurrency());
                    }
                }
            } catch (com.gistandard.transport.system.webservice.client.payinfo.Exception_Exception e) {
                throw new MobileStationBizException(MobileStationRetCode.PRICE_QUERY_ERROR, e);
            }
        }
        bookingForm.setSenderFollow(CustomerDefine.UNFOLLOW);
        bookingForm.setReceiverFollow(CustomerDefine.UNFOLLOW);
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
        if (!StringUtil.isEmpty(bookingForm.getTeamComsixName())) {
            bookingForm.setStartLocusNa(bookingForm.getTeamComsixName());
        }
        if (!StringUtil.isEmpty(bookingForm.getTeamComsixNo())) {
            bookingForm.setStartLocus(bookingForm.getTeamComsixNo());
        }
        if (req.getItemCode() != null && !MobileStationDefine.PRODUCT_TYPE_TCZS.equals(req.getItemCode())) {
            try {
                BaseRequestResult baseRequestResult = mobileRecOrderWebService.checkRouteExists(bookingForm.getCarriageReceiLongitude().doubleValue(),
                        bookingForm.getCarriageReceiLatitude().doubleValue(), bookingForm.getCarriageDelivLongitude().doubleValue(), bookingForm.getCarriageDelivLatitude().doubleValue());
                if (baseRequestResult == null || baseRequestResult.getStatus() != 1) {
                    throw new MobileStationBizException(baseRequestResult.getMesasge());
                }
            } catch (Exception_Exception e) {
                e.printStackTrace();
                throw new MobileStationBizException(e.getMessage());
            }
        }

        if (req.getCompanyAccountId() != null && req.getCompanyAccountId() > 0) {
            bookingForm.setCreateCompanyId(req.getCompanyAccountId());
            ComAccount companyAccount = comAccountDao.selectByPrimaryKey(req.getCompanyAccountId());
            if (companyAccount != null) {
                bookingForm.setPayUser(companyAccount.getAcctUsername());
                bookingForm.setPayUserRealName(companyAccount.getRealName());
                bookingForm.setPayUserTelephone(companyAccount.getTelephone());
            }
        }
        //嗨付券参数
        DataResultBean dataResultBean = couponDubboService.checkHaveCoupons(req.getAcctUsername());
        logger.info("Hi voucher interface call dataResultBean-{}", JSON.toJSONString(dataResultBean));
        if (dataResultBean == null) {
            logger.error("Hi voucher interface call failed res is null");
            bookingForm.setCouponFlag(0);
        } else {
            if (dataResultBean.getSucceed()) {
                logger.info("Hi voucher interface call successful");
                if (dataResultBean.getRefObject() == Boolean.TRUE) {
                    logger.info("Hi voucher interface response result is true");
                    bookingForm.setCouponFlag(1);
                }
                if (dataResultBean.getRefObject() == Boolean.FALSE) {
                    logger.info("Hi voucher interface response result is false");
                    bookingForm.setCouponFlag(0);
                }
            } else {
                logger.error("Hi voucher interface call failed");
                bookingForm.setCouponFlag(0);
            }
        }
        bookingFormDao.insertSelective(bookingForm);
        //保存货物信息
        saveGoodsInfo(req.getGoodsInfos(), bookingForm);

        ComWaybillTrace tmp = new ComWaybillTrace();

        MobileBookingForm mobileBookingForm = doSubOrder(bookingForm);

        tmp.setAcctUsername(req.getAcctUsername());
        if (comAccount != null && comAccount.getRealName() != null) {
            tmp.setRealName(comAccount.getRealName());
        } else {
            tmp.setRealName(bookingForm.getShipCustlinkMan());
        }
        tmp.setBusiBookNo(bookingForm.getBusiBookNo());
        tmp.setGrade(0);
        tmp.setRemark(BusinessDefine.ORDER);
        tmp.setExecCode(WayBillStatusDefine.ORDER.getIntValue());
        tmp.setStaDate(new Date());
        tmp.setRoleId(1);
        comWaybillTraceDao.insert(tmp);
        placeAnOrderRes.setBusiBookNo(bookingForm.getBusiBookNo());
        placeAnOrderRes.setInsured(req.getInsured());
        placeAnOrderRes.setOrderId(bookingForm.getId());
        placeAnOrderRes.setProductType(bookingForm.getTransportType());
        if (mobileBookingForm != null) {
            placeAnOrderRes.setMobileBookFormId(mobileBookingForm.getId());
        }
        //指派给MS时才推送消息
        if (null != req.getCourierId()) {
            if (null != req.getCourierAcctUsername()) {
                MsgIMReq msgIMReq = new MsgIMReq();
                msgIMReq.setBusiBookNo(bookingForm.getBusiBookNo());
                msgIMReq.setRemindCode("PUSH_ORDER_000005");
                msgIMReq.setCreateUser(req.getCourierAcctUsername());
                msgIMReq.setMsgTo(2);
                msgIMReq.setOrderFrom(4);
                msgIMReq.setOperateFrom(1);
                Map mapObject = new HashMap();
                mapObject.put("busiBookNo", msgIMReq.getBusiBookNo());
                msgIMReq.setMapObject(mapObject);
                BlockingQueue<MsgIMReq> blockingQueue = BlockingQueueFactory.getQueue();
                try {
                    blockingQueue.offer(msgIMReq, 300, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
        logger.debug("placeAnOrderRes={}", JSONObject.toJSONString(placeAnOrderRes));
        return placeAnOrderRes;
    }

    /**
     * 客户下错误地址单
     *
     * @param req
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public PlaceAnOrderRes placeAnErrOrder(PlaceAnOrderReq req) throws MobileStationBizException {
        PlaceAnOrderRes placeAnOrderRes = new PlaceAnOrderRes();
        String stationCode = req.getStationCode();
        //check param.
        checkParam(req);
        BookingFormErr bookingFormErr = new BookingFormErr();
        //保存地址信息
        setErrSenderAddress(req, bookingFormErr);
        setErrReceiverAddress(req, bookingFormErr);
        if (null != stationCode) {
            //服务提供者(站点)
            setErrStation(stationCode, bookingFormErr);
        }
        //报价单
        if (null != req.getQuoteNo()) {
            bookingFormErr.setDocno(req.getQuoteNo());
        }
        if (req.getRoleId() != null) {
            bookingFormErr.setBookingCtrl(req.getRoleId());
        }
        //收件人账号
        if (null != req.getReceiverAcctUsername()) {
            bookingFormErr.setReceiverUser(req.getReceiverAcctUsername());
        }

        ComAccount comAccount = req.getAppLoginInfo().getComAccount();
        if (null != comAccount) {
            bookingFormErr.setCreateUserId(comAccount.getId());
            req.setAccountId(comAccount.getId());
        }
        if (null != req.getAcctUsername()) {
            bookingFormErr.setCreateUser(req.getAcctUsername());
        }

        if (null != req.getNarrate()) {
            bookingFormErr.setNarrate(req.getNarrate());
        }
        if (null != req.getOrderForm()) {
            bookingFormErr.setOrderForm(req.getOrderForm());
        } else {
            bookingFormErr.setOrderForm(CustomerDefine.IM_ORDERFORM_APP);
        }
        bookingFormErr.setCarriageDeiv("2");//送货上门
        //保存主表信息
        bookingFormErr.setBookingDate(new Date());//申请订单日期
        bookingFormErr.setOrderType(req.getTransType()); // 1运输  2快递
        //价格和增值服务
        bookingFormErr.setNeedInsure(req.getInsured());
        if (null != req.getGoodsValue()) {
            bookingFormErr.setGoodsValue(BigDecimal.valueOf(req.getGoodsValue()));
        }
        if (null != req.getPremiumValue()) {
            bookingFormErr.setPremiumValue(req.getPremiumValue());
        }
        if (req.getPaymentType() == 1) {
            if (req.getReceiverAcctUsername() == null)
                throw new MobileStationBizException("收件人不是平台用户");
            if (null != req.getPredictValue() && req.getPredictValue().compareTo(BigDecimal.valueOf(Long.valueOf(customerOrderPredictValue))) > 0)
                throw new MobileStationBizException("运费金额要小于等于" + customerOrderPredictValue + "元");
        }
        bookingFormErr.setPayType(req.getPaymentType());//1-到付 2-在线支付 3-现金支付
        if (req.getPaymentType() == MobileStationDefine.PAYTYPE_COLLECT) {
            //到付设置付款人为收件人的平台帐号o2id
            bookingFormErr.setPayUser(req.getReceiverAcctUsername());
            if (StringUtils.isNotEmpty(req.getReceiverAcctUsername())) {
                ComAccount receiveAccount = comAccountDaoEx.queryByAccount(req.getReceiverAcctUsername());
                if (receiveAccount != null) {
                    bookingFormErr.setPayUserRealName(receiveAccount.getRealName());
                    bookingFormErr.setPayUserTelephone(receiveAccount.getTelephone());
                }
            }
        } else if (req.getPaymentType() == MobileStationDefine.PAYTYPE_GENERALACCT) {
            //平台支付，付款人设置为下单人
            if (StringUtil.isEmpty(req.getCompanyAccountId())) {
                bookingFormErr.setPayUser(req.getAcctUsername());
                bookingFormErr.setPayUserRealName(req.getAppLoginInfo().getComAccount().getRealName());
                bookingFormErr.setPayUserTelephone(req.getAppLoginInfo().getComAccount().getTelephone());
            } else {
                ComAccount companyAccount = comAccountDao.selectByPrimaryKey(req.getCompanyAccountId());
                bookingFormErr.setPayUser(companyAccount.getAcctUsername());
                bookingFormErr.setPayUserRealName(companyAccount.getRealName());
                bookingFormErr.setPayUserTelephone(companyAccount.getTelephone());
            }
        } else if (req.getPaymentType() == MobileStationDefine.PAYTYPE_CASH) {
            //现金支付，在快递员收件时设置为快递员
            bookingFormErr.setPayUser(req.getAcctUsername());
            bookingFormErr.setPayUserRealName(req.getAppLoginInfo().getComAccount().getRealName());
            bookingFormErr.setPayUserTelephone(req.getAppLoginInfo().getComAccount().getTelephone());
        }
        if (null != req.getCourierId()) {
            bookingFormErr.setRevUserId(req.getCourierId());
            if (null != req.getCourierAcctUsername()) {
                bookingFormErr.setRevUser(req.getCourierAcctUsername());
            }
            if (null != req.getCourierAcctUserRealName()) {
                bookingFormErr.setRevUserName(req.getCourierAcctUserRealName());
            } else {
                if (null != req.getCourierAcctUsername()) {
                    bookingFormErr.setRevUser(req.getCourierAcctUsername());
                    ComAccount account = comAccountService.queryAccountByAcctUsername(req.getCourierAcctUsername());
                    if (null != account) {
                        if (!StringUtil.isEmpty(account.getRealName())) {
                            bookingFormErr.setRevUserName(account.getRealName());
                        }

                    }
                }
            }
        }
        if (null != req.getQuotedType()) {
            bookingFormErr.setQuotedType(req.getQuotedType());
        }
        bookingFormErr.setCarriAgerecei(req.getAccessMethod() + "");//取货方式：1-客户自送，2-上门接货
        if (!StringUtils.isEmpty(req.getAccessTime())) {
            bookingFormErr.setAccesstime(req.getAccessTime());
        }

        bookingFormErr.setBusiCtrl(OrderState.WAITRE_CEIVING.getValue());//待接单

        bookingFormErr.setCargoLoader("0");//整车：1, 零担：0, 自拼车：2

        String busiNo = generateOrderNumberService.getOrderNumberByCityId(NumberUtils.toInt(bookingFormErr.getCarriageReceiCity()));
        if (StringUtil.isEmpty(busiNo)) {
            throw new MobileStationBizException("获取订单号异常!");
        }
        bookingFormErr.setBusiBookNo(busiNo);
        bookingFormErr.setWaybillNo(busiNo);

        if (null != req.getPredictValue()) {
            bookingFormErr.setPredictValue(req.getPredictValue());
        }
        if (null != req.getGoodsPayment()) {
            bookingFormErr.setGoodsPayment(req.getGoodsPayment());
        }
        if (null != req.getGoodsPaymentCurr()) {
            bookingFormErr.setGoodsPaymentCurr(req.getGoodsPaymentCurr());
        }
        bookingFormErr.setIsJs(0);
        bookingFormErr.setIsDel(0);

        if (null != req.getSmsNoty()) {
            bookingFormErr.setSmsNoty(req.getSmsNoty());
        }

        if (!StringUtil.isEmpty(req.getPredictCurr())) {
            bookingFormErr.setPredictCurr(req.getPredictCurr());
        }
        bookingFormErr.setTransportType(req.getItemCode());
        bookingFormErr.setWhtGrosswht(new BigDecimal(0));
        bookingFormErr.setWhtVolcbm(new BigDecimal(0));
        bookingFormErr.setGoodsQty(new BigDecimal(0));
        bookingFormErr.setWhtVolwht(new BigDecimal(0));
        bookingFormErr.setMileage(req.getMileage());
        for (GoodsInfo goodsInfoReq : req.getGoodsInfos()) {
            if (goodsInfoReq.getWeight() != null) {
                //毛重
                bookingFormErr.setWhtGrosswht(bookingFormErr.getWhtGrosswht().add(BigDecimal.valueOf(goodsInfoReq.getWeight())));
                bookingFormErr.setWhtFeewht(bookingFormErr.getWhtGrosswht());
            }
            if (null != goodsInfoReq.getHeight() && null != goodsInfoReq.getLength() && null != goodsInfoReq.getWidth()) {
                //体积
                bookingFormErr.setWhtVolcbm(bookingFormErr.getWhtVolcbm().add(BigDecimal.valueOf(goodsInfoReq.getHeight() * goodsInfoReq.getLength() * goodsInfoReq.getWidth()).setScale(4, BigDecimal.ROUND_HALF_UP)));
                BigDecimal goodsFeewht = new BigDecimal(goodsInfoReq.getLength() * goodsInfoReq.getWidth() * goodsInfoReq.getHeight())
                        .divide(new BigDecimal(6000), 8, BigDecimal.ROUND_HALF_EVEN);
                bookingFormErr.setWhtVolwht(bookingFormErr.getWhtVolwht().add(goodsFeewht));
            }
            if (null != goodsInfoReq.getQty()) {
                bookingFormErr.setGoodsQty(bookingFormErr.getGoodsQty().add(goodsInfoReq.getQty()));
            }
            if (bookingFormErr.getWhtFeewht() != null && bookingFormErr.getWhtVolwht() != null) {
                if (bookingFormErr.getWhtVolwht().compareTo(bookingFormErr.getWhtFeewht()) > 0) {
                    bookingFormErr.setWhtFeewht(bookingFormErr.getWhtVolwht());
                }
            }
        }
        bookingFormErr.setSenderFollow(CustomerDefine.UNFOLLOW);
        bookingFormErr.setReceiverFollow(CustomerDefine.UNFOLLOW);
        if (!StringUtil.isEmpty(bookingFormErr.getTeamComsixName())) {
            bookingFormErr.setStartLocusNa(bookingFormErr.getTeamComsixName());
        }
        if (!StringUtil.isEmpty(bookingFormErr.getTeamComsixNo())) {
            bookingFormErr.setStartLocus(bookingFormErr.getTeamComsixNo());
        }

        if (req.getCompanyAccountId() != null && req.getCompanyAccountId() > 0) {
            bookingFormErr.setCreateCompanyId(req.getCompanyAccountId());
            ComAccount companyAccount = comAccountDao.selectByPrimaryKey(req.getCompanyAccountId());
            if (companyAccount != null) {
                bookingFormErr.setPayUser(companyAccount.getAcctUsername());
                bookingFormErr.setPayUserRealName(companyAccount.getRealName());
                bookingFormErr.setPayUserTelephone(companyAccount.getTelephone());
            }
        }
        //嗨付券参数
        DataResultBean dataResultBean = couponDubboService.checkHaveCoupons(req.getAcctUsername());
        logger.info("Hi voucher interface call dataResultBean-{}", JSON.toJSONString(dataResultBean));
        if (dataResultBean == null) {
            logger.error("Hi voucher interface call failed res is null");
            bookingFormErr.setCouponFlag(0);
        } else {
            if (dataResultBean.getSucceed()) {
                logger.info("Hi voucher interface call successful");
                if (Boolean.TRUE.equals(dataResultBean.getRefObject())) {
                    logger.info("Hi voucher interface response result is true");
                    bookingFormErr.setCouponFlag(1);
                }
                if (Boolean.FALSE.equals(dataResultBean.getRefObject())) {
                    logger.info("Hi voucher interface response result is false");
                    bookingFormErr.setCouponFlag(0);
                }
            } else {
                logger.error("Hi voucher interface call failed");
                bookingFormErr.setCouponFlag(0);
            }
        }
        BookingForm bookingForm = new BookingForm();
        try {
            BeanUtils.copyProperties(bookingForm, bookingFormErr);
            bookingFormDao.insertSelective(bookingForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bookingFormErrDao.insert(bookingFormErr);
        ComWaybillTrace tmp = new ComWaybillTrace();
        tmp.setAcctUsername(req.getAcctUsername());
        if (comAccount != null && comAccount.getRealName() != null) {
            tmp.setRealName(comAccount.getRealName());
        } else {
            tmp.setRealName(bookingForm.getShipCustlinkMan());
        }
        tmp.setBusiBookNo(bookingForm.getBusiBookNo());
        tmp.setGrade(0);
        tmp.setRemark(BusinessDefine.ORDER);
        tmp.setExecCode(WayBillStatusDefine.ORDER.getIntValue());
        tmp.setStaDate(new Date());
        tmp.setRoleId(1);
        comWaybillTraceDao.insert(tmp);
        //保存货物信息
        saveGoodsInfo(req.getGoodsInfos(), bookingForm);

        placeAnOrderRes.setBusiBookNo(bookingForm.getBusiBookNo());
        placeAnOrderRes.setInsured(req.getInsured());
        placeAnOrderRes.setOrderId(bookingForm.getId());
        placeAnOrderRes.setProductType(bookingForm.getTransportType());
        logger.debug("placeAnErrOrderRes={}", JSONObject.toJSONString(placeAnOrderRes));
        return placeAnOrderRes;
    }

    /**
     * 将订单与货物信息备份到mobilestation
     *
     * @param mobileBookingForm
     * @param recordList
     * @return
     * @throws MobileStationBizException
     */
    private AppBaseResult receiveMobileStationOrder(MobileBookingForm mobileBookingForm, List<MobileGoodsDtl> recordList)
            throws MobileStationBizException {
        /**
         * 组装请求mobilestation下单接口参数
         */
        RecordMobileStationOrderRequest recordMobileStationOrderRequest = convertRecordMobileStationOrderRequest(mobileBookingForm);
        recordMobileStationOrderRequest.setRevUser(mobileBookingForm.getRevUser());
        recordMobileStationOrderRequest.setBusiBookNo(mobileBookingForm.getBusiBookNo());
        recordMobileStationOrderRequest.setRecordOrderType(RecordOrderType.ORDER_O.getValue());

        //组装接收人信息
        ArrayList<RevUserBean> revUserList = new ArrayList<RevUserBean>();
        RevUserBean revUserBean = new RevUserBean();
        revUserBean.setRevUserId(mobileBookingForm.getRevUserId());
        revUserBean.setRevUser(mobileBookingForm.getRevUser());
        revUserList.add(revUserBean);

        //设置站点信息
        recordMobileStationOrderRequest.setStartLocus(MobileStationDefine.POP);
        recordMobileStationOrderRequest.setDestnLocus(MobileStationDefine.POD);

        recordMobileStationOrderRequest.setRevUserList(revUserList);
        //赋值订单货物信息
        recordMobileStationOrderRequest.setMobileGoodDtlList(recordList);
        recordMobileStationOrderRequest.setRecordBrocast(true);
        //调用mobilestation接单接口：备份订单以及货物信息-->mobilestation
        AppBaseResult recordMobileStationOrder = mobileStationOrderService.receiveMobileStationOrder(recordMobileStationOrderRequest);

        return recordMobileStationOrder;
    }

    /**
     * 将订单与货物信息备份到mobilestation
     *
     * @param mobileBookingForm
     * @param recordList
     * @return
     * @throws MobileStationBizException
     */
    private AppBaseResult recordMoblieStationOrder(MobileBookingForm mobileBookingForm, List<MobileGoodsDtl> recordList)
            throws MobileStationBizException, MobileStationBizException {
        /**
         * 组装请求mobilestation下单接口参数
         */
        RecordMobileStationOrderRequest recordMobileStationOrderRequest = convertRecordMobileStationOrderRequest(mobileBookingForm);
        recordMobileStationOrderRequest.setRecordOrderType(RecordOrderType.ORDER_O.getValue());

        //组装接收人信息
        ArrayList<RevUserBean> revUserList = new ArrayList<RevUserBean>();
        RevUserBean revUserBean = new RevUserBean();
        revUserBean.setRevUserId(mobileBookingForm.getRevUserId());
        revUserBean.setRevUser(mobileBookingForm.getRevUser());
        revUserList.add(revUserBean);

        //设置站点信息
        recordMobileStationOrderRequest.setStartLocus(MobileStationDefine.POP);
        recordMobileStationOrderRequest.setDestnLocus(MobileStationDefine.POD);

        recordMobileStationOrderRequest.setRevUserList(revUserList);
        //赋值订单货物信息
        recordMobileStationOrderRequest.setMobileGoodDtlList(recordList);
        //调用mobilestation下单接口：备份订单以及货物信息-->mobilestation
        AppBaseResult recordMobileStationOrder = mobileStationOrderService.recordMobileStationOrder(recordMobileStationOrderRequest);

        return recordMobileStationOrder;
    }

    /**
     * 组装booking_form数据
     *
     * @param mobileBookingForm
     * @return
     * @throws MobileStationBizException
     */
    private RecordMobileStationOrderRequest convertRecordMobileStationOrderRequest(MobileBookingForm mobileBookingForm)
            throws MobileStationBizException {

        RecordMobileStationOrderRequest recordMobiStaOrderRequest = new RecordMobileStationOrderRequest();
        try {
            PropertyUtils.copyProperties(recordMobiStaOrderRequest, mobileBookingForm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MobileStationBizException("参数格式异常");
        }
        return recordMobiStaOrderRequest;
    }

    private void broadcastOrder(BookingForm bookingForm) {
        try {
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
            pushBean.setTotalPrice(bookingForm.getPredictValue() == null ? BigDecimal.valueOf(0) : bookingForm.getPredictValue());
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
            List<String> allModuleCode = new ArrayList<>();
            allModuleCode.add(bookingForm.getTransportType());
            pushBean.setAllModuleCode(allModuleCode);
            List<String> allRoleCode = new ArrayList<>();
            if (bookingForm.getOrderType() == 1) {//运输
                allRoleCode.add("OPERATOR_CAR_OWNER");
            } else if (bookingForm.getOrderType() == 2) {//快递
                if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(bookingForm.getTransportType())) {
                    allRoleCode.add("OPERATOR_COURIER");
                    allRoleCode.add("OPERATOR_CAR_OWNER");
                } else {
                    allRoleCode.add("OPERATOR_COURIER");
                    allRoleCode.add("OPERATOR_MSTATION");
                }
            } else {
                allRoleCode.add("OPERATOR_CAR_OWNER");
            }
            pushBean.setAllRoleCode(allRoleCode);
            pushBean.setTsClientPushed(new Date());
            ComAccount account = comAccountDao.selectByPrimaryKey(bookingForm.getCreateUserId());
            if (account != null && !StringUtil.isEmpty(account.getRealName())) {
                pushBean.setIsRealName(true);
            } else {
                pushBean.setIsRealName(false);
            }
            if (bookingForm.getCouponFlag() != null && bookingForm.getCouponFlag() == 1) {
                pushBean.setIsHifu(true);
            } else {
                pushBean.setIsHifu(false);
            }

            if ("1".equals(bookingForm.getCargoLoader()) && "2".equals(bookingForm.getOrderForm())) {
                pushBean.setTypeCreated(1);
            } else {
                pushBean.setTypeCreated(0);
            }
            pushBeanList.add(pushBean);
            logger.info("broadcastOrder sendBroadCastOrderMessage ={}", JSONArray.toJSONString(pushBeanList));
            gpsOrderService.sendBroadCastOrderMessage(pushBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 参数校验
     *
     * @param req
     * @throws MobileStationBizException
     */
    private void checkParam(PlaceAnOrderReq req) throws MobileStationBizException {
        if (!req.getBroadcast()) {
            Integer orderRecType = req.getOrderRecType();
            if (null != orderRecType && orderRecType == 1) {//站点
                if (StringUtils.isBlank(req.getStationCode())) {
                    throw new MobileStationBizException("客户下单站点代码入参错误");
                }
            } else {//快递员
                if (StringUtil.isEmpty(req.getCourierId())) {
                    throw new MobileStationBizException("客户下单快递员/司机代码入参错误");
                }
                if (StringUtil.isEmpty(req.getCourierAcctUsername())) {
                    throw new MobileStationBizException("客户下单快递员/司机账户入参错误");
                }
            }
            if (StringUtil.isEmpty(req.getAcctUsername())) {
                throw new MobileStationBizException("帐户名称为空");
            }

            if (StringUtil.isEmpty(req.getAccountId())) {
                throw new MobileStationBizException("帐户ID为空");
            }
        } else {
            if (null == req.getQuotedType()) {
                throw new MobileStationBizException("您选择的区域内，暂未提供同城快递服务，敬请期待");
            }
        }
        if (StringUtils.isEmpty(req.getQuoteNo()) && req.getQuotedType() != null && req.getQuotedType() != 6) {
            throw new MobileStationBizException("您选择的区域内，暂未提供同城快递服务，敬请期待");
        }

        if (req.getQuotedType() != 6) {//非面议
            if (null == req.getComQuoteId()) {
                throw new MobileStationBizException("报价单ID为空");
            }
            if (StringUtils.isBlank(req.getQuoteNo())) {
                throw new MobileStationBizException("客户下单报价单为空");
            }

            ComQuote comQuote = comQuoteService.getQuoteByQuoteNo(req.getQuoteNo());
            if (comQuote == null) {
                throw new MobileStationBizException("报价单不存在！");
            }

        } else {
            if (null == req.getPredictValue()) {//面议
                throw new MobileStationBizException("面议价格为空！");
            }
        }

        Integer transType = req.getTransType();
        if (transType == null || (transType != 1 && transType != 2)) {
            throw new MobileStationBizException("客户下单类型入参错误");
        }

        if (StringUtil.isEmpty(req.getItemCode())) {
            throw new MobileStationBizException("客户下单产品代码入参为空");
        }
        checkAddress(req);
        Integer payType = req.getPaymentType();
        if (payType == null || (payType != 3 && payType != 2 && payType != 1)) {
            throw new MobileStationBizException("客户下单付款方式入参错误");
        }
        //1-客户自送，2-上门接货
        Integer accessMethod = req.getAccessMethod();
        if (accessMethod == null || (accessMethod != 1 && accessMethod != 2)) {
            throw new MobileStationBizException("客户下单取件方式入参错误");
        }
    }

    /**
     * 校验货物信息
     *
     * @param req
     */
    private void checkGoods(PlaceAnOrderReq req) throws MobileStationBizException {
        List<GoodsInfo> goodsInfos = req.getGoodsInfos();
        if (goodsInfos != null && goodsInfos.size() > 0) {
            for (GoodsInfo goodsInfo : goodsInfos) {
                if (StringUtils.isEmpty(goodsInfo.getGoodsType())) {
                    throw new MobileStationBizException("客户下单货物信息货物类型入参错误");
                }
                if (StringUtils.isEmpty(goodsInfo.getGoodsName())) {
                    throw new MobileStationBizException("客户下单货物信息品名入参错误");
                }
                if (StringUtils.isEmpty(goodsInfo.getQtyUnit())) {
                    throw new MobileStationBizException("客户下单货物单位入参错误");
                }

                if (goodsInfo.getQty() == null || goodsInfo.getQty().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new MobileStationBizException("客户下单货物信息数量入参错误");
                }
                Double weight = goodsInfo.getWeight();
                if (weight == null || weight <= 0) {
                    throw new MobileStationBizException("客户下单货物信息重量入参错误");
                }
                String weightUnit = goodsInfo.getWeightUnit();
                if (!"kg".contains(weightUnit)) {
                    throw new MobileStationBizException("客户下单货物信息重量单位入参错误");
                }
                Double length = goodsInfo.getLength();
                Double wide = goodsInfo.getWidth();
                Double height = goodsInfo.getHeight();
                if (length == null || wide == null || height == null || length <= 0 || wide <= 0 || height <= 0) {
                    throw new MobileStationBizException("客户下单货物信息体积入参错误");
                }

            }
        }
    }

    private void setSelctCityId(String cityId, AddressInfo addressInfo) {
        if (null != cityId) {

            ComCounty tCounty = comCountyService.queryForMap().get(cityId);
            if (null != tCounty) {
                addressInfo.setCounty(tCounty.getId().toString());
                addressInfo.setCity(tCounty.getCityId().toString());
                ComCity tCtiy = comCityService.queryForMap().get(tCounty.getCityId().toString());
                if (null != tCtiy) {
                    addressInfo.setProvince(StringUtil.getObjStr(tCtiy.getProvinceId()));
                }
            } else {
                ComCity tCtiy = comCityService.queryForMap().get(cityId);

                if (null != tCtiy) {
                    addressInfo.setCity(tCtiy.getId().toString());
                    addressInfo.setProvince(StringUtil.getObjStr(tCtiy.getProvinceId()));
                } else {
                    ComProvince comProvince = comProvinceService.queryForMap().get(cityId);
                    if (null != comProvince) {
                        addressInfo.setProvince(comProvince.getId().toString());
                    }
                }
            }

        }
    }

    /**
     * 客户下单地址校验
     *
     * @param req
     * @throws MobileStationBizException
     */
    private void checkAddress(PlaceAnOrderReq req) throws MobileStationBizException {
        Integer senderAddrId = req.getSenderAddrId();
        AddressInfo senderAddr = req.getSenderAddr();
        if (senderAddrId == null && senderAddr == null) {
            throw new MobileStationBizException("发件人地址入参错误");
        }
        if (senderAddrId == null && senderAddr != null) {
            if (StringUtils.isEmpty(senderAddr.getName())) {
                throw new MobileStationBizException("发件人姓名入参错误");
            }
            if (StringUtils.isEmpty(senderAddr.getTelephone())) {
                throw new MobileStationBizException("发件人手机号码入参错误");
            }
//            if (StringUtils.isEmpty(senderAddr.getAddress())) {
//                throw new MobileStationBizException("发件人地址入参错误");
//            }
            setSelctCityId(senderAddr.getAreaIdSel() + "", req.getSenderAddr());
            if (StringUtils.isEmpty(req.getSenderAddr().getProvince()) || StringUtils.isEmpty(req.getSenderAddr().getCity())) {
                throw new MobileStationBizException("发件人地址入参错误");
            }
            //校验经纬度
//            checkAddressLongitudeAndLatitude(senderAddr);
        }
        Integer receiverAddrId = req.getReceiverAddrId();
        AddressInfo receiverAddr = req.getReceiverAddr();
        if (receiverAddrId == null && receiverAddr == null) {
            throw new MobileStationBizException("收件人地址入参错误");
        }
        if (receiverAddrId == null && receiverAddr != null) {
            if (StringUtils.isEmpty(receiverAddr.getName())) {
                throw new MobileStationBizException("收件人姓名入参错误");
            }
            if (StringUtils.isEmpty(receiverAddr.getTelephone())) {
                throw new MobileStationBizException("收件人手机号码入参错误");
            }
//            if (StringUtils.isEmpty(receiverAddr.getAddress())) {
//                throw new MobileStationBizException("收件人地址入参错误");
//            }
            setSelctCityId(receiverAddr.getAreaIdSel() + "", req.getReceiverAddr());
            if (StringUtils.isEmpty(req.getReceiverAddr().getProvince()) || StringUtils.isEmpty(req.getReceiverAddr().getCity())) {
                throw new MobileStationBizException("收件人地址入参错误");
            }

//            checkAddressLongitudeAndLatitude(receiverAddr);
        }
    }

    /**
     * 设置服务提供商-站点
     *
     * @param stationCode
     * @param bookingForm
     * @throws MobileStationBizException
     */
    private void setStation(String stationCode, BookingForm bookingForm) throws MobileStationBizException {
        ComCustomer comCustomer = comCustomerDaoEx.getComCustomerByCustTtl(stationCode);
        if (comCustomer == null) {
            throw new MobileStationBizException("服务提供者(站点)不存在");
        }
        bookingForm.setTeamComsixNo(stationCode);
        bookingForm.setTeamComsixName(comCustomer.getCustName());
    }


    /**
     * 保存货物信息
     *
     * @param goodsInfos
     * @param bookingForm
     */
    private void saveGoodsInfo(List<GoodsInfo> goodsInfos, BookingForm bookingForm) {
        for (GoodsInfo goodsInfoReq : goodsInfos) {
            BillingFormSalm billingFormSalm = new BillingFormSalm();
            billingFormSalm.setBusiBooknoId(bookingForm.getId());
            if (goodsInfoReq.getGoodsName() != null) {
                billingFormSalm.setHscodeNachs(goodsInfoReq.getGoodsName());
            }
            if (goodsInfoReq.getGoodsType() != null) {
                billingFormSalm.setHscodeSpecs(goodsInfoReq.getGoodsType());
            }
            billingFormSalm.setGoodsQty(goodsInfoReq.getQty());
            billingFormSalm.setGoodsQtyUnitCo(goodsInfoReq.getQtyUnit());
            if (goodsInfoReq.getWeight() != null) {
                billingFormSalm.setGoodsGrosswht(BigDecimal.valueOf(goodsInfoReq.getWeight()));
            }
            if (goodsInfoReq.getWeightUnit() != null) {
                billingFormSalm.setWeightUnitCo(goodsInfoReq.getWeightUnit());
            }
            //体积存储
            if (goodsInfoReq.getLength() != null) {
                billingFormSalm.setGoodsLength(goodsInfoReq.getLength());
            }
            if (goodsInfoReq.getWidth() != null) {
                billingFormSalm.setGoodsWidth(goodsInfoReq.getWidth());
            }
            if (goodsInfoReq.getHeight() != null) {
                billingFormSalm.setGoodsHeight(goodsInfoReq.getHeight());
            }
            billingFormSalmDao.insertSelective(billingFormSalm);
        }
    }

    /**
     * 保存收货地址
     *
     * @param req         请求参数
     * @param bookingForm 同城运输订单信息
     * @throws MobileStationBizException 手机业务异常
     */
    private void saveReceiverAddress(PlaceAnOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        Integer receiverAddrId = req.getReceiverAddrId();
        AddressInfo receiverAddr = req.getReceiverAddr();
        StringBuffer sb = new StringBuffer();
        //如果只传递了是地址id,代表是选择的已有地址
        if (receiverAddrId != null && receiverAddr == null) {
            AddressQueryRes addressQueryRes = customerMobileAddressInfoDao.queryByPrimaryKey(receiverAddrId);
            if (addressQueryRes == null) {
                throw new MobileStationBizException("地址不存在");
            }
            bookingForm.setCneeCustAddr(addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress());
            bookingForm.setCneeCustHouseNumber(addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress());
            bookingForm.setCneeCustLinkMan(addressQueryRes.getName());
            bookingForm.setCneeCustLinkTel(addressQueryRes.getTelephone());
            /*收件人地址*/
            if (StringUtils.isNotBlank(addressQueryRes.getProvinceName())) {
                sb.append(addressQueryRes.getProvinceName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCityName())) {
                sb.append(addressQueryRes.getCityName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCountyName())) {
                sb.append(addressQueryRes.getCountyName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getAddress())) {
                sb.append(addressQueryRes.getAddress());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getDetailAddress())) {
                sb.append(addressQueryRes.getDetailAddress());
            }
            bookingForm.setCarriageDelivAddr(sb.toString());
            //bookingForm.setCarriageDelivAddr((addressQueryRes.getProvinceName() == null ? "" : addressQueryRes.getProvinceName()) + (addressQueryRes.getCityName() == null ? "" : addressQueryRes.getCityName()) + (addressQueryRes.getCountyName() == null ? "" : addressQueryRes.getCountyName()) + (addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress()) + (addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress()));
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
            bookingForm.setCneeCustName(req.getReceiverAddr().getName());
            bookingForm.setCneeCustHouseNumber(req.getReceiverAddr().getDetailAddress() == null ? "" : req.getReceiverAddr().getDetailAddress());
            bookingForm.setCneeCustAddr(req.getReceiverAddr().getAddress() == null ? "" : req.getReceiverAddr().getAddress());
            bookingForm.setCneeCustLinkMan(req.getReceiverAddr().getName());
            bookingForm.setCneeCustLinkTel(req.getReceiverAddr().getTelephone());
            if (req.getReceiverAddrSaveFlag() != null && req.getReceiverAddrSaveFlag() == CustomerDefine.SAVE_ADDRES) {
                if (receiverAddr.getAccountId() == null) {
                    receiverAddr.setAccountId(req.getAccountId());
                }
                saveAddress(receiverAddr, 1);//1收货地址，2发货地址
            }
            /*收件人地址*/
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
            if (req.getReceiverAddr() != null && (req.getReceiverAddr().getAddressLatitude() == null || req.getReceiverAddr().getAddressLongitude() == null)) {
                //获取只有地址的信息
                String address = bookingForm.getCarriageDelivAddr();
                if (bookingForm.getCarriageDelivAddr() != null && bookingForm.getCneeCustHouseNumber() != null
                        && bookingForm.getCarriageDelivAddr().lastIndexOf(bookingForm.getCneeCustHouseNumber()) > 0) {
                    address = bookingForm.getCarriageDelivAddr().substring(0, bookingForm.getCarriageDelivAddr().lastIndexOf(bookingForm.getCneeCustHouseNumber()));
                }
                GiPoint giPoint = gpsService.getGiPointByNearBy(address, bookingForm.getCneeCustHouseNumber());
                req.getReceiverAddr().setAddressLatitude(BigDecimal.valueOf(giPoint.getLatitude()));
                req.getReceiverAddr().setAddressLongitude(BigDecimal.valueOf(giPoint.getLongitude()));
            }
            bookingForm.setCarriageDelivLongitude(req.getReceiverAddr().getAddressLongitude());
            bookingForm.setCarriageDelivLatitude(req.getReceiverAddr().getAddressLatitude());

        }
        //logger.debug("同城运输下单-保存收件人信息后,bookingForm={}", JSON.toJSONString(bookingForm));
    }

    /**
     * 保存发货地址相关参数的信息
     *
     * @param req         请求参数
     * @param bookingForm 订单bean
     * @throws MobileStationBizException 通用手机业务异常
     */
    private void saveSenderAddress(PlaceAnOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        Integer senderAddrId = req.getSenderAddrId();
        AddressInfo senderAddr = req.getSenderAddr();
        StringBuffer sb = new StringBuffer();
        //如果只传递了发货地址ID,代表是选择的已有地址
        if (senderAddrId != null && senderAddr == null) {
            AddressQueryRes addressQueryRes = customerMobileAddressInfoDao.queryByPrimaryKey(senderAddrId);
            if (addressQueryRes == null) {
                throw new MobileStationBizException("地址不存在");
            }
            bookingForm.setShipCustaDdr(addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress());
            bookingForm.setShipCustHouseNumber(addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress());
            bookingForm.setShipCustlinkMan(addressQueryRes.getName());
            bookingForm.setShipCustlinkTel(addressQueryRes.getTelephone());
            /*发件人地址*/
            if (StringUtils.isNotBlank(addressQueryRes.getProvinceName())) {
                sb.append(addressQueryRes.getProvinceName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCityName())) {
                sb.append(addressQueryRes.getCityName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCountyName())) {
                sb.append(addressQueryRes.getCountyName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getAddress())) {
                sb.append(addressQueryRes.getAddress());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getDetailAddress())) {
                sb.append(addressQueryRes.getDetailAddress());
            }
            bookingForm.setCarriageReceiAddr(sb.toString());
            //bookingForm.setCarriageReceiAddr((addressQueryRes.getProvinceName() == null ? "" : addressQueryRes.getProvinceName()) + (addressQueryRes.getCityName() == null ? "" : addressQueryRes.getCityName()) + (addressQueryRes.getCountyName() == null ? "" : addressQueryRes.getCountyName()) + (addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress()) + (addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress()));
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

            bookingForm.setShipCustaDdr(req.getSenderAddr().getAddress() == null ? "" : req.getSenderAddr().getAddress());
            bookingForm.setShipCustHouseNumber(req.getSenderAddr().getDetailAddress() == null ? "" : req.getSenderAddr().getDetailAddress());
            bookingForm.setShipCustlinkMan(req.getSenderAddr().getName());
            bookingForm.setShipCustlinkTel(req.getSenderAddr().getTelephone());
             /*发件人地址*/
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
            //bookingForm.setCarriageReceiAddr((req.getSenderAddr().getProvinceName() == null ? "" : req.getSenderAddr().getProvinceName()) + (req.getSenderAddr().getCityName() == null ? "" : req.getSenderAddr().getCityName()) + (req.getSenderAddr().getCountyName() == null ? "" : req.getSenderAddr().getCountyName()) + (req.getSenderAddr().getAddress() == null ? "" : req.getSenderAddr().getAddress()) + (req.getSenderAddr().getDetailAddress() == null ? "" : req.getSenderAddr().getDetailAddress()));
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
            if (req.getSenderAddr() != null && (req.getSenderAddr().getAddressLatitude() == null || req.getSenderAddr().getAddressLongitude() == null)) {
                //获取只有地址的信息
                String address = bookingForm.getCarriageReceiAddr();
                if (bookingForm.getCarriageReceiAddr() != null && bookingForm.getShipCustHouseNumber() != null
                        && bookingForm.getCarriageReceiAddr().lastIndexOf(bookingForm.getShipCustHouseNumber()) > 0) {
                    address = bookingForm.getCarriageReceiAddr().substring(0, bookingForm.getCarriageReceiAddr().lastIndexOf(bookingForm.getShipCustHouseNumber()));
                }
                GiPoint giPoint = gpsService.getGiPointByNearBy(address, bookingForm.getShipCustHouseNumber());
                req.getSenderAddr().setAddressLatitude(BigDecimal.valueOf(giPoint.getLatitude()));
                req.getSenderAddr().setAddressLongitude(BigDecimal.valueOf(giPoint.getLongitude()));
            }
            bookingForm.setCarriageReceiLongitude(req.getSenderAddr().getAddressLongitude());
            bookingForm.setCarriageReceiLatitude(req.getSenderAddr().getAddressLatitude());

            if (req.getSenderAddrSaveFlag() != null && req.getSenderAddrSaveFlag() == CustomerDefine.SAVE_ADDRES) {
                if (senderAddr.getAccountId() == null) {
                    senderAddr.setAccountId(req.getAccountId());
                }
                //判断是否需要保存该地址
                saveAddress(senderAddr, 2);//1收货地址，2发货地址
            }
        }
        //logger.debug("同城运输下单-保存发件人信息后,bookingForm={}", JSON.toJSONString(bookingForm));
    }

    /**
     * 保存地址
     *
     * @param addressReq addressReq
     * @throws MobileStationBizException 手机业务异常
     */
    private void saveAddress(AddressInfo addressReq, int addressType) throws MobileStationBizException {
        //判断是否需要保存
        Boolean isDefaultAddr = addressReq.isDefaultAddress();
        MobileAddressInfo mobileAddressInfo = new MobileAddressInfo();
        try {
            BeanUtils.copyProperties(mobileAddressInfo, addressReq);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new MobileStationBizException(e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new MobileStationBizException(e.getMessage());
        }
        if (mobileAddressInfo.getCounty() != null && "0".equals(mobileAddressInfo.getCounty())) {
            mobileAddressInfo.setCounty(null);
        }
        mobileAddressInfo.setStatus(1);
        mobileAddressInfo.setCreateTime(new Date());
        mobileAddressInfo.setAddressType(addressType);
        //判断是否默认
        if (isDefaultAddr != null && isDefaultAddr) {
            mobileAddressInfo.setDefaultAddress(isDefaultAddr ? 1 : 0);
        }

        if (mobileAddressInfoDaoEx.querySameAddressInfo(mobileAddressInfo) >= 1) {
            //要是存在姓名、手机、地址一样的就不保存了。
        } else {
            mobileAddressInfoDao.insert(mobileAddressInfo);
        }
    }

    /**
     * 设置服务提供商-站点
     *
     * @param stationCode
     * @param bookingFormErr
     * @throws MobileStationBizException
     */
    private void setErrStation(String stationCode, BookingFormErr bookingFormErr) throws MobileStationBizException {
        ComCustomer comCustomer = comCustomerDaoEx.getComCustomerByCustTtl(stationCode);
        if (comCustomer == null) {
            throw new MobileStationBizException("服务提供者(站点)不存在");
        }
        bookingFormErr.setTeamComsixNo(stationCode);
        bookingFormErr.setTeamComsixName(comCustomer.getCustName());
    }

    /**
     * 设置异常收货地址
     *
     * @param req            请求参数
     * @param bookingFormErr 同城运输订单信息
     * @throws MobileStationBizException 手机业务异常
     */
    private void setErrReceiverAddress(PlaceAnOrderReq req, BookingFormErr bookingFormErr) throws MobileStationBizException {
        Integer receiverAddrId = req.getReceiverAddrId();
        AddressInfo receiverAddr = req.getReceiverAddr();
        StringBuffer sb = new StringBuffer();
        bookingFormErr.setDestnErr(req.isDestnErr());
        //如果只传递了是地址id,代表是选择的已有地址
        if (receiverAddrId != null && receiverAddr == null) {
            AddressQueryRes addressQueryRes = customerMobileAddressInfoDao.queryByPrimaryKey(receiverAddrId);
            if (addressQueryRes == null) {
                throw new MobileStationBizException("地址不存在");
            }
            bookingFormErr.setCneeCustAddr(addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress());
            bookingFormErr.setCneeCustHouseNumber(addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress());
            bookingFormErr.setCneeCustLinkMan(addressQueryRes.getName());
            bookingFormErr.setCneeCustLinkTel(addressQueryRes.getTelephone());
            /*收件人地址*/
            if (StringUtils.isNotBlank(addressQueryRes.getProvinceName())) {
                sb.append(addressQueryRes.getProvinceName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCityName())) {
                sb.append(addressQueryRes.getCityName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCountyName())) {
                sb.append(addressQueryRes.getCountyName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getAddress())) {
                sb.append(addressQueryRes.getAddress());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getDetailAddress())) {
                sb.append(addressQueryRes.getDetailAddress());
            }
            bookingFormErr.setCarriageDelivAddr(sb.toString());
            //bookingForm.setCarriageDelivAddr((addressQueryRes.getProvinceName() == null ? "" : addressQueryRes.getProvinceName()) + (addressQueryRes.getCityName() == null ? "" : addressQueryRes.getCityName()) + (addressQueryRes.getCountyName() == null ? "" : addressQueryRes.getCountyName()) + (addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress()) + (addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress()));
            bookingFormErr.setCarriageDelivLongitude(addressQueryRes.getAddressLongitude());
            bookingFormErr.setCarriageDelivLatitude(addressQueryRes.getAddressLatitude());
            if (!StringUtil.isEmpty(addressQueryRes.getCounty()) && !"0".equals(addressQueryRes.getCounty())) {
                bookingFormErr.setCarriageDelivCounty(addressQueryRes.getCounty());
            }
            if (addressQueryRes.getCity() != null) {
                bookingFormErr.setCarriageDelivCity(addressQueryRes.getCity());
            }
            if (addressQueryRes.getProvince() != null) {
                bookingFormErr.setCarriageDelivProvince(addressQueryRes.getProvince());
            }
            if (addressQueryRes.getCompanyName() != null) {
                bookingFormErr.setCneeCustName(addressQueryRes.getCompanyName());
            }
            if (addressQueryRes.getZipCode() != null) {
                bookingFormErr.setReceiverZipCode(addressQueryRes.getZipCode());
            }
        } else {
            bookingFormErr.setCneeCustName(req.getReceiverAddr().getName());
            bookingFormErr.setCneeCustHouseNumber(req.getReceiverAddr().getDetailAddress() == null ? "" : req.getReceiverAddr().getDetailAddress());
            bookingFormErr.setCneeCustAddr(req.getReceiverAddr().getAddress() == null ? "" : req.getReceiverAddr().getAddress());
            bookingFormErr.setCneeCustLinkMan(req.getReceiverAddr().getName());
            bookingFormErr.setCneeCustLinkTel(req.getReceiverAddr().getTelephone());
            if (req.getReceiverAddrSaveFlag() != null && req.getReceiverAddrSaveFlag() == CustomerDefine.SAVE_ADDRES && !req.isDestnErr()) {
                if (receiverAddr.getAccountId() == null) {
                    receiverAddr.setAccountId(req.getAccountId());
                }
                saveAddress(receiverAddr, 1);//1收货地址，2发货地址
            }
            /*收件人地址*/
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
            bookingFormErr.setCarriageDelivAddr(sb.toString());
            /*县级单位*/
            if (!StringUtil.isEmpty(req.getReceiverAddr().getCounty()) && !"0".equals(req.getReceiverAddr().getCounty())) {
                bookingFormErr.setCarriageDelivCounty(req.getReceiverAddr().getCounty());
            }
            if (req.getReceiverAddr().getCity() != null) {
                bookingFormErr.setCarriageDelivCity(req.getReceiverAddr().getCity());
            }
            if (req.getReceiverAddr().getProvince() != null) {
                bookingFormErr.setCarriageDelivProvince(req.getReceiverAddr().getProvince());
            }
            if (req.getReceiverAddr().getCompanyName() != null) {
                bookingFormErr.setCneeCustName(req.getReceiverAddr().getCompanyName());
            }
            if (req.getReceiverAddr().getZipCode() != null) {
                bookingFormErr.setReceiverZipCode(req.getReceiverAddr().getZipCode());
            }
            if (req.getReceiverAddr() != null && (req.getReceiverAddr().getAddressLatitude() == null || req.getReceiverAddr().getAddressLongitude() == null)) {
                //获取只有地址的信息
                String address = bookingFormErr.getCarriageDelivAddr();
                if (bookingFormErr.getCarriageDelivAddr() != null && bookingFormErr.getCneeCustHouseNumber() != null
                        && bookingFormErr.getCarriageDelivAddr().lastIndexOf(bookingFormErr.getCneeCustHouseNumber()) > 0) {
                    address = bookingFormErr.getCarriageDelivAddr().substring(0, bookingFormErr.getCarriageDelivAddr().lastIndexOf(bookingFormErr.getCneeCustHouseNumber()));
                }
                GiPoint giPoint = gpsService.getGiPointByNearBy(address, bookingFormErr.getCneeCustHouseNumber());
                req.getReceiverAddr().setAddressLatitude(BigDecimal.valueOf(giPoint.getLatitude()));
                req.getReceiverAddr().setAddressLongitude(BigDecimal.valueOf(giPoint.getLongitude()));
            }
            bookingFormErr.setCarriageDelivLongitude(req.getReceiverAddr().getAddressLongitude());
            bookingFormErr.setCarriageDelivLatitude(req.getReceiverAddr().getAddressLatitude());
        }
        //logger.debug("同城运输下单-保存收件人信息后,bookingFormErr={}", JSON.toJSONString(bookingFormErr));
    }

    /**
     * 设置异常发货地址相关参数
     *
     * @param req            请求参数
     * @param bookingFormErr 订单bean
     * @throws MobileStationBizException 通用手机业务异常
     */
    private void setErrSenderAddress(PlaceAnOrderReq req, BookingFormErr bookingFormErr) throws MobileStationBizException {
        Integer senderAddrId = req.getSenderAddrId();
        AddressInfo senderAddr = req.getSenderAddr();
        StringBuffer sb = new StringBuffer();
        bookingFormErr.setStartErr(req.isStartErr());
        //如果只传递了发货地址ID,代表是选择的已有地址
        if (senderAddrId != null && senderAddr == null) {
            AddressQueryRes addressQueryRes = customerMobileAddressInfoDao.queryByPrimaryKey(senderAddrId);
            if (addressQueryRes == null) {
                throw new MobileStationBizException("地址不存在");
            }
            bookingFormErr.setShipCustaDdr(addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress());
            bookingFormErr.setShipCustHouseNumber(addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress());
            bookingFormErr.setShipCustlinkMan(addressQueryRes.getName());
            bookingFormErr.setShipCustlinkTel(addressQueryRes.getTelephone());
            /*发件人地址*/
            if (StringUtils.isNotBlank(addressQueryRes.getProvinceName())) {
                sb.append(addressQueryRes.getProvinceName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCityName())) {
                sb.append(addressQueryRes.getCityName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getCountyName())) {
                sb.append(addressQueryRes.getCountyName());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getAddress())) {
                sb.append(addressQueryRes.getAddress());
            }
            if (StringUtils.isNotBlank(addressQueryRes.getDetailAddress())) {
                sb.append(addressQueryRes.getDetailAddress());
            }
            bookingFormErr.setCarriageReceiAddr(sb.toString());
            //bookingFormErr.setCarriageReceiAddr((addressQueryRes.getProvinceName() == null ? "" : addressQueryRes.getProvinceName()) + (addressQueryRes.getCityName() == null ? "" : addressQueryRes.getCityName()) + (addressQueryRes.getCountyName() == null ? "" : addressQueryRes.getCountyName()) + (addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress()) + (addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress()));
            bookingFormErr.setCarriageReceiLongitude(addressQueryRes.getAddressLongitude());
            bookingFormErr.setCarriageReceiLatitude(addressQueryRes.getAddressLatitude());
            if (!StringUtil.isEmpty(addressQueryRes.getCounty()) && !"0".equals(addressQueryRes.getCounty())) {
                bookingFormErr.setCarriageReceiCounty(addressQueryRes.getCounty());
            }
            if (addressQueryRes.getCity() != null) {
                bookingFormErr.setCarriageReceiCity(addressQueryRes.getCity());
            }
            if (addressQueryRes.getProvince() != null) {
                bookingFormErr.setCarriageReceiProvince(addressQueryRes.getProvince());
            }
            if (addressQueryRes.getCompanyName() != null) {
                bookingFormErr.setShipCustName(addressQueryRes.getCompanyName());
            }
            if (addressQueryRes.getZipCode() != null) {
                bookingFormErr.setSenderZipCode(addressQueryRes.getZipCode());
            }
        } else {

            bookingFormErr.setShipCustaDdr(req.getSenderAddr().getAddress() == null ? "" : req.getSenderAddr().getAddress());
            bookingFormErr.setShipCustHouseNumber(req.getSenderAddr().getDetailAddress() == null ? "" : req.getSenderAddr().getDetailAddress());
            bookingFormErr.setShipCustlinkMan(req.getSenderAddr().getName());
            bookingFormErr.setShipCustlinkTel(req.getSenderAddr().getTelephone());
             /*发件人地址*/
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
            bookingFormErr.setCarriageReceiAddr(sb.toString());
            //bookingFormErr.setCarriageReceiAddr((req.getSenderAddr().getProvinceName() == null ? "" : req.getSenderAddr().getProvinceName()) + (req.getSenderAddr().getCityName() == null ? "" : req.getSenderAddr().getCityName()) + (req.getSenderAddr().getCountyName() == null ? "" : req.getSenderAddr().getCountyName()) + (req.getSenderAddr().getAddress() == null ? "" : req.getSenderAddr().getAddress()) + (req.getSenderAddr().getDetailAddress() == null ? "" : req.getSenderAddr().getDetailAddress()));
            if (!StringUtil.isEmpty(req.getSenderAddr().getCounty()) && !"0".equals(req.getSenderAddr().getCounty())) {
                bookingFormErr.setCarriageReceiCounty(req.getSenderAddr().getCounty());
            }
            if (req.getSenderAddr().getCity() != null) {
                bookingFormErr.setCarriageReceiCity(req.getSenderAddr().getCity());
            }
            if (req.getSenderAddr().getProvince() != null) {
                bookingFormErr.setCarriageReceiProvince(req.getSenderAddr().getProvince());
            }
            if (req.getSenderAddr().getCompanyName() != null) {
                bookingFormErr.setShipCustName(req.getSenderAddr().getCompanyName());
            }
            if (req.getSenderAddr().getZipCode() != null) {
                bookingFormErr.setSenderZipCode(req.getSenderAddr().getZipCode());
            }
            if (req.getSenderAddr() != null && (req.getSenderAddr().getAddressLatitude() == null || req.getSenderAddr().getAddressLongitude() == null)) {
                //获取只有地址的信息
                String address = bookingFormErr.getCarriageReceiAddr();
                if (bookingFormErr.getCarriageReceiAddr() != null && bookingFormErr.getShipCustHouseNumber() != null
                        && bookingFormErr.getCarriageReceiAddr().lastIndexOf(bookingFormErr.getShipCustHouseNumber()) > 0) {
                    address = bookingFormErr.getCarriageReceiAddr().substring(0, bookingFormErr.getCarriageReceiAddr().lastIndexOf(bookingFormErr.getShipCustHouseNumber()));
                }
                GiPoint giPoint = gpsService.getGiPointByNearBy(address, bookingFormErr.getShipCustHouseNumber());
                req.getSenderAddr().setAddressLatitude(BigDecimal.valueOf(giPoint.getLatitude()));
                req.getSenderAddr().setAddressLongitude(BigDecimal.valueOf(giPoint.getLongitude()));
            }
            bookingFormErr.setCarriageReceiLongitude(req.getSenderAddr().getAddressLongitude());
            bookingFormErr.setCarriageReceiLatitude(req.getSenderAddr().getAddressLatitude());

            if (req.getSenderAddrSaveFlag() != null && req.getSenderAddrSaveFlag() == CustomerDefine.SAVE_ADDRES && !req.isStartErr()) {
                if (senderAddr.getAccountId() == null) {
                    senderAddr.setAccountId(req.getAccountId());
                }
                //判断是否需要保存该地址
                saveAddress(senderAddr, 2);//1收货地址，2发货地址
            }
        }
        //logger.debug("同城运输下单-保存发件人信息后,bookingFormErr={}", JSON.toJSONString(bookingFormErr));
    }

    @Override
    public List<OrderQueryRes> queryList(OrderQueryReq req) throws MobileStationBizException {
        logger.info("queryList req ={}", JSONArray.toJSONString(req));
        if (StringUtil.isEmpty(req.getAcctUsername())) {
            throw new MobileStationBizException("查件人参数为空");
        }
        //如果是收件查询 设置收货人的电话号码为当前账户的电话号码
        if (req.getOrderType() != null && !req.getOrderType()) {
            if (StringUtil.isEmpty(req.getReceiverTelephone())) {
                if (req.getAppLoginInfo() != null && req.getAppLoginInfo().getComAccount() != null && !StringUtil.isEmpty(req.getAppLoginInfo().getComAccount().getTelephone())) {
                    req.setReceiverTelephone(req.getAppLoginInfo().getComAccount().getTelephone());
                } else {
                    throw new MobileStationBizException("收件人电话参数为空");
                }
            }
        } else {
            if (req.getAppLoginInfo() != null && req.getAppLoginInfo().getComAccount() != null && !StringUtil.isEmpty(req.getAppLoginInfo().getComAccount().getTelephone())) {
                req.setStartLinkTelephone(req.getAppLoginInfo().getComAccount().getTelephone());
            } else {
                throw new MobileStationBizException("发件人电话参数为空");
            }
        }
        //如果是寄件查询 寄件人为下单人信息或者发货人（判断发货人手机号）为当前登录账号的订单。
        if (req.getOrderType() != null && req.getOrderType() && req.getStaffAccountId() == null) {
            ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
            serviceAuthBean.setAuthPwd(SystemDefine.CUSTOMER_AUTH_PWD);
            serviceAuthBean.setAuthUser(SystemDefine.CUSTOMER_AUTH_USER);
            serviceAuthBean.setSysFlag(SystemDefine.TRANSPORT_SYS_FLAG);
            //TODO 发货人手机号码目前需要传递
            ServiceResult serviceResult = accountService.queryAccountByTelephone(serviceAuthBean, req.getStartLinkTelephone());
            if (serviceResult.isResult()) {
                List<AccountInfo> accountInfos = (List<AccountInfo>) serviceResult.getData();
                if (accountInfos != null) {
                    if (!req.getLoginAcctUserName().equals(accountInfos.get(0).getAcctUsername())) {
                        throw new MobileStationBizException("发件人参数错误");
                    }
                }
            }
        }
        List<CustomerOrderListBean> customerOrderListBeanList = customerBookingFormDao.queryOrderList(req);
        List<OrderQueryRes> orderQueryResList = new ArrayList<>();
        for (CustomerOrderListBean orderListBean : customerOrderListBeanList) {
            OrderQueryRes orderQueryRes = createOrderQueryRes(orderListBean);
            injectSmsInfo(orderQueryRes, orderListBean);
            //订单列表查询设置订单状态,只可能是O单,同城运输
            setOrderStatus(orderListBean, orderQueryRes);
            orderQueryResList.add(orderQueryRes);
        }
        logger.info("CustomerOrderServiceImpl queryList res reqId={}, size ={}", req.getReqId(), customerOrderListBeanList == null ? 0 : customerOrderListBeanList.size());
        return orderQueryResList;
    }

    @Override
    public List<OrderQueryRes> queryOrderByBusiNo(OrderQueryReq req) throws MobileStationBizException {
        if (StringUtils.isBlank(req.getBusiBookNo())) {
            throw new MobileStationBizException("订单编号为空");
        }
        OrderQueryReq newReq = new OrderQueryReq();
        newReq.setBusiBookNo(req.getBusiBookNo());
        logger.info("CustomerOrderServiceImpl queryOrderByBusiNo start");
        List<CustomerOrderListBean> customerOrderListBeanList = customerBookingFormDao.queryOrderListByBusiNo(newReq);
        List<OrderQueryRes> orderQueryResList = new ArrayList<>();
        for (CustomerOrderListBean orderListBean : customerOrderListBeanList) {
            OrderQueryRes orderQueryRes = createOrderQueryRes(orderListBean);
            //订单列表查询设置订单状态,只可能是O单,同城运输
            setOrderStatus(orderListBean, orderQueryRes);
            orderQueryResList.add(orderQueryRes);
        }
        logger.info("CustomerOrderServiceImpl queryOrderByBusiNo result-{}", JSON.toJSONString(orderQueryResList));
        return orderQueryResList;
    }

    /**
     * 设置收件人信息
     *
     * @param orderListBean orderListBean
     * @param res           res
     */
    private void setReceiverAddr(CustomerOrderListBean orderListBean, OrderQueryRes res) {
        AddressInfo receiverAddr = new AddressInfo();
        receiverAddr.setName(orderListBean.getCneeCustLinkMan() == null ? "" : orderListBean.getCneeCustLinkMan());
        receiverAddr.setAddress(orderListBean.getCneeCustAddr() == null ? "" : orderListBean.getCneeCustAddr());
        receiverAddr.setTelephone(orderListBean.getCneeCustLinkTel() == null ? "" : orderListBean.getCneeCustLinkTel());
        receiverAddr.setProvince(orderListBean.getCarriageDelivProvince() == null ? "" : orderListBean.getCarriageDelivProvince());
        receiverAddr.setDetailAddress(orderListBean.getCneeCustHouseNumber() == null ? "" : orderListBean.getCneeCustHouseNumber());
        receiverAddr.setCounty(orderListBean.getCarriageDelivCounty() == null ? "" : orderListBean.getCarriageDelivCounty());
        receiverAddr.setCity(orderListBean.getCarriageDelivCity() == null ? "" : orderListBean.getCarriageDelivCity());

        if (!StringUtil.isEmpty(orderListBean.getCarriageDelivCounty())) {
            ComCounty tCounty = comCountyService.queryForMap().get(orderListBean.getCarriageDelivCounty());
            if (null != tCounty) {
                receiverAddr.setCountyName(tCounty.getAreaName());
            }
        }
        if (!StringUtil.isEmpty(orderListBean.getCarriageDelivCity())) {
            ComCity tCtiy = comCityService.queryForMap().get(orderListBean.getCarriageDelivCity());
            if (null != tCtiy) {
                receiverAddr.setCityName(tCtiy.getName());
            }
        }
        if (!StringUtil.isEmpty(orderListBean.getCarriageDelivProvince())) {
            ComProvince comProvince = comProvinceService.queryForMap().get(orderListBean.getCarriageDelivProvince());
            if (null != comProvince) {
                receiverAddr.setProvinceName(comProvince.getProvinceName());
            }
        }
        receiverAddr.setAddressLatitude(orderListBean.getCarriageDelivLatitude());
        receiverAddr.setAddressLongitude(orderListBean.getCarriageDelivLongitude());
        res.setReceiverAddr(receiverAddr);
    }

    /**
     * 设置寄件人信息
     *
     * @param orderListBean orderListBean
     * @param res           res
     */
    private void setSenderAddr(CustomerOrderListBean orderListBean, OrderQueryRes res) {
        AddressInfo senderAddr = new AddressInfo();
        senderAddr.setName(orderListBean.getShipCustlinkMan() == null ? "" : orderListBean.getShipCustlinkMan());
        senderAddr.setAddress(orderListBean.getShipCustaDdr() == null ? "" : orderListBean.getShipCustaDdr());
        senderAddr.setTelephone(orderListBean.getShipCustlinkTel() == null ? "" : orderListBean.getShipCustlinkTel());
        senderAddr.setProvince(orderListBean.getCarriageReceiProvince() == null ? "" : orderListBean.getCarriageReceiProvince());
        senderAddr.setDetailAddress(orderListBean.getShipCustHouseNumber() == null ? "" : orderListBean.getShipCustHouseNumber());
        senderAddr.setCounty(orderListBean.getCarriageReceiCounty() == null ? "" : orderListBean.getCarriageReceiCounty());
        senderAddr.setCity(orderListBean.getCarriageReceiCity() == null ? "" : orderListBean.getCarriageReceiCity());
        senderAddr.setAddressLatitude(orderListBean.getCarriageReceiLatitude());
        senderAddr.setAddressLongitude(orderListBean.getCarriageReceiLongitude());
        if (!StringUtil.isEmpty(orderListBean.getCarriageReceiCounty())) {
            ComCounty tCounty = comCountyService.queryForMap().get(orderListBean.getCarriageReceiCounty());
            if (null != tCounty) {
                senderAddr.setCountyName(tCounty.getAreaName());
            }
        }
        if (!StringUtil.isEmpty(orderListBean.getCarriageReceiCity())) {
            ComCity tCtiy = comCityService.queryForMap().get(orderListBean.getCarriageReceiCity());
            if (null != tCtiy) {
                senderAddr.setCityName(tCtiy.getName());
            }
        }
        if (!StringUtil.isEmpty(orderListBean.getCarriageReceiProvince())) {
            ComProvince comProvince = comProvinceService.queryForMap().get(orderListBean.getCarriageReceiProvince());
            if (null != comProvince) {
                senderAddr.setProvinceName(comProvince.getProvinceName());
            }
        }
        res.setSenderAddr(senderAddr);
    }

    /**
     * 设置订单货物信息
     *
     * @param orderListBean orderListBean
     * @param res           res
     */
    private void setGoodsInfos(CustomerOrderListBean orderListBean, OrderQueryRes res) {
        List<GoodsInfo> goodsInfoList = new ArrayList<>();
        Map<String, ComGoodsType> comGoodsTypeMap = comGoodsTypeService.queryForMap();
        Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
        List<BillingFormSalm> billingFormSalmList = orderListBean.getGoodsList();
        for (BillingFormSalm billingFormSalm : billingFormSalmList) {
            GoodsInfo goodsInfo = new GoodsInfo();
            if (billingFormSalm.getHscodeNachs() != null) {
                goodsInfo.setGoodsName(billingFormSalm.getHscodeNachs());
            }
            if (billingFormSalm.getHscodeSpecs() != null) {
                if (comGoodsTypeMap.get(billingFormSalm.getHscodeSpecs()) != null) {
                    goodsInfo.setGoodsType(comGoodsTypeMap.get(billingFormSalm.getHscodeSpecs()).getTypeCh());
                } else {
                    goodsInfo.setGoodsType(billingFormSalm.getHscodeSpecs());
                }
            }
            if (billingFormSalm.getGoodsQty() != null) {
                goodsInfo.setQty(billingFormSalm.getGoodsQty());
            }
            if (billingFormSalm.getGoodsQtyUnitCo() != null) {
                if (comUnitMap.get(billingFormSalm.getGoodsQtyUnitCo()) != null) {
                    goodsInfo.setQtyUnit(comUnitMap.get(billingFormSalm.getGoodsQtyUnitCo()).getUnitCh());
                } else {
                    goodsInfo.setQtyUnit(billingFormSalm.getGoodsQtyUnitCo());
                }
            }
            if (billingFormSalm.getGoodsGrosswht() != null) {
                goodsInfo.setWeight(billingFormSalm.getGoodsGrosswht().doubleValue());
            }
            if (billingFormSalm.getWeightUnitCo() != null) {
                if (comUnitMap.get(billingFormSalm.getWeightUnitCo()) != null) {
                    goodsInfo.setWeightUnit(comUnitMap.get(billingFormSalm.getWeightUnitCo()).getUnitCh());
                } else {
                    goodsInfo.setWeightUnit(billingFormSalm.getWeightUnitCo());
                }
            }
            //体积
            if (billingFormSalm.getGoodsLength() != null) {
                goodsInfo.setLength(billingFormSalm.getGoodsLength());
            }
            if (billingFormSalm.getGoodsWidth() != null) {
                goodsInfo.setWidth(billingFormSalm.getGoodsWidth());
            }
            if (billingFormSalm.getGoodsHeight() != null) {
                goodsInfo.setHeight(billingFormSalm.getGoodsHeight());
            }
            goodsInfoList.add(goodsInfo);
        }
        res.setGoodsInfos(goodsInfoList);
    }

    /**
     * 设置列表返回信息
     *
     * @param orderListBean orderListBean
     * @return OrderQueryRes
     */
    private OrderQueryRes createOrderQueryRes(CustomerOrderListBean orderListBean) throws MobileStationBizException {
        OrderQueryRes res = new OrderQueryRes();
        res.setId(orderListBean.getId());//订单id
        res.setBusiBookNo(orderListBean.getBusiBookNo());//订单编号
        res.setTransType(orderListBean.getOrderType());//1.运输 2.快递
        res.setPaymentType(orderListBean.getPayType());//1.到付 2.在线 3.到付
        res.setCreateUser(orderListBean.getCreateUser());//下单人账号
        if (!StringUtil.isEmpty(orderListBean.getCarriAgerecei())) {
            res.setAccessMethod(Integer.valueOf(orderListBean.getCarriAgerecei()));//1.客户自送，2.上门接货
        }
        res.setStatus(orderListBean.getBusiCtrl());//订单状态
        if (!StringUtils.isEmpty(orderListBean.getAccesstime())) {
            res.setAccessTime(orderListBean.getAccesstime());//提货时间
        }
        //寄件人信息
        setSenderAddr(orderListBean, res);
        //收件人信息
        setReceiverAddr(orderListBean, res);
        //站点信息
        res.setStationName(orderListBean.getSuppCustName());
        res.setStationAddress(orderListBean.getSuppCustAddr());
        //获取保价信息
        res.setInsured(orderListBean.isNeedInsure());
        if (null != orderListBean.isNeedInsure() && orderListBean.isNeedInsure()) {
            if (orderListBean.getGoodsValue() != null) {
                res.setGoodsValue(orderListBean.getGoodsValue().doubleValue());
            }
            if (orderListBean.getPremiumValue() != null) {
                res.setPremiumValue(orderListBean.getPremiumValue());
            }
            if (null != orderListBean.getApplyno()) {
                res.setApplyNo(orderListBean.getApplyno());
            }
            if (null != orderListBean.getPolicyno()) {
                res.setPolicyNo(orderListBean.getPolicyno());
            }
            if (null != orderListBean.getPremiumStatus()) {
                res.setInsureStatus(orderListBean.getPremiumStatus());
            }
        }
        if (null != orderListBean.getCarriageReceiLatitude()) {
            res.setStaLatitude(orderListBean.getCarriageReceiLatitude());
        }
        if (null != orderListBean.getCarriageReceiLongitude()) {
            res.setStaLongitude(orderListBean.getCarriageReceiLongitude());
        }
        if (null != orderListBean.getPredictValue()) {
            res.setPredictValue(orderListBean.getPredictValue());
        }
        if (null != orderListBean.getRevUser()) {
            //res.setRevUser(orderListBean.getRevUser());添加司机和车辆信息
            setOrderQueryDriveWithCar(orderListBean, res);
        }
        if (null != orderListBean.getDocno()) {
            res.setQuoteNo(orderListBean.getDocno());
        }
        Map<String, ComCurrency> currencyMap = comCurrencyService.queryForMap();
        if (null != orderListBean.getSmsNoty()) {
            res.setSmsNoty(orderListBean.getSmsNoty());
        }
        if (null != orderListBean.getNarrate()) {
            res.setNarrate(orderListBean.getNarrate());
        }
        if (null != orderListBean.getPredictCurr()) {
            ComCurrency comCurrency = currencyMap.get(orderListBean.getPredictCurr());
            res.setQuoteCurr(orderListBean.getPredictCurr());
            res.setPredictCurrNa(comCurrency == null ? "" : comCurrency.getCurrencyCh());
            res.setQuoteCurrNa(comCurrency == null ? "" : comCurrency.getCurrencyCh());
        }
        if (null != orderListBean.getCreateUser()) {
            res.setAcctUsername(orderListBean.getCreateUser());
        }
        if (null != orderListBean.getReceiverUser()) {
            res.setReceiverAcctUsername(orderListBean.getReceiverUser());
        }
        if (null != orderListBean.getBookingDate()) {
            res.setBookingDate(DateFormatUtils.format(orderListBean.getBookingDate(), "yyyy-MM-dd HH:mm:ss"));
        }
        if (null != orderListBean.getSenderFollow()) {
            res.setSenderFollow(orderListBean.getSenderFollow());
        }
        if (null != orderListBean.getReceiverFollow()) {
            res.setReceiverFollow(orderListBean.getReceiverFollow());
        }
        if (null != orderListBean.getCarriageDelivLatitude()) {
            res.setCarriageDelivLatitude(orderListBean.getCarriageDelivLatitude());
        }
        if (null != orderListBean.getCarriageDelivLongitude()) {
            res.setCarriageDelivLongitude(orderListBean.getCarriageDelivLongitude());
        }
        if (null != orderListBean.getCarriageReceiLatitude()) {
            res.setCarriageReceiLatitude(orderListBean.getCarriageReceiLatitude());
        }
        if (null != orderListBean.getCarriageReceiLongitude()) {
            res.setCarriageReceiLongitude(orderListBean.getCarriageReceiLongitude());
        }
        if (null != orderListBean.getGoodsPayment()) {
            res.setGoodsPayment(orderListBean.getGoodsPayment());
        }
        if (null != orderListBean.getGoodsPaymentCurr()) {
            ComCurrency comCurrency = currencyMap.get(orderListBean.getGoodsPaymentCurr());
            res.setGoodsPaymentCurrNa(comCurrency == null ? "" : comCurrency.getCurrencyCh());
        }
        res.setStatusNa(OrderDefine.getName(orderListBean.getBusiCtrl().toString()));
        if (orderListBean.getTeamComsixNo() != null) {
            res.setRevUserNa(orderListBean.getTeamComsixName());
        } else {
            if (!StringUtil.isEmpty(orderListBean.getRevUserName())) {
                res.setRevUserNa(orderListBean.getRevUserName());
            }
        }
        res.setIsJs(orderListBean.getIsJs());
        res.setQuotedType(orderListBean.getQuotedType());
        if (null != orderListBean.getUnitcode()) {
            res.setUnitCode(orderListBean.getUnitcode());
        }
        if (null != orderListBean.getCreateCompanyId()) {
            res.setStaffAccountId(orderListBean.getCreateCompanyId());
        }
        res.setPayUserRealName(orderListBean.getPayUserRealName());
        res.setPayUserTelephone(orderListBean.getPayUserTelephone());
        //设置货物信息
        if (orderListBean.getGoodsList() != null && orderListBean.getGoodsList().size() > 0 && orderListBean.getGoodsList().get(0).getId() != null) {
            setGoodsInfos(orderListBean, res);
        } else {
            orderListBean.setGoodsList(null);
        }
        res.setItemCode(orderListBean.getTransportType());//此处transportType是产品类型
        res.setCargoLoader(orderListBean.getCargoLoader());//1.整车  0.零担
        res.setVehicleTypeId(orderListBean.getVehicleTypeId());//车辆类型id

        return res;
    }

    private void injectSmsInfo(OrderQueryRes orderQueryRes, CustomerOrderListBean orderListBean) {
        OrderSmsInfo orderSmsInfo = new OrderSmsInfo();
        orderSmsInfo.setCode(orderListBean.getCode());
        orderSmsInfo.setDeliverName(orderListBean.getDeliverName());
        orderSmsInfo.setDeliverO2id(orderListBean.getDeliverO2id());
        orderSmsInfo.setDeliverTel(orderListBean.getDeliverTel());
        orderSmsInfo.setReceiveNo(orderListBean.getReceiveNo());
        orderSmsInfo.setSmsContent(orderListBean.getSmsContent());
        orderQueryRes.setOrderSmsInfo(orderSmsInfo);
    }


    /**
     * 获取订单描述
     *
     * @param orderListBean orderListBean
     * @return 订单描述
     */
    private String getOrderDescription(CustomerOrderListBean orderListBean) {
        Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
        //如果是同城运输单，需要车辆信息
        String description = "货物:";
        String weightUtil = "";
        String qtyUtil = "";
        String typeName = "";
        Map<String, ComGoodsType> comGoodsTypeMap = comGoodsTypeService.queryForMap();
        //订单 同城运输订单、同城专送订单
        //货物类型+总件数+总重量+增值服务
        if (CollectionUtils.isNotEmpty(orderListBean.getGoodsList())) {
            BillingFormSalm billingFormSalm = orderListBean.getGoodsList().get(0);
            //司机的列表描述信息  货物类型+货物总件数+货物总量+增值服务
            if (StringUtils.isNotBlank(billingFormSalm.getHscodeSpecs())) {
                if (comGoodsTypeMap.get(billingFormSalm.getHscodeSpecs()) != null) {
                    typeName = comGoodsTypeMap.get(billingFormSalm.getHscodeSpecs()).getTypeCh();
                }
                description += typeName;
            }
            if (StringUtils.isNotBlank(billingFormSalm.getGoodsQtyUnitCo())) {
                if (comUnitMap.get(billingFormSalm.getGoodsQtyUnitCo()) != null) {
                    qtyUtil = comUnitMap.get(billingFormSalm.getGoodsQtyUnitCo()).getUnitCh();
                }
                if (billingFormSalm.getGoodsQty() != null && billingFormSalm.getGoodsQty().compareTo(new BigDecimal(0)) == 1) {
                    description += ", 总共:";
                    description += billingFormSalm.getGoodsQty().setScale(0, BigDecimal.ROUND_HALF_UP);
                    description += qtyUtil;
                }

            }
            if (StringUtils.isNotBlank(billingFormSalm.getWeightUnitCo())) {
                if (comUnitMap.get(billingFormSalm.getWeightUnitCo()) != null) {
                    weightUtil = comUnitMap.get(billingFormSalm.getWeightUnitCo()).getUnitCh();
                }
                if (billingFormSalm.getGoodsGrosswht() != null && billingFormSalm.getGoodsGrosswht().compareTo(new BigDecimal(0)) == 1) {
                    description += ", 总重量:";
                    description += billingFormSalm.getGoodsGrosswht().setScale(2, BigDecimal.ROUND_HALF_UP);
                    description += weightUtil;
                }
            }
        }
        //增值服务
        List<MobileValueAdd> mobileValueAddList = mobileValueAddDaoEx.queryMobileValueAddList(orderListBean.getId());
        if (CollectionUtils.isNotEmpty(mobileValueAddList)) {
            description += ", 增值服务:";
            for (MobileValueAdd mobileValueAdd : mobileValueAddList) {
                description += mobileValueAdd.getValueAddName() + " ";
            }
        }
        return description.trim();
    }


    /**
     * 如果是同城运输,则把相关字段带入前台
     *
     * @param orderListBean orderListBean
     * @param res           res
     * @throws MobileStationBizException 手机业务异常
     */
    private void setOrderStatus(CustomerOrderListBean orderListBean, OrderQueryRes res) throws MobileStationBizException {
        //判断当前的产品类型如果是同城运输，则把可能的相关字段带入前台
        //(用户自报价,车队信息(报价，报价时间,关注提货时间,关注送货时间)，车辆信息,)
        if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(res.getItemCode())) {
            logger.debug("同城运输订单查询,设置订单状态后-start,结果-res-{}", JSON.toJSONString(res));
            //竞价信息
            MobileFleetBidding mobileFleetBidding = mobileFleetBiddingDaoEx.selectByOrderId(orderListBean.getId());
            //1.如果没有车队报价,dubbo服务方已将订单状态改为-12,此时设置订单的状态改为,订单已失效
            if (res.getStatus() == MobileStationDefine.MOBILE_ORDER_STATUS_DOC_FAIL) {

                res.setStatusNa(OrderState.INVALID_TRANSPORT.getName());//已失效(订单) -12

            } else if (res.getStatus() == MobileStationDefine.MOBILE_ORDER_STATUS_QUO_FAIL) {

                res.setStatusNa(OrderState.INVALID_QUOTE.getName());//已失效(报价) -13

            } else if (res.getStatus() == MobileStationDefine.MOBILE_ORDER_STATUS_TO_QUOTE) { //2.下单未满十分钟

                res.setStatusNa(OrderState.WAITING_QUOTE.getName());// 待报价 12

            } else if (res.getStatus() == MobileStationDefine.MOBILE_ORDER_STATUS_QUOTE && mobileFleetBidding != null) {//3.竞价完成  等待用户确认 13

                res.setStatusNa(OrderState.WAITING_CONFIRM.getName());// 待确认 13

            } else if (res.getStatus() == MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER && mobileFleetBidding != null) {//4.如果用户点击确认报价,订单状态为已接单

                res.setStatusNa(OrderState.ORDER_ACCEPTED.getName());//已接单 1
            } else {
                //其他情况
            }
            if (mobileFleetBidding != null) {
                res.setFleetQuoteValue(mobileFleetBidding.getBidValue());//车队竞价金额
                /*if (mobileFleetBidding.getTaxRate() != null) { //如果有税率
                    res.setFleetQuoteValue(mobileFleetBidding.getBidValue().add(mobileFleetBidding.getBidValue().multiply(mobileFleetBidding.getTaxRate())).setScale(2, BigDecimal.ROUND_HALF_UP));
                }*/
                res.setFleetQuoteCurr(mobileFleetBidding.getBidCurr());//车队竞价金额 币制
                //TODO 车队竞价后塞入车队,司机，车辆等信息到返回结果中
                if (StringUtil.isEmpty(mobileFleetBidding.getCreateUserId())) {
                    throw new MobileStationBizException("车队账号参数为空");
                }
                //TODO 车队信息
                res.setFleetName(mobileFleetBidding.getFleetName());
                res.setCreateDate(mobileFleetBidding.getCreateDate());//报价时间
                res.setPickTime(mobileFleetBidding.getPickTime());//提货时间
                res.setDeliveryTime(mobileFleetBidding.getDeliveryTime());//送货时间
                res.setFocusAccessTime(orderListBean.getFocusAccessTime());
                res.setFocusDeliveryTime(orderListBean.getFocusDeliveryTime());

                res.setTaxRate(mobileFleetBidding.getTaxRate());
            }
            //TODO 自报价金额和币制
            res.setSelfQuoteValue(orderListBean.getSelfQuoteValue());
            res.setSelfQuoteCurr(orderListBean.getSelfQuoteCurr());
            //TODO 增值服务 根据订单编号id查找增值服务编号 再查询增值服务
            List<MobileValueAddRel> mobileValueAddRelList = mobileValueAddRelDao.selectByBookingFormId(orderListBean.getId());
            if (CollectionUtils.isEmpty(mobileValueAddRelList)) {
                res.setMobileValueAddList(null);
            } else {
                List<MobileValueAdd> mobileValueAddList = new ArrayList<>();
                for (MobileValueAddRel mobileValueAddRel : mobileValueAddRelList) {
                    MobileValueAdd mobileValueAdd = mobileValueAddService.queryForMap().get(mobileValueAddRel.getValueAddId());
                    mobileValueAddList.add(mobileValueAdd);
                }
                res.setMobileValueAddList(mobileValueAddList);
            }
            res.setOrderDesc(getOrderDescription(orderListBean));//订单描述
            logger.debug("同城运输订单查询,设置订单状态后-end,结果-res-{}", JSON.toJSONString(res));
        }
    }

    private void setOrderQueryDriveWithCar(CustomerOrderListBean orderListBean, OrderQueryRes res) {
        res.setRevUser(orderListBean.getRevUser());
        //2. 司机的账户
        ComAccount driverAccount = comAccountDao.queryByAccount(orderListBean.getRevUser());
        if (driverAccount == null) {
            logger.debug("同城订单查询,该订单自己账户信息为空");
            return;
        }
        //TODO 司机信息
        res.setDriverName(driverAccount.getRealName());
        res.setDriverTelephone(driverAccount.getTelephone());
        //TODO 车辆信息
        //司机的车辆信息
        List<ComDriverInfo> cviList = comDriverInfoDaoEx.queryListByAccountId(driverAccount.getId());
        if (CollectionUtils.isNotEmpty(cviList)) {
            ComVehicleInfo comVehicleInfo = comVehicleInfoDao.selectByPrimaryKey(cviList.get(0).getVehicleId());
            if (comVehicleInfo != null) {
                ComVehicleType comVehicleType = comVehicleTypeDao.selectByPrimaryKey(Integer.parseInt(comVehicleInfo.getTrucktype()));
                res.setComVehicleType(comVehicleType);
            }
            res.setComVehicleInfo(comVehicleInfo);
        }


    }

    @Override
    public int count(OrderQueryReq req) {
        return customerBookingFormDao.count(req);
    }

    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public void update(OrderUpdateReq req) throws MobileStationBizException {
        BookingForm bookingFormUpdate = new BookingForm();
        bookingFormUpdate.setId(req.getId());

        BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(req.getId());
        Integer status = req.getStatus();

        ComWaybillTrace tmp = new ComWaybillTrace();
        if (status == -1) {  //取消订单
            if ((bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_NOORDER || bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) && bookingForm.getCreateUser().equals(req.getAcctUsername())) { //建单人可以取消待接单状态订单
                bookingFormUpdate.setBusiCtrl(status);
                bookingFormUpdate.setBusiAbort(req.getAcctUsername());
                bookingFormUpdate.setBusiAbortCaus(req.getReason());
                bookingFormUpdate.setBusiAbortDate(new Date());
                withdrawInsure(bookingForm.getBusiBookNo());
                updateBookingForm(bookingFormUpdate);
                tmp.setExecCode(WayBillStatusDefine.CANCLE_ORDER.getIntValue());
                tmp.setRemark(WayBillStatusDefine.CANCLE_ORDER.getName());
                if (StringUtil.isEmpty(bookingForm.getTeamComsixNo()) && !StringUtil.isEmpty(bookingForm.getRevUser())) {//指派MS
                    MobileAssignBean mobileAssignBean = new MobileAssignBean();
                    mobileAssignBean.setBusiBookNo(bookingForm.getBusiBookNo());
                    List<MobileBookingForm> forms = mobileMyOrderDao.selectMobileOrderList(mobileAssignBean);
                    if (forms != null && forms.size() > 0) {
                        for (MobileBookingForm m : forms) {
                            m.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                            mobileBookingFormDao.updateByPrimaryKey(m);
                        }
                    }
                }

                //通知中层
                GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
                List<String> allBusNo = new ArrayList<>();
                allBusNo.add(bookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNo);
                giOrderTraceResynced.setProductType(bookingForm.getTransportType());
                giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
                giOrderTraceResynced.setTsAct(new Date());
                if (bookingForm.getCreateCompanyId() != null) {
                    ComAccount companyAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
                    if (companyAccount != null) {
                        giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
                    } else {
                        giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
                    }
                } else {
                    giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
                }
                giOrderTraceResynced.setLoginCode(bookingForm.getCreateUser());
                giOrderTraceResynced.setRoleCode(SysAccountRole.NORMAL_PERSONAL.getRoleCode());
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

            } else {
                throw new MobileStationBizException("订单更新失败,请检查订单状态");
            }
        } else if (status == -9) {  //删除订单
            if ((bookingForm.getBusiCtrl() == -1 || bookingForm.getBusiCtrl() == -2) && bookingForm.getCreateUser().equals(req.getAcctUsername())) {  //-1作废   /-2取消待确认
                bookingFormUpdate.setBusiCtrl(status);
                updateBookingForm(bookingFormUpdate);
                tmp.setExecCode(WayBillStatusDefine.DELETE_ORDER.getIntValue());
                tmp.setRemark(WayBillStatusDefine.DELETE_ORDER.getName());
            } else {
                throw new MobileStationBizException("订单更新失败,请检查订单状态");
            }
        } else if (status == 6) {   //客户确认收货
            if (bookingForm.getBusiCtrl() == 2 && req.getAcctUsername().equals(bookingForm.getReceiverUser())) {  //运送中
                bookingFormUpdate.setBusiCtrl(status);
                updateBookingForm(bookingFormUpdate);
                tmp.setExecCode(WayBillStatusDefine.FINISH_ORDER.getIntValue());
                tmp.setRemark(WayBillStatusDefine.MS_ARRIVE_POD.getName());
            } else {
                throw new MobileStationBizException("订单更新失败,请检查订单状态");
            }
        } else {
            throw new MobileStationBizException("订单更新失败,请检查订单状态");
        }

        tmp.setAcctUsername(req.getAcctUsername());
        ComAccount comAccount = comAccountService.queryAccountByAcctUsername(req.getAcctUsername());
        if (comAccount != null) {
            tmp.setRealName(comAccount.getRealName());
        } else {
            tmp.setRealName(req.getAcctUsername());
        }
        tmp.setBusiBookNo(bookingForm.getBusiBookNo());
        tmp.setGrade(0);
        tmp.setStaDate(new Date());
        tmp.setRoleId(1);
        comWaybillTraceDao.insert(tmp);

    }

    private void updateBookingForm(BookingForm bookingFormUpdate) throws MobileStationBizException {
        int size = bookingFormDao.updateByPrimaryKeySelective(bookingFormUpdate);
        if (size <= 0) {
            throw new MobileStationBizException("订单更新失败");
        }
    }

    @Override
    @Transactional
    public void follow(OrderFollowReq req) throws MobileStationBizException {
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBookbusino());
        if (bookingForm == null) {
            throw new MobileStationBizException("订单不存在");
        }
        if (!StringUtil.isEmpty(req.getSelfUsername())) {
            if (req.getSelfUsername().equals(bookingForm.getCreateUser()) && !StringUtil.isEmpty(bookingForm.getReceiverUser()) && bookingForm.getCreateUser().equals(bookingForm.getReceiverUser())) {
                bookingForm.setSenderFollow(OrderFollowReq.follow_done);  //已关注
                bookingForm.setReceiverFollow(OrderFollowReq.follow_done);
            } else if (req.getSelfUsername().equals(bookingForm.getCreateUser())) {   //发件人操作
                if (null != req.getAcctUsername()) {
                    String[] tmp = req.getAcctUsername().split(",");
                    for (int i = 0; i < tmp.length; i++) {
                        if (null != tmp[i] && tmp[i].equals(bookingForm.getCreateUser())) {    //发件人关注
                            bookingForm.setSenderFollow(OrderFollowReq.follow_done);  //已关注
                        }
                        if (null != tmp[i] && tmp[i].equals(bookingForm.getReceiverUser())) {  //收件人关注
                            bookingForm.setReceiverFollow(OrderFollowReq.follow_waiting);  //等待关注确认
                            sendMessageIM(req.getRealName(), req.getBookbusino(), req.getAcctUsername(), CustomerDefine.IM_FOLLOW_CODE, null);
                        }

                    }
                }
            } else if (req.getSelfUsername().equals(bookingForm.getReceiverUser())) {  //收件人操作
                if (bookingForm.getReceiverFollow().equals(OrderFollowReq.follow_waiting)) {
                    sendMessageIM(req.getRealName(), req.getBookbusino(), bookingForm.getCreateUser(), CustomerDefine.IM_FOLLOW_NOTIFY_CODE, 1); //同意关注
                }
                bookingForm.setReceiverFollow(OrderFollowReq.follow_done);  //确认关注
            }
            int result = bookingFormDao.updateByPrimaryKeySelective(bookingForm);
            if (result <= 0)
                throw new MobileStationBizException("bookingForm更新失败!");
        } else {
            throw new MobileStationBizException("关注入参错误!");
        }


    }

    private String sendMessageIM(String realName, String busibookno, String acctUsername, String remindCode, Integer status) {
        String url = imContentSystemUrl;
        Map<String, Object> req = createRequest(realName, busibookno, acctUsername, remindCode, status);
        logger.debug("req={}", JSONObject.toJSONString(req));
        String resultStr = HttpClientUtil.URLPost(url, req,
                HeadAuthentication.setIMHead(CustomerDefine.APP_TAG));
        logger.debug("result={}", resultStr);
        return resultStr;
    }

    private Map<String, Object> createRequest(String realName, String busibookno, String acctUsername, String remindCode, Integer status) {
        Map<String, Object> req = new HashMap<>();
        req.put("sysCode", CustomerDefine.IM_SYS_DEFINE);
        req.put("platAccounts", acctUsername);
        req.put("sysTag", CustomerDefine.IM_MS_DEFINE);
        Map<String, String> mapObject = new HashMap<>();
        mapObject.put("realName", realName);
        mapObject.put("busiBookNo", busibookno);
        if (remindCode.equals(CustomerDefine.IM_FOLLOW_CODE)) {
            mapObject.put("msg", new StringBuffer("您的好友").append(realName).append("给您推送了一个订单").append(busibookno).append(",关注，确认接受本次关注？").toString());
        } else if (remindCode.equals(CustomerDefine.IM_FOLLOW_NOTIFY_CODE)) {
            if (status == 1) {
                mapObject.put("msg", new StringBuffer("您的好友").append(realName).append("已同意了关注").append(busibookno).append("订单").toString());
            } else if (status == 0) {
                mapObject.put("msg", new StringBuffer("您的好友").append(realName).append("已拒绝了关注").append(busibookno).append("订单").toString());
            }
            req.put("status", status);
        }
        req.put("body", JSONObject.toJSON(mapObject).toString());
        req.put("remindCode", remindCode);
        return req;
    }

    @Override
    @Transactional
    public void cancelFollow(OrderFollowReq req) throws MobileStationBizException {
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBookbusino());
        if (bookingForm == null)
            throw new MobileStationBizException("订单不存在");
        if (!StringUtil.isEmpty(req.getSelfUsername())) {
            if (req.getSelfUsername().equals(bookingForm.getCreateUser()) && !StringUtil.isEmpty(bookingForm.getReceiverUser()) && bookingForm.getCreateUser().equals(bookingForm.getReceiverUser())) {
                bookingForm.setSenderFollow(OrderFollowReq.follow_nono);  //已取消关注
                bookingForm.setReceiverFollow(OrderFollowReq.follow_nono);
            } else if (req.getSelfUsername().equals(bookingForm.getCreateUser())) {   //发件人操作
                if (req.getAcctUsername().equals(bookingForm.getCreateUser())) {    //发件人取消关注
                    bookingForm.setSenderFollow(OrderFollowReq.follow_nono);  //已取消关注
                } else if (req.getAcctUsername().equals(bookingForm.getReceiverUser())) {  //收件人取消关注
                    bookingForm.setReceiverFollow(OrderFollowReq.follow_waiting);  //取消关注确认
                } else {
                    throw new MobileStationBizException("关注人不是发件人和收件人");
                }
            } else if (req.getSelfUsername().equals(bookingForm.getReceiverUser())) {  //收件人操作
                if (bookingForm.getReceiverFollow().equals(OrderFollowReq.follow_waiting)) {
                    sendMessageIM(req.getRealName(), req.getBookbusino(), bookingForm.getCreateUser(), CustomerDefine.IM_FOLLOW_NOTIFY_CODE, 0); //拒绝关注
                }
                bookingForm.setReceiverFollow(OrderFollowReq.follow_nono);  //取消确认关注
            }
            int result = bookingFormDao.updateByPrimaryKeySelective(bookingForm);
            if (result <= 0)
                throw new MobileStationBizException("bookingForm更新失败!");
        } else {
            throw new MobileStationBizException("取消关注入参错误!");
        }
    }

    @Override
    public OrderQueryRes queryBroadcastOrder(ReceiveCustomerOrderReq req) throws Exception {
        CustomerOrderListBean customerOrderListBean = customerBookingFormDao.getOrderInfoByBusiNo(req.getBookbusino());
        if (customerOrderListBean == null)
            throw new MobileStationBizException("订单不存在");
        return createOrderQueryRes(customerOrderListBean);
    }


    /**
     * 客戶下單接單接口
     *
     * @param req
     * @return
     * @throws MobileStationBizException
     */
    @Transactional(rollbackFor = MobileStationBizException.class)
    public boolean receiveCustomerOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException {
        /**
         * customer接口：提供mobilestation调用，
         * 1、广播单：更新booking_form数据状态 + 同步数据信息给MS
         * 2、指派单；更新booking_form数据状态
         */
        try {
            if (req.isBrocast()) {
                return receiveBroadcastOrder(req);
            } else {
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBookbusino());
                if (bookingForm == null)
                    throw new MobileStationBizException("订单不存在");
                if (bookingForm.getBusiCtrl() != CustomerDefine.ORDER_STATUS_NOORDER)
                    throw new MobileStationBizException("该订单不在可接单状态");
                bookingForm.setRevUser(req.getAcctUsername());   //接单人
                bookingForm.setRevUserId(req.getAccountId());    //接单人ID
                bookingForm.setRevDate(new Date());
                bookingForm.setRevUserName(req.getRealName());//接单人姓名
                bookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_HAVEORDER);  //已接单
                if (req.getRoleId() != null) {
                    bookingForm.setBookingCtrl(req.getRoleId());
                }
                if (req.getAppLoginInfo().getCurrentComCustomer() != null && req.getAppLoginInfo().getCurrentComCustomer().getAccountId() != null) {
                    bookingForm.setRevCompanyId(req.getAppLoginInfo().getCurrentComCustomer().getAccountId());
                }
                //add by yujie20170407 接单操作判断是否现金支付，更新支付人信息
                if (bookingForm.getPayType() == MobileStationDefine.PAYTYPE_CASH) {
                    bookingForm.setPayUser(req.getAcctUsername());
                    bookingForm.setPayUserRealName(req.getAppLoginInfo().getComAccount().getRealName());
                    bookingForm.setPayUserTelephone(req.getAppLoginInfo().getComAccount().getTelephone());
                }
                int updateResult = bookingFormDaoEx.updateBookingFormReceiveBroadcastOrder(bookingForm);
                if (updateResult <= 0) {
                    throw new MobileStationBizException("更新状态失败");
                }

                RecordMobileStationOrderRequest recordMobileStationOrderRequest = new RecordMobileStationOrderRequest();
                recordMobileStationOrderRequest.setOrderType(RecordOrderType.ORDER_O.getValue());
                recordMobileStationOrderRequest.setMobileBookingFormId(req.getMobileBookFormId());
                recordMobileStationOrderRequest.setRecordBrocast(false);
                recordMobileStationOrderRequest.setRevUser(req.getAcctUsername());
                recordMobileStationOrderRequest.setBusiBookNo(req.getBookbusino());

                if (req.getAppLoginInfo().getCurrentComCustomer() != null && req.getAppLoginInfo().getCurrentComCustomer().getAccountId() != null) {
                    recordMobileStationOrderRequest.setRevCompanyId(req.getAppLoginInfo().getCurrentComCustomer().getAccountId());
                }

                //调用mobilestation接单接口
                try {

                    //判断是否为咪站接用户指派单，如果咪站接O单，需要把单进行拆分，POP-M、M-POD俩个单
                    if (req.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                        //咪站接指派单 1、修改POP-POD订单状态 2、插入POP-M的指派单 3、插入M-POD的指派单

                        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(req.getMobileBookFormId());
                        mobileBookingForm.setRevDate(new Date());
                        mobileBookingForm.setBusiCtrl(-1);
                        mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                        createMobileBookingFormByM(req, bookingForm);

                        ComWaybillTrace tmp = new ComWaybillTrace();
                        ComAccount account = comAccountService.queryAccountByAcctUsername(bookingForm.getRevUser());
                        tmp.setAcctUsername(req.getAcctUsername());
                        if (account != null) {
                            tmp.setRealName(account.getRealName());
                            tmp.setRemark(SysAccountRole.getName(req.getRoleId().intValue()) + account.getRealName() + "已接单，联系电话：" + account.getTelephone());
                        }
                        tmp.setStartLocus(MobileStationDefine.POP);
                        tmp.setDestnLocus(MobileStationDefine.POD);
                        tmp.setGrade(BusinessDefine.GRADE);
                        tmp.setExecCode(WayBillStatusDefine.MS_AGREE_O.getIntValue());

                        tmp.setRoleId(req.getRoleId());
                        comWaybillTraceDao.insert(tmp);
                    } else {
                        mobileStationOrderService.receiveMobileStationOrder(recordMobileStationOrderRequest);
                    }
                } catch (Exception e) {
                    throw new MobileStationBizException(e.getMessage());
                }
                return true;
                //记录接单日志   Modify: MS端记录
                // recordReceiveLog(req, bookingForm);
            }
        } catch (Exception e) {
            if (e instanceof MobileStationBizException) {
                //业务异常
                throw new MobileStationBizException(e.getMessage());
            } else {
                logger.error("个人指派单接单异常：" + e.getMessage());
                //数据库连接异常
                throw new MobileStationBizException("服务器网络异常,请重试");
            }
        }
    }

    /**
     * 客户下单接收广播单接口
     * 说明：该广播单接口默认接收客户发的O单
     *
     * @param req
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public boolean receiveBroadcastOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException {
        try {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBookbusino());
            if (bookingForm == null)
                throw new MobileStationBizException("订单不存在");
            if (bookingForm.getBusiCtrl() != CustomerDefine.ORDER_STATUS_NOORDER)
                throw new MobileStationBizException("该广播单不在可接单状态");
            //判断是否是微信广播单
//            if (!StringUtil.isEmpty(bookingForm.getWechatId())) {
//                bookingForm.setCreateUserId(req.getAccountId());
//                bookingForm.setCreateUser(req.getAcctUsername());
//            }
            bookingForm.setRevUser(req.getAcctUsername());   //接单人
            bookingForm.setRevUserId(req.getAccountId());    //接单人ID
            bookingForm.setRevUserName(req.getRealName());   //接单人真实姓名
            bookingForm.setRevDate(new Date());
            bookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_HAVEORDER);  //已接单
            if (req.getRoleId() != null) {
                bookingForm.setBookingCtrl(req.getRoleId());
            }

            if (req.getRevCompanyId() != null && req.getRevCompanyId() > 0) {
                bookingForm.setRevCompanyId(req.getRevCompanyId());
            }

            if (req.getCompanyAccountId() != null) {
                bookingForm.setRevCompanyId(req.getCompanyAccountId());
            }
            //add by yujie20170407 接单操作判断是否现金支付，更新支付人信息
            if (bookingForm.getPayType() == MobileStationDefine.PAYTYPE_CASH) {
                bookingForm.setPayUser(req.getAcctUsername());
                if (req.getAppLoginInfo() != null && req.getAppLoginInfo().getComAccount() != null) {
                    bookingForm.setPayUserRealName(req.getAppLoginInfo().getComAccount().getRealName());
                    bookingForm.setPayUserTelephone(req.getAppLoginInfo().getComAccount().getTelephone());
                } else {
                    ComAccount comAccount = comAccountDao.selectByPrimaryKey(req.getAccountId());
                    if (comAccount != null) {
                        bookingForm.setPayUserRealName(comAccount.getRealName());
                        bookingForm.setPayUserTelephone(comAccount.getTelephone());
                    }
                }
            }
            int updateResult = bookingFormDaoEx.updateBookingFormReceiveBroadcastOrder(bookingForm);
            if (updateResult <= 0) {
                throw new MobileStationBizException("订单已被接单");
            }

            try {
                //判断是否为咪站接用户广播单，如果咪站接O单，需要把单进行拆分，POP-M、M-POD俩个单
                if (req.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                    //咪站接广播单 1、插入POP-M的指派单 2、插入M-POD的指派单
                    createMobileBookingFormByM(req, bookingForm);
                } else {
                    createMobileBookingForm(req, bookingForm);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug("MobileBookingForm创建失败:" + e.getMessage());
                throw new MobileStationBizException("订单接单失败！");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof MobileStationBizException) {
                //业务异常
                throw new MobileStationBizException(e.getMessage());
            } else {
                //数据库连接异常
                logger.error("个人广播单接单已经：" + e.getMessage());
                throw new MobileStationBizException("服务器网络异常,请重试");
            }
        }
    }

    private void createMobileBookingForm(ReceiveCustomerOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        //生成 mobileBookingForm
        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        if (bookingForm.getNarrate() != null) {
            mobileBookingForm.setNarrate(bookingForm.getNarrate());
        }
        //发货地址
        mobileBookingForm.setShipCustProvide(bookingForm.getCarriageReceiProvince());
        mobileBookingForm.setShipCustCity(bookingForm.getCarriageReceiCity());
        mobileBookingForm.setShipCustCounty(bookingForm.getCarriageReceiCounty());
        mobileBookingForm.setShipCustAddr((bookingForm.getShipCustaDdr() == null ? "" : bookingForm.getShipCustaDdr()) + (bookingForm.getShipCustHouseNumber() == null ? "" : bookingForm.getShipCustHouseNumber()));
        mobileBookingForm.setShipCustLinkMan(bookingForm.getShipCustlinkMan());
        mobileBookingForm.setShipCustLinkTel(bookingForm.getShipCustlinkTel());
        mobileBookingForm.setShipLongitude(bookingForm.getCarriageReceiLongitude());
        mobileBookingForm.setShipLatitude(bookingForm.getCarriageReceiLatitude());
        //收货地址
        mobileBookingForm.setCneeCustProvide(bookingForm.getCarriageDelivProvince());
        mobileBookingForm.setCneeCustCity(bookingForm.getCarriageDelivCity());
        mobileBookingForm.setCneeCustCounty(bookingForm.getCarriageDelivCounty());
        mobileBookingForm.setCneeCustAddr((bookingForm.getCneeCustAddr() == null ? "" : bookingForm.getCneeCustAddr()) + (bookingForm.getCneeCustHouseNumber() == null ? "" : bookingForm.getCneeCustHouseNumber()));
        mobileBookingForm.setCneeCustLinkMan(bookingForm.getCneeCustLinkMan());
        mobileBookingForm.setCneeCustLinkTel(bookingForm.getCneeCustLinkTel());
        mobileBookingForm.setCneeLongitude(bookingForm.getCarriageDelivLongitude());
        mobileBookingForm.setCneeLatitude(bookingForm.getCarriageDelivLatitude());

        mobileBookingForm.setBookingFormId(bookingForm.getId());
        mobileBookingForm.setCreateUserId(bookingForm.getCreateUserId());
        mobileBookingForm.setCreateUser(bookingForm.getCreateUser());
        mobileBookingForm.setBusiBookNo(bookingForm.getBusiBookNo());

        mobileBookingForm.setEtaPopDate(bookingForm.getEtaPopDate());

        mobileBookingForm.setGoodsValue(bookingForm.getGoodsValue());
        mobileBookingForm.setBookingDate(bookingForm.getBookingDate());
        mobileBookingForm.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST);
        mobileBookingForm.setTransportType(bookingForm.getOrderType());
        mobileBookingForm.setProductType(bookingForm.getTransportType());
        mobileBookingForm.setRevUserId(bookingForm.getRevUserId());
        mobileBookingForm.setRevUser(bookingForm.getRevUser());

        if (bookingForm.getRevCompanyId() != null && bookingForm.getRevCompanyId() > 0) {
            mobileBookingForm.setRevCompanyId(bookingForm.getRevCompanyId());
        }

//        String userScope;
//        if (req.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()) {
//            //车主
//            userScope = "6";
//        } else {
//            //快递员
//            userScope = "5";
//        }
//        PlatformQuote platformQuote = expressService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG, String.valueOf(bookingForm.getRouteSchemaId()), orderUtil.getAccountId(mobileBookingForm.getStartLocus()), orderUtil.getAccountId(mobileBookingForm.getDestnLocus()));
//        if (platformQuote != null) {
//            mobileBookingForm.setComQuoteId(platformQuote.getQuoteNo());
//            mobileBookingForm.setQuotedType(platformQuote.getQuoteType());
//            mobileBookingForm.setPredictValue(platformQuote.getPrice());
//            mobileBookingForm.setPredictCurr(platformQuote.getCurrencyCode());
//        }
        mobileBookingForm.setPayType(bookingForm.getPayType());
        mobileBookingForm.setDestPayment(bookingForm.getDestPayment());
        mobileBookingForm.setGoodsPaymentCurr(bookingForm.getGoodsPaymentCurr());

        if (null != bookingForm.getOrderType()) {
            mobileBookingForm.setOrderType(bookingForm.getOrderType());
        }
        if (null != bookingForm.getNeedInsure()) {
            mobileBookingForm.setNeedInsure(bookingForm.getNeedInsure());
        }
        mobileBookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_HAVEORDER);  //已接单
        mobileBookingForm.setCreateDate(new Date());
        mobileBookingForm.setRevDate(new Date());
        mobileBookingForm.setIsJs(0);
        if (req.getRoleId() != null) {
            mobileBookingForm.setRoleId(req.getRoleId()); //也可通过产品类型判断
        } else {
            if (mobileBookingForm.getTransportType() == 1) {//运输
                mobileBookingForm.setRoleId(3);//司机
            } else if (mobileBookingForm.getTransportType() == 2) {//快递
                boolean miStation = false;
                List<Integer> roles = comAccountRoleRelDaoEx.getUserRoleIdByAccountId(mobileBookingForm.getRevUserId());
                for (Integer i : roles) {
                    if (i.intValue() == 23) {
                        miStation = true;
                    }
                }
                if (miStation) {
                    mobileBookingForm.setRoleId(23);
                } else {
                    mobileBookingForm.setRoleId(7);//快递员
                }
            } else {
                mobileBookingForm.setRoleId(3);//司机
            }
        }

        List<MobileGoodsDtl> recordList = new ArrayList<MobileGoodsDtl>();
        List<BillingFormSalm> billingFormSalms = billingFormSalmDaoEx.queryByBusiBooknoId(bookingForm.getId());
        for (BillingFormSalm billingFormSalm : billingFormSalms) {
            MobileGoodsDtl mobileGoodsDtl = createMobileGoodsDtl(mobileBookingForm, billingFormSalm);
            recordList.add(mobileGoodsDtl);
        }

        /**
         * 设置已接单状态
         */
        mobileBookingForm.setRevUser(req.getAcctUsername());
        AppBaseResult recordMoblieStationOrder = receiveMobileStationOrder(mobileBookingForm, recordList);
        if (recordMoblieStationOrder.getRetCode() == SystemDefine.FAILURE) {
            throw new MobileStationBizException("下单数据备份mobilestation失败");
        }

    }

    /**
     * 咪站接O单创建MobileBookingForm记录，注：没有货物信息
     *
     * @param req
     * @param bookingForm
     * @throws MobileStationBizException
     */
    private void createMobileBookingFormByM(ReceiveCustomerOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        //生成 mobileBookingForm,如果是上面取件  生成POP-M、M-POD ；如果是送货上门，生成M-POD
        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        if (bookingForm.getNarrate() != null) {
            mobileBookingForm.setNarrate(bookingForm.getNarrate());
        }

        mobileBookingForm.setBookingFormId(bookingForm.getId());
        mobileBookingForm.setCreateUserId(bookingForm.getCreateUserId());
        mobileBookingForm.setCreateUser(bookingForm.getCreateUser());
        mobileBookingForm.setBusiBookNo(bookingForm.getBusiBookNo());
        mobileBookingForm.setGoodsValue(bookingForm.getGoodsValue());
        mobileBookingForm.setBookingDate(bookingForm.getBookingDate());
        mobileBookingForm.setTransportType(bookingForm.getOrderType());
        mobileBookingForm.setProductType(bookingForm.getTransportType());
        mobileBookingForm.setRevUserId(req.getAccountId());
        mobileBookingForm.setRevUser(req.getAcctUsername());
        mobileBookingForm.setPayType(bookingForm.getPayType());
        mobileBookingForm.setDestPayment(bookingForm.getDestPayment());
        mobileBookingForm.setGoodsPaymentCurr(bookingForm.getGoodsPaymentCurr());
        if (null != bookingForm.getOrderType()) {
            mobileBookingForm.setOrderType(bookingForm.getOrderType());
        }
        if (null != bookingForm.getNeedInsure()) {
            mobileBookingForm.setNeedInsure(bookingForm.getNeedInsure());
        }
        mobileBookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_HAVEORDER);  //已接单
        mobileBookingForm.setCreateDate(new Date());
        mobileBookingForm.setRevDate(new Date());
        mobileBookingForm.setIsJs(0);
        mobileBookingForm.setRoleId(req.getRoleId()); //也可通过产品类型判断
        mobileBookingForm.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST);

//        //设置咪站的平台报价
//        PlatformQuote platformQuote = expressService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG, mobileBookingForm.getProductType(), "4");
//        if (platformQuote != null) {
//            mobileBookingForm.setComQuoteId(platformQuote.getQuoteNo());
//            mobileBookingForm.setQuotedType(platformQuote.getQuoteType());
//            mobileBookingForm.setPredictValue(platformQuote.getPrice());
//            mobileBookingForm.setPredictCurr(platformQuote.getCurrencyCode());
//        }
        ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(req.getAccountId());
        if (bookingForm.getCarriAgerecei().equals("2")) {
            //上门取货
            //发货地址 POP
            mobileBookingForm.setShipCustProvide(bookingForm.getCarriageReceiProvince());
            mobileBookingForm.setShipCustCity(bookingForm.getCarriageReceiCity());
            mobileBookingForm.setShipCustCounty(bookingForm.getCarriageReceiCounty());
            mobileBookingForm.setShipCustAddr((StringUtil.isEmpty(bookingForm.getShipCustaDdr()) ? "" : bookingForm.getShipCustaDdr()) + (StringUtil.isEmpty(bookingForm.getShipCustHouseNumber()) ? "" : bookingForm.getShipCustHouseNumber()));
            mobileBookingForm.setShipCustLinkMan(bookingForm.getShipCustlinkMan());
            mobileBookingForm.setShipCustLinkTel(bookingForm.getShipCustlinkTel());
            mobileBookingForm.setShipLongitude(bookingForm.getCarriageReceiLongitude());
            mobileBookingForm.setShipLatitude(bookingForm.getCarriageReceiLatitude());
            //收货地址 M
            mobileBookingForm.setCneeCustProvide(comUserinfo.getProvince());
            mobileBookingForm.setCneeCustCity(comUserinfo.getCity());
            mobileBookingForm.setCneeCustCounty(comUserinfo.getCounty());
            mobileBookingForm.setCneeCustAddr(comUserinfo.getAddress() + comUserinfo.getDetailAdd());
            mobileBookingForm.setCneeCustLinkMan(comUserinfo.getRealName());
            mobileBookingForm.setCneeCustLinkTel(comUserinfo.getTelephone());
            mobileBookingForm.setCneeLongitude(comUserinfo.getStaLongitude());
            mobileBookingForm.setCneeLatitude(comUserinfo.getStaLatitude());
            mobileBookingForm.setEtaPopDate(bookingForm.getEtaPopDate());
            mobileBookingForm.setStartLocus(MobileStationDefine.POP);
            mobileBookingForm.setDestnLocus(MobileStationDefine.M);
            mobileBookingForm.setDestnLocusId(req.getAccountId());

            PlatformQuote platformQuote = expressService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG, mobileBookingForm.getBusiBookNo(), mobileBookingForm.getStartLocus(), mobileBookingForm.getDestnLocus(), mobileBookingForm.getRoleId());
            if (platformQuote != null) {
//                mobileBookingForm.setComQuoteId(platformQuote.getQuoteNo());
//                mobileBookingForm.setQuotedType(platformQuote.getQuoteType());
                mobileBookingForm.setPredictValue(platformQuote.getPrice());
                mobileBookingForm.setPredictCurr(platformQuote.getCurrencyCode());
            }
            mobileBookingFormDao.insert(mobileBookingForm);
        }

        mobileBookingForm.setId(null);
        //设置M-POD的单
        mobileBookingForm.setStartLocus(MobileStationDefine.M);
        mobileBookingForm.setStartLocusId(req.getAccountId());
        mobileBookingForm.setDestnLocus(MobileStationDefine.POD);
        //发货地址 M
        mobileBookingForm.setShipCustProvide(comUserinfo.getProvince());
        mobileBookingForm.setShipCustCity(comUserinfo.getCity());
        mobileBookingForm.setShipCustCounty(comUserinfo.getCounty());
        mobileBookingForm.setShipCustAddr(comUserinfo.getAddress() + comUserinfo.getDetailAdd());
        mobileBookingForm.setShipCustLinkMan(comUserinfo.getRealName());
        mobileBookingForm.setShipCustLinkTel(comUserinfo.getTelephone());
        mobileBookingForm.setShipLongitude(comUserinfo.getStaLongitude());
        mobileBookingForm.setShipLatitude(comUserinfo.getStaLatitude());

        //收货地址POD
        mobileBookingForm.setCneeCustProvide(bookingForm.getCarriageDelivProvince());
        mobileBookingForm.setCneeCustCity(bookingForm.getCarriageDelivCity());
        mobileBookingForm.setCneeCustCounty(bookingForm.getCarriageDelivCounty());
        mobileBookingForm.setCneeCustAddr((StringUtil.isEmpty(bookingForm.getCneeCustAddr()) ? "" : bookingForm.getCneeCustAddr()) + (StringUtil.isEmpty(bookingForm.getCneeCustHouseNumber()) ? "" : bookingForm.getCneeCustHouseNumber()));
        mobileBookingForm.setCneeCustLinkMan(bookingForm.getCneeCustLinkMan());
        mobileBookingForm.setCneeCustLinkTel(bookingForm.getCneeCustLinkTel());
        mobileBookingForm.setCneeLongitude(bookingForm.getCarriageDelivLongitude());
        mobileBookingForm.setCneeLatitude(bookingForm.getCarriageDelivLatitude());

        PlatformQuote platformQuote = expressService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG, mobileBookingForm.getBusiBookNo(), mobileBookingForm.getStartLocus(), mobileBookingForm.getDestnLocus(), mobileBookingForm.getRoleId());
        if (platformQuote != null) {
//            mobileBookingForm.setComQuoteId(platformQuote.getQuoteNo());
//            mobileBookingForm.setQuotedType(platformQuote.getQuoteType());
            mobileBookingForm.setPredictValue(platformQuote.getPrice());
            mobileBookingForm.setPredictCurr(platformQuote.getCurrencyCode());
        }
        mobileBookingFormDao.insert(mobileBookingForm);

    }

    private MobileGoodsDtl createMobileGoodsDtl(MobileBookingForm mobileBookingForm, BillingFormSalm billingFormSalm) {
        MobileGoodsDtl mobileGoodsDtl = new MobileGoodsDtl();
        mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
        mobileGoodsDtl.setCreateDate(new Date());
        mobileGoodsDtl.setCreateUser(mobileBookingForm.getCreateUser().toString());
        if (billingFormSalm.getGoodsHeight() != null) {
            mobileGoodsDtl.setGoodsHeight(BigDecimal.valueOf(billingFormSalm.getGoodsHeight()));
        }
        if (billingFormSalm.getGoodsLength() != null) {
            mobileGoodsDtl.setGoodsLenght(BigDecimal.valueOf(billingFormSalm.getGoodsLength()));
        }
        if (billingFormSalm.getGoodsWidth() != null) {
            mobileGoodsDtl.setGoodsWide(BigDecimal.valueOf(billingFormSalm.getGoodsWidth()));
        }
        if (billingFormSalm.getHscodeNachs() != null) {
            mobileGoodsDtl.setGoodsName(billingFormSalm.getHscodeNachs());
        }
        if (billingFormSalm.getGoodsQty() != null) {
            mobileGoodsDtl.setGoodsQty(billingFormSalm.getGoodsQty());
        }
        if (billingFormSalm.getHscodeSpecs() != null) {
            mobileGoodsDtl.setGoodsType(billingFormSalm.getHscodeSpecs());
        }
        if (billingFormSalm.getGoodsQtyUnitCo() != null) {
            mobileGoodsDtl.setGoodsQtyUnit(billingFormSalm.getGoodsQtyUnitCo());
        }
        if (billingFormSalm.getGoodsGrosswht() != null) {
            mobileGoodsDtl.setGoodsWeight(billingFormSalm.getGoodsGrosswht());
        }
        mobileGoodsDtl.setGoodsWeightUnit(billingFormSalm.getWeightUnitCo());
        if (billingFormSalm.getGoodsHeight() != null && billingFormSalm.getGoodsLength() != null && billingFormSalm.getGoodsWidth() != null) {
            mobileGoodsDtl.setGoodsVolume(BigDecimal.valueOf(billingFormSalm.getGoodsHeight() * billingFormSalm.getGoodsLength() * billingFormSalm.getGoodsWidth()).setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        mobileGoodsDtl.setGoodsVolumeUnit("164");
        return mobileGoodsDtl;
    }

    @Override
    @Transactional
    public void cacelBroadcastOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException {
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBookbusino());
        if (bookingForm == null) {
            throw new MobileStationBizException("订单不存在");
        }
        if (null != bookingForm.getBusiCtrl() && bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_CANCLE) {
            throw new MobileStationBizException("此单已取消!");
        }
        if (null != bookingForm.getBusiCtrl() && (bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_NOORDER || bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN)) {
        } else {
            throw new MobileStationBizException("此单不在待接单状态，无法取消!");
        }
        bookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_CANCLE);  //取消订单
        bookingForm.setBusiAbort(req.getAcctUsername());  //取消人的账号
        bookingForm.setBusiAuditDate(new Date());
        int result = bookingFormDao.updateByPrimaryKey(bookingForm);
        if (result > 0) {
            ComWaybillTrace tmp = new ComWaybillTrace();
            tmp.setAcctUsername(req.getAcctUsername());
            if (req.getRealName() != null) {
                tmp.setRealName(req.getRealName());
            }
            tmp.setBusiBookNo(bookingForm.getBusiBookNo());
            tmp.setGrade(0);
            tmp.setRemark(BusinessDefine.ORDER_BROADCAST_CACEL);
            if (req.getRoleId() != null) {
                tmp.setRoleId(req.getRoleId());
            }
            tmp.setExecCode(WayBillStatusDefine.CANCLE_ORDER.getIntValue());
            tmp.setStaDate(new Date());
            comWaybillTraceDao.insert(tmp);

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
            logger.info("cacelBroadcastOrder sendBroadCastOrderMessage ={}", JSONArray.toJSONString(pushBeanList));
            gpsOrderService.sendBroadCastOrderMessage(pushBeanList);

        } else {
            throw new MobileStationBizException("取消订单失败");
        }
    }


    /**
     * 广播单广播接口
     *
     * @param req
     * @throws MobileStationBizException
     */
    @Override
    public void broadcastOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException {
        if (req != null && !StringUtil.isEmpty(req.getBookbusino())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBookbusino());
            if (bookingForm != null && bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_CANCLE && StringUtil.isEmpty(bookingForm.getTeamComsixNo()) && StringUtil.isEmpty(bookingForm.getRevUser())) {
                bookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_NOORDER);
                bookingFormDaoEx.updateCtrl(bookingForm);
            }
            if (bookingForm != null && bookingForm.getBusiCtrl() != null && bookingForm.getBusiCtrl().equals(CustomerDefine.ORDER_STATUS_NOORDER)) {
                broadcastOrder(bookingForm);
            } else {
                throw new MobileStationBizException("此单不在待接单状态，无法广播!");
            }
        }

    }

    /**
     * @param req
     * @return
     */
    private String getItemCode(OrderPolicyBean req) {
        if (req == null && req.getGoodsInfos() == null) {
            return "";
        }
        ComGoodsType comGoodsType = comGoodsTypeDaoEx.selectByTypeCode(req.getGoodsInfos().get(0).getGoodsType());
        if (comGoodsType != null) {
            return comGoodsType.getInsuranceCode();
        } else {
            return "";
        }
    }

    /**
     * @param req
     * @return
     */
    private String getItem(OrderPolicyBean req) {
        if (req == null && req.getGoodsInfos() == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (GoodsInfo goodsInfos : req.getGoodsInfos()) {
            sb.append(goodsInfos.getGoodsName() + " ");
        }
        return sb.toString();
    }

    private String getQuantity(OrderPolicyBean req) {
        if (req == null && req.getGoodsInfos() == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (GoodsInfo goodsInfos : req.getGoodsInfos()) {
            ComUnit comUnit = comUnitService.getComUnitByCode(goodsInfos.getQtyUnit());
            sb.append(goodsInfos.getQty()).append(comUnit.getUnitCh() != null ? comUnit.getUnitCh() : "").append(" ");
        }
        return sb.toString();
    }

    @Override
    public boolean checkInsured(GoodsTypeBean goodsTypeBean) throws MobileStationBizException {
        if (null == goodsTypeBean) {
            throw new MobileStationBizException("参数为空!");
        }

        if (StringUtils.isEmpty(goodsTypeBean.getItemCode())) {
            throw new MobileStationBizException("订单类型参数为空!");
        }

        if (StringUtils.isEmpty(goodsTypeBean.getGoodsTypes())) {
            throw new MobileStationBizException("货物类型参数为空!");
        }

        if (!goodsTypeBean.getItemCode().equals(QuoteItem.OTCKD.getItemCode()) && !goodsTypeBean.getItemCode().equals(QuoteItem.OTCYSEX.getItemCode())) {
            throw new MobileStationBizException("产品类别不在可投保的范围内!");
        }

        String[] goodsTypes = goodsTypeBean.getGoodsTypes().split(",");
        if (goodsTypes.length >= 2) {
            boolean isSame = true;
            for (int i = 1; i < goodsTypes.length; i++) {
                if (!goodsTypes[i - 1].equals(goodsTypes[i])) {
                    isSame = false;
                }
            }
            if (!isSame) {
                throw new MobileStationBizException("货物类型存在多种，不能投保!");
            }

        }
        ComGoodsType comGoodsType = comGoodsTypeDaoEx.selectByTypeCode(goodsTypes[0]);
        if (comGoodsType != null) {
            if (!StringUtils.isEmpty(comGoodsType.getInsuranceCode())) {
                return true;
            } else {
                throw new MobileStationBizException("货物类型不在可投保范围内!");
            }
        } else {
            throw new MobileStationBizException("货物类型不存在!");
        }
    }

    @Override
    public InsuredPayRes goInsuredPay(InsuredPayReq insuredPayReq) throws MobileStationBizException {
        ApprovalRequest approvalRequest = new ApprovalRequest();
        InsuredPayRes insuredPayRes = new InsuredPayRes();
        LoginUser userInfo = new LoginUser();
        try {
            userInfo.setUserName(URLEncoder.encode(user, ENCODING));
            userInfo.setPassword(URLEncoder.encode(passwd, ENCODING));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //承保产品代号
        ApprovalProduct productInfo = new ApprovalProduct();
        productInfo.setClassesCode(CustomerDefine.CLASSTYPE);
        ApprovalResponse2 reposne = null;

        try {
            //包装发送信息
            approvalRequest.setUserInfo(userInfo);
            approvalRequest.setProductInfo(productInfo);
            approvalRequest.setCheckCode("1");
            StringBuffer sb = new StringBuffer("");
            sb.append("<?xml version ='1.0' encoding='utf-8'?>").append("<APPROVALREQUEST>")
                    .append("<DATA> ")
                    .append("<UNITCODE>").append(insuredPayReq.getUnitCode()).append("</UNITCODE>")
                    .append("<APPLYNO>").append(insuredPayReq.getApplyNo()).append("</APPLYNO>")
                    .append("<APPLYENDORSENO></APPLYENDORSENO>")
                    .append("<PREMIUM>").append(insuredPayReq.getPremium()).append("</PREMIUM>")
                    .append("<ISPAYMENT>").append(insuredPayReq.getIsPayment()).append("</ISPAYMENT>")
                    .append("<SUCCESSURL>").append(insuredPayReq.getSuccessUrl() != null ? insuredPayReq.getSuccessUrl() : "").append("</SUCCESSURL>")
                    .append("<FAILEDURL>").append(insuredPayReq.getFailedUrl() != null ? insuredPayReq.getFailedUrl() : "").append("</FAILEDURL>")
                    .append("</DATA>")
                    .append("</APPROVALREQUEST>");

            approvalRequest.setPolicyInfo(sb.toString());
            logger.debug("goInsuredPay approvalRequest={}", JSONObject.toJSONString(approvalRequest));
            reposne = iZrxCommonService.postApplication(approvalRequest);
            SysMessage sysMessage = reposne.getSysMessage();
            if (sysMessage != null) {
                logger.debug("错误类型:" + sysMessage.getErrorType() + "");
                logger.debug("错误代码:" + sysMessage.getErrorCode() + "");
                logger.debug("错误信息:" + sysMessage.getErrorMsg() + "");
                if (!StringUtil.isEmpty(sysMessage.getErrorCode())) {
                    throw new MobileStationBizException(sysMessage.getErrorMsg());
                }
            }
            if (null != reposne.getPolicyInfo()) {
                Document document = DocumentHelper.parseText(reposne.getPolicyInfo());
                Element root = document.getRootElement();
                Element result = null;
                Iterator e = null;
                for (e = root.element("DATA").elementIterator(); e.hasNext(); ) {
                    result = (Element) e.next();
                    if (result.getName().equals("UNITCODE")) {
                        insuredPayRes.setUnitCode(result.getTextTrim());
                    }
                    if (result.getName().equals("APPLYNO")) {
                        insuredPayRes.setApplyNo(result.getTextTrim());
                    }
                    if (result.getName().equals("PAYAPPLYNO")) {
                        insuredPayRes.setPayApplyNo(result.getTextTrim());
                    }
                    if (result.getName().equals("PAYFEEADDRESS")) {
                        insuredPayRes.setPayFeeAddress(result.getTextTrim());
                    }
                }
            }
        } catch (WssException_Exception e) {
            e.printStackTrace();
            throw new MobileStationBizException("调用太保接口失败!");
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new MobileStationBizException("调用太保接口失败!");
        }
        return insuredPayRes;
    }

    /**
     * 投保支付成功
     *
     * @param req
     * @throws MobileStationBizException
     */
    @Override
    public void paySuccessed(ReceiveCustomerOrderReq req) throws MobileStationBizException {
        if (req != null && !StringUtil.isEmpty(req.getBookbusino())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBookbusino());
            bookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_SENDIN);
            bookingForm.setPremiumStatus(10);//保单生效
            bookingFormDao.updateByPrimaryKey(bookingForm);
            doSubOrder(bookingForm);
        }

    }

    /**
     * 取消投保继续下单
     *
     * @param req
     * @throws MobileStationBizException
     */
    @Override
    public void cancelInsuredContinueOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException {
        if (req != null && !StringUtil.isEmpty(req.getBookbusino())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBookbusino());
            if (bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
                bookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_SENDIN);
            }
            bookingForm.setNeedInsure(false);
            bookingFormDao.updateByPrimaryKey(bookingForm);
            List<MobileBookingForm> mobileBookingForms = mobileBookingFormDaoEx.selectMobileOrderByBookBusiNo(req.getBookbusino());
            if (mobileBookingForms != null && mobileBookingForms.size() > 0) {
                for (MobileBookingForm mobileBookingForm : mobileBookingForms) {
                    mobileBookingForm.setNeedInsure(false);
                    mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                }
            }
        }

    }

    private MobileBookingForm doSubOrder(BookingForm bookingForm) throws MobileStationBizException {
        //车主或者快递员
        if (!StringUtil.isEmpty(bookingForm.getRevUserId()) || !StringUtil.isEmpty(bookingForm.getTeamComsixNo())) {
            //下单给mobilestation
            if (StringUtil.isEmpty(bookingForm.getTeamComsixNo())) {
                //下单给快递员，则构造数据插入MOBILE_BOOKING_FORM

                MobileBookingForm mobileBookingForm = new MobileBookingForm();

                mobileBookingForm.setShipCustProvide(bookingForm.getCarriageReceiProvince());
                mobileBookingForm.setShipCustCity(bookingForm.getCarriageReceiCity());
                if (null != bookingForm.getCarriageReceiCounty()) {
                    mobileBookingForm.setShipCustCounty(bookingForm.getCarriageReceiCounty());
                }
                mobileBookingForm.setShipCustAddr((bookingForm.getShipCustaDdr() == null ? "" : bookingForm.getShipCustaDdr()) + (bookingForm.getShipCustHouseNumber() == null ? "" : bookingForm.getShipCustHouseNumber()));
                mobileBookingForm.setShipCustLinkMan(bookingForm.getShipCustlinkMan());
                mobileBookingForm.setShipCustLinkTel(bookingForm.getShipCustlinkTel());
                mobileBookingForm.setShipLatitude(bookingForm.getCarriageReceiLatitude());
                mobileBookingForm.setShipLongitude(bookingForm.getCarriageReceiLongitude());

                mobileBookingForm.setCneeCustProvide(bookingForm.getCarriageDelivProvince());
                mobileBookingForm.setCneeCustCity(bookingForm.getCarriageDelivCity());
                if (null != bookingForm.getCarriageDelivCounty()) {
                    mobileBookingForm.setCneeCustCounty(bookingForm.getCarriageDelivCounty());
                }
                mobileBookingForm.setCneeCustAddr((bookingForm.getCneeCustAddr() == null ? "" : bookingForm.getCneeCustAddr()) + (bookingForm.getCneeCustHouseNumber() == null ? "" : bookingForm.getCneeCustHouseNumber()));
                mobileBookingForm.setCneeCustLinkMan(bookingForm.getCneeCustLinkMan());
                mobileBookingForm.setCneeCustLinkTel(bookingForm.getCneeCustLinkTel());
                mobileBookingForm.setCneeLatitude(bookingForm.getCarriageDelivLatitude());
                mobileBookingForm.setCneeLongitude(bookingForm.getCarriageDelivLongitude());
                if (null != bookingForm.getPredictValue()) {
                    mobileBookingForm.setPredictValue(bookingForm.getPredictValue());
                }
                mobileBookingForm.setBookingFormId(bookingForm.getId());
                mobileBookingForm.setCreateUserId(bookingForm.getCreateUserId());
                mobileBookingForm.setCreateUser(bookingForm.getCreateUser());
                mobileBookingForm.setBusiBookNo(bookingForm.getBusiBookNo());

                if (null != bookingForm.getGoodsValue()) {
                    mobileBookingForm.setGoodsValue(bookingForm.getGoodsValue());
                }
                mobileBookingForm.setBookingDate(bookingForm.getBookingDate());
                mobileBookingForm.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL);
                mobileBookingForm.setTransportType(bookingForm.getOrderType());
                mobileBookingForm.setComQuoteId(bookingForm.getDocno());
                mobileBookingForm.setPayType(bookingForm.getPayType());
                if (!StringUtil.isEmpty(bookingForm.getGoodsPayment())) {
                    mobileBookingForm.setDestPayment(bookingForm.getGoodsPayment());
                    mobileBookingForm.setGoodsPaymentCurr(bookingForm.getGoodsPaymentCurr());
                }
                if (!StringUtil.isEmpty(bookingForm.getGoodsPayment())) {
                    mobileBookingForm.setGoodsPayment(bookingForm.getGoodsPayment());
                    mobileBookingForm.setGoodsPaymentCurr(bookingForm.getGoodsPaymentCurr());
                }
                if (null != bookingForm.getPredictCurr()) {
                    mobileBookingForm.setPredictCurr(bookingForm.getPredictCurr());
                }
                if (!StringUtil.isEmpty(bookingForm.getCarriAgerecei())) {
                    mobileBookingForm.setOrderType(Integer.parseInt(bookingForm.getCarriAgerecei()));
                }
                if (null != bookingForm.getNeedInsure()) {
                    mobileBookingForm.setNeedInsure(bookingForm.getNeedInsure());
                }
                mobileBookingForm.setCreateDate(new Date());
                mobileBookingForm.setIsJs(0);
                mobileBookingForm.setProductType(bookingForm.getTransportType());

                if (null != bookingForm.getGoodsValue()) {
                    mobileBookingForm.setGoodsValue(bookingForm.getGoodsValue());
                }
                mobileBookingForm.setBookingDate(bookingForm.getBookingDate());
                mobileBookingForm.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL);
                mobileBookingForm.setTransportType(bookingForm.getOrderType());
                mobileBookingForm.setRevUserId(bookingForm.getRevUserId());
                mobileBookingForm.setRevUser(bookingForm.getRevUser());

                mobileBookingForm.setPayType(bookingForm.getPayType());

                mobileBookingForm.setBusiCtrl(0);
                mobileBookingForm.setCreateDate(new Date());
                mobileBookingForm.setStartLocus(MobileStationDefine.POP);
                mobileBookingForm.setDestnLocus(MobileStationDefine.POD);

                if (null != bookingForm.getNarrate()) {
                    mobileBookingForm.setNarrate(bookingForm.getNarrate());
                }
                //4:小(M)站,5:快递员,6:司机
                if (mobileBookingForm.getTransportType() == 1) {//运输
                    mobileBookingForm.setRoleId(3);//司机
                } else if (mobileBookingForm.getTransportType() == 2) {//快递
                    if (bookingForm.getBookingCtrl() != null) {
                        mobileBookingForm.setRoleId(bookingForm.getBookingCtrl());
                    } else {
                        boolean miStation = false;
                        List<Integer> roles = comAccountRoleRelDaoEx.getUserRoleIdByAccountId(mobileBookingForm.getRevUserId());
                        for (Integer i : roles) {
                            if (i.intValue() == 23) {
                                miStation = true;
                            }
                        }
                        if (miStation) {
                            mobileBookingForm.setRoleId(23);
                        } else {
                            mobileBookingForm.setRoleId(7);//快递员
                        }
                    }
                } else {
                    mobileBookingForm.setRoleId(3);//司机
                }
                if (bookingForm.getCreateCompanyId() != null && bookingForm.getCreateCompanyId() > 0) {
                    mobileBookingForm.setCreateCompanyId(bookingForm.getCreateCompanyId());
                }

                MobileMoudleRel cond = new MobileMoudleRel();
                cond.setAcctUsername(bookingForm.getRevUser());
                cond.setCompanyId(bookingForm.getRevCompanyId());
                if (bookingForm.getBookingCtrl() != null && bookingForm.getBookingCtrl() == 23) {
                    cond.setMoudleCode(MobileStationDefine.PRODUCT_TYPE_OTCKDM);
                } else {
                    cond.setMoudleCode(bookingForm.getTransportType());
                }

                if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(bookingForm.getTransportType())) {
                    cond.setMoudleCode(MobileStationDefine.PRODUCT_TYPE_TCKD);
                }
                if (bookingForm.getRouteSchemaId() != null) {
                    PlatformQuote platformQuote = expressService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG, mobileBookingForm.getBusiBookNo(),
                            mobileBookingForm.getStartLocus(), mobileBookingForm.getDestnLocus(), mobileBookingForm.getRoleId());
                    if (platformQuote != null) {
                        mobileBookingForm.setPredictValue(platformQuote.getPrice());
                        mobileBookingForm.setPredictCurr(platformQuote.getCurrencyCode());
                    }
                }
                //数据备份mobilestation
                mobileBookingFormDao.insert(mobileBookingForm);
                List<MobileGoodsDtl> recordList = new ArrayList<MobileGoodsDtl>();
                List<BillingFormSalm> billingFormSalms = billingFormSalmDaoEx.queryByBusiBooknoId(bookingForm.getId());
                for (BillingFormSalm billingFormSalm : billingFormSalms) {
                    MobileGoodsDtl mobileGoodsDtl = createMobileGoodsDtl(mobileBookingForm, billingFormSalm);
                    recordList.add(mobileGoodsDtl);
                }
                if (recordList != null && recordList.size() > 0) {
                    mobileGoodsDtlDao.batchInsert(recordList);
                }
                return mobileBookingForm;
            }
        }
        return null;
    }

    @Override
    public QueryPolicyRes queryPolicy(QueryPolicyReq queryPolicyReq) throws MobileStationBizException {
        QueryPolicyRes queryPolicyRes = new QueryPolicyRes();
        ApprovalRequest approvalRequest = new ApprovalRequest();
        LoginUser userInfo = new LoginUser();
        try {
            userInfo.setUserName(URLEncoder.encode(user, ENCODING));
            userInfo.setPassword(URLEncoder.encode(passwd, ENCODING));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //承保产品代号
        ApprovalProduct productInfo = new ApprovalProduct();
        productInfo.setClassesCode(CustomerDefine.CLASSTYPE);
        ApprovalResponse2 reposne = null;

        try {
            //包装发送信息
            approvalRequest.setUserInfo(userInfo);
            approvalRequest.setProductInfo(productInfo);
            approvalRequest.setCheckCode("1");
            StringBuffer sb = new StringBuffer("");
            sb.append("<?xml version ='1.0' encoding='utf-8'?>")
                    .append("<ROOT><CONFIG><TYPE>IN</TYPE><WORKTYPE>2</WORKTYPE></CONFIG><DATA><POLICY>")
                    .append("<UNITCODE>").append(queryPolicyReq.getUnitCode()).append("</UNITCODE>")
                    .append("<APPLYNO>").append(queryPolicyReq.getApplyNo()).append("</APPLYNO>")
                    .append("<APPLYENDORSENO>").append(queryPolicyReq.getApplyEndOrseNo() == null ? "" : queryPolicyReq.getApplyEndOrseNo()).append("</APPLYENDORSENO>")
                    .append("</POLICY></DATA>")
                    .append("</ROOT>");

            approvalRequest.setPolicyInfo(sb.toString());
            logger.debug("queryPolicy approvalRequest={}", JSONObject.toJSONString(approvalRequest));
            reposne = iZrxCommonService.queryPolicyStatus(approvalRequest);
            SysMessage sysMessage = reposne.getSysMessage();
            if (sysMessage != null) {
                logger.debug("错误类型:" + sysMessage.getErrorType() + "");
                logger.debug("错误代码:" + sysMessage.getErrorCode() + "");
                logger.debug("错误信息:" + sysMessage.getErrorMsg() + "");
                if (!StringUtil.isEmpty(sysMessage.getErrorCode())) {
                    throw new MobileStationBizException(sysMessage.getErrorMsg());
                }
            }
            if (null != reposne.getPolicyInfo()) {
                Document document = DocumentHelper.parseText(reposne.getPolicyInfo());
                Element root = document.getRootElement();
                Element result = null;
                Iterator e = null;
                for (e = root.element("DATE").element("P_AUDIT_WORK").elementIterator(); e.hasNext(); ) {
                    result = (Element) e.next();
                    if (result.getName().equals("FILE_EPOLICY")) {
                        queryPolicyRes.setFileEpolicy(result.getTextTrim());
                    }
                    if (result.getName().equals("STATUS")) {
                        queryPolicyRes.setStatus(result.getTextTrim());
                    }
                    if (result.getName().equals("STATUS_EPOLICY")) {
                        queryPolicyRes.setStatusEpolicy(result.getTextTrim());
                    }
                    if (result.getName().equals("P_AUDIT_WORK__POLICYNO")) {
                        queryPolicyRes.setPolicyno(result.getTextTrim());
                    }

                }
            }
        } catch (WssException_Exception e) {
            e.printStackTrace();
            throw new MobileStationBizException("调用太保接口失败!");
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new MobileStationBizException("调用太保接口失败!");
        }
        if (!StringUtil.isEmpty(queryPolicyReq.getBusiBookNo())) {
            BookingForm bf = bookingFormDaoEx.getBookingFormByBusiNo(queryPolicyReq.getBusiBookNo());
            if (null != bf) {
                if (queryPolicyRes != null && !StringUtil.isEmpty(queryPolicyRes.getStatus())) {
                    bf.setPremiumStatus(Integer.parseInt(queryPolicyRes.getStatus()));
                    bf.setPremiumApprovalStatus(Integer.parseInt(queryPolicyRes.getStatus()));
                    if (!StringUtils.isEmpty(queryPolicyRes.getPolicyno())) {
                        bf.setPolicyno(queryPolicyRes.getPolicyno());
                    }
                    bookingFormDao.updateByPrimaryKeySelective(bf);
                }
            }
        }
        logger.debug("Status={},PolicyRes={}", queryPolicyRes.getStatus() == null ? "null" : queryPolicyRes.getStatus(), queryPolicyRes.getPolicyno() == null ? "null" : queryPolicyRes.getPolicyno());
        return queryPolicyRes;
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
     * 退保
     *
     * @param busiNo
     */
    @Override
    public void withdrawInsure(String busiNo) throws MobileStationBizException {
        if (!org.apache.commons.lang3.StringUtils.isEmpty(busiNo)) {
            BookingForm bf = bookingFormDaoEx.getBookingFormByBusiNo(busiNo);
            if (bf != null && (bf.getBusiCtrl().equals(CustomerDefine.ORDER_STATUS_CANCLE) || CustomerDefine.ORDER_STATUS_DELETE == bf.getBusiCtrl()) && bf.getNeedInsure() && bf.getPremiumStatus() != null && bf.getPremiumStatus() == CustomerDefine.PREMIUM_STATUS_TAKE_EFFECT) {

                EndorseRequest endorseRequest = new EndorseRequest();
                LoginUser userInfo = new LoginUser();
                try {
                    userInfo.setUserName(URLEncoder.encode(user, ENCODING));
                    userInfo.setPassword(URLEncoder.encode(passwd, ENCODING));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //承保产品代号
                ApprovalProduct productInfo = new ApprovalProduct();
                productInfo.setClassesCode(CustomerDefine.CLASSTYPE);
                EndorseResponse2 reposne = null;

                try {
                    //包装发送信息
                    endorseRequest.setUserInfo(userInfo);
                    endorseRequest.setProductInfo(productInfo);
                    endorseRequest.setCheckCode("1");
                    EndorseType endorseType = new EndorseType();
                    endorseType.setTypeCode(CustomerDefine.WITHDRAW_INSURE);
                    endorseRequest.setEndorseType(endorseType);
                    StringBuffer sb = new StringBuffer("");
                    sb.append("<?xml version ='1.0' encoding='utf-8'?>")
                            .append("<FREIGHTCPIC><DATAS><DATA>")
                            .append("<ApplyId>").append(bf.getBusiBookNo()).append("0").append("</ApplyId>")
                            .append("<UNITCODE>").append(bf.getUnitcode()).append("</UNITCODE>")
                            .append("<POLICYNO>").append(bf.getPolicyno() == null ? "" : bf.getPolicyno()).append("</POLICYNO>")
                            .append("<CLASSTYPE>1</CLASSTYPE>")
                            .append("<CLASSCODE>").append(CustomerDefine.CLASSTYPE).append("</CLASSCODE>")
                            .append("<CANCELENDORSE><ENDORSETEXT>").append(CustomerDefine.ENDORSE_RESON).append("</ENDORSETEXT></CANCELENDORSE>")
                            .append("</DATA></DATAS>")
                            .append("</FREIGHTCPIC>");

                    endorseRequest.setEndorsementInfo(sb.toString());
                    logger.debug("withdrawInsure endorseRequest={}", JSONObject.toJSONString(endorseRequest));
                    reposne = iZrxCommonService.endorse(endorseRequest);
                    SysMessage sysMessage = reposne.getSysMessage();
                    if (sysMessage != null) {
                        logger.debug("错误类型:" + sysMessage.getErrorType() + "");
                        logger.debug("错误代码:" + sysMessage.getErrorCode() + "");
                        logger.debug("错误信息:" + sysMessage.getErrorMsg() + "");
                        if (!StringUtil.isEmpty(sysMessage.getErrorCode())) {
                            throw new MobileStationBizException(sysMessage.getErrorMsg());
                        }
                    }
                    if (null != reposne.getEndorsementInfo()) {
                        Document document = DocumentHelper.parseText(reposne.getEndorsementInfo());
                        Element root = document.getRootElement();
                        Element result = null;
                        Iterator e = null;
                        for (e = root.element("RESULT").elementIterator(); e.hasNext(); ) {
                            result = (Element) e.next();
                            if (result.getName().equals("STATUS")) {
                                bf.setPremiumApprovalStatus(Integer.parseInt(result.getTextTrim()));
                            }
                            if (result.getName().equals("APPLYENDORSENO")) {
                                bf.setApplyendorseno(result.getTextTrim());
                            }
                        }
                        bookingFormDao.updateByPrimaryKeySelective(bf);
                    }
                } catch (WssException_Exception e) {
                    e.printStackTrace();
                    throw new MobileStationBizException("调用太保接口失败!");
                } catch (DocumentException e) {
                    e.printStackTrace();
                    throw new MobileStationBizException("调用太保接口失败!");
                }
            }
        }
    }

    @Override
    public boolean orderPolicy(OrderPolicyBean req) throws MobileStationBizException {
        boolean res = true;
        ApprovalRequest approvalRequest = new ApprovalRequest();
        LoginUser userInfo = new LoginUser();
        try {
            userInfo.setUserName(URLEncoder.encode(user, ENCODING));
            userInfo.setPassword(URLEncoder.encode(passwd, ENCODING));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusibookno());
        if (bookingForm == null)
            throw new MobileStationBizException("订单不存在，请核查信息");
        bookingForm.setNeedInsure(true);

        //承保产品代号
        ApprovalProduct productInfo = new ApprovalProduct();
        productInfo.setClassesCode(CustomerDefine.CLASSTYPE);
        ApprovalResponse2 reposne = null;
        try {
            //包装发送信息
            approvalRequest.setUserInfo(userInfo);
            approvalRequest.setProductInfo(productInfo);
            approvalRequest.setCheckCode("1");
            StringBuffer sb = new StringBuffer("");
            ComUserinfo comUserinfo = null;
            ComCustomer comCustomer = null;
            ComAccount comAccount = comAccountDaoEx.queryByAccount(req.getPolicyAcctusername());
            if (comAccount != null) {
                comUserinfo = comUserinfoDaoEx.queryByAcctId(comAccount.getId());
                if (null == comUserinfo) {
                    comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(comAccount.getId());
                }
            }
            Date now = new Date();
            //快递
            if (req.getItemCode() != null && (req.getItemCode().equals(QuoteItem.OTCKD.getItemCode()))) {
                sb.append("<?xml version ='1.0' encoding='utf-8'?>").append("<FREIGHTCPIC>")
                        .append("<HEADER> ")
                        .append("<ApplyId>").append(bookingForm.getBusiBookNo()).append("</ApplyId>")
                        .append("<CLASSESTYPE>").append(CustomerDefine.CLASSESTYPE).append("</CLASSESTYPE>")
                        .append("</HEADER>")
                        .append("<DATAS>")
                        .append("<DATA>")
                        .append("<APPLYNAME>").append(null != comUserinfo ? comUserinfo.getRealName() : comCustomer.getCustName()).append("</APPLYNAME>")
                        .append("<APPLYCARTTYPE>").append(null != comUserinfo ? "1170010001" : "2170010001").append("</APPLYCARTTYPE>")
                        .append("<APPLYCARDCODE>").append(null != comUserinfo ? comUserinfo.getIdentno() : comCustomer.getOrgCodeLilcenseNo()).append("</APPLYCARDCODE>")
                        .append("<INSURANTNAME>").append(bookingForm.getShipCustlinkMan()).append("</INSURANTNAME>")
                        .append("<INSURANTTEL>").append(bookingForm.getShipCustlinkTel()).append("</INSURANTTEL>")
                        .append("<CLASSTYPE>").append(CustomerDefine.CLASSTYPE).append("</CLASSTYPE>")
                        .append("<MARK>").append(bookingForm.getBusiBookNo()).append("</MARK>")
                        .append("<QUANTITY>").append(getQuantity(req)).append("</QUANTITY>")
                        .append("<ITEM>").append(getItem(req)).append("</ITEM>")
                        .append("<PACKCODE>").append(InsuranceUnit.getInsuranceCode(req.getGoodsInfos().get(0).getQtyUnit())).append("</PACKCODE>")
                        .append("<ITEMCODE>").append(getItemCode(req)).append("</ITEMCODE>")
                        .append("<KIND>").append(CustomerDefine.HIGHWAY).append("</KIND>")
                        .append("<KINDNAME>").append(CustomerDefine.PARCEL_TOOL).append("</KINDNAME>")
                        .append("<STARTPORT>").append(getCity((StringUtils.isEmpty(bookingForm.getCarriageReceiCounty()) || "0".equals(bookingForm.getCarriageReceiCounty())) ? bookingForm.getCarriageReceiCity() : bookingForm.getCarriageReceiCounty())).append("</STARTPORT>")
                        .append("<ENDPORT>").append(getCity((StringUtils.isEmpty(bookingForm.getCarriageDelivCounty()) || "0".equals(bookingForm.getCarriageDelivCounty())) ? bookingForm.getCarriageDelivCity() : bookingForm.getCarriageDelivCounty())).append("</ENDPORT>")
                        .append("<MAINITEMCODE>").append(CustomerDefine.MAINITEMCODE_KD).append("</MAINITEMCODE>")
                        .append("<ITEMCONTENT>邮包一切险保险条款</ITEMCONTENT>")
                        .append("<CURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</CURRENCYCODE>")
                        .append("<PRICECOND>").append(CustomerDefine.PRICECOND).append("</PRICECOND>")
                        .append("<AMOUNT>").append(req.getGoodsValue()).append("</AMOUNT>")
                        .append("<RATE>").append(CustomerDefine.RATE).append("</RATE>")
                        .append("<PREMIUM>").append(req.getPremiumValue()).append("</PREMIUM>")
                        .append("<POLICYNUM>1</POLICYNUM>")
                        .append("<CLAIMCURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</CLAIMCURRENCYCODE>")
                        .append("<CLAIMPAYPLACE>China</CLAIMPAYPLACE>")
                        .append("<FCURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</FCURRENCYCODE>")
                        .append("<EFFECTDATE>").append(sdf.format(now)).append("</EFFECTDATE>")
                        .append("<SAILDATE>").append(sdf.format(now)).append("</SAILDATE>")
                        .append("<FRANCHISE>本保单其他承保条件同协议；绝对免赔率</FRANCHISE>")
                        .append("<USERNO>").append(bookingForm.getBusiBookNo()).append("</USERNO>")
                        .append("<IFBILL>").append(CustomerDefine.NOTNEEDBILL).append("</IFBILL>")
                        .append("<PRINTTYPE>").append(CustomerDefine.PRINTTYPE).append("</PRINTTYPE>")
                        .append("</DATA>")
                        .append("</DATAS>")
                        .append("</FREIGHTCPIC>");
            } else {
                sb.append("<?xml version ='1.0' encoding='utf-8'?>").append("<FREIGHTCPIC>")
                        .append("<HEADER> ")
                        .append("<ApplyId>").append(bookingForm.getBusiBookNo()).append("</ApplyId>")
                        .append("<CLASSESTYPE>").append(CustomerDefine.CLASSESTYPE).append("</CLASSESTYPE>")
                        .append("</HEADER>")
                        .append("<DATAS>")
                        .append("<DATA>")
                        .append("<APPLYNAME>").append(null != comUserinfo ? comUserinfo.getRealName() : comCustomer.getCustName()).append("</APPLYNAME>")
                        .append("<APPLYCARTTYPE>").append(null != comUserinfo ? "1170010001" : "2170010001").append("</APPLYCARTTYPE>")
                        .append("<APPLYCARDCODE>").append(null != comUserinfo ? comUserinfo.getIdentno() : comCustomer.getOrgCodeLilcenseNo()).append("</APPLYCARDCODE>")
                        .append("<INSURANTNAME>").append(bookingForm.getShipCustlinkMan()).append("</INSURANTNAME>")
                        .append("<INSURANTTEL>").append(bookingForm.getShipCustlinkTel()).append("</INSURANTTEL>")
                        .append("<CLASSTYPE>").append(CustomerDefine.CLASSTYPE).append("</CLASSTYPE>")
                        .append("<MARK>").append(bookingForm.getBusiBookNo()).append("</MARK>")
                        .append("<QUANTITY>").append(getQuantity(req)).append("</QUANTITY>")
                        .append("<ITEM>").append(getItem(req)).append("</ITEM>")
                        .append("<PACKCODE>").append(InsuranceUnit.getInsuranceCode(req.getGoodsInfos().get(0).getQtyUnit())).append("</PACKCODE>")
                        .append("<ITEMCODE>").append(getItemCode(req)).append("</ITEMCODE>")
                        .append("<KIND>").append(CustomerDefine.HIGHWAY).append("</KIND>")
                        .append("<KINDNAME>").append(CustomerDefine.HIGHWAY_TOOL).append("</KINDNAME>")
                        .append("<STARTPORT>").append(getCity((StringUtils.isEmpty(bookingForm.getCarriageReceiCounty()) || "0".equals(bookingForm.getCarriageReceiCounty())) ? bookingForm.getCarriageReceiCity() : bookingForm.getCarriageReceiCounty())).append("</STARTPORT>")
                        .append("<ENDPORT>").append(getCity((StringUtils.isEmpty(bookingForm.getCarriageDelivCounty()) || "0".equals(bookingForm.getCarriageDelivCounty())) ? bookingForm.getCarriageDelivCity() : bookingForm.getCarriageDelivCounty())).append("</ENDPORT>")
                        .append("<MAINITEMCODE>").append(CustomerDefine.MAINITEMCODE_YS).append("</MAINITEMCODE>")
                        .append("<ITEMCONTENT>公路货物运输保险条款</ITEMCONTENT>")
                        .append("<CURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</CURRENCYCODE>")
                        .append("<PRICECOND>").append(CustomerDefine.PRICECOND).append("</PRICECOND>")
                        .append("<AMOUNT>").append(req.getGoodsValue()).append("</AMOUNT>")
                        .append("<RATE>").append(CustomerDefine.RATE).append("</RATE>")
                        .append("<PREMIUM>").append(req.getPremiumValue()).append("</PREMIUM>")
                        .append("<POLICYNUM>1</POLICYNUM>")
                        .append("<CLAIMCURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</CLAIMCURRENCYCODE>")
                        .append("<CLAIMPAYPLACE>China</CLAIMPAYPLACE>")
                        .append("<FCURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</FCURRENCYCODE>")
                        .append("<EFFECTDATE>").append(sdf.format(now)).append("</EFFECTDATE>")
                        .append("<SAILDATE>").append(sdf.format(now)).append("</SAILDATE>")
                        .append("<FRANCHISE>本保单其他承保条件同协议；绝对免赔率</FRANCHISE>")
                        .append("<USERNO>").append(bookingForm.getBusiBookNo()).append("</USERNO>")
                        .append("<IFBILL>").append(CustomerDefine.NOTNEEDBILL).append("</IFBILL>")
                        .append("<PRINTTYPE>").append(CustomerDefine.PRINTTYPE).append("</PRINTTYPE>")
                        .append("</DATA>")
                        .append("</DATAS>")
                        .append("</FREIGHTCPIC>");
            }
            approvalRequest.setPolicyInfo(sb.toString());
            logger.debug("approvalRequest={}", JSONObject.toJSONString(approvalRequest));
            reposne = iZrxCommonService.approval(approvalRequest);
            SysMessage sysMessage = reposne.getSysMessage();
            if (sysMessage != null) {
                logger.debug("错误类型:" + sysMessage.getErrorType() + "");
                logger.debug("错误代码:" + sysMessage.getErrorCode() + "");
                logger.debug("错误信息:" + sysMessage.getErrorMsg() + "");
                if (!StringUtil.isEmpty(sysMessage.getErrorCode())) {
                    bookingForm.setPremiumStatus(-1);//保单状态
                    bookingForm.setBusiCtrl(-4); //冻结
                    res = false;
                }
            }
            if (null != reposne.getPolicyInfo()) {
                Document document = DocumentHelper.parseText(reposne.getPolicyInfo());
                Element root = document.getRootElement();
                Element result = null;
                Iterator e = null;
                for (e = root.element("RESULT").elementIterator(); e.hasNext(); ) {

                    result = (Element) e.next();
                    if (result.getName().equals("UNITCODE")) {//投保分公司
                        bookingForm.setUnitcode(result.getTextTrim());
                    }
                    if (result.getName().equals("APPLYNO")) {//投保单号
                        bookingForm.setApplyno(result.getTextTrim());
                    }
                    if (result.getName().equals("POLICYNO")) {//保单号
                        bookingForm.setPolicyno(result.getTextTrim());
                    }
                    if (result.getName().equals("STATUS")) {//保单状态
                        bookingForm.setPremiumStatus(Integer.parseInt(result.getTextTrim()));
                    }

                    if (result.getName().equals("RMB_PREMIUM")) {//保险费用
                        bookingForm.setPremiumValue(new BigDecimal(result.getTextTrim()));
                    }

                }

            }
        } catch (WssException_Exception e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        if (bookingForm.getNeedInsure()) {
            bookingFormDao.updateByPrimaryKey(bookingForm);
        }
        return res;
    }

    @Override
    public AppBaseResult updateOrderPolicy(UpdateOrderPolicyReq req) throws MobileStationBizException {
        AppBaseResult res = new AppBaseResult();
        List<MobileBookingForm> mobileBookingForms = mobileBookingFormDaoEx.queryOrderListByConditions(
                req.getBusibookno(), req.getAcctUsername(), req.getCompanyAccountId(), null);
        BookingForm bookingForm = null;
        if (mobileBookingForms.size() == 0)
            throw new MobileStationBizException("MobileStation订单不存在");
//        if (!(mobileBookingForm.getProductType().equals(MobileStationDefine.PRODUCT_TYPE_ITCKD)
//                && mobileBookingForm.getOrderType() == 1)) {
        bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusibookno());
        if (bookingForm == null) {
            throw new MobileStationBizException("订单不存在");
        }
//        }

        updateOrderPayType(req, bookingForm);

        if (req.isPolicy()) {
            if (req.getGoodsInfos() == null || req.getGoodsInfos().size() == 0) {
                throw new MobileStationBizException("货物数据参数不能为空");
            }
            GoodsTypeBean goodsTypeBean = new GoodsTypeBean();
            StringBuffer buffer = new StringBuffer();
            final Iterator<GoodsInfo> iterator = req.getGoodsInfos().iterator();
            while (iterator.hasNext()) {
                buffer.append(iterator.next().getGoodsType());
                buffer.append(",");
            }
            final String goodsTypes = buffer.substring(0, buffer.length() - 1);
            final boolean insured = checkInsured(goodsTypeBean.setGoodsTypes(goodsTypes).setItemCode(req.getItemCode()));
            if (!insured) {
                res.setRetCode(SystemDefine.FAILURE);
                res.setRetMsg("货物所属类型不能进行投保");
                return res;
            } else {
                updateOrderPolicy(req, bookingForm);
            }
        } else {
            //更新bookingform投保状态
            if (bookingForm != null) {
                bookingForm.setGoodsValue(req.getGoodsValue());
                bookingForm.setPremiumValue(req.getPremiumValue());
                bookingForm.setNeedInsure(req.isPolicy());
                bookingFormDao.updateByPrimaryKeySelective(bookingForm);
            }
            //更新所有订单的投保状态
            mobileBookingFormDaoEx.updatePolicyByBookBusiNo(bookingForm.getBusiBookNo(), req.isPolicy(), req.getPremiumValue(), req.getGoodsValue(), null);
        }
        return res;
    }

    @Override
    public AppBaseResult orderOnePrice(QuoteOnePriceReq quoteInfoReq) throws MobileStationBizException {
        AppBaseResult result = new AppBaseResult();
        result.setRetCode(SystemDefine.FAILURE);
        QuoteBean quoteBean = new QuoteBean();
        quoteBean.setComQuote(quoteInfoReq.getComQuote());
        ResultBean res = comQuoteService.saveMobileYkjQuote(quoteBean);

        if (res.isState()) {
            ComQuote comQuoteBean = (ComQuote) res.getData();
            MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(quoteInfoReq.getBusibookno(), null, null, null, null);
            if (mobileBookingForm == null)
                throw new MobileStationBizException("MobileStation订单不存在");
            if (!(mobileBookingForm.getProductType().equals(MobileStationDefine.PRODUCT_TYPE_ITCKD)
                    && mobileBookingForm.getOrderType() == 1)) {
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(quoteInfoReq.getBusibookno());
                if (bookingForm == null) {
                    throw new MobileStationBizException("订单不存在");
                }
                bookingForm.setPredictValue(comQuoteBean.getUnitPrice());
                if (!StringUtil.isEmpty(comQuoteBean.getCurrencyCode())) {
                    bookingForm.setPredictCurr(comQuoteBean.getCurrencyCode());
                }
                bookingForm.setDocno(comQuoteBean.getQuoteNo());
                bookingForm.setQuotedType(QuoteTypeDefine.AP.getValue());   //按件报价
                bookingFormDao.updateByPrimaryKeySelective(bookingForm);
            }
            mobileBookingForm.setPredictValue(comQuoteBean.getUnitPrice());
            if (!StringUtil.isEmpty(comQuoteBean.getCurrencyCode())) {
                mobileBookingForm.setPredictCurr(comQuoteBean.getCurrencyCode());
            }
            mobileBookingForm.setComQuoteId(comQuoteBean.getQuoteNo());
            mobileBookingForm.setQuotedType(QuoteTypeDefine.AP.getValue());
            mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
            result.setRetCode(SystemDefine.SUCCESS);
        }
        return result;
    }

    @Override
    public void setQuote2MobileBooingForm(String busiBookNo) throws MobileStationBizException {
        List<MobileBookingForm> mobileBookingForms = mobileBookingFormDaoEx.queryByConditions(busiBookNo, null, null);
        if (mobileBookingForms.size() > 0) {
            for (MobileBookingForm mobileBookingForm : mobileBookingForms) {
                PlatformQuote platformQuote = expressService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG, mobileBookingForm.getBusiBookNo(), mobileBookingForm.getStartLocus(), mobileBookingForm.getDestnLocus(), mobileBookingForm.getRoleId());
                if (platformQuote != null) {
                    mobileBookingForm.setPredictValue(platformQuote.getPrice());
                    mobileBookingForm.setPredictCurr(platformQuote.getCurrencyCode());
                    mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
                }
            }
        }
    }


    @Transactional
    private void updateOrderPolicy(UpdateOrderPolicyReq req, BookingForm bookingForm) {
        if (bookingForm != null) {
            bookingForm.setGoodsValue(req.getGoodsValue());
            bookingForm.setPremiumValue(req.getPremiumValue());
            bookingForm.setNeedInsure(req.isPolicy());
            bookingFormDao.updateByPrimaryKeySelective(bookingForm);
        }

        mobileBookingFormDaoEx.updatePolicyByBookBusiNo(bookingForm.getBusiBookNo(), req.isPolicy(), req.getPremiumValue(), req.getGoodsValue(), null);
//        mobileBookingForm.setGoodsValue(req.getGoodsValue());
//        mobileBookingForm.setPremiumValue(req.getPremiumValue());
//        mobileBookingForm.setNeedInsure(req.isPolicy());
//        mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
    }

    @Transactional
    private void updateOrderPayType(UpdateOrderPolicyReq req, BookingForm bookingForm) {
        if (bookingForm != null) {
            bookingForm.setPayType(req.getPaymentType());
            if (req.getGoodsValue() != null) {
                bookingForm.setGoodsValue(req.getGoodsValue());
            }
            bookingFormDao.updateByPrimaryKeySelective(bookingForm);
        }
//        mobileBookingForm.setPayType(req.getPaymentType());
//        mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
        mobileBookingFormDaoEx.updatePolicyByBookBusiNo(bookingForm.getBusiBookNo(), null, null, null, req.getPaymentType());
    }

    @Override
    public QueryPolicyRes queryPolicyForAndroid(QueryPolicyReq queryPolicyReq) throws MobileStationBizException {
        QueryPolicyRes queryPolicyRes = new QueryPolicyRes();
        ApprovalRequest approvalRequest = new ApprovalRequest();
        LoginUser userInfo = new LoginUser();
        try {
            userInfo.setUserName(URLEncoder.encode(user, ENCODING));
            userInfo.setPassword(URLEncoder.encode(passwd, ENCODING));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //承保产品代号
        ApprovalProduct productInfo = new ApprovalProduct();
        productInfo.setClassesCode(CustomerDefine.CLASSTYPE);
        ApprovalResponse2 reposne = null;

        try {
            //包装发送信息
            approvalRequest.setUserInfo(userInfo);
            approvalRequest.setProductInfo(productInfo);
            approvalRequest.setCheckCode("1");
            StringBuffer sb = new StringBuffer("");
            sb.append("<?xml version ='1.0' encoding='utf-8'?>")
                    .append("<ROOT><CONFIG><TYPE>IN</TYPE><WORKTYPE>2</WORKTYPE></CONFIG><DATA><POLICY>")
                    .append("<UNITCODE>").append(queryPolicyReq.getUnitCode()).append("</UNITCODE>")
                    .append("<APPLYNO>").append(queryPolicyReq.getApplyNo()).append("</APPLYNO>")
                    .append("<APPLYENDORSENO>").append(queryPolicyReq.getApplyEndOrseNo() == null ? "" : queryPolicyReq.getApplyEndOrseNo()).append("</APPLYENDORSENO>")
                    .append("</POLICY></DATA>")
                    .append("</ROOT>");

            approvalRequest.setPolicyInfo(sb.toString());
            logger.debug("queryPolicy approvalRequest={}", JSONObject.toJSONString(approvalRequest));
            reposne = iZrxCommonService.queryPolicyStatus(approvalRequest);
            SysMessage sysMessage = reposne.getSysMessage();
            if (sysMessage != null) {
                logger.debug("错误类型:" + sysMessage.getErrorType() + "");
                logger.debug("错误代码:" + sysMessage.getErrorCode() + "");
                logger.debug("错误信息:" + sysMessage.getErrorMsg() + "");
                if (!StringUtil.isEmpty(sysMessage.getErrorCode())) {
                    throw new MobileStationBizException(sysMessage.getErrorMsg());
                }
            }
            if (null != reposne.getPolicyInfo()) {
                Document document = DocumentHelper.parseText(reposne.getPolicyInfo());
                Element root = document.getRootElement();
                Element result = null;
                Iterator e = null;
                for (e = root.element("DATE").element("P_AUDIT_WORK").elementIterator(); e.hasNext(); ) {
                    result = (Element) e.next();
                    if (result.getName().equals("FILE_EPOLICY")) {
                        queryPolicyRes.setFileEpolicy(result.getTextTrim());
                    }
                    if (result.getName().equals("STATUS")) {
                        queryPolicyRes.setStatus(result.getTextTrim());
                    }
                    if (result.getName().equals("STATUS_EPOLICY")) {
                        queryPolicyRes.setStatusEpolicy(result.getTextTrim());
                    }
                    if (result.getName().equals("P_AUDIT_WORK__POLICYNO")) {
                        queryPolicyRes.setPolicyno(result.getTextTrim());
                    }

                }
            }
        } catch (WssException_Exception e) {
            e.printStackTrace();
            throw new MobileStationBizException("调用太保接口失败!");
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new MobileStationBizException("调用太保接口失败!");
        }
        if (!StringUtil.isEmpty(queryPolicyReq.getBusiBookNo())) {
            BookingForm bf = bookingFormDaoEx.getBookingFormByBusiNo(queryPolicyReq.getBusiBookNo());
            if (null != bf) {
                if (queryPolicyRes != null && !StringUtil.isEmpty(queryPolicyRes.getStatus())) {
                    bf.setPremiumStatus(Integer.parseInt(queryPolicyRes.getStatus()));
                    bf.setPremiumApprovalStatus(Integer.parseInt(queryPolicyRes.getStatus()));
                    if (!StringUtils.isEmpty(queryPolicyRes.getPolicyno())) {
                        bf.setPolicyno(queryPolicyRes.getPolicyno());
                    }
                    bookingFormDao.updateByPrimaryKeySelective(bf);
                }
            }
        }
        try {
            if (!StringUtil.isEmpty(queryPolicyRes.getFileEpolicy())) {
                PDDocument pdf = PDDocument.load(Base64Util.decode(queryPolicyRes.getFileEpolicy()));
                PDFRenderer pdfRenderer = new PDFRenderer(pdf);
                PDPageTree pageTree = pdf.getPages();
                PDPage page1 = pageTree.get(0);
                float width = page1.getCropBox().getWidth();
                float scale = 1.0f;
                if (width > 720) {
                    scale = 720 / width;
                }
                BufferedImage bim = pdfRenderer.renderImage(0, scale, ImageType.RGB);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(bim, "gif", out);
                byte[] lens = out.toByteArray();
                queryPolicyRes.setFileEpolicy(Base64Util.encode(lens));
                out.close();
                pdf.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryPolicyRes;
    }

    /**
     * 通知gps
     *
     * @param placeAnOrderRes
     * @param errOrder
     */
    @Override
    public void notifyGps(PlaceAnOrderRes placeAnOrderRes, Boolean errOrder) {
        BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(placeAnOrderRes.getOrderId());
        if (null != bookingForm) {
            GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
            if (StringUtil.isEmpty(bookingForm.getRevUserId())) {
                if (errOrder == null || !errOrder) {
                    broadcastOrder(bookingForm);
                }
                giOrderTraceResynced.setIsToAccept(true);
            } else {
                giOrderTraceResynced.setIsToAccept(false);
            }

            List<String> allBusNo = new ArrayList<>();
            allBusNo.add(bookingForm.getBusiBookNo());
            giOrderTraceResynced.setAllBusiNo(allBusNo);
            giOrderTraceResynced.setProductType(placeAnOrderRes.getProductType());
            if (errOrder != null && errOrder) {
                giOrderTraceResynced.setAction(MobileStationDefine.Action_ErrorPosition);
            } else {
                giOrderTraceResynced.setAction(MobileStationDefine.Action_PopOrdered);
            }
            giOrderTraceResynced.setTsAct(new Date());
            if (bookingForm.getCreateCompanyId() != null) {
                ComAccount companyAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
                if (companyAccount != null) {
                    giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
                } else {
                    giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
                }
            } else {
                giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
            }
            giOrderTraceResynced.setLoginCode(bookingForm.getCreateUser());
            if (placeAnOrderRes.getRoleId() != null) {
                giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(placeAnOrderRes.getRoleId()));
            }
            logger.info("notifyGps giOrderTraceResynced ={}", JSONArray.toJSONString(giOrderTraceResynced));
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
            //如果是客户指派快递员，强制接单
//            if (placeAnOrderRes.getForceAccept() != null && placeAnOrderRes.getForceAccept() == 1) {
//                giOrderTraceResynced.setAction(MobileStationDefine.Action_AcceptOrder);
//                giOrderTraceResynced.setUserCode(bookingForm.getRevUser());
//                giOrderTraceResynced.setLoginCode(bookingForm.getRevUser());
//                giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode());
//                giOrderTraceResynced.setTsAct(new Date());
//                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
//            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder4KDZS(CancleOrderReq req) throws MobileStationBizException {
        logger.info("OTCKD--OTCZS取消订单校验参数开始");
        if (req == null) {
            throw new MobileStationBizException("请求为空");
        }
        if (req.getOrderId() == null) {
            throw new MobileStationBizException("订单id不存在");
        }
        BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(req.getOrderId());
        logger.info("OTCKD--OTCZS取消订单的bookingForm-{}", JSON.toJSONString(bookingForm));
        if (bookingForm == null) {
            throw new MobileStationBizException("订单不存在");
        }
        logger.info("OTCKD--OTCZS取消订单校验参数结束-succssful");
        /*1. 待接单取消*/
        if (bookingForm.getBusiCtrl() == 0) {
            //先更新BOOKING_FORM
            cancelOrde4DataSource(req, bookingForm);
            //如果是指派单则通知中层,如果是广播单则另外还要发送GPS消息删除
            cancelOrderSendGpsMsg(bookingForm);
            if (StringUtils.isEmpty(bookingForm.getRevUser())) {
                deleteOrderSendGpsMsg(bookingForm);
            }
        }
        /*2. 已接单再取消订单*/
        if (bookingForm.getBusiCtrl() == 1) {
            //如果是待结算0,则只是更新数据库BOOKING_FORM和MOBILE_BOOKING_FORM为-1
            if (bookingForm.getIsJs() == 0) {
                cancelOrde4DataSource(req, bookingForm);
            }
            //已结算1（可以发起支付） 已对账2 则更新数据表和无效结算接口
            else if (bookingForm.getIsJs() == 1 || bookingForm.getIsJs() == 2) {
                cancelOrde4DataSource(req, bookingForm);
                calcWebService.cancelCalcForPriceO(bookingForm.getBusiBookNo());
            }
            //已支付3
            else if (bookingForm.getIsJs() == 3) {
                throw new MobileStationBizException("订单已经支付,无法取消订单");
            }
            //取消订单通知GPS
            cancelOrderSendGpsMsg(bookingForm);
        }
        //4. 取消订单增加追踪日志
        cancelOrder4BillTrace(req, bookingForm);
        logger.info("OTCKD-OTCZS取消订单结束-successful");
    }

    /**
     * 已接单状态订单被取消时,数据库操作
     *
     * @param req         req
     * @param bookingForm bookingForm
     * @throws MobileStationBizException MobileStationBizException
     */
    private void cancelOrde4DataSource(CancleOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        logger.info("已接单状态订单被取消时,数据库操作订单号-{}", bookingForm.getBusiBookNo());
        bookingForm.setBusiCtrl(-1);
        bookingForm.setBusiAbort(req.getAcctUsername());
        bookingForm.setBusiAbortCaus(req.getReason());
        bookingForm.setBusiAbortDate(new Date());
        withdrawInsure(bookingForm.getBusiBookNo());
        bookingFormDao.updateByPrimaryKey(bookingForm);
        List<MobileBookingForm> mbfList = mobileBookingFormDaoEx.selectMobileOrderByBookBusiNo(req.getBusiBookNo());
        if (CollectionUtils.isNotEmpty(mbfList)) {
            for (MobileBookingForm m : mbfList) {
                m.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                mobileBookingFormDao.updateByPrimaryKey(m);
            }
        }
        logger.info("已接单状态订单被取消时,数据库操作结束successful!!");
    }

    /**
     * 用户取消订单记录追踪日志
     *
     * @param req         req
     * @param bookingForm bookingForm
     */
    private void cancelOrder4BillTrace(CancleOrderReq req, BookingForm bookingForm) {
        logger.info("用户取消订单记录追踪日志-开始");
        //1. 记录追踪日志
        ComWaybillTrace tmp = new ComWaybillTrace();
        tmp.setExecCode(WayBillStatusDefine.CANCLE_ORDER.getIntValue());
        tmp.setRemark(WayBillStatusDefine.CANCLE_ORDER.getName());
        tmp.setAcctUsername(req.getAcctUsername());
        ComAccount comAccount = comAccountService.queryAccountByAcctUsername(req.getAcctUsername());
        if (comAccount != null) {
            tmp.setRealName(comAccount.getRealName());
        } else {
            tmp.setRealName(req.getAcctUsername());
        }
        tmp.setBusiBookNo(bookingForm.getBusiBookNo());
        tmp.setGrade(0);
        tmp.setStaDate(new Date());
        tmp.setRoleId(1);
        comWaybillTraceDao.insert(tmp);
        //1. 记录追踪日志
        logger.info("用户取消订单记录追踪日志-结束-数据库中追踪的对象是-{}", JSON.toJSONString(tmp));
    }

    /**
     * 通知中层取消订单
     *
     * @param bookingForm bookingForm
     */
    private void cancelOrderSendGpsMsg(BookingForm bookingForm) {
        //1. 通知中层
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(bookingForm.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(bookingForm.getTransportType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
        giOrderTraceResynced.setTsAct(new Date());
        if (bookingForm.getCreateCompanyId() != null) {
            ComAccount companyAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
            if (companyAccount != null) {
                giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
            } else {
                giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
            }
        } else {
            giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
        }
        giOrderTraceResynced.setLoginCode(bookingForm.getCreateUser());
        giOrderTraceResynced.setRoleCode(SysAccountRole.NORMAL_PERSONAL.getRoleCode());
        logger.info("cancleOrder sendGpsLogMessage ={}", JSONArray.toJSONString(giOrderTraceResynced));
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);

    }

    /**
     * 通知GPS删除订单
     *
     * @param bookingForm bookingForm
     */
    private void deleteOrderSendGpsMsg(BookingForm bookingForm) {
        // 通知gps删除
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
    }

}
