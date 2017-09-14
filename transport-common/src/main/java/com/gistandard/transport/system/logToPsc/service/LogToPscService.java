package com.gistandard.transport.system.logToPsc.service;

import com.gistandard.transport.system.logToPsc.bean.OperateLog;

/**
 * Created by m on 2016/2/4.
 */
public interface LogToPscService {

	/**
	 * 发送操作日志到PSC系统
	 *
	 * @param opType
	 *            操作类型
	 * @param opContent
	 *            操作内容
	 * @param opResult
	 *            操作结果
	 * @param param
	 *            操作参数
	 * @param sysFlag
	 *            操作所属系统标志
	 */
	void sendOperateLogMessage(Integer opType, String opContent, Integer opResult, String param, String sysFlag);

	/**
	 * 发送操作日志到PSC系统
	 *	 *
	 * @param opType
	 *            操作帐号
	 * @param opType
	 *            操作类型
	 * @param opContent
	 *            操作内容
	 * @param opResult
	 *            操作结果
	 * @param param
	 *            操作参数
	 * @param sysFlag
	 *            操作所属系统标志
	 */
	void sendOperateLogMessage(String opUser, Integer opType, String opContent, Integer opResult, String param, String sysFlag);

	void sendLogMessage(final OperateLog operateLog);

}
