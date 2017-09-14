package com.gistandard.transport.order.module.mobilestation.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.order.bean.*;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.BookingFormDao;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.base.entity.dao.MobileSingleCenterDao;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.CustomerOrderService;
import com.gistandard.transport.order.module.customer.bean.PlaceAnOrderRes;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.userorder.*;
import com.gistandard.transport.order.module.mobilestation.bean.userorder.AssignOrderBean;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileUserOrderDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileStationOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileUserOrderService;
import com.gistandard.transport.order.module.service.StatsBizOrderService;
import com.gistandard.transport.order.webservice.client.merchant.order.DispatchExtend;
import com.gistandard.transport.order.webservice.client.merchant.order.HubToHubDataResult;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import com.gistandard.transport.order.webservice.client.merchant.order.OrderObject;
import com.gistandard.transport.order.webservice.client.order.expressbusiness.BatchSyncBusinessOrderRequest;
import com.gistandard.transport.order.webservice.client.order.expressbusiness.ExpreessBusinessOrderVO;
import com.gistandard.transport.order.webservice.client.order.expressbusiness.ExpressBusinessOrderWebService;
import com.gistandard.transport.order.webservice.client.order.expressbusiness.ServiceResponse;
import com.gistandard.transport.order.webservice.client.order.hub.ExpreessOrderWebService;
import com.gistandard.transport.order.webservice.client.order.hub.SuccDeliverSchudeCarOrderRequest;
import com.gistandard.transport.quote.system.common.bean.ComQuotePriceBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.quote.system.database.services.ComQuoteService;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.constant.PayTypeDefine;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.gps.util.GeoUtil;
import com.gistandard.transport.system.upload.define.UploadFileType;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
import com.gistandard.transport.tools.util.DateUtil;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.common.bean.AccountInfo;
import com.valueplus.psc.dubbo.service.common.bean.ServiceAuthBean;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import com.valueplus.psc.dubbo.service.login.AccountService;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author xgw
 * @ClassName MobileStationOrderServiceImpl
 * @Description
 * @Version 1.0
 * @Date 2015-08-17
 */
@Service
public class MobileUserOrderServiceImpl implements MobileUserOrderService {

    private static final Logger logger = LoggerFactory.getLogger(MobileUserOrderServiceImpl.class);
    @Autowired
    private MobileUserOrderDao mobileUserOrderDao;
    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;
    @Autowired
    private MobileSingleCenterDao mobileSingleCenterDao;
    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;
    @Autowired
    private ExpressBusinessOrderWebService expressBusinessOrderWebService;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private ComAccountDaoEx comAccountDaoEx;
    @Autowired
    private BizAttachmentDaoEx bizAttachmentDaoEx;
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
    private ComQuoteService comQuoteService;
    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private MobileStationOrderService mobileStationOrderService;
    @Autowired
    private MobileStationOrderDao mobileStationOrderDao;
    @Autowired
    private MobileGoodsDtlDaoEx mobileGoodsDtlDaoEx;
    @Autowired
    private BookingFormDao bookingFormDao;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private ExpreessOrderWebService expreessOrderWebService;
    @Autowired
    private MobileMyOrderService mobileMyOrderService;
    @Autowired
    private ComAccountService comAccountService;
    @Autowired
    private ComAccountRoleRelDaoEx accountRoleRelDaoEx;
    @Autowired
    private ComGoodsTypeService comGoodsTypeService;
    @Autowired
    private PnWebService pnWebService;
    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;
    @Autowired
    private ComVehicleInfoDaoEx comVehicleInfoDaoEx;
    @Autowired
    private ComAccountCategoryDaoEx comAccountCategoryDaoEx;
    @Autowired
    private ComCountyDaoEx comCountyDaoEx;
    @Autowired
    private GpsLogService gpsLogService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StatsBizOrderService statsBizOrderService;

    @Value("#{spring.sysGFCode}")
    private String sysGFCode;   //平台公布价结算时的平台账号

    /**
     * MS3.0用户订单 - 订单列表查询
     *
     * @param mobileUserOrderListReq
     * @return
     */
    @Override
    public UserOrderQueryListResult queryList(MobileUserOrderListReq mobileUserOrderListReq)
            throws MobileStationBizException {
        if (mobileUserOrderListReq == null) {
            throw new MobileStationBizException("请求参数不能为空！");
        }
        UserOrderQueryListResult baseResPageBean = new UserOrderQueryListResult(mobileUserOrderListReq);
        int recordCount = 0;
        List<MobileStationOrderListBean> orderBeanList;
        if (mobileUserOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
            orderBeanList = mobileUserOrderDao.queryMiUserOrderList(mobileUserOrderListReq);
            logger.info("咪站用户订单列表查询  ， 入参：{} ", JSON.toJSONString(mobileUserOrderListReq));
        } else {
            orderBeanList = mobileUserOrderDao.queryUserOrderList(mobileUserOrderListReq);
            logger.info("用户订单列表查询  ， 入参：{} ", JSON.toJSONString(mobileUserOrderListReq));
        }
        List<MobileUserOrderListBean> userOrderBeanList = new ArrayList<>();
        if (orderBeanList.size() > 0) {
            // 查询订单总条数
            if (mobileUserOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                recordCount = mobileUserOrderDao.getMiUserOrderListCount(mobileUserOrderListReq);
            } else {
                recordCount = mobileUserOrderDao.getUserOrderListCount(mobileUserOrderListReq);
            }
            try {
                Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                Map<String, ComCity> comCityMap = comCityService.queryForMap();
                Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
                Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
                Map<String, ComGoodsType> comGoodsTypeMap = comGoodsTypeService.queryForMap();
                for (MobileStationOrderListBean orderBean : orderBeanList) {
                    MobileUserOrderListBean userOrderListBean = new MobileUserOrderListBean();
                    PropertyUtils.copyProperties(userOrderListBean, orderBean);
                    if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                        // 签派待接单，需要显示待接单HUB信息
                        List<MobileSingleCenter> mobileSingleCenterList = mobileUserOrderDao.querySingleCenterByOrderId(orderBean.getOrderId(), orderBean.getRevUser(), 1);
                        if (mobileSingleCenterList != null && mobileSingleCenterList.size() > 0) {
                            if (mobileSingleCenterList.get(0).getTeamComsixNo() != null) {
                                List<ComCustomer> comCustomerList = comCustomerDaoEx.queryCompanyByParams(mobileSingleCenterList.get(0).getTeamComsixNo(), null);
                                if (comCustomerList != null && comCustomerList.size() > 0) {
                                    userOrderListBean.setDestProvide(comCustomerList.get(0).getProvince());
                                    userOrderListBean.setDestCity(comCustomerList.get(0).getCity());
                                    userOrderListBean.setDestCounty(comCustomerList.get(0).getCounty());
                                    userOrderListBean.setDestHubName(comCustomerList.get(0).getCustName());
                                    userOrderListBean.setDestAddress(comCustomerList.get(0).getCustAdd());
                                    userOrderListBean.setDestnLocus(mobileSingleCenterList.get(0).getTeamComsixNo());
                                    userOrderListBean.setDestLongitude(comCustomerList.get(0).getStaLongitude());
                                    userOrderListBean.setDestLatitude(comCustomerList.get(0).getStaLatitude());
                                    userOrderListBean.setDestTel(comCustomerList.get(0).getCustTel());
                                }
                            }
                        }
                    }

                    // 根据省市区设置地址
                    String startAddress = "";
                    String destAddress = "";
                    if (!StringUtil.isEmpty(orderBean.getStartProvide())
                            && comProvinceMap.get(orderBean.getStartProvide()) != null) {
                        // 判断地址信息是否包含省
                        if (orderBean.getStartAddress().indexOf(comProvinceMap.get(orderBean.getStartProvide()).getProvinceName()) == -1) {
                            startAddress += comProvinceMap.get(orderBean.getStartProvide()).getProvinceName();
                            if (!StringUtil.isEmpty(orderBean.getStartCity()) && comCityMap.get(orderBean.getStartCity()) != null) {
                                startAddress += comCityMap.get(orderBean.getStartCity()).getName();
                            }
                            if (!StringUtil.isEmpty(orderBean.getStartCounty()) && comCountyMap.get(orderBean.getStartCounty()) != null) {
                                startAddress += comCountyMap.get(orderBean.getStartCounty()).getAreaName();
                            }
                        }
                    }
                    userOrderListBean.setStartAddress(startAddress + orderBean.getStartAddress());
                    if (!StringUtil.isEmpty(orderBean.getDestProvide())
                            && comProvinceMap.get(orderBean.getDestProvide()) != null) {
                        // 判断地址信息是否包含省
                        if (orderBean.getDestAddress().indexOf(comProvinceMap.get(orderBean.getDestProvide()).getProvinceName()) == -1) {
                            destAddress += comProvinceMap.get(orderBean.getDestProvide()).getProvinceName();
                            if (!StringUtil.isEmpty(orderBean.getDestCity()) && comCityMap.get(orderBean.getDestCity()) != null) {
                                destAddress += comCityMap.get(orderBean.getDestCity()).getName();
                            }
                            if (!StringUtil.isEmpty(orderBean.getDestCounty()) && comCountyMap.get(orderBean.getDestCounty()) != null) {
                                destAddress += comCountyMap.get(orderBean.getDestCounty()).getAreaName();
                            }
                        }
                    }

                    userOrderListBean.setDestAddress(destAddress + orderBean.getDestAddress());
                    // 设置描述信息
                    String description = "";
                    String weightUtil = "";
                    for (MobileGoodsInfo goodsInfo : orderBean.getGoodsInfoList()) {
                        if (!StringUtil.isEmpty(goodsInfo.getGoodsType()) && comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                            description += comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh() + "-";
                        }
                        if (goodsInfo.getGoodsName() != null) {
                            description += goodsInfo.getGoodsName();
                        }
                        if (goodsInfo.getGoodsWeight() != null) {
                            if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit()) && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                                weightUtil = comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh();
                            }
                            description += "重量：" + goodsInfo.getGoodsWeight().setScale(2, BigDecimal.ROUND_HALF_UP) + weightUtil + " ";
                        }
                        if (goodsInfo.getGoodsLength() != null && goodsInfo.getGoodsWide() != null && goodsInfo.getGoodsHeight() != null) {
                            description += "体积：" + goodsInfo.getGoodsLength().setScale(0, BigDecimal.ROUND_HALF_UP)
                                    + "*" + goodsInfo.getGoodsWide().setScale(0, BigDecimal.ROUND_HALF_UP) + "*"
                                    + goodsInfo.getGoodsHeight().setScale(0, BigDecimal.ROUND_HALF_UP) + "立方厘米";
                        }
                        description += " ";
                        // 货物类型名称
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
                    userOrderListBean.setDescription(description);

                    BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(userOrderListBean.getBusiBookNo());
                    //咪站的订单列表
                    if (mobileUserOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                        // 判断咪站是否需要签派
                        userOrderListBean.setDispatchFlag(false);
                        if (MobileStationDefine.M.equals(userOrderListBean.getStartLocus())
                                && MobileStationDefine.POD.equals(userOrderListBean.getDestnLocus())
                                && userOrderListBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                                && !userOrderListBean.getOrderFrom().equals(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU)
                                && !userOrderListBean.getOrderFrom().equals(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST)) {
                            userOrderListBean.setDispatchFlag(true);
                        }

                        //设置咪站指派的MS信息
                        if (userOrderListBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE) {
                            MobileSingleCenter mobileSingleCenter = mobileMyOrderDao.querySingleCenter(userOrderListBean.getOrderId(),
                                    MobileStationDefine.SINGLE_CENTER_ACCEPT,
                                    mobileUserOrderListReq.getAccountId());
                            if (mobileSingleCenter != null && mobileSingleCenter.getRevUserId() != null) {
                                ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(mobileSingleCenter.getRevUserId());
                                if (comUserinfo != null) {
                                    userOrderListBean.setMsLinkName(comUserinfo.getRealName());
                                    userOrderListBean.setMsLinkTel(comUserinfo.getTelephone());
                                }
                                if (mobileUserOrderListReq.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()) {
                                    //如果是车主，设置车牌号
                                    List<ComVehicleInfo> comVehicleInfoList = comVehicleInfoDaoEx.queryVehicleByAcctId(mobileSingleCenter.getRevUserId());
                                    if (comVehicleInfoList != null && comVehicleInfoList.size() > 0) {
                                        userOrderListBean.setMsLinkCarNo(comVehicleInfoList.get(0).getTruckcode());
                                    }
                                }
                            }
                        }

                        //咪站 M-POD 展示目的地市、区；M-W 展示W站点名称
                        String destAddressName = "";
                        if (userOrderListBean.getDestnLocus().equals(MobileStationDefine.POD)) {
                            //咪站到POD
                            if (!StringUtil.isEmpty(orderBean.getDestCity()) && comCityMap.get(orderBean.getDestCity()) != null) {
                                destAddressName += comCityMap.get(orderBean.getDestCity()).getName();
                            }
                            if (!StringUtil.isEmpty(orderBean.getDestCounty()) && comCountyMap.get(orderBean.getDestCounty()) != null) {
                                destAddressName += " " + comCountyMap.get(orderBean.getDestCounty()).getAreaName();
                            }
                        } else {
                            //咪站到W站，根据W站code获取W站名称
                            ComCustomer comCustomer = comCustomerDaoEx.getComCustomerByCustTtl(orderBean.getDestnLocus());
                            if (comCustomer != null) {
                                destAddressName = comCustomer.getCustName();
                            }
                        }
                        userOrderListBean.setDestAddressName(destAddressName);
                    }

                    if (bookingForm != null) {
                        userOrderListBean.setCarriAgerecei(bookingForm.getCarriAgerecei());//设置订单是送货上门还是上门取件
                        userOrderListBean.setShipAddr(bookingForm.getCarriageReceiAddr());//用户订单，需要有发货人地址，做送达确认
                        //是否是四通一达单
                        if (StringUtil.isEmpty(bookingForm.getExpressOrderNo())) {
                            userOrderListBean.setStydFlag(false);
                        } else {
                            userOrderListBean.setStydFlag(true);
                        }
                    }
                    userOrderBeanList.add(userOrderListBean);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new MobileStationBizException("订单参数异常");
            }
        }
        baseResPageBean.setRecordCount(recordCount);
        baseResPageBean.setData(userOrderBeanList);
        return baseResPageBean;
    }

    /**
     * MS3.0用户订单 - 订单详细查询
     *
     * @param mobileUserOrderDetailReq
     * @return
     */
    @Override
    public UserOrderQueryDetailResult queryDetail(MobileUserOrderDetailReq mobileUserOrderDetailReq)
            throws MobileStationBizException {
        UserOrderQueryDetailResult baseResBean = new UserOrderQueryDetailResult(mobileUserOrderDetailReq);
        MobileStationOrderDetailBean detailBean = mobileUserOrderDao.queryUserOrderDetail(mobileUserOrderDetailReq);
        MobileUserOrderDetailBean userOrderDetailBean = new MobileUserOrderDetailBean();
        if (detailBean == null) {
            throw new MobileStationBizException("订单数据为空！");
        } else {
            try {
                PropertyUtils.copyProperties(userOrderDetailBean, detailBean);
                Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                Map<String, ComCity> comCityMap = comCityService.queryForMap();
                Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
                Map<String, ComCurrency> comCurrencyMap = comCurrencyService.queryForMap();
                Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
                Map<String, ComGoodsType> comGoodsTypeMap = comGoodsTypeService.queryForMap();

                if (detailBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                    // 签派待接单，需要显示待接单HUB信息
                    List<MobileSingleCenter> mobileSingleCenterList = mobileUserOrderDao.querySingleCenterByOrderId(
                            mobileUserOrderDetailReq.getOrderId(), mobileUserOrderDetailReq.getAcctUsername(), 1);
                    if (mobileSingleCenterList != null && mobileSingleCenterList.size() > 0) {
                        userOrderDetailBean.setSingleDate(mobileSingleCenterList.get(0).getSingleDate());// 转单日期
                        userOrderDetailBean.setAssignDate(mobileSingleCenterList.get(0).getSingleDate());
                        userOrderDetailBean.setAssignPredictValue(mobileSingleCenterList.get(0).getSingleValue());
                        userOrderDetailBean.setAssignPredictCurr(mobileSingleCenterList.get(0).getSingleCurr());
                        userOrderDetailBean.setAssignRevUserId(mobileSingleCenterList.get(0).getRevUserId());
                        ComAccount comAccount = comAccountDao.selectByPrimaryKey(mobileSingleCenterList.get(0)
                                .getRevUserId());
                        if (comAccount != null) {
                            userOrderDetailBean.setAssignRevUser(comAccount.getRealName());
                        }
                    }
                }

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
                        userOrderDetailBean.setQuoteDesc(quoteDesc);
                    }
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
                    userOrderDetailBean.setStartProvide(comProvinceMap.get(detailBean.getStartProvide())
                            .getProvinceName());
                    if (!StringUtil.isEmpty(detailBean.getStartCity())
                            && comCityMap.get(detailBean.getStartCity()) != null) {
                        userOrderDetailBean.setStartCity(comCityMap.get(detailBean.getStartCity()).getName());
                    }
                    if (!StringUtil.isEmpty(detailBean.getStartCounty())
                            && comCountyMap.get(detailBean.getStartCounty()) != null) {
                        userOrderDetailBean.setStartCounty(comCountyMap.get(detailBean.getStartCounty()).getAreaName());
                    }
                }
                userOrderDetailBean.setStartArea(startArea);

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
                }
                userOrderDetailBean.setDestProvide(comProvinceMap.get(detailBean.getDestProvide())
                        .getProvinceName());
                if (!StringUtil.isEmpty(detailBean.getDestCity())
                        && comCityMap.get(detailBean.getDestCity()) != null) {
                    userOrderDetailBean.setDestCity(comCityMap.get(detailBean.getDestCity()).getName());
                }
                if (!StringUtil.isEmpty(detailBean.getDestCounty())
                        && comCountyMap.get(detailBean.getDestCounty()) != null) {
                    userOrderDetailBean.setDestCounty(comCountyMap.get(detailBean.getDestCounty()).getAreaName());
                }
                userOrderDetailBean.setDestArea(destArea);

                // 设置单位名称
                for (MobileGoodsInfo goodsInfo : detailBean.getGoodsInfoList()) {
                    if (!StringUtil.isEmpty(goodsInfo.getGoodsType())
                            && comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                        goodsInfo.setGoodsTypeName(comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh());
                    }
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
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new MobileStationBizException("订单数据异常！");
            }
        }
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(userOrderDetailBean.getBusiBookNo());
        if (null != bookingForm) {
            userOrderDetailBean.setBookingUser(bookingForm.getCreateUser());
            userOrderDetailBean.setAccesstime(bookingForm.getAccesstime());
            userOrderDetailBean.setCarriAgerecei(bookingForm.getCarriAgerecei());
            userOrderDetailBean.setPlatQuoteNo(bookingForm.getDocno());
            userOrderDetailBean.setShipCustlinkTel(bookingForm.getShipCustlinkTel());
            userOrderDetailBean.setCreateUserTel(bookingForm.getShipCustlinkTel());
            userOrderDetailBean.setDestCustLinkMan(bookingForm.getCneeCustLinkMan());
            userOrderDetailBean.setDestCustLinkTel(bookingForm.getCneeCustLinkTel());
        }
        //设置发货人、收货人账号信息
        if (!StringUtil.isEmpty(detailBean.getStartTel())) {
            userOrderDetailBean.setStartUser(queryAccountByTelephone(detailBean.getStartTel()));
        }
        if (!StringUtil.isEmpty(detailBean.getDestTel())) {
            userOrderDetailBean.setDestUser(queryAccountByTelephone(detailBean.getDestTel()));
        }
        baseResBean.setData(userOrderDetailBean);
        return baseResBean;
    }

    /**
     * MS3.0 用户订单-放弃订单
     *
     * @param mobileStatusOperateReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult giveUpOrder(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);

        if (!checkParam(mobileStatusOperateReq)) {
            baseResBean.setRetCode(SysResult.FAILED.getValue());
            baseResBean.setRetMsg("参数不合法！");
            return baseResBean;
        }
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileStatusOperateReq.getBusiBookNo());
        List<MobileBookingForm> mobileBookingForms = new ArrayList<>();
        if (mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
            //M站会生成POP-M和M-POD两条，都得取消，状态分别为1、11
            mobileBookingForms = mobileBookingFormDaoEx.queryMiOrderListByConditions(mobileStatusOperateReq.getBusiBookNo()
                    , mobileStatusOperateReq.getAcctUsername(), mobileStatusOperateReq.getCompanyAccountId(), mobileStatusOperateReq.getRoleId());
            if (mobileBookingForms.size() == 1 && !bookingForm.getCarriAgerecei().equals("1")) {//1送货上门，2上门接货
                //如果不是送货上门且只有一条已接单记录，则证明POP-M已处于不可取消状态，则M-POD也不可取消
                throw new MobileStationBizException("咪站已指派快递员上门取件，订单不可取消");
            }
        }
        // add by yujie20170418 取消订单后，订单回归订单池
        bookingForm.setRevUser(null);
        bookingForm.setRevUserId(null);
        bookingForm.setRevDate(null);
        bookingForm.setRevCompanyId(null);
        bookingForm.setRevUserName(null);
        bookingForm.setBusiCtrl(OrderState.WAITRE_CEIVING.getValue());
        bookingFormDao.updateByPrimaryKey(bookingForm);
        customerOrderService.withdrawInsure(bookingForm.getBusiBookNo());

        if (mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
            for (MobileBookingForm mobileBookingForm : mobileBookingForms) {
                // 修改订单状态为放弃
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_GIVEUP);
                mobileBookingForm.setRevCompanyId(mobileStatusOperateReq.getCompanyAccountId());
                mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
                if (mobileBookingForms.size() == 2 && MobileStationDefine.M.equals(mobileBookingForm.getDestnLocus())) {

                } else { //记录日志
                    insertWaybillTrace(mobileStatusOperateReq, mobileBookingForm);
                }
            }
        } else {
            // 修改订单状态为放弃
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq.getOrderId());
            if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER) {
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_GIVEUP);
                mobileBookingForm.setRevCompanyId(mobileStatusOperateReq.getCompanyAccountId());
                mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
                //记录日志
                insertWaybillTrace(mobileStatusOperateReq, mobileBookingForm);
            } else {
                throw new MobileStationBizException("订单状态不是已接单，无法取消订单！");
            }
        }
        // 推送消息
        MsgIMReq msgIMReq = new MsgIMReq();
        try {
            PropertyUtils.copyProperties(msgIMReq, mobileStatusOperateReq);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_GIVEUP_ORDER);
        sendMsg(msgIMReq);
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(mobileStatusOperateReq.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(bookingForm.getTransportType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelAccept);
        giOrderTraceResynced.setTsAct(new Date());
        giOrderTraceResynced.setUserCode(mobileStatusOperateReq.getLoginAcctUserName());
        giOrderTraceResynced.setLoginCode(mobileStatusOperateReq.getAcctUsername());
        if (mobileStatusOperateReq.getRoleId() != null) {
            giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(mobileStatusOperateReq.getRoleId()));
        }
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
        //add by yujie20170418 订单取消后重新进入订单池，重新发送消息给中层
        PlaceAnOrderRes placeAnOrderRes = new PlaceAnOrderRes();
        placeAnOrderRes.setBusiBookNo(bookingForm.getBusiBookNo());
        placeAnOrderRes.setOrderId(bookingForm.getId());
        placeAnOrderRes.setProductType(bookingForm.getTransportType());
        placeAnOrderRes.setRoleId(mobileStatusOperateReq.getRoleId());
        customerOrderService.notifyGps(placeAnOrderRes, null);
        return baseResBean;
    }

    private void insertWaybillTrace(MobileStatusOperateReq mobileStatusOperateReq, MobileBookingForm mobileBookingForm) {
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        waybillTraceOperateBean.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
        // 插入之派单流程跟踪日志
//            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq
//                    .getOrderId());
        waybillTraceOperateBean.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
        if (mobileBookingForm != null) {
            waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
            waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
        }
        waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
        waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_GIVEUP.getIntValue());

        if (mobileStatusOperateReq.getRoleId() != null) {
            waybillTraceOperateBean.setRoleId(mobileStatusOperateReq.getRoleId());
            ComAccount account = comAccountService.queryAccountByAcctUsername(mobileStatusOperateReq.getAcctUsername());
            if (account != null) {
                waybillTraceOperateBean.setRealName(account.getRealName());
                waybillTraceOperateBean.setRemark(SysAccountRole.getName(mobileStatusOperateReq.getRoleId().intValue()) + account.getRealName()
                        + "已取消接单，请重新下单");
            } else {
                waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_GIVEUP.getName()
                        + mobileStatusOperateReq.getDescription());
            }
        } else {
            waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_GIVEUP.getName()
                    + mobileStatusOperateReq.getDescription());
        }
        mobileStationOrderService.insertWaybillTrace(waybillTraceOperateBean);
    }

    private boolean checkParam(MobileStatusOperateReq mobileStatusOperateReq) {
        if (mobileStatusOperateReq == null || mobileStatusOperateReq.getRoleId() == null
                || StringUtil.isEmpty(mobileStatusOperateReq.getBusiBookNo())
                || StringUtil.isEmpty(mobileStatusOperateReq.getAcctUsername())) {
            return false;
        }
        return true;
    }

    /**
     * MS3.0 用户订单-发车
     *
     * @param mobileStatusOperateReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult depart(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();//记录GPS操作日志
        List<String> allBusNoList = new ArrayList<>();
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq
                .getOrderId());

        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
        if (mobileBookingForm != null && MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus())) {
            if (bookingForm != null) {
                int premiumStatus = (bookingForm.getPremiumStatus() == null) ? -1 : bookingForm.getPremiumStatus().intValue();
                if (bookingForm.getNeedInsure() && CustomerDefine.HAVE_PAY != premiumStatus) {
                    if (CustomerDefine.NEED_PAY == premiumStatus) {
                        throw new MobileStationBizException("用户尚未支付保费，无法发车，请先与用户联系！");
                    }
                    if (bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
                        throw new MobileStationBizException("用户订单核保失败尚未解冻，无法发车，请先与用户联系！");
                    }


                }
            }
        }
        // 判断订单支付状态，如果没有支付成功，不能发车
        // if (bookingForm.getPayType() != MobileStationDefine.PAYTYPE_COLLECT)
        // {
        // if (bookingForm.getIsJs() != 3) {
        // throw new MobileStationBizException("订单未支付成功，不能发车！");
        // }
        // }
        // 获取订单状态，如果是取件成功，可以发车，否则不允许发车
        // if (mobileBookingForm.getBusiCtrl() !=
        // MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
        // throw new MobileStationBizException("取件成功后，才可以发车！");
        // }

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
        // 修改BOOKING_FORM数据
        if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.POP)) {
            if (bookingForm.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("该订单核保失败已被冻结，请与用户确认");
                return baseResBean;
            }
        }
        bookingForm.setBusiCtrl(2);
        int flag = bookingFormDaoEx.updateBookingFormStatus(bookingForm.getBusiBookNo(), 2);
        if (flag > 0) {
            // 发车成功
            // 修改订单当前状态
            MobileOrderOperateBean orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                    mobileStatusOperateReq.getOrderId(), mobileStatusOperateReq.getAcctUsername(),
                    MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS, MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN);
            mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);

            // 插入流程跟踪日志
            waybillTraceOperateBean.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
            if (mobileBookingForm != null) {
                waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
                waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
            }
            waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
            if(mobileStatusOperateReq.getAppLoginInfo()!=null && mobileStatusOperateReq.getAppLoginInfo().getComAccount()!=null) {
                waybillTraceOperateBean.setRealName(mobileStatusOperateReq.getAppLoginInfo().getComAccount().getRealName());
            }
            ComAccount account = comAccountService.queryAccountByAcctUsername(bookingForm.getRevUser());
            if (account != null && mobileStatusOperateReq.getRoleId() != null) {
                waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_DEPART_POP.getIntValue());
                waybillTraceOperateBean.setRealName(account.getRealName());
                waybillTraceOperateBean.setRemark(SysAccountRole.getName(mobileStatusOperateReq.getRoleId().intValue()) + account.getRealName() + "快件已发出");
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
            waybillTraceOperateBean.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
            mobileStationOrderService.insertWaybillTrace(waybillTraceOperateBean);

            //记录GPS操作日志
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            giOrderTraceResynced.setAction(MobileStationDefine.Action_TransportStart);
            allBusNoList.add(mobileBookingForm.getBusiBookNo());
            giOrderTraceResynced.setAllBusiNo(allBusNoList);
            giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
            giOrderTraceResynced.setUserCode(mobileStatusOperateReq.getLoginAcctUserName());
            giOrderTraceResynced.setLoginCode(mobileBookingForm.getRevUser());
            if (mobileStatusOperateReq.getRoleId() != null) {
                giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(mobileStatusOperateReq.getRoleId()));
            }
            giOrderTraceResynced.setUserCodeFrom(bookingForm.getCreateUser());
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

            giOrderTraceResynced.setUserCodeTo(null);
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
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
        } else {
            // 发车失败
            throw new MobileStationBizException("发车失败！");
        }
        return baseResBean;
    }

    /**
     * MS3.0 用户订单-订单失败
     *
     * @param mobileStatusOperateReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult orderFailure(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq
                .getOrderId());
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
        // 修改BOOKING_FORM数据
        bookingForm.setBusiCtrl(-1);
        int flag = bookingFormDao.updateByPrimaryKey(bookingForm);

        if (flag < 1) {
            throw new MobileStationBizException("操作失败！");
        } else {
            // 修改订单状态为放弃
            MobileOrderOperateBean orderOperateBean;
            // 派件单 状态从20到30 派件失败 需要退货
            orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                    mobileStatusOperateReq.getOrderId(), mobileStatusOperateReq.getAcctUsername(),
                    MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS, MobileStationDefine.MOBILE_ORDER_STATUS_FAILURE);
            mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);

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
        }
        return baseResBean;
    }

    /**
     * MS3.0 用户订单-确认送达
     *
     * @param mobileStatusOperateReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult deliveryConfirm(MobileStatusOperateReq mobileStatusOperateReq)
            throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();//记录GPS操作日志
        List<String> allBusNoList = new ArrayList<>();
        try {
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq.getOrderId());
            if (mobileBookingForm != null) {
                if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                    baseResBean.setRetCode(SystemDefine.FAILURE);
                    baseResBean.setRetMsg("该订单已经送达确认！");
                    return baseResBean;
                } else {
                    if (!mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)) {
                        if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.M)) {
                            baseResBean.setRetCode(SystemDefine.FAILURE);
                            baseResBean.setRetMsg("等待咪站收货！");
                            return baseResBean;
                        } else {
                            baseResBean.setRetCode(SystemDefine.FAILURE);
                            baseResBean.setRetMsg("等待蛙站收货！");
                            return baseResBean;
                        }
                    }
                    if(mobileBookingForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                            && mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()){
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("该订单未收货，不能送达确认！");
                        return baseResBean;
                    }
                    if(mobileBookingForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN
                            && mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()){
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("该订单未发车，不能送达确认！");
                        return baseResBean;
                    }
                }
            }
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
            int flag = 0;
            if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)) {
                // 送达给客户，直接送达
                bookingForm.setBusiCtrl(5);
                bookingForm.setDeliverDate(new Date());
                flag = bookingFormDao.updateByPrimaryKey(bookingForm);
                giOrderTraceResynced.setAction(MobileStationDefine.Action_PodConfirmed);
                giOrderTraceResynced.setUserCodeTo(null);
            } else if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.M)) {
                // 目的地为咪站，需要先在咪站收货 POP-M M-POD
                //1、订单号相同，2接单人为咪站，3订单起始为咪站
                MobileAssignBean mobileAssignBean = new MobileAssignBean();
                mobileAssignBean.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
                if (mobileBookingForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST
                        || mobileBookingForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL) {
                    //MS POP-POD 指派给M  生成POP-M、M-POD  POP-M送达时，需要判断M-POD的入库
                    mobileAssignBean.setCreateUser(mobileBookingForm.getRevUser());
                }
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
                                    && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH
                                    && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL
                                    && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_GIVEUP
                                    && form.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_REFUSE) {
                                baseResBean.setRetCode(SystemDefine.FAILURE);
                                baseResBean.setRetMsg("咪站没有收货，不能送达确认！");
                                return baseResBean;
                            }
                        }
                    }
                    flag = 1;
                    giOrderTraceResynced.setUserCodeTo(forms.get(0).getRevUser());
                }
                giOrderTraceResynced.setAction(MobileStationDefine.Action_TransportArrival);
            } else {
                // 指派给HUB，判断HUB入库状态
                // 调用hub接口
                SuccDeliverSchudeCarOrderRequest succDeliverSchudeCarOrderRequest = new SuccDeliverSchudeCarOrderRequest();
                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                    succDeliverSchudeCarOrderRequest.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                    succDeliverSchudeCarOrderRequest.setStaionCode(mobileBookingForm.getDestnLocus());
                    succDeliverSchudeCarOrderRequest.setDeliverAccount(mobileBookingForm.getRevUser());
                } else {
                    succDeliverSchudeCarOrderRequest.setSchudeCarNo(mobileStatusOperateReq.getScheducarno());
                }
                succDeliverSchudeCarOrderRequest.setSchudeCarType(mobileStatusOperateReq.getTransportType());
                succDeliverSchudeCarOrderRequest.setTotalLevel(true);
                logger.info("确认送达传参:{}", JSON.toJSONString(succDeliverSchudeCarOrderRequest));
                String expreessResStr = expreessOrderWebService
                        .succDeliverSchudeCarOrder(succDeliverSchudeCarOrderRequest);
                if (null != expreessResStr) {
                    AppBaseResult expreessRes = JSON.parseObject(expreessResStr, AppBaseResult.class);
                    if (expreessRes.getRetCode() == 0) {
                        flag = 1;
                    } else {
                        logger.info("确认送达结果{}", expreessRes.getRetMsg());
                        throw new MobileStationBizException(expreessRes.getRetMsg());
                    }
                }
                giOrderTraceResynced.setAction(MobileStationDefine.Action_TransportArrival);
                if (!StringUtil.isEmpty(mobileBookingForm.getDestnLocus())) {
                    String acctUsername = mobileMyOrderDao.queryAcctUsernameByCustTtl(mobileBookingForm.getDestnLocus());
                    giOrderTraceResynced.setUserCodeTo(acctUsername);
                }
            }

            if (flag < 1) {
                throw new MobileStationBizException("送达确认失败！");
            } else {
                // 修改订单状态为已完成
                MobileOrderOperateBean orderOperateBean;
                if (mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                    orderOperateBean = new MobileOrderOperateBean(
                            mobileStatusOperateReq.getAccountId(), mobileStatusOperateReq.getOrderId(),
                            mobileStatusOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS,
                            MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                    mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
                } else {
                    orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getOrderId(),
                            new Date(), MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN,
                            MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                    mobileStationOrderDao.driverUpdateBookingFormStatus(orderOperateBean);
                }


                if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)) {
                    statsBizOrderService.sendFinishOrderStats(bookingForm);
                }
                // 推送消息
                MsgIMReq msgIMReq = new MsgIMReq();
                try {
                    PropertyUtils.copyProperties(msgIMReq, mobileStatusOperateReq);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_DELIVERY_CONFIRM);
                sendMsg(msgIMReq);
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
                waybillTraceOperateBean.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
                if (mobileStatusOperateReq.getRoleId() != null) {
                    waybillTraceOperateBean.setRoleId(mobileStatusOperateReq.getRoleId());

                }
                mobileStationOrderService.insertWaybillTrace(waybillTraceOperateBean);
                //记录GPS操作日志
                Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                Map<String, ComCity> comCityMap = comCityService.queryForMap();
                Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
                allBusNoList.add(mobileBookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNoList);
                giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
                giOrderTraceResynced.setUserCode(mobileStatusOperateReq.getLoginAcctUserName());
                giOrderTraceResynced.setLoginCode(mobileBookingForm.getRevUser());
                if (mobileStatusOperateReq.getRoleId() != null) {
                    giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(mobileStatusOperateReq.getRoleId()));
                }
                giOrderTraceResynced.setUserCodeFrom(bookingForm.getCreateUser());
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
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
            }
        } catch (Exception e) {
            throw new MobileStationBizException(e.getMessage());
        }

        return baseResBean;
    }

    /**
     * MS3.0 用户订单-退回失败派件单
     *
     * @param mobileStatusOperateReq
     * @return
     */
    @Override
    public AppBaseResult returnFailureOrder(MobileStatusOperateReq mobileStatusOperateReq)
            throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        // 判断是否有货物未退回

        // 修改订单状态为取件完成
        MobileOrderOperateBean orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                mobileStatusOperateReq.getOrderId(), mobileStatusOperateReq.getAcctUsername(),
                MobileStationDefine.MOBILE_ORDER_STATUS_FAILURE, MobileStationDefine.MOBILE_ORDER_STATUS_RETURN);
        int flag = mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
        if (flag < 1) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("退回失败派件单成功失败！");
        } else {
            // 插入流程跟踪日志
        }
        return baseResBean;
    }

    /**
     * MS3.0 获取距离范围内附近的快递站点
     *
     * @param getNearStationReq
     * @return
     */
    @Override
    public UserOrderGetNearStationResult getNearStation(GetNearStationReq getNearStationReq)
            throws MobileStationBizException {
        UserOrderGetNearStationResult baseResBean = new UserOrderGetNearStationResult(getNearStationReq);
        double[] db = GeoUtil.getRectangle(getNearStationReq.getLongitude(), getNearStationReq.getLatitude(),
                getNearStationReq.getDistance());
        List<ComCustomer> stationList = mobileUserOrderDao.getNearStation(db[0], db[1], db[2], db[3]);
        List<ComStationBean> comStationBeanList = new ArrayList<>();
        try {
            ComAccount comAccount;
            List<BizAttachment> bizAttachmentList;
            BizAttachment bizAttachment;
            for (ComCustomer station : stationList) {
                ComStationBean comStationBean = new ComStationBean();
                PropertyUtils.copyProperties(comStationBean, station);
                // 设置距离
                double distance = GeoUtil
                        .calcDistance(getNearStationReq.getLongitude(), getNearStationReq.getLatitude(), station
                                .getStaLongitude().doubleValue(), station.getStaLatitude().doubleValue());
                comStationBean.setDistance(distance);
                // 设置头像
                comAccount = comAccountDao.selectByPrimaryKey(station.getAccountId());
                comStationBean.setHeadImage(comAccount.getUserImg());
                comStationBean.setAcctUsername(comAccount.getAcctUsername());
                // 设置营业执照
                bizAttachment = new BizAttachment();
                bizAttachment.setUploadUser(station.getAccountId());
                bizAttachment.setFileType(UploadFileType.OPERATE_LICENSE.getValue());
                bizAttachmentList = bizAttachmentDaoEx.querybyConditions(bizAttachment);
                comStationBean.setTelephone(station.getFworkTel());
                if (bizAttachmentList != null && bizAttachmentList.size() > 0) {
                    comStationBean.setYyzzImage(bizAttachmentList.get(0).getAttachPath());
                }
                comStationBeanList.add(comStationBean);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        baseResBean.setData(comStationBeanList);
        return baseResBean;
    }

    /**
     * MS3.0 用户订单-批量指派 MS在转单指派时，可以选择多个待转单，对某个站点进行批量指派
     *
     * @param batchAssignReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult batchAssign(BatchAssignReq batchAssignReq) throws MobileStationBizException {
        AppBaseResult baseResBean = new AppBaseResult(batchAssignReq);
        // 调用签派接口进行批量指派
        List<OrderObject> orderList = new ArrayList<>();
        OrderObject orderObject;
        for (AssignOrderBean assignOrder : batchAssignReq.getOrderBeanList()) {
            orderObject = new OrderObject();
            orderObject.setBusiNo(assignOrder.getBusiBookNo());
            orderObject.setQuotationNo(assignOrder.getComQuoteId());
            orderList.add(orderObject);
        }
        ComCustomer comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(batchAssignReq.getToGFuserID());
        List<DispatchExtend> dispatchExtends = new ArrayList<>();
        List<ExpreessBusinessOrderVO> expreessBusinessOrderList = new ArrayList<>();
        DispatchExtend dispatchExtend;
        ExpreessBusinessOrderVO expreessBusinessOrderVO;
        for (AssignOrderBean assignOrder : batchAssignReq.getOrderBeanList()) {
            dispatchExtend = new DispatchExtend();
            dispatchExtend.setDispatcherId(batchAssignReq.getAccountId());
            dispatchExtend.setFromCustomerId(0);
            dispatchExtend.setToCustomerId(comCustomer.getId());
            dispatchExtend.setSort(1);
            dispatchExtend.setOperatorId(batchAssignReq.getToGFuserID());
            dispatchExtend.setParentIraId(0L);
            dispatchExtend.setBusiBookNo(assignOrder.getBusiBookNo());
            dispatchExtend.setType(MobileStationDefine.W);
            dispatchExtend.setStatus(2);  //1、待接单，2、已接单
            dispatchExtend.setComments("POP签派到W站");
            dispatchExtends.add(dispatchExtend);

            //设置蛙站参数
            expreessBusinessOrderVO = new ExpreessBusinessOrderVO();
            expreessBusinessOrderVO.setBusiBookNO(assignOrder.getBusiBookNo());
            expreessBusinessOrderVO.setBusiOrderType(1);//1:POP-W 2 M-W
            if (batchAssignReq.getAppLoginInfo() != null && batchAssignReq.getAppLoginInfo().getComAccount() != null) {
                expreessBusinessOrderVO.setDeliveryUserName(batchAssignReq.getAppLoginInfo().getComAccount().getRealName());//快递员或司机姓名
                expreessBusinessOrderVO.setDeliveryUserTel(batchAssignReq.getAppLoginInfo().getComAccount().getTelephone());//快递员或司机电话
            }
            expreessBusinessOrderVO.setProductType(MobileStationDefine.PRODUCT_TYPE_TCKD);
            expreessBusinessOrderVO.setStationCode(batchAssignReq.getToGFuserTTL());
            expreessBusinessOrderVO.setLastStationCode(MobileStationDefine.POP);
            expreessBusinessOrderList.add(expreessBusinessOrderVO);
        }
        logger.debug("用户订单-批量指派 batchAssign：hubToHub before" + JSON.toJSONString(dispatchExtends));
        HubToHubDataResult result = mobileRecOrderWebService.hubToHub(null, dispatchExtends);
        logger.debug("用户订单-批量指派 batchAssign：hubToHub after" + JSON.toJSONString(result));
        if (result.getStatus() == 0) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg(result.getMesasge());
        } else {
            BatchSyncBusinessOrderRequest batchSyncBusinessOrderRequest = new BatchSyncBusinessOrderRequest();
            batchSyncBusinessOrderRequest.setExpreessBusinessOrderList(expreessBusinessOrderList);
            logger.debug("用户订单-批量指派 蛙站：batchSyncBusinessOrder before" + JSON.toJSONString(batchSyncBusinessOrderRequest));
            ServiceResponse serviceResponse = expressBusinessOrderWebService.batchSyncBusinessOrder(batchSyncBusinessOrderRequest);
            logger.debug("用户订单-批量指派 蛙站：batchSyncBusinessOrder after" + JSON.toJSONString(serviceResponse));
            MobileSingleCenter mobileSingleCenter;
            MobileBookingForm mobileBookingForm;
            logger.debug("用户订单-批量指派 签派返回列表：batchAssign after" + JSON.toJSONString(result.getDispatch()));
            for (DispatchExtend dispatch : result.getDispatch()) {
                logger.debug("用户订单-批量指派 ：batchAssign dispatch" + JSON.toJSONString(dispatch));
                // 根据签派返回的签派ID回写用户订单表
                mobileSingleCenter = new MobileSingleCenter();
                // 修改原POP-POD订单状态为已签派，待接单
                mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(dispatch.getBusiBookNo(),
                        batchAssignReq.getAcctUsername(), batchAssignReq.getCompanyAccountId(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS, null);
                if (mobileBookingForm != null) {
                    MobileOrderOperateBean orderOperateBean = new MobileOrderOperateBean(batchAssignReq.getAccountId(),
                            mobileBookingForm.getId(), batchAssignReq.getAcctUsername(),
                            MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS,
                            MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE);  //递送员指派给W站，W站自动接单
                    orderOperateBean.setNarrate(batchAssignReq.getNarrate());

                    int flag = mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
                    if (flag == 1) {
                        mobileSingleCenter.setMobileBookingFormId(mobileBookingForm.getId());
                        mobileSingleCenter.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                        mobileSingleCenter.setCreateUserId(mobileBookingForm.getRevUserId());
                        mobileSingleCenter.setCreateUser(mobileBookingForm.getRevUser());
                        mobileSingleCenter.setCreateDate(new Date());
                        mobileSingleCenter.setDispatchId(dispatch.getId().intValue());
                        mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_ACCEPT);
                        mobileSingleCenter.setSingleDate(new Date());
                        mobileSingleCenter.setTeamComsixNo(batchAssignReq.getToGFuserTTL());
                        mobileSingleCenter.setRevUserId(batchAssignReq.getToGFuserID());
                        mobileSingleCenter.setRevUser(batchAssignReq.getToGFuserName());
                        for (AssignOrderBean assignOrder : batchAssignReq.getOrderBeanList()) {
                            if (assignOrder.getBusiBookNo().equals(dispatch.getBusiBookNo())) {
                                mobileSingleCenter.setSingleCurr(assignOrder.getPredictCurr());
                                mobileSingleCenter.setSingleValue(assignOrder.getPredictValue());
                                break;
                            }
                        }
                        mobileSingleCenterDao.insert(mobileSingleCenter);
                        //调用W站接单
                        AcceptSingleOrderBean acceptSingleOrderBean = new AcceptSingleOrderBean();
                        acceptSingleOrderBean.setOrderId(mobileBookingForm.getId());
                        acceptSingleOrderBean.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                        acceptSingleOrderBean.setDestProvide(comCustomer.getProvince());
                        acceptSingleOrderBean.setDestCity(comCustomer.getCity());
                        acceptSingleOrderBean.setDestCounty(comCustomer.getCounty());
                        acceptSingleOrderBean.setDestLinkMan(comCustomer.getFlinkMan());
                        acceptSingleOrderBean.setDestLinkTel(comCustomer.getFworkTel());
                        acceptSingleOrderBean.setDestAddress(comCustomer.getCustAdd());
                        acceptSingleOrderBean.setDestLongitude(comCustomer.getStaLongitude());
                        acceptSingleOrderBean.setDestLatitude(comCustomer.getStaLatitude());
                        acceptSingleOrderBean.setCreateUserId(mobileBookingForm.getRevUserId());
                        acceptSingleOrderBean.setCreateUser(mobileBookingForm.getRevUser());
                        acceptSingleOrderBean.setCreteCompanyId(batchAssignReq.getCompanyAccountId());
                        acceptSingleOrderBean.setTeamComsixNo(comCustomer.getCustTtl());
                        acceptSingleOrderBean.setTeamComsixName(batchAssignReq.getToGFuserName());
                        acceptSingleOrderBean.setTeamComsixId(comCustomer.getAccountId());
                        acceptSingleOrderBean.setRevUser(batchAssignReq.getToGFuserName());
                        acceptSingleOrderBean.setRevUserId(batchAssignReq.getToGFuserID());
                        acceptSingleOrder(acceptSingleOrderBean, mobileBookingForm);
                    } else {
                        throw new MobileStationBizException("订单当前不可以指派！");
                    }
                } else {
                    throw new MobileStationBizException("订单当前不可以指派！");
                }
            }
        }
        logger.debug("用户订单-批量指派 batchAssign：end" + JSON.toJSONString(baseResBean));
        return baseResBean;
    }

    /**
     * 获取订单的支付状态接口
     *
     * @param getOrderPayStatus
     * @throws Exception
     */
    @Override
    public UserOrderGetOrderPayStatusResult getOrderPayStatus(GetOrderPayStatusReq getOrderPayStatus)
            throws MobileStationBizException {
        UserOrderGetOrderPayStatusResult baseResBean = new UserOrderGetOrderPayStatusResult(getOrderPayStatus);
        GetOrderPayStatusBean getOrderPayStatusBean = new GetOrderPayStatusBean();
        boolean payStatus = false;
        // 获取订单的支付状态
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(getOrderPayStatus.getBusiBookNo());
        if (bookingForm != null) {
            if (bookingForm.getIsJs() == 3) {
                // 如果是已支付状态
                bookingForm.getIsJs();// 获取结算、支付状态
                payStatus = true;
            } else {
                payStatus = false;
            }
            getOrderPayStatusBean.setPayStatus(payStatus);
            if (!StringUtil.isEmpty(bookingForm.getPayUser())) {
                ComAccount comAccount = comAccountDaoEx.queryByAccount(bookingForm.getPayUser());
                if (comAccount != null && !StringUtil.isEmpty(comAccount.getRealName())) {
                    getOrderPayStatusBean.setPayUserName(comAccount.getRealName());
                    getOrderPayStatusBean.setPayUserTel(comAccount.getTelephone());
                }
            }
            Map<String, ComCurrency> comCurrencyMap = comCurrencyService.queryForMap();
            if (!StringUtil.isEmpty(bookingForm.getPredictCurr()) && comCurrencyMap.get(bookingForm.getPredictCurr()) != null) {
                getOrderPayStatusBean.setPayValue(bookingForm.getPredictValue() + comCurrencyMap.get(bookingForm.getPredictCurr()).getCurrencyCh());
            }
            if (bookingForm.getPayType() != null && PayTypeDefine.getName(bookingForm.getPayType().toString()) != null) {
                getOrderPayStatusBean.setPayType(PayTypeDefine.getName(bookingForm.getPayType().toString()));
            }
            baseResBean.setData(getOrderPayStatusBean);
        } else {
            throw new MobileStationBizException("订单不存在！");
        }
        return baseResBean;
    }

    /**
     * MS3.0 转单后，商户接单 MS在用户订单指派后，等待商户接单，如果商户接单，生成一个POP-HUB的A单，并修改转单记录状态
     *
     * @param acceptSingleOrderBean
     * @return
     */
    public AppBaseResult acceptSingleOrder(AcceptSingleOrderBean acceptSingleOrderBean, MobileBookingForm mobileBookingForm)
            throws MobileStationBizException {
        logger.info("转单后，商户接单：acceptSingleOrder" + acceptSingleOrderBean.toString());
        AppBaseResult baseResBean = new AppBaseResult();
        // 生成一个POP-HUB的A单
        BookingForm bookingForm;
        if (mobileBookingForm != null) {
            // 查询BookingForm信息，看看首站点是否有值，如果没值，填写HUBcode
            bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
            if (bookingForm != null && StringUtil.isEmpty(bookingForm.getStartLocus())) {
                bookingForm.setStartLocus(acceptSingleOrderBean.getTeamComsixNo());
                bookingForm.setStartLocusNa(acceptSingleOrderBean.getTeamComsixName());
            }
            bookingFormDaoEx.updateBookingForm(bookingForm);
            // 查询原订单货物信息
            List<MobileGoodsDtl> mobileGoodsDtlList = mobileGoodsDtlDaoEx.selectByMobileBookingFormId(mobileBookingForm.getId());
            // 插入POP-HUB子订单
            mobileBookingForm.setId(null);
            mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
            mobileBookingForm.setCneeCustProvide(acceptSingleOrderBean.getDestProvide());
            mobileBookingForm.setCreateCompanyId(acceptSingleOrderBean.getCreteCompanyId());
            mobileBookingForm.setCneeCustCity(acceptSingleOrderBean.getDestCity());
            mobileBookingForm.setCneeCustCounty(acceptSingleOrderBean.getDestCounty());
            mobileBookingForm.setCneeCustAddr(acceptSingleOrderBean.getDestAddress());
            mobileBookingForm.setCneeCustLinkMan(acceptSingleOrderBean.getDestLinkMan());
            mobileBookingForm.setCneeCustLinkTel(acceptSingleOrderBean.getDestLinkTel());
            mobileBookingForm.setCneeLatitude(acceptSingleOrderBean.getDestLatitude());
            mobileBookingForm.setCneeLongitude(acceptSingleOrderBean.getDestLongitude());
            mobileBookingForm.setStartLocus(MobileStationDefine.POP);
            mobileBookingForm.setDestnLocus(acceptSingleOrderBean.getTeamComsixNo());
            mobileBookingForm.setDestnLocusId(acceptSingleOrderBean.getTeamComsixId());
            mobileBookingFormDao.insert(mobileBookingForm);

            // 插入A单货物信息
            for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
                mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
            }
            if (mobileGoodsDtlList.size() > 0) {
                mobileGoodsDtlDaoEx.batchInsert(mobileGoodsDtlList);
            }
        }
        return baseResBean;
    }

    public void sendMsg(MsgIMReq msgIMReq) {
        // 发送消息
        Map<String, String> mapObject = new HashMap<String, String>();
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
            msgIMReq.setMsgTo(1);
            msgIMReq.setMapObject(mapObject);
            mobileMyOrderService.pushMessageIM(msgIMReq);
        } catch (MobileStationBizException e) {
            logger.info("推送IM消息参数错误：" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String queryAccountByTelephone(String telephone) {
        ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
        serviceAuthBean.setAuthPwd(SystemDefine.CUSTOMER_AUTH_PWD);
        serviceAuthBean.setAuthUser(SystemDefine.CUSTOMER_AUTH_USER);
        serviceAuthBean.setSysFlag(SystemDefine.TRANSPORT_SYS_FLAG);
        ServiceResult serviceResult = accountService.queryAccountByTelephone(serviceAuthBean, telephone);
        if (serviceResult.isResult()) {
            List<AccountInfo> accountInfos = (List<AccountInfo>) serviceResult.getData();
            if (accountInfos != null && accountInfos.size() > 0) {
                return accountInfos.get(0).getAcctUsername();
            }
        }
        return null;
    }

    @Override
    public UserOrderQueryListResult queryListCustom(MobileUserOrderListReq mobileUserOrderListReq) throws MobileStationBizException {
        if (mobileUserOrderListReq == null) {
            throw new MobileStationBizException("请求参数不能为空！");
        }
        UserOrderQueryListResult baseResPageBean = new UserOrderQueryListResult(mobileUserOrderListReq);
        int recordCount = 0;
        List<MobileStationOrderListBean> orderBeanList;
        if (mobileUserOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
            orderBeanList = mobileUserOrderDao.queryUserOrderListCustom(mobileUserOrderListReq);
            logger.info("咪站用户订单列表查询  ， 入参：{} ", JSON.toJSONString(mobileUserOrderListReq));
            logger.info("咪站用户订单列表查询  ， 结果size：{} ", orderBeanList == null ? 0 : orderBeanList.size());
        } else {
            orderBeanList = mobileUserOrderDao.queryUserOrderList(mobileUserOrderListReq);
            logger.info("用户订单列表查询  ， 入参：{} ", JSON.toJSONString(mobileUserOrderListReq));
            logger.info("用户订单列表查询  ， 结果size：{} ", orderBeanList == null ? 0 : orderBeanList.size());
        }
        List<MobileUserOrderListBean> userOrderBeanList = new ArrayList<>();
        if (orderBeanList.size() > 0) {
            // 查询订单总条数
            if (mobileUserOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                recordCount = mobileUserOrderDao.getUserOrderListCustomCount(mobileUserOrderListReq);
            } else {
                recordCount = mobileUserOrderDao.getUserOrderListCount(mobileUserOrderListReq);
            }
            try {
//                Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                Map<String, ComCity> comCityMap = comCityService.queryForMap();
                Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
//                Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
//                Map<String, ComGoodsType> comGoodsTypeMap = comGoodsTypeService.queryForMap();
                for (MobileStationOrderListBean orderBean : orderBeanList) {
                    MobileUserOrderListBean userOrderListBean = new MobileUserOrderListBean();
                    PropertyUtils.copyProperties(userOrderListBean, orderBean);
                    if (orderBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                        // 签派待接单，需要显示待接单HUB信息
                        List<MobileSingleCenter> mobileSingleCenterList = mobileUserOrderDao.querySingleCenterByOrderId(orderBean.getOrderId(), orderBean.getRevUser(), 1);
                        if (mobileSingleCenterList != null && mobileSingleCenterList.size() > 0) {
                            if (mobileSingleCenterList.get(0).getTeamComsixNo() != null) {
                                List<ComCustomer> comCustomerList = comCustomerDaoEx.queryCompanyByParams(mobileSingleCenterList.get(0).getTeamComsixNo(), null);
                                if (comCustomerList != null && comCustomerList.size() > 0) {
                                    userOrderListBean.setDestProvide(comCustomerList.get(0).getProvince());
                                    userOrderListBean.setDestCity(comCustomerList.get(0).getCity());
                                    userOrderListBean.setDestCounty(comCustomerList.get(0).getCounty());
                                    userOrderListBean.setDestHubName(comCustomerList.get(0).getCustName());
                                    userOrderListBean.setDestAddress(comCustomerList.get(0).getCustAdd());
                                    userOrderListBean.setDestnLocus(mobileSingleCenterList.get(0).getTeamComsixNo());
                                    userOrderListBean.setDestLongitude(comCustomerList.get(0).getStaLongitude());
                                    userOrderListBean.setDestLatitude(comCustomerList.get(0).getStaLatitude());
                                    userOrderListBean.setDestTel(comCustomerList.get(0).getCustTel());
                                }
                            }
                        }
                    }

//                    // 根据省市区设置地址
//                    String startAddress = "";
//                    String destAddress = "";
//                    if (!StringUtil.isEmpty(orderBean.getStartProvide())
//                            && comProvinceMap.get(orderBean.getStartProvide()) != null) {
//                        // 判断地址信息是否包含省
//                        if (orderBean.getStartAddress().indexOf(comProvinceMap.get(orderBean.getStartProvide()).getProvinceName()) == -1) {
//                            startAddress += comProvinceMap.get(orderBean.getStartProvide()).getProvinceName();
//                            if (!StringUtil.isEmpty(orderBean.getStartCity()) && comCityMap.get(orderBean.getStartCity()) != null) {
//                                startAddress += comCityMap.get(orderBean.getStartCity()).getName();
//                            }
//                            if (!StringUtil.isEmpty(orderBean.getStartCounty()) && comCountyMap.get(orderBean.getStartCounty()) != null) {
//                                startAddress += comCountyMap.get(orderBean.getStartCounty()).getAreaName();
//                            }
//                        }
//                    }
//                    userOrderListBean.setStartAddress(startAddress + orderBean.getStartAddress());
//                    if (!StringUtil.isEmpty(orderBean.getDestProvide())
//                            && comProvinceMap.get(orderBean.getDestProvide()) != null) {
//                        // 判断地址信息是否包含省
//                        if (orderBean.getDestAddress().indexOf(comProvinceMap.get(orderBean.getDestProvide()).getProvinceName()) == -1) {
//                            destAddress += comProvinceMap.get(orderBean.getDestProvide()).getProvinceName();
//                            if (!StringUtil.isEmpty(orderBean.getDestCity()) && comCityMap.get(orderBean.getDestCity()) != null) {
//                                destAddress += comCityMap.get(orderBean.getDestCity()).getName();
//                            }
//                            if (!StringUtil.isEmpty(orderBean.getDestCounty()) && comCountyMap.get(orderBean.getDestCounty()) != null) {
//                                destAddress += comCountyMap.get(orderBean.getDestCounty()).getAreaName();
//                            }
//                        }
//                    }
//
//                    userOrderListBean.setDestAddress(destAddress + orderBean.getDestAddress());
                    // 设置描述信息
//                    String description = "";
//                    String weightUtil = "";
//                    for (MobileGoodsInfo goodsInfo : orderBean.getGoodsInfoList()) {
//                        if (!StringUtil.isEmpty(goodsInfo.getGoodsType()) && comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
//                            description += comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh() + "-";
//                        }
//                        if (goodsInfo.getGoodsName() != null) {
//                            description += goodsInfo.getGoodsName();
//                        }
//                        if (goodsInfo.getGoodsWeight() != null) {
//                            if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit()) && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
//                                weightUtil = comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh();
//                            }
//                            description += "重量：" + goodsInfo.getGoodsWeight().setScale(2, BigDecimal.ROUND_HALF_UP) + weightUtil + " ";
//                        }
//                        if (goodsInfo.getGoodsLength() != null && goodsInfo.getGoodsWide() != null && goodsInfo.getGoodsHeight() != null) {
//                            description += "体积：" + goodsInfo.getGoodsLength().setScale(2, BigDecimal.ROUND_HALF_UP)
//                                    + "*" + goodsInfo.getGoodsWide().setScale(2, BigDecimal.ROUND_HALF_UP) + "*"
//                                    + goodsInfo.getGoodsHeight().setScale(2, BigDecimal.ROUND_HALF_UP) + "立方厘米";
//                        }
//                        description += " ";
//                        // 货物类型名称
//                        if (!StringUtil.isEmpty(goodsInfo.getGoodsType()) && comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
//                            goodsInfo.setGoodsTypeName(comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh());
//                        }
//                        if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit()) && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
//                            goodsInfo.setGoodsQtyUnitName(comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh());
//                        }
//                        if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit()) && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
//                            goodsInfo.setGoodsWeightUnitName(comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh());
//                        }
//                        if (!StringUtil.isEmpty(goodsInfo.getGoodsVolumeUnit()) && comUnitMap.get(goodsInfo.getGoodsVolumeUnit()) != null) {
//                            goodsInfo.setGoodsVolumeUnitName(comUnitMap.get(goodsInfo.getGoodsVolumeUnit()).getUnitCh());
//                        }
//                    }
//                    userOrderListBean.setDescription(description);

//                    BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(userOrderListBean.getBusiBookNo());
                    //咪站的订单列表
                    if (mobileUserOrderListReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                        // 判断咪站是否需要签派
                        userOrderListBean.setDispatchFlag(false);
                        if (MobileStationDefine.M.equals(userOrderListBean.getStartLocus())
                                && MobileStationDefine.POD.equals(userOrderListBean.getDestnLocus())
                                && userOrderListBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS
                                && !userOrderListBean.getOrderFrom().equals(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU)
                                && !userOrderListBean.getOrderFrom().equals(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST)) {
                            userOrderListBean.setDispatchFlag(true);
                        }

                        //设置咪站指派的MS信息
                        if (userOrderListBean.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE) {
                            MobileSingleCenter mobileSingleCenter = mobileMyOrderDao.querySingleCenter(userOrderListBean.getOrderId(),
                                    MobileStationDefine.SINGLE_CENTER_ACCEPT,
                                    mobileUserOrderListReq.getAccountId());
                            if (mobileSingleCenter != null && mobileSingleCenter.getRevUserId() != null) {
                                ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(mobileSingleCenter.getRevUserId());
                                if (comUserinfo != null) {
                                    userOrderListBean.setMsLinkName(comUserinfo.getRealName());
                                    userOrderListBean.setMsLinkTel(comUserinfo.getTelephone());
                                }
                                if (mobileUserOrderListReq.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()) {
                                    //如果是车主，设置车牌号
                                    List<ComVehicleInfo> comVehicleInfoList = comVehicleInfoDaoEx.queryVehicleByAcctId(mobileSingleCenter.getRevUserId());
                                    if (comVehicleInfoList != null && comVehicleInfoList.size() > 0) {
                                        userOrderListBean.setMsLinkCarNo(comVehicleInfoList.get(0).getTruckcode());
                                    }
                                }
                            }
                        }

                        //咪站 M-POD 展示目的地市、区；M-W 展示W站点名称
                        String destAddressName = "";
                        if (userOrderListBean.getDestnLocus().equals(MobileStationDefine.POD)) {
                            //咪站到POD
                            if (!StringUtil.isEmpty(orderBean.getDestCity()) && comCityMap.get(orderBean.getDestCity()) != null) {
                                destAddressName += comCityMap.get(orderBean.getDestCity()).getName();
                            }
                            if (!StringUtil.isEmpty(orderBean.getDestCounty()) && comCountyMap.get(orderBean.getDestCounty()) != null) {
                                destAddressName += " " + comCountyMap.get(orderBean.getDestCounty()).getAreaName();
                            }
                        } else {
                            //咪站到W站，根据W站code获取W站名称
                            ComCustomer comCustomer = comCustomerDaoEx.getComCustomerByCustTtl(orderBean.getDestnLocus());
                            if (comCustomer != null) {
                                destAddressName = comCustomer.getCustName();
                            }
                        }
                        userOrderListBean.setDestAddressName(destAddressName);
                    }

//                    if (bookingForm != null) {
//                        userOrderListBean.setCarriAgerecei(bookingForm.getCarriAgerecei());//设置订单是送货上门还是上门取件
//                        userOrderListBean.setShipAddr(bookingForm.getCarriageReceiAddr());//用户订单，需要有发货人地址，做送达确认
//                        //是否是四通一达单
//                        if(StringUtil.isEmpty(bookingForm.getExpressOrderNo())){
//                            userOrderListBean.setStydFlag(false);
//                        }else {
//                            userOrderListBean.setStydFlag(true);
//                            userOrderListBean.setCneeCustLinkMan(bookingForm.getCneeCustLinkMan());
//                            userOrderListBean.setCneeCustLinkTel(bookingForm.getCneeCustLinkTel());
//                        }
//                    }
                    userOrderBeanList.add(userOrderListBean);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new MobileStationBizException("订单参数异常");
            }
        }
        baseResPageBean.setRecordCount(recordCount);
        baseResPageBean.setData(userOrderBeanList);
        return baseResPageBean;
    }
}
