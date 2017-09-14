package com.gistandard.transport.order.dubbo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.app.dubbo.pojo.res.WaQRCodeDubboResBean;
import com.gistandard.transport.app.dubbo.pojo.res.WaSignInDubboResBean;
import com.gistandard.transport.app.dubbo.wa.bean.*;
import com.gistandard.transport.app.dubbo.wa.service.WaOrderDubboService;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.MobileBookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileGoodsDtlDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileValueAddDaoEx;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.bean.QRCodeReq;
import com.gistandard.transport.order.module.customer.bean.QRCodeRes;
import com.gistandard.transport.order.module.customer.bean.SignInReq;
import com.gistandard.transport.order.module.customer.bean.SignInRes;
import com.gistandard.transport.order.module.mobilestation.service.MobileSignInService;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: WaOrderDubboServiceImpl
 * @Date: 2017/7/4
 * @Description: 提供给蛙站的订单dubbo接口
 */
public class WaOrderDubboServiceImpl implements WaOrderDubboService {


    private final static Logger logger = LoggerFactory.getLogger(WaOrderDubboServiceImpl.class);
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileScheduSubOrderDao mobileScheduSubOrderDao;
    @Autowired
    private MobileGoodsDtlDaoEx mobileGoodsDtlDaoEx;
    @Autowired
    private MobileFleetBiddingDao mobileFleetBiddingDao;
    @Autowired
    private GpsLogService gpsLogService;
    @Autowired
    private MobileValueAddDao mobileValueAddDao;
    @Autowired
    private MobileValueAddDaoEx mobileValueAddDaoEx;

    @Autowired
    private MobileSignInService mobileSignInService;


    /**
     * 蛙站M-W发起竞价接口
     * @param waBidInitiationReq
     * @return
     */
    @Transactional
    public MsDubboResBean waBidInitiation(WaBidInitiationReq waBidInitiationReq) {
        MsDubboResBean msDubboResBean = new MsDubboResBean();
        //check param
        //判断是否是M-W
        try {
            if (waBidInitiationReq.getBusiBookNo().startsWith("M_")) {
                //咪站生成的M-W的派车单，更新订单
                MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.getMobileOrderByBookBusiNo(waBidInitiationReq.getBusiBookNo(), SysAccountRole.OPERATOR_MSTATION.getValue(),
                        waBidInitiationReq.getCustTtl());
                if (mobileBookingForm == null || mobileBookingForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
                    msDubboResBean.setRetCode(SystemDefine.FAILURE);
                    msDubboResBean.setRetMsg("M-W派车单当前不可以发起竞价！");
                } else {
                    //发送竞价广播
                    GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();//记录GPS操作日志
                    List<String> allBusNoList = new ArrayList<>();
                    giOrderTraceResynced.setAction(MobileStationDefine.Action_PopOrdered);
                    giOrderTraceResynced.setIsToAccept(true);
                    allBusNoList.add(mobileBookingForm.getBusiBookNo());
                    giOrderTraceResynced.setAllBusiNo(allBusNoList);
                    giOrderTraceResynced.setProductType(MobileStationDefine.PRODUCT_TYPE_ITCYS);
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
                    giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_COMPANY_STATION.getRoleCode());
                    giOrderTraceResynced.setTsAct(new Date());
                    logger.info("notifyGps giOrderTraceResynced ={}", JSONArray.toJSONString(giOrderTraceResynced));
                    gpsLogService.sendGpsLogMessage(giOrderTraceResynced);

                    //更新订单信息
                    mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE);
                    mobileBookingForm.setBidUserId(waBidInitiationReq.getBidUserId());
                    mobileBookingForm.setBidUser(waBidInitiationReq.getBidUser());
                    mobileBookingForm.setBidBy("W");
                    mobileBookingForm.setSelfQuoteValue(waBidInitiationReq.getSelfQuoteValue());
                    mobileBookingForm.setSelfQuoteCurr(waBidInitiationReq.getSelfQuoteCurr());
                    mobileBookingForm.setVehicleTypeId(waBidInitiationReq.getVehicleTypeId());
                    mobileBookingForm.setCreateDate(new Date());
                    mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);

                    //插入增值服务
                    if (waBidInitiationReq.getValueAddList() != null) {
                        List<MobileValueAddRel> mobileValueAddRelList = new ArrayList<>();
                        for (MobileValueAddDubbo mobileValueTemp : waBidInitiationReq.getValueAddList()) {
                            //TODO 增值服务创建
                            MobileValueAdd mva = mobileValueAddDao.selectByPrimaryKey(mobileValueTemp.getValueAddId());
                            if (mva != null) {
                                //TODO 增值服务关联类创建
                                MobileValueAddRel mobileValueAddRel = new MobileValueAddRel();
                                mobileValueAddRel.setCreateDate(new Date());
                                mobileValueAddRel.setCreateUser(waBidInitiationReq.getBidUser());
                                mobileValueAddRel.setCreateUserId(waBidInitiationReq.getBidUserId());
                                mobileValueAddRel.setMobileBookingFormId(mobileBookingForm.getId());
                                mobileValueAddRel.setValueAddId(mobileValueTemp.getValueAddId());
                                mobileValueAddRelList.add(mobileValueAddRel);
                            }
                        }
                        if (CollectionUtils.isNotEmpty(mobileValueAddRelList)) {
                            mobileValueAddDaoEx.batchInsert(mobileValueAddRelList);
                        }
                    }
                }
            } else {
                msDubboResBean.setRetCode(SystemDefine.FAILURE);
                msDubboResBean.setRetMsg("不是M-W派车单不可以调用此接口！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msDubboResBean.setRetCode(SystemDefine.FAILURE);
            msDubboResBean.setRetMsg("调用接口异常！");
        }

        return msDubboResBean;
    }

    /**
     * MS3.0 蛙站确认车队竞价接口
     * 1、蛙站发起M-W、W-W、W-M广播竞价
     * 2、车队填写报价、税率，发起竞价
     * 3、蛙站确认车队报价后调用此接口
     * 4、插入MobileBookingForm、MobileSubOrder、MobileGoodsDtl、MobileFleetBidding
     * @param waConfirmOrderReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public MsDubboResBean waConfirmOrder(WaConfirmOrderReq waConfirmOrderReq) throws Exception {
        logger.info("蛙站确认车队竞价接口 waConfirmOrder {}", JSON.toJSONString(waConfirmOrderReq));
        MsDubboResBean msDubboResBean = new MsDubboResBean();
        //check param
        checkWaConfirmOrderParam(waConfirmOrderReq);
        //判断是否是M-W
        MobileBookingFormDubbo mobileBookingFormDubbo = waConfirmOrderReq.getMobileBookingForm();
        if (mobileBookingFormDubbo.getBusiBookNo().startsWith("M_")) {
            //咪站生成的M-W的派车单，更新订单
            MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.getMobileOrderByBookBusiNo(mobileBookingFormDubbo.getBusiBookNo(), SysAccountRole.OPERATOR_MSTATION.getValue(),
                    mobileBookingFormDubbo.getDestnLocus());
            if (mobileBookingForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                msDubboResBean.setRetCode(SystemDefine.FAILURE);
                msDubboResBean.setRetMsg("M-W派车单当前不可以发起竞价！");
                return msDubboResBean;
            }

        } else if (mobileBookingFormDubbo.getBusiBookNo().startsWith("S_")) {
            //蛙站生成的W-W、W-M派车单
            //插入MobileBookingForm
            MobileBookingForm mobileBookingForm = new MobileBookingForm();
            BeanUtils.copyProperties(mobileBookingForm, waConfirmOrderReq.getMobileBookingForm());
            mobileBookingForm.setIsJs(0);
            mobileBookingFormDao.insert(mobileBookingForm);
            //插入子订单表
            List<MobileGoodsDtl> mobileGoodsDtlList = new ArrayList<>();
            for (MobileSubOrderDubbo subOrderDubbo : waConfirmOrderReq.getMobileSubOrderList()) {
                MobileScheduSubOrder subOrder = new MobileScheduSubOrder();
                BeanUtils.copyProperties(subOrder, subOrderDubbo);
                subOrder.setMobileBookingFormId(mobileBookingForm.getId());
                mobileScheduSubOrderDao.insert(subOrder);
                if (subOrderDubbo.getMobileGoodDtlList() != null) {
                    for (MobileGoodsDtlDubbo goodsDtlDubbo : subOrderDubbo.getMobileGoodDtlList()) {
                        MobileGoodsDtl goodsDtl = new MobileGoodsDtl();
                        BeanUtils.copyProperties(goodsDtl, goodsDtlDubbo);
                        goodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
                        goodsDtl.setMobileScheduOrderId(subOrder.getId());
                        mobileGoodsDtlList.add(goodsDtl);
                    }
                }
            }
            //插入货物信息
            if (mobileGoodsDtlList != null && mobileGoodsDtlList.size() > 0) {
                mobileGoodsDtlDaoEx.batchInsert(mobileGoodsDtlList);
            }
            //插入竞价信息
            if (waConfirmOrderReq.getMobileFleetBidding() != null) {
                MobileFleetBidding mobileFleetBidding = new MobileFleetBidding();
                BeanUtils.copyProperties(mobileFleetBidding, waConfirmOrderReq.getMobileFleetBidding());
                mobileFleetBidding.setMobileBookingFormId(mobileBookingForm.getId());
                mobileFleetBidding.setScheducarno(mobileBookingForm.getScheducarno());
                mobileFleetBidding.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                if (mobileFleetBidding.getDriverUserId() != null) {
                    ComAccount driverAccount = comAccountDao.selectByPrimaryKey(mobileFleetBidding.getDriverUserId());
                    if (driverAccount != null) {
                        mobileFleetBidding.setDriverUser(driverAccount.getAcctUsername());
                        mobileFleetBidding.setDriverName(driverAccount.getRealName());
                        mobileFleetBidding.setDriverTelephone(driverAccount.getTelephone());
                    }
                }
                mobileFleetBiddingDao.insert(mobileFleetBidding);
            }
        } else {
            msDubboResBean.setRetCode(SystemDefine.FAILURE);
            msDubboResBean.setRetMsg("不是蛙站派车单不可以调用此接口！");
        }
        return msDubboResBean;
    }

    /**
     * MS3.0 蛙站指派快递员W-POD接口
     * 1、蛙站指派给快递员W-POD订单
     * 2、快递员接W-POD广播单后，蛙站回调
     * 3、插入MobileBookingForm、MobileSubOrder、MobileGoodsDtl
     * @param waAssignOrderReq
     * @return
     */
    @Override
    public MsDubboResBean waAssignOrder(WaAssignOrderReq waAssignOrderReq) throws Exception {
        MsDubboResBean msDubboResBean = new MsDubboResBean();
        return msDubboResBean;
    }

    /**
     * 蛙站确认车队竞价接口 参数校验
     * @param waConfirmOrderReq
     */
    private void checkWaConfirmOrderParam(WaConfirmOrderReq waConfirmOrderReq) throws MobileStationBizException {
        if (waConfirmOrderReq == null) {
            throw new MobileStationBizException("蛙站确认车队竞价请求参数为空!");
        }
        if (waConfirmOrderReq.getMobileBookingForm() == null) {
            throw new MobileStationBizException("派车单订单信息不能为空!");
        }
        if (waConfirmOrderReq.getMobileSubOrderList() == null) {
            throw new MobileStationBizException("派车单子订单信息不能为空!");
        }
        if (waConfirmOrderReq.getMobileFleetBidding() == null) {
            throw new MobileStationBizException("订单竞价信息不能为空!");
        }
        if (waConfirmOrderReq.getMobileBookingForm().getBusiBookNo() == null) {
            List<MobileBookingForm> orderList = mobileBookingFormDaoEx.selectMobileOrderByScheducarno(waConfirmOrderReq.getMobileBookingForm().getScheducarno());
            for (MobileBookingForm order : orderList) {
                if (order.getBusiCtrl() > 0) {
                    throw new MobileStationBizException("派车单订单信息已经存在，不能重复确认!");
                }
            }
        }
        logger.debug("蛙站确认车队竞价接口,校验参数通过--successful！！");
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public WaSignInDubboResBean addSignInfo(WaSignInDubboReq req) throws Exception {
        logger.info("洼站调用dubbo签到接口req-{}", JSON.toJSONString(req));
        WaSignInDubboResBean res = new WaSignInDubboResBean();
        SignInReq signInReq = new SignInReq();
        BeanUtils.copyProperties(signInReq, req);
        logger.info("洼站调用dubbo签到,签到请求signInReq-{}", JSON.toJSONString(signInReq));
        SignInRes result = mobileSignInService.addSignInfo(signInReq);
        logger.info("洼站调用Msapp签到,签到响应result-----{}", JSON.toJSONString(result));
        BeanUtils.copyProperties(result, res);
        logger.info("洼站调用dubbo签到,签到响应res-----{}", JSON.toJSONString(res));
        return res;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public WaQRCodeDubboResBean getQRCodeInfo(WaQRCodeDubboReq req) throws Exception {
        logger.info("洼站调用dubbo生成二维码接口req-{}", JSON.toJSONString(req));
        WaQRCodeDubboResBean res = new WaQRCodeDubboResBean();
        QRCodeReq qrCodeReq = new QRCodeReq();
        BeanUtils.copyProperties(qrCodeReq, req);
        logger.info("洼站调用dubbo二维码接口,qrCodeReq-{}", JSON.toJSONString(qrCodeReq));
        QRCodeRes result = mobileSignInService.getQRCodeInfo(qrCodeReq);
        logger.info("洼站调用Msapp生成二维码接口,result-{}", JSON.toJSONString(result));
        BeanUtils.copyProperties(res, result);
        logger.info("洼站调用dubbo二维码接口,最终返回res--{}", JSON.toJSONString(res));
        return res;
    }
}
