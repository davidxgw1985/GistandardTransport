package com.gistandard.transport.app.module.message.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gistandard.transport.app.module.message.bean.MessageQueryResultBean;
import com.gistandard.transport.app.module.message.service.MobileStationMessageService;
import com.gistandard.transport.base.bean.message.MobileStationMessageListReq;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.dao.ex.SysMessageInfoDaoEx;

/**
 * @author yjf
 * @ClassName MobileStationMessageServiceImpl
 * @Description 我的、系统消息实现
 * @Version 1.0
 * @Date 2016-3-8
 */
@Service
public class MobileStationMessageServiceImpl implements MobileStationMessageService {
	@Autowired
	private SysMessageInfoDaoEx sysMessageInfoDaoEx;

	@Override
	public MessageQueryResultBean queryMessageList(MobileStationMessageListReq mobileStationMessageListReq) {
		MessageQueryResultBean baseResPageBean = new MessageQueryResultBean();
		baseResPageBean.setRetCode(SystemDefine.SUCCESS);
		baseResPageBean.setReqId(mobileStationMessageListReq.getReqId());
		try {
			int resultSize = sysMessageInfoDaoEx.querySysMessageInfoByAccountIdCount(mobileStationMessageListReq);
			if (resultSize > 0) {
				baseResPageBean
						.setData(sysMessageInfoDaoEx.querySysMessageInfoByAccountId(mobileStationMessageListReq));
			}
			baseResPageBean.setRecordCount(resultSize);
		} catch (Exception e) {
			baseResPageBean.setRetCode(SystemDefine.FAILURE);
		}
		return baseResPageBean;
	}
}
