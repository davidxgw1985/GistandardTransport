package com.gistandard.transport.app.module.message.action;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.module.message.bean.AddCallInfoReq;
import com.gistandard.transport.app.module.message.bean.MessageQueryResultBean;
import com.gistandard.transport.app.module.message.service.CallInfoService;
import com.gistandard.transport.app.module.message.service.MobileStationMessageService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.tools.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: xgw
 * @ClassName: CallInfoController
 * @Date: 2017/2/14
 * @Description: 记录拨打电话记录
 */
@Controller
@RequestMapping(value = AppServerDefine.MY_CALL_URL)
public class CallInfoController extends BaseController {

    private static final String TAGS_DESC = "记录拨打电话模块";
    @Autowired
    private CallInfoService callInfoService;

    @ApiOperation(value = "记录通话信息-V1.0.1", tags = TAGS_DESC,httpMethod = "POST",
            response = AppBaseResult.class, produces = "application/json;charset=UTF-8")
    @RequestMapping(value = "/addCallInfo", method = RequestMethod.POST)
    @ResponseBody
    public AppBaseResult addCallInfo(@RequestBody AddCallInfoReq addCallInfoReq) throws Exception {
        AppBaseResult result = null;
        if (addCallInfoReq != null && !StringUtil.isEmpty(addCallInfoReq.getAccountId())) {
            result = callInfoService.addCallInfo(addCallInfoReq);
        } else {
            result = new AppBaseResult();
            result.setRetCode(SystemDefine.FAILURE);
            result.setRetMsg("请求参数不能为空！");
        }
        return result;
    }
}
