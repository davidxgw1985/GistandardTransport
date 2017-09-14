package com.gistandard.transport.order.module.mistation.dispatch.service;

import com.gistandard.transport.order.module.mistation.dispatch.bean.BatchDispatchReq;
import com.gistandard.transport.order.module.mistation.dispatch.bean.BatchDispatchResultBean;
import com.gistandard.transport.order.module.mistation.dispatch.bean.DispatchParamBean;
import com.gistandard.transport.order.module.mistation.dispatch.bean.DispatchResultBean;

/**
 * 咪站签派服务
 * 
 * @author ShengHao
 * 
 */
public interface MiStationDispatchService {

	/**
	 * 签派指定站点服务
	 */
	DispatchResultBean dispatchStation(DispatchParamBean dispatchParamBean) throws Exception;

	/**
	 * 咪站批量中转接口
	 */
	BatchDispatchResultBean batchDispatch(BatchDispatchReq batchDispatchReq) throws Exception;

}
