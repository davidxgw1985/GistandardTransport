package com.gistandard.transport.app.module.order.transport.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.order.customer.bean.PlaceAnOrderResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.service.ComAccountService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.CustomerOrderService;
import com.gistandard.transport.order.module.customer.TransportOrderService;
import com.gistandard.transport.order.module.customer.bean.*;
import com.gistandard.transport.order.webservice.client.merchant.order.Exception_Exception;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.upload.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 同城运输控制类
 * @author 员伟
 */
@RequestMapping(value = AppServerDefine.TRANSPORT_ORDER_URL)
@Controller
@Api(value = "同城运输下单模块", tags = "同城运输下单模块")
public class TransportOrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TransportOrderController.class);

    private static final String TAGS_DESC = "同城运输模块";

    @Autowired
    private TransportOrderService transportOrderService;

    @Autowired
    private CustomerOrderService customerOrderService;

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
    private ComAccountService comAccountService;

    @Autowired
    private UploadService uploadService;


    @ResponseBody
    @ApiOperation(value = "同城运输下单APP订单保存接口-V1.0.1", httpMethod = "POST", response = PlaceAnOrderResult.class,
             tags = TAGS_DESC, produces = "application/json", notes = "同城运输下单APP订单保存")
    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public PlaceAnOrderResult placeOrder(@RequestBody PlaceAnOrderReq req) throws MobileStationBizException, Exception_Exception {
        if (req == null) {
            throw new MobileStationBizException("请求参数异常");
        }
        logger.debug("-同城运输用户下单请求--{}", JSON.toJSONString(req));
        PlaceAnOrderResult res = new PlaceAnOrderResult(req);

        PlaceAnOrderRes placeAnOrderRes = transportOrderService.placeAnOrder(req);
        res.setData(placeAnOrderRes);
        //TODO 同城运输 用户也是需要通知GPS的
        placeAnOrderRes.setRoleId(SysAccountRole.NORMAL_PERSONAL.getValue());
        customerOrderService.notifyGps(placeAnOrderRes,null);
        logger.debug("同城运输客户下单APP订单保存 apply操作结果{}", JSON.toJSONString(res));
        return res;
    }


    @ApiOperation(value = "获取当前系统的时间戳", httpMethod = "GET",tags = TAGS_DESC, notes = "获取当前系统的时间戳")
    @RequestMapping(value = "/getSystemTimestamp", method = RequestMethod.GET)
    @ResponseBody
    public Object getSystemTimestamp() throws Exception {

        Map<String, Date> data = new HashMap<>();
        data.put("time", new Date());
        return JSON.toJSON(data);
    }


    @ResponseBody
    @ApiOperation(value = "OTCYSEX同城运输确认报价接口-V1.0.1", httpMethod = "POST", response = QuotePriceRes.class,
            tags = TAGS_DESC,  produces = "application/json", notes = "同城运输确认报价接口")
    @RequestMapping(value = "/quotePrice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public QuotePriceRes quotePrice(@RequestBody QuotePriceReq req) throws MobileStationBizException, Exception_Exception {
        //判断参数合法性
        if (req == null) {
            throw new MobileStationBizException("请求参数异常");
        }
        logger.debug("OTCYSEX同城运输确认报价接口请求参数--{}", JSON.toJSONString(req));
        //处理同城运输确认或取消报价
        QuotePriceRes res = transportOrderService.handleQuotePrice(req);
        //打印结果到日志
        logger.debug("OTCYSEX同城运输确认报价-接口操作结果{}", JSON.toJSONString(res));
        return res;
    }


    @ResponseBody
    @ApiOperation(value = "同城运输用户或者咪站取消订单接口-V1.0.1", httpMethod = "POST", response = CancleOrderRes.class,
            tags = TAGS_DESC,  produces = "application/json", notes = "同城运输用户或者咪站取消订单接口")
    @RequestMapping(value = "/cancleOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public CancleOrderRes cancleOrder(@RequestBody CancleOrderReq req) throws MobileStationBizException, Exception_Exception {
        //1. 判断参数合法性
        if (req == null) {
            throw new MobileStationBizException("请求参数异常");
        }
        logger.debug("同城运输用户或者咪站取消订单接口req-{}", JSON.toJSONString(req));
        //2. 同城运输用户或者咪站取消订单接口
        CancleOrderRes res = transportOrderService.handleCancleOrder(req);
        //3. 打印结果到日志
        logger.debug("同城运输用户或者咪站取消订单接口,返回结果-{}", JSON.toJSONString(res));
        return res;
    }



}
