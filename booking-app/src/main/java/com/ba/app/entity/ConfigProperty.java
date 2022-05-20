package com.ba.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * key : MobileNumberFirstDigitValidation  Value - Integer
 * 
 * */

@Entity
@Data
@Table(name = "t_config_property")
@NoArgsConstructor
public class ConfigProperty {

	@Id
	private String key;
	private String value;
}
