package com.gistandard.transport.app.module.address.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.ComTown;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "街道返回对象", parent = AppBaseResult.class)
public class TownCountyResultBean extends AppBaseResult {

	@ApiModelProperty(value = "街道返回对象")
	private List<ComTown> data;


	public List<ComTown> getData() {
		return data;
	}

	public void setData(List<ComTown> data) {
		this.data = data;
	}

}
