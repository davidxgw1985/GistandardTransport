package com.gistandard.transport.app.module.message.bean;

import java.util.List;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.SysMessageInfo;

public class MessageQueryResultBean extends AppBaseResult {

	private List<SysMessageInfo> data;

	private int recordCount;// 总条数

	public List<SysMessageInfo> getData() {
		return data;
	}

	public void setData(List<SysMessageInfo> data) {
		this.data = data;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

}
