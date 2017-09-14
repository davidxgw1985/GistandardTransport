package com.gistandard.transport.app.module.order.upload.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.order.customer.bean.OrderQueryResult;
import com.gistandard.transport.order.module.customer.bean.OrderUploadRes;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.order.module.customer.CustomerOrderService;
import com.gistandard.transport.order.module.customer.bean.OrderQueryReq;
import com.gistandard.transport.order.module.upload.service.QueryUploadService;
import com.gistandard.transport.system.common.controller.BaseController;
import io.swagger.annotations.Api;
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
 * 订单上传信息控制器
 * @author 员伟
 * @date 2017-09-08
 */
@RequestMapping(value = AppServerDefine.QUERY_ORDER_UPLOAD)
@Controller
@Api(value = "同城运输下单模块", tags = "同城运输下单模块")
public class OrderUploadController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(OrderUploadController.class);

    @Autowired
    private QueryUploadService queryUploadService;


    @ResponseBody
    @ApiOperation(value = "根据订单和账号查询订单上传图片-V1.0.1", httpMethod = "POST", response = OrderQueryResult.class,
            produces = "application/json", notes = "根据订单和账号查询订单上传图片")
    @RequestMapping(value = "/queryPath", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public OrderUploadRes queryPath(@RequestBody OrderQueryReq req) {

        logger.debug("order upload queryPath req={}", JSON.toJSONString(req));
        OrderUploadRes res = new OrderUploadRes(req);
        try {
            queryUploadService.queryOrderUploadPath(req,res);
        } catch (Exception e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        logger.debug("order upload queryPath res={}", JSON.toJSONString(res));
        return res;
    }



}
