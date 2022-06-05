package com.ba.app.mobile.util;

public enum CommonEnums {
ADMIN_USER("admin");
	private String value;
	
	private CommonEnums(String value) {
		this.value = value;
	}
	
	public String get() {
		return this.value;
	}

}
