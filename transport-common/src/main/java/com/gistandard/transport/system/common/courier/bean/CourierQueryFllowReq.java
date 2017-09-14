package com.gistandard.transport.system.common.courier.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

/**
 * 查询关注的快递请求
* @className  CourierQueryFllowReq 
* @describe  TODO
* @author  M.simple 
* @datetime  2016年4月11日 上午11:10:29
 */
public class CourierQueryFllowReq extends AppBasePagerRequest {

	/** 
	* @fieldName serialVersionUID
	* @describe TODO
	* @fieldType long
	*/ 
	private static final long serialVersionUID = 2004846000705793794L;
	
	/**
	 * 报价类别ID
	 */
	private Integer itemId;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
}
