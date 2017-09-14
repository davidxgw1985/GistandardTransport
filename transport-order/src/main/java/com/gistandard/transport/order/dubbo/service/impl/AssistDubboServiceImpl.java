package com.gistandard.transport.order.dubbo.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.dubbo.order.bean.AssistApplyOrderReq;
import com.gistandard.transport.app.dubbo.order.service.AssistDubboService;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeReq;
import com.gistandard.transport.app.dubbo.sms.service.SmsDubboService;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.BookingForm;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.MobileAssistInfo;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.dao.BookingFormDao;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.MobileAssistInfoDao;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileAssistInfoDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileBookingFormDaoEx;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.tools.util.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 订单援助dubbo接口实现类
 * @author 员伟
 */
public class AssistDubboServiceImpl implements AssistDubboService {

    private static final Logger logger = LoggerFactory.getLogger(AssistDubboServiceImpl.class);

    @Autowired
    private SmsDubboService smsDubboService;

    @Autowired
    private GpsLogService gpsLogService;

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private BookingFormDao bookingFormDao;

    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;

    @Autowired
    private MobileAssistInfoDao mobileAssistInfoDao;

    @Autowired
    private MobileAssistInfoDaoEx mobileAssistInfoDaoEx;

    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;

    /**
     * 驳回援助申请接口
     * @param req 调度管理系统驳回申请请求
     * @return 返回结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public MsDubboResBean overruleAssistApply(AssistApplyOrderReq req) {
        logger.info("调度管理系统驳回申请请求-req:{}", JSON.toJSONString(req));
        MsDubboResBean res = new MsDubboResBean();
        //1. 校验
        if (validORParam(req, res)) {
            logger.error("调度管理系统驳回校验参数不通过");
            return res;
        }
        //2. 查询援助信息合法性
        MobileAssistInfo mai = mobileAssistInfoDaoEx.queryMobileAssistInfo(req.getBusiBookNo());
        if (mai == null) {
            logger.error("当前驳回请求所属订单号援助表中不存在");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("援助请求信息数据库中已不存在");
            return res;
        }
        //3. 更新援助申请表
        mai.setScheduAcctUser(req.getSchedulAcctUser());
        mai.setScheduFlag(req.getSchedulFlag());
        mai.setScheduTime(new Date());
        mai.setScheduRemark(req.getSchedulRemark());
        mai.setScheduRefuseReason(req.getScheduRefuseReason());
        mobileAssistInfoDao.updateByPrimaryKey(mai);
        //4. 恢复先前订单的状态
        int busiCtrlBefore = mai.getBusiCtrlBefore();
        MobileBookingForm mbf = mobileBookingFormDao.selectByPrimaryKey(mai.getMobileBookingFormId());
        mbf.setBusiCtrl(busiCtrlBefore);
        mobileBookingFormDao.updateByPrimaryKey(mbf);
        //5. 驳回援助通知快递员或者咪站消息
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(mai.getApplyAcctUser());
        if (comAccount == null) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("驳回信息无法发送通知");
            return res;
        }
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(18);//驳回消息标识
        sendSmsVerifyCodeReq.setBusiBookNo(req.getBusiBookNo());
        sendSmsVerifyCodeReq.setBusiTime(DateUtil.getFormatDate2(mai.getApplyTime()));
        logger.info("调度管理系统驳回申请通知消息-msg:{}", JSON.toJSONString(sendSmsVerifyCodeReq));
        smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
        logger.info("调度管理系统驳回申请请求处理完成--TO THE END!");
        return res;
    }


    /**
     * 校验调度管理员驳回请求参数
     * @param req 调度管理员驳回请求参数
     * @param res 调度管理员驳回返回结果
     * @return true:不通过;false:通过
     * <p>schedulFlag:调度援助标识 0:援助中 1:已经同意 2:驳回申请<p/>
     */
    private Boolean validORParam(AssistApplyOrderReq req, MsDubboResBean res) {
        logger.info("校验调度管理员驳回请求参数--开始");
        if (valid4BaseApply(req, res)) {
            logger.error("驳回和同意基础校验不通过");
            return true;
        }
        if (req.getSchedulFlag() == null || req.getSchedulFlag() != 2) {
            logger.error("调度管理员驳回请求接口,调度却执行同意请求");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("驳回标识非法");
            return true;
        }
        return false;
    }


    /**
     * 校验快递员或咪站申请援助参数-基础校验
     * @param req 快递员或咪站援助请求参数
     * @param res 快递员或咪站援助校验返回
     * @return true:不通过;false:通过
     * <p>assistType:调度类型标识 1:订单无法完成，请重新指派 2:取消订单 3:无法联系用户<p/>
     */
    private boolean valid4BaseApply(AssistApplyOrderReq req, MsDubboResBean res) {
        if (req == null) {
            logger.error("调度管理系统驳回申请请求为空");
            res.setRetMsg("请求参数为空");
            res.setRetCode(SystemDefine.FAILURE);
            return true;
        }
        if (req.getAssistType() == null) {
            logger.error("调度管理员驳回请求,援助类型为空");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("援助类型为空");
            return true;
        }
        if (req.getAssistType() != 1 && req.getAssistType() != 2
                && req.getAssistType() != 3) {
            logger.error("调度管理员驳回请求,援助类型错误");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("援助类型错误");
            return true;
        }
        if (StringUtils.isBlank(req.getBusiBookNo())) {
            logger.error("调度管理员驳回请求,订单号不存在");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("订单号不存在");
            return true;
        }
        if (StringUtils.isBlank(req.getSchedulAcctUser())) {
            logger.error("调度管理员驳回请求,调度管理员不存在");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("调度员账号不存在");
            return true;
        }
        return false;
    }


    /**
     * 同意援助申请接口
     * @param req 调度管理人员同意申请请求
     * @return 返回结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public MsDubboResBean approvalAssistApply(AssistApplyOrderReq req) {
        logger.info("调度管理系统同意申请请求-req:{}", JSON.toJSONString(req));
        MsDubboResBean res = new MsDubboResBean();
        //1. 校验参数
        if (validARParam(req, res)) {
            logger.error("调度管理人员同意申请请求,参数校验失败");
            return res;
        }
        //2. 查询援助信息
        MobileAssistInfo mai = mobileAssistInfoDaoEx.queryMobileAssistInfo(req.getBusiBookNo());
        if (mai == null) {
            logger.error("当前订单{}的援助数据库信息不存在", req.getBusiBookNo());
            res.setRetCode(-1);
            res.setRetMsg("援助申请信息已经不存在");
            return res;
        }
        //3. 更新mobileBookingForm并通知相关消息
        switch (req.getAssistType()) {
            case 1: //如果是订单无法完成，请重新指派
                reAssignOrderAid(req, mai, res);
                break;
            case 2: //取消订单
                cancelOrderAid(req, mai, res);
                break;
            case 3: //无法联系用户
                unableConnectAid(req, mai, res);
                break;
            default:
                res.setRetCode(SystemDefine.FAILURE);
                res.setRetMsg("申请援助类型非法");
                return res;
        }
        return res;
    }

    /**
     * 重新指派数据库操作以及通知消息
     * @param req 重新指派请求
     * @param mai 当前订单先前援助数据库信息
     * @param res 当前订单援助接口返回信息
     */
    private void reAssignOrderAid(AssistApplyOrderReq req, MobileAssistInfo mai, MsDubboResBean res) {
        //取消快递员旧订单
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mai.getMobileBookingFormId());
        if (mobileBookingForm != null && MobileStationDefine.MOBILE_ORDER_STATUS_ASSIST == mobileBookingForm.getBusiCtrl()) {
            //1. 取消先前快递员的订单
            mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
            mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
            //2.更新援助信息
            mai.setScheduAcctUser(req.getSchedulAcctUser());
            mai.setScheduAssistAcctUser(req.getScheduAssistAcctUser());
            mai.setScheduFlag(1);
            mai.setScheduRemark(req.getSchedulRemark());
            mai.setScheduTime(new Date());
            mobileAssistInfoDao.updateByPrimaryKeySelective(mai);
            //3. 插入快递员新订单
            MobileBookingForm newForm = new MobileBookingForm();
            try {
                BeanUtils.copyProperties(newForm, mobileBookingForm);
            } catch (Exception e) {
                res.setRetCode(SystemDefine.FAILURE);
                res.setRetMsg("重新指派出错");
                return;
            }
            ComAccount assistAccount = comAccountDao.queryByAccount(mai.getScheduAssistAcctUser());//被指派人账号
            if (assistAccount == null) {
                res.setRetCode(SystemDefine.FAILURE);
                res.setRetMsg("指派人账号不存在！");
                return;
            }
            ComAccount assignAccount = comAccountDao.queryByAccount(mai.getScheduAcctUser());//业务员账号
            if (assignAccount == null) {
                res.setRetCode(SystemDefine.FAILURE);
                res.setRetMsg("调度员账号不存在！");
                return;
            }
            newForm.setId(null);
            newForm.setRevUserId(assistAccount.getId());
            newForm.setRevUser(mai.getScheduAssistAcctUser());
            newForm.setRevDate(new Date());
            newForm.setBusiCtrl(mai.getBusiCtrlBefore());
            newForm.setAssignUserId(assignAccount.getId());
            newForm.setAssignUser(mai.getScheduAcctUser());
            newForm.setAssignDate(new Date());
            mobileBookingFormDao.insert(newForm);
            //4. 重新指派通知用户消息
            reAssign4SendMsg(req, mai, assistAccount);
            //重新指派通知中层
            sendOrderGpsMsg(req);
        } else {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("当前订单不在待援助状态！");
        }
    }

    /**
     * 重新指派发送通知消息
     * @param req 请求信息
     * @param mai 援助信息
     * @param assistAccount 指派的账户
     */
    private void reAssign4SendMsg(AssistApplyOrderReq req, MobileAssistInfo mai, ComAccount assistAccount) {
        // 通知新快递员消息  重新指派订单通知当前快递员消息
        SendSmsVerifyCodeReq sendSmsNwCodeReq = new SendSmsVerifyCodeReq();
        sendSmsNwCodeReq.setSystem("MobileStation");
        sendSmsNwCodeReq.setReceiveNo(assistAccount.getTelephone());
        sendSmsNwCodeReq.setModel(21);// 重新指派
        sendSmsNwCodeReq.setBusiBookNo(req.getBusiBookNo());
        logger.info("无法联系通知消息-nowCourierCodeReq{}", JSON.toJSONString(sendSmsNwCodeReq));
        smsDubboService.sendSmsVerifyCode(sendSmsNwCodeReq);
        // 通知先前快递员消息 重新指派订单通知先前快递员消息
        SendSmsVerifyCodeReq sendSmsBfCodeReq = new SendSmsVerifyCodeReq();
        sendSmsBfCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(mai.getApplyAcctUser());
        sendSmsBfCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsBfCodeReq.setModel(19);//取消订单
        sendSmsBfCodeReq.setBusiBookNo(req.getBusiBookNo());
        sendSmsBfCodeReq.setBusiTime(DateUtil.getFormatDate2(mai.getApplyTime()));
        logger.info("无法联系通知消息-sendSmsBfCodeReq{}", JSON.toJSONString(sendSmsBfCodeReq));
        smsDubboService.sendSmsVerifyCode(sendSmsBfCodeReq);
    }


    /**
     * 取消订单数据库操作以及通知消息
     * @param req 取消订单请求
     * @param mai 当前订单先前援助数据库信息
     * @param res 当前订单援助接口返回信息
     */
    private void cancelOrderAid(AssistApplyOrderReq req, MobileAssistInfo mai, MsDubboResBean res) {
        logger.info("取消订单援助-请求req:{}", JSON.toJSONString(req));
        logger.info("取消订单援助-数据mai:{}", JSON.toJSONString(mai));
        //1. 根据订单id查询出订单,然后根据起始地POP-M-POD和当前订单角色更新mobileBookingForm
        MobileBookingForm mbf = mobileBookingFormDao.selectByPrimaryKey(mai.getMobileBookingFormId());
        if (mbf == null) {
            logger.error("根据援助信息的订单id查询到订单信息不存在");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("取消的订单已不存在");
            return;
        }
        //2. 如果当前是要取消快递员、咪站的订单
        List<MobileBookingForm> mbfList = mobileBookingFormDaoEx.selectMobileOrderByBookBusiNo(req.getBusiBookNo());
        //批量更新订单表状态为已取消
        if (CollectionUtils.isNotEmpty(mbfList)) {
            for (MobileBookingForm bean : mbfList) {
                bean.setBusiCtrl(-1);
                mobileBookingFormDao.updateByPrimaryKey(bean);
            }
        }
        //3. 更新BookingForm订单为-1,取消订单
        if (mbf.getBookingFormId() != null) {
            BookingForm bf = bookingFormDao.selectByPrimaryKey((mbf.getBookingFormId()));
            if (bf == null) {
                logger.error("根据援助信息的订单id查询到订单信息不存在");
                res.setRetCode(SystemDefine.FAILURE);
                res.setRetMsg("取消的订单已不存在");
                return;
            }
            bf.setBusiCtrl(-1);
            bookingFormDao.updateByPrimaryKey(bf);
        }
        //4. 申请援助中取消订单通知消息
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(mai.getApplyAcctUser());
        if (comAccount == null) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("申请援助账号已不存在！");
            return;
        }
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(19);// 同意取消订单
        sendSmsVerifyCodeReq.setBusiBookNo(mbf.getBusiBookNo());
        sendSmsVerifyCodeReq.setBusiTime(DateUtil.getFormatDate2(mai.getApplyTime()));
        logger.info("申请援助中取消订单推送消息-msg:{}", JSON.toJSONString(sendSmsVerifyCodeReq));
        sendOrderGpsMsg(req);
        logger.info("取消订单数据库操作以及通知消息--结束-successful!!");
    }

    /**
     * 通知消息操作
     * @param req 请求信息
     */
    private void sendOrderGpsMsg(AssistApplyOrderReq req) {
        logger.info("申请援助中取消订单、重新指派通知消息GPS消息开始");
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusiBookNo());
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(bookingForm.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(bookingForm.getTransportType());
        if (req.getAssistType() == 1) {
            giOrderTraceResynced.setAction(MobileStationDefine.Action_AcceptOrder);
            giOrderTraceResynced.setUserCode(req.getScheduAssistAcctUser());
            giOrderTraceResynced.setLoginCode(req.getScheduAssistAcctUser());
            giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode());
        } else {
            giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
            giOrderTraceResynced.setTypeCancelOrdered(4);//援助取消
            giOrderTraceResynced.setUserCode(req.getSchedulAcctUser());
            giOrderTraceResynced.setLoginCode(req.getSchedulAcctUser());
            giOrderTraceResynced.setRoleCode(SysAccountRole.NORMAL_PERSONAL.getRoleCode());
        }
        giOrderTraceResynced.setTsAct(new Date());
        logger.info("PriceOrder4Aid sendGpsLogMessage ={}", JSON.toJSONString(giOrderTraceResynced));
        //通知GPS消息
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
        logger.info("申请援助中取消订单、重新指派通知消息GPS消息结束");
    }


    /**
     * 无法联系用户数据库操作以及通知消息
     * @param req 无法联系用户请求
     * @param mai 当前订单先前援助数据库信息
     * @param res 当前订单援助接口返回信息
     */
    private void unableConnectAid(AssistApplyOrderReq req, MobileAssistInfo mai, MsDubboResBean res) {
        logger.info("无法联系用户-处理请求req:{}", JSON.toJSONString(req));
        logger.info("无法联系用户-援助数据mai:{}", JSON.toJSONString(mai));
        //1. 根据订单id查询出订单,然后根据起始地POP-M-POD和当前订单角色更新mobileBookingForm
        MobileBookingForm mbf = mobileBookingFormDao.selectByPrimaryKey(mai.getMobileBookingFormId());
        if (validOrder4UnableCon(res, mbf)) {
            logger.error("无法联系用户订单状态校验不通过！");
            return;
        }
        if (unableCon4DataSource(req, res, mbf, mai)) {
            logger.error("无法联系用户订单状态修改数据库操作失败！");
            return;
        }
        //3. 修改援助表信息
        mai.setScheduAcctUser(req.getSchedulAcctUser());
        mai.setScheduFlag(1);//schedulFlag:调度援助标识 0:援助中 1:已经同意 2:驳回申请
        mai.setScheduRemark(req.getSchedulRemark());
        mai.setScheduTime(new Date());
        mai.setScheduAssShipTel(req.getScheduAssAcctShipTel());
        mai.setScheduAssCneeTel(req.getScheduAssAcctCneeTel());
        mobileAssistInfoDao.updateByPrimaryKey(mai);
        //4. 通知消息
        if (unableCon4SendMsg(mai, res, mbf)) {
            logger.error("无法联系用户发送消息失败!");
            return;
        }
        logger.info("无法联系用户数据库操作以及通知消息-结束-TO THE END!!");
    }

    /**
     * 无法联系用户通知消息操作
     * @param mai 援助信息
     * @param res 返回信息
     * @param mbf 订单信息
     * @return boolean值 true:不通过 false:通过
     */
    private boolean unableCon4SendMsg(MobileAssistInfo mai, MsDubboResBean res, MobileBookingForm mbf) {
        //4.无法联系用户修改联系方式 通知快递员或者咪站消息
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(mai.getApplyAcctUser());
        if (comAccount == null) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("申请援助账号已不存在！");
            return true;
        }
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(20);// 更正用户信息
        sendSmsVerifyCodeReq.setBusiBookNo(mbf.getBusiBookNo());
        sendSmsVerifyCodeReq.setBusiTime(DateUtil.getFormatDate2(mai.getApplyTime()));
        logger.info("无法联系通知消息-msg:{}", JSON.toJSONString(sendSmsVerifyCodeReq));
        smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
        return false;
    }

    /**
     * 无法联系用户数据库操作
     * @param req 请求信息
     * @param res 返回信息
     * @param mbf 订单信息
     * @param mai 援助信息
     * @return boolean值 true:不通过 false:通过
     */
    private boolean unableCon4DataSource(AssistApplyOrderReq req, MsDubboResBean res, MobileBookingForm mbf, MobileAssistInfo mai) {
        //1. 如果当前是要修改快递员、咪站的订单
        mbf.setBusiCtrl(mai.getBusiCtrlBefore());
        if (MobileStationDefine.POP.equals(mbf.getStartLocus())) {
            if (StringUtils.isNotBlank(req.getScheduAssAcctShipTel())) {
                mbf.setShipCustLinkTel(req.getScheduAssAcctShipTel());
            }
        }
        if (MobileStationDefine.POD.equals(mbf.getDestnLocus())) {
            if (StringUtils.isNotBlank(req.getScheduAssAcctCneeTel())) {
                mbf.setCneeCustLinkTel(req.getScheduAssAcctCneeTel());
            }
        }
        mobileBookingFormDao.updateByPrimaryKey(mbf);
        //2. 如果是BookingForm的订单
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusiBookNo());
        if(bookingForm != null){
            if (StringUtils.isNotBlank(req.getScheduAssAcctShipTel())) {
                bookingForm.setShipCustlinkTel(req.getScheduAssAcctShipTel());
            }
            if (StringUtils.isNotBlank(req.getScheduAssAcctCneeTel())) {
                bookingForm.setCneeCustLinkTel(req.getScheduAssAcctCneeTel());
            }
            bookingFormDao.updateByPrimaryKey(bookingForm);
        }
        List<MobileBookingForm> mbfList = mobileBookingFormDaoEx.selectMobileOrderByBookBusiNo(req.getBusiBookNo());
        //3. 批量更新订单表联系方式
        if (CollectionUtils.isNotEmpty(mbfList)) {
            for (MobileBookingForm bean : mbfList) {
                if (mbf.getId().equals(bean.getId())) {
                    continue;
                }
                if (MobileStationDefine.POP.equals(mbf.getStartLocus())) {
                    if (StringUtils.isNotBlank(req.getScheduAssAcctShipTel())) {
                        bean.setShipCustLinkTel(req.getScheduAssAcctShipTel());
                    }
                }
                if (MobileStationDefine.POD.equals(mbf.getDestnLocus())) {
                    if (StringUtils.isNotBlank(req.getScheduAssAcctCneeTel())) {
                        bean.setCneeCustLinkTel(req.getScheduAssAcctCneeTel());
                    }
                }
                logger.info("修改当前订单号的其他订单-开始");
                mobileBookingFormDao.updateByPrimaryKey(bean);
            }
        }
        // 如果是W-POD的订单
        if (!MobileStationDefine.POP.equals(mbf.getStartLocus()) && MobileStationDefine.POD.equals(mbf.getDestnLocus())) {
            MobileBookingForm mbfModel = mobileBookingFormDaoEx.queryWaAssignOrder(req.getBusiBookNo());
            if (mbfModel != null) {
                if (StringUtils.isNotBlank(req.getScheduAssAcctCneeTel())) {
                    mbfModel.setCneeCustLinkTel(req.getScheduAssAcctCneeTel());
                } else {
                    res.setRetCode(SystemDefine.FAILURE);
                    res.setRetMsg("收件人联系方式不能为空！");
                    return true;
                }
                mobileBookingFormDao.updateByPrimaryKey(mbfModel);
            }
        }
        return false;
    }

    /**
     * 无法联系用户操作--校验订单信息
     * @param res 返回信息
     * @param mbf 订单信息
     * @return boolean值 true:不通过 false:通过
     */
    private boolean validOrder4UnableCon(MsDubboResBean res, MobileBookingForm mbf) {
        if (mbf == null) {
            logger.error("根据援助信息的订单id查询到订单信息不存在");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("取消的订单已不存在！");
            return true;
        }
        if (mbf.getBusiCtrl() == null ||
                MobileStationDefine.MOBILE_ORDER_STATUS_ASSIST != mbf.getBusiCtrl()) {
            logger.error("根据援助信息的订单id查询到订单信息不存在");
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("当前订单不在待援助状态！");
            return true;
        }
        //2. POP-POD | POP-X | X-POD
        if (!MobileStationDefine.POP.equals(mbf.getStartLocus()) && !MobileStationDefine.POD.equals(mbf.getDestnLocus())) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("当前订单不可以修改联系方式！");
            return true;
        }
        return false;
    }


    /**
     * 校验同意快递员或咪站申请援助参数
     * @param req 快递员或咪站申请援助请求参数
     * @param res 快递员或咪站申请援助校验返回
     * @return true:不通过;false:通过
     * <p>schedulFlag:调度援助标识 0:援助中 1:已经同意 2:驳回申请<p/>
     * <p>assistType:调度类型标识 1:订单无法完成，请重新指派 2:取消订单 3:无法联系用户<p/>
     */
    private boolean validARParam(AssistApplyOrderReq req, MsDubboResBean res) {
        logger.info("调度管理人员同意申请请求,参数校验开始");
        if (valid4BaseApply(req, res)) {
            logger.error("驳回和同意基础校验不通过");
            return true;
        }
        if (req.getAssistType() == 1) {
            if (StringUtils.isBlank(req.getScheduAssistAcctUser())) {
                logger.error("调度管理员指派人员账号为空");
                res.setRetMsg("指派人员账号为空");
                res.setRetCode(SystemDefine.FAILURE);
                return true;
            }
        }
        if (req.getAssistType() == 3) {
            if (StringUtils.isBlank(req.getScheduAssAcctCneeTel()) &&
                    StringUtils.isBlank(req.getScheduAssAcctShipTel())) {
                logger.error("调度管理员在无法联系用户给出的新的联系方式为空");
                res.setRetMsg("联系方式为空");
                res.setRetCode(SystemDefine.FAILURE);
                return true;
            }
        }
        if (req.getSchedulFlag() == null || req.getSchedulFlag() != 1) {
            logger.error("调度管理员同意标识不是1,参数非法");
            res.setRetMsg("同意标识非法");
            res.setRetCode(SystemDefine.FAILURE);
            return true;
        }
        logger.info("调度管理人员同意申请请求,参数校验通过");
        return false;
    }
}
