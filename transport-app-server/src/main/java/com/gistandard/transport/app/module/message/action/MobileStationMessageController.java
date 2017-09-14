package com.gistandard.transport.app.module.message.action;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gistandard.transport.app.module.message.bean.MessageQueryResultBean;
import com.gistandard.transport.app.module.message.service.MobileStationMessageService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.bean.message.MobileStationMessageListReq;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.tools.util.StringUtil;

/**
 * @author yjf
 * @ClassName MobileStationMessageController
 * @Description 我的、系统消息
 * @Version 1.0
 * @Date 2016-3-8
 */
@Controller
@RequestMapping(value = AppServerDefine.MY_MESSAGE_URL)
public class MobileStationMessageController extends BaseController {

	@Autowired
	private MobileStationMessageService mobileStationMessageService;

	@ApiOperation(value = "获取我的消息-V1.0.1", httpMethod = "POST", response = MessageQueryResultBean.class, produces = "application/json;charset=UTF-8", notes = "获取我的消息，其中reqId安卓必填、账户ID必填！")
	@RequestMapping(value = "/queryMessageList", method = RequestMethod.POST)
	@ResponseBody
	public MessageQueryResultBean queryStationList(@RequestBody
	MobileStationMessageListReq mobileStationMessageListReq) throws Exception {
		MessageQueryResultBean result = null;
		if (mobileStationMessageListReq != null && !StringUtil.isEmpty(mobileStationMessageListReq.getAccountId())) {
			result = mobileStationMessageService.queryMessageList(mobileStationMessageListReq);
		} else {
			result = new MessageQueryResultBean();
			result.setRetCode(SystemDefine.FAILURE);
			result.setRetMsg("请求参数不能为空！");
		}
		return result;
	}

}
