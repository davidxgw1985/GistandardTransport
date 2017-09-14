package com.gistandard.transport.system.common.bean.tree;

import java.io.Serializable;
import java.util.List;

/**
 * Created by m on 2016/1/22.
 */
public class TreeBean implements Serializable {

	private static final long serialVersionUID = -1608059884670842156L;

	private Integer id;

	private Integer pId;

	private Integer sort;

	private String text;

	private String href;

	private String icon;

	private String tags;

	private TreeStateBean state;

	private List<TreeBean> nodes;

	public TreeStateBean getState() {
		return state;
	}

	public void setState(TreeStateBean state) {
		this.state = state;
	}

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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<TreeBean> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeBean> nodes) {
		this.nodes = nodes;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
