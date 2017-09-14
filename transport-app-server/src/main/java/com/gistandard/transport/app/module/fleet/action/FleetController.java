package com.gistandard.transport.app.module.fleet.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.fleet.bean.QueryFleetListReq;
import com.gistandard.transport.app.module.fleet.bean.QueryFleetListRes;
import com.gistandard.transport.app.module.fleet.service.FleetService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.exception.CustomerBizException;
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
 * @author xgw
 * @ClassName FleetController
 * @Description 车队信息
 * @Version 1.0
 * @Date 2017/7/18
 */
@Controller
@RequestMapping(value = AppServerDefine.FLEET_URL)
@Api(value = "车队信息接口", tags = "车队信息接口")
public class FleetController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(FleetController.class);
    @Autowired
    private FleetService fleetService;

    @ApiOperation(value = "车队列表查询-V1.0.1", httpMethod = "POST", response = QueryFleetListRes.class,
            produces = "application/json;charset=UTF-8", notes = "")
    @RequestMapping(value = "/queryFleetList", method = RequestMethod.POST)
    @ResponseBody
    public QueryFleetListRes queryFleetList(@RequestBody QueryFleetListReq req) throws CustomerBizException {
        logger.debug("车队列表查询 queryFleetList {}", JSON.toJSONString(req));
        return fleetService.queryFleetList(req);
    }
}
