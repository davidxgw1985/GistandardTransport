package com.gistandard.transport.order.module.mistation.dispatch.service.impl;

import java.lang.Exception;
import java.util.*;

import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.dispatch.bean.*;
import com.gistandard.transport.order.webservice.client.merchant.order.*;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.base.entity.dao.MobileGoodsDtlDao;
import com.gistandard.transport.base.entity.dao.ex.ComCustomerDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileGoodsDtlDaoEx;
import com.gistandard.transport.base.entity.service.ComAccountService;
import com.gistandard.transport.order.module.mistation.dispatch.service.MiStationDispatchService;
import com.gistandard.transport.order.module.mistation.schedu.service.impl.MiStationBathScheCarServiceImpl;
import com.gistandard.transport.tools.annotation.ObjectFieldNotNull;
import com.gistandard.transport.tools.util.ObjectFiledNullVerityUtil;

@Service
public class MiStationDispatchServiceImpl implements MiStationDispatchService {

    private final static Logger logger = LoggerFactory.getLogger(MiStationBathScheCarServiceImpl.class);

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;

    @Autowired
    private MobileGoodsDtlDao mobileGoodsDtlDao;

    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;

    @Autowired
    private MobileGoodsDtlDaoEx mobileGoodsDtlDaoEx;

    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;

    @Autowired
    private ComAccountService comAccountService;

    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Override
    @Transactional
    public DispatchResultBean dispatchStation(DispatchParamBean dispatchParamBean) throws Exception {

        logger.info("进入签派指定站点接口，入参:{}", JSON.toJSONString(dispatchParamBean));

        // 参数合法性校验
        DispatchResultBean verifyResultBean = checkDispatchParamBean(dispatchParamBean);
        // 校验失败，直接返回错误信息
        if (!verifyResultBean.reqResultFlag()) {
            return verifyResultBean;
        }

        if (!StringUtil.isEmpty(dispatchParamBean.getBusiNo())) {
            //校验太保投保是否支付
            BookingForm bf = bookingFormDaoEx.getBookingFormByBusiNo(dispatchParamBean.getBusiNo());
            if (bf != null) {
                int premiumStatus = (bf == null || bf.getPremiumStatus() == null) ? -1 : bf.getPremiumStatus().intValue();
                if (bf != null && "1".equals(bf.getCarriAgerecei()) && bf.getNeedInsure() && CustomerDefine.HAVE_PAY != premiumStatus) {
                    if (CustomerDefine.NEED_PAY == premiumStatus) {
                        throw new MobileStationBizException("用户尚未支付保费，订单无法继续执行，请先与用户联系！");
                    }
                    if (bf.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
                        throw new MobileStationBizException("订单核保失败尚未解冻，无法继续执行，请先与用户联系！");
                    }
                }
            }
        }

        // 调用订单签派的ws接口
        //2、调用签派接口，生成下一个路径
        try {
            List<DispatchExtend> dispatchExtendList = new ArrayList<>();
            DispatchExtend dispatchExtend;
            //普通订单
            dispatchExtend = new DispatchExtend();
            dispatchExtend.setBusiBookNo(dispatchParamBean.getBusiNo());
            dispatchExtend.setCreateUserId(dispatchParamBean.getAccountId());
            dispatchExtend.setFromAccountId(dispatchParamBean.getAccountId());
            dispatchExtend.setToAccountId(dispatchParamBean.getWHubId());
            dispatchExtend.setType("W");//指派给蛙站
            dispatchExtendList.add(dispatchExtend);
            List<BaseRequestResult> resultList = mobileRecOrderWebService.addRouteEndTran(dispatchExtendList);
            if (resultList != null && resultList.size() > 0) {
                if (resultList.get(0).getStatus() != 1)
                    throw new MobileStationBizException(resultList.get(0).getMesasge());
            }

        } catch (Exception_Exception e) {
            throw new MobileStationBizException("签派路径失败！");
        }

        // 更新之前订单状态
        MobileBookingForm dbMobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(dispatchParamBean
                .getMobileBookingFormId());
        if (dbMobileBookingForm == null) {
            logger.info("签派流程：mobileBookingFormId：{} ， 库中无记录！", dispatchParamBean.getMobileBookingFormId());
            throw new MobileStationBizException("签派流程库中无记录！");
        }
        dbMobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
        mobileBookingFormDao.updateByPrimaryKey(dbMobileBookingForm);

        // 重新下单
        MobileBookingForm mobileBookingForm = buildMobileBookingForm(dbMobileBookingForm, dispatchParamBean);
        mobileBookingFormDao.insert(mobileBookingForm);

        List<MobileGoodsDtl> mobileGoodsDtlList = mobileGoodsDtlDaoEx.selectByMobileBookingFormId(dispatchParamBean
                .getMobileBookingFormId());
        if (CollectionUtils.isNotEmpty(mobileGoodsDtlList)) {
            for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
                mobileGoodsDtl.setId(null);
                mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
                mobileGoodsDtlDao.insert(mobileGoodsDtl);
            }
        }

        DispatchResultBean result = buildDispatchResultBean(SysResult.SUCCESS.getValue(), "成功");
        logger.info("进入签派指定站点接口，入参:{} , 返回结果:{}", JSON.toJSONString(dispatchParamBean), JSON.toJSONString(result));
        return result;

    }

    /**
     * 咪站批量中转接口
     *
     * @param batchDispatchReq
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public BatchDispatchResultBean batchDispatch(BatchDispatchReq batchDispatchReq) throws Exception {
        BatchDispatchResultBean batchDispatchResultBean = new BatchDispatchResultBean(batchDispatchReq);
        List<DispatchResBean> dispatchResBeanList = new ArrayList<>();
        List<DispatchExtend> dispatchExtendList = new ArrayList<>();
        HashMap<String, DispatchReqBean> dispatchReqBeanMap = new HashMap<>();
        for (DispatchReqBean dispatchReqBean : batchDispatchReq.getDispatchReqBeanList()) {
            DispatchResBean dispatchResBean = new DispatchResBean();
            dispatchReqBeanMap.put(dispatchReqBean.getBusiNo(), dispatchReqBean);

            logger.info("进入签派指定站点接口，入参:{}", JSON.toJSONString(dispatchReqBean));

            if (!StringUtil.isEmpty(dispatchReqBean.getBusiNo())) {
                //校验太保投保是否支付
                BookingForm bf = bookingFormDaoEx.getBookingFormByBusiNo(dispatchReqBean.getBusiNo());
                if (bf != null) {
                    int premiumStatus = (bf == null || bf.getPremiumStatus() == null) ? -1 : bf.getPremiumStatus().intValue();
                    if (bf != null && "1".equals(bf.getCarriAgerecei()) && bf.getNeedInsure() && CustomerDefine.HAVE_PAY != premiumStatus) {
                        if (CustomerDefine.NEED_PAY == premiumStatus) {
                            dispatchResBean.setRetCode(SystemDefine.FAILURE);
                            dispatchResBean.setRetMsg("用户尚未支付保费，订单无法继续执行，请先与用户联系！");
                            dispatchResBeanList.add(dispatchResBean);
                            continue;
                        }
                        if (bf.getBusiCtrl() == CustomerDefine.ORDER_STATUS_FROZEN) {
                            dispatchResBean.setRetCode(SystemDefine.FAILURE);
                            dispatchResBean.setRetMsg("订单核保失败尚未解冻，无法继续执行，请先与用户联系！");
                            dispatchResBeanList.add(dispatchResBean);
                            continue;
                        }
                    }
                }
            }

            // 调用订单签派的ws接口
            //2、调用签派接口，生成下一个路径
            //普通订单
            DispatchExtend dispatchExtend = new DispatchExtend();
            dispatchExtend.setBusiBookNo(dispatchReqBean.getBusiNo());
            dispatchExtend.setCreateUserId(batchDispatchReq.getAccountId());
            dispatchExtend.setFromAccountId(batchDispatchReq.getAccountId());
            dispatchExtend.setToAccountId(dispatchReqBean.getWHubId());
            dispatchExtend.setType("W");//指派给蛙站
            dispatchExtendList.add(dispatchExtend);
        }
        batchDispatchOrder(dispatchExtendList, dispatchResBeanList, dispatchReqBeanMap);

        batchDispatchResultBean.setDispatchResBeanList(dispatchResBeanList);
        return batchDispatchResultBean;
    }

    private List<DispatchResBean> batchDispatchOrder(List<DispatchExtend> dispatchExtendList, List<DispatchResBean> dispatchResBeanList, HashMap<String, DispatchReqBean> dispatchReqBeanMap) {
        try {
            List<BaseRequestResult> resultList = mobileRecOrderWebService.addRouteEndTran(dispatchExtendList);
            logger.info("进入签派指定站点接口 addRouteEndTran，入参:{} , 返回结果:{}", JSON.toJSONString(dispatchExtendList), JSON.toJSONString(resultList));

            if (resultList != null) {
                for (BaseRequestResult result : resultList) {
                    DispatchResBean dispatchResBean = new DispatchResBean();
                    dispatchResBean.setBusiBookNo(result.getData().toString());
                    if (result.getStatus() == 1) {
                        dispatchResBean.setRetCode(SystemDefine.SUCCESS);
                        DispatchReqBean dispatchReqBean = dispatchReqBeanMap.get(result.getData());
                        // 更新之前订单状态
                        MobileBookingForm dbMobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(dispatchReqBean
                                .getMobileBookingFormId());
                        if (dbMobileBookingForm == null) {
                            logger.info("签派流程：mobileBookingFormId：{} ， 库中无记录！", dispatchReqBean.getMobileBookingFormId());
                            dispatchResBean.setRetCode(SystemDefine.FAILURE);
                            dispatchResBean.setRetMsg("签派流程库中无记录！");
                            dispatchResBeanList.add(dispatchResBean);
                            continue;
                        }
                        dbMobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                        mobileBookingFormDao.updateByPrimaryKey(dbMobileBookingForm);

                        // 重新下单
                        MobileBookingForm mobileBookingForm = buildMobileBookingForm(dbMobileBookingForm, dispatchReqBean);
                        mobileBookingFormDao.insert(mobileBookingForm);

                        List<MobileGoodsDtl> mobileGoodsDtlList = mobileGoodsDtlDaoEx.selectByMobileBookingFormId(dispatchReqBean
                                .getMobileBookingFormId());
                        if (CollectionUtils.isNotEmpty(mobileGoodsDtlList)) {
                            List<MobileGoodsDtl> insertOrderGoodsList = new ArrayList<>();
                            for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
                                mobileGoodsDtl.setId(null);
                                mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
                                insertOrderGoodsList.add(mobileGoodsDtl);
                            }
                            if (insertOrderGoodsList.size() > 0) {
                                mobileGoodsDtlDaoEx.batchInsert(insertOrderGoodsList);
                            }
                        }
                    } else {
                        dispatchResBean.setRetCode(SystemDefine.FAILURE);
                        dispatchResBean.setRetMsg(result.getMesasge());
                    }
                    dispatchResBeanList.add(dispatchResBean);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            for (DispatchExtend dispatchExtend : dispatchExtendList) {
                DispatchResBean dispatchResBean = new DispatchResBean();
                dispatchResBean.setRetCode(SystemDefine.FAILURE);
                dispatchResBean.setRetMsg("中转失败！");
                dispatchResBean.setBusiBookNo(dispatchExtend.getBusiBookNo());
                dispatchResBeanList.add(dispatchResBean);
            }
        }
        return dispatchResBeanList;
    }

    private MobileBookingForm buildMobileBookingForm(MobileBookingForm dbMobileBookingForm,
                                                     DispatchReqBean dispatchReqBean) throws Exception {
        ComCustomer comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(dispatchReqBean.getWHubId());

        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        BeanUtils.copyProperties(mobileBookingForm, dbMobileBookingForm);
        mobileBookingForm.setId(null);
        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
        mobileBookingForm.setTransportType(Integer.parseInt(MobileStationDefine.TRANSPORT_TYPE_EXPRESS));
        mobileBookingForm.setStartLocus(MobileStationDefine.M);
        mobileBookingForm.setStartLocusId(dispatchReqBean.getCreateUserId());
        mobileBookingForm.setDestnLocus(comCustomer.getCustTtl());
        mobileBookingForm.setDestnLocusId(comCustomer.getAccountId());

        ComAccount miStationAccount = comAccountService.queryAccountById(dispatchReqBean.getCreateUserId());
        mobileBookingForm.setCreateUserId(dbMobileBookingForm.getCreateUserId());
        mobileBookingForm.setCreateUser(dbMobileBookingForm.getCreateUser());
        mobileBookingForm.setCreateCompanyId(dbMobileBookingForm.getCreateCompanyId());
        mobileBookingForm.setCreateDate(new Date());

        mobileBookingForm.setRevUserId(dispatchReqBean.getCreateUserId());
        mobileBookingForm.setRevUser(miStationAccount.getAcctUsername());
        mobileBookingForm.setRevDate(new Date());

        mobileBookingForm.setRoleId(SysAccountRole.OPERATOR_MSTATION.getValue());
        mobileBookingForm.setNarrate(dispatchReqBean.getNarrate());

        ComCustomer waComCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(dispatchReqBean.getWHubId());
        if (waComCustomer == null) {
            throw new MobileStationBizException("咪站签派查询目的地蛙站企业账号数据查无结果");
        }

        mobileBookingForm.setCneeCustProvide(waComCustomer.getProvince());
        mobileBookingForm.setCneeCustCity(waComCustomer.getCity());
        mobileBookingForm.setCneeCustCounty(waComCustomer.getCounty());
        mobileBookingForm.setCneeCustAddr(waComCustomer.getCustAdd());
        mobileBookingForm.setCneeCustLinkMan(waComCustomer.getFlinkMan());
        mobileBookingForm.setCneeCustLinkTel(waComCustomer.getFworkTel());
        mobileBookingForm.setCneeLongitude(waComCustomer.getStaLongitude());
        mobileBookingForm.setCneeLatitude(waComCustomer.getStaLatitude());
        return mobileBookingForm;
    }

    private MobileBookingForm buildMobileBookingForm(MobileBookingForm dbMobileBookingForm,
                                                     DispatchParamBean dispatchParamBean) throws Exception {

        ComCustomer comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(dispatchParamBean.getWHubId());
        if (comCustomer == null) {
            logger.info("企业账号：{} ， 企业信息库中无数据。", dispatchParamBean.getWHubId());
            throw new MobileStationBizException("无企业信息。");
        }

        MobileBookingForm mobileBookingForm = new MobileBookingForm();

        BeanUtils.copyProperties(mobileBookingForm, dbMobileBookingForm);

        mobileBookingForm.setId(null);
        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
        mobileBookingForm.setTransportType(Integer.parseInt(MobileStationDefine.TRANSPORT_TYPE_EXPRESS));
        mobileBookingForm.setStartLocus(MobileStationDefine.M);
        mobileBookingForm.setStartLocusId(dispatchParamBean.getAccountId());
        mobileBookingForm.setDestnLocus(comCustomer.getCustTtl());
        mobileBookingForm.setDestnLocusId(comCustomer.getAccountId());
        ComAccount miStationAccount = comAccountService.queryAccountById(dispatchParamBean.getCreateUserId());

        if (miStationAccount == null) {
            throw new MobileStationBizException("无帐号信息");
        }

        mobileBookingForm.setCreateUserId(dbMobileBookingForm.getCreateUserId());
        mobileBookingForm.setCreateUser(dbMobileBookingForm.getCreateUser());
        mobileBookingForm.setCreateCompanyId(dbMobileBookingForm.getCreateCompanyId());
        mobileBookingForm.setCreateDate(new Date());

        mobileBookingForm.setRevUserId(dispatchParamBean.getCreateUserId());
        mobileBookingForm.setRevUser(miStationAccount.getAcctUsername());
        mobileBookingForm.setRevDate(new Date());

        mobileBookingForm.setRoleId(SysAccountRole.OPERATOR_MSTATION.getValue());
        mobileBookingForm.setNarrate(dispatchParamBean.getNarrate());

        ComCustomer waComCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(dispatchParamBean.getWHubId());
        if (waComCustomer == null) {
            throw new MobileStationBizException("咪站签派查询目的地蛙站企业账号数据查无结果");
        }

        mobileBookingForm.setCneeCustProvide(waComCustomer.getProvince());
        mobileBookingForm.setCneeCustCity(waComCustomer.getCity());
        mobileBookingForm.setCneeCustCounty(waComCustomer.getCounty());
        mobileBookingForm.setCneeCustAddr(waComCustomer.getCustAdd());
        mobileBookingForm.setCneeCustLinkMan(waComCustomer.getFlinkMan());
        mobileBookingForm.setCneeCustLinkTel(waComCustomer.getFworkTel());
        mobileBookingForm.setCneeLongitude(waComCustomer.getStaLongitude());
        mobileBookingForm.setCneeLatitude(waComCustomer.getStaLatitude());

        return mobileBookingForm;
    }

    private DispatchResultBean checkDispatchParamBean(DispatchParamBean dispatchParamBean) {

        Map<String, List<String>> nullFieldMap = new HashMap<String, List<String>>();
        ObjectFiledNullVerityUtil.verifyFieldNotNull(dispatchParamBean, ObjectFieldNotNull.class, nullFieldMap, null);
        // 有必填属性为空
        if (nullFieldMap.size() > 0) {
            logger.info("签派指定站点接口有必填参数为空，以下参数未传：{}", JSON.toJSONString(nullFieldMap));
            return buildDispatchResultBean(SysResult.FAILED.getValue(), "以下参数必填：" + nullFieldMap.toString());
        }

        return buildDispatchResultBean(SysResult.SUCCESS.getValue(), "成功");
    }

    private DispatchResultBean buildDispatchResultBean(Integer resultCode, String resultMsg) {
        DispatchResultBean result = new DispatchResultBean();
        result.setRetCode(resultCode);
        result.setRetMsg(resultMsg);
        return result;
    }
}
