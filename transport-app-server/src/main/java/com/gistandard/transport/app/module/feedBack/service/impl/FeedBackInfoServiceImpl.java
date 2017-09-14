package com.gistandard.transport.app.module.feedBack.service.impl;

import java.util.Date;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.define.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gistandard.transport.app.module.feedBack.bean.FeedBackSaveReq;
import com.gistandard.transport.app.module.feedBack.service.FeedBackInfoService;
import com.gistandard.transport.base.entity.bean.FeedbackInfo;
import com.gistandard.transport.base.entity.dao.FeedbackInfoDao;
import com.gistandard.transport.base.exception.CustomerBizException;

/**
 * @author kongxm
 * @Description
 * @Version 1.0
 * @Date 2016/3/7
 */
@Service
public class FeedBackInfoServiceImpl implements FeedBackInfoService {

	@Autowired
	private FeedbackInfoDao feedbackInfoDao;

	@Override
	public AppBaseResult save(FeedBackSaveReq req) throws CustomerBizException {
		AppBaseResult appBaseResult = new AppBaseResult();
		appBaseResult.setReqId(req.getReqId());
		checkParam(req);
		FeedbackInfo feedbackInfo = new FeedbackInfo();
		feedbackInfo.setFeedbackContent(req.getContent());
		feedbackInfo.setFeedbackDate(new Date());
		feedbackInfo.setFeedbackSystem(req.getSystemFlag());
		feedbackInfo.setFeedbackStatus(0);// 处理状态：0未处理，1处理中，2已处理
		feedbackInfo.setFeedbackUser(req.getAccountId());
		int size = feedbackInfoDao.insertSelective(feedbackInfo);
		if (size <= 0) {
			appBaseResult.setRetCode(SysResult.FAILED.getValue());
			appBaseResult.setRetMsg("保存失败");
		}
		return appBaseResult;
	}

	private void checkParam(FeedBackSaveReq req) throws CustomerBizException {
		if (req.getAccountId() == null) {
			throw new CustomerBizException("accountId未传递");
		}
		if (req.getContent() == null) {
			throw new CustomerBizException("内容不能为空");
		}
		if (req.getSystemFlag() == null) {
			throw new CustomerBizException("系统标识不能为空");
		}
	}

}
