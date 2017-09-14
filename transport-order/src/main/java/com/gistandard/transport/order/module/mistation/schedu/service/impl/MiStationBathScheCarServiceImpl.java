package com.gistandard.transport.order.module.mistation.schedu.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.MobileBookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileValueAddDaoEx;
import com.gistandard.transport.base.entity.service.*;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.schedu.bean.MiBidInitiationReq;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.order.webservice.client.order.expressbusiness.*;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.dao.ex.ComAccountRoleRelDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileGoodsDtlDaoEx;
import com.gistandard.transport.order.module.mistation.define.OrderAdviceTypeEnum;
import com.gistandard.transport.order.module.mistation.schedu.bean.ScheCarParamBean;
import com.gistandard.transport.order.module.mistation.schedu.bean.ScheCarResultBean;
import com.gistandard.transport.order.module.mistation.schedu.bean.WaitScheOrderInfoBean;
import com.gistandard.transport.order.module.mistation.schedu.service.MiStationBathScheCarService;
import com.gistandard.transport.order.webservice.client.order.hub.ExpreessOrderWebService;
import com.gistandard.transport.order.webservice.client.order.hub.GenSchecarIdRequest;
import com.gistandard.transport.order.webservice.client.order.hub.GenSchecarIdRequestResponse;
import com.gistandard.transport.tools.annotation.BooleanObjectFieldNotNull;
import com.gistandard.transport.tools.annotation.ObjectFieldNotNull;
import com.gistandard.transport.tools.util.ObjectFieldCopyUtil;
import com.gistandard.transport.tools.util.ObjectFiledNullVerityUtil;

@Service
public class MiStationBathScheCarServiceImpl implements MiStationBathScheCarService {

    private final static Logger logger = LoggerFactory.getLogger(MiStationBathScheCarServiceImpl.class);

    @Autowired
    private MobileScheduSubOrderDao mobileScheduSubOrderDao;

    @Autowired
    private MobileGoodsDtlDao mobileGoodsDtlDao;

    @Autowired
    private MobileGoodsDtlDaoEx mobileGoodsDtlDaoEx;

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;

    @Autowired
    private ComAccountRoleRelDaoEx comAccountRoleRelDaoEx;

    @Autowired
    private ComAccountService comAccountService;

    @Autowired
    private ExpreessOrderWebService expreessOrderWebService;

    @Autowired
    private ExpressBusinessOrderWebService expressBusinessOrderWebService;

    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private MobileValueAddDao mobileValueAddDao;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;
    @Autowired
    private MobileSingleCenterDao mobileSingleCenterDao;
    @Autowired
    private GpsOrderService gpsOrderService;
    @Autowired
    private MobileValueAddDaoEx mobileValueAddDaoEx;
    @Autowired
    private MobileMyOrderService mobileMyOrderService;
    @Autowired
    private ComProvinceService comProvinceService;
    @Autowired
    private ComCityService comCityService;
    @Autowired
    private ComCountyService comCountyService;
    @Autowired
    private ComCurrencyService comCurrencyService;
    @Autowired
    private GpsLogService gpsLogService;

    /**
     * 咪站对M-W的订单进行批量排货，生成派车单
     *
     * @param scheCarParamBean 入参
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public ScheCarResultBean batchSchedule(ScheCarParamBean scheCarParamBean) throws Exception {
        logger.info("进入对订单进行批量排货派车接口，入参：{}", JSON.toJSONString(scheCarParamBean));

        // 1、校验参数合法性
        ScheCarResultBean verifyResultBean = verifyParam(scheCarParamBean);
        // 校验失败，直接返回错误信息
        if (!verifyResultBean.reqResultFlag()) {
            return verifyResultBean;
        }
        // 2、校验成功，调用批量排货派车接口
        GenSchecarIdRequest genSchecarIdRequest = buildGenSchecarIdRequest(scheCarParamBean);

        // 0成功，非0失败
        GenSchecarIdRequestResponse genSchecarIdRequestResponse = expreessOrderWebService.genSchecarId(genSchecarIdRequest);
        logger.info("调用批量排货派车接口，入参：{} ， 返回结果：{}", JSON.toJSONString(genSchecarIdRequest),
                genSchecarIdRequestResponse == null ? "" : JSON.toJSONString(genSchecarIdRequestResponse));

        ScheCarResultBean scheCarResultBean = buildScheCarResultBeanByResponse(genSchecarIdRequestResponse);
        logger.info("进入对订单进行批量排货派车接口，入参：{} ， 返回：{}", JSON.toJSONString(scheCarParamBean),
                JSON.toJSONString(scheCarResultBean));

        // 如果调用批量排货派车接口失败，则直接返回错误信息
        if (!scheCarResultBean.reqResultFlag()) {
            return scheCarResultBean;
        }

        // 3、生成新的咪站派车单，更新老单状态
        MobileBookingForm miStationMobileBookingForm = createNewMobileOrder(scheCarParamBean,
                genSchecarIdRequestResponse.getScheduCarId());

        BatchSyncBusinessOrderRequest batchSyncBusinessOrderRequest = new BatchSyncBusinessOrderRequest();
        //设置蛙站参数
        List<ExpreessBusinessOrderVO> expreessBusinessOrderList = new ArrayList<>();
        for (WaitScheOrderInfoBean waitScheOrderInfoBean : scheCarParamBean.getWaitScheOrderInfoList()) {
            ExpreessBusinessOrderVO expreessBusinessOrderVO = new ExpreessBusinessOrderVO();
            expreessBusinessOrderVO.setBusiOrderNO(genSchecarIdRequestResponse.getScheduCarId());
            expreessBusinessOrderVO.setBusiBookNO(waitScheOrderInfoBean.getBusiBookNo());
            expreessBusinessOrderVO.setBusiOrderType(2);//1:POP-W 2 M-W
            if (scheCarParamBean.getAppLoginInfo() != null && scheCarParamBean.getAppLoginInfo().getComAccount() != null) {
                expreessBusinessOrderVO.setDeliveryUserName(scheCarParamBean.getAppLoginInfo().getComAccount().getRealName());//快递员或司机姓名
                expreessBusinessOrderVO.setDeliveryUserTel(scheCarParamBean.getAppLoginInfo().getComAccount().getTelephone());//快递员或司机电话
            }
            expreessBusinessOrderVO.setProductType(MobileStationDefine.PRODUCT_TYPE_TCKD);
            expreessBusinessOrderVO.setStationCode(waitScheOrderInfoBean.getEndStationShortName());
            expreessBusinessOrderVO.setLastStationAcctId(scheCarParamBean.getAccountId());
            expreessBusinessOrderVO.setLastStationCode(MobileStationDefine.M);
            expreessBusinessOrderList.add(expreessBusinessOrderVO);
        }

        ExpreessMainOrder expreessMainOrder = new ExpreessMainOrder();
        expreessMainOrder.setBusiOrderNO(miStationMobileBookingForm.getBusiBookNo());
        expreessMainOrder.setBusiOrderType(2);
        expreessMainOrder.setCreateTime(new Date());
        expreessMainOrder.setLastStationAcctId(scheCarParamBean.getAccountId());
        expreessMainOrder.setLastStationCode(MobileStationDefine.M);
        expreessMainOrder.setStationCode(scheCarParamBean.getWaitScheOrderInfoList().get(0).getEndStationShortName());
        batchSyncBusinessOrderRequest.setExpreessMainOrder(expreessMainOrder);
        batchSyncBusinessOrderRequest.setExpreessBusinessOrderList(expreessBusinessOrderList);
        ServiceResponse serviceResponse = expressBusinessOrderWebService.batchSyncBusinessOrder(batchSyncBusinessOrderRequest);
        logger.info("调用蛙站接口 batchSyncBusinessOrder，入参：{} ， 返回：{}", JSON.toJSONString(batchSyncBusinessOrderRequest), serviceResponse);
        scheCarResultBean.setData(miStationMobileBookingForm);
        return scheCarResultBean;
    }

    /**
     * 咪站对派车单发起竞价 M-W
     * 1、修改M-W 订单状态 改为12待报价
     * 2、10分钟后，由中层选择价格最低 的车队报价 订单状态改为13已报价，如果没有报价，咪站状态改为40 可以重新发起竞价
     * 3、咪站选择是否确认该车队报价，如果确认，订单状态改为26待出库 再生成一条M-W的车队订单状态为1已接单
     * 4、如果咪站超过10分钟还未确认报价，默认流标状态改为40，可以重新发起竞价，
     *
     * @param miBidInitiationReq
     * @return
     */
    @Override
    public AppBaseResult miBidInitiation(MiBidInitiationReq miBidInitiationReq) throws Exception {
        AppBaseResult appBaseResult = new AppBaseResult(miBidInitiationReq);
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(miBidInitiationReq.getOrderId());
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
        giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
        giOrderTraceResynced.setTsAct(new Date());
        logger.info("notifyGps giOrderTraceResynced ={}", JSONArray.toJSONString(giOrderTraceResynced));
        //更新订单
        mobileBookingForm.setSelfQuoteValue(miBidInitiationReq.getSelfQuoteValue());
        mobileBookingForm.setSelfQuoteCurr(miBidInitiationReq.getSelfQuoteCurr());
        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TO_QUOTE);
        mobileBookingForm.setBidBy(MobileStationDefine.M);
        mobileBookingForm.setBidUser(miBidInitiationReq.getAcctUsername());
        mobileBookingForm.setBidUserId(miBidInitiationReq.getAccountId());
        mobileBookingForm.setCreateDate(new Date());
        mobileBookingForm.setVehicleTypeId(miBidInitiationReq.getVehicleTypeId());
        mobileBookingForm.setMileage(miBidInitiationReq.getMileage());
        mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
        //插入增值服务
        if (miBidInitiationReq.getValueAddList() != null) {
            List<MobileValueAddRel> mobileValueAddRelList = new ArrayList<>();
            for (MobileValueAdd mobileValueTemp : miBidInitiationReq.getValueAddList()) {
                //TODO 增值服务创建
                MobileValueAdd mva = mobileValueAddDao.selectByPrimaryKey(mobileValueTemp.getId());
                if (mva != null) {
                    //TODO 增值服务关联类创建
                    MobileValueAddRel mobileValueAddRel = new MobileValueAddRel();
                    mobileValueAddRel.setCreateDate(mobileBookingForm.getCreateDate());
                    mobileValueAddRel.setCreateUser(miBidInitiationReq.getAcctUsername());
                    mobileValueAddRel.setCreateUserId(miBidInitiationReq.getAccountId());
                    mobileValueAddRel.setMobileBookingFormId(mobileBookingForm.getId());
                    mobileValueAddRel.setValueAddId(mobileValueTemp.getId());
                    mobileValueAddRelList.add(mobileValueAddRel);
                }
            }
            if (CollectionUtils.isNotEmpty(mobileValueAddRelList)) {
                mobileValueAddDaoEx.batchInsert(mobileValueAddRelList);
            }
        }
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
        return appBaseResult;
    }

    private MobileBookingForm createNewMobileOrder(ScheCarParamBean scheCarParamBean, String scheduleCarNo)
            throws Exception {

        // 3.1、生成咪站的派车单 MOBILE_BOOKING_FORM
        MobileBookingForm miStationMobileBookingForm = buildMobileBookingForm(scheCarParamBean, scheduleCarNo);
        mobileBookingFormDao.insert(miStationMobileBookingForm);

        // 循环插入订单子表和货物表
        for (WaitScheOrderInfoBean waitScheOrderInfoBean : scheCarParamBean.getWaitScheOrderInfoList()) {
            MobileBookingForm dbMobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(waitScheOrderInfoBean
                    .getMobileBookingFormId());
            if (dbMobileBookingForm == null) {
                logger.info("mobileBookingFormId：{} ， 库中无记录！", waitScheOrderInfoBean.getMobileBookingFormId());
                throw new RuntimeException("mobileBookingFormId：" + waitScheOrderInfoBean.getMobileBookingFormId()
                        + "  ，  库中无记录！");
            }

            // 3.2、插入子表MOBILE_SCHEDU_SUB_ORDER
            MobileScheduSubOrder mobileScheduSubOrder = new MobileScheduSubOrder();
            BeanUtils.copyProperties(mobileScheduSubOrder, dbMobileBookingForm);
            mobileScheduSubOrder.setScheducarno(scheduleCarNo);
            mobileScheduSubOrder.setMobileBookingFormId(miStationMobileBookingForm.getId());
            mobileScheduSubOrder.setId(null);
            mobileScheduSubOrder.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
            mobileScheduSubOrderDao.insert(mobileScheduSubOrder);

            // 3.3、插入货物表MOBILE_GOODS_DTL
            List<MobileGoodsDtl> mobileGoodsDtlList = mobileGoodsDtlDaoEx
                    .selectByMobileBookingFormId(waitScheOrderInfoBean.getMobileBookingFormId());
            if (CollectionUtils.isNotEmpty(mobileGoodsDtlList)) {
                for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
                    mobileGoodsDtl.setId(null);
                    mobileGoodsDtl.setMobileBookingFormId(miStationMobileBookingForm.getId());
                    mobileGoodsDtl.setMobileScheduOrderId(mobileScheduSubOrder.getId());
                    mobileGoodsDtlDao.insert(mobileGoodsDtl);
                }
            }

            // 3.4、更新之前的老订单列表
            dbMobileBookingForm.setScheducarno(null);
            dbMobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
            mobileBookingFormDao.updateByPrimaryKey(dbMobileBookingForm);
        }

        return miStationMobileBookingForm;

    }

    private MobileBookingForm buildMobileBookingForm(ScheCarParamBean scheCarParamBean, String scheduleCarNo)
            throws Exception {

        MobileBookingForm dbMobileBookingForm = null;
        //咪站得到的钱就是把所有要排货派车的订单钱的总和
        BigDecimal miPredictValue = new BigDecimal(0);
        for (WaitScheOrderInfoBean waitScheOrderInfoBean : scheCarParamBean.getWaitScheOrderInfoList()) {
            dbMobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(waitScheOrderInfoBean
                    .getMobileBookingFormId());
            if (dbMobileBookingForm != null && dbMobileBookingForm.getPredictValue() != null) {
                miPredictValue = miPredictValue.add(dbMobileBookingForm.getPredictValue());
                break;
            }
        }
        miPredictValue = miPredictValue.multiply(BigDecimal.valueOf(scheCarParamBean.getWaitScheOrderInfoList().size()));

        if (dbMobileBookingForm == null) {
            logger.info("入参中mobileBookingFormId列表在库中均无记录，入参：{}", JSON.toJSONString(scheCarParamBean));
            throw new RuntimeException("入参中mobileBookingFormId列表在库中均无记录，入参：" + JSON.toJSONString(scheCarParamBean));
        }

        // 组装需要copy属性的映射Map，key是数据源对象属性名，value是被赋值对象的属性名
        Map<String, String> mobileBookingFormPropertiesMap = new HashMap<>();

        mobileBookingFormPropertiesMap.put("shipCustProvide", "shipCustProvide");
        mobileBookingFormPropertiesMap.put("shipCustCity", "shipCustCity");
        mobileBookingFormPropertiesMap.put("shipCustCounty", "shipCustCounty");
        mobileBookingFormPropertiesMap.put("shipCustAddr", "shipCustAddr");
        mobileBookingFormPropertiesMap.put("shipCustLinkMan", "shipCustLinkMan");
        mobileBookingFormPropertiesMap.put("shipCustLinkTel", "shipCustLinkTel");
        mobileBookingFormPropertiesMap.put("shipLongitude", "shipLongitude");
        mobileBookingFormPropertiesMap.put("shipLatitude", "shipLatitude");
        mobileBookingFormPropertiesMap.put("cneeCustProvide", "cneeCustProvide");
        mobileBookingFormPropertiesMap.put("cneeCustCity", "cneeCustCity");
        mobileBookingFormPropertiesMap.put("cneeCustCounty", "cneeCustCounty");
        mobileBookingFormPropertiesMap.put("cneeCustAddr", "cneeCustAddr");
        mobileBookingFormPropertiesMap.put("cneeCustLinkMan", "cneeCustLinkMan");
        mobileBookingFormPropertiesMap.put("cneeCustLinkTel", "cneeCustLinkTel");
        mobileBookingFormPropertiesMap.put("cneeLongitude", "cneeLongitude");
        mobileBookingFormPropertiesMap.put("cneeLatitude", "cneeLatitude");
        mobileBookingFormPropertiesMap.put("startLocus", "startLocus");
        mobileBookingFormPropertiesMap.put("startLocusId", "startLocusId");
        mobileBookingFormPropertiesMap.put("destnLocus", "destnLocus");
        mobileBookingFormPropertiesMap.put("destnLocusId", "destnLocusId");

        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        ObjectFieldCopyUtil.copyProperties(mobileBookingFormPropertiesMap, mobileBookingForm, dbMobileBookingForm);


        mobileBookingForm.setProductType(MobileStationDefine.PRODUCT_TYPE_ITCYS);
        mobileBookingForm.setBusiBookNo(scheduleCarNo);
        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
        mobileBookingForm.setTransportType(Integer.parseInt(MobileStationDefine.TRANSPORT_TYPE_EXPRESS));
        mobileBookingForm.setOrderType(MobileStationDefine.GET_ORDER_TYPE);
        mobileBookingForm.setPayType(MobileStationDefine.PAYTYPE_GENERALACCT);
        mobileBookingForm.setIsJs(MobileStationDefine.HAD_JS);
        mobileBookingForm.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_M);
        mobileBookingForm.setScheducarno(scheduleCarNo);
        Integer miStationAcctId = scheCarParamBean.getWaitScheOrderInfoList().get(0).getStartStationAccountId();
        mobileBookingForm.setCreateUserId(miStationAcctId);
        ComAccount miStationAccount = comAccountService.queryAccountById(miStationAcctId);

        mobileBookingForm.setCreateUser(miStationAccount.getAcctUsername());
        mobileBookingForm.setCreateDate(new Date());
        mobileBookingForm.setRevUserId(miStationAcctId);
        mobileBookingForm.setRevUser(miStationAccount.getAcctUsername());
        mobileBookingForm.setRevCompanyId(scheCarParamBean.getCompanyAccountId());
        mobileBookingForm.setRevDate(new Date());
        mobileBookingForm.setRoleId(SysAccountRole.OPERATOR_MSTATION.getValue());
        mobileBookingForm.setPredictValue(miPredictValue);
        mobileBookingForm.setPredictCurr(dbMobileBookingForm.getPredictCurr());
        return mobileBookingForm;
    }

    /**
     * 组装对订单批量排货派车入参
     */
    private GenSchecarIdRequest buildGenSchecarIdRequest(ScheCarParamBean scheCarParamBean) {
        GenSchecarIdRequest genSchecarIdRequest = new GenSchecarIdRequest();
        genSchecarIdRequest.setStationId(scheCarParamBean.getWaitScheOrderInfoList().get(0).getStartStationAccountId());
        return genSchecarIdRequest;
    }

    /**
     * 校验参数合法性
     */
    private ScheCarResultBean verifyParam(ScheCarParamBean scheCarParamBean) {

        // 校验必填参数非空性
        ScheCarResultBean verifyResultBean = checkParamNotNull(scheCarParamBean);
        if (!verifyResultBean.reqResultFlag()) {
            return verifyResultBean;
        }

        // 校验站点信息是否合法
        verifyResultBean = verifyStationInfo(scheCarParamBean);
        if (!verifyResultBean.reqResultFlag()) {
            return verifyResultBean;
        }

        return buildScheCarResultBean(SysResult.SUCCESS.getValue(), "成功");
    }

    /**
     * 校验站点信息
     */
    private ScheCarResultBean verifyStationInfo(ScheCarParamBean scheCarParamBean) {

        // 初次赋值起始站点账号ID
        Integer miStationAccountId = -1;
        // 初次赋值终点账号ID
        // Integer waStationAccountId = -1;
        String waStationShortName = null;

        // 初次赋值起始站点和终点账号ID标志
        boolean hasSetValue = false;

        for (WaitScheOrderInfoBean waitScheOrderInfo : scheCarParamBean.getWaitScheOrderInfoList()) {
            if (!hasSetValue) {
                miStationAccountId = waitScheOrderInfo.getStartStationAccountId();
                waStationShortName = waitScheOrderInfo.getEndStationShortName();
                hasSetValue = true;
            }

            // 校验起始站点和终点是否一致
            if ((!miStationAccountId.equals(waitScheOrderInfo.getStartStationAccountId()))
                    || (!waStationShortName.equals(waitScheOrderInfo.getEndStationShortName()))) {
                logger.info("起点或终点比对不一致，起点咪站1：{}，起点咪站2：{}，终点蛙站1：{}，终点蛙站2：{}", miStationAccountId,
                        waitScheOrderInfo.getStartStationAccountId(), waStationShortName,
                        waitScheOrderInfo.getEndStationShortName());
                return buildScheCarResultBean(SysResult.FAILED.getValue(), "起点或终点不一致！");
            }

            // 校验手机订单终点站是否为非POD，若是，则不可以参与排货派车
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(waitScheOrderInfo
                    .getMobileBookingFormId());
            if (mobileBookingForm == null) {
                logger.info("手机站点订单主键：{}，库中无数据。", waitScheOrderInfo.getMobileBookingFormId());
                return buildScheCarResultBean(SysResult.FAILED.getValue(),
                        "订单号" + waitScheOrderInfo.getMobileBookingFormId() + "，库中无订单！");
            }
            if (MobileStationDefine.POD.equalsIgnoreCase(mobileBookingForm.getDestnLocus())) {
                logger.info("订单主键：{}，终点地址为用户终点地址。", waitScheOrderInfo.getMobileBookingFormId());
                return buildScheCarResultBean(SysResult.FAILED.getValue(), "用户终点地址不可生成派车单！");
            }
        }

        // 校验起始站点是否为咪站和终点站是否为蛙站
        List<Integer> miStationAccountRoleList = comAccountRoleRelDaoEx.getUserRoleIdByAccountId(miStationAccountId);
        boolean isMiStation = hasRoleId(miStationAccountRoleList, SysAccountRole.OPERATOR_MSTATION.getValue());
        if (!isMiStation) {
            logger.info("站点：{}，不是咪站", miStationAccountId);
            return buildScheCarResultBean(SysResult.FAILED.getValue(), "起始站必须是咪站！");
        }

        return buildScheCarResultBean(SysResult.SUCCESS.getValue(), "成功");

    }

    /**
     * 判断集合中是否有指定的角色ID
     *
     * @param roleIdList 校验的角色集合
     * @param roleId     被校验的角色ID
     * @return true-有，false-无
     */
    private boolean hasRoleId(List<Integer> roleIdList, Integer roleId) {
        if (CollectionUtils.isEmpty(roleIdList)) {
            return false;
        }
        for (Integer rId : roleIdList) {
            if (rId.equals(roleId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验必填参数非空性
     */
    private ScheCarResultBean checkParamNotNull(ScheCarParamBean scheCarParamBean) {
        Map<String, List<String>> nullFieldMap = new HashMap<String, List<String>>();
        ObjectFiledNullVerityUtil.verifyFieldNotNull(scheCarParamBean, ObjectFieldNotNull.class, nullFieldMap, null);
        // 有必填属性为空
        if (nullFieldMap.size() > 0) {
            logger.info("批量生成排货派车单有必填参数为空，以下参数未传：{}", JSON.toJSONString(nullFieldMap));
            return buildScheCarResultBean(SysResult.FAILED.getValue(), "以下参数必填：" + nullFieldMap.toString());
        }

        return buildScheCarResultBean(SysResult.SUCCESS.getValue(), "成功");
    }

    /**
     * 组装返回结果对象
     */
    private ScheCarResultBean buildScheCarResultBean(Integer resultCode, String resultMsg) {
        ScheCarResultBean result = new ScheCarResultBean();
        result.setRetCode(resultCode);
        result.setRetMsg(resultMsg);
        return result;
    }

    private ScheCarResultBean buildScheCarResultBeanByResponse(GenSchecarIdRequestResponse genSchecarIdRequestResponse) {
        ScheCarResultBean result = new ScheCarResultBean();
        // 0成功，非0失败
        if ("0".equals(genSchecarIdRequestResponse.getCode())) {
            result.setRetCode(SysResult.SUCCESS.getValue());
        } else {
            result.setRetCode(SysResult.FAILED.getValue());
        }
        result.setRetMsg(genSchecarIdRequestResponse.getMsg());
        return result;
    }

    public AppBaseResult miOrderAssign(MobileOrderAssignReq mobileOrderAssignReq) throws Exception {
        AppBaseResult baseResBean = new AppBaseResult(mobileOrderAssignReq);
        //咪站派车 M-W 广播给司机

        //获取原始要指派的咪站订单
        MobileBookingForm assignForm = mobileBookingFormDao.selectByPrimaryKey(mobileOrderAssignReq.getOrderId());
        int flag = 0;
        if (null != assignForm) {
            // 判断咪站原单状态 只有状态为40 可以进行批量排货派车
            if (assignForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
                throw new MobileStationBizException("咪站派车失败,该订单不在待派车状态！");
            }

            //校验是否发过广播单
            MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(
                    mobileOrderAssignReq.getBusiBookNo(), null, null, MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER, null);
            if (mobileBookingForm != null) {
                throw new MobileStationBizException("该订单已经广播单已存在,不能" + (mobileOrderAssignReq.isBroadcast() ? "重复发送！" : "指派"));
            }
            assignForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_BROADCAST);
            flag = mobileMyOrderDao.updateByPrimaryKey2(assignForm);
            if (flag == 0) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("广播失败！");
                return baseResBean;
            }

            // 生成新的MS的订单
            MobileBookingForm assignA = new MobileBookingForm();
            //如果是咪站指派
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
                assignA.setProductType(MobileStationDefine.PRODUCT_TYPE_ITCYS);
                assignA.setNarrate(mobileOrderAssignReq.getNarrate());

                assignA.setPayType(MobileStationDefine.PAYTYPE_GENERALACCT);// 平台支付
                assignA.setOrderType(2);// 都是派件单

                assignA.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST);
                assignA.setRevUserId(null);
                assignA.setRevUser(null);
                assignA.setRevCompanyId(null);
                assignA.setRoleId(mobileOrderAssignReq.getDestRoleId());
                flag = mobileBookingFormDao.insert(assignA);
                if (flag == 0) {
                    throw new MobileStationBizException("此单已存在，请勿重复操作");
                }
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
                throw new MobileStationBizException("广播失败");
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
                //广播单进行广播
                broadcastMSOrder(assignA);
                assignForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_BROADCAST);
                List<MobileBookingForm> oldMobileBookingForms = new ArrayList<>();
                oldMobileBookingForms.add(assignForm);
            }
        }
        if (flag < 1) {
            throw new MobileStationBizException("咪站派车失败！");
        } else {
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
        }
        return baseResBean;
    }

    /**
     * 咪站-指派发送广播
     * 咪站广播发起竞价寻找车队  40->22
     *
     * @param mobileBookingForm
     */
    private void broadcastMSOrder(MobileBookingForm mobileBookingForm) {
        try {
            List<GiBizOrder> giBizOrderList = new ArrayList<>();
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
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
            allRoleCode.add(SysAccountRole.OPERATOR_COMPANY_FLEET.getRoleCode());
            List<String> allModuleCode = new ArrayList<>();
            allModuleCode.add(MobileStationDefine.PRODUCT_TYPE_ITCYS);
            giBizOrder.setAllModuleCode(allModuleCode);
            giBizOrder.setIsRealName(true);
            giBizOrder.setAllRoleCode(allRoleCode);
            giBizOrderList.add(giBizOrder);
            logger.info("23 broadCast Order sendBroadCastOrderMessage ={}", JSONArray.toJSONString(giBizOrderList));
            gpsOrderService.sendBroadCastOrderMessage(giBizOrderList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
