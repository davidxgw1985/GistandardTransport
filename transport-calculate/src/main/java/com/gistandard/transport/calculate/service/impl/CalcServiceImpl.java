package com.gistandard.transport.calculate.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.ComCurrencyService;
import com.gistandard.transport.calculate.bean.calc.*;
import com.gistandard.transport.calculate.bean.calc.DataResult;
import com.gistandard.transport.calculate.service.CalcService;
import com.gistandard.transport.giifiCalc.external.bean.OCalcForPrice;
import com.gistandard.transport.quote.module.calc.bean.CalcForPlatPriceReq;
import com.gistandard.transport.quote.module.calc.bean.CalcForPriceReq;
import com.gistandard.transport.quote.module.calc.bean.CalcParamaters;
import com.gistandard.transport.quote.module.calc.quote.QuoteStrategyEnum;
import com.gistandard.transport.quote.module.calc.quote.impl.AbstractQuoteStrategy;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.quote.system.database.services.ComQuoteService;
import com.gistandard.transport.system.calc.service.CalcJmsService;
import com.gistandard.transport.system.im.ImMessageService;
import com.gistandard.transport.system.webservice.client.calcWebService.*;
import com.gistandard.transport.tools.util.DateUtil;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


/**
 * @author xgw
 * @ClassName WalletServiceImpl
 * @Description 我的-通用账户
 * @Version 1.0
 * @Date 2016/2/23
 */
@Service
public class CalcServiceImpl implements CalcService {
    private static final Logger logger = LoggerFactory.getLogger(CalcServiceImpl.class);
    @Autowired
    private CalcWebService calcWebService;
    @Autowired
    private ComQuoteService comQuoteService;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;
    @Autowired
    private ComCustomerDao comCustomerDao;
    @Autowired
    private ComCurrencyService comCurrencyService;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private ExpreessScheduCarDao expreessScheduCarDao;
    @Autowired
    private ImMessageService commonService;
    @Autowired
    private CalcJmsService calcJmsService;

    private AbstractQuoteStrategy abstractQuoteStrategy;
    @Value("#{spring.sysGFCode}")
    private String sysGFCode;   //平台公布价结算时的平台账号

    /**
     * O单结算 同步
     *
     * @param calcForPriceReq
     * @return
     */
    @Override
    public CalcForPlatPriceResult calcForPriceForOut(CalcForPriceReq calcForPriceReq) {
        CalcForPlatPriceResult calcForPlatPriceResult = new CalcForPlatPriceResult(calcForPriceReq);
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(calcForPriceReq.getBusiBookNo());
        try {
            CalcParamaters calcParamaters = new CalcParamaters();
            if (bookingForm == null) {
                calcForPlatPriceResult.setRetCode(SystemDefine.FAILURE);
                calcForPlatPriceResult.setRetMsg("该订单不存在！");
                return calcForPlatPriceResult;
            }
            if (!calcForPriceReq.getSystemFlag().equals("NJKD")) {
                calcParamaters = setCalcParamaters(calcParamaters, bookingForm);
            }
            int orderCatalog;//1：BUS号；2：签派号;3:派车单号
            if (calcForPriceReq.getScheducarno() != null) {
                orderCatalog = 3;
            } else {
                orderCatalog = 1;
            }

            //默认平台支付
            String paymentTerm = MobileStationDefine.PAYMENT_GENERALACCT;
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(calcForPriceReq.getOrderId());
            if (mobileBookingForm == null) {
                calcForPlatPriceResult.setRetCode(SystemDefine.FAILURE);
                calcForPlatPriceResult.setRetMsg("该订单不存在！");
                return calcForPlatPriceResult;
            }
            //如果是到付的o单结算，收款方是平台
            if (!StringUtil.isEmpty(calcForPriceReq.getBusiBookNo()) && calcForPriceReq.getgFUserFromCode().equals(sysGFCode)) {
                if (bookingForm != null && bookingForm.getPayType() != null && bookingForm.getPayType() == 1 && !StringUtil.isEmpty(bookingForm.getReceiverUser())) {
                    //到付
                    calcForPriceReq.setgFUserToCode(bookingForm.getReceiverUser());
                } else {
                    //寄付
                    if (bookingForm != null && bookingForm.getCreateCompanyId() != null) {
                        //企业下单，使用企业结算
                        ComAccount companyAcct = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
                        if (companyAcct != null) {
                            calcForPriceReq.setgFUserToCode(companyAcct.getAcctUsername());
                        }
                    }
                }
            }

            String result;
            logger.info("O单结算 同步calcForPriceForOut={},paymentTerm={}", JSONObject.toJSONString(calcForPriceReq), paymentTerm);
            if (orderCatalog == 2) {
                result = calcWebService.calcForPrice(calcForPriceReq.getSystemFlag(), orderCatalog, calcForPriceReq.getDispatchId() + "",
                        JSON.toJSONString(calcParamaters), calcForPriceReq.getgFUserFromCode(), calcForPriceReq.getgFUserToCode(),
                        paymentTerm, "", calcForPriceReq.getComQuoteId(), bookingForm.getId());
            } else {
                result = calcWebService.calcForPrice(calcForPriceReq.getSystemFlag(), orderCatalog, calcForPriceReq.getBusiBookNo(),
                        JSON.toJSONString(calcParamaters), calcForPriceReq.getgFUserFromCode(), calcForPriceReq.getgFUserToCode(),
                        paymentTerm, "", calcForPriceReq.getComQuoteId(), bookingForm.getId());
            }
            CalcForPriceRes res = JSON.parseObject(result, CalcForPriceRes.class);
            logger.info("O单结算 同步calcForPriceForOut CalcForPriceRes={}", JSONObject.toJSONString(res));
            if (res != null && res.isSucceed()) {
                calcForPlatPriceResult.setRetCode(SystemDefine.SUCCESS);
                CalcForPriceBean calcForPriceBean = new CalcForPriceBean();
                calcForPriceBean.setBillNo(res.getBillNo());
                calcForPriceBean.setAmount(res.getAmount());
                calcForPriceBean.setCurrency(res.getCurrency());
                calcForPlatPriceResult.setData(calcForPriceBean);
                //设置订单结算状态，并保存对账单
                mobileBookingForm.setIsJs(1);
                mobileBookingForm.setValidBillno(calcForPriceBean.getBillNo());
                if (calcForPriceBean.getAmount() != null) {
                    mobileBookingForm.setPredictValue(calcForPriceBean.getAmount());
                }
                if (calcForPriceBean.getCurrency() != null) {
                    mobileBookingForm.setPredictCurr(calcForPriceBean.getCurrency());
                }
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
            } else {
                calcForPlatPriceResult.setRetCode(SystemDefine.FAILURE);
                calcForPlatPriceResult.setRetMsg(res.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            calcForPlatPriceResult.setRetCode(SystemDefine.FAILURE);
            calcForPlatPriceResult.setRetMsg(e.getMessage());
        }
        if (SystemDefine.FAILURE == calcForPlatPriceResult.getRetCode() && calcForPlatPriceResult.getRetMsg() == null) {
            //失败单如果没有返回错误信息，设置错误信息
            calcForPlatPriceResult.setRetMsg("结算失败！");
        }
        return calcForPlatPriceResult;
    }

    /**
     * 预估结算接口
     *
     * @param calcForPriceReq
     */
    @Override
    public CalcForPlatPriceResult calcForTempPrice(CalcForPriceReq calcForPriceReq) {
        CalcForPlatPriceResult calcForPlatPriceResult = new CalcForPlatPriceResult(calcForPriceReq);
        try {
            List<CalcParamaters> calcParamatersList = new ArrayList<>();
            CalcParamaters calcParamaters = new CalcParamaters();
            calcParamatersList.add(setCalcParamaters(calcParamaters, calcForPriceReq));
            logger.info("CalcParamaters={}", JSONObject.toJSONString(calcParamatersList));
            String result = calcWebService.calcForTempPrice(calcForPriceReq.getSystemFlag(), JSON.toJSONString(calcParamatersList));
            CalcForTempPriceRes res = JSON.parseObject(result.replace("[", "").replace("]", ""), CalcForTempPriceRes.class);
            logger.info("CalcForTempPriceRes={}", JSONObject.toJSONString(res));
            if (res.getStatus() == 1) {
                calcForPlatPriceResult.setRetCode(SystemDefine.SUCCESS);
                CalcForPriceBean calcForPriceBean = new CalcForPriceBean();
                calcForPriceBean.setAmount(res.getAmount());
                calcForPriceBean.setCurrency(res.getCurrency());
                if (calcForPriceReq.getMileage() != null) {
                    calcForPriceBean.setMileage(calcForPriceReq.getMileage());
                }
                calcForPlatPriceResult.setRetMsg(res.getAmount() + comCurrencyService.getComCurrencyByCode(res.getCurrency()).getCurrencyCh());
                calcForPlatPriceResult.setData(calcForPriceBean);
            } else {
                calcForPlatPriceResult.setRetCode(SystemDefine.FAILURE);
                if (res != null && !StringUtil.isEmpty(res.getMessage())) {
                    calcForPlatPriceResult.setRetMsg(res.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            calcForPlatPriceResult.setRetCode(SystemDefine.FAILURE);
            calcForPlatPriceResult.setRetMsg(e.getMessage());
        }
        return calcForPlatPriceResult;
    }


    /**
     * 查询待支付列表
     *
     * @param appBasePagerRequest
     */
    @Override
    public FindAllValidBillMstResult findAllValidBillMst(AppBasePagerRequest appBasePagerRequest) {
        FindAllValidBillMstResult findAllValidBillMstResult = new FindAllValidBillMstResult(appBasePagerRequest);
        try {
            int pageNum = 1;
            if (appBasePagerRequest.getPageSize() != 0) {
                pageNum = 1 + appBasePagerRequest.getStartRecord() / appBasePagerRequest.getPageSize();
            }
            ValidResultBean validResultBean = calcWebService.findAllValidBillMst(appBasePagerRequest.getLoginAcctUserName(), appBasePagerRequest.getPageSize(), pageNum);
            if (validResultBean != null && validResultBean.isSucceed()) {
                findAllValidBillMstResult.setRetCode(SystemDefine.SUCCESS);
                if (null != validResultBean.getTotalRecords()) {
                    findAllValidBillMstResult.setRecordCount(validResultBean.getTotalRecords().intValue());
                }
                List<FindAllValidBillMstBean> validBillMstBeanList = new ArrayList<>();
                FindAllValidBillMstBean findAllValidBillMstBean;
                for (ValidBean validBean : validResultBean.getAllValidBillMst()) {
                    findAllValidBillMstBean = new FindAllValidBillMstBean();
                    PropertyUtils.copyProperties(findAllValidBillMstBean, validBean);
                    findAllValidBillMstBean.setProductType(validBean.getTransportType());
                    validBillMstBeanList.add(findAllValidBillMstBean);
                }
                findAllValidBillMstResult.setData(validBillMstBeanList);
            } else {
                findAllValidBillMstResult.setRetCode(SystemDefine.FAILURE);
                findAllValidBillMstResult.setRetMsg(validResultBean.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            findAllValidBillMstResult.setRetCode(SystemDefine.FAILURE);
        }
        return findAllValidBillMstResult;
    }

    private CalcParamaters setCalcParamaters(CalcParamaters calcParamaters, CalcForPriceReq calcForPriceReq) {
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(calcForPriceReq.getBusiBookNo());
        QuoteResultBean quoteResultBean = comQuoteService.getQuoteInfoByQuoteNo(calcForPriceReq.getComQuoteId());
        try {
            if (null == quoteResultBean || null == quoteResultBean.getComQuote()) {
                quoteResultBean = comQuoteService.getQuoteInfo(Integer.parseInt(calcForPriceReq.getComQuoteId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 备注：1，如果整车，则List<FormulaParameters>的长度为1，并且只有Price 和 Currency为必填,其他可不填。
         *       2,如果零担，则List<FormulaParameters>长度为2，并且只有StartRangeValue,RangeUnit,Price,Currency,Formula为必填。
         *         Formula=1代表重货价格，Formula=2代表轻货价格。
         *       3,如果是按里程保价，则List<FormulaParameters>长度为1，并且只有StartRangeValue,RangeUnit,Price,Currency为必填，其他可不填。
         *       4,如果是里程分段，则List<FormulaParameters>长度大于或等于1，全部字段都必填，并且公式的顺序和List<FormulaParameters>的序号保持一致。
         *       5,如果是重量分段，则其规则和4一致。
         *       6,面议。
         *       7,快递（按票）。
         *       8,快递（规则同重量分段）。
         **/

        ComQuote comQuote = quoteResultBean.getComQuote();
        if (null == comQuote.getQuoteType()) {
            return calcParamaters;
        } else {
            abstractQuoteStrategy = QuoteStrategyEnum.INSTANCE.getQuoteStrategy(comQuote.getQuoteType());
            abstractQuoteStrategy.setFormualParameters(calcParamaters, quoteResultBean, calcForPriceReq);
        }
        //如果是一口价的报价类型，结算方式改为面议
        if (comQuote.getItemId() == 42) {
            calcParamaters.setQuotationType(MobileStationDefine.QUOTE_TYPE_MY);
        }
        //设置订单号
        if (calcForPriceReq.getOrderFrom() != null) {
            if (calcForPriceReq.getOrderFrom() == 4) {
                //个人指派单，设置busiBookNo
                calcParamaters.setOrderNo(calcForPriceReq.getBusiBookNo());
            } else if (calcForPriceReq.getOrderFrom() == 1 || calcForPriceReq.getOrderFrom() == 3) {
                //签派单，设置dispatchId
                calcParamaters.setOrderNo(calcForPriceReq.getDispatchId() + "");
            } else if (calcForPriceReq.getOrderFrom() == 2) {
                //运输单，设置scheducarno
                calcParamaters.setOrderNo(calcForPriceReq.getScheducarno());
            } else {
                calcParamaters.setOrderNo(calcForPriceReq.getBusiBookNo());
            }
        }
        if (StringUtil.isEmpty(calcParamaters.getOrderNo())) {
            calcParamaters.setOrderNo("0");
        }
        calcParamaters.setMileageUnit(MobileStationDefine.COM_UNIT_MI);

        if (bookingForm != null && bookingForm.getWhtFeewht() != null && calcForPriceReq.getWeight() != null && bookingForm.getWhtFeewht().compareTo(calcForPriceReq.getWeight()) > 0) {
            calcParamaters.setWeight(bookingForm.getWhtFeewht());
        } else {
            calcParamaters.setWeight(calcForPriceReq.getWeight());
        }
        calcParamaters.setWeightUnit(calcForPriceReq.getWeightUnit());
        calcParamaters.setVolume(calcForPriceReq.getVolume());
        calcParamaters.setVolumeUnit(calcForPriceReq.getVolumeUnit());
        return calcParamaters;
    }

    @Override
    public BaseResBean getCalcStatus(CalcPricePayStatus calcPricePayStatus) {
        BaseResBean baseResBean = new BaseResBean(calcPricePayStatus);
        try {
            PayInfoResultBean payInfoResultBean = new PayInfoResultBean();
            //O单，直接根据busNo查询
            if (calcPricePayStatus.getOrderType() == 0) {
                if (StringUtil.isEmpty(calcPricePayStatus.getScheducarno())) {
                    //O单，直接根据busNo查询
                    payInfoResultBean = calcWebService.queryPayinfo(calcPricePayStatus.getBusiBookNo(), CalcPricePayStatus.validCode);
                } else {
                    //如果是派车单，获取子订单，并判断其支付状态,只有送达到POD才需要判断O单的支付状态，派车单只有一条子订单
                    List<MobileScheduSubOrder> subOrderList = mobileScheduSubOrderDaoEx
                            .selectByScheducarno(calcPricePayStatus.getScheducarno(), null, null);
                    for (MobileScheduSubOrder mobileScheduSubOrder : subOrderList) {
                        payInfoResultBean = calcWebService.queryPayinfo(mobileScheduSubOrder.getBusiBookNo(), CalcPricePayStatus.validCode);
                    }
                }
            } else {
                //若有scheducarno单号，可不传输busNo和dispatchId,但是得同时传递派车单号，和当前用户帐号
                if (!StringUtil.isEmpty(calcPricePayStatus.getScheducarno())) {
                    payInfoResultBean = calcWebService.queryIarPayinfo(null, null, calcPricePayStatus.getScheducarno(), calcPricePayStatus.getAcctUsername(), CalcPricePayStatus.validCode, null, calcPricePayStatus.getValidBillno());
                } else {
                    //I单，busNo和dispatchId一起传递，
                    payInfoResultBean = calcWebService.queryIarPayinfo(calcPricePayStatus.getBusiBookNo(), calcPricePayStatus.getDispatchId(), null, calcPricePayStatus.getAcctUsername(), CalcPricePayStatus.validCode, null, calcPricePayStatus.getValidBillno());
                }

            }
            if (payInfoResultBean.getResultCode() == 2) {
                //0 未生成账单，1请确认账单，2 请支付，3 已支付
                int status = payInfoResultBean.getAllPayInfoBeam().get(0).getStatus();
                switch (status) {
                    case 0:
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg(payInfoResultBean.getAllPayInfoBeam().get(0).getStatusDesc());
                        break;
                    case 1:
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg(payInfoResultBean.getAllPayInfoBeam().get(0).getStatusDesc());
                        break;
                    case 2:
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        String msg = payInfoResultBean.getAllPayInfoBeam().get(0).getPayGFUserName() + "运费" + payInfoResultBean.getAllPayInfoBeam().get(0).getAmount() + "元,未支付.";
//                        baseResBean.setRetMsg(payInfoResultBean.getAllPayInfoBeam().get(0).getStatusDesc());
                        baseResBean.setRetMsg(msg);
                        //未支付，通知用户支付
                        if (payInfoResultBean.getAllPayInfoBeam().get(0).getPayGFUserCode() != null) {
                            MsgIMReq msgIMReq = calcPricePayStatus.getPushMessageIMBean();
                            msgIMReq.setBusiBookNo(calcPricePayStatus.getBusiBookNo());
                            msgIMReq.setScheducarno(calcPricePayStatus.getScheducarno());
                            Map map = new HashMap();
                            map.put("bookbusino", calcPricePayStatus.getBusiBookNo());
                            map.put("acctUsername", calcPricePayStatus.getAcctUsername());
                            map.put("msgTime", DateUtil.formatDate2Str(new Date(), "yyyy-MM-dd"));
                            msgIMReq.setMapObject(map);
                            msgIMReq.setCreateUser(payInfoResultBean.getAllPayInfoBeam().get(0).getPayGFUserCode());
                            commonService.pushMessageIM(msgIMReq);
                        }
                        break;
                    case 3:
                        baseResBean.setRetMsg(payInfoResultBean.getAllPayInfoBeam().get(0).getStatusDesc());
                        break;

                }
            } else if (payInfoResultBean.getResultCode() == 1) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg(payInfoResultBean.getMessage());
            } else {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg(payInfoResultBean.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("查询失败");
        }


        return baseResBean;
    }

    /**
     * 查询订单是否可以解冻
     *
     * @param orderThawStatusReq
     * @throws Exception
     */
    @Override
    public BaseResBean getOrderThawStatus(OrderThawStatusReq orderThawStatusReq) {
        BaseResBean baseResBean = new BaseResBean(orderThawStatusReq);
        List<OrderThawBean> orderThawBeanList = new ArrayList<>();
        OrderThawBean orderThawBean;
        boolean thawStatus;
        try {
            for (ValidBean validBean : orderThawStatusReq.getAllValidBillMst()) {
                orderThawBean = new OrderThawBean();
                thawStatus = false;
                //如果是订单，判断BookingForm订单的状态，如果是已送达
                if (validBean.getInitDoc() == 1) {
                    BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(validBean.getInitDocNo());
                    if (bookingForm != null) {
                        Integer state = bookingForm.getBusiCtrl();
                        if (state != null && state == MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                            thawStatus = false;
                        } else {
                            thawStatus = true;
                        }
                    }
                } else if (validBean.getInitDoc() == 2) {
                    //如果是派车单,判断派车单的执行情况，是否已送达HUB，或者是送达POD  三种情况 POP-HUB、HUB-HUB、HUB-POD
                    ExpreessScheduCar expreessScheduCar = expreessScheduCarDao.selectByPrimaryKey(validBean.getInitDocNo());
                    if (expreessScheduCar != null) {
                        //0默认创建 1 已经接单  2.拒绝  3 放弃(接单后)4.已发车 5.已确认送达 6.紧急指派   7.取件/配送失败 8.退回
                        String state = expreessScheduCar.getMsOrderStatus();
                        if (state != null && state.equals(String.valueOf(MobileStationDefine.MOBILE_ORDER_STATUS_FINISH))) {
                            thawStatus = false;
                        } else {
                            thawStatus = true;
                        }
                    }
                }

                orderThawBean.setAmount(validBean.getPrice());
                orderThawBean.setRecPscCode(validBean.getRecGFUserCode());
                orderThawBean.setCurrencyCode(validBean.getCurrencyCode());
                orderThawBean.setDocNo(validBean.getDocNo());
                orderThawBean.setNeedConfirmPay(thawStatus);
                orderThawBean.setType(1);
                orderThawBeanList.add(orderThawBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("查询失败");
        }
        baseResBean.setData(orderThawBeanList);
        return baseResBean;
    }

    /**
     * O单同城快递 平台批量结算接口
     *
     * @param batchCalcForPlatReq
     * @return
     */
    @Override
    public BatchCalcForPlatPriceResult batchCalcForPrice(BatchCalcForPlatReq batchCalcForPlatReq) {
        BatchCalcForPlatPriceResult batchCalcForPlatPriceResult = new BatchCalcForPlatPriceResult(batchCalcForPlatReq);
        if (batchCalcForPlatReq == null || batchCalcForPlatReq.getCalcList() == null) {
            batchCalcForPlatPriceResult.setRetCode(SystemDefine.FAILURE);
            batchCalcForPlatPriceResult.setRetMsg("批量结算参数不能为空！");
            return batchCalcForPlatPriceResult;
        }
        List<CalcForPlatPriceResBean> calcForPlatPriceResList = new ArrayList<>();
        List<CheckOCalcForPrice> oCalcForPriceList = new ArrayList<>();
        for (CalcForPlatPriceBean platPriceBean : batchCalcForPlatReq.getCalcList()) {
            CalcForPlatPriceResBean resBean = new CalcForPlatPriceResBean();
            resBean.setBusiBookNo(platPriceBean.getBusiBookNo());
            BookingForm bookingForm;
            try {
                //1、判断是否为条形码的订单
                if (platPriceBean.getBusiType() == null) {
                    bookingForm = bookingFormDaoEx.getBookingFormByExpressOrderNo(platPriceBean.getBusiBookNo());
                    if (bookingForm != null) {
                        platPriceBean.setBusiBookNo(bookingForm.getBusiBookNo());
                    } else {
                        bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(platPriceBean.getBusiBookNo());
                        if (bookingForm != null) {
                            platPriceBean.setBusiBookNo(bookingForm.getBusiBookNo());
                        } else {
                            resBean.setRetCode(SystemDefine.FAILURE);
                            resBean.setRetMsg("订单号不正确！");
                            calcForPlatPriceResList.add(resBean);
                            continue;
                        }
                    }
                } else if (platPriceBean.getBusiType() == 2) {
                    bookingForm = bookingFormDaoEx.getBookingFormByExpressOrderNo(platPriceBean.getBusiBookNo());
                    if (bookingForm != null) {
                        platPriceBean.setBusiBookNo(bookingForm.getBusiBookNo());
                    } else {
                        resBean.setRetCode(SystemDefine.FAILURE);
                        resBean.setRetMsg("订单号不正确！");
                        calcForPlatPriceResList.add(resBean);
                        continue;
                    }
                } else {
                    bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(platPriceBean.getBusiBookNo());
                    if (bookingForm != null) {
                        platPriceBean.setBusiBookNo(bookingForm.getBusiBookNo());
                    } else {
                        resBean.setRetCode(SystemDefine.FAILURE);
                        resBean.setRetMsg("订单号不正确！");
                        calcForPlatPriceResList.add(resBean);
                        continue;
                    }
                }
                //3、设置订单类型
                int orderCatalog = 1;//1：BUS号

                //5、默认平台支付
                String paymentTerm = MobileStationDefine.PAYMENT_GENERALACCT;

                CheckOCalcForPrice oCalcForPrice = new CheckOCalcForPrice();
                oCalcForPrice.setOrderId(bookingForm.getId());
                oCalcForPrice.setSystemFlag(platPriceBean.getSystemFlag());
                oCalcForPrice.setOrderCatalog(orderCatalog);
                oCalcForPrice.setScanBusiNo(resBean.getBusiBookNo());
                CalcParamaters calcParamaters = new CalcParamaters();
                calcParamaters = setCalcParamaters(calcParamaters, bookingForm);
                oCalcForPrice.setCalcParamaters(JSON.toJSONString(calcParamaters));
                oCalcForPrice.setGFUserFromCode(sysGFCode);
                oCalcForPrice.setPaymentTerm(paymentTerm);
                oCalcForPrice.setStationCode(null);
                oCalcForPrice.setQuotationNo(bookingForm.getDocno());
                oCalcForPrice.setOrderNo(platPriceBean.getBusiBookNo());

                //如果是到付的o单结算，收款方是平台
                //设置付款用户
                oCalcForPrice.setGFUserToCode(platPriceBean.getgFUserToCode());
                if (!StringUtil.isEmpty(oCalcForPrice.getScanBusiNo())) {
                    if (bookingForm.getPayType() != null && bookingForm.getPayType() == 1 && !StringUtil.isEmpty(bookingForm.getReceiverUser())) {
                        //到付
                        oCalcForPrice.setGFUserToCode(bookingForm.getReceiverUser());
                    } else if (bookingForm.getPayType() != null && bookingForm.getPayType() == MobileStationDefine.PAYTYPE_CASH) {
                        //如果现金支付，支付人为当前收件的快递员
                        oCalcForPrice.setGFUserToCode(batchCalcForPlatReq.getAcctUsername());
                    } else {
                        //寄付
                        if (bookingForm != null && bookingForm.getCreateCompanyId() != null) {
                            //企业下单，使用企业结算
                            ComAccount companyAcct = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
                            if (companyAcct != null) {
                                oCalcForPrice.setGFUserToCode(companyAcct.getAcctUsername());
                            }
                        } else {
                            oCalcForPrice.setGFUserToCode(bookingForm.getCreateUser());
                        }
                    }
                }
                logger.info("结算信息 {}", JSON.toJSONString(oCalcForPrice));
                oCalcForPriceList.add(oCalcForPrice);
            } catch (Exception e) {
                e.printStackTrace();
                resBean.setRetCode(SystemDefine.FAILURE);
                resBean.setRetMsg(e.getMessage());
                calcForPlatPriceResList.add(resBean);
            }
        }
        if (oCalcForPriceList != null && oCalcForPriceList.size() > 0) {
            calcForPlatPriceResList = checkOCalcInfoList(oCalcForPriceList, calcForPlatPriceResList);
        }
        batchCalcForPlatPriceResult.setData(calcForPlatPriceResList);
        logger.info("O单同城快递 平台批量结算接口 batchCalcForPrice 操作完成");
        return batchCalcForPlatPriceResult;
    }

    /**
     * O单同城运输、I单同城运输 竞价的用户、咪站、蛙站和平台之间的结算
     * 司机收货时发起结算接口
     *
     * @param calcOtcysReq
     * @return
     */
    @Override
    public AppBaseResult calcForPriceTcys(CalcOtcysReq calcOtcysReq) {
        MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(calcOtcysReq.getOrderId());
        AppBaseResult appBaseResult = new AppBaseResult(calcOtcysReq);
        CalcForBidding calcForBidding = new CalcForBidding();
        calcForBidding.setGFUserFromCode(sysGFCode);
        calcForBidding.setGFUserFromName(MobileStationDefine.SYS_COMPANY_NAME);
        if (StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
            calcForBidding.setGFUserToCode(calcOtcysReq.getGfUserToCode());
            calcForBidding.setGFUserToName(calcOtcysReq.getGfUserToName());
        } else {
            ComAccount comAccount;
            if (mobileBookingForm.getCreateCompanyId() != null) {
                comAccount = comAccountDao.selectByPrimaryKey(mobileBookingForm.getCreateCompanyId());
            } else {
                comAccount = comAccountDao.selectByPrimaryKey(mobileBookingForm.getCreateUserId());
            }
            calcForBidding.setGFUserToCode(comAccount.getAcctUsername());
            calcForBidding.setGFUserToName(comAccount.getRealName());
        }
        calcForBidding.setAmount(calcOtcysReq.getAmount());
        calcForBidding.setCurrencyCode(calcOtcysReq.getCurrencyCode());
        calcForBidding.setOrderId(calcOtcysReq.getOrderId());
        calcForBidding.setOrderNo(mobileBookingForm.getBusiBookNo());
        int orderCatalog = 1;//1：BUS号 3 派车单号
        if (!StringUtil.isEmpty(mobileBookingForm.getScheducarno())) {
            orderCatalog = 3;
        }
        calcForBidding.setOrderCatalog(orderCatalog);
        //结算来源系统  MS：1,Hub:2
        calcForBidding.setSystemFlag("1");
        //默认平台支付 支付方式 MOP为月结，COD为现场付款,CASH为现金,GeneralAcct为通用账户支付
        calcForBidding.setPaymentTerm(MobileStationDefine.PAYMENT_GENERALACCT);
        if (calcOtcysReq.getTax() == null) {
            calcForBidding.setTax(new BigDecimal(0.06));
        } else {
            calcForBidding.setTax(calcOtcysReq.getTax());
        }
        calcForBidding.setOperateCode(calcOtcysReq.getAcctUsername());
        logger.info("calcOtcys bidding req={}", JSON.toJSONString(calcForBidding));
        String result = calcWebService.bidding(calcForBidding);
        logger.info("calcOtcys bidding res={}", result);
        CalcForPriceRes res = JSON.parseObject(result, CalcForPriceRes.class);
        if (res != null && res.isSucceed()) {
            mobileBookingForm.setValidBillno(res.getBillNo());
//            mobileBookingForm.setPredictValue(res.getAmount());
//            mobileBookingForm.setPredictCurr(res.getCurrency());
            mobileBookingForm.setIsJs(1);
            mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
            return appBaseResult;
        } else {
            appBaseResult.setRetCode(SystemDefine.FAILURE);
            if (res == null) {
                appBaseResult.setRetMsg("结算失败");
            } else {
                appBaseResult.setRetMsg(res.getMessage());
            }
        }
        return appBaseResult;
    }

    /**
     * O单发送队列消息
     *
     * @param oCalcForPriceList
     */
    private List<CalcForPlatPriceResBean> checkOCalcInfoList(List<CheckOCalcForPrice> oCalcForPriceList, List<CalcForPlatPriceResBean> CalcForPlatPriceResBeanList) {
        logger.info("checkParametersForOSettlementBatch={}", JSONObject.toJSONString(oCalcForPriceList));
        //校验I单结算入参
        String result = calcWebService.checkParametersForOSettlementBatch(oCalcForPriceList);
        logger.info("checkParametersForOSettlementBatch={}", result);
        DataResult res = JSON.parseObject(result, DataResult.class);
        //判断是否校验成功
        if (res.getSucceed()) {
            for (CalcDataResult calcDataResult : res.getRefObject()) {
                CalcForPlatPriceResBean resBean = new CalcForPlatPriceResBean();
                if (calcDataResult != null && calcDataResult.getSucceed()) {
                    resBean.setRetCode(SystemDefine.SUCCESS);
                    //校验成功，发送队列信息
                    for (CheckOCalcForPrice calcInfo : oCalcForPriceList) {
                        if (calcInfo.getScanBusiNo().equals(calcDataResult.getScanBusiNo())) {
                            try {
                                OCalcForPrice oCalcForPrice = new OCalcForPrice();
                                BeanUtils.copyProperties(calcInfo, oCalcForPrice);
                                oCalcForPrice.setgFUserFromCode(calcInfo.getGFUserFromCode());
                                oCalcForPrice.setgFUserToCode(calcInfo.getGFUserToCode());
                                calcJmsService.sendCalcForPriceMessage(oCalcForPrice);
                                logger.info("O单结算发送队列消息成功 sendCalcForPrice2Message={}", oCalcForPrice);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    resBean.setRetCode(SystemDefine.FAILURE);
                    resBean.setRetMsg(calcDataResult.getMessage());
                }

                resBean.setBusiBookNo(calcDataResult.getScanBusiNo());
                CalcForPlatPriceResBeanList.add(resBean);
            }
        } else {
            CalcForPlatPriceResBean resBean = new CalcForPlatPriceResBean();
            resBean.setRetCode(SystemDefine.FAILURE);
            resBean.setRetMsg(res.getMessage());
            for (CheckOCalcForPrice oCalcForPrice : oCalcForPriceList) {
                resBean.setBusiBookNo(oCalcForPrice.getScanBusiNo());
                CalcForPlatPriceResBeanList.add(resBean);
            }
        }
        return CalcForPlatPriceResBeanList;
    }

    /**
     * O单批量
     *
     * @param calcParamaters
     * @param bookingForm
     * @return
     */

    private CalcParamaters setCalcParamaters(CalcParamaters calcParamaters, BookingForm bookingForm) {
        CalcForPriceReq calcForPriceReq = new CalcForPriceReq();
        calcForPriceReq.setBusiBookNo(bookingForm.getBusiBookNo());
        calcForPriceReq.setComQuoteId(bookingForm.getDocno());
        calcForPriceReq.setWeight(bookingForm.getWhtFeewht());
        calcForPriceReq.setWeightUnit("035");
        calcForPriceReq.setVolume(bookingForm.getWhtVolwht());
        calcForPriceReq.setVolumeUnit("164");
        calcForPriceReq.setMileage(bookingForm.getMileage());
        calcForPriceReq.setMileageUnit("147");
        QuoteResultBean quoteResultBean = comQuoteService.getQuoteInfoByQuoteNo(bookingForm.getDocno());
        try {
            if (null == quoteResultBean || null == quoteResultBean.getComQuote()) {
                quoteResultBean = comQuoteService.getQuoteInfo(Integer.parseInt(bookingForm.getDocno()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 备注：1，如果整车，则List<FormulaParameters>的长度为1，并且只有Price 和 Currency为必填,其他可不填。
         *       2,如果零担，则List<FormulaParameters>长度为2，并且只有StartRangeValue,RangeUnit,Price,Currency,Formula为必填。
         *         Formula=1代表重货价格，Formula=2代表轻货价格。
         *       3,如果是按里程保价，则List<FormulaParameters>长度为1，并且只有StartRangeValue,RangeUnit,Price,Currency为必填，其他可不填。
         *       4,如果是里程分段，则List<FormulaParameters>长度大于或等于1，全部字段都必填，并且公式的顺序和List<FormulaParameters>的序号保持一致。
         *       5,如果是重量分段，则其规则和4一致。
         *       6,面议。
         *       7,快递（按票）。
         *       8,快递（规则同重量分段）。
         *       9,同城专送（规则同重量分段）。
         **/

        ComQuote comQuote = quoteResultBean.getComQuote();
        if (null == comQuote.getQuoteType()) {
            return calcParamaters;
        } else {
            abstractQuoteStrategy = QuoteStrategyEnum.INSTANCE.getQuoteStrategy(comQuote.getQuoteType());
            abstractQuoteStrategy.setFormualParameters(calcParamaters, quoteResultBean, calcForPriceReq);
        }
        //如果是一口价的报价类型，结算方式改为面议
        if (comQuote.getItemId() == 42) {
            calcParamaters.setQuotationType(MobileStationDefine.QUOTE_TYPE_MY);
        } else if (comQuote.getItemId() == 45) {
            calcParamaters.setQuotationType(MobileStationDefine.QUOTE_TYPE_SPECIAL_DELIVERY);
            if (quoteResultBean.getComQuotePriceList() != null && quoteResultBean.getComQuotePriceList().size() > 0) {
                calcParamaters.setAddScalar(quoteResultBean.getComQuotePriceList().get(0).getAddScalar());
                calcParamaters.setScalarUnit(quoteResultBean.getComQuotePriceList().get(0).getScalarUnit());
            }
        }
        //个人指派单，设置busiBookNo
        calcParamaters.setOrderNo(bookingForm.getBusiBookNo());
        if (StringUtil.isEmpty(calcParamaters.getOrderNo())) {
            calcParamaters.setOrderNo("0");
        }
        calcParamaters.setMileageUnit(MobileStationDefine.COM_UNIT_MI);

        if (bookingForm != null && bookingForm.getWhtFeewht() != null && calcForPriceReq.getWeight() != null && bookingForm.getWhtFeewht().compareTo(calcForPriceReq.getWeight()) > 0) {
            calcParamaters.setWeight(bookingForm.getWhtFeewht());
        } else {
            calcParamaters.setWeight(calcForPriceReq.getWeight());
        }
        calcParamaters.setWeightUnit(calcForPriceReq.getWeightUnit());
        calcParamaters.setVolume(calcForPriceReq.getVolume());
        calcParamaters.setVolumeUnit(calcForPriceReq.getVolumeUnit());
        return calcParamaters;
    }

    /**
     * 第三方代付接口
     *
     * @param thirdPartPayReq
     * @return
     */
    @Override
    public AppBaseResult thirdPartyPay(ThirdPartPayReq thirdPartPayReq) {
        AppBaseResult appBaseResult = new AppBaseResult(thirdPartPayReq);
        String result = calcWebService.thirdPartyPay(thirdPartPayReq.getValidBillno(), thirdPartPayReq.getgFUser3FToCode(), thirdPartPayReq.getgFUser3FToName(), thirdPartPayReq.getType());
        logger.info("thirdPartyPay Res={}", result);
        DataResult res = JSON.parseObject(result, DataResult.class);
        //判断是否校验成功
        if (!res.getSucceed()) {
            appBaseResult.setRetCode(SystemDefine.FAILURE);
            appBaseResult.setRetMsg(res.getMessage());
        }
        return appBaseResult;
    }

    /**
     * 打赏获取结算对账单
     *
     * @param rewardReq
     * @return
     */
    @Override
    public RewardRes reward(RewardReq rewardReq) {
        RewardRes rewardRes = new RewardRes(rewardReq);
        CalcForReward calcForReward = new CalcForReward();
        try {
            calcForReward.setCurrencyCode(rewardReq.getCurrencyCode());
            calcForReward.setPaymentTerm(rewardReq.getPaymentTerm());
            calcForReward.setAmount(rewardReq.getAmount());
            calcForReward.setGFUserFromCode(rewardReq.getGfUserFromCode());
            calcForReward.setGFUserFromName(rewardReq.getGfUserFromName());
            calcForReward.setGFUserToCode(rewardReq.getGfUserToCode());
            calcForReward.setGFUserToName(rewardReq.getGfUserToName());
            calcForReward.setOrderNo(rewardReq.getOrderNo());
            logger.info("打赏获取结算对账单 reward Req={}", JSON.toJSONString(calcForReward));
            String result = calcWebService.reward(calcForReward);
            logger.info("打赏获取结算对账单 reward Res={}", result);
            CalcDataResult res = JSON.parseObject(result, CalcDataResult.class);
            //判断是否校验成功
            if (res == null || !res.getSucceed()) {
                rewardRes.setRetCode(SystemDefine.FAILURE);
                rewardRes.setRetMsg(res.getMessage());
            } else {
                rewardRes.setBiilNo(res.getBillNo());
            }
        } catch (Exception e) {
            rewardRes.setRetCode(SystemDefine.FAILURE);
            rewardRes.setRetMsg("获取对账单失败！");
        }
        return rewardRes;
    }

    /**
     * 购买嗨付券结算
     *
     * @param purchaseReq
     * @return
     */
    @Override
    public PurchaseRes purchase(PurchaseReq purchaseReq) {
        PurchaseRes purchaseRes = new PurchaseRes(purchaseReq);
        PurchaseCoupon purchaseCoupon = new PurchaseCoupon();
        purchaseCoupon.setAmount(purchaseReq.getAmount());
        purchaseCoupon.setCurrencyCode(purchaseReq.getCurrencyCode());
        purchaseCoupon.setGFUserFromCode(purchaseReq.getgFUserFromCode());
        purchaseCoupon.setGFUserFromName(purchaseReq.getgFUserFromName());
        purchaseCoupon.setGFUserToCode(purchaseReq.getgFUserToCode());
        purchaseCoupon.setGFUserToName(purchaseReq.getgFUserToName());
        purchaseCoupon.setOrderNo(purchaseReq.getOrderNo());
        logger.info("购买嗨付券结算 purchase req={}", JSON.toJSONString(purchaseCoupon));
        String result = calcWebService.purchase(purchaseCoupon);
        logger.info("购买嗨付券结算 purchase res={}", result);
        CalcForPriceRes res = JSON.parseObject(result, CalcForPriceRes.class);

        if (res == null) {
            purchaseRes.setRetCode(SystemDefine.FAILURE);
            purchaseRes.setRetMsg("购买嗨付券结算失败");
        } else {
            if (!res.isSucceed()) {
                purchaseRes.setRetCode(SystemDefine.FAILURE);
                purchaseRes.setRetMsg(res.getMessage());
            } else {
                purchaseRes.setBillNo(res.getBillNo());
            }
        }
        return purchaseRes;
    }

}
