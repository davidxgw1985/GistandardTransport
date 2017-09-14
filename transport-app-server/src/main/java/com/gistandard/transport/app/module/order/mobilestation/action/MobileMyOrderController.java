package com.gistandard.transport.app.module.order.mobilestation.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.order.bean.MobileMyOrderDetailReq;
import com.gistandard.transport.app.dubbo.order.bean.MobileMyOrderListReq;
import com.gistandard.transport.app.dubbo.order.bean.QueryMyOrderDetailResult;
import com.gistandard.transport.app.dubbo.order.bean.QueryMyOrderListResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.userorder.BatchAssignReq;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.tools.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileMyOrderController
 * @Date: 2016/6/7
 * @Description: 订单管理-商户订单
 */
@Controller
@RequestMapping(value = AppServerDefine.MY_ORDER_URL)
public class MobileMyOrderController extends BaseController {

    private static final String TAGS_DESC = "商户订单";
    private static final Logger logger = LoggerFactory.getLogger(MobileMyOrderController.class);

    @Autowired
    private MobileMyOrderService mobileMyOrderService;


    @ApiOperation(value = "商户订单-商户订单列表 包含待接单、待执行、执行中、失败单、全部订单-V1.0.1", httpMethod = "POST",
            response = QueryMyOrderListResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/queryMyOrderList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryMyOrderListResult queryMyOrderList(@RequestBody MobileMyOrderListReq mobileMyOrderListReq) throws Exception {
        logger.info("商户订单-列表查询 queryMyOrderList param:{}", JSON.toJSONString(mobileMyOrderListReq));
        if (!StringUtil.isEmpty(mobileMyOrderListReq.getProductType())) {
            List<String> productTypeList = new ArrayList<String>();
            String[] s = mobileMyOrderListReq.getProductType().split(",");
            Collections.addAll(productTypeList, s);
            mobileMyOrderListReq.setProductTypeList(productTypeList);
        }
        return mobileMyOrderService.queryMyOrderList(mobileMyOrderListReq);
    }

    @ApiOperation(value = "商户订单-商户订单详细-V1.0.1", httpMethod = "POST",
            response = QueryMyOrderDetailResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/queryMyOrderDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryMyOrderDetailResult queryMyOrderDetail(@RequestBody MobileMyOrderDetailReq mobileMyOrderDetailReq) throws Exception {
        logger.info("商户订单-详细查询 queryMyOrderDetail param:{}", JSON.toJSONString(mobileMyOrderDetailReq));
        return mobileMyOrderService.queryMyOrderDetail(mobileMyOrderDetailReq);
    }

    @ApiOperation(value = "商户订单-扫描订单号订单详细查看-V1.0.1", httpMethod = "POST",
            response = QueryMyOrderDetailResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/queryOrderDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SMQueryOrderDetailResult queryOrderDetail(@RequestBody QueryOrderDetailReq queryOrderDetailReq) throws IllegalAccessException, MobileStationBizException, InvocationTargetException {
        logger.info("商户订单-扫描订单号订单详细查询 queryOrderDetail param:{}", JSON.toJSONString(queryOrderDetailReq));
        return mobileMyOrderService.queryOrderDetail(queryOrderDetailReq);
    }

    @ApiOperation(value = "商户订单-商户订单执行操作-V1.0.1", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/mobileOrderOperate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult mobileOrderOperate(@RequestBody MobileStatusOperateReq mobileStatusOperateReq) throws Exception {
        logger.info("商户订单-商户订单执行 mobileOrderOperate param:{}", JSON.toJSONString(mobileStatusOperateReq));
        AppBaseResult res = null;
        switch (mobileStatusOperateReq.getOperateType()) {
            //1放弃订单;2发车;3订单失败、配送失败;4送达确认;5退回失败派件单
            case 1: {
                //1放弃订单
                res = mobileMyOrderService.giveUpOrder(mobileStatusOperateReq);
                break;
            }
            case 2: {
                //2发车
                res = mobileMyOrderService.depart(mobileStatusOperateReq);
                break;
            }
            case 3: {
                //3订单失败、配送失败
                res = mobileMyOrderService.orderFailure(mobileStatusOperateReq);
                break;
            }
            case 4: {
                //4送达确认
                res = mobileMyOrderService.deliveryConfirm(mobileStatusOperateReq);
                break;
            }
            case 6: {
                //6重新指派
                res = mobileMyOrderService.cancleAssign(mobileStatusOperateReq);
                break;
            }
            default:
                break;
        }
        return res;
    }

    @ApiOperation(value = "MS批量指派(或广播)", httpMethod = "POST",
            response = CheckAssignOrderforbatchResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/batchMobileOrderAssign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public CheckAssignOrderforbatchResult batchMobileOrderAssign(@RequestBody BatchMobileOrderAssignReq batchMobileOrderAssignReq) throws Exception {
        logger.info("MS指派(或广播) mobileOrderAssign param:{}", JSON.toJSONString(batchMobileOrderAssignReq));
        //先校验入参列表，筛选错误数据
        CheckAssignOrderforbatchResult checkAssignOrderforbatchResult = mobileMyOrderService.checkAssignOrderforbatch(batchMobileOrderAssignReq);
        if (checkAssignOrderforbatchResult.getCheckAssignOrderforbatchSuccesses() != null
                && checkAssignOrderforbatchResult.getCheckAssignOrderforbatchSuccesses().size() > 0) {
            //对校验通过数据进行统一处理
            checkAssignOrderforbatchResult = mobileMyOrderService.doBatchMobileOrderAssign(checkAssignOrderforbatchResult.getCheckAssignOrderforbatchSuccesses(), batchMobileOrderAssignReq);
        }
        if (checkAssignOrderforbatchResult.getCheckAssignOrderforbatchSuccesses() == null
                || checkAssignOrderforbatchResult.getCheckAssignOrderforbatchSuccesses().size() == 0){
            checkAssignOrderforbatchResult.setRetCode(0);
            checkAssignOrderforbatchResult.setRetMsg("失败");
        }
        return checkAssignOrderforbatchResult;
    }

    @ApiOperation(value = "MS指派(或广播)", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/mobileOrderAssign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult mobileOrderAssign(@RequestBody MobileOrderAssignReq mobileOrderAssignReq) throws Exception {
        logger.info("MS指派(或广播) mobileOrderAssign param:{}", JSON.toJSONString(mobileOrderAssignReq));
        return mobileMyOrderService.assignOrder(mobileOrderAssignReq);
    }

    @ApiOperation(value = "广播单继续广播接口", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/broadcastOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult broadcastOrder(@RequestBody BroadcastOrderReq broadcastOrderReq) throws Exception {
        logger.info("广播单继续广播接口 broadcastOrder param:{}", JSON.toJSONString(broadcastOrderReq));
        AppBaseResult res = new AppBaseResult();
        res.setReqId(broadcastOrderReq.getReqId());
        mobileMyOrderService.broadcastOrder(broadcastOrderReq);
        return res;
    }

    @ApiOperation(value = "MS广播取消订单接口", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/cacelMobileBroadcastOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult cacelMobileBroadcastOrder(@RequestBody CacelMobileBroadcastOrderReq cacelMobileBroadcastOrderReq) throws Exception {
        logger.info("MS广播取消订单接口 cacelMobileBroadcastOrder param:{}", JSON.toJSONString(cacelMobileBroadcastOrderReq));
        AppBaseResult res = new AppBaseResult();
        mobileMyOrderService.cacelMobileBroadcastOrder(cacelMobileBroadcastOrderReq);
        return res;
    }

    @ApiOperation(value = "MS广播批量取消订单接口", httpMethod = "POST",
            response = BatchCacelMobileBroadcastOrderResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/batchCacelMobileBroadcastOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BatchCacelMobileBroadcastOrderResult batchCacelMobileBroadcastOrder(@RequestBody BatchCacelMobileBroadcastOrderReq batchCacelMobileBroadcastOrderReq) throws Exception {
        logger.info("MS广播批量取消订单接口 batchCacelMobileBroadcastOrder param:{}", JSON.toJSONString(batchCacelMobileBroadcastOrderReq));
        BatchCacelMobileBroadcastOrderResult result = new BatchCacelMobileBroadcastOrderResult();
        List<CacelMobileBroadcastOrderReq> failds = new ArrayList<>();
        for (CacelMobileBroadcastOrderReq cacelMobileBroadcastOrderReq : batchCacelMobileBroadcastOrderReq.getCacelMobileBroadcastOrderReqs()) {
            try {
                cacelMobileBroadcastOrderReq.setAccountId(batchCacelMobileBroadcastOrderReq.getAccountId());
                cacelMobileBroadcastOrderReq.setAcctUsername(batchCacelMobileBroadcastOrderReq.getAcctUsername());
                cacelMobileBroadcastOrderReq.setAppLoginInfo(batchCacelMobileBroadcastOrderReq.getAppLoginInfo());
                mobileMyOrderService.cacelMobileBroadcastOrder(cacelMobileBroadcastOrderReq);
            } catch (MobileStationBizException e) {
                failds.add(cacelMobileBroadcastOrderReq);
            }
        }
        result.setFaileds(failds);
        if (failds.size() == batchCacelMobileBroadcastOrderReq.getCacelMobileBroadcastOrderReqs().size()){
            result.setRetCode(0);
            result.setRetMsg("失败");
        }
        return result;
    }

    @ApiOperation(value = "批量指派接口", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/hubAssign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult hubAssign(@RequestBody BatchAssignReq batchAssignReq) throws Exception {
        logger.info("批量指派接口 hubAssign param:{}", JSON.toJSONString(batchAssignReq));
        return mobileMyOrderService.hubAssignOrder(batchAssignReq);
    }

    @ApiOperation(value = "批量更新货物信息", httpMethod = "POST",
            response = BatchModifyGoodsInfoResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/batchModifyGoodsInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BatchModifyGoodsInfoResult batchModifyGoodsInfo(@RequestBody BatchUpdateGoodsInfoReq batchUpdateGoodsInfoReq) throws Exception {
        logger.info("批量更新货物信息 batchModifyGoodsInfo param:{}", JSON.toJSONString(batchUpdateGoodsInfoReq));
        BatchModifyGoodsInfoResult res = new BatchModifyGoodsInfoResult(batchUpdateGoodsInfoReq);
        Validator validator = new Validator();
        for (MobileGoodsInfoReq goods : batchUpdateGoodsInfoReq.getGoodsInfoList()) {
            List<ConstraintViolation> ret = validator.validate(goods);
            if (ret.size() > 0) {
                logger.info("参数传递错误：" + ret.toString());
                res.setRetMsg(ret.get(0).getMessage());
                res.setRetCode(SystemDefine.FAILURE);
                return res;
            }
        }
        return mobileMyOrderService.batchModifyGoodsInfo(batchUpdateGoodsInfoReq);
    }

    @ApiOperation(value = "异常收件", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/exceptionReceive", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult exceptionReceive(@RequestBody ExceptionReceiveReq exceptionReceiveReq) throws Exception {
        logger.info("异常收件 exceptionReceive param:{}", JSON.toJSONString(exceptionReceiveReq));
        AppBaseResult res = new AppBaseResult();
        mobileMyOrderService.exceptionReceive(exceptionReceiveReq);
        return res;
    }

    @ApiOperation(value = "推送IM消息", httpMethod = "POST",
            response = BaseResBean.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/pushMessageIM", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResBean pushMessageIM(@RequestBody MsgIMReq msgIMReq) throws Exception {
        return mobileMyOrderService.pushMessageIM(msgIMReq);
    }

}
