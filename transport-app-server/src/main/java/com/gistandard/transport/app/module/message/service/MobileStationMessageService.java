package com.gistandard.transport.app.module.message.service;

import com.gistandard.transport.app.module.message.bean.MessageQueryResultBean;
import com.gistandard.transport.base.bean.message.MobileStationMessageListReq;

/**
 * @author yjf
 * @ClassName MobileStationMessageService
 * @Description 我的、系统消息接口
 * @Version 1.0
 * @Date 2016-3-8
 */
public interface MobileStationMessageService {
	/**
	 * 根据帐号获取消息列表
	 * 
	 * @param mobileStationMessageListReq
	 * @return
	 */
	MessageQueryResultBean queryMessageList(MobileStationMessageListReq mobileStationMessageListReq);
}
