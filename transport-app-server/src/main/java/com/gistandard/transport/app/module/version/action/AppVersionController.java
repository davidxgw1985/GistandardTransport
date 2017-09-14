package com.gistandard.transport.app.module.version.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.version.bean.CheckVersionParam;
import com.gistandard.transport.app.module.version.bean.CheckVersionResult;
import com.gistandard.transport.app.module.version.service.AppVersionService;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
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
 * Created by yujie on 2016/9/29.
 */
@Controller
@RequestMapping(value = AppServerDefine.VERSION_URL)
@Api(value = "APP版本管理", tags = "APP版本管理" )
public class AppVersionController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AppVersionController.class);
    @Autowired
    private AppVersionService appVersionService;

    @ApiOperation(value = "版本升级校验接口-V1.0.1", httpMethod = "POST", response = CheckVersionResult.class,
            produces = "application/json", notes = "参数[操作系统、项目名称、版本号不能为空]<br>" +
            "返回结果action{0：不需要升级1:需要升级}<br>" +
            "version:当前版本号<br>" +
            "forceUpdate{0：不需要强制升级1:需要强制升级}<br>" +
            "filePath:升级地址")
    @RequestMapping(value = "/checkAppVersion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public CheckVersionResult checkAppVersion(@RequestBody CheckVersionParam checkVersionParam) throws Exception {
        logger.info("版本升级校验接口 checkAppVersion param:{}", JSON.toJSONString(checkVersionParam));
        CheckVersionResult baseResBean = null;
        if(checkVersionParam != null){
            baseResBean = appVersionService.checkAppVersion(checkVersionParam);
        }
        logger.debug("版本升级校验接口 checkAppVersion res:{}", JSON.toJSONString(baseResBean));
        return baseResBean;
    }

}
