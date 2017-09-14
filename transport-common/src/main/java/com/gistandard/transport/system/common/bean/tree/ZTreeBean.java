package com.gistandard.transport.system.common.bean.tree;

import java.io.Serializable;

/**
 * Created by m on 2016/1/22.
 */
public class ZTreeBean implements Serializable {

	private static final long serialVersionUID = -1608059884670842156L;

	// 模块ID或者是操作类型数值
	private Integer id;

	// 所属模块数值
	private Integer pId;

	// 名称
	private String name;

	// 操作类型ID
	private Integer oId;

	// 类型：模块还是操作类型
	private Integer type;

	private boolean checked;

	private boolean open = true;

	private boolean disabled = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getoId() {
		return oId;
	}

	public void setoId(Integer oId) {
		this.oId = oId;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
