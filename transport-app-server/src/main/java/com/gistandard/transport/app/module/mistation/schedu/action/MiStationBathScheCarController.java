package com.gistandard.transport.app.module.mistation.schedu.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.mistation.schedu.bean.MiBidInitiationReq;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.order.module.mistation.schedu.bean.ScheCarParamBean;
import com.gistandard.transport.order.module.mistation.schedu.bean.ScheCarResultBean;
import com.gistandard.transport.order.module.mistation.schedu.service.MiStationBathScheCarService;

/**
 * 
 * 咪站对订单进行批量排货派车
 * 
 * @author ShengHao
 * 
 */
@Controller
@RequestMapping(value = AppServerDefine.BATCH_SCHEDU_CAR_ORDER)
public class MiStationBathScheCarController {
	private static final String TAGS_DESC = "咪站-排货派车";
	private final static Logger logger = LoggerFactory.getLogger(MiStationBathScheCarController.class);

	@Autowired
	private MiStationBathScheCarService miStationBathScheCarService;

	@ApiOperation(value = "咪站对订单进行批量排货派车-V1.0.1", httpMethod = "POST", response = ScheCarResultBean.class,
			tags = TAGS_DESC,produces = "application/json", notes = "咪站对订单进行批量排货派车，其中待排货订单列表和列表中订单属性信息必填！")
	@RequestMapping(value = "/scheduleCarOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ScheCarResultBean scheduleCarOrder(@RequestBody ScheCarParamBean scheCarParamBean) throws Exception {
		logger.info("咪站对订单进行批量排货派车请求 scheduleCarOrder ：{}", JSON.toJSONString(scheCarParamBean));
		return miStationBathScheCarService.batchSchedule(scheCarParamBean);
	}

	@ApiOperation(value = "咪站对派车单发起竞价-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
			tags = TAGS_DESC,produces = "application/json", notes = "咪站对派车单发起竞价")
	@RequestMapping(value = "/miBidInitiation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AppBaseResult miBidInitiation(@RequestBody MiBidInitiationReq miBidInitiationReq) throws Exception {
		logger.info("咪站对派车单发起竞价 miBidInitiation ：{}", JSON.toJSONString(miBidInitiationReq));
		return miStationBathScheCarService.miBidInitiation(miBidInitiationReq);
	}

}
