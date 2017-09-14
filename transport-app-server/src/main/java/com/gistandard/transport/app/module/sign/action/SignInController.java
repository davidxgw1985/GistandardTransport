package com.gistandard.transport.app.module.sign.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.order.customer.bean.PlaceAnOrderResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.bean.QRCodeReq;
import com.gistandard.transport.order.module.customer.bean.QRCodeRes;
import com.gistandard.transport.order.module.customer.bean.SignInReq;
import com.gistandard.transport.order.module.customer.bean.SignInRes;
import com.gistandard.transport.order.module.mobilestation.service.MobileSignInService;
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
 * 利用二维码信息进行签到
 * @author 员伟
 */
@Controller
@RequestMapping(value = AppServerDefine.SIGN_IN_URL)
@Api(value = "二维码签到模块", tags = "二维码签到模块" )
public class SignInController {


    private static final Logger logger = LoggerFactory.getLogger(SignInController.class);


    private static final String SIGNIN_TAGS_DESC = "签到模块";


    private static final String QRCODE_TAGS_DESC = "生成签到二维码模块";

    @Autowired
    private MobileSignInService mobileSignInService;

    @ResponseBody
    @ApiOperation(value = "签到模块接口-V1.0.1", httpMethod = "POST", response = SignInRes.class,
            tags = SIGNIN_TAGS_DESC, produces = "application/json", notes = "签到模块保存接口")
    @RequestMapping(value = "/addSignInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public SignInRes addSignInfo(@RequestBody SignInReq req) throws MobileStationBizException {
        logger.info("签到模块保存签到信息{}", JSON.toJSONString(req));
        return  mobileSignInService.addSignInfo(req);
    }


    @ResponseBody
    @ApiOperation(value = "获取生成签到内容的二维码信息", httpMethod = "POST",response = QRCodeRes.class,
            tags = QRCODE_TAGS_DESC,  produces = "application/json",notes = "获取生成签到内容的二维码信息")
    @RequestMapping(value = "/getQRCodeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public QRCodeRes getQRCodeInfo(@RequestBody QRCodeReq req) throws MobileStationBizException {
        logger.info("获取生成签到二维码信息req-{}", JSON.toJSONString(req));
        return  mobileSignInService.getQRCodeInfo(req);
    }



}
