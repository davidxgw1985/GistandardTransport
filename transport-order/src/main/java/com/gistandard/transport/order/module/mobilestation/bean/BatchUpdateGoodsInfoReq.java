package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: UpdateGoodsInfoReq
 * @Date: 2016/2/24
 * @Description: 新增货物或修改货物请求Bean
 */
public class BatchUpdateGoodsInfoReq extends AppBaseRequest {
	/** serialVersionUID*/
	private static final long serialVersionUID = 2549640856446695772L;
	private List<MobileGoodsInfoReq> goodsInfoList;
	private Integer orderId;//MobileBookingForm 主键
	public List<MobileGoodsInfoReq> getGoodsInfoList() {
		return goodsInfoList;
	}
	public void setGoodsInfoList(List<MobileGoodsInfoReq> goodsInfoList) {
		this.goodsInfoList = goodsInfoList;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
