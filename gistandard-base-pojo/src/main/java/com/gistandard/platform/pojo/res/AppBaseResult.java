package com.gistandard.platform.pojo.res;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by yujie on 2016/9/29.
 */
@ApiModel(description = "返回结果基类")
public class AppBaseResult implements Serializable{

	private static final long serialVersionUID = -2800408178676748983L;
	@ApiModelProperty(value = "请求结果:1为成功,其它为失败", required = true )
	private int retCode;// 返回代码 1为成功 其它为失败

	@ApiModelProperty(value = "返回信息", required = true)
	private String retMsg = "成功";// 返回信息

	@ApiModelProperty(value = "请求")
	private long reqId;// 请求ReqId

	public AppBaseResult() {
		this.retCode = 1;
	}

	public boolean reqResultFlag() {
		return retCode == 1;
	}

	public AppBaseResult(AppBaseRequest appBaseRequest) {
		this.retCode = 1;
		if (appBaseRequest != null) {
			this.reqId = appBaseRequest.getReqId();
		}
	}
	
	public AppBaseResult(int retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}
	

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public long getReqId() {
		return reqId;
	}

	public void setReqId(long reqId) {
		this.reqId = reqId;
	}
	
	

}
