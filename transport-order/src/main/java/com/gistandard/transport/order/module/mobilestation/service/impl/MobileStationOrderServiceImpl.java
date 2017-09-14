package com.gistandard.transport.order.module.mobilestation.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gistandard.transport.app.dubbo.order.bean.RemoveStaffReq;
import com.gistandard.transport.app.dubbo.order.bean.UpdateStaffRoleReq;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.bean.ex.MobileScheduSubOrderEx;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDetailDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileStationOrderService;
import com.gistandard.transport.order.webservice.client.merchant.order.BaseRequestResult;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.*;
import com.gistandard.transport.order.webservice.server.mobilestation.constant.RecordOrderType;
import com.gistandard.transport.pay.webservice.generalAcct.GeneralAcctWebService;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.quote.system.database.services.ComQuoteService;
import com.gistandard.transport.quote.system.database.services.ExpressService;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeResult;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeReq;
import com.gistandard.transport.app.dubbo.sms.service.SmsDubboService;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuote;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormDetailModel;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormInModel;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormOutModel;
import com.gistandard.transport.system.webservice.client.payinfo.QueryCalcManagerWebService;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xgw
 * @ClassName MobileStationOrderServiceImpl
 * @Description
 * @Version 1.0
 * @Date 2015-08-17
 */
@Service
public class MobileStationOrderServiceImpl implements MobileStationOrderService {

    private static final Logger logger = LoggerFactory.getLogger(MobileStationOrderServiceImpl.class);
    @Autowired
    private MobileStationOrderDao mobileStationOrderDao;

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;

    @Autowired
    private MobileGoodsDtlDaoEx mobileGoodsDtlDaoEx;

    @Autowired
    private SmsDubboService smsService;

    @Autowired
    private ComQuoteService comQuoteService;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private ComCountyService comCountyService;

    @Autowired
    private ComProvinceService comProvinceService;

    @Autowired
    private ComAccountService comAccountService;

    @Autowired
    private ComUnitService comUnitService;

    @Autowired
    private BookingFormDao bookingFormDao;

    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Autowired
    private GeneralAcctWebService generalAcctWebService;

    @Autowired
    private ComWaybillTraceService comWaybillTraceService;

    @Autowired
    private MobileScheduSubOrderDao mobileScheduSubOrderDao;

    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;

    @Autowired
    private ComWaybillTraceDao comWaybillTraceDao;

    @Autowired
    private MobileStationOrderDetailDao mobileStationOrderDetailDao;

    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;

    @Autowired
    private ComCustomerDao comCustomerDao;

    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;

    @Autowired
    private ExpressService expressService;

    @Autowired
    private BillingFormSalmDaoEx billingFormSalmDaoEx;

    @Autowired
    private MobileMoudleRelDao mobileMoudleRelDao;

    @Autowired
    private MobileMoudleRelDaoEx mobileMoudleRelDaoEx;

    @Autowired
    private ComCountyDaoEx comCountyDaoEx;

    @Autowired
    private QueryCalcManagerWebService queryCalcManagerWebService;

    @Autowired
    private ComAccountDao comAccountDao;

    /**
     * 根据订单编号和货物编号，获取货物信息
     *
     * @param getGoodsInfoReq
     * @throws Exception
     */
    @Override
    public GetGoodsInfoResult getGoodsInfo(GetGoodsInfoReq getGoodsInfoReq) {
        GetGoodsInfoResult baseResBean = new GetGoodsInfoResult(getGoodsInfoReq);
        //根据orderId,goodsId获取货物信息
        GoodsStockInfo goodsInfo = mobileStationOrderDao.getMobileGoodsInfo(getGoodsInfoReq);
        if (goodsInfo == null) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("货物信息不存在。");
        } else {
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
            Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
            //根据省市区设置地址
            String startAddress = "";
            String destAddress = "";
            if (!StringUtil.isEmpty(goodsInfo.getStartProvide()) && comProvinceMap.get(goodsInfo.getStartProvide()) != null) {
                //判断地址信息是否包含省
                if (goodsInfo.getStartAddress().indexOf(comProvinceMap.get(goodsInfo.getStartProvide()).getProvinceName()) == -1) {
                    startAddress += comProvinceMap.get(goodsInfo.getStartProvide()).getProvinceName();
                    if (!StringUtil.isEmpty(goodsInfo.getStartCity()) && comCityMap.get(goodsInfo.getStartCity()) != null) {
                        startAddress += comCityMap.get(goodsInfo.getStartCity()).getName();
                    }
                    if (!StringUtil.isEmpty(goodsInfo.getStartCounty()) && comCountyMap.get(goodsInfo.getStartCounty()) != null) {
                        startAddress += comCountyMap.get(goodsInfo.getStartCounty()).getAreaName();
                    }
                }
            }
            goodsInfo.setStartAddress(startAddress + goodsInfo.getStartAddress());

            if (!StringUtil.isEmpty(goodsInfo.getDestProvide()) && comProvinceMap.get(goodsInfo.getDestProvide()) != null) {
                //判断地址信息是否包含省
                if (goodsInfo.getDestAddress().indexOf(comProvinceMap.get(goodsInfo.getDestProvide()).getProvinceName()) == -1) {
                    destAddress += comProvinceMap.get(goodsInfo.getDestProvide()).getProvinceName();
                    if (!StringUtil.isEmpty(goodsInfo.getDestCity()) && comCityMap.get(goodsInfo.getDestCity()) != null) {
                        destAddress += comCityMap.get(goodsInfo.getDestCity()).getName();
                    }
                    if (!StringUtil.isEmpty(goodsInfo.getDestCounty()) && comCountyMap.get(goodsInfo.getDestCounty()) != null) {
                        destAddress += comCountyMap.get(goodsInfo.getDestCounty()).getAreaName();
                    }
                }
            }
            goodsInfo.setDestAddress(destAddress + goodsInfo.getDestAddress());
            //设置货物单位
            if (!StringUtil.isEmpty(goodsInfo.getGoodsQtyUnit()) && comUnitMap.get(goodsInfo.getGoodsQtyUnit()) != null) {
                goodsInfo.setGoodsQtyUnitName(comUnitMap.get(goodsInfo.getGoodsQtyUnit()).getUnitCh());
            }
            //设置订单状态
            if (goodsInfo.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER) {
                goodsInfo.setBusiCtrlName("待接单");
            } else if (goodsInfo.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER) {
                goodsInfo.setBusiCtrlName("已接单");
            } else if (goodsInfo.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_GIVEUP) {
                goodsInfo.setBusiCtrlName("已放弃");
            } else if (goodsInfo.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN) {
                goodsInfo.setBusiCtrlName("派件中");
            } else if (goodsInfo.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_FAILURE) {
                goodsInfo.setBusiCtrlName("派件失败");
            } else if (goodsInfo.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
                goodsInfo.setBusiCtrlName("取件成功");
            } else if (goodsInfo.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_REFUSE) {
                goodsInfo.setBusiCtrlName("已拒绝");
            } else if (goodsInfo.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                goodsInfo.setBusiCtrlName("已完成");
            } else if (goodsInfo.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_RETURN) {
                goodsInfo.setBusiCtrlName("已退回");
            }

        }
        baseResBean.setData(goodsInfo);
        return baseResBean;
    }

    /**
     * 获取某段时间所有单据的收支列表
     *
     * @param queryOrderBalanceListByTimeReq
     * @return
     */
    @Override
    public QueryOrderBalanceListByTimeResult queryOrderBalanceListByTime(QueryOrderBalanceListByTimeReq queryOrderBalanceListByTimeReq) {
        QueryOrderBalanceListByTimeResult baseResBean = new QueryOrderBalanceListByTimeResult(queryOrderBalanceListByTimeReq);
        QueryMSInAndOutDataResult res;
        try {
            String result = generalAcctWebService.queryMSInAndOut(queryOrderBalanceListByTimeReq.getLoginAcctUserName(), queryOrderBalanceListByTimeReq.getBeginDate(), queryOrderBalanceListByTimeReq.getEndDate(), queryOrderBalanceListByTimeReq.getPayFlag(), queryOrderBalanceListByTimeReq.getStartRecord(), queryOrderBalanceListByTimeReq.getPageSize());
            logger.info("获取某段时间所有单据的收支列表 queryMSInAndOut " + result);
            res = JSON.parseObject(result, QueryMSInAndOutDataResult.class);

            if (res != null) {
                if (res.isSucceed()) {
                    baseResBean.setRetCode(SystemDefine.SUCCESS);
                    if (null != res.getRefObject()) {
                        baseResBean.setData(res.getRefObject().getAllQueryMSInAndOutBean());
                        baseResBean.setRecordCount(res.getRefObject().getRecordCount());
                    } else {
                        baseResBean.setRecordCount(0);
                    }

                } else {
                    baseResBean.setRetCode(SystemDefine.FAILURE);
                    baseResBean.setRetMsg(res.getMessage());
                }
            } else {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("获取某段时间所有单据的收支列表失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("获取某段时间所有单据的收支列表失败");
        }
        return baseResBean;
    }


    /**
     * 发送派件通知
     *
     * @param mobileSendMessageReq
     * @throws Exception
     */
    @Override
    public SendSmsVerifyCodeResult sendOrderMessageNotice(MobileSendMessageReq mobileSendMessageReq) {
        //调用短信接口，发送短信
        SendSmsVerifyCodeReq smsSendReq = new SendSmsVerifyCodeReq();
        smsSendReq.setAccountId(mobileSendMessageReq.getAccountId());
        smsSendReq.setSystem(mobileSendMessageReq.getSystem());
        smsSendReq.setModel(mobileSendMessageReq.getModel());
        smsSendReq.setReceiveNo(mobileSendMessageReq.getReceiveNo());
        smsSendReq.setBusiBookNo(mobileSendMessageReq.getBusiBookNo());
        smsSendReq.setStartAddress(mobileSendMessageReq.getStartAddress());
        SendSmsVerifyCodeResult baseResBean = smsService.sendSmsVerifyCode(smsSendReq);
        return baseResBean;
    }

    /**
     * 移动station下单
     *
     * @param mobileStationOrderBean
     * @return
     */
    @Override
    @Transactional
    public AppBaseResult makerOrder(MobileStationOrderBean mobileStationOrderBean) throws
            MobileStationBizException {
        AppBaseResult mobileStationOrderResBean = new AppBaseResult();
        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        if (!MobileStationDefine.POP.equals(mobileStationOrderBean.getStartLocus()) && mobileStationOrderBean.getStartLocusId() == null) {
            throw new MobileStationBizException("起始站点账号不能为空 startLocusId");
        }
        if (!MobileStationDefine.POD.equals(mobileStationOrderBean.getDestnLocus()) && mobileStationOrderBean.getDestnLocusId() == null) {
            throw new MobileStationBizException("目的地站点账号不能为空 destnLocusId");
        }
        List<MobileGoodsDtl> mobileGoodsInfoList = JSONArray.parseArray(mobileStationOrderBean.getMobileGoodsInfoListStr(), MobileGoodsDtl.class);
        try {
            PropertyUtils.copyProperties(mobileBookingForm, mobileStationOrderBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MobileStationBizException("参数格式异常");
        }
        //待接单
        mobileBookingForm.setBusiCtrl(0);
        mobileBookingForm.setCreateDate(new Date());
        mobileBookingForm.setIsJs(0);
        if (StringUtil.isEmpty(mobileBookingForm.getDispatchId())) {
            throw new MobileStationBizException("签派单号不能为空！");
        }
        //检查下单数据是否合法
        if (check(mobileBookingForm, mobileStationOrderResBean)) {
            int result = mobileBookingFormDao.insert(mobileBookingForm);
            if (result > 0) {
                for (MobileGoodsDtl mobileGoodsInfo : mobileGoodsInfoList) {
                    mobileGoodsInfo.setMobileBookingFormId(mobileBookingForm.getId());
                    mobileGoodsInfo.setCreateDate(new Date());
                    if (null != mobileBookingForm.getCreateUser()) {
                        mobileGoodsInfo.setCreateUser(mobileBookingForm.getCreateUser());
                    }
                }
                result = mobileGoodsDtlDaoEx.batchInsert(mobileGoodsInfoList);
                if (result <= 0) {
                    throw new MobileStationBizException("新增MOBILE_GOODS_DTL表失败！");
                }

            } else {
                throw new MobileStationBizException("新增MOBILE_BOOKING_FORM表失败！");
            }

        }

        return mobileStationOrderResBean;
    }

    @Override
    @Transactional
    public AppBaseResult makerOrder(MobileStationOrderTransportBean
                                            mobileStationOrderTransportBean) throws MobileStationBizException {
        AppBaseResult mobileStationOrderResBean = new AppBaseResult();
        if (!MobileStationDefine.POP.equals(mobileStationOrderTransportBean.getStartLocus()) && mobileStationOrderTransportBean.getStartLocusId() == null) {
            throw new MobileStationBizException("起始站点账号不能为空 startLocusId");
        }
        if (!MobileStationDefine.POD.equals(mobileStationOrderTransportBean.getDestnLocus()) && mobileStationOrderTransportBean.getDestnLocusId() == null) {
            throw new MobileStationBizException("目的地站点账号不能为空 destnLocusId");
        }
        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        List<MobileGoodsDtl> mobileGoodsInfoList = JSONArray.parseArray(mobileStationOrderTransportBean.getMobileGoodsInfoListStr(), MobileGoodsDtl.class);
        List<RevUserBean> revUserBeanList = JSONArray.parseArray(mobileStationOrderTransportBean.getRevUserListStr(), RevUserBean.class);
        try {
            PropertyUtils.copyProperties(mobileBookingForm, mobileStationOrderTransportBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MobileStationBizException("参数格式异常");
        }
        //指派单下给多个人
        for (RevUserBean revUser : revUserBeanList) {
            mobileBookingForm.setId(null);
            mobileBookingForm.setRevUserId(revUser.getRevUserId());
            mobileBookingForm.setRevUser(revUser.getRevUser());
            //待接单
            mobileBookingForm.setBusiCtrl(0);
            mobileBookingForm.setCreateDate(new Date());
            mobileBookingForm.setIsJs(0);
            //检查下单数据是否合法
            if (check(mobileBookingForm, mobileStationOrderResBean)) {
                int result = mobileBookingFormDao.insert(mobileBookingForm);
                if (result > 0) {
                    for (MobileGoodsDtl mobileGoodsInfo : mobileGoodsInfoList) {
                        mobileGoodsInfo.setMobileBookingFormId(mobileBookingForm.getId());
                        mobileGoodsInfo.setCreateDate(new Date());
                        if (null != mobileBookingForm.getCreateUser()) {
                            mobileGoodsInfo.setCreateUser(mobileBookingForm.getCreateUser());
                        }
                    }
                    result = mobileGoodsDtlDaoEx.batchInsert(mobileGoodsInfoList);
                    if (result <= 0) {
                        throw new MobileStationBizException("新增MOBILE_GOODS_DTL表失败！");
                    }
                } else {
                    throw new MobileStationBizException("新增MOBILE_BOOKING_FORM表失败！");
                }
            }
        }
        return mobileStationOrderResBean;
    }

    @Override
    public AppBaseResult cancelOrder(MobileStationCancelOrderBean
                                             mobileStationCancelOrderBean) throws MobileStationBizException {
        AppBaseResult mobileStationCancelOrderResBean = new AppBaseResult();
        if (null == mobileStationCancelOrderBean) {
            throw new MobileStationBizException("参数为空");
        } else if (StringUtil.isEmpty(mobileStationCancelOrderBean.getScheducarno())) {
            throw new MobileStationBizException("参数为空");
        } else {
            List<MobileBookingForm> mobileBookingFormList = mobileStationOrderDao.getMobileBookingFormByScheducarno(mobileStationCancelOrderBean.getScheducarno());

            if (null == mobileBookingFormList || mobileBookingFormList.size() <= 0) {
                return mobileStationCancelOrderResBean;
            }
            boolean canDelete = Boolean.TRUE;
            for (MobileBookingForm mobileBookingForm : mobileBookingFormList) {
                if (mobileBookingForm.getBusiCtrl() > MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER) {
                    canDelete = Boolean.FALSE;
                    break;
                }
            }
            if (!canDelete) {
                throw new MobileStationBizException("已接单不能取消");
            }

            for (MobileBookingForm mobileBookingForm : mobileBookingFormList) {
                mobileBookingForm.setBusiCtrl(-1);
                mobileBookingForm.setEditDate(new Date());
                if (null != mobileStationCancelOrderBean.getAccountId()) {
                    mobileBookingForm.setEditUserId(mobileStationCancelOrderBean.getAccountId());
                }
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
            }
        }
        return mobileStationCancelOrderResBean;
    }

    @Override
    public AppBaseResult cancelOrderByDispatch(MobileStationCancelOrderBean
                                                       mobileStationCancelOrderBean) throws MobileStationBizException {
        AppBaseResult mobileStationCancelOrderResBean = new AppBaseResult();
        if (null == mobileStationCancelOrderBean) {
            throw new MobileStationBizException("参数为空");
        } else if (StringUtil.isEmpty(mobileStationCancelOrderBean.getScheducarno())) {
            throw new MobileStationBizException("参数为空");
        } else {
            List<MobileBookingForm> mobileBookingFormList = mobileStationOrderDao.getMobileBookingFormByDispatchId(mobileStationCancelOrderBean.getDispatchId());

            if (null == mobileBookingFormList || mobileBookingFormList.size() <= 0) {
                return mobileStationCancelOrderResBean;
            }
            boolean canDelete = Boolean.TRUE;
            for (MobileBookingForm mobileBookingForm : mobileBookingFormList) {
                if (mobileBookingForm.getBusiCtrl() != 0 && mobileBookingForm.getBusiCtrl() != -1 && mobileBookingForm.getBusiCtrl() != 50 && mobileBookingForm.getBusiCtrl() != 10) {
                    canDelete = Boolean.FALSE;
                    break;
                }
            }
            if (!canDelete) {
                throw new MobileStationBizException("已接单不能取消");
            }

            for (MobileBookingForm mobileBookingForm : mobileBookingFormList) {
                mobileBookingForm.setBusiCtrl(-1);
                mobileBookingForm.setEditDate(new Date());
                if (null != mobileStationCancelOrderBean.getAccountId()) {
                    mobileBookingForm.setEditUserId(mobileStationCancelOrderBean.getAccountId());
                }
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
            }
        }
        return mobileStationCancelOrderResBean;
    }


    /**
     * 插入跟踪日志
     *
     * @param waybillTraceOperateBean
     * @throws Exception
     */
    public void insertWaybillTrace(WaybillTraceOperateBean waybillTraceOperateBean) {
        ComWaybillTrace tmp;
        if (waybillTraceOperateBean != null) {
            if (StringUtil.isEmpty(waybillTraceOperateBean.getScheducarno())) {
                //订单，根据订单号获取订单信息，直接插入订单的跟踪日志
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(waybillTraceOperateBean.getBusiBookNo());
                tmp = new ComWaybillTrace();
                tmp.setAcctUsername(waybillTraceOperateBean.getAcctUsername());
                tmp.setBusiBookNo(waybillTraceOperateBean.getBusiBookNo());
                if (bookingForm != null) {
                    tmp.setWaybillNo(bookingForm.getWaybillNo());
                }
                tmp.setStartLocus(waybillTraceOperateBean.getStartLocus());
                tmp.setDestnLocus(waybillTraceOperateBean.getDestnLocus());
                tmp.setGrade(waybillTraceOperateBean.getGrade());
                tmp.setRemark(waybillTraceOperateBean.getRemark());
                tmp.setExecCode(waybillTraceOperateBean.getExecCode());
                if (waybillTraceOperateBean.getRealName() != null) {
                    tmp.setRealName(waybillTraceOperateBean.getRealName());
                }
                tmp.setRoleId(waybillTraceOperateBean.getRoleId());
                tmp.setStaDate(new Date());
                comWaybillTraceService.insert(tmp);
            } else {
                //派车单，需要插入派车单和所有子订单的跟踪日志
                List<BookingForm> bookingForms = bookingFormDaoEx.getBookingFormListByScheducarno(waybillTraceOperateBean.getScheducarno());
                for (int i = 0; i < bookingForms.size(); i++) {
                    tmp = new ComWaybillTrace();
                    tmp.setAcctUsername(waybillTraceOperateBean.getAcctUsername());
                    tmp.setBusiBookNo(bookingForms.get(i).getBusiBookNo());
                    tmp.setWaybillNo(bookingForms.get(i).getWaybillNo());
                    tmp.setStartLocus(waybillTraceOperateBean.getStartLocus());
                    tmp.setDestnLocus(waybillTraceOperateBean.getDestnLocus());
                    tmp.setGrade(waybillTraceOperateBean.getGrade());
                    tmp.setRemark(waybillTraceOperateBean.getRemark());
                    tmp.setExecCode(waybillTraceOperateBean.getExecCode());
                    tmp.setStaDate(new Date());
                    tmp.setHubNo(waybillTraceOperateBean.getScheducarno());
                    tmp.setRealName(waybillTraceOperateBean.getRealName());
                    tmp.setRoleId(waybillTraceOperateBean.getRoleId());
                    comWaybillTraceService.insert(tmp);
                }
            }
        }
    }

    /**
     * 接单接口
     *
     * @param recordMobiStaOrderRequest
     * @return
     * @throws MobileStationBizException
     * @title receiveMobileStationOrder
     * @author M.simple
     * @version 1.0
     * @datetime 2016年6月20日 上午11:27:51
     */
    @Transactional(rollbackFor = Exception.class)
    public AppBaseResult receiveMobileStationOrder(RecordMobileStationOrderRequest recordMobiStaOrderRequest)
            throws MobileStationBizException {

        AppBaseResult recordMobileStationOrder = new AppBaseResult();
        recordMobileStationOrder.setRetCode(SystemDefine.SUCCESS);
        recordMobileStationOrder.setRetMsg("mobilestaion接单成功");
        List<MobileScheduSubOrderBean> mobileScheduOrderList = new ArrayList<>();
        MobileBookingForm bookingForm = new MobileBookingForm();

        //广播单,需要同步订单数据
        if (recordMobiStaOrderRequest.isRecordBrocast()) {
            recordMobiStaOrderRequest.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
            recordMobileStationOrder = recordMobileStationOrder(recordMobiStaOrderRequest);

            bookingForm = convertBookingForm(recordMobiStaOrderRequest);
            bookingForm.setRevUser(recordMobiStaOrderRequest.getRevUserList().get(0).getRevUser());
            //bookingForm.setCreateDate(new Date());
            mobileScheduOrderList = recordMobiStaOrderRequest.getMobileScheduOrderList();
        } else {
            //指派单，订单数据已同步,更改订单数据状态(已接单)
            recordMobileStationOrder = receiveAppointOrder(recordMobiStaOrderRequest);
            MobileStationDbOrder mobileBookingForm = mobileStationOrderDetailDao.queryStationOrderDetail(recordMobiStaOrderRequest.getMobileBookingFormId());
            recordMobiStaOrderRequest.setRevUser(mobileBookingForm.getRevUser());

            bookingForm = convertBookingForm(mobileBookingForm);
//    		bookingForm.setRevUser(recordMobiStaOrderRequest.getRevUserList().get(0).getRevUser());
            mobileScheduOrderList = mobileBookingForm.getMobileScheduOrderList();
        }
        //记录接单日志
        recordReceiveLog(bookingForm, mobileScheduOrderList);

        return recordMobileStationOrder;
    }

    /**
     * 修改订单线路信息
     *
     * @param updateLineInfoReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public ModifyLineInfoResult modifyLineInfo(UpdateLineInfoReq updateLineInfoReq) throws MobileStationBizException {
        ModifyLineInfoResult baseResBean = new ModifyLineInfoResult(updateLineInfoReq);
        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        mobileBookingForm.setId(updateLineInfoReq.getOrderId());
        MobileBookingForm mbf = mobileBookingFormDao.selectByPrimaryKey(updateLineInfoReq.getOrderId());
        //如果是快递员POP-M时编辑订单，先编辑POP
        if (MobileStationDefine.POP.equals(mbf.getStartLocus()) && MobileStationDefine.M.equals(mbf.getDestnLocus())) {
            //获取M-POD的订单，并修改POD的地址
            MobileBookingForm mobileBookingFormMi = mobileBookingFormDaoEx.selectByConditions2(mbf.getBusiBookNo(), mbf.getCreateUser(), MobileStationDefine.MOBILE_ORDER_STATUS_TO_DO);
            if (mobileBookingFormMi != null) {
                if (null != updateLineInfoReq.getCneeProvince()) {
                    mobileBookingFormMi.setCneeCustProvide(updateLineInfoReq.getCneeProvince().toString());
                }
                if (null != updateLineInfoReq.getCneeCity()) {
                    mobileBookingFormMi.setCneeCustCity(updateLineInfoReq.getCneeCity().toString());
                }
                if (null != updateLineInfoReq.getCneeCounty()) {
                    mobileBookingFormMi.setCneeCustCounty(updateLineInfoReq.getCneeCounty().toString());
                }
                if (!StringUtil.isEmpty(updateLineInfoReq.getCneeCustAddr())) {
                    mobileBookingFormMi.setCneeCustAddr(updateLineInfoReq.getCneeCustAddr());
                }
                if (null != updateLineInfoReq.getCneeLongitude()) {
                    mobileBookingFormMi.setCneeLongitude(new BigDecimal(updateLineInfoReq.getCneeLongitude()));
                }
                if (null != updateLineInfoReq.getCneeLatitude()) {
                    mobileBookingFormMi.setCneeLatitude(new BigDecimal(updateLineInfoReq.getCneeLatitude()));
                }
                mobileBookingFormMi.setFormEditFlag(true);
                mobileBookingFormMi.setEditDate(new Date());
                mobileBookingFormMi.setEditUserId(updateLineInfoReq.getAccountId());
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingFormMi);
            }

            //更新POP-M的订单 POP地址信息
            mobileBookingForm.setCneeCustProvide(null);
            mobileBookingForm.setCneeCustCity(null);
            mobileBookingForm.setCneeCustCounty(null);
            mobileBookingForm.setCneeCustAddr(null);
            mobileBookingForm.setCneeLongitude(null);
            mobileBookingForm.setCneeLatitude(null);
        } else {
            //POP-POD 更新地址信息
            updateReq(updateLineInfoReq, mbf);
            if (null != updateLineInfoReq.getCneeProvince()) {
                mobileBookingForm.setCneeCustProvide(updateLineInfoReq.getCneeProvince().toString());
            }
            if (null != updateLineInfoReq.getCneeCity()) {
                mobileBookingForm.setCneeCustCity(updateLineInfoReq.getCneeCity().toString());
            }
            if (null != updateLineInfoReq.getCneeCounty()) {
                mobileBookingForm.setCneeCustCounty(updateLineInfoReq.getCneeCounty().toString());
            }
            if (!StringUtil.isEmpty(updateLineInfoReq.getCneeCustAddr())) {
                mobileBookingForm.setCneeCustAddr(updateLineInfoReq.getCneeCustAddr());
            }
            if (null != updateLineInfoReq.getCneeLongitude()) {
                mobileBookingForm.setCneeLongitude(new BigDecimal(updateLineInfoReq.getCneeLongitude()));
            }
            if (null != updateLineInfoReq.getCneeLatitude()) {
                mobileBookingForm.setCneeLatitude(new BigDecimal(updateLineInfoReq.getCneeLatitude()));
            }
        }

        if (null != updateLineInfoReq.getShipProvince()) {
            mobileBookingForm.setShipCustProvide(updateLineInfoReq.getShipProvince().toString());
        }
        if (null != updateLineInfoReq.getShipCity()) {
            mobileBookingForm.setShipCustCity(updateLineInfoReq.getShipCity().toString());
        }
        if (null != updateLineInfoReq.getShipCounty()) {
            mobileBookingForm.setShipCustCounty(updateLineInfoReq.getShipCounty().toString());
        }
        if (!StringUtil.isEmpty(updateLineInfoReq.getShipCustAddr())) {
            mobileBookingForm.setShipCustAddr(updateLineInfoReq.getShipCustAddr());
        }
        if (null != updateLineInfoReq.getShipLatitude()) {
            mobileBookingForm.setShipLatitude(new BigDecimal(updateLineInfoReq.getShipLatitude()));
        }
        if (null != updateLineInfoReq.getShipLongitude()) {
            mobileBookingForm.setShipLongitude(new BigDecimal(updateLineInfoReq.getShipLongitude()));
        }
        mobileBookingForm.setFormEditFlag(true);
        mobileBookingForm.setEditDate(new Date());
        mobileBookingForm.setEditUserId(updateLineInfoReq.getAccountId());
        if (!MobileStationDefine.PRODUCT_TYPE_TCZS.equals(mobileBookingForm.getProductType())) {
            try {
                BaseRequestResult baseRequestResult = mobileRecOrderWebService.checkRouteExists(Double.parseDouble(updateLineInfoReq.getShipLongitude()),
                        Double.parseDouble(updateLineInfoReq.getShipLatitude()), Double.parseDouble(updateLineInfoReq.getCneeLongitude()), Double.parseDouble(updateLineInfoReq.getCneeLatitude()));
                if (baseRequestResult == null || baseRequestResult.getStatus() != 1) {
                    throw new MobileStationBizException(baseRequestResult.getMesasge());
                }
            } catch (com.gistandard.transport.order.webservice.client.merchant.order.Exception_Exception e) {
                e.printStackTrace();
                throw new MobileStationBizException(e.getMessage());
            }
        }

        if (mbf != null) {
            BookingForm bf = bookingFormDaoEx.getBookingFormByBusiNo(mbf.getBusiBookNo());
            if (bf != null) {
                bf.setCarriageReceiLongitude(new BigDecimal(updateLineInfoReq.getShipLongitude()));
                bf.setCarriageReceiLatitude(new BigDecimal(updateLineInfoReq.getShipLatitude()));
                bf.setCarriageDelivLongitude(new BigDecimal(updateLineInfoReq.getCneeLongitude()));
                bf.setCarriageDelivLatitude(new BigDecimal(updateLineInfoReq.getCneeLatitude()));
                if (null != updateLineInfoReq.getShipProvince()) {
                    bf.setCarriageReceiProvince(updateLineInfoReq.getShipProvince().toString());
                }
                if (null != updateLineInfoReq.getShipCity()) {
                    bf.setCarriageReceiCity(updateLineInfoReq.getShipCity().toString());
                }
                if (null != updateLineInfoReq.getShipCounty()) {
                    bf.setCarriageReceiCounty(updateLineInfoReq.getShipCounty().toString());
                }
                if (null != updateLineInfoReq.getCneeProvince()) {
                    bf.setCarriageDelivProvince(updateLineInfoReq.getCneeProvince().toString());
                }
                if (null != updateLineInfoReq.getCneeCity()) {
                    bf.setCarriageDelivCity(updateLineInfoReq.getCneeCity().toString());
                }
                if (null != updateLineInfoReq.getCneeCounty()) {
                    bf.setCarriageDelivCounty(updateLineInfoReq.getCneeCounty().toString());
                }
                if (null != updateLineInfoReq.getShipCustAddr()) {
                    bf.setShipCustHouseNumber(updateLineInfoReq.getShipCustAddr());
                }
                if (null != updateLineInfoReq.getShipCustAddr()) {
                    bf.setCarriageReceiAddr(updateLineInfoReq.getShipCustAddr());
                }
                if (null != updateLineInfoReq.getCneeCustAddr()) {
                    bf.setCneeCustHouseNumber(updateLineInfoReq.getCneeCustAddr());
                }
                if (null != updateLineInfoReq.getCneeCustAddr()) {
                    bf.setCarriageDelivAddr(updateLineInfoReq.getCneeCustAddr());
                }
                bf.setMileage(updateLineInfoReq.getMileage());
                //add  by yujie 20170310 编辑路线重新查询报价，回写订单表
                PlatFormInModel platFormInModel = new PlatFormInModel();
                platFormInModel.setItemCode(bf.getTransportType());
                platFormInModel.setFromCountryCode("142");
                platFormInModel.setFromProvinceCode(bf.getCarriageReceiProvince());
                platFormInModel.setFromCityCode(bf.getCarriageReceiCity());
                platFormInModel.setFromCountyCode(bf.getCarriageReceiCounty());
                platFormInModel.setArriveCountryCode("142");
                platFormInModel.setArriveProvinceCode(bf.getCarriageDelivProvince());
                platFormInModel.setArriveCityCode(bf.getCarriageDelivCity());
                platFormInModel.setArriveCountyCode(bf.getCarriageDelivCounty());
                if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(bf.getTransportType())) {
                    platFormInModel.setQuoteType(MobileStationDefine.QUOTE_TYPE_SPECIAL_DELIVERY);
                    platFormInModel.setMileage(updateLineInfoReq.getMileage());
                } else {
                    platFormInModel.setQuoteType(MobileStationDefine.QUOTE_TYPE_EXPRESS);
                }
                if (bf.getWhtGrosswht() != null && bf.getWhtVolcbm() != null) {
                    if (bf.getWhtVolwht() != null) {
                        bf.setWhtFeewht(bf.getWhtGrosswht().compareTo(bf.getWhtVolwht()) > 0 ? bf.getWhtGrosswht() : bf.getWhtVolwht());
                    } else {
                        bf.setWhtFeewht(bf.getWhtGrosswht());
                    }
                    platFormInModel.setWeight(bf.getWhtFeewht());
                    platFormInModel.setVolume(bf.getWhtVolcbm());
                    try {
                        PlatFormOutModel platFormOutModel = queryCalcManagerWebService.getQuote(platFormInModel);
                        if (platFormOutModel.getStatus() != null && "0".equals(platFormOutModel.getStatus())) {
                            List<PlatFormDetailModel> quoteDetailList = platFormOutModel.getQuoteDetailList();
                            if (quoteDetailList != null && quoteDetailList.size() > 0) {
                                PlatFormDetailModel platFormDetailModel = quoteDetailList.get(0);
                                logger.info("edit line info ,busiNo : {}, predictValue : {}, docNo : {}", bf.getBusiBookNo(),
                                        platFormDetailModel.getPredictValue(), platFormDetailModel.getQuoteNo());
                                bf.setPredictValue(platFormDetailModel.getPredictValueAfterTax());
                                bf.setDocno(platFormDetailModel.getQuoteNo());
                                bf.setPredictCurr(platFormDetailModel.getCurrency());
                                baseResBean.setData(bf);
                            }
                        }
                    } catch (com.gistandard.transport.system.webservice.client.payinfo.Exception_Exception e) {
                        throw new MobileStationBizException(MobileStationRetCode.PRICE_QUERY_ERROR, e);
                    }
                }
                bookingFormDao.updateByPrimaryKey(bf);
                baseResBean.setData(bf);
            }
        }

        //查询状态
        int flag = mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
        if (flag > 0) {
            baseResBean.setRetCode(SystemDefine.SUCCESS);
        } else {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("修改订单线路信息失败！");
        }
        return baseResBean;
    }

    /**
     * 结算完成后更新MobileBookingForm的结算对账单号
     *
     * @param updateValidBillNoReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public AppBaseResult updateValidBillno(UpdateValidBillNoReq updateValidBillNoReq) throws MobileStationBizException {
        AppBaseResult appBaseResult = new AppBaseResult();
        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        mobileBookingForm.setId(updateValidBillNoReq.getOrderId());
        mobileBookingForm.setValidBillno(updateValidBillNoReq.getValidBillNo());
        if (updateValidBillNoReq.getPredictValue() != null) {
            mobileBookingForm.setPredictValue(updateValidBillNoReq.getPredictValue());
        }
        if (!StringUtil.isEmpty(updateValidBillNoReq.getPredictCurr())) {
            mobileBookingForm.setPredictCurr(updateValidBillNoReq.getPredictCurr());
        }
        int flag = mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
        if (flag > 0) {
            appBaseResult.setRetCode(SystemDefine.SUCCESS);
        } else {
            appBaseResult.setRetCode(SystemDefine.FAILURE);
            appBaseResult.setRetMsg("更新结算对账单号失败！");
        }
        return appBaseResult;
    }

    @Override
    public AppBaseResult updateStaffRole(UpdateStaffRoleReq updateStaffRoleReq) throws Exception {
        AppBaseResult appBaseResult = new AppBaseResult();
        if (updateStaffRoleReq != null && updateStaffRoleReq.getRoleIds() != null) {
            MobileMoudleRel record = new MobileMoudleRel();
            record.setAccountId(updateStaffRoleReq.getStaffAccountId());
            record.setCompanyId(updateStaffRoleReq.getCompanyAccountId());
            //移除所有的该企业角色后，重新添加
            mobileMoudleRelDaoEx.removeStaffRole(record);

            String[] newRoles = updateStaffRoleReq.getRoleIds().split(",");
            if (newRoles != null && newRoles.length > 0) {
                ComAccount staffAccount = comAccountDao.selectByPrimaryKey(updateStaffRoleReq.getStaffAccountId());
                ComAccount companyAccount = comAccountDao.selectByPrimaryKey(updateStaffRoleReq.getCompanyAccountId());
                for (String newRole : newRoles) {
                    record = new MobileMoudleRel();
                    record.setAccountId(updateStaffRoleReq.getStaffAccountId());
                    record.setCompanyId(updateStaffRoleReq.getCompanyAccountId());
                    record.setIsOn(1);
                    if (companyAccount != null) {
                        record.setCompanyCode(companyAccount.getAcctUsername());
                    }
                    record.setAcctUsername(staffAccount.getAcctUsername());
                    record.setCreateDate(new Date());
                    record.setMoudleStatus(1);
                    //需要新增的角色
                    if ("23".equals(newRole)) {
                        record.setMoudleCode(MobileStationDefine.PRODUCT_TYPE_OTCKDM);
                        record.setMoudleName("咪站");
                    } else if ("3".equals(newRole)) {
                        record.setMoudleCode(MobileStationDefine.PRODUCT_TYPE_TCYS);
                        record.setMoudleName("同城运输");
                    } else if ("7".equals(newRole)) {
                        record.setMoudleCode(MobileStationDefine.PRODUCT_TYPE_TCKD);
                        record.setMoudleName("同城快递");
                    } else {
                        continue;
                    }
                    mobileMoudleRelDao.insert(record);
                }
            }
        }

        return appBaseResult;
    }

    @Override
    public AppBaseResult removeStaff(RemoveStaffReq removeStaffReq) throws Exception {
        AppBaseResult appBaseResult = new AppBaseResult();
        MobileMoudleRel record = new MobileMoudleRel();
        record.setAccountId(removeStaffReq.getStaffAccountId());
        record.setCompanyId(removeStaffReq.getCompanyAccountId());
        mobileMoudleRelDaoEx.removeStaffRole(record);
        return appBaseResult;
    }

    private void updateReq(UpdateLineInfoReq updateLineInfoReq, MobileBookingForm mbf) {
        if (StringUtils.isBlank(updateLineInfoReq.getCneeCustAddr())) {
            updateLineInfoReq.setCneeCustAddr(mbf.getCneeCustAddr());
            updateLineInfoReq.setCneeLatitude(String.valueOf(mbf.getCneeLatitude()));
            updateLineInfoReq.setCneeLongitude(String.valueOf(mbf.getCneeLongitude()));
        } else if (StringUtils.isBlank(updateLineInfoReq.getShipCustAddr())) {
            updateLineInfoReq.setShipCustAddr(mbf.getShipCustAddr());
            updateLineInfoReq.setShipLatitude(String.valueOf(mbf.getShipLatitude()));
            updateLineInfoReq.setShipLongitude(String.valueOf(mbf.getShipLongitude()));
        }
    }

    /**
     * 记录customer接单日志
     *
     * @param bookingForm
     * @param mobileScheduOrderList
     * @throws MobileStationBizException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private void recordReceiveLog(MobileBookingForm bookingForm, List<MobileScheduSubOrderBean> mobileScheduOrderList) throws MobileStationBizException {

        ComWaybillTrace comWaybillTrace = new ComWaybillTrace();
        if (null == bookingForm.getBusiBookNo()) {
            throw new MobileStationBizException("订单busiBookNo不能为空");
        }
        if (null == bookingForm.getRevUser()) {
            throw new MobileStationBizException("接单人不能为空");
        }
        if (bookingForm.getRoleId() == null) {
            if (MobileStationDefine.PRODUCT_TYPE_ITCKD.equals(bookingForm.getProductType())
                    || MobileStationDefine.PRODUCT_TYPE_TCKD.equals(bookingForm.getProductType())) {
                bookingForm.setRoleId(7);
            }
            if (MobileStationDefine.PRODUCT_TYPE_ITCYS.equals(bookingForm.getProductType())
                    || MobileStationDefine.PRODUCT_TYPE_TCYS.equals(bookingForm.getProductType())) {
                bookingForm.setRoleId(3);
            }
        }
        ComAccount comAccount = comAccountService.queryAccountByAcctUsername(bookingForm.getRevUser());
        comWaybillTrace.setAcctUsername(bookingForm.getRevUser());
        if (comAccount != null) {
            comWaybillTrace.setRealName(comAccount.getRealName());
        }
        comWaybillTrace.setBusiBookNo(bookingForm.getBusiBookNo());
        comWaybillTrace.setHubNo(bookingForm.getScheducarno());
        comWaybillTrace.setStartLocus(bookingForm.getStartLocus());
        comWaybillTrace.setDestnLocus(bookingForm.getDestnLocus());
        comWaybillTrace.setGrade(0);
        if (bookingForm.getRoleId() != null && comAccount != null) {
            comWaybillTrace.setRemark(SysAccountRole.getName(bookingForm.getRoleId().intValue()) + "已接单，联系电话：" + comAccount.getTelephone());
        } else {
            comWaybillTrace.setRemark("已接单");
        }

        comWaybillTrace.setExecCode(WayBillStatusDefine.MS_AGREE_O.getIntValue());
        comWaybillTrace.setStaDate(new Date());
        comWaybillTrace.setRoleId(bookingForm.getRoleId());
        comWaybillTraceDao.insert(comWaybillTrace);

        //如果存在子订单数据 记录子订单日志
        if (null != mobileScheduOrderList) {
            for (MobileScheduSubOrderBean mobileScheduSubOrderBean : mobileScheduOrderList) {
                if (null == mobileScheduSubOrderBean.getBusiBookNo()) {
                    throw new MobileStationBizException("订单busiBookNo不能为空");
                }
                comAccount = comAccountService.queryAccountByAcctUsername(bookingForm.getRevUser());
                ComWaybillTrace comWaybillTraceSub = new ComWaybillTrace();
                comWaybillTraceSub.setAcctUsername(bookingForm.getRevUser());
                if (comAccount != null) {
                    comWaybillTraceSub.setRealName(comAccount.getRealName());
                }
                comWaybillTraceSub.setStartLocus(bookingForm.getStartLocus());
                comWaybillTraceSub.setDestnLocus(bookingForm.getDestnLocus());
                comWaybillTraceSub.setBusiBookNo(mobileScheduSubOrderBean.getBusiBookNo());
                comWaybillTraceSub.setHubNo(mobileScheduSubOrderBean.getScheducarno());
                comWaybillTraceSub.setGrade(0);
                comWaybillTraceSub.setRemark(WayBillStatusDefine.MS_AGREE_O.getName());
                comWaybillTraceSub.setExecCode(WayBillStatusDefine.MS_AGREE_O.getIntValue());
                comWaybillTraceSub.setStaDate(new Date());
                comWaybillTraceSub.setRoleId(bookingForm.getRoleId());
                comWaybillTraceDao.insert(comWaybillTraceSub);
            }
        }
    }

    /**
     * 指派单接单接口
     *
     * @param recordMobiStaOrderRequest
     * @return
     * @title receiveAppointOrder
     * @author M.simple
     * @version 3.0
     * @datetime 2016年6月20日 下午2:35:29
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private AppBaseResult receiveAppointOrder(RecordMobileStationOrderRequest recordMobiStaOrderRequest) {

        AppBaseResult recordMobileStationOrder = new AppBaseResult();
        recordMobileStationOrder.setRetCode(SystemDefine.SUCCESS);
        recordMobileStationOrder.setRetMsg("mobilestaion接单成功");

        try {

            MobileBookingForm mobileBookingForm = new MobileBookingForm();
            mobileBookingForm.setId(recordMobiStaOrderRequest.getMobileBookingFormId());
            mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
            mobileBookingForm.setRevDate(new Date());
            mobileBookingForm.setNarrate(recordMobiStaOrderRequest.getNarrate());
            if (recordMobiStaOrderRequest.getRevCompanyId() != null && recordMobiStaOrderRequest.getRevCompanyId() > 0) {
                mobileBookingForm.setRevCompanyId(recordMobiStaOrderRequest.getRevCompanyId());
            }
            //mobileBookingForm数据更新已接单状态
            int update = mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
            if (update <= 0) {
                recordMobileStationOrder.setRetCode(SystemDefine.FAILURE);
                recordMobileStationOrder.setRetMsg("mobilestaion接单失败");
                return recordMobileStationOrder;
            }

            //如果是内部单，顺便更改子订单状态
            if (RecordOrderType.ORDER_I.equals(recordMobiStaOrderRequest.getRecordOrderType())) {
//				mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(recordMobiStaOrderRequest.getMobileBookingFormId());
                MobileScheduSubOrder record = new MobileScheduSubOrder();
                record.setMobileBookingFormId(mobileBookingForm.getId());
                //子订单设置已接单状态
                record.setBusiCtrl(1);
                mobileScheduSubOrderDaoEx.updateByMoblieBookingFormId(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
            recordMobileStationOrder.setRetCode(SystemDefine.FAILURE);
            recordMobileStationOrder.setRetMsg(e.getMessage());
        }

        return recordMobileStationOrder;
    }

    /**
     * mobilestation 3.0下单接口(包含I单和O单逻辑)
     * 指派单才存在下单逻辑，广播单直接接单
     *
     * @param recordMobiStaOrderRequest
     * @return
     * @title recordMobileStationOrder
     * @author M.simple
     * @version 3.0
     * @datetime 2016年6月13日 下午6:06:06
     */
    @Transactional(rollbackFor = MobileStationBizException.class)
    public MobileStationOrderResult recordMobileStationOrder(RecordMobileStationOrderRequest recordMobiStaOrderRequest)
            throws MobileStationBizException {
        MobileStationOrderResult mobileStationOrderResBean = new MobileStationOrderResult();
        try {
            List<RevUserBean> revUserBeanList = recordMobiStaOrderRequest.getRevUserList();

            MobileBookingForm mobileBookingForm = convertBookingForm(recordMobiStaOrderRequest);
            if (null != revUserBeanList && revUserBeanList.size() > 0) {
                RevUserBean revUser = revUserBeanList.get(0);
                mobileBookingForm.setId(null);
                mobileBookingForm.setRevUserId(revUser.getRevUserId());
                mobileBookingForm.setRevUser(revUser.getRevUser());
                mobileBookingForm.setNarrate(recordMobiStaOrderRequest.getNarrate());
                if (null == recordMobiStaOrderRequest.getBusiCtrl()) {
                    // 不传默认：待接单,并写入下单时间
                    mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
                    mobileBookingForm.setBookingDate(new Date());
                } else if (MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER == recordMobiStaOrderRequest.getBusiCtrl()) {
                    //状态为1，赋值接单时间
                    mobileBookingForm.setRevDate(new Date());
                    mobileBookingForm.setBookingDate(new Date());
                } else if (MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS == recordMobiStaOrderRequest.getBusiCtrl() || MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN == recordMobiStaOrderRequest.getBusiCtrl()) {
                    //自理下单，赋值接单时间
                    mobileBookingForm.setRevDate(new Date());
                }
                //默认0
                if (null == recordMobiStaOrderRequest.getBusiCtrl()) {
                    mobileBookingForm.setIsJs(0);
                }

                //若没有传输createDate过来，则设置时间为当前时间
                if (recordMobiStaOrderRequest.getCreateDate() == null) {
                    mobileBookingForm.setCreateDate(new Date());
                } else {
                    mobileBookingForm.setCreateDate(recordMobiStaOrderRequest.getCreateDate());
                }

                //如果备份的是O单的数据
                if (RecordOrderType.ORDER_O.getValue().equals(recordMobiStaOrderRequest.getRecordOrderType())) {
                    MobileStationOrderResult recordOutOrder = recordOuterOrder(mobileBookingForm, recordMobiStaOrderRequest);
                    mobileStationOrderResBean.setRetCode(recordOutOrder.getRetCode());
                    mobileStationOrderResBean.setRetMsg(recordOutOrder.getRetMsg());
                    mobileStationOrderResBean.setData(recordOutOrder.getData());
                } else if (RecordOrderType.ORDER_I.getValue().equals(recordMobiStaOrderRequest.getRecordOrderType())) {
                    //备份I单的处理逻辑
                    MobileStationOrderResult recordInnerOrder = recordInnerOrder(mobileBookingForm, recordMobiStaOrderRequest);
                    mobileStationOrderResBean.setRetCode(recordInnerOrder.getRetCode());
                    mobileStationOrderResBean.setRetMsg(recordInnerOrder.getRetMsg());
                    mobileStationOrderResBean.setData(recordInnerOrder.getData());
                }
            } else {
                throw new MobileStationBizException("订单指派人不能为空");
            }
        } catch (MobileStationBizException e) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            mobileStationOrderResBean.setRetMsg(e.getMessage());
        }

        return mobileStationOrderResBean;
    }

    @Override
    @Transactional
    public MobileStationOrderResult recordMWMobileStationOrder(RecordMWMobileStationOrderRequest recordMWMobileStationOrderRequest) {
        logger.info("下订单W站指派给M站(非派车单) recordMWMobileStationOrder {}", JSON.toJSONString(recordMWMobileStationOrderRequest));
        MobileStationOrderResult mobileStationOrderResBean = new MobileStationOrderResult();
        try {
            ComAccount mcomAccount = comAccountService.queryAccountById(recordMWMobileStationOrderRequest.getMId());
            if (mcomAccount == null)
                throw new MobileStationBizException("咪站不是平台账号");
            String macctUsername = mcomAccount.getAcctUsername();
            ComCustomer comCustomer = comCustomerDao.selectByPrimaryKey(recordMWMobileStationOrderRequest.getWId());
            if (comCustomer == null)
                throw new MobileStationBizException("W站账户信息不存在");
            Integer accountId = comCustomer.getAccountId();
            ComAccount wcomAccount = comAccountService.queryAccountById(accountId);
            if (wcomAccount == null)
                throw new MobileStationBizException("W站不是平台账号");
            String wacctUsername = wcomAccount.getAcctUsername();
//            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByConditions(recordMWMobileStationOrderRequest.getBusiBookNo(), null, null);
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(recordMWMobileStationOrderRequest.getBusiBookNo());
            if (bookingForm == null)
                throw new MobileStationBizException("订单不存在");
            // 生产新的W-M的订单
            MobileBookingForm assignA = new MobileBookingForm();
            assignA.setBookingFormId(bookingForm.getId());
            assignA.setBusiBookNo(bookingForm.getBusiBookNo());
            assignA.setCneeCustLinkMan(bookingForm.getCneeCustLinkMan());
            if (bookingForm.getGoodsValue() != null) {
                assignA.setGoodsValue(bookingForm.getGoodsValue());
            }
            if (bookingForm.getPredictValue() != null) {
                assignA.setPremiumValue(bookingForm.getPremiumValue());
            }
            if (bookingForm.getNeedInsure() != null) {
                assignA.setNeedInsure(bookingForm.getNeedInsure());
            }
            if (bookingForm.getGoodsValue() != null) {
                assignA.setGoodsValue(bookingForm.getGoodsValue());
            }
            //设置目的地咪站的经度
            if (recordMWMobileStationOrderRequest.getLatitude() != null) {
                assignA.setCneeLatitude(recordMWMobileStationOrderRequest.getLatitude());
            } else {
                assignA.setCneeLatitude(bookingForm.getCarriageDelivLatitude());
            }
            //设置目的地咪站的纬度
            if (recordMWMobileStationOrderRequest.getLongitude() != null) {
                assignA.setCneeLongitude(recordMWMobileStationOrderRequest.getLongitude());
            } else {
                assignA.setCneeLongitude(bookingForm.getCarriageDelivLongitude());
            }
            assignA.setCneeCustLinkTel(bookingForm.getCneeCustLinkTel());

            //设置目的地咪站地址
            if (!StringUtil.isEmpty(recordMWMobileStationOrderRequest.getAddress())) {
                assignA.setCneeCustAddr(recordMWMobileStationOrderRequest.getAddress());
            } else {
                assignA.setCneeCustAddr(bookingForm.getCarriageDelivAddr());
            }

            //设置目的地咪站省
            if (!StringUtil.isEmpty(recordMWMobileStationOrderRequest.getProvinceName())) {
                Map<String, ComProvince> comProvinceMap = comProvinceService.queryNameForMap();
                if (comProvinceMap.get(recordMWMobileStationOrderRequest.getProvinceName()) != null) {
                    assignA.setCneeCustProvide(comProvinceMap.get(recordMWMobileStationOrderRequest.getProvinceName()).getId().toString());
                }
            } else {
                assignA.setCneeCustProvide(bookingForm.getCarriageDelivProvince());
            }

            //设置目的地咪站市
            if (!StringUtil.isEmpty(recordMWMobileStationOrderRequest.getCityName())) {
                Map<String, ComCity> comCityMap = comCityService.queryNameForMap();
                if (comCityMap.get(recordMWMobileStationOrderRequest.getCityName()) != null) {
                    assignA.setCneeCustCity(comCityMap.get(recordMWMobileStationOrderRequest.getCityName()).getId().toString());

                    //设置目的地咪站区
                    if (!StringUtil.isEmpty(recordMWMobileStationOrderRequest.getCountyName())) {
                        ComCounty comCounty = comCountyDaoEx.queryByParams(comCityMap.get(recordMWMobileStationOrderRequest.getCityName()).getId().toString(), recordMWMobileStationOrderRequest.getCountyName());
                        assignA.setCneeCustCounty(comCounty.getAreaName());
                    } else {
                        assignA.setCneeCustCounty(bookingForm.getCarriageDelivCounty());
                    }
                }
            } else {
                assignA.setCneeCustCity(bookingForm.getCarriageDelivCity());
                assignA.setCneeCustCounty(bookingForm.getCarriageDelivCounty());
            }

            assignA.setBookingDate(new Date());
            assignA.setTransportType(2);  //快递
            assignA.setOrderType(2);// 都是派件单
            assignA.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
            assignA.setCreateDate(new Date());
            assignA.setCreateUserId(accountId);
            assignA.setCreateUser(wacctUsername);
            assignA.setRevUserId(recordMWMobileStationOrderRequest.getMId());
            assignA.setRevUser(macctUsername);
            assignA.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU);  //运输指派单
            assignA.setProductType(recordMWMobileStationOrderRequest.getProductType());
            assignA.setRoleId(23);
            assignA.setIsJs(0);
            assignA.setRevDate(null);
            assignA.setEditDate(null);
            assignA.setEditUser(null);
            assignA.setId(null);
            assignA.setEditUserId(null);
            assignA.setFormEditFlag(null);
            //发货地址 M
            ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(recordMWMobileStationOrderRequest.getMId());
            assignA.setShipCustProvide(comUserinfo.getProvince());
            assignA.setShipCustCity(comUserinfo.getCity());
            assignA.setShipCustCounty(comUserinfo.getCounty());
            assignA.setShipCustAddr(comUserinfo.getAddress() + comUserinfo.getDetailAdd());
            assignA.setShipCustLinkMan(comUserinfo.getRealName());
            assignA.setShipCustLinkTel(comUserinfo.getTelephone());
            assignA.setShipLongitude(comUserinfo.getStaLongitude());
            assignA.setShipLatitude(comUserinfo.getStaLatitude());
            assignA.setStartLocus(MobileStationDefine.M);
            assignA.setStartLocusId(comUserinfo.getAccountId());
            assignA.setDestnLocus(MobileStationDefine.POD);

            PlatformQuote platformQuote = expressService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG, assignA.getBusiBookNo(), assignA.getStartLocus(), assignA.getDestnLocus(), assignA.getRoleId());
            if (platformQuote != null) {
//                    mobileBookingForm.setComQuoteId(platformQuote.getQuoteNo());
//                    mobileBookingForm.setQuotedType(platformQuote.getQuoteType());
                assignA.setPredictValue(platformQuote.getPrice());
                assignA.setPredictCurr(platformQuote.getCurrencyCode());
            }
            mobileBookingFormDao.insert(assignA);
            // 生成货物信息
            List<MobileGoodsDtl> recordList = new ArrayList<MobileGoodsDtl>();
            List<BillingFormSalm> billingFormSalms = billingFormSalmDaoEx.queryByBusiBooknoId(bookingForm.getId());
            for (BillingFormSalm billingFormSalm : billingFormSalms) {
                MobileGoodsDtl mobileGoodsDtl = createMobileGoodsDtl(assignA, billingFormSalm);
                recordList.add(mobileGoodsDtl);
            }
            if (recordList != null && recordList.size() > 0) {
                mobileGoodsDtlDaoEx.batchInsert(recordList);
            }
        } catch (MobileStationBizException e) {
            e.printStackTrace();
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            mobileStationOrderResBean.setRetMsg(e.getMessage());
        }
        return mobileStationOrderResBean;
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

    /**
     * 记录O单数据信息
     *
     * @param recordMobiStaOrderRequest
     * @return
     * @throws MobileStationBizException
     * @title recordOutOrder
     * @author M.simple
     * @version 1.0
     * @datetime 2016年6月14日 上午11:24:52
     */
    private MobileStationOrderResult recordOuterOrder(MobileBookingForm mobileBookingForm, RecordMobileStationOrderRequest recordMobiStaOrderRequest)
            throws MobileStationBizException {

        MobileStationOrderResult mobileStationOrderResBean = new MobileStationOrderResult();
        mobileStationOrderResBean.setRetCode(SystemDefine.SUCCESS);

        //如果是O单，不存在子订单
        List<MobileGoodsDtl> mobileGoodsInfoList = recordMobiStaOrderRequest.getMobileGoodDtlList();
        // 检查下单数据是否合法
        if (check(mobileBookingForm, mobileStationOrderResBean)) {
            int result = mobileBookingFormDao.insert(mobileBookingForm);
            recordMobiStaOrderRequest.setMobileBookingFormId(mobileBookingForm.getId());
            mobileStationOrderResBean.setData(mobileBookingForm);
            if (result <= 0) {
                throw new MobileStationBizException("新增订单失败！");
            }
            //货物信息下单可以不填
            if (mobileGoodsInfoList != null && mobileGoodsInfoList.size() > 0) {
                for (MobileGoodsDtl mobileGoodsInfo : mobileGoodsInfoList) {
                    mobileGoodsInfo.setMobileBookingFormId(mobileBookingForm.getId());
                    mobileGoodsInfo.setCreateDate(new Date());
                    if (null != mobileBookingForm.getCreateUser()) {
                        mobileGoodsInfo.setCreateUser(mobileBookingForm.getCreateUser());
                    }
                }
                result = mobileGoodsDtlDaoEx.batchInsert(mobileGoodsInfoList);
                if (result <= 0) {
                    throw new MobileStationBizException("新增货物信息失败！");
                }
            }
        } else {
            throw new MobileStationBizException("新增订单失败！");
        }

        return mobileStationOrderResBean;
    }

    /**
     * 组装booking_form数据
     *
     * @param recordMobiStaOrderRequest
     * @return
     * @throws MobileStationBizException
     * @title convertBookingForm
     * @author M.simple
     * @version 1.0
     * @datetime 2016年6月14日 上午11:20:02
     */
    private MobileBookingForm convertBookingForm(Object recordMobiStaOrderRequest)
            throws MobileStationBizException {

        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        try {
            PropertyUtils.copyProperties(mobileBookingForm, recordMobiStaOrderRequest);
            if (recordMobiStaOrderRequest instanceof RecordMobileStationOrderRequest) {
                List<RevUserBean> revUserList = ((RecordMobileStationOrderRequest) recordMobiStaOrderRequest).getRevUserList();
                mobileBookingForm.setRevUserId(revUserList.get(0).getRevUserId());
                mobileBookingForm.setRevUser(revUserList.get(0).getRevUser());
                mobileBookingForm.setCreateDate(new Date());
            }
            //判断是否为自理下单，如果不是，把接单日期清空,自理下单的下单人和接单人是同一个人
            if (mobileBookingForm.getCreateUserId() != mobileBookingForm.getRevUserId()) {
                mobileBookingForm.setRevDate(null);
            }
            //接驳单状态，默认情况为0
            mobileBookingForm.setConnectionStatus(0);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MobileStationBizException("参数格式异常");
        }
        return mobileBookingForm;
    }

    /**
     * 记录I单信息
     *
     * @param recordMobiStaOrderRequest
     * @throws MobileStationBizException
     * @title recordInnerOrder
     * @author M.simple
     * @version 1.0
     * @datetime 2016年6月14日 上午11:09:59
     */
    private MobileStationOrderResult recordInnerOrder(MobileBookingForm mobileBookingForm, RecordMobileStationOrderRequest recordMobiStaOrderRequest)
            throws MobileStationBizException {

        MobileStationOrderResult mobileStationOrderResBean = new MobileStationOrderResult();
        mobileStationOrderResBean.setRetCode(SystemDefine.SUCCESS);

        //1.获取I单子订单
        List<MobileScheduSubOrderBean> mobileScheduOrderList = recordMobiStaOrderRequest.getMobileScheduOrderList();
        //去除子订单校验
        if (checkBookingForm(mobileBookingForm, mobileStationOrderResBean)) {
            //1.1记录主订单数据
            int result = mobileBookingFormDao.insert(mobileBookingForm);
            recordMobiStaOrderRequest.setMobileBookingFormId(mobileBookingForm.getId());
            mobileStationOrderResBean.setData(mobileBookingForm);
            if (result <= 0) {
                throw new MobileStationBizException("新增订单失败！");
            }
            for (MobileScheduSubOrderBean mobileScheduOrderBean : mobileScheduOrderList) {
                //设置初始值
                mobileScheduOrderBean.setIsJs(0);
                //如果是广播单接单逻辑,子订单状态同步为已接单状态
                if (mobileBookingForm.getBusiCtrl() == 1) {
                    mobileScheduOrderBean.setBusiCtrl(1);
                } else {
                    mobileScheduOrderBean.setBusiCtrl(0);
                }

                //2.获取子订单物品
                List<MobileGoodsDtl> mobileGoodDtlList = mobileScheduOrderBean.getMobileGoodDtlList();
                //2.1校验货物信息
                if (!mobileBookingForm.getStartLocus().equals(MobileStationDefine.POP)) {
                    if (checkMobileGood(mobileGoodDtlList, mobileStationOrderResBean)) {
                        MobileScheduSubOrder mobileScheduSubOrder = convertMobileSubOrder(mobileScheduOrderBean);
                        mobileScheduSubOrder.setMobileBookingFormId(mobileBookingForm.getId());
                        int insertMobiScheduOrder = mobileScheduSubOrderDao.insert(mobileScheduSubOrder);
                        if (insertMobiScheduOrder <= 0) {
                            throw new MobileStationBizException("新增订单失败！");
                        }
                        //2.2组装货物信息
                        for (MobileGoodsDtl mobileGoodsDtl : mobileGoodDtlList) {
                            mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
                            mobileGoodsDtl.setMobileScheduOrderId(mobileScheduSubOrder.getId());
                            mobileGoodsDtl.setCreateDate(new Date());
                            if (null != mobileBookingForm.getCreateUser()) {
                                mobileGoodsDtl.setCreateUser(mobileBookingForm.getCreateUser());
                            }
                        }
                        result = mobileGoodsDtlDaoEx.batchInsert(mobileGoodDtlList);
                        if (result <= 0) {
                            throw new MobileStationBizException("插入货物信息失败！");
                        }
                    } else {
                        throw new MobileStationBizException("新增订单失败！");
                    }
                } else {
                    MobileScheduSubOrder mobileScheduSubOrder = convertMobileSubOrder(mobileScheduOrderBean);
                    mobileScheduSubOrder.setMobileBookingFormId(mobileBookingForm.getId());
                    int insertMobiScheduOrder = mobileScheduSubOrderDao.insert(mobileScheduSubOrder);
                    if (insertMobiScheduOrder <= 0) {
                        throw new MobileStationBizException("新增订单失败！");
                    }
                    //2.2组装货物信息
                    if (mobileGoodDtlList != null) {
                        for (MobileGoodsDtl mobileGoodsInfo : mobileGoodDtlList) {
                            mobileGoodsInfo.setMobileBookingFormId(mobileBookingForm.getId());
                            mobileGoodsInfo.setMobileScheduOrderId(mobileScheduSubOrder.getId());
                            mobileGoodsInfo.setCreateDate(new Date());
                            if (null != mobileBookingForm.getCreateUser()) {
                                mobileGoodsInfo.setCreateUser(mobileBookingForm.getCreateUser());
                            }
                        }
                        result = mobileGoodsDtlDaoEx.batchInsert(mobileGoodDtlList);
                        if (result <= 0) {
                            throw new MobileStationBizException("插入货物信息失败！");
                        }
                    }
                }
            }
        }

        return mobileStationOrderResBean;
    }

    /**
     * 构造子订单数据
     *
     * @param mobileScheduOrderBean
     * @return
     * @throws MobileStationBizException
     * @title convertMobileSubOrder
     * @author M.simple
     * @version 1.0
     * @datetime 2016年6月20日 下午3:15:35
     */
    private MobileScheduSubOrderEx convertMobileSubOrder(MobileScheduSubOrderBean mobileScheduOrderBean) throws MobileStationBizException {
        MobileScheduSubOrderEx mobileScheduSubOrder = new MobileScheduSubOrderEx();

        try {
            PropertyUtils.copyProperties(mobileScheduSubOrder, mobileScheduOrderBean);
            if (null == mobileScheduSubOrder.getComQuoteId() && null != mobileScheduSubOrder.getDocno()) {
                QuoteResultBean quote = comQuoteService.getQuoteInfoByQuoteNo(mobileScheduOrderBean.getDocno());
                if (quote.getComQuote() != null) {
                    mobileScheduSubOrder.setComQuoteId(String.valueOf(quote.getComQuote().getQuoteNo()));  //报价单编号
                    mobileScheduSubOrder.setQuotedType(quote.getComQuote().getQuoteType());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MobileStationBizException("参数格式异常");
        }
        return mobileScheduSubOrder;
    }

    private boolean check(MobileBookingForm mobileBookingForm, AppBaseResult mobileStationOrderResBean) throws MobileStationBizException {
        checkBookingForm(mobileBookingForm, mobileStationOrderResBean);
        return true;
    }

    /**
     * 校验下单的bookingForm数据是否合法
     *
     * @param mobileGoodsInfoList
     * @param mobileStationOrderResBean
     * @return
     * @throws MobileStationBizException
     * @title checkMobileGood
     * @author M.simple
     * @version 3.0
     * @datetime 2016年6月14日 上午9:48:32
     */
    private boolean checkMobileGood(List<MobileGoodsDtl> mobileGoodsInfoList, AppBaseResult mobileStationOrderResBean) throws MobileStationBizException {

        if (null == mobileGoodsInfoList || mobileGoodsInfoList.size() <= 0) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("货物信息数据为空！");
        }

        return true;
    }

    /**
     * 校验下单的mobileGoods数据是否合法
     *
     * @param mobileBookingForm
     * @param mobileStationOrderResBean
     * @return
     * @throws MobileStationBizException
     * @title checkBookingForm
     * @author M.simple
     * @version 1.0
     * @datetime 2016年6月14日 上午9:49:01
     */
    private boolean checkBookingForm(MobileBookingForm mobileBookingForm, AppBaseResult mobileStationOrderResBean) throws MobileStationBizException {

        if (null == mobileBookingForm) {
            throw new MobileStationBizException("获取移动station下单数据失败！");
        }

        if (StringUtil.isEmpty(mobileBookingForm.getStartLocus())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("派车单起始站点为空！");
        }
        if (StringUtil.isEmpty(mobileBookingForm.getDestnLocus())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("派车单目的站点为空！");
        }

        if (StringUtil.isEmpty(mobileBookingForm.getShipCustAddr())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("发货方地址为空！");
        }
        if (StringUtil.isEmpty(mobileBookingForm.getShipCustLinkMan())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("发货方联系人为空！");

        }
        if (StringUtil.isEmpty(mobileBookingForm.getShipCustLinkTel())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("发货方联系电话为空！");
        }
        if (StringUtil.isEmpty(mobileBookingForm.getShipLongitude())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("发货方经度为空！");
        }
        if (StringUtil.isEmpty(mobileBookingForm.getShipLatitude())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("发货方纬度为空！");
        }

        if (StringUtil.isEmpty(mobileBookingForm.getCneeCustAddr())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("收货方地址为空！");
        }
        if (StringUtil.isEmpty(mobileBookingForm.getCneeCustLinkMan())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("收货方联系人为空！");
        }
        if (StringUtil.isEmpty(mobileBookingForm.getCneeCustLinkTel())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("收货方联系电话为空！");
        }
        if (StringUtil.isEmpty(mobileBookingForm.getCneeLongitude())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("收货方经度为空！");
        }
        if (StringUtil.isEmpty(mobileBookingForm.getCneeLatitude())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("收货方纬度为空！");
        }
        if (StringUtil.isEmpty(mobileBookingForm.getTransportType())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("订单操作类型为空！");
        }
        if (StringUtil.isEmpty(mobileBookingForm.getOrderType())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("订单类型为空！");
        }

        if (StringUtil.isEmpty(mobileBookingForm.getOrderFrom())) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            throw new MobileStationBizException("订单来源为空！");
        }
        return true;
    }

    /**
     * 校验下单的mobileGoods数据是否合法
     *
     * @param mobileStationOrderResBean
     * @return
     * @throws MobileStationBizException
     * @title checkBookingForm
     * @author M.simple
     * @version 1.0
     * @datetime 2016年6月14日 上午9:49:01
     */
    private boolean checkSchudeSubOrder(List<MobileScheduSubOrderBean> mobileScheduOrderList, MobileStationOrderResBean mobileStationOrderResBean) throws MobileStationBizException {

        if (null == mobileScheduOrderList || mobileScheduOrderList.size() <= 0) {
            throw new MobileStationBizException("获取移动station子订单数据失败！");
        }

        for (MobileScheduSubOrderBean mobileScheduSubOrderBean : mobileScheduOrderList) {

            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getBookingFormId())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-订单主表编号为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getBusiBookNo())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-业务订单号为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getShipCustProvide())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-发货方省为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getShipCustCity())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-发货方市为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getShipCustAddr())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-发货方地址为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getShipCustLinkMan())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-发货方联系人为空！");

            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getShipCustLinkTel())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-发货方联系电话为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getShipLongitude())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-发货方经度为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getShipLatitude())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-发货方纬度为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getCneeCustProvide())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-收货方省为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getCneeCustCity())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-收货方市为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getCneeCustAddr())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-收货方地址为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getCneeCustLinkMan())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-收货方联系人为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getCneeCustLinkTel())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-收货方联系电话为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getCneeLongitude())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-收货方经度为空！");
            }
            if (StringUtil.isEmpty(mobileScheduSubOrderBean.getCneeLatitude())) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                throw new MobileStationBizException("子订单-收货方纬度为空！");
            }
        }
        return true;
    }
}
