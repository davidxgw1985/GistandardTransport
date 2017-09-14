package com.gistandard.transport.app.module.order.mobilestation.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.BatchOperateReq;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.BatchOperateResult;
import com.gistandard.transport.order.module.mobilestation.service.MobileAcceptOrderService;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.gps.bean.*;
import com.gistandard.transport.system.gps.bean.DataResult;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.tools.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author xgw
 * @ClassName MobileAcceptOrderController
 * @Description MobileStation3.0 接单模块订单操作
 * @Version 3.0
 * @Date 2016-06-14
 */
@Controller
@RequestMapping(value = AppServerDefine.ACCEPT_ORDER_URL)
public class MobileAcceptOrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MobileAcceptOrderController.class);

    private static final String TAGS_DESC = "接单模块";

    @Autowired
    private MobileAcceptOrderService mobileAcceptOrderService;
    @Autowired
    private GpsOrderService gpsOrderService;

    @ApiOperation(value = "接单-指派单订单列表查询,分页参数-1  -V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = QueryOrderListRes.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/queryOrderList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryOrderListRes queryOrderList(@RequestBody MobileAcceptOrderListReq acceptOrderListReq) throws Exception {
        logger.debug("接单-列表查询 queryOrderList param:{}", JSON.toJSONString(acceptOrderListReq));
        if (!StringUtil.isEmpty(acceptOrderListReq.getProductType())) {
            List<String> productTypeList = new ArrayList<String>();
            String[] s = acceptOrderListReq.getProductType().split(",");
            Collections.addAll(productTypeList, s);
            acceptOrderListReq.setProductTypeList(productTypeList);
        }
        return mobileAcceptOrderService.queryOrderList(acceptOrderListReq);
    }

    @ApiOperation(value = "接单-抢单列表查询,分页参数-1  -V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = QueryGrabOrderListRes.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/queryGrabOrderList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryGrabOrderListRes queryGrabOrderList(@RequestBody MobileGrabOrderListReq grabOrderListReq) throws Exception {
        logger.debug("接单-抢单列表查询 queryGrabOrderList param:{}", JSON.toJSONString(grabOrderListReq));
        if (!StringUtil.isEmpty(grabOrderListReq.getProductType())) {
            List<String> productTypeList = new ArrayList<String>();
            String[] s = grabOrderListReq.getProductType().split(",");
            Collections.addAll(productTypeList, s);
            grabOrderListReq.setProductTypeList(productTypeList);
        }
        return mobileAcceptOrderService.queryGrabOrderList(grabOrderListReq);
    }

    @ApiOperation(value = "接单-订单详细查询-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = QueryOrderDetailResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/queryOrderDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryOrderDetailResult queryOrderDetail(@RequestBody MobileStationOrderDetailReq orderDetailReq) throws Exception {
        logger.debug("接单-详细查询 queryOrderDetail param:{}", JSON.toJSONString(orderDetailReq));
        return mobileAcceptOrderService.queryOrderDetail(orderDetailReq);
    }

    @ApiOperation(value = "接单操作-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/acceptOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult acceptOrder(@RequestBody MobileStationAcceptOrderReq acceptOrderReq) throws Exception {
        logger.debug("接单-接单 acceptOrder param:{}", JSON.toJSONString(acceptOrderReq));
        AppBaseResult baseResult;
        //接单前，先判断是否有GPS坐标上传，如果没有，不允许接单
//        DataResult dataResult = gpsOrderService.getGpsOnlineStatus(acceptOrderReq.getAcctUsername(), acceptOrderReq.getRoleId());
//        if (dataResult != null && !dataResult.isSucceed()) {
//            baseResult = new AppBaseResult(acceptOrderReq);
//            baseResult.setRetCode(SystemDefine.FAILURE);
//            baseResult.setRetMsg(dataResult.getMessage());
//            return baseResult;
//        }
        //接单前判断是否有商管指派单未完成，如果有未完成的，不允许接单
        int count = mobileAcceptOrderService.getMerchantOrderCount(acceptOrderReq.getAcctUsername(), acceptOrderReq.getRoleId());
        if (count > 0) {
            baseResult = new AppBaseResult(acceptOrderReq);
            baseResult.setRetCode(SystemDefine.FAILURE);
            baseResult.setRetMsg("您还有未处理的商管指派单，请处理完后再接单！");
            return baseResult;
        }
        baseResult = mobileAcceptOrderService.acceptOrder(acceptOrderReq);
        return baseResult;
    }

    @ApiOperation(value = "批量接单操作-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = MobileStationBatchAcceptOrderResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/batchAcceptOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MobileStationBatchAcceptOrderResult batchAcceptOrder(@RequestBody MobileStationBatchAcceptOrderReq acceptOrderReq) throws Exception {
        logger.debug("批量接单 batchAcceptOrder param:{}", JSON.toJSONString(acceptOrderReq));
        MobileStationBatchAcceptOrderResult res = new MobileStationBatchAcceptOrderResult(acceptOrderReq);
        //接单前，先判断是否有GPS坐标上传，如果没有，不允许接单
//        DataResult dataResult = gpsOrderService.getGpsOnlineStatus(acceptOrderReq.getAcctUsername(), acceptOrderReq.getRoleId());
//        if (dataResult != null && !dataResult.isSucceed()) {
//            res.setRetCode(SystemDefine.FAILURE);
//            res.setRetMsg(dataResult.getMessage());
//            return res;
//        }
        //接单前判断是否有商管指派单未完成，如果有未完成的，不允许接单
        int count = mobileAcceptOrderService.getMerchantOrderCount(acceptOrderReq.getAcctUsername(), acceptOrderReq.getRoleId());
        if (count > 0) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("您还有未处理的商管指派单，请处理完后再接单！");
            return res;
        }
        res.setData(mobileAcceptOrderService.batchAcceptOrder(acceptOrderReq));
        logger.debug("批量接单 batchAcceptOrder param:res{}", JSON.toJSONString(res));
        return res;
    }

    @ApiOperation(value = "拒绝订单-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/refuseOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult refuseOrder(@RequestBody MobileStatusOperateReq mobileStatusOperateReq) throws Exception {
        logger.debug("接单-拒绝订单 refuseOrder param:{}", JSON.toJSONString(mobileStatusOperateReq));
        return mobileAcceptOrderService.refuseOrder(mobileStatusOperateReq);
    }

    @ApiOperation(value = "批量拒绝订单-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/batchRefuseOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BatchRefuseOrderResult batchRefuseOrder(@RequestBody BatchRefuseOrderReq batchRefuseOrderReq) throws Exception {
        logger.debug("接单-批量拒绝订单 batchRefuseOrder param:{}", JSON.toJSONString(batchRefuseOrderReq));
        //数据校验
        BatchRefuseOrderResult batchRefuseOrderResult = mobileAcceptOrderService.checkBatchRefuseOrder(batchRefuseOrderReq);
        if (batchRefuseOrderResult.getSuccesses() != null && batchRefuseOrderResult.getSuccesses().size() > 0) {
            //校验通过数据集中处理
            mobileAcceptOrderService.doBatchRefuseOrder(batchRefuseOrderResult.getSuccesses());
        }
        if (batchRefuseOrderResult.getSuccesses() == null || batchRefuseOrderResult.getSuccesses().size() == 0) {
            batchRefuseOrderResult.setRetCode(0);
            batchRefuseOrderResult.setRetMsg("失败");
        }
        return batchRefuseOrderResult;
    }

    @ApiOperation(value = "查询推送规则-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = QueryPushRuleResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/queryPushRule", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryPushRuleResult queryPushRule(@RequestBody AppBaseRequest baseReqBean) throws Exception {
        logger.debug("接单-查询推送规则 queryPushRule param:{},", JSON.toJSONString(baseReqBean));
        return mobileAcceptOrderService.queryPushRule(baseReqBean);
    }


    @ApiOperation(value = "设置推送规则-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/setPushRule", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult setPushRule(@RequestBody MobileStationSetPushRuleReq setPushRuleReq) throws Exception {
        logger.debug("接单-设置推送规则 setPushRule param:{}", JSON.toJSONString(setPushRuleReq));
        return mobileAcceptOrderService.setPushRule(setPushRuleReq);
    }

    @ApiOperation(value = "设置推送规则状态-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/setPushStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult setPushStatus(@RequestBody SetPushStatusReq setPushStatusReq) throws Exception {
        logger.debug("接单-设置推送规则状态 setPushStatus param:{},", JSON.toJSONString(setPushStatusReq));
        return mobileAcceptOrderService.setPushStatus(setPushStatusReq);
    }

    @ApiOperation(value = "批量取消广播单操作-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = BatchOperateResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/batchCancelOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BatchOperateResult batchCancelOrder(@RequestBody BatchOperateReq batchOperateReq) throws Exception {
        logger.debug("批量取消广播单操作 batchCancelOrder param:{}", JSON.toJSONString(batchOperateReq));
        return mobileAcceptOrderService.batchCancelOrder(batchOperateReq);
    }
}
