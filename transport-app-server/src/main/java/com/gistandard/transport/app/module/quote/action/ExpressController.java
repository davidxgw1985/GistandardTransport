package com.gistandard.transport.app.module.quote.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.app.dubbo.wechat.bean.QueryPlatFormExpressPriceReq;
import com.gistandard.transport.app.module.quote.bean.*;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.app.system.common.util.CustomerUtils;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SysResult;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.quote.system.database.bean.QueryPlatformQuoteReq2;
import com.gistandard.transport.quote.system.database.services.ExpressService;
import com.gistandard.transport.system.center.search.bean.QueryDistanceByLatAndLonResult;
import com.gistandard.transport.system.center.search.service.SenderSearchService;
import com.gistandard.transport.system.common.bean.AddressReq;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.logToPsc.service.LogToPscService;
import com.gistandard.transport.system.webservice.client.calcWebService.CalcWebService;
import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuote;
import com.gistandard.transport.system.webservice.client.payinfo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.Exception;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by m on 2016/5/19.
 */
@Controller
@RequestMapping(value = AppServerDefine.EXPRESS_QUOTE_URL)
@Api(value = "平台报价模块", tags = "平台报价模块")
public class ExpressController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressController.class);

    @Autowired
    private QueryCalcManagerWebService queryCalcManagerWebService;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private LogToPscService logToPscService;
    @Value("#{logToPsc.customerQueryPlatFormPriceLog}")
    private String customerQueryPlatFormPriceLog;
    @Autowired
    private CustomerUtils customerUtils;
    @Autowired
    private ComProvinceService comProvinceService;
    @Autowired
    private ComCityService comCityService;
    @Autowired
    private ComCountyService comCountyService;
    @Autowired
    private SenderSearchService senderSearchService;
    @Autowired
    private CalcWebService calcWebService;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @RequestMapping(value = "/queryPlatFormExpressPrice", method = {RequestMethod.POST})
    @ResponseBody
    public void queryPlatFormExpressPrice(@RequestBody QueryPlatFormExpressPriceReq req) throws CustomerBizException, Exception_Exception {
        LOGGER.info(JSONObject.toJSONString(req));
        PlatFormInModel queryPlatFormExpressPriceModel = new PlatFormInModel();
        AppBaseRequest appBaseRequest = JSON.parseObject(JSON.toJSONString(req), AppBaseRequest.class);
        QueryPlatFormExpressPriceResult res = new QueryPlatFormExpressPriceResult(appBaseRequest);
        try {
            BeanUtils.copyProperties(queryPlatFormExpressPriceModel, req);
        } catch (IllegalAccessException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        } catch (InvocationTargetException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }

        //计算体积重
        if (req.getVolume() != null && req.getVolume().intValue() != 0) {
            BigDecimal volumeWht = req.getVolume().divide(new BigDecimal(6000), 4, BigDecimal.ROUND_HALF_EVEN);
            if (volumeWht.compareTo(req.getWeight()) > 0) {
                req.setWeight(volumeWht);
            }
        }
        LOGGER.info("调用结算 获取报价估价接口请求：------------------>{}", JSONObject.toJSONString(queryPlatFormExpressPriceModel));
        PlatFormOutModel request = queryCalcManagerWebService.getQuote(queryPlatFormExpressPriceModel);
        if (request.getStatus().equals("0")) {
            //成功
            res.setData(request);
        } else {
            throw new CustomerBizException(request.getMessage());
        }
        LOGGER.info("调用结算 获取报价估价接口返回：------------------>{}", JSONObject.toJSONString(request));
        writeJson(res);
    }


    @RequestMapping(value = "/compareExpressPrice", method = {RequestMethod.POST})
    @ResponseBody
    public void compareExpressPrice(@RequestBody QueryPlatFormExpressPriceReq req) throws CustomerBizException, Exception_Exception {
        LOGGER.info("比价 compareExpressPrice req{}",JSON.toJSONString(req));
        PlatFormInModel platFormInModel = new PlatFormInModel();
        AppBaseRequest appBaseRequest = JSON.parseObject(JSON.toJSONString(req), AppBaseRequest.class);
        QueryComparePriceResult res = new QueryComparePriceResult(appBaseRequest);
        try {
            BeanUtils.copyProperties(platFormInModel, req);
            //现在最新比价操作,校验参数
            if (req != null && req.validateRequestParam()) {
                PlatFormOutModWrap platFormOutModWrap = expressService.priceCompareOperate(platFormInModel);
                res.setData(platFormOutModWrap);
            } else {
                res.setRetCode(-1);
                res.setRetMsg("参数校验失败");
            }
        } catch (IllegalAccessException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        } catch (InvocationTargetException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        LOGGER.info("调用结算 返回的json数据：------------------>{}", JSONObject.toJSONString(res));
        writeJson(res);
    }


    /*@SysControllerLog(description = "查询平台预估快递报价金额")*/
    @RequestMapping(value = "/queryExpressTransportPayMoney", method = {RequestMethod.POST})
    @ResponseBody
    public void queryExpressTransportPayMoney(@RequestBody QueryExpressTransportPayMoneyReq req) throws Exception {
        LOGGER.info("查询平台预估快递报价金额 queryExpressTransportPayMoney {}", JSON.toJSONString(req));
        QueryExpressTransportPayMoneyResult res = new QueryExpressTransportPayMoneyResult(req);
        String flag = null;//如果为空是查询O单、否则为I单
        if (req.getBusiBookNo().startsWith("M_") || req.getBusiBookNo().startsWith("S_")) {
            flag = "1";
        }
        ValidBillMst validBillMst = queryCalcManagerWebService.queryExpressTransportPayMoney(req.getBusiBookNo(), flag, "82098f5ab036412d9042837036b02c72", req.getgFUserFromCode(), null);
        if (validBillMst != null) {
            ValidBillMstResBean validBillMstResBean = new ValidBillMstResBean();
            BeanUtils.copyProperties(validBillMstResBean, validBillMst);
            if (flag == null) {
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusiBookNo());
                if (bookingForm != null && bookingForm.getRevUser() != null) {
                    validBillMstResBean.setExpressUser(bookingForm.getRevUser());
                    validBillMstResBean.setProductType(bookingForm.getTransportType());
                    ComAccount comAccount = comAccountDao.selectByPrimaryKey(bookingForm.getRevUserId());
                    if (comAccount != null) {
                        validBillMstResBean.setExpressName(comAccount.getRealName());
                    }
                    if ("1".equals(bookingForm.getCargoLoader()) && "2".equals(bookingForm.getOrderForm())) {
                        validBillMstResBean.setTestFlag(1);
                    } else {
                        validBillMstResBean.setTestFlag(0);
                    }
                }
            }
            res.setData(validBillMstResBean);
        } else {
            int i = 10;
            while (validBillMst == null && i > 0) {
                i--;
                LOGGER.info("调用结算 queryExpressTransportPayMoney 请求：------------------>{}", JSONObject.toJSONString(req));
                validBillMst = queryCalcManagerWebService.queryExpressTransportPayMoney(req.getBusiBookNo(), flag, "82098f5ab036412d9042837036b02c72", req.getgFUserFromCode(), null);
                if (validBillMst != null) {
                    ValidBillMstResBean validBillMstResBean = new ValidBillMstResBean();
                    BeanUtils.copyProperties(validBillMstResBean, validBillMst);
                    if (flag == null) {
                        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusiBookNo());
                        if (bookingForm != null && bookingForm.getRevUser() != null) {
                            validBillMstResBean.setExpressUser(bookingForm.getRevUser());
                            validBillMstResBean.setProductType(bookingForm.getTransportType());
                            ComAccount comAccount = comAccountDao.selectByPrimaryKey(bookingForm.getRevUserId());
                            if (comAccount != null) {
                                validBillMstResBean.setExpressName(comAccount.getRealName());
                            }
                            if ("1".equals(bookingForm.getCargoLoader()) && "2".equals(bookingForm.getOrderForm())) {
                                validBillMstResBean.setTestFlag(1);
                            } else {
                                validBillMstResBean.setTestFlag(0);
                            }
                        }
                    }
                    res.setData(validBillMstResBean);
                }
            }
            if (validBillMst == null) {
                res.setData(null);
            }

        }
        LOGGER.info("调用结算 queryExpressTransportPayMoney 结果：------------------>{}", JSONObject.toJSONString(res));
        writeJson(res);
    }

    /*@SysControllerLog(description = "MS查询平台预估快递报价金额")*/
    @RequestMapping(value = "/queryExpressTransport", method = {RequestMethod.POST})
    @ResponseBody
    public void queryExpressTransport(@RequestBody QueryPlatFormExpressPriceReq req) throws Exception {
        LOGGER.info("queryExpressTransport: {}", JSONObject.toJSONString(req));
        String fromCode = null;
        String arriveCode = null;
        if (req.getFromCountyCode() != null) {
            fromCode = req.getFromCountyCode();
        } else if (req.getFromCityCode() != null) {
            fromCode = req.getFromCityCode();
        }
        if (req.getArriveCountyCode() != null) {
            arriveCode = req.getArriveCountyCode();
        } else if (req.getArriveCityCode() != null) {
            arriveCode = req.getArriveCityCode();
        }
        QueryDistanceByLatAndLonResult resPageBean = senderSearchService.queryDistanceByLatAndLon(fromCode, arriveCode, req.getShipLatitude(), req.getShipLongitude(), req.getCneeLatitude(), req.getCneeLongitude());
        if (resPageBean.getRetCode() == 1) {
            BigDecimal mileage = resPageBean.getData();
            req.setMileage(mileage);
        } else {
            throw new CustomerBizException("查询公里数失败");
        }
        PlatFormInModel queryPlatFormExpressPriceModel = new PlatFormInModel();
        AppBaseRequest appBaseRequest = JSON.parseObject(JSON.toJSONString(req), AppBaseRequest.class);
        QueryExpressTransportResult res = new QueryExpressTransportResult(appBaseRequest);
        BeanUtils.copyProperties(queryPlatFormExpressPriceModel, req);
        //计算体积重
        if (queryPlatFormExpressPriceModel.getVolume() != null && queryPlatFormExpressPriceModel.getVolume().intValue() != 0) {
            BigDecimal volumeWht = queryPlatFormExpressPriceModel.getVolume().divide(new BigDecimal(6000), 4, BigDecimal.ROUND_HALF_EVEN);
            if (volumeWht.compareTo(queryPlatFormExpressPriceModel.getWeight()) > 0) {
                queryPlatFormExpressPriceModel.setWeight(volumeWht);
            }
        }
        LOGGER.info("调用结算 获取报价估价接口请求：------------------>{}", JSONObject.toJSONString(queryPlatFormExpressPriceModel));
        PlatFormOutModel request = queryCalcManagerWebService.getQuote(queryPlatFormExpressPriceModel);
        if (request.getStatus().equals("0")) {
            //成功
            List<PlatFormDetailModelMore> platFormDetailModelMores = new ArrayList();
            for (PlatFormDetailModel platFormDetailModel : request.getQuoteDetailList()) {
                PlatFormDetailModelMore platFormDetailModelMore = new PlatFormDetailModelMore();
                BeanUtils.copyProperties(platFormDetailModelMore, platFormDetailModel);
                setArea(platFormDetailModelMore, platFormDetailModel);
                platFormDetailModelMores.add(platFormDetailModelMore);
            }
            res.setData(platFormDetailModelMores);
            res.setRecordCount(request.getQuoteCount());
        } else {
            throw new CustomerBizException(request.getMessage());
        }
        LOGGER.info("调用结算 获取报价估价接口返回：------------------>{}", JSONObject.toJSONString(request));
        writeJsonDisableCircularReferenceDetect(res);
    }

    /*@SysControllerLog(description = "查询平台报价")*/
    @RequestMapping(value = "/queryPlatformQuote", method = {RequestMethod.POST})
    @ResponseBody
    public void QueryPlatformQuote(@RequestBody QueryPlatformQuoteReq req) throws Exception {
        LOGGER.info("QueryPlatformQuote: " + JSONObject.toJSONString(req));
        if (req.getItemCode().equals(MobileStationDefine.PRODUCT_TYPE_TCKD)) {
            req.setItemCode(MobileStationDefine.PRODUCT_TYPE_ITCKD);
        }
        List<PlatformQuote> platformQuotes = calcWebService.queryPlatformQuote(req.getSystemFlag(), req.getItemCode(), req.getUserScope());
        QueryPlatformQuoteResult result = new QueryPlatformQuoteResult();
        result.setData(platformQuotes);
        writeJsonDisableCircularReferenceDetect(result);
    }

    @ApiOperation(value = "查询平台报价（新）接口-V1.0.1", httpMethod = "POST", response = QueryPlatformQuoteResult2.class)
    @RequestMapping(value = "/queryPlatformQuote2", method = {RequestMethod.POST})
    @ResponseBody
    public QueryPlatformQuoteResult2 QueryPlatformQuote2(@RequestBody QueryPlatformQuoteReq2 req) throws Exception {
        LOGGER.info("QueryPlatformQuote2: " + JSONObject.toJSONString(req));
        Integer roleId = null;
        //M站得到的钱传M-M
        if (req.getStartLocus().equals(MobileStationDefine.POP)) {
            if (req.getDestLocus().equals(MobileStationDefine.M)) {
                roleId = SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue();
            } else {
                roleId = SysAccountRole.OPERATOR_MSTATION.getValue();
            }
        } else if (req.getStartLocus().equals(MobileStationDefine.M)) {
            if (req.getDestLocus().equals(MobileStationDefine.POD)) {
                roleId = SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue();
            } else if (req.getDestLocus().equals(MobileStationDefine.M)) {
                roleId = SysAccountRole.OPERATOR_MSTATION.getValue();
                req.setDestLocus(MobileStationDefine.POD);
            } else {
                roleId = SysAccountRole.OPERATOR_CAR_OWNER.getValue();
            }
        }
        PlatformQuote platformQuote = expressService.queryPlatformQuote2(req.getSystemFlag(), req.getBusibookno(), req.getStartLocus(), req.getDestLocus(), roleId);
        QueryPlatformQuoteResult2 result = new QueryPlatformQuoteResult2();
        if (platformQuote != null) {
            result.setData(platformQuote);
        } else {
            result.setRetCode(SysResult.FAILED.getValue());
            result.setRetMsg("查不到平台报价");
        }
        return result;
    }

    private void setArea(PlatFormDetailModelMore platFormDetailModelMore, PlatFormDetailModel platFormDetailModel) throws CustomerBizException {
        Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
        Map<String, ComCity> comCityMap = comCityService.queryForMap();
        Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
        AddressReq addressReq = new AddressReq();
        addressReq.setAreaId(platFormDetailModel.getStartRoute());
        customerUtils.setArea(addressReq);
        if (addressReq.getProvince() != null) {
            platFormDetailModelMore.setStartProvince(addressReq.getProvince());
            ComProvince comProvince = comProvinceMap.get(addressReq.getProvince());
            platFormDetailModelMore.setStartProvinceName(comProvince.getProvinceName());
        }
        if (addressReq.getCity() != null) {
            platFormDetailModelMore.setStartCity(addressReq.getCity());
            ComCity comCity = comCityMap.get(addressReq.getCity());
            platFormDetailModelMore.setStartCityName(comCity.getName());
        }
        if (addressReq.getCounty() != null) {
            platFormDetailModelMore.setStartCounty(addressReq.getCounty());
            ComCounty comCounty = comCountyMap.get(addressReq.getCounty());
            platFormDetailModelMore.setStartCountyName(comCounty.getAreaName());
        }
        addressReq.setAreaId(platFormDetailModel.getEndRoute());
        customerUtils.setArea(addressReq);
        if (addressReq.getProvince() != null) {
            platFormDetailModelMore.setEndProvince(addressReq.getProvince());
            ComProvince comProvince = comProvinceMap.get(addressReq.getProvince());
            platFormDetailModelMore.setEndProvinceName(comProvince.getProvinceName());
        }
        if (addressReq.getCity() != null) {
            platFormDetailModelMore.setEndCity(addressReq.getCity());
            ComCity comCity = comCityMap.get(addressReq.getCity());
            platFormDetailModelMore.setEndCityName(comCity.getName());
        }
        if (addressReq.getCounty() != null) {
            platFormDetailModelMore.setEndCounty(addressReq.getCounty());
            ComCounty comCounty = comCountyMap.get(addressReq.getCounty());
            platFormDetailModelMore.setEndCountyName(comCounty.getAreaName());
        }
    }
}
