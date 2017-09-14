package com.gistandard.transport.app.module.feedBack.service;

import com.gistandard.transport.app.module.feedBack.bean.FeedBackSaveReq;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.exception.CustomerBizException;

/**
 * @author kongxm
 * @Description
 * @Version 1.0
 * @Date 2016/3/7
 */
public interface FeedBackInfoService {

	AppBaseResult save(FeedBackSaveReq req) throws CustomerBizException;

}
