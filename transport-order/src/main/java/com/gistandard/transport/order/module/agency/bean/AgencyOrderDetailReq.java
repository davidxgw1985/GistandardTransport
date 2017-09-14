package com.gistandard.transport.order.module.agency.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

/**
 * @author: xgw
 * @ClassName: AgencyOrderDetailReq
 * @Date: 2016/6/27
 * @Description: 代理下单详细请求bean
 */
public class AgencyOrderDetailReq extends AppBaseRequest implements ValidTokenMark {

	private Integer mobileStationFormId;// 订单ID

	public Integer getMobileStationFormId() {
		return mobileStationFormId;
	}

	public void setMobileStationFormId(Integer mobileStationFormId) {
		this.mobileStationFormId = mobileStationFormId;
	}
}
