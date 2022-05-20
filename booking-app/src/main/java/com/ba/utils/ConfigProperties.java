package com.ba.utils;

public enum ConfigProperties {
	
	MOBILE_F_D_V("MobileNumberFirstDigitValidation"),
	;

	
	private String value;
	
	private ConfigProperties(String value) {
		this.value = value;
	}
	
	public String get() {
		return this.value;
	}
	
	public Boolean match(String matchValue) {
		return this.value.equals(matchValue);
	}

}
