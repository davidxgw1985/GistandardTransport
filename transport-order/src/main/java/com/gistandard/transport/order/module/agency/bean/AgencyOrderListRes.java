package com.gistandard.transport.order.module.agency.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author
 * @ClassName AgencyOrderListRes
 * @Description 代理下单订单列表查询返回
 * @Version 1.0
 * @Date 2016/2/2
 */
@ApiModel(description = "自理下单-列表查询 返回对象", parent = AppBasePagerResult.class)
public class AgencyOrderListRes extends AppBasePagerResult{

	@ApiModelProperty(value = "自理下单-列表查询返回Bean", required = true)
	private List<AgencyOrderListBean> data;

	public AgencyOrderListRes(AppBaseRequest appBaseRequest) {
		super(appBaseRequest);
	}

	public List<AgencyOrderListBean> getData() {
		return data;
	}

	public void setData(List<AgencyOrderListBean> data) {
		this.data = data;
	}
}
