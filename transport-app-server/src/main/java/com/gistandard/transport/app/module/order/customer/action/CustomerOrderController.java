package com.gistandard.transport.app.module.order.customer.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.app.module.order.customer.bean.*;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.BookingForm;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.base.entity.service.ComAccountService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.CustomerOrderService;
import com.gistandard.transport.order.module.customer.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationAcceptOrderReq;
import com.gistandard.transport.order.module.mobilestation.service.MobileAcceptOrderService;
import com.gistandard.transport.order.webservice.client.merchant.order.BaseRequestResult;
import com.gistandard.transport.order.webservice.client.merchant.order.Exception_Exception;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import com.gistandard.transport.system.center.pay.bean.SafePayInfoReq;
import com.gistandard.transport.system.center.pay.bean.SafePayInfoRes;
import com.gistandard.transport.system.center.pay.service.SenderCenterPayService;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.tools.util.Base64Util;
import com.gistandard.transport.tools.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.DataOutputStream;
import java.util.List;

/**
 * Created by m on 2016/9/30.
 */
@RequestMapping(value = AppServerDefine.ORDER_URL)
@Controller
@Api(value = "客户下单模块", tags = "客户下单模块")
public class CustomerOrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerOrderController.class);
    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileAcceptOrderService mobileAcceptOrderService;
    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;
    //    @Autowired
//    private PscOperateLogService pscOperateLogService;
    @Value("#{logToPsc.customerPlaceOrderLog}")
    private String customerPlaceOrderLog;
    @Value("#{logToPsc.customerQueryOrderLog}")
    private String customerQueryOrderLog;
    @Value("#{logToPsc.customerBroadcastOrderRevLog}")
    private String customerBroadcastOrderRevLog;
    @Value("#{logToPsc.customerBroadcastOrderCacelLog}")
    private String customerBroadcastOrderCacelLog;
    @Value("#{logToPsc.customerOrderFollowLog}")
    private String customerOrderFollowLog;
    @Autowired
    private SenderCenterPayService senderCenterPayService;

    @ResponseBody
    @ApiOperation(value = "客户下单APP订单保存接口-V1.0.1", httpMethod = "POST", response = PlaceAnOrderResult.class,
            produces = "application/json", notes = "客户下单APP订单保存")
    @RequestMapping(value = "/apply", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public PlaceAnOrderResult apply(@RequestBody PlaceAnOrderReq req) throws MobileStationBizException, Exception_Exception {
        logger.debug("apply jsonStr={}", JSON.toJSONString(req));
        PlaceAnOrderResult res = new PlaceAnOrderResult(req);
        if (req != null && req.getAppLoginInfo() != null && req.getAppLoginInfo().getCurrentComCustomer() != null) {
            if (req.getAppLoginInfo().getCurrentComCustomer().getAccountId() != null) {
                req.setStaffAccountId(req.getAppLoginInfo().getCurrentComCustomer().getAccountId());
            }
        }
        if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(req.getItemCode()) && req.getMileage() == null) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("同城专送单必须有公里数！");
            return res;
        }

        PlaceAnOrderRes placeAnOrderRes = null;
        if ((req.isStartErr() != null && req.isStartErr()) || (req.isDestnErr() != null && req.isDestnErr())) {
            //下单地址有错误
            logger.debug("apply error order start--------");
            placeAnOrderRes = customerOrderService.placeAnErrOrder(req);
            placeAnOrderRes.setRoleId(SysAccountRole.NORMAL_PERSONAL.getValue());
            customerOrderService.notifyGps(placeAnOrderRes, true);
        } else {
            placeAnOrderRes = customerOrderService.placeAnOrder(req);
            //更新路由
            if (req.getItemCode() != null && !MobileStationDefine.PRODUCT_TYPE_TCZS.equals(req.getItemCode())) {
                BaseRequestResult result = mobileRecOrderWebService.updateOrderRouteId(placeAnOrderRes.getBusiBookNo());
                if (result == null || result.getStatus() != 1) {
                    throw new MobileStationBizException(result == null ? "调用签派更新路由失败" : (result.getMesasge() == null ? "调用签派更新路由失败" : result.getMesasge()));
                }
            }
            placeAnOrderRes.setRoleId(SysAccountRole.NORMAL_PERSONAL.getValue());
            customerOrderService.notifyGps(placeAnOrderRes, null);
            if (placeAnOrderRes.getForceAccept() != null && placeAnOrderRes.getForceAccept() == 1) {
                //客户下单快递员，强制接单
                MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(placeAnOrderRes.getMobileBookFormId());
                if (mobileBookingForm != null) {
                    MobileStationAcceptOrderReq acceptOrderReq = new MobileStationAcceptOrderReq();
                    acceptOrderReq.setAppLoginInfo(new AppLoginInfo());
                    acceptOrderReq.setRoleId(SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue());
                    acceptOrderReq.setAccountId(mobileBookingForm.getRevUserId());
                    acceptOrderReq.setAcctUsername(mobileBookingForm.getRevUser());
                    acceptOrderReq.setLoginAcctUserName(mobileBookingForm.getRevUser());
                    acceptOrderReq.setLoginAccountId(mobileBookingForm.getRevUserId());
                    acceptOrderReq.setOrderFrom(mobileBookingForm.getOrderFrom());
                    acceptOrderReq.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                    acceptOrderReq.setOrderId(mobileBookingForm.getId());
                    if (mobileBookingForm.getTransportType() != null) {
                        acceptOrderReq.setTransportType(mobileBookingForm.getTransportType().toString());
                    }
                    mobileAcceptOrderService.acceptOrder(acceptOrderReq);
                }

            }
        }
        res.setData(placeAnOrderRes);
        logger.debug("客户下单APP订单保存 apply操作结果{}", JSON.toJSONString(res));
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP编辑保单接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "客户下单APP编辑保单")
    @RequestMapping(value = "/updateOrderPolicy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult updateOrderPolicy(@RequestBody UpdateOrderPolicyReq req) {
        logger.debug("updateOrderPolicy jsonStr={}", JSON.toJSONString(req));
        AppBaseResult res = new AppBaseResult(req);

        try {
            res = customerOrderService.updateOrderPolicy(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("客户下单APP编辑保单 updateOrderPolicy操作结果{}", JSON.toJSONString(res));
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP货物确认接口-V1.0.1", httpMethod = "POST", response = OrderPolicyResult.class,
            produces = "application/json", notes = "客户下单APP货物确认")
    @RequestMapping(value = "/orderPolicy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public OrderPolicyResult orderPolicy(@RequestBody OrderPolicyBean req) {
        logger.debug("orderPolicy jsonStr={}", JSON.toJSONString(req));
        OrderPolicyResult res = new OrderPolicyResult(req);

        try {
            final boolean b = customerOrderService.orderPolicy(req);
            res.setData(b);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("客户下单APP货物确认 orderPolicy操作结果{}", JSON.toJSONString(res));
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP修改一口价-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "客户下单APP修改一口价")
    @RequestMapping(value = "/orderOnePrice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult orderOnePrice(@RequestBody QuoteOnePriceReq quoteInfoReq) {
        AppBaseResult res = new AppBaseResult();

        try {
            res = customerOrderService.orderOnePrice(quoteInfoReq);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("客户下单APP修改一口价 orderOnePrice操作结果{}", JSON.toJSONString(res));
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP订单查询-V1.0.1", httpMethod = "POST", response = OrderQueryResult.class,
            produces = "application/json", notes = "客户下单APP订单查询")
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public OrderQueryResult query(@RequestBody OrderQueryReq req) {
        logger.debug("query jsonStr={}", JSON.toJSONString(req));
        OrderQueryResult res = new OrderQueryResult(req);
        req.setAcctUsername(req.getAppLoginInfo().getAcctUsername());
        //企业账号
        req.setAccountId(req.getAppLoginInfo().getAccountId());
        if (req.getAppLoginInfo() != null && req.getAppLoginInfo().getCurrentComCustomer() != null) {
            if (req.getAppLoginInfo().getCurrentComCustomer().getAccountId() != null) {
                req.setStaffAccountId(req.getAppLoginInfo().getCurrentComCustomer().getAccountId());
            }
        }
        try {
            List<OrderQueryRes> queryRes = customerOrderService.queryList(req);
            res.setData(queryRes);
            int recordCount = customerOrderService.count(req);
            res.setRecordCount(recordCount);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
//        logger.debug("OrderQueryResult is={}", JSON.toJSONString(res));
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP订单状态修改-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "客户下单APP订单状态修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult update(@RequestBody OrderUpdateReq req) {
        logger.debug("update jsonStr={}", JSON.toJSONString(req));
        AppBaseResult res = new AppBaseResult(req);

        try {
            customerOrderService.update(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("客户下单APP订单状态修改 update操作结果{}", JSON.toJSONString(res));
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "订单关注/取关-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "订单关注/取关,status : 0关注/1取关/")
    @RequestMapping(value = "/follow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult follow(@RequestBody OrderFollowReq req) {
        logger.debug("follow jsonStr={}", JSON.toJSONString(req));
        AppBaseResult res = new AppBaseResult(req);

        try {
            if (req.getStatus() == 0) { //关注
                customerOrderService.follow(req);
            } else if (req.getStatus() == 1) {//取关
                customerOrderService.cancelFollow(req);
            }
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("订单关注/取关操作结果{}", JSON.toJSONString(res));
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "广播单取消下单接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "广播单取消下单接口")
    @RequestMapping(value = "/cacelBroadcastOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult cacelBroadcastOrder(@RequestBody ReceiveCustomerOrderReq req) {
        logger.debug("cacelBroadcastOrder jsonStr={}", JSON.toJSONString(req));
        AppBaseResult res = new AppBaseResult(req);

        try {
            customerOrderService.cacelBroadcastOrder(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("广播单取消下单接口 cacelBroadcastOrder操作结果{}", JSON.toJSONString(res));
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "广播单继续广播接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "广播单继续广播接口")
    @RequestMapping(value = "/broadcastOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult roadcastOrder(@RequestBody ReceiveCustomerOrderReq req) {
        logger.debug("roadcastOrder jsonStr={}", JSON.toJSONString(req));
        AppBaseResult res = new AppBaseResult(req);

        try {
            customerOrderService.broadcastOrder(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("广播单继续广播接口 roadcastOrder操作结果{}", JSON.toJSONString(res));
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "校验是否可以投保接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "校验是否可以投保接口")
    @RequestMapping(value = "/checkInsured", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult checkInsured(@RequestBody GoodsTypeBean req) {
        AppBaseResult res = new AppBaseResult(req);

        try {
            boolean result = customerOrderService.checkInsured(req);
            if (!result) {
                res.setRetCode(SystemDefine.FAILURE);
            }
        } catch (MobileStationBizException e) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("校验是否可以投保接口 checkInsured操作结果{}", JSON.toJSONString(res));
        return res;
    }

    @ApiOperation(value = "投保信息接口-V1.0.1", httpMethod = "POST", response = PayInfoResult.class,
            produces = "application/json", notes = "投保信息接口")
    @RequestMapping(value = "/goInsured", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PayInfoResult goInsured(@RequestBody SafePayInfoReq req) {
        logger.debug("goInsured jsonStr={}", JSON.toJSONString(req));
        PayInfoResult res = new PayInfoResult();

        try {
            SafePayInfoRes payInfoRes = senderCenterPayService.safePayInfo(req.getBusiBookNo());
            if (payInfoRes != null) {
                res.setRetCode(SystemDefine.SUCCESS);
                res.setData(payInfoRes);
            } else {
                res.setRetCode(SystemDefine.FAILURE);
                res.setRetMsg("查询投保信息失败");
            }
        } catch (Exception e) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("投保信息 goInsured操作结果{}", JSON.toJSONString(res));
        return res;
    }


    @ResponseBody
    @ApiOperation(value = "投保支付接口-V1.0.1", httpMethod = "POST", response = InsuredPayResult.class,
            produces = "application/json", notes = "投保支付接口")
    @RequestMapping(value = "/goInsuredPay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public InsuredPayResult goInsuredPay(@RequestBody InsuredPayReq req) {
        logger.debug("goInsuredPay jsonStr={}", JSON.toJSONString(req));
        InsuredPayResult res = new InsuredPayResult(req);

        try {
            InsuredPayRes insuredPayRes = customerOrderService.goInsuredPay(req);
            res.setData(insuredPayRes);
        } catch (MobileStationBizException e) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("投保支付接口 goInsuredPay操作结果{}", JSON.toJSONString(res));
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "支付成功回调接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "支付成功回调接口")
    @RequestMapping(value = "/paySuccess", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult paySuccess(@RequestBody ReceiveCustomerOrderReq req) {
        logger.debug("paySuccess jsonStr={}", JSON.toJSONString(req));
        AppBaseResult res = new AppBaseResult(req);

        try {
            customerOrderService.paySuccessed(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("支付成功回调 paySuccess操作结果{}", JSON.toJSONString(res));
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "取消投保继续下单接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "取消投保继续下单接口")
    @RequestMapping(value = "/cancelInsuredContinueOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult cancelInsuredContinueOrder(@RequestBody ReceiveCustomerOrderReq req) {
        logger.debug("cancelInsuredContinueOrder jsonStr={}", JSON.toJSONString(req));
        AppBaseResult res = new AppBaseResult(req);

        try {
            customerOrderService.cancelInsuredContinueOrder(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("取消投保继续下单 cancelInsuredContinueOrder操作结果{}", JSON.toJSONString(res));
        return res;
    }


    @ResponseBody
    @ApiOperation(value = "投保查询接口-V1.0.1", httpMethod = "POST", response = QueryPolicyResult.class,
            produces = "application/json", notes = "根据查询条件投保查询接口")
    @RequestMapping(value = "/queryPolicy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public QueryPolicyResult queryPolicy(@RequestBody QueryPolicyReq req) {
        logger.debug("queryPolicy jsonStr={}", JSON.toJSONString(req));
        QueryPolicyResult res = new QueryPolicyResult(req);

        try {
            QueryPolicyRes queryPolicyRes = customerOrderService.queryPolicy(req);
            res.setData(queryPolicyRes);
        } catch (MobileStationBizException e) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("投保查询 queryPolicy操作结果{}", JSON.toJSONString(res));
        return res;
    }


    @RequestMapping(value = {"/{unitCode}/{applyNo}/{busiBookNo}/goShowPdf"}, method = RequestMethod.GET)
    public ModelAndView goShowPdf(@PathVariable String unitCode, @PathVariable String applyNo, @PathVariable String busiBookNo) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("unitCode", unitCode);
        modelAndView.addObject("applyNo", applyNo);
        modelAndView.addObject("busiBookNo", busiBookNo);
        modelAndView.setViewName("showPdf");
        return modelAndView;
    }


    @RequestMapping(value = {"/{unitCode}/{applyNo}/{busiBookNo}/showPdf"}, method = RequestMethod.GET)
    public void showPdf(HttpServletResponse response, @PathVariable String unitCode, @PathVariable String applyNo, @PathVariable String busiBookNo) throws Exception {
        try {
            QueryPolicyReq req = new QueryPolicyReq();
            req.setUnitCode(unitCode);
            req.setApplyNo(applyNo);
            req.setBusiBookNo(busiBookNo);
            QueryPolicyRes queryPolicyRes = customerOrderService.queryPolicyForAndroid(req);
            if (!StringUtil.isEmpty(queryPolicyRes.getFileEpolicy())) {
                DataOutputStream temps = new DataOutputStream(response
                        .getOutputStream());
                temps.write(Base64Util.decode(queryPolicyRes.getFileEpolicy()));
                temps.flush();
                temps.close();
            }
        } catch (Exception e) {
        }
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP根据订单编号查询-V1.0.1", httpMethod = "POST", response = OrderQueryResult.class,
            produces = "application/json", notes = "客户下单APP根据订单编号查询")
    @RequestMapping(value = "/queryOrderByBusiNo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public OrderQueryResult queryOrderByBusiNo(@RequestBody OrderQueryReq req) {
        logger.debug("queryOrderByBusiNo jsonStr={}", JSON.toJSONString(req));
        OrderQueryResult res = new OrderQueryResult(req);
        try {
            List<OrderQueryRes> queryRes = customerOrderService.queryOrderByBusiNo(req);
            res.setData(queryRes);
            if (CollectionUtils.isNotEmpty(queryRes)) {
                res.setRecordCount(queryRes.size());
            }
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("queryOrderByBusiNo res={}", JSON.toJSONString(res));
        return res;
    }


    @ResponseBody
    @ApiOperation(value = "同城快递和同城专送取消订单-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "同城快递和同城专送取消订单")
    @RequestMapping(value = "/cancelOrder4KDZS", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult cancelOrder4KDZS(@RequestBody CancleOrderReq req) {
        logger.debug("cancelOrder4KDZS req={}", JSON.toJSONString(req));
        AppBaseResult res = new AppBaseResult(req);
        try {
            customerOrderService.cancelOrder4KDZS(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("同城快递和同城专送取消订单操作结果{}", JSON.toJSONString(res));
        return res;
    }


}
