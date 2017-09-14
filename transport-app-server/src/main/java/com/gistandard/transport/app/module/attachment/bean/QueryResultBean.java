package com.gistandard.transport.app.module.attachment.bean;

import java.util.List;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.BizAttachment;

public class QueryResultBean extends AppBaseResult {

	private List<BizAttachment> data;
	
	private int recordCount;// 总条数

	public List<BizAttachment> getData() {
		return data;
	}

	public void setData(List<BizAttachment> data) {
		this.data = data;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

}
