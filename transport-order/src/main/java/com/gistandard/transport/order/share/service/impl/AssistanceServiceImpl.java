package com.gistandard.transport.order.share.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.dubbo.order.bean.MobileSubOrder;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.BookingForm;
import com.gistandard.transport.base.entity.bean.MobileAssistInfo;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.bean.MobileScheduSubOrder;
import com.gistandard.transport.base.entity.dao.MobileAssistInfoDao;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileScheduSubOrderDaoEx;
import com.gistandard.transport.order.share.bean.AssistanceReq;
import com.gistandard.transport.order.share.bean.AssistanceRes;
import com.gistandard.transport.order.share.service.AssistanceService;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 快递员或者咪站援助请求业务实现
 * @author 员伟
 */
@Service
public class AssistanceServiceImpl implements AssistanceService {

    private static final Logger logger = LoggerFactory.getLogger(AssistanceServiceImpl.class);

    @Autowired
    private GpsLogService gpsLogService;

    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;

    @Autowired
    private MobileAssistInfoDao mobileAssistInfoDao;

    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;

    /**
     * 咪站或者快递员申請援助
     * @param req 援助请求
     * @return 援助结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssistanceRes assistance4MiOrCourier(AssistanceReq req) {
        logger.info("为咪站或者快递员提供援助接口--begin");
        AssistanceRes res = new AssistanceRes(req);
        //校验参数
        if (validateReq(req, res)) {
            logger.error("检验参数没有通过");
            return res;
        }
        //通知中层
        try {
            assist4DataSource(req);
            sendOrderTraceGpsMsg(req);
        } catch (Exception e) {
            logger.error("咪站或者快递员申请援助调用中层出错");
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.info("为咪站或者快递员提供援助接口--end-successful");
        res.setApplyFlag(Boolean.TRUE);//已申请
        return res;
    }

    /***
     * 快递员、咪站申请援助数据库操作
     * @param req req
     */
    private void assist4DataSource(AssistanceReq req) {
        //1. 更新MobileBookingForm
        MobileBookingForm mbf = mobileBookingFormDao.selectByPrimaryKey(req.getOrderId());
        int busiCtrlBefore = mbf.getBusiCtrl();
        mbf.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIST);
        mobileBookingFormDao.updateByPrimaryKey(mbf);
        //2. 插入援助表信息
        MobileAssistInfo msi = new MobileAssistInfo();
        msi.setMobileBookingFormId(mbf.getId());
        if (req.getBusiBookNo().startsWith("S_") && req.getAssistType() == 3) {
            List<MobileScheduSubOrder> list = mobileScheduSubOrderDaoEx.selectMobileSubOrderByMobileId(req.getOrderId());
            if (CollectionUtils.isNotEmpty(list)) {
                msi.setBusiBookNo((list.get(0).getBusiBookNo()));
            }
        } else {
            msi.setBusiBookNo(req.getBusiBookNo());
        }
        msi.setBusiCtrlBefore(busiCtrlBefore);
        msi.setBusiCtrlApply(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIST);
        msi.setApplyAcctUser(req.getAcctUsername());
        msi.setApplyAcctUserId(req.getAccountId());
        msi.setApplyUserRoleId(req.getRoleId());
        msi.setApplyType(req.getAssistType());
        msi.setApplyTime(new Date());
        msi.setApplyReason(req.getAssitRemark());
        msi.setScheduFlag(0);//TODO 援助申请中
        mobileAssistInfoDao.insertSelective(msi);
    }


    /**
     * 通知调度系统 申请援助的消息
     * @param req 申请援助的消息
     */
    private void sendOrderTraceGpsMsg(AssistanceReq req) {
        //1. 更新MobileBookingForm
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        if (req.getBusiBookNo().startsWith("S_") && req.getAssistType() == 3) {
            List<MobileScheduSubOrder> list = mobileScheduSubOrderDaoEx.selectMobileSubOrderByMobileId(req.getOrderId());
            if (CollectionUtils.isNotEmpty(list)) {
                allBusNo.add(list.get(0).getBusiBookNo());
            }
        } else {
            allBusNo.add(req.getBusiBookNo());
        }
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setAction(MobileStationDefine.Action_FirstAid);
        giOrderTraceResynced.setTsAct(new Date());
        giOrderTraceResynced.setTypeFirstAid(req.getAssistType());
        giOrderTraceResynced.setRemarkFirstAid(req.getAssitRemark());
        //TODO 可能是企业用户申请援助
        if (req.getAppLoginInfo() != null && req.getAppLoginInfo().getCurrentComCustomerAccount() != null) {
            giOrderTraceResynced.setUserCode(req.getLoginAcctUserName());
            giOrderTraceResynced.setLoginCode(req.getAcctUsername());
        } else {
            giOrderTraceResynced.setUserCode(req.getAcctUsername());
        }
        if (SysAccountRole.OPERATOR_MSTATION.getValue() == req.getRoleId()) {
            giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
        }
        if (SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue() == req.getRoleId()) {
            giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode());
        }
        logger.info("sendGpsMsg giOrderTraceResynced ={}", JSON.toJSONString(giOrderTraceResynced));
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);

    }


    /**
     * 校验快递员或者咪站的援助请求参数
     * @param req 快递员或者咪站的援助请求参数
     * @param res 快递员咪站的援助请求校验结果
     * @return 布尔值 true:校验不通过 false:通过
     */
    private boolean validateReq(AssistanceReq req, AssistanceRes res) {
        logger.info("快递员、咪站发起援助校验参数开始--begin");
        if (req == null) {
            logger.error("快递员、咪站发起援助中请求参数为空");
            res.setRetCode(-1);
            res.setRetMsg("请求参数非法");
            return true;
        }
        if (req.getRoleId() == null) {
            logger.error("快递员、咪站发起援助中角色参数为空");
            res.setRetCode(-1);
            res.setRetMsg("请求角色不存在");
            return true;
        }
        if (SysAccountRole.OPERATOR_MSTATION.getValue() != req.getRoleId() &&
                SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue() != req.getRoleId()) {
            logger.error("快递员、咪站发起援助中请求参数为空");
            res.setRetCode(-1);
            res.setRetMsg("请求角色非法");
            return true;
        }
        // TODO 援助类型 1.订单无法完成，请重新指派 2.取消订单 3.无法联系用户
        if (req.getAssistType() == null) {
            res.setRetCode(-1);
            res.setRetMsg("援助类型为空");
            return true;
        }
        if (req.getAssistType() != 1 && req.getAssistType() != 2 && req.getAssistType() != 3) {
            res.setRetCode(-1);
            res.setRetMsg("援助类型错误");
            return true;
        }
        if (req.getAssistType() == 1 || req.getAssistType() == 2) {
            BookingForm bf = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusiBookNo());
            if (bf == null) {
                res.setRetCode(-1);
                res.setRetMsg("订单不存在,不能发起援助");
                return true;
            }
            if (bf.getIsJs() == 1 || bf.getIsJs() == 2 || bf.getIsJs() == 3) {
                res.setRetCode(-1);
                res.setRetMsg("订单已结算,不能发起援助");
                return true;
            }
            MobileBookingForm mbf = mobileBookingFormDao.selectByPrimaryKey(req.getOrderId());
            if (mbf == null) {
                res.setRetCode(-1);
                res.setRetMsg("订单不存在,不能发起援助");
                return true;
            }
            if (mbf.getBusiCtrl() != null && mbf.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_ASSIST) {
                res.setRetCode(-1);
                res.setRetMsg("正在申请中,请勿重复申请");
                return true;
            }
        }
        logger.info("快递员、咪站发起援助校验参数結束--successful");
        return false;
    }
}
