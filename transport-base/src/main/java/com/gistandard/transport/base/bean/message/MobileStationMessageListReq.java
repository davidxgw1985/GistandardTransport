package com.gistandard.transport.base.bean.message;

import com.gistandard.transport.base.bean.app.BaseReqPageBean;

/**
 * @author yjf
 * @ClassName MobileStationMessageListReq
 * @Description 系统消息请求bean
 * @Version 1.0
 * @Date 2016-3-8
 */
public class MobileStationMessageListReq extends BaseReqPageBean {
	private static final long serialVersionUID = -8920680405475871735L;
	private Integer accountId;

	@Override
	public Integer getAccountId() {
		return accountId;
	}

	@Override
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
}
