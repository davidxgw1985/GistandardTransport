package com.gistandard.transport.app.module.mistation.dispatch.action;

import com.gistandard.transport.order.module.mistation.dispatch.bean.BatchDispatchReq;
import com.gistandard.transport.order.module.mistation.dispatch.bean.BatchDispatchResultBean;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.order.module.mistation.dispatch.bean.DispatchParamBean;
import com.gistandard.transport.order.module.mistation.dispatch.bean.DispatchResultBean;
import com.gistandard.transport.order.module.mistation.dispatch.service.MiStationDispatchService;

/**
 * 
 * 咪站签派
 * 
 * @author ShengHao
 * 
 */
@Controller
@RequestMapping(value = AppServerDefine.DISPATCH_ORDER_URL)
public class MiStationDispatchController {

	private static final String TAGS_DESC = "咪站-中转";
	private final static Logger logger = LoggerFactory.getLogger(MiStationDispatchController.class);

	@Autowired
	private MiStationDispatchService miStationDispatchService;

	@ApiOperation(value = "咪站中转订单-V1.0.1", httpMethod = "POST", response = DispatchResultBean.class, produces = "application/json",
			tags = TAGS_DESC,notes = "咪站中转订单，busiNo需要签派的订单号，createUserId签派人账号ID，wHubId站点账号ID都必填！")
	@RequestMapping(value = "/dispatchStation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DispatchResultBean dispatchStation(@RequestBody DispatchParamBean dispatchParamBean) throws Exception {
		logger.info("咪站中转入参返回 dispatchStation ：{}", JSON.toJSONString(dispatchParamBean));
		DispatchResultBean bean = miStationDispatchService.dispatchStation(dispatchParamBean);
		logger.info("咪站中转最终返回：{}", JSON.toJSONString(bean));
		return bean;
	}

	@ApiOperation(value = "咪站批量中转订单-V1.0.1", httpMethod = "POST", response = BatchDispatchResultBean.class, produces = "application/json",
			tags = TAGS_DESC,notes = "咪站批量中转签派订单，busiNo需要签派的订单号，createUserId签派人账号ID，wHubId站点账号ID都必填！")
	@RequestMapping(value = "/batchDispatchStation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BatchDispatchResultBean batchDispatchStation(@RequestBody BatchDispatchReq batchDispatchReq) throws Exception {
		logger.info("咪站批量中转入参返回batchDispatchStation ：{}", JSON.toJSONString(batchDispatchReq));
		BatchDispatchResultBean bean = miStationDispatchService.batchDispatch(batchDispatchReq);
		logger.info("咪站批量中转最终返回：{}", JSON.toJSONString(bean));
		return bean;
	}
}
