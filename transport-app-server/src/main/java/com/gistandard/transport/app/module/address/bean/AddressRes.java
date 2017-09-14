package com.gistandard.transport.app.module.address.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author kongxm
 * @ClassName AddressRes
 * @Description
 * @Version 1.0
 * @Date 2016/2/3
 */
public class AddressRes {
	@ApiModelProperty(value = "新增地址簿的Id")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
