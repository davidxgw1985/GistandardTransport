package com.gistandard.transport.order.module.mistation.schedu.bean;

import java.math.BigDecimal;
import java.util.List;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.tools.annotation.BooleanObjectFieldNotNull;
import com.gistandard.transport.tools.annotation.ObjectFieldNotNull;

/**
 * 批量排货派车入参
 * 
 * @author Administrator
 * 
 */
public class ScheCarParamBean extends AppBaseRequest {

	// 待排货订单列表
	@ObjectFieldNotNull
	private List<WaitScheOrderInfoBean> waitScheOrderInfoList;

	public List<WaitScheOrderInfoBean> getWaitScheOrderInfoList() {
		return waitScheOrderInfoList;
	}

	public void setWaitScheOrderInfoList(List<WaitScheOrderInfoBean> waitScheOrderInfoList) {
		this.waitScheOrderInfoList = waitScheOrderInfoList;
	}
}
