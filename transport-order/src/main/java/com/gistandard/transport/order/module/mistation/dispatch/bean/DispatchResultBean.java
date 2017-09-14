package com.gistandard.transport.order.module.mistation.dispatch.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * 签派请求执行结果bean
 * 
 * @author ShengHao
 * 
 */
public class DispatchResultBean extends AppBaseResult {

	private Integer dispatcherId;

	public Integer getDispatcherId() {
		return dispatcherId;
	}

	public void setDispatcherId(Integer dispatcherId) {
		this.dispatcherId = dispatcherId;
	}

}
