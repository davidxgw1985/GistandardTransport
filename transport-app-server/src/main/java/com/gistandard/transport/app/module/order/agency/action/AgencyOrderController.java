package com.gistandard.transport.app.module.order.agency.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.module.api.action.ApiController;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.agency.bean.*;
import com.gistandard.transport.order.module.agency.service.AgencyOrderService;
import com.gistandard.transport.order.module.customer.bean.ReceiveCustomerOrderReq;
import com.gistandard.transport.order.webservice.client.merchant.order.BaseRequestResult;
import com.gistandard.transport.order.webservice.client.merchant.order.Exception_Exception;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xgw
 * @ClassName AgencyOrderController
 * @Description MS3.0代理下单
 * @Version 3.0
 * @Date 2016/6/22
 */
@Controller
@RequestMapping(value = AppServerDefine.AGENCY_ORDER_URL)
public class AgencyOrderController extends ApiController {

    private static final String TAGS_DESC = "自理下单";
    private static final Logger logger = LoggerFactory.getLogger(AgencyOrderController.class);
    @Autowired
    private AgencyOrderService agencyOrderService;
    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;

    @ApiOperation(value = "自理下单-订单列表查询接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "MS代理下单订单列表查询接口")
    @RequestMapping(value = "/queryList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AgencyOrderListRes queryList(@RequestBody AgencyOrderListReq agencyOrderListReq) throws Exception {
        logger.info("agencyOrder queryList jsonStr={}", JSON.toJSONString(agencyOrderListReq));
        AgencyOrderListRes res = agencyOrderService.queryList(agencyOrderListReq);
        return res;
    }

    @ApiOperation(value = "自理下单-订单详细查询接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "MS代理下单订单详细查询接口")
    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult queryDetail(@RequestBody AgencyOrderDetailReq agencyOrderDetailReq) throws Exception {
        logger.info("agencyOrder queryDetail jsonStr={}", JSON.toJSONString(agencyOrderDetailReq));
        AppBaseResult res = agencyOrderService.queryDetail(agencyOrderDetailReq);
        return res;
    }

    @ApiOperation(value = "自理下单-接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "MS代理下单接口")
    @RequestMapping(value = "/agencyPlaceOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult agencyPlaceOrder(@RequestBody PlaceAnOrderReq placeAnOrderReq) throws Exception {
        logger.info("agencyOrder agencyPlaceOrder jsonStr={}", JSON.toJSONString(placeAnOrderReq));
        AppBaseResult appBaseResult = new AppBaseResult(placeAnOrderReq);
        if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(placeAnOrderReq.getItemCode()) && placeAnOrderReq.getMileage() == null) {
            appBaseResult.setRetCode(SystemDefine.FAILURE);
            appBaseResult.setRetMsg("同城专送单必须有公里数！");
            return appBaseResult;
        }
        AgencyPlaceOrderRes agencyPlaceOrderRes = agencyOrderService.agencyPlaceOrder(placeAnOrderReq);
        //更新路由
        if (placeAnOrderReq.getItemCode() != null && !MobileStationDefine.PRODUCT_TYPE_TCZS.equals(placeAnOrderReq.getItemCode())) {
            try {
                BaseRequestResult result = mobileRecOrderWebService.updateOrderRouteId(agencyPlaceOrderRes.getData().getBusiBookNo());
                if (result == null || result.getStatus() != 1) {
                    throw new MobileStationBizException(result == null ? "调用签派更新路由失败" : (result.getMesasge() == null ? "调用签派更新路由失败" : result.getMesasge()));
                }
            } catch (Exception_Exception e) {
                e.printStackTrace();
                throw new MobileStationBizException(e.getMessage());
            }
        }
        if (agencyPlaceOrderRes.getRetCode() == SystemDefine.SUCCESS) {
            appBaseResult = agencyOrderService.agencyPlaceOrderAfter(agencyPlaceOrderRes);
            if (appBaseResult.getRetCode() != SystemDefine.SUCCESS) {
                agencyPlaceOrderRes.setRetCode(-1);
                agencyPlaceOrderRes.setRetMsg(appBaseResult.getRetMsg());
            }
        }
        logger.info("agencyOrder agencyPlaceOrder res={}", JSON.toJSONString(agencyPlaceOrderRes));
        return agencyPlaceOrderRes;
    }

    @ApiOperation(value = "自理下单-支付成功回调接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "MS代理下单,支付成功回调接口")
    @RequestMapping(value = "/paySuccess", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult paySuccess(@RequestBody ReceiveCustomerOrderReq receiveCustomerOrderReq) throws Exception {
        logger.info("agencyOrder paySuccess jsonStr={}", JSON.toJSONString(receiveCustomerOrderReq));
        return agencyOrderService.paySuccessed(receiveCustomerOrderReq);
    }

    @ApiOperation(value = "自理下单-取消投保继续下单接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "MS代理下单,取消投保继续下单接口")
    @RequestMapping(value = "/cancelInsuredContinueOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult cancelInsuredContinueOrder(@RequestBody ReceiveCustomerOrderReq receiveCustomerOrderReq) throws Exception {
        logger.info("agencyOrder cancelInsuredContinueOrder jsonStr={}", JSON.toJSONString(receiveCustomerOrderReq));
        return agencyOrderService.cancelInsuredContinueOrder(receiveCustomerOrderReq);
    }

}
