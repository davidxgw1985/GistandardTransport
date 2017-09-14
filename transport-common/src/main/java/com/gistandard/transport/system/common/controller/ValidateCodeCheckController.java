package com.gistandard.transport.system.common.controller;

import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = SystemDefine.VALIDATE_CODE_CHECK_URL)
public class ValidateCodeCheckController extends BaseController{

    /**
     * ajax校验验证码
     * @param validateCode
     */
    @RequestMapping(value = { "","/" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public void checkValidateCode(String validateCode) {
        String realValidateCode = StringUtil.getObjStr(getSession().getAttribute(
                SystemDefine.SESSION_ATTR_VALIDATE_CODE));
        boolean checkResult = false;
        if (StringUtils.isNotEmpty(realValidateCode) && StringUtils.isNotEmpty(validateCode)
                && realValidateCode.toUpperCase().equals(validateCode.toUpperCase())) {
            checkResult = true;
        }
        writeResultJson("", checkResult);
    }
}
