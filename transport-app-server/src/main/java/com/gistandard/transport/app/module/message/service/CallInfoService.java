package com.gistandard.transport.app.module.message.service;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.module.message.bean.AddCallInfoReq;

/**
 * @author xgw
 * @ClassName CallInfoService
 * @Description 通话信息接口
 * @Version 1.0
 * @Date 2017/2/14
 */
public interface CallInfoService {
	/**
	 * 记录通话信息
	 * 
	 * @param addCallInfoReq
	 * @return
	 */
	AppBaseResult addCallInfo(AddCallInfoReq addCallInfoReq);
}
