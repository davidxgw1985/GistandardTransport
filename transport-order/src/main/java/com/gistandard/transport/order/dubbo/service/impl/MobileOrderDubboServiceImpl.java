package com.gistandard.transport.order.dubbo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.app.dubbo.order.bean.*;
import com.gistandard.transport.app.dubbo.order.bean.QueryOrderListResult;
import com.gistandard.transport.app.dubbo.order.service.MobileOrderDubboService;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.app.dubbo.register.bean.RegResultBean;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeReq;
import com.gistandard.transport.app.dubbo.sms.service.SmsDubboService;
import com.gistandard.transport.app.dubbo.sys.bean.SysResult;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.dubbo.bean.QueryBusRegisterUserListBean;
import com.gistandard.transport.order.dubbo.bean.QueryUserOrderListBean;
import com.gistandard.transport.order.dubbo.dao.OrderCountDao;
import com.gistandard.transport.order.dubbo.dao.OrderDubboDao;
import com.gistandard.transport.order.module.customer.CustomerOrderService;
import com.gistandard.transport.order.module.customer.bean.*;
import com.gistandard.transport.order.module.customer.dao.CustomerBookingFormDao;
import com.gistandard.transport.order.module.mistation.accept.service.MiStationAcceptService;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.expressOrder.ExpreessBillingFormSalm;
import com.gistandard.transport.order.module.mobilestation.bean.expressOrder.ExpreessBookingForm;
import com.gistandard.transport.order.module.mobilestation.bean.expressOrder.ExpreessMobileBookingForm;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.AllGiBizOrderRes;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.MobileOrderDetailBean;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.MobileOrderInfo;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileOrderDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileAcceptOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileStationOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileUserOrderService;
import com.gistandard.transport.order.module.mobilestation.service.impl.BlockingQueueFactory;
import com.gistandard.transport.order.webservice.client.merchant.order.BaseRequestResult;
import com.gistandard.transport.order.webservice.client.merchant.order.Exception_Exception;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import com.gistandard.transport.order.webservice.client.order.hub.ExpreessOrderWebService;
import com.gistandard.transport.order.webservice.client.order.hub.QueryOrderDetailRequest;
import com.gistandard.transport.quote.system.database.services.ExpressService;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.system.utils.OrderUtil;
import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuote;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormDetailModel;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormInModel;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormOutModel;
import com.gistandard.transport.system.webservice.client.payinfo.QueryCalcManagerWebService;
import com.gistandard.transport.tools.util.DateUtil;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by m on 2016/11/4.
 */
public class MobileOrderDubboServiceImpl implements MobileOrderDubboService {
    private final Logger logger = LoggerFactory.getLogger(MobileOrderDubboServiceImpl.class);

    @Autowired
    private MobileMyOrderService mobileMyOrderService;
    @Autowired
    private MobileUserOrderService mobileUserOrderService;
    @Autowired
    private MobileStationOrderService mobileStationOrderService;
    @Autowired
    private PnWebService pnWebService;
    @Autowired
    private ComProvinceService comProvinceService;
    @Autowired
    private ComCityService comCityService;
    @Autowired
    private ComCountyService comCountyService;
    @Autowired
    private MobileOrderDao mobileOrderDao;
    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;
    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;
    @Autowired
    private ExpreessOrderWebService expreessOrderWebService;
    @Autowired
    private MobileAcceptOrderService mobileAcceptOrderService;
    @Autowired
    private MiStationAcceptService miStationAcceptService;
    @Autowired
    private SmsDubboService smsDubboService;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private ComCompanyStaffDaoEx comCompanyStaffDaoEx;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private OrderCountDao orderCountDao;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Autowired
    private CustomerBookingFormDao customerBookingFormDao;

    @Autowired
    private MobileGoodsDtlDao mobileGoodsDtlDao;

    @Autowired
    private BillingFormSalmDaoEx billingFormSalmDaoEx;
    @Autowired
    private GpsLogService gpsLogService;
    @Autowired
    private GpsOrderService gpsOrderService;
    @Autowired
    private OrderDubboDao orderDubboDao;
    @Autowired
    private BookingFormDao bookingFormDao;
    @Autowired
    private BookingFormErrDaoEx bookingFormErrDaoEx;
    @Autowired
    private QueryCalcManagerWebService queryCalcManagerWebService;
    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;
    @Autowired
    private ComAccountRoleRelDaoEx comAccountRoleRelDaoEx;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private OrderUtil orderUtil;
    @Autowired
    private ComCountyDaoEx comCountyDaoEx;

    @Value("#{spring.sysGFCode}")
    private String sysGFCode;   //平台公布价结算时的平台账号

    @Override
    public QueryMyOrderListResult queryMyOrderList(MobileMyOrderListReq myOrderListReq) throws Exception {
        logger.info("dubbo Service MobileOrderDubboService queryMyOrderList param:{}", JSON.toJSONString(myOrderListReq));
        QueryMyOrderListResult result = new QueryMyOrderListResult();
        MobileMyOrderListReq orderListReq =
                new MobileMyOrderListReq();
        BeanUtils.copyProperties(orderListReq, myOrderListReq);
        try {
            QueryMyOrderListResult queryMyOrderListResult = mobileMyOrderService.queryMyOrderList(orderListReq);
            BeanUtils.copyProperties(result, queryMyOrderListResult);
        } catch (MobileStationBizException e) {
            result.setRetCode(SysResult.FAILED.getValue());
            result.setRetMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public QueryMyOrderDetailResult queryMyOrderDetail(MobileMyOrderDetailReq myOrderDetailReq) throws Exception {
        logger.info("dubbo Service MobileOrderDubboService queryMyOrderDetail param:{}", JSON.toJSONString(myOrderDetailReq));
        QueryMyOrderDetailResult result = new QueryMyOrderDetailResult(myOrderDetailReq);
        MobileMyOrderDetailReq orderDetailReq = new MobileMyOrderDetailReq();
        BeanUtils.copyProperties(orderDetailReq, myOrderDetailReq);
        try {
            QueryMyOrderDetailResult queryMyOrderDetailResult =
                    mobileMyOrderService.queryMyOrderDetail(orderDetailReq);
            MobileStationOrderDetailBean data = new MobileStationOrderDetailBean();
            BeanUtils.copyProperties(data, queryMyOrderDetailResult.getData());
            result.setData(data);
            result.setRetCode(queryMyOrderDetailResult.getRetCode());
            result.setRetMsg(queryMyOrderDetailResult.getRetMsg());
        } catch (MobileStationBizException e) {
            result.setRetCode(SysResult.FAILED.getValue());
            result.setRetMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public UserOrderQueryListResult queryList(MobileUserOrderListReq mobileUserOrderListReq) throws Exception {
        logger.info("dubbo Service MobileOrderDubboService queryList param:{}", JSON.toJSONString(mobileUserOrderListReq));
        UserOrderQueryListResult result = new UserOrderQueryListResult(mobileUserOrderListReq);
        MobileUserOrderListReq userOrderListReq = new MobileUserOrderListReq();
        BeanUtils.copyProperties(userOrderListReq, mobileUserOrderListReq);
        try {
            UserOrderQueryListResult userOrderQueryListResult = mobileUserOrderService.queryListCustom(userOrderListReq);
            BeanUtils.copyProperties(result, userOrderQueryListResult);
        } catch (MobileStationBizException e) {
            result.setRetCode(SysResult.FAILED.getValue());
            result.setRetMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public UserOrderQueryDetailResult queryDetail(MobileUserOrderDetailReq mobileUserOrderDetailReq) throws Exception {
        logger.info("dubbo Service MobileOrderDubboService queryDetail param:{}", JSON.toJSONString(mobileUserOrderDetailReq));
        UserOrderQueryDetailResult result = new UserOrderQueryDetailResult(mobileUserOrderDetailReq);
        MobileUserOrderDetailReq userOrderDetailReq = new MobileUserOrderDetailReq();
        BeanUtils.copyProperties(userOrderDetailReq, mobileUserOrderDetailReq);
        try {
            UserOrderQueryDetailResult userOrderQueryDetailResult =
                    mobileUserOrderService.queryDetail(userOrderDetailReq);
            MobileUserOrderDetailBean data = new MobileUserOrderDetailBean();
            BeanUtils.copyProperties(data, userOrderQueryDetailResult.getData());
            result.setData(data);
            result.setRetCode(userOrderQueryDetailResult.getRetCode());
            result.setRetMsg(userOrderQueryDetailResult.getRetMsg());
        } catch (MobileStationBizException e) {
            result.setRetCode(SysResult.FAILED.getValue());
            result.setRetMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public MsDubboResBean updateStaffRole(UpdateStaffRoleReq updateStaffRoleReq) throws Exception {
        logger.info("更新角色模块 Service updateStaffRole param:{}", JSON.toJSONString(updateStaffRoleReq));
        AppBaseResult baseResult = mobileStationOrderService.updateStaffRole(updateStaffRoleReq);
        MsDubboResBean appBaseResult = new MsDubboResBean();
        BeanUtils.copyProperties(appBaseResult, baseResult);
        return appBaseResult;
    }

    @Override
    public MsDubboResBean removeStaff(RemoveStaffReq removeStaffReq) throws Exception {
        AppBaseResult baseResult = mobileStationOrderService.removeStaff(removeStaffReq);
        MsDubboResBean appBaseResult = new MsDubboResBean();
        BeanUtils.copyProperties(appBaseResult, baseResult);
        return appBaseResult;
    }

    /**
     * 商管中心 广播单列表查询
     * 获取广播单列表
     *
     * @param mobileAssignOrderListReq
     * @throws Exception
     */
    @Override
    public QueryAssignOrderListResult queryAssignOrderList(MobileAssignOrderListReq mobileAssignOrderListReq) throws Exception {
        logger.info("queryAssignOrderList----广播单列表查询---{}", mobileAssignOrderListReq.toString());
        QueryAssignOrderListResult baseResPageBean = new QueryAssignOrderListResult();
        if (0 == mobileAssignOrderListReq.getAssignStatue()) {
            if (mobileAssignOrderListReq.getStartDate() == null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(1900, 1, 1);
                mobileAssignOrderListReq.setStartDate(calendar.getTime());
            }
            if (mobileAssignOrderListReq.getEndDate() == null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(3000, 1, 1);
                mobileAssignOrderListReq.setEndDate(calendar.getTime());
            }
            String startProvince = "";
            String startCity = "";
            String startCounty = "";
            String destProvince = "";
            String destCity = "";
            String destCounty = "";
            //查询待指派列表
            if (mobileAssignOrderListReq.getStartCounty() != null) {
                //根据区获取区的名称
                Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
                Map<String, ComCity> comCityMap = comCityService.queryForMap();
                Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                if (comCountyMap.get(mobileAssignOrderListReq.getStartCounty().toString()) != null) {
                    startCounty = comCountyMap.get(mobileAssignOrderListReq.getStartCounty().toString()).getAreaName();
                    if (comCityMap.get(comCountyMap.get(mobileAssignOrderListReq.getStartCounty().toString()).getCityId().toString()) != null) {
                        startCity = comCityMap.get(comCountyMap.get(mobileAssignOrderListReq.getStartCounty().toString()).getCityId().toString()).getName();
                        if (comProvinceMap.get(comCityMap.get(comCountyMap.get(mobileAssignOrderListReq.getStartCounty().toString()).getCityId().toString()).getProvinceId().toString()) != null) {
                            startProvince = comProvinceMap.get(comCityMap.get(comCountyMap.get(mobileAssignOrderListReq.getStartCounty().toString()).getCityId().toString()).getProvinceId().toString()).getProvinceName();
                        }
                    }
                }
                if (comCountyMap.get(mobileAssignOrderListReq.getDestCounty().toString()) != null) {
                    destCounty = comCountyMap.get(mobileAssignOrderListReq.getDestCounty().toString()).getAreaName();
                    if (comCityMap.get(comCountyMap.get(mobileAssignOrderListReq.getDestCounty().toString()).getCityId().toString()) != null) {
                        destCity = comCityMap.get(comCountyMap.get(mobileAssignOrderListReq.getDestCounty().toString()).getCityId().toString()).getName();
                        if (comProvinceMap.get(comCityMap.get(comCountyMap.get(mobileAssignOrderListReq.getDestCounty().toString()).getCityId().toString()).getProvinceId().toString()) != null) {
                            destProvince = comProvinceMap.get(comCityMap.get(comCountyMap.get(mobileAssignOrderListReq.getDestCounty().toString()).getCityId().toString()).getProvinceId().toString()).getProvinceName();
                        }
                    }
                }
            }
            int pageNum = mobileAssignOrderListReq.getStartRecord() / mobileAssignOrderListReq.getPageSize();
            String roleCode = "";
            if (mobileAssignOrderListReq.getRoleId() != null) {
                roleCode = SysAccountRole.getRoleCode(mobileAssignOrderListReq.getRoleId());
            }
            //获取所有的待抢单，并按照距离进行排序
            try {
                logger.info("queryAssignOrderList----广播单列表查询-startProvince--{}--startCity--{}--startCounty--{}--destProvince--{}" +
                        "--destCity--{}--destCounty--{}--roleCode--{}--startDate--{}--endDate--{}--pageNum--{}--PageSize--{}"
                        , startProvince, startCity, startCounty, destProvince, destCity, destCounty, roleCode
                        , mobileAssignOrderListReq.getStartDate(), mobileAssignOrderListReq.getEndDate(), pageNum, mobileAssignOrderListReq.getPageSize());
                String gpsRes = pnWebService.getAllGiBizOrderAdhocPaging(startProvince, startCity, startCounty, destProvince, destCity, destCounty,
                        mobileAssignOrderListReq.getBusiBookNo(), roleCode, mobileAssignOrderListReq.getStartDate(), mobileAssignOrderListReq.getEndDate(), pageNum, mobileAssignOrderListReq.getPageSize());
//                logger.info("queryAssignOrderList----广播单列表查询返回---" + gpsRes);

                AllGiBizOrderRes allGiBizOrderRes = JSON.parseObject(gpsRes, AllGiBizOrderRes.class);
                List<GiBizOrder> orderListBeans = allGiBizOrderRes.getAllGiBizOrder();
                List<MobileAssignOrderListBean> assignOrderListBeans = new ArrayList<>();
                for (GiBizOrder giBizOrder : orderListBeans) {
                    MobileAssignOrderListBean assignOrderListBean = new MobileAssignOrderListBean();
                    assignOrderListBean.setBusiBookNo(giBizOrder.getDocNo());
                    assignOrderListBean.setAssignStatue(mobileAssignOrderListReq.getAssignStatue());
                    assignOrderListBean.setCreateDate(giBizOrder.getTsClientPushed());
                    assignOrderListBean.setOrderFrom(Integer.parseInt(giBizOrder.getDocFrom()));
                    assignOrderListBean.setStartAddress(giBizOrder.getSourceAddress());
                    assignOrderListBean.setDestAddress(giBizOrder.getDestAddress());
                    assignOrderListBean.setTransportType(2);
                    assignOrderListBean.setPushDate(giBizOrder.getTsClientPushed());
                    assignOrderListBean.setAllRoleCode(giBizOrder.getAllRoleCode());
                    assignOrderListBean.setStartLinkMan(giBizOrder.getSourceUserName());
                    assignOrderListBean.setStartTel(giBizOrder.getSourceUserTel());
                    assignOrderListBean.setDestLinkMan(giBizOrder.getDestUserName());
                    assignOrderListBean.setDestTel(giBizOrder.getDestUserTel());
                    if (giBizOrder.getPointSource() != null) {
                        assignOrderListBean.setStartLatitude(BigDecimal.valueOf(giBizOrder.getPointSource().getLatitude()));
                        assignOrderListBean.setStartLongitude(BigDecimal.valueOf(giBizOrder.getPointSource().getLongitude()));
                    }
                    if (giBizOrder.getPointDest() != null) {
                        assignOrderListBean.setDestLatitude(BigDecimal.valueOf(giBizOrder.getPointDest().getLatitude()));
                        assignOrderListBean.setDestLongitude(BigDecimal.valueOf(giBizOrder.getPointDest().getLongitude()));
                    }
                    assignOrderListBean.setProductType(giBizOrder.getProductType());
                    assignOrderListBean.setScheducarno(giBizOrder.getScheducarno());
                    assignOrderListBeans.add(assignOrderListBean);
                }
                //根据分页参数，返回订单信息
                baseResPageBean.setRecordCount(allGiBizOrderRes.getCount());
                baseResPageBean.setData(assignOrderListBeans);
            } catch (Exception e) {
                baseResPageBean.setRetCode(SystemDefine.FAILURE);
                baseResPageBean.setRetMsg(e.getMessage());
            }
        } else {
            //查询已指派列表
            int count = mobileOrderDao.getAssignOrderListCount(mobileAssignOrderListReq);
            List<MobileStationOrderListBean> orderListBeans = mobileOrderDao.queryAssignOrderList(mobileAssignOrderListReq);
            List<MobileAssignOrderListBean> assignOrderListBeans = new ArrayList<>();
            for (MobileStationOrderListBean listBean : orderListBeans) {
                MobileAssignOrderListBean assignOrderListBean = new MobileAssignOrderListBean();
                BeanUtils.copyProperties(assignOrderListBean, listBean);
                assignOrderListBean.setProductType(listBean.getProductType());
                assignOrderListBean.setAssignStatue(mobileAssignOrderListReq.getAssignStatue());
                assignOrderListBeans.add(assignOrderListBean);
            }
            baseResPageBean.setRecordCount(count);
            baseResPageBean.setData(assignOrderListBeans);
        }
        logger.info("queryAssignOrderList----广播单列表查询返回---{}", JSON.toJSONString(baseResPageBean));
        return baseResPageBean;
    }

    /**
     * 商管中心 广播单详细查询
     * 获取广播单详细信息
     *
     * @param mobileAssignOrderDetailReq
     * @throws Exception
     */
    @Override
    public QueryAssignOrderDetailResult queryAssignOrderDetail(MobileAssignOrderDetailReq mobileAssignOrderDetailReq) throws Exception {
        logger.info("queryAssignOrderDetail----广播单详细查询---{}", JSON.toJSONString(mobileAssignOrderDetailReq));
        QueryAssignOrderDetailResult queryAssignOrderDetailResult = new QueryAssignOrderDetailResult();
        //查询已指派的订单
        if (mobileAssignOrderDetailReq.getAssignStatue() == 1) {
            MobileOrderDetailBean mobileOrderDetailBean = mobileOrderDao.queryOrderDetail(mobileAssignOrderDetailReq.getOrderId());
            MobileAssignOrderDetailBean mobileAssignOrderDetailBean = new MobileAssignOrderDetailBean();
            BeanUtils.copyProperties(mobileAssignOrderDetailBean, mobileOrderDetailBean);
            //设置产品类型
            if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(mobileOrderDetailBean.getProductType())
                    || MobileStationDefine.PRODUCT_TYPE_ITCKD.equals(mobileOrderDetailBean.getProductType())) {
                mobileAssignOrderDetailBean.setProductTypeName("同城快递");
            } else {
                mobileAssignOrderDetailBean.setProductTypeName("同城运输");
            }
            //设置接单人姓名
            if (mobileOrderDetailBean.getRevUserId() != null) {
                ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(mobileOrderDetailBean.getRevUserId());
                if (comUserinfo != null) {
                    mobileAssignOrderDetailBean.setRevUserName(comUserinfo.getRealName());
                }
            }
            //设置报价单类型
            if (mobileOrderDetailBean.getQuotedType() != null) {
                mobileAssignOrderDetailBean.setQuotedTypeName("");
            }

            queryAssignOrderDetailResult.setData(mobileAssignOrderDetailBean);
        } else {
            //未指派单，判断订单的来源
            MobileAssignOrderDetailBean mobileAssignOrderDetailBean = new MobileAssignOrderDetailBean();
            if (mobileAssignOrderDetailReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST) {
                //用户广播单
                ReceiveCustomerOrderReq req = new ReceiveCustomerOrderReq();
                req.setBookbusino(mobileAssignOrderDetailReq.getBusiBookNo());
                req.setBrocast(true);
                logger.info("订单详细查询-个人广播单:{}", JSON.toJSONString(req));
                try {
                    OrderQueryRes res = customerOrderService.queryBroadcastOrder(req);
                    setDetailInfo(mobileAssignOrderDetailBean, res);
                    mobileAssignOrderDetailBean.setOrderFrom(mobileAssignOrderDetailReq.getOrderFrom());
                    //设置产品类型
                    mobileAssignOrderDetailBean.setProductTypeName("同城快递");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (mobileAssignOrderDetailReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST) {
                //MS广播单
                MobileStationOrderDetailBean detailBean = mobileMyOrderDao.queryOrderByBusiNoAndBusiCtrl(mobileAssignOrderDetailReq.getBusiBookNo(),
                        MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
                BeanUtils.copyProperties(mobileAssignOrderDetailBean, detailBean);
                //设置产品类型
                if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(detailBean.getProductType())
                        || MobileStationDefine.PRODUCT_TYPE_ITCKD.equals(detailBean.getProductType())) {
                    mobileAssignOrderDetailBean.setProductTypeName("同城快递");
                } else {
                    mobileAssignOrderDetailBean.setProductTypeName("同城运输");
                }
            } else if (mobileAssignOrderDetailReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST) {
                //蛙站广播单
                QueryOrderDetailRequest req = new QueryOrderDetailRequest();
                req.setSchduleCarNo(mobileAssignOrderDetailReq.getBusiBookNo());
                req.setSchudeCarType("2");
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
                    mobileAssignOrderDetailBean = setScheduOrderDetail(expreessOrder);
                    //设置产品类型
                    if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(expreessOrder.getProductType())
                            || MobileStationDefine.PRODUCT_TYPE_ITCKD.equals(expreessOrder.getProductType())) {
                        mobileAssignOrderDetailBean.setProductTypeName("同城快递");
                    } else {
                        mobileAssignOrderDetailBean.setProductTypeName("同城运输");
                    }
                } else {
                    logger.info("订单详细查询-hub广播单-快递单:HUB返回null");
                    throw new MobileStationBizException("查询订单详情失败！");
                }
            }
            mobileAssignOrderDetailBean.setRevUserName("未指派");
            queryAssignOrderDetailResult.setData(mobileAssignOrderDetailBean);
        }
        logger.info("queryAssignOrderDetail----广播单详细查询返回---{}", JSON.toJSONString(queryAssignOrderDetailResult));
        return queryAssignOrderDetailResult;
    }

    /**
     * 商管中心 批量指派广播单接口
     *
     * @param batchAssignOrderReq
     * @throws Exception
     */
    @Override
    public BatchAssignOrderResult batchAssignOrder(BatchAssignOrderReq batchAssignOrderReq) throws Exception {
        logger.info("batchAssignOrder----批量指派广播单接口---{}", JSON.toJSONString(batchAssignOrderReq));
        BatchAssignOrderResult batchAssignOrderResult = new BatchAssignOrderResult();
        MobileStationBatchAcceptOrderReq batchAcceptOrderReq = new MobileStationBatchAcceptOrderReq();
        if (StringUtil.isEmpty(batchAssignOrderReq.getRoleCode()) && SysAccountRole.getRoleIdByCode(batchAssignOrderReq.getRoleCode()) != 0) {
            batchAssignOrderResult.setRetCode(SystemDefine.FAILURE);
            batchAssignOrderResult.setRetMsg("角色不能为空!");
            return batchAssignOrderResult;
        }
        batchAcceptOrderReq.setRoleId(SysAccountRole.getRoleIdByCode(batchAssignOrderReq.getRoleCode()));
        batchAcceptOrderReq.setAccountId(batchAssignOrderReq.getRevUserId());
        batchAcceptOrderReq.setAcctUsername(batchAssignOrderReq.getRevUser());
        AppLoginInfo appLoginInfo = new AppLoginInfo();
        batchAcceptOrderReq.setAppLoginInfo(appLoginInfo);
        batchAcceptOrderReq.setAssignUser(batchAssignOrderReq.getAssignUser());
        batchAcceptOrderReq.setAssignUserId(batchAssignOrderReq.getAssignUserId());
        batchAcceptOrderReq.setAssignDate(batchAssignOrderReq.getAssignDate());
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(batchAssignOrderReq.getRevUserId());
        ComCompanyStaff comCompanyStaff = comCompanyStaffDaoEx.queryCompanyByAccount(batchAssignOrderReq.getRevUserId());
        List<MobileStationAcceptOrderCustomReq> acceptOrderCustomReqList = new ArrayList<>();
        for (AssignOrderBean assignOrderBean : batchAssignOrderReq.getAssignOrderBeanList()) {
            MobileStationAcceptOrderCustomReq acceptOrderCustomReq = new MobileStationAcceptOrderCustomReq();
            acceptOrderCustomReq.setBusiBookNo(assignOrderBean.getBusiBookNo());
            acceptOrderCustomReq.setOrderFrom(assignOrderBean.getOrderFrom());
            acceptOrderCustomReq.setPushDate(assignOrderBean.getPushDate());
            acceptOrderCustomReq.setScheducarno(assignOrderBean.getScheducarno());
            acceptOrderCustomReq.setTransportType(assignOrderBean.getTransportType());
            acceptOrderCustomReqList.add(acceptOrderCustomReq);
        }
        batchAcceptOrderReq.setMobileStationAcceptOrderReqList(acceptOrderCustomReqList);
        if (comCompanyStaff != null) {
            batchAcceptOrderReq.setCompanyAccountId(comCompanyStaff.getCompanyAccountId());
            batchAssignOrderReq.setRevCompanyId(comCompanyStaff.getCompanyAccountId());
        }
        MobileStationAcceptOrderCustomResult appBaseResult;
        if (batchAcceptOrderReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
            appBaseResult = miStationAcceptService.batchAcceptOrder(batchAcceptOrderReq);
        } else if (batchAcceptOrderReq.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()) {
            //如果是指派给司机，则为指派给公司直属车队
            List<MobileStationAcceptOrderCustomReq> failList = new ArrayList<>();
            List<MobileStationAcceptOrderCustomReq> successList = new ArrayList<>();
            Integer successCount = 0;
            Integer failCount = 0;
            appBaseResult = new MobileStationAcceptOrderCustomResult();
            for (AssignOrderBean assignOrderBean : batchAssignOrderReq.getAssignOrderBeanList()) {
                //更新BookingForm
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(assignOrderBean.getBusiBookNo());
                int flag;
                int fleetId;
                if (batchAssignOrderReq.getCompanyAccountId() != null) {
                    //系统指派司机时，有车队账号
                    fleetId = batchAssignOrderReq.getCompanyAccountId();
                    flag = bookingFormDaoEx.updateBookingFormOldStatusDriver(bookingForm.getBusiBookNo(), MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER,
                            MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER, batchAssignOrderReq.getRevUserId(), batchAssignOrderReq.getRevUser(), new Date(), batchAssignOrderReq.getCompanyAccountId());
                } else {
                    fleetId = comAccount.getId();
                    flag = bookingFormDaoEx.updateBookingFormOldStatus(bookingForm.getBusiBookNo(), MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER,
                            MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER, comAccount.getId());
                }
                MobileStationAcceptOrderCustomReq reqBean = new MobileStationAcceptOrderCustomReq();
                if (flag < 1) {
                    failCount = failCount + 1;
                    reqBean.setTransportType(bookingForm.getTransportType());
                    reqBean.setPushDate(new Date());
                    reqBean.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST);
                    reqBean.setBusiBookNo(bookingForm.getBusiBookNo());
                    reqBean.setOrderId(bookingForm.getId());
                    reqBean.setErrMsg("订单已经被接！");
                    failList.add(reqBean);
                } else {
                    //插入MobileBookingForm
                    MobileBookingForm mobileBookingForm = createMobileBookingForm(bookingForm, batchAssignOrderReq, fleetId);
                    //插入货物信息
                    List<BillingFormSalm> billingFormSalmList = billingFormSalmDaoEx.queryByBusiBookno(mobileBookingForm.getBusiBookNo());
                    if (billingFormSalmList != null && billingFormSalmList.size() > 0) {
                        for (BillingFormSalm billingFormSalm : billingFormSalmList) {
                            MobileGoodsDtl mobileGoodsDtl = createMobileGoodsDtl(mobileBookingForm, billingFormSalm);
                            mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
                            mobileGoodsDtlDao.insert(mobileGoodsDtl);
                        }
                    }
                    reqBean.setTransportType(bookingForm.getTransportType());
                    reqBean.setPushDate(new Date());
                    reqBean.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST);
                    reqBean.setBusiBookNo(bookingForm.getBusiBookNo());
                    reqBean.setOrderId(bookingForm.getId());
                    successCount = successCount + 1;
                    successList.add(reqBean);

                    GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();//记录GPS操作日志
                    List<String> allBusNoList = new ArrayList<>();
                    allBusNoList.add(bookingForm.getBusiBookNo());
                    giOrderTraceResynced.setAllBusiNo(allBusNoList);
                    giOrderTraceResynced.setAction(MobileStationDefine.Action_BizAssignFleet);
                    giOrderTraceResynced.setUserCode(batchAssignOrderReq.getAssignUser());
                    giOrderTraceResynced.setLoginCode(batchAssignOrderReq.getAssignUser());

                    if (batchAssignOrderReq.getCompanyAccountId() != null) {
                        //系统指派司机时，有车队账号
                        giOrderTraceResynced.setUserCodeFrom(batchAssignOrderReq.getCompanyAcctUsername());
                        giOrderTraceResynced.setUserCodeTo(batchAssignOrderReq.getRevUser());
                    } else {
                        //商管指派车队，没有司机
                        giOrderTraceResynced.setUserCodeFrom(comAccount.getAcctUsername());
                    }
                    giOrderTraceResynced.setProductType(bookingForm.getTransportType());
                    gpsLogService.sendGpsLogMessage(giOrderTraceResynced);

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

                }
                appBaseResult.setFailList(failList);
                appBaseResult.setFailCount(failCount);
                appBaseResult.setSuccessCount(successCount);
                appBaseResult.setSuccessList(successList);
            }
        } else {
            appBaseResult = mobileAcceptOrderService.batchAcceptOrder(batchAcceptOrderReq);
        }
        logger.info("batchAssignOrder----接单广播单接口返回---{}", JSON.toJSONString(appBaseResult));
        List<AssignOrderResBean> assignOrderResBeanList = new ArrayList<>();
        //指派成功发送短信
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq;
        if (appBaseResult.getSuccessList() != null) {
            for (MobileStationAcceptOrderCustomReq acceptOrderCustomReq : appBaseResult.getSuccessList()) {
                AssignOrderResBean assignOrderResBean = new AssignOrderResBean();
                assignOrderResBean.setBusiBookNo(acceptOrderCustomReq.getBusiBookNo());
                assignOrderResBean.setRetCode(SystemDefine.SUCCESS);
                assignOrderResBean.setRetMsg("指派成功");
                assignOrderResBeanList.add(assignOrderResBean);
                if (comAccount != null && !StringUtil.isEmpty(comAccount.getTelephone())) {
                    sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
                    sendSmsVerifyCodeReq.setSystem("MobileStation");
                    sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
                    sendSmsVerifyCodeReq.setBusiBookNo(acceptOrderCustomReq.getBusiBookNo());
                    sendSmsVerifyCodeReq.setModel(8);
                    smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
                }
            }
        }
        if (appBaseResult.getFailList() != null) {
            for (MobileStationAcceptOrderCustomReq acceptOrderCustomReq : appBaseResult.getFailList()) {
                AssignOrderResBean assignOrderResBean = new AssignOrderResBean();
                assignOrderResBean.setBusiBookNo(acceptOrderCustomReq.getBusiBookNo());
                assignOrderResBean.setRetCode(SystemDefine.FAILURE);
                assignOrderResBean.setRetMsg(acceptOrderCustomReq.getErrMsg());
                assignOrderResBeanList.add(assignOrderResBean);
            }
        }
        batchAssignOrderResult.setData(assignOrderResBeanList);
        logger.info("batchAssignOrder----批量指派广播单接口返回---{}", JSON.toJSONString(batchAssignOrderResult));
        return batchAssignOrderResult;
    }

    /**
     * 商管中心 业务注册用户列表查询
     *
     * @param queryBusRegisterUserReq
     * @return
     * @throws Exception
     */
    @Override
    public QueryBusRegisterUserResult queryBusRegisterUser(QueryBusRegisterUserReq queryBusRegisterUserReq) throws Exception {
        logger.info("商管中心 业务注册用户报表查询 {}", queryBusRegisterUserReq.toString());
        QueryBusRegisterUserResult result = new QueryBusRegisterUserResult();
        QueryBusRegisterUserBean registerUser;
        List<QueryBusRegisterUserListBean> userList = orderDubboDao.queryBusRegisterUserList(queryBusRegisterUserReq);
        int count = orderDubboDao.getBusRegisterUserCount(queryBusRegisterUserReq);
        if (userList != null && userList.size() > 0) {
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            List<QueryBusRegisterUserBean> registerUserList = new ArrayList<>();
            for (QueryBusRegisterUserListBean user : userList) {
                registerUser = new QueryBusRegisterUserBean();
                BeanUtils.copyProperties(registerUser, user);
                //设置是否实名制
                if (StringUtil.isEmpty(user.getUserName())) {
                    registerUser.setRealNameFlag(false);
                } else {
                    registerUser.setRealNameFlag(true);
                }
                //是否是企业用户
                if (!StringUtil.isEmpty(user.getAcctType()) && user.getAcctType() == 2) {
                    registerUser.setCompanyFlag(true);
                } else {
                    registerUser.setCompanyFlag(false);
                }
                //设置地址
                StringBuffer addSb = new StringBuffer();
                if (!StringUtil.isEmpty(user.getUserProvice()) && comProvinceMap.get(user.getUserProvice().toString()) != null) {
                    // 判断地址信息是否包含省
                    addSb.append(comProvinceMap.get(user.getUserProvice().toString()).getProvinceName());
                    if (!StringUtil.isEmpty(user.getUserCity()) && comCityMap.get(user.getUserCity().toString()) != null) {
                        addSb.append(comCityMap.get(user.getUserCity().toString()).getName());
                    }
                    if (!StringUtil.isEmpty(user.getUserCounty()) && comCountyMap.get(user.getUserCounty().toString()) != null) {
                        addSb.append(comCountyMap.get(user.getUserCounty().toString()).getAreaName());
                    }
                }
                registerUser.setRegisterAdd(addSb.toString());
                registerUserList.add(registerUser);
            }
            result.setDate(registerUserList);
        }
        result.setRecordCount(count);
        return result;
    }

    /**
     * 商管中心 用户下单列表查询
     *
     * @param queryUserOrderReq
     * @return
     * @throws Exception
     */
    @Override
    public QueryUserOrderResult queryUserOrderList(QueryUserOrderReq queryUserOrderReq) throws Exception {
        logger.info("商管中心 用户下单报表查询 {}", queryUserOrderReq.toString());
        QueryUserOrderResult queryUserOrderResult = new QueryUserOrderResult();
        List<QueryUserOrderListBean> userOrderList = orderDubboDao.queryUserOrderList(queryUserOrderReq);
        int count = orderDubboDao.getUserOrderCount(queryUserOrderReq);
        if (userOrderList != null && userOrderList.size() > 0) {
            List<QueryUserOrderResBean> userOrderResBeanList = new ArrayList<>();
            QueryUserOrderResBean userOrderResBean;
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            for (QueryUserOrderListBean userOrder : userOrderList) {
                userOrderResBean = new QueryUserOrderResBean();
                BeanUtils.copyProperties(userOrderResBean, userOrder);
                //设置订单地址
                StringBuffer addSb = new StringBuffer();
                if (!StringUtil.isEmpty(userOrder.getProvice()) && comProvinceMap.get(userOrder.getProvice().toString()) != null) {
                    addSb.append(comProvinceMap.get(userOrder.getProvice().toString()).getProvinceName());
                    if (!StringUtil.isEmpty(userOrder.getCity()) && comCityMap.get(userOrder.getCity().toString()) != null) {
                        addSb.append(comCityMap.get(userOrder.getCity().toString()).getName());
                    }
                    if (!StringUtil.isEmpty(userOrder.getCounty()) && comCountyMap.get(userOrder.getCounty().toString()) != null) {
                        addSb.append(comCountyMap.get(userOrder.getCounty().toString()).getAreaName());
                    }
                }
                userOrderResBean.setBookingAdd(addSb.toString());
                userOrderResBeanList.add(userOrderResBean);
            }
            queryUserOrderResult.setData(userOrderResBeanList);
        }
        queryUserOrderResult.setRecordCount(count);
        return queryUserOrderResult;
    }

    /**
     * 根据订单号列表查询订单信息
     *
     * @param queryOrderListReq
     * @return
     * @throws Exception
     */
    @Override
    public QueryOrderListResult queryOrderListByBusiBookNo(QueryOrderListReq queryOrderListReq) throws Exception {
        QueryOrderListResult result = new QueryOrderListResult();
        List<BookingForm> bookingFormList = mobileOrderDao.queryBookingFormList(queryOrderListReq.getBusiBookNoList());
        if (bookingFormList != null && bookingFormList.size() > 0) {
            List<QueryOrderListBean> orderList = new ArrayList<>();
            QueryOrderListBean queryOrderListBean;
            for (BookingForm bookingForm : bookingFormList) {
                queryOrderListBean = new QueryOrderListBean();
                queryOrderListBean.setBusiBookNo(bookingForm.getBusiBookNo());
                if (bookingForm.getCreateCompanyId() != null) {
                    queryOrderListBean.setOrderType(2);//企业单
                } else {
                    queryOrderListBean.setOrderType(1);//个人单
                }
                queryOrderListBean.setShipAddress(bookingForm.getCarriageReceiAddr());
                queryOrderListBean.setShipLinkMan(bookingForm.getShipCustlinkMan());
                queryOrderListBean.setShipLinkTel(bookingForm.getShipCustlinkTel());
                queryOrderListBean.setCneeAddress(bookingForm.getCarriageDelivAddr());
                queryOrderListBean.setCneeLinkMan(bookingForm.getCneeCustLinkMan());
                queryOrderListBean.setCneeLinkTel(bookingForm.getCneeCustLinkTel());
                if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(bookingForm.getTransportType())) {
                    queryOrderListBean.setProductType("同城快递");
                } else if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(bookingForm.getTransportType())) {
                    queryOrderListBean.setProductType("同城运输");
                } else if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(bookingForm.getTransportType())) {
                    queryOrderListBean.setProductType("同城专送");
                }
                if (bookingForm.getBusiCtrl() != null) {
                    if (bookingForm.getBusiCtrl() == 0) {
                        queryOrderListBean.setOrderStatus("待接单");
                    } else if (bookingForm.getBusiCtrl() == 1) {
                        queryOrderListBean.setOrderStatus("已接单");
                    } else if (bookingForm.getBusiCtrl() == 5) {
                        queryOrderListBean.setOrderStatus("已签收");
                    } else {
                        queryOrderListBean.setOrderStatus("运输中");
                    }
                }
                orderList.add(queryOrderListBean);
            }
            result.setData(orderList);
        }
        return result;
    }

    /**
     * 根据订单号列表查询I单结算信息列表
     *
     * @param queryOrdersCalcListReq
     * @return
     * @throws Exception
     */
    @Override
    public QueryOrdersCalcListRes queryOrdersCalcList(QueryOrdersCalcListReq queryOrdersCalcListReq) throws Exception {
        logger.debug("I单异步结算  queryOrdersCalcList ： {}", JSON.toJSONString(queryOrdersCalcListReq));
        QueryOrdersCalcListRes queryOrdersCalcListRes = new QueryOrdersCalcListRes();
        List<QueryOrdersCalcListResBean> calcList = new ArrayList<>();
        QueryOrdersCalcListResBean queryOrdersCalcListResBean;
        //查询订单信息
        for (OrderCalcBean orderCalcBean : queryOrdersCalcListReq.getOrderCalcBeanList()) {
            queryOrdersCalcListResBean = new QueryOrdersCalcListResBean();
            queryOrdersCalcListResBean.setOrderNo(orderCalcBean.getOrderNo());
            queryOrdersCalcListResBean.setBookingDate(orderCalcBean.getBookingDate());
            queryOrdersCalcListResBean.setId(orderCalcBean.getId());
            queryOrdersCalcListResBean.setRouteId(orderCalcBean.getRouteId());
            queryOrdersCalcListResBean.setTransportType(orderCalcBean.getTransportType());
            List<RouteStep> routeStepList = new ArrayList<>();
            if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(orderCalcBean.getTransportType())) {
                //只有快递员、车队的I单结算
                List<MobileOrderInfo> mobileOrderInfoList = mobileOrderDao.queryICalcOrderList(orderCalcBean.getOrderNo());
                if (mobileOrderInfoList != null && mobileOrderInfoList.size() > 0) {
                    MobileOrderInfo mobileOrderInfo = mobileOrderInfoList.get(0);
                    RouteStep routeStep = new RouteStep();
                    //如果是企业接单，企业结算
                    if (mobileOrderInfo.getRevCompanyId() != null) {
                        ComAccount comAccount = comAccountDao.selectByPrimaryKey(mobileOrderInfo.getRevCompanyId());
                        if (comAccount != null) {
                            routeStep.setGfUserFromCode(comAccount.getAcctUsername());
                            routeStep.setGfUserFromName(comAccount.getRealName());
                        } else {
                            routeStep.setGfUserFromCode(mobileOrderInfo.getRevUser());
                            routeStep.setGfUserFromName(mobileOrderInfo.getRealName());
                        }
                    } else {
                        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileOrderInfo.getBusiBookNo());
                        if (bookingForm.getRevCompanyId() != null) {
                            ComAccount comAccount = comAccountDao.selectByPrimaryKey(bookingForm.getRevCompanyId());
                            if (comAccount != null) {
                                routeStep.setGfUserFromCode(comAccount.getAcctUsername());
                                routeStep.setGfUserFromName(comAccount.getRealName());
                            } else {
                                routeStep.setGfUserFromCode(mobileOrderInfo.getRevUser());
                                routeStep.setGfUserFromName(mobileOrderInfo.getRealName());
                            }
                        } else {
                            routeStep.setGfUserFromCode(mobileOrderInfo.getRevUser());
                            routeStep.setGfUserFromName(mobileOrderInfo.getRealName());
                        }
                    }
                    routeStep.setGfUserToCode(sysGFCode);
                    routeStep.setGfUserToName(MobileStationDefine.SYS_COMPANY_NAME);
                    routeStep.setStartAccountId("-1");
                    routeStep.setEndAccountId("-1");
                    routeStep.setOperateCode(mobileOrderInfo.getRevUser());
                    routeStepList.add(routeStep);
                }
                queryOrdersCalcListResBean.setRouteStepList(routeStepList);
            } else if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(orderCalcBean.getTransportType())) {
                //有快递员、车队、咪站的I单结算
                //快递员和咪站的I单结算 pop-pod、pop-M、M-pod
                List<MobileOrderInfo> mobileBookingFormList = mobileOrderDao.queryICalcOrderList(orderCalcBean.getOrderNo());
                for (MobileOrderInfo mobileOrderInfo : mobileBookingFormList) {
                    if (mobileOrderInfo.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                        RouteStep routeStep = new RouteStep();
                        if (mobileOrderInfo.getRevCompanyId() != null) {
                            ComAccount comAccount = comAccountDao.selectByPrimaryKey(mobileOrderInfo.getRevCompanyId());
                            if (comAccount != null) {
                                routeStep.setGfUserFromCode(comAccount.getAcctUsername());
                                routeStep.setGfUserFromName(comAccount.getRealName());
                            }
                        } else {
                            List<ComCompanyStaff> comCompanyStaffList = comCompanyStaffDaoEx.queryCompanyByAccountRole(mobileOrderInfo.getRevUserId());
                            ComCompanyStaff comCompanyStaff = null;
                            if (comCompanyStaffList != null && comCompanyStaffList.size() > 0) {
                                for (ComCompanyStaff staff : comCompanyStaffList) {
                                    if (!StringUtil.isEmpty(staff.getRoleIds())) {
                                        String[] roleIds = staff.getRoleIds().split(",");
                                        if (roleIds != null && roleIds.length > 0) {
                                            for (String roleId : roleIds) {
                                                if ("7".equals(roleId)) {
                                                    comCompanyStaff = staff;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if (comCompanyStaff != null) {
                                        break;
                                    }
                                }
                            }
                            if (comCompanyStaff != null && comCompanyStaff.getCompanyAccountId() != null) {
                                ComAccount comAccount = comAccountDao.selectByPrimaryKey(comCompanyStaff.getCompanyAccountId());
                                if (comAccount != null) {
                                    routeStep.setGfUserFromCode(comAccount.getAcctUsername());
                                    routeStep.setGfUserFromName(comAccount.getRealName());
                                } else {
                                    routeStep.setGfUserFromCode(mobileOrderInfo.getRevUser());
                                    routeStep.setGfUserFromName(mobileOrderInfo.getRealName());
                                }
                            } else {
                                routeStep.setGfUserFromCode(mobileOrderInfo.getRevUser());
                                routeStep.setGfUserFromName(mobileOrderInfo.getRealName());
                            }
                        }
                        routeStep.setOperateCode(mobileOrderInfo.getRevUser());
                        //快递员
                        if (MobileStationDefine.POP.equals(mobileOrderInfo.getStartLocus())
                                && MobileStationDefine.POD.equals(mobileOrderInfo.getDestnLocus())) {
                            //POP-POD
                            if (mobileBookingFormList.size() > 1) {
                                continue;
                            } else {
                                routeStep.setGfUserToCode(sysGFCode);
                                routeStep.setGfUserToName(MobileStationDefine.SYS_COMPANY_NAME);
                                routeStep.setStartAccountId("-1");
                                routeStep.setEndAccountId("-1");
                                routeStepList.add(routeStep);
                            }
                        } else if (MobileStationDefine.POP.equals(mobileOrderInfo.getStartLocus())
                                && MobileStationDefine.M.equals(mobileOrderInfo.getDestnLocus())) {
                            //POP-M
                            routeStep.setGfUserToCode(sysGFCode);
                            routeStep.setGfUserToName(MobileStationDefine.SYS_COMPANY_NAME);
                            routeStep.setStartAccountId("-1");
                            routeStep.setEndAccountId("23");
                            routeStepList.add(routeStep);
                        } else if (MobileStationDefine.M.equals(mobileOrderInfo.getStartLocus())
                                && MobileStationDefine.POD.equals(mobileOrderInfo.getDestnLocus())) {
                            //M-POD
                            routeStep.setGfUserToCode(sysGFCode);
                            routeStep.setGfUserToName(MobileStationDefine.SYS_COMPANY_NAME);
                            routeStep.setStartAccountId("23");
                            routeStep.setEndAccountId("-1");
                            routeStepList.add(routeStep);
                        } else if (MobileStationDefine.POP.equals(mobileOrderInfo.getStartLocus())
                                && !MobileStationDefine.M.equals(mobileOrderInfo.getDestnLocus())
                                && !MobileStationDefine.POD.equals(mobileOrderInfo.getDestnLocus())) {
                            //POP-W
                            routeStep.setGfUserToCode(sysGFCode);
                            routeStep.setGfUserToName(MobileStationDefine.SYS_COMPANY_NAME);
                            routeStep.setStartAccountId("-1");
                            String endAccountId = orderUtil.getAccountId(mobileOrderInfo.getDestnLocus(), mobileOrderInfo.getRoleId());
                            routeStep.setEndAccountId(endAccountId);
                            routeStepList.add(routeStep);
                        }
                    } else if (mobileOrderInfo.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                        //咪站 POP-M、M-POD
                        if (MobileStationDefine.POP.equals(mobileOrderInfo.getStartLocus()) && MobileStationDefine.M.equals(mobileOrderInfo.getDestnLocus())) {
                            continue;
                        } else {
                            RouteStep routeStep = new RouteStep();

                            if (mobileOrderInfo.getRevCompanyId() != null) {
                                ComAccount comAccount = comAccountDao.selectByPrimaryKey(mobileOrderInfo.getRevCompanyId());
                                if (comAccount != null) {
                                    routeStep.setGfUserFromCode(comAccount.getAcctUsername());
                                    routeStep.setGfUserFromName(comAccount.getRealName());
                                }
                            } else {
                                List<ComCompanyStaff> comCompanyStaffList = comCompanyStaffDaoEx.queryCompanyByAccountRole(mobileOrderInfo.getRevUserId());
                                ComCompanyStaff comCompanyStaff = null;
                                if (comCompanyStaffList != null && comCompanyStaffList.size() > 0) {
                                    for (ComCompanyStaff staff : comCompanyStaffList) {
                                        if (!StringUtil.isEmpty(staff.getRoleIds())) {
                                            String[] roleIds = staff.getRoleIds().split(",");
                                            if (roleIds != null && roleIds.length > 0) {
                                                for (String roleId : roleIds) {
                                                    if ("23".equals(roleId)) {
                                                        comCompanyStaff = staff;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        if (comCompanyStaff != null) {
                                            break;
                                        }
                                    }
                                }
                                if (comCompanyStaff != null && comCompanyStaff.getRoleIds() != null) {
                                    ComAccount comAccount = comAccountDao.selectByPrimaryKey(comCompanyStaff.getCompanyAccountId());
                                    if (comAccount != null) {
                                        routeStep.setGfUserFromCode(comAccount.getAcctUsername());
                                        routeStep.setGfUserFromName(comAccount.getRealName());
                                    } else {
                                        routeStep.setGfUserFromCode(mobileOrderInfo.getRevUser());
                                        routeStep.setGfUserFromName(mobileOrderInfo.getRealName());
                                    }
                                } else {
                                    routeStep.setGfUserFromCode(mobileOrderInfo.getRevUser());
                                    routeStep.setGfUserFromName(mobileOrderInfo.getRealName());
                                }
                            }
                            routeStep.setGfUserToCode(sysGFCode);
                            routeStep.setGfUserToName(MobileStationDefine.SYS_COMPANY_NAME);
                            if (MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST == mobileOrderInfo.getOrderFrom()
                                    || MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU == mobileOrderInfo.getOrderFrom()) {
                                routeStep.setEndAccountId("23");
                            } else {
                                routeStep.setStartAccountId("23");
                            }
                            routeStep.setOperateCode(mobileOrderInfo.getRevUser());
                            routeStepList.add(routeStep);
                        }
                    }
                }
                //司机和快递员的派车单I单结算
                List<MobileOrderInfo> scheduOrderList = mobileOrderDao.queryICalcScheduOrderList(orderCalcBean.getOrderNo());
                for (MobileOrderInfo scheduOrder : scheduOrderList) {
                    //M-W 、W-W、W-POD派车单
                    if (scheduOrder.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                        //W-POD
                        RouteStep routeStep = new RouteStep();
                        if (scheduOrder.getRevCompanyId() != null) {
                            ComAccount comAccount = comAccountDao.selectByPrimaryKey(scheduOrder.getRevCompanyId());
                            if (comAccount != null) {
                                routeStep.setGfUserFromCode(comAccount.getAcctUsername());
                                routeStep.setGfUserFromName(comAccount.getRealName());
                            }
                        } else {
                            List<ComCompanyStaff> comCompanyStaffList = comCompanyStaffDaoEx.queryCompanyByAccountRole(scheduOrder.getRevUserId());
                            ComCompanyStaff comCompanyStaff = null;
                            if (comCompanyStaffList != null && comCompanyStaffList.size() > 0) {
                                for (ComCompanyStaff staff : comCompanyStaffList) {
                                    if (!StringUtil.isEmpty(staff.getRoleIds())) {
                                        String[] roleIds = staff.getRoleIds().split(",");
                                        if (roleIds != null && roleIds.length > 0) {
                                            for (String roleId : roleIds) {
                                                if ("7".equals(roleId)) {
                                                    comCompanyStaff = staff;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if (comCompanyStaff != null) {
                                        break;
                                    }
                                }
                            }
                            if (comCompanyStaff != null && comCompanyStaff.getCompanyAccountId() != null) {
                                ComAccount comAccount = comAccountDao.selectByPrimaryKey(comCompanyStaff.getCompanyAccountId());
                                if (comAccount != null) {
                                    routeStep.setGfUserFromCode(comAccount.getAcctUsername());
                                    routeStep.setGfUserFromName(comAccount.getRealName());
                                } else {
                                    routeStep.setGfUserFromCode(scheduOrder.getRevUser());
                                    routeStep.setGfUserFromName(scheduOrder.getRealName());
                                }
                            } else {
                                routeStep.setGfUserFromCode(scheduOrder.getRevUser());
                                routeStep.setGfUserFromName(scheduOrder.getRealName());
                            }
                        }
                        routeStep.setOperateCode(scheduOrder.getRevUser());
                        routeStep.setGfUserToCode(sysGFCode);
                        routeStep.setGfUserToName(MobileStationDefine.SYS_COMPANY_NAME);
                        String startAccountId = orderUtil.getAccountId(scheduOrder.getStartLocus(), scheduOrder.getRoleId());
                        routeStep.setStartAccountId(startAccountId);
                        routeStep.setEndAccountId("-1");
                        routeStepList.add(routeStep);
                    } else if (scheduOrder.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()) {
                        //W-W、W-M的运输走蛙站，M-W根据竞价判断是咪站还是蛙站，如果是咪站发起
                        //咪站
                        RouteStep routeStep = new RouteStep();
                        String endAccountId = orderUtil.getAccountId(scheduOrder.getDestnLocus(), scheduOrder.getRoleId());

                        if (MobileStationDefine.M.equals(scheduOrder.getStartLocus())) {
                            if (MobileStationDefine.MOBILE_ORDER_FROM_MS == scheduOrder.getOrderFrom()
                                    || MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST == scheduOrder.getOrderFrom()) {
                                //M-W 判断是否是咪站发起的竞价，如果是，
                                if (scheduOrder.getCreateCompanyId() != null) {
                                    ComAccount miAccount = comAccountDao.selectByPrimaryKey(scheduOrder.getCreateCompanyId());
                                    routeStep.setGfUserFromCode(miAccount.getAcctUsername());
                                    routeStep.setGfUserFromName(miAccount.getRealName());
                                } else {
                                    ComAccount miAccount = comAccountDao.selectByPrimaryKey(scheduOrder.getCreateUserId());
                                    List<ComCompanyStaff> comCompanyStaffList = comCompanyStaffDaoEx.queryCompanyByAccountRole(scheduOrder.getCreateUserId());
                                    ComCompanyStaff comCompanyStaff = null;
                                    if (comCompanyStaffList != null && comCompanyStaffList.size() > 0) {
                                        for (ComCompanyStaff staff : comCompanyStaffList) {
                                            if (!StringUtil.isEmpty(staff.getRoleIds())) {
                                                String[] roleIds = staff.getRoleIds().split(",");
                                                if (roleIds != null && roleIds.length > 0) {
                                                    for (String roleId : roleIds) {
                                                        if ("7".equals(roleId)) {
                                                            comCompanyStaff = staff;
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            if (comCompanyStaff != null) {
                                                break;
                                            }
                                        }
                                    }
                                    if (comCompanyStaff != null && comCompanyStaff.getCompanyAccountId() != null) {
                                        ComAccount comAccount = comAccountDao.selectByPrimaryKey(comCompanyStaff.getCompanyAccountId());
                                        if (comAccount != null) {
                                            routeStep.setGfUserFromCode(comAccount.getAcctUsername());
                                            routeStep.setGfUserFromName(comAccount.getRealName());
                                        } else {
                                            routeStep.setGfUserFromCode(miAccount.getAcctUsername());
                                            routeStep.setGfUserFromName(miAccount.getRealName());
                                        }
                                    } else {
                                        routeStep.setGfUserFromCode(miAccount.getAcctUsername());
                                        routeStep.setGfUserFromName(miAccount.getRealName());
                                    }
                                }

                                routeStep.setGfUserToCode(sysGFCode);
                                routeStep.setGfUserToName(MobileStationDefine.SYS_COMPANY_NAME);
                                routeStep.setStartAccountId("23");
                                routeStep.setEndAccountId(endAccountId);
                                routeStep.setOperateCode(scheduOrder.getRevUser());
                                routeStepList.add(routeStep);
                            }
                        } else {
                            //W-W、W-M由蛙站发起I单结算
                        }
                    }
                }
                queryOrdersCalcListResBean.setRouteStepList(routeStepList);
            } else if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(orderCalcBean.getTransportType())) {
                //只有车队的I单结算
//                BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(orderCalcBean.getId().intValue());
//                List<TransportPay> transportPayList = new ArrayList<>();
//                if (bookingForm != null && bookingForm.getRevCompanyId() != null) {
//                    TransportPay transportPay = new TransportPay();
//                    ComAccount comAccount = comAccountDao.selectByPrimaryKey(bookingForm.getRevCompanyId());
//                    if (comAccount != null) {
//                        transportPay.setGfUserFromCode(comAccount.getAcctUsername());
//                        transportPay.setGfUserFromName(comAccount.getRealName());
//                        transportPay.setGfUserToCode(sysGFCode);
//                        transportPay.setGfUserToName(MobileStationDefine.SYS_COMPANY_NAME);
//                        transportPay.setType(SysAccountRole.OPERATOR_COMPANY_FLEET.getRoleCode());
//                        transportPay.setPaymentTerm(MobileStationDefine.PAYMENT_GENERALACCT);
//                        transportPay.setAmount(bookingForm.getPredictValue());
//                        transportPayList.add(transportPay);
//                    }
//                }
//                queryOrdersCalcListResBean.setTransportPayList(transportPayList);
            }
            calcList.add(queryOrdersCalcListResBean);
        }
        queryOrdersCalcListRes.setCalcList(calcList);
        logger.debug("I单异步结算  queryOrdersCalcList ： return {}", JSON.toJSONString(queryOrdersCalcListRes));
        return queryOrdersCalcListRes;
    }

    /**
     * 蛙站收货更新订单状态 为已完成
     *
     * @param updateOrderStatusReq
     * @return
     * @throws Exception
     */
    @Override
    public MsDubboResBean updateOrderDeliveryStatus(UpdateOrderStatusReq updateOrderStatusReq) throws Exception {
        logger.debug("蛙站收货更新订单状态  updateOrderDeliveryStatus ：{}", JSON.toJSONString(updateOrderStatusReq));
        MsDubboResBean msDubboResBean = new MsDubboResBean();
        //判断蛙站收货有三种情况，POP-W 蛙站以订单收货  M-W W-W蛙站以派车单
        MobileBookingForm mobileBookingForm;
        if (updateOrderStatusReq != null && updateOrderStatusReq.getScheducarno() == null) {
            mobileBookingForm = mobileBookingFormDaoEx.getMobileOrderByBookBusiNo(updateOrderStatusReq.getBusiBookNo(), SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue(), updateOrderStatusReq.getCustTtl());
        } else if (updateOrderStatusReq != null && updateOrderStatusReq.getScheducarno() != null) {
            mobileBookingForm = mobileBookingFormDaoEx.getMobileOrderByBookBusiNo(updateOrderStatusReq.getScheducarno(), SysAccountRole.OPERATOR_CAR_OWNER.getValue(), updateOrderStatusReq.getCustTtl());
        } else {
            msDubboResBean.setRetCode(SystemDefine.FAILURE);
            msDubboResBean.setRetMsg("更新失败！");
            return msDubboResBean;
        }
        if (mobileBookingForm != null) {
            if (mobileBookingForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
            }
        } else {
            msDubboResBean.setRetCode(SystemDefine.FAILURE);
            msDubboResBean.setRetMsg("当前订单不能自动送达确认！");
            return msDubboResBean;
        }
        logger.debug("蛙站收货更新订单状态  updateOrderDeliveryStatus return：{}", JSON.toJSONString(msDubboResBean));
        return msDubboResBean;
    }

    @Override
    public RegResultBean queryPickupSendOrderCount(List<Integer> accountIdList, String date) {
        logger.debug("查询取件派件订单数  queryPickupSendOrderCount ：{}", accountIdList.toString());
        RegResultBean resultBean = new RegResultBean();
        List<PopAndPodOrderCount> list = new ArrayList<PopAndPodOrderCount>();
        for (Integer accountId : accountIdList) {
            PopAndPodOrderCount count = orderCountDao.QueryPopAndPodCount(accountId, date);
            count.setAccountId(accountId);
            list.add(count);
        }
        resultBean.setData(list);
        resultBean.setState(true);
        return resultBean;
    }

    /**
     * 更新错误订单 地址坐标信息
     *
     * @param updateErrOrderSourceReq
     * @return
     * @throws Exception
     */
    @Override
    public MsDubboResBean updateErrOrderSource(UpdateErrOrderSourceReq updateErrOrderSourceReq) throws Exception {
        logger.info("更新错误订单 地址坐标信息 updateErrOrderSource req {}", updateErrOrderSourceReq.toString());
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(updateErrOrderSourceReq.getBusiBookNo());
        MsDubboResBean msDubboResBean = validAllParam(updateErrOrderSourceReq, bookingForm);
        if (msDubboResBean.getRetCode() == SystemDefine.FAILURE) {
            return msDubboResBean;
        }
        BookingFormErr bookingFormErr = bookingFormErrDaoEx.getBookingFormErrByBusiNo(updateErrOrderSourceReq.getBusiBookNo());
        List<BillingFormSalm> billingFormSalmList = billingFormSalmDaoEx.queryByBusiBooknoId(bookingForm.getId());
        if (bookingFormErr != null) {
            if (bookingFormErr.getBusiCtrl() != 0) {
                msDubboResBean.setRetCode(SystemDefine.FAILURE);
                msDubboResBean.setRetMsg("该错误订单已经被处理！");
                return msDubboResBean;
            }
            //插入booking_form

            bookingForm.setCarriageReceiLongitude(updateErrOrderSourceReq.getStartLng());
            bookingForm.setCarriageReceiLatitude(updateErrOrderSourceReq.getStartLat());
            bookingForm.setCarriageDelivLongitude(updateErrOrderSourceReq.getDestnLng());
            bookingForm.setCarriageDelivLatitude(updateErrOrderSourceReq.getDestnLat());
            if (updateErrOrderSourceReq.getMileage() != null) {
                bookingForm.setMileage(updateErrOrderSourceReq.getMileage().divide(new BigDecimal(1000)));
            }

            //获取路由
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

            if (bookingForm.getTransportType() != null && !MobileStationDefine.PRODUCT_TYPE_TCZS.equals(bookingForm.getTransportType())) {
                try {
                    BaseRequestResult baseRequestResult = mobileRecOrderWebService.checkRouteExists(bookingForm.getCarriageReceiLongitude().doubleValue(),
                            bookingForm.getCarriageReceiLatitude().doubleValue(), bookingForm.getCarriageDelivLongitude().doubleValue(), bookingForm.getCarriageDelivLatitude().doubleValue());
                    if (baseRequestResult == null || baseRequestResult.getStatus() != 1) {
                        msDubboResBean.setRetCode(SystemDefine.FAILURE);
                        msDubboResBean.setRetMsg(baseRequestResult.getMesasge());
                        return msDubboResBean;
                    }
                } catch (Exception_Exception e) {
                    e.printStackTrace();
                    msDubboResBean.setRetCode(SystemDefine.FAILURE);
                    msDubboResBean.setRetMsg("更新路由失败！");
                    return msDubboResBean;
                }
            }

            //设置价格
            if (billingFormSalmList != null && billingFormSalmList.size() > 0) {
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

                if (bookingForm.getWhtGrosswht().compareTo(bookingForm.getWhtVolwht()) > 0) {
                    platFormInModel.setWeight(bookingForm.getWhtGrosswht());
                } else {
                    platFormInModel.setWeight(bookingForm.getWhtVolwht());
                }
                platFormInModel.setVolume(bookingForm.getWhtVolcbm().divide(new BigDecimal(1000000), 8, BigDecimal.ROUND_HALF_EVEN));
                platFormInModel.setCurrency("142");
                try {
                    logger.debug("print call calc interface -> query price, busiNo : {},, param : {} ", bookingForm.getBusiBookNo(), JSON.toJSONString(platFormInModel));
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
                    msDubboResBean.setRetCode(SystemDefine.FAILURE);
                    msDubboResBean.setRetMsg("报价查询错误！");
                    return msDubboResBean;
                }
            }
            //更新bookingFormErr状态
            int flag = bookingFormErrDaoEx.updateBookingFormErrStatus(updateErrOrderSourceReq.getBusiBookNo(), 0, 1);
            if (flag > 0) {
                bookingFormDao.updateByPrimaryKeySelective(bookingForm);
                logger.info("更新路由 start ----");
                //更新路由
                if (bookingForm.getTransportType() != null && !MobileStationDefine.PRODUCT_TYPE_TCZS.equals(bookingForm.getTransportType())) {
                    BaseRequestResult result = mobileRecOrderWebService.updateOrderRouteId(bookingForm.getBusiBookNo());
                    if (result == null || result.getStatus() != 1) {
                        msDubboResBean.setRetCode(SystemDefine.FAILURE);
                        if (result == null || result.getMesasge() == null) {
                            msDubboResBean.setRetMsg("调用签派更新路由失败!");
                        } else {
                            msDubboResBean.setRetMsg(result.getMesasge());
                        }
                        bookingFormErrDaoEx.updateBookingFormErrStatus(updateErrOrderSourceReq.getBusiBookNo(), 1, 0);
                        return msDubboResBean;
                    }
                }

                //记录地址簿,如果发件地址错误就记录发件地址簿，如果收件地址错误，就记录收件地址簿
//                if (bookingFormErr.getStartErr() != null && bookingFormErr.getStartErr()) {
//
//                }
//                if (bookingFormErr.getDestnErr() != null && bookingFormErr.getDestnErr()) {
//
//                }
                //判断是否是指派单，如果是指派单需要插入MobileBookingForm
                MobileBookingForm mobileBookingForm = doSubOrder(bookingForm);
                //指派给MS时才推送消息
                if (null != bookingForm.getRevUser()) {
                    if (null != bookingForm.getRevUser()) {
                        MsgIMReq msgIMReq = new MsgIMReq();
                        msgIMReq.setBusiBookNo(bookingForm.getBusiBookNo());
                        msgIMReq.setRemindCode("PUSH_ORDER_000005");
                        msgIMReq.setCreateUser(bookingForm.getRevUser());
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
                logger.info("通知GPS start ----");
                //通知GPS
                PlaceAnOrderRes placeAnOrderRes = new PlaceAnOrderRes();
                placeAnOrderRes.setBusiBookNo(bookingForm.getBusiBookNo());
                placeAnOrderRes.setOrderId(bookingForm.getId());
                placeAnOrderRes.setProductType(bookingForm.getTransportType());
                placeAnOrderRes.setRoleId(SysAccountRole.NORMAL_PERSONAL.getValue());
                customerOrderService.notifyGps(placeAnOrderRes, null);

                //客户下单快递员，强制接单
                if (mobileBookingForm != null) {
                    MobileStationAcceptOrderReq acceptOrderReq = new MobileStationAcceptOrderReq();
                    acceptOrderReq.setAppLoginInfo(new AppLoginInfo());
                    acceptOrderReq.setRoleId(mobileBookingForm.getRoleId());
                    acceptOrderReq.setAccountId(mobileBookingForm.getRevUserId());
                    acceptOrderReq.setAcctUsername(mobileBookingForm.getRevUser());
                    acceptOrderReq.setLoginAcctUserName(mobileBookingForm.getRevUser());
                    acceptOrderReq.setLoginAccountId(mobileBookingForm.getRevUserId());
                    acceptOrderReq.setOrderFrom(mobileBookingForm.getOrderFrom());
                    acceptOrderReq.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                    acceptOrderReq.setOrderId(mobileBookingForm.getId());
                    if (mobileBookingForm.getTransportType() != null) {
                        acceptOrderReq.setTransportType(mobileBookingForm.getTransportType().toString());
                    }
                    mobileAcceptOrderService.acceptOrder(acceptOrderReq);
                }
            }
        }
        logger.debug("更新错误订单 地址坐标信息 updateErrOrderSource res={}", JSONObject.toJSONString(msDubboResBean));
        return msDubboResBean;
    }

    /**
     * 校验修改位置偏移入参
     *
     * @param req         修改位置偏移请求参数
     * @param bookingForm 订单信息
     * @return res 修改位置偏移返回结果
     */
    private MsDubboResBean validAllParam(UpdateErrOrderSourceReq req, BookingForm bookingForm) {
        logger.info("校验修改位置偏移入参--开始");
        MsDubboResBean res = new MsDubboResBean();
        Map<String, ComProvince> comProvinceMap = comProvinceService.queryNameForMap();
        Map<String, ComCity> comCityMap = comCityService.queryNameForMap();
        if (!StringUtil.isEmpty(req.getStartProvideName()) && !StringUtil.isEmpty(req.getStartCityName())
                && !StringUtil.isEmpty(req.getStartCountyName())) {
            Integer startProvide;
            Integer startCity = null;
            Integer destnProvide;
            Integer destnCity = null;
            //起点省市区不为空
            if (req.getStartProvideName() != null && comProvinceMap.get(req.getStartProvideName()) != null) {
                //设置省份code
                startProvide = comProvinceMap.get(req.getStartProvideName()).getId();
                if (req.getStartCityName() != null && comCityMap.get(req.getStartCityName()) != null) {
                    //设置市的code
                    startCity = comCityMap.get(req.getStartCityName()).getId();

                    //设置起点区
                    if (!StringUtil.isEmpty(req.getStartCountyName())) {
                        ComCounty comCounty = comCountyDaoEx.queryByParams(comCityMap.get(req.getStartCityName()).getId().toString(), req.getStartCountyName());
                        if (comCounty != null) {
                            bookingForm.setCarriageReceiCounty(comCounty.getId().toString());
                            bookingForm.setCarriageReceiAddr(req.getStartProvideName() + req.getStartCityName() + req.getStartCountyName() + bookingForm.getShipCustaDdr() + bookingForm.getShipCustHouseNumber());
                        }
                    }
                }
                if (startProvide != null && bookingForm.getCarriageReceiProvince() != null && startProvide != Integer.parseInt(bookingForm.getCarriageReceiProvince())) {
                    res.setRetCode(SystemDefine.FAILURE);
                    res.setRetMsg("起点省份不能修改！");
                }
                if (startCity != null && bookingForm.getCarriageReceiCity() != null && startCity != Integer.parseInt(bookingForm.getCarriageReceiCity())) {
                    res.setRetCode(SystemDefine.FAILURE);
                    res.setRetMsg("起点市不能修改！");
                }
            }
            if (!StringUtil.isEmpty(req.getDestnProvideName()) && !StringUtil.isEmpty(req.getDestnCityName())
                    && !StringUtil.isEmpty(req.getDestnCountyName())) {
                //终点省市区不为空
                destnProvide = comProvinceMap.get(req.getDestnProvideName()).getId();
                if (req.getDestnCityName() != null && comCityMap.get(req.getDestnCityName()) != null) {
                    //设置市的code
                    destnCity = comCityMap.get(req.getDestnCityName()).getId();

                    //设置目的地区
                    if (!StringUtil.isEmpty(req.getDestnCountyName())) {
                        ComCounty comCounty = comCountyDaoEx.queryByParams(comCityMap.get(req.getDestnCityName()).getId().toString(), req.getDestnCountyName());
                        if (comCounty != null) {
                            bookingForm.setCarriageDelivCounty(comCounty.getId().toString());
                            bookingForm.setCarriageDelivAddr(req.getDestnProvideName() + req.getDestnCityName() + req.getDestnCountyName() + bookingForm.getCneeCustAddr() + bookingForm.getCneeCustHouseNumber());
                        }
                    }
                }
                if (destnProvide != null && bookingForm.getCarriageDelivProvince() != null && destnProvide != Integer.parseInt(bookingForm.getCarriageDelivProvince())) {
                    res.setRetCode(SystemDefine.FAILURE);
                    res.setRetMsg("终点省份不能修改！");
                }
                if (destnCity != null && bookingForm.getCarriageDelivCity() != null && destnCity != Integer.parseInt(bookingForm.getCarriageDelivCity())) {
                    res.setRetCode(SystemDefine.FAILURE);
                    res.setRetMsg("终点市不能修改！");
                }
            }
        }
        logger.info("校验修改位置偏移入参--订单信息 ,{}", JSON.toJSONString(bookingForm));
        logger.info("校验修改位置偏移入参--结束");
        return res;
    }

    /**
     * 获取BookingForm路由路径
     *
     * @param cityCode
     * @return
     */
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
     * 判断是否为指派订单，如果是指派订单，需要新增MobileBookingForm信息
     *
     * @param bookingForm
     * @throws MobileStationBizException
     */
    private MobileBookingForm doSubOrder(BookingForm bookingForm) throws MobileStationBizException {

        //车主或者快递员
        if (!StringUtil.isEmpty(bookingForm.getRevUserId())) {
            logger.info("新增MobileBookingForm start ----");
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
            mobileBookingForm.setRevUserId(bookingForm.getRevUserId());
            mobileBookingForm.setRevUser(bookingForm.getRevUser());
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
            mobileBookingForm.setBusiCtrl(0);
            mobileBookingForm.setCreateDate(new Date());
            mobileBookingForm.setIsJs(0);
            mobileBookingForm.setProductType(bookingForm.getTransportType());

            mobileBookingForm.setStartLocus(MobileStationDefine.POP);
            mobileBookingForm.setDestnLocus(MobileStationDefine.POD);
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

            if (null != bookingForm.getNarrate()) {
                mobileBookingForm.setNarrate(bookingForm.getNarrate());
            }
            //4:小(M)站,5:快递员,6:司机
            if (mobileBookingForm.getTransportType() == 1) {//运输
                mobileBookingForm.setRoleId(3);//司机
            } else if (mobileBookingForm.getTransportType() == 2) {//快递
                if (bookingForm.getBookingCtrl() != null) {
                    mobileBookingForm.setRoleId(bookingForm.getBookingCtrl());
                    if (bookingForm.getBookingCtrl() == 7) {
                    } else if (bookingForm.getBookingCtrl() == 23) {
                    } else if (bookingForm.getBookingCtrl() == 3) {
                    }

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
        } else {
            return null;
        }
    }

    private void setDetailInfo(MobileAssignOrderDetailBean detailBean, OrderQueryRes res) {
        detailBean.setBusiBookNo(res.getBusiBookNo());
        detailBean.setPayType(res.getPaymentType());
        detailBean.setNeedInsure(res.isInsured());
        detailBean.setStartLongitude(res.getCarriageReceiLongitude());// 发货地址经度
        detailBean.setStartLatitude(res.getCarriageReceiLatitude());
        detailBean.setDestLongitude(res.getCarriageDelivLongitude());
        detailBean.setDestLatitude(res.getCarriageDelivLatitude());
        if (null != res.getAccessTime()) {
            detailBean.setEtaPopDate(DateUtil.parseDate(res.getAccessTime()));//取件时间
        }

        // 设置发货人信息
        detailBean.setStartAddress((StringUtil.isEmpty(res.getSenderAddr().getAddress()) ? "" : res.getSenderAddr().getAddress()) + (StringUtil.isEmpty(res.getSenderAddr().getDetailAddress()) ? "" : res.getSenderAddr().getDetailAddress()));
        detailBean.setStartLinkMan(res.getSenderAddr().getName());
        detailBean.setStartTel(res.getSenderAddr().getTelephone());
        // 设置收货人信息
        detailBean.setDestAddress((StringUtil.isEmpty(res.getReceiverAddr().getAddress()) ? "" : res.getReceiverAddr().getAddress()) + (StringUtil.isEmpty(res.getReceiverAddr().getDetailAddress()) ? "" : res.getReceiverAddr().getDetailAddress()));
        detailBean.setDestLinkMan(res.getReceiverAddr().getName());
        detailBean.setDestTel(res.getReceiverAddr().getTelephone());
        detailBean.setPredictCurr(res.getQuoteCurr());// 运费币值
        if (res.getPremiumValue() != null) {
            detailBean.setPremiumValue(res.getPremiumValue());// 保险费用
        }
        if (null != res.getGoodsValue()) {
            detailBean.setGoodsValue(BigDecimal.valueOf(res.getGoodsValue()));
        }
        detailBean.setRevUser(res.getRevUser());
        detailBean.setNarrate(res.getNarrate());
        if (res.getPredictValue() != null) {
            detailBean.setPredictValue(res.getPredictValue());
        }
        // 设置货物信息
        List<MobileGoodsInfo> mobileGoods = new ArrayList<MobileGoodsInfo>();
        List<GoodsInfo> goods = res.getGoodsInfos();
        if (goods != null) {
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

    private MobileAssignOrderDetailBean setScheduOrderDetail(ExpreessMobileBookingForm expreessOrder) {
        MobileAssignOrderDetailBean detailBean = new MobileAssignOrderDetailBean();
        // 提取主单信息
        detailBean.setBusiBookNo(expreessOrder.getBusiBookNo());
        detailBean.setEtaPopDate(expreessOrder.getEtaPopDate());
        detailBean.setOrderFrom(expreessOrder.getOrderFrom());

        detailBean.setStartAddress(expreessOrder.getShipCustAddr());
        detailBean.setStartTel(expreessOrder.getShipCustLinkTel());
        detailBean.setStartLinkMan(expreessOrder.getShipCustLinkMan());
        detailBean.setStartLatitude(expreessOrder.getShipLatitude());
        detailBean.setStartLongitude(expreessOrder.getShipLongitude());

        detailBean.setDestAddress(expreessOrder.getCneeCustAddr());
        detailBean.setDestTel(expreessOrder.getCneeCustLinkTel());
        detailBean.setDestLinkMan(expreessOrder.getCneeCustLinkMan());
        detailBean.setDestLatitude(expreessOrder.getCneeLatitude());
        detailBean.setDestLongitude(expreessOrder.getCneeLongitude());

        detailBean.setGoodsValue(expreessOrder.getGoodsValue());
        detailBean.setNeedInsure(expreessOrder.getNeedInsure());
        detailBean.setPredictValue(expreessOrder.getPredictValue());
        detailBean.setPredictCurr(expreessOrder.getPredictCurr());
        detailBean.setPayType(expreessOrder.getPayType());
        detailBean.setNarrate(expreessOrder.getNarrate());
        detailBean.setPremiumValue(expreessOrder.getPremiumValue());
        detailBean.setPremiumProportion(expreessOrder.getPremiumProportion());
        detailBean.setRevUserId(expreessOrder.getRevUserId());
        detailBean.setRevUser(expreessOrder.getRevUser());
        detailBean.setRevDate(expreessOrder.getRevDate());

        // 提取货物信息
        List<MobileGoodsInfo> goodsInfoList = new ArrayList<MobileGoodsInfo>();
        List<ExpreessBookingForm> subOrderList = expreessOrder.getBookingFormList();
        if (null != subOrderList && subOrderList.size() > 0) {
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

    private MobileBookingForm createMobileBookingForm(BookingForm bookingForm, BatchAssignOrderReq batchAssignOrderReq, Integer fleetId) {
        //生成 mobileBookingForm,如果是上面取件  生成POP-M、M-POD ；如果是送货上门，生成M-POD
        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        if (bookingForm.getNarrate() != null) {
            mobileBookingForm.setNarrate(bookingForm.getNarrate());
        }
        mobileBookingForm.setBookingFormId(bookingForm.getId());
        mobileBookingForm.setCreateUserId(bookingForm.getCreateUserId());
        mobileBookingForm.setCreateUser(bookingForm.getCreateUser());
        mobileBookingForm.setCreateCompanyId(bookingForm.getCreateCompanyId());
        mobileBookingForm.setBusiBookNo(bookingForm.getBusiBookNo());
        mobileBookingForm.setGoodsValue(bookingForm.getGoodsValue());
        mobileBookingForm.setBookingDate(bookingForm.getBookingDate());
        mobileBookingForm.setTransportType(bookingForm.getOrderType());
        mobileBookingForm.setProductType(bookingForm.getTransportType());
        if (batchAssignOrderReq.getCompanyAccountId() != null) {
            //系统指派司机时，有车队账号
            mobileBookingForm.setRevUserId(batchAssignOrderReq.getRevUserId());
            mobileBookingForm.setRevUser(batchAssignOrderReq.getRevUser());
            mobileBookingForm.setRevDate(new Date());
        }
        mobileBookingForm.setRevCompanyId(fleetId);
        mobileBookingForm.setPayType(bookingForm.getPayType());
        mobileBookingForm.setDestPayment(bookingForm.getDestPayment());
        mobileBookingForm.setGoodsPaymentCurr(bookingForm.getGoodsPaymentCurr());
        if (null != bookingForm.getOrderType()) {
            mobileBookingForm.setOrderType(bookingForm.getOrderType());
        }
        if (null != bookingForm.getNeedInsure()) {
            mobileBookingForm.setNeedInsure(bookingForm.getNeedInsure());
        }
        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);  //已接单
        mobileBookingForm.setCreateDate(new Date());
        mobileBookingForm.setRevDate(new Date());
        mobileBookingForm.setIsJs(0);
        mobileBookingForm.setRoleId(3);
        mobileBookingForm.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST);
        mobileBookingForm.setAssignUserId(batchAssignOrderReq.getAssignUserId());
        mobileBookingForm.setAssignUser(batchAssignOrderReq.getAssignUser());
        mobileBookingForm.setAssignDate(batchAssignOrderReq.getAssignDate());
        //发货地址 POP
        mobileBookingForm.setShipCustProvide(bookingForm.getCarriageReceiProvince());
        mobileBookingForm.setShipCustCity(bookingForm.getCarriageReceiCity());
        mobileBookingForm.setShipCustCounty(bookingForm.getCarriageReceiCounty());
        mobileBookingForm.setShipCustAddr((StringUtil.isEmpty(bookingForm.getShipCustaDdr()) ? "" : bookingForm.getShipCustaDdr()) + (StringUtil.isEmpty(bookingForm.getShipCustHouseNumber()) ? "" : bookingForm.getShipCustHouseNumber()));
        mobileBookingForm.setShipCustLinkMan(bookingForm.getShipCustlinkMan());
        mobileBookingForm.setShipCustLinkTel(bookingForm.getShipCustlinkTel());
        mobileBookingForm.setShipLongitude(bookingForm.getCarriageReceiLongitude());
        mobileBookingForm.setShipLatitude(bookingForm.getCarriageReceiLatitude());
        //收货地址POD
        mobileBookingForm.setCneeCustProvide(bookingForm.getCarriageDelivProvince());
        mobileBookingForm.setCneeCustCity(bookingForm.getCarriageDelivCity());
        mobileBookingForm.setCneeCustCounty(bookingForm.getCarriageDelivCounty());
        mobileBookingForm.setCneeCustAddr((StringUtil.isEmpty(bookingForm.getCneeCustAddr()) ? "" : bookingForm.getCneeCustAddr()) + (StringUtil.isEmpty(bookingForm.getCneeCustHouseNumber()) ? "" : bookingForm.getCneeCustHouseNumber()));
        mobileBookingForm.setCneeCustLinkMan(bookingForm.getCneeCustLinkMan());
        mobileBookingForm.setCneeCustLinkTel(bookingForm.getCneeCustLinkTel());
        mobileBookingForm.setCneeLongitude(bookingForm.getCarriageDelivLongitude());
        mobileBookingForm.setCneeLatitude(bookingForm.getCarriageDelivLatitude());

        mobileBookingForm.setEtaPopDate(bookingForm.getEtaPopDate());
        mobileBookingForm.setStartLocus(MobileStationDefine.POP);
        mobileBookingForm.setDestnLocus(MobileStationDefine.POD);

        mobileBookingFormDao.insert(mobileBookingForm);
        return mobileBookingForm;
    }

    /**
     * 生成货物信息
     *
     * @param mobileBookingForm
     * @param billingFormSalm
     * @return
     */
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


    /**
     * 根据参数查询订单报表信息
     *
     * @param req 请求信息
     * @return 订单报表信息
     */
    @Override
    public OrderReportDubboRes queryOrderReportList(OrderReportDubboReq req) {
        logger.info("根据参数查询订单报表信息-req:{}", JSON.toJSONString(req));
        OrderReportDubboRes res = new OrderReportDubboRes();
        if (req.validateParam(res)) {
            logger.error("根据参数查询订单报表信息-req校验失败");
            return res;
        }
        logger.info("根据参数查询订单报表请求结束-successful!");
        OrderReportReq orderReportReq = new OrderReportReq();
        try {
            BeanUtils.copyProperties(orderReportReq, req);
        } catch (Exception e) {
            logger.error("请求参数复制失败");
            res.setRetCode(-1);
            res.setRetMsg("请求参数复制失败");
            return res;
        }
        List<OrderReportBean> result = customerBookingFormDao.queryOrderReport(orderReportReq);
        int recodeCount = customerBookingFormDao.queryOrderReportCount(orderReportReq);
        if (CollectionUtils.isEmpty(result) || recodeCount == 0) {
            res.setData(null);
            res.setTotalSize(0);
            return res;
        }
        List<OrderReportDubboBean> ordbList = new ArrayList<>();
        for (OrderReportBean bean : result) {
            OrderReportDubboBean ordb = new OrderReportDubboBean();
            try {
                BeanUtils.copyProperties(ordb, bean);
            } catch (Exception e) {
                logger.error("bean参数复制失败");
                res.setRetCode(-1);
                res.setRetMsg("bean参数复制失败");
                return res;
            }
            ordbList.add(ordb);
        }
        logger.info("订单报表信息-data:{}", JSON.toJSONString(ordbList));
        res.setData(ordbList);
        res.setTotalSize(recodeCount);
        return res;
    }

}
