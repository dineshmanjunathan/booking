package com.ba.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author USER
 *
 */
@Getter
@Setter
@Entity
@Table(name = "t_location")
public class Location implements Serializable {

	private static final long serialVersionUID = -7187348510206952329L;

	@Id
	private String id;
	private String location;
	private String address ;
	private Integer uploadingCharge;
	private LocalDateTime createon = LocalDateTime.now();
	private LocalDateTime updatedon = LocalDateTime.now();
	
	
}
