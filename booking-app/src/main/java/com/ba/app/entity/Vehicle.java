package com.ba.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "t_vehicle")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = -7187348510206952329L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String vehicle;
	private String details ;
	private LocalDateTime createon = LocalDateTime.now();
	private LocalDateTime updatedon = LocalDateTime.now();
	
	
}
