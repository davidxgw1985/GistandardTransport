package com.gistandard.transport.app.module.attachment.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/3/7.
 */
@ApiModel(description = "附件上传参数对象", parent = AppBaseRequest.class)
public class PicFileAddReq extends AppBaseRequest {

	@ApiModelProperty(value = "附件要关联的业务信息表的Id",required = true, position = 1)
	private Integer id;

	@ApiModelProperty(value = "关联的业务表的类型(1、ms订单表，2、原始订单表，3、排车货物表)",required = true, position = 2)
	private Integer tableNameType;

	@ApiModelProperty(value = "上传文件类型",required = true, position = 3)
	private Integer uploadFileType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTableNameType() {
		return tableNameType;
	}

	public void setTableNameType(Integer tableNameType) {
		this.tableNameType = tableNameType;
	}

	public Integer getUploadFileType() {
		return uploadFileType;
	}

	public void setUploadFileType(Integer uploadFileType) {
		this.uploadFileType = uploadFileType;
	}
}
