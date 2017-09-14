package com.gistandard.transport.app.module.address.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "地址新增接口返回对象", parent = AppBaseResult.class)
public class AddressAddResultBean extends AppBaseResult {
	@ApiModelProperty(value = "地址新增接口返回数据")
	private AddressRes data;

	public AddressRes getData() {
		return data;
	}

	public void setData(AddressRes data) {
		this.data = data;
	}

}
