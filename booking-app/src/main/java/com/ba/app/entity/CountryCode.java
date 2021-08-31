package com.ba.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_country_code")
public class CountryCode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String countryCode;
	private String countryDesc;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryDesc() {
		return countryDesc;
	}
	public void setCountryDesc(String countryDesc) {
		this.countryDesc = countryDesc;
	}



}
