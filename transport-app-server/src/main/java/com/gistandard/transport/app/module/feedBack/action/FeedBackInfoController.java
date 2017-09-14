package com.gistandard.transport.app.module.feedBack.action;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gistandard.transport.app.module.feedBack.bean.FeedBackSaveReq;
import com.gistandard.transport.app.module.feedBack.service.FeedBackInfoService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.system.common.controller.BaseController;

/**
 * @author kongxm
 * @ClassName FeedBackInfoController
 * @Description 客户意见反馈
 * @Version 1.0
 * @Date 2016/1/29
 */
@Controller
@RequestMapping(value = AppServerDefine.FEED_BACK_URL)
@Api(value = "反馈信息接口", tags = "反馈信息接口")
public class FeedBackInfoController extends BaseController {

	@Autowired
	private FeedBackInfoService feedBackInfoService;

	@ApiOperation(value = "客户下单APP添加意见反馈-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
			produces = "application/json;charset=UTF-8", notes = "客户下单APP添加意见反馈，其中reqId安卓必填、accountId、内容、系统标识必填！")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public AppBaseResult add(@RequestBody FeedBackSaveReq req) throws CustomerBizException{
		return feedBackInfoService.save(req);
	}
}
