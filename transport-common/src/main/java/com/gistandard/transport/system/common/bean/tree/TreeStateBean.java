package com.gistandard.transport.system.common.bean.tree;

import java.io.Serializable;

public class TreeStateBean implements Serializable{
	
	private static final long serialVersionUID = 418644768369625283L;
	
	private boolean checked;
	private boolean disabled;
	private boolean expanded;
	private boolean selected;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
