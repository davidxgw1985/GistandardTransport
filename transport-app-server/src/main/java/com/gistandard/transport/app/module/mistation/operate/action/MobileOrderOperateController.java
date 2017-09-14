package com.gistandard.transport.app.module.mistation.operate.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysResult;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.operate.service.MobileOrderOperateService;
import com.gistandard.transport.order.module.mistation.operate.service.bean.MiAssignFleetReq;
import com.gistandard.transport.order.module.mistation.operate.service.bean.MiConfirmOrderReq;
import com.gistandard.transport.order.module.mistation.operate.service.bean.MiQueryOrderDetailResult;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.order.utils.OrderUtils;
import com.gistandard.transport.system.frame.batch.BatchFramer;
import com.gistandard.transport.system.frame.batch.Interceptor;
import com.gistandard.transport.system.frame.batch.bean.BaseBatchResult;
import io.swagger.annotations.ApiOperation;
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
import java.util.List;

/**
 * 咪站，订单操作
 * Created by m on 2016/10/13.
 */
@Controller
@RequestMapping(value = AppServerDefine.OPERATE_ORDER_URL)
public class MobileOrderOperateController {
    private final static Logger logger = LoggerFactory.getLogger(MobileOrderOperateController.class);

    private static final String TAGS_DESC = "咪站订单操作";
    @Autowired
    private MobileOrderOperateService mobileOrderOperateService;
    @Autowired
    private MobileMyOrderService mobileMyOrderService;
    @Autowired
    private BatchMiOrderAssignInterceptor batchMiOrderAssignInterceptor;
    @Autowired
    private BatchMiOrderAssignCompletor batchMiOrderAssignCompletor;

    @ApiOperation(value = "咪站-分拣出库-V1.0.1", httpMethod = "POST", response = AppBaseResult.class, tags = TAGS_DESC,
            produces = "application/json", notes = "咪站-分拣出库")
    @RequestMapping(value = "/deliveryConfirm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult sortStockOut(@RequestBody MobileStatusOperateReq mobileStatusOperateReq) throws Exception {
        logger.info("咪站-分拣出库 sortStockOut request: {}", JSON.toJSONString(mobileStatusOperateReq));
        AppBaseResult result = mobileOrderOperateService.sortStockOut(mobileStatusOperateReq);
        return result;
    }

    @ApiOperation(value = "咪站-分拣出库(批量)-V1.0.1", httpMethod = "POST", tags = TAGS_DESC,
            response = BatchMobileStatusOperateResult.class, produces = "application/json", notes = "咪站-分拣出库")
    @RequestMapping(value = "/batchDeliveryConfirm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BatchMobileStatusOperateResult batchSortStockOut(@RequestBody BatchMobileStatusOperateReq batchMobileStatusOperateReq) throws Exception {
        logger.info("咪站-分拣出库 batchSortStockOut request:{}", JSON.toJSONString(batchMobileStatusOperateReq));
        BatchMobileStatusOperateResult result = new BatchMobileStatusOperateResult();
        List<BatchMobileStatusOperateReqBean> batchMobileStatusOperateReqBeans = batchMobileStatusOperateReq.getBatchMobileStatusOperateReqBeans();
        List<BatchMobileStatusOperateResultBean> errorBeans = new ArrayList<>();
        for (BatchMobileStatusOperateReqBean batchMobileStatusOperateReqBean : batchMobileStatusOperateReqBeans) {
            try {
                QueryOrderDetailReq queryOrderDetailReq = new QueryOrderDetailReq();
                queryOrderDetailReq.setBusiBookNo(batchMobileStatusOperateReqBean.getBusiBookNo());
                queryOrderDetailReq.setBusiNoTag(batchMobileStatusOperateReqBean.getBusiNoTag());
                queryOrderDetailReq.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE);
                queryOrderDetailReq.setAccountId(batchMobileStatusOperateReq.getAccountId());
                queryOrderDetailReq.setAcctUsername(batchMobileStatusOperateReq.getAcctUsername());
                queryOrderDetailReq.setAppLoginInfo(batchMobileStatusOperateReq.getAppLoginInfo());
                queryOrderDetailReq.setRoleId(batchMobileStatusOperateReq.getRoleId());
                SMQueryOrderDetailResult smQueryOrderDetailResult = mobileMyOrderService.queryOrderDetail(queryOrderDetailReq);
                List<QueryOrderDetailBean> data = smQueryOrderDetailResult.getData();
                if (data == null || data.size() == 0)
                    throw new MobileStationBizException("此订单信息不存在");
                QueryOrderDetailBean queryOrderDetailBean = data.get(0);

                MobileStatusOperateReq mobileStatusOperateReq = OrderUtils.createMobileStatusOperateReq(batchMobileStatusOperateReq, batchMobileStatusOperateReqBean, queryOrderDetailBean);
                AppBaseResult res = mobileOrderOperateService.sortStockOut(mobileStatusOperateReq);
                if (res.getRetCode() == SysResult.FAILED.getValue()) {
                    BatchMobileStatusOperateResultBean batchMobileStatusOperateResultBean = new BatchMobileStatusOperateResultBean();
                    batchMobileStatusOperateResultBean.setBusiNoTag(batchMobileStatusOperateReqBean.getBusiNoTag());
                    batchMobileStatusOperateResultBean.setBusiBookNo(batchMobileStatusOperateReqBean.getBusiBookNo());
                    batchMobileStatusOperateResultBean.setMsg(res.getRetMsg());
                    errorBeans.add(batchMobileStatusOperateResultBean);
                }
            } catch (Exception e) {
                BatchMobileStatusOperateResultBean batchMobileStatusOperateResultBean = new BatchMobileStatusOperateResultBean();
                batchMobileStatusOperateResultBean.setBusiNoTag(batchMobileStatusOperateReqBean.getBusiNoTag());
                batchMobileStatusOperateResultBean.setBusiBookNo(batchMobileStatusOperateReqBean.getBusiBookNo());
                batchMobileStatusOperateResultBean.setMsg(e.getMessage());
                errorBeans.add(batchMobileStatusOperateResultBean);
            }
        }
        result.setBatchMobileStatusOperateResultBeans(errorBeans);
        return result;
    }

    @ApiOperation(value = "咪站-派车(或广播派车)", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/miOrderAssign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult miOrderAssign(@RequestBody MobileOrderAssignReq mobileOrderAssignReq) throws Exception {
        logger.info("咪站-派车(或广播派车) miOrderAssign param:{}", JSON.toJSONString(mobileOrderAssignReq));
        return mobileOrderOperateService.miOrderAssign(mobileOrderAssignReq);
    }

    @ApiOperation(value = "咪站-批量派车(或广播派车)", httpMethod = "POST",
            response = CheckAssignOrderforbatchResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/batchMiOrderAssign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public CheckAssignOrderforbatchResult batchMiOrderAssign(@RequestBody BatchMobileOrderAssignReq batchMobileOrderAssignReq) throws Exception {
        logger.info("咪站-批量派车(或广播派车) batchMiOrderAssign param:{}", JSON.toJSONString(batchMobileOrderAssignReq));
        //批量派车校验
        CheckAssignOrderforbatchResult result = mobileOrderOperateService.checkAssignOrderforbatch(batchMobileOrderAssignReq);
        if (result.getCheckAssignOrderforbatchSuccesses() != null && result.getCheckAssignOrderforbatchSuccesses().size() > 0) {
            //校验通过数据的统一处理
            result = mobileOrderOperateService.doBatchMiOrderAssign(result.getCheckAssignOrderforbatchSuccesses(), batchMobileOrderAssignReq);
        }
        if (result.getCheckAssignOrderforbatchSuccesses() == null || result.getCheckAssignOrderforbatchSuccesses().size() == 0) {
            result.setRetCode(0);
            result.setRetMsg("失败");
        }
        return result;
    }

    @ApiOperation(value = "咪站-批量派车(或广播派车)(暂未用,用于实验batch框架)", httpMethod = "POST",
            response = CheckAssignOrderforbatchResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/batchMiOrderAssignRX", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseBatchResult<AssignOrderforbatchSuccess> batchMiOrderAssignRX(@RequestBody BatchMobileOrderAssignReq batchMobileOrderAssignReq) throws Exception {
        logger.info("咪站-批量派车(或广播派车) batchMiOrderAssign param:{}", JSON.toJSONString(batchMobileOrderAssignReq));
        List<Interceptor> list = new ArrayList();
        list.add(batchMiOrderAssignInterceptor);
        //启用了batch框架
        BaseBatchResult<AssignOrderforbatchSuccess> result = BatchFramer.eachCheck(list, batchMobileOrderAssignReq, batchMiOrderAssignCompletor);
        if (result.getSuccessBeans() == null || result.getSuccessBeans().size() == 0) {
            result.setRetCode(0);
            result.setRetMsg("失败");
        }
        return result;
    }

    @ApiOperation(value = "咪站广播单继续广播接口", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/broadcastOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult broadcastOrder(@RequestBody BroadcastOrderReq broadcastOrderReq) throws Exception {
        logger.info("咪站广播单继续广播接口 broadcastOrder param:{}", JSON.toJSONString(broadcastOrderReq));
        AppBaseResult res = new AppBaseResult();
        res.setReqId(broadcastOrderReq.getReqId());
        mobileOrderOperateService.broadcastOrder(broadcastOrderReq);
        return res;
    }

    @ApiOperation(value = "商户订单-咪站重新指派-V1.0.1", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/cancleAssign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult cancleAssign(@RequestBody MobileStatusOperateReq mobileStatusOperateReq) throws Exception {
        logger.info("商户订单-咪站重新指派 cancleAssign param:{}", JSON.toJSONString(mobileStatusOperateReq));
        return mobileOrderOperateService.cancleAssign(mobileStatusOperateReq);
    }

    @ApiOperation(value = "商户订单-咪站批量重新指派-V1.0.1", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/batchCancleAssign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult batchCancleAssign(@RequestBody BatchCancleAssignReq batchCancleAssignReq) throws Exception {
        logger.info("商户订单-咪站批量重新指派 batchCancleAssign param:{}", JSON.toJSONString(batchCancleAssignReq));
        return mobileOrderOperateService.batchCancleAssign(batchCancleAssignReq);
    }

    @ApiOperation(value = "商户订单-咪站取消广播-V1.0.1", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/cacelMobileBroadcastOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult cacelMobileBroadcastOrder(@RequestBody CacelMobileBroadcastOrderReq cacelMobileBroadcastOrderReq) throws Exception {
        logger.info("商户订单-咪站取消广播 cacelMobileBroadcastOrder param:{}", JSON.toJSONString(cacelMobileBroadcastOrderReq));
        mobileOrderOperateService.cacelMobileBroadcastOrder(cacelMobileBroadcastOrderReq);
        return new AppBaseResult();
    }

    @ApiOperation(value = "商户订单-咪站批量取消广播-V1.0.1", httpMethod = "POST",
            response = BatchCacelMobileBroadcastOrderResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/batchCacelMobileBroadcastOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BatchCacelMobileBroadcastOrderResult batchCacelMobileBroadcastOrder(@RequestBody BatchCacelMobileBroadcastOrderReq batchCacelMobileBroadcastOrderReq) throws Exception {
        logger.info("商户订单-咪站批量取消广播 batchCacelMobileBroadcastOrder param:{}", JSON.toJSONString(batchCacelMobileBroadcastOrderReq));
        BatchCacelMobileBroadcastOrderResult result = new BatchCacelMobileBroadcastOrderResult();
        List<CacelMobileBroadcastOrderReq> failds = new ArrayList<>();
        for (CacelMobileBroadcastOrderReq cacelMobileBroadcastOrderReq : batchCacelMobileBroadcastOrderReq.getCacelMobileBroadcastOrderReqs()) {
            try {
                cacelMobileBroadcastOrderReq.setAccountId(batchCacelMobileBroadcastOrderReq.getAccountId());
                cacelMobileBroadcastOrderReq.setAcctUsername(batchCacelMobileBroadcastOrderReq.getAcctUsername());
                cacelMobileBroadcastOrderReq.setAppLoginInfo(batchCacelMobileBroadcastOrderReq.getAppLoginInfo());
                mobileOrderOperateService.cacelMobileBroadcastOrder(cacelMobileBroadcastOrderReq);
            } catch (MobileStationBizException e) {
                failds.add(cacelMobileBroadcastOrderReq);
            }
        }
        result.setFaileds(failds);
        if (failds.size() == batchCacelMobileBroadcastOrderReq.getCacelMobileBroadcastOrderReqs().size()) {
            result.setRetCode(0);
            result.setRetMsg("失败");
        }
        return result;
    }

    @ApiOperation(value = "商户订单-分拣出库打印订单详细查看-V1.0.1", httpMethod = "POST",
            response = MiQueryOrderDetailResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/queryOrderDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MiQueryOrderDetailResult queryOrderDetail(@RequestBody QueryOrderDetailReq queryOrderDetailReq) throws IllegalAccessException, MobileStationBizException, InvocationTargetException {
        logger.info("商户订单-扫描订单号订单详细查询 queryOrderDetail param:{}", JSON.toJSONString(queryOrderDetailReq));
        return mobileOrderOperateService.queryOrderDetail(queryOrderDetailReq);
    }

    @ApiOperation(value = "商户订单-客户自提-V1.0.1", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/getGoodsMySelf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult getGoodsMySelf(@RequestBody GetGoodsMySelfReq getGoodsMySelfReq) throws Exception {
        logger.info("商户订单-咪站客户自提 getGoodsMySelf param:{}", JSON.toJSONString(getGoodsMySelfReq));
        return mobileOrderOperateService.getGoodsMySelf(getGoodsMySelfReq);
    }

    @ApiOperation(value = "咪站确认车队竞价-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/miConfirmOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult miConfirmOrder(@RequestBody MiConfirmOrderReq miConfirmOrderReq) throws Exception {
        logger.debug("咪站-确认车队竞价 miConfirmOrder param:{}", JSON.toJSONString(miConfirmOrderReq));
        return mobileOrderOperateService.miConfirmOrder(miConfirmOrderReq);
    }

    @ApiOperation(value = "咪站本地交接给蛙站-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC, response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/miLocalHandover", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult miLocalHandover(@RequestBody MiConfirmOrderReq miConfirmOrderReq) throws Exception {
        logger.debug("咪站-咪站本地交接给蛙站 miLocalHandover param:{}", JSON.toJSONString(miConfirmOrderReq));
        return mobileOrderOperateService.miLocalHandover(miConfirmOrderReq);
    }

    @ApiOperation(value = "咪站-指派车队 车队强制接单-V1.0.1", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/miAssignFleet", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult miAssignFleet(@RequestBody MiAssignFleetReq miAssignFleetReq) throws Exception {
        logger.info("咪站-指派车队 车队强制接单 miAssignFleet param:{}", JSON.toJSONString(miAssignFleetReq));
        return mobileOrderOperateService.miAssignFleet(miAssignFleetReq);
    }
}
