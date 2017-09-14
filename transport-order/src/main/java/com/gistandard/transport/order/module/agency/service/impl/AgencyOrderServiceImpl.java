package com.gistandard.transport.order.module.agency.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComGoodsTypeDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileBookingFormDaoEx;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.calculate.bean.calc.CalcForPlatPriceResult;
import com.gistandard.transport.calculate.service.CalcService;
import com.gistandard.transport.gps.service.GpsService;
import com.gistandard.transport.order.module.agency.bean.*;
import com.gistandard.transport.order.module.agency.service.AgencyOrderService;
import com.gistandard.transport.order.module.customer.bean.PlaceAnOrderRes;
import com.gistandard.transport.order.module.customer.bean.ReceiveCustomerOrderReq;
import com.gistandard.transport.app.dubbo.genno.GenerateOrderNumberService;
import com.gistandard.transport.order.module.mobilestation.bean.MoStationOrderDbQueryReq;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationDbOrder;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationOrderResult;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockInOutReq;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDetailDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileStationOrderService;
import com.gistandard.transport.order.webservice.client.merchant.order.BaseRequestResult;
import com.gistandard.transport.order.webservice.client.merchant.order.Exception_Exception;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import com.gistandard.transport.order.webservice.client.merchant.order.RouteInfo;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileScheduSubOrderBean;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.RecordMobileStationOrderRequest;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.RevUserBean;
import com.gistandard.transport.order.webservice.server.mobilestation.constant.RecordOrderType;
import com.gistandard.transport.quote.module.calc.bean.CalcForPlatPriceReq;
import com.gistandard.transport.quote.module.calc.bean.CalcForPriceReq;
import com.gistandard.transport.quote.system.common.bean.ComQuotePriceBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.quote.system.database.services.ComQuoteService;
import com.gistandard.transport.system.common.address.dao.CustomerMobileAddressInfoDao;
import com.gistandard.transport.system.common.bean.AddressQueryRes;
import com.gistandard.transport.system.common.define.OrderDefine;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.tools.util.DateUtil;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xgw
 * @ClassName AgencyOrderServiceImpl
 * @Description MS3.0代理代理下单管理
 * @Version 3.0
 * @Date 2016/6/22
 */
@Service
public class AgencyOrderServiceImpl implements AgencyOrderService {

    @Autowired
    private MobileAddressInfoDao mobileAddressInfoDao;
    @Autowired
    private CustomerMobileAddressInfoDao customerMobileAddressInfoDao;
    @Autowired
    private BookingFormDao bookingFormDao;
    @Autowired
    private BillingFormSalmDao billingFormSalmDao;
    @Autowired
    private OrderCustomInfoDao orderCustomInfoDao;
    @Autowired
    private ComCurrencyService comCurrencyService;
    @Autowired
    private ComWaybillTraceDao comWaybillTraceDao;
    @Autowired
    private ComQuoteDao comQuoteDao;
    @Autowired
    private ComQuoteItemDao comQuoteItemDao;

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

    @Autowired
    private ComUserinfoDao comUserinfoDao;
    @Autowired
    private MobileStationOrderService mobileStationOrderService;
    @Autowired
    private ComProvinceService comProvinceService;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private ComCountyService comCountyService;
    @Autowired
    private ComQuoteService comQuoteService;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Autowired
    private MobileStationOrderDetailDao mobileStationOrderDetailDao;
    @Autowired
    private ComUnitService comUnitService;
    @Autowired
    private ComGoodsTypeDaoEx comGoodsTypeDaoEx;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private ComGoodsTypeService comGoodsTypeService;
    @Autowired
    private MobileStockDetailDao mobileStockDetailDao;
    @Autowired
    private MobileStockDao mobileStockDao;
    @Autowired
    private MobileGoodsDtlDao mobileGoodsDtlDao;
    @Autowired
    private GenerateOrderNumberService generateOrderNumberService;
    @Autowired
    private CalcService calcService;
    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;
    @Autowired
    private GpsLogService gpsLogService;
    @Autowired
    private GpsService gpsService;

    private static final Logger logger = LoggerFactory.getLogger(AgencyOrderServiceImpl.class);

    @Value("#{spring.user}")
    private String user;

    @Value("#{spring.passwd}")
    private String passwd;

    @Value("#{spring.sysGFCode}")
    private String sysGFCode;   //平台公布价结算时的平台账号

    @Value("#{customerOrderIM.customerOrderPredictValue}")
    private String customerOrderPredictValue;

    //编码格式。发送编码格式统一用GBK
    private static String ENCODING = "GBK";

    @Autowired
    private ComCustomerDao comCustomerDao;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * MS3.0 代理下单-列表查询
     *
     * @param req
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public AgencyOrderListRes queryList(AgencyOrderListReq req) throws MobileStationBizException {

        if (req == null) {
            throw new MobileStationBizException("请求参数不能为空！");
        }
        AgencyOrderListRes agencyOrderListRes = new AgencyOrderListRes(req);

        // 业务请求和数据库访问参数分离：构造数据库请求参数
        MoStationOrderDbQueryReq moStationOrderDbQueryReq = new MoStationOrderDbQueryReq();
        // 该业务获取待发车订单数据
        moStationOrderDbQueryReq.setStartRecord(req.getStartRecord());
        moStationOrderDbQueryReq.setPageSize(req.getPageSize());
        // 产品类型
        moStationOrderDbQueryReq.setProductTypeList(req.getProductTypeList());
        moStationOrderDbQueryReq.setShipperName(req.getShipperName());
        moStationOrderDbQueryReq.setReceiverName(req.getReceiverName());
        moStationOrderDbQueryReq.setStartTime(req.getStartTime());
        moStationOrderDbQueryReq.setEndTime(req.getEndTime());
        moStationOrderDbQueryReq.setCreateUserId(req.getAccountId());
        moStationOrderDbQueryReq.setRevUserId(req.getAccountId());
        moStationOrderDbQueryReq.setAreaId(req.getAreaId());
        moStationOrderDbQueryReq.setRoleId(req.getRoleId());

        // 查询订单总条数
        int recordCount = mobileStationOrderDetailDao.queryMobileStationOrderListCount(moStationOrderDbQueryReq);
        List<MobileStationDbOrder> orderDetailList;
        List<AgencyOrderListBean> agencyOrderList = new ArrayList<>();
        if (recordCount > 0) {
            orderDetailList = mobileStationOrderDetailDao.queryMobileStationOrderList(moStationOrderDbQueryReq);
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
            Map<String, ComGoodsType> comGoodsTypeMap = comGoodsTypeService.queryForMap();

            for (MobileStationDbOrder mobStationOrderDetail : orderDetailList) {

                AgencyOrderListBean agencyOrderListBean = convertAgencyOrderListRes(mobStationOrderDetail);
                if (!StringUtil.isEmpty(mobStationOrderDetail.getShipCustProvide())) {
                    agencyOrderListBean.setShipCustProvinceName(comProvinceMap.get(mobStationOrderDetail.getShipCustProvide()).getProvinceName());
                }
                if (!!StringUtil.isEmpty(mobStationOrderDetail.getShipCustCity())) {
                    agencyOrderListBean.setShipCustCityName(comCityMap.get(mobStationOrderDetail.getShipCustCity()).getName());
                }

                // 设置描述信息
                String description = "";
                String weightUtil = "";
                DecimalFormat myformat = new DecimalFormat("##################0.00");
                if (null == mobStationOrderDetail.getMobileScheduOrderList()
                        || mobStationOrderDetail.getMobileScheduOrderList().isEmpty()) {
                    for (MobileGoodsDtl goodsInfo : mobStationOrderDetail.getMobileGoodDtlList()) {
                        if (goodsInfo.getGoodsType() != null) {
                            String typeName = "";
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
                                goodsInfo.setGoodsWeightUnitName(weightUtil);
                            }

                            description += "  重量：" + myformat.format(goodsInfo.getGoodsWeight()) + weightUtil + " ";
                        }
                        if (!StringUtil.isEmpty(goodsInfo.getGoodsVolumeUnit())
                                && comUnitMap.get(goodsInfo.getGoodsVolumeUnit()) != null) {
                            goodsInfo.setGoodsVolumeUnitName(comUnitMap.get(goodsInfo.getGoodsVolumeUnit()).getUnitCh());
                        }
                        if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit())
                                && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                            goodsInfo.setGoodsQtyUnitName(comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh());
                        }
                        if (goodsInfo.getGoodsLenght() != null && goodsInfo.getGoodsWide() != null
                                && goodsInfo.getGoodsHeight() != null) {

                            description += "  体积：" + myformat.format(goodsInfo.getGoodsLenght()) + "CM*" + myformat.format(goodsInfo.getGoodsWide()) + "CM*"
                                    + myformat.format(goodsInfo.getGoodsHeight()) + "CM";
                        }
                        description += " ";
                    }
                    agencyOrderListBean.setMobileGoodDtlList(mobStationOrderDetail.getMobileGoodDtlList());
                } else {
                    for (MobileScheduSubOrderBean mobileScheduSubOrderBean : mobStationOrderDetail
                            .getMobileScheduOrderList()) {
                        List<MobileGoodsDtl> mobileGoodDtlList = mobileScheduSubOrderBean.getMobileGoodDtlList();
                        for (MobileGoodsDtl goodsInfo : mobileGoodDtlList) {
                            if (goodsInfo.getGoodsType() != null) {
                                String typeName = "";
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

                                description += "  重量：" + goodsInfo.getGoodsWeight() + weightUtil + " ";
                            }
                            if (goodsInfo.getGoodsLenght() != null && goodsInfo.getGoodsWide() != null
                                    && goodsInfo.getGoodsHeight() != null) {
                                description += "  体积：" + goodsInfo.getGoodsLenght() + "*" + goodsInfo.getGoodsWide()
                                        + "*" + goodsInfo.getGoodsHeight();
                            }
                            description += " ";
                        }
                    }
                }
                agencyOrderListBean.setOrderRemark(description);
                if (mobStationOrderDetail.getRoutePathInfo() != null) {
                    List<RouteInfo> routeList = JSON.parseArray(mobStationOrderDetail.getRoutePathInfo(), RouteInfo.class);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < routeList.size(); i++) {
                        sb.append(routeList.get(i).getCustName());
                        if (i != routeList.size() - 1) {
                            sb.append("->");
                        }
                    }
                    agencyOrderListBean.setOrderRouteInfo(sb.toString());
                }
                agencyOrderList.add(agencyOrderListBean);
            }
        }
        agencyOrderListRes.setRecordCount(recordCount);
        agencyOrderListRes.setData(agencyOrderList);

        return agencyOrderListRes;
    }

    /**
     * 组装代理下单列表参数对象
     *
     * @param mobStationOrderDetail
     * @return
     * @title convertAgencyOrderListRes
     * @author M.simple
     * @version 1.0
     * @datetime 2016年6月28日 下午5:07:50
     */
    private AgencyOrderListBean convertAgencyOrderListRes(MobileStationDbOrder mobStationOrderDetail) {
        AgencyOrderListBean agencyOrderListBean = new AgencyOrderListBean();
        try {
            // agencyOrderListRes 组装
            PropertyUtils.copyProperties(agencyOrderListBean, mobStationOrderDetail);
            agencyOrderListBean.setRevUser(null != mobStationOrderDetail.getRevComAccount() ? mobStationOrderDetail.getRevComAccount().getRealName() : null);
            agencyOrderListBean.setMobileBookingFormId(mobStationOrderDetail.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return agencyOrderListBean;
    }

    /**
     * MS3.0代理下单订单详细查询
     *
     * @return
     */
    @Override
    public AppBaseResult queryDetail(AgencyOrderDetailReq req) throws MobileStationBizException {
        AgencyOrderDetailRes agencyOrderDetailRes = new AgencyOrderDetailRes(req);

        MobileStationDbOrder detailBean = mobileStationOrderDetailDao.queryStationOrderDetail(req.getMobileStationFormId());
        if (null == detailBean) {
            throw new MobileStationBizException("该订单数据不存在！");
        }
        Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
        Map<String, ComCity> comCityMap = comCityService.queryForMap();
        Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
        Map<String, ComCurrency> comCurrencyMap = comCurrencyService.queryForMap();
        Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
        Map<String, ComGoodsType> comGoodsTypeMap = comGoodsTypeService.queryForMap();
//        if (!StringUtil.isEmpty(detailBean.getComQuoteId()) && detailBean.getQuotedType() != null) {
        QuoteResultBean quoteResultBean = comQuoteService.getQuoteInfoByQuoteNo(detailBean.getComQuoteId());
        if (null != quoteResultBean) {
            ComQuote comQuote = quoteResultBean.getComQuote();
            List<ComQuotePriceBean> comQuotePriceList = quoteResultBean.getComQuotePriceList();
            if (comQuote != null && detailBean.getQuotedType() != null) {
                String quoteDesc = "计价方式：";
                //设置报价信息
                if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_ZC) {
                    //整车报价
                    if (comQuote.getTotalPrice() != null && comQuote.getCurrencyCode() != null && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                        quoteDesc += "整车 " + comQuote.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP) + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh();
                    }
                } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_LD) {
                    //零担报价
                    if (comQuote.getHeavyUnitPrice() != null && comQuote.getCurrencyCode() != null && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                        quoteDesc += "重货 " + comQuote.getHeavyUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP) + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh() + "/千克 ";
                    }
                    if (comQuote.getLightUnitPrice() != null && comQuote.getCurrencyCode() != null && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                        quoteDesc += "轻货 " + comQuote.getLightUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP) + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh() + "/立方米 ";
                    }

                } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_ZLFD) {
                    //重量分段报价
                    if (comQuotePriceList != null) {
                        for (ComQuotePriceBean comQuotePrice : comQuotePriceList) {
                            if (comQuotePrice.getPrevPointValue() == null) {
                                quoteDesc += "0";
                            } else {
                                quoteDesc += comQuotePrice.getPrevPointValue().setScale(2, BigDecimal.ROUND_HALF_UP);
                            }
                            if (comQuotePrice.getPointValue() != null && comQuotePrice.getUnitPrice() != null && comUnitMap.get(comQuotePrice.getUnitCode()) != null && comQuote.getCurrencyCode() != null && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                                quoteDesc += "-" + comQuotePrice.getPointValue().setScale(2, BigDecimal.ROUND_HALF_UP) + " " + comQuotePrice.getUnitPrice() + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh() + "/" + comUnitMap.get(comQuotePrice.getUnitCode()).getUnitCh() + " ";
                            } else {
                                quoteDesc = "计价方式：";
                            }
                        }
                    }
                } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_GL) {
                    //公里报价
                    if (comQuote.getUnitPrice() != null) {
                        quoteDesc += "每公里 " + comQuote.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP) + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh();
                    }
                } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_GLFD) {
                    //公里分段报价
                    if (comQuotePriceList != null) {
                        for (ComQuotePriceBean comQuotePrice : comQuotePriceList) {
                            if (comQuotePrice.getPrevPointValue() == null) {
                                quoteDesc += "0";
                            } else {
                                quoteDesc += comQuotePrice.getPrevPointValue().setScale(2, BigDecimal.ROUND_HALF_UP);
                            }
                            if (comQuotePrice.getPointValue() != null && comQuotePrice.getUnitPrice() != null && comUnitMap.get(comQuotePrice.getUnitCode()) != null && comQuote.getCurrencyCode() != null && comCurrencyMap.get(comQuote.getCurrencyCode()) != null) {
                                quoteDesc += "-" + comQuotePrice.getPointValue().setScale(2, BigDecimal.ROUND_HALF_UP) + " " + comQuotePrice.getUnitPrice() + comCurrencyMap.get(comQuote.getCurrencyCode()).getCurrencyCh() + "/" + comUnitMap.get(comQuotePrice.getUnitCode()).getUnitCh() + " ";
                            } else {
                                quoteDesc = "计价方式：";
                            }
                        }
                    }
                } else if (detailBean.getQuotedType() == MobileStationDefine.QUOTE_TYPE_MY) {
                    //面议报价
                    quoteDesc += "面议";

                }
                detailBean.setQuoteRemark(quoteDesc);
            }
        }
        //设置币值
        if (!StringUtil.isEmpty(detailBean.getPredictCurr()) && comCurrencyMap.get(detailBean.getPredictCurr()) != null) {
            detailBean.setPredictCurr(comCurrencyMap.get(detailBean.getPredictCurr()).getCurrencyCh());
        }
        //根据省市区设置地址
        String startAddress = "";
        String destAddress = "";
        if (!StringUtil.isEmpty(detailBean.getShipCustProvide()) && comProvinceMap.get(detailBean.getShipCustProvide()) != null) {
            //判断地址信息是否包含省
            if (detailBean.getShipCustAddr().indexOf(comProvinceMap.get(detailBean.getShipCustProvide()).getProvinceName()) == -1) {
                startAddress += comProvinceMap.get(detailBean.getShipCustProvide()).getProvinceName();
                if (!StringUtil.isEmpty(detailBean.getShipCustCity()) && comCityMap.get(detailBean.getShipCustCity()) != null) {
                    startAddress += comCityMap.get(detailBean.getShipCustCity()).getName();
                }
                if (!StringUtil.isEmpty(detailBean.getShipCustCounty()) && comCountyMap.get(detailBean.getShipCustCounty()) != null) {
                    startAddress += comCountyMap.get(detailBean.getShipCustCounty()).getAreaName();
                }
            }
        }
        detailBean.setShipCustAddr(startAddress + detailBean.getShipCustAddr());

        if (!StringUtil.isEmpty(detailBean.getCneeCustProvide()) && comProvinceMap.get(detailBean.getCneeCustProvide()) != null) {
            //判断地址信息是否包含省
            if (detailBean.getCneeCustAddr().indexOf(comProvinceMap.get(detailBean.getCneeCustProvide()).getProvinceName()) == -1) {
                destAddress += comProvinceMap.get(detailBean.getCneeCustProvide()).getProvinceName();
                if (!StringUtil.isEmpty(detailBean.getCneeCustCity()) && comCityMap.get(detailBean.getCneeCustCity()) != null) {
                    destAddress += comCityMap.get(detailBean.getCneeCustCity()).getName();
                }
                if (!StringUtil.isEmpty(detailBean.getCneeCustCounty()) && comCountyMap.get(detailBean.getCneeCustCounty()) != null) {
                    destAddress += comCountyMap.get(detailBean.getCneeCustCounty()).getAreaName();
                }
            }
        }
        detailBean.setCneeCustAddr(destAddress + detailBean.getCneeCustAddr());

        // 设置单位名称
        List<MobileGoodsDtl> mobileGoodsDtls = new ArrayList<MobileGoodsDtl>();
        for (MobileGoodsDtl goodsInfo : detailBean.getMobileGoodDtlList()) {
            if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit())
                    && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                goodsInfo.setGoodsQtyUnit(comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh());
            }
            if (!StringUtil.isEmpty(goodsInfo.getGoodsWeightUnit())
                    && comUnitMap.get(goodsInfo.getGoodsWeightUnit()) != null) {
                goodsInfo.setGoodsWeightUnitName(comUnitMap.get(goodsInfo.getGoodsWeightUnit()).getUnitCh());
            }
            if (!StringUtil.isEmpty(goodsInfo.getGoodsVolumeUnit())
                    && comUnitMap.get(goodsInfo.getGoodsVolumeUnit()) != null) {
                goodsInfo.setGoodsVolumeUnitName(comUnitMap.get(goodsInfo.getGoodsVolumeUnit()).getUnitCh());
            }
            if (!StringUtil.isEmpty(goodsInfo.getGoodsType())
                    && comGoodsTypeMap.get(goodsInfo.getGoodsType()) != null) {
                goodsInfo.setGoodsType(comGoodsTypeMap.get(goodsInfo.getGoodsType()).getTypeCh());
            }
            mobileGoodsDtls.add(goodsInfo);
        }
        detailBean.setMobileGoodDtlList(mobileGoodsDtls);
//        }
        agencyOrderDetailRes.setData(detailBean);
        return agencyOrderDetailRes;
    }

    /**
     * 代理下单逻辑 1、订单类型：Outter单+指派单 2、下单到booking_form 订单状态为已接单（1）
     * 3、下单到moblie_booking_form 订单状态为已接单（1）
     *
     * @param req
     * @return
     * @throws MobileStationBizException
     * @title placeAnOrder
     * @describe TODO
     * @author M.simple
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AgencyPlaceOrderRes agencyPlaceOrder(PlaceAnOrderReq req) throws MobileStationBizException {
        AgencyPlaceOrderRes agencyPlaceOrderRes = new AgencyPlaceOrderRes(req);
        PlaceAnOrderRes placeAnOrderRes = new PlaceAnOrderRes();
        placeAnOrderRes.setGoodsPaymentCurr(req.getGoodsPaymentCurr());
        // TODO：服务提供者就是站点吗？不是，还有快递员和车辆
        checkParam(req);

        BookingForm bookingForm = new BookingForm();
        // 保存地址信息
        saveSenderAddress(req, bookingForm);
        saveReceiverAddress(req, bookingForm);

        if (null != req.getQuoteNo()) {
            bookingForm.setDocno(req.getQuoteNo());
        }

        // 收件人账号
        if (null != req.getReceiverAcctUsername()) {
            bookingForm.setReceiverUser(req.getReceiverAcctUsername());
        }

        ComAccount comAccount = comAccountDao.queryByAccount(req.getAcctUsername());
        if (null != comAccount) {
            bookingForm.setCreateUserId(comAccount.getId());
            req.setAccountId(comAccount.getId());
        }
        if (null != req.getAcctUsername()) {
            bookingForm.setCreateUser(req.getAcctUsername());
        }
        bookingForm.setCreateCompanyId(req.getCompanyAccountId());
        if (null != req.getNarrate()) {
            bookingForm.setNarrate(req.getNarrate());
        }
        if (null != req.getOrderForm()) {
            bookingForm.setOrderForm(req.getOrderForm());
        } else {
            bookingForm.setOrderForm("1");
        }

        bookingForm.setCarriageDeiv("2");//送货上门


        // 保存主表信息
        bookingForm.setBookingDate(new Date());// 申请订单日期
        bookingForm.setOrderType(req.getTransType()); // 1运输 2快递
        // 价格和增值服务
        bookingForm.setNeedInsure(req.getInsured());
        if (null != req.getGoodsValue()) {
            bookingForm.setGoodsValue(BigDecimal.valueOf(req.getGoodsValue()));
        }
        if (null != req.getPremiumValue()) {
            bookingForm.setPremiumValue(req.getPremiumValue());
        }
        if (req.getPaymentType() == 1) {
            if (req.getReceiverAcctUsername() == null) {
                throw new MobileStationBizException("收件人不是平台账户");
            }
            if (req.getPredictValue().compareTo(BigDecimal.valueOf(Long.valueOf(customerOrderPredictValue))) > 0) {
                throw new MobileStationBizException("运费金额要大于" + customerOrderPredictValue + "元");
            }

            bookingForm.setPayUser(req.getReceiverAcctUsername());
            if (StringUtils.isNotEmpty(req.getReceiverAcctUsername())) {
                ComAccount receiveAccount = comAccountDaoEx.queryByAccount(req.getReceiverAcctUsername());
                if (receiveAccount != null) {
                    bookingForm.setPayUserRealName(receiveAccount.getRealName());
                    bookingForm.setPayUserTelephone(receiveAccount.getTelephone());
                }
            }
        } else {
            //平台支付，付款人设置为下单人
            bookingForm.setPayUser(req.getAcctUsername());
            bookingForm.setPayUserRealName(req.getAppLoginInfo().getComAccount().getRealName());
            bookingForm.setPayUserTelephone(req.getAppLoginInfo().getComAccount().getTelephone());
        }
        bookingForm.setPayType(req.getPaymentType());// 1-到付 2-在线支付 3-现金支付
        if (null != req.getCourierId()) {
            bookingForm.setRevUserId(req.getCourierId());
            if (null != req.getCourierAcctUsername()) {
                bookingForm.setRevUser(req.getCourierAcctUsername());
            }
            if (null != req.getCourierAcctUserRealName()) {
                bookingForm.setRevUserName(req.getCourierAcctUserRealName());
            }
            bookingForm.setRevDate(new Date());
            bookingForm.setRevCompanyId(req.getCompanyAccountId());
        }
        if (null != req.getQuotedType()) {
            bookingForm.setQuotedType(req.getQuotedType());
        }

        bookingForm.setCarriAgerecei(req.getAccessMethod() + "");// 取货方式：1-客户自送，2-上门接货
        if (!StringUtils.isEmpty(req.getAccessTime())) {
            bookingForm.setAccesstime(req.getAccessTime());//取件时间
        }

        bookingForm.setCargoLoader("0");// 整车：1, 零担：0, 自拼车：2
        bookingForm.setIsAgent(true);
        String busiNo = generateOrderNumberService.getOrderNumberByCityId(NumberUtils.toInt(bookingForm.getCarriageReceiCity()));
        if (StringUtil.isEmpty(busiNo)) {
            throw new MobileStationBizException("获取订单号异常!");
        }
        bookingForm.setBusiBookNo(busiNo);
        bookingForm.setWaybillNo(busiNo);

        if (null != req.getPredictValue()) {//预估费用
            bookingForm.setPredictValue(req.getPredictValue());
        }
        if (null != req.getGoodsPayment()) {//收贷贷款
            bookingForm.setGoodsPayment(req.getGoodsPayment());
        }
        if (null != req.getGoodsPaymentCurr()) {//收贷贷款币制
            bookingForm.setGoodsPaymentCurr(req.getGoodsPaymentCurr());
        }
        bookingForm.setIsJs(0);//是否结算
        bookingForm.setIsDel(0);//是否可删报价

        if (null != req.getSmsNoty()) {
            bookingForm.setSmsNoty(req.getSmsNoty());//短信通知
        }

        if (!StringUtil.isEmpty(req.getPredictCurr())) {
            bookingForm.setPredictCurr(req.getPredictCurr());//广播单的币制
        }

        bookingForm.setTransportType(req.getItemCode());//产品类型

        if (!req.getBroadcast()) {//brodacast 广播单true 指派单false
            try {
                ComQuote comQuote = comQuoteDao.selectByPrimaryKey(Integer.parseInt(req.getComQuoteId()));
                if (comQuote != null) {
                    ComQuoteItem comQuoteItem = comQuoteItemDao.selectByPrimaryKey(comQuote.getItemId());
                    if (comQuoteItem != null) {
                        bookingForm.setTransportType(comQuoteItem.getItemCode());
                    }
                    if (StringUtil.isEmpty(req.getPredictCurr())) {
                        if (!StringUtil.isEmpty(comQuote.getCurrencyCode())) {
                            req.setPredictCurr(comQuote.getCurrencyCode());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        bookingForm.setWhtGrosswht(new BigDecimal(0));//总毛重
        bookingForm.setWhtVolcbm(new BigDecimal(0));//体积重量
        bookingForm.setGoodsQty(new BigDecimal(0));
        for (GoodsInfo goodsInfoReq : req.getGoodsInfos()) {
            if ("kg".equals(goodsInfoReq.getWeightUnit())) {
                if (null != goodsInfoReq.getWeight()) {
                    // 毛重
                    bookingForm.setWhtGrosswht(bookingForm.getWhtGrosswht().add(BigDecimal.valueOf(goodsInfoReq.getWeight())));
                    bookingForm.setWhtFeewht(bookingForm.getWhtGrosswht());
                }
            } else if ("g".equals(goodsInfoReq.getWeightUnit())) {
                if (null != goodsInfoReq.getWeight()) {
                    bookingForm.setWhtGrosswht(bookingForm.getWhtGrosswht().add(BigDecimal.valueOf(goodsInfoReq.getWeight())
                            .divide(BigDecimal.valueOf(1000)).setScale(4, BigDecimal.ROUND_HALF_UP)));
                    bookingForm.setWhtFeewht(bookingForm.getWhtGrosswht());
                }
            }
            if (null != goodsInfoReq.getHeight() && null != goodsInfoReq.getLength()
                    && null != goodsInfoReq.getWidth()) {
                // 体积
                bookingForm.setWhtVolcbm(bookingForm.getWhtVolcbm().add(BigDecimal.valueOf(goodsInfoReq.getHeight() * goodsInfoReq.getLength()
                        * goodsInfoReq.getWidth()).setScale(4,
                        BigDecimal.ROUND_HALF_UP)));
            }
            if (null != goodsInfoReq.getQty()) {
                bookingForm.setGoodsQty(bookingForm.getGoodsQty().add(BigDecimal.valueOf(goodsInfoReq.getQty())));
            }
        }
        if (bookingForm.getWhtFeewht() != null && bookingForm.getWhtVolwht() != null) {
            if (bookingForm.getWhtVolwht().compareTo(bookingForm.getWhtFeewht()) > 0) {
                bookingForm.setWhtFeewht(bookingForm.getWhtVolwht());
            }
        }
        bookingForm.setSenderFollow(CustomerDefine.UNFOLLOW);//发件人关注
        bookingForm.setReceiverFollow(CustomerDefine.UNFOLLOW);//收件人关注

        //根据保险情况，设置订单状态，若不投保，则直接进入接单状态，若投保，则先置为已取件
        if (req.getInsured()) {
            bookingForm.setBusiCtrl(OrderState.CANCLE_ORDER_FROZEN.getValue());
        } else {
            //自理下单后订单状态改为已接单 add by yujie20170329
            bookingForm.setBusiCtrl(Integer.valueOf(OrderDefine.ORDER_STATUS_HAVEORDER.getValue()));
        }

        //取件时间
        bookingForm.setAccesstime(DateUtil.formatDate2Str(new Date(), "yyyy-MM-dd HH-mm-ss"));

        //订单从哪里到哪里
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
        if (req.getStaffAccountId() != null && req.getStaffAccountId() > 0) {
            bookingForm.setCreateCompanyId(req.getStaffAccountId());
        }
        if (req.getRoleId() != null) {
            bookingForm.setBookingCtrl(req.getRoleId());
            placeAnOrderRes.setRoleId(req.getRoleId());
        }
        bookingForm.setMileage(req.getMileage());
        bookingFormDao.insertSelective(bookingForm);

        // 保存货物信息
        saveGoodsInfo(req.getGoodsInfos(), bookingForm);
//        //保存订仓订单表收发客户信息
//        saveOrderCustomInfo(req, bookingForm.getId());

        //投保操作
//        if (req.getInsured()) {
//            ApprovalRequest approvalRequest = new ApprovalRequest();
//            LoginUser userInfo = new LoginUser();
//            try {
//                userInfo.setUserName(URLEncoder.encode(user, ENCODING));
//                userInfo.setPassword(URLEncoder.encode(passwd, ENCODING));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            //承保产品代号
//            ApprovalProduct productInfo = new ApprovalProduct();
//            productInfo.setClassesCode(CustomerDefine.CLASSTYPE);
//            ApprovalResponse2 reposne = null;
//
//            try {
//                //包装发送信息
//                approvalRequest.setUserInfo(userInfo);
//                approvalRequest.setProductInfo(productInfo);
//                approvalRequest.setCheckCode("1");
//                StringBuffer sb = new StringBuffer("");
//                ComUserinfo comUserinfo = null;
//                ComCustomer comCustomer = null;
//                if (comAccount != null) {
//                    comUserinfo = comUserinfoDao.queryByAcctId(comAccount.getId());
//                    if (null == comUserinfo) {
//                        comCustomer = comCustomerDao.queryCustomerInfoByAcctId(comAccount.getId());
//                    }
//                }
//                Date now = new Date();
//                //快递
//                if (req.getItemCode() != null && (req.getItemCode().equals(QuoteItem.OTCKD.getItemCode()))) {
//                    sb.append("<?xml version ='1.0' encoding='utf-8'?>").append("<FREIGHTCPIC>")
//                            .append("<HEADER> ")
//                            .append("<ApplyId>").append(bookingForm.getBusiBookNo()).append("</ApplyId>")
//                            .append("<CLASSESTYPE>").append(CustomerDefine.CLASSESTYPE).append("</CLASSESTYPE>")
//                            .append("</HEADER>")
//                            .append("<DATAS>")
//                            .append("<DATA>")
//                            .append("<APPLYNAME>").append(null != comUserinfo ? comUserinfo.getRealName() : comCustomer.getCustName()).append("</APPLYNAME>")
//                            .append("<APPLYCARTTYPE>").append(null != comUserinfo ? "1170010001" : "2170010001").append("</APPLYCARTTYPE>")
//                            .append("<APPLYCARDCODE>").append(null != comUserinfo ? comUserinfo.getIdentno() : comCustomer.getOrgCodeLilcenseNo()).append("</APPLYCARDCODE>")
//                            .append("<INSURANTNAME>").append(bookingForm.getShipCustlinkMan()).append("</INSURANTNAME>")
//                            .append("<INSURANTTEL>").append(bookingForm.getShipCustlinkTel()).append("</INSURANTTEL>")
//                            .append("<CLASSTYPE>").append(CustomerDefine.CLASSTYPE).append("</CLASSTYPE>")
//                            .append("<MARK>").append(bookingForm.getBusiBookNo()).append("</MARK>")
//                            .append("<QUANTITY>").append(getQuantity(req)).append("</QUANTITY>")
//                            .append("<ITEM>").append(getItem(req)).append("</ITEM>")
//                            .append("<PACKCODE>").append(InsuranceUnit.getInsuranceCode(req.getGoodsInfos().get(0).getQtyUnitCode())).append("</PACKCODE>")
//                            .append("<ITEMCODE>").append(getItemCode(req)).append("</ITEMCODE>")
//                            .append("<KIND>").append(CustomerDefine.HIGHWAY).append("</KIND>")
//                            .append("<KINDNAME>").append(CustomerDefine.PARCEL_TOOL).append("</KINDNAME>")
//                            .append("<STARTPORT>").append(getCity(bookingForm.getCarriageReceiCounty() == null ? bookingForm.getCarriageReceiCity() : bookingForm.getCarriageReceiCounty())).append("</STARTPORT>")
//                            .append("<ENDPORT>").append(getCity(bookingForm.getCarriageDelivCounty() == null ? bookingForm.getCarriageDelivCity() : bookingForm.getCarriageDelivCounty())).append("</ENDPORT>")
//                            .append("<MAINITEMCODE>").append(CustomerDefine.MAINITEMCODE_KD).append("</MAINITEMCODE>")
//                            .append("<ITEMCONTENT>邮包一切险保险条款</ITEMCONTENT>")
//                            .append("<CURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</CURRENCYCODE>")
//                            .append("<PRICECOND>").append(CustomerDefine.PRICECOND).append("</PRICECOND>")
//                            .append("<AMOUNT>").append(bookingForm.getGoodsValue()).append("</AMOUNT>")
//                            .append("<RATE>").append(CustomerDefine.RATE).append("</RATE>")
//                            .append("<PREMIUM>").append(bookingForm.getPremiumValue()).append("</PREMIUM>")
//                            .append("<POLICYNUM>1</POLICYNUM>")
//                            .append("<CLAIMCURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</CLAIMCURRENCYCODE>")
//                            .append("<CLAIMPAYPLACE>China</CLAIMPAYPLACE>")
//                            .append("<FCURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</FCURRENCYCODE>")
//                            .append("<EFFECTDATE>").append(sdf.format(now)).append("</EFFECTDATE>")
//                            .append("<SAILDATE>").append(sdf.format(now)).append("</SAILDATE>")
//                            .append("<FRANCHISE>本保单其他承保条件同协议；绝对免赔率</FRANCHISE>")
//                            .append("<USERNO>").append(bookingForm.getBusiBookNo()).append("</USERNO>")
//                            .append("<IFBILL>").append(CustomerDefine.NOTNEEDBILL).append("</IFBILL>")
//                            .append("<PRINTTYPE>").append(CustomerDefine.PRINTTYPE).append("</PRINTTYPE>")
//                            .append("</DATA>")
//                            .append("</DATAS>")
//                            .append("</FREIGHTCPIC>");
//                } else {
//                    sb.append("<?xml version ='1.0' encoding='utf-8'?>").append("<FREIGHTCPIC>")
//                            .append("<HEADER> ")
//                            .append("<ApplyId>").append(bookingForm.getBusiBookNo()).append("</ApplyId>")
//                            .append("<CLASSESTYPE>").append(CustomerDefine.CLASSESTYPE).append("</CLASSESTYPE>")
//                            .append("</HEADER>")
//                            .append("<DATAS>")
//                            .append("<DATA>")
//                            .append("<APPLYNAME>").append(null != comUserinfo ? comUserinfo.getRealName() : comCustomer.getCustName()).append("</APPLYNAME>")
//                            .append("<APPLYCARTTYPE>").append(null != comUserinfo ? "1170010001" : "2170010001").append("</APPLYCARTTYPE>")
//                            .append("<APPLYCARDCODE>").append(null != comUserinfo ? comUserinfo.getIdentno() : comCustomer.getOrgCodeLilcenseNo()).append("</APPLYCARDCODE>")
//                            .append("<INSURANTNAME>").append(bookingForm.getShipCustlinkMan()).append("</INSURANTNAME>")
//                            .append("<INSURANTTEL>").append(bookingForm.getShipCustlinkTel()).append("</INSURANTTEL>")
//                            .append("<CLASSTYPE>").append(CustomerDefine.CLASSTYPE).append("</CLASSTYPE>")
//                            .append("<MARK>").append(bookingForm.getBusiBookNo()).append("</MARK>")
//                            .append("<QUANTITY>").append(getQuantity(req)).append("</QUANTITY>")
//                            .append("<ITEM>").append(getItem(req)).append("</ITEM>")
//                            .append("<PACKCODE>").append(InsuranceUnit.getInsuranceCode(req.getGoodsInfos().get(0).getQtyUnitCode())).append("</PACKCODE>")
//                            .append("<ITEMCODE>").append(getItemCode(req)).append("</ITEMCODE>")
//                            .append("<KIND>").append(CustomerDefine.HIGHWAY).append("</KIND>")
//                            .append("<KINDNAME>").append(CustomerDefine.HIGHWAY_TOOL).append("</KINDNAME>")
//                            .append("<STARTPORT>").append(getCity(bookingForm.getCarriageReceiCounty() == null ? bookingForm.getCarriageReceiCity() : bookingForm.getCarriageReceiCounty())).append("</STARTPORT>")
//                            .append("<ENDPORT>").append(getCity(bookingForm.getCarriageDelivCounty() == null ? bookingForm.getCarriageDelivCity() : bookingForm.getCarriageDelivCounty())).append("</ENDPORT>")
//                            .append("<MAINITEMCODE>").append(CustomerDefine.MAINITEMCODE_YS).append("</MAINITEMCODE>")
//                            .append("<ITEMCONTENT>公路货物运输保险条款</ITEMCONTENT>")
//                            .append("<CURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</CURRENCYCODE>")
//                            .append("<PRICECOND>").append(CustomerDefine.PRICECOND).append("</PRICECOND>")
//                            .append("<AMOUNT>").append(bookingForm.getGoodsValue()).append("</AMOUNT>")
//                            .append("<RATE>").append(CustomerDefine.RATE).append("</RATE>")
//                            .append("<PREMIUM>").append(bookingForm.getPremiumValue()).append("</PREMIUM>")
//                            .append("<POLICYNUM>1</POLICYNUM>")
//                            .append("<CLAIMCURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</CLAIMCURRENCYCODE>")
//                            .append("<CLAIMPAYPLACE>China</CLAIMPAYPLACE>")
//                            .append("<FCURRENCYCODE>").append(CustomerDefine.CURRENCYCODE).append("</FCURRENCYCODE>")
//                            .append("<EFFECTDATE>").append(sdf.format(now)).append("</EFFECTDATE>")
//                            .append("<SAILDATE>").append(sdf.format(now)).append("</SAILDATE>")
//                            .append("<FRANCHISE>本保单其他承保条件同协议；绝对免赔率</FRANCHISE>")
//                            .append("<USERNO>").append(bookingForm.getBusiBookNo()).append("</USERNO>")
//                            .append("<IFBILL>").append(CustomerDefine.NOTNEEDBILL).append("</IFBILL>")
//                            .append("<PRINTTYPE>").append(CustomerDefine.PRINTTYPE).append("</PRINTTYPE>")
//                            .append("</DATA>")
//                            .append("</DATAS>")
//                            .append("</FREIGHTCPIC>");
//                }
//                placeAnOrderRes.setApplyName(null != comUserinfo ? comUserinfo.getRealName() : comCustomer.getCustName());
//                placeAnOrderRes.setApplyCode(null != comUserinfo ? comUserinfo.getIdentno() : comCustomer.getOrgCodeLilcenseNo());
//                placeAnOrderRes.setGoodValue(bookingForm.getGoodsValue() == null ? "" : bookingForm.getGoodsValue().toString());
//                placeAnOrderRes.setInsuranceName(bookingForm.getCneeCustLinkMan());
//                placeAnOrderRes.setInsuranceTel(bookingForm.getCneeCustLinkTel());
//                placeAnOrderRes.setApplyName(bookingForm.getShipCustlinkMan());//投保人姓名
//                placeAnOrderRes.setApplyCode(req.getApplyCode());//投保人身份证
//                placeAnOrderRes.setInsuranceName(bookingForm.getCneeCustLinkMan());//被保人姓名
//                placeAnOrderRes.setInsuranceTel(bookingForm.getCneeCustLinkTel());//被保人联系方式
//                approvalRequest.setPolicyInfo(sb.toString());
//                logger.info("approvalRequest={}", JSONObject.toJSONString(approvalRequest));
//                reposne = iZrxCommonService.approval(approvalRequest);
//                SysMessage sysMessage = reposne.getSysMessage();
//                if (sysMessage != null) {
//                    logger.info("错误类型:" + sysMessage.getErrorType() + "");
//                    logger.info("错误代码:" + sysMessage.getErrorCode() + "");
//                    logger.info("错误信息:" + sysMessage.getErrorMsg() + "");
//                    if (!StringUtil.isEmpty(sysMessage.getErrorCode())) {
//                        throw new MobileStationBizException(sysMessage.getErrorMsg());
//                    }
//                }
//                if (null != reposne.getPolicyInfo()) {
//                    Document document = DocumentHelper.parseText(reposne.getPolicyInfo());
//                    Element root = document.getRootElement();
//                    Element result = null;
//                    Iterator e = null;
//                    for (e = root.element("RESULT").elementIterator(); e.hasNext(); ) {
//
//                        result = (Element) e.next();
//                        if (result.getName().equals("UNITCODE")) {//投保分公司
//                            placeAnOrderRes.setUnitCode(result.getTextTrim());
//                            bookingForm.setUnitcode(result.getTextTrim());
//                        }
//                        if (result.getName().equals("APPLYNO")) {//投保单号
//                            placeAnOrderRes.setApplyNo(result.getTextTrim());
//                            bookingForm.setApplyno(result.getTextTrim());
//                        }
//                        if (result.getName().equals("POLICYNO")) {//保单号
//                            placeAnOrderRes.setPolicyNo(result.getTextTrim());
//                            bookingForm.setPolicyno(result.getTextTrim());
//                        }
//                        if (result.getName().equals("STATUS")) {//保单状态
//                            placeAnOrderRes.setStatus(result.getTextTrim());
//                            bookingForm.setPremiumStatus(Integer.parseInt(result.getTextTrim()));
//                        }
//                        if (result.getName().equals("PREMIUM")) {
//                            placeAnOrderRes.setPremium(new BigDecimal(result.getTextTrim()));
//
//                        }
//                        if (result.getName().equals("RMB_PREMIUM")) {//保险费用
//                            placeAnOrderRes.setRmbPremium(new BigDecimal(result.getTextTrim()));
//                            bookingForm.setPremiumValue(new BigDecimal(result.getTextTrim()));
//                        }
//                    }
//
////                    logger.info("应答报文: \r" + reposne.getPolicyInfo() + "");
//                }
//            } catch (WssException_Exception e) {
//                e.printStackTrace();
//                // throw new CustomerBizException("调用太保接口失败!");
//            } catch (DocumentException e) {
//                e.printStackTrace();
//                // throw new CustomerBizException("调用太保接口失败!");
//            }
//            if (req.getInsured()) {
//                bookingFormDao.updateByPrimaryKey(bookingForm);
//            }
//        }

        ComWaybillTrace tmp = new ComWaybillTrace();
        // 下单给mobilestation
        if (null != req.getOrderRecType() && 2 == req.getOrderRecType()) {//若下单对象是快递员
            MobileBookingForm mobileBookingForm = new MobileBookingForm();
            mobileBookingForm.setShipCustProvide(bookingForm.getCarriageReceiProvince());
            mobileBookingForm.setShipCustCity(bookingForm.getCarriageReceiCity());
            mobileBookingForm.setShipCustCounty(bookingForm.getCarriageReceiCounty());
            mobileBookingForm.setShipCustAddr(bookingForm.getCarriageReceiAddr());
            mobileBookingForm.setShipCustLinkMan(bookingForm.getShipCustlinkMan());
            mobileBookingForm.setShipCustLinkTel(bookingForm.getShipCustlinkTel());
            mobileBookingForm.setShipLatitude(bookingForm.getCarriageReceiLatitude());
            mobileBookingForm.setShipLongitude(bookingForm.getCarriageReceiLongitude());

            mobileBookingForm.setCneeCustProvide(bookingForm.getCarriageDelivProvince());
            mobileBookingForm.setCneeCustCity(bookingForm.getCarriageDelivCity());
            mobileBookingForm.setCneeCustCounty(bookingForm.getCarriageDelivCounty());
            mobileBookingForm.setCneeCustAddr(bookingForm.getCarriageDelivAddr());
            mobileBookingForm.setCneeCustLinkMan(bookingForm.getCneeCustLinkMan());
            mobileBookingForm.setCneeCustLinkTel(bookingForm.getCneeCustLinkTel());
            mobileBookingForm.setCneeLatitude(bookingForm.getCarriageDelivLatitude());
            mobileBookingForm.setCneeLongitude(bookingForm.getCarriageDelivLongitude());

            mobileBookingForm.setProductType(req.getItemCode());
            mobileBookingForm.setBookingFormId(bookingForm.getId());
            mobileBookingForm.setCreateUserId(bookingForm.getCreateUserId());
            mobileBookingForm.setCreateUser(req.getAcctUsername());
            mobileBookingForm.setCreateCompanyId(req.getCompanyAccountId());
            mobileBookingForm.setBusiBookNo(bookingForm.getBusiBookNo());

            if (null != req.getGoodsValue()) {
                mobileBookingForm.setGoodsValue(BigDecimal.valueOf(req.getGoodsValue()));
            }
            mobileBookingForm.setBookingDate(bookingForm.getBookingDate());
            mobileBookingForm.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL);
            mobileBookingForm.setTransportType(bookingForm.getOrderType());
            mobileBookingForm.setRevUserId(req.getCourierId());
            mobileBookingForm.setRevUser(req.getCourierAcctUsername());
            mobileBookingForm.setRevCompanyId(req.getCompanyAccountId());
            mobileBookingForm.setPayType(req.getPaymentType());
            if (req.getPaymentType() == 1) {
                mobileBookingForm.setDestPayment(req.getGoodsPayment());
                mobileBookingForm.setGoodsPaymentCurr(req.getGoodsPaymentCurr());
            } else {
                mobileBookingForm.setGoodsPayment(req.getGoodsPayment());
                mobileBookingForm.setGoodsPaymentCurr(req.getGoodsPaymentCurr());
            }
            if (null != req.getAccessMethod()) {
                mobileBookingForm.setOrderType(req.getAccessMethod());
            }

            if (req.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                //米站自理下单，i单状态改为已接单add by yujie20170329
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
            }
            if (req.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                //快递员自理下单，i单状态改为已接单add by yujie20170329
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
            }

            mobileBookingForm.setNeedInsure(req.getInsured());

            mobileBookingForm.setCreateDate(new Date());
            mobileBookingForm.setIsJs(0);
            mobileBookingForm.setPremiumValue(bookingForm.getPremiumValue());

            List<MobileGoodsDtl> recordList = new ArrayList<>();
            for (GoodsInfo goodsInfoReq : req.getGoodsInfos()) {
                MobileGoodsDtl mobileGoodsDtl = new MobileGoodsDtl();
                // mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
                mobileGoodsDtl.setCreateDate(new Date());
                mobileGoodsDtl.setCreateUser(mobileBookingForm.getCreateUser().toString());
                if (goodsInfoReq.getHeight() != null) {
                    mobileGoodsDtl.setGoodsHeight(BigDecimal.valueOf(goodsInfoReq.getHeight()));
                }
                if (goodsInfoReq.getLength() != null) {
                    mobileGoodsDtl.setGoodsLenght(BigDecimal.valueOf(goodsInfoReq.getLength()));
                }
                if (goodsInfoReq.getWidth() != null) {
                    mobileGoodsDtl.setGoodsWide(BigDecimal.valueOf(goodsInfoReq.getWidth()));
                }
                mobileGoodsDtl.setGoodsName(goodsInfoReq.getGoodsName());
                mobileGoodsDtl.setGoodsQty(BigDecimal.valueOf(goodsInfoReq.getQty()));
                mobileGoodsDtl.setGoodsType(goodsInfoReq.getGoodsTypeCode());
                mobileGoodsDtl.setGoodsQtyUnit(goodsInfoReq.getQtyUnitCode());
                mobileGoodsDtl.setGoodsWeight(BigDecimal.valueOf(goodsInfoReq.getWeight()));
                String weightUnitCo = "035";
                if ("kg".equals(goodsInfoReq.getWeightUnit())) {
                    weightUnitCo = "035";
                } else if ("g".equals(goodsInfoReq.getWeightUnit())) {
                    weightUnitCo = "036";
                }
                mobileGoodsDtl.setGoodsWeightUnit(weightUnitCo);
                if (goodsInfoReq.getHeight() != null && goodsInfoReq.getLength() != null && goodsInfoReq.getWidth() != null) {
                    mobileGoodsDtl.setGoodsVolume(BigDecimal.valueOf(
                            goodsInfoReq.getHeight() * goodsInfoReq.getLength() * goodsInfoReq.getWidth())
                            .setScale(0, BigDecimal.ROUND_HALF_UP));
                    mobileGoodsDtl.setGoodsVolumeUnit("164");
                }

                recordList.add(mobileGoodsDtl);
            }

            if (req.getRoleId() != null) {
                mobileBookingForm.setRoleId(req.getRoleId());
            } else {
                if (req.getTransType() == 1) {//运输
                    mobileBookingForm.setRoleId(3);//司机
                } else if (req.getTransType() == 2) {//快递
                    mobileBookingForm.setRoleId(7);//快递员
                } else {
                    throw new MobileStationBizException("填写正确的下单类型：transType 1运输 2快递");
                }
            }
            //取件时间
            mobileBookingForm.setEtaPopDate(new Date());
            mobileBookingForm.setRevDate(new Date());

            // 数据备份mobilestation
            MobileStationOrderResult recordMoblieStationOrder = recordMoblieStationOrder(mobileBookingForm,
                    recordList);
            if (recordMoblieStationOrder.getRetCode() == SystemDefine.FAILURE) {
                throw new MobileStationBizException("下单数据备份mobilestation失败");
            }

            MobileBookingForm mobileBookingForm1 = mobileBookingFormDaoEx.selectByConditions(
                    mobileBookingForm.getBusiBookNo(), mobileBookingForm.getRevUser(), mobileBookingForm.getRevCompanyId(), mobileBookingForm.getBusiCtrl(), null);
            placeAnOrderRes.setMobileBookFormId(mobileBookingForm1.getId());

            //若不走保险，则用户默认取走物件，走库存
            if (!req.getInsured()) {
                stockInOperate(bookingForm.getBusiBookNo(), req.getAccountId());

            }
            //是咪站操作则做结算
            CalcForPriceReq calcForPriceReq = new CalcForPriceReq();
            MobileBookingForm data = recordMoblieStationOrder.getData();
            //NJKD 为平台报价结算（平台报价）
            calcForPriceReq.setSystemFlag("1b4d92f79abb44c297850f6f1da2c959");
            calcForPriceReq.setOrderId(mobileBookingForm1.getId());
            calcForPriceReq.setBusiBookNo(data.getBusiBookNo());
            calcForPriceReq.setComQuoteId(req.getQuoteNo());
            calcForPriceReq.setgFUserFromCode(sysGFCode);
            calcForPriceReq.setgFUserToCode(req.getAcctUsername());
            if (req.getGoodsInfos().get(0).getLength() != null && req.getGoodsInfos().get(0).getHeight() != null && req.getGoodsInfos().get(0).getHeight() != null) {
                calcForPriceReq.setVolume(BigDecimal.valueOf(req.getGoodsInfos().get(0).getLength() * req.getGoodsInfos().get(0).getHeight() * req.getGoodsInfos().get(0).getHeight()).setScale(4, BigDecimal.ROUND_HALF_UP));
            }
            calcForPriceReq.setWeight(BigDecimal.valueOf(req.getGoodsInfos().get(0).getWeight()).setScale(4, BigDecimal.ROUND_HALF_UP));
            calcForPriceReq.setWeightUnit(req.getGoodsInfos().get(0).getWeightUnit());
            calcForPriceReq.setComQuoteId(req.getQuoteNo());
            calcForPriceReq.setMileage(req.getMileage());
            agencyPlaceOrderRes.setCalcForPriceReq(calcForPriceReq);
        }
        tmp.setAcctUsername(req.getAcctUsername());
        tmp.setRealName(req.getAcctUsername());
        tmp.setBusiBookNo(bookingForm.getBusiBookNo());
        tmp.setGrade(0);
        tmp.setRemark(WayBillStatusDefine.ORDER.getName());
        tmp.setExecCode(WayBillStatusDefine.ORDER.getIntValue());
        tmp.setStaDate(new Date());
        tmp.setRoleId(1);
        comWaybillTraceDao.insert(tmp);

        placeAnOrderRes.setBusiBookNo(bookingForm.getBusiBookNo());
        placeAnOrderRes.setInsured(req.getInsured());
        placeAnOrderRes.setOrderId(bookingForm.getId());
        placeAnOrderRes.setProductType(bookingForm.getTransportType());
        agencyPlaceOrderRes.setData(placeAnOrderRes);
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(bookingForm.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(bookingForm.getTransportType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_PopOrdered);
        giOrderTraceResynced.setIsToAccept(false);
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
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);

        giOrderTraceResynced.setAction(MobileStationDefine.Action_AcceptOrder);
        giOrderTraceResynced.setTsAct(new Date());
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced, 3000);

        if (req.getRoleId() != null && req.getRoleId() == 23) {

            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            giOrderTraceResynced.setAction(MobileStationDefine.Action_StationReceived);
            giOrderTraceResynced.setTsAct(new Date());
            giOrderTraceResynced.setUserCodeFrom(bookingForm.getCreateUser());
            ComUserinfo comUserinfo = comUserinfoDao.queryByAcctId(bookingForm.getCreateUserId());
            if (!StringUtil.isEmpty(comUserinfo.getProvince())
                    && comProvinceMap.get(comUserinfo.getProvince()) != null) {
                giOrderTraceResynced.setProvinceFrom(comProvinceMap.get(comUserinfo.getProvince()).getProvinceName());
            }
            if (!StringUtil.isEmpty(comUserinfo.getCity())
                    && comCityMap.get(comUserinfo.getCity()) != null) {
                giOrderTraceResynced.setCityFrom(comCityMap.get(comUserinfo.getCity()).getName());
            }
            if (!StringUtil.isEmpty(comUserinfo.getCounty())
                    && comCountyMap.get(comUserinfo.getCounty()) != null) {
                giOrderTraceResynced.setDistrictFrom(comCountyMap.get(comUserinfo.getCounty()).getAreaName());
            }
            giOrderTraceResynced.setAddressFrom(bookingForm.getShipCustaDdr());
            GiPoint pointFrom = new GiPoint();
            pointFrom.setLatitude(comUserinfo.getStaLatitude().doubleValue());
            pointFrom.setLongitude(comUserinfo.getStaLatitude().doubleValue());
            giOrderTraceResynced.setPointFrom(pointFrom);
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced, 6000);
        } else {
            giOrderTraceResynced.setAction(MobileStationDefine.Action_PickupOrder);
            giOrderTraceResynced.setTsAct(new Date());
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced, 6000);

            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            giOrderTraceResynced.setAction(MobileStationDefine.Action_TransportStart);
            giOrderTraceResynced.setTsAct(new Date());
            giOrderTraceResynced.setUserCodeFrom(bookingForm.getCreateUser());
            GiPoint pointFrom = new GiPoint();
            if (req.getSenderAddrId() != null) {
                AddressQueryRes addressQueryRes = customerMobileAddressInfoDao
                        .queryByPrimaryKey(req.getSenderAddrId());
                if (null != addressQueryRes) {
                    if (!StringUtil.isEmpty(addressQueryRes.getProvince())
                            && comProvinceMap.get(addressQueryRes.getProvince()) != null) {
                        giOrderTraceResynced.setProvinceFrom(comProvinceMap.get(addressQueryRes.getProvince()).getProvinceName());
                    }
                    if (!StringUtil.isEmpty(addressQueryRes.getCity())
                            && comCityMap.get(addressQueryRes.getCity()) != null) {
                        giOrderTraceResynced.setCityFrom(comCityMap.get(addressQueryRes.getCity()).getName());
                    }
                    if (!StringUtil.isEmpty(addressQueryRes.getCounty())
                            && comCountyMap.get(addressQueryRes.getCounty()) != null) {
                        giOrderTraceResynced.setDistrictFrom(comCountyMap.get(addressQueryRes.getCounty()).getAreaName());
                    }
                    pointFrom.setLatitude(addressQueryRes.getAddressLatitude().doubleValue());
                    pointFrom.setLongitude(addressQueryRes.getAddressLongitude().doubleValue());
                }
            } else {
                if (!StringUtil.isEmpty(req.getSenderAddr().getProvince())
                        && comProvinceMap.get(req.getSenderAddr().getProvince()) != null) {
                    giOrderTraceResynced.setProvinceFrom(comProvinceMap.get(req.getSenderAddr().getProvince()).getProvinceName());
                }
                if (!StringUtil.isEmpty(req.getSenderAddr().getCity())
                        && comCityMap.get(req.getSenderAddr().getCity()) != null) {
                    giOrderTraceResynced.setCityFrom(comCityMap.get(req.getSenderAddr().getCity()).getName());
                }
                if (!StringUtil.isEmpty(req.getSenderAddr().getCounty())
                        && comCountyMap.get(req.getSenderAddr().getCounty()) != null) {
                    giOrderTraceResynced.setDistrictFrom(comCountyMap.get(req.getSenderAddr().getCounty()).getAreaName());
                }
                pointFrom.setLatitude(req.getSenderAddr().getAddressLatitude().doubleValue());
                pointFrom.setLongitude(req.getSenderAddr().getAddressLongitude().doubleValue());
            }

            giOrderTraceResynced.setAddressFrom(bookingForm.getShipCustaDdr());
            giOrderTraceResynced.setPointFrom(pointFrom);
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced, 6000);

        }
        return agencyPlaceOrderRes;
    }

    @Override
    @Transactional
    public AppBaseResult agencyPlaceOrderAfter(AgencyPlaceOrderRes agencyPlaceOrderRes) {
        //不投保,是咪站操作则做结算 咪站、MS支付给平台
        CalcForPriceReq calcForPriceReq = agencyPlaceOrderRes.getCalcForPriceReq();
        CalcForPlatPriceResult calcForPlatPriceResult = calcService.calcForPriceForOut(calcForPriceReq);

        //咪站、MS和平台之间结算 平台支付给咪站、MS
        CalcForPlatPriceReq calcForPlatPriceReq = new CalcForPlatPriceReq();
        calcForPlatPriceReq.setSystemFlag("NJKD");
        calcForPlatPriceReq.setOrderId(calcForPriceReq.getOrderId());
        calcForPlatPriceReq.setBusiBookNo(calcForPriceReq.getBusiBookNo());
        calcForPlatPriceReq.setgFUserFromCode(calcForPriceReq.getgFUserToCode());
        calcForPlatPriceReq.setgFUserToCode(sysGFCode);
        if (agencyPlaceOrderRes.getData().getRoleId() != null && agencyPlaceOrderRes.getData().getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
            calcForPlatPriceReq.setCalcObjectType(2);
        } else {
            calcForPlatPriceReq.setCalcObjectType(1);
        }


        AppBaseResult result = new AppBaseResult();
        if (calcForPlatPriceResult == null || calcForPlatPriceResult.getRetCode() != SystemDefine.SUCCESS) {
            //结算失败删除之前自理下单的两个订单
            bookingFormDaoEx.deleteByBusiNo(calcForPriceReq.getBusiBookNo(), CustomerDefine.ORDER_STATUS_SENDIN);
            mobileBookingFormDaoEx.deleteByBusiNo(calcForPriceReq.getBusiBookNo(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
            result.setRetCode(SysResult.FAILED.getValue());
            result.setReqId(agencyPlaceOrderRes.getReqId());
            if (calcForPlatPriceResult.getRetCode() != SystemDefine.SUCCESS) {
                result.setRetMsg(calcForPlatPriceResult.getRetMsg());
            } else {
                result.setRetMsg("自理下单结算失败，请重新下单");
            }
        } else {
            result.setRetCode(SysResult.SUCCESS.getValue());
            result.setReqId(agencyPlaceOrderRes.getReqId());
            result.setRetMsg("自理下单成功");
        }
        return result;
    }

    private String getCity(String cityCode) {
        StringBuilder routeFullName = new StringBuilder();
        //区县
        ComCounty tCounty = comCountyService.queryForMap().get(cityCode);
        if (null != tCounty) {
            routeFullName.append(tCounty.getAreaName());
            cityCode = tCounty.getCityId().toString();
        }
        ComCity tCtiy = comCityService.queryForMap().get(cityCode);
        if (null != tCtiy) {
            routeFullName.insert(0, tCtiy.getName() + " ");
            cityCode = tCtiy.getProvinceId().toString();
        }
        ComProvince comProvince = comProvinceService.queryForMap().get(cityCode);
        if (null != comProvince) {
            routeFullName.insert(0, comProvince.getProvinceName() + " ");
        }
        return routeFullName.toString();
    }

    private String getItem(PlaceAnOrderReq req) {
        if (req == null && req.getGoodsInfos() == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (GoodsInfo goodsInfos : req.getGoodsInfos()) {
            sb.append(goodsInfos.getGoodsName() + " ");
        }
        return sb.toString();
    }

    private String getQuantity(PlaceAnOrderReq req) {
        if (req == null && req.getGoodsInfos() == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (GoodsInfo goodsInfos : req.getGoodsInfos()) {
            ComUnit comUnit = comUnitService.getComUnitByCode(goodsInfos.getQtyUnitCode());
            sb.append(goodsInfos.getQty()).append(comUnit.getUnitCh() != null ? comUnit.getUnitCh() : "").append(" ");
        }
        return sb.toString();
    }

    private String getItemCode(PlaceAnOrderReq req) {
        if (req == null && req.getGoodsInfos() == null) {
            return "";
        }
        ComGoodsType comGoodsType = comGoodsTypeDaoEx.selectByTypeCode(req.getGoodsInfos().get(0).getGoodsTypeCode());
        if (comGoodsType != null) {
            return comGoodsType.getInsuranceCode();
        } else {
            return "";
        }
    }

    /**
     * 将订单与货物信息备份到mobilestation
     *
     * @param mobileBookingForm
     * @param recordList
     * @return
     * @throws MobileStationBizException
     * @title recordMoblieStationOrder
     * @author M.simple
     * @version 1.0
     * @datetime 2016年6月15日 下午4:35:20
     */
    private MobileStationOrderResult recordMoblieStationOrder(MobileBookingForm mobileBookingForm,
                                                              List<MobileGoodsDtl> recordList) throws MobileStationBizException {

        MobileStationOrderResult recordMobileStationOrder = new MobileStationOrderResult();

        /**
         * 组装请求mobilestation下单接口参数
         */
        RecordMobileStationOrderRequest recordMobileStationOrderRequest = convertRecordMobileStationOrderRequest(
                mobileBookingForm);
        recordMobileStationOrderRequest.setRecordOrderType(RecordOrderType.ORDER_O.getValue());

        // 组装接收人信息
        ArrayList<RevUserBean> revUserList = new ArrayList<RevUserBean>();
        RevUserBean revUserBean = new RevUserBean();
        revUserBean.setRevUserId(mobileBookingForm.getRevUserId());
        revUserBean.setRevUser(mobileBookingForm.getRevUser());
        revUserList.add(revUserBean);

        // 设置站点信息
        if (mobileBookingForm.getRoleId() == 23) {
            recordMobileStationOrderRequest.setStartLocus(MobileStationDefine.M);
        } else {
            recordMobileStationOrderRequest.setStartLocus(MobileStationDefine.POP);
        }
        recordMobileStationOrderRequest.setDestnLocus(MobileStationDefine.POD);

        recordMobileStationOrderRequest.setRevUserList(revUserList);
        // 赋值订单货物信息
        recordMobileStationOrderRequest.setMobileGoodDtlList(recordList);
        recordMobileStationOrderRequest.setRecordBrocast(false);
        // 设置状态为已接单
//		recordMobileStationOrderRequest.setBusiCtrl(OrderState.ORDER_ACCEPTED.getValue());
        // 调用mobilestation下单接口
        try {
            recordMobileStationOrder = mobileStationOrderService
                    .recordMobileStationOrder(recordMobileStationOrderRequest);
        } catch (MobileStationBizException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new MobileStationBizException("订单数据同步mobileStation失败");
        }

        return recordMobileStationOrder;
    }

    /**
     * 组装booking_form数据
     *
     * @param mobileBookingForm
     * @return
     * @throws MobileStationBizException
     * @title convertBookingForm
     * @author M.simple
     * @version 1.0
     * @datetime 2016年6月14日 上午11:20:02
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

    /**
     * 保存订仓订单表收发客户信息
     *
     * @param req
     * @param id
     */
    private void saveOrderCustomInfo(PlaceAnOrderReq req, Integer id) throws MobileStationBizException {

        Integer senderAddrId = req.getSenderAddrId();
        AddressInfo senderAddr = req.getSenderAddr();
        OrderCustomInfo shipOrderCustomInfo = new OrderCustomInfo();
        if (senderAddrId != null && senderAddr == null) {
            AddressQueryRes addressQueryRes = customerMobileAddressInfoDao.queryByPrimaryKey(senderAddrId);
            if (addressQueryRes == null) {
                throw new MobileStationBizException("地址不存在");
            }
            shipOrderCustomInfo.setCustomName(addressQueryRes.getName());
            shipOrderCustomInfo
                    .setCustomAddr((null != addressQueryRes.getProvinceName() ? addressQueryRes.getProvinceName() : "")
                            + " " + (null != addressQueryRes.getCityName() ? addressQueryRes.getCityName() : "") + " "
                            + (null != addressQueryRes.getCountyName() ? addressQueryRes.getCountyName() : "") + " "
                            + addressQueryRes.getAddress());
            shipOrderCustomInfo.setCustomLinkMan(addressQueryRes.getName());
            shipOrderCustomInfo.setCustomLinkTel(addressQueryRes.getTelephone());
            if (null != addressQueryRes.getCity()) {
                shipOrderCustomInfo.setCustomCity(addressQueryRes.getCity());
            }
            if (null != addressQueryRes.getProvince()) {
                shipOrderCustomInfo.setCustomProvince(addressQueryRes.getProvince());
            }
            if (null != addressQueryRes.getCounty()) {
                shipOrderCustomInfo.setCustomCounty(addressQueryRes.getCounty());
            }
            if (null != addressQueryRes.getAddressLatitude()) {
                shipOrderCustomInfo.setCustomLatitude(addressQueryRes.getAddressLatitude());
            }
            if (null != addressQueryRes.getAddressLongitude()) {
                shipOrderCustomInfo.setCustomLongitude(addressQueryRes.getAddressLongitude());
            }

        } else {
            shipOrderCustomInfo.setCustomName(req.getSenderAddr().getName());
            shipOrderCustomInfo.setCustomAddr(req.getSenderAddr().getAddress());
            shipOrderCustomInfo.setCustomLinkMan(req.getSenderAddr().getName());
            shipOrderCustomInfo.setCustomLinkTel(req.getSenderAddr().getTelephone());
            if (null != req.getSenderAddr().getCity()) {
                shipOrderCustomInfo.setCustomCity(req.getSenderAddr().getCity());
            }
            if (null != req.getSenderAddr().getProvince()) {
                shipOrderCustomInfo.setCustomProvince(req.getSenderAddr().getProvince());
            }
            if (null != req.getSenderAddr().getCounty()) {
                shipOrderCustomInfo.setCustomCounty(req.getSenderAddr().getCounty());
            }
            if (null != req.getSenderAddr().getAddressLatitude()) {
                shipOrderCustomInfo.setCustomLatitude(req.getSenderAddr().getAddressLatitude());
            }
            if (null != req.getSenderAddr().getAddressLongitude()) {
                shipOrderCustomInfo.setCustomLongitude(req.getSenderAddr().getAddressLongitude());
            }
        }
        shipOrderCustomInfo.setCustomType(4);
        shipOrderCustomInfo.setCustomCategory(2);
        shipOrderCustomInfo.setCreateDate(new Date());
        shipOrderCustomInfo.setCreateUserId(req.getAccountId());
        shipOrderCustomInfo.setBookingFomrId(id);

        int result = orderCustomInfoDao.insert(shipOrderCustomInfo);

        if (result <= 0) {
            throw new MobileStationBizException("保存客户发货方信息失败");
        }

        Integer receiverAddrId = req.getReceiverAddrId();
        AddressInfo receiverAddr = req.getReceiverAddr();

        OrderCustomInfo cneeOrderCustomInfo = new OrderCustomInfo();

        if (receiverAddrId != null && receiverAddr == null) {
            AddressQueryRes addressQueryRes = customerMobileAddressInfoDao.queryByPrimaryKey(receiverAddrId);
            if (addressQueryRes == null) {
                throw new MobileStationBizException("地址不存在");
            }
            cneeOrderCustomInfo.setCustomName(addressQueryRes.getName());
            cneeOrderCustomInfo
                    .setCustomAddr((null != addressQueryRes.getProvinceName() ? addressQueryRes.getProvinceName() : "")
                            + " " + (null != addressQueryRes.getCityName() ? addressQueryRes.getCityName() : "") + " "
                            + (null != addressQueryRes.getCountyName() ? addressQueryRes.getCountyName() : "") + " "
                            + addressQueryRes.getAddress());
            cneeOrderCustomInfo.setCustomLinkMan(addressQueryRes.getName());
            cneeOrderCustomInfo.setCustomLinkTel(addressQueryRes.getTelephone());
            if (null != addressQueryRes.getCity()) {
                cneeOrderCustomInfo.setCustomCity(addressQueryRes.getCity());
            }
            if (null != addressQueryRes.getProvince()) {
                cneeOrderCustomInfo.setCustomProvince(addressQueryRes.getProvince());
            }
            if (null != addressQueryRes.getCounty()) {
                cneeOrderCustomInfo.setCustomCounty(addressQueryRes.getCounty());
            }
            if (null != addressQueryRes.getAddressLatitude()) {
                cneeOrderCustomInfo.setCustomLatitude(addressQueryRes.getAddressLatitude());
            }
            if (null != addressQueryRes.getAddressLongitude()) {
                cneeOrderCustomInfo.setCustomLongitude(addressQueryRes.getAddressLongitude());
            }
        } else {
            cneeOrderCustomInfo.setCustomName(req.getReceiverAddr().getName());
            cneeOrderCustomInfo.setCustomAddr(req.getReceiverAddr().getAddress());
            cneeOrderCustomInfo.setCustomLinkMan(req.getReceiverAddr().getName());
            cneeOrderCustomInfo.setCustomLinkTel(req.getReceiverAddr().getTelephone());
            if (null != req.getReceiverAddr().getCity()) {
                cneeOrderCustomInfo.setCustomCity(req.getReceiverAddr().getCity());
            }
            if (null != req.getReceiverAddr().getProvince()) {
                cneeOrderCustomInfo.setCustomProvince(req.getReceiverAddr().getProvince());
            }
            if (null != req.getReceiverAddr().getCounty()) {
                cneeOrderCustomInfo.setCustomCounty(req.getReceiverAddr().getCounty());
            }
            if (null != req.getReceiverAddr().getAddressLatitude()) {
                cneeOrderCustomInfo.setCustomLatitude(req.getReceiverAddr().getAddressLatitude());
            }
            if (null != req.getReceiverAddr().getAddressLongitude()) {
                cneeOrderCustomInfo.setCustomLongitude(req.getReceiverAddr().getAddressLongitude());
            }
        }
        cneeOrderCustomInfo.setCustomType(5);
        cneeOrderCustomInfo.setCustomCategory(2);
        cneeOrderCustomInfo.setCreateDate(new Date());
        cneeOrderCustomInfo.setCreateUserId(req.getAccountId());
        cneeOrderCustomInfo.setBookingFomrId(id);

        result = orderCustomInfoDao.insert(cneeOrderCustomInfo);
        if (result <= 0) {
            throw new MobileStationBizException("保存客户收货方信息失败");
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
            if (null != orderRecType && orderRecType == 1) {// 站点
                if (StringUtils.isBlank(req.getStationCode())) {
                    throw new MobileStationBizException("代理下单站点代码入参错误");
                }
            } else {// 快递员
                if (StringUtil.isEmpty(req.getCourierId())) {
                    throw new MobileStationBizException("代理下单快递员/司机代码入参错误");
                }
                if (StringUtil.isEmpty(req.getCourierAcctUsername())) {
                    throw new MobileStationBizException("代理下单快递员/司机账户入参错误");
                }
            }

            if (StringUtil.isEmpty(req.getAcctUsername())) {
                throw new MobileStationBizException("帐户名称为空");
            }

            if (StringUtil.isEmpty(req.getAccountId())) {
                throw new MobileStationBizException("帐户ID为空");
            }
        } else {
            if (StringUtils.isEmpty(req.getPredictCurr())) {
                throw new MobileStationBizException("广播单币制为空");
            }
        }

        if (null == req.getQuotedType()) {
            throw new MobileStationBizException("报价单类型为空");
        }

        if (req.getQuotedType() != 6) {// 非面议
            if (null == req.getComQuoteId()) {
                throw new MobileStationBizException("报价单ID为空");
            }
            if (StringUtils.isBlank(req.getQuoteNo())) {
                throw new MobileStationBizException("代理下单报价单为空");
            }
        }

        Integer transType = req.getTransType();
        if (transType == null || (transType != 1 && transType != 2)) {
            throw new MobileStationBizException("代理下单类型入参错误");
        }

        if (StringUtil.isEmpty(req.getItemCode())) {
            throw new MobileStationBizException("代理下单产品代码入参为空");
        }
        checkAddress(req);
        checkGoods(req);
        if (req.getInsured()) {
            if (null == req.getGoodsValue()) {
                throw new MobileStationBizException("使用货物保价时，货物价值入参为空");
            }
            if (null == req.getPremiumValue()) {
                throw new MobileStationBizException("使用货物保价时，保价费用入参为空");
            }
            // 保价费用为货物价值的万分之5,货物的价值不能大于100万
            Double goodsValue = req.getGoodsValue();
            if (goodsValue <= 0 || goodsValue > 100 * 10000) {
                throw new MobileStationBizException("货物价值必须介于0至100万元之间");
            }
        }
        Integer payType = req.getPaymentType();
        if (payType == null || (payType != 3 && payType != 2 && payType != 1)) {
            throw new MobileStationBizException("代理下单付款方式入参错误");
        }
        // 1-客户自送，2-上门接货
        Integer accessMethod = req.getAccessMethod();
        if (accessMethod == null || (accessMethod != 1 && accessMethod != 2)) {
            throw new MobileStationBizException("代理下单取件方式入参错误");
        }

        if (null != req.getGoodsPayment() && StringUtil.isEmpty(req.getGoodsPaymentCurr())) {
            throw new MobileStationBizException("代收货款币制为空");
        }

        if (null == req.getPredictValue()) {
            throw new MobileStationBizException("预估结算费用为空");
        }

    }

    private void checkAddressLongitudeAndLatitude(AddressInfo address) throws MobileStationBizException {
        if (address.getAddressLongitude() == null || address.getAddressLongitude().compareTo(new BigDecimal(0)) == 0) {
            throw new MobileStationBizException("经度不能为空");
        }
        if (address.getAddressLatitude() == null || address.getAddressLatitude().compareTo(new BigDecimal(0)) == 0) {
            throw new MobileStationBizException("纬度不能为空");
        }
    }

    /**
     * 校验货物信息
     *
     * @param req
     */
    private void checkGoods(PlaceAnOrderReq req) throws MobileStationBizException {
        List<GoodsInfo> goodsInfos = req.getGoodsInfos();
        if (goodsInfos == null || goodsInfos.size() == 0) {
            throw new MobileStationBizException("代理下单货物信息货物信息入参错误");
        }
        for (GoodsInfo goodsInfo : goodsInfos) {
            if (StringUtils.isEmpty(goodsInfo.getGoodsType())) {
                throw new MobileStationBizException("代理下单货物信息货物类型入参错误");
            }
            Integer qty = goodsInfo.getQty();
            if (qty == null || qty <= 0) {
                throw new MobileStationBizException("代理下单货物信息数量入参错误");
            }
            Double weight = goodsInfo.getWeight();
            if (weight == null || weight <= 0) {
                throw new MobileStationBizException("代理下单货物信息重量入参错误");
            }
            String weightUnit = goodsInfo.getWeightUnit();
            if (!"kg".contains(weightUnit)) {
                throw new MobileStationBizException("代理下单货物信息重量单位入参错误");
            }
        }
    }

    private void setSelctCityId(String cityId, AddressInfo addressInfo) {
        if (null != cityId) {

            ComCounty tCounty = comCountyService.queryForMap().get(cityId);
            if (null != tCounty) {
                addressInfo.setCounty(StringUtil.getObjStr(tCounty.getId()));
                addressInfo.setCity(StringUtil.getObjStr(tCounty.getCityId()));
                ComCity tCtiy = comCityService.queryForMap().get(tCounty.getCityId());
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
     * 代理下单地址校验
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
            if (StringUtils.isEmpty(req.getSenderAddr().getProvince())
                    || StringUtils.isEmpty(req.getSenderAddr().getCity())) {
                throw new MobileStationBizException("发件人地址入参错误");
            }
            // 校验经纬度
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
            setSelctCityId(senderAddr.getAreaIdSel() + "", req.getReceiverAddr());
            if (StringUtils.isEmpty(req.getReceiverAddr().getProvince())
                    || StringUtils.isEmpty(req.getReceiverAddr().getCity())) {
                throw new MobileStationBizException("收件人地址入参错误");
            }

//            checkAddressLongitudeAndLatitude(receiverAddr);
        }
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
            billingFormSalm.setHscodeNachs(goodsInfoReq.getGoodsName());
            billingFormSalm.setHscodeSpecs(goodsInfoReq.getGoodsTypeCode());
            billingFormSalm.setGoodsQty(BigDecimal.valueOf(goodsInfoReq.getQty()));
            billingFormSalm.setGoodsQtyUnitCo(goodsInfoReq.getQtyUnit());
            billingFormSalm.setGoodsGrosswht(BigDecimal.valueOf(goodsInfoReq.getWeight()));
            String weightUnitCo = "";
            if ("kg".equals(goodsInfoReq.getWeightUnit())) {
                weightUnitCo = "035";

            } else if ("g".equals(goodsInfoReq.getWeightUnit())) {
                weightUnitCo = "036";
            }
            billingFormSalm.setWeightUnitCo(weightUnitCo);
            // 体积存储
            billingFormSalm.setGoodsLength(goodsInfoReq.getLength());
            billingFormSalm.setGoodsWidth(goodsInfoReq.getWidth());
            billingFormSalm.setGoodsHeight(goodsInfoReq.getHeight());
            billingFormSalmDao.insertSelective(billingFormSalm);
        }
    }

    /**
     * 保存收货地址
     *
     * @param req
     * @param bookingForm
     */
    private void saveReceiverAddress(PlaceAnOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        Integer receiverAddrId = req.getReceiverAddrId();
        AddressInfo receiverAddr = req.getReceiverAddr();
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
            bookingForm.setCarriageDelivAddr((addressQueryRes.getProvinceName() == null ? "" : addressQueryRes.getProvinceName()) + (addressQueryRes.getCityName() == null ? "" : addressQueryRes.getCityName()) + (addressQueryRes.getCountyName() == null ? "" : addressQueryRes.getCountyName()) + (addressQueryRes.getAddress() == null ? "" : addressQueryRes.getAddress()) + (addressQueryRes.getDetailAddress() == null ? "" : addressQueryRes.getDetailAddress()));
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
            bookingForm.setCneeCustAddr(req.getReceiverAddr().getAddress() == null ? "" : req.getReceiverAddr().getAddress());
            bookingForm.setCneeCustHouseNumber(req.getReceiverAddr().getDetailAddress() == null ? "" : req.getReceiverAddr().getDetailAddress());
            bookingForm.setCneeCustLinkMan(req.getReceiverAddr().getName());
            bookingForm.setCneeCustLinkTel(req.getReceiverAddr().getTelephone());
            if (req.getReceiverAddrSaveFlag() != null && req.getReceiverAddrSaveFlag() == CustomerDefine.SAVE_ADDRES) {
                if (receiverAddr.getAccountId() == null) {
                    receiverAddr.setAccountId(req.getAccountId());
                }
                saveAddress(receiverAddr, 1);//1收货地址，2发货地址
            }
            bookingForm.setCarriageDelivAddr((req.getReceiverAddr().getAddress() == null ? "" : req.getReceiverAddr().getAddress()) + (req.getReceiverAddr().getDetailAddress() == null ? "" : req.getReceiverAddr().getDetailAddress()))
            ;
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
    }

    /**
     * 保存发货地址
     *
     * @param req
     * @param bookingForm
     */
    private void saveSenderAddress(PlaceAnOrderReq req, BookingForm bookingForm) throws MobileStationBizException {
        Integer senderAddrId = req.getSenderAddrId();
        AddressInfo senderAddr = req.getSenderAddr();
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

            bookingForm.setShipCustaDdr(req.getSenderAddr().getAddress() == null ? "" : req.getSenderAddr().getAddress());
            bookingForm.setShipCustHouseNumber(req.getSenderAddr().getDetailAddress() == null ? "" : req.getSenderAddr().getDetailAddress());
            bookingForm.setShipCustlinkMan(req.getSenderAddr().getName());
            bookingForm.setShipCustlinkTel(req.getSenderAddr().getTelephone());
            bookingForm.setCarriageReceiAddr((req.getSenderAddr().getAddress() == null ? "" : req.getSenderAddr().getAddress()) + (req.getSenderAddr().getDetailAddress() == null ? "" : req.getSenderAddr().getDetailAddress()));
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
    }

    /**
     * @param addressReq
     * @throws MobileStationBizException
     */
    private void saveAddress(AddressInfo addressReq, int addressType) throws MobileStationBizException {
        // 判断是否需要保存
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
        mobileAddressInfo.setStatus(1);
        mobileAddressInfo.setCreateTime(new Date());
        mobileAddressInfo.setAddressType(addressType);
        // 判断是否默认
        if (isDefaultAddr != null && isDefaultAddr) {
            mobileAddressInfo.setDefaultAddress(isDefaultAddr ? 1 : 0);
        }
        mobileAddressInfoDao.insert(mobileAddressInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppBaseResult paySuccessed(ReceiveCustomerOrderReq req) throws MobileStationBizException {
        AppBaseResult appBaseResult = new AppBaseResult(req);
        if (req != null && !StringUtil.isEmpty(req.getBookbusino())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBookbusino());
            bookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_SENDIN);
            bookingForm.setPremiumStatus(10);//保单生效
            bookingFormDao.updateByPrimaryKey(bookingForm);

            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(req.getMobileBookFormId());
            mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
            mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);

            //取件后，需要走库存
            stockInOperate(bookingForm.getBusiBookNo(), req.getAccountId());

        }
        return appBaseResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppBaseResult cancelInsuredContinueOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException {
        AppBaseResult appBaseResult = new AppBaseResult(req);
        if (req != null && !StringUtil.isEmpty(req.getBookbusino())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBookbusino());
            bookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
            bookingForm.setNeedInsure(false);
            bookingFormDao.updateByPrimaryKey(bookingForm);

            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(req.getMobileBookFormId());
            mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
            mobileBookingForm.setNeedInsure(false);
            mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);

            //取件后，需要走库存
            stockInOperate(bookingForm.getBusiBookNo(), req.getAccountId());

        }
        return appBaseResult;
    }

    //用户取件，则直接将商品入库
    private void stockInOperate(String busiNo, int accountId) throws MobileStationBizException {
        StockInOutReq stockInOutReq = new StockInOutReq();
        stockInOutReq.setGoodsQtyUnit("0");
        stockInOutReq.setStockType(0);//0:入库
        BigDecimal count = BigDecimal.ZERO;

        MobileBookingForm MobileBookingForm = mobileBookingFormDaoEx.selectByConditions(busiNo, null, null, null, null);
        if (MobileBookingForm != null) {
            List<MobileGoodsDtl> mobileGoodsDtls = mobileGoodsDtlDao.selectByMobileBookingFormId(MobileBookingForm.getId());
            for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtls) {
                if (stockInOutReq.getGoodsQtyUnit().equals("0")) {//第一次，设置货物单位
                    stockInOutReq.setGoodsQtyUnit(mobileGoodsDtl.getGoodsQtyUnit());
                } else if (!stockInOutReq.getGoodsQtyUnit().equals(mobileGoodsDtl.getGoodsQtyUnit())) {//若一个订单，多个货物单位不相同，则默认设置货物单位为箱，及code=120
                    stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                }

                count = count.add(mobileGoodsDtl.getGoodsQty());
            }

            if (count.equals(BigDecimal.ZERO)) {
                count = new BigDecimal(1);
            }

            //入库 插入出入库历史明细表
            MobileStockDetail mobileStockDetail = new MobileStockDetail();
            mobileStockDetail.setBusiBookNo(busiNo);
            mobileStockDetail.setCreateDate(new Date());
            mobileStockDetail.setCreateUser(accountId);
            mobileStockDetail.setGoodsQty(count);
            mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
            mobileStockDetail.setStockType(stockInOutReq.getStockType());
            mobileStockDetail.setOrderFrom(MobileBookingForm.getOrderFrom());
            mobileStockDetailDao.insert(mobileStockDetail);
            //插入当前库存表
            MobileStock mobileStock = new MobileStock();
            mobileStock.setAccountId(accountId);
            mobileStock.setOperDate(new Date());
            mobileStock.setBusiBookNo(busiNo);
            mobileStock.setGoodsQty(count);
            mobileStock.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
            mobileStock.setOrderFrom(MobileBookingForm.getOrderFrom());
            mobileStockDao.insert(mobileStock);
        }
    }

}