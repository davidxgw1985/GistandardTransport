package com.gistandard.transport.order.module.mobilestation.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.order.bean.*;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.app.HubBatchResBean;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.bean.MobileScheduSubOrder;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.CustomerOrderService;
import com.gistandard.transport.order.module.customer.bean.GoodsInfo;
import com.gistandard.transport.order.module.customer.bean.OrderQueryRes;
import com.gistandard.transport.order.module.customer.bean.PlaceAnOrderRes;
import com.gistandard.transport.order.module.customer.bean.ReceiveCustomerOrderReq;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.expressOrder.*;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.BatchOperateReq;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.BatchOperateResult;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.OperateReqBean;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.OperateResBean;
import com.gistandard.transport.order.module.mobilestation.bean.userorder.ReceiveMsOrderReq;
import com.gistandard.transport.order.module.mobilestation.dao.MobileAcceptOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileUserOrderDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileAcceptOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileUserOrderService;
import com.gistandard.transport.order.utils.OrderUtils;
import com.gistandard.transport.order.webservice.client.merchant.order.DispatchDetail;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import com.gistandard.transport.order.webservice.client.merchant.order.RequestDetailResult;
import com.gistandard.transport.order.webservice.client.order.hub.*;
import com.gistandard.transport.order.webservice.client.order.transport.BroadcastOrderQueryParam;
import com.gistandard.transport.order.webservice.client.order.transport.OrderService;
import com.gistandard.transport.quote.system.common.bean.ComQuotePriceBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.quote.system.database.bean.CheckAssignOrderforbatchFailed;
import com.gistandard.transport.quote.system.database.services.ComQuoteService;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
import com.gistandard.transport.tools.util.DateUtil;
import com.gistandard.transport.tools.util.HeadAuthentication;
import com.gistandard.transport.tools.util.HttpClientUtil;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xgw
 * @ClassName MobileStationOrderServiceImpl
 * @Description
 * @Version 1.0
 * @Date 2015-08-17
 */
@Service
public class MobileAcceptOrderServiceImpl implements MobileAcceptOrderService {

    private static final Logger logger = LoggerFactory.getLogger(MobileAcceptOrderServiceImpl.class);
    @Autowired
    private ComAccountService comAccountService;
    @Autowired
    private ComGoodsTypeService comGoodsTypeService;
    @Autowired
    private ComProvinceService comProvinceService;
    @Autowired
    private ComCityService comCityService;
    @Autowired
    private ComCountyService comCountyService;
    @Autowired
    private ComCurrencyService comCurrencyService;
    @Autowired
    private ComUnitService comUnitService;
    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private ExpreessOrderWebService expreessOrderWebService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MobileMyOrderService mobileMyOrderService;
    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;
    @Autowired
    private ComQuoteService comQuoteService;
    @Autowired
    private MobileStationOrderDao mobileStationOrderDao;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private PushRoleDao pushRoleDao;
    @Autowired
    private ComCustomerDao comCustomerDao;
    @Autowired
    private MobileGoodsDtlDaoEx mobileGoodsDtlDaoEx;
    @Autowired
    private BookingFormDao bookingFormDao;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private MobileAcceptOrderDao mobileAcceptOrderDao;
    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;
    @Autowired
    private ComAccountRoleRelDaoEx accountRoleRelDaoEx;
    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;
    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;
    @Autowired
    private MobileSingleCenterDao mobileSingleCenterDao;
    @Autowired
    private MobileUserOrderDao mobileUserOrderDao;
    @Autowired
    private GpsLogService gpsLogService;
    @Autowired
    private MobileUserOrderService mobileUserOrderService;
    @Autowired
    private OrderUtils orderUtils;
    @Autowired
    private PnWebService pnWebService;
    @Autowired
    private GpsOrderService gpsOrderService;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private ComWaybillTraceDao comWaybillTraceDao;

    @Value("#{customerOrderIM.imPushUrl}")
    private String imPushUrl;

    /**
     * 接单-指派单订单列表查询 0：广播单,1：站点指派单,2：个人指派单
     *
     * @param acceptOrderListReq
     * @return
     */
    @Override
    public QueryOrderListRes queryOrderList(MobileAcceptOrderListReq acceptOrderListReq) throws MobileStationBizException {
        QueryOrderListRes baseResPageBean = new QueryOrderListRes(acceptOrderListReq);
        try {

            List<MobileAcceptOrderQueryListBean> orderListBeans = mobileAcceptOrderDao.queryOrderList(acceptOrderListReq);
            int recordCount = 0;
            List<MobileAcceptListBean> resultListBeans = new ArrayList<>();
            if (orderListBeans != null && orderListBeans.size() > 0) {
                //获取总记录条数
                recordCount = mobileAcceptOrderDao.getOrderListCount(acceptOrderListReq);
                Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                Map<String, ComCity> comCityMap = comCityService.queryForMap();
                Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
                Map<String, ComCurrency> comCurrencyMap = comCurrencyService.queryForMap();
                Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
                Map<String, ComGoodsType> goodsTypeMap = comGoodsTypeService.queryForMap();
                // 根据条件查询订单列表
                for (MobileAcceptOrderQueryListBean orderListBean : orderListBeans) {
                    MobileAcceptListBean resultBean = new MobileAcceptListBean();
                    PropertyUtils.copyProperties(resultBean, orderListBean);
                    // 设置币值
                    if (!StringUtil.isEmpty(orderListBean.getPredictCurr())
                            && comCurrencyMap.get(orderListBean.getPredictCurr()) != null) {
                        resultBean.setPredictCurr(comCurrencyMap.get(orderListBean.getPredictCurr()).getCurrencyCh());
                    }

                    // 根据省市区设置地址
                    String startAddress = "";
                    String destAddress = "";
                    if (!StringUtil.isEmpty(orderListBean.getStartProvide())
                            && comProvinceMap.get(orderListBean.getStartProvide()) != null) {
                        // 判断地址信息是否包含省
                        if (orderListBean.getStartAddress().indexOf(
                                comProvinceMap.get(orderListBean.getStartProvide()).getProvinceName()) == -1) {
                            startAddress += comProvinceMap.get(orderListBean.getStartProvide()).getProvinceName();
                            if (!StringUtil.isEmpty(orderListBean.getStartCity())
                                    && comCityMap.get(orderListBean.getStartCity()) != null) {
                                startAddress += comCityMap.get(orderListBean.getStartCity()).getName();
                            }
                            if (!StringUtil.isEmpty(orderListBean.getStartCounty())
                                    && comCountyMap.get(orderListBean.getStartCounty()) != null) {
                                startAddress += comCountyMap.get(orderListBean.getStartCounty()).getAreaName();
                            }
                        }
                    }
                    resultBean.setStartAddress(startAddress + orderListBean.getStartAddress());

                    if (!StringUtil.isEmpty(orderListBean.getDestProvide())
                            && comProvinceMap.get(orderListBean.getDestProvide()) != null) {
                        // 判断地址信息是否包含省
                        if (orderListBean.getDestAddress().indexOf(
                                comProvinceMap.get(orderListBean.getDestProvide()).getProvinceName()) == -1) {
                            destAddress += comProvinceMap.get(orderListBean.getDestProvide()).getProvinceName();
                            if (!StringUtil.isEmpty(orderListBean.getDestCity())
                                    && comCityMap.get(orderListBean.getDestCity()) != null) {
                                destAddress += comCityMap.get(orderListBean.getDestCity()).getName();
                            }
                            if (!StringUtil.isEmpty(orderListBean.getDestCounty())
                                    && comCountyMap.get(orderListBean.getDestCounty()) != null) {
                                destAddress += comCountyMap.get(orderListBean.getDestCounty()).getAreaName();
                            }
                        }
                    }

                    resultBean.setDestAddress(destAddress + orderListBean.getDestAddress());

                    // 设置描述信息
                    String description = "";
                    String weightUtil = "";

                    if (StringUtil.isEmpty(orderListBean.getScheducarno())) {
                        for (MobileGoodsInfo goodsInfo : orderListBean.getGoodsInfoList()) {
                            if (goodsInfo.getGoodsType() != null) {
                                String typeName = goodsInfo.getGoodsType();
                                if (null != goodsTypeMap.get(goodsInfo.getGoodsType())) {
                                    typeName = goodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh();
                                }
                                description += typeName + "-";
                            }
                            if (goodsInfo.getGoodsName() != null) {
                                description += goodsInfo.getGoodsName();
                            }
                            if (goodsInfo.getGoodsWeight() != null) {
                                if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit())
                                        && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                                    weightUtil = comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh();
                                }
                                description += "重量：" + goodsInfo.getGoodsWeight().setScale(2, BigDecimal.ROUND_HALF_UP)
                                        + weightUtil + " ";
                            }
                            if (goodsInfo.getGoodsLength() != null && goodsInfo.getGoodsWide() != null
                                    && goodsInfo.getGoodsHeight() != null) {
                                description += "体积：" + goodsInfo.getGoodsLength().setScale(0, BigDecimal.ROUND_HALF_UP)
                                        + "*" + goodsInfo.getGoodsWide().setScale(0, BigDecimal.ROUND_HALF_UP) + "*"
                                        + goodsInfo.getGoodsHeight().setScale(0, BigDecimal.ROUND_HALF_UP) + "立方厘米";
                            }
                            description += " ";
                        }
                    } else {
                        BigDecimal temp = new BigDecimal(0);
                        List<MobileScheduSubOrder> subOrderList = mobileMyOrderDao.selectMobileSubOrderByOrderId(orderListBean.getOrderId());
                        for (MobileGoodsInfo goodsInfo : orderListBean.getGoodsInfoList()) {
                            if (goodsInfo.getGoodsWeight() != null) {
                                if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit())
                                        && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                                    weightUtil = comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh();
                                }
                                temp = temp.add(goodsInfo.getGoodsWeight());
                            }

                            for (MobileScheduSubOrder subOrder : subOrderList) {
                                if (goodsInfo.getMobileScheduOrderId() != null && goodsInfo.getMobileScheduOrderId().equals(subOrder.getId())) {
                                    goodsInfo.setMobileScheduOrderNo(subOrder.getBusiBookNo());
                                    break;
                                }
                            }
                        }
                        description += "重量：" + temp.setScale(2, BigDecimal.ROUND_HALF_UP) + weightUtil + " ";
                    }
                    resultBean.setDescription(description);
                    //只有在咪站指派POP-M时才存在未实名的指派单
                    if (MobileStationDefine.POP.equals(orderListBean.getStartLocus()) && MobileStationDefine.M.equals(orderListBean.getDestnLocus())) {
                        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(orderListBean.getBusiBookNo());
                        if (bookingForm != null && bookingForm.getCreateUserId() != null) {
                            ComAccount comAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateUserId());
                            if (comAccount != null && !StringUtil.isEmpty(comAccount.getRealName())) {
                                resultBean.setIsRealName(true);
                            } else {
                                resultBean.setIsRealName(false);
                            }
                        }
                    } else {
                        resultBean.setIsRealName(true);
                    }
                    //设置是否能够拒绝订单，如果是咪站的O单指派单，不允许拒绝
                    if (acceptOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue() && orderListBean.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL) {
                        resultBean.setShowRefuseFlag(false);
                    } else {
                        resultBean.setShowRefuseFlag(true);
                    }
                    resultListBeans.add(resultBean);
                }
            }
            baseResPageBean.setRecordCount(recordCount);
            baseResPageBean.setData(resultListBeans);
        } catch (Exception e) {
            e.printStackTrace();
            baseResPageBean.setRecordCount(0);
            baseResPageBean.setRetCode(SystemDefine.FAILURE);
            baseResPageBean.setRetMsg(MobileStationMsgDefine.TY_MSG_ORDER_LIST_EXP);
        }
        baseResPageBean.setReqId(acceptOrderListReq.getReqId());
        return baseResPageBean;

    }

    /**
     * 接单-抢单列表查询
     *
     * @param grabOrderListReq
     * @return
     */
    @Override
    public QueryGrabOrderListRes queryGrabOrderList(MobileGrabOrderListReq grabOrderListReq) throws MobileStationBizException {
        QueryGrabOrderListRes baseResPageBean = new QueryGrabOrderListRes(grabOrderListReq);
        try {
            if (StringUtil.isEmpty(grabOrderListReq.getProvinceName())) {
                throw new MobileStationBizException("所在省不能为空！");
            }
            if (StringUtil.isEmpty(grabOrderListReq.getCityName())) {
                throw new MobileStationBizException("所在市不能为空！");
            }
            if (StringUtil.isEmpty(grabOrderListReq.getCountyName())) {
                throw new MobileStationBizException("所在区不能为空！");
            }
            int pageNum = grabOrderListReq.getStartRecord() / grabOrderListReq.getPageSize();
            List<String> roleList = new ArrayList<>();
            roleList.add(SysAccountRole.getRoleCode(grabOrderListReq.getRoleId()));
            //获取总记录条数
            long recordCount = pnWebService.getAllGiBizOrderBizModeCount(grabOrderListReq.getLongitude().doubleValue(), grabOrderListReq.getLatitude().doubleValue(),
                    grabOrderListReq.getProvinceName(), grabOrderListReq.getCityName(), grabOrderListReq.getCountyName(), JSON.toJSONString(roleList), grabOrderListReq.getCompanyBusinessMode());
            //获取所有的待抢单，并按照距离进行排序
            String gpsRes = pnWebService.getAllGiBizOrderBizModePaging(grabOrderListReq.getLongitude().doubleValue(), grabOrderListReq.getLatitude().doubleValue(),
                    grabOrderListReq.getProvinceName(), grabOrderListReq.getCityName(), grabOrderListReq.getCountyName(), JSON.toJSONString(roleList),
                    grabOrderListReq.getCompanyBusinessMode(), pageNum, grabOrderListReq.getPageSize());
            List<GiBizOrder> orderListBeans = JSON.parseArray(gpsRes, GiBizOrder.class);
            //根据分页参数，返回订单信息
            baseResPageBean.setRecordCount(Integer.parseInt(recordCount + ""));
            baseResPageBean.setData(orderListBeans);
        } catch (Exception e) {
            e.printStackTrace();
            baseResPageBean.setRecordCount(0);
            baseResPageBean.setRetCode(SystemDefine.FAILURE);
            baseResPageBean.setRetMsg(MobileStationMsgDefine.TY_MSG_ORDER_LIST_EXP);
        }
        baseResPageBean.setReqId(grabOrderListReq.getReqId());
        return baseResPageBean;

    }

    /**
     * 接单-订单详细查询
     *
     * @param orderDetailReq
     * @return
     */
    @Override
    public QueryOrderDetailResult queryOrderDetail(MobileStationOrderDetailReq orderDetailReq) throws MobileStationBizException {
        QueryOrderDetailResult baseResBean = new QueryOrderDetailResult(orderDetailReq);
        MobileStationOrderDetailBean detailBean = null;
        if (orderDetailReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST) {
            // 个人广播单
            ReceiveCustomerOrderReq req = new ReceiveCustomerOrderReq();
            req.setBookbusino(orderDetailReq.getBusiBookNo());
            req.setBrocast(true);
            logger.info("订单详细查询-个人广播单:{}", JSON.toJSONString(req));
            try {
                OrderQueryRes res = customerOrderService.queryBroadcastOrder(req);
                detailBean = new MobileStationOrderDetailBean();
                setDetailInfo(detailBean, res);
                detailBean.setOrderFrom(orderDetailReq.getOrderFrom());
                detailBean.setOrderType(1);// 默认取件单
                ComAccount account = comAccountService.queryAccountByAcctUsername(res.getCreateUser());
                if (null != account) {
                    detailBean.setCreateUserTel(account.getTelephone());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (orderDetailReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST) {
            // 运输广播单
            if (orderDetailReq.getTransportType().equals(MobileStationDefine.TRANSPORT_TYPE_LOGISTICS)) {
                // 物流
                BroadcastOrderQueryParam broadcastOrderQueryParam = new BroadcastOrderQueryParam();
                broadcastOrderQueryParam.setScheduCarNo(orderDetailReq.getScheducarno());
                logger.info("订单详细查询-hub广播单-物流单:{}", JSON.toJSONString(broadcastOrderQueryParam));
                String expreessResStr = orderService.queryBroadcastOrderList(broadcastOrderQueryParam);
                if (null != expreessResStr) {
                    BroadcastOrderBean orderBean = JSON.parseObject(expreessResStr, BroadcastOrderBean.class);
                    detailBean = setScheduOrderLogisticsDetail(orderBean);
                }
            } else {
                QueryOrderDetailRequest req = new QueryOrderDetailRequest();
                req.setSchduleCarNo(orderDetailReq.getBusiBookNo());
                req.setSchudeCarType(orderDetailReq.getTransportType());
                logger.info("订单详细查询-hub广播单-快递单:{}", JSON.toJSONString(req));
                String expreessResStr = expreessOrderWebService.queryExpreessOrderDetail(req);
                if (!StringUtil.isEmpty(expreessResStr)) {
                    BaseResBean expreessRes = JSON.parseObject(expreessResStr, BaseResBean.class);
                    if (expreessRes.getRetCode() != 0) {
                        logger.info("订单详细查询-hub广播单-快递单:查询订单详情失败{}", expreessRes.getRetMsg());
                        throw new MobileStationBizException(expreessRes.getRetMsg());
                    }
                    ExpreessMobileBookingForm expreessOrder = JSON.parseObject(expreessRes.getData().toString(),
                            ExpreessMobileBookingForm.class);
                    detailBean = setScheduOrderDetail(expreessOrder);
                } else {
                    logger.info("订单详细查询-hub广播单-快递单:HUB返回null");
                    throw new MobileStationBizException("查询订单详情失败！");
                }
            }
        } else if (orderDetailReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST) {
            detailBean = mobileMyOrderDao.queryOrderByBusiNoAndBusiCtrl(orderDetailReq.getBusiBookNo(),
                    MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
        } else {
            detailBean = mobileAcceptOrderDao.queryOrderDetail(orderDetailReq.getOrderId());
        }
        if (detailBean == null) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("查询订单详情失败！");
        } else {
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            Map<String, ComCurrency> comCurrencyMap = comCurrencyService.queryForMap();
            Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
            Map<String, ComGoodsType> goodsTypeMap = comGoodsTypeService.queryForMap();
            if (!StringUtil.isEmpty(detailBean.getComQuoteId()) && detailBean.getQuotedType() != null) {
                QuoteResultBean quoteResultBean = comQuoteService.getQuoteInfoByQuoteNo(detailBean.getComQuoteId());
                if (null != quoteResultBean) {
                    ComQuote comQuote = quoteResultBean.getComQuote();
                    List<ComQuotePriceBean> comQuotePriceList = quoteResultBean.getComQuotePriceList();
                    if (comQuote != null && detailBean.getQuotedType() != null) {
                        String quoteDesc = "计价方式：";
                        // 设置报价信息
                        if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_ZC) {
                            // 整车报价
                            if (comQuote.getTotalPrice() != null && comQuote.getCurrencyCode() != null
                                    && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                                quoteDesc += "整车 " + comQuote.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)
                                        + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh();
                            }
                        } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_LD) {
                            // 零担报价
                            if (comQuote.getHeavyUnitPrice() != null && comQuote.getCurrencyCode() != null
                                    && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                                quoteDesc += "重货 " + comQuote.getHeavyUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP)
                                        + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh() + "/千克 ";
                            }
                            if (comQuote.getLightUnitPrice() != null && comQuote.getCurrencyCode() != null
                                    && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                                quoteDesc += "轻货 " + comQuote.getLightUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP)
                                        + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh() + "/立方米 ";
                            }

                        } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_ZLFD) {
                            // 重量分段报价
                            if (comQuotePriceList != null) {
                                for (ComQuotePriceBean comQuotePrice : comQuotePriceList) {
                                    if (comQuotePrice.getPrevPointValue() == null) {
                                        quoteDesc += "0";
                                    } else {
                                        quoteDesc += comQuotePrice.getPrevPointValue().setScale(2,
                                                BigDecimal.ROUND_HALF_UP);
                                    }
                                    if (comQuotePrice.getPointValue() != null && comQuotePrice.getUnitPrice() != null
                                            && comUnitMap.get(comQuotePrice.getUnitCode()) != null
                                            && comQuote.getCurrencyCode() != null
                                            && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                                        quoteDesc += "-"
                                                + comQuotePrice.getPointValue().setScale(2, BigDecimal.ROUND_HALF_UP)
                                                + " " + comQuotePrice.getUnitPrice()
                                                + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh() + "/"
                                                + comUnitMap.get(comQuotePrice.getUnitCode()).getUnitCh() + " ";
                                    } else {
                                        quoteDesc = "计价方式：";
                                    }
                                }
                            }
                        } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_GL) {
                            // 公里报价
                            if (comQuote.getUnitPrice() != null) {
                                quoteDesc += "每公里 " + comQuote.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP)
                                        + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh();
                            }
                        } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_GLFD) {
                            // 公里分段报价
                            if (comQuotePriceList != null) {
                                for (ComQuotePriceBean comQuotePrice : comQuotePriceList) {
                                    if (comQuotePrice.getPrevPointValue() == null) {
                                        quoteDesc += "0";
                                    } else {
                                        quoteDesc += comQuotePrice.getPrevPointValue().setScale(2,
                                                BigDecimal.ROUND_HALF_UP);
                                    }
                                    if (comQuotePrice.getPointValue() != null && comQuotePrice.getUnitPrice() != null
                                            && comUnitMap.get(comQuotePrice.getUnitCode()) != null
                                            && comQuote.getCurrencyCode() != null
                                            && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                                        quoteDesc += "-"
                                                + comQuotePrice.getPointValue().setScale(2, BigDecimal.ROUND_HALF_UP)
                                                + " " + comQuotePrice.getUnitPrice()
                                                + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh() + "/"
                                                + comUnitMap.get(comQuotePrice.getUnitCode()).getUnitCh() + " ";
                                    } else {
                                        quoteDesc = "计价方式：";
                                    }
                                }
                            }
                        } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_MY) {
                            // 面议报价
                            quoteDesc += "面议";
                        } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_EXPRESS_PER) {
                            // 快递按票
                            if (comQuote.getUnitPrice() != null) {
                                quoteDesc += "每票 " + comQuote.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP)
                                        + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh();
                            }
                        } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_EXPRESS) {
                            // 快递首重续重
                            if (comQuote.getUnitPrice() != null) {
                                quoteDesc += "首重 "
                                        + comQuote.getFirstWeightUnit().setScale(2, BigDecimal.ROUND_HALF_UP) + "千克"
                                        + comQuote.getFirstWeight().setScale(2, BigDecimal.ROUND_HALF_UP)
                                        + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh();
                                quoteDesc += "续重 "
                                        + comQuote.getAdditionalWeightUnit().setScale(2, BigDecimal.ROUND_HALF_UP)
                                        + "千克" + comQuote.getAdditionalWeight().setScale(2, BigDecimal.ROUND_HALF_UP)
                                        + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh();
                            }
                        }
                        detailBean.setQuoteDesc(quoteDesc);
                    }
                }

            }
            // 设置币值
            if (!StringUtil.isEmpty(detailBean.getCurrency()) && comCurrencyMap.get(detailBean.getCurrency()) != null) {
                if (null != comCurrencyMap.get(detailBean.getCurrency())) {
                    detailBean.setCurrency(comCurrencyMap.get(detailBean.getCurrency()).getCurrencyCh());
                }
            }
            if (!StringUtil.isEmpty(detailBean.getPredictCurr())
                    && comCurrencyMap.get(detailBean.getPredictCurr()) != null) {
                if (null != comCurrencyMap.get(detailBean.getPredictCurr())) {
                    detailBean.setPredictCurr(comCurrencyMap.get(detailBean.getPredictCurr()).getCurrencyCh());
                }
            }
            // 根据省市区设置地址
            String startAddress = "";
            String destAddress = "";
            if (!StringUtil.isEmpty(detailBean.getStartProvide())
                    && comProvinceMap.get(detailBean.getStartProvide()) != null) {
                // 判断地址信息是否包含省
                if (detailBean.getStartAddress().indexOf(
                        comProvinceMap.get(detailBean.getStartProvide()).getProvinceName()) == -1) {
                    startAddress += comProvinceMap.get(detailBean.getStartProvide()).getProvinceName();
                    if (!StringUtil.isEmpty(detailBean.getStartCity())
                            && comCityMap.get(detailBean.getStartCity()) != null) {
                        startAddress += comCityMap.get(detailBean.getStartCity()).getName();
                    }
                    if (!StringUtil.isEmpty(detailBean.getStartCounty())
                            && comCountyMap.get(detailBean.getStartCounty()) != null) {
                        startAddress += comCountyMap.get(detailBean.getStartCounty()).getAreaName();
                    }
                }
            }
            detailBean.setStartAddress(startAddress + detailBean.getStartAddress());

            if (!StringUtil.isEmpty(detailBean.getDestProvide())
                    && comProvinceMap.get(detailBean.getDestProvide()) != null) {
                // 判断地址信息是否包含省
                if (detailBean.getDestAddress().indexOf(
                        comProvinceMap.get(detailBean.getDestProvide()).getProvinceName()) == -1) {
                    destAddress += comProvinceMap.get(detailBean.getDestProvide()).getProvinceName();
                    if (!StringUtil.isEmpty(detailBean.getDestCity())
                            && comCityMap.get(detailBean.getDestCity()) != null) {
                        destAddress += comCityMap.get(detailBean.getDestCity()).getName();
                    }
                    if (!StringUtil.isEmpty(detailBean.getDestCounty())
                            && comCountyMap.get(detailBean.getDestCounty()) != null) {
                        destAddress += comCountyMap.get(detailBean.getDestCounty()).getAreaName();
                    }
                }
            }
            detailBean.setDestAddress(destAddress + detailBean.getDestAddress());

            //设置子订单列表
            List<com.gistandard.transport.base.entity.bean.MobileScheduSubOrder> subOrderList = mobileMyOrderDao.selectMobileSubOrderByMobileId(detailBean.getOrderId());
            String subOrderListJsonStr = JSON.toJSONString(subOrderList);
            if (subOrderList != null && subOrderList.size() > 0) {
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrderList.get(0).getBusiBookNo());
                if (bookingForm != null) {
                    MobileScheduSubOrder tmp = subOrderList.get(0);
                    tmp.setShipCustAddr(bookingForm.getShipCustaDdr());
                    subOrderList.set(0, tmp);
                }

                detailBean.setSubOrderList(JSON.parseArray(subOrderListJsonStr, com.gistandard.transport.app.dubbo.order.bean.MobileScheduSubOrder.class));// 设置子派车单
            }
            // 设置单位名称
            if (detailBean.getGoodsInfoList() != null) {
                for (MobileGoodsInfo goodsInfo : detailBean.getGoodsInfoList()) {
                    if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit())
                            && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                        goodsInfo.setGoodsQtyUnitName(comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh());
                    }
                    if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit())
                            && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                        goodsInfo.setGoodsWeightUnitName(comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh());
                    }
                    if (!StringUtil.isEmpty(goodsInfo.getGoodsVolumeUnit())
                            && comUnitMap.get(goodsInfo.getGoodsVolumeUnit()) != null) {
                        goodsInfo.setGoodsVolumeUnitName(comUnitMap.get(goodsInfo.getGoodsVolumeUnit()).getUnitCh());
                    }
                    if (!StringUtil.isEmpty(goodsInfo.getGoodsType())) {
                        String typeName = goodsInfo.getGoodsType();
                        if (goodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                            typeName = goodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh();
                        }
                        goodsInfo.setGoodsTypeName(typeName);
                    }
                    if (subOrderList != null && subOrderList.size() > 0) {
                        for (MobileScheduSubOrder subOrder : subOrderList) {
                            if (goodsInfo.getMobileScheduOrderId() != null && goodsInfo.getMobileScheduOrderId().equals(subOrder.getId())) {
                                goodsInfo.setMobileScheduOrderNo(subOrder.getBusiBookNo());
                                break;
                            }
                        }
                    }
                }
            }

            if (orderDetailReq.getOrderFrom() != MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST
                    && orderDetailReq.getOrderFrom() != MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST) {
                if (!StringUtil.isEmpty(detailBean.getStartLocus())
                        && detailBean.getStartLocus().equals(MobileStationDefine.POP)) {
                    BookingForm bookingForm = null;
                    if (StringUtil.isEmpty(orderDetailReq.getScheducarno())) {
                        bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(orderDetailReq.getBusiBookNo());
                    } else {
                        if (subOrderList.size() > 0) {
                            bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrderList.get(0).getBusiBookNo());
                        }
                    }

                    if (null != bookingForm) {
                        detailBean.setAccesstime(bookingForm.getAccesstime());
                        ComAccount account = comAccountService.queryAccountByAcctUsername(bookingForm.getCreateUser());
                        if (null != account) {
                            detailBean.setCreateUserTel(account.getTelephone());
                        }
                    }
                }
            }
            //设置发货人、收货人账号信息
            if (!StringUtil.isEmpty(detailBean.getStartTel())) {
                detailBean.setStartUser(mobileUserOrderService.queryAccountByTelephone(detailBean.getStartTel()));
            }
            if (!StringUtil.isEmpty(detailBean.getDestTel())) {
                detailBean.setDestUser(mobileUserOrderService.queryAccountByTelephone(detailBean.getDestTel()));
            }
            baseResBean.setData(detailBean);
        }
        return baseResBean;
    }

    private MobileStationOrderDetailBean setScheduOrderLogisticsDetail(BroadcastOrderBean broadcastOrderBean) {
        MobileStationOrderDetailBean detailBean = new MobileStationOrderDetailBean();
        // 提取主单信息
        detailBean.setBusiBookNo(broadcastOrderBean.getBusiBookNo());
        if (!StringUtil.isEmpty(broadcastOrderBean.getBookingFormId())) {
            detailBean.setBookingFormId(Integer.valueOf(broadcastOrderBean.getBookingFormId()));
        }

        if (!StringUtil.isEmpty(broadcastOrderBean.getOrderType())) {
            detailBean.setOrderType(Integer.valueOf(broadcastOrderBean.getOrderType()));
        }
        if (!StringUtil.isEmpty(broadcastOrderBean.getEtaPopDate())) {
            detailBean.setEtaPopDate(broadcastOrderBean.getEtaPopDate());
        }
        // detailBean.setOrderFrom(broadcastOrderBean.getOrderFrom());

        detailBean.setStartAddress(broadcastOrderBean.getShipCustAddr());
        detailBean.setStartTel(broadcastOrderBean.getShipLinkTel());
        detailBean.setStartLinkMan(broadcastOrderBean.getShipLinkMan());
        detailBean.setStartCity(broadcastOrderBean.getShipCityName());
        detailBean.setStartCounty(broadcastOrderBean.getShipAreaName());
        detailBean.setStartProvide(broadcastOrderBean.getShipProvinceName());
        detailBean.setStartLatitude(broadcastOrderBean.getShipLatitude());
        detailBean.setStartLongitude(broadcastOrderBean.getShipLongitude());

        detailBean.setDestAddress(broadcastOrderBean.getCneeCustAddr());
        detailBean.setDestTel(broadcastOrderBean.getCneeLinkTel());
        detailBean.setDestLinkMan(broadcastOrderBean.getCneeLinkMan());
        detailBean.setDestCity(broadcastOrderBean.getCneeCity());
        detailBean.setDestCounty(broadcastOrderBean.getCneeCounty());
        detailBean.setDestProvide(broadcastOrderBean.getCneeProvince());
        if (!StringUtil.isEmpty(broadcastOrderBean.getCneeLatitude())) {
            detailBean.setDestLatitude(broadcastOrderBean.getCneeLatitude());
        }
        if (!StringUtil.isEmpty(broadcastOrderBean.getCneeLongitude())) {
            detailBean.setDestLongitude(broadcastOrderBean.getCneeLongitude());
        }
        if (!StringUtil.isEmpty(broadcastOrderBean.getBookingDate())) {
            detailBean.setBookingDate(broadcastOrderBean.getBookingDate());
        }
        detailBean.setProductType(broadcastOrderBean.getTransportType());
        detailBean.setStartPayment(broadcastOrderBean.getGoodsPayment());
        detailBean.setStartCurrency(broadcastOrderBean.getGoodsPaymentCurr());
        detailBean.setDestPayment(broadcastOrderBean.getDestPayment());
        detailBean.setDestCurrency(broadcastOrderBean.getDestPaymentCurr());
        if (!StringUtil.isEmpty(broadcastOrderBean.getGoodsValue())) {
            detailBean.setGoodsValue(new BigDecimal(broadcastOrderBean.getGoodsValue()));
        }

        if (!StringUtil.isEmpty(broadcastOrderBean.getNeedDnsure())) {
            detailBean.setNeedInsure(Boolean.valueOf(broadcastOrderBean.getNeedDnsure()));
        }
        detailBean.setIsJs(broadcastOrderBean.getIsJs());
        detailBean.setQuotedType(broadcastOrderBean.getQuotedType());
        detailBean.setComQuoteId(broadcastOrderBean.getDocno());
        // detailBean.setBusiCtrl(broadcastOrderBean.getBusiCtrl());
        if (!StringUtil.isEmpty(broadcastOrderBean.getPredictValue())) {
            detailBean.setPredictValue(new BigDecimal(broadcastOrderBean.getPredictValue()));
        }
        detailBean.setPredictCurr(broadcastOrderBean.getPredictCurr());
        detailBean.setPayType(broadcastOrderBean.getPayType());
        detailBean.setNarrate(broadcastOrderBean.getNarRate());
        detailBean.setPremiumValue(broadcastOrderBean.getPremiumValue());
        if (!StringUtil.isEmpty(broadcastOrderBean.getPremiumProportion())) {
            detailBean.setPremiumProportion(new BigDecimal(broadcastOrderBean.getPremiumProportion()));
        }
        // detailBean.setRevUserId(broadcastOrderBean.getRevUserId());
        // detailBean.setRevUser(broadcastOrderBean.getRevUser());
        // detailBean.setRevDate(broadcastOrderBean.getRevDate());
        detailBean.setStartLocus(broadcastOrderBean.getStartLocus());
        detailBean.setDestnLocus(broadcastOrderBean.getDestnLocus());
        // detailBean.setCreateDate(broadcastOrderBean.getCreateDate());
        detailBean.setCreateUser(broadcastOrderBean.getCreateUser());
        // detailBean.setCreateUserId(broadcastOrderBean.getCreateUserId());
        // 提取货物信息
        List<MobileGoodsInfo> goodsInfoList = new ArrayList<MobileGoodsInfo>();
        if (null != broadcastOrderBean.getMobileGoodsDetailList()) {
            for (MobileGoodsDetail good : broadcastOrderBean.getMobileGoodsDetailList()) {
                MobileGoodsInfo mobileGood = new MobileGoodsInfo();
                mobileGood.setGoodsType(good.getGoodsType());
                mobileGood.setGoodsName(good.getGoodsName());
                mobileGood.setGoodsQty(good.getGoodsQty());
                mobileGood.setGoodsQtyUnit(good.getGoodsQtyUnit());
                mobileGood.setGoodsLength(good.getGoodsLenght());
                mobileGood.setGoodsWide(good.getGoodsWide());
                mobileGood.setGoodsHeight(good.getGoodsHeight());
                mobileGood.setGoodsWeight(good.getGoodsWeight());
                mobileGood.setGoodsWeightUnit(good.getGoodsWeightUnit());
                mobileGood.setGoodsWeightUnitName(good.getGoodsWeightUnitName());
                mobileGood.setGoodsQtyUnitName(good.getGoodsQtyUnitName());
                mobileGood.setGoodsVolume(good.getGoodsVolume());
                if (StringUtil.isEmpty(good.getGoodsVolumeUnit())) {
                    mobileGood.setGoodsVolumeUnit("164");
                } else {
                    mobileGood.setGoodsVolumeUnit(good.getGoodsVolumeUnit());
                }
                goodsInfoList.add(mobileGood);
            }
        }
        detailBean.setGoodsInfoList(goodsInfoList);
        return detailBean;
    }

    private void setDetailInfo(MobileStationOrderDetailBean detailBean, OrderQueryRes res) {
        detailBean.setBusiBookNo(res.getBusiBookNo());
        detailBean.setBookingFormId(res.getId());
        detailBean.setTransportType(res.getTransType());
        detailBean.setPayType(res.getPaymentType());
        detailBean.setBusiCtrl(res.getStatus());
        detailBean.setNeedInsure(res.isInsured());
        detailBean.setComQuoteId(res.getQuoteNo());
        detailBean.setStartLongitude(res.getCarriageReceiLongitude());// 发货地址经度
        detailBean.setStartLatitude(res.getCarriageReceiLatitude());
        detailBean.setDestLongitude(res.getCarriageDelivLongitude());
        detailBean.setDestLatitude(res.getCarriageDelivLatitude());
        detailBean.setPayUser(res.getPayUser());
        detailBean.setPayUserRealName(res.getPayUserRealName());
        detailBean.setPayUserTelephone(res.getPayUserTelephone());
        detailBean.setOrderPrice(res.getPredictValue());
        /*
         * if (null != res.getAccessTime()) {
		 * detailBean.setEtaPopDate(DateUtil.parseDate(res.getAccessTime()));//
		 * 取件时间 }
		 */
        if (!StringUtil.isEmpty(res.getAccessTime())) {
            detailBean.setAccesstime(res.getAccessTime());// 取件时间
        }
        // 设置发货人信息
        detailBean.setStartProvide(res.getSenderAddr().getProvince());
        detailBean.setStartCity(res.getSenderAddr().getCity());
        if (!StringUtil.isEmpty(res.getSenderAddr().getCounty())) {
            detailBean.setStartCounty(res.getSenderAddr().getCounty());
        }
        detailBean.setStartAddress((StringUtil.isEmpty(res.getSenderAddr().getAddress()) ? "" : res.getSenderAddr().getAddress()) + (StringUtil.isEmpty(res.getSenderAddr().getDetailAddress()) ? "" : res.getSenderAddr().getDetailAddress()));
        detailBean.setStartLinkMan(res.getSenderAddr().getName());
        detailBean.setStartTel(res.getSenderAddr().getTelephone());
        // 设置收货人信息
        detailBean.setDestProvide(res.getReceiverAddr().getProvince());
        detailBean.setDestCity(res.getReceiverAddr().getCity());
        if (!StringUtil.isEmpty(res.getReceiverAddr().getCounty())) {
            detailBean.setDestCounty(res.getReceiverAddr().getCounty());
        }
        detailBean.setDestAddress((StringUtil.isEmpty(res.getReceiverAddr().getAddress()) ? "" : res.getReceiverAddr().getAddress()) + (StringUtil.isEmpty(res.getReceiverAddr().getDetailAddress()) ? "" : res.getReceiverAddr().getDetailAddress()));
        detailBean.setDestLinkMan(res.getReceiverAddr().getName());
        detailBean.setDestTel(res.getReceiverAddr().getTelephone());
        /*if (!StringUtil.isEmpty(res.getGoodsPayment())) {
            detailBean.setOrderPrice(res.getGoodsPayment());// 应收金额
        }*/
        if (!StringUtil.isEmpty(res.getGoodsPaymentCurrNa())) {
            detailBean.setCurrency(res.getGoodsPaymentCurrNa());
        }
        detailBean.setPredictCurr(res.getQuoteCurr());// 运费币值
        /*if (null != res.getBookingDate()) {
            detailBean.setBookingDate(DateUtil.parseDate(res.getAccessTime()));
		}*/
        if (res.getPremiumValue() != null) {
            detailBean.setPremiumValue(res.getPremiumValue());// 保险费用
        }
        if (null != res.getGoodsValue()) {
            detailBean.setGoodsValue(BigDecimal.valueOf(res.getGoodsValue()));
        }
        detailBean.setRevUser(res.getRevUser());
        detailBean.setIsJs(res.getIsJs());
        detailBean.setNarrate(res.getNarrate());
        detailBean.setStartLocus(MobileStationDefine.POP);
        detailBean.setDestnLocus(MobileStationDefine.POD);
        detailBean.setQuotedType(res.getQuotedType());
        if (res.getPredictValue() != null) {
            detailBean.setPredictValue(res.getPredictValue());
        }
        // 设置货物信息
        List<MobileGoodsInfo> mobileGoods = new ArrayList<>();
        if (res.getGoodsInfos() != null) {
            List<GoodsInfo> goods = res.getGoodsInfos();
            for (GoodsInfo good : goods) {
                MobileGoodsInfo mobileGood = new MobileGoodsInfo();
                mobileGood.setGoodsType(good.getGoodsType());
                mobileGood.setGoodsName(good.getGoodsName());
                mobileGood.setGoodsQty(good.getQty());
                mobileGood.setGoodsQtyUnit(good.getQtyUnit());
                if (null != good.getLength()) {
                    mobileGood.setGoodsLength(BigDecimal.valueOf(good.getLength()));
                }
                if (null != good.getWidth()) {
                    mobileGood.setGoodsWide(BigDecimal.valueOf(good.getWidth()));
                }
                if (null != good.getHeight()) {
                    mobileGood.setGoodsHeight(BigDecimal.valueOf(good.getHeight()));
                }

                if (null != good.getWeight()) {
                    mobileGood.setGoodsWeight(BigDecimal.valueOf(good.getWeight()));
                }
                if (null != good.getWeightUnit()) {
                    mobileGood.setGoodsWeightUnit(good.getWeightUnit());
                } else {
                    mobileGood.setGoodsWeightUnit("035");
                }
                mobileGood.setGoodsWeightUnitName(good.getWeightUnit());
                mobileGood.setGoodsQtyUnitName(good.getQtyUnit());
                mobileGood.setGoodsVolumeUnit("164");
                if (null != good.getWidth() && null != good.getHeight() && null != good.getLength()) {
                    mobileGood.setGoodsVolume(BigDecimal.valueOf(good.getHeight() * good.getWidth() * good.getLength()));
                }
                mobileGoods.add(mobileGood);
            }
        }
        detailBean.setGoodsInfoList(mobileGoods);
    }

    private MobileStationOrderDetailBean setScheduOrderDetail(ExpreessMobileBookingForm expreessOrder) {
        MobileStationOrderDetailBean detailBean = new MobileStationOrderDetailBean();
        // 提取主单信息
        detailBean.setBusiBookNo(expreessOrder.getBusiBookNo());
        detailBean.setBookingFormId(expreessOrder.getBookingFormId());

        detailBean.setOrderType(expreessOrder.getOrderType());
        detailBean.setEtaPopDate(expreessOrder.getEtaPopDate());
        detailBean.setOrderFrom(expreessOrder.getOrderFrom());

        detailBean.setStartAddress(expreessOrder.getShipCustAddr());
        detailBean.setStartTel(expreessOrder.getShipCustLinkTel());
        detailBean.setStartLinkMan(expreessOrder.getShipCustLinkMan());
        detailBean.setStartCity(expreessOrder.getShipCustCity());
        detailBean.setStartCounty(expreessOrder.getShipCustCounty());
        detailBean.setStartProvide(expreessOrder.getShipCustProvide());
        detailBean.setStartLatitude(expreessOrder.getShipLatitude());
        detailBean.setStartLongitude(expreessOrder.getShipLongitude());

        detailBean.setDestAddress(expreessOrder.getCneeCustAddr());
        detailBean.setDestTel(expreessOrder.getCneeCustLinkTel());
        detailBean.setDestLinkMan(expreessOrder.getCneeCustLinkMan());
        detailBean.setDestCity(expreessOrder.getCneeCustCity());
        detailBean.setDestCounty(expreessOrder.getCneeCustCounty());
        detailBean.setDestProvide(expreessOrder.getCneeCustProvide());
        detailBean.setDestLatitude(expreessOrder.getCneeLatitude());
        detailBean.setDestLongitude(expreessOrder.getCneeLongitude());

        detailBean.setBookingDate(expreessOrder.getBookingDate());
        detailBean.setTransportType(expreessOrder.getTransportType());

        detailBean.setStartPayment(expreessOrder.getGoodsPayment());
        detailBean.setStartCurrency(expreessOrder.getGoodsPaymentCurr());
        detailBean.setDestPayment(expreessOrder.getDestPayment());
        detailBean.setDestCurrency(expreessOrder.getDestPaymentCurr());
        detailBean.setGoodsValue(expreessOrder.getGoodsValue());

        detailBean.setNeedInsure(expreessOrder.getNeedInsure());
        detailBean.setIsJs(expreessOrder.getIsJs());
        detailBean.setQuotedType(expreessOrder.getQuotedType());
        detailBean.setComQuoteId(expreessOrder.getComQuoteId());
        detailBean.setBusiCtrl(expreessOrder.getBusiCtrl());
        detailBean.setPredictValue(expreessOrder.getPredictValue());
        detailBean.setPredictCurr(expreessOrder.getPredictCurr());
        detailBean.setPayType(expreessOrder.getPayType());
        detailBean.setNarrate(expreessOrder.getNarrate());
        detailBean.setPremiumValue(expreessOrder.getPremiumValue());
        detailBean.setPremiumProportion(expreessOrder.getPremiumProportion());
        detailBean.setRevUserId(expreessOrder.getRevUserId());
        detailBean.setRevUser(expreessOrder.getRevUser());
        detailBean.setRevDate(expreessOrder.getRevDate());
        detailBean.setStartLocus(expreessOrder.getStartLocus());
        detailBean.setDestnLocus(expreessOrder.getDestnLocus());
        detailBean.setCreateDate(expreessOrder.getCreateDate());
        detailBean.setCreateUser(expreessOrder.getCreateUser());
        detailBean.setCreateUserId(expreessOrder.getCreateUserId());

        // 提取货物信息
        List<MobileGoodsInfo> goodsInfoList = new ArrayList<MobileGoodsInfo>();
        List<ExpreessBookingForm> subOrderList = expreessOrder.getBookingFormList();
        if (null != subOrderList && subOrderList.size() > 0) {
            ComAccount account = comAccountService.queryAccountByAcctUsername(subOrderList.get(0).getCreateUser());
            if (null != account) {
                detailBean.setCreateUserTel(account.getTelephone());
            }
            detailBean.setAccesstime(subOrderList.get(0).getAccesstime());
            for (ExpreessBookingForm subOrder : subOrderList) {
                if (null != subOrder.getBillingFormSalmList()) {
                    for (ExpreessBillingFormSalm salm : subOrder.getBillingFormSalmList()) {
                        goodsInfoList.add(createMobileGoodsInfo(salm, subOrder));
                    }
                }
            }

        }
        detailBean.setGoodsInfoList(goodsInfoList);
        return detailBean;
    }

    /**
     * 提取货物信息
     *
     * @param billingFormSalm
     * @return
     */
    private MobileGoodsInfo createMobileGoodsInfo(ExpreessBillingFormSalm billingFormSalm, ExpreessBookingForm subOrder) {
        MobileGoodsInfo mobileGoodsDtl = new MobileGoodsInfo();
        if (null != billingFormSalm.getGoodsHeight()) {
            mobileGoodsDtl.setGoodsHeight(BigDecimal.valueOf(billingFormSalm.getGoodsHeight()));
        }
        if (null != billingFormSalm.getGoodsLength()) {
            mobileGoodsDtl.setGoodsLength(BigDecimal.valueOf(billingFormSalm.getGoodsLength()));
        }
        if (null != billingFormSalm.getGoodsWidth()) {
            mobileGoodsDtl.setGoodsWide(BigDecimal.valueOf(billingFormSalm.getGoodsWidth()));
        }

        mobileGoodsDtl.setGoodsName(billingFormSalm.getHscodeNachs());
        mobileGoodsDtl.setGoodsQty(billingFormSalm.getGoodsQty());
        mobileGoodsDtl.setGoodsType(billingFormSalm.getHscodeSpecs());
        mobileGoodsDtl.setGoodsQtyUnit(billingFormSalm.getGoodsQtyUnitCo());
        mobileGoodsDtl.setGoodsWeight(billingFormSalm.getGoodsGrosswht());
        mobileGoodsDtl.setGoodsWeightUnit(billingFormSalm.getWeightUnitCo());
        mobileGoodsDtl.setGoodsVolume(billingFormSalm.getGoodsVolume());
        if (StringUtil.isEmpty(mobileGoodsDtl.getGoodsVolumeUnit())) {
            mobileGoodsDtl.setGoodsVolumeUnit("164");
        } else {
            mobileGoodsDtl.setGoodsVolumeUnit(mobileGoodsDtl.getGoodsVolumeUnit());
        }
        mobileGoodsDtl.setMobileScheduOrderNo(subOrder.getBusiBookNo());
        return mobileGoodsDtl;
    }

    /**
     * 接单
     *
     * @param acceptOrderReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult acceptOrder(MobileStationAcceptOrderReq acceptOrderReq) throws MobileStationBizException {
        logger.info("接单  acceptOrder {}", JSON.toJSONString(acceptOrderReq));
        AppBaseResult baseResBean = new AppBaseResult(acceptOrderReq);
        int flag = 0;
        int msgTo = 0;// 发消息给谁
        MsgIMReq msgIMReq = new MsgIMReq();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();//记录GPS操作日志
        List<String> allBusNoList = new ArrayList<>();
        if (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST
                || acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST) {
            // 判断广播单是运输广播单或者是个人广播单
            if (!StringUtils.isEmpty(acceptOrderReq.getScheducarno())) {
                giOrderTraceResynced.setProductType(MobileStationDefine.PRODUCT_TYPE_ITCYS);
                // 运输广播单
                // 告知运输，已经接单
                ReceiveExpreessOrderRequest receiveExpreessOrderRequest = new ReceiveExpreessOrderRequest();
                receiveExpreessOrderRequest.setBrocast(true);
                receiveExpreessOrderRequest.setMobileBookFormId(acceptOrderReq.getOrderId());
                receiveExpreessOrderRequest.setRevUser(acceptOrderReq.getAcctUsername());
                receiveExpreessOrderRequest.setRevUserId(acceptOrderReq.getAccountId() + "");
                receiveExpreessOrderRequest.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                receiveExpreessOrderRequest.setSchudeCarNo(acceptOrderReq.getScheducarno());
                receiveExpreessOrderRequest.setSchudeCarType(acceptOrderReq.getTransportType());
                receiveExpreessOrderRequest.setRoleId(acceptOrderReq.getRoleId()); // 广播单需要更新对应的接单方式
                if (acceptOrderReq.getPushDate() != null) {
                    receiveExpreessOrderRequest.setPushOrderDate(format.format(acceptOrderReq.getPushDate()));
                }
                logger.info("运输广播单接单传参-快递单:{}", JSON.toJSONString(receiveExpreessOrderRequest));
                String expreessResStr = expreessOrderWebService.receiveExpreessOrder(receiveExpreessOrderRequest);
                if (!StringUtil.isEmpty(expreessResStr)) {
                    BaseResBean expreessRes = JSON.parseObject(expreessResStr, BaseResBean.class);
                    if (expreessRes.getRetCode() == 0) {
                        flag = 1;
                    } else if (expreessRes.getRetCode() == 1001) {
                        // 更新订单状态为已取消指派
                        MobileBookingForm mobileBookingForm = new MobileBookingForm();
                        mobileBookingForm.setId(acceptOrderReq.getOrderId());
                        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                        mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
                        throw new MobileStationBizException(expreessRes.getRetMsg());
                    } else {
                        logger.info("运输广播单接单返回-快递单:{}", expreessRes.getRetMsg());
                        throw new MobileStationBizException(expreessRes.getRetMsg());
                    }
                } else {
                    logger.info("运输广播单接单返回-快递单:HUB返回null");
                    throw new MobileStationBizException("接单失败");
                }
                msgTo = 3;
            } else {
                // 个人广播单
                try {
                    ReceiveCustomerOrderReq orderReq = new ReceiveCustomerOrderReq();
                    PropertyUtils.copyProperties(orderReq, acceptOrderReq); // 广播单需要更新接单方式
                    if (acceptOrderReq.getCompanyAccountId() != null) {
                        orderReq.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                    }
                    orderReq.setBookbusino(acceptOrderReq.getBusiBookNo());
                    // 设置账户角色
                    if (acceptOrderReq.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()) {
                        orderReq.setRegisterType(MobileStationDefine.OPERATOR_CAR_OWNER);
                    } else if (acceptOrderReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                        orderReq.setRegisterType(MobileStationDefine.OPERATOR_COURIER);
                    } else {
                        orderReq.setRegisterType(MobileStationDefine.OPERATOR_MSTATION);
                    }

                    // 设置账户信息
                    ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(acceptOrderReq.getAccountId());
                    if (null != comUserinfo) {
                        orderReq.setRealName(comUserinfo.getRealName());
                        orderReq.setTel(comUserinfo.getTelephone());
                        orderReq.setUserImg(comUserinfo.getUserImg());
                    }
                    orderReq.setBrocast(true);
                    orderReq.setRoleId(acceptOrderReq.getRoleId());
                    logger.info("个人广播单接单参数-customerOrderService:{}", JSON.toJSONString(orderReq));
                    boolean resultFlag = customerOrderService.receiveCustomerOrder(orderReq);
                    if (resultFlag) {
                        flag = 1;
                    }
                } catch (Exception e) {
                    logger.info(e.getMessage());
                    throw new MobileStationBizException(e.getMessage());
                }
                msgTo = 1;
                giOrderTraceResynced.setAction(MobileStationDefine.Action_AcceptOrder);
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(acceptOrderReq.getBusiBookNo());
                if (bookingForm != null) {
                    giOrderTraceResynced.setProductType(bookingForm.getTransportType());
                }
                allBusNoList.add(acceptOrderReq.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNoList);
                giOrderTraceResynced.setUserCode(acceptOrderReq.getLoginAcctUserName());
                giOrderTraceResynced.setLoginCode(acceptOrderReq.getAcctUsername());
            }

        } else if (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU) {
            // 运输指派单接单，回复运输，告知接单
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(acceptOrderReq.getOrderId());
            if (acceptOrderReq.getRoleId() != null && acceptOrderReq.getRoleId() != 7) {
                ReceiveExpreessOrderRequest receiveExpreessOrderRequest = new ReceiveExpreessOrderRequest();
                receiveExpreessOrderRequest.setBrocast(false);
                receiveExpreessOrderRequest.setMobileBookFormId(acceptOrderReq.getOrderId());
                receiveExpreessOrderRequest.setRevUser(acceptOrderReq.getAcctUsername());
                receiveExpreessOrderRequest.setRevUserId(acceptOrderReq.getAccountId() + "");
                receiveExpreessOrderRequest.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                receiveExpreessOrderRequest.setSchudeCarNo(acceptOrderReq.getScheducarno());
                receiveExpreessOrderRequest.setSchudeCarType(acceptOrderReq.getTransportType());
                receiveExpreessOrderRequest.setRoleId(acceptOrderReq.getRoleId()); // 指派单已生成,可不加
                receiveExpreessOrderRequest.setPushOrderDate(format.format(mobileBookingForm.getCreateDate()));

                logger.info("运输指派单接单参数-expreessOrderWebService:{}", JSON.toJSONString(receiveExpreessOrderRequest));
                String expreessResStr = expreessOrderWebService.receiveExpreessOrder(receiveExpreessOrderRequest);
                if (!StringUtil.isEmpty(expreessResStr)) {
                    AppBaseResult expreessRes = JSON.parseObject(expreessResStr, AppBaseResult.class);
                    if (expreessRes.getRetCode() == 0) {
                        flag = 1;
                        List<MobileBookingForm> mobileBookingForms = mobileBookingFormDaoEx.queryOrderListByConditions(mobileBookingForm.getBusiBookNo(), null, null, MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
                        for (MobileBookingForm bookingForm : mobileBookingForms) {
                            if (bookingForm.getId().intValue() != acceptOrderReq.getOrderId()
                                    && bookingForm.getStartLocus().equals(mobileBookingForm.getStartLocus())
                                    && bookingForm.getDestnLocus().equals(mobileBookingForm.getDestnLocus())
                                    && bookingForm.getCreateUser().equals(mobileBookingForm.getCreateUser())
                                    && bookingForm.getOrderFrom() == mobileBookingForm.getOrderFrom()
                                    && bookingForm.getProductType().equals(mobileBookingForm.getProductType())
                                    && bookingForm.getShipCustAddr().equals(mobileBookingForm.getShipCustAddr())
                                    && bookingForm.getCneeCustAddr().equals(mobileBookingForm.getCneeCustAddr())) {
                                //如果接单成功，把指派给其他人的未接单订单全部取消
                                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                                mobileBookingFormDao.updateByPrimaryKey(bookingForm);
                            }
                        }
                    } else if (expreessRes.getRetCode() == 1001) {
                        // 更新订单状态为已取消指派
                        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                        mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                        throw new MobileStationBizException(expreessRes.getRetMsg());
                    } else {
                        logger.info("运输指派单接单返回-expreessOrderWebService:" + expreessRes.getRetMsg());
                        throw new MobileStationBizException(expreessRes.getRetMsg());
                    }
                } else {
                    logger.info("运输指派单接单返回:HUB返回null");
                    throw new MobileStationBizException("接单失败");
                }
            } else {
                flag = 1;
                List<MobileBookingForm> mobileBookingForms = mobileBookingFormDaoEx.queryOrderListByConditions(mobileBookingForm.getBusiBookNo(), null, null, MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
                for (MobileBookingForm bookingForm : mobileBookingForms) {
                    if (bookingForm.getId().intValue() != acceptOrderReq.getOrderId()
                            && bookingForm.getStartLocus().equals(mobileBookingForm.getStartLocus())
                            && bookingForm.getDestnLocus().equals(mobileBookingForm.getDestnLocus())
                            && bookingForm.getCreateUser().equals(mobileBookingForm.getCreateUser())
                            && bookingForm.getOrderFrom() == mobileBookingForm.getOrderFrom()
                            && bookingForm.getProductType().equals(mobileBookingForm.getProductType())
                            && bookingForm.getShipCustAddr().equals(mobileBookingForm.getShipCustAddr())
                            && bookingForm.getCneeCustAddr().equals(mobileBookingForm.getCneeCustAddr())) {
                        //如果接单成功，把指派给其他人的未接单订单全部取消
                        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                        mobileBookingFormDao.updateByPrimaryKey(bookingForm);
                    }
                }
                //更新订单的接单状态

                mobileBookingForm.setRevUserId(acceptOrderReq.getAccountId());
                mobileBookingForm.setRevUser(acceptOrderReq.getAcctUsername());
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                mobileBookingForm.setRevDate(new Date());
                mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
                //记录日志信息

            }

            msgTo = 3;
            giOrderTraceResynced.setProductType(MobileStationDefine.PRODUCT_TYPE_ITCYS);
        } else if (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL) {
            try {
                ReceiveCustomerOrderReq orderReq = new ReceiveCustomerOrderReq();
                BeanUtils.copyProperties(acceptOrderReq, orderReq);
                if (acceptOrderReq.getCompanyAccountId() != null) {
                    orderReq.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                }
                orderReq.setBookbusino(acceptOrderReq.getBusiBookNo());
                orderReq.setBrocast(false);
                orderReq.setMobileBookFormId(acceptOrderReq.getOrderId());
                orderReq.setRoleId(acceptOrderReq.getRoleId());
                logger.info("个人指派单接单参数-customerOrderService:{}", JSON.toJSONString(orderReq));
                boolean resultFlag = customerOrderService.receiveCustomerOrder(orderReq);
                if (resultFlag) {
                    flag = 1;
                }
            } catch (Exception e) {
                throw new MobileStationBizException(e.getMessage());
            }
            msgTo = 1;
            giOrderTraceResynced.setAction(MobileStationDefine.Action_AcceptOrder);
            allBusNoList.add(acceptOrderReq.getBusiBookNo());
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(acceptOrderReq.getBusiBookNo());
            if (bookingForm != null) {
                giOrderTraceResynced.setProductType(bookingForm.getTransportType());
            }
            giOrderTraceResynced.setAllBusiNo(allBusNoList);
            giOrderTraceResynced.setUserCode(acceptOrderReq.getLoginAcctUserName());
            giOrderTraceResynced.setLoginCode(acceptOrderReq.getAcctUsername());
        } else if (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS
                || acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST) {
            //获取广播单状态
            boolean broadcast = (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST); // 判断是否是广播单

            // 获取待接单的订单信息
            MobileStationOrderDetailBean orderDetailBean = mobileMyOrderDao.queryOrderByBusiNoAndBusiCtrl(
                    acceptOrderReq.getBusiBookNo(), MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
            if (orderDetailBean == null) {
                throw new MobileStationBizException("订单已被别人接单或已取消，不在可接单状态！");
            }
            msgIMReq.setCreateUser(orderDetailBean.getCreateUser());
            msgIMReq.setOrderId(orderDetailBean.getOrderId());

            //咪站广播单、指派单 ，修改广播单的订单状态、接单人，并更新原单的状态为已指派，已接单

            // 更新MS订单的状态为已接单;接单人已生成
            MobileBookingForm mobileBookingForm = new MobileBookingForm();
            mobileBookingForm.setId(orderDetailBean.getOrderId());
            mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER); // MS待接单改为已接单
            mobileBookingForm.setRevDate(new Date()); // 接单日期
            mobileBookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
            // 修改原订单状态为已签派，已接单
            boolean isFlag = false;
            List<MobileBookingForm> mobileBookingFormList = mobileStationOrderDao.getMobileBookingFormByBusiBookNo(
                    orderDetailBean.getBusiBookNo(), orderDetailBean.getCreateUser(), orderDetailBean.getCreateCompanyId());
            if (broadcast) {
                mobileBookingForm.setRevUserId(acceptOrderReq.getAccountId()); // 更新接单人
                mobileBookingForm.setRevUser(acceptOrderReq.getAcctUsername());
                mobileBookingForm.setRoleId(acceptOrderReq.getRoleId()); // 广播单需要更新接单方式

                for (MobileBookingForm mobileBF : mobileBookingFormList) {
                    if (orderDetailBean.getStartLocus().equals(mobileBF.getStartLocus())
                            && mobileBF.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_BROADCAST) {
                        mobileBF.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE);
                        mobileBookingFormDao.updateByPrimaryKey(mobileBF);
                        isFlag = true;

                        // 咪站广播单，生成转单中心信息
                        MobileSingleCenter mobileSingleCenter = new MobileSingleCenter();
                        mobileSingleCenter.setMobileBookingFormId(mobileBF.getId());
                        mobileSingleCenter.setBusiBookNo(orderDetailBean.getBusiBookNo());
                        mobileSingleCenter.setCreateUserId(orderDetailBean.getCreateUserId());
                        mobileSingleCenter.setCreateUser(orderDetailBean.getCreateUser());
                        mobileSingleCenter.setCreateDate(new Date());
                        mobileSingleCenter.setRevUser(acceptOrderReq.getAcctUsername());
                        mobileSingleCenter.setRevUserId(acceptOrderReq.getAccountId());
                        mobileSingleCenter.setDispatchId(null);
                        mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_ACCEPT);
                        mobileSingleCenter.setSingleValue(orderDetailBean.getPredictValue());
                        mobileSingleCenter.setSingleCurr(orderDetailBean.getPredictCurr());
                        mobileSingleCenter.setSingleDate(new Date());
                        mobileSingleCenterDao.insert(mobileSingleCenter);
                    }
                }
            } else {
                //指派单，更新转单信息记录为已接单
                List<MobileSingleCenter> mobileSingleCenters = mobileUserOrderDao.querySingleCenterByBusiNo(
                        acceptOrderReq.getBusiBookNo(), orderDetailBean.getCreateUser(),
                        MobileStationDefine.SINGLE_CENTER_TOACCEPT);
                if (mobileSingleCenters != null && mobileSingleCenters.size() > 0) {
                    MobileSingleCenter mobileSingleCenter = mobileSingleCenters.get(0);
                    mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_ACCEPT);// 状态为已接单
                    mobileSingleCenter.setRevUserId(acceptOrderReq.getAccountId()); //
                    mobileSingleCenter.setRevUser(acceptOrderReq.getAcctUsername());
                    mobileSingleCenter.setRevDate(new Date());
                    mobileSingleCenterDao.updateByPrimaryKey(mobileSingleCenter);
                }
                for (MobileBookingForm mobileBF : mobileBookingFormList) {
                    if (orderDetailBean.getStartLocus().equals(mobileBF.getStartLocus()) && mobileBF.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                        mobileBF.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE);
                        mobileBookingFormDao.updateByPrimaryKey(mobileBF);
                        isFlag = true;
                    }
                }
            }

            if (!isFlag) {
                throw new MobileStationBizException("订单已被别人接单或已取消，不在可接单状态！");
            }

            //更新待接单状态 为已接单
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("busiCtrl", MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
            param.put("mbf", mobileBookingForm);
            int i = mobileBookingFormDaoEx.updateByBusiCtrlLock(param);

            if (i < 1) {
                throw new MobileStationBizException("订单已被人抢单");
            }

            // 如果是派车单，更新派车单状态为已接单
            if (!StringUtil.isEmpty(orderDetailBean.getScheducarno())) {
                // 更新子单为拒接
                mobileScheduSubOrderDaoEx.updateStatusByMobileBookingFormId(acceptOrderReq.getOrderId(),
                        MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER,
                        MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
            }
            flag = 1;
            msgTo = 2;
            giOrderTraceResynced.setProductType(orderDetailBean.getProductType());
        }
        if (flag < 1) {
            throw new MobileStationBizException("订单已被人抢单");
        } else {
            try {
                PropertyUtils.copyProperties(msgIMReq, acceptOrderReq);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            msgIMReq.setMsgTo(msgTo);
            msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_ACCEPT_ORDER);
            msgIMReq.setOrderId(acceptOrderReq.getOrderId());
            sendMsg(msgIMReq);// 推送消息
            if (acceptOrderReq.getRoleId() != null) {
                giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(acceptOrderReq.getRoleId()));
            }
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);

            if (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST
                    || acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST
                    || acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST) {
                //如果是广播单，判断是否是商管中心指派的，如果是指派的，更新订单状态
                if (acceptOrderReq.getAssignUserId() != null) {
                    MobileBookingForm form = new MobileBookingForm();
                    form.setAssignUserId(acceptOrderReq.getAssignUserId());
                    form.setAssignUser(acceptOrderReq.getAssignUser());
                    form.setAssignDate(acceptOrderReq.getAssignDate());
                    form.setRevUser(acceptOrderReq.getAcctUsername());
                    form.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                    form.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                    form.setBusiBookNo(acceptOrderReq.getBusiBookNo());
                    mobileBookingFormDaoEx.updateOrderByParam(form);
                }
                List<GiBizOrder> giBizOrderList = new ArrayList<>();
                GiBizOrder giBizOrder = new GiBizOrder();
                giBizOrder.setAppTag(CustomerDefine.IM_MS_DEFINE);
                giBizOrder.setDocNo(acceptOrderReq.getBusiBookNo());
                giBizOrder.setAction(MobileStationDefine.GPS_ACTION_DELETE);
                giBizOrderList.add(giBizOrder);
                gpsOrderService.sendBroadCastOrderMessage(giBizOrderList);
            }
        }
        return baseResBean;
    }

    /**
     * MS3.0 MS指派，快递员或运输车接单 MS在用户订单发车后进行指派，如果快递员或运输车接单，生成一个POP-A的MS1单，并修改转单记录状态
     *
     * @param receiveMsOrderReq
     * @return
     * @throws MobileStationBizException
     */
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult acceptMsOrder(ReceiveMsOrderReq receiveMsOrderReq) throws MobileStationBizException {
        try {
            logger.info("发车后重新指派，快递员或运输车接单：acceptMsOrder" + JSONObject.toJSONString(receiveMsOrderReq));
            AppBaseResult baseResBean = new AppBaseResult();
            // 参数校验
            if (checkMsAcceptOperate(receiveMsOrderReq, baseResBean)) {
                boolean isFlag = false;
                // 修改原POP-POD订单状态为已签派，已接单
                List<MobileBookingForm> mobileBookingFormList = mobileStationOrderDao.getMobileBookingFormByBusiBookNo(
                        receiveMsOrderReq.getBusiBookNo(), receiveMsOrderReq.getRevUser(), receiveMsOrderReq.getRevCompanyId());
                if (mobileBookingFormList != null && mobileBookingFormList.size() > 0) {
                    for (MobileBookingForm mobileBF : mobileBookingFormList) {
                        boolean execute = false;
                        if (receiveMsOrderReq.isBrocast()
                                && mobileBF.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_BROADCAST) { // 广播单
                            execute = true;
                        } else if (mobileBF.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) { // 指派单
                            execute = true;
                        }
                        if (execute) {
                            mobileBF.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE);
                            mobileBookingFormDao.updateByPrimaryKey(mobileBF); // POP-POD
                            isFlag = true;
                            break;
                        }
                    }
                } else {
                    baseResBean.setRetMsg("订单不存在！");
                    baseResBean.setRetCode(SystemDefine.FAILURE);
                }

                // 修改转单记录状态,为已接单
                List<MobileSingleCenter> mobileSingleCenters = mobileUserOrderDao.querySingleCenterByBusiNo(
                        receiveMsOrderReq.getBusiBookNo(), receiveMsOrderReq.getRevUser(),
                        MobileStationDefine.SINGLE_CENTER_TOACCEPT);
                if (mobileSingleCenters != null && mobileSingleCenters.size() > 0) {
                    MobileSingleCenter mobileSingleCenter = mobileSingleCenters.get(0);
                    mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_ACCEPT);// 状态为已接单
                    mobileSingleCenter.setRevUserId(receiveMsOrderReq.getAccountId()); //
                    mobileSingleCenter.setRevUser(receiveMsOrderReq.getAcctUsername());
                    mobileSingleCenter.setRevDate(new Date());
                    // mobileSingleCenter.setTeamComsixNo(receiveMsOrderReq.getTeamComsixNo());
                    mobileSingleCenterDao.updateByPrimaryKey(mobileSingleCenter);
                }
                if (!isFlag) {
                    throw new MobileStationBizException("接单失败！");
                }
            } else {
                baseResBean.setRetMsg("参数不能为空");
                baseResBean.setRetCode(SystemDefine.FAILURE);
            }
            return baseResBean;
        } catch (Exception e) {
            if (e instanceof MobileStationBizException) {
                // 业务异常
                throw new MobileStationBizException(e.getMessage());
            } else {
                // 数据库连接异常
                logger.error("MS指派单或广播单接单异常：" + e.getMessage());
                throw new MobileStationBizException("服务器网络异常,请重试");
            }
        }
    }

    /**
     * 参数校验
     */
    boolean checkMsAcceptOperate(ReceiveMsOrderReq receiveMsOrderReq, AppBaseResult baseResBean)
            throws MobileStationBizException {

        if (StringUtil.isEmpty(receiveMsOrderReq.getBusiBookNo())) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("业务订单号为空！");
        }
        if (StringUtil.isEmpty(receiveMsOrderReq.getRevUser())) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("接单人为空！");
        }

        return true;
    }

    /**
     * 接单和拒接推送消息
     *
     * @param msgIMReq
     * @throws MobileStationBizException
     */
    public void sendMsg(MsgIMReq msgIMReq) {
        // 发送消息
        Map<String, String> mapObject = new HashMap<String, String>();
        if (msgIMReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST
                || msgIMReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU) {
            return;// 派车单 接单不发消息
        }
        try {
            // 设置账户信息
            ComAccount account = comAccountService.queryAccountByAcctUsername(msgIMReq.getAcctUsername());
            if (null != account) {
                if (!StringUtil.isEmpty(account.getRealName())) {
                    mapObject.put("realName", account.getRealName());
                } else {
                    throw new MobileStationBizException("推送人姓名为空");
                }
                mapObject.put("acctUsername", account.getAcctUsername());
                mapObject.put("userImg", account.getUserImg());
                mapObject.put("tel", account.getTelephone());
                mapObject.put("accountId", account.getId().toString());
            } else {
                throw new MobileStationBizException("推送人账户不存在");
            }
            mapObject.put("msgTime", DateUtil.formatDate2Str(new Date(), DateUtil.C_TIME_PATTON_DEFAULT));
            if (msgIMReq.getRoleId() == null) {
                List<ComAccountRoleRel> roleList = accountRoleRelDaoEx.queryByAccountId(msgIMReq.getAccountId());
                msgIMReq.setRoleId(roleList.get(0).getRoleId());
            }
            // 设置账户角色
            if (msgIMReq.getRoleId() == 3) {
                mapObject.put("registerType", MobileStationDefine.OPERATOR_CAR_OWNER);
            } else if (msgIMReq.getRoleId() == 7) {
                mapObject.put("registerType", MobileStationDefine.OPERATOR_COURIER);
            } else {
                mapObject.put("registerType", MobileStationDefine.OPERATOR_MSTATION);
            }
            mapObject.put("bookbusino", msgIMReq.getBusiBookNo());
            msgIMReq.setMapObject(mapObject);
            mobileMyOrderService.pushMessageIM(msgIMReq);
        } catch (MobileStationBizException e) {
            logger.info("推送IM消息参数错误：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 派车单接单日志
     *
     * @param mobileBookingForm
     * @throws MobileStationBizException
     */
    private void recordReceiveLog(MobileBookingForm mobileBookingForm) throws MobileStationBizException {

        ComWaybillTrace comWaybillTrace = new ComWaybillTrace();
        if (null == mobileBookingForm.getBusiBookNo()) {
            throw new MobileStationBizException("订单busiBookNo不能为空");
        }
        if (null == mobileBookingForm.getRevUser()) {
            throw new MobileStationBizException("接单人不能为空");
        }
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(mobileBookingForm.getRevUserId());
        comWaybillTrace.setAcctUsername(mobileBookingForm.getRevUser());
        if (comAccount != null) {
            comWaybillTrace.setRealName(comAccount.getRealName());
        }
        comWaybillTrace.setBusiBookNo(mobileBookingForm.getBusiBookNo());
        comWaybillTrace.setHubNo(mobileBookingForm.getScheducarno());
        comWaybillTrace.setStartLocus(mobileBookingForm.getStartLocus());
        comWaybillTrace.setDestnLocus(mobileBookingForm.getDestnLocus());
        comWaybillTrace.setGrade(0);
        if (mobileBookingForm.getRoleId() != null && comAccount != null) {
            comWaybillTrace.setRemark(SysAccountRole.getName(mobileBookingForm.getRoleId().intValue()) + "已接单，联系电话：" + comAccount.getTelephone());
        } else {
            comWaybillTrace.setRemark("已接单");
        }

        comWaybillTrace.setExecCode(WayBillStatusDefine.MS_AGREE_O.getIntValue());
        comWaybillTrace.setStaDate(new Date());
        comWaybillTrace.setRoleId(mobileBookingForm.getRoleId());
        comWaybillTraceDao.insert(comWaybillTrace);

        //如果存在子订单数据 记录子订单日志
        List<MobileScheduSubOrder> subOrderList = mobileScheduSubOrderDaoEx.selectMobileSubOrderByMobileId(mobileBookingForm.getId());
        if (null != subOrderList && subOrderList.size() > 0) {
            for (MobileScheduSubOrder subOrder : subOrderList) {
                if (null == subOrder.getBusiBookNo()) {
                    throw new MobileStationBizException("订单busiBookNo不能为空");
                }
                comAccount = comAccountService.queryAccountByAcctUsername(mobileBookingForm.getRevUser());
                ComWaybillTrace comWaybillTraceSub = new ComWaybillTrace();
                comWaybillTraceSub.setAcctUsername(mobileBookingForm.getRevUser());
                if (comAccount != null) {
                    comWaybillTraceSub.setRealName(comAccount.getRealName());
                }
                comWaybillTraceSub.setStartLocus(mobileBookingForm.getStartLocus());
                comWaybillTraceSub.setDestnLocus(mobileBookingForm.getDestnLocus());
                comWaybillTraceSub.setBusiBookNo(subOrder.getBusiBookNo());
                comWaybillTraceSub.setHubNo(subOrder.getScheducarno());
                comWaybillTraceSub.setGrade(0);
                comWaybillTraceSub.setRemark(WayBillStatusDefine.MS_AGREE_O.getName());
                comWaybillTraceSub.setExecCode(WayBillStatusDefine.MS_AGREE_O.getIntValue());
                comWaybillTraceSub.setStaDate(new Date());
                comWaybillTraceSub.setRoleId(mobileBookingForm.getRoleId());
                comWaybillTraceDao.insert(comWaybillTraceSub);
            }
        }
    }

    /**
     * 拒绝订单 状态0到50
     *
     * @param mobileStatusOperateReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult refuseOrder(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        int flag = 0;
        int msgTo = 0;
        if (mobileStatusOperateReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU) {
            // 运输指派单
            String expreessResStr = "";
            if (mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                // 咪站拒绝蛙站下的订单
                MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq.getOrderId());
                MStationRevOrderRequest mStationRefusOrderRequest = new MStationRevOrderRequest();
                mStationRefusOrderRequest.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
                mStationRefusOrderRequest.setMStationComAccountId(mobileStatusOperateReq.getAccountId());
                if (mobileBookingForm != null && mobileBookingForm.getCreateUserId() != null) {
                    ComCustomer comCustomer = comCustomerDao.queryCustomerInfoByAcctId(mobileBookingForm.getCreateUserId());
                    if (comCustomer != null) {
                        mStationRefusOrderRequest.setYComCustomerId(comCustomer.getId());
                    }
                }
                logger.info("运输指派单咪站拒绝参数-expreessOrderWebService:{}", JSON.toJSONString(mStationRefusOrderRequest));
                expreessResStr = expreessOrderWebService.mStationRefusOrder(mStationRefusOrderRequest);
            } else {
                GiveUpSchudeCarOrderRequest giveUpSchudeCarOrderRequest = new GiveUpSchudeCarOrderRequest();
                giveUpSchudeCarOrderRequest.setSchudeCarNo(mobileStatusOperateReq.getScheducarno());
                giveUpSchudeCarOrderRequest.setSchudeCarType(mobileStatusOperateReq.getTransportType());
                giveUpSchudeCarOrderRequest.setTotalLevel(true);
                logger.info("运输指派单拒绝参数-expreessOrderWebService:{}", JSON.toJSONString(giveUpSchudeCarOrderRequest));
                expreessResStr = expreessOrderWebService.refuseSchudeCarOrder(giveUpSchudeCarOrderRequest);
            }
            if (!StringUtil.isEmpty(expreessResStr)) {
                BaseResBean expreessRes = JSON.parseObject(expreessResStr, BaseResBean.class);
                if (expreessRes.getRetCode() == 0) {
                    flag = 1;
                } else {
                    logger.info("运输指派单拒绝返回:" + expreessRes.getRetMsg());
                }
            } else {
                logger.info("运输指派单拒绝返回:HUB返回null");
                throw new MobileStationBizException("拒绝订单失败");
            }
            msgTo = 3;
        } else if (mobileStatusOperateReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL) {
            // 个人指派单
            // 修改BOOKING_FORM数据
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileStatusOperateReq.getBusiBookNo());
            bookingForm.setRevUser(null);
            bookingForm.setRevUserId(null);
            bookingForm.setRevDate(null);
            bookingForm.setRevCompanyId(null);
            bookingForm.setRevUserName(null);
            bookingForm.setBusiCtrl(OrderState.WAITRE_CEIVING.getValue());
            flag = bookingFormDao.updateByPrimaryKey(bookingForm);

//                GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
//                List<String> allBusNo = new ArrayList<>();
//                allBusNo.add(mobileStatusOperateReq.getBusiBookNo());
//                giOrderTraceResynced.setAllBusiNo(allBusNo);
//                giOrderTraceResynced.setProductType(bookingForm.getTransportType());
//                giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelAccept);
//                giOrderTraceResynced.setTsAct(new Date());
//                giOrderTraceResynced.setUserCode(mobileStatusOperateReq.getLoginAcctUserName());
//                giOrderTraceResynced.setLoginCode(mobileStatusOperateReq.getAcctUsername());
//                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
            //add by xugw20170821 订单取消后重新进入订单池，重新发送消息给中层
            PlaceAnOrderRes placeAnOrderRes = new PlaceAnOrderRes();
            placeAnOrderRes.setBusiBookNo(bookingForm.getBusiBookNo());
            placeAnOrderRes.setOrderId(bookingForm.getId());
            placeAnOrderRes.setProductType(bookingForm.getTransportType());
            placeAnOrderRes.setRoleId(mobileStatusOperateReq.getRoleId());
            customerOrderService.notifyGps(placeAnOrderRes, null);
//            if (flag > 0) {
//                try {
//                    logger.info("取消投保单号：" + mobileStatusOperateReq.getBusiBookNo());
//                    customerOrderService.withdrawInsure(mobileStatusOperateReq.getBusiBookNo());
//                } catch (MobileStationBizException e) {
//                    logger.info("取消投保失败" + e.getMessage());
//                    // throw new MobileStationBizException("取消投保失败");
//                }
//            }
            msgTo = 1;
        } else if (mobileStatusOperateReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS) {
            // MS指派单或者咪站派车单
            // 获取当前订单信息
            MobileStationOrderDetailBean orderDetailBean = mobileMyOrderDao.queryOrderDetail(mobileStatusOperateReq
                    .getOrderId());
            if (orderDetailBean == null) {
                throw new MobileStationBizException("订单不存在");
            }
            // 修改指派原单订单状态
            List<MobileBookingForm> mobileBookingFormList = mobileStationOrderDao.getMobileBookingFormByBusiBookNo(
                    mobileStatusOperateReq.getBusiBookNo(), orderDetailBean.getCreateUser(), orderDetailBean.getCreateCompanyId()); // 查询指派前的订单
            if (mobileBookingFormList == null || mobileBookingFormList.size() < 1) {
                throw new MobileStationBizException("订单不存在");
            }
            boolean isFlag = false;
            for (MobileBookingForm mobileBForm : mobileBookingFormList) {
                // 原订单当前状态为已指派，待接单
                if (mobileBForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                    //如果是MS指派给MS，拒绝后改为已发车，如果是咪站派车给MS，POP-M拒绝后改为已接单，M-POD或者M-W拒绝后改为取件成功
                    if (mobileBForm.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()
                            && MobileStationDefine.M.equals(mobileBForm.getStartLocus())) {
                        // 如果是咪站派车给MS，M-POD或者M-W拒绝后改为取件成功
                        mobileBForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                    } else if (mobileBForm.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()
                            && MobileStationDefine.M.equals(mobileBForm.getDestnLocus())) {
                        // 如果是咪站派车给MS，POP-M拒绝后改为已接单
                        mobileBForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                    } else {
                        // 如果是MS指派给MS，拒绝后改为已发车
                        if (mobileBForm.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                            mobileBForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                        } else {
                            mobileBForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN);
                        }
                    }
                    mobileBookingFormDao.updateByPrimaryKey(mobileBForm);
                    isFlag = true;
                    break;
                }
            }
            // 修改转单记录状态，为拒绝
            List<MobileSingleCenter> mobileSingleCenters = mobileUserOrderDao.querySingleCenterByBusiNo(
                    mobileStatusOperateReq.getBusiBookNo(), orderDetailBean.getRevUser(),
                    MobileStationDefine.SINGLE_CENTER_TOACCEPT); // 更新A-POD
            if (mobileSingleCenters != null && mobileSingleCenters.size() > 0) {
                MobileSingleCenter mobileSingleCenter = mobileSingleCenters.get(0);
                mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_REFUSE);// 状态为拒绝
                mobileSingleCenter.setRevDate(new Date());
                mobileSingleCenterDao.updateByPrimaryKey(mobileSingleCenter);
            }

            if (isFlag) {
                flag = 1;
                // 更新MS指派单的狀態
                MobileBookingForm mobileBookingForm = new MobileBookingForm();
                mobileBookingForm.setId(orderDetailBean.getOrderId());
                // 更新A-POD的订单状态為拒接
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_REFUSE);
                mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
                // 如果是派车单，更新派车单状态为拒接
                if (!StringUtil.isEmpty(mobileStatusOperateReq.getScheducarno())) {
                    // 更新子单为拒接
                    mobileScheduSubOrderDaoEx.updateStatusByMobileBookingFormId(mobileStatusOperateReq.getOrderId(),
                            MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER,
                            MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                }
            } else {
                throw new MobileStationBizException("拒绝失败！");
            }
            msgTo = 2;
        }

        if (flag < 1) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("拒绝失败!");
            // throw new MobileStationBizException("拒绝失败!");
        } else {
            // 修改订单状态 状态从0到50
            MobileOrderOperateBean orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                    mobileStatusOperateReq.getOrderId(),
                    mobileStatusOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER,
                    MobileStationDefine.MOBILE_ORDER_STATUS_REFUSE);
            mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
            MsgIMReq msgIMReq = new MsgIMReq();
            try {
                PropertyUtils.copyProperties(msgIMReq, mobileStatusOperateReq);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            msgIMReq.setMsgTo(msgTo);
            msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_REFUSE_ORDER);
            sendMsg(msgIMReq);// 推送消息

            if (StringUtils.isNotBlank(mobileStatusOperateReq.getScheducarno())) {
                waybillTraceOperateBean.setScheducarno(mobileStatusOperateReq.getScheducarno());
            } else {
                waybillTraceOperateBean.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
            }
            // 插入流程跟踪日志
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq
                    .getOrderId());
            waybillTraceOperateBean.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
            ComAccount account = comAccountService.queryAccountByAcctUsername(mobileStatusOperateReq.getAcctUsername());
            if (mobileBookingForm != null) {
                waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
                waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
            }
            waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
            waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_REJECT.getIntValue());
            if (account != null) {
                waybillTraceOperateBean.setRemark(SysAccountRole.getName(mobileStatusOperateReq.getRoleId().intValue()) + account.getRealName() + "已拒绝接单");
            } else {
                waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_REJECT.getName()
                        + mobileStatusOperateReq.getRefuseDesc());
            }
            waybillTraceOperateBean.setDispatchId(mobileStatusOperateReq.getOrderId());
            if (mobileStatusOperateReq.getRoleId() != null) {
                waybillTraceOperateBean.setRoleId(mobileStatusOperateReq.getRoleId());
            }
            mobileMyOrderService.insertWaybillTrace(waybillTraceOperateBean);
        }

        return baseResBean;
    }

    /**
     * 批量webservice请求处理，过滤失败的数据
     *
     * @param mStationRevOrderRequests
     * @param giveUpSchudeCarOrderRequests
     * @return
     */
    private Map<String, HubBatchResBean.DataBean> mStationRefusOrderList(List<MStationRevOrderRequest> mStationRevOrderRequests,
                                                                         List<GiveUpSchudeCarOrderRequest> giveUpSchudeCarOrderRequests) {
        Map<String, HubBatchResBean.DataBean> expreessResMap = new HashMap<>();
//        for (MStationRevOrderRequest mStationRevOrderRequest : mStationRevOrderRequests) {
//            String expreessResStr = null;
//            try {
//                expreessResStr = expreessOrderWebService.mStationRefusOrder(mStationRevOrderRequest);
//                expreessResMap.put(mStationRevOrderRequest.getBusiBookNo(), expreessResStr);
//            } catch (ExpreessException_Exception e) {
//                e.printStackTrace();
//            }
//        }
        if (mStationRevOrderRequests.size() > 0) {
            String expreessResStrs = expreessOrderWebService.mStationRefusOrderList(mStationRevOrderRequests);
            if (org.apache.commons.lang3.StringUtils.isNotBlank(expreessResStrs)) {
                HubBatchResBean hubBatchResBean = JSON.parseObject(expreessResStrs, HubBatchResBean.class);
                if (hubBatchResBean.getRetCode().equals("0")) {
                    List<HubBatchResBean.DataBean> list = hubBatchResBean.getData();
                    for (HubBatchResBean.DataBean dataBean : list) {
                        expreessResMap.put(dataBean.getData(), dataBean);
                    }
                }
            }
        }
        if (giveUpSchudeCarOrderRequests.size() > 0) {
            String expreessResStrs = expreessOrderWebService.refuseSchudeCarOrderList(giveUpSchudeCarOrderRequests);
            if (org.apache.commons.lang3.StringUtils.isNotBlank(expreessResStrs)) {
                HubBatchResBean hubBatchResBean = JSON.parseObject(expreessResStrs, HubBatchResBean.class);
                if (hubBatchResBean.getRetCode().equals("0")) {
                    List<HubBatchResBean.DataBean> list = hubBatchResBean.getData();
                    for (HubBatchResBean.DataBean dataBean : list) {
                        expreessResMap.put(dataBean.getData(), dataBean);
                    }
                }
            }
        }
        return expreessResMap;
    }

    @Override
    public BatchRefuseOrderResult checkBatchRefuseOrder(BatchRefuseOrderReq batchRefuseOrderReq) throws MobileStationBizException {
        BatchRefuseOrderResult refuseOrderResult = new BatchRefuseOrderResult();
        List<CheckAssignOrderforbatchSuccess<RefuseOrderSuccess>> successes = new ArrayList<>();
        //失败列表
        List<CheckAssignOrderforbatchFailed> faileds = new ArrayList<>();
        //下面webservice批量操作入参列表
        List<MStationRevOrderRequest> mStationRevOrderRequests = new ArrayList<>();
        List<GiveUpSchudeCarOrderRequest> giveUpSchudeCarOrderRequests = new ArrayList<>();
        //订单号和请求的映射，以便在之后使用中通过订单号获取到对应的请求对象
        Map<String, MobileStatusOperateReq> reqMap = new HashMap<>();
        for (MobileStatusOperateReq mobileStatusOperateReq : batchRefuseOrderReq.getMobileStatusOperateReqList()) {
            CheckAssignOrderforbatchSuccess<RefuseOrderSuccess> success = new CheckAssignOrderforbatchSuccess();
            int flag = 0;
            int msgTo = 0;
            if (mobileStatusOperateReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU) {
                // 运输指派单
                if (mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                    // 咪站拒绝蛙站下的订单
                    MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq.getOrderId());
                    MStationRevOrderRequest mStationRefusOrderRequest = new MStationRevOrderRequest();
                    mStationRefusOrderRequest.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
                    mStationRefusOrderRequest.setMStationComAccountId(mobileStatusOperateReq.getAccountId());
                    if (mobileBookingForm != null && mobileBookingForm.getCreateUserId() != null) {
                        ComCustomer comCustomer = comCustomerDao.queryCustomerInfoByAcctId(mobileBookingForm.getCreateUserId());
                        if (comCustomer != null) {
                            mStationRefusOrderRequest.setYComCustomerId(comCustomer.getId());
                        }
                    }
                    logger.info("运输指派单咪站拒绝参数-expreessOrderWebService:{}", JSON.toJSONString(mStationRefusOrderRequest));
                    mStationRevOrderRequests.add(mStationRefusOrderRequest);
                    reqMap.put(mobileStatusOperateReq.getBusiBookNo(), mobileStatusOperateReq);
                } else {
                    GiveUpSchudeCarOrderRequest giveUpSchudeCarOrderRequest = new GiveUpSchudeCarOrderRequest();
                    giveUpSchudeCarOrderRequest.setSchudeCarNo(mobileStatusOperateReq.getScheducarno());
                    giveUpSchudeCarOrderRequest.setSchudeCarType(mobileStatusOperateReq.getTransportType());
                    giveUpSchudeCarOrderRequest.setTotalLevel(true);
                    logger.info("运输指派单拒绝参数-expreessOrderWebService:{}", JSON.toJSONString(giveUpSchudeCarOrderRequest));
                    giveUpSchudeCarOrderRequests.add(giveUpSchudeCarOrderRequest);
                    reqMap.put(mobileStatusOperateReq.getScheducarno(), mobileStatusOperateReq);
                }
                flag = 3;
                msgTo = 3;
            } else if (mobileStatusOperateReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL) {
                // 个人指派单
                // 修改BOOKING_FORM数据
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileStatusOperateReq.getBusiBookNo());
                bookingForm.setRevUser(null);
                bookingForm.setRevUserId(null);
                bookingForm.setRevDate(null);
                bookingForm.setRevCompanyId(null);
                bookingForm.setRevUserName(null);
                bookingForm.setBusiCtrl(OrderState.WAITRE_CEIVING.getValue());
                flag = bookingFormDao.updateByPrimaryKey(bookingForm);

//                GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
//                List<String> allBusNo = new ArrayList<>();
//                allBusNo.add(mobileStatusOperateReq.getBusiBookNo());
//                giOrderTraceResynced.setAllBusiNo(allBusNo);
//                giOrderTraceResynced.setProductType(bookingForm.getTransportType());
//                giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelAccept);
//                giOrderTraceResynced.setTsAct(new Date());
//                giOrderTraceResynced.setUserCode(mobileStatusOperateReq.getLoginAcctUserName());
//                giOrderTraceResynced.setLoginCode(mobileStatusOperateReq.getAcctUsername());
//                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
                //add by xugw20170821 订单取消后重新进入订单池，重新发送消息给中层
                PlaceAnOrderRes placeAnOrderRes = new PlaceAnOrderRes();
                placeAnOrderRes.setOrderId(bookingForm.getId());
                placeAnOrderRes.setRoleId(mobileStatusOperateReq.getRoleId());
                customerOrderService.notifyGps(placeAnOrderRes, null);
//            if (flag > 0) {
//                try {
//                    logger.info("取消投保单号：" + mobileStatusOperateReq.getBusiBookNo());
//                    customerOrderService.withdrawInsure(mobileStatusOperateReq.getBusiBookNo());
//                } catch (MobileStationBizException e) {
//                    logger.info("取消投保失败" + e.getMessage());
//                    // throw new MobileStationBizException("取消投保失败");
//                }
//            }
                msgTo = 1;
            } else if (mobileStatusOperateReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS) {
                // MS指派单或者咪站派车单
                // 获取当前订单信息
                MobileStationOrderDetailBean orderDetailBean = mobileMyOrderDao.queryOrderDetail(mobileStatusOperateReq
                        .getOrderId());
                if (orderDetailBean == null) {
                    CheckAssignOrderforbatchFailed failed = new CheckAssignOrderforbatchFailed();
                    failed.setBusibookno(org.apache.commons.lang3.StringUtils.isNotBlank(mobileStatusOperateReq.getBusiBookNo()) ? mobileStatusOperateReq.getBusiBookNo() : mobileStatusOperateReq.getScheducarno());
                    failed.setMsg("订单不存在");
                    faileds.add(failed);
                    continue;
//                    throw new MobileStationBizException("订单不存在");
                }
                // 修改指派原单订单状态
                List<MobileBookingForm> mobileBookingFormList = mobileStationOrderDao.getMobileBookingFormByBusiBookNo(
                        mobileStatusOperateReq.getBusiBookNo(), orderDetailBean.getCreateUser(), orderDetailBean.getCreateCompanyId()); // 查询指派前的订单
                if (mobileBookingFormList == null || mobileBookingFormList.size() < 1) {
                    CheckAssignOrderforbatchFailed failed = new CheckAssignOrderforbatchFailed();
                    failed.setBusibookno(mobileStatusOperateReq.getBusiBookNo());
                    failed.setMsg("订单不存在");
                    faileds.add(failed);
                    continue;
                }
                boolean isFlag = false;
                for (MobileBookingForm mobileBForm : mobileBookingFormList) {
                    // 原订单当前状态为已指派，待接单
                    if (mobileBForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                        //如果是MS指派给MS，拒绝后改为已发车，如果是咪站派车给MS，POP-M拒绝后改为已接单，M-POD或者M-W拒绝后改为取件成功
                        if (mobileBForm.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()
                                && MobileStationDefine.M.equals(mobileBForm.getStartLocus())) {
                            // 如果是咪站派车给MS，M-POD或者M-W拒绝后改为取件成功
                            mobileBForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                        } else if (mobileBForm.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()
                                && MobileStationDefine.M.equals(mobileBForm.getDestnLocus())) {
                            // 如果是咪站派车给MS，POP-M拒绝后改为已接单
                            mobileBForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                        } else {
                            // 如果是MS指派给MS，拒绝后改为已发车
                            if (mobileBForm.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                                mobileBForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                            } else {
                                mobileBForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN);
                            }
                        }
                        mobileBookingFormDao.updateByPrimaryKey(mobileBForm);
                        isFlag = true;
                        break;
                    }
                }
                // 修改转单记录状态，为拒绝
                List<MobileSingleCenter> mobileSingleCenters = mobileUserOrderDao.querySingleCenterByBusiNo(
                        mobileStatusOperateReq.getBusiBookNo(), orderDetailBean.getRevUser(),
                        MobileStationDefine.SINGLE_CENTER_TOACCEPT); // 更新A-POD
                if (mobileSingleCenters != null && mobileSingleCenters.size() > 0) {
                    MobileSingleCenter mobileSingleCenter = mobileSingleCenters.get(0);
                    mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_REFUSE);// 状态为拒绝
                    mobileSingleCenter.setRevDate(new Date());
                    mobileSingleCenterDao.updateByPrimaryKey(mobileSingleCenter);
                }

                if (isFlag) {
                    flag = 1;
                    // 更新MS指派单的狀態
                    MobileBookingForm mobileBookingForm = new MobileBookingForm();
                    mobileBookingForm.setId(orderDetailBean.getOrderId());
                    // 更新A-POD的订单状态為拒接
                    mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_REFUSE);
                    mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
                    // 如果是派车单，更新派车单状态为拒接
                    if (!StringUtil.isEmpty(mobileStatusOperateReq.getScheducarno())) {
                        // 更新子单为拒接
                        mobileScheduSubOrderDaoEx.updateStatusByMobileBookingFormId(mobileStatusOperateReq.getOrderId(),
                                MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER,
                                MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                    }
                } else {
                    CheckAssignOrderforbatchFailed failed = new CheckAssignOrderforbatchFailed();
                    failed.setBusibookno(org.apache.commons.lang3.StringUtils.isNotBlank(mobileStatusOperateReq.getBusiBookNo()) ? mobileStatusOperateReq.getBusiBookNo() : mobileStatusOperateReq.getScheducarno());
                    failed.setMsg("拒绝失败！");
                    faileds.add(failed);
                    continue;
                }
                msgTo = 2;
            }

            if (flag < 1) {
                CheckAssignOrderforbatchFailed failed = new CheckAssignOrderforbatchFailed();
                failed.setBusibookno(mobileStatusOperateReq.getBusiBookNo());
                failed.setMsg("拒绝失败！");
                faileds.add(failed);
                continue;
            }
            if (flag == 3) {
                logger.info("成败暂未判定");
                continue;
            }
            RefuseOrderSuccess refuseOrderSuccess = new RefuseOrderSuccess();
            refuseOrderSuccess.setMsgTo(msgTo);
            refuseOrderSuccess.setMobileStatusOperateReq(mobileStatusOperateReq);
            success.setReq(refuseOrderSuccess);
            successes.add(success);
        }
        Map<String, HubBatchResBean.DataBean> expressResMap = mStationRefusOrderList(mStationRevOrderRequests, giveUpSchudeCarOrderRequests);
        for (Map.Entry<String, MobileStatusOperateReq> entry : reqMap.entrySet()) {
            HubBatchResBean.DataBean expreessRes = expressResMap.get(entry.getKey());
            if (expreessRes.getRetCode().equals("0")) {
                CheckAssignOrderforbatchSuccess<RefuseOrderSuccess> success = new CheckAssignOrderforbatchSuccess<RefuseOrderSuccess>();
                RefuseOrderSuccess refuseOrderSuccess = new RefuseOrderSuccess();
                refuseOrderSuccess.setMsgTo(3);
                refuseOrderSuccess.setMobileStatusOperateReq(entry.getValue());
                success.setReq(refuseOrderSuccess);
                successes.add(success);
            } else {
                logger.info("运输指派单拒绝返回:" + expreessRes.getRetMsg());
                CheckAssignOrderforbatchFailed failed = new CheckAssignOrderforbatchFailed();
                failed.setBusibookno(entry.getKey());
                failed.setMsg(expreessRes.getRetMsg());
                faileds.add(failed);
            }
        }
        refuseOrderResult.setSuccesses(successes);
        refuseOrderResult.setFaileds(faileds);
        return refuseOrderResult;
    }

    @Override
    @Transactional
    public void doBatchRefuseOrder(List<CheckAssignOrderforbatchSuccess<RefuseOrderSuccess>> successes) {
        List<WaybillTraceOperateBean> waybillTraceOperateBeans = new ArrayList<>();
        for (CheckAssignOrderforbatchSuccess<RefuseOrderSuccess> success : successes) {
            RefuseOrderSuccess refuseOrderSuccess = success.getReq();
            MobileStatusOperateReq mobileStatusOperateReq = refuseOrderSuccess.getMobileStatusOperateReq();
            // 修改订单状态 状态从0到50
            MobileOrderOperateBean orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                    mobileStatusOperateReq.getOrderId(),
                    mobileStatusOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER,
                    MobileStationDefine.MOBILE_ORDER_STATUS_REFUSE);
            mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
            MsgIMReq msgIMReq = new MsgIMReq();
            try {
                PropertyUtils.copyProperties(msgIMReq, mobileStatusOperateReq);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            msgIMReq.setMsgTo(refuseOrderSuccess.getMsgTo());
            msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_REFUSE_ORDER);
            sendMsg(msgIMReq);// 推送消息

            WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
            if (StringUtils.isNotBlank(mobileStatusOperateReq.getScheducarno())) {
                waybillTraceOperateBean.setScheducarno(mobileStatusOperateReq.getScheducarno());
            } else {
                waybillTraceOperateBean.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
            }
            // 插入流程跟踪日志
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq
                    .getOrderId());
            waybillTraceOperateBean.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
            ComAccount account = comAccountService.queryAccountByAcctUsername(mobileStatusOperateReq.getAcctUsername());
            if (mobileBookingForm != null) {
                waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
                waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
            }
            waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
            waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_REJECT.getIntValue());
            if (account != null) {
                waybillTraceOperateBean.setRemark(SysAccountRole.getName(mobileStatusOperateReq.getRoleId().intValue()) + account.getRealName() + "已拒绝接单");
            } else {
                waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_REJECT.getName()
                        + mobileStatusOperateReq.getRefuseDesc());
            }
            waybillTraceOperateBean.setDispatchId(mobileStatusOperateReq.getOrderId());
            if (mobileStatusOperateReq.getRoleId() != null) {
                waybillTraceOperateBean.setRoleId(mobileStatusOperateReq.getRoleId());
            }
            waybillTraceOperateBeans.add(waybillTraceOperateBean);
//            mobileMyOrderService.insertWaybillTrace(waybillTraceOperateBean);
        }
        orderUtils.batchInsertWaybillTrace(waybillTraceOperateBeans);
    }

    /**
     * 查询推送规则
     *
     * @param baseReqBean
     * @return
     */
    @Override
    public QueryPushRuleResult queryPushRule(AppBaseRequest baseReqBean) throws MobileStationBizException, InvocationTargetException, IllegalAccessException {
        QueryPushRuleResult appBaseRequest = new QueryPushRuleResult(baseReqBean);
        org.apache.commons.beanutils.BeanUtils.copyProperties(appBaseRequest, baseReqBean);
        PushRole pushRole = mobileStationOrderDao.getPushRule(baseReqBean.getAccountId());
        PushRoleBean pushRoleBean = new PushRoleBean();
        if (pushRole == null || pushRole.getId() == null) {
            return appBaseRequest;
        } else {
            try {
                PropertyUtils.copyProperties(pushRoleBean, pushRole);
            } catch (Exception e) {
                e.printStackTrace();
                throw new MobileStationBizException("参数格式异常");
            }

            Map<String, ComCurrency> comCurrencyMap = comCurrencyService.queryForMap();
            if (!StringUtil.isEmpty(pushRoleBean.getCurrencyCode())
                    && comCurrencyMap.get(pushRoleBean.getCurrencyCode()) != null) {
                pushRoleBean.setCurrencyName(comCurrencyMap.get(pushRoleBean.getCurrencyCode()).getCurrencyCh());
            }
            appBaseRequest.setData(pushRoleBean);
        }

        return appBaseRequest;
    }

    /**
     * 设置推送规则
     *
     * @param setPushRuleReq
     * @return
     */
    @Override
    public AppBaseResult setPushRule(MobileStationSetPushRuleReq setPushRuleReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(setPushRuleReq);
        PushRole pushRole = mobileStationOrderDao.getPushRule(setPushRuleReq.getAccountId());
        int flag;
        if (pushRole == null || StringUtil.isEmpty(pushRole.getId())) {
            // 没有推送规则，新增
            pushRole = new PushRole();
            pushRole.setAccountId(setPushRuleReq.getAccountId());
            pushRole.setStartAddress(setPushRuleReq.getStartAddress());
            pushRole.setDestAddress(setPushRuleReq.getDestAddress());
            pushRole.setDestLatitude(setPushRuleReq.getDestLatitude());
            pushRole.setDestLongitude(setPushRuleReq.getDestLongitude());
            pushRole.setLengthValue(setPushRuleReq.getLengthValue());
            pushRole.setLengthUnit(setPushRuleReq.getLengthUnit());
            pushRole.setPriceMax(setPushRuleReq.getPriceMax());
            pushRole.setPriceMin(setPushRuleReq.getPriceMin());
            pushRole.setCurrencyCode(setPushRuleReq.getCurrencyCode());
            pushRole.setWeightMax(setPushRuleReq.getWeightMax());
            pushRole.setWeightMin(setPushRuleReq.getWeightMin());
            pushRole.setWeightUnit(setPushRuleReq.getWeightUnit());
            pushRole.setVolumeMax(setPushRuleReq.getVolumeMax());
            pushRole.setVolumeMin(setPushRuleReq.getVolumeMin());
            pushRole.setVolumeUnit(setPushRuleReq.getVolumeUnit());
            pushRole.setCreateUser(setPushRuleReq.getAcctUsername());
            pushRole.setCreateDate(new Date());
            pushRole.setPushStatus(true);
            pushRole.setExpressStatus(true);
            pushRole.setTransportStatus(true);
            // 记录推送规则
            flag = pushRoleDao.insert(pushRole);
        } else {
            // 有推送规则，覆盖
            pushRole.setAccountId(setPushRuleReq.getAccountId());
            pushRole.setStartAddress(setPushRuleReq.getStartAddress());
            pushRole.setDestAddress(setPushRuleReq.getDestAddress());
            pushRole.setDestLatitude(setPushRuleReq.getDestLatitude());
            pushRole.setDestLongitude(setPushRuleReq.getDestLongitude());
            pushRole.setLengthValue(setPushRuleReq.getLengthValue());
            pushRole.setLengthUnit(setPushRuleReq.getLengthUnit());
            pushRole.setPriceMax(setPushRuleReq.getPriceMax());
            pushRole.setPriceMin(setPushRuleReq.getPriceMin());
            pushRole.setCurrencyCode(setPushRuleReq.getCurrencyCode());
            pushRole.setWeightMax(setPushRuleReq.getWeightMax());
            pushRole.setWeightMin(setPushRuleReq.getWeightMin());
            pushRole.setWeightUnit(setPushRuleReq.getWeightUnit());
            pushRole.setVolumeMax(setPushRuleReq.getVolumeMax());
            pushRole.setVolumeMin(setPushRuleReq.getVolumeMin());
            pushRole.setVolumeUnit(setPushRuleReq.getVolumeUnit());
            pushRole.setCreateUser(setPushRuleReq.getAcctUsername());
            pushRole.setCreateDate(new Date());
            pushRole.setPushStatus(true);
            flag = pushRoleDao.updateByPrimaryKey(pushRole);
        }

        if (flag < 1) {
            throw new MobileStationBizException("设置推送规则失败!");
        }
        return baseResBean;
    }

    /**
     * 设置推送规则状态
     *
     * @param setPushStatusReq
     * @return
     */
    @Override
    public AppBaseResult setPushStatus(SetPushStatusReq setPushStatusReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(setPushStatusReq);
        PushRole pushRole = mobileStationOrderDao.getPushRule(setPushStatusReq.getAccountId());
        int flag;
        if (pushRole == null || StringUtil.isEmpty(pushRole.getId())) {
            // 没有推送规则，新增
            pushRole = new PushRole();
            pushRole.setAccountId(setPushStatusReq.getAccountId());
            pushRole.setCreateUser(setPushStatusReq.getAcctUsername());
            pushRole.setCreateDate(new Date());
            pushRole.setPushStatus(true);
            pushRole.setExpressStatus(setPushStatusReq.isExpressStatus());
            pushRole.setTransportStatus(setPushStatusReq.isTransportStatus());
            // 记录推送规则
            flag = pushRoleDao.insert(pushRole);
        } else {
            // 有推送规则，覆盖
            pushRole.setCreateUser(setPushStatusReq.getAcctUsername());
            pushRole.setCreateDate(new Date());
            pushRole.setPushStatus(true);
            pushRole.setExpressStatus(setPushStatusReq.isExpressStatus());
            pushRole.setTransportStatus(setPushStatusReq.isTransportStatus());
            flag = pushRoleDao.updateByPrimaryKey(pushRole);
        }

        if (flag < 1) {
            throw new MobileStationBizException("设置推送规则失败!");
        }
        return baseResBean;
    }

    /**
     * 获取签派广播单详情
     *
     * @param orderDetailReq
     * @return
     */
    private MobileStationOrderDetailBean setDetailBean(MobileStationOrderDetailReq orderDetailReq) {
        MobileStationOrderDetailBean detailBean = new MobileStationOrderDetailBean();
        // 获取订单信息
        BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(orderDetailReq.getDocId());
        // 获取签派信息
        RequestDetailResult requestDetailResult = mobileRecOrderWebService.getDispatchDetail(orderDetailReq
                .getDispatchID().longValue());
        if (bookingForm != null && requestDetailResult != null && requestDetailResult.getDispatchDetail() != null) {
            DispatchDetail dispatchDetail = requestDetailResult.getDispatchDetail();

            detailBean.setBookingDate(bookingForm.getBookingDate());
            detailBean.setBookingFormId(bookingForm.getId());
            detailBean.setBusiBookNo(bookingForm.getBusiBookNo());
            detailBean.setBusiCtrl(0);
            detailBean.setNeedInsure(bookingForm.getNeedInsure());
            detailBean.setPremiumValue(bookingForm.getPremiumValue());
            detailBean.setGoodsValue(bookingForm.getGoodsValue());
            detailBean.setTeamComsixNo(bookingForm.getTeamComsixNo());
            if (detailBean.getStartLocus().equals(MobileStationDefine.POP)) {
                detailBean.setOrderType(1);// 都是取件单
            } else {
                detailBean.setOrderType(2);// 都是派件单
            }
            detailBean.setOrderPrice(orderDetailReq.getOrderPrice());
            detailBean.setPredictValue(orderDetailReq.getPredictValue());
            detailBean.setCurrency(orderDetailReq.getCurrency());

            detailBean.setDestProvide(dispatchDetail.getCneeCustProvide());
            detailBean.setDestCity(dispatchDetail.getCneeCustCity());
            detailBean.setDestCounty(dispatchDetail.getCneeCustCounty());
            detailBean.setDestAddress(dispatchDetail.getCneeCustAddr());
            detailBean.setDestLatitude(dispatchDetail.getCneeLatitude());
            detailBean.setDestLongitude(dispatchDetail.getCneeLongitude());
            detailBean.setDestLinkMan(dispatchDetail.getCneeCustLinkMan());
            detailBean.setDestTel(dispatchDetail.getCneeCustLinkTel());

            detailBean.setStartProvide(dispatchDetail.getShipCustPrivide());
            detailBean.setStartCity(dispatchDetail.getShipCustCity());
            detailBean.setStartCounty(dispatchDetail.getShipCustCounty());
            detailBean.setStartAddress(dispatchDetail.getShipCustAddr());
            detailBean.setStartLatitude(dispatchDetail.getShipLatitude());
            detailBean.setStartLongitude(dispatchDetail.getShipLongitude());
            detailBean.setStartLinkMan(dispatchDetail.getShipCustLinkMan());
            detailBean.setStartTel(dispatchDetail.getShipCustLinkTel());

            detailBean.setPayType(dispatchDetail.getPayType());
            detailBean.setStartPayment(dispatchDetail.getGoodsPayment());
            detailBean.setStartCurrency(dispatchDetail.getGoodsPaymentCurr());
            detailBean.setDestPayment(dispatchDetail.getDestPayment());
            detailBean.setDestCurrency(dispatchDetail.getDestPaymentCurr());

            detailBean.setCreateUser(dispatchDetail.getCreateUser());
            detailBean.setCreateUserId((int) dispatchDetail.getCreateUserId());
            detailBean.setIsJs(0);
            detailBean.setComQuoteId(dispatchDetail.getComQuoteId() + "");
            detailBean.setQuotedType(dispatchDetail.getQuotedType());
            detailBean.setDispatchId(orderDetailReq.getDispatchID());
            detailBean.setOrderFrom(1);

            // 获取货物信息
            List<MobileGoodsDtl> mobileGoodsDtlList = mobileStationOrderDao
                    .queryGoodsDtlList(orderDetailReq.getDocId());
            MobileGoodsInfo mobileGoodsInfo;
            List<MobileGoodsInfo> mobileGoodsInfoList = new ArrayList();
            for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
                mobileGoodsInfo = new MobileGoodsInfo();
                mobileGoodsInfo.setGoodsType(mobileGoodsDtl.getGoodsType());
                mobileGoodsInfo.setGoodsName(mobileGoodsDtl.getGoodsName());
                mobileGoodsInfo.setGoodsQty(mobileGoodsDtl.getGoodsQty());
                mobileGoodsInfo.setGoodsQtyUnit(mobileGoodsDtl.getGoodsQtyUnit());
                if (null != mobileGoodsDtl.getGoodsVolume()) {
                    mobileGoodsInfo.setGoodsVolume(mobileGoodsDtl.getGoodsVolume());
                }
                mobileGoodsInfo.setGoodsVolumeUnit(mobileGoodsDtl.getGoodsVolumeUnit());
                mobileGoodsInfo.setGoodsWeight(mobileGoodsDtl.getGoodsWeight());
                mobileGoodsInfo.setGoodsWeightUnit(mobileGoodsDtl.getGoodsWeightUnit());

                if (null != mobileGoodsDtl.getGoodsLenght()) {
                    mobileGoodsInfo.setGoodsLength(mobileGoodsDtl.getGoodsLenght());
                }
                if (null != mobileGoodsDtl.getGoodsWide()) {
                    mobileGoodsInfo.setGoodsWide(mobileGoodsDtl.getGoodsWide());
                }
                if (null != mobileGoodsDtl.getGoodsHeight()) {
                    mobileGoodsInfo.setGoodsHeight(mobileGoodsDtl.getGoodsHeight());
                }
                mobileGoodsInfoList.add(mobileGoodsInfo);
            }
            detailBean.setGoodsInfoList(mobileGoodsInfoList);
        } else {
            return null;
        }
        return detailBean;
    }

    /**
     * 获取运输广播单详情
     *
     * @param orderDetailReq
     * @return
     */
    private MobileStationOrderDetailBean setTransportDetailBean(MobileStationOrderDetailReq orderDetailReq) {
        MobileStationOrderDetailBean detailBean = new MobileStationOrderDetailBean();

        List<MobileBookingForm> mobileBookingFormList = mobileStationOrderDao
                .getTransportDetailByScheducarno(orderDetailReq.getScheducarno());
        if (mobileBookingFormList != null && mobileBookingFormList.size() > 0) {

            detailBean.setBookingDate(mobileBookingFormList.get(0).getBookingDate());
            detailBean.setBookingFormId(mobileBookingFormList.get(0).getId());
            detailBean.setBusiBookNo(orderDetailReq.getScheducarno());
            detailBean.setBusiCtrl(0);
            // detailBean.setNeedInsure(mobileBookingFormList.get(0).getNeedInsure());
            // detailBean.setPremiumValue(mobileBookingFormList.get(0).getPremiumValue());
            // detailBean.setGoodsValue(mobileBookingFormList.get(0).getGoodsValue());
            detailBean.setTeamComsixNo(mobileBookingFormList.get(0).getTeamComsixNo());
            if (detailBean.getStartLocus().equals(MobileStationDefine.POP)) {
                detailBean.setOrderType(1);// 都是取件单
            } else {
                detailBean.setOrderType(2);// 都是派件单
            }
            if (orderDetailReq.getOrderPrice() != null) {
                detailBean.setOrderPrice(orderDetailReq.getOrderPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if (orderDetailReq.getPredictValue() != null) {
                detailBean.setPredictValue(orderDetailReq.getPredictValue().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            detailBean.setPredictCurr(orderDetailReq.getCurrency());
            detailBean.setCurrency(orderDetailReq.getCurrency());

            detailBean.setDestProvide(mobileBookingFormList.get(0).getCneeCustProvide());
            detailBean.setDestCity(mobileBookingFormList.get(0).getCneeCustCity());
            detailBean.setDestCounty(mobileBookingFormList.get(0).getCneeCustCounty());
            detailBean.setDestAddress(mobileBookingFormList.get(0).getCneeCustAddr());
            detailBean.setDestLatitude(mobileBookingFormList.get(0).getCneeLatitude());
            detailBean.setDestLongitude(mobileBookingFormList.get(0).getCneeLongitude());
            detailBean.setDestLinkMan(mobileBookingFormList.get(0).getCneeCustLinkMan());
            detailBean.setDestTel(mobileBookingFormList.get(0).getCneeCustLinkTel());

            detailBean.setStartProvide(mobileBookingFormList.get(0).getShipCustProvide());
            detailBean.setStartCity(mobileBookingFormList.get(0).getShipCustCity());
            detailBean.setStartCounty(mobileBookingFormList.get(0).getShipCustCounty());
            detailBean.setStartAddress(mobileBookingFormList.get(0).getShipCustAddr());
            detailBean.setStartLatitude(mobileBookingFormList.get(0).getShipLatitude());
            detailBean.setStartLongitude(mobileBookingFormList.get(0).getShipLongitude());
            detailBean.setStartLinkMan(mobileBookingFormList.get(0).getShipCustLinkMan());
            detailBean.setStartTel(mobileBookingFormList.get(0).getShipCustLinkTel());

            // detailBean.setPayType(mobileBookingFormList.get(0).getPayType());
            // detailBean.setStartPayment(mobileBookingFormList.get(0).getGoodsPayment());
            if (null != mobileBookingFormList.get(0).getGoodsPaymentCurr()) {
                detailBean.setStartCurrency(mobileBookingFormList.get(0).getGoodsPaymentCurr());
            }
            // detailBean.setDestPayment(mobileBookingFormList.get(0).getDestPayment());
            if (null != mobileBookingFormList.get(0).getDestPaymentCurr()) {
                detailBean.setDestCurrency(mobileBookingFormList.get(0).getDestPaymentCurr());
            }

            detailBean.setCreateUser(mobileBookingFormList.get(0).getCreateUser());
            detailBean.setIsJs(0);
            detailBean.setComQuoteId(mobileBookingFormList.get(0).getComQuoteId());
            detailBean.setQuotedType(mobileBookingFormList.get(0).getQuotedType());
            detailBean.setScheducarno(orderDetailReq.getScheducarno());
            detailBean.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST);

            detailBean.setGoodsValue(new BigDecimal(0));
            detailBean.setStartPayment(new BigDecimal(0));
            detailBean.setDestPayment(new BigDecimal(0));
            for (MobileBookingForm mobileBookingForm : mobileBookingFormList) {
                if (null != mobileBookingForm.getGoodsValue()) {
                    detailBean.setGoodsValue(detailBean.getGoodsValue().add(mobileBookingForm.getGoodsValue()));
                }
                if (null != mobileBookingForm.getGoodsPayment()) {
                    detailBean.setStartPayment(detailBean.getStartPayment().add(mobileBookingForm.getGoodsPayment()));
                }
                if (null != mobileBookingForm.getDestPayment()) {
                    detailBean.setDestPayment(detailBean.getDestPayment().add(mobileBookingForm.getDestPayment()));
                }
            }

            if (detailBean.getStartPayment().compareTo(BigDecimal.ZERO) > 0
                    && detailBean.getDestPayment().compareTo(BigDecimal.ZERO) > 0) {
                detailBean.setPayType(2);
            } else if (detailBean.getStartPayment().compareTo(BigDecimal.ZERO) > 0) {
                detailBean.setPayType(1);
            } else {
                detailBean.setPayType(0);
            }

            // 获取货物信息
            List<MobileGoodsDtl> mobileGoodsDtlList = mobileStationOrderDao
                    .queryGoodsDtlListByScheducarno(orderDetailReq.getScheducarno());
            MobileGoodsInfo mobileGoodsInfo;
            List<MobileGoodsInfo> mobileGoodsInfoList = new ArrayList();
            for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
                mobileGoodsInfo = new MobileGoodsInfo();
                mobileGoodsInfo.setGoodsType(mobileGoodsDtl.getGoodsType());
                mobileGoodsInfo.setGoodsName(mobileGoodsDtl.getGoodsName());
                mobileGoodsInfo.setGoodsQty(mobileGoodsDtl.getGoodsQty());
                mobileGoodsInfo.setGoodsQtyUnit(mobileGoodsDtl.getGoodsQtyUnit());
                if (null != mobileGoodsDtl.getGoodsVolume()) {
                    mobileGoodsInfo.setGoodsVolume(mobileGoodsDtl.getGoodsVolume());
                }
                mobileGoodsInfo.setGoodsVolumeUnit(mobileGoodsDtl.getGoodsVolumeUnit());
                mobileGoodsInfo.setGoodsWeight(mobileGoodsDtl.getGoodsWeight());
                mobileGoodsInfo.setGoodsWeightUnit(mobileGoodsDtl.getGoodsWeightUnit());

                if (null != mobileGoodsDtl.getGoodsLenght()) {
                    mobileGoodsInfo.setGoodsLength(mobileGoodsDtl.getGoodsLenght());
                }
                if (null != mobileGoodsDtl.getGoodsWide()) {
                    mobileGoodsInfo.setGoodsWide(mobileGoodsDtl.getGoodsWide());
                }
                if (null != mobileGoodsDtl.getGoodsHeight()) {
                    mobileGoodsInfo.setGoodsHeight(mobileGoodsDtl.getGoodsHeight());
                }
                mobileGoodsInfoList.add(mobileGoodsInfo);
            }
            detailBean.setGoodsInfoList(mobileGoodsInfoList);
        } else {
            return null;
        }
        return detailBean;
    }

    /**
     * 获取个人广播单详情
     *
     * @param orderDetailReq
     * @return
     */
    private MobileStationOrderDetailBean setPersonalDetailBean(MobileStationOrderDetailReq orderDetailReq) {
        MobileStationOrderDetailBean detailBean = new MobileStationOrderDetailBean();

        List<MobileBookingForm> mobileBookingFormList = mobileStationOrderDao
                .getTransportDetailByScheducarno(orderDetailReq.getScheducarno());
        if (mobileBookingFormList != null && mobileBookingFormList.size() > 0) {

            detailBean.setBookingDate(mobileBookingFormList.get(0).getBookingDate());
            detailBean.setBookingFormId(mobileBookingFormList.get(0).getId());
            detailBean.setBusiBookNo(orderDetailReq.getScheducarno());
            detailBean.setBusiCtrl(0);
            // detailBean.setNeedInsure(mobileBookingFormList.get(0).getNeedInsure());
            // detailBean.setPremiumValue(mobileBookingFormList.get(0).getPremiumValue());
            // detailBean.setGoodsValue(mobileBookingFormList.get(0).getGoodsValue());
            detailBean.setTeamComsixNo(mobileBookingFormList.get(0).getTeamComsixNo());
            if (detailBean.getStartLocus().equals(MobileStationDefine.POP)) {
                detailBean.setOrderType(1);// 都是取件单
            } else {
                detailBean.setOrderType(2);// 都是派件单
            }
            if (orderDetailReq.getOrderPrice() != null) {
                detailBean.setOrderPrice(orderDetailReq.getOrderPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if (orderDetailReq.getPredictValue() != null) {
                detailBean.setPredictValue(orderDetailReq.getPredictValue().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            detailBean.setPredictCurr(orderDetailReq.getCurrency());
            detailBean.setCurrency(orderDetailReq.getCurrency());

            detailBean.setDestProvide(mobileBookingFormList.get(0).getCneeCustProvide());
            detailBean.setDestCity(mobileBookingFormList.get(0).getCneeCustCity());
            detailBean.setDestCounty(mobileBookingFormList.get(0).getCneeCustCounty());
            detailBean.setDestAddress(mobileBookingFormList.get(0).getCneeCustAddr());
            detailBean.setDestLatitude(mobileBookingFormList.get(0).getCneeLatitude());
            detailBean.setDestLongitude(mobileBookingFormList.get(0).getCneeLongitude());
            detailBean.setDestLinkMan(mobileBookingFormList.get(0).getCneeCustLinkMan());
            detailBean.setDestTel(mobileBookingFormList.get(0).getCneeCustLinkTel());

            detailBean.setStartProvide(mobileBookingFormList.get(0).getShipCustProvide());
            detailBean.setStartCity(mobileBookingFormList.get(0).getShipCustCity());
            detailBean.setStartCounty(mobileBookingFormList.get(0).getShipCustCounty());
            detailBean.setStartAddress(mobileBookingFormList.get(0).getShipCustAddr());
            detailBean.setStartLatitude(mobileBookingFormList.get(0).getShipLatitude());
            detailBean.setStartLongitude(mobileBookingFormList.get(0).getShipLongitude());
            detailBean.setStartLinkMan(mobileBookingFormList.get(0).getShipCustLinkMan());
            detailBean.setStartTel(mobileBookingFormList.get(0).getShipCustLinkTel());

            // detailBean.setPayType(mobileBookingFormList.get(0).getPayType());
            // detailBean.setStartPayment(mobileBookingFormList.get(0).getGoodsPayment());
            if (null != mobileBookingFormList.get(0).getGoodsPaymentCurr()) {
                detailBean.setStartCurrency(mobileBookingFormList.get(0).getGoodsPaymentCurr());
            }
            // detailBean.setDestPayment(mobileBookingFormList.get(0).getDestPayment());
            if (null != mobileBookingFormList.get(0).getDestPaymentCurr()) {
                detailBean.setDestCurrency(mobileBookingFormList.get(0).getDestPaymentCurr());
            }

            detailBean.setCreateUser(mobileBookingFormList.get(0).getCreateUser());
            detailBean.setIsJs(0);
            detailBean.setComQuoteId(mobileBookingFormList.get(0).getComQuoteId());
            detailBean.setQuotedType(mobileBookingFormList.get(0).getQuotedType());
            detailBean.setScheducarno(orderDetailReq.getScheducarno());
            detailBean.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST);

            detailBean.setGoodsValue(new BigDecimal(0));
            detailBean.setStartPayment(new BigDecimal(0));
            detailBean.setDestPayment(new BigDecimal(0));
            for (MobileBookingForm mobileBookingForm : mobileBookingFormList) {
                if (null != mobileBookingForm.getGoodsValue()) {
                    detailBean.setGoodsValue(detailBean.getGoodsValue().add(mobileBookingForm.getGoodsValue()));
                }
                if (null != mobileBookingForm.getGoodsPayment()) {
                    detailBean.setStartPayment(detailBean.getStartPayment().add(mobileBookingForm.getGoodsPayment()));
                }
                if (null != mobileBookingForm.getDestPayment()) {
                    detailBean.setDestPayment(detailBean.getDestPayment().add(mobileBookingForm.getDestPayment()));
                }
            }

            if (detailBean.getStartPayment().compareTo(BigDecimal.ZERO) > 0
                    && detailBean.getDestPayment().compareTo(BigDecimal.ZERO) > 0) {
                detailBean.setPayType(2);
            } else if (detailBean.getStartPayment().compareTo(BigDecimal.ZERO) > 0) {
                detailBean.setPayType(1);
            } else {
                detailBean.setPayType(0);
            }

            // 获取货物信息
            List<MobileGoodsDtl> mobileGoodsDtlList = mobileStationOrderDao
                    .queryGoodsDtlListByScheducarno(orderDetailReq.getScheducarno());
            MobileGoodsInfo mobileGoodsInfo;
            List<MobileGoodsInfo> mobileGoodsInfoList = new ArrayList();
            for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
                mobileGoodsInfo = new MobileGoodsInfo();
                mobileGoodsInfo.setGoodsType(mobileGoodsDtl.getGoodsType());
                mobileGoodsInfo.setGoodsName(mobileGoodsDtl.getGoodsName());
                mobileGoodsInfo.setGoodsQty(mobileGoodsDtl.getGoodsQty());
                mobileGoodsInfo.setGoodsQtyUnit(mobileGoodsDtl.getGoodsQtyUnit());
                if (null != mobileGoodsDtl.getGoodsVolume()) {
                    mobileGoodsInfo.setGoodsVolume(mobileGoodsDtl.getGoodsVolume());
                }
                mobileGoodsInfo.setGoodsVolumeUnit(mobileGoodsDtl.getGoodsVolumeUnit());
                mobileGoodsInfo.setGoodsWeight(mobileGoodsDtl.getGoodsWeight());
                mobileGoodsInfo.setGoodsWeightUnit(mobileGoodsDtl.getGoodsWeightUnit());

                if (null != mobileGoodsDtl.getGoodsLenght()) {
                    mobileGoodsInfo.setGoodsLength(mobileGoodsDtl.getGoodsLenght());
                }
                if (null != mobileGoodsDtl.getGoodsWide()) {
                    mobileGoodsInfo.setGoodsWide(mobileGoodsDtl.getGoodsWide());
                }
                if (null != mobileGoodsDtl.getGoodsHeight()) {
                    mobileGoodsInfo.setGoodsHeight(mobileGoodsDtl.getGoodsHeight());
                }
                mobileGoodsInfoList.add(mobileGoodsInfo);
            }
            detailBean.setGoodsInfoList(mobileGoodsInfoList);
        } else {
            return null;
        }
        return detailBean;
    }


    private String pushMessageIM(Map<String, String> mapObject, String createUser, String appTag) {
        StringBuffer sb = new StringBuffer("<message>");
        sb.append("<fromusername>").append(appTag).append("</fromusername>");
        sb.append("<pushType>PUSH_CS_ORDER_000001</pushType>");
        sb.append("<pushTargetUser>2</pushTargetUser>");
        sb.append("<title>客户下单_应答通知</title>");
        sb.append("<text>" + JSONObject.toJSON(mapObject).toString() + "</text>");
        sb.append("<extend_a>extend_a</extend_a>");
        sb.append("<extend_b>extend_b</extend_b>");
        sb.append("<extend_c>extend_c</extend_c>");
        sb.append("<extend_d>extend_d</extend_d>");
        sb.append("<extend_e>extend_e</extend_e>");
        sb.append("<extend_f>extend_f</extend_f>");
        sb.append("<extend_g>extend_g</extend_g>");
        sb.append("<extend_h>extend_h</extend_h>");
        sb.append("<extend_i>extend_i</extend_i>");
        sb.append("<extend_j>extend_j</extend_j>");
        sb.append("<pushmember>[{\"user\":\"").append(createUser).append("\"}]</pushmember>");
        sb.append("</message>");
        String resultStr = HttpClientUtil.xmlPost(imPushUrl, HeadAuthentication.setIMHead(CustomerDefine.APP_TAG),
                sb.toString());
        return resultStr;
    }

    @Override
    public BaseResBean queryScheducarOrderDetail(MobileStationOrderDetailReq orderDetailReq)
            throws MobileStationBizException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MobileStationAcceptOrderCustomResult batchAcceptOrder(MobileStationBatchAcceptOrderReq acceptOrderReq) throws MobileStationBizException {
        MobileStationAcceptOrderCustomResult res = new MobileStationAcceptOrderCustomResult();
        for (MobileStationAcceptOrderCustomReq msaocr : acceptOrderReq.getMobileStationAcceptOrderReqList()) {
            try {
                MobileStationAcceptOrderReq mobileStationAcceptOrderReq = new MobileStationAcceptOrderReq();
                PropertyUtils.copyProperties(mobileStationAcceptOrderReq, msaocr);
                if (null != acceptOrderReq.getRoleId()) {
                    mobileStationAcceptOrderReq.setRoleId(acceptOrderReq.getRoleId());
                }
                if (null != acceptOrderReq.getAppLoginInfo()) {
                    mobileStationAcceptOrderReq.setAppLoginInfo(acceptOrderReq.getAppLoginInfo());
                }
                if (acceptOrderReq.getAccountId() != null) {
                    mobileStationAcceptOrderReq.setAccountId(acceptOrderReq.getAccountId());
                }
                if (!StringUtil.isEmpty(acceptOrderReq.getAcctUsername())) {
                    mobileStationAcceptOrderReq.setAcctUsername(acceptOrderReq.getAcctUsername());
                }
                mobileStationAcceptOrderReq.setAssignUserId(acceptOrderReq.getAssignUserId());
                mobileStationAcceptOrderReq.setAssignUser(acceptOrderReq.getAssignUser());
                mobileStationAcceptOrderReq.setAssignDate(acceptOrderReq.getAssignDate());
                mobileStationAcceptOrderReq.setCompanyAccountId(acceptOrderReq.getCompanyAccountId());
                AppBaseResult appBaseResult = acceptOrder(mobileStationAcceptOrderReq);
                if (null != appBaseResult && appBaseResult.getRetCode() != SysResult.SUCCESS.getValue()) {
                    msaocr.setErrMsg(appBaseResult.getRetMsg());
                }
            } catch (Exception e) {
                msaocr.setErrMsg(e.getMessage());
            }
            if (StringUtil.isEmpty(msaocr.getErrMsg())) {
                if (res.getSuccessList() == null) {
                    List<MobileStationAcceptOrderCustomReq> succTemp = new ArrayList<>();
                    succTemp.add(msaocr);
                    res.setSuccessList(succTemp);
                } else {
                    List<MobileStationAcceptOrderCustomReq> succTemp = res.getSuccessList();
                    succTemp.add(msaocr);
                    res.setSuccessList(succTemp);
                }
            } else {
                if (res.getFailList() == null) {
                    List<MobileStationAcceptOrderCustomReq> failTemp = new ArrayList<>();
                    failTemp.add(msaocr);
                    res.setFailList(failTemp);
                } else {
                    List<MobileStationAcceptOrderCustomReq> failTemp = res.getFailList();
                    failTemp.add(msaocr);
                    res.setFailList(failTemp);
                }
            }

        }

        if (res.getSuccessList() != null) {
            res.setSuccessCount(res.getSuccessList().size());
        } else {
            res.setSuccessCount(0);
        }
        if (res.getFailList() != null) {
            res.setFailCount(res.getFailList().size());
        } else {
            res.setFailCount(0);
        }
        return res;
    }


    /**
     * 批量取消广播单
     *
     * @param batchOperateReq
     * @return
     */
    @Override
    public BatchOperateResult batchCancelOrder(BatchOperateReq batchOperateReq) throws MobileStationBizException {
        BatchOperateResult batchOperateResult = new BatchOperateResult(batchOperateReq);
        try {
            List<GiBizOrder> giBizOrderList = new ArrayList<>();
            List<OperateResBean> operateResBeanList = new ArrayList<>();
            int destBusiCtrl;
            if (batchOperateReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue() || batchOperateReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                //咪站、快递员广播单取消  22->40
                destBusiCtrl = MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS;
            } else {
                //司机广播单取消 22->40
                destBusiCtrl = MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN;
            }
            for (OperateReqBean operateReqBean : batchOperateReq.getDepatReqBeanList()) {
                OperateResBean operateResBean = new OperateResBean();
                GiBizOrder giBizOrder = new GiBizOrder();
                giBizOrder.setAppTag(CustomerDefine.IM_MS_DEFINE);
                giBizOrder.setAction(MobileStationDefine.GPS_ACTION_DELETE);
                giBizOrder.setDocNo(operateReqBean.getBusiBookNo());
                giBizOrderList.add(giBizOrder);
                //广播取消，修改订单状态
                int flag = mobileBookingFormDaoEx.updateOrderBusi(operateReqBean.getOrderId(), MobileStationDefine.MOBILE_ORDER_STATUS_BROADCAST, destBusiCtrl);
                if (flag > 0) {
                    operateResBean.setRetCode(SystemDefine.FAILURE);
                    operateResBean.setRetMsg("广播单已经被接单不能取消！");
                }
                operateResBeanList.add(operateResBean);
            }
            batchOperateResult.setData(operateResBeanList);
            logger.info("7、23 batchCancelOrder Order sendBroadCastOrderMessage ={}", JSONArray.toJSONString(giBizOrderList));
            gpsOrderService.sendBroadCastOrderMessage(giBizOrderList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return batchOperateResult;
    }

    /**
     * 获取用户待执行的商管指派单个数
     *
     * @param acctUserName
     * @param roleId
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public int getMerchantOrderCount(String acctUserName, Integer roleId) {
        return mobileBookingFormDaoEx.getMerchantOrderCount(acctUserName, roleId);
    }

    /**
     * 商管中心 批量指派快递员接单
     *
     * @param acceptOrderReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public MobileStationAcceptOrderCustomResult batchAssignOrder(MobileStationBatchAcceptOrderReq acceptOrderReq) throws MobileStationBizException {
        return null;
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
}
