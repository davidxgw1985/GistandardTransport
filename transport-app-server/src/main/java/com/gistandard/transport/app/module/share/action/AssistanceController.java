package com.gistandard.transport.app.module.share.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.order.share.bean.AssistanceReq;
import com.gistandard.transport.order.share.bean.AssistanceRes;
import com.gistandard.transport.order.share.service.AssistanceService;
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
 * 服务共享模块
 * <p>提供快递员、咪站援助控制层<p/>
 * @author 员伟
 */
@Controller
@RequestMapping(value = AppServerDefine.ORDER_ASSISTANCE_URL)
@Api(value = "服务共享模块", tags = "提供快递员和咪站援助等服务功能")
public class AssistanceController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AssistanceController.class);

    private static final String ASSISTANCE_TAGS_DESC = "服务共享模块";

    @Autowired
    private AssistanceService assistanceService;


    @ResponseBody
    @ApiOperation(value = "提供快递员、咪站援助接口-V1.0.1", httpMethod = "POST", response = AssistanceRes.class,
            tags = ASSISTANCE_TAGS_DESC, produces = "application/json", notes = "提供快递员、咪站援助")
    @RequestMapping(value = "/apply", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AssistanceRes apply(@RequestBody AssistanceReq req) {
        logger.info("提供快递员、咪站援助--{}", JSON.toJSONString(req));
        return assistanceService.assistance4MiOrCourier(req);
    }




}
