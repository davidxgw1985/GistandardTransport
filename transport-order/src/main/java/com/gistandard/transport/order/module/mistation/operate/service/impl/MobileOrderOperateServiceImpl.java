package com.gistandard.transport.order.module.mistation.operate.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.operate.service.MobileOrderOperateService;
import com.gistandard.transport.order.module.mistation.operate.service.bean.MiAssignFleetReq;
import com.gistandard.transport.order.module.mistation.operate.service.bean.MiConfirmOrderReq;
import com.gistandard.transport.order.module.mistation.operate.service.bean.MiQueryOrderDetailBean;
import com.gistandard.transport.order.module.mistation.operate.service.bean.MiQueryOrderDetailResult;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockBaseReq;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockInOutReq;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileAcceptOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.order.module.service.StatsBizOrderService;
import com.gistandard.transport.order.stock.service.MobileStockService;
import com.gistandard.transport.order.utils.OrderUtils;
import com.gistandard.transport.order.webservice.client.order.expressbusiness.ExpressBusinessOrderWebService;
import com.gistandard.transport.order.webservice.client.order.expressbusiness.MainOrderNotifyRequest;
import com.gistandard.transport.quote.system.database.bean.CheckAssignOrderforbatchFailed;
import com.gistandard.transport.quote.system.database.bean.QueryBatchPlatformQuote2Result;
import com.gistandard.transport.quote.system.database.bean.QueryPlatformQuote2Req;
import com.gistandard.transport.quote.system.database.services.ExpressService;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuote;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.tools.util.JedisManager;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by m on 2016/10/13.
 */
@Service
public class MobileOrderOperateServiceImpl implements MobileOrderOperateService {
    private static final Logger logger = LoggerFactory.getLogger(MobileOrderOperateServiceImpl.class);

    @Autowired
    private MobileMyOrderService mobileMyOrderService;
    @Autowired
    private MobileAcceptOrderService mobileAcceptOrderService;
    @Autowired
    private ComCurrencyService comCurrencyService;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileStockService mobileStockService;
    @Autowired
    private MobileStationOrderDao mobileStationOrderDao;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;
    @Autowired
    private MobileGoodsDtlDaoEx mobileGoodsDtlDaoEx;
    @Autowired
    private MobileGoodsDtlDao mobileGoodsDtlDao;
    @Autowired
    private MobileScheduSubOrderDao mobileScheduSubOrderDao;
    @Autowired
    private MobileSingleCenterDao mobileSingleCenterDao;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private BookingFormDao bookingFormDao;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private ComAccountService comAccountService;
    @Autowired
    private ComWaybillTraceDao comWaybillTraceDao;
    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;
    @Autowired
    private JedisManager jedisManager;
    @Autowired
    private OrderUtils orderUtils;
    @Autowired
    private MobileSingleCenterDaoEx mobileSingleCenterDaoEx;
    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;
    @Autowired
    private GpsOrderService gpsOrderService;
    @Autowired
    private ComProvinceService comProvinceService;
    @Autowired
    private ComCityService comCityService;
    @Autowired
    private ComCountyService comCountyService;
    @Autowired
    private GpsLogService gpsLogService;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private MobileFleetBiddingDao mobileFleetBiddingDao;
    @Autowired
    private StatsBizOrderService statsBizOrderService;
    @Autowired
    private MobileFleetBiddingDaoEx mobileFleetBiddingDaoEx;
    @Autowired
    private ExpressBusinessOrderWebService expressBusinessOrderWebService;

    /**
     * 咪站-分拣出库
     *
     * @param mobileStatusOperateReq
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult sortStockOut(MobileStatusOperateReq mobileStatusOperateReq) throws Exception {
        if (StringUtils.isBlank(mobileStatusOperateReq.getBusiNoTag())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByExpressOrderNo(mobileStatusOperateReq.getBusiBookNo());
            if (bookingForm != null) {
                mobileStatusOperateReq.setBusiBookNo(bookingForm.getBusiBookNo());
            }
        } else if (!mobileStatusOperateReq.getBusiNoTag().equals(StockBaseReq.BusiNoTag.QR_CODE.getCode())) {
            String busiNoFrom4T1Dno = orderUtils.getBusiNoFrom4T1Dno(mobileStatusOperateReq.getBusiBookNo());
            mobileStatusOperateReq.setBusiBookNo(busiNoFrom4T1Dno);
        }

        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        if (!doSortStockOutForSon(mobileStatusOperateReq))
            return baseResBean;
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        // 运输指派单
        if (mobileStatusOperateReq.getOrderId() != null && mobileStatusOperateReq.getOrderId() != 0) {
            // 出库
            StockInOutReq stockInOutReq = new StockInOutReq();
            stockInOutReq.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
            stockInOutReq.setStockType(MobileStationDefine.STOCK_OUT);
            stockInOutReq.setAccountId(mobileStatusOperateReq.getAccountId());
            stockInOutReq.setOrderFrom(mobileStatusOperateReq.getOrderFrom());
            stockInOutReq.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
            stockInOutReq.setScheducarno(mobileStatusOperateReq.getScheducarno());
            stockInOutReq.setAppLoginInfo(mobileStatusOperateReq.getAppLoginInfo());
            stockInOutReq.setCompanyAccountId(mobileStatusOperateReq.getCompanyAccountId());
            AppBaseResult res = mobileStockService.stockInOut(stockInOutReq);
            if (res.getRetCode() == SystemDefine.FAILURE) {
                // 送达失败
                logger.info("分拣出库:{}", res.getRetMsg());
                throw new MobileStationBizException("库存存操作失败，分拣出库失败！");
            }
            MobileOrderOperateBean orderOperateBean;
            if (mobileStatusOperateReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {  //如果是咪站
                // 修改订单状态为已完成
                orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                        mobileStatusOperateReq.getOrderId(),
                        mobileStatusOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE,
                        MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                //如果有**-M的订单，则设置状态为5，防止查询时候查出来
                MobileBookingForm mobileBookingForm2M = mobileBookingFormDaoEx.selectByConditions(mobileStatusOperateReq.getBusiBookNo(),
                        mobileStatusOperateReq.getAcctUsername(), mobileStatusOperateReq.getCompanyAccountId(), MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE, null);
                if (mobileBookingForm2M != null) {
                    if (mobileBookingForm2M.getDestnLocus().equals(MobileStationDefine.M)) {
                        mobileBookingForm2M.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                        mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm2M);
                    }
                }
            } else {
                // 修改订单状态为已完成
                orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                        mobileStatusOperateReq.getOrderId(),
                        mobileStatusOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS,
                        MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
            }
            int flag = mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
            if (flag < 1) {
                throw new MobileStationBizException("订单状态不对，分拣出库失败！");
            } else {
                // 设置派车单号
                MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq
                        .getOrderId());
                if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
                    waybillTraceOperateBean.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                } else {
                    waybillTraceOperateBean.setScheducarno(mobileBookingForm.getScheducarno());
                }
                //插入流程跟踪日志
                waybillTraceOperateBean.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
                if (mobileBookingForm != null) {
                    waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
                    waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
                }
                if (mobileStatusOperateReq.getAppLoginInfo() != null && mobileStatusOperateReq.getAppLoginInfo().getComAccount() != null) {
                    waybillTraceOperateBean.setRealName(mobileStatusOperateReq.getAppLoginInfo().getComAccount().getRealName());
                }
                waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
                waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_STOCKOUT_MI.getIntValue());
                waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_STOCKOUT_MI.getName());
                waybillTraceOperateBean.setDispatchId(mobileStatusOperateReq.getOrderId());
                waybillTraceOperateBean.setRoleId(23);
                mobileMyOrderService.insertWaybillTrace(waybillTraceOperateBean);
            }
        } else {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("orderId不能为空或者0");
        }
        return baseResBean;
    }

    /**
     * 检查是否是子订单,如果是，则判断是否全部记录，是，则对派车单进行分拣出库
     *
     * @param mobileStatusOperateReq
     * @return
     * @throws MobileStationBizException
     */
    private boolean doSortStockOutForSon(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException {
//        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq.getOrderId());
        MobileScheduSubOrder scheduSubOrderBean = mobileScheduSubOrderDaoEx.selectMobiScheduSubOrderByBusibookno2(mobileStatusOperateReq.getBusiBookNo(),
                mobileStatusOperateReq.getRoleId(), MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE);
        if (scheduSubOrderBean != null) {
            List<MobileScheduSubOrder> mobileScheduSubOrders = mobileScheduSubOrderDaoEx
                    .selectByScheducarno(scheduSubOrderBean.getScheducarno(), mobileStatusOperateReq.getAcctUsername(),
                            mobileStatusOperateReq.getRoleId());
            if (mobileScheduSubOrders.size() > 0) {
                //如果是子订单，则
                HashMap<String, String> map = (HashMap) jedisManager.getValueByKey("MMiStockOut:MMiStockOut" + scheduSubOrderBean.getScheducarno());
                if (map == null) {
                    map = new HashMap();
                }
                map.put(mobileStatusOperateReq.getBusiBookNo(), mobileStatusOperateReq.getBusiBookNo());
                jedisManager.saveValueByKey("MMiStockOut:MMiStockOut" + scheduSubOrderBean.getScheducarno(), map, 3600 * 24 * 2);
                if (map.size() == mobileScheduSubOrders.size()) {
                    List<MobileBookingForm> mobileBookingForms = mobileBookingFormDaoEx.queryOrderListByConditions2(scheduSubOrderBean.getScheducarno(),
                            mobileStatusOperateReq.getAcctUsername(), mobileStatusOperateReq.getCompanyAccountId(), null, mobileStatusOperateReq.getRoleId());
                    if (mobileBookingForms.size() == 0)
                        throw new MobileStationBizException("订单已分拣出库,请勿重复操作");
                    mobileStatusOperateReq.setOrderId(mobileBookingForms.get(0).getId());
                    mobileStatusOperateReq.setBusiBookNo(mobileBookingForms.get(0).getBusiBookNo());
                    mobileStatusOperateReq.setOrderFrom(mobileBookingForms.get(0).getOrderFrom());
                    mobileStatusOperateReq.setScheducarno(mobileBookingForms.get(0).getScheducarno());
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 咪站-派车(或广播派车)
     * 咪站广播、指派给快递员 POP-M、M-POD
     *
     * @param mobileOrderAssignReq
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult miOrderAssign(MobileOrderAssignReq mobileOrderAssignReq) throws Exception {
        AppBaseResult baseResBean = new AppBaseResult(mobileOrderAssignReq);
        //咪站派车  POP-M、M-POD派车(或广播)给快递员
        List<MobileStationAcceptOrderCustomReq> acceptOrderCustomReqList = new ArrayList<>();

        //获取原始要指派的咪站订单
        MobileBookingForm assignForm = mobileBookingFormDao.selectByPrimaryKey(mobileOrderAssignReq.getOrderId());
        int flag = 0;
        if (null != assignForm) {
            // 判断咪站原单状态 只有状态为1、或者40 可以进行派车
            if (assignForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER && assignForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
                throw new MobileStationBizException("咪站派车失败,该订单不在待派车状态！");
            }

//            if (assignForm.getOrderFrom() != null && (assignForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST || assignForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL)) {
//                //校验太保投保是否支付
//                BookingForm bf = bookingFormDaoEx.getBookingFormByBusiNo(assignForm.getBusiBookNo());
//                if (bf != null) {
//                    int premiumStatus = (bf == null || bf.getPremiumStatus() == null) ? -1 : bf.getPremiumStatus().intValue();
//                    if (bf != null && "1".equals(bf.getCarriAgerecei()) && bf.getNeedInsure() && CustomerDefine.HAVE_PAY != premiumStatus) {
//                        if (CustomerDefine.NEED_PAY == premiumStatus) {
//                            throw new MobileStationBizException("用户尚未支付保费，订单无法继续执行，请先与用户联系！");
//                        }
//                        if (bf.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
//                            throw new MobileStationBizException("订单核保失败尚未解冻，无法继续执行，请先与用户联系！");
//                        }
//
//
//                    }
//                }
//            }

            //校验是否发过广播单
            MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(
                    mobileOrderAssignReq.getBusiBookNo(), null, null, MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER, null);
            if (mobileBookingForm != null) {
                throw new MobileStationBizException("该订单已经广播单已存在,不能" + (mobileOrderAssignReq.isBroadcast() ? "重复发送！" : "指派"));
            }
            if (!mobileOrderAssignReq.isBroadcast()) {
                // 修改咪站原单状态
                assignForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING);
                flag = mobileMyOrderDao.updateByPrimaryKey2(assignForm);
                if (flag == 0) {
                    baseResBean.setRetCode(SystemDefine.FAILURE);
                    baseResBean.setRetMsg("MS指派失败,该订单已经被指派！");
                    return baseResBean;
                }
            } else {
                assignForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_BROADCAST);
                flag = mobileMyOrderDao.updateByPrimaryKey2(assignForm);
                if (flag == 0) {
                    baseResBean.setRetCode(SystemDefine.FAILURE);
                    baseResBean.setRetMsg("广播失败！");
                    return baseResBean;
                }
            }

            // 生成新的MS的订单
            MobileBookingForm assignA = new MobileBookingForm();
            //如果是咪站指派
            if (mobileOrderAssignReq.getStartRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                try {
                    PropertyUtils.copyProperties(assignA, assignForm);
                    assignA.setId(null);
                    assignA.setIsJs(0);// 都是未结算
                    assignA.setValidBillno(null);
                    assignA.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);// 待接单
                    assignA.setCreateDate(new Date());
                    assignA.setCreateUser(mobileOrderAssignReq.getAcctUsername());
                    assignA.setCreateUserId(mobileOrderAssignReq.getAccountId());
                    assignA.setCreateCompanyId(mobileOrderAssignReq.getCompanyAccountId());
                    assignA.setEditUserId(null);
                    assignA.setFormEditFlag(null);
                    assignA.setRevDate(null);
                    assignA.setEditDate(null);
                    assignA.setEditUser(null);
                    if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(mobileOrderAssignReq.getProductType())) {
                        assignA.setProductType(MobileStationDefine.PRODUCT_TYPE_ITCKD);
                    } else if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(mobileOrderAssignReq.getProductType())) {
                        assignA.setProductType(MobileStationDefine.PRODUCT_TYPE_ITCYS);
                    } else {
                        assignA.setProductType(mobileOrderAssignReq.getProductType());
                    }
                    assignA.setNarrate(mobileOrderAssignReq.getNarrate());

                    assignA.setPayType(MobileStationDefine.PAYTYPE_GENERALACCT);// 平台支付
                    if (assignA.getStartLocus() != null && assignA.getStartLocus().equals(MobileStationDefine.POP)) {
                        assignA.setOrderType(1);// 都是派件单
                    } else {
                        assignA.setOrderType(2);// 都是派件单
                    }

                    //设置咪站的平台报价
                    PlatformQuote platformQuote = expressService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG, assignA.getBusiBookNo(), assignA.getStartLocus(), assignA.getDestnLocus(), mobileOrderAssignReq.getDestRoleId());
                    if (platformQuote != null) {
                        assignA.setPredictValue(platformQuote.getPrice());
                        assignA.setPredictCurr(platformQuote.getCurrencyCode());
                    } else {
                        throw new MobileStationBizException("网络异常请重试");
                    }
                    if (!mobileOrderAssignReq.isBroadcast()) {
                        assignA.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS);
                        assignA.setRevUserId(mobileOrderAssignReq.getRevUserId());
                        assignA.setRevUser(mobileOrderAssignReq.getRevUser());
                        assignA.setRevCompanyId(null);
                    } else {
                        assignA.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST);
                        assignA.setRevUserId(null);
                        assignA.setRevUser(null);
                        assignA.setRevCompanyId(null);
                    }
                    assignA.setRoleId(mobileOrderAssignReq.getDestRoleId());
                    flag = mobileBookingFormDao.insert(assignA);
                    if (flag == 0) {
                        throw new MobileStationBizException("此单已存在，请勿重复操作");
                    }

                    MobileStationAcceptOrderCustomReq acceptOrderCustomReq = new MobileStationAcceptOrderCustomReq();
                    acceptOrderCustomReq.setBusiBookNo(mobileOrderAssignReq.getBusiBookNo());
                    acceptOrderCustomReq.setOrderFrom(assignA.getOrderFrom());
                    acceptOrderCustomReq.setPushDate(assignA.getCreateDate());
                    acceptOrderCustomReq.setScheducarno(assignA.getScheducarno());
                    if (assignA.getTransportType() != null) {
                        acceptOrderCustomReq.setTransportType(assignA.getTransportType() + "");
                    }
                    acceptOrderCustomReqList.add(acceptOrderCustomReq);
                    if (!StringUtil.isEmpty(assignForm.getScheducarno())) {
                        // 生成子订单信息
                        if (!StringUtil.isEmpty(assignForm.getScheducarno())) {
                            List<MobileScheduSubOrder> subOrderList = mobileMyOrderDao.selectMobileSubOrderByOrderId(assignForm.getId());
                            for (MobileScheduSubOrder subOrder : subOrderList) {
                                List<MobileGoodsDtl> mobileGoodsDtlList = mobileGoodsDtlDaoEx.selectBySubOrderId(assignForm.getId(), subOrder.getId());
                                subOrder.setMobileBookingFormId(assignA.getId());
                                subOrder.setId(null);
                                mobileScheduSubOrderDao.insert(subOrder);

                                for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
                                    mobileGoodsDtl.setId(null);
                                    mobileGoodsDtl.setMobileBookingFormId(assignA.getId());
                                    mobileGoodsDtl.setMobileScheduOrderId(subOrder.getId());
                                    mobileGoodsDtlDao.insert(mobileGoodsDtl);
                                }
                            }
                        }
                    } else {
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
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                    throw new MobileStationBizException("指派失败");
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
                    mobileSingleCenter.setDispatchId(null);
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
            }
        }
        if (flag < 1) {
            throw new MobileStationBizException("咪站派车失败！");
        } else {
            //咪站批量指派快递员，快递员强制接单
            if (acceptOrderCustomReqList != null && acceptOrderCustomReqList.size() > 0) {
                MobileStationBatchAcceptOrderReq batchAcceptOrderReq = new MobileStationBatchAcceptOrderReq();
                batchAcceptOrderReq.setRoleId(SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue());
                batchAcceptOrderReq.setAppLoginInfo(new AppLoginInfo());
                batchAcceptOrderReq.setMobileStationAcceptOrderReqList(acceptOrderCustomReqList);
                batchAcceptOrderReq.setAccountId(mobileOrderAssignReq.getRevUserId());
                batchAcceptOrderReq.setAcctUsername(mobileOrderAssignReq.getRevUser());
                batchAcceptOrderReq.setLoginAcctUserName(mobileOrderAssignReq.getRevUser());
                mobileAcceptOrderService.batchAcceptOrder(batchAcceptOrderReq);
            }
        }
        return baseResBean;
    }

    @Override
    public CheckAssignOrderforbatchResult checkAssignOrderforbatch(BatchMobileOrderAssignReq batchMobileOrderAssignReq) throws MobileStationBizException {
        CheckAssignOrderforbatchResult result = new CheckAssignOrderforbatchResult();
        //失败列表信息
        List<CheckAssignOrderforbatchFailed> faileds = new ArrayList<>();
        //校验成功信息
        List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> successes = new ArrayList<>();
        for (MobileOrderAssignReq mobileOrderAssignReq : batchMobileOrderAssignReq.getMobileOrderAssignReqList()) {
            CheckAssignOrderforbatchFailed failed = new CheckAssignOrderforbatchFailed();
            CheckAssignOrderforbatchSuccess success = new CheckAssignOrderforbatchSuccess();
            AssignOrderforbatchSuccess assignOrderforbatchSuccess = new AssignOrderforbatchSuccess();
            MobileBookingForm assignForm = mobileBookingFormDao.selectByPrimaryKey(mobileOrderAssignReq.getOrderId());
            if (null != assignForm) {
                // 判断咪站原单状态 只有状态为1、或者40 可以进行派车
                if (assignForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER && assignForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
                    failed.setBusibookno(assignForm.getBusiBookNo());
                    failed.setMsg("咪站派车失败,该订单不在待派车状态！");
                    faileds.add(failed);
                    continue;
                }

//                if (assignForm.getOrderFrom() != null && (assignForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST || assignForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL)) {
//                    //校验太保投保是否支付
//                    BookingForm bf = bookingFormDaoEx.getBookingFormByBusiNo(assignForm.getBusiBookNo());
//                    if (bf != null) {
//                        int premiumStatus = (bf == null || bf.getPremiumStatus() == null) ? -1 : bf.getPremiumStatus().intValue();
//                        if (bf != null && "1".equals(bf.getCarriAgerecei()) && bf.getNeedInsure() && CustomerDefine.HAVE_PAY != premiumStatus) {
//                            if (CustomerDefine.NEED_PAY == premiumStatus) {
//                                failed.setBusibookno(assignForm.getBusiBookNo());
//                                failed.setMsg("用户尚未支付保费，订单无法继续执行，请先与用户联系！");
//                                faileds.add(failed);
//                                continue;
//                            }
//                            if (bf.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
//                                failed.setBusibookno(assignForm.getBusiBookNo());
//                                failed.setMsg("订单核保失败尚未解冻，无法继续执行，请先与用户联系！");
//                                faileds.add(failed);
//                                continue;
//                            }
//                        }
//                    }
//                }

                //校验是否发过广播单
                MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(
                        mobileOrderAssignReq.getBusiBookNo(), null, null, MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER, null);
                if (mobileBookingForm != null) {
                    failed.setBusibookno(assignForm.getBusiBookNo());
                    failed.setMsg("该订单已经广播单已存在,不能" + (mobileOrderAssignReq.isBroadcast() ? "重复发送！" : "指派"));
                    faileds.add(failed);
                    continue;
                }
                assignOrderforbatchSuccess.setBusibookno(assignForm.getBusiBookNo());
                assignOrderforbatchSuccess.setAssignForm(assignForm);
                assignOrderforbatchSuccess.setMobileOrderAssignReq(mobileOrderAssignReq);
                success.setReq(assignOrderforbatchSuccess);
                successes.add(success);
            }
        }
        result.setCheckAssignOrderforbatchFaileds(faileds);
        result.setCheckAssignOrderforbatchSuccesses(successes);
        return result;
    }

    @Override
    @Transactional
    public CheckAssignOrderforbatchResult doBatchMiOrderAssign(List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> checkAssignOrderforbatchSuccesses,
                                                               BatchMobileOrderAssignReq batchMobileOrderAssignReq) throws Exception {
        StringBuffer idsBuffer = new StringBuffer();
        //失败列表
        List<CheckAssignOrderforbatchFailed> faileds = new ArrayList<>();
        //成功列表，下面操作要用
        List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> successes = new ArrayList<>();
        //统一查询报价信息入参列表
        List<QueryPlatformQuote2Req> queryPlatformQuote2Reqs = new ArrayList<>();
        //被指派的订单列表，下方需要批量修改状态
        List<MobileBookingForm> oldMobileBookingForms = new ArrayList<>();
        Integer revUserId = null;
        String revUser = null;
        MobileStationBatchAcceptOrderReq batchAcceptOrderReq = new MobileStationBatchAcceptOrderReq();
        for (CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess> checkAssignOrderforbatchSuccess : checkAssignOrderforbatchSuccesses) {
            AssignOrderforbatchSuccess successreq = checkAssignOrderforbatchSuccess.getReq();
            QueryPlatformQuote2Req queryPlatformQuote2Req = new QueryPlatformQuote2Req();
            CheckAssignOrderforbatchFailed failed = new CheckAssignOrderforbatchFailed();
            // 生产新的A-pod的订单
            MobileBookingForm assignA = new MobileBookingForm();
            MobileBookingForm assignForm = successreq.getAssignForm();
            MobileOrderAssignReq mobileOrderAssignReq = successreq.getMobileOrderAssignReq();
            //如果是咪站指派
            if (mobileOrderAssignReq.getStartRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                try {
                    PropertyUtils.copyProperties(assignA, assignForm);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                    failed.setBusibookno(assignForm.getBusiBookNo());
                    failed.setMsg("指派失败");
                    faileds.add(failed);
                    continue;
                }
                assignA.setId(null);
                assignA.setIsJs(0);// 都是未结算
                assignA.setValidBillno(null);
                assignA.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);// 待接单
                assignA.setCreateDate(new Date());
                assignA.setCreateUser(batchMobileOrderAssignReq.getAcctUsername());
                assignA.setCreateUserId(batchMobileOrderAssignReq.getAccountId());
                assignA.setCreateCompanyId(batchMobileOrderAssignReq.getCompanyAccountId());
                assignA.setEditUserId(null);
                assignA.setFormEditFlag(null);
                assignA.setRevUser(null);
                assignA.setEditDate(null);
                assignA.setEditUser(null);
                if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(mobileOrderAssignReq.getProductType())) {
                    assignA.setProductType(MobileStationDefine.PRODUCT_TYPE_ITCKD);
                } else if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(mobileOrderAssignReq.getProductType())) {
                    assignA.setProductType(MobileStationDefine.PRODUCT_TYPE_ITCYS);
                } else {
                    assignA.setProductType(mobileOrderAssignReq.getProductType());
                }
                assignA.setNarrate(mobileOrderAssignReq.getNarrate());

                assignA.setPayType(MobileStationDefine.PAYTYPE_GENERALACCT);// 平台支付
                if (assignA.getStartLocus() != null && assignA.getStartLocus().equals(MobileStationDefine.POP)) {
                    assignA.setOrderType(1);// 都是派件单
                } else {
                    assignA.setOrderType(2);// 都是派件单
                }

                if (!mobileOrderAssignReq.isBroadcast()) {
                    assignA.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS);
                    revUserId = mobileOrderAssignReq.getRevUserId();
                    revUser = mobileOrderAssignReq.getRevUser();
                    assignA.setRevUserId(mobileOrderAssignReq.getRevUserId());
                    assignA.setRevUser(mobileOrderAssignReq.getRevUser());
                    // assignA.setRevCompanyId(null);
                } else {
                    assignA.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST);
                    assignA.setRevUserId(null);
                    //assignA.setRevCompanyId(null);
                }
                assignA.setRoleId(mobileOrderAssignReq.getDestRoleId());
            } else {
                failed.setBusibookno(assignForm.getBusiBookNo());
                failed.setMsg("您未以咪站身份操作，无法指派");
                faileds.add(failed);
                continue;
            }

            queryPlatformQuote2Req.setSystemFlag(SystemDefine.MOBILE_STATION_SYS_FLAG);
            queryPlatformQuote2Req.setBusibookno(assignA.getBusiBookNo());
            queryPlatformQuote2Req.setStartAccountId(assignA.getStartLocus());
            queryPlatformQuote2Req.setEndAccountId(assignA.getDestnLocus());
            queryPlatformQuote2Req.setRoleId(mobileOrderAssignReq.getDestRoleId());
            queryPlatformQuote2Reqs.add(queryPlatformQuote2Req);
            successreq.setAssignA(assignA);
            successes.add(checkAssignOrderforbatchSuccess);

        }
        QueryBatchPlatformQuote2Result queryBatchPlatformQuote2Result = expressService.queryBatchPlatformQuote2(queryPlatformQuote2Reqs);
        faileds.addAll(queryBatchPlatformQuote2Result.getFaileds());
        Map<String, PlatformQuote> platformQuoteMap = queryBatchPlatformQuote2Result.getPlatformQuoteMap();
        List<MobileGoodsDtl> goodsListAll = new ArrayList<>();
        List<MobileSingleCenter> mobileSingleCenters = new ArrayList<>();
        List<MobileBookingForm> mobileBookingForms = new ArrayList<>();
        List<MobileStationAcceptOrderCustomReq> acceptOrderCustomReqList = new ArrayList<>();
        for (CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess> checkAssignOrderforbatchSuccess : successes) {
            AssignOrderforbatchSuccess successreq = checkAssignOrderforbatchSuccess.getReq();
            MobileBookingForm assignA = successreq.getAssignA();
            if (!platformQuoteMap.containsKey(assignA.getBusiBookNo()))
                continue;
            PlatformQuote platformQuote = platformQuoteMap.get(assignA.getBusiBookNo());
            assignA.setPredictValue(platformQuote.getPrice());
            assignA.setPredictCurr(platformQuote.getCurrencyCode());
            mobileBookingFormDao.insert(assignA);
            if (successreq.getMobileOrderAssignReq().isBroadcast()) {
                successreq.getAssignForm().setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_BROADCAST);
                oldMobileBookingForms.add(successreq.getAssignForm());
            }
        }
        for (CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess> checkAssignOrderforbatchSuccess : successes) {
            AssignOrderforbatchSuccess successreq = checkAssignOrderforbatchSuccess.getReq();
            MobileBookingForm assignA = successreq.getAssignA();
            if (!platformQuoteMap.containsKey(assignA.getBusiBookNo()))
                continue;
            MobileBookingForm assignForm = successreq.getAssignForm();
            Integer dispatchId = successreq.getDispatchId();
            MobileOrderAssignReq mobileOrderAssignReq = successreq.getMobileOrderAssignReq();
            if (!StringUtil.isEmpty(assignForm.getScheducarno())) {
                // 生成子订单信息
                if (!StringUtil.isEmpty(assignForm.getScheducarno())) {
                    List<MobileScheduSubOrder> subOrderList = mobileMyOrderDao.selectMobileSubOrderByOrderId(assignForm.getId());
                    for (MobileScheduSubOrder subOrder : subOrderList) {
                        List<MobileGoodsDtl> mobileGoodsDtlList = mobileGoodsDtlDaoEx.selectBySubOrderId(assignForm.getId(), subOrder.getId());
                        subOrder.setMobileBookingFormId(assignA.getId());
                        subOrder.setId(null);
                        mobileScheduSubOrderDao.insert(subOrder);

                        for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
                            mobileGoodsDtl.setId(null);
                            mobileGoodsDtl.setMobileBookingFormId(assignA.getId());
                            mobileGoodsDtl.setMobileScheduOrderId(subOrder.getId());
                            goodsListAll.add(mobileGoodsDtl);
                        }
                    }
                }
            } else {
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

                MobileStationAcceptOrderCustomReq acceptOrderCustomReq = new MobileStationAcceptOrderCustomReq();
                acceptOrderCustomReq.setBusiBookNo(mobileOrderAssignReq.getBusiBookNo());
                acceptOrderCustomReq.setOrderFrom(assignA.getOrderFrom());
                acceptOrderCustomReq.setPushDate(assignA.getCreateDate());
                acceptOrderCustomReq.setScheducarno(assignA.getScheducarno());
                if (assignA.getTransportType() != null) {
                    acceptOrderCustomReq.setTransportType(assignA.getTransportType() + "");
                }
                acceptOrderCustomReqList.add(acceptOrderCustomReq);
            }
            if (mobileOrderAssignReq.isBroadcast()) {
                //广播单进行广播
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
                mobileMyOrderService.sendMsg(msgIMReq);
            }
            if (!mobileOrderAssignReq.isBroadcast()) {
                idsBuffer.append(assignForm.getId());
                idsBuffer.append(",");
            }
        }
        String ids = idsBuffer.toString();
        if (!StringUtils.isBlank(ids)) {
            ids = ids.substring(0, ids.length() - 1);
            mobileMyOrderDao.batchUpdateByPrimaryKey2(ids, MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING);
        }
        if (goodsListAll.size() > 0) {
            mobileGoodsDtlDaoEx.batchInsert(goodsListAll);
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

        //咪站批量指派快递员，快递员强制接单
        if (acceptOrderCustomReqList != null && acceptOrderCustomReqList.size() > 0) {
            batchAcceptOrderReq.setRoleId(SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue());
            batchAcceptOrderReq.setAppLoginInfo(new AppLoginInfo());
            batchAcceptOrderReq.setMobileStationAcceptOrderReqList(acceptOrderCustomReqList);
            batchAcceptOrderReq.setAccountId(revUserId);
            batchAcceptOrderReq.setAcctUsername(revUser);
            batchAcceptOrderReq.setLoginAcctUserName(revUser);
            mobileAcceptOrderService.batchAcceptOrder(batchAcceptOrderReq);
        }

        CheckAssignOrderforbatchResult result = new CheckAssignOrderforbatchResult();
        result.setCheckAssignOrderforbatchFaileds(faileds);
        result.setCheckAssignOrderforbatchSuccesses(successes);
        return result;
    }

    @Override
    public void broadcastOrder(BroadcastOrderReq broadcastOrderReq) throws MobileStationBizException {
        if (broadcastOrderReq != null && broadcastOrderReq.getBusibooknos() != null && broadcastOrderReq.getBusibooknos().size() > 0) {
            List<MobileBookingForm> mobileBookingForms = new ArrayList<>();
            for (String busibookno : broadcastOrderReq.getBusibooknos()) {
                //校验是否发过广播单
                MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(busibookno, null, null, MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER, null);
                if (mobileBookingForm != null) {
                    mobileBookingForms.add(mobileBookingForm);
                } /*else {
                    throw new MobileStationBizException("此单不是广播单，无法广播!");
                }*/
            }
            if (mobileBookingForms.size() > 0) {
                broadcastMSOrder(mobileBookingForms);
            }
        }
    }

    /**
     * 咪站-指派发送广播
     * 咪站广播寻找快递员  40->22
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

                List<String> allRoleCode = new ArrayList<>();
                if (mobileBookingForm.getScheducarno() != null) {
                    allRoleCode.add(SysAccountRole.OPERATOR_CAR_OWNER.getRoleCode());
                    List<String> allModuleCode = new ArrayList<>();
                    allModuleCode.add(MobileStationDefine.PRODUCT_TYPE_TCYS);
                    giBizOrder.setAllModuleCode(allModuleCode);
                } else {
                    if (MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus()) || MobileStationDefine.POD.equals(mobileBookingForm.getDestnLocus())) {//快递员
                        allRoleCode.add(SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode());
                        List<String> allModuleCode = new ArrayList<>();
                        allModuleCode.add(MobileStationDefine.PRODUCT_TYPE_TCKD);
                        giBizOrder.setAllModuleCode(allModuleCode);
                    } else {//司机
                        allRoleCode.add(SysAccountRole.OPERATOR_CAR_OWNER.getRoleCode());
                        List<String> allModuleCode = new ArrayList<>();
                        allModuleCode.add(MobileStationDefine.PRODUCT_TYPE_TCYS);
                        giBizOrder.setAllModuleCode(allModuleCode);
                    }
                }
                if (MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus()) && MobileStationDefine.M.equals(mobileBookingForm.getDestnLocus())) {
                    ComAccount account = comAccountDao.selectByPrimaryKey(mobileBookingForm.getCreateUserId());
                    if (account != null && !StringUtil.isEmpty(account.getRealName())) {
                        giBizOrder.setIsRealName(true);
                    } else {
                        giBizOrder.setIsRealName(false);
                    }
                } else {
                    giBizOrder.setIsRealName(true);
                }
                giBizOrder.setAllRoleCode(allRoleCode);
                giBizOrderList.add(giBizOrder);
            }
            logger.info("23 broadCast Order sendBroadCastOrderMessage ={}", JSONArray.toJSONString(giBizOrderList));
            gpsOrderService.sendBroadCastOrderMessage(giBizOrderList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量取消广播单订单
     * 咪站取消广播寻找快递员、司机  22->40
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
                mobileAssignBean.setCreateCompanyId(mobileStatusOperateReq.getCompanyAccountId());
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
            //获取广播单
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
            if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.POP)) {
                // 当前订单,状态改成已接单
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
            } else if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)) {
                // 当前订单,状态改成已取件
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
            } else {
                // 当前订单,状态改成已取件
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
            }
            mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
            // 修改转单中心表状态为拒绝
            if (mobileSingleCenter != null) {
                mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_REFUSE);
                mobileSingleCenterDao.updateByPrimaryKey(mobileSingleCenter);
            }
        } else {
            throw new MobileStationBizException("取消指派失败！");
        }
        return baseResBean;
    }

    @Override
    public AppBaseResult batchCancleAssign(BatchCancleAssignReq batchCancleAssignReq) throws MobileStationBizException {
        return null;
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

        if (mobileBookingForm.getScheducarno() != null) {
            MobileBookingForm oldMobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(cacelMobileBroadcastOrderReq
                    .getOrderId());

            //派车单则要恢复子订单原单状态
            orderUtils.querySubOrderbyScheno(oldMobileBookingForm,
                    MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL, MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);

            oldMobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
            mobileBookingFormDao.updateByPrimaryKey(oldMobileBookingForm);
        }

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

    @Override
    public MiQueryOrderDetailResult queryOrderDetail(QueryOrderDetailReq queryOrderDetailReq) throws InvocationTargetException, IllegalAccessException, MobileStationBizException {
        if (StringUtils.isBlank(queryOrderDetailReq.getBusiNoTag())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByExpressOrderNo(queryOrderDetailReq.getBusiBookNo());
            if (bookingForm != null) {
                queryOrderDetailReq.setBusiBookNo(bookingForm.getBusiBookNo());
            }
        } else if (!queryOrderDetailReq.getBusiNoTag().equals(StockBaseReq.BusiNoTag.QR_CODE.getCode())) {
            String busiNoFrom4T1Dno = orderUtils.getBusiNoFrom4T1Dno(queryOrderDetailReq.getBusiBookNo());
            queryOrderDetailReq.setBusiBookNo(busiNoFrom4T1Dno);
        }

        MiQueryOrderDetailResult result = new MiQueryOrderDetailResult();
//        MiQueryOrderDetailBean data = null;
//        if (queryOrderDetailReq.getBusiBookNo().startsWith("M_")){
        queryOrderDetailReq.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE);
//        }else {
//            queryOrderDetailReq.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE);


//        }
        MiQueryOrderDetailBean data = queryDetailOrder(queryOrderDetailReq);
        if (data == null) {
            data = queryDetailSonorder(queryOrderDetailReq);
        }
        result.setData(data);
        return result;
    }

    private MiQueryOrderDetailBean queryDetailOrder(QueryOrderDetailReq queryOrderDetailReq) throws InvocationTargetException, IllegalAccessException, MobileStationBizException {
        List<MobileBookingForm> mobileBookingForms = mobileBookingFormDaoEx.queryOrderListByConditions(
                queryOrderDetailReq.getBusiBookNo(), queryOrderDetailReq.getAcctUsername(), queryOrderDetailReq.getCompanyAccountId(), queryOrderDetailReq.getBusiCtrl());
        MiQueryOrderDetailBean data = null;
        if (mobileBookingForms.size() > 0) {
            for (MobileBookingForm mobileBookingForm : mobileBookingForms) {
                if (mobileBookingForm.getRoleId().equals(queryOrderDetailReq.getRoleId())) {
                    data = new MiQueryOrderDetailBean();
                    BeanUtils.copyProperties(data, mobileBookingForm);
                    data.setStartmName(queryOrderDetailReq.getAppLoginInfo().getComAccount().getRealName());
                    if (!MobileStationDefine.POD.equals(mobileBookingForm.getDestnLocus())
                            && !MobileStationDefine.M.equals(mobileBookingForm.getDestnLocus())) {
                        ComCustomer comCustomer = comCustomerDaoEx.getComCustomerByCustTtl(mobileBookingForm.getDestnLocus());
                        if (comCustomer == null) {
                            throw new MobileStationBizException("指派的洼站信息不存在");
                        }
                        data.setNextwName(comCustomer.getCustName());
                    } else if (MobileStationDefine.POD.equals(mobileBookingForm.getDestnLocus())) {
                        Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                        Map<String, ComCity> comCityMap = comCityService.queryForMap();
                        Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
                        String nextName = "";
                        if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustProvide()) && comProvinceMap.get(mobileBookingForm.getCneeCustProvide()) != null) {
                            nextName += comProvinceMap.get(mobileBookingForm.getCneeCustProvide()).getProvinceName();
                        }
                        if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCity()) && comCityMap.get(mobileBookingForm.getCneeCustCity()) != null) {
                            nextName += comCityMap.get(mobileBookingForm.getCneeCustCity()).getName();
                        }
                        if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCounty()) && comCountyMap.get(mobileBookingForm.getCneeCustCounty()) != null) {
                            nextName += comCountyMap.get(mobileBookingForm.getCneeCustCounty()).getAreaName();
                        }
                        data.setNextwName(nextName);
                    }
                    if (mobileBookingForm.getScheducarno() != null) {
                        List<MobileScheduSubOrder> mobileScheduSubOrders = mobileScheduSubOrderDaoEx
                                .selectByScheducarno(mobileBookingForm.getScheducarno(), queryOrderDetailReq.getAcctUsername(),
                                        queryOrderDetailReq.getRoleId());
                        data.setGoodsNums(mobileScheduSubOrders.size());
                        data.setScheducarno(mobileBookingForm.getScheducarno());
                    } else {
                        data.setGoodsNums(1);
                    }
                    break;
                }
            }
        }
        return data;
    }

    private MiQueryOrderDetailBean queryDetailSonorder(QueryOrderDetailReq queryOrderDetailReq) throws MobileStationBizException, InvocationTargetException, IllegalAccessException {
        MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.queryMobileOrderBySonBookBusiNo(queryOrderDetailReq.getBusiBookNo(),
                queryOrderDetailReq.getRoleId(), queryOrderDetailReq.getBusiCtrl());
//        MobileScheduSubOrder mobileScheduSubOrder = mobileScheduSubOrderDaoEx
//                .selectMobiScheduSubOrderByBusibookno2(queryOrderDetailReq.getBusiBookNo(), null, null);
        if (mobileBookingForm == null) {
            throw new MobileStationBizException("此订单无法分拣出库打印");
        }
//        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileScheduSubOrder.getMobileBookingFormId());
        MiQueryOrderDetailBean data = new MiQueryOrderDetailBean();
        BeanUtils.copyProperties(data, mobileBookingForm);
        data.setStartmName(queryOrderDetailReq.getAppLoginInfo().getComAccount().getRealName());
        if (!mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)
                && !mobileBookingForm.getDestnLocus().equals(MobileStationDefine.M)) {
            ComCustomer comCustomer = comCustomerDaoEx.getComCustomerByCustTtl(mobileBookingForm.getDestnLocus());
            if (comCustomer == null) {
                throw new MobileStationBizException("指派的洼站信息不存在");
            }
            data.setNextwName(comCustomer.getCustName());
        } else if (mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)) {
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            String nextName = "";
            if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustProvide()) && comProvinceMap.get(mobileBookingForm.getCneeCustProvide()) != null) {
                nextName += comProvinceMap.get(mobileBookingForm.getCneeCustProvide()).getProvinceName();
            }
            if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCity()) && comCityMap.get(mobileBookingForm.getCneeCustCity()) != null) {
                nextName += comCityMap.get(mobileBookingForm.getCneeCustCity()).getName();
            }
            if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCounty()) && comCountyMap.get(mobileBookingForm.getCneeCustCounty()) != null) {
                nextName += comCountyMap.get(mobileBookingForm.getCneeCustCounty()).getAreaName();
            }
            data.setNextwName(nextName);
        }
        data.setGoodsNums(1);
        return data;
    }

    @Override
    public AppBaseResult getGoodsMySelf(GetGoodsMySelfReq getGoodsMySelfReq) throws MobileStationBizException {
        List<MobileBookingForm> mobileBookingForms = mobileBookingFormDaoEx.queryOrderListByConditions(getGoodsMySelfReq.getBusibookno(),
                getGoodsMySelfReq.getAcctUsername(), getGoodsMySelfReq.getCompanyAccountId(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
        for (MobileBookingForm mobileBookingForm : mobileBookingForms) {
            BookingForm bf = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
            if (mobileBookingForm != null && MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus())) {
                if (bf != null) {
                    int premiumStatus = (bf == null || bf.getPremiumStatus() == null) ? -1 : bf.getPremiumStatus().intValue();
                    if (bf != null && bf.getNeedInsure() && CustomerDefine.HAVE_PAY != premiumStatus) {
                        if (CustomerDefine.NEED_PAY == premiumStatus) {
                            throw new MobileStationBizException("用户尚未支付保费，订单无法继续执行，请先与用户联系！");
                        }
                        if (bf.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
                            throw new MobileStationBizException("订单核保失败尚未解冻，无法继续执行，请先与用户联系！");
                        }

                    }
                }
            }
            if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.M)
                    && mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)
                    && mobileBookingForm.getRoleId() == getGoodsMySelfReq.getRoleId()) {
                GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();//记录GPS操作日志
                List<String> allBusNoList = new ArrayList<>();
                //记录GPS操作日志
                giOrderTraceResynced.setAction(MobileStationDefine.Action_PodConfirmed);
                allBusNoList.add(mobileBookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNoList);
                giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
                if (mobileBookingForm.getRevCompanyId() != null) {
                    ComAccount companyAccount = comAccountDao.selectByPrimaryKey(mobileBookingForm.getRevCompanyId());
                    if (companyAccount != null) {
                        giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
                    } else {
                        giOrderTraceResynced.setUserCode(mobileBookingForm.getRevUser());
                    }
                } else {
                    giOrderTraceResynced.setUserCode(mobileBookingForm.getRevUser());
                }
                giOrderTraceResynced.setLoginCode(mobileBookingForm.getRevUser());
                giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_MSTATION.getRoleCode());

                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
                bf.setBusiCtrl(OrderState.FINISHED.getValue());
                bf.setDeliverDate(new Date());
                bookingFormDao.updateByPrimaryKey(bf);
                //记录日志
                mobileMyOrderService.confirmPayAtMS(mobileBookingForm.getBusiBookNo(), mobileBookingForm.getScheducarno());
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
                if (bookingForm != null) {
                    statsBizOrderService.sendFinishOrderStats(bookingForm);
                }
                insertComWaybillTrace(getGoodsMySelfReq, mobileBookingForm);
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
            }
        }
        return new AppBaseResult();
    }

    /**
     * 咪站确认车队报价
     *
     * @param miConfirmOrderReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult miConfirmOrder(MiConfirmOrderReq miConfirmOrderReq) throws MobileStationBizException {
        AppBaseResult appBaseResult = new AppBaseResult(miConfirmOrderReq);
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(miConfirmOrderReq.getOrderId());
        if (mobileBookingForm != null) {
            GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
            //获取车队竞价单
            MobileFleetBidding mobileFleetBidding = mobileFleetBiddingDaoEx.queryFleetBidding(mobileBookingForm.getBusiBookNo(), mobileBookingForm.getScheducarno());
            //更新咪站订单 状态为已接单，接单人为司机 公司为车队
            if (mobileFleetBidding != null) {
                int flag = mobileBookingFormDaoEx.updateMiConfirmOrder(mobileBookingForm.getId(), MobileStationDefine.MOBILE_ORDER_STATUS_QUOTE,
                        MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE, mobileFleetBidding.getBidValue(), mobileFleetBidding.getBidCurr());

                if (flag < 1) {
                    appBaseResult.setRetCode(SystemDefine.FAILURE);
                    appBaseResult.setRetMsg("当前状态不可以确认，请刷新后再试！");
                    return appBaseResult;
                }
                //插入车队的MobileBookingForm记录
                MobileBookingForm fleetOrder = new MobileBookingForm();
                try {
                    BeanUtils.copyProperties(fleetOrder, mobileBookingForm);
                    fleetOrder.setId(null);
                    fleetOrder.setRevUser(mobileFleetBidding.getDriverUser());
                    fleetOrder.setRoleId(SysAccountRole.OPERATOR_CAR_OWNER.getValue());
                    fleetOrder.setRevCompanyId(mobileFleetBidding.getFleetUserId());
                    fleetOrder.setRevUserId(mobileFleetBidding.getDriverUserId());
                    fleetOrder.setPredictValue(mobileFleetBidding.getBidValue());
                    fleetOrder.setPredictCurr(mobileFleetBidding.getBidCurr());
                    fleetOrder.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                    fleetOrder.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST);
                    fleetOrder.setIsJs(0);
                    mobileBookingFormDao.insert(fleetOrder);
                } catch (Exception e) {
                    throw new MobileStationBizException("咪站确认车队报价失败！");
                }
                //插入车队的货物信息
                List<MobileGoodsDtl> mobileGoodsDtlList = mobileGoodsDtlDaoEx.selectByMobileBookingFormId(mobileBookingForm.getId());
                //插入子订单信息
                List<MobileScheduSubOrder> miSubOrderList = mobileScheduSubOrderDaoEx.selectMobileSubOrderByMobileId(mobileBookingForm.getId());

                List<MobileGoodsDtl> goodsListAll = new ArrayList<>();
                for (MobileScheduSubOrder subOrder : miSubOrderList) {
                    List<MobileGoodsDtl> miGoodsDtlList = new ArrayList<>();
                    for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
                        if (subOrder.getId().equals(mobileGoodsDtl.getMobileScheduOrderId())) {
                            miGoodsDtlList.add(mobileGoodsDtl);
                        }
                    }
                    subOrder.setMobileBookingFormId(fleetOrder.getId());
                    subOrder.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                    subOrder.setId(null);
                    mobileScheduSubOrderDao.insert(subOrder);

                    for (MobileGoodsDtl mobileGoodsDtl : miGoodsDtlList) {
                        mobileGoodsDtl.setId(null);
                        mobileGoodsDtl.setMobileBookingFormId(fleetOrder.getId());
                        mobileGoodsDtl.setMobileScheduOrderId(subOrder.getId());
                        goodsListAll.add(mobileGoodsDtl);
                    }
                }
                if (goodsListAll.size() > 0) {
                    mobileGoodsDtlDaoEx.batchInsert(goodsListAll);
                }
                List<String> allBusNo = new ArrayList<>();
                allBusNo.add(mobileBookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNo);
                giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
                giOrderTraceResynced.setAction(MobileStationDefine.Action_FleetAccept);
                giOrderTraceResynced.setTsAct(new Date());
                giOrderTraceResynced.setUserCode(miConfirmOrderReq.getLoginAcctUserName());
                giOrderTraceResynced.setLoginCode(miConfirmOrderReq.getAcctUsername());
                giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
            } else {
                appBaseResult.setRetCode(SystemDefine.FAILURE);
                appBaseResult.setRetMsg("查询不到报价！");
            }
        } else {
            appBaseResult.setRetCode(SystemDefine.FAILURE);
            appBaseResult.setRetMsg("查询不到该订单！");
        }

        return appBaseResult;
    }

    /**
     * 咪站本地交接给蛙站
     *
     * @param miConfirmOrderReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public AppBaseResult miLocalHandover(MiConfirmOrderReq miConfirmOrderReq) throws MobileStationBizException {
        //咪站本地交接 把M-W的派车单直接送达确认
        AppBaseResult appBaseResult = new AppBaseResult(miConfirmOrderReq);
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(miConfirmOrderReq.getOrderId());
        if (mobileBookingForm != null) {
            if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
                int flag = mobileBookingFormDaoEx.updateOrderBusi(miConfirmOrderReq.getOrderId(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS, MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                if (flag < 1) {
                    appBaseResult.setRetCode(SystemDefine.FAILURE);
                    appBaseResult.setRetMsg("本地交接失败！");
                } else {
                    MainOrderNotifyRequest mainOrderNotifyRequest = new MainOrderNotifyRequest();
                    mainOrderNotifyRequest.setMotorcateQuoteAction("orderLocalConnect");
                    mainOrderNotifyRequest.setBusiOrderNO(mobileBookingForm.getBusiBookNo());
                    mainOrderNotifyRequest.setMainTransType("1");
                    expressBusinessOrderWebService.mainOrderNotify(mainOrderNotifyRequest);
                }
            } else {
                appBaseResult.setRetCode(SystemDefine.FAILURE);
                appBaseResult.setRetMsg("当前状态不可以本地交接！");
            }
        } else {
            appBaseResult.setRetCode(SystemDefine.FAILURE);
            appBaseResult.setRetMsg("查询不到该订单！");
        }
        return appBaseResult;
    }

    /**
     * 咪站-指派车队 车队强制接单
     *
     * @param miAssignFleetReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult miAssignFleet(MiAssignFleetReq miAssignFleetReq) throws MobileStationBizException {
        AppBaseResult appBaseResult = new AppBaseResult(miAssignFleetReq);
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(miAssignFleetReq.getOrderId());
        if (mobileBookingForm != null) {
            GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
            //获取车队竞价单
            MobileFleetBidding mobileFleetBidding = new MobileFleetBidding();
            mobileFleetBidding.setMobileBookingFormId(miAssignFleetReq.getOrderId());
            mobileFleetBidding.setBusiBookNo(mobileBookingForm.getBusiBookNo());
            mobileFleetBidding.setProductType(mobileBookingForm.getProductType());
            mobileFleetBidding.setBidValue(miAssignFleetReq.getSelfQuoteValue());
            mobileFleetBidding.setBidCurr(miAssignFleetReq.getSelfQuoteCurr());
            mobileFleetBidding.setChooseFlag(1);
            mobileFleetBidding.setCreateDate(new Date());
            mobileFleetBidding.setFleetUserId(miAssignFleetReq.getFleetAccountId());
            mobileFleetBidding.setFleetUser(miAssignFleetReq.getFleetAccount());
            mobileFleetBidding.setFleetName(miAssignFleetReq.getFleetName());
            mobileFleetBidding.setScheducarno(mobileBookingForm.getScheducarno());
            mobileFleetBidding.setCreateUserId(miAssignFleetReq.getAccountId());
            mobileFleetBidding.setCreateUser(miAssignFleetReq.getAcctUsername());
            mobileFleetBidding.setTaxRate(miAssignFleetReq.getTaxRate());
            mobileFleetBiddingDao.insert(mobileFleetBidding);
            //更新咪站订单 状态为已接单，接单人为司机 公司为车队
            int flag = mobileBookingFormDaoEx.updateMiAssignOrder(mobileBookingForm.getId(), MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS,
                    MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE, miAssignFleetReq.getSelfQuoteValue(), miAssignFleetReq.getSelfQuoteCurr(), miAssignFleetReq.getMileage());

            if (flag < 1) {
                appBaseResult.setRetCode(SystemDefine.FAILURE);
                appBaseResult.setRetMsg("当前状态不可以指派，请刷新后再试！");
                return appBaseResult;
            }
            //插入车队的MobileBookingForm记录
            MobileBookingForm fleetOrder = new MobileBookingForm();
            try {
                BeanUtils.copyProperties(fleetOrder, mobileBookingForm);
                fleetOrder.setId(null);
                fleetOrder.setRoleId(SysAccountRole.OPERATOR_CAR_OWNER.getValue());
                fleetOrder.setRevCompanyId(miAssignFleetReq.getFleetAccountId());
                fleetOrder.setPredictValue(miAssignFleetReq.getSelfQuoteValue());
                fleetOrder.setPredictCurr(miAssignFleetReq.getSelfQuoteCurr());
                fleetOrder.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                fleetOrder.setCreateCompanyId(mobileBookingForm.getRevCompanyId());
                fleetOrder.setCreateUserId(mobileBookingForm.getRevUserId());
                fleetOrder.setCreateUser(mobileBookingForm.getRevUser());
                fleetOrder.setCreateDate(new Date());
                fleetOrder.setRevUserId(null);
                fleetOrder.setRevUser(null);
                fleetOrder.setRevDate(new Date());
                fleetOrder.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS);
                fleetOrder.setIsJs(0);
                fleetOrder.setMileage(miAssignFleetReq.getMileage());
                mobileBookingFormDao.insert(fleetOrder);
            } catch (Exception e) {
                throw new MobileStationBizException("咪站指派车队失败！");
            }
            //插入车队的货物信息
            List<MobileGoodsDtl> mobileGoodsDtlList = mobileGoodsDtlDaoEx.selectByMobileBookingFormId(mobileBookingForm.getId());
            //插入子订单信息
            List<MobileScheduSubOrder> miSubOrderList = mobileScheduSubOrderDaoEx.selectMobileSubOrderByMobileId(mobileBookingForm.getId());

            List<MobileGoodsDtl> goodsListAll = new ArrayList<>();
            for (MobileScheduSubOrder subOrder : miSubOrderList) {
                subOrder.setMobileBookingFormId(fleetOrder.getId());
                subOrder.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                subOrder.setId(null);
                mobileScheduSubOrderDao.insert(subOrder);

                for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
                    mobileGoodsDtl.setId(null);
                    mobileGoodsDtl.setMobileBookingFormId(fleetOrder.getId());
                    mobileGoodsDtl.setMobileScheduOrderId(subOrder.getId());
                    goodsListAll.add(mobileGoodsDtl);
                }
            }
            if (goodsListAll.size() > 0) {
                mobileGoodsDtlDaoEx.batchInsert(goodsListAll);
            }
            List<String> allBusNo = new ArrayList<>();
            allBusNo.add(mobileBookingForm.getBusiBookNo());
            giOrderTraceResynced.setAllBusiNo(allBusNo);
            giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
            giOrderTraceResynced.setAction(MobileStationDefine.Action_BizAssignFleet);
            giOrderTraceResynced.setTsAct(new Date());
            giOrderTraceResynced.setTypeBizAssign(1);
            giOrderTraceResynced.setUserCode(miAssignFleetReq.getLoginAcctUserName());
            giOrderTraceResynced.setUserCodeFrom(miAssignFleetReq.getFleetAccount());
            giOrderTraceResynced.setLoginCode(miAssignFleetReq.getAcctUsername());
            giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
        } else {
            appBaseResult.setRetCode(SystemDefine.FAILURE);
            appBaseResult.setRetMsg("查询不到该订单！");
        }

        return appBaseResult;
    }

    private void insertComWaybillTrace(GetGoodsMySelfReq getGoodsMySelfReq, MobileBookingForm mobileBookingForm) {
        ComWaybillTrace tmp = new ComWaybillTrace();
        tmp.setAcctUsername(getGoodsMySelfReq.getAcctUsername());
        tmp.setBusiBookNo(mobileBookingForm.getBusiBookNo());
        ComAccount account = comAccountService.queryAccountByAcctUsername(getGoodsMySelfReq.getAcctUsername());
        if (account != null) {
            tmp.setRealName(account.getRealName());
            tmp.setRemark(SysAccountRole.getName(getGoodsMySelfReq.getRoleId().intValue()) + account.getRealName() + "已送达");
        }
        tmp.setGrade(BusinessDefine.GRADE);
        tmp.setExecCode(WayBillStatusDefine.TAKE_SUCCESS.getIntValue());
        tmp.setRoleId(getGoodsMySelfReq.getRoleId());
        tmp.setStaDate(new Date());
        comWaybillTraceDao.insert(tmp);
    }
}
