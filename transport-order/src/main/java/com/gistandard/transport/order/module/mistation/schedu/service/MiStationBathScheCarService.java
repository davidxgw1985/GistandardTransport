package com.gistandard.transport.order.module.mistation.schedu.service;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.mistation.schedu.bean.MiBidInitiationReq;
import com.gistandard.transport.order.module.mistation.schedu.bean.ScheCarParamBean;
import com.gistandard.transport.order.module.mistation.schedu.bean.ScheCarResultBean;

/**
 * 
 * 咪站对订单进行批量排货派车服务
 * 
 * @author ShengHao
 * 
 */
public interface MiStationBathScheCarService {

	/**
	 * 批量排货派车<br>
	 * 
	 * 1、校验各个订单的起始地点和终点是否一致、并且起点是否为咪站、终点是否为蛙站<br>
	 * 2、调用Hub的批量排货派车接口 <br>
	 * 3、返回结果
	 * 
	 * @param scheCarParamBean
	 *            入参
	 * @return
	 */
	ScheCarResultBean batchSchedule(ScheCarParamBean scheCarParamBean) throws Exception;

	/**
	 * 咪站对派车单发起竞价 M-W
	 * 1、修改M-W 订单状态 改为12待报价
	 * 2、10分钟后，由中层选择价格最低 的车队报价 订单状态改为13已报价，如果没有报价，咪站状态改为40 可以重新发起竞价
	 * 3、咪站选择是否确认该车队报价，如果确认，订单状态改为26待出库 再生成一条M-W的车队订单状态为1已接单
	 * 4、如果咪站超过10分钟还未确认报价，默认流标状态改为40，可以重新发起竞价，
	 *
	 * @param miBidInitiationReq
	 *            入参
	 * @return
	 */
	AppBaseResult miBidInitiation(MiBidInitiationReq miBidInitiationReq) throws Exception;

}
