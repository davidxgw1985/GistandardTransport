package com.gistandard.transport.system.token.action;

import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.token.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yujie
 * @ClassName TokenController
 * @Description
 * @Version 1.0
 * @Date 2015-07-31
 */
@Controller
@RequestMapping(value = SystemDefine.TOKEN_MODULE_URL)
@springfox.documentation.annotations.ApiIgnore
public class TokenController extends BaseController {
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/getToken",  method = {RequestMethod.POST})
    public @ResponseBody void getTokenInfo(HttpServletRequest request){
        tokenService.newToken(request);
        writeResultJson(request.getAttribute(SystemDefine.REQUEST_TOKEN_NAME));
    }
}
