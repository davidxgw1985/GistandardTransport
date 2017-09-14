package com.gistandard.transport.order.webservice.server.mobilestation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationAcceptOrderReq;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationOrderResult;
import com.gistandard.transport.order.module.mobilestation.bean.want.GetByLineStartAndLineDestAndUserNameAndStationNameRes;
import com.gistandard.transport.order.module.mobilestation.bean.want.GetByLineStartAndLineDestAndUserNameAndStationNameResult;
import com.gistandard.transport.order.module.mobilestation.service.MobileAcceptOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileStationOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileWantService;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.*;
import com.gistandard.transport.order.webservice.server.mobilestation.service.MobileStationWebService;
import com.gistandard.transport.tools.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Map;

/**
 * @author yjf
 * @ClassName MobileStationWebService
 * @Description
 * @Version 1.0
 * @Date 2016-03-09
 */
@WebService
public class MobileStationWebServiceImpl implements MobileStationWebService {
    private static final Logger logger = LoggerFactory.getLogger(MobileStationWebServiceImpl.class);
    @Autowired
    private MobileStationOrderService mobileStationOrderService;

    @Autowired
    private ComProvinceService comProvinceService;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private ComCountyService comCountyService;


    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Autowired
    private MobileWantService mobileWantService;

    @Autowired
    private MobileAcceptOrderService mobileAcceptOrderService;

    /**
     * mobilestation3.0下单(包含I单和O单的逻辑)
     *
     * @param recordMobileStationOrderRequest
     * @return
     */
    @WebMethod(operationName = "receiveMobileStationOrder")
    @WebResult(name = "receiveMobileStationOrderResult")
    public String receiveMobileStationOrder(@WebParam(name = "receiveMobileStationOrderRequest") RecordMobileStationOrderRequest recordMobileStationOrderRequest) {
        logger.debug("mobilestation3.0下单 receiveMobileStationOrder " + JSONObject.toJSONString(recordMobileStationOrderRequest));

        AppBaseResult mobileStationOrderResBean = new AppBaseResult();
        if (!MobileStationDefine.POP.equals(recordMobileStationOrderRequest.getStartLocus()) && recordMobileStationOrderRequest.getStartLocusId() == null) {
            if (recordMobileStationOrderRequest.isRecordBrocast()) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                mobileStationOrderResBean.setRetMsg("起始站点账号不能为空 startLocusId");
                return JSONObject.toJSONString(mobileStationOrderResBean);
            }
        }
        if (!MobileStationDefine.POD.equals(recordMobileStationOrderRequest.getDestnLocus()) && recordMobileStationOrderRequest.getDestnLocusId() == null) {
            if (recordMobileStationOrderRequest.isRecordBrocast()) {
                mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
                mobileStationOrderResBean.setRetMsg("目的地站点账号不能为空 destnLocusId");
                return JSONObject.toJSONString(mobileStationOrderResBean);
            }
        }
        try {
            mobileStationOrderResBean = mobileStationOrderService.receiveMobileStationOrder(recordMobileStationOrderRequest);
        } catch (MobileStationBizException e) {
            e.printStackTrace();
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            mobileStationOrderResBean.setRetMsg(e.getMessage());
        }

        return JSONObject.toJSONString(mobileStationOrderResBean);
    }

    /**
     * mobilestation 3.0下单接口
     *
     * @param recordMobileStationOrderRequest
     * @return
     * @title recordMobileStationOrder s
     * @describe TODO
     * @overridden @see com.valueplus.system.webservice.server.mobilestation.service.MobileStationWebService#recordMobileStationOrder(com.valueplus.system.webservice.server.mobilestation.bean.RecordMobileStationOrderRequest)
     * @author M.simple
     */
    @Override
    public String recordMobileStationOrder(@WebParam(name = "recordMobileStationOrderRequest") RecordMobileStationOrderRequest recordMobileStationOrderRequest) {
        logger.debug("mobilestation3.0下单 recordMobileStationOrder " + JSONObject.toJSONString(recordMobileStationOrderRequest));

        MobileStationOrderResult mobileStationOrderResBean = new MobileStationOrderResult();
        if (!MobileStationDefine.POP.equals(recordMobileStationOrderRequest.getStartLocus()) && recordMobileStationOrderRequest.getStartLocusId() == null) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            mobileStationOrderResBean.setRetMsg("起始站点账号不能为空 startLocusId");
            return JSONObject.toJSONString(mobileStationOrderResBean);
        }
        if (!MobileStationDefine.POD.equals(recordMobileStationOrderRequest.getDestnLocus()) && recordMobileStationOrderRequest.getDestnLocusId() == null) {
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            mobileStationOrderResBean.setRetMsg("目的地站点账号不能为空 destnLocusId");
            return JSONObject.toJSONString(mobileStationOrderResBean);
        }
        try {
            mobileStationOrderResBean = mobileStationOrderService.recordMobileStationOrder(recordMobileStationOrderRequest);

            //如果是蛙站指派快递员订单，快递员强制接单
            if (!StringUtil.isEmpty(recordMobileStationOrderRequest.getScheducarno()) && recordMobileStationOrderRequest.getScheducarno().startsWith("S_")
                    && !recordMobileStationOrderRequest.isRecordBrocast()) {
                MobileBookingForm mobileBookingForm = mobileStationOrderResBean.getData();
                MobileStationAcceptOrderReq req = new MobileStationAcceptOrderReq();
                req.setRoleId(SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue());
                req.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                req.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU);
                req.setOrderId(mobileBookingForm.getId());
                req.setAccountId(mobileBookingForm.getRevUserId());
                req.setAcctUsername(mobileBookingForm.getRevUser());
                req.setCompanyAccountId(mobileBookingForm.getRevCompanyId());
                req.setLoginAcctUserName(mobileBookingForm.getRevUser());
                req.setScheducarno(mobileBookingForm.getScheducarno());
                if (mobileBookingForm.getTransportType() != null) {
                    req.setTransportType(mobileBookingForm.getTransportType() + "");
                }
                mobileAcceptOrderService.acceptOrder(req);
            }
        } catch (MobileStationBizException e) {
            e.printStackTrace();
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            mobileStationOrderResBean.setRetMsg(e.getMessage());
        }

        return JSONObject.toJSONString(mobileStationOrderResBean);
    }

    @Override
    public String recordMWMobileStationOrder(RecordMWMobileStationOrderRequest recordMWMobileStationOrderRequest) {
        logger.debug("mobilestation3.0下单 recordMobileStationOrder " + JSONObject.toJSONString(recordMWMobileStationOrderRequest));

        MobileStationOrderResult recordMWMobileStationOrderResBean = new MobileStationOrderResult();
        try {
            recordMWMobileStationOrderResBean = mobileStationOrderService.recordMWMobileStationOrder(recordMWMobileStationOrderRequest);
        } catch (MobileStationBizException e) {
            e.printStackTrace();
            recordMWMobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            recordMWMobileStationOrderResBean.setRetMsg(e.getMessage());
        }
        return JSONObject.toJSONString(recordMWMobileStationOrderResBean);
    }

    /**
     * 移动station下单实现
     *
     * @param mobileStationOrderBean
     * @return
     */
    @Override
    public String makerMobileStationOrder(MobileStationOrderBean mobileStationOrderBean) {
        AppBaseResult mobileStationOrderResBean = new AppBaseResult();
        try {
            mobileStationOrderResBean = mobileStationOrderService.makerOrder(mobileStationOrderBean);
        } catch (MobileStationBizException e) {
            e.printStackTrace();
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            mobileStationOrderResBean.setRetMsg(e.getMessage());
        }

        return JSONObject.toJSONString(mobileStationOrderResBean);
    }

    @Override
    public String makerMobileStationOrderTransport(MobileStationOrderTransportBean mobileStationOrderTransportBean) {
        AppBaseResult mobileStationOrderResBean = new AppBaseResult();
        try {
            mobileStationOrderResBean = mobileStationOrderService.makerOrder(mobileStationOrderTransportBean);
        } catch (MobileStationBizException e) {
            e.printStackTrace();
            mobileStationOrderResBean.setRetCode(SystemDefine.FAILURE);
            mobileStationOrderResBean.setRetMsg(e.getMessage());
        }

        return JSONObject.toJSONString(mobileStationOrderResBean);
    }

    /**
     * 获取订单地址信息
     *
     * @param getOrderAddressInfoReq
     * @return
     */
    @Override
    public GetOrderAddressInfoRes getOrderAddressInfo(GetOrderAddressInfoReq getOrderAddressInfoReq) {
        GetOrderAddressInfoRes res = new GetOrderAddressInfoRes();
        if (getOrderAddressInfoReq == null || getOrderAddressInfoReq.getType() == null || getOrderAddressInfoReq.getBusiNo() == null) {
            res.setStatus(SystemDefine.FAILURE);
            res.setMessage("请求参数不能为空！");
        } else {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(getOrderAddressInfoReq.getBusiNo());
            if (bookingForm != null) {
                res.setStatus(SystemDefine.SUCCESS);
                Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                Map<String, ComCity> comCityMap = comCityService.queryForMap();
                Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
                String address = "";
                if (getOrderAddressInfoReq.getType() == 0) {
                    //发货地地址
                    if (!StringUtil.isEmpty(bookingForm.getCarriageReceiProvince())) {
                        address += comProvinceMap.get(bookingForm.getCarriageReceiProvince()).getProvinceName();
                    }
                    if (!StringUtil.isEmpty(bookingForm.getCarriageReceiCity())) {
                        address += comCityMap.get(bookingForm.getCarriageReceiCity()).getName();
                    }
                    if (!StringUtil.isEmpty(bookingForm.getCarriageReceiCounty())) {
                        address += comCountyMap.get(bookingForm.getCarriageReceiCounty()).getAreaName();
                    }
                    res.setLatitude(bookingForm.getCarriageReceiLatitude());
                    res.setLongitude(bookingForm.getCarriageReceiLongitude());
                    res.setLocation(address + bookingForm.getCarriageReceiAddr());
                } else {
                    //收获地地址
                    if (!StringUtil.isEmpty(bookingForm.getCarriageDelivProvince())) {
                        address += comProvinceMap.get(bookingForm.getCarriageDelivProvince()).getProvinceName();
                    }
                    if (!StringUtil.isEmpty(bookingForm.getCarriageDelivCity())) {
                        address += comCityMap.get(bookingForm.getCarriageDelivCity()).getName();
                    }
                    if (!StringUtil.isEmpty(bookingForm.getCarriageDelivCounty())) {
                        address += comCountyMap.get(bookingForm.getCarriageDelivCounty()).getAreaName();
                    }
                    res.setLatitude(bookingForm.getCarriageDelivLatitude());
                    res.setLongitude(bookingForm.getCarriageDelivLongitude());
                    res.setLocation(address + bookingForm.getCarriageDelivAddr());
                }
            } else {
                res.setStatus(SystemDefine.FAILURE);
                res.setMessage("查询不到该订单！");
            }
        }
        return res;
    }

    @Override
    public String cancelMobileStationOrderTransport(MobileStationCancelOrderBean mobileStationCancelOrderBean) {
        AppBaseResult mobileStationCancelOrderResBean = new AppBaseResult();
        try {
            mobileStationCancelOrderResBean = mobileStationOrderService.cancelOrder(mobileStationCancelOrderBean);
        } catch (MobileStationBizException e) {
            e.printStackTrace();
            mobileStationCancelOrderResBean.setRetCode(SystemDefine.FAILURE);
            mobileStationCancelOrderResBean.setRetMsg(e.getMessage());
        }

        return JSONObject.toJSONString(mobileStationCancelOrderResBean);
    }

    @Override
    public String cancelMobileStationOrderDispatch(MobileStationCancelOrderBean mobileStationCancelOrderBean) {
        AppBaseResult mobileStationCancelOrderResBean = new AppBaseResult();
        try {
            mobileStationCancelOrderResBean = mobileStationOrderService.cancelOrderByDispatch(mobileStationCancelOrderBean);
        } catch (MobileStationBizException e) {
            e.printStackTrace();
            mobileStationCancelOrderResBean.setRetCode(SystemDefine.FAILURE);
            mobileStationCancelOrderResBean.setRetMsg(e.getMessage());
        }

        return JSONObject.toJSONString(mobileStationCancelOrderResBean);
    }

    @Override
    public String getMobileWantOrderInfo(MobileWantReq mobileWantReq) {
        GetByLineStartAndLineDestAndUserNameAndStationNameResult res = null;
        try {
            res = mobileWantService.getByLineStartAndLineDestAndUserNameAndStationName(mobileWantReq);
        } catch (MobileStationBizException e) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
        }

        return JSONObject.toJSONString(res);
    }

    @Override
    public String getMobileWantOrderSjInfo(MobileWantSjReq mobileWantSjReq) {
        GetByLineStartAndLineDestAndUserNameAndStationNameRes res = null;
        try {
            res = mobileWantService.getByLineStartAndLineDestAndUserNameAndStationName(mobileWantSjReq);
        } catch (MobileStationBizException e) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
        }

        return JSONObject.toJSONString(res);
    }

    @Override
    public AppBaseResult updateValidBillno(UpdateValidBillNoReq updateValidBillNoReq) {
        AppBaseResult updateValidBillNoRes = new AppBaseResult();
        try {
            updateValidBillNoRes = mobileStationOrderService.updateValidBillno(updateValidBillNoReq);
        } catch (MobileStationBizException e) {
            e.printStackTrace();
            updateValidBillNoRes.setRetCode(SystemDefine.FAILURE);
            updateValidBillNoRes.setRetMsg(e.getMessage());
        }

        return updateValidBillNoRes;
    }
}
