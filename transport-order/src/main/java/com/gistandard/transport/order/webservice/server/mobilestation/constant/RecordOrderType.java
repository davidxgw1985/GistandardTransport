package com.gistandard.transport.order.webservice.server.mobilestation.constant;

public enum RecordOrderType {
	
	ORDER_I(0, "I单"),ORDER_O(1, "O单");
	
	private Integer value;
	
	RecordOrderType(Integer value, String description){
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
