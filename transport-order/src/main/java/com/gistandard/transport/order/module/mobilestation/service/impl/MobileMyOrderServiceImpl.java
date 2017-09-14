package com.gistandard.transport.order.module.mobilestation.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.order.bean.*;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.bean.MobileScheduSubOrder;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockBaseReq;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockInOutReq;
import com.gistandard.transport.order.module.mobilestation.bean.userorder.AssignOrderBean;
import com.gistandard.transport.order.module.mobilestation.bean.userorder.BatchAssignReq;
import com.gistandard.transport.order.module.mobilestation.dao.MobileAcceptOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.stock.MobileStationStockDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileStationOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileUserOrderService;
import com.gistandard.transport.order.module.service.StatsBizOrderService;
import com.gistandard.transport.order.stock.service.MobileStockService;
import com.gistandard.transport.order.utils.OrderUtils;
import com.gistandard.transport.order.webservice.client.merchant.order.*;
import com.gistandard.transport.order.webservice.client.order.hub.*;
import com.gistandard.transport.quote.system.common.bean.ComQuotePriceBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.quote.system.database.bean.CheckAssignOrderforbatchFailed;
import com.gistandard.transport.quote.system.database.bean.QueryBatchPlatformQuote2Result;
import com.gistandard.transport.quote.system.database.bean.QueryPlatformQuote2Req;
import com.gistandard.transport.quote.system.database.services.ComQuoteService;
import com.gistandard.transport.quote.system.database.services.ExpressService;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.frame.disruptor.DisruptorHelper;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.system.webservice.client.calcWebService.*;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
import com.gistandard.transport.system.webservice.client.payinfo.*;
import com.gistandard.transport.tools.util.DateUtil;
import com.gistandard.transport.tools.util.ImPushUtil;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Exception;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.ArrayList;

/**
 * @author xgw
 */
@Service
public class MobileMyOrderServiceImpl implements MobileMyOrderService {

    private static final Logger logger = LoggerFactory.getLogger(MobileMyOrderServiceImpl.class);

    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;
    @Autowired
    private ComProvinceService comProvinceService;
    @Autowired
    private ComCityService comCityService;
    @Autowired
    private PnWebService pnWebService;
    @Autowired
    private ComCountyService comCountyService;
    @Autowired
    private ComCurrencyService comCurrencyService;
    @Autowired
    private ComUnitService comUnitService;
    @Autowired
    private ComQuoteService comQuoteService;
    @Autowired
    private ComWaybillTraceService comWaybillTraceService;
    @Autowired
    private ComAccountService comAccountService;
    @Autowired
    private MobileStationStockDao mobileStationStockDao;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private BookingFormDao bookingFormDao;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private MobileStationOrderDao mobileStationOrderDao;
    @Autowired
    private MobileAcceptOrderDao mobileAcceptOrderDao;
    @Autowired
    private MobileStockService mobileStockService;
    @Autowired
    private ExpreessOrderWebService expreessOrderWebService;
    @Autowired
    private MobileGoodsDtlDao mobileGoodsDtlDao;
    @Autowired
    private MobileGoodsDtlDaoEx mobileGoodsDtlDaoEx;
    @Autowired
    private ComAccountRoleRelDaoEx accountRoleRelDaoEx;
    @Autowired
    private StatsBizOrderService statsBizOrderService;
    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;
    @Autowired
    private MobileSingleCenterDao mobileSingleCenterDao;
    @Autowired
    private MobileSingleCenterDaoEx mobileSingleCenterDaoEx;
    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;
    @Autowired
    private MobileStationOrderService mobileStationOrderService;
    @Autowired
    private ComGoodsTypeService comGoodsTypeService;
    @Autowired
    private BillingFormSalmDaoEx billingFormSalmDaoEx;
    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;
    @Autowired
    private ComVehicleInfoDaoEx comVehicleInfoDaoEx;
    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;
    @Autowired
    private ComAccountCategoryDaoEx comAccountCategoryDaoEx;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private ComCountyDaoEx comCountyDaoEx;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private GpsLogService gpsLogService;
    @Autowired
    private GpsOrderService gpsOrderService;
    @Autowired
    private OrderUtils orderUtils;
    @Autowired
    private MobileUserOrderService mobileUserOrderService;
    @Autowired
    private MobileOrderOperateDao mobileOrderOperateDao;

    @Autowired
    private QueryCalcManagerWebService queryCalcManagerWebService;

    @Value("#{spring.sysGFCode}")
    private String sysGFCode;   //平台公布价结算时的平台账号

    /**
     * MS3.0 获取订单管理-我的订单(商户订单)列表
     *
     * @param myOrderListReq
     * @throws Exception
     */
    @Override
    public QueryMyOrderListResult queryMyOrderList(MobileMyOrderListReq myOrderListReq) throws MobileStationBizException {
        QueryMyOrderListResult baseResPageBean = new QueryMyOrderListResult();
        if (null == myOrderListReq.getOrderStatue() || myOrderListReq.getOrderStatue() < 1
                || myOrderListReq.getOrderStatue() > 4) {
            baseResPageBean.setRetCode(SystemDefine.FAILURE);
            baseResPageBean.setRetMsg("查询参数错误！");
            return baseResPageBean;
        }
        logger.info(JSON.toJSONString(myOrderListReq));
        // 查询订单总条数
        int recordCount = 0;
        if (myOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
            recordCount = mobileMyOrderDao.getMiOrderListCount(myOrderListReq);
        } else {
            recordCount = mobileMyOrderDao.getMyOrderListCount(myOrderListReq);
        }

        List<MobileStationOrderListBean> orderListBeans;
        List<MobileMyOrderListBean> myOrderListBeans = new ArrayList<>();
        try {
            if (recordCount > 0) {
                // 根据条件查询订单列表
                if (myOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                    orderListBeans = mobileMyOrderDao.queryMiOrderList(myOrderListReq);
                    logger.info("咪站商户订单列表查询  ， 入参：{} ", JSON.toJSONString(myOrderListReq));
                } else {
                    orderListBeans = mobileMyOrderDao.queryMyOrderList(myOrderListReq);
                    logger.info("商户订单列表查询  ， 入参：{} ", JSON.toJSONString(myOrderListReq));
                }
                // 根据条件查询订单列表
                Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                Map<String, ComCity> comCityMap = comCityService.queryForMap();
                Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
                Map<String, ComCurrency> comCurrencyMap = comCurrencyService.queryForMap();
                Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
                Map<String, ComGoodsType> comGoodsTypeMap = comGoodsTypeService.queryForMap();
                for (MobileStationOrderListBean orderListBean : orderListBeans) {
                    MobileMyOrderListBean myOrderListBean = new MobileMyOrderListBean();
                    try {
                        PropertyUtils.copyProperties(myOrderListBean, orderListBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new MobileStationBizException("参数格式异常");
                    }
                    // 设置订单入库状态
                    if (orderListBean.getFlag() == null) {
                        myOrderListBean.setStockFlag(false);
                    } else {
                        myOrderListBean.setStockFlag(true);
                    }
                    // 判断是寄付还是到付
                    if (1 == orderListBean.getPayType()) {
                        // 到付
                        myOrderListBean.setOrderPrice(orderListBean.getDestPayment());
                        myOrderListBean.setCurrency(orderListBean.getDestCurrency());
                    } else {
                        // 寄付
                        myOrderListBean.setOrderPrice(orderListBean.getStartPayment());
                        myOrderListBean.setCurrency(orderListBean.getStartCurrency());
                    }
                    // 设置币值
                    if (!StringUtil.isEmpty(orderListBean.getCurrency())
                            && comCurrencyMap.get(orderListBean.getCurrency()) != null) {
                        myOrderListBean.setCurrency(comCurrencyMap.get(orderListBean.getCurrency()).getCurrencyCh());
                    }
                    if (!StringUtil.isEmpty(orderListBean.getPredictCurr())
                            && comCurrencyMap.get(orderListBean.getPredictCurr()) != null) {
                        myOrderListBean.setPredictCurr(comCurrencyMap.get(orderListBean.getPredictCurr())
                                .getCurrencyCh());
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
                    myOrderListBean.setStartAddress(startAddress + orderListBean.getStartAddress());

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
                    myOrderListBean.setDestAddress(destAddress + orderListBean.getDestAddress());

                    // 设置描述信息
                    String description = "";
                    String weightUtil = "";
                    if (StringUtil.isEmpty(orderListBean.getScheducarno())) {
                        for (MobileGoodsInfo goodsInfo : orderListBean.getGoodsInfoList()) {
                            if (goodsInfo.getGoodsType() != null) {
                                String typeName = goodsInfo.getGoodsType();
                                if (comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                                    typeName = comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh();
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
                                description += "体积：" + goodsInfo.getGoodsLength().setScale(2, BigDecimal.ROUND_HALF_UP)
                                        + "*" + goodsInfo.getGoodsWide().setScale(2, BigDecimal.ROUND_HALF_UP) + "*"
                                        + goodsInfo.getGoodsHeight().setScale(2, BigDecimal.ROUND_HALF_UP) + "立方厘米";
                            }
                            description += " ";
                            //货物类型名称
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsType()) && comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                                goodsInfo.setGoodsTypeName(comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh());
                            }
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit()) && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                                goodsInfo.setGoodsQtyUnitName(comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh());
                            }
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit()) && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                                goodsInfo.setGoodsWeightUnitName(comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh());
                            }
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsVolumeUnit()) && comUnitMap.get(goodsInfo.getGoodsVolumeUnit()) != null) {
                                goodsInfo.setGoodsVolumeUnitName(comUnitMap.get(goodsInfo.getGoodsVolumeUnit()).getUnitCh());
                            }
                        }
                        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(orderListBean.getBusiBookNo());
                        if (bookingForm != null) {
                            myOrderListBean.setShipAddr(bookingForm.getCarriageReceiAddr());
                            if (StringUtil.isEmpty(bookingForm.getExpressOrderNo())) {
                                myOrderListBean.setStydFlag(false);
                            } else {
                                myOrderListBean.setStydFlag(true);
                            }
                        }
                    } else {
                        //设置子订单列表
                        List<MobileScheduSubOrder> subOrderList = mobileMyOrderDao.selectMobileSubOrderByMobileId(myOrderListBean.getOrderId());
                        if (subOrderList != null && subOrderList.size() > 0) {
                            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrderList.get(0).getBusiBookNo());
                            if (bookingForm != null) {
                                myOrderListBean.setShipAddr(bookingForm.getCarriageReceiAddr());
                                MobileScheduSubOrder tmp = subOrderList.get(0);
                                tmp.setShipCustAddr(bookingForm.getShipCustaDdr());
                                subOrderList.set(0, tmp);
                            }
                            String subOrderListJsonStr = JSON.toJSONString(subOrderList);
                            myOrderListBean.setSubOrderList(JSON.parseArray(subOrderListJsonStr, com.gistandard.transport.app.dubbo.order.bean.MobileScheduSubOrder.class));// 设置子派车单
                        }

                        BigDecimal temp = new BigDecimal(0);
                        for (MobileGoodsInfo goodsInfo : orderListBean.getGoodsInfoList()) {
                            if (goodsInfo.getGoodsWeight() != null) {
                                if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit())
                                        && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                                    weightUtil = comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh();
                                }
                                temp = temp.add(goodsInfo.getGoodsWeight());
                            }

                            //货物类型名称
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsType()) && comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                                goodsInfo.setGoodsTypeName(comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh());
                            }
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit()) && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                                goodsInfo.setGoodsQtyUnitName(comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh());
                            }
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit()) && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                                goodsInfo.setGoodsWeightUnitName(comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh());
                            }
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsVolumeUnit()) && comUnitMap.get(goodsInfo.getGoodsVolumeUnit()) != null) {
                                goodsInfo.setGoodsVolumeUnitName(comUnitMap.get(goodsInfo.getGoodsVolumeUnit()).getUnitCh());
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
                    myOrderListBean.setDescription(description);


                    //咪站的订单列表
                    if (myOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                        //判断是否需要签派,只有咪站，并且目的地是POD才可以签派
                        myOrderListBean.setDispatchFlag(false);
                        if (MobileStationDefine.M.equals(myOrderListBean.getStartLocus())
                                && MobileStationDefine.POD.equals(myOrderListBean.getDestnLocus())
                                && myOrderListBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                                && !myOrderListBean.getOrderFrom().equals(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU)
                                && !myOrderListBean.getOrderFrom().equals(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST)) {
                            myOrderListBean.setDispatchFlag(true);
                        }

                        //设置咪站指派的MS信息
                        if (myOrderListBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE) {
                            MobileSingleCenter mobileSingleCenter = mobileMyOrderDao.querySingleCenter(myOrderListBean.getOrderId(),
                                    MobileStationDefine.SINGLE_CENTER_ACCEPT,
                                    myOrderListReq.getAccountId());
                            if (mobileSingleCenter != null && mobileSingleCenter.getRevUserId() != null) {
                                ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(mobileSingleCenter.getRevUserId());
                                if (comUserinfo != null) {
                                    myOrderListBean.setMsLinkName(comUserinfo.getRealName());
                                    myOrderListBean.setMsLinkTel(comUserinfo.getTelephone());
                                }
                                if (myOrderListReq.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()) {
                                    //如果是车主，设置车牌号
                                    List<ComVehicleInfo> comVehicleInfoList = comVehicleInfoDaoEx.queryVehicleByAcctId(mobileSingleCenter.getRevUserId());
                                    if (comVehicleInfoList != null && comVehicleInfoList.size() > 0) {
                                        myOrderListBean.setMsLinkCarNo(comVehicleInfoList.get(0).getTruckcode());
                                    }
                                }
                            }
                        }

                        //咪站 M-POD 展示目的地市、区；M-W 展示W站点名称
                        String destAddressName = "";
                        if (myOrderListBean.getDestnLocus().equals(MobileStationDefine.POD)) {
                            //咪站到POD
                            if (!StringUtil.isEmpty(orderListBean.getDestCity()) && comCityMap.get(orderListBean.getDestCity()) != null) {
                                destAddressName += comCityMap.get(orderListBean.getDestCity()).getName();
                            }
                            if (!StringUtil.isEmpty(orderListBean.getDestCounty()) && comCountyMap.get(orderListBean.getDestCounty()) != null) {
                                destAddressName += " " + comCountyMap.get(orderListBean.getDestCounty()).getAreaName();
                            }
                        } else {
                            //咪站到W站，根据W站code获取W站名称
                            ComCustomer comCustomer = comCustomerDaoEx.getComCustomerByCustTtl(orderListBean.getDestnLocus());
                            if (comCustomer != null) {
                                destAddressName = comCustomer.getCustName();
                            }
                        }
                        myOrderListBean.setDestAddressName(destAddressName);
                    }

                    myOrderListBeans.add(myOrderListBean);
                }
            }
            baseResPageBean.setRecordCount(recordCount);
            baseResPageBean.setData(myOrderListBeans);
        } catch (Exception e) {
            e.printStackTrace();
            baseResPageBean.setRecordCount(0);
            baseResPageBean.setRetCode(SystemDefine.FAILURE);
            baseResPageBean.setRetMsg("查询订单列表异常！");
        }
        baseResPageBean.setReqId(myOrderListReq.getReqId());
        return baseResPageBean;
    }

    /**
     * MS3.0 获取订单管理-我的订单详细
     *
     * @param orderDetailReq
     * @throws Exception
     */
    @Override
    public QueryMyOrderDetailResult queryMyOrderDetail(MobileMyOrderDetailReq orderDetailReq) throws MobileStationBizException {
        QueryMyOrderDetailResult baseResBean = new QueryMyOrderDetailResult(orderDetailReq);
        MobileStationOrderDetailBean detailBean = null;
        if (null != orderDetailReq.getOrderId()) {
            detailBean = mobileAcceptOrderDao.queryOrderDetail(orderDetailReq.getOrderId());
        }
        if (detailBean == null) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("该订单不存在！");
        } else {
            if (detailBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                // 签派待接单，需要显示待接单HUB信息
                MobileSingleCenter mobileSingleCenter = mobileMyOrderDao.querySingleCenter(orderDetailReq.getOrderId(),
                        MobileStationDefine.SINGLE_CENTER_TOACCEPT,
                        orderDetailReq.getAccountId());
                if (null != mobileSingleCenter) {
                    detailBean.setAssignDate(mobileSingleCenter.getSingleDate());
                    detailBean.setAssignPredictValue(mobileSingleCenter.getSingleValue());
                    detailBean.setAssignPredictCurr(mobileSingleCenter.getSingleCurr());
                    detailBean.setAssignRevUserId(mobileSingleCenter.getRevUserId());
                    ComAccount comAccount = comAccountService.queryAccountByAcctUsername(mobileSingleCenter.getRevUser());
                    if (null != comAccount) {
                        detailBean.setAssignRevUser(comAccount.getRealName());
                    } else {
                        detailBean.setAssignRevUser(mobileSingleCenter.getRevUser());
                    }

                }
            }
            //如果是派车单获取总重量和总体积
            if (!StringUtil.isEmpty(detailBean.getScheducarno()) && !detailBean.getScheducarno().startsWith("M_")) {
                try {
                    GetExpressScheduCarInfoRequest getExpressScheduCarInfoRequest = new GetExpressScheduCarInfoRequest();
                    getExpressScheduCarInfoRequest.setScheduCarId(detailBean.getScheducarno());
                    String carInfoStr = expreessOrderWebService.getExpressScheduCarInfo(getExpressScheduCarInfoRequest);
                    GetExpressScheduCarInfoRes carInfoRes = JSON.parseObject(carInfoStr, GetExpressScheduCarInfoRes.class);
                    if (carInfoRes != null && carInfoRes.getData() != null) {
                        detailBean.setTotalWht(carInfoRes.getData().getRevWeight());
                        detailBean.setTotalVol(carInfoRes.getData().getRevVolume());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            List<MobileScheduSubOrder> subOrderList = new ArrayList<>();
            List<String> subOrderNoList = new ArrayList<>();
            if (!StringUtil.isEmpty(detailBean.getScheducarno())) {
                // 设置子订单
                subOrderList = mobileMyOrderDao.selectMobileSubOrderByMobileId(orderDetailReq.getOrderId());
                String subOrderListJsonStr = JSON.toJSONString(subOrderList);
                detailBean.setSubOrderList(JSON.parseArray(subOrderListJsonStr, com.gistandard.transport.app.dubbo.order.bean.MobileScheduSubOrder.class));// 设置子派车单
                if (!detailBean.getScheducarno().startsWith("M_")) {
                    // 设置散件和集包列表
                    subOrderNoList = mobileMyOrderDao.selectMobileSubOrderNoList(detailBean.getScheducarno());
                } else {
                    for (MobileScheduSubOrder subOrder : subOrderList) {
                        subOrderNoList.add(subOrder.getBusiBookNo());
                    }
                }
                detailBean.setSubOrderNoList(subOrderNoList);
            }
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            Map<String, ComCurrency> comCurrencyMap = comCurrencyService.queryForMap();
            Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
            Map<String, ComGoodsType> goodsTypeMap = comGoodsTypeService.queryForMap();
            if (!StringUtil.isEmpty(detailBean.getComQuoteId()) && detailBean.getQuotedType() != null) {
                QuoteResultBean quoteResultBean = comQuoteService.getQuoteInfoByQuoteNo(detailBean.getComQuoteId());
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
                                    quoteDesc += comQuotePrice.getPrevPointValue()
                                            .setScale(2, BigDecimal.ROUND_HALF_UP);
                                }
                                if (comQuotePrice.getPointValue() != null && comQuotePrice.getUnitPrice() != null
                                        && comUnitMap.get(comQuotePrice.getUnitCode()) != null
                                        && comQuote.getCurrencyCode() != null
                                        && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                                    quoteDesc += "-"
                                            + comQuotePrice.getPointValue().setScale(2, BigDecimal.ROUND_HALF_UP) + " "
                                            + comQuotePrice.getUnitPrice()
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
                                    quoteDesc += comQuotePrice.getPrevPointValue()
                                            .setScale(2, BigDecimal.ROUND_HALF_UP);
                                }
                                if (comQuotePrice.getPointValue() != null && comQuotePrice.getUnitPrice() != null
                                        && comUnitMap.get(comQuotePrice.getUnitCode()) != null
                                        && comQuote.getCurrencyCode() != null
                                        && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                                    quoteDesc += "-"
                                            + comQuotePrice.getPointValue().setScale(2, BigDecimal.ROUND_HALF_UP) + " "
                                            + comQuotePrice.getUnitPrice()
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
                            quoteDesc += "首重 " + comQuote.getFirstWeightUnit().setScale(2, BigDecimal.ROUND_HALF_UP)
                                    + "千克" + comQuote.getFirstWeight().setScale(2, BigDecimal.ROUND_HALF_UP)
                                    + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh();
                            quoteDesc += "续重 "
                                    + comQuote.getAdditionalWeightUnit().setScale(2, BigDecimal.ROUND_HALF_UP) + "千克"
                                    + comQuote.getAdditionalWeight().setScale(2, BigDecimal.ROUND_HALF_UP)
                                    + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh();
                        }
                    }
                    detailBean.setQuoteDesc(quoteDesc);
                }
            }
            // 设置币值
            if (!StringUtil.isEmpty(detailBean.getCurrency()) && comCurrencyMap.get(detailBean.getCurrency()) != null) {
                detailBean.setCurrency(comCurrencyMap.get(detailBean.getCurrency()).getCurrencyCh());
            }
            if (!StringUtil.isEmpty(detailBean.getPredictCurr())
                    && comCurrencyMap.get(detailBean.getPredictCurr()) != null) {
                detailBean.setPredictCurr(comCurrencyMap.get(detailBean.getPredictCurr()).getCurrencyCh());
            }
            if (!StringUtil.isEmpty(detailBean.getAssignPredictCurr())
                    && comCurrencyMap.get(detailBean.getAssignPredictCurr()) != null) {
                detailBean.setAssignPredictCurr(comCurrencyMap.get(detailBean.getAssignPredictCurr()).getCurrencyCh());
            }
            // 根据省市区设置地址
            String startArea = "";
            String destArea = "";
            if (!StringUtil.isEmpty(detailBean.getStartProvide())
                    && comProvinceMap.get(detailBean.getStartProvide()) != null) {
                startArea += comProvinceMap.get(detailBean.getStartProvide()).getProvinceName();
                if (!StringUtil.isEmpty(detailBean.getStartCity())
                        && comCityMap.get(detailBean.getStartCity()) != null) {
                    startArea += comCityMap.get(detailBean.getStartCity()).getName();
                }
                if (!StringUtil.isEmpty(detailBean.getStartCounty())
                        && comCountyMap.get(detailBean.getStartCounty()) != null) {
                    startArea += comCountyMap.get(detailBean.getStartCounty()).getAreaName();
                }
                detailBean.setStartProvide(comProvinceMap.get(detailBean.getStartProvide()).getProvinceName());
                if (!StringUtil.isEmpty(detailBean.getStartCity()) && comCityMap.get(detailBean.getStartCity()) != null) {
                    detailBean.setStartCity(comCityMap.get(detailBean.getStartCity()).getName());
                }
                if (!StringUtil.isEmpty(detailBean.getStartCounty())
                        && comCountyMap.get(detailBean.getStartCounty()) != null) {
                    detailBean.setStartCounty(comCountyMap.get(detailBean.getStartCounty()).getAreaName());
                }
            }
            detailBean.setStartArea(startArea);

            if (!StringUtil.isEmpty(detailBean.getDestProvide())
                    && comProvinceMap.get(detailBean.getDestProvide()) != null) {
                destArea += comProvinceMap.get(detailBean.getDestProvide()).getProvinceName();
                if (!StringUtil.isEmpty(detailBean.getDestCity())
                        && comCityMap.get(detailBean.getDestCity()) != null) {
                    destArea += comCityMap.get(detailBean.getDestCity()).getName();
                }
                if (!StringUtil.isEmpty(detailBean.getDestCounty())
                        && comCountyMap.get(detailBean.getDestCounty()) != null) {
                    destArea += comCountyMap.get(detailBean.getDestCounty()).getAreaName();
                }
                detailBean.setDestProvide(comProvinceMap.get(detailBean.getDestProvide()).getProvinceName());
                if (!StringUtil.isEmpty(detailBean.getDestCity()) && comCityMap.get(detailBean.getDestCity()) != null) {
                    detailBean.setDestCity(comCityMap.get(detailBean.getDestCity()).getName());
                }
                if (!StringUtil.isEmpty(detailBean.getDestCounty())
                        && comCountyMap.get(detailBean.getDestCounty()) != null) {
                    detailBean.setDestCounty(comCountyMap.get(detailBean.getDestCounty()).getAreaName());
                }
            }
            detailBean.setDestArea(destArea);

            ComAccount comAccount = comAccountService.queryAccountById(orderDetailReq.getAccountId());
            ComWaybillTrace comWaybillTrace = new ComWaybillTrace();
            if (comAccount != null) {
                comWaybillTrace.setAcctUsername(comAccount.getAcctUsername());
                comWaybillTrace.setBusiBookNo(detailBean.getBusiBookNo());
                try {
                    if (detailBean.getBusiCtrl() != null
                            && detailBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_RETURN) {
                        // 设置失败原因
                        comWaybillTrace.setExecCode(WayBillStatusDefine.MS_FAIL.getIntValue());
                        List<ComWaybillTrace> comWaybillTraceList = comWaybillTraceService
                                .queryWaybillListByCondition(comWaybillTrace);
                        if (comWaybillTraceList != null && comWaybillTraceList.size() > 0) {
                            detailBean.setFailureDesc(comWaybillTraceList.get(0).getRemark());
                        }
                    } else if (detailBean.getBusiCtrl() != null
                            && detailBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_GIVEUP) {
                        // 设置放弃原因
                        comWaybillTrace.setExecCode(WayBillStatusDefine.MS_GIVEUP.getIntValue());
                        List<ComWaybillTrace> comWaybillTraceList = comWaybillTraceService
                                .queryWaybillListByCondition(comWaybillTrace);
                        if (comWaybillTraceList != null && comWaybillTraceList.size() > 0) {
                            detailBean.setFailureDesc(comWaybillTraceList.get(0).getRemark());
                        }
                    } else if (detailBean.getBusiCtrl() != null
                            && detailBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_REFUSE) {
                        // 设置拒绝原因
                        comWaybillTrace.setExecCode(WayBillStatusDefine.MS_REJECT.getIntValue());
                        List<ComWaybillTrace> comWaybillTraceList = comWaybillTraceService
                                .queryWaybillListByCondition(comWaybillTrace);
                        if (comWaybillTraceList != null && comWaybillTraceList.size() > 0) {
                            detailBean.setFailureDesc(comWaybillTraceList.get(0).getRemark());
                        }
                    }
                } catch (MobileStationBizException e) {
                    throw new MobileStationBizException(e.getMessage());
                }
            }
            for (MobileGoodsInfo goodsInfo : detailBean.getGoodsInfoList()) {
                if (!StringUtil.isEmpty(goodsInfo.getGoodsType()) && goodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                    goodsInfo.setGoodsTypeName(goodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh());
                }
                if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit()) && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                    goodsInfo.setGoodsQtyUnitName(comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh());
                }
                if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit()) && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                    goodsInfo.setGoodsWeightUnitName(comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh());
                }
                if (!StringUtil.isEmpty(goodsInfo.getGoodsVolumeUnit()) && comUnitMap.get(goodsInfo.getGoodsVolumeUnit()) != null) {
                    goodsInfo.setGoodsVolumeUnitName(comUnitMap.get(goodsInfo.getGoodsVolumeUnit()).getUnitCh());
                }
                if (subOrderList != null) {
                    for (MobileScheduSubOrder subOrder : subOrderList) {
                        if (goodsInfo.getMobileScheduOrderId() != null && goodsInfo.getMobileScheduOrderId().equals(subOrder.getId())) {
                            goodsInfo.setMobileScheduOrderNo(subOrder.getBusiBookNo());
                            break;
                        }
                    }
                }
            }
            BookingForm bookingForm = null;
            if (StringUtil.isEmpty(detailBean.getScheducarno())) {
                bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(detailBean.getBusiBookNo());
            } else {
                if (subOrderList.size() > 0) {
                    bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrderList.get(0).getBusiBookNo());
                }
            }

            if (null != bookingForm) {
                detailBean.setBookingUser(bookingForm.getCreateUser());
                detailBean.setAccesstime(bookingForm.getAccesstime());
                detailBean.setPlatQuoteNo(bookingForm.getDocno());
                detailBean.setCreateUserTel(bookingForm.getShipCustlinkTel());
                detailBean.setDestCustLinkMan(bookingForm.getCneeCustLinkMan());
                detailBean.setDestCustLinkTel(bookingForm.getCneeCustLinkTel());
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

    /**
     * 查询订单详情，出入库扫描使用
     *
     * @param queryOrderDetailReq
     * @return
     * @throws MobileStationBizException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public SMQueryOrderDetailResult queryOrderDetail(QueryOrderDetailReq queryOrderDetailReq) throws MobileStationBizException, InvocationTargetException, IllegalAccessException {
        //四通一达单号处理
        if (StringUtils.isBlank(queryOrderDetailReq.getBusiNoTag())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByExpressOrderNo(queryOrderDetailReq.getBusiBookNo());
            if (bookingForm != null) {
                queryOrderDetailReq.setBusiBookNo(bookingForm.getBusiBookNo());
            }
        } else if (!queryOrderDetailReq.getBusiNoTag().equals(StockBaseReq.BusiNoTag.QR_CODE.getCode())) {
            String busiNoFrom4T1Dno = orderUtils.getBusiNoFrom4T1Dno(queryOrderDetailReq.getBusiBookNo());
            queryOrderDetailReq.setBusiBookNo(busiNoFrom4T1Dno);
        }

        SMQueryOrderDetailResult result = new SMQueryOrderDetailResult();
        List<MobileBookingForm> mobileBookingForms = mobileBookingFormDaoEx.queryOrderListByConditions(
                queryOrderDetailReq.getBusiBookNo(), queryOrderDetailReq.getAcctUsername(), queryOrderDetailReq.getCompanyAccountId(), queryOrderDetailReq.getBusiCtrl());
        List<QueryOrderDetailBean> datas = new ArrayList<>();
        //如果是订单
        if (mobileBookingForms.size() > 0) {
            for (MobileBookingForm mobileBookingForm : mobileBookingForms) {
                //订单列表筛选
                if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL
                        || mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_GIVEUP
                        || mobileBookingForm.getRoleId() != queryOrderDetailReq.getRoleId()) {
                    continue;
                }
                QueryOrderDetailBean data = new QueryOrderDetailBean();
                BeanUtils.copyProperties(data, mobileBookingForm);
                if (mobileBookingForm.getScheducarno() != null) {
                    List<MobileScheduSubOrder> mobileScheduSubOrders = mobileScheduSubOrderDaoEx
                            .selectByScheducarno(mobileBookingForm.getScheducarno(), queryOrderDetailReq.getAcctUsername(),
                                    queryOrderDetailReq.getRoleId());
                    data.setGoodsNums(mobileScheduSubOrders.size());
                    data.setScheducarno(mobileBookingForm.getScheducarno());
                } else {
                    data.setGoodsNums(1);
                }
                data.setOrderId(mobileBookingForm.getId());
                data.setSystemFlag(SystemDefine.CALC_MS_SYSTEM_FLAG);
                data.setgFUserFromCode(queryOrderDetailReq.getAcctUsername());
                data.setgFUserToCode(sysGFCode);
                datas.add(data);
//        } else {
//            result.setRetCode(SystemDefine.FAILURE);
//            result.setRetMsg("不是您的订单，无法查看");
//            return result;
            }
        }
        //如果是子订单
        if (datas.size() == 0) {
            MobileScheduSubOrder mobileScheduSubOrder = mobileScheduSubOrderDaoEx
                    .selectMobiScheduSubOrderByBusibookno2(queryOrderDetailReq.getBusiBookNo(), null, null);
            if (mobileScheduSubOrder == null) {
                result.setRetCode(SystemDefine.FAILURE);
                result.setRetMsg("您的子订单不存在");
                return result;
            }
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileScheduSubOrder.getMobileBookingFormId());
            QueryOrderDetailBean data = new QueryOrderDetailBean();
            BeanUtils.copyProperties(data, mobileBookingForm);
            data.setGoodsNums(1);
            data.setOrderId(mobileBookingForm.getId());
            datas.add(data);
        }
        result.setData(datas);
        return result;
    }

    /**
     * MS3.0放弃订单
     *
     * @param mobileStatusOperateReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult giveUpOrder(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException {
        //商户单所有的取消订单功能去掉
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq.getOrderId());
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(mobileStatusOperateReq.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
        giOrderTraceResynced.setTsAct(new Date());

        giOrderTraceResynced.setUserCode(mobileStatusOperateReq.getLoginAcctUserName());
        giOrderTraceResynced.setLoginCode(mobileStatusOperateReq.getAcctUsername());
        if (mobileStatusOperateReq.getRoleId() != null) {
            giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(mobileStatusOperateReq.getRoleId()));
        }
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        // 判断当前库存，如果有，不允许取消订单
        List<MobileStock> mobileStockList = mobileStationStockDao.queryScheducarMobileStockListByOrderId(
                mobileStatusOperateReq.getOrderId(), mobileStatusOperateReq.getAccountId());
        for (MobileStock mobileStock : mobileStockList) {
            if (mobileStock.getGoodsQty().compareTo(BigDecimal.ZERO) == 1) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("当前订单已经有库存，不能取消订单！");
                return baseResBean;
            }
        }
        int flag = 0;

        if (StringUtil.isEmpty(mobileStatusOperateReq.getScheducarno())) {
            //如果是
            flag = 1;
        } else {
            if (mobileStatusOperateReq.getScheducarno().startsWith("M_")) {
                flag = 1;
            } else {
                // 调用hub接口
                GiveUpSchudeCarOrderRequest giveUpSchudeCarOrderRequest = new GiveUpSchudeCarOrderRequest();
                if (StringUtil.isEmpty(mobileStatusOperateReq.getScheducarno())) {
                    //如果是咪站取消订单，派车单号为空，设置订单号
                    giveUpSchudeCarOrderRequest.setSchudeCarNo(mobileStatusOperateReq.getBusiBookNo());
                } else {
                    giveUpSchudeCarOrderRequest.setSchudeCarNo(mobileStatusOperateReq.getScheducarno());
                }
                giveUpSchudeCarOrderRequest.setSchudeCarType(mobileStatusOperateReq.getTransportType());
                giveUpSchudeCarOrderRequest.setTotalLevel(true);
                giveUpSchudeCarOrderRequest.setOperateRemark(mobileStatusOperateReq.getRefuseDesc());
                logger.info("放弃订单传参:{}", JSON.toJSONString(giveUpSchudeCarOrderRequest));
                String expreessResStr = expreessOrderWebService.giveUpSchudeCarOrder(giveUpSchudeCarOrderRequest);
                if (!StringUtil.isEmpty(expreessResStr)) {
                    AppBaseResult expreessRes = JSON.parseObject(expreessResStr, AppBaseResult.class);
                    if (expreessRes.getRetCode() == 0) {
                        flag = 1;
                    } else {
                        logger.info("放弃订单返回结果{}", expreessRes.getRetMsg());
                        throw new MobileStationBizException(expreessRes.getRetMsg());
                    }
                } else {
                    logger.info("HUB返回NULL");
                    throw new MobileStationBizException("放弃订单失败！");
                }
            }
        }


        if (flag > 0) {
            List<MobileScheduSubOrder> subOrderList = mobileMyOrderDao.selectMobileSubOrderByMobileId(mobileStatusOperateReq.getOrderId());
            for (MobileScheduSubOrder subOrder : subOrderList) {
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrder.getBusiBookNo());
                bookingForm.setBusiCtrl(-1);
                flag = bookingFormDao.updateByPrimaryKey(bookingForm);
            }
            // 修改订单状态为放弃
            MobileOrderOperateBean orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                    mobileStatusOperateReq.getOrderId(),
                    mobileStatusOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER,
                    MobileStationDefine.MOBILE_ORDER_STATUS_GIVEUP);
            //如果为咪站指派单，取消改为已接单、取件成功
            //如果是蛙站指派单，调用蛙站接口
            //如果是MS指派单，取消改为已发车
            orderOperateBean.setOldStatusStr(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER + ","
                    + MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN);
            flag = mobileStationOrderDao.giveUpOrder(orderOperateBean);
            if (flag < 1) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("放弃订单失败！");
            } else {
                // 设置派车单号
                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                    waybillTraceOperateBean.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                } else {
                    waybillTraceOperateBean.setScheducarno(mobileBookingForm.getScheducarno());
                }

                // 插入之派单流程跟踪日志
                waybillTraceOperateBean.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
                if (mobileBookingForm != null) {
                    waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
                    waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
                }
                ComAccount account = comAccountService.queryAccountByAcctUsername(mobileStatusOperateReq.getAcctUsername());
                if (account != null && mobileStatusOperateReq.getRoleId() != null) {
                    waybillTraceOperateBean.setRealName(account.getRealName());
                    waybillTraceOperateBean.setRemark(SysAccountRole.getName(mobileStatusOperateReq.getRoleId().intValue()) + account.getRealName() + "已取消接单");
                } else {
                    waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_GIVEUP.getName()
                            + mobileStatusOperateReq.getRefuseDesc());
                }
                waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
                waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_GIVEUP.getIntValue());

                waybillTraceOperateBean.setDispatchId(mobileStatusOperateReq.getOrderId());
                if (mobileStatusOperateReq.getRoleId() != null) {
                    waybillTraceOperateBean.setRoleId(mobileStatusOperateReq.getRoleId());
                }
                insertWaybillTrace(waybillTraceOperateBean);
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
            }

        }
        return baseResBean;
    }

    /**
     * MS3.0发车
     *
     * @param mobileStatusOperateReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult depart(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNoList = new ArrayList<>();
        int flag = 0;
        // 获取订单状态，如果是取件成功，可以发车，否则不允许发车
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq
                .getOrderId());
        if (mobileBookingForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("取件成功后，才可以发车！");
            return baseResBean;
        }

        //判断是否是派车单，如果是派车单，查询出子订单列表，并设置GPS操作日志参数
        List<MobileScheduSubOrder> subOrderList = new ArrayList<>();
        if (!StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
            subOrderList = mobileMyOrderDao.selectMobileSubOrderByMobileId(mobileBookingForm.getId());
            for (MobileScheduSubOrder subOrder : subOrderList) {
                allBusNoList.add(subOrder.getBusiBookNo());
            }
        } else {
            allBusNoList.add(mobileBookingForm.getBusiBookNo());
        }

        if (mobileBookingForm != null && MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus())) {
            //取件发车，判断核保
//            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
//            if (bookingForm != null) {
//                int premiumStatus = (bookingForm == null || bookingForm.getPremiumStatus() == null) ? -1 : bookingForm.getPremiumStatus().intValue();
//                if (bookingForm != null && bookingForm.getNeedInsure() && CustomerDefine.HAVE_PAY != premiumStatus) {
//                    if (CustomerDefine.NEED_PAY == premiumStatus) {
//                        throw new MobileStationBizException("用户尚未支付保费，无法发车，请先与用户联系！");
//                    }
//                    if (bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
//                        throw new MobileStationBizException("用户订单核保失败尚未解冻，无法发车，请先与用户联系！");
//                    }
//
//                }
//            }
        } else {
            //记录GPS操作日志
            if (MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus())) {
                //起点为POP取O单下单人的账号
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
                giOrderTraceResynced.setUserCodeFrom(bookingForm.getCreateUser());
            } else if (MobileStationDefine.M.equals(mobileBookingForm.getStartLocus())) {
                //起点为咪站，取订单下单人账号
                giOrderTraceResynced.setUserCodeFrom(mobileBookingForm.getCreateUser());
            } else {
                //起点为蛙站，取蛙站账号
                if (!StringUtil.isEmpty(mobileBookingForm.getStartLocus())) {
                    String acctUsername = mobileMyOrderDao.queryAcctUsernameByCustTtl(mobileBookingForm.getStartLocus());
                    giOrderTraceResynced.setUserCodeFrom(acctUsername);
                }
            }

            if (MobileStationDefine.POD.equals(mobileBookingForm.getDestnLocus())) {
                //如果终点是POD，最后传空
                giOrderTraceResynced.setAction(MobileStationDefine.Action_DeliveryToPod);
                giOrderTraceResynced.setUserCodeTo(null);
            } else if (MobileStationDefine.M.equals(mobileBookingForm.getDestnLocus())) {
                //如果目的地是咪站，分两种情况，1是POP-M；2是W-M
                giOrderTraceResynced.setAction(MobileStationDefine.Action_TransportStart);
                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                    //POP-M,取下单人
                    giOrderTraceResynced.setUserCodeTo(mobileBookingForm.getCreateUser());
                } else {
                    //W-M,查询出M-POD的咪站接单人
                    if (subOrderList != null && subOrderList.size() > 0) {
                        MobileBookingForm order = mobileMyOrderDao.queryMiOrderByBusiBookNo(subOrderList.get(0).getBusiBookNo());
                        giOrderTraceResynced.setUserCodeTo(order.getRevUser());
                    }
                }
            } else {
                //如果目的地是蛙站，取蛙站的账号
                giOrderTraceResynced.setAction(MobileStationDefine.Action_TransportStart);
                if (!StringUtil.isEmpty(mobileBookingForm.getDestnLocus())) {
                    String acctUsername = mobileMyOrderDao.queryAcctUsernameByCustTtl(mobileBookingForm.getDestnLocus());
                    giOrderTraceResynced.setUserCodeTo(acctUsername);
                }
            }
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            giOrderTraceResynced.setAllBusiNo(allBusNoList);
            giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());

            giOrderTraceResynced.setUserCode(mobileStatusOperateReq.getLoginAcctUserName());
            giOrderTraceResynced.setLoginCode(mobileStatusOperateReq.getAcctUsername());

            if (mobileStatusOperateReq.getRoleId() != null) {
                giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(mobileStatusOperateReq.getRoleId()));
            }
            if (!StringUtil.isEmpty(mobileBookingForm.getShipCustProvide())
                    && comProvinceMap.get(mobileBookingForm.getShipCustProvide()) != null) {
                giOrderTraceResynced.setProvinceFrom(comProvinceMap.get(mobileBookingForm.getShipCustProvide()).getProvinceName());
            }
            if (!StringUtil.isEmpty(mobileBookingForm.getShipCustCity())
                    && comCityMap.get(mobileBookingForm.getShipCustCity()) != null) {
                giOrderTraceResynced.setCityFrom(comCityMap.get(mobileBookingForm.getShipCustCity()).getName());
            }
            if (!StringUtil.isEmpty(mobileBookingForm.getShipCustCounty())
                    && comCountyMap.get(mobileBookingForm.getShipCustCounty()) != null) {
                giOrderTraceResynced.setDistrictFrom(comCountyMap.get(mobileBookingForm.getShipCustCounty()).getAreaName());
            }

            giOrderTraceResynced.setAddressFrom(mobileBookingForm.getShipCustAddr());
            GiPoint pointFrom = new GiPoint();
            pointFrom.setLatitude(mobileBookingForm.getShipLatitude().doubleValue());
            pointFrom.setLongitude(mobileBookingForm.getShipLongitude().doubleValue());
            giOrderTraceResynced.setPointFrom(pointFrom);

            if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustProvide())
                    && comProvinceMap.get(mobileBookingForm.getCneeCustProvide()) != null) {
                giOrderTraceResynced.setProvinceTo(comProvinceMap.get(mobileBookingForm.getCneeCustProvide()).getProvinceName());
            }
            if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCity())
                    && comCityMap.get(mobileBookingForm.getCneeCustCity()) != null) {
                giOrderTraceResynced.setCityTo(comCityMap.get(mobileBookingForm.getCneeCustCity()).getName());
            }
            if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCounty())
                    && comCountyMap.get(mobileBookingForm.getCneeCustCounty()) != null) {
                giOrderTraceResynced.setDistrictTo(comCountyMap.get(mobileBookingForm.getCneeCustCounty()).getAreaName());
            }

            giOrderTraceResynced.setAddressTo(mobileBookingForm.getCneeCustAddr());
            GiPoint pointTo = new GiPoint();
            pointTo.setLatitude(mobileBookingForm.getCneeLatitude().doubleValue());
            pointTo.setLongitude(mobileBookingForm.getCneeLongitude().doubleValue());
            giOrderTraceResynced.setPointTo(pointTo);
        }

        //判断目的地是否为蛙站，如果是蛙站
        // 1、判断是否为移动蛙站，如果是移动蛙站获取当前位置；
        if (!MobileStationDefine.POD.equals(mobileBookingForm.getDestnLocus())
                && !MobileStationDefine.POM.equals(mobileBookingForm.getDestnLocus())
                && !MobileStationDefine.M.equals(mobileBookingForm.getDestnLocus())) {
            //目的地为蛙站
            ComCustomer comCustomer = comCustomerDaoEx.getComCustomerByCustTtl(mobileBookingForm.getDestnLocus());
            if (comCustomer != null) {
                //1、判断是否为移动蛙站，如果是移动蛙站获取当前位置；
                ComAccountCategory comAccountCategory = comAccountCategoryDaoEx.getWaStationStatus(comCustomer.getAccountId());
                if (comAccountCategory != null) {
                    //当前为移动蛙站
                    try {
                        ComAccount comAccount = comAccountDao.selectByPrimaryKey(comAccountCategory.getAccountId());
                        String result = pnWebService.getGiPositionUserOfMovingWaByUserCode(comAccount.getAcctUsername());
                        GiPositionUser giPositionUser = JSON.parseObject(result, GiPositionUser.class);
                        if (giPositionUser != null) {
                            Map<String, ComProvince> comProvinceMap = comProvinceService.queryNameForMap();
                            Map<String, ComCity> comCityMap = comCityService.queryNameForMap();

                            if (giPositionUser.getProvince() != null && comProvinceMap.get(giPositionUser.getProvince()) != null) {
                                //设置省份code
                                mobileBookingForm.setCneeCustProvide(comProvinceMap.get(giPositionUser.getProvince()).getId().toString());
                                if (giPositionUser.getCity() != null && comCityMap.get(giPositionUser.getCity()) != null) {
                                    //设置市的code
                                    mobileBookingForm.setCneeCustCity(comCityMap.get(giPositionUser.getCity()).getId().toString());
                                    if (giPositionUser.getDistrict() != null) {
                                        ComCounty comCounty = comCountyDaoEx.queryByParams(comCityMap.get(giPositionUser.getCity()).getId().toString(), giPositionUser.getDistrict());
                                        if (comCounty != null) {
                                            mobileBookingForm.setCneeCustCounty(comCounty.getId().toString());
                                        }
                                    }
                                }
                            }
                            mobileBookingForm.setCneeCustAddr(giPositionUser.getAddress());
                            mobileBookingForm.setCneeLongitude(new BigDecimal(giPositionUser.getGiPoint().getLongitude()));
                            mobileBookingForm.setCneeLatitude(new BigDecimal(giPositionUser.getGiPoint().getLatitude()));
                        } else {
                            throw new MobileStationBizException("获取移动蛙站位置失败，不能发车！");
                        }
                    } catch (Exception e) {
                        throw new MobileStationBizException("获取移动蛙站位置失败，不能发车！");
                    }
                }
            }
        }
        if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.POP) || mobileBookingForm.getStartLocus().equals(MobileStationDefine.POM) || mobileBookingForm.getStartLocus().equals(MobileStationDefine.M)) {
            if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.POP)) {
                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                    BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
                    if (bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("该订单核保失败已被冻结，请与用户确认");
                        return baseResBean;
                    }
                } else {
                    for (MobileScheduSubOrder subOrder : subOrderList) {
                        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrder.getBusiBookNo());
                        if (bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
                            baseResBean.setRetCode(SystemDefine.FAILURE);
                            baseResBean.setRetMsg("该订单核保失败已被冻结，请与用户确认");
                            return baseResBean;
                        }
                    }
                }
                if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.M)) {
                    //如果是POP-M的订单发车，修改咪站的M-POD的状态为已接单
                    mobileMyOrderDao.updateMiOrderStatus(mobileBookingForm.getBusiBookNo());
                }
            }
            if (!StringUtil.isEmpty(mobileBookingForm.getScheducarno())
                    && !mobileBookingForm.getStartLocus().equals("POM")
                    && !mobileBookingForm.getOrderFrom().equals(MobileStationDefine.MOBILE_ORDER_FROM_M)
                    && !mobileBookingForm.getScheducarno().startsWith("M_")) {
                // 运输单，调用修改状态接口
                // 调用hub接口
                DepartSchudeCarOrderRequest departSchudeCarOrderRequest = new DepartSchudeCarOrderRequest();
                departSchudeCarOrderRequest.setSchudeCarNo(mobileStatusOperateReq.getScheducarno());
                departSchudeCarOrderRequest.setSchudeCarType(mobileStatusOperateReq.getTransportType());
                logger.info("发车传参:{}", JSON.toJSONString(departSchudeCarOrderRequest));
                String expreessResStr = expreessOrderWebService.departSchudeCarOrder(departSchudeCarOrderRequest);
                if (!StringUtil.isEmpty(expreessResStr)) {
                    AppBaseResult expreessRes = JSON.parseObject(expreessResStr, AppBaseResult.class);
                    if (expreessRes.getRetCode() == 0) {
                        flag = 1;
                    } else {
                        logger.info("发车返回结果{}", expreessRes.getRetMsg());
                        throw new MobileStationBizException(expreessRes.getRetMsg());
                    }
                } else {
                    logger.info("HUB发车返回NULL");
                    throw new MobileStationBizException("发车失败！");
                }
            } else {
                //如果是咪站的单，递送员发车时需要判断咪站的出库状态
//                if(mobileBookingForm.getStartLocus().equals(MobileStationDefine.M)){
//                    //根据订单号查询咪站订单，获取订单状态
////                    MobileBookingForm mobileBookingForm1 = mobileBookingFormDaoEx.selectByConditions()
//                    flag = 1;
//                }else {
                flag = 1;
//                }
            }
        } else {
            // 运输单，调用修改状态接口
            if (mobileBookingForm.getScheducarno().startsWith("M_")) {
                flag = 1;
            } else {
                // 调用hub接口
                DepartSchudeCarOrderRequest departSchudeCarOrderRequest = new DepartSchudeCarOrderRequest();
                departSchudeCarOrderRequest.setSchudeCarNo(mobileStatusOperateReq.getScheducarno());
                departSchudeCarOrderRequest.setSchudeCarType(mobileStatusOperateReq.getTransportType());
                logger.info("发车传参:{}", JSON.toJSONString(departSchudeCarOrderRequest));
                String expreessResStr = expreessOrderWebService.departSchudeCarOrder(departSchudeCarOrderRequest);
                if (!StringUtil.isEmpty(expreessResStr)) {
                    AppBaseResult expreessRes = JSON.parseObject(expreessResStr, AppBaseResult.class);
                    if (expreessRes.getRetCode() == 0) {
                        flag = 1;
                    } else {
                        logger.info("发车返回结果{}", expreessRes.getRetMsg());
                        throw new MobileStationBizException(expreessRes.getRetMsg());
                    }
                } else {
                    logger.info("HUB发车返回NULL");
                    throw new MobileStationBizException("发车失败！");
                }
            }

        }
        if (mobileBookingForm.getScheducarno() != null && mobileBookingForm.getScheducarno().startsWith("M_") && mobileBookingForm.getDestnLocus().equals(MobileStationDefine.M)) {
            // M-W 的派车单 调用hub接口
            DepartSchudeCarOrderRequest departSchudeCarOrderRequest = new DepartSchudeCarOrderRequest();
            departSchudeCarOrderRequest.setSchudeCarNo(mobileStatusOperateReq.getScheducarno());
            departSchudeCarOrderRequest.setSchudeCarType(mobileStatusOperateReq.getTransportType());
            departSchudeCarOrderRequest.setAccountId(mobileStatusOperateReq.getAccountId());
            departSchudeCarOrderRequest.setStationCode(mobileBookingForm.getDestnLocus());
            if (mobileStatusOperateReq.getAppLoginInfo() != null && mobileStatusOperateReq.getAppLoginInfo().getComAccount() != null) {
                departSchudeCarOrderRequest.setDeliveryName(mobileStatusOperateReq.getAppLoginInfo().getComAccount().getRealName());
                departSchudeCarOrderRequest.setDeliveryTel(mobileStatusOperateReq.getAppLoginInfo().getComAccount().getTelephone());
            }
            List<String> businessNoList = new ArrayList<>();
            for (MobileScheduSubOrder subOrder : subOrderList) {
                businessNoList.add(subOrder.getBusiBookNo());
            }
            departSchudeCarOrderRequest.setBusinessNOList(businessNoList);
            logger.info("发车传参:{}", JSON.toJSONString(departSchudeCarOrderRequest));
            String expreessResStr = expreessOrderWebService.departSchudeCarOrder(departSchudeCarOrderRequest);
            if (!StringUtil.isEmpty(expreessResStr)) {
                AppBaseResult expreessRes = JSON.parseObject(expreessResStr, AppBaseResult.class);
                if (expreessRes.getRetCode() == 0) {
                    flag = 1;
                } else {
                    logger.info("发车返回结果{}", expreessRes.getRetMsg());
                    throw new MobileStationBizException(expreessRes.getRetMsg());
                }
            } else {
                logger.info("HUB发车返回NULL");
                throw new MobileStationBizException("发车失败！");
            }
        }
        if (flag > 0) {
            // 发车成功
            // 修改订单当前状态
            MobileOrderOperateBean orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                    mobileStatusOperateReq.getOrderId(),
                    mobileStatusOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS,
                    MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN);
            flag = mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
            if (flag < 1) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("发车失败！");
            } else {
                // 设置派车单号
                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                    waybillTraceOperateBean.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                } else {
                    waybillTraceOperateBean.setScheducarno(mobileBookingForm.getScheducarno());
                }
                // 插入流程跟踪日志
                if (mobileStatusOperateReq.getAppLoginInfo() != null && mobileStatusOperateReq.getAppLoginInfo().getComAccount() != null) {
                    waybillTraceOperateBean.setRealName(mobileStatusOperateReq.getAppLoginInfo().getComAccount().getRealName());
                }
                waybillTraceOperateBean.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
                ComAccount account = comAccountService.queryAccountByAcctUsername(mobileStatusOperateReq.getAcctUsername());

                if (mobileBookingForm != null) {
                    waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
                    waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
                }
                waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
                if (account != null && mobileStatusOperateReq.getRoleId() != null) {
                    waybillTraceOperateBean.setRealName(account.getRealName());
                    if (MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus())) {
                        waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_DEPART_POP.getIntValue());
                        waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_DEPART_POP.getName());
                    } else {
                        if (MobileStationDefine.POD.equals(mobileBookingForm.getDestnLocus())) {
                            waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_DEPART_POD.getIntValue());
                            waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_DEPART_POD.getName());
                        } else {
                            waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_DEPART_HUB.getIntValue());
                            waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_DEPART_HUB.getName() + mobileBookingForm.getDestnLocus());
                        }
                    }

                } else {
                    if (MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus())) {
                        waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_DEPART_POP.getIntValue());
                        waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_DEPART_POP.getName());
                    } else {
                        if (MobileStationDefine.POD.equals(mobileBookingForm.getDestnLocus())) {
                            waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_DEPART_POD.getIntValue());
                            waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_DEPART_POD.getName());
                        } else {
                            waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_DEPART_HUB.getIntValue());
                            waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_DEPART_HUB.getName()
                                    + mobileBookingForm.getDestnLocus());
                        }
                    }
                }

                if (mobileStatusOperateReq.getRoleId() != null) {
                    waybillTraceOperateBean.setRoleId(mobileStatusOperateReq.getRoleId());
                }
                waybillTraceOperateBean.setDispatchId(mobileStatusOperateReq.getOrderId());
                insertWaybillTrace(waybillTraceOperateBean);
            }
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
        }
        return baseResBean;
    }

    /**
     * MS3.0 订单失败
     * 判断是M-POD才允许配送失败
     *
     * @param mobileStatusOperateReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult orderFailure(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        //1、调用取消结算接口 2、M-POD 快递员状态改为 已退回 M-POD 咪站状态改为已接单、待收件 3、W-POD通知蛙站
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq.getOrderId());
        if (mobileBookingForm != null && MobileStationDefine.M.equals(mobileBookingForm.getStartLocus())
                && MobileStationDefine.POD.equals(mobileBookingForm.getDestnLocus())) {
            //M-POD 快递员状态改为 已退回 M-POD 咪站状态改为已接单
            MobileBookingForm miOrder = mobileMyOrderDao.queryMiOrderByBusiBookNo(mobileBookingForm.getBusiBookNo());
            if (miOrder != null) {
                miOrder.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                if (miOrder.getFailureTimes() != null) {
                    miOrder.setFailureTimes(miOrder.getFailureTimes() + 1);
                } else {
                    miOrder.setFailureTimes(1);
                }
                mobileBookingFormDao.updateByPrimaryKey(miOrder);
            }
//            ICalcForPrice iCalcForPrice = new ICalcForPrice();
//            iCalcForPrice.setOrderId(mobileBookingForm.getId());
//            iCalcForPrice.setSystemFlag("NJKD");
//            iCalcForPrice.setOrderCatalog(1);
//            iCalcForPrice.setOrderNo(mobileBookingForm.getBusiBookNo());
//            //设置收款用户
//            if (mobileBookingForm.getCreateCompanyId() != null) {
//                //企业下工作，使用企业结算
//                ComAccount companyAcct = comAccountDao.selectByPrimaryKey(mobileBookingForm.getCreateCompanyId());
//                if (companyAcct != null) {
//                    iCalcForPrice.setGFUserFromCode(companyAcct.getAcctUsername());
//                } else {
//                    iCalcForPrice.setGFUserFromCode(mobileBookingForm.getRevUser());
//                }
//            } else {
//                iCalcForPrice.setGFUserFromCode(mobileBookingForm.getRevUser());
//            }
//            iCalcForPrice.setGFUserToCode(sysGFCode);
//            iCalcForPrice.setPaymentTerm(MobileStationDefine.PAYMENT_GENERALACCT);
//            iCalcForPrice.setStartAccountId("23");
//            iCalcForPrice.setEndAccountId("-1");
//            iCalcForPrice.setSystemFrom(1);
//            String calcRes = calcWebService.cancelCalcForPriceI(iCalcForPrice);
//            CalcDataResult calcDataResult = JSON.parseObject(calcRes, CalcDataResult.class);
//            if (calcDataResult == null || !calcDataResult.getSucceed()) {
//                throw new MobileStationBizException("操作失败！");
//            }
            // 修改订单状态为放弃
            // 派件单 状态从20到30 派件失败 需要退货
            MobileOrderOperateBean orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                    mobileStatusOperateReq.getOrderId(), mobileStatusOperateReq.getAcctUsername(),
                    MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS, MobileStationDefine.MOBILE_ORDER_STATUS_FAILURE);
            mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);

            //插入日志记录
            MobileOrderOperate mobileOrderOperate = new MobileOrderOperate();
            mobileOrderOperate.setBusiBookNo(mobileBookingForm.getBusiBookNo());
            mobileOrderOperate.setCreateUser(mobileStatusOperateReq.getAcctUsername());
            mobileOrderOperate.setCreateUserId(mobileStatusOperateReq.getAccountId());
            mobileOrderOperate.setCreateUserName(mobileStatusOperateReq.getLoginAcctUserName());
            mobileOrderOperate.setCreateDate(new Date());
            mobileOrderOperate.setMobileBookingFormId(miOrder.getId());
            mobileOrderOperate.setDescription(mobileStatusOperateReq.getDescription());
            mobileOrderOperate.setOperType(3);

            mobileOrderOperateDao.insert(mobileOrderOperate);

            GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();//记录GPS操作日志
            List<String> allBusNoList = new ArrayList<>();
            allBusNoList.add(mobileBookingForm.getBusiBookNo());
            giOrderTraceResynced.setAction(MobileStationDefine.Action_PodFailured);
            giOrderTraceResynced.setAllBusiNo(allBusNoList);
            giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());

            giOrderTraceResynced.setUserCode(mobileStatusOperateReq.getLoginAcctUserName());
            giOrderTraceResynced.setLoginCode(mobileStatusOperateReq.getAcctUsername());

            if (mobileStatusOperateReq.getRoleId() != null) {
                giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(mobileStatusOperateReq.getRoleId()));
            }
            // 插入流程跟踪日志
            waybillTraceOperateBean.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
            if (mobileBookingForm != null) {
                waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
                waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
            }
            waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
            waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_FAIL.getIntValue());
            waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_FAIL.getName()
                    + mobileStatusOperateReq.getDescription());
            waybillTraceOperateBean.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
            if (mobileStatusOperateReq.getRoleId() != null) {
                waybillTraceOperateBean.setRoleId(mobileStatusOperateReq.getRoleId());
                ComAccount account = comAccountService.queryAccountByAcctUsername(mobileStatusOperateReq.getAcctUsername());
                if (account != null) {
                    waybillTraceOperateBean.setRealName(account.getRealName());
                }
            }
            mobileStationOrderService.insertWaybillTrace(waybillTraceOperateBean);
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
        } else {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("操作失败！");
            return baseResBean;
        }
        return baseResBean;
    }

    /**
     * MS3.0确认送达
     *
     * @param mobileStatusOperateReq
     * @return
     */
    @Override
    @Transactional
    public AppBaseResult deliveryConfirm(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();//记录GPS操作日志
        List<String> allBusNoList = new ArrayList<>();
        List<MobileScheduSubOrder> subOrderList = new ArrayList<>();
        int flag = 0;
        // 运输指派单
        if (mobileStatusOperateReq.getOrderId() != null && mobileStatusOperateReq.getOrderId() != 0) {
            // 判断终点是POD还是HUB，如果是pod，到付还需要判断是否支付
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq
                    .getOrderId());
            //判断订单状态
            if (mobileBookingForm != null) {
                if ((mobileBookingForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                        && mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue())
                        || (mobileBookingForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN
                        && mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue())) {
                    baseResBean.setRetCode(SystemDefine.FAILURE);
                    baseResBean.setRetMsg("订单状态不对，不能送达确认！");
                    return baseResBean;
                }
                if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                    return baseResBean;
                } else {
                    if (!mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)) {
                        if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.M)) {
                            baseResBean.setRetCode(SystemDefine.FAILURE);
                            baseResBean.setRetMsg("等待咪站收货！");
                            return baseResBean;
                        } else {
                            //M-W没有自动送达
                            if (!mobileBookingForm.getStartLocus().equals(MobileStationDefine.M)) {
                                baseResBean.setRetCode(SystemDefine.FAILURE);
                                baseResBean.setRetMsg("等待蛙站收货！");
                                return baseResBean;
                            }
                        }
                    }
                }
            }
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            // 目的地为POD
            if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)) {
                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                    // 订单
                    BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
                    if (bookingForm.getPayType() == 1) {// d到付
                        if (bookingForm.getIsJs() != 3) {// 3已支付
                            baseResBean.setRetCode(SystemDefine.FAILURE);
                            baseResBean.setRetMsg("定单:" + mobileBookingForm.getBusiBookNo() + "未支付，送达确认失败！");
                            return baseResBean;
                        }
                    }
                    // 更新bookingForm
                    bookingForm.setBusiCtrl(5);
                    bookingForm.setDeliverDate(new Date());
                    bookingFormDao.updateByPrimaryKey(bookingForm);

                    //记录GPS操作日志
                    allBusNoList.add(mobileBookingForm.getBusiBookNo());
                } else {
                    //蛙站到POD的派车单
                    subOrderList = mobileScheduSubOrderDaoEx.selectMobileSubOrderByMobileId(mobileStatusOperateReq.getOrderId());
                    if (subOrderList != null && subOrderList.size() > 0) {
                        allBusNoList.add(subOrderList.get(0).getBusiBookNo());
                        // 更新bookingForm
                        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrderList.get(0).getBusiBookNo());
                        if (bookingForm.getPayType() == 1) {// d到付
                            if (bookingForm.getIsJs() != 3) {// 3已支付
                                baseResBean.setRetCode(SystemDefine.FAILURE);
                                baseResBean.setRetMsg("定单:" + mobileBookingForm.getBusiBookNo() + "未支付，送达确认失败！");
                                return baseResBean;
                            }
                        }
                        // 更新bookingForm
                        bookingForm.setBusiCtrl(5);
                        bookingForm.setDeliverDate(new Date());
                        bookingFormDao.updateByPrimaryKey(bookingForm);
                    }
                }

                //记录GPS操作日志
                giOrderTraceResynced.setAction(MobileStationDefine.Action_PodConfirmed);
                giOrderTraceResynced.setAllBusiNo(allBusNoList);
                giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
                giOrderTraceResynced.setUserCode(mobileStatusOperateReq.getLoginAcctUserName());
                giOrderTraceResynced.setLoginCode(mobileStatusOperateReq.getAcctUsername());

                // 路径为POM-POD，解冻支付的现金
//                confirmPayAtMS(mobileBookingForm.getBusiBookNo(), mobileBookingForm.getScheducarno());
                flag = 1;
                MsgIMReq msgIMReq = new MsgIMReq();
                try {
                    PropertyUtils.copyProperties(msgIMReq, mobileStatusOperateReq);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                // 推送消息给用户
                msgIMReq.setMsgTo(1);
                msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_DELIVERY_CONFIRM);
                sendMsg(msgIMReq);
            } else if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.M)) {
                // 目的地为咪站，需要先在咪站收货 POP-M M-POD;  W-M M-POD
                //1、订单号相同，2接单人为咪站，3订单起始为咪站
                MobileAssignBean mobileAssignBean = new MobileAssignBean();
                //如果目的地是咪站，分两种情况，1是POP-M；2是W-M
                if (mobileStatusOperateReq.getScheducarno() != null) {
                    //派车单，需要判断所有子订单都已经收货 W-M M-POD
                    subOrderList = mobileMyOrderDao.selectMobileSubOrderByMobileId(mobileStatusOperateReq.getOrderId());
                    for (MobileScheduSubOrder subOrder : subOrderList) {
                        allBusNoList.add(subOrder.getBusiBookNo());
                        mobileAssignBean.setBusiBookNo(subOrder.getBusiBookNo());
                        mobileAssignBean.setRoleId(SysAccountRole.OPERATOR_MSTATION.getValue());
                        List<MobileBookingForm> forms = mobileMyOrderDao.selectMobileOrderList(mobileAssignBean);
                        if (forms.size() > 0) {
                            for (MobileBookingForm form : forms) {
                                if (form.getStartLocus().equals(MobileStationDefine.M)
                                        && (MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU == form.getOrderFrom()
                                        || MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST == form.getOrderFrom())) {
                                    if (form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                                            && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN
                                            && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING
                                            && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE
                                            && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_DONE
                                            && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                                        throw new MobileStationBizException("咪站没有确认收货，不能送达确认！");
                                    }
                                }
                            }
                        }
                    }
                    if (subOrderList != null && subOrderList.size() > 0) {
                        MobileBookingForm order = mobileMyOrderDao.queryMiOrderByBusiBookNo(subOrderList.get(0).getBusiBookNo());
                        giOrderTraceResynced.setUserCodeTo(order.getRevUser());
                    }
                    flag = 1;
                } else {
                    mobileAssignBean.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                    allBusNoList.add(mobileBookingForm.getBusiBookNo());
                    if (mobileBookingForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST
                            || mobileBookingForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL) {
                        //MS POP-POD 指派给M  生成POP-M、M-POD  POP-M送达时，需要判断M-POD的入库
                        mobileAssignBean.setCreateUser(mobileBookingForm.getRevUser());
                    } else {
                        //POP-M M指派给MS  POP-M的CreateUser等于M-POD的RevUser
                        mobileAssignBean.setRevUser(mobileBookingForm.getCreateUser());
                    }
                    giOrderTraceResynced.setUserCodeTo(mobileBookingForm.getCreateUser());
                    mobileAssignBean.setRoleId(SysAccountRole.OPERATOR_MSTATION.getValue());
                    List<MobileBookingForm> forms = mobileMyOrderDao.selectMobileOrderList(mobileAssignBean);
                    if (forms.size() > 0) {
                        for (MobileBookingForm form : forms) {
                            if (form.getStartLocus().equals(MobileStationDefine.M)) {
                                if (form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                                        && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN
                                        && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING
                                        && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE
                                        && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_DONE
                                        && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                                    throw new MobileStationBizException("咪站没有确认收货，不能送达确认！");
                                }
                            }
                        }
                        flag = 1;
                    }
                }

                //记录GPS操作日志
                giOrderTraceResynced.setAction(MobileStationDefine.Action_TransportArrival);
                giOrderTraceResynced.setAllBusiNo(allBusNoList);
                giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());

                giOrderTraceResynced.setUserCode(mobileStatusOperateReq.getLoginAcctUserName());
                giOrderTraceResynced.setLoginCode(mobileStatusOperateReq.getAcctUsername());

                if (!StringUtil.isEmpty(mobileBookingForm.getShipCustProvide())
                        && comProvinceMap.get(mobileBookingForm.getShipCustProvide()) != null) {
                    giOrderTraceResynced.setProvinceFrom(comProvinceMap.get(mobileBookingForm.getShipCustProvide()).getProvinceName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getShipCustCity())
                        && comCityMap.get(mobileBookingForm.getShipCustCity()) != null) {
                    giOrderTraceResynced.setCityFrom(comCityMap.get(mobileBookingForm.getShipCustCity()).getName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getShipCustCounty())
                        && comCountyMap.get(mobileBookingForm.getShipCustCounty()) != null) {
                    giOrderTraceResynced.setDistrictFrom(comCountyMap.get(mobileBookingForm.getShipCustCounty()).getAreaName());
                }
                giOrderTraceResynced.setAddressFrom(mobileBookingForm.getShipCustAddr());
                GiPoint pointFrom = new GiPoint();
                pointFrom.setLatitude(mobileBookingForm.getShipLatitude().doubleValue());
                pointFrom.setLongitude(mobileBookingForm.getShipLongitude().doubleValue());
                giOrderTraceResynced.setPointFrom(pointFrom);

                if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustProvide())
                        && comProvinceMap.get(mobileBookingForm.getCneeCustProvide()) != null) {
                    giOrderTraceResynced.setProvinceTo(comProvinceMap.get(mobileBookingForm.getCneeCustProvide()).getProvinceName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCity())
                        && comCityMap.get(mobileBookingForm.getCneeCustCity()) != null) {
                    giOrderTraceResynced.setCityTo(comCityMap.get(mobileBookingForm.getCneeCustCity()).getName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCounty())
                        && comCountyMap.get(mobileBookingForm.getCneeCustCounty()) != null) {
                    giOrderTraceResynced.setDistrictTo(comCountyMap.get(mobileBookingForm.getCneeCustCounty()).getAreaName());
                }
                giOrderTraceResynced.setAddressTo(mobileBookingForm.getCneeCustAddr());
                GiPoint pointTo = new GiPoint();
                pointTo.setLatitude(mobileBookingForm.getCneeLatitude().doubleValue());
                pointTo.setLongitude(mobileBookingForm.getCneeLongitude().doubleValue());
                giOrderTraceResynced.setPointTo(pointTo);
            } else {
                // 调用hub接口
                SuccDeliverSchudeCarOrderRequest succDeliverSchudeCarOrderRequest = new SuccDeliverSchudeCarOrderRequest();
                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                    succDeliverSchudeCarOrderRequest.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                } else {
                    succDeliverSchudeCarOrderRequest.setSchudeCarNo(mobileBookingForm.getScheducarno());
                }
                succDeliverSchudeCarOrderRequest.setStaionCode(mobileBookingForm.getDestnLocus());
                succDeliverSchudeCarOrderRequest.setDeliverAccount(mobileBookingForm.getRevUser());
                succDeliverSchudeCarOrderRequest.setSchudeCarType(mobileStatusOperateReq.getTransportType());
                succDeliverSchudeCarOrderRequest.setTotalLevel(true);
                logger.info("确认送达传参:{}", JSON.toJSONString(succDeliverSchudeCarOrderRequest));
                String expreessResStr = expreessOrderWebService
                        .succDeliverSchudeCarOrder(succDeliverSchudeCarOrderRequest);
                if (!StringUtil.isEmpty(expreessResStr)) {
                    AppBaseResult expreessRes = JSON.parseObject(expreessResStr, AppBaseResult.class);
                    if (expreessRes.getRetCode() != 0) {
                        logger.info("确认送达结果{}", expreessRes.getRetMsg());
                        throw new MobileStationBizException(expreessRes.getRetMsg());
                    }
                } else {
                    logger.info("HUB返回NULL");
                    throw new MobileStationBizException("确认送达失败！");
                }
                flag = 1;
                subOrderList = mobileScheduSubOrderDaoEx.selectMobileSubOrderByMobileId(mobileStatusOperateReq.getOrderId());
                for (MobileScheduSubOrder subOrder : subOrderList) {
                    allBusNoList.add(subOrder.getBusiBookNo());
                }
                //记录GPS操作日志
                giOrderTraceResynced.setAction(MobileStationDefine.Action_TransportArrival);
                giOrderTraceResynced.setAllBusiNo(allBusNoList);
                giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());

                giOrderTraceResynced.setUserCode(mobileStatusOperateReq.getLoginAcctUserName());
                giOrderTraceResynced.setLoginCode(mobileStatusOperateReq.getAcctUsername());
                if (!StringUtil.isEmpty(mobileBookingForm.getShipCustProvide())
                        && comProvinceMap.get(mobileBookingForm.getShipCustProvide()) != null) {
                    giOrderTraceResynced.setProvinceFrom(comProvinceMap.get(mobileBookingForm.getShipCustProvide()).getProvinceName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getShipCustCity())
                        && comCityMap.get(mobileBookingForm.getShipCustCity()) != null) {
                    giOrderTraceResynced.setCityFrom(comCityMap.get(mobileBookingForm.getShipCustCity()).getName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getShipCustCounty())
                        && comCountyMap.get(mobileBookingForm.getShipCustCounty()) != null) {
                    giOrderTraceResynced.setDistrictFrom(comCountyMap.get(mobileBookingForm.getShipCustCounty()).getAreaName());
                }
                giOrderTraceResynced.setAddressFrom(mobileBookingForm.getShipCustAddr());
                GiPoint pointFrom = new GiPoint();
                pointFrom.setLatitude(mobileBookingForm.getShipLatitude().doubleValue());
                pointFrom.setLongitude(mobileBookingForm.getShipLongitude().doubleValue());
                giOrderTraceResynced.setPointFrom(pointFrom);
                if (!StringUtil.isEmpty(mobileBookingForm.getDestnLocus())) {
                    String acctUsername = mobileMyOrderDao.queryAcctUsernameByCustTtl(mobileBookingForm.getDestnLocus());
                    giOrderTraceResynced.setUserCodeTo(acctUsername);
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustProvide())
                        && comProvinceMap.get(mobileBookingForm.getCneeCustProvide()) != null) {
                    giOrderTraceResynced.setProvinceTo(comProvinceMap.get(mobileBookingForm.getCneeCustProvide()).getProvinceName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCity())
                        && comCityMap.get(mobileBookingForm.getCneeCustCity()) != null) {
                    giOrderTraceResynced.setCityTo(comCityMap.get(mobileBookingForm.getCneeCustCity()).getName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCounty())
                        && comCountyMap.get(mobileBookingForm.getCneeCustCounty()) != null) {
                    giOrderTraceResynced.setDistrictTo(comCountyMap.get(mobileBookingForm.getCneeCustCounty()).getAreaName());
                }
                giOrderTraceResynced.setAddressTo(mobileBookingForm.getCneeCustAddr());
                GiPoint pointTo = new GiPoint();
                pointTo.setLatitude(mobileBookingForm.getCneeLatitude().doubleValue());
                pointTo.setLongitude(mobileBookingForm.getCneeLongitude().doubleValue());
                giOrderTraceResynced.setPointTo(pointTo);
            }
        }
        if (flag > 0) {
            // 出库
            StockInOutReq stockInOutReq = new StockInOutReq();
            stockInOutReq.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
            stockInOutReq.setStockType(MobileStationDefine.STOCK_OUT);
            stockInOutReq.setAccountId(mobileStatusOperateReq.getAccountId());
            stockInOutReq.setOrderFrom(mobileStatusOperateReq.getOrderFrom());
            stockInOutReq.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
            stockInOutReq.setScheducarno(mobileStatusOperateReq.getScheducarno());
            stockInOutReq.setCompanyAccountId(mobileStatusOperateReq.getCompanyAccountId());
            AppBaseResult res = mobileStockService.stockInOut(stockInOutReq);
            logger.info("到达确认----出库成功");
            MobileOrderOperateBean orderOperateBean;
            if (mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {  //如果是咪站
                // 修改订单状态为已完成
                orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                        mobileStatusOperateReq.getOrderId(),
                        mobileStatusOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE,
                        MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                //如果有**-M的订单，则设置状态为5，防止查询时候查出来
                MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(mobileStatusOperateReq.getBusiBookNo(),
                        mobileStatusOperateReq.getAcctUsername(), mobileStatusOperateReq.getCompanyAccountId(), MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE, null);
                if (mobileBookingForm != null) {
                    if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.M)) {
                        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                        mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
                    }
                }
                mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
            } else if (mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                // 修改订单状态为已完成
                orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                        mobileStatusOperateReq.getOrderId(),
                        mobileStatusOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS,
                        MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
            } else {
                // 修改订单状态为已完成,司机同城运输，更新送达时间
                orderOperateBean = new MobileOrderOperateBean(
                        mobileStatusOperateReq.getOrderId(),
                        new Date(), MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN,
                        MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                mobileStationOrderDao.driverUpdateBookingFormStatus(orderOperateBean);
            }
            // 设置派车单号
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq
                    .getOrderId());

            if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)) {
                if (mobileBookingForm.getScheducarno() == null) {
                    BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
                    statsBizOrderService.sendFinishOrderStats(bookingForm);
                } else {
                    if (subOrderList != null && subOrderList.size() > 0) {
                        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrderList.get(0).getBusiBookNo());
                        statsBizOrderService.sendFinishOrderStats(bookingForm);
                    }
                }
            }
            if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                waybillTraceOperateBean.setBusiBookNo(mobileBookingForm.getBusiBookNo());
            } else {
                waybillTraceOperateBean.setScheducarno(mobileBookingForm.getScheducarno());
            }
            // 插入流程跟踪日志
            waybillTraceOperateBean.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
            if (mobileBookingForm != null) {
                waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
                waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
            }
            waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
            ComAccount account = comAccountService.queryAccountByAcctUsername(mobileStatusOperateReq.getAcctUsername());
            if (account != null && mobileStatusOperateReq.getRoleId() != null) {
                waybillTraceOperateBean.setRealName(account.getRealName());
                if (MobileStationDefine.POD.equals(mobileBookingForm.getDestnLocus())) {
                    waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_ARRIVE_POD.getIntValue());
                    waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_ARRIVE_POD.getName());
                } else {
                    waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_ARRIVE_HUB.getIntValue());
                    waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_ARRIVE_HUB.getName()
                            + mobileBookingForm.getDestnLocus());
                }
            } else {
                if (MobileStationDefine.POD.equals(mobileBookingForm.getDestnLocus())) {
                    waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_ARRIVE_POD.getIntValue());
                    waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_ARRIVE_POD.getName());
                } else {
                    waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_ARRIVE_HUB.getIntValue());
                    waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_ARRIVE_HUB.getName()
                            + mobileBookingForm.getDestnLocus());
                }
            }

            waybillTraceOperateBean.setDispatchId(mobileStatusOperateReq.getOrderId());
            if (mobileStatusOperateReq.getRoleId() != null) {
                waybillTraceOperateBean.setRoleId(mobileStatusOperateReq.getRoleId());
            }
            insertWaybillTrace(waybillTraceOperateBean);
            if (MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus())) {
                //起点为POP取O单下单人的账号
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
                giOrderTraceResynced.setUserCodeFrom(bookingForm.getCreateUser());
            } else if (MobileStationDefine.M.equals(mobileBookingForm.getStartLocus())) {
                //起点为咪站，取订单下单人账号
                giOrderTraceResynced.setUserCodeFrom(mobileBookingForm.getCreateUser());
            } else {
                //起点为蛙站，取蛙站账号
                if (!StringUtil.isEmpty(mobileBookingForm.getStartLocus())) {
                    String acctUsername = mobileMyOrderDao.queryAcctUsernameByCustTtl(mobileBookingForm.getStartLocus());
                    giOrderTraceResynced.setUserCodeFrom(acctUsername);
                }
            }
            if (mobileStatusOperateReq.getRoleId() != null) {
                giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(mobileStatusOperateReq.getRoleId()));
            }
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
        } else {
            throw new MobileStationBizException("送达确认失败！");
        }
        return baseResBean;
    }

    /**
     * 插入跟踪日志
     *
     * @param waybillTraceOperateBean
     * @throws Exception
     */
    @Override
    @Transactional
    public void insertWaybillTrace(WaybillTraceOperateBean waybillTraceOperateBean) {
        List<ComWaybillTrace> tmps = new ArrayList<>();
        ComWaybillTrace tmp;
        if (waybillTraceOperateBean != null) {
            if (StringUtil.isEmpty(waybillTraceOperateBean.getScheducarno())) {
                // 订单，根据订单号获取订单信息，直接插入订单的跟踪日志
                tmp = new ComWaybillTrace();
                tmp.setAcctUsername(waybillTraceOperateBean.getAcctUsername());
                tmp.setBusiBookNo(waybillTraceOperateBean.getBusiBookNo());
                tmp.setStartLocus(waybillTraceOperateBean.getStartLocus());
                tmp.setDestnLocus(waybillTraceOperateBean.getDestnLocus());
                tmp.setGrade(waybillTraceOperateBean.getGrade());
                tmp.setRemark(waybillTraceOperateBean.getRemark());
                tmp.setExecCode(waybillTraceOperateBean.getExecCode());
                tmp.setStaDate(new Date());
                if (waybillTraceOperateBean.getRoleId() != null) {
                    tmp.setRoleId(waybillTraceOperateBean.getRoleId());
                }
                if (waybillTraceOperateBean.getRealName() != null) {
                    tmp.setRealName(waybillTraceOperateBean.getRealName());
                }
                tmps.add(tmp);
//                comWaybillTraceService.insert(tmp);
            } else {
                // 派车单，需要插入派车单和所有子订单的跟踪日志
                // 插入主单日志
                tmp = new ComWaybillTrace();
                tmp.setAcctUsername(waybillTraceOperateBean.getAcctUsername());
                tmp.setBusiBookNo(waybillTraceOperateBean.getScheducarno());
                tmp.setStartLocus(waybillTraceOperateBean.getStartLocus());
                tmp.setDestnLocus(waybillTraceOperateBean.getDestnLocus());
                tmp.setGrade(waybillTraceOperateBean.getGrade());
                tmp.setRemark(waybillTraceOperateBean.getRemark());
                tmp.setExecCode(waybillTraceOperateBean.getExecCode());
                tmp.setHubNo(waybillTraceOperateBean.getScheducarno());
                tmp.setStaDate(new Date());
                if (waybillTraceOperateBean.getRoleId() != null) {
                    tmp.setRoleId(waybillTraceOperateBean.getRoleId());
                }
                tmp.setRealName(waybillTraceOperateBean.getRealName());
                tmps.add(tmp);
//                comWaybillTraceService.insert(tmp);
                // 插入子弹日志
                List<MobileScheduSubOrder> bookingForms = mobileMyOrderDao
                        .selectMobileSubOrderByMobileId(waybillTraceOperateBean.getDispatchId());
                for (int i = 0; i < bookingForms.size(); i++) {
                    ComWaybillTrace sontmp = new ComWaybillTrace();
                    sontmp.setAcctUsername(waybillTraceOperateBean.getAcctUsername());
                    sontmp.setBusiBookNo(bookingForms.get(i).getBusiBookNo());
                    sontmp.setStartLocus(waybillTraceOperateBean.getStartLocus());
                    sontmp.setDestnLocus(waybillTraceOperateBean.getDestnLocus());
                    sontmp.setGrade(waybillTraceOperateBean.getGrade());
                    sontmp.setRemark(waybillTraceOperateBean.getRemark());
                    sontmp.setExecCode(waybillTraceOperateBean.getExecCode());
                    sontmp.setStaDate(new Date());
                    if (waybillTraceOperateBean.getRoleId() != null) {
                        sontmp.setRoleId(waybillTraceOperateBean.getRoleId());
                    }
                    sontmp.setRealName(waybillTraceOperateBean.getRealName());
                    sontmp.setHubNo(waybillTraceOperateBean.getScheducarno());
                    tmps.add(sontmp);
                }
            }
        }
        if (tmps.size() > 0) {
            DisruptorHelper.getInstance().producer(tmps);
        }
    }

    /**
     * 更新货物信息 根据主键判断如果有主键就更新，没有就插入
     *
     * @param batchUpdateGoodsInfoReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public BatchModifyGoodsInfoResult batchModifyGoodsInfo(BatchUpdateGoodsInfoReq batchUpdateGoodsInfoReq)
            throws MobileStationBizException {
        BatchModifyGoodsInfoResult baseResBean = new BatchModifyGoodsInfoResult(batchUpdateGoodsInfoReq);
        BigDecimal weightSum = new BigDecimal(0);
        BigDecimal volumeSum = new BigDecimal(0);
        BigDecimal feewhtSum = new BigDecimal(0);
        BigDecimal qtySum = new BigDecimal(0);
        String volumeUnit;

        if (null != batchUpdateGoodsInfoReq.getGoodsInfoList()) {
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(batchUpdateGoodsInfoReq
                    .getOrderId());
            BookingForm bookingForm = null;
            if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
            } else {
                List<MobileScheduSubOrder> subOrderList = mobileMyOrderDao
                        .selectMobileSubOrderByMobileId(mobileBookingForm.getId());
                if (subOrderList.size() > 0) {
                    bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrderList.get(0).getBusiBookNo());
                }
            }
            if (null == bookingForm) {
                baseResBean.setRetMsg("没有子订单！");
                baseResBean.setRetCode(SystemDefine.FAILURE);
                return baseResBean;
            }
            List<BillingFormSalm> recordList = new ArrayList<BillingFormSalm>();
            mobileMyOrderDao.deleteGoodsByOrderId(batchUpdateGoodsInfoReq.getOrderId());// 删除原来的货物信息
            billingFormSalmDaoEx.deleteByBusiBooknoId(bookingForm.getId());
            int flag = 0;
            List<MobileGoodsDtl> goodsList = new ArrayList<>();
            for (MobileGoodsInfoReq updateGoodsInfoReq : batchUpdateGoodsInfoReq.getGoodsInfoList()) {
                MobileGoodsDtl goodsDtl = new MobileGoodsDtl();
                BillingFormSalm billingFormSalm = new BillingFormSalm();
                goodsDtl.setMobileBookingFormId(batchUpdateGoodsInfoReq.getOrderId());
                goodsDtl.setGoodsType(updateGoodsInfoReq.getGoodsType());
                goodsDtl.setGoodsName(updateGoodsInfoReq.getGoodsName());
                goodsDtl.setGoodsQty(new BigDecimal(updateGoodsInfoReq.getGoodsQty()));
                goodsDtl.setGoodsQtyUnit(updateGoodsInfoReq.getGoodsQtyUnit());
                if (StringUtil.isEmpty(updateGoodsInfoReq.getGoodsLength())) {
                    updateGoodsInfoReq.setGoodsLength("0");
                }
                if (StringUtil.isEmpty(updateGoodsInfoReq.getGoodsWide())) {
                    updateGoodsInfoReq.setGoodsWide("0");
                }
                if (StringUtil.isEmpty(updateGoodsInfoReq.getGoodsHeight())) {
                    updateGoodsInfoReq.setGoodsHeight("0");
                }
                BigDecimal goodsLength = new BigDecimal(updateGoodsInfoReq.getGoodsLength());
                BigDecimal goodsWith = new BigDecimal(updateGoodsInfoReq.getGoodsWide());
                BigDecimal goodsHeight = new BigDecimal(updateGoodsInfoReq.getGoodsHeight());
                BigDecimal volume;
                if (null == updateGoodsInfoReq.getGoodsVolume()) {
                    volume = (goodsLength.multiply(goodsWith).multiply(goodsHeight)).setScale(4, BigDecimal.ROUND_HALF_UP);
                } else {
                    volume = new BigDecimal(updateGoodsInfoReq.getGoodsVolume()).setScale(4, BigDecimal.ROUND_HALF_UP);
                }
                if (volume.toString().length() > 19) {
                    baseResBean.setRetMsg("货物体积位数超过最大值");
                    baseResBean.setRetCode(SystemDefine.FAILURE);
                    logger.info("货物体积位数超过最大值" + volume.toString());
                    return baseResBean;
                }
                goodsDtl.setGoodsLenght(goodsLength.setScale(4, BigDecimal.ROUND_HALF_UP));
                goodsDtl.setGoodsWide(goodsWith.setScale(4, BigDecimal.ROUND_HALF_UP));
                goodsDtl.setGoodsHeight(goodsHeight.setScale(4, BigDecimal.ROUND_HALF_UP));
                goodsDtl.setGoodsVolume(volume);
                volumeUnit = updateGoodsInfoReq.getGoodsVolumeUnit();
                if (StringUtil.isEmpty(volumeUnit)) {
                    volumeUnit = "164";
                }
                goodsDtl.setGoodsVolumeUnit(volumeUnit);
                goodsDtl.setGoodsWeight(new BigDecimal(updateGoodsInfoReq.getGoodsWeight()).setScale(4, BigDecimal.ROUND_HALF_UP));
                goodsDtl.setGoodsWeightUnit(updateGoodsInfoReq.getGoodsWeightUnit());
                goodsDtl.setCreateUser(batchUpdateGoodsInfoReq.getAcctUsername());
                goodsDtl.setCreateDate(new Date());

                weightSum = weightSum.add(new BigDecimal(updateGoodsInfoReq.getGoodsWeight()));
                volumeSum = volumeSum.add(goodsDtl.getGoodsVolume());
                qtySum = qtySum.add(goodsDtl.getGoodsQty());

                billingFormSalm.setBusiBooknoId(bookingForm.getId());
                billingFormSalm.setHscodeNachs(updateGoodsInfoReq.getGoodsName());
                billingFormSalm.setHscodeSpecs(updateGoodsInfoReq.getGoodsType());
                billingFormSalm.setGoodsQty(goodsDtl.getGoodsQty());
                billingFormSalm.setGoodsQtyUnitCo(goodsDtl.getGoodsQtyUnit());
                billingFormSalm.setGoodsGrosswht(goodsDtl.getGoodsWeight());
                billingFormSalm.setWeightUnitCo(goodsDtl.getGoodsWeightUnit());
                //体积存储
                billingFormSalm.setGoodsLength(goodsDtl.getGoodsLenght().doubleValue());
                billingFormSalm.setGoodsWidth(goodsDtl.getGoodsWide().doubleValue());
                billingFormSalm.setGoodsHeight(goodsDtl.getGoodsHeight().doubleValue());
                BigDecimal goodsFeewht = goodsDtl.getGoodsLenght()
                        .multiply(goodsDtl.getGoodsWide())
                        .multiply(goodsDtl.getGoodsHeight())
                        .divide(new BigDecimal(6000), 8, BigDecimal.ROUND_HALF_EVEN);
                billingFormSalm.setGoodsFeewht(goodsFeewht);//设置计费体积重
                feewhtSum = feewhtSum.add(goodsFeewht);
                recordList.add(billingFormSalm);
                // 新增货物信息
                flag = mobileGoodsDtlDao.insert(goodsDtl);
                goodsList.add(goodsDtl);
            }
            //修改booking_form货物信息
            if (recordList.size() > 0) {
                billingFormSalmDaoEx.batchInsert(recordList);
                //add by yujie20170310 保存货物信息后，查询报价接口回写订单价格
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
                if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(bookingForm.getTransportType())) {
                    platFormInModel.setQuoteType(MobileStationDefine.QUOTE_TYPE_SPECIAL_DELIVERY);
                    platFormInModel.setMileage(bookingForm.getMileage());
                } else {
                    platFormInModel.setQuoteType(MobileStationDefine.QUOTE_TYPE_EXPRESS);
                }
                if (weightSum.compareTo(feewhtSum) > 0) {
                    platFormInModel.setWeight(weightSum);
                } else {
                    platFormInModel.setWeight(feewhtSum);
                }
                platFormInModel.setVolume(volumeSum.divide(new BigDecimal(1000000), 8, BigDecimal.ROUND_HALF_EVEN));
                platFormInModel.setCurrency("142");
                try {
                    logger.debug("print call calc interface -> query price busiNo : {},, param : {} ", bookingForm.getBusiBookNo(), JSON.toJSONString(platFormInModel));
                    PlatFormOutModel platFormOutModel = queryCalcManagerWebService.getQuote(platFormInModel);
                    if (platFormOutModel.getStatus() != null && "0".equals(platFormOutModel.getStatus())) {
                        List<PlatFormDetailModel> quoteDetailList = platFormOutModel.getQuoteDetailList();
                        if (quoteDetailList != null && quoteDetailList.size() > 0) {
                            PlatFormDetailModel platFormDetailModel = quoteDetailList.get(0);
                            logger.info("edit goods info ,busiNo : {}, predictValue : {}, docNo : {}", bookingForm.getBusiBookNo(),
                                    platFormDetailModel.getPredictValue(), platFormDetailModel.getQuoteNo());
                            bookingForm.setPredictValue(platFormDetailModel.getPredictValue().multiply(new BigDecimal(1.06)));
                            bookingForm.setDocno(platFormDetailModel.getQuoteNo());
                            bookingForm.setPredictCurr(platFormDetailModel.getCurrency());
                            baseResBean.setData(bookingForm);
                        }
                    }
                } catch (com.gistandard.transport.system.webservice.client.payinfo.Exception_Exception e) {
                    throw new MobileStationBizException(MobileStationRetCode.PRICE_QUERY_ERROR, e);
                }
            }
            if (flag > 0) {
                //如果是咪站，需要更新货物信息到 M-POD里面
                MobileBookingForm mobileBookingForm1 = mobileBookingFormDaoEx.selectByConditions(mobileBookingForm.getBusiBookNo(),
                        mobileBookingForm.getCreateUser(), mobileBookingForm.getCreateCompanyId(), MobileStationDefine.MOBILE_ORDER_STATUS_TO_DO, null);
                if (mobileBookingForm1 != null && !batchUpdateGoodsInfoReq.getOrderId().equals(mobileBookingForm1.getId()) && goodsList.size() > 0) {
                    for (MobileGoodsDtl good : goodsList) {
                        good.setMobileBookingFormId(mobileBookingForm1.getId());
                        good.setId(null);
                    }
                    mobileGoodsDtlDaoEx.batchInsert(goodsList);
                }
                BookingForm updBookingForm = new BookingForm();
                updBookingForm.setId(bookingForm.getId());
                updBookingForm.setWhtGrosswht(weightSum);
                updBookingForm.setWhtVolcbm(volumeSum);
                updBookingForm.setWhtVolwht(feewhtSum);
                //更新计费重量
                if (weightSum.compareTo(feewhtSum) > 0) {
                    updBookingForm.setWhtFeewht(weightSum);
                } else {
                    updBookingForm.setWhtFeewht(feewhtSum);
                }
                updBookingForm.setGoodsPackageQty(qtySum);
                updBookingForm.setGoodsQty(qtySum);
                if (bookingForm != null) {
                    updBookingForm.setPredictValue(bookingForm.getPredictValue());
                    updBookingForm.setDocno(bookingForm.getDocno());
                }
//                logger.info("订单表bookingForm信息 " + JSON.toJSONString(updBookingForm));
                bookingFormDao.updateByPrimaryKeySelective(updBookingForm);

                //不需要修改运费，现在是和平台结算
//                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
//                    // 修改O单的运费
//                    mobileBookingForm.setPredictValue(new BigDecimal(calcForPriceBean.getAmount()));
//                    mobileBookingForm.setPredictCurr(calcForPriceBean.getCurrency());
//                } else {
//                    // 修改派车单的运费
//                    if (mobileBookingForm.getQuotedType() != MobileStationDefine.QUOTE_TYPE_EXPRESS_PER) {
//                        calcForPriceReq.setComQuoteId(mobileBookingForm.getComQuoteId());
//                        BaseResBean mobilePriceRes = calcService.calcForTempPrice(calcForPriceReq);
//                        if (mobilePriceRes.getRetCode() == SystemDefine.FAILURE) {
//                            throw new MobileStationBizException("更新货物信息获取派车单运费失败：" + priceRes.getRetMsg());
//                        }
//                        CalcForPriceBean mobileCalcForPriceBean = (CalcForPriceBean) mobilePriceRes.getData();
//                        mobileBookingForm.setPredictValue(new BigDecimal(mobileCalcForPriceBean.getAmount()));
//                        mobileBookingForm.setPredictCurr(mobileCalcForPriceBean.getCurrency());
//                    }
//                }
                mobileBookingForm.setGoodsEditFlag(true);
                mobileBookingForm.setEditDate(new Date());
                mobileBookingForm.setEditUserId(batchUpdateGoodsInfoReq.getAccountId());
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);

            } else {
                throw new MobileStationBizException("更新货物信息失败！");
            }
        }
        return baseResBean;
    }

    /**
     * MS指派(或广播)
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult assignOrder(MobileOrderAssignReq mobileOrderAssignReq) throws MobileStationBizException {
        //快递员指派、广播 POP-POD、M-POD
        AppBaseResult baseResBean = new AppBaseResult(mobileOrderAssignReq);
        MobileBookingForm assignForm = mobileBookingFormDao.selectByPrimaryKey(mobileOrderAssignReq.getOrderId());
        int flag = 0;
        Integer dispatchId = 0;
        if (null != assignForm) {
            // 判断原单状态
            if (assignForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING || assignForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("MS指派失败,该订单已经被指派！");
                return baseResBean;
            }
            //校验是否发过广播单
            MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(mobileOrderAssignReq.getBusiBookNo(), null, null, MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER, null);
            if (mobileBookingForm != null) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("该订单已经广播单已存在,不能" + (mobileOrderAssignReq.isBroadcast() ? "重复发送！" : "指派"));
                return baseResBean;
            }
            if (!mobileOrderAssignReq.isBroadcast()) {
                //指派单就做签派，广播单在接单时候做签派
                // 获取签派单号
                dispatchId = assignForm.getDispatchId();
                String busiNo = null;
                if (StringUtil.isEmpty(assignForm.getScheducarno())) {
                    busiNo = assignForm.getBusiBookNo();
                }
                if (StringUtil.isEmpty(assignForm.getDispatchId())) {
                    dispatchId = 0;
                }
                logger.info("签派传参-UserID:" + mobileOrderAssignReq.getAccountId() + " BusiNo:" + busiNo + " dispatchId:"
                        + dispatchId + " ScheduCarNO:" + assignForm.getScheducarno()
                        + " fromGFuserId:" + assignForm.getRevUserId() + " toGFuserId:" + mobileOrderAssignReq.getRevUserId()
                        + " quotationNo:" + mobileOrderAssignReq.getComQuoteId());
                MobileToMobileDataResult mTmRes = mobileRecOrderWebService.mobileToMobile(
                        mobileOrderAssignReq.getAccountId(),
                        busiNo, dispatchId, assignForm.getScheducarno(), assignForm.getRevUserId(),
                        mobileOrderAssignReq.getRevUserId(), mobileOrderAssignReq.getComQuoteId());
                if (mTmRes.getStatus() == 0) {
                    throw new MobileStationBizException(mTmRes.getMesasge());
                } else {
                    // 修改原单状态
                    assignForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING);
                    flag = mobileMyOrderDao.updateByPrimaryKey2(assignForm);
                    if (flag == 0) {
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("MS指派失败,该订单已经被指派！");
                        return baseResBean;
                    }
                }
                dispatchId = (int) mTmRes.getDispatchId();
                logger.info("签派返回{}", JSON.toJSONString(mTmRes));
            }
            // 生产新的A-pod的订单
            MobileBookingForm assignA = new MobileBookingForm();
            try {
                PropertyUtils.copyProperties(assignA, assignForm);
                PropertyUtils.copyProperties(assignA, mobileOrderAssignReq);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                throw new MobileStationBizException("指派失败");
            }
            if (StringUtil.isEmpty(assignA.getProductType())) {
                assignA.setProductType(assignForm.getProductType());
            }
            if (null == assignA.getTransportType()) {
                assignA.setTransportType(assignForm.getTransportType());
            }
            assignA.setPayType(MobileStationDefine.PAYTYPE_GENERALACCT);// 平台支付
            if (assignA.getStartLocus().equals(MobileStationDefine.POP)) {
                assignA.setOrderType(1);// 都是取件单
            } else {
                assignA.setOrderType(2);// 都是派件单
            }

            assignA.setIsJs(0);// 都是未结算
            assignA.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);// 待接单
            assignA.setCreateDate(new Date());
            assignA.setCreateUser(assignForm.getRevUser());
            assignA.setCreateUserId(assignForm.getRevUserId());
            assignA.setCreateCompanyId(mobileOrderAssignReq.getCompanyAccountId());
            assignA.setShipCustProvide(assignForm.getShipCustProvide());
            assignA.setShipCustCity(assignForm.getShipCustCity());
            assignA.setShipCustCounty(assignForm.getShipCustCounty());
            assignA.setShipCustAddr(assignForm.getShipCustAddr());
            assignA.setShipCustLinkMan(assignForm.getShipCustLinkMan());
            assignA.setShipCustLinkTel(assignForm.getShipCustLinkTel());
            assignA.setShipLongitude(assignForm.getShipLongitude());
            assignA.setShipLatitude(assignForm.getShipLatitude());
            if (!mobileOrderAssignReq.isBroadcast()) {
                assignA.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS);
                assignA.setDispatchId(dispatchId);
            } else {
                assignA.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST);
            }
            assignA.setRevDate(null);
            assignA.setEditDate(null);
            assignA.setEditUser(null);
            assignA.setId(null);
            assignA.setEditUserId(null);
            assignA.setFormEditFlag(null);
            if (mobileOrderAssignReq.getDestRoleId().equals(SysAccountRole.OPERATOR_MSTATION.getValue())) {
                assignA.setStartLocus(MobileStationDefine.M);
                assignA.setStartLocusId(mobileOrderAssignReq.getRevUserId());
            }
            assignA.setRoleId(mobileOrderAssignReq.getDestRoleId());

            //查询报价
            PlatformQuote platformQuote = expressService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG, assignA.getBusiBookNo(), assignA.getStartLocus(), assignA.getDestnLocus(), mobileOrderAssignReq.getDestRoleId());
            if (platformQuote != null) {
                assignA.setPredictValue(platformQuote.getPrice());
                assignA.setPredictCurr(platformQuote.getCurrencyCode());
            } else {
                throw new MobileStationBizException("网络异常请重试");
            }

            flag = mobileBookingFormDao.insert(assignA);
            if (flag == 0) {
                throw new MobileStationBizException("此单已存在，请勿重复操作");
            }
            // 生成货物信息
            List<MobileGoodsDtl> goodsList = mobileMyOrderDao.selectMobileGoodsInfoByOrderId(assignForm.getId());
            if (goodsList.size() > 0) {
                int assignAId = assignA.getId();
                for (MobileGoodsDtl good : goodsList) {
                    good.setMobileBookingFormId(assignAId);
                    good.setId(null);
                }
                mobileGoodsDtlDaoEx.batchInsert(goodsList);
            }
            if (!StringUtil.isEmpty(assignForm.getScheducarno())) {
                List<MobileScheduSubOrder> subOrderList = mobileMyOrderDao.selectMobileSubOrderByOrderId(assignForm
                        .getId());
                for (MobileScheduSubOrder subOrder : subOrderList) {
                    subOrder.setMobileBookingFormId(assignA.getId());
                    subOrder.setId(null);
                }
                if (null != subOrderList && subOrderList.size() > 0) {
                    mobileScheduSubOrderDaoEx.batchInsert(subOrderList);
                }
            }

            if (flag > 0) {
                if (!mobileOrderAssignReq.isBroadcast()) {
                    //指派单就生成转单中心记录，如果是广播单则在接单时生成
                    MobileSingleCenter mobileSingleCenter = new MobileSingleCenter();
                    mobileSingleCenter.setMobileBookingFormId(assignForm.getId());
                    mobileSingleCenter.setBusiBookNo(assignForm.getBusiBookNo());
                    mobileSingleCenter.setCreateUserId(assignForm.getRevUserId());
                    mobileSingleCenter.setCreateUser(assignForm.getRevUser());
                    mobileSingleCenter.setCreateDate(new Date());
                    mobileSingleCenter.setRevUser(assignA.getRevUser());
                    mobileSingleCenter.setRevUserId(assignA.getRevUserId());
                    mobileSingleCenter.setDispatchId(dispatchId);
                    mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_TOACCEPT);
                    mobileSingleCenter.setSingleValue(mobileOrderAssignReq.getPredictValue());
                    mobileSingleCenter.setSingleCurr(mobileOrderAssignReq.getPredictCurr());
                    mobileSingleCenter.setSingleDate(new Date());
                    mobileSingleCenterDao.insert(mobileSingleCenter);
                }
            }
            if (mobileOrderAssignReq.isBroadcast()) {
                List<MobileBookingForm> mobileBookingForms = new ArrayList<>();
                mobileBookingForms.add(assignA);
                //广播单进行广播
                broadcastMSOrder(mobileBookingForms);
                assignForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_BROADCAST);
                List<MobileBookingForm> oldMobileBookingForms = new ArrayList<>();
                oldMobileBookingForms.add(assignForm);
                //广播成功修改订单状态
                mobileBookingFormDaoEx.batchUpdateOrderBusi(oldMobileBookingForms);

            }
        }
        if (flag < 1) {
            throw new MobileStationBizException("MS指派失败！");
        } else {
            //推送消息
            if (!mobileOrderAssignReq.isBroadcast()) {
                MsgIMReq msgIMReq = new MsgIMReq();
                try {
                    PropertyUtils.copyProperties(msgIMReq, mobileOrderAssignReq);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                // 推送消息给MS
                msgIMReq.setMsgTo(2);
                msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_ACCEPT_ORDER);
                msgIMReq.setCreateUser(mobileOrderAssignReq.getRevUser());
                sendMsg(msgIMReq);
            }
        }
        return baseResBean;
    }

    @Override
    public CheckAssignOrderforbatchResult checkAssignOrderforbatch(BatchMobileOrderAssignReq batchMobileOrderAssignReq) {
        CheckAssignOrderforbatchResult result = new CheckAssignOrderforbatchResult();
        //失败列表
        List<CheckAssignOrderforbatchFailed> faileds = new ArrayList<>();
        //成功列表
        List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> successes = new ArrayList<>();
        //批量签派请求列表
        List<MobileToMobileParam> mobileToMobileParams = new ArrayList<>();
        //以订单号+接单人为key，订单对象为value，生成映射，因为批量操作，用于确认匹配
        Map<String, MobileBookingForm> busiMap = new HashMap<>();
        //以订单号+接单人为key，单个请求对象为value，生成映射，因为批量操作，用于确认匹配
        Map<String, MobileOrderAssignReq> reqMap = new HashMap<>();
        for (MobileOrderAssignReq mobileOrderAssignReq : batchMobileOrderAssignReq.getMobileOrderAssignReqList()) {
            CheckAssignOrderforbatchFailed failed = new CheckAssignOrderforbatchFailed();
            MobileToMobileParam mobileToMobileParam = new MobileToMobileParam();
            MobileBookingForm assignForm = mobileBookingFormDao.selectByPrimaryKey(mobileOrderAssignReq.getOrderId());
            Integer dispatchId = 0;
            if (null != assignForm) {
                // 判断原单状态
                if (assignForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING || assignForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE) {
                    failed.setMsg("MS指派失败,该订单已经被指派！");
                    failed.setBusibookno(mobileOrderAssignReq.getBusiBookNo());
                    faileds.add(failed);
                    continue;
                }
                //校验是否发过广播单
                MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(mobileOrderAssignReq.getBusiBookNo(), batchMobileOrderAssignReq.getAcctUsername(),
                        batchMobileOrderAssignReq.getCompanyAccountId(), MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER, null);
                if (mobileBookingForm != null) {
                    failed.setMsg("该订单已经广播单已存在,不能" + (mobileOrderAssignReq.isBroadcast() ? "重复发送！" : "指派"));
                    failed.setBusibookno(mobileOrderAssignReq.getBusiBookNo());
                    faileds.add(failed);
                    continue;
                }
                if (!mobileOrderAssignReq.isBroadcast()) {
                    //指派单就做签派，广播单在接单时候做签派
                    // 获取签派单号
                    dispatchId = assignForm.getDispatchId();
                    String busiNo = null;
                    if (StringUtil.isEmpty(assignForm.getScheducarno())) {
                        busiNo = assignForm.getBusiBookNo();
                    }
                    if (StringUtil.isEmpty(assignForm.getDispatchId())) {
                        dispatchId = 0;
                    }
                    logger.info("签派传参-UserID:" + mobileOrderAssignReq.getAccountId() + " BusiNo:" + busiNo + " dispatchId:"
                            + dispatchId + " ScheduCarNO:" + assignForm.getScheducarno()
                            + " fromGFuserId:" + assignForm.getRevUserId() + " toGFuserId:" + mobileOrderAssignReq.getRevUserId()
                            + " quotationNo:" + mobileOrderAssignReq.getComQuoteId());
                    mobileToMobileParam.setUserID(batchMobileOrderAssignReq.getAccountId());
                    mobileToMobileParam.setBusiNo(busiNo);
                    mobileToMobileParam.setDispatchId(dispatchId);
                    mobileToMobileParam.setScheduCarNO(assignForm.getScheducarno());
                    mobileToMobileParam.setFromGFuserId(assignForm.getRevUserId());
                    mobileToMobileParam.setToGFuserId(mobileOrderAssignReq.getRevUserId());
                    mobileToMobileParam.setQuotationNo(mobileOrderAssignReq.getComQuoteId());
                    mobileToMobileParam.setKey(mobileOrderAssignReq.getRevUser());
                    mobileToMobileParams.add(mobileToMobileParam);

                    //生成一个映射，方便使用
                    busiMap.put(assignForm.getBusiBookNo() + mobileOrderAssignReq.getRevUser(), assignForm);
                    reqMap.put(assignForm.getBusiBookNo() + mobileOrderAssignReq.getRevUser(), mobileOrderAssignReq);
                } else {
                    CheckAssignOrderforbatchSuccess success = new CheckAssignOrderforbatchSuccess();
                    AssignOrderforbatchSuccess assignOrderforbatchSuccess = new AssignOrderforbatchSuccess();
                    assignOrderforbatchSuccess.setBusibookno(assignForm.getBusiBookNo());
                    assignOrderforbatchSuccess.setMobileOrderAssignReq(mobileOrderAssignReq);
                    assignOrderforbatchSuccess.setAssignForm(assignForm);
//                    success.setDispatchId();
                    success.setReq(assignOrderforbatchSuccess);
                    successes.add(success);
                }
            }
        }
        //批量签派，并做结果筛选
        if (mobileToMobileParams.size() > 0) {
            List<MobileToMobileDataResult> mobileToMobileDataResults = mobileRecOrderWebService.batchMobileToMobile(mobileToMobileParams);
            for (MobileToMobileDataResult mobileToMobileDataResult : mobileToMobileDataResults) {
                CheckAssignOrderforbatchFailed failed = new CheckAssignOrderforbatchFailed();
                CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess> success = new CheckAssignOrderforbatchSuccess();
                AssignOrderforbatchSuccess assignOrderforbatchSuccess = new AssignOrderforbatchSuccess();
                if (mobileToMobileDataResult.getStatus() == 0) {
                    failed.setMsg(mobileToMobileDataResult.getMesasge());
                    failed.setBusibookno(StringUtils.isBlank(mobileToMobileDataResult.getBusiNo()) ? mobileToMobileDataResult.getScheduCarNO() : mobileToMobileDataResult.getBusiNo());
                    faileds.add(failed);
                } else {
                    assignOrderforbatchSuccess.setBusibookno(StringUtils.isBlank(mobileToMobileDataResult.getBusiNo()) ? mobileToMobileDataResult.getScheduCarNO() : mobileToMobileDataResult.getBusiNo());
                    assignOrderforbatchSuccess.setMobileOrderAssignReq(reqMap.get(assignOrderforbatchSuccess.getBusibookno() + mobileToMobileDataResult.getKey()));
                    assignOrderforbatchSuccess.setAssignForm(busiMap.get(assignOrderforbatchSuccess.getBusibookno() + mobileToMobileDataResult.getKey()));
                    assignOrderforbatchSuccess.setDispatchId((int) mobileToMobileDataResult.getDispatchId());
                    success.setReq(assignOrderforbatchSuccess);
                    successes.add(success);
                }
            }
        }
        result.setCheckAssignOrderforbatchFaileds(faileds);
        result.setCheckAssignOrderforbatchSuccesses(successes);
        return result;
    }

    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public CheckAssignOrderforbatchResult doBatchMobileOrderAssign(List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> checkAssignOrderforbatchSuccesses,
                                                                   BatchMobileOrderAssignReq batchMobileOrderAssignReq) throws MobileStationBizException {
        StringBuffer idsBuffer = new StringBuffer();
        //失败列表
        List<CheckAssignOrderforbatchFailed> faileds = new ArrayList<>();
        List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> successes = new ArrayList<>();
        //批量报价列表查询对象
        List<QueryPlatformQuote2Req> queryPlatformQuote2Reqs = new ArrayList<>();
        //被指派单列表，用于批量修改状态
        List<MobileBookingForm> oldMobileBookingForms = new ArrayList<>();
        for (CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess> checkAssignOrderforbatchSuccess : checkAssignOrderforbatchSuccesses) {
            QueryPlatformQuote2Req queryPlatformQuote2Req = new QueryPlatformQuote2Req();
            CheckAssignOrderforbatchFailed failed = new CheckAssignOrderforbatchFailed();
            AssignOrderforbatchSuccess successreq = checkAssignOrderforbatchSuccess.getReq();
            // 生产新的A-pod的订单
            MobileBookingForm assignA = new MobileBookingForm();
            MobileBookingForm assignForm = successreq.getAssignForm();
            Integer dispatchId = successreq.getDispatchId();
            MobileOrderAssignReq mobileOrderAssignReq = successreq.getMobileOrderAssignReq();
            try {
                PropertyUtils.copyProperties(assignA, assignForm);
                PropertyUtils.copyProperties(assignA, mobileOrderAssignReq);

            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                failed.setBusibookno(assignForm.getBusiBookNo());
                failed.setMsg("指派失败");
                faileds.add(failed);
                continue;
            }
            if (StringUtil.isEmpty(assignA.getProductType())) {
                assignA.setProductType(assignForm.getProductType());
            }
            if (null == assignA.getTransportType()) {
                assignA.setTransportType(assignForm.getTransportType());
            }
            assignA.setPayType(MobileStationDefine.PAYTYPE_GENERALACCT);// 平台支付
            if (assignA.getStartLocus().equals(MobileStationDefine.POP)) {
                assignA.setOrderType(1);// 都是取件单
            } else {
                assignA.setOrderType(2);// 都是派件单
            }

            assignA.setIsJs(0);// 都是未结算
            assignA.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);// 待接单
            assignA.setCreateDate(new Date());
            assignA.setCreateUser(assignForm.getRevUser());
            assignA.setCreateUserId(assignForm.getRevUserId());
            assignA.setCreateCompanyId(batchMobileOrderAssignReq.getCompanyAccountId());
            if (!mobileOrderAssignReq.isBroadcast()) {
                assignA.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS);
                assignA.setDispatchId(dispatchId);
            } else {
                assignA.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST);
            }
            assignA.setRevDate(null);
            assignA.setEditDate(null);
            assignA.setEditUser(null);
            assignA.setId(null);
            assignA.setEditUserId(null);
            assignA.setFormEditFlag(null);
            assignA.setShipCustProvide(assignForm.getShipCustProvide());
            assignA.setShipCustCity(assignForm.getShipCustCity());
            assignA.setShipCustCounty(assignForm.getShipCustCounty());
            assignA.setShipCustLinkMan(assignForm.getShipCustLinkMan());
            assignA.setShipCustLinkTel(assignForm.getShipCustLinkTel());
            if (mobileOrderAssignReq.getDestRoleId().equals(SysAccountRole.OPERATOR_MSTATION.getValue())) {
                assignA.setStartLocus(MobileStationDefine.M);
                assignA.setStartLocusId(mobileOrderAssignReq.getRevUserId());
            } else if (mobileOrderAssignReq.getDestRoleId().equals(SysAccountRole.OPERATOR_COMPANY_STATION)) {
                ComCustomer comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(mobileOrderAssignReq.getRevUserId());
                if (comCustomer == null) {
                    failed.setBusibookno(assignForm.getBusiBookNo());
                    failed.setMsg("接单站点信息不存在");
                    faileds.add(failed);
                    continue;
                }
                assignA.setStartLocus(comCustomer.getCustTtl());
                assignA.setStartLocusId(comCustomer.getAccountId());
            }
            assignA.setRoleId(mobileOrderAssignReq.getDestRoleId());

            queryPlatformQuote2Req.setSystemFlag(SystemDefine.MOBILE_STATION_SYS_FLAG);
            queryPlatformQuote2Req.setBusibookno(assignA.getBusiBookNo());
            queryPlatformQuote2Req.setStartAccountId(assignA.getStartLocus());
            queryPlatformQuote2Req.setEndAccountId(assignA.getDestnLocus());
            queryPlatformQuote2Req.setRoleId(mobileOrderAssignReq.getDestRoleId());
            queryPlatformQuote2Reqs.add(queryPlatformQuote2Req);
            //查询报价
//            PlatformQuote platformQuote = expressService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG, assignA.getBusiBookNo(), assignA.getStartLocus(), assignA.getDestnLocus(), mobileOrderAssignReq.getDestRoleId());
//            if (platformQuote != null) {
//                assignA.setPredictValue(platformQuote.getPrice());
//                assignA.setPredictCurr(platformQuote.getCurrencyCode());
//            } else {
//                throw new MobileStationBizException("网络异常请重试");
//            }
            successreq.setAssignA(assignA);
            checkAssignOrderforbatchSuccess.setReq(successreq);
            successes.add(checkAssignOrderforbatchSuccess);

            if (mobileOrderAssignReq.isBroadcast()) {
                assignForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_BROADCAST);
                oldMobileBookingForms.add(assignForm);
            }
        }
        QueryBatchPlatformQuote2Result queryBatchPlatformQuote2Result = expressService.queryBatchPlatformQuote2(queryPlatformQuote2Reqs);
        faileds.addAll(queryBatchPlatformQuote2Result.getFaileds());
        Map<String, PlatformQuote> platformQuoteMap = queryBatchPlatformQuote2Result.getPlatformQuoteMap();
        List<MobileBookingForm> assignAs = new ArrayList<>();
        List<MobileGoodsDtl> goodsListAll = new ArrayList<>();
        List<MobileScheduSubOrder> subOrderListAll = new ArrayList<>();
        List<MobileSingleCenter> mobileSingleCenters = new ArrayList<>();
        List<MobileBookingForm> mobileBookingForms = new ArrayList<>();
        for (CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess> checkAssignOrderforbatchSuccess : successes) {
            AssignOrderforbatchSuccess successreq = checkAssignOrderforbatchSuccess.getReq();
            MobileBookingForm assignA = successreq.getAssignA();
            if (!platformQuoteMap.containsKey(assignA.getBusiBookNo()))
                continue;
            PlatformQuote platformQuote = platformQuoteMap.get(assignA.getBusiBookNo());
            assignA.setPredictValue(platformQuote.getPrice());
            assignA.setPredictCurr(platformQuote.getCurrencyCode());
            assignAs.add(assignA);
            mobileBookingFormDao.insert(assignA);
        }
        for (CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess> checkAssignOrderforbatchSuccess : successes) {
            AssignOrderforbatchSuccess successreq = checkAssignOrderforbatchSuccess.getReq();
            MobileBookingForm assignA = successreq.getAssignA();
            if (!platformQuoteMap.containsKey(assignA.getBusiBookNo()))
                continue;
            MobileBookingForm assignForm = successreq.getAssignForm();
            Integer dispatchId = successreq.getDispatchId();
            MobileOrderAssignReq mobileOrderAssignReq = successreq.getMobileOrderAssignReq();
            // 生成货物信息
            List<MobileGoodsDtl> goodsList = mobileMyOrderDao.selectMobileGoodsInfoByOrderId(assignForm.getId());
            if (goodsList.size() > 0) {
                int assignAId = assignA.getId();
                for (MobileGoodsDtl good : goodsList) {
                    good.setMobileBookingFormId(assignAId);
                    good.setId(null);
                    goodsListAll.add(good);
                }
            }
            if (!StringUtil.isEmpty(assignForm.getScheducarno())) {
                List<MobileScheduSubOrder> subOrderList = mobileMyOrderDao.selectMobileSubOrderByOrderId(assignForm
                        .getId());
                for (MobileScheduSubOrder subOrder : subOrderList) {
                    subOrder.setMobileBookingFormId(assignA.getId());
                    subOrder.setId(null);
                    subOrderListAll.add(subOrder);
                }
            }

            if (!mobileOrderAssignReq.isBroadcast()) {
                //指派单就生成转单中心记录，如果是广播单则在接单时生成
                MobileSingleCenter mobileSingleCenter = new MobileSingleCenter();
                mobileSingleCenter.setMobileBookingFormId(assignForm.getId());
                mobileSingleCenter.setBusiBookNo(assignForm.getBusiBookNo());
                mobileSingleCenter.setCreateUserId(assignForm.getRevUserId());
                mobileSingleCenter.setCreateUser(assignForm.getRevUser());
                mobileSingleCenter.setCreateDate(new Date());
                mobileSingleCenter.setRevUser(assignA.getRevUser());
                mobileSingleCenter.setRevUserId(assignA.getRevUserId());
                mobileSingleCenter.setDispatchId(dispatchId);
                mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_TOACCEPT);
                mobileSingleCenter.setSingleValue(mobileOrderAssignReq.getPredictValue());
                mobileSingleCenter.setSingleCurr(mobileOrderAssignReq.getPredictCurr());
                mobileSingleCenter.setSingleDate(new Date());
                mobileSingleCenters.add(mobileSingleCenter);
            } else {
                mobileBookingForms.add(assignA);
            }
            //推送消息
            if (!mobileOrderAssignReq.isBroadcast()) {
                MsgIMReq msgIMReq = new MsgIMReq();
                try {
                    PropertyUtils.copyProperties(msgIMReq, mobileOrderAssignReq);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                // 推送消息给MS
                msgIMReq.setMsgTo(2);
                msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_ACCEPT_ORDER);
                msgIMReq.setCreateUser(mobileOrderAssignReq.getRevUser());
                sendMsg(msgIMReq);
            }
            if (!mobileOrderAssignReq.isBroadcast()) {
                idsBuffer.append(assignForm.getId());
                idsBuffer.append(",");
            }
//            success.setBusibookno(assignForm.getBusiBookNo());
//            successes.add(success);
        }
        String ids = idsBuffer.toString();
        if (!StringUtils.isBlank(ids)) {
            ids = ids.substring(0, ids.length() - 1);
            mobileMyOrderDao.batchUpdateByPrimaryKey2(ids, MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING);
        }
        if (assignAs.size() > 0) {
//            mobileBookingFormDaoEx.batchInsert(assignAs);
        }
        if (goodsListAll.size() > 0) {
            mobileGoodsDtlDaoEx.batchInsert(goodsListAll);
        }
        if (subOrderListAll.size() > 0) {
            mobileScheduSubOrderDaoEx.batchInsert(subOrderListAll);
        }
        if (mobileSingleCenters.size() > 0) {
            mobileSingleCenterDaoEx.batchInsert(mobileSingleCenters);
        }
        //广播单进行批量广播
        if (mobileBookingForms.size() > 0) {
            broadcastMSOrder(mobileBookingForms);
            //广播成功修改订单状态
            mobileBookingFormDaoEx.batchUpdateOrderBusi(oldMobileBookingForms);
        }
        CheckAssignOrderforbatchResult result = new CheckAssignOrderforbatchResult();
        result.setCheckAssignOrderforbatchFaileds(faileds);
        result.setCheckAssignOrderforbatchSuccesses(successes);
        return result;
    }

    @Override
    public void broadcastOrder(BroadcastOrderReq req) throws MobileStationBizException {
        if (req != null && req.getBusibooknos() != null && req.getBusibooknos().size() > 0) {
            List<MobileBookingForm> mobileBookingForms = new ArrayList<>();
            for (String busibookno : req.getBusibooknos()) {
                //校验是否发过广播单
                MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(busibookno, null, null, MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER, null);
                if (mobileBookingForm != null) {
                    mobileBookingForms.add(mobileBookingForm);
                }
            }
            if (mobileBookingForms.size() > 0) {
                broadcastMSOrder(mobileBookingForms);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public void cacelMobileBroadcastOrder(CacelMobileBroadcastOrderReq cacelMobileBroadcastOrderReq) throws MobileStationBizException {
        MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(
                cacelMobileBroadcastOrderReq.getBookbusino(), null, null, MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER, null);
        if (mobileBookingForm == null)
            throw new MobileStationBizException("此广播单无法取消，请检查订单状态");
        if (!mobileBookingForm.getCreateUser().equals(cacelMobileBroadcastOrderReq.getAcctUsername()))
            throw new MobileStationBizException("此广播单建单人不是你，无法取消");

        // 修改订单状态为放弃
        MobileOrderOperateBean orderOperateBean = new MobileOrderOperateBean(cacelMobileBroadcastOrderReq.getAccountId(),
                mobileBookingForm.getId(),
                cacelMobileBroadcastOrderReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER,
                MobileStationDefine.MOBILE_ORDER_STATUS_GIVEUP);
        orderOperateBean.setOldStatusStr(String.valueOf(MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER));
        int flag = mobileStationOrderDao.giveUpOrder(orderOperateBean);
        if (flag < 1) {
            throw new MobileStationBizException("广播单取消失败");
        }
    }

    /**
     * 发送广播
     * 快递员广播寻找咪站  20->22
     *
     * @param mobileBookingForms
     */
    private void broadcastMSOrder(List<MobileBookingForm> mobileBookingForms) {
        try {
            List<GiBizOrder> giBizOrderList = new ArrayList<>();
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            for (MobileBookingForm mobileBookingForm : mobileBookingForms) {
                GiBizOrder giBizOrder = new GiBizOrder();
                giBizOrder.setAppTag(CustomerDefine.IM_MS_DEFINE);
                giBizOrder.setAction(MobileStationDefine.GPS_ACTION_RESYNC_DATA);
                giBizOrder.setDocId(mobileBookingForm.getId());
                giBizOrder.setDocNo(mobileBookingForm.getBusiBookNo());
                giBizOrder.setDocFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST + "");
                giBizOrder.setDocType(mobileBookingForm.getOrderType());
                giBizOrder.setProductType(mobileBookingForm.getProductType());

                giBizOrder.setTotalPrice(mobileBookingForm.getPredictValue());
                giBizOrder.setTransportCost(mobileBookingForm.getPredictValue());
                giBizOrder.setPredictCurr(mobileBookingForm.getPredictCurr());
                if (null != mobileBookingForm.getPredictCurr()) {
                    giBizOrder.setCurrencyName(comCurrencyService.getComCurrencyByCode(mobileBookingForm.getPredictCurr()).getCurrencyCh());
                }

                if (!StringUtil.isEmpty(mobileBookingForm.getShipCustProvide()) && comProvinceMap.get(mobileBookingForm.getShipCustProvide()) != null) {
                    giBizOrder.setSourceProvince(comProvinceMap.get(mobileBookingForm.getShipCustProvide()).getProvinceName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getShipCustCity()) && comCityMap.get(mobileBookingForm.getShipCustCity()) != null) {
                    giBizOrder.setSourceCity(comCityMap.get(mobileBookingForm.getShipCustCity()).getName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getShipCustCounty()) && comCountyMap.get(mobileBookingForm.getShipCustCounty()) != null) {
                    giBizOrder.setSourceDistrict(comCountyMap.get(mobileBookingForm.getShipCustCounty()).getAreaName());
                }
                giBizOrder.setSourceAddress(mobileBookingForm.getShipCustAddr());
                giBizOrder.setSourceUserTel(mobileBookingForm.getShipCustLinkTel());
                giBizOrder.setSourceUserName(mobileBookingForm.getShipCustLinkMan());
                giBizOrder.setDestUserTel(mobileBookingForm.getCneeCustLinkTel());
                giBizOrder.setDestUserName(mobileBookingForm.getCneeCustLinkMan());
                GiPoint giPoint = new GiPoint();
                giPoint.setLongitude(mobileBookingForm.getShipLongitude().doubleValue());
                giPoint.setLatitude(mobileBookingForm.getShipLatitude().doubleValue());
                giBizOrder.setPointSource(giPoint);

                if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustProvide()) && comProvinceMap.get(mobileBookingForm.getCneeCustProvide()) != null) {
                    giBizOrder.setDestProvince(comProvinceMap.get(mobileBookingForm.getCneeCustProvide()).getProvinceName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCity()) && comCityMap.get(mobileBookingForm.getCneeCustCity()) != null) {
                    giBizOrder.setDestCity(comCityMap.get(mobileBookingForm.getCneeCustCity()).getName());
                }
                if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCounty()) && comCountyMap.get(mobileBookingForm.getCneeCustCounty()) != null) {
                    giBizOrder.setDestDistrict(comCountyMap.get(mobileBookingForm.getCneeCustCounty()).getAreaName());
                }
                giBizOrder.setDestAddress(mobileBookingForm.getCneeCustAddr());
                giPoint.setLongitude(mobileBookingForm.getCneeLongitude().doubleValue());
                giPoint.setLatitude(mobileBookingForm.getCneeLatitude().doubleValue());
                giBizOrder.setPointDest(giPoint);

                if (!StringUtil.isEmpty(mobileBookingForm.getNarrate())) {
                    giBizOrder.setDescription(mobileBookingForm.getNarrate());
                }

                List<String> allModuleCode = new ArrayList<>();
                allModuleCode.add(MobileStationDefine.PRODUCT_TYPE_OTCKDM);
                giBizOrder.setAllModuleCode(allModuleCode);
                List<String> allRoleCode = new ArrayList<>();
                allRoleCode.add(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
                giBizOrder.setAllRoleCode(allRoleCode);
                giBizOrder.setIsRealName(true);
                giBizOrderList.add(giBizOrder);
            }
            logger.info("7 broadCast Order sendBroadCastOrderMessage ={}", JSONArray.toJSONString(giBizOrderList));
            gpsOrderService.sendBroadCastOrderMessage(giBizOrderList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量取消广播单订单
     * 快递员取消广播寻找咪站  22->40
     *
     * @param mobileBookingForms
     */

    private void cancelBroadcastMSOrder(List<MobileBookingForm> mobileBookingForms) {
        try {
            List<GiBizOrder> giBizOrderList = new ArrayList<>();
            for (MobileBookingForm mobileBookingForm : mobileBookingForms) {
                GiBizOrder giBizOrder = new GiBizOrder();
                giBizOrder.setAppTag(CustomerDefine.IM_MS_DEFINE);
                giBizOrder.setAction(MobileStationDefine.GPS_ACTION_DELETE);
                giBizOrder.setDocNo(mobileBookingForm.getBusiBookNo());
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                giBizOrderList.add(giBizOrder);
            }
            logger.info("7 cancelBroadCast Order sendBroadCastOrderMessage ={}", JSONArray.toJSONString(giBizOrderList));
            gpsOrderService.sendBroadCastOrderMessage(giBizOrderList);
            //广播取消，修改订单状态
            mobileBookingFormDaoEx.batchUpdateOrderBusi(mobileBookingForms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重新指派
     *
     * @throws MobileStationBizException
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult cancleAssign(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        int flag = 0;
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq
                .getOrderId());
        MobileSingleCenter mobileSingleCenter = new MobileSingleCenter();
        if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
            //指派单
            mobileSingleCenter = mobileMyOrderDao.querySingleCenter(mobileStatusOperateReq.getOrderId(),
                    MobileStationDefine.SINGLE_CENTER_TOACCEPT,
                    mobileStatusOperateReq.getAccountId());
            if (null == mobileSingleCenter) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("转单中心没有这条记录，请核实！");
                return baseResBean;
            }
            if (StringUtil.isEmpty(mobileSingleCenter.getTeamComsixNo())) {
                // MS TO MS
                MobileAssignBean mobileAssignBean = new MobileAssignBean();
                mobileAssignBean.setCreateUser(mobileStatusOperateReq.getAcctUsername());
                mobileAssignBean.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
                mobileAssignBean.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
                List<MobileBookingForm> forms = mobileMyOrderDao.selectMobileOrderList(mobileAssignBean);
                if (forms.size() > 0) {
                    MobileBookingForm formA = forms.get(0);
                    // 修改A到pod的指派单状态
                    if (formA.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL) {
                        formA.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                        flag = mobileBookingFormDao.updateByPrimaryKey(formA);
                    }
                } else {
                    baseResBean.setRetCode(SystemDefine.FAILURE);
                    baseResBean.setRetMsg("指派单已经接单不能取消！");
                    return baseResBean;
                }
            } else {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("订单状态不对，不能重新指派！");
                return baseResBean;
            }
        } else if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_BROADCAST) {
            //广播单
            MobileAssignBean mobileAssignBean = new MobileAssignBean();
            mobileAssignBean.setCreateUser(mobileStatusOperateReq.getAcctUsername());
            mobileAssignBean.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
            mobileAssignBean.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
            List<MobileBookingForm> forms = mobileMyOrderDao.selectMobileOrderListByCondition(mobileAssignBean);
            if (forms.size() > 0) {
                MobileBookingForm formA = forms.get(0);
                // 修改A到pod的指派单状态
                if (formA.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL) {
                    formA.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                    flag = mobileBookingFormDao.updateByPrimaryKey(formA);
                    List<GiBizOrder> giBizOrderList = new ArrayList<>();
                    GiBizOrder giBizOrder = new GiBizOrder();
                    giBizOrder.setAction(MobileStationDefine.GPS_ACTION_DELETE);
                    giBizOrder.setAppTag(CustomerDefine.IM_MS_DEFINE);
                    giBizOrder.setDocNo(formA.getBusiBookNo());
                    giBizOrderList.add(giBizOrder);
                    gpsOrderService.sendBroadCastOrderMessage(giBizOrderList);
                }
            } else {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("广播单已经被接单不能取消！");
                return baseResBean;
            }
        } else {
            // MS TO HUB
            flag = 1;
        }
        if (flag > 0) {
            // 当前订单,状态改成发车
            mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
            mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
            // 修改转单中心表状态为拒绝
            if (mobileSingleCenter != null && mobileSingleCenter.getId() != null) {
                mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_REFUSE);
                mobileSingleCenterDao.updateByPrimaryKey(mobileSingleCenter);
                // 调用接口取消签派
                RequestDetailResult res = mobileRecOrderWebService.cancelMobileDispatch(
                        mobileStatusOperateReq.getAccountId(), mobileSingleCenter.getDispatchId());
                if (res.getStatus() != 1) {
                    throw new MobileStationBizException("取消签派失败：" + res.getMesasge());
                }
                logger.info("取消签派成功");
            }
        } else {
            throw new MobileStationBizException("取消指派失败！");
        }
        return baseResBean;
    }

    /**
     * 批量指派
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult hubAssignOrder(BatchAssignReq batchAssignReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(batchAssignReq);
        // 调用签派接口进行批量指派
        List<OrderObject> orderList = new ArrayList<>();
        for (AssignOrderBean assignOrder : batchAssignReq.getOrderBeanList()) {
            OrderObject orderObject = new OrderObject();
            orderObject.setBusiNo(assignOrder.getBusiBookNo());
            orderObject.setQuotationNo(assignOrder.getComQuoteId());
            orderList.add(orderObject);
        }
        MobileToHubDataResult result = mobileRecOrderWebService.mobileToHub(batchAssignReq.getAccountId(), orderList,
                batchAssignReq.getToGFuserID());
        if (result.getStatus() == 0) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg(result.getMesasge());
        } else {
            MobileSingleCenter mobileSingleCenter;
            MobileBookingForm mobileBookingForm;
            for (OrderObject orderObject : result.getResult()) {
                // 根据签派返回的签派ID回写用户订单表
                mobileSingleCenter = new MobileSingleCenter();
                // 修改原POP-POD订单状态为已签派，待接单
                mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(orderObject.getBusiNo(),
                        batchAssignReq.getAcctUsername(), batchAssignReq.getCompanyAccountId(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS, null);
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING);
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                mobileSingleCenter.setTeamComsixNo(batchAssignReq.getToGFuserTTL());
                mobileSingleCenter.setRevUser(batchAssignReq.getToGFuserName());
                mobileSingleCenter.setRevUserId(batchAssignReq.getToGFuserID());
                mobileSingleCenter.setMobileBookingFormId(mobileBookingForm.getId());
                mobileSingleCenter.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                mobileSingleCenter.setCreateUserId(mobileBookingForm.getRevUserId());
                mobileSingleCenter.setCreateUser(mobileBookingForm.getRevUser());
                mobileSingleCenter.setCreateDate(new Date());
                mobileSingleCenter.setDispatchId(orderObject.getDispatchId().intValue());
                mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_TOACCEPT);
                mobileSingleCenter.setSingleDate(new Date());
                /*
                 * mobileSingleCenter.setSingleValue(orderObject.getPredictValue(
				 * ));
				 * mobileSingleCenter.setSingleCurr(orderObject.getPredictCurr
				 * ());
				 */
                // mobileSingleCenter.setSingleCurr(singleCurr)
                mobileSingleCenterDao.insert(mobileSingleCenter);
            }
        }
        return baseResBean;
    }

    /**
     * MS指派MS送达确认接口，当startLocus为POM，destLocus与原始单相同时，送达确认
     *
     * @param deliVeryConfirmReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    @Transactional()
    public int deliveryConfirmFromMS(DeliVeryConfirmReq deliVeryConfirmReq) throws MobileStationBizException {
        // 根据订单号和接单人，查询订单列表
        int flag = 1;
        try {
            List<MobileBookingForm> mobileBookingFormList = mobileMyOrderDao.selectValidOrderList(deliVeryConfirmReq);
            for (MobileBookingForm mobileBookingForm : mobileBookingFormList) {
                if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE) {
                    if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.POM)) {
                        // 判断原始单的startLocus是不是POM，如果是，需要递归调用
                        DeliVeryConfirmReq req = new DeliVeryConfirmReq();
                        deliVeryConfirmReq.setBusiBookNo(deliVeryConfirmReq.getBusiBookNo());
                        deliVeryConfirmReq.setRevUser(mobileBookingForm.getCreateUser());
                        flag = deliveryConfirmFromMS(req);
                        if (flag == 0) {
                            throw new MobileStationBizException("送达确认失败！");
                        }
                    } else if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.POP)) {
                        // 如果是起点POP,修改booking_form 表状态
                        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(deliVeryConfirmReq
                                .getBusiBookNo());
                        bookingForm.setBusiCtrl(5);
                        bookingFormDaoEx.updateCtrl(bookingForm);
                    } else if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.M)) {
                        // 如果是起点POP,修改booking_form 表状态
//                        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(deliVeryConfirmReq
//                                .getBusiBookNo());
//                        bookingForm.setBusiCtrl(5);
//                        bookingFormDaoEx.updateCtrl(bookingForm);
                    } else {
                        if (mobileBookingForm.getScheducarno() != null && !mobileBookingForm.getScheducarno().startsWith("M_")) {
                            // 起点是HUB,通知HUB，调用HUB送达确认接口
                            SuccDeliverSchudeCarOrderRequest succDeliverSchudeCarOrderRequest = new SuccDeliverSchudeCarOrderRequest();
                            if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                                succDeliverSchudeCarOrderRequest.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                                succDeliverSchudeCarOrderRequest.setStaionCode(mobileBookingForm.getDestnLocus());
                                succDeliverSchudeCarOrderRequest.setDeliverAccount(mobileBookingForm.getRevUser());
                            } else {
                                succDeliverSchudeCarOrderRequest.setSchudeCarNo(deliVeryConfirmReq.getBusiBookNo());
                            }
                            succDeliverSchudeCarOrderRequest.setSchudeCarType(mobileBookingForm.getTransportType() + "");
                            succDeliverSchudeCarOrderRequest.setTotalLevel(true);
                            logger.info("确认送达传参:{}", JSON.toJSONString(succDeliverSchudeCarOrderRequest));
                            String expreessResStr = expreessOrderWebService
                                    .succDeliverSchudeCarOrder(succDeliverSchudeCarOrderRequest);
                            BaseResBean expreessRes = JSON.parseObject(expreessResStr, BaseResBean.class);
                            logger.info("确认送达结果{}", expreessRes.getRetMsg());
                            if (expreessRes == null || expreessRes.getRetCode() != 0) {
                                throw new MobileStationBizException(expreessRes.getRetMsg());
                            }
                        }
                    }

                    mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                    mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                }
            }
        } catch (MobileStationBizException e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    /**
     * 在送达时调用解冻接口
     *
     * @param busiBookNo
     * @param scheducarno
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public int confirmPayAtMS(String busiBookNo, String scheducarno) throws MobileStationBizException {
//        logger.info("解冻支付现金请求： " + busiBookNo + "----" + scheducarno);
//        ValidResultBean validResultBean;
//        if (StringUtil.isEmpty(scheducarno)) {
//            validResultBean = calcWebService.queryValidBillForPayStatus(busiBookNo, null);
//        } else {
//            validResultBean = calcWebService.queryValidBillForPayStatus(null, scheducarno);
//        }
//        logger.info("查询结算返回：{}", JSON.toJSONString(validResultBean));
//
//        //多个订单解冻
//        final List<OrderConfirmPayBean> orderConfirmPayBeanList = new ArrayList<>();
//        OrderConfirmPayBean orderConfirmPayBean = new OrderConfirmPayBean();
//        List<ConfirmPayOrderInfoBean> orderInfoBeanNeedConfirmList = new ArrayList<>();
//        for (ValidBean validBean : validResultBean.getAllValidBillMst()) {
//            List<BaseOrderInfoBean> baseOrderInfoBeanList = new ArrayList<>();
//            ConfirmPayOrderInfoBean confirmPayOrderInfoBean = new ConfirmPayOrderInfoBean();
//            confirmPayOrderInfoBean.setDocNo(validBean.getDocNo());
//            confirmPayOrderInfoBean.setType(1);
//            confirmPayOrderInfoBean.setMultiCalc(validBean.getIsMultiCalc());
//            if (validBean.getOrderInfoBeanOList() != null && validBean.getOrderInfoBeanOList().size() > 0) {
//                for (com.gistandard.transport.system.webservice.client.calcWebService.BaseOrderInfoBean orderInfoBean : validBean.getOrderInfoBeanOList()) {
//                    BaseOrderInfoBean baseOrderInfoBean = new BaseOrderInfoBean();
//                    baseOrderInfoBean.setDocNo(orderInfoBean.getDocNo());
//                    baseOrderInfoBean.setType(orderInfoBean.getType());
//                    baseOrderInfoBeanList.add(baseOrderInfoBean);
//                }
//            }
//            confirmPayOrderInfoBean.setOrderInfoBeanOList(baseOrderInfoBeanList);
//            orderInfoBeanNeedConfirmList.add(confirmPayOrderInfoBean);
//        }
//        //O单信息
//        if (validResultBean != null && validResultBean.getOValidBean() != null) {
//            BaseOrderInfoBean orderInfoBean = new BaseOrderInfoBean();
//            orderInfoBean.setDocNo(validResultBean.getOValidBean().getDocNo());
//            orderInfoBean.setType(1);
//            orderConfirmPayBean.setOrderInfoBeanO(orderInfoBean);
//        }
//        orderConfirmPayBean.setOrderInfoBeanNeedConfirmList(orderInfoBeanNeedConfirmList);
//        orderConfirmPayBeanList.add(orderConfirmPayBean);
//        logger.info("发送解冻支付现金请求{}", JSON.toJSONString(orderConfirmPayBeanList));
//        try {
//            jmsTemplateCalc.send(orderConfirmPayQueue, new MessageCreator() {
//                public Message createMessage(Session session) throws JMSException {
//                    return session.createTextMessage(JSON.toJSONString(orderConfirmPayBeanList));
//                }
//            });
//        } catch (JmsException e) {
//            e.printStackTrace();
//            logger.error("解冻支付现金请求发送失败,参数:{}", JSON.toJSONString(orderConfirmPayBeanList));
//        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public void exceptionReceive(ExceptionReceiveReq req) throws MobileStationBizException {
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusibookno());
        if (bookingForm != null) {
            throw new MobileStationBizException("此用户订单不存在");
        }
        MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(
                req.getBusibookno(), req.getAcctUsername(), req.getCompanyAccountId(), null, null);
        if (mobileBookingForm != null) {
            throw new MobileStationBizException("您的mobile station订单不存在");
        }
        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_EXCEPTION_RECE);
        mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
        bookingForm.setBusiCtrl(OrderState.EXCEPTION_RECE.getValue());
        bookingFormDaoEx.updateBookingForm(bookingForm);

        ComWaybillTrace tmp = new ComWaybillTrace();
        tmp.setAcctUsername(req.getAcctUsername());
        tmp.setBusiBookNo(req.getBusibookno());
        tmp.setStartLocus(bookingForm.getStartLocus());
        tmp.setDestnLocus(bookingForm.getDestnLocus());
        tmp.setGrade(1);
        tmp.setRemark(req.getReason());
        tmp.setExecCode(WayBillStatusDefine.EXCEPTION_RECE.getIntValue());
        tmp.setStaDate(new Date());
        comWaybillTraceService.insert(tmp);
    }

    /**
     * 推送IM消息
     * <p>
     * Title: sendMsg
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msgIMReq
     * @throws MobileStationBizException
     */
    public BaseResBean pushMessageIM(MsgIMReq msgIMReq) {
        logger.info("消息pushMessageIM参数{}", JSON.toJSONString(msgIMReq));
        String createUser = msgIMReq.getCreateUser();
        String sysTag = CustomerDefine.IM_MS_DEFINE;
        String sysCode = CustomerDefine.IM_MOBILE_STATION_DEFINE;
        BaseResBean res = new BaseResBean();
        try {
            if (msgIMReq.getOperateFrom() == 1) {// 下单推消息给MS
                ImPushUtil.sendMessageIM(sysCode, createUser, sysTag,
                        msgIMReq.getRemindCode(), msgIMReq.getMapObject());
                return res;
            }
            if (msgIMReq.getMsgTo() == 1) {// 推消息给用户
                if (StringUtil.isEmpty(msgIMReq.getScheducarno())) {
                    BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(msgIMReq.getBusiBookNo());
                    createUser = bookingForm.getCreateUser();
                    if (bookingForm.getOrderForm() == null
                            || bookingForm.getOrderForm().equals(CustomerDefine.IM_ORDERFORM_APP)) {
                        sysTag = CustomerDefine.IM_MS_DEFINE;
                    } else if (bookingForm.getOrderForm() != null
                            && bookingForm.getOrderForm().equals(CustomerDefine.IM_ORDERFORM_BS)) {
                        sysTag = CustomerDefine.IM_BS_DEFINE;
                        sysCode = CustomerDefine.IM_TRANSPORT_DEFINE;
                    }
                    ImPushUtil.sendMessageIM(sysCode, createUser, sysTag,
                            msgIMReq.getRemindCode(), msgIMReq.getMapObject());
                } else {
                    Integer orderId = msgIMReq.getOrderId();
                    if (null == orderId) {
                        List<MobileBookingForm> formList = mobileMyOrderDao.selectMobileOrderByScheducarno(msgIMReq
                                .getScheducarno());
                        if (formList.size() > 0) {
                            orderId = formList.get(0).getId();
                        }
                    }
                    if (null != orderId) {
                        List<MobileScheduSubOrder> subOrderList = mobileMyOrderDao
                                .selectMobileSubOrderByMobileId(orderId);
                        for (MobileScheduSubOrder subOrder : subOrderList) {
                            // sendMsg(subOrder.getBusiBookNo(),msgIMReq);
                            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrder.getBusiBookNo());
                            createUser = bookingForm.getCreateUser();
                            if (bookingForm.getOrderForm() == null
                                    || bookingForm.getOrderForm().equals(CustomerDefine.IM_ORDERFORM_APP)) {
                                sysTag = CustomerDefine.IM_MS_DEFINE;
                                sysCode = CustomerDefine.IM_MOBILE_STATION_DEFINE;
                            } else if (bookingForm.getOrderForm().equals(CustomerDefine.IM_ORDERFORM_BS)) {
                                sysTag = CustomerDefine.IM_BS_DEFINE;
                                sysCode = CustomerDefine.IM_TRANSPORT_DEFINE;
                            }
                            msgIMReq.getMapObject().put("bookbusino", subOrder.getBusiBookNo());
                            ImPushUtil.sendMessageIM(sysCode, createUser, sysTag,
                                    msgIMReq.getRemindCode(), msgIMReq.getMapObject());
                        }
                    } else {
                        res.setRetCode(SystemDefine.FAILURE);
                        res.setRetMsg("参数错误，找不到订单！");
                        logger.info("推送IM消息错误：参数错误，找不到订单！");
                    }
                }
            } else if (msgIMReq.getMsgTo() == 2) {// 推送给MS
                if (StringUtil.isEmpty(createUser)) {
                    MobileBookingForm form = mobileBookingFormDao.selectByPrimaryKey(msgIMReq.getOrderId());
                    createUser = form.getCreateUser();
                }
                ImPushUtil.sendMessageIM(sysCode, createUser, sysTag,
                        msgIMReq.getRemindCode(), msgIMReq.getMapObject());
            }
        } catch (Exception e) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
            logger.info("推送IM消息错误：" + e.getMessage());
        }
        return res;

    }

    /**
     * 确认送达发送消息
     *
     * @param msgIMReq
     */
    @Override
    public void sendMsg(MsgIMReq msgIMReq) {
        Map<String, String> mapObject = new HashMap<String, String>();
        try {
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
            if (StringUtils.isNotBlank(msgIMReq.getFleetName())) {
                mapObject.put("fleetName", msgIMReq.getFleetName());
                mapObject.put("bidValue", msgIMReq.getBidValue() == null ? "" : msgIMReq.getBidValue().toString());
                mapObject.put("bidCurr", msgIMReq.getBidCurr() == null ? "" : msgIMReq.getBidCurr());
            }
            mapObject.put("msgTime", DateUtil.formatDate2Str(new Date(), DateUtil.C_TIME_PATTON_DEFAULT));
            int roleId;
            if (msgIMReq.getRoleId() != null) {
                roleId = msgIMReq.getRoleId();
            } else {
                List<ComAccountRoleRel> roleList = accountRoleRelDaoEx.queryByAccountId(msgIMReq.getAccountId());
                roleId = roleList.get(0).getRoleId();
            }

            // 设置账户角色
            if (roleId == 3) {
                mapObject.put("registerType", MobileStationDefine.OPERATOR_CAR_OWNER);
            } else if (roleId == 7) {
                mapObject.put("registerType", MobileStationDefine.OPERATOR_COURIER);
            } else {
                mapObject.put("registerType", MobileStationDefine.OPERATOR_MSTATION);
            }
            mapObject.put("bookbusino", msgIMReq.getBusiBookNo());
            msgIMReq.setMapObject(mapObject);
            pushMessageIM(msgIMReq);
        } catch (Exception e) {
            logger.info("推送IM消息参数错误：" + e.getMessage());
            e.printStackTrace();
        }
    }
}