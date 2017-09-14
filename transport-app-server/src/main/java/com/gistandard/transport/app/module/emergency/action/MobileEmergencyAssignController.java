package com.gistandard.transport.app.module.emergency.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.common.emergency.bean.GetNearMsDataResult;
import com.gistandard.transport.system.common.emergency.bean.GetNearMsReq;
import com.gistandard.transport.system.common.emergency.service.MobileEmergencyAssignService;
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
 * @ClassName MobileEmergencyAssignController
 * @Description MobileStation用户紧急指派
 * @Version 3.0
 * @Date 2016-06-16
 */
@Controller
@RequestMapping(value = AppServerDefine.EMERGENCY_URL)
public class MobileEmergencyAssignController extends BaseController {
    private static final String TAGS_DESC = "用户紧急指派模块";

    private static final Logger logger = LoggerFactory.getLogger(MobileEmergencyAssignController.class);
    @Autowired
    private MobileEmergencyAssignService mobileEmergencyAssignService;



    /**
     * 获取距离范围内附近的MS接口（包含快递员、司机）
     *
     * @param jsonStr
     * @throws Exception
     */
    @ApiOperation(value = "获取距离范围内附近的MS接口（包含快递员、司机）-V1.0.1", httpMethod = "POST", response = GetNearMsDataResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "获取距离范围内附近的MS接口（包含快递员、司机）")
    @RequestMapping(value = "/queryUsersByLocation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public GetNearMsDataResult queryUsersByLocation(@RequestBody String jsonStr) throws Exception {
        logger.info("获取附近MS：queryUsersByLocation" + jsonStr);
        GetNearMsReq getNearMsReq = JSON.parseObject(jsonStr, GetNearMsReq.class);
        GetNearMsDataResult res = new GetNearMsDataResult();
        try {
            res = mobileEmergencyAssignService.queryUsersByLocation(getNearMsReq);
        } catch (MobileStationBizException e) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }
}
