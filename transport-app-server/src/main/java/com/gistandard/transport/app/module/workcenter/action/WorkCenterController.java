package com.gistandard.transport.app.module.workcenter.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.order.module.workcenter.bean.WorkCenterListReq;
import com.gistandard.transport.order.module.workcenter.bean.WorkCenterListResult;
import com.gistandard.transport.order.module.workcenter.bean.WorkCenterMapListReq;
import com.gistandard.transport.order.module.workcenter.bean.WorkCenterMapListResult;
import com.gistandard.transport.order.module.workcenter.service.WorkCenterService;
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
 * @author: xgw
 * @ClassName: WorkCenterController
 * @Date: 2017/4/6
 * @Description: 工作中心 模块
 */
@Controller
@RequestMapping(value = AppServerDefine.ORDER_WORK_CENTER_URL)
public class WorkCenterController extends BaseController {
    private static final String TAGS_DESC = "工作中心";
    private static final Logger logger = LoggerFactory.getLogger(WorkCenterController.class);

    @Autowired
    private WorkCenterService workCenterService;

    @ApiOperation(value = "工作中心-地图列表请求对象 -V1.0.1", httpMethod = "POST",
            response = WorkCenterMapListResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/queryMapOrderList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public WorkCenterMapListResult queryMapOrderList(@RequestBody WorkCenterMapListReq workCenterMapListReq) throws Exception {
        logger.info("工作中心-地图列表请求对象 queryMapOrderList param:{}", JSON.toJSONString(workCenterMapListReq));
        return workCenterService.queryMapOrderList(workCenterMapListReq);
    }

    @ApiOperation(value = "工作中心-列表请求对象 -V1.0.1", httpMethod = "POST",
            response = WorkCenterListResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/queryOrderList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public WorkCenterListResult queryOrderList(@RequestBody WorkCenterListReq workCenterListReq) throws Exception {
        logger.info("工作中心-列表请求对象 queryOrderList param:{}", JSON.toJSONString(workCenterListReq));
        return workCenterService.queryOrderList(workCenterListReq);
    }
}
