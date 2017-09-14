package com.gistandard.transport.base.entity.dao.ex;

import java.util.List;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.bean.message.MobileStationMessageListReq;
import com.gistandard.transport.base.entity.bean.SysMessageInfo;

/**
 * Created by m on 2016/3/8.
 */
@MyBatisRepository
public interface SysMessageInfoDaoEx {

	/**
	 * 根据帐号获取消息总数
	 * 
	 * @param mobileStationMessageListReq
	 * @return
	 */
	int querySysMessageInfoByAccountIdCount(MobileStationMessageListReq mobileStationMessageListReq);

	/**
	 * 根据帐号获取消息列表
	 * 
	 * @param mobileStationMessageListReq
	 * @return
	 */
	List<SysMessageInfo> querySysMessageInfoByAccountId(MobileStationMessageListReq mobileStationMessageListReq);
}
