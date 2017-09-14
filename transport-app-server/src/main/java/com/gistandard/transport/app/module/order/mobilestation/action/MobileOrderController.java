package com.gistandard.transport.app.module.order.mobilestation.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.*;
import com.gistandard.transport.order.module.mobilestation.service.MobileOrderService;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author: xgw
 * @ClassName: MobileOrderController
 * @Date: 2016/12/9
 * @Description: 订单管理
 */
@Controller
@RequestMapping(value = AppServerDefine.ORDER_MANAGE_URL)
public class MobileOrderController extends BaseController {
    private static final String TAGS_DESC = "订单管理";
    private static final Logger logger = LoggerFactory.getLogger(MobileOrderController.class);

    @Autowired
    private MobileOrderService mobileOrderService;


    @ApiOperation(value = "订单管理-列表查询 包含咪站、快递员、司机（用户单、商户单）-V1.0.1", httpMethod = "POST",
            response = MobileOrderListResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/queryOrderList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MobileOrderListResult queryOrderList(@RequestBody MobileOrderListReq mobileOrderListReq) throws Exception {
        logger.info("订单管理-列表查询(用户单、商户单) queryOrderList param:{}", JSON.toJSONString(mobileOrderListReq));
        return mobileOrderService.queryOrderList(mobileOrderListReq);
    }

    @ApiOperation(value = "订单管理-订单详细(用户单、商户单)-V1.0.1", httpMethod = "POST",
            response = MobileOrderDetailResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/queryOrderDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MobileOrderDetailResult queryOrderDetail(@RequestBody MobileOrderDetailReq mobileOrderDetailReq) throws Exception {
        logger.info("订单管理-订单详细(用户单、商户单) queryOrderDetail param:{}", JSON.toJSONString(mobileOrderDetailReq));
        return mobileOrderService.queryOrderDetail(mobileOrderDetailReq);
    }

    @ApiOperation(value = "订单管理-订单详细(用户单、商户单)  咪站-V1.0.1", httpMethod = "POST",
            response = MobileOrderDetailResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/queryMiOrderDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MobileOrderDetailResult queryMiOrderDetail(@RequestBody MobileOrderDetailReq mobileOrderDetailReq) throws Exception {
        logger.info("订单管理-订单详细(用户单、商户单) 咪站 queryMiOrderDetail param:{}", JSON.toJSONString(mobileOrderDetailReq));
        return mobileOrderService.queryMiOrderDetail(mobileOrderDetailReq);
    }

    @ApiOperation(value = "订单管理-订单详细(用户单、商户单)  司机-V1.0.1", httpMethod = "POST",
            response = MobileOrderDetailResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/queryDriverOrderDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MobileOrderDetailResult queryDriverOrderDetail(@RequestBody MobileOrderDetailReq mobileOrderDetailReq) throws Exception {
        logger.info("订单管理-订单详细(用户单、商户单) queryDriverOrderDetail param:{}", JSON.toJSONString(mobileOrderDetailReq));
        return mobileOrderService.queryDriverOrderDetail(mobileOrderDetailReq);
    }

    @ApiOperation(value = "MS批量发车", httpMethod = "POST",
            response = BatchOperateResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/batchDepart", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BatchOperateResult batchDepart(@RequestBody BatchOperateReq batchOperateReq) throws Exception {
        logger.info("MS批量发车 batchDepart param:{}", JSON.toJSONString(batchOperateReq));
        return mobileOrderService.batchDepart(batchOperateReq);
    }

    @ApiOperation(value = "MS批量送达确认", httpMethod = "POST",
            response = BatchOperateResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/batchDeliveryConfirm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BatchOperateResult batchDeliveryConfirm(@RequestBody BatchOperateReq batchOperateReq) throws Exception {
        logger.info("MS批量送达确认 batchDeliveryConfirm param:{}", JSON.toJSONString(batchOperateReq));
        return mobileOrderService.batchDeliveryConfirm(batchOperateReq);
    }

    @ApiOperation(value = "订单图片上传-上传订单附件", httpMethod = "POST",
            response = OrderPicUploadResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "订单图片上传")
    @RequestMapping(value = "/orderPicUpload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public OrderPicUploadResult orderPicUpload(MultipartHttpServletRequest request) throws Exception {
        logger.info("订单图片上传-上传订单附件 orderPicUpload ");
        return mobileOrderService.orderPicUpload(request);
    }

    /**
     * 修改订单支付方式
     *
     * @param updatePayInfoReq
     * @throws Exception
     */
    @ApiOperation(value = "修改订单支付方式", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/modifyOrderPayInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult modifyOrderPayInfo(@RequestBody UpdatePayInfoReq updatePayInfoReq) throws Exception {
        logger.info("修改订单支付方式 modifyOrderPayInfo:{}", JSON.toJSONString(updatePayInfoReq));
        return mobileOrderService.modifyOrderPayInfo(updatePayInfoReq);
    }
}
