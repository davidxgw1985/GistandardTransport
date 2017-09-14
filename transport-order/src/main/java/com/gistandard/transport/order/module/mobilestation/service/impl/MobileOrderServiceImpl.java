package com.gistandard.transport.order.module.mobilestation.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.order.bean.*;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.bean.MobileScheduSubOrder;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.ComVehicleInfoDao;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.base.entity.dao.ex.MobileFleetBiddingDaoEx;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.*;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.RouteInfo;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockInOutReq;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileUserOrderDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileUserOrderService;
import com.gistandard.transport.order.module.service.StatsBizOrderService;
import com.gistandard.transport.order.stock.service.MobileStockService;
import com.gistandard.transport.order.webservice.client.order.hub.*;
import com.gistandard.transport.quote.system.common.bean.ComQuotePriceBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.quote.system.database.services.ComQuoteService;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.constant.BookingFormIsJsDefine;
import com.gistandard.transport.system.constant.PayTypeDefine;
import com.gistandard.transport.system.frame.disruptor.DisruptorHelper;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.upload.service.UploadService;
import com.gistandard.transport.system.webservice.client.calcWebService.BidingInfoResultBean;
import com.gistandard.transport.system.webservice.client.calcWebService.CalcWebService;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
import com.gistandard.transport.system.webservice.client.payinfo.QueryCalcManagerWebService;
import com.gistandard.transport.system.webservice.client.payinfo.ValidBillMst;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.lang.Exception;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author: xgw
 * @ClassName: MobileOrderServiceImpl
 * @Date: 2016/12/9
 * @Description:
 */
@Service
public class MobileOrderServiceImpl implements MobileOrderService {

    private static final Logger logger = LoggerFactory.getLogger(MobileOrderServiceImpl.class);

    @Autowired
    private MobileOrderDao mobileOrderDao;
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
    private ComGoodsTypeService comGoodsTypeService;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;
    @Autowired
    private ComVehicleInfoDao comVehicleInfoDao;
    @Autowired
    private ComVehicleInfoDaoEx comVehicleInfoDaoEx;
    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;
    @Autowired
    private MobileOrderOperateDaoEx mobileOrderOperateDaoEx;
    @Autowired
    private MobileUserOrderService mobileUserOrderService;
    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;
    @Autowired
    private ComAccountService comAccountService;
    @Autowired
    private ExpreessOrderWebService expreessOrderWebService;
    @Autowired
    private ComAccountDaoEx comAccountDaoEx;
    @Autowired
    private ComQuoteService comQuoteService;
    @Autowired
    private ComAccountCategoryDaoEx comAccountCategoryDaoEx;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private PnWebService pnWebService;
    @Autowired
    private ComCountyDaoEx comCountyDaoEx;
    @Autowired
    private GpsLogService gpsLogService;
    @Autowired
    private MobileStationOrderDao mobileStationOrderDao;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private MobileUserOrderDao mobileUserOrderDao;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private MobileStockService mobileStockService;
    @Autowired
    private QueryCalcManagerWebService queryCalcManagerWebService;
    @Autowired
    private CalcWebService calcWebService;
    @Autowired
    private StatsBizOrderService statsBizOrderService;
    @Autowired
    private MobileValueAddDaoEx mobileValueAddDaoEx;
    @Autowired
    private MobileFleetBiddingDaoEx mobileFleetBiddingDaoEx;
    @Autowired
    private ComVehicleTypeService comVehicleTypeService;

    @Value("#{spring.sysGFCode}")
    private String sysGFCode;   //平台公布价结算时的平台账号

    /**
     * 订单管理-列表查询(用户单、商户单)
     *
     * @param mobileOrderListReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public MobileOrderListResult queryOrderList(MobileOrderListReq mobileOrderListReq) throws MobileStationBizException {
        MobileOrderListResult baseResPageBean = new MobileOrderListResult();
        List<MobileStationOrderListBean> orderListBeans;
        // 根据条件查询订单列表
        if (mobileOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
            logger.info("咪站订单管理-列表查询  ， 入参：{} ", JSON.toJSONString(mobileOrderListReq));
            orderListBeans = mobileOrderDao.queryMiOrderList(mobileOrderListReq);
        } else {
            logger.info("订单管理-列表查询  ， 入参：{} ", JSON.toJSONString(mobileOrderListReq));
            orderListBeans = mobileOrderDao.queryOrderList(mobileOrderListReq);
        }

        int recordCount = 0;
        List<MobileOrderListBean> orderList = new ArrayList<>();
        try {
            if (orderListBeans != null && orderListBeans.size() > 0) {
                // 查询订单总条数
                if (mobileOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                    recordCount = mobileOrderDao.getMiOrderListCount(mobileOrderListReq);
                } else {
                    recordCount = mobileOrderDao.getOrderListCount(mobileOrderListReq);
                }
                // 根据条件查询订单列表
                Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                Map<String, ComCity> comCityMap = comCityService.queryForMap();
                Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
                Map<String, ComCurrency> comCurrencyMap = comCurrencyService.queryForCurrencyEnMap();
                Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
                Map<String, ComGoodsType> comGoodsTypeMap = comGoodsTypeService.queryForMap();

                for (MobileStationOrderListBean orderListBean : orderListBeans) {
                    MobileOrderListBean orderBean = new MobileOrderListBean();
                    try {
                        PropertyUtils.copyProperties(orderBean, orderListBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new MobileStationBizException("参数格式异常");
                    }
                    // 设置订单入库状态
                    if (orderListBean.getFlag() == null) {
                        orderBean.setStockFlag(false);
                    } else {
                        orderBean.setStockFlag(true);
                    }
                    // 设置币制
                    if (!StringUtil.isEmpty(orderListBean.getPredictCurr())
                            && comCurrencyMap.get(orderListBean.getPredictCurr()) != null) {
                        orderBean.setPredictCurr(comCurrencyMap.get(orderListBean.getPredictCurr())
                                .getCurrencyCode());
                    }

                    // 根据省市区设置地址
                    String startAddress = "";
                    String destAddress = "";
                    if (!StringUtil.isEmpty(orderListBean.getStartProvide())
                            && comProvinceMap.get(orderListBean.getStartProvide()) != null) {
                        // 判断地址信息是否包含省
                        if (!orderListBean.getStartAddress().contains(comProvinceMap.get(orderListBean.getStartProvide()).getProvinceName())) {
                            startAddress += comProvinceMap.get(orderListBean.getStartProvide()).getProvinceName();
                            if (!orderListBean.getStartAddress().contains(comCityMap.get(orderListBean.getStartCity()).getName())) {
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
                    }
                    orderBean.setStartAddress(startAddress + orderListBean.getStartAddress());

                    if (!StringUtil.isEmpty(orderListBean.getDestProvide())
                            && comProvinceMap.get(orderListBean.getDestProvide()) != null) {
                        // 判断地址信息是否包含省
                        if (!orderListBean.getDestAddress().contains(comProvinceMap.get(orderListBean.getDestProvide()).getProvinceName())) {
                            destAddress += comProvinceMap.get(orderListBean.getDestProvide()).getProvinceName();
                            if (!orderListBean.getStartAddress().contains(comCityMap.get(orderListBean.getDestCity()).getName())) {
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
                    }
                    orderBean.setDestAddress(destAddress + orderListBean.getDestAddress());

                    // 设置描述信息
                    String description = "";
                    String weightUtil = "";
                    String qtyUtil = "";
                    if (StringUtil.isEmpty(orderListBean.getScheducarno())) {
                        if (SysAccountRole.OPERATOR_CAR_OWNER.getRoleCode().equals(mobileOrderListReq.getRoleId())) {
                            for (MobileGoodsInfo goodsInfo : orderListBean.getGoodsInfoList()) {
                                //司机的列表描述信息  货物类型+货物总件数+货物总量+增值服务
                                if (goodsInfo.getGoodsType() != null) {
                                    String typeName = goodsInfo.getGoodsType();
                                    if (comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                                        typeName = comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh();
                                    }
                                    description += typeName + ",";
                                }
                                if (goodsInfo.getGoodsQtyUnit() != null) {
                                    if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit())
                                            && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                                        qtyUtil = comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh();
                                    }

                                    description += " 总共" + goodsInfo.getGoodsQty().setScale(0, BigDecimal.ROUND_HALF_UP)
                                            + qtyUtil + ",";
                                }
                                if (goodsInfo.getGoodsWeight() != null) {
                                    if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit())
                                            && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                                        weightUtil = comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh();
                                    }

                                    description += goodsInfo.getGoodsWeight().setScale(2, BigDecimal.ROUND_HALF_UP)
                                            + weightUtil + ",";
                                }
                            }
                            //增值服务
                            List<MobileValueAdd> mobileValueAddList = mobileValueAddDaoEx.queryMobileValueAddList(orderListBean.getBookingFormId());
                            if (mobileValueAddList != null && mobileValueAddList.size() > 0) {
                                for (MobileValueAdd mobileValueAdd : mobileValueAddList) {
                                    description += mobileValueAdd.getValueAddName() + " ";
                                }
                            }
                        } else {
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
                                    description += "体积：" + goodsInfo.getGoodsLength().setScale(0, BigDecimal.ROUND_HALF_UP)
                                            + "*" + goodsInfo.getGoodsWide().setScale(0, BigDecimal.ROUND_HALF_UP) + "*"
                                            + goodsInfo.getGoodsHeight().setScale(0, BigDecimal.ROUND_HALF_UP) + "立方厘米";
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
                        }
                        if (orderListBean.getBfIsJs() != null && BookingFormIsJsDefine.PAY_SUCCESS.getValue().equals(orderListBean.getBfIsJs().toString())) {
                            orderBean.setPayFlag(true);
                        } else {
                            orderBean.setPayFlag(false);
                        }
                        if (StringUtils.isNotEmpty(orderListBean.getCreateRealName())) {
                            orderBean.setRealNameFlag(true);
                        } else {
                            orderBean.setRealNameFlag(false);
                        }
                    } else {
                        //设置子订单列表
                        List<MobileSubOrder> subOrderList = orderListBean.getSubOrderList();
                        if (subOrderList != null && subOrderList.size() > 0) {
                            MobileSubOrder tmp = subOrderList.get(0);
                            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(tmp.getSubBusiBookNo());
                            orderBean.setShipAddr(bookingForm.getCarriageReceiAddr());
                            tmp.setSubShipCustAddr(bookingForm.getCarriageReceiAddr());
                            subOrderList.set(0, tmp);

                            if (orderListBean.getDestnLocus().equals(MobileStationDefine.POD) && !StringUtil.isEmpty(orderListBean.getBfExpressOrderNo())) {
                                orderBean.setStydFlag(true);
                            } else {
                                orderBean.setStydFlag(false);
                            }
                            orderBean.setSubOrderList(subOrderList);// 设置子派车单
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
                            for (MobileSubOrder subOrder : subOrderList) {
                                if (goodsInfo.getMobileScheduOrderId() != null && goodsInfo.getMobileScheduOrderId().equals(subOrder.getSubId())) {
                                    goodsInfo.setMobileScheduOrderNo(subOrder.getSubBusiBookNo());
                                    break;
                                }
                            }
                        }
                        description += "重量：" + temp.setScale(2, BigDecimal.ROUND_HALF_UP) + weightUtil + " ";
                        if (SysAccountRole.OPERATOR_CAR_OWNER.getRoleCode().equals(mobileOrderListReq.getRoleId())) {
                            //增值服务
                            List<MobileValueAdd> mobileValueAddList = mobileValueAddDaoEx.queryMobileValueAddListByMoblieOrderId(orderListBean.getOrderId());
                            if (mobileValueAddList != null && mobileValueAddList.size() > 0) {
                                for (MobileValueAdd mobileValueAdd : mobileValueAddList) {
                                    description += mobileValueAdd.getValueAddName() + " ";
                                }
                            }
                        }
                        orderBean.setPayFlag(true);
                        orderBean.setRealNameFlag(true);
                    }

                    orderBean.setDescription(description);

                    orderBean.setMiAssignFlag(false);
                    //咪站的订单列表
                    if (mobileOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                        //设置咪站订单的操作标识
                        if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER && MobileStationDefine.M.equals(orderBean.getStartLocus())) {
                            //咪站取件,如果是客户自送的订单，需要编辑货物，不能批量收件
                            if ((orderBean.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL
                                    || orderBean.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST)
                                    && "1".equals(orderListBean.getCarriAgerecei())) {
                            } else {
                                orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_PICK_UP);
                            }
                        }
                        if ((MobileStationDefine.POP.equals(orderBean.getStartLocus())
                                && orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER)
                                || (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                                && MobileStationDefine.POD.equals(orderBean.getDestnLocus()))) {
                            //咪站派车快递员
                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_ASSIGNMENT_STATION);
                        }
//                        if (MobileStationDefine.M.equals(orderBean.getStartLocus())
//                                && orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
//                                && !StringUtil.isEmpty(orderBean.getScheducarno())
//                                && !MobileStationDefine.POD.equals(orderBean.getDestnLocus())) {
//                            //咪站派车司机
//                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_ASSIGNMENT_DRIVER);
//                        }
                        if (MobileStationDefine.M.equals(orderBean.getStartLocus())
                                && MobileStationDefine.POD.equals(orderBean.getDestnLocus())
                                && orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                                && !orderBean.getOrderFrom().equals(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU)
                                && !orderBean.getOrderFrom().equals(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST)) {
                            //咪站中转，目的地是POD才可以中转
                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_TRANSFER);
                        }
                        if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                                && MobileStationDefine.M.equals(orderBean.getStartLocus())
                                && StringUtil.isEmpty(orderBean.getScheducarno())
                                && !MobileStationDefine.POD.equals(orderBean.getDestnLocus())) {
                            //咪站批量排货派车
                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_ASSIGNMENT);
                        }
                        if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE
                                && !MobileStationDefine.POP.equals(orderBean.getStartLocus())) {
                            //咪站分拣出库
                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_STOCK_OUT);
                        }


                        //设置咪站指派的MS信息
                        if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE) {
                            MobileSingleCenter mobileSingleCenter = mobileOrderDao.querySingleCenter(orderBean.getOrderId(),
                                    MobileStationDefine.SINGLE_CENTER_ACCEPT,
                                    mobileOrderListReq.getAccountId());
                            if (mobileSingleCenter != null && mobileSingleCenter.getRevUserId() != null) {
                                ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(mobileSingleCenter.getRevUserId());
                                if (comUserinfo != null) {
                                    orderBean.setMsLinkName(comUserinfo.getRealName());
                                    orderBean.setMsLinkTel(comUserinfo.getTelephone());
                                }
                                if (mobileOrderListReq.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()) {
                                    //如果是车主，设置车牌号
                                    List<ComVehicleInfo> comVehicleInfoList = comVehicleInfoDaoEx.queryVehicleByAcctId(mobileSingleCenter.getRevUserId());
                                    if (comVehicleInfoList != null && comVehicleInfoList.size() > 0) {
                                        orderBean.setMsLinkCarNo(comVehicleInfoList.get(0).getTruckcode());
                                    }
                                }
                            } else {
                                if (orderListBean.getRevUserId() != 0) {
                                    ComAccount comAccount = comAccountDao.selectByPrimaryKey(orderListBean.getRevUserId());
                                    if (comAccount != null) {
                                        orderBean.setMsLinkName(comAccount.getRealName());
                                        orderBean.setMsLinkTel(comAccount.getTelephone());
                                    }
                                }
                            }
                        }

                        //咪站 M-POD 展示目的地市、区；M-W 展示W站点名称
                        String destAddressName = "";
                        if (orderBean.getDestnLocus().equals(MobileStationDefine.POD)) {
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
                        orderBean.setDestAddressName(destAddressName);

                        //如果是咪站竞价单，需要车队的报价时间
                        if (orderBean.getStartLocus().equals(MobileStationDefine.M) && !MobileStationDefine.POD.equals(orderBean.getDestnLocus())) {
                            MobileFleetBidding mobileFleetBidding = mobileFleetBiddingDaoEx.queryFleetBidding(orderBean.getBusiBookNo(), orderBean.getScheducarno());
                            if (mobileFleetBidding != null) {
                                orderBean.setQuoteTime(mobileFleetBidding.getCreateDate());
                                orderBean.setTaxRate(mobileFleetBidding.getTaxRate());
                                orderBean.setBidValue(mobileFleetBidding.getBidValue());
                                orderBean.setBidValueCurr(mobileFleetBidding.getBidCurr());
                                orderBean.setFleetName(mobileFleetBidding.getFleetName());
                            }
                        }
                        if ((orderListBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_FINISH
                                || orderListBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE)
                                && orderListBean.getBidBy() == null) {
                            orderBean.setMiAssignFlag(true);
                        }
                        orderBean.setMiBidTime(orderListBean.getCreateDate());
                    } else if (mobileOrderListReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                        //设置快递员订单的操作标识
                        if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER
                                && !MobileStationDefine.POP.equals(orderBean.getStartLocus())) {
                            //快递员取件
                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_PICK_UP);
                        }
//                        if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
//                            //快递员发车
//                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_SENDING);
//                        }
                        if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                                && MobileStationDefine.POP.equals(orderBean.getStartLocus())
                                && MobileStationDefine.POD.equals(orderBean.getDestnLocus())
                                && !MobileStationDefine.PRODUCT_TYPE_TCZS.equals(orderBean.getProductType())) {
                            //快递员指派
                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_ASSIGNMENT_COURIER);
                        }
                        if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                                && !MobileStationDefine.POD.equals(orderBean.getDestnLocus())) {
                            //快递员送达确认
                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_DELIVERY_CONFIRM);
                        }
                    } else if (mobileOrderListReq.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()) {
                        //设置司机订单的操作标识
//                        if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER) {
//                            //司机取件
//                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_PICK_UP);
//                        }
                        if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
                            //司机发车
                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_SENDING);
                        }
                        if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN) {
                            //司机送达确认
                            orderBean.setOperateFlag(orderBean.getOperateFlag() | MobileStationDefine.BATCH_DELIVERY_CONFIRM);
                        }
                    }

                    if (orderListBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                        // 签派待接单，需要显示待接单HUB信息
                        List<MobileSingleCenter> mobileSingleCenterList = mobileUserOrderDao.querySingleCenterByOrderId(orderListBean.getOrderId(), orderListBean.getRevUser(), 1);
                        if (mobileSingleCenterList != null && mobileSingleCenterList.size() > 0) {
                            if (mobileSingleCenterList.get(0).getTeamComsixNo() != null) {
                                List<ComCustomer> comCustomerList = comCustomerDaoEx.queryCompanyByParams(mobileSingleCenterList.get(0).getTeamComsixNo(), null);
                                if (comCustomerList != null && comCustomerList.size() > 0) {
                                    orderBean.setDestProvide(comCustomerList.get(0).getProvince());
                                    orderBean.setDestCity(comCustomerList.get(0).getCity());
                                    orderBean.setDestCounty(comCustomerList.get(0).getCounty());
                                    orderBean.setDestHubName(comCustomerList.get(0).getCustName());
                                    orderBean.setDestAddress(comCustomerList.get(0).getCustAdd());
                                    orderBean.setDestnLocus(mobileSingleCenterList.get(0).getTeamComsixNo());
                                    orderBean.setDestLongitude(comCustomerList.get(0).getStaLongitude());
                                    orderBean.setDestLatitude(comCustomerList.get(0).getStaLatitude());
                                    orderBean.setDestTel(comCustomerList.get(0).getCustTel());
                                }
                            }
                        }
                    }
                    //设置订单类型 1用户订单、2商户订单
                    if (!StringUtil.isEmpty(orderBean.getProductType())) {
                        if (orderBean.getProductType().startsWith("O")) {
                            //用户单
                            orderBean.setOrderStyle(1);
                        } else {
                            //商户单
                            orderBean.setOrderStyle(2);
                        }
                    }

                    if (orderListBean.getRoutePathInfo() != null) {
                        List<RouteInfo> routeList = JSON.parseArray(orderListBean.getRoutePathInfo(), RouteInfo.class);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < routeList.size(); i++) {
                            sb.append(routeList.get(i).getCustName());
                            if (i != routeList.size() - 1) {
                                sb.append("->");
                            }
                        }
                        orderBean.setOrderRouteInfo(sb.toString());
                    }
                    orderList.add(orderBean);
                }
            }
            baseResPageBean.setRecordCount(recordCount);
            baseResPageBean.setData(orderList);
        } catch (Exception e) {
            e.printStackTrace();
            baseResPageBean.setRecordCount(0);
            baseResPageBean.setRetCode(SystemDefine.FAILURE);
            baseResPageBean.setRetMsg("查询订单列表异常！");
        }
        baseResPageBean.setReqId(mobileOrderListReq.getReqId());
        return baseResPageBean;
    }

    /**
     * 订单管理-订单详细(用户单、商户单)
     *
     * @param mobileOrderDetailReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public MobileOrderDetailResult queryOrderDetail(MobileOrderDetailReq mobileOrderDetailReq) throws MobileStationBizException {
        MobileOrderDetailResult baseResBean = new MobileOrderDetailResult(mobileOrderDetailReq);
        MobileOrderDetailBean detailBean = null;
        if (null != mobileOrderDetailReq.getOrderId()) {
            detailBean = mobileOrderDao.queryOrderDetail(mobileOrderDetailReq.getOrderId());
        }
        if (detailBean == null) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("该订单不存在！");
        } else {
            if (detailBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                // 签派待接单，需要显示待接单HUB信息
                MobileSingleCenter mobileSingleCenter = mobileMyOrderDao.querySingleCenter(mobileOrderDetailReq.getOrderId(),
                        MobileStationDefine.SINGLE_CENTER_TOACCEPT,
                        mobileOrderDetailReq.getAccountId());
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
            List<MobileSubOrder> subOrderList = detailBean.getSubOrderList();
            List<String> subOrderNoList = new ArrayList<>();
            if (!StringUtil.isEmpty(detailBean.getScheducarno())) {
                // 设置子订单
                detailBean.setSubOrderList(detailBean.getSubOrderList());// 设置子派车单
                if (!detailBean.getScheducarno().startsWith("M_")) {
                    // 设置散件和集包列表
                    subOrderNoList = mobileMyOrderDao.selectMobileSubOrderNoList(detailBean.getScheducarno());
                } else {
                    for (MobileSubOrder subOrder : subOrderList) {
                        subOrderNoList.add(subOrder.getSubBusiBookNo());
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
                // 判断地址信息是否包含省
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
                    for (MobileSubOrder subOrder : subOrderList) {
                        if (goodsInfo.getMobileScheduOrderId() != null && goodsInfo.getMobileScheduOrderId().equals(subOrder.getSubId())) {
                            goodsInfo.setMobileScheduOrderNo(subOrder.getSubBusiBookNo());
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
                    bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrderList.get(0).getSubBusiBookNo());
                }
            }

            if (null != bookingForm) {
                detailBean.setBookingUser(bookingForm.getCreateUser());
                detailBean.setAccesstime(bookingForm.getAccesstime());
                detailBean.setPlatQuoteNo(bookingForm.getDocno());
                detailBean.setCreateUserTel(bookingForm.getShipCustlinkTel());
                detailBean.setDestCustLinkMan(bookingForm.getCneeCustLinkMan());
                detailBean.setDestCustLinkTel(bookingForm.getCneeCustLinkTel());
                if (bookingForm.getRoutePathInfo() != null) {
                    List<RouteInfo> routeInfoList = JSON.parseArray(bookingForm.getRoutePathInfo(), RouteInfo.class);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < routeInfoList.size(); i++) {
                        sb.append(routeInfoList.get(i).getCustName());
                        if (i != routeInfoList.size() - 1) {
                            sb.append("->");
                        }
                    }
                    detailBean.setOrderRouteInfo(sb.toString());
                }
                if (BookingFormIsJsDefine.PAY_SUCCESS.getValue().equals(bookingForm.getIsJs().toString())) {
                    detailBean.setPayFlag(true);
                } else {
                    detailBean.setPayFlag(false);
                    //设置O单对账单号
                    try {
                        ValidBillMst validBillMst = queryCalcManagerWebService.queryExpressTransportPayMoney(bookingForm.getBusiBookNo(), null, "82098f5ab036412d9042837036b02c72", sysGFCode, bookingForm.getPayUser());
                        if (validBillMst != null && validBillMst.getDocNo() != null) {
                            detailBean.setValidBillnoForOut(validBillMst.getDocNo());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (bookingForm.getIsJs() != null && (BookingFormIsJsDefine.CALC_SUCCESS.getValue().equals(bookingForm.getIsJs().toString())
                        || BookingFormIsJsDefine.BALANCE_SUCCESS.getValue().equals(bookingForm.getIsJs().toString())
                        || BookingFormIsJsDefine.PAY_SUCCESS.getValue().equals(bookingForm.getIsJs().toString()))) {
                    detailBean.setJsFlag(true);
                } else {
                    detailBean.setJsFlag(false);
                }
                detailBean.setDestProvideForOut(bookingForm.getCarriageDelivProvince());
                detailBean.setDestCityForOut(bookingForm.getCarriageDelivCity());
                detailBean.setDestCountyForOut(bookingForm.getCarriageDelivCounty());

                String destAreaForOut = "";
                if (!StringUtil.isEmpty(detailBean.getDestProvideForOut())
                        && comProvinceMap.get(detailBean.getDestProvideForOut()) != null) {
                    // 判断地址信息是否包含省
                    destAreaForOut += comProvinceMap.get(detailBean.getDestProvideForOut()).getProvinceName();
                    if (!StringUtil.isEmpty(detailBean.getDestCityForOut())
                            && comCityMap.get(detailBean.getDestCityForOut()) != null) {
                        destAreaForOut += comCityMap.get(detailBean.getDestCityForOut()).getName();
                    }
                    if (!StringUtil.isEmpty(detailBean.getDestCountyForOut())
                            && comCountyMap.get(detailBean.getDestCountyForOut()) != null) {
                        destAreaForOut += comCountyMap.get(detailBean.getDestCountyForOut()).getAreaName();
                    }
                }
                detailBean.setDestAreaForOut(destAreaForOut);
                detailBean.setDestAddressForOut(bookingForm.getCarriageDelivAddr());
                detailBean.setDestLinkManForOut(bookingForm.getCneeCustLinkMan());
                detailBean.setDestUserForOut(bookingForm.getReceiverUser());
                detailBean.setDestTelForOut(bookingForm.getCneeCustLinkTel());
                detailBean.setDestLongitudeForOut(bookingForm.getCarriageDelivLongitude());
                detailBean.setDestLatitudeForOut(bookingForm.getCarriageDelivLatitude());
            }
            //设置发货人、收货人账号信息
            if (!StringUtil.isEmpty(detailBean.getStartTel())) {
                detailBean.setStartUser(mobileUserOrderService.queryAccountByTelephone(detailBean.getStartTel()));
            }
            if (!StringUtil.isEmpty(detailBean.getDestTel())) {
                detailBean.setDestUser(mobileUserOrderService.queryAccountByTelephone(detailBean.getDestTel()));
            }
            if (MobileStationDefine.M.equals(detailBean.getStartLocus()) && MobileStationDefine.POD.equals(detailBean.getDestnLocus())) {
                MobileOrderOperate mobileOrderOperate = mobileOrderOperateDaoEx.getMobileOrderOperate(detailBean.getBusiBookNo(), 3);
                if (mobileOrderOperate != null) {
                    MobileOperateInfo mobileOperateInfo = new MobileOperateInfo();
                    BeanUtils.copyProperties(mobileOrderOperate, mobileOperateInfo);
                    detailBean.setMobileOperateInfo(mobileOperateInfo);
                }
            }
            baseResBean.setData(detailBean);
        }
        return baseResBean;
    }

    /**
     * 订单管理-订单详细(用户单、商户单)---咪站订单详情
     *
     * @param mobileOrderDetailReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public MobileOrderDetailResult queryMiOrderDetail(MobileOrderDetailReq mobileOrderDetailReq) throws MobileStationBizException {
        MobileOrderDetailResult baseResBean = new MobileOrderDetailResult(mobileOrderDetailReq);
        MobileOrderDetailBean detailBean = null;
        if (null != mobileOrderDetailReq.getOrderId()) {
            detailBean = mobileOrderDao.queryOrderDetail(mobileOrderDetailReq.getOrderId());
        }
        if (detailBean == null) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("该订单不存在！");
        } else {
            if (detailBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                // 签派待接单，需要显示待接单HUB信息
                MobileSingleCenter mobileSingleCenter = mobileMyOrderDao.querySingleCenter(mobileOrderDetailReq.getOrderId(),
                        MobileStationDefine.SINGLE_CENTER_TOACCEPT,
                        mobileOrderDetailReq.getAccountId());
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
            detailBean.setMiAssignFlag(false);
            List<MobileSubOrder> subOrderList = detailBean.getSubOrderList();
            List<String> subOrderNoList = new ArrayList<>();
            if (!StringUtil.isEmpty(detailBean.getScheducarno())) {
                // 设置子订单
                detailBean.setSubOrderList(detailBean.getSubOrderList());// 设置子派车单
                if (!detailBean.getScheducarno().startsWith("M_")) {
                    // 设置散件和集包列表
                    subOrderNoList = mobileMyOrderDao.selectMobileSubOrderNoList(detailBean.getScheducarno());
                } else {
                    for (MobileSubOrder subOrder : subOrderList) {
                        subOrderNoList.add(subOrder.getSubBusiBookNo());
                    }
                }
                detailBean.setSubOrderNoList(subOrderNoList);
                if ((detailBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_FINISH
                        || detailBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE)
                        && detailBean.getBidBy() == null) {
                    detailBean.setMiAssignFlag(true);
                }
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
                // 判断地址信息是否包含省
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
                    for (MobileSubOrder subOrder : subOrderList) {
                        if (goodsInfo.getMobileScheduOrderId() != null && goodsInfo.getMobileScheduOrderId().equals(subOrder.getSubId())) {
                            goodsInfo.setMobileScheduOrderNo(subOrder.getSubBusiBookNo());
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
                    bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrderList.get(0).getSubBusiBookNo());
                }
            }

            if (null != bookingForm) {
                detailBean.setBookingUser(bookingForm.getCreateUser());
                detailBean.setAccesstime(bookingForm.getAccesstime());
                detailBean.setPlatQuoteNo(bookingForm.getDocno());
                detailBean.setCreateUserTel(bookingForm.getShipCustlinkTel());
                detailBean.setDestCustLinkMan(bookingForm.getCneeCustLinkMan());
                detailBean.setDestCustLinkTel(bookingForm.getCneeCustLinkTel());
                if (bookingForm.getRoutePathInfo() != null) {
                    List<RouteInfo> routeInfoList = JSON.parseArray(bookingForm.getRoutePathInfo(), RouteInfo.class);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < routeInfoList.size(); i++) {
                        sb.append(routeInfoList.get(i).getCustName());
                        if (i != routeInfoList.size() - 1) {
                            sb.append("->");
                        }
                    }
                    detailBean.setOrderRouteInfo(sb.toString());
                }
                if (BookingFormIsJsDefine.PAY_SUCCESS.getValue().equals(bookingForm.getIsJs().toString())) {
                    detailBean.setPayFlag(true);
                } else {
                    detailBean.setPayFlag(false);
                    //设置O单对账单号
                    try {
                        ValidBillMst validBillMst = queryCalcManagerWebService.queryExpressTransportPayMoney(bookingForm.getBusiBookNo(), null, "82098f5ab036412d9042837036b02c72", sysGFCode, bookingForm.getPayUser());
                        if (validBillMst != null && validBillMst.getDocNo() != null) {
                            detailBean.setValidBillnoForOut(validBillMst.getDocNo());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (bookingForm.getIsJs() != null && (BookingFormIsJsDefine.CALC_SUCCESS.getValue().equals(bookingForm.getIsJs().toString())
                        || BookingFormIsJsDefine.BALANCE_SUCCESS.getValue().equals(bookingForm.getIsJs().toString())
                        || BookingFormIsJsDefine.PAY_SUCCESS.getValue().equals(bookingForm.getIsJs().toString()))) {
                    detailBean.setJsFlag(true);
                } else {
                    detailBean.setJsFlag(false);
                }
                detailBean.setDestProvideForOut(bookingForm.getCarriageDelivProvince());
                detailBean.setDestCityForOut(bookingForm.getCarriageDelivCity());
                detailBean.setDestCountyForOut(bookingForm.getCarriageDelivCounty());

                String destAreaForOut = "";
                if (!StringUtil.isEmpty(detailBean.getDestProvideForOut())
                        && comProvinceMap.get(detailBean.getDestProvideForOut()) != null) {
                    // 判断地址信息是否包含省
                    destAreaForOut += comProvinceMap.get(detailBean.getDestProvideForOut()).getProvinceName();
                    if (!StringUtil.isEmpty(detailBean.getDestCityForOut())
                            && comCityMap.get(detailBean.getDestCityForOut()) != null) {
                        destAreaForOut += comCityMap.get(detailBean.getDestCityForOut()).getName();
                    }
                    if (!StringUtil.isEmpty(detailBean.getDestCountyForOut())
                            && comCountyMap.get(detailBean.getDestCountyForOut()) != null) {
                        destAreaForOut += comCountyMap.get(detailBean.getDestCountyForOut()).getAreaName();
                    }
                }
                detailBean.setDestAreaForOut(destAreaForOut);
                detailBean.setDestAddressForOut(bookingForm.getCarriageDelivAddr());
                detailBean.setDestLinkManForOut(bookingForm.getCneeCustLinkMan());
                detailBean.setDestUserForOut(bookingForm.getReceiverUser());
                detailBean.setDestTelForOut(bookingForm.getCneeCustLinkTel());
                detailBean.setDestLongitudeForOut(bookingForm.getCarriageDelivLongitude());
                detailBean.setDestLatitudeForOut(bookingForm.getCarriageDelivLatitude());
            }
            //如果是同城运输单，需要车辆信息
            String description = "";
            String weightUtil = "";
            String qtyUtil = "";
            BigDecimal tempWeight = new BigDecimal(0);
            BigDecimal tempQty = new BigDecimal(0);
            if (!StringUtil.isEmpty(detailBean.getScheducarno())) {
                //派车单 M-W
                if (detailBean.getGoodsInfoList() != null && detailBean.getGoodsInfoList().size() > 0) {
                    for (MobileGoodsInfo goodsInfo : detailBean.getGoodsInfoList()) {
                        if (goodsInfo.getGoodsQtyUnit() != null) {
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit())
                                    && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                                qtyUtil = comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh();
                            }
                            tempQty = tempQty.add(goodsInfo.getGoodsWeight());
                        }
                        if (goodsInfo.getGoodsWeight() != null) {
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit())
                                    && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                                weightUtil = comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh();
                            }
                            tempWeight = tempWeight.add(goodsInfo.getGoodsWeight());
                        }
                    }
                    if (tempQty.intValue() == 0) {
                        description += tempWeight.setScale(2, BigDecimal.ROUND_HALF_UP) + weightUtil + " ";
                    } else {
                        description += tempQty.setScale(2, BigDecimal.ROUND_HALF_UP) + qtyUtil + "," +
                                tempWeight.setScale(2, BigDecimal.ROUND_HALF_UP) + weightUtil + " ";
                    }
                }

                //增值服务
                List<MobileValueAdd> mobileValueAddList = mobileValueAddDaoEx.queryMobileValueAddListByMoblieOrderId(detailBean.getOrderId());
                if (mobileValueAddList != null && mobileValueAddList.size() > 0) {
                    for (MobileValueAdd mobileValueAdd : mobileValueAddList) {
                        description += mobileValueAdd.getValueAddName() + " ";
                    }
                }
                detailBean.setDescription(description);
                detailBean.setRealNameFlag(true);
            }

            MobileFleetBidding mobileFleetBidding = mobileFleetBiddingDaoEx.queryFleetBidding(detailBean.getBusiBookNo(), detailBean.getScheducarno());
            if (mobileFleetBidding != null) {
                //司机信息 司机姓名、电话、描述
                detailBean.setDriverName(mobileFleetBidding.getDriverName());
                detailBean.setDriverTel(mobileFleetBidding.getDriverTelephone());

                //车辆信息 车牌号、车型、车长、载重、载货体积
                if (mobileFleetBidding.getTruckId() != null) {
                    CarInfo carInfo = new CarInfo();
                    carInfo.setTruckId(mobileFleetBidding.getTruckId());//车辆ID
                    carInfo.setTruckCode(mobileFleetBidding.getTruckCode());//车牌号码
                    ComVehicleInfo comVehicleInfo = comVehicleInfoDao.selectByPrimaryKey(mobileFleetBidding.getTruckId());
                    if (comVehicleInfo != null) {
                        carInfo.setTruckType(comVehicleInfo.getTrucktype());
                        Map<Integer, ComVehicleType> comVehicleTypeMap = comVehicleTypeService.queryForMap();
                        if (comVehicleTypeMap != null && comVehicleTypeMap.get(Integer.parseInt(comVehicleInfo.getTrucktype())) != null) {
                            carInfo.setTruckTypeName(comVehicleTypeMap.get(Integer.parseInt(comVehicleInfo.getTrucktype())).getCarTypeName());
                        }
                        carInfo.setTruckLength(comVehicleInfo.getLength());//车长
                        carInfo.setTruckWidth(comVehicleInfo.getWidth());//车宽
                        carInfo.setTruckHeight(comVehicleInfo.getHeight());//车高
                        carInfo.setTruckWeight(comVehicleInfo.getMaxWeight());//载重
                        carInfo.setBoxVolume(comVehicleInfo.getCargovolume());//载货体积
                    }
                    detailBean.setCarInfo(carInfo);
                }

                //竞价信息 车队名称、报价时间、提货时间、送货时间
                detailBean.setFleetName(mobileFleetBidding.getFleetName());
                detailBean.setQuoteTime(mobileFleetBidding.getCreateDate());
                detailBean.setPickTime(mobileFleetBidding.getPickTime());
                detailBean.setDeliveryTime(mobileFleetBidding.getDeliveryTime());
                detailBean.setTaxRate(mobileFleetBidding.getTaxRate());//税率
                detailBean.setBidValue(mobileFleetBidding.getBidValue());//车队报价
                detailBean.setBidValueCurr(mobileFleetBidding.getBidCurr());
            }

            //设置发货人、收货人账号信息
            if (!StringUtil.isEmpty(detailBean.getStartTel())) {
                detailBean.setStartUser(mobileUserOrderService.queryAccountByTelephone(detailBean.getStartTel()));
            }
            if (!StringUtil.isEmpty(detailBean.getDestTel())) {
                detailBean.setDestUser(mobileUserOrderService.queryAccountByTelephone(detailBean.getDestTel()));
            }
            if (MobileStationDefine.M.equals(detailBean.getStartLocus()) && MobileStationDefine.POD.equals(detailBean.getDestnLocus())) {
                MobileOrderOperate mobileOrderOperate = mobileOrderOperateDaoEx.getMobileOrderOperate(detailBean.getBusiBookNo(), 3);
                if (mobileOrderOperate != null) {
                    MobileOperateInfo mobileOperateInfo = new MobileOperateInfo();
                    BeanUtils.copyProperties(mobileOrderOperate, mobileOperateInfo);
                    detailBean.setMobileOperateInfo(mobileOperateInfo);
                }
            }
            if (!MobileStationDefine.POD.equals(detailBean.getDestnLocus()) && !MobileStationDefine.M.equals(detailBean.getDestnLocus())) {
                //目的地是蛙站
                ComCustomer comCustomer = comCustomerDaoEx.getComCustomerByCustTtl(detailBean.getDestnLocus());
                if (comCustomer != null) {
                    detailBean.setDestnLocusName(comCustomer.getCustName());
                }
            }
            baseResBean.setData(detailBean);
        }
        return baseResBean;
    }


    /**
     * 订单管理-订单详细(用户单、商户单)---司机订单详情
     *
     * @param mobileOrderDetailReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public MobileOrderDetailResult queryDriverOrderDetail(MobileOrderDetailReq mobileOrderDetailReq) throws MobileStationBizException {
        MobileOrderDetailResult baseResBean = new MobileOrderDetailResult(mobileOrderDetailReq);
        MobileOrderDetailBean detailBean = null;
        if (null != mobileOrderDetailReq.getOrderId()) {
            detailBean = mobileOrderDao.queryOrderDetail(mobileOrderDetailReq.getOrderId());
        }
        if (detailBean == null) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("该订单不存在！");
        } else {
            List<MobileSubOrder> subOrderList = detailBean.getSubOrderList();
            List<String> subOrderNoList = new ArrayList<>();
            if (!StringUtil.isEmpty(detailBean.getScheducarno())) {
                // 设置子订单
                if (!detailBean.getScheducarno().startsWith("M_")) {
                    // 设置散件和集包列表
                    subOrderNoList = mobileMyOrderDao.selectMobileSubOrderNoList(detailBean.getScheducarno());
                } else {
                    for (MobileSubOrder subOrder : subOrderList) {
                        subOrderNoList.add(subOrder.getSubBusiBookNo());
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

            // 设置币值
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
                // 判断地址信息是否包含省
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
            detailBean.setMiAssignFlag(false);
            if (detailBean.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS
                    && detailBean.getBidBy() == null) {
                detailBean.setMiAssignFlag(true);
            }
            //如果是同城运输单，需要车辆信息
            String description = "";
            String weightUtil = "";
            String qtyUtil = "";
            BigDecimal tempWeight = new BigDecimal(0);
            BigDecimal tempQty = new BigDecimal(0);
            if (StringUtil.isEmpty(detailBean.getScheducarno())) {
                Map<String, ComGoodsType> comGoodsTypeMap = comGoodsTypeService.queryForMap();
                //订单 同城运输订单、同城专送订单
                //货物类型+总件数+总重量+增值服务
                if (detailBean.getGoodsInfoList() != null && detailBean.getGoodsInfoList().size() > 0) {
                    MobileGoodsInfo goodsInfo = detailBean.getGoodsInfoList().get(0);
                    //司机的列表描述信息  货物类型+货物总件数+货物总量+增值服务
                    if (goodsInfo.getGoodsType() != null) {
                        String typeName = goodsInfo.getGoodsType();
                        if (comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                            typeName = comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh();
                        }
                        description += typeName + ",";
                    }
                    if (goodsInfo.getGoodsQtyUnit() != null && goodsInfo.getGoodsQty() != null && goodsInfo.getGoodsQty().intValue() != 0) {
                        if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit())
                                && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                            qtyUtil = comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh();
                        }
                        description += " " + goodsInfo.getGoodsQty().setScale(0, BigDecimal.ROUND_HALF_UP)
                                + qtyUtil + ",";
                    }
                    if (goodsInfo.getGoodsWeight() != null) {
                        if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit())
                                && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                            weightUtil = comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh();
                        }
                        description += goodsInfo.getGoodsWeight().setScale(2, BigDecimal.ROUND_HALF_UP)
                                + weightUtil + ",";
                    }
                    ComAccount createAccount = comAccountDao.selectByPrimaryKey(detailBean.getCreateUserId());
                    if (StringUtils.isNotEmpty(createAccount.getRealName())) {
                        detailBean.setRealNameFlag(true);
                    } else {
                        detailBean.setRealNameFlag(false);
                    }
                }
                //增值服务
                List<MobileValueAdd> mobileValueAddList = mobileValueAddDaoEx.queryMobileValueAddList(detailBean.getBookingFormId());
                if (mobileValueAddList != null && mobileValueAddList.size() > 0) {
                    for (MobileValueAdd mobileValueAdd : mobileValueAddList) {
                        description += mobileValueAdd.getValueAddName() + " ";
                    }
                }
                detailBean.setDescription(description);
            } else {
                //派车单 M-W、W-W、W-M
                if (detailBean.getGoodsInfoList() != null && detailBean.getGoodsInfoList().size() > 0) {
                    for (MobileGoodsInfo goodsInfo : detailBean.getGoodsInfoList()) {
                        if (goodsInfo.getGoodsQtyUnit() != null) {
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit())
                                    && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                                qtyUtil = comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh();
                            }
                            tempQty = tempQty.add(goodsInfo.getGoodsWeight());
                        }
                        if (goodsInfo.getGoodsWeight() != null) {
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit())
                                    && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                                weightUtil = comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh();
                            }
                            tempWeight = tempWeight.add(goodsInfo.getGoodsWeight());
                        }
                    }
                    description += tempQty.setScale(2, BigDecimal.ROUND_HALF_UP) + qtyUtil + "," +
                            tempWeight.setScale(2, BigDecimal.ROUND_HALF_UP) + weightUtil + " ";
                }

                //增值服务
                List<MobileValueAdd> mobileValueAddList = mobileValueAddDaoEx.queryMobileValueAddListByMoblieOrderId(detailBean.getOrderId());
                if (mobileValueAddList != null && mobileValueAddList.size() > 0) {
                    for (MobileValueAdd mobileValueAdd : mobileValueAddList) {
                        description += mobileValueAdd.getValueAddName() + " ";
                    }
                }
                detailBean.setDescription(description);
                detailBean.setRealNameFlag(true);
                //支付人为蛙站或咪站
                ComAccount comAccount;
                ComCustomer comCustomer = null;
                if (detailBean.getCreateCompanyId() != null) {
                    comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(detailBean.getCreateCompanyId());
                    comAccount = comAccountDao.selectByPrimaryKey(detailBean.getCreateCompanyId());
                } else {
                    comAccount = comAccountDao.selectByPrimaryKey(detailBean.getCreateUserId());
                }
                if (comAccount != null) {
                    detailBean.setPayUser(comAccount.getAcctUsername());
                    detailBean.setPayUserRealName(comAccount.getRealName());
                    detailBean.setPayUserTelephone(comAccount.getTelephone());
                }
                if (comCustomer != null) {
                    detailBean.setPayUserTelephone(comCustomer.getFmobile());
                }
            }

            MobileFleetBidding mobileFleetBidding = mobileFleetBiddingDaoEx.queryFleetBidding(detailBean.getBusiBookNo(), detailBean.getScheducarno());
            if (mobileFleetBidding != null) {
                //司机信息 司机姓名、电话、描述
                detailBean.setDriverName(mobileFleetBidding.getDriverName());
                detailBean.setDriverTel(mobileFleetBidding.getDriverTelephone());

                //车辆信息 车牌号、车型、车长、载重、载货体积

                if (mobileFleetBidding.getTruckId() != null) {
                    CarInfo carInfo = new CarInfo();
                    carInfo.setTruckId(mobileFleetBidding.getTruckId());//车辆ID
                    carInfo.setTruckCode(mobileFleetBidding.getTruckCode());//车牌号码
                    ComVehicleInfo comVehicleInfo = comVehicleInfoDao.selectByPrimaryKey(mobileFleetBidding.getTruckId());
                    if (comVehicleInfo != null) {
                        carInfo.setTruckType(comVehicleInfo.getTrucktype());
                        Map<Integer, ComVehicleType> comVehicleTypeMap = comVehicleTypeService.queryForMap();
                        if (comVehicleTypeMap != null && comVehicleTypeMap.get(Integer.parseInt(comVehicleInfo.getTrucktype())) != null) {
                            carInfo.setTruckTypeName(comVehicleTypeMap.get(Integer.parseInt(comVehicleInfo.getTrucktype())).getCarTypeName());
                        }
                        carInfo.setTruckLength(comVehicleInfo.getLength());//车长
                        carInfo.setTruckWidth(comVehicleInfo.getWidth());//车宽
                        carInfo.setTruckHeight(comVehicleInfo.getHeight());//车高
                        carInfo.setTruckWeight(comVehicleInfo.getMaxWeight());//载重
                        carInfo.setBoxVolume(comVehicleInfo.getCargovolume());//载货体积
                    }
                    detailBean.setCarInfo(carInfo);
                }
                //竞价信息 车队名称、报价时间、提货时间、送货时间
                detailBean.setFleetName(mobileFleetBidding.getFleetName());
                detailBean.setQuoteTime(mobileFleetBidding.getCreateDate());
                detailBean.setPickTime(mobileFleetBidding.getPickTime());
                detailBean.setDeliveryTime(mobileFleetBidding.getDeliveryTime());
                /*if (mobileFleetBidding.getTaxRate() != null) {
                    detailBean.setBidValue(mobileFleetBidding.getBidValue().add(mobileFleetBidding.getBidValue().multiply(mobileFleetBidding.getTaxRate())).setScale(2, BigDecimal.ROUND_HALF_UP));
                } else {
                    detailBean.setBidValue(mobileFleetBidding.getBidValue());
                }*/
                detailBean.setBidValue(mobileFleetBidding.getBidValue());
                detailBean.setTaxRate(mobileFleetBidding.getTaxRate());
                detailBean.setBidValueCurr(mobileFleetBidding.getBidCurr());
            }

            //设置货物信息
            for (MobileGoodsInfo goodsInfo : detailBean.getGoodsInfoList()) {
                //货物类型名称
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
                for (MobileSubOrder subOrder : subOrderList) {
                    if (goodsInfo.getMobileScheduOrderId() != null && goodsInfo.getMobileScheduOrderId().equals(subOrder.getSubId())) {
                        goodsInfo.setMobileScheduOrderNo(subOrder.getSubBusiBookNo());
                        break;
                    }
                }
            }

            BookingForm bookingForm = null;
            if (StringUtil.isEmpty(detailBean.getScheducarno())) {
                bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(detailBean.getBusiBookNo());
                if (bookingForm.getIsJs() != null && (BookingFormIsJsDefine.CALC_SUCCESS.getValue().equals(bookingForm.getIsJs().toString())
                        || BookingFormIsJsDefine.BALANCE_SUCCESS.getValue().equals(bookingForm.getIsJs().toString())
                        || BookingFormIsJsDefine.PAY_SUCCESS.getValue().equals(bookingForm.getIsJs().toString()))) {
                    detailBean.setJsFlag(true);
                } else {
                    detailBean.setJsFlag(false);
                }
                if (BookingFormIsJsDefine.PAY_SUCCESS.getValue().equals(bookingForm.getIsJs().toString())) {
                    detailBean.setPayFlag(true);
                } else {
                    detailBean.setPayFlag(false);
                    //设置O单对账单号
                    try {
                        ValidBillMst validBillMst = queryCalcManagerWebService.queryExpressTransportPayMoney(bookingForm.getBusiBookNo(), null, "82098f5ab036412d9042837036b02c72", sysGFCode, bookingForm.getPayUser());
                        if (validBillMst != null && validBillMst.getDocNo() != null) {
                            detailBean.setValidBillnoForOut(validBillMst.getDocNo());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (subOrderList.size() > 0) {
                    bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrderList.get(0).getSubBusiBookNo());
                }
                if (detailBean.getIsJs() != null && detailBean.getIsJs() == 1) {
                    detailBean.setJsFlag(true);
                    if (!StringUtil.isEmpty(detailBean.getValidBillno())) {
                        BidingInfoResultBean resultBean = calcWebService.queryBillStatus(detailBean.getValidBillno());
                        logger.debug("司机根据对账单号查询支付状态 queryBillStatus{}" + JSON.toJSONString(resultBean));
                        if (resultBean != null && resultBean.getResultCode() == 2 && resultBean.getStatus() != 0) {
                            detailBean.setPayFlag(true);
                        } else {
                            detailBean.setPayFlag(false);
                        }
                    }
                } else {
                    detailBean.setJsFlag(false);
                }
            }

            if (null != bookingForm) {
                detailBean.setBookingUser(bookingForm.getCreateUser());
                detailBean.setAccesstime(bookingForm.getAccesstime());
                detailBean.setPlatQuoteNo(bookingForm.getDocno());
                detailBean.setCreateUserTel(bookingForm.getShipCustlinkTel());
                detailBean.setDestCustLinkMan(bookingForm.getCneeCustLinkMan());
                detailBean.setDestCustLinkTel(bookingForm.getCneeCustLinkTel());
                detailBean.setDestProvideForOut(bookingForm.getCarriageDelivProvince());
                detailBean.setDestCityForOut(bookingForm.getCarriageDelivCity());
                detailBean.setDestCountyForOut(bookingForm.getCarriageDelivCounty());

                String destAreaForOut = "";
                if (!StringUtil.isEmpty(detailBean.getDestProvideForOut())
                        && comProvinceMap.get(detailBean.getDestProvideForOut()) != null) {
                    // 判断地址信息是否包含省
                    destAreaForOut += comProvinceMap.get(detailBean.getDestProvideForOut()).getProvinceName();
                    if (!StringUtil.isEmpty(detailBean.getDestCityForOut())
                            && comCityMap.get(detailBean.getDestCityForOut()) != null) {
                        destAreaForOut += comCityMap.get(detailBean.getDestCityForOut()).getName();
                    }
                    if (!StringUtil.isEmpty(detailBean.getDestCountyForOut())
                            && comCountyMap.get(detailBean.getDestCountyForOut()) != null) {
                        destAreaForOut += comCountyMap.get(detailBean.getDestCountyForOut()).getAreaName();
                    }
                }
                detailBean.setDestAreaForOut(destAreaForOut);
                detailBean.setDestAddressForOut(bookingForm.getCarriageDelivAddr());
                detailBean.setDestLinkManForOut(bookingForm.getCneeCustLinkMan());
                detailBean.setDestUserForOut(bookingForm.getReceiverUser());
                detailBean.setDestTelForOut(bookingForm.getCneeCustLinkTel());
                detailBean.setDestLongitudeForOut(bookingForm.getCarriageDelivLongitude());
                detailBean.setDestLatitudeForOut(bookingForm.getCarriageDelivLatitude());
            }
            //设置发货人、收货人账号信息
            if (!StringUtil.isEmpty(detailBean.getStartTel())) {
                detailBean.setStartUser(mobileUserOrderService.queryAccountByTelephone(detailBean.getStartTel()));
            }
            if (!StringUtil.isEmpty(detailBean.getDestTel())) {
                detailBean.setDestUser(mobileUserOrderService.queryAccountByTelephone(detailBean.getDestTel()));
            }

            if (!MobileStationDefine.POD.equals(detailBean.getDestnLocus()) && !MobileStationDefine.M.equals(detailBean.getDestnLocus())) {
                //目的地是蛙站
                ComCustomer comCustomer = comCustomerDaoEx.getComCustomerByCustTtl(detailBean.getDestnLocus());
                if (comCustomer != null) {
                    detailBean.setDestnLocusName(comCustomer.getCustName());
                }
            }
            //设置订单类型 1用户订单、2商户订单
            if (!StringUtil.isEmpty(detailBean.getProductType())) {
                if (detailBean.getProductType().startsWith("O")) {
                    //用户单
                    detailBean.setOrderStyle(1);
                } else {
                    //商户单
                    detailBean.setOrderStyle(2);
                }
            }
            baseResBean.setData(detailBean);
        }
        return baseResBean;
    }

    /**
     * MS批量发车
     *
     * @param batchOperateReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public BatchOperateResult batchDepart(BatchOperateReq batchOperateReq) throws MobileStationBizException {
        BatchOperateResult batchOperateResult = new BatchOperateResult(batchOperateReq);
        List<OperateResBean> departResBeanList = new ArrayList<>();
        Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
        Map<String, ComCity> comCityMap = comCityService.queryForMap();
        Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();

        for (OperateReqBean departReqBean : batchOperateReq.getDepatReqBeanList()) {

            OperateResBean departResBean = new OperateResBean();
            departResBean.setOrderId(departReqBean.getOrderId());
            departResBean.setBusiBookNo(departReqBean.getBusiBookNo());
            // 获取订单状态，如果是取件成功，可以发车，否则不允许发车
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(departReqBean.getOrderId());
            if (mobileBookingForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
                departResBean.setRetCode(SystemDefine.FAILURE);
                departResBean.setRetMsg("取件成功后，才可以发车！");
                departResBeanList.add(departResBean);
                continue;
            }
            //如果是咪站的单，递送员、司机发车时需要判断咪站的出库状态
            if ((batchOperateReq.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()
                    || batchOperateReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue())
                    && mobileBookingForm.getStartLocus().equals(MobileStationDefine.M)) {
                //获取咪站订单状态 M-POD，M-W两种情况
                MobileBookingForm miOrderForm = new MobileBookingForm();
                miOrderForm.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                miOrderForm.setRoleId(SysAccountRole.OPERATOR_MSTATION.getValue());
                miOrderForm.setStartLocus(mobileBookingForm.getStartLocus());
                miOrderForm.setDestnLocus(mobileBookingForm.getDestnLocus());
                miOrderForm = mobileBookingFormDaoEx.getMobileBookingFormByConditions(miOrderForm);
                if (miOrderForm == null || miOrderForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                    departResBean.setRetCode(SystemDefine.FAILURE);
                    departResBean.setRetMsg("咪站分拣出库后，才可以发车！");
                    departResBeanList.add(departResBean);
                    continue;
                }
            }


            //记录日志
            WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
            GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
            List<String> allBusNoList = new ArrayList<>();

            //判断是派车单还是订单
            List<MobileScheduSubOrder> subOrderList = new ArrayList<>();
            if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                //取件发车，判断核保
//                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
//                if (bookingForm != null) {
//                    int premiumStatus = (bookingForm == null || bookingForm.getPremiumStatus() == null) ? -1 : bookingForm.getPremiumStatus().intValue();
//                    if (bookingForm != null && (bookingForm.getNeedInsure() != null && bookingForm.getNeedInsure()) && CustomerDefine.HAVE_PAY != premiumStatus) {
//                        if (CustomerDefine.NEED_PAY == premiumStatus) {
//                            departResBean.setRetCode(SystemDefine.FAILURE);
//                            departResBean.setRetMsg("该订单尚未支付保费，无法发车，请先与用户联系！");
//                            departResBeanList.add(departResBean);
//                            continue;
//                        }
//                        if (bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
//                            departResBean.setRetCode(SystemDefine.FAILURE);
//                            departResBean.setRetMsg("该订单核保失败已被冻结，无法发车，请先与用户联系！");
//                            departResBeanList.add(departResBean);
//                            continue;
//                        }
//                    }
//                }
                //订单设置订单号
                allBusNoList.add(mobileBookingForm.getBusiBookNo());
            } else {
                subOrderList = mobileMyOrderDao.selectMobileSubOrderByMobileId(mobileBookingForm.getId());
                for (MobileScheduSubOrder subOrder : subOrderList) {
                    allBusNoList.add(subOrder.getBusiBookNo());
                    BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrder.getBusiBookNo());
                    if (bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
                        departResBean.setRetCode(SystemDefine.FAILURE);
                        departResBean.setRetMsg("该订单核保失败已被冻结，请与用户确认");
                        departResBeanList.add(departResBean);
                        continue;
                    }
                }
            }

            //判断是否是派车单，如果是派车单，查询出子订单列表，并设置GPS操作日志参数
            if (!MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus())) {
                //记录GPS操作日志,只有记录非POP的日志需要这些内容
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

                giOrderTraceResynced.setAllBusiNo(allBusNoList);
                giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
                giOrderTraceResynced.setUserCode(batchOperateReq.getLoginAcctUserName());
                giOrderTraceResynced.setLoginCode(batchOperateReq.getAcctUsername());

                if (batchOperateReq.getRoleId() != null) {
                    giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(batchOperateReq.getRoleId()));
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
                            logger.debug("发车获取移动蛙地址  {}", result);
                            GiPositionUser giPositionUser = JSON.parseObject(result, GiPositionUser.class);
                            if (giPositionUser != null) {
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
                                departResBean.setRetCode(SystemDefine.FAILURE);
                                departResBean.setRetMsg("获取移动蛙站位置失败，不能发车！");
                                departResBeanList.add(departResBean);
                                continue;
                            }
                        } catch (Exception e) {
                            departResBean.setRetCode(SystemDefine.FAILURE);
                            departResBean.setRetMsg("获取移动蛙站位置失败，不能发车！");
                            departResBeanList.add(departResBean);
                            continue;
                        }
                    }
                }
            }
            if (!StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                // 运输单，调用修改状态接口
                // 调用hub接口
                DepartSchudeCarOrderRequest departSchudeCarOrderRequest = new DepartSchudeCarOrderRequest();
                departSchudeCarOrderRequest.setSchudeCarNo(departReqBean.getScheducarno());
                departSchudeCarOrderRequest.setSchudeCarType(batchOperateReq.getTransportType());
                departSchudeCarOrderRequest.setAccountId(batchOperateReq.getAccountId());
                departSchudeCarOrderRequest.setStationCode(mobileBookingForm.getDestnLocus());
                if (batchOperateReq.getAppLoginInfo() != null && batchOperateReq.getAppLoginInfo().getComAccount() != null) {
                    departSchudeCarOrderRequest.setDeliveryName(batchOperateReq.getAppLoginInfo().getComAccount().getRealName());
                    departSchudeCarOrderRequest.setDeliveryTel(batchOperateReq.getAppLoginInfo().getComAccount().getTelephone());
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
                    if (expreessRes.getRetCode() != 0) {
                        logger.info("发车返回结果{}", expreessRes.getRetMsg());
                        departResBean.setRetCode(SystemDefine.FAILURE);
                        departResBean.setRetMsg(expreessRes.getRetMsg());
                        departResBeanList.add(departResBean);
                        continue;
                    }
                } else {
                    logger.info("HUB发车返回NULL");
                    departResBean.setRetCode(SystemDefine.FAILURE);
                    departResBean.setRetMsg("蛙站返回发车失败");
                    departResBeanList.add(departResBean);
                    continue;
                }
            }
            // 发车成功
            if (MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus())
                    && MobileStationDefine.M.equals(mobileBookingForm.getDestnLocus())) {
                //如果是POP-M的订单发车，修改咪站的M-POD的状态为已接单
                mobileMyOrderDao.updateMiOrderStatus(mobileBookingForm.getBusiBookNo());
            }
            // 修改订单当前状态
            MobileOrderOperateBean orderOperateBean = new MobileOrderOperateBean(batchOperateReq.getAccountId(),
                    departReqBean.getOrderId(),
                    batchOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS,
                    MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN);
            int flag = mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
            if (flag < 1) {
                departResBean.setRetCode(SystemDefine.FAILURE);
                departResBean.setRetMsg("发车失败！");
                departResBeanList.add(departResBean);
                continue;
            } else {
                // 设置派车单号
                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                    waybillTraceOperateBean.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                } else {
                    waybillTraceOperateBean.setScheducarno(mobileBookingForm.getScheducarno());
                }
                // 插入流程跟踪日志
                waybillTraceOperateBean.setAcctUsername(batchOperateReq.getAcctUsername());
                ComAccount account = comAccountService.queryAccountByAcctUsername(batchOperateReq.getAcctUsername());

                if (mobileBookingForm != null) {
                    waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
                    waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
                }
                waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
                if (batchOperateReq.getAppLoginInfo() != null && batchOperateReq.getAppLoginInfo().getComAccount() != null) {
                    waybillTraceOperateBean.setRealName(batchOperateReq.getAppLoginInfo().getComAccount().getRealName());
                }
                if (account != null && batchOperateReq.getRoleId() != null) {
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
                            waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_DEPART_HUB.getName()
                                    + mobileBookingForm.getDestnLocus());
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

                if (batchOperateReq.getRoleId() != null) {
                    waybillTraceOperateBean.setRoleId(batchOperateReq.getRoleId());
                }
                waybillTraceOperateBean.setDispatchId(departReqBean.getOrderId());
                insertWaybillTrace(waybillTraceOperateBean);
            }
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
            departResBeanList.add(departResBean);
        }
        batchOperateResult.setData(departResBeanList);
        return batchOperateResult;
    }

    /**
     * 批量送达确认
     *
     * @param batchOperateReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public BatchOperateResult batchDeliveryConfirm(BatchOperateReq batchOperateReq) throws MobileStationBizException {
        BatchOperateResult batchOperateResult = new BatchOperateResult(batchOperateReq);
        List<OperateResBean> operateResBeanList = new ArrayList<>();
        List<WaybillTraceOperateBean> waybillTraceOperateBeanList = new ArrayList<>();
        Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
        Map<String, ComCity> comCityMap = comCityService.queryForMap();
        Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();

        for (OperateReqBean operateReqBean : batchOperateReq.getDepatReqBeanList()) {
            OperateResBean operateResBean = new OperateResBean();
            operateResBean.setOrderId(operateReqBean.getOrderId());
            operateResBean.setBusiBookNo(operateReqBean.getBusiBookNo());

            WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
            GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();//记录GPS操作日志
            List<String> allBusNoList = new ArrayList<>();

            // 获取订单信息
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(operateReqBean.getOrderId());
            if (mobileBookingForm != null) {
                if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                    operateResBeanList.add(operateResBean);
                    continue;
                } else {
                    if (!mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)) {
                        if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.M)) {
                            operateResBean.setRetCode(SystemDefine.FAILURE);
                            operateResBean.setRetMsg("等待咪站收货！");
                            operateResBeanList.add(operateResBean);
                            continue;
                        } else {
                            //M-W没有自动送达
                            if (!mobileBookingForm.getStartLocus().equals(MobileStationDefine.M)) {
                                operateResBean.setRetCode(SystemDefine.FAILURE);
                                operateResBean.setRetMsg("等待蛙站收货！");
                                operateResBeanList.add(operateResBean);
                                continue;
                            }
                        }
                    }
                }
            }
            List<MobileScheduSubOrder> subOrderList = new ArrayList<>();
            // 目的地为M、目的地为蛙站
            if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)) {
                operateResBean.setRetCode(SystemDefine.FAILURE);
                operateResBean.setRetMsg("目的地为POD不允许批量送达！");
                operateResBeanList.add(operateResBean);
                continue;
            } else if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.M)) {
                // 目的地为咪站，需要先在咪站收货 POP-M M-POD;  W-M M-POD
                //1、订单号相同，2接单人为咪站，3订单起始为咪站
                MobileAssignBean mobileAssignBean = new MobileAssignBean();
                //如果目的地是咪站，分两种情况，1是POP-M；2是W-M
                if (operateReqBean.getScheducarno() != null) {
                    //派车单，需要判断所有子订单都已经收货 W-M M-POD
                    subOrderList = mobileMyOrderDao.selectMobileSubOrderByMobileId(operateReqBean.getOrderId());
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
                                        operateResBean.setRetCode(SystemDefine.FAILURE);
                                        operateResBean.setRetMsg("咪站没有确认收货，不能送达确认！");
                                        operateResBeanList.add(operateResBean);
                                        break;
                                    }
                                }
                            }
                            if (operateResBean.getRetCode() == SystemDefine.FAILURE) {
                                break;
                            }
                        }
                    }
                    if (operateResBean.getRetCode() == SystemDefine.FAILURE) {
                        continue;
                    }
                    if (subOrderList != null && subOrderList.size() > 0) {
                        MobileBookingForm order = mobileMyOrderDao.queryMiOrderByBusiBookNo(subOrderList.get(0).getBusiBookNo());
                        giOrderTraceResynced.setUserCodeTo(order.getRevUser());
                    }
                } else {
                    //订单 POP-M
                    mobileAssignBean.setBusiBookNo(operateReqBean.getBusiBookNo());
                    allBusNoList.add(mobileBookingForm.getBusiBookNo());
                    if (mobileBookingForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST
                            || mobileBookingForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL) {
                        //MS POP-POD 指派给M  生成POP-M、M-POD  POP-M送达时，需要判断M-POD的入库
                        mobileAssignBean.setCreateUser(mobileBookingForm.getRevUser());
                    } else {
                        //POP-M M指派给MS  POP-M的CreateUser等于M-POD的RevUser
                        mobileAssignBean.setRevUser(mobileBookingForm.getCreateUser());
                    }
                    mobileAssignBean.setRoleId(SysAccountRole.OPERATOR_MSTATION.getValue());
                    List<MobileBookingForm> forms = mobileMyOrderDao.selectMobileOrderList(mobileAssignBean);
                    if (forms.size() > 0) {
                        for (MobileBookingForm form : forms) {
                            if (form.getStartLocus().equals(MobileStationDefine.M)
                                    && form.getOrderFrom() != MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU
                                    && form.getOrderFrom() != MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST) {
                                if (mobileBookingForm.getCreateUser().equals(mobileAssignBean.getCreateUser())) {
                                    giOrderTraceResynced.setUserCodeTo(form.getRevUser());
                                } else {
                                    giOrderTraceResynced.setUserCodeTo(mobileBookingForm.getCreateUser());
                                }
                                if (form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                                        && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN
                                        && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING
                                        && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE
                                        && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_DONE
                                        && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                                    operateResBean.setRetCode(SystemDefine.FAILURE);
                                    operateResBean.setRetMsg("咪站没有确认收货，不能送达确认！");
                                    operateResBeanList.add(operateResBean);
                                    break;
                                }
                            }
                        }
                        if (operateResBean.getRetCode() == SystemDefine.FAILURE) {
                            continue;
                        }
                    }
                }
            } else {
                // 调用hub接口 pop-w、m-w、w-w
                SuccDeliverSchudeCarOrderRequest succDeliverSchudeCarOrderRequest = new SuccDeliverSchudeCarOrderRequest();
                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                    allBusNoList.add(mobileBookingForm.getBusiBookNo());
                    succDeliverSchudeCarOrderRequest.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                } else {
                    subOrderList = mobileMyOrderDao.selectMobileSubOrderByMobileId(operateReqBean.getOrderId());
                    for (MobileScheduSubOrder subOrder : subOrderList) {
                        allBusNoList.add(subOrder.getBusiBookNo());
                    }
                    succDeliverSchudeCarOrderRequest.setSchudeCarNo(mobileBookingForm.getScheducarno());
                }
                succDeliverSchudeCarOrderRequest.setStaionCode(mobileBookingForm.getDestnLocus());
                succDeliverSchudeCarOrderRequest.setDeliverAccount(mobileBookingForm.getRevUser());
                succDeliverSchudeCarOrderRequest.setSchudeCarType(batchOperateReq.getTransportType());
                succDeliverSchudeCarOrderRequest.setTotalLevel(true);
                logger.info("确认送达传参:{}", JSON.toJSONString(succDeliverSchudeCarOrderRequest));
                String expreessResStr = expreessOrderWebService
                        .succDeliverSchudeCarOrder(succDeliverSchudeCarOrderRequest);
                if (!StringUtil.isEmpty(expreessResStr)) {
                    AppBaseResult expreessRes = JSON.parseObject(expreessResStr, AppBaseResult.class);
                    if (expreessRes.getRetCode() != 0) {
                        logger.info("确认送达结果{}", expreessRes.getRetMsg());
                        operateResBean.setRetCode(SystemDefine.FAILURE);
                        operateResBean.setRetMsg(expreessRes.getRetMsg());
                        operateResBeanList.add(operateResBean);
                        continue;
                    } else {
                        String acctUserName = comCustomerDaoEx.getAcctUserNameByCustTtl(mobileBookingForm.getDestnLocus());
                        giOrderTraceResynced.setUserCodeTo(acctUserName);
                    }
                } else {
                    logger.info("HUB返回NULL");
                    operateResBean.setRetCode(SystemDefine.FAILURE);
                    operateResBean.setRetMsg("确认送达失败！");
                    operateResBeanList.add(operateResBean);
                    continue;
                }
            }

            //记录GPS操作日志
            giOrderTraceResynced.setAction(MobileStationDefine.Action_TransportArrival);
            giOrderTraceResynced.setAllBusiNo(allBusNoList);
            giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
            giOrderTraceResynced.setUserCode(batchOperateReq.getLoginAcctUserName());
            giOrderTraceResynced.setLoginCode(batchOperateReq.getAcctUsername());

            if (batchOperateReq.getRoleId() != null) {
                giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(batchOperateReq.getRoleId()));
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

            // 出库
            StockInOutReq stockInOutReq = new StockInOutReq();
            stockInOutReq.setBusiBookNo(operateReqBean.getBusiBookNo());
            stockInOutReq.setStockType(MobileStationDefine.STOCK_OUT);
            stockInOutReq.setAccountId(batchOperateReq.getAccountId());
            stockInOutReq.setOrderFrom(operateReqBean.getOrderFrom());
            stockInOutReq.setAcctUsername(batchOperateReq.getAcctUsername());
            stockInOutReq.setScheducarno(operateReqBean.getScheducarno());
            stockInOutReq.setCompanyAccountId(batchOperateReq.getCompanyAccountId());
            AppBaseResult res = mobileStockService.stockInOut(stockInOutReq);

            if (res.getRetCode() == SystemDefine.FAILURE) {
                // 送达失败
                logger.info(res.getRetMsg());
                operateResBean.setRetCode(SystemDefine.FAILURE);
                operateResBean.setRetMsg("库存存操作失败，送达确认失败！");
                operateResBeanList.add(operateResBean);
                continue;
            }

            // 修改订单状态为已完成
            MobileOrderOperateBean orderOperateBean;
            if (batchOperateReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                orderOperateBean = new MobileOrderOperateBean(batchOperateReq.getAccountId(),
                        operateReqBean.getOrderId(),
                        batchOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS,
                        MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
            } else {
                orderOperateBean = new MobileOrderOperateBean(batchOperateReq.getAccountId(),
                        operateReqBean.getOrderId(),
                        batchOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN,
                        MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
            }

            int flag = mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
            if (flag < 1) {
                operateResBean.setRetCode(SystemDefine.FAILURE);
                if (batchOperateReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                    operateResBean.setRetMsg("订单当前不是已收货状态，不能送达确认！");
                } else {
                    operateResBean.setRetMsg("订单当前不是发车状态，不能送达确认！");
                }
                operateResBeanList.add(operateResBean);
                continue;
            } else {
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
                // 设置派车单号
                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                    waybillTraceOperateBean.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                } else {
                    waybillTraceOperateBean.setScheducarno(mobileBookingForm.getScheducarno());
                }
                // 插入流程跟踪日志
                waybillTraceOperateBean.setAcctUsername(batchOperateReq.getAcctUsername());
                if (mobileBookingForm != null) {
                    waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
                    waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
                }
                waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
                ComAccount account = comAccountService.queryAccountByAcctUsername(batchOperateReq.getAcctUsername());
                if (account != null && batchOperateReq.getRoleId() != null) {
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

                waybillTraceOperateBean.setDispatchId(operateReqBean.getOrderId());
                if (batchOperateReq.getRoleId() != null) {
                    waybillTraceOperateBean.setRoleId(batchOperateReq.getRoleId());
                }
                insertWaybillTrace(waybillTraceOperateBean);
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
            }
            waybillTraceOperateBeanList.add(waybillTraceOperateBean);
            operateResBeanList.add(operateResBean);
        }

        batchOperateResult.setData(operateResBeanList);
        return batchOperateResult;
    }

    /**
     * 上传文件
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public OrderPicUploadResult orderPicUpload(MultipartHttpServletRequest request) throws Exception {
        OrderPicUploadResult orderPicUploadResult = new OrderPicUploadResult();
        List<OrderPicUploadResBean> orderPicUploadResBeanList = new ArrayList<>();
        MultipartFile file = null;
        Map<String, MultipartFile> fileMap = request.getFileMap();
        if (fileMap != null && fileMap.size() > 0) {
            for (String key : fileMap.keySet()) {
                String[] params = key.split("_");
                OrderPicUploadResBean orderPicUploadResBean = new OrderPicUploadResBean();
                orderPicUploadResBean.setFileId(params[2]);
                orderPicUploadResBean.setBusiBookNo(params[0]);
                file = fileMap.get(key);

                logger.info("文件上传fileId：" + key);
                ResultBean resultBean = checkFileInfo(file, key);
                if (!resultBean.isState()) {
                    orderPicUploadResBean.setRetCode(SystemDefine.FAILURE);
                    orderPicUploadResBean.setRetMsg(resultBean.getMessage());
                } else {
                    ExpreessFileUploadRecord expreessFileUploadRecord = new ExpreessFileUploadRecord();
                    expreessFileUploadRecord.setBusiBookNo(params[0]);
                    expreessFileUploadRecord.setUploadPeople(params[1]);
                    expreessFileUploadRecord.setUploadDate(new Date());
                    ExpreessFileUploadRecord record = uploadService.uploadPicFile(file, expreessFileUploadRecord);
                    if (record == null) {
                        orderPicUploadResBean.setRetCode(SystemDefine.FAILURE);
                        orderPicUploadResBean.setRetMsg("文件上传失败");
                    }
                }
                orderPicUploadResBeanList.add(orderPicUploadResBean);
            }
        }
        orderPicUploadResult.setList(orderPicUploadResBeanList);
        return orderPicUploadResult;
    }

    /**
     * 修改订单支付方式
     *
     * @param updatePayInfoReq
     * @return
     * @throws Exception
     */
    @Override
    public AppBaseResult modifyOrderPayInfo(UpdatePayInfoReq updatePayInfoReq) throws Exception {
        AppBaseResult appBaseResult = new AppBaseResult(updatePayInfoReq);
        //更新MobileBookingForm支付方式
        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        mobileBookingForm.setId(updatePayInfoReq.getOrderId());
        mobileBookingForm.setPayType(updatePayInfoReq.getPayType());
        int flag = mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
        if (flag < 1) {
            appBaseResult.setRetCode(SystemDefine.FAILURE);
            appBaseResult.setRetMsg("修改支付方式失败！");
        }
        //更新BookingForm支付方式
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(updatePayInfoReq.getBusiBookNo());
        if (bookingForm != null) {
            boolean companyFlag = false;
            if (updatePayInfoReq.getPayType().equals(PayTypeDefine.CASH_PAYMENT.getValue())) {
                //现金支付，付款人为接单人
                bookingForm.setPayUser(bookingForm.getRevUser());
            } else if (updatePayInfoReq.getPayType().equals(PayTypeDefine.ONLINE_PAYMENT.getValue())) {
                //平台支付，付款人为下单人
                if (bookingForm.getCreateCompanyId() != null) {
                    companyFlag = true;
                } else {
                    bookingForm.setPayUser(bookingForm.getCreateUser());
                }
            } else if (updatePayInfoReq.getPayType().equals(PayTypeDefine.TO_PAY.getValue())) {
                //到付，付款人为收货人
                bookingForm.setPayUser(bookingForm.getReceiverUser());
            }
            ComAccount comAccount;
            if (companyFlag) {
                comAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
            } else {
                comAccount = comAccountDaoEx.queryByAccount(bookingForm.getPayUser());
            }
            if (comAccount != null) {
                bookingForm.setPayUser(comAccount.getAcctUsername());
                bookingForm.setPayUserRealName(comAccount.getRealName());
                bookingForm.setPayUserTelephone(comAccount.getTelephone());
            }
        }
        flag = bookingFormDaoEx.updateBookingFormPayType(updatePayInfoReq.getBusiBookNo(), updatePayInfoReq.getPayType(),
                bookingForm.getPayUser(), bookingForm.getPayUserRealName(), bookingForm.getPayUserTelephone());
        if (flag < 1) {
            appBaseResult.setRetCode(SystemDefine.FAILURE);
            appBaseResult.setRetMsg("修改支付方式失败！");
        }
        return appBaseResult;
    }

    /**
     * 上传文件校验
     *
     * @param file
     * @param fileId
     * @return
     */
    private ResultBean checkFileInfo(MultipartFile file, String fileId) {
        ResultBean resultBean = new ResultBean();
        if (file == null || file.getSize() == 0) {
            resultBean.setMessage("文件为空");
            return resultBean;
        }
        if (file.getSize() > MobileStationDefine.NORMAL_FILE_SIZE) {
            resultBean.setMessage("文件超过指定大小");
            return resultBean;
        }
        resultBean.setState(true);
        return resultBean;
    }

    /**
     * 插入跟踪日志
     *
     * @param waybillTraceOperateBean
     * @throws Exception
     */

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
            } else {
                // 派车单，需要插入派车单和所有子订单的跟踪日志
                // 插入主单日志
                tmp = new ComWaybillTrace();
                tmp.setAcctUsername(waybillTraceOperateBean.getAcctUsername());
                tmp.setRealName(waybillTraceOperateBean.getRealName());
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
                tmps.add(tmp);
                // 插入子单日志
                List<MobileScheduSubOrder> bookingForms = mobileMyOrderDao
                        .selectMobileSubOrderByMobileId(waybillTraceOperateBean.getDispatchId());
                for (int i = 0; i < bookingForms.size(); i++) {
                    ComWaybillTrace sontmp = new ComWaybillTrace();
                    sontmp.setAcctUsername(waybillTraceOperateBean.getAcctUsername());
                    tmp.setRealName(waybillTraceOperateBean.getRealName());
                    sontmp.setBusiBookNo(bookingForms.get(i).getBusiBookNo());
                    sontmp.setStartLocus(waybillTraceOperateBean.getStartLocus());
                    sontmp.setDestnLocus(waybillTraceOperateBean.getDestnLocus());
                    sontmp.setGrade(waybillTraceOperateBean.getGrade());
                    sontmp.setRemark(waybillTraceOperateBean.getRemark());
                    sontmp.setExecCode(waybillTraceOperateBean.getExecCode());
                    sontmp.setStaDate(new Date());
                    sontmp.setHubNo(waybillTraceOperateBean.getScheducarno());
                    if (waybillTraceOperateBean.getRoleId() != null) {
                        sontmp.setRoleId(waybillTraceOperateBean.getRoleId());
                    }
                    tmps.add(sontmp);
                }
            }
        }
        if (tmps.size() > 0) {
            DisruptorHelper.getInstance().producer(tmps);
        }
    }
}
