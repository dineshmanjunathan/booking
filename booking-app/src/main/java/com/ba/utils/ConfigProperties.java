package com.ba.utils;

public enum ConfigProperties {
	MOBILE_F_D_V("MobileNumberFirstDigitValidation"),
	FREIGHT_CHARGES("FREIGHT"),
	LOADING_CHARGES("LOADING CHARGES"),	
	FUEL_CHARGES("FUEL CHARGES")	
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
