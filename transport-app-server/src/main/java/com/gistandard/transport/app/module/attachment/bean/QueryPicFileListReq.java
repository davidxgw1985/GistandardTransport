package com.gistandard.transport.app.module.attachment.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: QueryPicFIleListReq
 * @Date: 2016/3/5
 * @Description: 送达确认附件列表查询
 */

@ApiModel(description = "附件查询接口参数对象", parent = AppBaseRequest.class)
public class QueryPicFileListReq extends AppBasePagerRequest {

	@ApiModelProperty(value = "订单Id",required = true, position = 1)
	private Integer orderId;// 订单编号

	@ApiModelProperty(value = "附件对应业务表的名称",position = 2)
	private String tableName;// 表名

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
