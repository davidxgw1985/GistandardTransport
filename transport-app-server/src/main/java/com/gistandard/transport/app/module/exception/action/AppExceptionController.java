package com.gistandard.transport.app.module.exception.action;

import com.gistandard.transport.app.module.exception.bean.RecordExceptionParam;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.SysExceptionLog;
import com.gistandard.transport.base.entity.service.SysExceptionLogService;
import com.gistandard.transport.system.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by yujie on 2016/9/29.
 */
@Controller
@RequestMapping(value = "/appException")
@Api(value = "App异常日志管理模块", tags = "App异常日志管理模块" )
public class AppExceptionController extends BaseController{

    @Autowired
    private SysExceptionLogService sysExceptionLogService;

    @ApiOperation(value = "异常记录接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "异常日志上传接口，参数包括异常信息、用户Id、应用名称、异常代码")
    @RequestMapping(value = "/recordException", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult recordException(@RequestBody RecordExceptionParam recordExceptionParam) {
        AppBaseResult baseResBean = new AppBaseResult();
        if (recordExceptionParam != null) {
            SysExceptionLog sysExceptionLog = new SysExceptionLog();
            sysExceptionLog.setExCause(recordExceptionParam.getExceptionInfo());
            if (StringUtils.isNotEmpty(recordExceptionParam.getUserId())) {
                sysExceptionLog.setExUser(recordExceptionParam.getUserId());
            }
            sysExceptionLog.setCreateTime(new Date());
            /*sysExceptionLog.setExMessage(exceptionReq.getExceptionCode());*/
            if (sysExceptionLog != null) {
                if(sysExceptionLogService.insert(sysExceptionLog) <1){
                    baseResBean.setReqId(recordExceptionParam.getReqId());
                    baseResBean.setRetCode(0);
                    baseResBean.setRetMsg("异常日志上传失败！");
                }
            }
        }else {
            baseResBean.setRetCode(0);
            baseResBean.setRetMsg("异常日志上传失败！");
        }
        return baseResBean;
    }
}
