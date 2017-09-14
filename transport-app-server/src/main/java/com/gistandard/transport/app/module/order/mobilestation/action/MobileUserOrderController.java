package com.gistandard.transport.app.module.order.mobilestation.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.dubbo.order.bean.MobileUserOrderDetailReq;
import com.gistandard.transport.app.dubbo.order.bean.MobileUserOrderListReq;
import com.gistandard.transport.app.dubbo.order.bean.UserOrderQueryDetailResult;
import com.gistandard.transport.app.dubbo.order.bean.UserOrderQueryListResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStatusOperateReq;
import com.gistandard.transport.order.module.mobilestation.bean.userorder.*;
import com.gistandard.transport.order.module.mobilestation.service.MobileUserOrderService;
import com.gistandard.transport.system.common.controller.BaseController;
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
 * @ClassName MobileUserOrderController
 * @Description MobileStation用户订单操作
 * @Version 1.0
 * @Date 2016-06-03
 */
@Controller
@RequestMapping(value = AppServerDefine.USER_ORDER_URL)
public class MobileUserOrderController extends BaseController {

    private static final String TAGS_DESC = "用户订单";
    private static final Logger logger = LoggerFactory.getLogger(MobileUserOrderController.class);
    @Autowired
    private MobileUserOrderService mobileUserOrderService;

    @ApiOperation(value = "用户订单-列表查询，包含所有订单、待执行、执行中、失败单-V1.0.1", httpMethod = "POST", response = UserOrderQueryListResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/queryList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public UserOrderQueryListResult queryList(@RequestBody MobileUserOrderListReq mobileSingleListReq) throws Exception {
        logger.info("用户订单-列表查询 queryList param:{}", JSON.toJSONString(mobileSingleListReq));
        return mobileUserOrderService.queryList(mobileSingleListReq);
    }

    @ApiOperation(value = "用户订单-订单详细查询-V1.0.1", httpMethod = "POST", response = UserOrderQueryDetailResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public UserOrderQueryDetailResult queryDetail(@RequestBody MobileUserOrderDetailReq mobileUserOrderDetailReq) throws Exception {
        logger.info("用户订单-详细查询 queryDetail param:{}", JSON.toJSONString(mobileUserOrderDetailReq));
        return  mobileUserOrderService.queryDetail(mobileUserOrderDetailReq);
    }

    @ApiOperation(value = "用户订单-订单执行操作-V1.0.1", httpMethod = "POST", response = UserOrderQueryDetailResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/mobileOrderOperate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult mobileOrderOperate(@RequestBody MobileStatusOperateReq mobileStatusOperateReq) throws Exception {
        logger.info("用户订单-订单执行查询 mobileOrderOperate param:{}", JSON.toJSONString(mobileStatusOperateReq));
        AppBaseResult res = null;
        switch (mobileStatusOperateReq.getOperateType()) {
            //1放弃订单;2发车;3订单失败、配送失败;4送达确认;5退回失败派件单
            case 1: {
                //1放弃订单
                res = mobileUserOrderService.giveUpOrder(mobileStatusOperateReq);
                break;
            }
            case 2: {
                //2发车
                res = mobileUserOrderService.depart(mobileStatusOperateReq);
                break;
            }
            case 3: {
                //3订单失败、配送失败
                res = mobileUserOrderService.orderFailure(mobileStatusOperateReq);
                break;
            }
            case 4: {
                //4送达确认
                res = mobileUserOrderService.deliveryConfirm(mobileStatusOperateReq);
                break;
            }
            case 5: {
                //5退回失败派件单
                res = mobileUserOrderService.returnFailureOrder(mobileStatusOperateReq);
                break;
            }
            default:
                break;
        }
        return res;
    }

    @ApiOperation(value = "获取距离范围内附近的站点接口-V1.0.1", httpMethod = "POST", response = UserOrderGetNearStationResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/getNearStation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public UserOrderGetNearStationResult getNearStation(@RequestBody GetNearStationReq getNearStationReq) throws Exception {
        logger.info("用户订单-获取距离范围内附近的站点查询 getNearStation param:{}", JSON.toJSONString(getNearStationReq));
        return mobileUserOrderService.getNearStation(getNearStationReq);
    }

    @ApiOperation(value = "批量指派接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/batchAssign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult batchAssign(@RequestBody BatchAssignReq batchAssignReq) throws Exception {
        logger.info("用户订单-批量指派 batchAssign param:{}", JSON.toJSONString(batchAssignReq));
        return  mobileUserOrderService.batchAssign(batchAssignReq);
    }

    @ApiOperation(value = "获取订单的支付信息接口-V1.0.1", httpMethod = "POST", response = UserOrderGetOrderPayStatusResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/getOrderPayStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public UserOrderGetOrderPayStatusResult getOrderPayStatus(@RequestBody GetOrderPayStatusReq getOrderPayStatusReq) throws Exception {
        logger.info("用户订单-获取订单的信息状态 getOrderPayStatus param:{}", JSON.toJSONString(getOrderPayStatusReq));
        return mobileUserOrderService.getOrderPayStatus(getOrderPayStatusReq);
    }
}
