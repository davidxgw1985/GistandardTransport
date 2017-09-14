package com.gistandard.transport.app.module.address.bean;

import java.util.List;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.system.common.bean.AddressQueryRes;

public class AddressQueryResultBean extends AppBaseResult {

	private List<AddressQueryRes> data;

	private Integer recordCount;

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public List<AddressQueryRes> getData() {
		return data;
	}

	public void setData(List<AddressQueryRes> data) {
		this.data = data;
	}

}
